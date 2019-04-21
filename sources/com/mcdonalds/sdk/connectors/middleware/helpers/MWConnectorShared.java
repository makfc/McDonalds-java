package com.mcdonalds.sdk.connectors.middleware.helpers;

import android.content.Context;
import android.util.SparseArray;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.middleware.MWException;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWAppParameter;
import com.mcdonalds.sdk.connectors.middleware.request.DCSApplicationSecurityRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetAppParametersRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSignInRequest;
import com.mcdonalds.sdk.connectors.middleware.response.DCSApplicationSecurityResponse;
import com.mcdonalds.sdk.connectors.middleware.response.DCSApplicationSecurityResponse.DCSApplicationSecurityDetails;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetAppParametersResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWSignInData;
import com.mcdonalds.sdk.connectors.middleware.response.MWSignInResponse;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.AppParameters;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;
import com.mcdonalds.sdk.services.network.SessionManager;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MWConnectorShared {
    private List<MWAppParameter> mAppParams;
    private MiddlewareConnector mBaseConnector;
    private MWCatalogManager mCatalogManager;
    private SparseArray<Store> storeCache;

    /* renamed from: com.mcdonalds.sdk.connectors.middleware.helpers.MWConnectorShared$3 */
    class C39033 implements AsyncListener<MWGetAppParametersResponse> {
        C39033() {
        }

        public void onResponse(MWGetAppParametersResponse response, AsyncToken token, AsyncException exception) {
            if (response != null && !ListUtils.isEmpty((Collection) response.getData())) {
                MWConnectorShared.this.setAppParams((List) response.getData());
            }
        }
    }

    public enum ImageSize {
        SMALL,
        LARGE
    }

    public MWConnectorShared() {
        this.mBaseConnector = null;
        this.mAppParams = new ArrayList();
    }

    public MWConnectorShared(MiddlewareConnector connector) {
        this.mBaseConnector = null;
        this.mBaseConnector = connector;
        this.mAppParams = new ArrayList();
        this.mCatalogManager = new MWCatalogManager(this);
    }

    public static String getFullImagePath(String baseUrl, ImageSize imageSize) {
        int extensionIndex = baseUrl.lastIndexOf(".");
        String base = baseUrl.substring(0, extensionIndex);
        String size = imageSize == ImageSize.LARGE ? "_270" : "_180";
        String extension = baseUrl.substring(extensionIndex);
        return String.format("%s%s%s", new Object[]{base, size, extension});
    }

    public RequestManagerServiceConnection getNetworkConnection() {
        return this.mBaseConnector.getNetworkConnection();
    }

    public Context getContext() {
        return this.mBaseConnector.getContext();
    }

    public String getAppParameter(String key) {
        if (this.mAppParams != null) {
            for (MWAppParameter appParam : this.mAppParams) {
                if (key.equals(appParam.name)) {
                    return appParam.value;
                }
            }
        }
        return null;
    }

    public void setAppParams(List<MWAppParameter> appParams) {
        this.mAppParams = appParams;
        toAppParameters();
    }

    public MiddlewareConnector getBaseConnector() {
        return this.mBaseConnector;
    }

    public void setBaseConnector(MiddlewareConnector baseConnector) {
        this.mBaseConnector = baseConnector;
    }

    public List<MWAppParameter> getAppParams() {
        return this.mAppParams;
    }

    public MWCatalogManager getCatalogManager() {
        return this.mCatalogManager;
    }

    public AsyncToken signIn(AsyncListener<MWSignInResponse> listener) {
        return signIn(listener, false);
    }

    public AsyncToken signIn(final AsyncListener<MWSignInResponse> listener, boolean useDCS) {
        final AsyncToken connectorToken = new AsyncToken("signIn");
        if (useDCS) {
            getNetworkConnection().processRequest(new DCSApplicationSecurityRequest(), new AsyncListener<DCSApplicationSecurityResponse>() {
                public void onResponse(DCSApplicationSecurityResponse response, AsyncToken token, AsyncException exception) {
                    SessionManager.getInstance().setRefreshing(false);
                    AsyncException localException = exception;
                    if (response != null) {
                        if (response.getStatusCode() != 11011) {
                            localException = MWException.fromErrorCode(response.getStatusCode());
                        } else if (localException == null) {
                            SessionManager.getInstance().setToken(((DCSApplicationSecurityDetails) response.getDetails()).token);
                            SessionManager.getInstance().notifyTokenRefreshed();
                            MWConnectorShared.this.retrieveAppParameters(((DCSApplicationSecurityDetails) response.getDetails()).token);
                        }
                    }
                    listener.onResponse(null, connectorToken, localException);
                }
            });
        } else {
            getNetworkConnection().processRequest(new MWSignInRequest(), new AsyncListener<MWSignInResponse>() {
                public void onResponse(MWSignInResponse response, AsyncToken token, AsyncException exception) {
                    SessionManager.getInstance().setRefreshing(false);
                    AsyncException localException = exception;
                    if (response != null) {
                        if (response.getResultCode() != 1) {
                            localException = MWException.fromErrorCode(response.getResultCode());
                        } else if (localException == null) {
                            SessionManager.getInstance().setToken(((MWSignInData) response.getData()).getAccessData().token);
                            SessionManager.getInstance().notifyTokenRefreshed();
                            MWConnectorShared.this.setAppParams(((MWSignInData) response.getData()).getAccessData().appParameter);
                        }
                    }
                    listener.onResponse(response, connectorToken, localException);
                }
            });
        }
        return connectorToken;
    }

    public void retrieveAppParameters(String guestToken) {
        getNetworkConnection().processRequest(new MWGetAppParametersRequest(guestToken), new C39033());
    }

    public void cacheStores(List<Store> stores) {
        if (stores != null) {
            for (Store store : stores) {
                getStoreCache().put(store.getStoreId(), store);
            }
        }
    }

    public SparseArray<Store> getStoreCache() {
        if (this.storeCache == null) {
            this.storeCache = new SparseArray();
        }
        return this.storeCache;
    }

    private void toAppParameters() {
        Map<String, String> appParameters = new HashMap();
        if (!(this.mAppParams == null || this.mAppParams.isEmpty())) {
            int size = this.mAppParams.size();
            for (int i = 0; i < size; i++) {
                MWAppParameter mwAppParameter = (MWAppParameter) this.mAppParams.get(i);
                appParameters.put(mwAppParameter.name, mwAppParameter.value);
            }
        }
        AppParameters.setAppParameters(appParameters);
    }
}
