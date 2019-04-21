package com.mcdonalds.app.startup;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.Toast;
import com.ensighten.Ensighten;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.McDonaldsApplication;
import com.mcdonalds.app.account.ProfileUpdateActivity;
import com.mcdonalds.app.analytics.NewRelic.NewRelicWrapper;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.analytics.ensighten.EnsightenWrapper;
import com.mcdonalds.app.customer.push.ExtendedData;
import com.mcdonalds.app.customer.push.ExtendedData.DeepLinkingType;
import com.mcdonalds.app.customer.push.NotificationManager;
import com.mcdonalds.app.gmalite.customer.LitePolicyUpdatesActivity;
import com.mcdonalds.app.p043ui.NavigationDrawerFragment;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity.PermissionListener;
import com.mcdonalds.app.tutorial.TutorialActivity;
import com.mcdonalds.app.tutorial.TutorialFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.LanguageUtil;
import com.mcdonalds.app.util.ServiceUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.connectors.middleware.MWException;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.model.MWForceUpdateResponse;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.mcdonalds.sdk.modules.offers.OffersModule;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.BusinessArgs;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.analytics.conversionmaster.InitializationAction;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.configuration.Configuration.NetworkUpdateListener;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestManager;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import com.mcdonalds.sdk.utils.SDKUtils;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplashActivity extends URLActionBarActivity {
    private static final String CHECK_FOR_ROOTED = "interface.checkForRooted";
    private static final String DIRECT_BIN_DL = "forceUpdate.directBinaryDownload";
    private static final int REQUEST_PERMISSION_STARTUP = 1;
    private static final int REQUEST_PERMISSION_STORAGE_ACCESS = 2;
    public static final int SOCIAL_LOGIN_LOGIN_FAIL = -2105;
    private CustomerModule mCustomerModule;
    private Intent mPushIntent;
    private RequestManagerServiceConnection mServiceConnection;
    private SocialNetwork mSocialNetwork = null;
    private boolean mUpgradeRequired;
    private String mVersionName = "-1";
    private boolean permissionRequested;

    /* renamed from: com.mcdonalds.app.startup.SplashActivity$1 */
    class C24391 implements Runnable {
        C24391() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            SplashActivity.access$000(SplashActivity.this);
        }
    }

    /* renamed from: com.mcdonalds.app.startup.SplashActivity$4 */
    class C24464 implements AsyncListener<Boolean> {

        /* renamed from: com.mcdonalds.app.startup.SplashActivity$4$1 */
        class C24471 implements OnClickListener {
            C24471() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                dialog.dismiss();
                SplashActivity.access$400(SplashActivity.this);
            }
        }

        /* renamed from: com.mcdonalds.app.startup.SplashActivity$4$2 */
        class C24482 implements OnClickListener {
            C24482() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                dialog.dismiss();
                SplashActivity.access$500(SplashActivity.this);
            }
        }

        C24464() {
        }

        public void onResponse(Boolean response, AsyncToken asyncToken, AsyncException exception) {
            boolean z = false;
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, asyncToken, exception});
            SplashActivity splashActivity = SplashActivity.this;
            if (response != null) {
                z = response.booleanValue();
            }
            SplashActivity.access$302(splashActivity, z);
            if (exception != null && exception.getErrorCode() == 1001) {
                return;
            }
            if (response == null) {
                SplashActivity.access$400(SplashActivity.this);
            } else if (response.booleanValue()) {
                SplashActivity.access$600(SplashActivity.this);
            } else {
                MCDAlertDialogBuilder.withContext(SplashActivity.this).setMessage(SplashActivity.this.getString(C2658R.string.force_upgrade_recommended)).setPositiveButton((int) C2658R.string.yes, new C24482()).setNegativeButton((int) C2658R.string.f6082no, new C24471()).create().show();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.startup.SplashActivity$6 */
    class C24506 implements AsyncListener<MWForceUpdateResponse> {

        /* renamed from: com.mcdonalds.app.startup.SplashActivity$6$1 */
        class C24511 implements OnClickListener {
            C24511() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                dialog.dismiss();
                SplashActivity.access$400(SplashActivity.this);
            }
        }

        /* renamed from: com.mcdonalds.app.startup.SplashActivity$6$2 */
        class C24522 implements OnClickListener {
            C24522() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                dialog.dismiss();
                SplashActivity.access$500(SplashActivity.this);
            }
        }

        C24506() {
        }

        public void onResponse(MWForceUpdateResponse response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception != null || response == null || response.versionInfo == null) {
                SplashActivity.access$400(SplashActivity.this);
                return;
            }
            String minimumVersion = response.versionInfo.minVersion;
            String currentVersion = response.versionInfo.currentVersion;
            String versionNow = "0.0.0";
            Matcher m = Pattern.compile("^.*?([0-9]+(\\.[0-9]+)*).*$").matcher(Ensighten.evaluateEvent(null, "com.mcdonalds.app.startup.SplashActivity", "access$700", new Object[]{SplashActivity.this}));
            if (m.matches()) {
                versionNow = m.group(1);
            }
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.startup.SplashActivity", "access$800", new Object[]{SplashActivity.this, versionNow, currentVersion})) {
                MCDLog.info("app version is current");
                SplashActivity.access$400(SplashActivity.this);
            } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.startup.SplashActivity", "access$800", new Object[]{SplashActivity.this, versionNow, minimumVersion})) {
                MCDAlertDialogBuilder.withContext(SplashActivity.this).setMessage(SplashActivity.this.getString(C2658R.string.force_upgrade_recommended)).setPositiveButton((int) C2658R.string.yes, new C24522()).setNegativeButton((int) C2658R.string.f6082no, new C24511()).create().show();
                DataLayerManager.getInstance().recordError("Recommend upgrade");
            } else {
                SplashActivity.access$302(SplashActivity.this, true);
                SplashActivity.access$600(SplashActivity.this);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.startup.SplashActivity$7 */
    class C24537 implements OnClickListener {
        C24537() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
            SplashActivity.access$500(SplashActivity.this);
        }
    }

    /* renamed from: com.mcdonalds.app.startup.SplashActivity$8 */
    class C24548 implements AsyncListener<Offer> {
        C24548() {
        }

        public void onResponse(Offer offer, AsyncToken asyncToken, AsyncException e) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{offer, asyncToken, e});
            if (offer != null) {
                Bundle extras = new Bundle();
                extras.putParcelable(PushConstants.BUNDLE_OFFER_KEY, offer);
                SplashActivity.access$900(SplashActivity.this, extras);
            } else {
                SplashActivity.access$1000(SplashActivity.this);
            }
            SplashActivity.this.finish();
        }
    }

    static /* synthetic */ void access$000(SplashActivity x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.startup.SplashActivity", "access$000", new Object[]{x0});
        x0.dropSplashScreen();
    }

    static /* synthetic */ void access$1000(SplashActivity x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.startup.SplashActivity", "access$1000", new Object[]{x0});
        x0.startMainActivity();
    }

    static /* synthetic */ void access$1100(SplashActivity x0, AuthenticationParameters x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.startup.SplashActivity", "access$1100", new Object[]{x0, x1});
        x0.continueLogin(x1);
    }

    static /* synthetic */ void access$200(SplashActivity x0, String x1, AuthenticationParameters x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.startup.SplashActivity", "access$200", new Object[]{x0, x1, x2});
        x0.requestPermission(x1, x2);
    }

    static /* synthetic */ boolean access$302(SplashActivity x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.startup.SplashActivity", "access$302", new Object[]{x0, new Boolean(x1)});
        x0.mUpgradeRequired = x1;
        return x1;
    }

    static /* synthetic */ void access$400(SplashActivity x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.startup.SplashActivity", "access$400", new Object[]{x0});
        x0.proceedToNextScreen();
    }

    static /* synthetic */ void access$500(SplashActivity x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.startup.SplashActivity", "access$500", new Object[]{x0});
        x0.handleVersionUpdate();
    }

    static /* synthetic */ void access$600(SplashActivity x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.startup.SplashActivity", "access$600", new Object[]{x0});
        x0.showUpgradeRequiredDialog();
    }

    static /* synthetic */ void access$900(SplashActivity x0, Bundle x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.startup.SplashActivity", "access$900", new Object[]{x0, x1});
        x0.startMainActivity(x1);
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        EnsightenWrapper.init(this);
        super.onCreate(savedInstanceState);
        if (VERSION.SDK_INT < 21) {
            McDonalds.initializeService(new Intent(McDonaldsApplication.getInstance(), SplashActivity.class));
        }
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mCustomerModule.getSyncAccount();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new SplashFragment()));
        transaction.commit();
        this.mServiceConnection = RequestManager.register(this);
        this.permissionRequested = false;
        NewRelicWrapper.startNewRelic(getApplication());
        handlePushIntent(getIntent());
        HashMap<String, Object> jiceMap = new HashMap();
        jiceMap.put(JiceArgs.INIT, null);
        Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).setConversionMaster(new InitializationAction()).build());
        if (Configuration.getSharedInstance().getBooleanForKey(CHECK_FOR_ROOTED)) {
            checkForRooted();
        }
    }

    private void handlePushIntent(Intent intent) {
        Ensighten.evaluateEvent(this, "handlePushIntent", new Object[]{intent});
        this.mPushIntent = intent;
        if (this.mPushIntent != null) {
            Bundle extras = this.mPushIntent.getExtras();
            if (extras != null && extras.getBoolean(PushConstants.NOTIFICATION_CLICKED, false)) {
                int messageId = extras.getInt(PushConstants.MESSAGE_ID, -1);
                String deliveryId = extras.getString(PushConstants.DELIVERY_ID, "");
                if (messageId == -1) {
                    messageId = extras.getInt(PushConstants.VMOB_MESSAGE_ID, -1);
                }
                if (messageId == -1) {
                    String messageIdString = (String) extras.get(PushConstants.MESSAGE_ID);
                    if (!(messageIdString == null || messageIdString.isEmpty())) {
                        messageId = Integer.parseInt(messageIdString);
                    }
                }
                if (messageId != -1) {
                    ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).trackNotification(messageId, deliveryId, 2);
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        int prefSavedSocialID;
        boolean autoLogin;
        boolean useSocial = false;
        super.onStart();
        if (didChangeDeviceLanguage()) {
        }
        LocalDataManager localDataManager = LocalDataManager.getSharedInstance();
        String prefSavedLogin = localDataManager.getPrefSavedLogin();
        String prefSavedLoginPass = localDataManager.getPrefSavedLoginPass();
        if ("com.mcdonalds.gma.hongkong.account".toLowerCase().contains("stg")) {
            prefSavedSocialID = -1;
        } else {
            prefSavedSocialID = localDataManager.getPrefSavedSocialNetworkId();
        }
        if (TextUtils.isEmpty(prefSavedLogin) || ((TextUtils.isEmpty(prefSavedLoginPass) && prefSavedSocialID == -1) || (!McDonaldsApplication.getInstance().isColdStart() && this.mCustomerModule.isLoggedIn()))) {
            autoLogin = false;
        } else {
            autoLogin = true;
        }
        if (prefSavedSocialID != -1) {
            useSocial = true;
        }
        boolean rememberLogin = LanguageUtil.getPrefRememberLogin();
        AuthenticationParameters parameters;
        if (!autoLogin || rememberLogin) {
            performLogin(null);
        } else if (!useSocial) {
            parameters = new AuthenticationParameters();
            parameters.setUserName(prefSavedLogin);
            parameters.setPassword(prefSavedLoginPass);
            performLogin(parameters);
        } else if (prefSavedSocialID != 3) {
            this.mSocialNetwork = new SocialNetwork(prefSavedSocialID, prefSavedSocialID);
        } else {
            parameters = new AuthenticationParameters();
            parameters.setUserName(prefSavedLogin);
            parameters.setAllowSocialLoginWithoutEmail(true);
            parameters.setUsingSocialLogin(true);
            parameters.setSocialServiceID(prefSavedSocialID);
            parameters.setSocialAuthenticationToken(prefSavedLoginPass);
            parameters.setSocialUserID(prefSavedLogin);
            performLogin(parameters);
        }
        AnalyticsUtils.trackEvent(BusinessArgs.EVENT_APP_OPEN_COUNT, "1");
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        if (this.mUpgradeRequired) {
            showUpgradeRequiredDialog();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        RequestManager.unregister(this, this.mServiceConnection);
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        Ensighten.evaluateEvent(this, "onNewIntent", new Object[]{intent});
        super.onNewIntent(intent);
        handlePushIntent(intent);
    }

    public SocialNetwork getSocialNetwork() {
        Ensighten.evaluateEvent(this, "getSocialNetwork", null);
        return this.mSocialNetwork;
    }

    public void performLogin(AuthenticationParameters parameters) {
        Ensighten.evaluateEvent(this, "performLogin", new Object[]{parameters});
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        boolean intialRequest = LocalDataManager.getSharedInstance().getBoolean(permission, false);
        if (!isPermissionGranted(permission) && !intialRequest) {
            requestPermission(permission, parameters);
            this.permissionRequested = true;
        } else if (Configuration.getSharedInstance().hasKey("configUpdate")) {
            requestConfig(parameters);
        } else {
            continueLogin(parameters);
        }
    }

    private void continueLogin(AuthenticationParameters parameters) {
        Ensighten.evaluateEvent(this, "continueLogin", new Object[]{parameters});
        if (parameters != null) {
            authenticateCustomer(parameters);
        } else {
            startGuestMode();
        }
    }

    private void startGuestMode() {
        Ensighten.evaluateEvent(this, "startGuestMode", null);
        String customerId = "Guest";
        if (this.mCustomerModule.getCurrentProfile() != null) {
            customerId = String.valueOf(this.mCustomerModule.getCurrentProfile().getCustomerId());
        }
        Analytics.trackCustom(2, customerId);
        DataLayerManager.getInstance().setUser(null, "Anonymous", AppUtils.getCurrentMenuType());
        int speed = Configuration.getSharedInstance().getIntForKey("interface.splashscreen.speed");
        if (speed == 0) {
            speed = 1;
        }
        new Handler().postDelayed(new C24391(), (long) (6000 / speed));
    }

    private void authenticateCustomer(final AuthenticationParameters parameters) {
        Ensighten.evaluateEvent(this, "authenticateCustomer", new Object[]{parameters});
        this.mCustomerModule.authenticate(parameters, new AsyncListener<CustomerProfile>() {

            /* renamed from: com.mcdonalds.app.startup.SplashActivity$2$1 */
            class C24411 implements AsyncListener<Void> {
                C24411() {
                }

                public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    SplashActivity.access$000(SplashActivity.this);
                    DataLayerManager.getInstance().setUser(null, "Signed-out", AppUtils.getCurrentMenuType());
                }
            }

            /* renamed from: com.mcdonalds.app.startup.SplashActivity$2$2 */
            class C24422 implements AsyncListener<Boolean> {
                C24422() {
                }

                public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    if (response == null) {
                        MCDLog.info("SplashActivity: Notification autologin FAILURE");
                    } else if (AppUtils.isTrue(response)) {
                        MCDLog.info("SplashActivity: Notification autologin SUCCESS");
                    }
                }
            }

            public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (exception == null) {
                    if (response.isActive()) {
                        if (parameters.isUsingSocialLogin()) {
                            response.setUsingSocialLogin(true);
                            if (parameters.isAllowSocialLoginWithoutEmail()) {
                                response.setUsingSocialLoginWithoutEmail(true);
                            }
                            response.setSocialServiceAuthenticationID(parameters.getSocialServiceID());
                            response.setSocialUserID(parameters.getSocialUserID());
                        }
                        if (response.getNotificationPreferences() != null && AppUtils.isTrue(response.getNotificationPreferences().getAppNotificationPreferencesEnabled())) {
                            NotificationManager.register(new C24422());
                        }
                        Analytics.trackCustom(2, String.valueOf(response.getCustomerId()));
                        NavigationDrawerFragment navFragment = (NavigationDrawerFragment) SplashActivity.this.getSupportFragmentManager().findFragmentById(C2358R.C2357id.navigation_drawer);
                        if (navFragment == null) {
                            if (response.shouldUpdateTermsAndCondition() || response.shouldUpdatePrivacyPolicy()) {
                                SplashActivity.this.startActivity(LitePolicyUpdatesActivity.class);
                            } else {
                                SplashActivity.access$000(SplashActivity.this);
                            }
                        } else if (response.shouldUpdateTermsAndCondition() || response.shouldUpdatePrivacyPolicy()) {
                            SplashActivity.this.startActivity(LitePolicyUpdatesActivity.class);
                        } else {
                            navFragment.setLoggedInDrawerState(true);
                        }
                        DataLayerManager.getInstance().setUser(response, "Signed-in", AppUtils.getCurrentMenuType());
                        return;
                    }
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.startup.SplashActivity", "access$100", new Object[]{SplashActivity.this}).logout(new C24411());
                } else if (exception instanceof MWException) {
                    if (exception.getErrorCode() == SplashActivity.SOCIAL_LOGIN_LOGIN_FAIL) {
                        LocalDataManager.getSharedInstance().setPrefSavedLoginPass("");
                    }
                    SplashActivity.access$000(SplashActivity.this);
                } else {
                    DataLayerManager.getInstance().setUser(null, "Anonymous", AppUtils.getCurrentMenuType());
                    SplashActivity.access$000(SplashActivity.this);
                }
            }
        });
    }

    private void requestPermission(String permission, final AuthenticationParameters parameters) {
        Ensighten.evaluateEvent(this, "requestPermission", new Object[]{permission, parameters});
        requestPermission(permission, 1, C2658R.string.permission_explanation_gps, new PermissionListener() {
            public void onRequestPermissionsResult(int requestCode, final String permission, int grantResult) {
                Ensighten.evaluateEvent(this, "onRequestPermissionsResult", new Object[]{new Integer(requestCode), permission, new Integer(grantResult)});
                if (grantResult == -1) {
                    MCDAlertDialogBuilder.withContext(SplashActivity.this).setTitle((int) C2658R.string.gps_permission_title).setMessage((int) C2658R.string.gps_permission_text).setPositiveButton((int) C2658R.string.yes, new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                            SplashActivity.access$200(SplashActivity.this, permission, parameters);
                            dialog.dismiss();
                        }
                    }).setNegativeButton((int) C2658R.string.f6082no, new OnClickListener() {
                        public void onClick(DialogInterface dialog, int i) {
                            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(i)});
                            dialog.dismiss();
                            LocalDataManager.getSharedInstance().set(permission, true);
                            SplashActivity.this.performLogin(parameters);
                        }
                    }).create().show();
                    return;
                }
                LocalDataManager.getSharedInstance().set(permission, true);
                SplashActivity.this.performLogin(parameters);
            }
        });
    }

    private void dropSplashScreen() {
        Ensighten.evaluateEvent(this, "dropSplashScreen", null);
        if (this.mCustomerModule != null && this.mCustomerModule.isLoggedIn()) {
            ServiceUtils.getSharedInstance().fetchFavoriteLocations();
        }
        this.mVersionName = "4.8.10";
        if (Configuration.getSharedInstance().hasKey("forceUpdate.baseUrl")) {
            performForceUpdateCheck();
        } else {
            checkIfForceUpdate();
        }
    }

    private void performForceUpdateCheck() {
        Ensighten.evaluateEvent(this, "performForceUpdateCheck", null);
        SDKUtils.checkForUpdates(this.mServiceConnection, "4.8.10", new C24464());
    }

    @Deprecated
    private void checkIfForceUpdate() {
        Ensighten.evaluateEvent(this, "checkIfForceUpdate", null);
        final Object versionsURL = Configuration.getSharedInstance().getValueForKey("forceUpdate.baseUrl");
        final Object versionsAPIKey = Configuration.getSharedInstance().getValueForKey("forceUpdate.headerArgs.apiKey");
        if ((versionsURL instanceof String) && (versionsAPIKey instanceof String)) {
            this.mServiceConnection.processRequest(new RequestProvider() {
                public MethodType getMethodType() {
                    Ensighten.evaluateEvent(this, "getMethodType", null);
                    return MethodType.GET;
                }

                public RequestType getRequestType() {
                    Ensighten.evaluateEvent(this, "getRequestType", null);
                    return RequestType.JSON;
                }

                public String getURLString() {
                    Ensighten.evaluateEvent(this, "getURLString", null);
                    return (String) versionsURL;
                }

                public Map<String, String> getHeaders() {
                    Ensighten.evaluateEvent(this, "getHeaders", null);
                    Map<String, String> mHeaderMap = new HashMap();
                    mHeaderMap.put(MiddlewareConnector.CONFIG_HEADER_API_KEY, (String) versionsAPIKey);
                    mHeaderMap.put("MarketId", "US");
                    mHeaderMap.put("Nonce", "2014-03-31T15:34:39");
                    mHeaderMap.put("content-type", "text/json");
                    return mHeaderMap;
                }

                public String getBody() {
                    Ensighten.evaluateEvent(this, "getBody", null);
                    return null;
                }

                public void setBody(Object body) {
                    Ensighten.evaluateEvent(this, "setBody", new Object[]{body});
                }

                public Class getResponseClass() {
                    Ensighten.evaluateEvent(this, "getResponseClass", null);
                    return MWForceUpdateResponse.class;
                }

                public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
                    Ensighten.evaluateEvent(this, "getCustomTypeAdapters", null);
                    return null;
                }

                public String toString() {
                    Ensighten.evaluateEvent(this, "toString", null);
                    return null;
                }
            }, new C24506());
            return;
        }
        proceedToNextScreen();
    }

    @Deprecated
    private boolean compareVersions(String versionNow, String referenceVersion) {
        Ensighten.evaluateEvent(this, "compareVersions", new Object[]{versionNow, referenceVersion});
        String[] versionNowComponents = versionNow.split("\\.");
        String[] referenceVersionComponents = referenceVersion.split("\\.");
        int length = Math.max(versionNowComponents.length, referenceVersionComponents.length);
        int i = 0;
        while (i < length) {
            int refDigit;
            int nowDigit = i < versionNowComponents.length ? Integer.valueOf(versionNowComponents[i]).intValue() : 0;
            if (i < referenceVersionComponents.length) {
                refDigit = Integer.valueOf(referenceVersionComponents[i]).intValue();
            } else {
                refDigit = 0;
            }
            if (nowDigit < refDigit) {
                return false;
            }
            if (nowDigit > refDigit) {
                return true;
            }
            i++;
        }
        return true;
    }

    private void showUpgradeRequiredDialog() {
        Ensighten.evaluateEvent(this, "showUpgradeRequiredDialog", null);
        MCDAlertDialogBuilder.withContext(this).setMessage(getString(C2658R.string.force_upgrade_required)).setPositiveButton((int) C2658R.string.f6083ok, new C24537()).create().show();
        DataLayerManager.getInstance().recordError("Force upgrade");
    }

    private void proceedToNextScreen() {
        Ensighten.evaluateEvent(this, "proceedToNextScreen", null);
        boolean delayFinish = false;
        if (TutorialFragment.shouldShowTutorial(Configuration.getSharedInstance(), LocalDataManager.getSharedInstance())) {
            Analytics.track(AnalyticType.Event, new ArgBuilder().setBusiness(BusinessArgs.getAppOpen()).build());
            startActivity(new Intent(this, TutorialActivity.class));
        } else if (this.mPushIntent != null && this.mPushIntent.getExtras() != null && this.mPushIntent.getExtras().containsKey(PushConstants.EXTENDED_DATA)) {
            dispatchPushNotification();
            delayFinish = true;
        } else if (this.mCustomerModule.getCurrentProfile() == null || this.mCustomerModule.getCurrentProfile().isMobileVerified() || Configuration.getSharedInstance().getBooleanForKey("interface.hideMobileVerificatinScreen")) {
            startMainActivity();
        } else {
            startActivity(ProfileUpdateActivity.class, "mobile_verify");
        }
        preCacheCurrentStoreCatalogIfNeeded();
        if (!delayFinish) {
            finish();
        }
    }

    private void startMainActivity() {
        Ensighten.evaluateEvent(this, "startMainActivity", null);
        startMainActivity(null);
    }

    private void startMainActivity(Bundle extras) {
        Ensighten.evaluateEvent(this, "startMainActivity", new Object[]{extras});
        Intent intent = new Intent(this, MainActivity.class);
        if (extras != null) {
            intent.putExtras(extras);
        }
        startActivity(intent);
    }

    private void dispatchPushNotification() {
        Ensighten.evaluateEvent(this, "dispatchPushNotification", null);
        if (this.mPushIntent.getExtras().getString(PushConstants.EXTENDED_DATA) != null) {
            try {
                ExtendedData extendedData;
                DeepLinkingType type;
                Gson gson = new Gson();
                String string = this.mPushIntent.getExtras().getString(PushConstants.EXTENDED_DATA);
                Class cls = ExtendedData.class;
                if (gson instanceof Gson) {
                    extendedData = GsonInstrumentation.fromJson(gson, string, cls);
                } else {
                    extendedData = gson.fromJson(string, cls);
                }
                extendedData = extendedData;
                if (extendedData.getDeepLinkingID() == null || extendedData.getDeepLinkingID().intValue() <= 0 || extendedData.getDeepLinkingID().intValue() >= DeepLinkingType.values().length) {
                    MCDLog.error(getClass().getSimpleName(), "Invalid Deep Linking ID: " + extendedData.getDeepLinkingID());
                    type = DeepLinkingType.Dashboard;
                } else {
                    type = DeepLinkingType.values()[extendedData.getDeepLinkingID().intValue()];
                }
                switch (type) {
                    case Dashboard:
                        startMainActivity();
                        finish();
                        return;
                    case OfferDetails:
                        getOfferDetails(extendedData.getOfferID(), new C24548());
                        return;
                    default:
                        return;
                }
            } catch (JsonSyntaxException e) {
                MCDLog.error(getClass().getSimpleName(), "ExtendedData not valid json");
                startMainActivity();
                return;
            }
        }
        MCDLog.error(getClass().getSimpleName(), "Extended Data missing from Push Notification");
        startMainActivity();
    }

    private void getOfferDetails(final Integer offerId, final AsyncListener<Offer> listener) {
        Ensighten.evaluateEvent(this, "getOfferDetails", new Object[]{offerId, listener});
        if (offerId == null) {
            listener.onResponse(null, null, null);
            return;
        }
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        OffersModule offersModule = (OffersModule) ModuleManager.getModule(OffersModule.NAME);
        if (customerModule.isLoggedIn()) {
            Location lastLocation = null;
            try {
                lastLocation = AppUtils.getUserLocation();
            } catch (IllegalStateException e) {
            }
            Double latitude = null;
            Double longitude = null;
            if (lastLocation != null) {
                latitude = Double.valueOf(lastLocation.getLatitude());
                longitude = Double.valueOf(lastLocation.getLongitude());
            }
            CustomerProfile profile = customerModule.getCurrentProfile();
            Store store = OrderManager.getInstance().getCurrentStore();
            if (store == null) {
                listener.onResponse(null, null, null);
                return;
            } else {
                ServiceUtils.getSharedInstance().retrieveOffers(profile.getUserName(), String.valueOf(store.getStoreId()), latitude, longitude, new AsyncListener<List<Offer>>() {
                    public void onResponse(List<Offer> offers, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{offers, token, exception});
                        if (exception != null) {
                            MCDLog.error(getClass().getSimpleName(), "Error retrieving Offers");
                            listener.onResponse(null, null, null);
                        } else if (offers != null) {
                            for (Offer offer : offers) {
                                if (offer.getOfferId().equals(offerId)) {
                                    listener.onResponse(offer, null, null);
                                    return;
                                }
                            }
                            listener.onResponse(null, null, null);
                        } else {
                            listener.onResponse(null, null, null);
                        }
                    }
                });
                return;
            }
        }
        listener.onResponse(null, null, null);
    }

    private void preCacheCurrentStoreCatalogIfNeeded() {
        Ensighten.evaluateEvent(this, "preCacheCurrentStoreCatalogIfNeeded", null);
        if (ModuleManager.isModuleEnabled("ordering").booleanValue() && ModuleManager.isModuleEnabled(CustomerModule.NAME).booleanValue()) {
            ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).requestSync();
        }
    }

    private void handleVersionUpdate() {
        Ensighten.evaluateEvent(this, "handleVersionUpdate", null);
        String directDlLink = Configuration.getSharedInstance().getStringForKey(DIRECT_BIN_DL);
        if (directDlLink != null) {
            directBinaryDownload(directDlLink);
        } else {
            sendToPlayStore();
        }
    }

    private void directBinaryDownload(final String directDlLink) {
        Ensighten.evaluateEvent(this, "directBinaryDownload", new Object[]{directDlLink});
        requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", 2, C2658R.string.permission_explanation_direct_bin_dl, new PermissionListener() {
            public void onRequestPermissionsResult(int requestCode, String permission, int grantResult) {
                Ensighten.evaluateEvent(this, "onRequestPermissionsResult", new Object[]{new Integer(requestCode), permission, new Integer(grantResult)});
                if (grantResult == -1) {
                    SplashActivity.this.finish();
                    return;
                }
                try {
                    DownloadApkFileFromUrl downloadApkFileFromUrl = new DownloadApkFileFromUrl(SplashActivity.this);
                    URL[] urlArr = new URL[]{new URL(directDlLink)};
                    if (downloadApkFileFromUrl instanceof AsyncTask) {
                        AsyncTaskInstrumentation.execute(downloadApkFileFromUrl, urlArr);
                    } else {
                        downloadApkFileFromUrl.execute(urlArr);
                    }
                } catch (MalformedURLException e) {
                }
            }
        });
    }

    private void sendToPlayStore() {
        Ensighten.evaluateEvent(this, "sendToPlayStore", null);
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.mcdonalds.gma.hongkong")));
    }

    private void requestConfig(final AuthenticationParameters parameters) {
        Ensighten.evaluateEvent(this, "requestConfig", new Object[]{parameters});
        Configuration.getSharedInstance().networkUpdate(this.mServiceConnection, new NetworkUpdateListener() {
            public void onComplete() {
                Ensighten.evaluateEvent(this, "onComplete", null);
                SplashActivity.access$1100(SplashActivity.this, parameters);
            }
        });
    }

    private boolean didChangeDeviceLanguage() {
        Ensighten.evaluateEvent(this, "didChangeDeviceLanguage", null);
        LanguageUtil.changeAppLanguage(getResources(), LanguageUtil.getAppLanguage());
        if (!LanguageUtil.isClear()) {
            return false;
        }
        LanguageUtil.setClear(false);
        return true;
    }

    private void checkForRooted() {
        int i = 0;
        Ensighten.evaluateEvent(this, "checkForRooted", null);
        if (Build.TAGS == null || !Build.TAGS.contains("test-keys")) {
            String[] paths = new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"};
            int length = paths.length;
            while (i < length) {
                if (new File(paths[i]).exists()) {
                    Toast.makeText(this, C2658R.string.root_detect_toast_msg, 1).show();
                    return;
                }
                i++;
            }
            if (!new File("/system/etc/security/otacerts.zip").exists()) {
                Toast.makeText(this, C2658R.string.root_detect_toast_msg, 1).show();
                return;
            }
            return;
        }
        Toast.makeText(this, C2658R.string.root_detect_toast_msg, 1).show();
    }
}
