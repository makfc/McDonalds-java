package com.mcdonalds.sdk.services.network;

import com.android.volley.toolbox.NetworkImageView;
import com.mcdonalds.sdk.AsyncCounter;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.connectors.ConnectorManager;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnectorUtils;
import com.mcdonalds.sdk.connectors.middleware.request.DCSApplicationSecurityRequest;
import com.mcdonalds.sdk.connectors.middleware.request.DCSAuthenticationRequest;
import com.mcdonalds.sdk.connectors.middleware.request.DCSRegistrationRequest;
import com.mcdonalds.sdk.connectors.middleware.request.DCSResetPasswordRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWNutritionRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSignInRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSignOutRequest;
import com.mcdonalds.sdk.connectors.middleware.response.DCSResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWSignInResponse;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.log.MCDLog;
import java.util.LinkedHashMap;
import java.util.List;

public class RequestManagerServiceConnection {
    private static final String CONNECTOR_KEY_MIDDLEWARE = "Middleware";
    private static final int ERROR_DCS_EXPIRED_TOKEN = 10022;
    private static final int ERROR_DCS_INVALID_TOKEN = 10018;
    private static final int ERROR_INVALID_TOKEN = -1037;
    private static final int MAX_TOKEN_RETRIES = 2;
    private static final String TAG = RequestManagerServiceConnection.class.getSimpleName();
    private final AsyncListener<CustomerProfile> mAuthenticateListener = new C26193();
    private final AsyncListener<List<Void>> mCompleteListener = new C26204();
    private LinkedHashMap<RequestProvider<?, ?>, ErrorCheckListenerWrapper<?>> mErrorRetryRequestQueue = new LinkedHashMap();
    private LinkedHashMap<String, ImageRequest> mImageRequestQueue = new LinkedHashMap();
    private RequestManager mManager;
    private boolean mReceivedInvalidTokenResponse = false;
    private ErrorCheckListenerWrapper mRetryListener = null;
    private RequestProvider mRetryRequest = null;
    private final AsyncListener<MWSignInResponse> mSignInListener = new C26182();
    private int mTokenErrorRetryCount = 0;
    private LinkedHashMap<RequestProvider<?, ?>, ErrorCheckListenerWrapper<?>> mTokenRequestQueue = new LinkedHashMap();

    /* renamed from: com.mcdonalds.sdk.services.network.RequestManagerServiceConnection$2 */
    class C26182 implements AsyncListener<MWSignInResponse> {
        C26182() {
        }

        public void onResponse(MWSignInResponse response, AsyncToken token, AsyncException exception) {
            SessionManager.getInstance().setRefreshing(false);
            if (exception == null) {
                RequestManagerServiceConnection.this.processTokenQueue();
            } else {
                RequestManagerServiceConnection.this.failAllQueuedRequests();
            }
        }
    }

    /* renamed from: com.mcdonalds.sdk.services.network.RequestManagerServiceConnection$3 */
    class C26193 implements AsyncListener<CustomerProfile> {
        C26193() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            SessionManager.getInstance().setRefreshing(false);
            if (exception == null) {
                RequestManagerServiceConnection.this.processTokenQueue();
            } else {
                RequestManagerServiceConnection.this.failAllQueuedRequests();
            }
        }
    }

    /* renamed from: com.mcdonalds.sdk.services.network.RequestManagerServiceConnection$4 */
    class C26204 implements AsyncListener<List<Void>> {
        C26204() {
        }

        public void onResponse(List<Void> list, AsyncToken token, AsyncException exception) {
            if (!SessionManager.getInstance().isTokenAuthenticated() && ((MiddlewareConnector) ConnectorManager.getConnector(RequestManagerServiceConnection.CONNECTOR_KEY_MIDDLEWARE)) != null) {
                MWSignOutRequest request = new MWSignOutRequest(SessionManager.getInstance().getToken());
                SessionManager.getInstance().clearToken();
                RequestManagerServiceConnection.this.mManager.processRequest(request, null);
            }
        }
    }

    class AsyncListenerWrapper<T> implements AsyncListener<T> {
        private AsyncCounter<Void> mCounter;
        private AsyncListener<T> mOrigin;

        public AsyncListenerWrapper(AsyncListener<T> origin, AsyncCounter<Void> counter) {
            this.mOrigin = origin;
            this.mCounter = counter;
        }

        public void onResponse(T response, AsyncToken token, AsyncException exception) {
            this.mCounter.success(null);
            if (this.mOrigin != null) {
                this.mOrigin.onResponse(response, token, exception);
            }
        }
    }

    class ErrorCheckListenerWrapper<T> implements AsyncListener<T> {
        private AsyncListener<T> mOrigin;
        private RequestProvider mRequestProvider;

        public ErrorCheckListenerWrapper(RequestProvider requestProvider, AsyncListener<T> origin) {
            this.mRequestProvider = requestProvider;
            this.mOrigin = origin;
        }

        public AsyncListener<T> getOrigin() {
            return this.mOrigin;
        }

        public void onResponse(T response, AsyncToken token, AsyncException exception) {
            boolean notifyOriginListener = true;
            if (exception == null && ((response instanceof MWJSONResponse) || (response instanceof DCSResponse))) {
                notifyOriginListener = RequestManagerServiceConnection.this.processResponseForRequest(this.mRequestProvider, this, response);
            }
            if (exception != null) {
                exception.setRequestUrl(this.mRequestProvider.getURLString());
            }
            if (notifyOriginListener && this.mOrigin != null) {
                this.mOrigin.onResponse(response, token, exception);
            }
        }
    }

    class ImageRequest {
        int defaultImageResId;
        int errorImageResId;
        NetworkImageView networkImageView;
        String url;

        public ImageRequest(String url, NetworkImageView networkImageView, int defaultImageResId, int errorImageResId) {
            this.url = url;
            this.networkImageView = networkImageView;
            this.defaultImageResId = defaultImageResId;
            this.errorImageResId = errorImageResId;
        }
    }

    RequestManagerServiceConnection(RequestManager manager) {
        this.mManager = manager;
    }

    private synchronized void processTokenQueue() {
        SessionManager manager = SessionManager.getInstance();
        final String sessionToken = manager.getToken();
        if (manager.isRefreshing() || sessionToken.isEmpty()) {
            MCDLog.error(TAG, "Refreshing token error during token queue flush.");
        } else if (this.mReceivedInvalidTokenResponse) {
            this.mManager.processRequest(this.mRetryRequest, this.mRetryListener);
        } else {
            final boolean shouldDeleteSessionOnComplete = (SessionManager.getInstance().isTokenAuthenticated() || MiddlewareConnectorUtils.isUsingECP3()) ? false : true;
            AsyncCounter<Void> asyncCounter = new AsyncCounter(this.mTokenRequestQueue.size() + this.mErrorRetryRequestQueue.size(), new AsyncListener<List<Void>>() {
                public void onResponse(List<Void> list, AsyncToken token, AsyncException exception) {
                    if (shouldDeleteSessionOnComplete && ((MiddlewareConnector) ConnectorManager.getConnector(RequestManagerServiceConnection.CONNECTOR_KEY_MIDDLEWARE)) != null) {
                        RequestManagerServiceConnection.this.mManager.processRequest(new MWSignOutRequest(sessionToken), null);
                    }
                }
            });
            LinkedHashMap<RequestProvider, ErrorCheckListenerWrapper> temp = (LinkedHashMap) this.mErrorRetryRequestQueue.clone();
            this.mErrorRetryRequestQueue.clear();
            for (RequestProvider request : temp.keySet()) {
                SessionManager.getInstance().updateRequestToken(sessionToken, request);
                processRequest(request, new AsyncListenerWrapper(((ErrorCheckListenerWrapper) temp.get(request)).getOrigin(), asyncCounter));
            }
            temp = (LinkedHashMap) this.mTokenRequestQueue.clone();
            this.mTokenRequestQueue.clear();
            for (RequestProvider request2 : temp.keySet()) {
                SessionManager.getInstance().updateRequestToken(sessionToken, request2);
                processRequest(request2, new AsyncListenerWrapper(((ErrorCheckListenerWrapper) temp.get(request2)).getOrigin(), asyncCounter));
            }
            if (shouldDeleteSessionOnComplete) {
                SessionManager.getInstance().clearToken();
            }
        }
    }

    private synchronized boolean processResponseForRequest(RequestProvider provider, ErrorCheckListenerWrapper listener, Object response) {
        boolean requestSuccessful;
        if (SessionManager.getInstance().isTokenNeeded(provider) && (getResultCode(response) == ERROR_INVALID_TOKEN || getResultCode(response) == ERROR_DCS_INVALID_TOKEN || getResultCode(response) == ERROR_DCS_EXPIRED_TOKEN)) {
            if (!this.mReceivedInvalidTokenResponse) {
                this.mReceivedInvalidTokenResponse = true;
                this.mRetryRequest = provider;
                this.mRetryListener = listener;
                updateToken();
            } else if (provider == this.mRetryRequest) {
                this.mTokenErrorRetryCount++;
                if (this.mTokenErrorRetryCount >= 2) {
                    failAllQueuedRequests();
                    resetRetryState();
                } else {
                    updateToken();
                }
            } else {
                this.mErrorRetryRequestQueue.put(provider, listener);
            }
            requestSuccessful = false;
        } else {
            requestSuccessful = true;
            if (this.mReceivedInvalidTokenResponse) {
                resetRetryState();
                processTokenQueue();
            }
        }
        return requestSuccessful;
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void addRequestToQueue(RequestProvider provider, AsyncListener listener) {
        ErrorCheckListenerWrapper wrapper = new ErrorCheckListenerWrapper(provider, listener);
        if (this.mReceivedInvalidTokenResponse) {
            this.mErrorRetryRequestQueue.put(provider, wrapper);
        } else {
            this.mTokenRequestQueue.put(provider, wrapper);
        }
    }

    private void failAllQueuedRequests() {
        ErrorCheckListenerWrapper listener;
        if (this.mRetryRequest != null) {
            this.mRetryListener.getOrigin().onResponse(createTokenFailureResponse(this.mRetryRequest), null, null);
        }
        LinkedHashMap<RequestProvider, ErrorCheckListenerWrapper> temp = (LinkedHashMap) this.mErrorRetryRequestQueue.clone();
        this.mErrorRetryRequestQueue.clear();
        for (RequestProvider request : temp.keySet()) {
            listener = (ErrorCheckListenerWrapper) temp.get(request);
            listener.getOrigin().onResponse(createTokenFailureResponse(request), null, null);
        }
        temp = (LinkedHashMap) this.mTokenRequestQueue.clone();
        this.mTokenRequestQueue.clear();
        for (RequestProvider request2 : temp.keySet()) {
            listener = (ErrorCheckListenerWrapper) temp.get(request2);
            listener.getOrigin().onResponse(createTokenFailureResponse(request2), null, null);
        }
    }

    private MWJSONResponse createTokenFailureResponse(RequestProvider request) {
        MWJSONResponse response = null;
        try {
            response = (MWJSONResponse) request.getResponseClass().newInstance();
            response.setResultCode(ERROR_INVALID_TOKEN);
            response.setData(null);
            return response;
        } catch (InstantiationException e) {
            MCDLog.error(TAG, "Token createTokenFailureResponse failure.");
            return response;
        } catch (IllegalAccessException e2) {
            MCDLog.error(TAG, "Token createTokenFailureResponse failure.");
            return response;
        } catch (ClassCastException e3) {
            MCDLog.error(TAG, "Token createTokenFailureResponse failure.");
            return response;
        }
    }

    private void resetRetryState() {
        this.mReceivedInvalidTokenResponse = false;
        this.mRetryRequest = null;
        this.mRetryListener = null;
        this.mTokenErrorRetryCount = 0;
    }

    private static int getResultCode(Object response) {
        if (response instanceof MWJSONResponse) {
            return ((MWJSONResponse) response).getResultCode();
        }
        if (response instanceof DCSResponse) {
            return ((DCSResponse) response).getStatusCode();
        }
        return -1;
    }

    public void processRequest(RequestProvider provider, AsyncListener listener) {
        if (Configuration.getSharedInstance().getBooleanForKey("log.network") && Configuration.getSharedInstance().getBooleanForKey("log.logsToConsole")) {
            MCDLog.debug(provider.toString() == null ? "" : provider.toString());
        }
        boolean isTokenNeeded = SessionManager.getInstance().isTokenNeeded(provider);
        if (isTokenNeeded && SessionManager.getInstance().isAuthenticationNeeded(provider) && !SessionManager.getInstance().isTokenAuthenticated()) {
            listener.onResponse(null, null, new AsyncException(McDonalds.getContext().getString(C3883R.string.auto_login_fail_message)));
        } else if (!isTokenNeeded || (provider instanceof MWSignInRequest) || (provider instanceof DCSAuthenticationRequest) || (provider instanceof DCSRegistrationRequest) || (provider instanceof DCSApplicationSecurityRequest) || (provider instanceof DCSResetPasswordRequest) || (provider instanceof MWSignOutRequest) || (provider instanceof MWNutritionRequest)) {
            this.mManager.processRequest(provider, listener);
        } else if (SessionManager.getInstance().isRefreshing()) {
            addRequestToQueue(provider, listener);
        } else if (tokenRefreshRequired(provider)) {
            updateToken();
            addRequestToQueue(provider, listener);
        } else {
            this.mManager.processRequest(provider, new ErrorCheckListenerWrapper(provider, listener));
        }
    }

    private boolean tokenRefreshRequired(RequestProvider provider) {
        if (SessionManager.getInstance().getToken().isEmpty()) {
            return true;
        }
        if (!SessionManager.getInstance().isAuthenticationNeeded(provider)) {
            return false;
        }
        if (SessionManager.getInstance().isTokenAuthenticated()) {
            return false;
        }
        return true;
    }

    private boolean isCustomerLoggedIn() {
        CustomerModule module = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        return module != null && module.isLoggedIn();
    }

    private void updateToken() {
        SessionManager manager = SessionManager.getInstance();
        MiddlewareConnector connector = (MiddlewareConnector) ConnectorManager.getConnector(CONNECTOR_KEY_MIDDLEWARE);
        if (connector != null) {
            manager.clearToken();
            manager.setRefreshing(true);
            if (isCustomerLoggedIn()) {
                CustomerProfile profile = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentProfile();
                if (profile != null) {
                    connector.authenticate(AuthenticationParameters.fromProfile(profile), this.mAuthenticateListener);
                    return;
                }
                return;
            }
            connector.signIn(this.mSignInListener);
        }
    }

    public void processUrlIntoNetworkImageView(String url, NetworkImageView networkImageView, int defaultImageResId, int errorImageResId) {
        if (this.mManager != null) {
            MCDLog.debug(url);
            this.mManager.processUrlIntoNetworkImageView(url, networkImageView, defaultImageResId, errorImageResId);
            return;
        }
        this.mImageRequestQueue.put(url, new ImageRequest(url, networkImageView, defaultImageResId, errorImageResId));
    }
}
