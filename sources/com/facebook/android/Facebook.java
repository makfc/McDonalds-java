package com.facebook.android;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.LegacyHelper;
import com.facebook.Session;
import com.facebook.Session.Builder;
import com.facebook.Session.OpenRequest;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionState;
import com.facebook.Settings;
import com.facebook.TokenCachingStrategy;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Facebook {
    @Deprecated
    public static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    @Deprecated
    public static final Uri ATTRIBUTION_ID_CONTENT_URI = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
    @Deprecated
    public static final String CANCEL_URI = "fbconnect://cancel";
    private static final int DEFAULT_AUTH_ACTIVITY_CODE = 32665;
    @Deprecated
    protected static String DIALOG_BASE_URL = "https://m.facebook.com/dialog/";
    @Deprecated
    public static final String EXPIRES = "expires_in";
    @Deprecated
    public static final String FB_APP_SIGNATURE = "30820268308201d102044a9c4610300d06092a864886f70d0101040500307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e3020170d3039303833313231353231365a180f32303530303932353231353231365a307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e30819f300d06092a864886f70d010101050003818d0030818902818100c207d51df8eb8c97d93ba0c8c1002c928fab00dc1b42fca5e66e99cc3023ed2d214d822bc59e8e35ddcf5f44c7ae8ade50d7e0c434f500e6c131f4a2834f987fc46406115de2018ebbb0d5a3c261bd97581ccfef76afc7135a6d59e8855ecd7eacc8f8737e794c60a761c536b72b11fac8e603f5da1a2d54aa103b8a13c0dbc10203010001300d06092a864886f70d0101040500038181005ee9be8bcbb250648d3b741290a82a1c9dc2e76a0af2f2228f1d9f9c4007529c446a70175c5a900d5141812866db46be6559e2141616483998211f4a673149fb2232a10d247663b26a9031e15f84bc1c74d141ff98a02d76f85b2c8ab2571b6469b232d8e768a7f7ca04f7abe4a775615916c07940656b58717457b42bd928a2";
    @Deprecated
    public static final int FORCE_DIALOG_AUTH = -1;
    @Deprecated
    protected static String GRAPH_BASE_URL = "https://graph.facebook.com/";
    private static final String LOGIN = "oauth";
    @Deprecated
    public static final String REDIRECT_URI = "fbconnect://success";
    private static final long REFRESH_TOKEN_BARRIER = 86400000;
    @Deprecated
    protected static String RESTSERVER_URL = "https://api.facebook.com/restserver.php";
    @Deprecated
    public static final String SINGLE_SIGN_ON_DISABLED = "service_disabled";
    @Deprecated
    public static final String TOKEN = "access_token";
    private long accessExpiresMillisecondsAfterEpoch = 0;
    private String accessToken = null;
    private long lastAccessUpdateMillisecondsAfterEpoch = 0;
    private final Object lock = new Object();
    private String mAppId;
    private Activity pendingAuthorizationActivity;
    private String[] pendingAuthorizationPermissions;
    private Session pendingOpeningSession;
    private volatile Session session;
    private boolean sessionInvalidated;
    private SetterTokenCachingStrategy tokenCache;
    private volatile Session userSetSession;

    public interface DialogListener {
        void onCancel();

        void onComplete(Bundle bundle);

        void onError(DialogError dialogError);

        void onFacebookError(FacebookError facebookError);
    }

    public interface ServiceListener {
        void onComplete(Bundle bundle);

        void onError(Error error);

        void onFacebookError(FacebookError facebookError);
    }

    private class SetterTokenCachingStrategy extends TokenCachingStrategy {
        private SetterTokenCachingStrategy() {
        }

        /* synthetic */ SetterTokenCachingStrategy(Facebook x0, C19191 x1) {
            this();
        }

        public Bundle load() {
            Bundle bundle = new Bundle();
            if (Facebook.this.accessToken != null) {
                TokenCachingStrategy.putToken(bundle, Facebook.this.accessToken);
                TokenCachingStrategy.putExpirationMilliseconds(bundle, Facebook.this.accessExpiresMillisecondsAfterEpoch);
                TokenCachingStrategy.putPermissions(bundle, Facebook.stringList(Facebook.this.pendingAuthorizationPermissions));
                TokenCachingStrategy.putSource(bundle, AccessTokenSource.WEB_VIEW);
                TokenCachingStrategy.putLastRefreshMilliseconds(bundle, Facebook.this.lastAccessUpdateMillisecondsAfterEpoch);
            }
            return bundle;
        }

        public void save(Bundle bundle) {
            Facebook.this.accessToken = TokenCachingStrategy.getToken(bundle);
            Facebook.this.accessExpiresMillisecondsAfterEpoch = TokenCachingStrategy.getExpirationMilliseconds(bundle);
            Facebook.this.pendingAuthorizationPermissions = Facebook.stringArray(TokenCachingStrategy.getPermissions(bundle));
            Facebook.this.lastAccessUpdateMillisecondsAfterEpoch = TokenCachingStrategy.getLastRefreshMilliseconds(bundle);
        }

        public void clear() {
            Facebook.this.accessToken = null;
        }
    }

    private static class TokenRefreshConnectionHandler extends Handler {
        WeakReference<TokenRefreshServiceConnection> connectionWeakReference;
        WeakReference<Facebook> facebookWeakReference;

        TokenRefreshConnectionHandler(Facebook facebook, TokenRefreshServiceConnection connection) {
            this.facebookWeakReference = new WeakReference(facebook);
            this.connectionWeakReference = new WeakReference(connection);
        }

        public void handleMessage(Message msg) {
            Facebook facebook = (Facebook) this.facebookWeakReference.get();
            TokenRefreshServiceConnection connection = (TokenRefreshServiceConnection) this.connectionWeakReference.get();
            if (facebook != null && connection != null) {
                String token = msg.getData().getString("access_token");
                long expiresAtMsecFromEpoch = msg.getData().getLong(Facebook.EXPIRES) * 1000;
                if (token != null) {
                    facebook.setAccessToken(token);
                    facebook.setAccessExpires(expiresAtMsecFromEpoch);
                    Session refreshSession = facebook.session;
                    if (refreshSession != null) {
                        LegacyHelper.extendTokenCompleted(refreshSession, msg.getData());
                    }
                    if (connection.serviceListener != null) {
                        Bundle resultBundle = (Bundle) msg.getData().clone();
                        resultBundle.putLong(Facebook.EXPIRES, expiresAtMsecFromEpoch);
                        connection.serviceListener.onComplete(resultBundle);
                    }
                } else if (connection.serviceListener != null) {
                    String error = msg.getData().getString("error");
                    if (msg.getData().containsKey(NativeProtocol.BRIDGE_ARG_ERROR_CODE)) {
                        connection.serviceListener.onFacebookError(new FacebookError(error, null, msg.getData().getInt(NativeProtocol.BRIDGE_ARG_ERROR_CODE)));
                    } else {
                        ServiceListener serviceListener = connection.serviceListener;
                        if (error == null) {
                            error = "Unknown service error";
                        }
                        serviceListener.onError(new Error(error));
                    }
                }
                connection.applicationsContext.unbindService(connection);
            }
        }
    }

    private class TokenRefreshServiceConnection implements ServiceConnection {
        final Context applicationsContext;
        final Messenger messageReceiver = new Messenger(new TokenRefreshConnectionHandler(Facebook.this, this));
        Messenger messageSender = null;
        final ServiceListener serviceListener;

        public TokenRefreshServiceConnection(Context applicationsContext, ServiceListener serviceListener) {
            this.applicationsContext = applicationsContext;
            this.serviceListener = serviceListener;
        }

        public void onServiceConnected(ComponentName className, IBinder service) {
            this.messageSender = new Messenger(service);
            refreshToken();
        }

        public void onServiceDisconnected(ComponentName arg) {
            this.serviceListener.onError(new Error("Service disconnected"));
            try {
                this.applicationsContext.unbindService(this);
            } catch (IllegalArgumentException e) {
            }
        }

        private void refreshToken() {
            Bundle requestData = new Bundle();
            requestData.putString("access_token", Facebook.this.accessToken);
            Message request = Message.obtain();
            request.setData(requestData);
            request.replyTo = this.messageReceiver;
            try {
                this.messageSender.send(request);
            } catch (RemoteException e) {
                this.serviceListener.onError(new Error("Service connection error"));
            }
        }
    }

    @Deprecated
    public Facebook(String appId) {
        if (appId == null) {
            throw new IllegalArgumentException("You must specify your application ID when instantiating a Facebook object. See README for details.");
        }
        this.mAppId = appId;
    }

    @Deprecated
    public void authorize(Activity activity, DialogListener listener) {
        authorize(activity, new String[0], DEFAULT_AUTH_ACTIVITY_CODE, SessionLoginBehavior.SSO_WITH_FALLBACK, listener);
    }

    @Deprecated
    public void authorize(Activity activity, String[] permissions, DialogListener listener) {
        authorize(activity, permissions, DEFAULT_AUTH_ACTIVITY_CODE, SessionLoginBehavior.SSO_WITH_FALLBACK, listener);
    }

    @Deprecated
    public void authorize(Activity activity, String[] permissions, int activityCode, DialogListener listener) {
        authorize(activity, permissions, activityCode, activityCode >= 0 ? SessionLoginBehavior.SSO_WITH_FALLBACK : SessionLoginBehavior.SUPPRESS_SSO, listener);
    }

    private void authorize(Activity activity, String[] permissions, int activityCode, SessionLoginBehavior behavior, final DialogListener listener) {
        boolean z = false;
        checkUserSession("authorize");
        this.pendingOpeningSession = new Builder(activity).setApplicationId(this.mAppId).setTokenCachingStrategy(getTokenCache()).build();
        this.pendingAuthorizationActivity = activity;
        if (permissions == null) {
            permissions = new String[0];
        }
        this.pendingAuthorizationPermissions = permissions;
        OpenRequest openRequest = new OpenRequest(activity).setCallback(new StatusCallback() {
            public void call(Session callbackSession, SessionState state, Exception exception) {
                Facebook.this.onSessionCallback(callbackSession, state, exception, listener);
            }
        }).setLoginBehavior(behavior).setRequestCode(activityCode).setPermissions(Arrays.asList(this.pendingAuthorizationPermissions));
        Session session = this.pendingOpeningSession;
        if (this.pendingAuthorizationPermissions.length > 0) {
            z = true;
        }
        openSession(session, openRequest, z);
    }

    private void openSession(Session session, OpenRequest openRequest, boolean isPublish) {
        openRequest.setIsLegacy(true);
        if (isPublish) {
            session.openForPublish(openRequest);
        } else {
            session.openForRead(openRequest);
        }
    }

    private void onSessionCallback(Session callbackSession, SessionState state, Exception exception, DialogListener listener) {
        Bundle extras = callbackSession.getAuthorizationBundle();
        if (state == SessionState.OPENED) {
            Session sessionToClose = null;
            synchronized (this.lock) {
                if (callbackSession != this.session) {
                    sessionToClose = this.session;
                    this.session = callbackSession;
                    this.sessionInvalidated = false;
                }
            }
            if (sessionToClose != null) {
                sessionToClose.close();
            }
            listener.onComplete(extras);
        } else if (exception == null) {
        } else {
            if (exception instanceof FacebookOperationCanceledException) {
                listener.onCancel();
            } else if ((exception instanceof FacebookAuthorizationException) && extras != null && extras.containsKey(Session.WEB_VIEW_ERROR_CODE_KEY) && extras.containsKey(Session.WEB_VIEW_FAILING_URL_KEY)) {
                listener.onError(new DialogError(exception.getMessage(), extras.getInt(Session.WEB_VIEW_ERROR_CODE_KEY), extras.getString(Session.WEB_VIEW_FAILING_URL_KEY)));
            } else {
                listener.onFacebookError(new FacebookError(exception.getMessage()));
            }
        }
    }

    private boolean validateServiceIntent(Context context, Intent intent) {
        ResolveInfo resolveInfo = context.getPackageManager().resolveService(intent, 0);
        if (resolveInfo == null) {
            return false;
        }
        return validateAppSignatureForPackage(context, resolveInfo.serviceInfo.packageName);
    }

    private boolean validateAppSignatureForPackage(Context context, String packageName) {
        try {
            for (Signature signature : context.getPackageManager().getPackageInfo(packageName, 64).signatures) {
                if (signature.toCharsString().equals(FB_APP_SIGNATURE)) {
                    return true;
                }
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    @Deprecated
    public void authorizeCallback(int requestCode, int resultCode, Intent data) {
        checkUserSession("authorizeCallback");
        Session pending = this.pendingOpeningSession;
        if (pending != null && pending.onActivityResult(this.pendingAuthorizationActivity, requestCode, resultCode, data)) {
            this.pendingOpeningSession = null;
            this.pendingAuthorizationActivity = null;
            this.pendingAuthorizationPermissions = null;
        }
    }

    @Deprecated
    public boolean extendAccessToken(Context context, ServiceListener serviceListener) {
        checkUserSession("extendAccessToken");
        Intent intent = new Intent();
        intent.setClassName("com.facebook.katana", "com.facebook.katana.platform.TokenRefreshService");
        if (validateServiceIntent(context, intent)) {
            return context.bindService(intent, new TokenRefreshServiceConnection(context, serviceListener), 1);
        }
        return false;
    }

    @Deprecated
    public boolean extendAccessTokenIfNeeded(Context context, ServiceListener serviceListener) {
        checkUserSession("extendAccessTokenIfNeeded");
        if (shouldExtendAccessToken()) {
            return extendAccessToken(context, serviceListener);
        }
        return true;
    }

    @Deprecated
    public boolean shouldExtendAccessToken() {
        checkUserSession("shouldExtendAccessToken");
        return isSessionValid() && System.currentTimeMillis() - this.lastAccessUpdateMillisecondsAfterEpoch >= REFRESH_TOKEN_BARRIER;
    }

    @Deprecated
    public String logout(Context context) throws MalformedURLException, IOException {
        return logoutImpl(context);
    }

    /* Access modifiers changed, original: 0000 */
    public String logoutImpl(Context context) throws MalformedURLException, IOException {
        Session sessionToClose;
        checkUserSession("logout");
        Bundle b = new Bundle();
        b.putString("method", "auth.expireSession");
        String response = request(b);
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.lock) {
            sessionToClose = this.session;
            this.session = null;
            this.accessToken = null;
            this.accessExpiresMillisecondsAfterEpoch = 0;
            this.lastAccessUpdateMillisecondsAfterEpoch = currentTimeMillis;
            this.sessionInvalidated = false;
        }
        if (sessionToClose != null) {
            sessionToClose.closeAndClearTokenInformation();
        }
        return response;
    }

    @Deprecated
    public String request(Bundle parameters) throws MalformedURLException, IOException {
        if (parameters.containsKey("method")) {
            return requestImpl(null, parameters, "GET");
        }
        throw new IllegalArgumentException("API method must be specified. (parameters must contain key \"method\" and value). See http://developers.facebook.com/docs/reference/rest/");
    }

    @Deprecated
    public String request(String graphPath) throws MalformedURLException, IOException {
        return requestImpl(graphPath, new Bundle(), "GET");
    }

    @Deprecated
    public String request(String graphPath, Bundle parameters) throws MalformedURLException, IOException {
        return requestImpl(graphPath, parameters, "GET");
    }

    @Deprecated
    public String request(String graphPath, Bundle params, String httpMethod) throws FileNotFoundException, MalformedURLException, IOException {
        return requestImpl(graphPath, params, httpMethod);
    }

    /* Access modifiers changed, original: 0000 */
    public String requestImpl(String graphPath, Bundle params, String httpMethod) throws FileNotFoundException, MalformedURLException, IOException {
        params.putString("format", "json");
        if (isSessionValid()) {
            params.putString("access_token", getAccessToken());
        }
        return Util.openUrl(graphPath != null ? GRAPH_BASE_URL + graphPath : RESTSERVER_URL, httpMethod, params);
    }

    @Deprecated
    public void dialog(Context context, String action, DialogListener listener) {
        dialog(context, action, new Bundle(), listener);
    }

    @Deprecated
    public void dialog(Context context, String action, Bundle parameters, DialogListener listener) {
        parameters.putString(ServerProtocol.DIALOG_PARAM_DISPLAY, ServerProtocol.FALLBACK_DIALOG_DISPLAY_VALUE_TOUCH);
        parameters.putString(ServerProtocol.DIALOG_PARAM_REDIRECT_URI, REDIRECT_URI);
        if (action.equals(LOGIN)) {
            parameters.putString("type", "user_agent");
            parameters.putString("client_id", this.mAppId);
        } else {
            parameters.putString("app_id", this.mAppId);
            if (isSessionValid()) {
                parameters.putString("access_token", getAccessToken());
            }
        }
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") != 0) {
            Util.showAlert(context, "Error", "Application requires permission to access the Internet");
        } else {
            new FbDialog(context, action, parameters, listener).show();
        }
    }

    @Deprecated
    public boolean isSessionValid() {
        return getAccessToken() != null && (getAccessExpires() == 0 || System.currentTimeMillis() < getAccessExpires());
    }

    @Deprecated
    public void setSession(Session session) {
        if (session == null) {
            throw new IllegalArgumentException("session cannot be null");
        }
        synchronized (this.lock) {
            this.userSetSession = session;
        }
    }

    private void checkUserSession(String methodName) {
        if (this.userSetSession != null) {
            throw new UnsupportedOperationException(String.format("Cannot call %s after setSession has been called.", new Object[]{methodName}));
        }
    }

    /* JADX WARNING: Missing block: B:21:0x0023, code skipped:
            if (r0 != null) goto L_0x0027;
     */
    /* JADX WARNING: Missing block: B:23:0x0027, code skipped:
            if (r3 == null) goto L_0x0050;
     */
    /* JADX WARNING: Missing block: B:24:0x0029, code skipped:
            r5 = r3.getPermissions();
     */
    /* JADX WARNING: Missing block: B:25:0x002d, code skipped:
            r2 = new com.facebook.Session.Builder(r11.pendingAuthorizationActivity).setApplicationId(r11.mAppId).setTokenCachingStrategy(getTokenCache()).build();
     */
    /* JADX WARNING: Missing block: B:26:0x004c, code skipped:
            if (r2.getState() == com.facebook.SessionState.CREATED_TOKEN_LOADED) goto L_0x0060;
     */
    /* JADX WARNING: Missing block: B:29:0x0052, code skipped:
            if (r11.pendingAuthorizationPermissions == null) goto L_0x005b;
     */
    /* JADX WARNING: Missing block: B:30:0x0054, code skipped:
            r5 = java.util.Arrays.asList(r11.pendingAuthorizationPermissions);
     */
    /* JADX WARNING: Missing block: B:31:0x005b, code skipped:
            r5 = java.util.Collections.emptyList();
     */
    /* JADX WARNING: Missing block: B:32:0x0060, code skipped:
            r4 = new com.facebook.Session.OpenRequest(r11.pendingAuthorizationActivity).setPermissions(r5);
     */
    /* JADX WARNING: Missing block: B:33:0x006f, code skipped:
            if (r5.isEmpty() != false) goto L_0x0094;
     */
    /* JADX WARNING: Missing block: B:34:0x0071, code skipped:
            r7 = true;
     */
    /* JADX WARNING: Missing block: B:35:0x0072, code skipped:
            openSession(r2, r4, r7);
            r1 = null;
            r6 = null;
            r10 = r11.lock;
     */
    /* JADX WARNING: Missing block: B:36:0x0079, code skipped:
            monitor-enter(r10);
     */
    /* JADX WARNING: Missing block: B:39:0x007c, code skipped:
            if (r11.sessionInvalidated != false) goto L_0x0082;
     */
    /* JADX WARNING: Missing block: B:41:0x0080, code skipped:
            if (r11.session != null) goto L_0x008a;
     */
    /* JADX WARNING: Missing block: B:42:0x0082, code skipped:
            r1 = r11.session;
            r11.session = r2;
            r6 = r2;
            r11.sessionInvalidated = false;
     */
    /* JADX WARNING: Missing block: B:43:0x008a, code skipped:
            monitor-exit(r10);
     */
    /* JADX WARNING: Missing block: B:44:0x008b, code skipped:
            if (r1 == null) goto L_0x0090;
     */
    /* JADX WARNING: Missing block: B:45:0x008d, code skipped:
            r1.close();
     */
    /* JADX WARNING: Missing block: B:47:0x0094, code skipped:
            r7 = false;
     */
    /* JADX WARNING: Missing block: B:58:0x0090, code skipped:
            continue;
     */
    /* JADX WARNING: Missing block: B:65:?, code skipped:
            return null;
     */
    /* JADX WARNING: Missing block: B:66:?, code skipped:
            return null;
     */
    @java.lang.Deprecated
    public final com.facebook.Session getSession() {
        /*
        r11 = this;
        r9 = 0;
        r8 = 0;
    L_0x0002:
        r0 = 0;
        r3 = 0;
        r10 = r11.lock;
        monitor-enter(r10);
        r7 = r11.userSetSession;	 Catch:{ all -> 0x001b }
        if (r7 == 0) goto L_0x000f;
    L_0x000b:
        r6 = r11.userSetSession;	 Catch:{ all -> 0x001b }
        monitor-exit(r10);	 Catch:{ all -> 0x001b }
    L_0x000e:
        return r6;
    L_0x000f:
        r7 = r11.session;	 Catch:{ all -> 0x001b }
        if (r7 != 0) goto L_0x0017;
    L_0x0013:
        r7 = r11.sessionInvalidated;	 Catch:{ all -> 0x001b }
        if (r7 != 0) goto L_0x001e;
    L_0x0017:
        r6 = r11.session;	 Catch:{ all -> 0x001b }
        monitor-exit(r10);	 Catch:{ all -> 0x001b }
        goto L_0x000e;
    L_0x001b:
        r7 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x001b }
        throw r7;
    L_0x001e:
        r0 = r11.accessToken;	 Catch:{ all -> 0x001b }
        r3 = r11.session;	 Catch:{ all -> 0x001b }
        monitor-exit(r10);	 Catch:{ all -> 0x001b }
        if (r0 != 0) goto L_0x0027;
    L_0x0025:
        r6 = r9;
        goto L_0x000e;
    L_0x0027:
        if (r3 == 0) goto L_0x0050;
    L_0x0029:
        r5 = r3.getPermissions();
    L_0x002d:
        r7 = new com.facebook.Session$Builder;
        r10 = r11.pendingAuthorizationActivity;
        r7.<init>(r10);
        r10 = r11.mAppId;
        r7 = r7.setApplicationId(r10);
        r10 = r11.getTokenCache();
        r7 = r7.setTokenCachingStrategy(r10);
        r2 = r7.build();
        r7 = r2.getState();
        r10 = com.facebook.SessionState.CREATED_TOKEN_LOADED;
        if (r7 == r10) goto L_0x0060;
    L_0x004e:
        r6 = r9;
        goto L_0x000e;
    L_0x0050:
        r7 = r11.pendingAuthorizationPermissions;
        if (r7 == 0) goto L_0x005b;
    L_0x0054:
        r7 = r11.pendingAuthorizationPermissions;
        r5 = java.util.Arrays.asList(r7);
        goto L_0x002d;
    L_0x005b:
        r5 = java.util.Collections.emptyList();
        goto L_0x002d;
    L_0x0060:
        r7 = new com.facebook.Session$OpenRequest;
        r10 = r11.pendingAuthorizationActivity;
        r7.<init>(r10);
        r4 = r7.setPermissions(r5);
        r7 = r5.isEmpty();
        if (r7 != 0) goto L_0x0094;
    L_0x0071:
        r7 = 1;
    L_0x0072:
        r11.openSession(r2, r4, r7);
        r1 = 0;
        r6 = 0;
        r10 = r11.lock;
        monitor-enter(r10);
        r7 = r11.sessionInvalidated;	 Catch:{ all -> 0x0096 }
        if (r7 != 0) goto L_0x0082;
    L_0x007e:
        r7 = r11.session;	 Catch:{ all -> 0x0096 }
        if (r7 != 0) goto L_0x008a;
    L_0x0082:
        r1 = r11.session;	 Catch:{ all -> 0x0096 }
        r11.session = r2;	 Catch:{ all -> 0x0096 }
        r6 = r2;
        r7 = 0;
        r11.sessionInvalidated = r7;	 Catch:{ all -> 0x0096 }
    L_0x008a:
        monitor-exit(r10);	 Catch:{ all -> 0x0096 }
        if (r1 == 0) goto L_0x0090;
    L_0x008d:
        r1.close();
    L_0x0090:
        if (r6 == 0) goto L_0x0002;
    L_0x0092:
        goto L_0x000e;
    L_0x0094:
        r7 = r8;
        goto L_0x0072;
    L_0x0096:
        r7 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x0096 }
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.android.Facebook.getSession():com.facebook.Session");
    }

    @Deprecated
    public String getAccessToken() {
        Session s = getSession();
        if (s != null) {
            return s.getAccessToken();
        }
        return null;
    }

    @Deprecated
    public long getAccessExpires() {
        Session s = getSession();
        if (s != null) {
            return s.getExpirationDate().getTime();
        }
        return this.accessExpiresMillisecondsAfterEpoch;
    }

    @Deprecated
    public long getLastAccessUpdate() {
        return this.lastAccessUpdateMillisecondsAfterEpoch;
    }

    @Deprecated
    public void setTokenFromCache(String accessToken, long accessExpires, long lastAccessUpdate) {
        checkUserSession("setTokenFromCache");
        synchronized (this.lock) {
            this.accessToken = accessToken;
            this.accessExpiresMillisecondsAfterEpoch = accessExpires;
            this.lastAccessUpdateMillisecondsAfterEpoch = lastAccessUpdate;
        }
    }

    @Deprecated
    public void setAccessToken(String token) {
        checkUserSession("setAccessToken");
        synchronized (this.lock) {
            this.accessToken = token;
            this.lastAccessUpdateMillisecondsAfterEpoch = System.currentTimeMillis();
            this.sessionInvalidated = true;
        }
    }

    @Deprecated
    public void setAccessExpires(long timestampInMsec) {
        checkUserSession("setAccessExpires");
        synchronized (this.lock) {
            this.accessExpiresMillisecondsAfterEpoch = timestampInMsec;
            this.lastAccessUpdateMillisecondsAfterEpoch = System.currentTimeMillis();
            this.sessionInvalidated = true;
        }
    }

    @Deprecated
    public void setAccessExpiresIn(String expiresInSecsFromNow) {
        checkUserSession("setAccessExpiresIn");
        if (expiresInSecsFromNow != null) {
            setAccessExpires(expiresInSecsFromNow.equals("0") ? 0 : System.currentTimeMillis() + (Long.parseLong(expiresInSecsFromNow) * 1000));
        }
    }

    @Deprecated
    public String getAppId() {
        return this.mAppId;
    }

    @Deprecated
    public void setAppId(String appId) {
        checkUserSession("setAppId");
        synchronized (this.lock) {
            this.mAppId = appId;
            this.sessionInvalidated = true;
        }
    }

    private TokenCachingStrategy getTokenCache() {
        if (this.tokenCache == null) {
            this.tokenCache = new SetterTokenCachingStrategy(this, null);
        }
        return this.tokenCache;
    }

    private static String[] stringArray(List<String> list) {
        String[] array = new String[(list != null ? list.size() : 0)];
        if (list != null) {
            for (int i = 0; i < array.length; i++) {
                array[i] = (String) list.get(i);
            }
        }
        return array;
    }

    private static List<String> stringList(String[] array) {
        if (array != null) {
            return Arrays.asList(array);
        }
        return Collections.emptyList();
    }

    @Deprecated
    public static String getAttributionId(ContentResolver contentResolver) {
        return Settings.getAttributionId(contentResolver);
    }

    @Deprecated
    public boolean getShouldAutoPublishInstall() {
        return Settings.getShouldAutoPublishInstall();
    }

    @Deprecated
    public void setShouldAutoPublishInstall(boolean value) {
        Settings.setShouldAutoPublishInstall(value);
    }
}
