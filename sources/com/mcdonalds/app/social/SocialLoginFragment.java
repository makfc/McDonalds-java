package com.mcdonalds.app.social;

import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.facebook.HttpMethod;
import com.facebook.Request.Callback;
import com.facebook.Request.GraphUserCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.OpenRequest;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.stetho.common.Utf8Charset;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.McDonaldsApplication;
import com.mcdonalds.app.p043ui.URLNavigationActivity.PermissionListener;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.social.SocialLogin.SocialLoginClientCallback;
import com.mcdonalds.app.social.SocialLogin.SocialLoginListener;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.middleware.model.MWWechatInfoData;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.SocialLoginAuthenticationResults;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.instrumentation.okhttp2.OkHttp2Instrumentation;
import com.newrelic.agent.android.tracing.Trace;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.tencent.p050mm.sdk.modelmsg.SendAuth.Req;
import com.tencent.p050mm.sdk.openapi.IWXAPI;
import com.tencent.p050mm.sdk.openapi.WXAPIFactory;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class SocialLoginFragment extends URLNavigationFragment implements ConnectionCallbacks, OnConnectionFailedListener, SocialLoginClientCallback, SocialLoginListener {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final int RC_SIGN_IN = 10;
    private static final int RC_USER_RECOVERABLE_ERROR = 11;
    private static final int RC_WECHAT_SIGN_IN = 12;
    private static final int REQUEST_PERMISSION_ACCOUNT = 13;
    public static final String TAG = "SocialLoginFragment";
    public static String code;
    private static String getUserInfoRequest = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
    public static AsyncToken mStateToken;
    private static final String weChatAppId = McDonaldsApplication.getInstance().getString(C2658R.string.wechat_api_app_id);
    protected String acces_token;
    private boolean disableFacebookAndGoogle;
    private ConnectionResult mConnectionResult;
    private SocialNetwork mCurrentNetwork;
    private boolean mDisableSocialButtons = false;
    private boolean mFBUserInfoInProgress = false;
    private StatusCallback mFaceBookStatusCallback = new C30332();
    private UiLifecycleHelper mFacebookLifeCycleHelper;
    private GoogleApiClient mGoogleApiClient;
    private boolean mIntentInProgress;
    private boolean mSignInClicked;
    private SocialLogin mSocialLoginHelper;
    private IWXAPI mWeChatApi;
    protected String nickName;
    protected String openid;
    protected String provider;

    /* renamed from: com.mcdonalds.app.social.SocialLoginFragment$1 */
    class C30321 implements PermissionListener {
        C30321() {
        }

        public void onRequestPermissionsResult(int requestCode, String permission, int grantResult) {
            Ensighten.evaluateEvent(this, "onRequestPermissionsResult", new Object[]{new Integer(requestCode), permission, new Integer(grantResult)});
            if (grantResult == 0) {
                SocialLoginFragment.access$002(SocialLoginFragment.this, true);
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLoginFragment", "access$100", new Object[]{SocialLoginFragment.this}).isConnected()) {
                    SocialLoginFragment.access$200(SocialLoginFragment.this);
                } else {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLoginFragment", "access$100", new Object[]{SocialLoginFragment.this}).connect();
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.social.SocialLoginFragment$2 */
    class C30332 implements StatusCallback {
        C30332() {
        }

        public void call(Session session, SessionState state, Exception exception) {
            Ensighten.evaluateEvent(this, "call", new Object[]{session, state, exception});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLoginFragment", "access$300", new Object[]{SocialLoginFragment.this}) == null || Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLoginFragment", "access$300", new Object[]{SocialLoginFragment.this}).getType() != 2) {
            }
            if (state.isOpened()) {
                MCDLog.temp("Facebook Session open...");
                SocialLoginFragment.access$400(SocialLoginFragment.this, session);
            } else if (state.isClosed()) {
                MCDLog.temp("Facebook Session closed...");
            }
        }
    }

    /* renamed from: com.mcdonalds.app.social.SocialLoginFragment$3 */
    class C30343 implements AsyncListener<MWWechatInfoData> {
        C30343() {
        }

        public void onResponse(MWWechatInfoData response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null) {
                SocialLoginFragment.this.acces_token = response.accessToken;
                SocialLoginFragment.this.openid = response.internalID;
                SocialLoginFragment.this.weChatRequestForUserInfo(SocialLoginFragment.this.acces_token, SocialLoginFragment.this.openid);
                return;
            }
            AsyncException.report(exception);
        }
    }

    /* renamed from: com.mcdonalds.app.social.SocialLoginFragment$5 */
    class C30365 extends AsyncTask<Void, Void, String> implements TraceFieldInterface {
        public Trace _nr_trace;

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        C30365() {
        }

        /* Access modifiers changed, original: protected|varargs */
        public String doInBackground(Void... params) {
            Ensighten.evaluateEvent(this, "doInBackground", new Object[]{params});
            String accessToken = null;
            try {
                return GoogleAuthUtil.getToken(SocialLoginFragment.this.getActivity(), Plus.AccountApi.getAccountName(Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLoginFragment", "access$100", new Object[]{SocialLoginFragment.this})), "oauth2:" + Plus.SCOPE_PLUS_LOGIN);
            } catch (IOException transientEx) {
                MCDLog.temp(transientEx.getLocalizedMessage());
                return accessToken;
            } catch (UserRecoverableAuthException e) {
                SocialLoginFragment.this.getActivity().startActivityForResult(e.getIntent(), 11);
                return accessToken;
            } catch (GoogleAuthException authEx) {
                MCDLog.temp(authEx.getLocalizedMessage());
                return accessToken;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(String token) {
            Ensighten.evaluateEvent(this, "onPostExecute", new Object[]{token});
            if (token != null) {
                SocialLoginFragment.access$700(SocialLoginFragment.this, token);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.social.SocialLoginFragment$6 */
    class C30376 implements GraphUserCallback {
        C30376() {
        }

        public void onCompleted(GraphUser user, Response response) {
            Ensighten.evaluateEvent(this, "onCompleted", new Object[]{user, response});
            if (user != null) {
                SocialLoginFragment.access$302(SocialLoginFragment.this, null);
                SocialLoginFragment.access$800(SocialLoginFragment.this, user);
                SocialLoginFragment.access$902(SocialLoginFragment.this, false);
                return;
            }
            throw response.getError().getException();
        }
    }

    private class GoogleUserInfoAsyncTask extends AsyncTask<Void, Void, String> implements TraceFieldInterface {
        public Trace _nr_trace;
        final /* synthetic */ SocialLoginFragment this$0;

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        /* Access modifiers changed, original: protected|varargs */
        public String doInBackground(Void... params) {
            Ensighten.evaluateEvent(this, "doInBackground", new Object[]{params});
            String accessToken = null;
            try {
                return GoogleAuthUtil.getToken(this.this$0.getActivity(), Plus.AccountApi.getAccountName(Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLoginFragment", "access$100", new Object[]{this.this$0})), "oauth2:" + Plus.SCOPE_PLUS_LOGIN);
            } catch (IOException transientEx) {
                MCDLog.temp(transientEx.getLocalizedMessage());
                return accessToken;
            } catch (UserRecoverableAuthException e) {
                this.this$0.getActivity().startActivityForResult(e.getIntent(), 11);
                return accessToken;
            } catch (GoogleAuthException authEx) {
                MCDLog.temp(authEx.getLocalizedMessage());
                return accessToken;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(String accessToken) {
            Ensighten.evaluateEvent(this, "onPostExecute", new Object[]{accessToken});
            SocialLoginFragment.access$700(this.this$0, accessToken);
        }
    }

    public abstract int getLayoutResourceId();

    static /* synthetic */ boolean access$002(SocialLoginFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLoginFragment", "access$002", new Object[]{x0, new Boolean(x1)});
        x0.mSignInClicked = x1;
        return x1;
    }

    static /* synthetic */ void access$200(SocialLoginFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLoginFragment", "access$200", new Object[]{x0});
        x0.acquireGoogleUserInfo();
    }

    static /* synthetic */ SocialNetwork access$302(SocialLoginFragment x0, SocialNetwork x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLoginFragment", "access$302", new Object[]{x0, x1});
        x0.mCurrentNetwork = x1;
        return x1;
    }

    static /* synthetic */ void access$400(SocialLoginFragment x0, Session x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLoginFragment", "access$400", new Object[]{x0, x1});
        x0.acquireFacebookUserInfo(x1);
    }

    static /* synthetic */ void access$700(SocialLoginFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLoginFragment", "access$700", new Object[]{x0, x1});
        x0.onGooglePlusFlowComplete(x1);
    }

    static /* synthetic */ void access$800(SocialLoginFragment x0, GraphUser x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLoginFragment", "access$800", new Object[]{x0, x1});
        x0.onFacebookFlowComplete(x1);
    }

    static /* synthetic */ boolean access$902(SocialLoginFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLoginFragment", "access$902", new Object[]{x0, new Boolean(x1)});
        x0.mFBUserInfoInProgress = x1;
        return x1;
    }

    /* Access modifiers changed, original: protected */
    public SocialLogin getSocialLoginHelper() {
        Ensighten.evaluateEvent(this, "getSocialLoginHelper", null);
        return this.mSocialLoginHelper;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.disableFacebookAndGoogle = Configuration.getSharedInstance().getBooleanForKey("interface.disableFacebookAndGoogle");
        if (!this.disableFacebookAndGoogle) {
            this.mFacebookLifeCycleHelper = new UiLifecycleHelper(getActivity(), this.mFaceBookStatusCallback);
            this.mFacebookLifeCycleHelper.onCreate(savedInstanceState);
            this.mGoogleApiClient = new Builder(getActivity()).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Plus.API).addScope(Plus.SCOPE_PLUS_PROFILE).build();
        }
        mStateToken = new AsyncToken(SocialNetwork.WECHAT_NAME);
        Log.d(TAG, "mWeChatApi.registerApp(weChatAppId);");
    }

    private String urlEnodeUTF8(String str) {
        Ensighten.evaluateEvent(this, "urlEnodeUTF8", new Object[]{str});
        String result = str;
        try {
            return URLEncoder.encode(str, Utf8Charset.NAME);
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    public void onStart() {
        super.onStart();
        if (!this.disableFacebookAndGoogle) {
            this.mGoogleApiClient.connect();
        }
    }

    public void onStop() {
        super.onStop();
        if (!this.disableFacebookAndGoogle && this.mGoogleApiClient.isConnected()) {
            this.mGoogleApiClient.disconnect();
        }
        if (this.mWeChatApi != null) {
            this.mWeChatApi.unregisterApp();
            Log.d(TAG, "mWeChatApi.unregisterApp()");
        }
    }

    public void onResume() {
        super.onResume();
        if (this.mSocialLoginHelper != null) {
            this.mSocialLoginHelper.refresh();
        }
        if (this.mCurrentNetwork != null) {
            switch (this.mCurrentNetwork.getType()) {
                case 2:
                    if (!this.disableFacebookAndGoogle) {
                        facebookOnResume();
                        break;
                    }
                    break;
            }
        }
        if (code != null) {
            weChatRequestForToken(code);
            UIUtils.startActivityIndicator(getActivity(), getString(C2658R.string.dialog_signing_in));
            Log.d(TAG, "wechat code: " + code);
            code = null;
        }
        if (!this.disableFacebookAndGoogle) {
            this.mFacebookLifeCycleHelper.onResume();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!this.disableFacebookAndGoogle) {
            this.mFacebookLifeCycleHelper.onActivityResult(requestCode, resultCode, data);
        }
        if (requestCode == 10) {
            this.mIntentInProgress = false;
            if (resultCode != -1) {
                this.mSignInClicked = false;
            } else if (!this.disableFacebookAndGoogle && !this.mGoogleApiClient.isConnecting()) {
                this.mGoogleApiClient.connect();
            }
        } else if (requestCode == 11 && resultCode == -1 && !this.disableFacebookAndGoogle) {
            acquireGoogleUserInfo();
        }
    }

    public void facebookOnResume() {
        Ensighten.evaluateEvent(this, "facebookOnResume", null);
        Session session = Session.getActiveSession();
        if (session == null) {
            return;
        }
        if (session.isOpened() || session.isClosed()) {
            this.mFaceBookStatusCallback.call(session, session.getState(), null);
        }
    }

    public void onPause() {
        super.onPause();
        if (!this.disableFacebookAndGoogle) {
            this.mFacebookLifeCycleHelper.onPause();
        }
        this.mDisableSocialButtons = false;
    }

    public void onDestroy() {
        super.onDestroy();
        if (!this.disableFacebookAndGoogle) {
            this.mFacebookLifeCycleHelper.onDestroy();
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        Ensighten.evaluateEvent(this, "onSaveInstanceState", new Object[]{outState});
        super.onSaveInstanceState(outState);
        if (!this.disableFacebookAndGoogle) {
            this.mFacebookLifeCycleHelper.onSaveInstanceState(outState);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutResourceId(), container, false);
        View socialLoginView = rootView.findViewById(C2358R.C2357id.social_login);
        if (socialLoginView != null) {
            this.mSocialLoginHelper = new SocialLogin(this, new SocialLoginViewHolder(socialLoginView), this);
            this.mSocialLoginHelper.setListener(this);
        }
        return rootView;
    }

    public void onSocialNetworkSelected(SocialNetwork socialNetwork) {
        Ensighten.evaluateEvent(this, "onSocialNetworkSelected", new Object[]{socialNetwork});
        if (!this.mDisableSocialButtons) {
            this.mCurrentNetwork = socialNetwork;
            switch (this.mCurrentNetwork.getType()) {
                case 1:
                    if (!this.disableFacebookAndGoogle) {
                        onGooglePlusSelected();
                        return;
                    }
                    return;
                case 2:
                    if (!this.disableFacebookAndGoogle) {
                        onFacebookSelected();
                        return;
                    }
                    return;
                case 3:
                    onWeChatSelected();
                    return;
                default:
                    return;
            }
        }
    }

    private void onGooglePlusSelected() {
        Ensighten.evaluateEvent(this, "onGooglePlusSelected", null);
        if (getNavigationActivity() != null) {
            getNavigationActivity().requestPermission("android.permission.GET_ACCOUNTS", 13, C2658R.string.permission_explanation_account, new C30321());
        }
    }

    private void onFacebookSelected() {
        Ensighten.evaluateEvent(this, "onFacebookSelected", null);
        Session session = Session.getActiveSession();
        session.closeAndClearTokenInformation();
        if (session == null || session.isOpened() || session.isClosed()) {
            List permissions = new ArrayList();
            permissions.add("email");
            Session.openActiveSession(getActivity(), true, permissions, this.mFaceBookStatusCallback);
            return;
        }
        session.openForRead(new OpenRequest((Fragment) this).setPermissions(Arrays.asList(new String[]{"public_profile", "email"})).setCallback(this.mFaceBookStatusCallback));
    }

    private void onWeChatSelected() {
        Ensighten.evaluateEvent(this, "onWeChatSelected", null);
        if (this.mWeChatApi == null || !this.mWeChatApi.isWXAppInstalled()) {
            MCDAlertDialogBuilder.withContext(getActivity()).setMessage((int) C2658R.string.wechat_not_installed_alert).setPositiveButton(getString(C2658R.string.f6083ok), null).create().show();
            return;
        }
        Req req = new Req();
        req.scope = "snsapi_userinfo";
        req.state = mStateToken.toString();
        this.mWeChatApi.sendReq(req);
        Log.d(TAG, "mWeChatApi.sendReq(req)");
    }

    private void onGooglePlusFlowComplete(String accessToken) {
        Ensighten.evaluateEvent(this, "onGooglePlusFlowComplete", new Object[]{accessToken});
        SocialLoginAuthenticationResults results = new SocialLoginAuthenticationResults(accessToken);
        Person me = Plus.PeopleApi.getCurrentPerson(this.mGoogleApiClient);
        if (me == null) {
            AsyncException.report(new AsyncException(getActivity().getString(C2658R.string.google_plus_profile_error)));
            return;
        }
        if (me.getName() != null) {
            results.setFirstName(me.getName().getGivenName());
            results.setLastName(me.getName().getFamilyName());
        }
        results.setUserId(me.getId());
        results.setEmailAddress(Plus.AccountApi.getAccountName(this.mGoogleApiClient));
        this.provider = SocialNetwork.GOOGLEPLUS_NAME;
        onSocialNetworkAuthenticationComplete(results);
    }

    private void onFacebookFlowComplete(GraphUser user) {
        Ensighten.evaluateEvent(this, "onFacebookFlowComplete", new Object[]{user});
        SocialLoginAuthenticationResults results = new SocialLoginAuthenticationResults(Session.getActiveSession().getAccessToken());
        results.setFirstName(user.getFirstName());
        results.setLastName(user.getLastName());
        results.setUserId(user.getId());
        results.setEmailAddress((String) user.getProperty("email"));
        if (!(user.getLocation() == null || user.getLocation().getLocation() == null)) {
            results.setZipCode(user.getLocation().getLocation().getZip());
        }
        this.provider = "facebook";
        onSocialNetworkAuthenticationComplete(results);
    }

    private void onWeChatFlowComplete(String username, String userId, String accessToken, boolean AllowSocialLoginWithoutEmail) {
        Ensighten.evaluateEvent(this, "onWeChatFlowComplete", new Object[]{username, userId, accessToken, new Boolean(AllowSocialLoginWithoutEmail)});
        onSocialNetworkAuthenticationComplete(new SocialLoginAuthenticationResults(username, userId, accessToken, AllowSocialLoginWithoutEmail));
    }

    public void onConnected(Bundle bundle) {
        Ensighten.evaluateEvent(this, "onConnected", new Object[]{bundle});
        MCDLog.temp("Google+ Bundle: " + (bundle == null ? "" : bundle.toString()));
        if (this.mSignInClicked && !this.disableFacebookAndGoogle) {
            acquireGoogleUserInfo();
        }
        this.mSignInClicked = false;
    }

    public void onConnectionSuspended(int i) {
        Ensighten.evaluateEvent(this, "onConnectionSuspended", new Object[]{new Integer(i)});
        if (!this.disableFacebookAndGoogle && this.mGoogleApiClient != null) {
            this.mGoogleApiClient.connect();
        }
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        Ensighten.evaluateEvent(this, "onConnectionFailed", new Object[]{connectionResult});
        if (!this.mIntentInProgress) {
            this.mConnectionResult = connectionResult;
            if (this.mSignInClicked && !this.disableFacebookAndGoogle) {
                resolveGoogleSignInError();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void weChatRequestForToken(String code) {
        Ensighten.evaluateEvent(this, "weChatRequestForToken", new Object[]{code});
        ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getSocialNetworkAccessToken(3, code, new C30343());
    }

    /* Access modifiers changed, original: protected */
    public void weChatRequestForUserInfo(final String access_token, final String openid) {
        Ensighten.evaluateEvent(this, "weChatRequestForUserInfo", new Object[]{access_token, openid});
        Thread thread = new Thread(new Runnable() {
            public void run() {
                Ensighten.evaluateEvent(this, "run", null);
                try {
                    JSONObject obj = JSONObjectInstrumentation.init(Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLoginFragment", "access$600", new Object[]{SocialLoginFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.social.SocialLoginFragment", "access$500", null).replace("ACCESS_TOKEN", access_token).replace("OPENID", openid)}));
                    SocialLoginFragment.this.nickName = obj.getString("nickname");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e2) {
                }
            }
        });
        thread.start();
        try {
            thread.join();
            onWeChatFlowComplete(this.nickName, openid, this.acces_token, true);
            UIUtils.stopActivityIndicator();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String doGetRequest(String url) throws IOException {
        Ensighten.evaluateEvent(this, "doGetRequest", new Object[]{url});
        OkHttpClient HttpClient = new OkHttpClient();
        Request.Builder url2 = new Request.Builder().url(url);
        Request request = !(url2 instanceof Request.Builder) ? url2.build() : OkHttp2Instrumentation.build(url2);
        return (!(HttpClient instanceof OkHttpClient) ? HttpClient.newCall(request) : OkHttp2Instrumentation.newCall(HttpClient, request)).execute().body().string();
    }

    private String doPostRequest(String url, String json) throws IOException {
        Ensighten.evaluateEvent(this, "doPostRequest", new Object[]{url, json});
        OkHttpClient HttpClient = new OkHttpClient();
        Request.Builder post = new Request.Builder().url(url).post(RequestBody.create(JSON, json));
        Request request = !(post instanceof Request.Builder) ? post.build() : OkHttp2Instrumentation.build(post);
        return (!(HttpClient instanceof OkHttpClient) ? HttpClient.newCall(request) : OkHttp2Instrumentation.newCall(HttpClient, request)).execute().body().string();
    }

    private void resolveGoogleSignInError() {
        Ensighten.evaluateEvent(this, "resolveGoogleSignInError", null);
        if (!this.mIntentInProgress && this.mConnectionResult.hasResolution()) {
            try {
                this.mIntentInProgress = true;
                getActivity().startIntentSenderForResult(this.mConnectionResult.getResolution().getIntentSender(), 10, null, 0, 0, 0);
            } catch (SendIntentException e) {
                this.mIntentInProgress = false;
                this.mGoogleApiClient.connect();
            }
        }
    }

    private void acquireGoogleUserInfo() {
        Ensighten.evaluateEvent(this, "acquireGoogleUserInfo", null);
        C30365 c30365 = new C30365();
        Void[] voidArr = new Void[0];
        if (c30365 instanceof AsyncTask) {
            AsyncTaskInstrumentation.execute(c30365, voidArr);
        } else {
            c30365.execute(voidArr);
        }
    }

    private void acquireFacebookUserInfo(Session session) {
        Ensighten.evaluateEvent(this, "acquireFacebookUserInfo", new Object[]{session});
        if (!this.mFBUserInfoInProgress && session.isOpened()) {
            this.mFBUserInfoInProgress = true;
            com.facebook.Request request = com.facebook.Request.newMeRequest(session, new C30376());
            Bundle bundle = request.getParameters();
            bundle.putString("fields", "email,first_name,last_name");
            request.setParameters(bundle);
            request.executeAsync();
        }
    }

    public boolean isSupported(int type) {
        Ensighten.evaluateEvent(this, "isSupported", new Object[]{new Integer(type)});
        switch (type) {
            case 3:
                String weChatAppId = McDonaldsApplication.getInstance().getString(C2658R.string.wechat_api_app_id);
                IWXAPI weChatApi = WXAPIFactory.createWXAPI(getActivity(), weChatAppId, false);
                weChatApi.registerApp(weChatAppId);
                return weChatApi.isWXAppInstalled() && weChatApi.isWXAppSupportAPI();
            default:
                return false;
        }
    }

    private void deauthorizeActiveFacebookAccount() {
        Ensighten.evaluateEvent(this, "deauthorizeActiveFacebookAccount", null);
        final Session session = Session.getActiveSession();
        new com.facebook.Request(session, "/me/permissions", null, HttpMethod.DELETE, new Callback() {
            public void onCompleted(Response response) {
                Ensighten.evaluateEvent(this, "onCompleted", new Object[]{response});
                session.closeAndClearTokenInformation();
            }
        }).executeAsync();
    }
}
