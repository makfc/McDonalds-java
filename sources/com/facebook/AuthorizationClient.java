package com.facebook;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.facebook.Request.Callback;
import com.facebook.android.C1926R;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.PlatformServiceClient.CompletedListener;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.model.GraphUser;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.Builder;
import com.facebook.widget.WebDialog.OnCompleteListener;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class AuthorizationClient implements Serializable {
    static final String EVENT_EXTRAS_DEFAULT_AUDIENCE = "default_audience";
    static final String EVENT_EXTRAS_IS_LEGACY = "is_legacy";
    static final String EVENT_EXTRAS_LOGIN_BEHAVIOR = "login_behavior";
    static final String EVENT_EXTRAS_MISSING_INTERNET_PERMISSION = "no_internet_permission";
    static final String EVENT_EXTRAS_NEW_PERMISSIONS = "new_permissions";
    static final String EVENT_EXTRAS_NOT_TRIED = "not_tried";
    static final String EVENT_EXTRAS_PERMISSIONS = "permissions";
    static final String EVENT_EXTRAS_REQUEST_CODE = "request_code";
    static final String EVENT_EXTRAS_TRY_LEGACY = "try_legacy";
    static final String EVENT_EXTRAS_TRY_LOGIN_ACTIVITY = "try_login_activity";
    static final String EVENT_NAME_LOGIN_COMPLETE = "fb_mobile_login_complete";
    private static final String EVENT_NAME_LOGIN_METHOD_COMPLETE = "fb_mobile_login_method_complete";
    private static final String EVENT_NAME_LOGIN_METHOD_START = "fb_mobile_login_method_start";
    static final String EVENT_NAME_LOGIN_START = "fb_mobile_login_start";
    static final String EVENT_PARAM_AUTH_LOGGER_ID = "0_auth_logger_id";
    static final String EVENT_PARAM_ERROR_CODE = "4_error_code";
    static final String EVENT_PARAM_ERROR_MESSAGE = "5_error_message";
    static final String EVENT_PARAM_EXTRAS = "6_extras";
    static final String EVENT_PARAM_LOGIN_RESULT = "2_result";
    static final String EVENT_PARAM_METHOD = "3_method";
    private static final String EVENT_PARAM_METHOD_RESULT_SKIPPED = "skipped";
    static final String EVENT_PARAM_TIMESTAMP = "1_timestamp_ms";
    private static final String TAG = "Facebook-AuthorizationClient";
    private static final String WEB_VIEW_AUTH_HANDLER_STORE = "com.facebook.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY";
    private static final String WEB_VIEW_AUTH_HANDLER_TOKEN_KEY = "TOKEN";
    private static final long serialVersionUID = 1;
    private transient AppEventsLogger appEventsLogger;
    transient BackgroundProcessingListener backgroundProcessingListener;
    transient boolean checkedInternetPermission;
    transient Context context;
    AuthHandler currentHandler;
    List<AuthHandler> handlersToTry;
    Map<String, String> loggingExtras;
    transient OnCompletedListener onCompletedListener;
    AuthorizationRequest pendingRequest;
    transient StartActivityDelegate startActivityDelegate;

    interface StartActivityDelegate {
        Activity getActivityContext();

        void startActivityForResult(Intent intent, int i);
    }

    /* renamed from: com.facebook.AuthorizationClient$2 */
    class C18782 implements StartActivityDelegate {
        C18782() {
        }

        public void startActivityForResult(Intent intent, int requestCode) {
            AuthorizationClient.this.pendingRequest.getStartActivityDelegate().startActivityForResult(intent, requestCode);
        }

        public Activity getActivityContext() {
            return AuthorizationClient.this.pendingRequest.getStartActivityDelegate().getActivityContext();
        }
    }

    static class AuthDialogBuilder extends Builder {
        private static final String OAUTH_DIALOG = "oauth";
        static final String REDIRECT_URI = "fbconnect://success";
        private String e2e;
        private boolean isRerequest;

        public AuthDialogBuilder(Context context, String applicationId, Bundle parameters) {
            super(context, applicationId, OAUTH_DIALOG, parameters);
        }

        public AuthDialogBuilder setE2E(String e2e) {
            this.e2e = e2e;
            return this;
        }

        public AuthDialogBuilder setIsRerequest(boolean isRerequest) {
            this.isRerequest = isRerequest;
            return this;
        }

        public WebDialog build() {
            Bundle parameters = getParameters();
            parameters.putString(ServerProtocol.DIALOG_PARAM_REDIRECT_URI, "fbconnect://success");
            parameters.putString("client_id", getApplicationId());
            parameters.putString("e2e", this.e2e);
            parameters.putString(ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, ServerProtocol.DIALOG_RESPONSE_TYPE_TOKEN);
            parameters.putString(ServerProtocol.DIALOG_PARAM_RETURN_SCOPES, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            if (this.isRerequest && !Settings.getPlatformCompatibilityEnabled()) {
                parameters.putString(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, ServerProtocol.DIALOG_REREQUEST_AUTH_TYPE);
            }
            return new WebDialog(getContext(), OAUTH_DIALOG, parameters, getTheme(), getListener());
        }
    }

    abstract class AuthHandler implements Serializable {
        private static final long serialVersionUID = 1;
        Map<String, String> methodLoggingExtras;

        public abstract String getNameForLogging();

        public abstract boolean tryAuthorize(AuthorizationRequest authorizationRequest);

        AuthHandler() {
        }

        /* Access modifiers changed, original: 0000 */
        public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
            return false;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean needsRestart() {
            return false;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean needsInternetPermission() {
            return false;
        }

        /* Access modifiers changed, original: 0000 */
        public void cancel() {
        }

        /* Access modifiers changed, original: protected */
        public void addLoggingExtra(String key, Object value) {
            if (this.methodLoggingExtras == null) {
                this.methodLoggingExtras = new HashMap();
            }
            this.methodLoggingExtras.put(key, value == null ? null : value.toString());
        }
    }

    static class AuthorizationRequest implements Serializable {
        private static final long serialVersionUID = 1;
        private final String applicationId;
        private final String authId;
        private final SessionDefaultAudience defaultAudience;
        private boolean isLegacy = false;
        private boolean isRerequest = false;
        private final SessionLoginBehavior loginBehavior;
        private List<String> permissions;
        private final String previousAccessToken;
        private final int requestCode;
        private final transient StartActivityDelegate startActivityDelegate;

        AuthorizationRequest(SessionLoginBehavior loginBehavior, int requestCode, boolean isLegacy, List<String> permissions, SessionDefaultAudience defaultAudience, String applicationId, String validateSameFbidAsToken, StartActivityDelegate startActivityDelegate, String authId) {
            this.loginBehavior = loginBehavior;
            this.requestCode = requestCode;
            this.isLegacy = isLegacy;
            this.permissions = permissions;
            this.defaultAudience = defaultAudience;
            this.applicationId = applicationId;
            this.previousAccessToken = validateSameFbidAsToken;
            this.startActivityDelegate = startActivityDelegate;
            this.authId = authId;
        }

        /* Access modifiers changed, original: 0000 */
        public StartActivityDelegate getStartActivityDelegate() {
            return this.startActivityDelegate;
        }

        /* Access modifiers changed, original: 0000 */
        public List<String> getPermissions() {
            return this.permissions;
        }

        /* Access modifiers changed, original: 0000 */
        public void setPermissions(List<String> permissions) {
            this.permissions = permissions;
        }

        /* Access modifiers changed, original: 0000 */
        public SessionLoginBehavior getLoginBehavior() {
            return this.loginBehavior;
        }

        /* Access modifiers changed, original: 0000 */
        public int getRequestCode() {
            return this.requestCode;
        }

        /* Access modifiers changed, original: 0000 */
        public SessionDefaultAudience getDefaultAudience() {
            return this.defaultAudience;
        }

        /* Access modifiers changed, original: 0000 */
        public String getApplicationId() {
            return this.applicationId;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean isLegacy() {
            return this.isLegacy;
        }

        /* Access modifiers changed, original: 0000 */
        public void setIsLegacy(boolean isLegacy) {
            this.isLegacy = isLegacy;
        }

        /* Access modifiers changed, original: 0000 */
        public String getPreviousAccessToken() {
            return this.previousAccessToken;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean needsNewTokenValidation() {
            return (this.previousAccessToken == null || this.isLegacy) ? false : true;
        }

        /* Access modifiers changed, original: 0000 */
        public String getAuthId() {
            return this.authId;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean isRerequest() {
            return this.isRerequest;
        }

        /* Access modifiers changed, original: 0000 */
        public void setRerequest(boolean isRerequest) {
            this.isRerequest = isRerequest;
        }
    }

    interface BackgroundProcessingListener {
        void onBackgroundProcessingStarted();

        void onBackgroundProcessingStopped();
    }

    class GetTokenAuthHandler extends AuthHandler {
        private static final long serialVersionUID = 1;
        private transient GetTokenClient getTokenClient;

        GetTokenAuthHandler() {
            super();
        }

        /* Access modifiers changed, original: 0000 */
        public String getNameForLogging() {
            return "get_token";
        }

        /* Access modifiers changed, original: 0000 */
        public void cancel() {
            if (this.getTokenClient != null) {
                this.getTokenClient.cancel();
                this.getTokenClient = null;
            }
        }

        /* Access modifiers changed, original: 0000 */
        public boolean needsRestart() {
            return this.getTokenClient == null;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean tryAuthorize(final AuthorizationRequest request) {
            this.getTokenClient = new GetTokenClient(AuthorizationClient.this.context, request.getApplicationId());
            if (!this.getTokenClient.start()) {
                return false;
            }
            AuthorizationClient.this.notifyBackgroundProcessingStart();
            this.getTokenClient.setCompletedListener(new CompletedListener() {
                public void completed(Bundle result) {
                    GetTokenAuthHandler.this.getTokenCompleted(request, result);
                }
            });
            return true;
        }

        /* Access modifiers changed, original: 0000 */
        public void getTokenCompleted(AuthorizationRequest request, Bundle result) {
            this.getTokenClient = null;
            AuthorizationClient.this.notifyBackgroundProcessingStop();
            if (result != null) {
                ArrayList<String> currentPermissions = result.getStringArrayList(NativeProtocol.EXTRA_PERMISSIONS);
                List<String> permissions = request.getPermissions();
                if (currentPermissions == null || !(permissions == null || currentPermissions.containsAll(permissions))) {
                    List<String> newPermissions = new ArrayList();
                    for (String permission : permissions) {
                        if (!currentPermissions.contains(permission)) {
                            newPermissions.add(permission);
                        }
                    }
                    if (!newPermissions.isEmpty()) {
                        addLoggingExtra(AuthorizationClient.EVENT_EXTRAS_NEW_PERMISSIONS, TextUtils.join(",", newPermissions));
                    }
                    request.setPermissions(newPermissions);
                } else {
                    AuthorizationClient.this.completeAndValidate(Result.createTokenResult(AuthorizationClient.this.pendingRequest, AccessToken.createFromNativeLogin(result, AccessTokenSource.FACEBOOK_APPLICATION_SERVICE)));
                    return;
                }
            }
            AuthorizationClient.this.tryNextHandler();
        }
    }

    abstract class KatanaAuthHandler extends AuthHandler {
        private static final long serialVersionUID = 1;

        KatanaAuthHandler() {
            super();
        }

        /* Access modifiers changed, original: protected */
        public boolean tryIntent(Intent intent, int requestCode) {
            if (intent == null) {
                return false;
            }
            try {
                AuthorizationClient.this.getStartActivityDelegate().startActivityForResult(intent, requestCode);
                return true;
            } catch (ActivityNotFoundException e) {
                return false;
            }
        }
    }

    class KatanaProxyAuthHandler extends KatanaAuthHandler {
        private static final long serialVersionUID = 1;
        private String applicationId;

        KatanaProxyAuthHandler() {
            super();
        }

        /* Access modifiers changed, original: 0000 */
        public String getNameForLogging() {
            return "katana_proxy_auth";
        }

        /* Access modifiers changed, original: 0000 */
        public boolean tryAuthorize(AuthorizationRequest request) {
            this.applicationId = request.getApplicationId();
            String e2e = AuthorizationClient.getE2E();
            Intent intent = NativeProtocol.createProxyAuthIntent(AuthorizationClient.this.context, request.getApplicationId(), request.getPermissions(), e2e, request.isRerequest(), request.getDefaultAudience());
            addLoggingExtra("e2e", e2e);
            return tryIntent(intent, request.getRequestCode());
        }

        /* Access modifiers changed, original: 0000 */
        public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
            Result outcome;
            if (data == null) {
                outcome = Result.createCancelResult(AuthorizationClient.this.pendingRequest, "Operation canceled");
            } else if (resultCode == 0) {
                outcome = Result.createCancelResult(AuthorizationClient.this.pendingRequest, data.getStringExtra("error"));
            } else if (resultCode != -1) {
                outcome = Result.createErrorResult(AuthorizationClient.this.pendingRequest, "Unexpected resultCode from authorization.", null);
            } else {
                outcome = handleResultOk(data);
            }
            if (outcome != null) {
                AuthorizationClient.this.completeAndValidate(outcome);
            } else {
                AuthorizationClient.this.tryNextHandler();
            }
            return true;
        }

        private Result handleResultOk(Intent data) {
            Bundle extras = data.getExtras();
            String error = extras.getString("error");
            if (error == null) {
                error = extras.getString(NativeProtocol.BRIDGE_ARG_ERROR_TYPE);
            }
            String errorCode = extras.getString(NativeProtocol.BRIDGE_ARG_ERROR_CODE);
            String errorMessage = extras.getString("error_message");
            if (errorMessage == null) {
                errorMessage = extras.getString(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION);
            }
            String e2e = extras.getString("e2e");
            if (!Utility.isNullOrEmpty(e2e)) {
                AuthorizationClient.this.logWebLoginCompleted(this.applicationId, e2e);
            }
            if (error == null && errorCode == null && errorMessage == null) {
                return Result.createTokenResult(AuthorizationClient.this.pendingRequest, AccessToken.createFromWebBundle(AuthorizationClient.this.pendingRequest.getPermissions(), extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB));
            } else if (ServerProtocol.errorsProxyAuthDisabled.contains(error)) {
                return null;
            } else {
                if (ServerProtocol.errorsUserCanceled.contains(error)) {
                    return Result.createCancelResult(AuthorizationClient.this.pendingRequest, null);
                }
                return Result.createErrorResult(AuthorizationClient.this.pendingRequest, error, errorMessage, errorCode);
            }
        }
    }

    interface OnCompletedListener {
        void onCompleted(Result result);
    }

    static class Result implements Serializable {
        private static final long serialVersionUID = 1;
        final Code code;
        final String errorCode;
        final String errorMessage;
        Map<String, String> loggingExtras;
        final AuthorizationRequest request;
        final AccessToken token;

        enum Code {
            SUCCESS(Response.SUCCESS_KEY),
            CANCEL(FacebookDialog.COMPLETION_GESTURE_CANCEL),
            ERROR("error");
            
            private final String loggingValue;

            private Code(String loggingValue) {
                this.loggingValue = loggingValue;
            }

            /* Access modifiers changed, original: 0000 */
            public String getLoggingValue() {
                return this.loggingValue;
            }
        }

        private Result(AuthorizationRequest request, Code code, AccessToken token, String errorMessage, String errorCode) {
            this.request = request;
            this.token = token;
            this.errorMessage = errorMessage;
            this.code = code;
            this.errorCode = errorCode;
        }

        static Result createTokenResult(AuthorizationRequest request, AccessToken token) {
            return new Result(request, Code.SUCCESS, token, null, null);
        }

        static Result createCancelResult(AuthorizationRequest request, String message) {
            return new Result(request, Code.CANCEL, null, message, null);
        }

        static Result createErrorResult(AuthorizationRequest request, String errorType, String errorDescription) {
            return createErrorResult(request, errorType, errorDescription, null);
        }

        static Result createErrorResult(AuthorizationRequest request, String errorType, String errorDescription, String errorCode) {
            return new Result(request, Code.ERROR, null, TextUtils.join(": ", Utility.asListNoNulls(errorType, errorDescription)), errorCode);
        }
    }

    class WebViewAuthHandler extends AuthHandler {
        private static final long serialVersionUID = 1;
        private String applicationId;
        private String e2e;
        private transient WebDialog loginDialog;

        WebViewAuthHandler() {
            super();
        }

        /* Access modifiers changed, original: 0000 */
        public String getNameForLogging() {
            return "web_view";
        }

        /* Access modifiers changed, original: 0000 */
        public boolean needsRestart() {
            return true;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean needsInternetPermission() {
            return true;
        }

        /* Access modifiers changed, original: 0000 */
        public void cancel() {
            if (this.loginDialog != null) {
                this.loginDialog.setOnCompleteListener(null);
                this.loginDialog.dismiss();
                this.loginDialog = null;
            }
        }

        /* Access modifiers changed, original: 0000 */
        public boolean tryAuthorize(final AuthorizationRequest request) {
            this.applicationId = request.getApplicationId();
            Bundle parameters = new Bundle();
            if (!Utility.isNullOrEmpty(request.getPermissions())) {
                String scope = TextUtils.join(",", request.getPermissions());
                parameters.putString("scope", scope);
                addLoggingExtra("scope", scope);
            }
            parameters.putString("default_audience", request.getDefaultAudience().getNativeProtocolAudience());
            String previousToken = request.getPreviousAccessToken();
            if (Utility.isNullOrEmpty(previousToken) || !previousToken.equals(loadCookieToken())) {
                Utility.clearFacebookCookies(AuthorizationClient.this.context);
                addLoggingExtra("access_token", "0");
            } else {
                parameters.putString("access_token", previousToken);
                addLoggingExtra("access_token", "1");
            }
            OnCompleteListener listener = new OnCompleteListener() {
                public void onComplete(Bundle values, FacebookException error) {
                    WebViewAuthHandler.this.onWebDialogComplete(request, values, error);
                }
            };
            this.e2e = AuthorizationClient.getE2E();
            addLoggingExtra("e2e", this.e2e);
            this.loginDialog = ((Builder) new AuthDialogBuilder(AuthorizationClient.this.getStartActivityDelegate().getActivityContext(), this.applicationId, parameters).setE2E(this.e2e).setIsRerequest(request.isRerequest()).setOnCompleteListener(listener)).build();
            this.loginDialog.show();
            return true;
        }

        /* Access modifiers changed, original: 0000 */
        public void onWebDialogComplete(AuthorizationRequest request, Bundle values, FacebookException error) {
            Result outcome;
            if (values != null) {
                if (values.containsKey("e2e")) {
                    this.e2e = values.getString("e2e");
                }
                AccessToken token = AccessToken.createFromWebBundle(request.getPermissions(), values, AccessTokenSource.WEB_VIEW);
                outcome = Result.createTokenResult(AuthorizationClient.this.pendingRequest, token);
                CookieSyncManager.createInstance(AuthorizationClient.this.context).sync();
                saveCookieToken(token.getToken());
            } else if (error instanceof FacebookOperationCanceledException) {
                outcome = Result.createCancelResult(AuthorizationClient.this.pendingRequest, "User canceled log in.");
            } else {
                this.e2e = null;
                String errorCode = null;
                String errorMessage = error.getMessage();
                if (error instanceof FacebookServiceException) {
                    errorCode = String.format("%d", new Object[]{Integer.valueOf(((FacebookServiceException) error).getRequestError().getErrorCode())});
                    errorMessage = requestError.toString();
                }
                outcome = Result.createErrorResult(AuthorizationClient.this.pendingRequest, null, errorMessage, errorCode);
            }
            if (!Utility.isNullOrEmpty(this.e2e)) {
                AuthorizationClient.this.logWebLoginCompleted(this.applicationId, this.e2e);
            }
            AuthorizationClient.this.completeAndValidate(outcome);
        }

        private void saveCookieToken(String token) {
            AuthorizationClient.this.getStartActivityDelegate().getActivityContext().getSharedPreferences(AuthorizationClient.WEB_VIEW_AUTH_HANDLER_STORE, 0).edit().putString(AuthorizationClient.WEB_VIEW_AUTH_HANDLER_TOKEN_KEY, token).apply();
        }

        private String loadCookieToken() {
            return AuthorizationClient.this.getStartActivityDelegate().getActivityContext().getSharedPreferences(AuthorizationClient.WEB_VIEW_AUTH_HANDLER_STORE, 0).getString(AuthorizationClient.WEB_VIEW_AUTH_HANDLER_TOKEN_KEY, "");
        }
    }

    AuthorizationClient() {
    }

    /* Access modifiers changed, original: 0000 */
    public void setContext(Context context) {
        this.context = context;
        this.startActivityDelegate = null;
    }

    /* Access modifiers changed, original: 0000 */
    public void setContext(final Activity activity) {
        this.context = activity;
        this.startActivityDelegate = new StartActivityDelegate() {
            public void startActivityForResult(Intent intent, int requestCode) {
                activity.startActivityForResult(intent, requestCode);
            }

            public Activity getActivityContext() {
                return activity;
            }
        };
    }

    /* Access modifiers changed, original: 0000 */
    public void startOrContinueAuth(AuthorizationRequest request) {
        if (getInProgress()) {
            continueAuth();
        } else {
            authorize(request);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void authorize(AuthorizationRequest request) {
        if (request != null) {
            if (this.pendingRequest != null) {
                throw new FacebookException("Attempted to authorize while a request is pending.");
            } else if (!request.needsNewTokenValidation() || checkInternetPermission()) {
                this.pendingRequest = request;
                this.handlersToTry = getHandlerTypes(request);
                tryNextHandler();
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void continueAuth() {
        if (this.pendingRequest == null || this.currentHandler == null) {
            throw new FacebookException("Attempted to continue authorization without a pending request.");
        } else if (this.currentHandler.needsRestart()) {
            this.currentHandler.cancel();
            tryCurrentHandler();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean getInProgress() {
        return (this.pendingRequest == null || this.currentHandler == null) ? false : true;
    }

    /* Access modifiers changed, original: 0000 */
    public void cancelCurrentHandler() {
        if (this.currentHandler != null) {
            this.currentHandler.cancel();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.pendingRequest == null || requestCode != this.pendingRequest.getRequestCode()) {
            return false;
        }
        return this.currentHandler.onActivityResult(requestCode, resultCode, data);
    }

    private List<AuthHandler> getHandlerTypes(AuthorizationRequest request) {
        ArrayList<AuthHandler> handlers = new ArrayList();
        SessionLoginBehavior behavior = request.getLoginBehavior();
        if (behavior.allowsKatanaAuth()) {
            if (!request.isLegacy()) {
                handlers.add(new GetTokenAuthHandler());
            }
            handlers.add(new KatanaProxyAuthHandler());
        }
        if (behavior.allowsWebViewAuth()) {
            handlers.add(new WebViewAuthHandler());
        }
        return handlers;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean checkInternetPermission() {
        if (this.checkedInternetPermission) {
            return true;
        }
        if (checkPermission("android.permission.INTERNET") != 0) {
            complete(Result.createErrorResult(this.pendingRequest, this.context.getString(C1926R.string.com_facebook_internet_permission_error_title), this.context.getString(C1926R.string.com_facebook_internet_permission_error_message)));
            return false;
        }
        this.checkedInternetPermission = true;
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    public void tryNextHandler() {
        if (this.currentHandler != null) {
            logAuthorizationMethodComplete(this.currentHandler.getNameForLogging(), EVENT_PARAM_METHOD_RESULT_SKIPPED, null, null, this.currentHandler.methodLoggingExtras);
        }
        while (this.handlersToTry != null && !this.handlersToTry.isEmpty()) {
            this.currentHandler = (AuthHandler) this.handlersToTry.remove(0);
            if (tryCurrentHandler()) {
                return;
            }
        }
        if (this.pendingRequest != null) {
            completeWithFailure();
        }
    }

    private void completeWithFailure() {
        complete(Result.createErrorResult(this.pendingRequest, "Login attempt failed.", null));
    }

    private void addLoggingExtra(String key, String value, boolean accumulate) {
        Object value2;
        if (this.loggingExtras == null) {
            this.loggingExtras = new HashMap();
        }
        if (this.loggingExtras.containsKey(key) && accumulate) {
            value2 = ((String) this.loggingExtras.get(key)) + "," + value2;
        }
        this.loggingExtras.put(key, value2);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean tryCurrentHandler() {
        boolean tried = false;
        if (!this.currentHandler.needsInternetPermission() || checkInternetPermission()) {
            tried = this.currentHandler.tryAuthorize(this.pendingRequest);
            if (tried) {
                logAuthorizationMethodStart(this.currentHandler.getNameForLogging());
            } else {
                addLoggingExtra(EVENT_EXTRAS_NOT_TRIED, this.currentHandler.getNameForLogging(), true);
            }
        } else {
            addLoggingExtra(EVENT_EXTRAS_MISSING_INTERNET_PERMISSION, "1", false);
        }
        return tried;
    }

    /* Access modifiers changed, original: 0000 */
    public void completeAndValidate(Result outcome) {
        if (outcome.token == null || !this.pendingRequest.needsNewTokenValidation()) {
            complete(outcome);
        } else {
            validateSameFbidAndFinish(outcome);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void complete(Result outcome) {
        if (this.currentHandler != null) {
            logAuthorizationMethodComplete(this.currentHandler.getNameForLogging(), outcome, this.currentHandler.methodLoggingExtras);
        }
        if (this.loggingExtras != null) {
            outcome.loggingExtras = this.loggingExtras;
        }
        this.handlersToTry = null;
        this.currentHandler = null;
        this.pendingRequest = null;
        this.loggingExtras = null;
        notifyOnCompleteListener(outcome);
    }

    /* Access modifiers changed, original: 0000 */
    public OnCompletedListener getOnCompletedListener() {
        return this.onCompletedListener;
    }

    /* Access modifiers changed, original: 0000 */
    public void setOnCompletedListener(OnCompletedListener onCompletedListener) {
        this.onCompletedListener = onCompletedListener;
    }

    /* Access modifiers changed, original: 0000 */
    public BackgroundProcessingListener getBackgroundProcessingListener() {
        return this.backgroundProcessingListener;
    }

    /* Access modifiers changed, original: 0000 */
    public void setBackgroundProcessingListener(BackgroundProcessingListener backgroundProcessingListener) {
        this.backgroundProcessingListener = backgroundProcessingListener;
    }

    /* Access modifiers changed, original: 0000 */
    public StartActivityDelegate getStartActivityDelegate() {
        if (this.startActivityDelegate != null) {
            return this.startActivityDelegate;
        }
        return this.pendingRequest != null ? new C18782() : null;
    }

    /* Access modifiers changed, original: 0000 */
    public int checkPermission(String permission) {
        return this.context.checkCallingOrSelfPermission(permission);
    }

    /* Access modifiers changed, original: 0000 */
    public void validateSameFbidAndFinish(Result pendingResult) {
        if (pendingResult.token == null) {
            throw new FacebookException("Can't validate without a token");
        }
        RequestBatch batch = createReauthValidationBatch(pendingResult);
        notifyBackgroundProcessingStart();
        batch.executeAsync();
    }

    /* Access modifiers changed, original: 0000 */
    public RequestBatch createReauthValidationBatch(Result pendingResult) {
        final ArrayList<String> fbids = new ArrayList();
        final ArrayList<String> grantedPermissions = new ArrayList();
        final ArrayList<String> declinedPermissions = new ArrayList();
        String newToken = pendingResult.token.getToken();
        Callback meCallback = new Callback() {
            public void onCompleted(Response response) {
                try {
                    GraphUser user = (GraphUser) response.getGraphObjectAs(GraphUser.class);
                    if (user != null) {
                        fbids.add(user.getId());
                    }
                } catch (Exception e) {
                }
            }
        };
        String validateSameFbidAsToken = this.pendingRequest.getPreviousAccessToken();
        createGetProfileIdRequest(validateSameFbidAsToken).setCallback(meCallback);
        createGetProfileIdRequest(newToken).setCallback(meCallback);
        createGetPermissionsRequest(validateSameFbidAsToken).setCallback(new Callback() {
            public void onCompleted(Response response) {
                try {
                    PermissionsPair permissionsPair = Session.handlePermissionResponse(response);
                    if (permissionsPair != null) {
                        grantedPermissions.addAll(permissionsPair.getGrantedPermissions());
                        declinedPermissions.addAll(permissionsPair.getDeclinedPermissions());
                    }
                } catch (Exception e) {
                }
            }
        });
        RequestBatch batch = new RequestBatch(requestCurrentTokenMe, requestNewTokenMe, requestCurrentTokenPermissions);
        batch.setBatchApplicationId(this.pendingRequest.getApplicationId());
        final Result result = pendingResult;
        batch.addCallback(new RequestBatch.Callback() {
            public void onBatchCompleted(RequestBatch batch) {
                try {
                    Result result;
                    if (fbids.size() != 2 || fbids.get(0) == null || fbids.get(1) == null || !((String) fbids.get(0)).equals(fbids.get(1))) {
                        result = Result.createErrorResult(AuthorizationClient.this.pendingRequest, "User logged in as different Facebook user.", null);
                    } else {
                        result = Result.createTokenResult(AuthorizationClient.this.pendingRequest, AccessToken.createFromTokenWithRefreshedPermissions(result.token, grantedPermissions, declinedPermissions));
                    }
                    AuthorizationClient.this.complete(result);
                } catch (Exception ex) {
                    AuthorizationClient.this.complete(Result.createErrorResult(AuthorizationClient.this.pendingRequest, "Caught exception", ex.getMessage()));
                } finally {
                    AuthorizationClient.this.notifyBackgroundProcessingStop();
                }
            }
        });
        return batch;
    }

    /* Access modifiers changed, original: 0000 */
    public Request createGetPermissionsRequest(String accessToken) {
        Bundle parameters = new Bundle();
        parameters.putString("access_token", accessToken);
        return new Request(null, "me/permissions", parameters, HttpMethod.GET, null);
    }

    /* Access modifiers changed, original: 0000 */
    public Request createGetProfileIdRequest(String accessToken) {
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id");
        parameters.putString("access_token", accessToken);
        return new Request(null, "me", parameters, HttpMethod.GET, null);
    }

    private AppEventsLogger getAppEventsLogger() {
        if (this.appEventsLogger == null || !this.appEventsLogger.getApplicationId().equals(this.pendingRequest.getApplicationId())) {
            this.appEventsLogger = AppEventsLogger.newLogger(this.context, this.pendingRequest.getApplicationId());
        }
        return this.appEventsLogger;
    }

    private void notifyOnCompleteListener(Result outcome) {
        if (this.onCompletedListener != null) {
            this.onCompletedListener.onCompleted(outcome);
        }
    }

    private void notifyBackgroundProcessingStart() {
        if (this.backgroundProcessingListener != null) {
            this.backgroundProcessingListener.onBackgroundProcessingStarted();
        }
    }

    private void notifyBackgroundProcessingStop() {
        if (this.backgroundProcessingListener != null) {
            this.backgroundProcessingListener.onBackgroundProcessingStopped();
        }
    }

    private void logAuthorizationMethodStart(String method) {
        Bundle bundle = newAuthorizationLoggingBundle(this.pendingRequest.getAuthId());
        bundle.putLong(EVENT_PARAM_TIMESTAMP, System.currentTimeMillis());
        bundle.putString(EVENT_PARAM_METHOD, method);
        getAppEventsLogger().logSdkEvent(EVENT_NAME_LOGIN_METHOD_START, null, bundle);
    }

    private void logAuthorizationMethodComplete(String method, Result result, Map<String, String> loggingExtras) {
        logAuthorizationMethodComplete(method, result.code.getLoggingValue(), result.errorMessage, result.errorCode, loggingExtras);
    }

    private void logAuthorizationMethodComplete(String method, String result, String errorMessage, String errorCode, Map<String, String> loggingExtras) {
        Bundle bundle;
        if (this.pendingRequest == null) {
            bundle = newAuthorizationLoggingBundle("");
            bundle.putString(EVENT_PARAM_LOGIN_RESULT, Code.ERROR.getLoggingValue());
            bundle.putString(EVENT_PARAM_ERROR_MESSAGE, "Unexpected call to logAuthorizationMethodComplete with null pendingRequest.");
        } else {
            bundle = newAuthorizationLoggingBundle(this.pendingRequest.getAuthId());
            if (result != null) {
                bundle.putString(EVENT_PARAM_LOGIN_RESULT, result);
            }
            if (errorMessage != null) {
                bundle.putString(EVENT_PARAM_ERROR_MESSAGE, errorMessage);
            }
            if (errorCode != null) {
                bundle.putString(EVENT_PARAM_ERROR_CODE, errorCode);
            }
            if (!(loggingExtras == null || loggingExtras.isEmpty())) {
                JSONObject jsonObject = new JSONObject(loggingExtras);
                bundle.putString(EVENT_PARAM_EXTRAS, !(jsonObject instanceof JSONObject) ? jsonObject.toString() : JSONObjectInstrumentation.toString(jsonObject));
            }
        }
        bundle.putString(EVENT_PARAM_METHOD, method);
        bundle.putLong(EVENT_PARAM_TIMESTAMP, System.currentTimeMillis());
        getAppEventsLogger().logSdkEvent(EVENT_NAME_LOGIN_METHOD_COMPLETE, null, bundle);
    }

    static Bundle newAuthorizationLoggingBundle(String authLoggerId) {
        Bundle bundle = new Bundle();
        bundle.putLong(EVENT_PARAM_TIMESTAMP, System.currentTimeMillis());
        bundle.putString(EVENT_PARAM_AUTH_LOGGER_ID, authLoggerId);
        bundle.putString(EVENT_PARAM_METHOD, "");
        bundle.putString(EVENT_PARAM_LOGIN_RESULT, "");
        bundle.putString(EVENT_PARAM_ERROR_MESSAGE, "");
        bundle.putString(EVENT_PARAM_ERROR_CODE, "");
        bundle.putString(EVENT_PARAM_EXTRAS, "");
        return bundle;
    }

    private static String getE2E() {
        JSONObject e2e = new JSONObject();
        try {
            e2e.put("init", System.currentTimeMillis());
        } catch (JSONException e) {
        }
        return !(e2e instanceof JSONObject) ? e2e.toString() : JSONObjectInstrumentation.toString(e2e);
    }

    private void logWebLoginCompleted(String applicationId, String e2e) {
        AppEventsLogger appEventsLogger = AppEventsLogger.newLogger(this.context, applicationId);
        Bundle parameters = new Bundle();
        parameters.putString(AnalyticsEvents.PARAMETER_WEB_LOGIN_E2E, e2e);
        parameters.putLong(AnalyticsEvents.PARAMETER_WEB_LOGIN_SWITCHBACK_TIME, System.currentTimeMillis());
        parameters.putString("app_id", applicationId);
        appEventsLogger.logSdkEvent(AnalyticsEvents.EVENT_WEB_LOGIN_COMPLETE, null, parameters);
    }
}
