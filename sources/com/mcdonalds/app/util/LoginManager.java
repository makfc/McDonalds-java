package com.mcdonalds.app.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.ensighten.Ensighten;
import com.google.gson.Gson;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.customer.SignInActivity;
import com.mcdonalds.app.customer.SignInExistingAccountFragment;
import com.mcdonalds.app.customer.SignUpActivity;
import com.mcdonalds.app.customer.push.OffersRequestActivity;
import com.mcdonalds.app.customer.push.PushNotificationRequestActivity;
import com.mcdonalds.app.gmalite.customer.LiteEmailVerificationActivity;
import com.mcdonalds.app.gmalite.customer.LiteSmsVerificationActivity;
import com.mcdonalds.app.model.FormField;
import com.mcdonalds.app.model.Register;
import com.mcdonalds.app.model.RegisterToggle;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.startup.SplashActivity;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.middleware.MWException;
import com.mcdonalds.sdk.connectors.middleware.model.DCSProfile;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.customer.CustomerProfile.AccountVerificationType;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.modules.models.StoreFavoriteInfo;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.BusinessArgs;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

public class LoginManager extends Observable {
    private static LoginManager sInstance;
    private CustomerModule mCustomerModule = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME));
    private CustomerProfile mCustomerProfile;
    private final AsyncListener<CustomerProfile> mCustomerProfileListener = new C38625();
    private HashMap<String, Boolean> mDefaultStates;
    private HashMap<String, Boolean> mMandatoryFields;
    private List<String> mMandatoryToggles;
    private HashMap<String, String[]> mOptionFields;
    private Register mRegisterSettings;
    private RegisterToggle mRegisterToggleSettings;
    private final AsyncListener<Void> mResendListener = new C38636();
    private List<String> mVisibleFields;

    /* renamed from: com.mcdonalds.app.util.LoginManager$5 */
    class C38625 implements AsyncListener<CustomerProfile> {
        C38625() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception != null) {
                AsyncException.report(exception);
            }
            if (response != null) {
                LoginManager.access$102(LoginManager.this, response);
                LoginManager.access$1200(LoginManager.this);
                LoginManager.this.notifyObservers(Integer.valueOf(3002));
            }
        }
    }

    /* renamed from: com.mcdonalds.app.util.LoginManager$6 */
    class C38636 implements AsyncListener<Void> {
        C38636() {
        }

        public void onResponse(Void response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            LoginManager.access$1300(LoginManager.this);
            LoginManager.this.notifyObservers(Integer.valueOf(3003));
        }
    }

    static /* synthetic */ void access$000(LoginManager x0, URLNavigationActivity x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$000", new Object[]{x0, x1});
        x0.showVerificationScreen(x1);
    }

    static /* synthetic */ void access$1000(LoginManager x0, URLNavigationActivity x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$1000", new Object[]{x0, x1});
        x0.startLiteEmailVerificationActivity(x1);
    }

    static /* synthetic */ CustomerProfile access$102(LoginManager x0, CustomerProfile x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$102", new Object[]{x0, x1});
        x0.mCustomerProfile = x1;
        return x1;
    }

    static /* synthetic */ void access$1100(LoginManager x0, AuthenticationParameters x1, URLNavigationActivity x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$1100", new Object[]{x0, x1, x2});
        x0.onAuthentication(x1, x2);
    }

    static /* synthetic */ void access$1200(LoginManager x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$1200", new Object[]{x0});
        x0.setChanged();
    }

    static /* synthetic */ void access$1300(LoginManager x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$1300", new Object[]{x0});
        x0.setChanged();
    }

    static /* synthetic */ void access$1500(LoginManager x0, URLNavigationActivity x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$1500", new Object[]{x0, x1});
        x0.startMainActivity(x1);
    }

    static /* synthetic */ void access$300(LoginManager x0, Context x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$300", new Object[]{x0, x1});
        x0.startSignInActivity(x1);
    }

    static /* synthetic */ void access$400(LoginManager x0, CustomerProfile x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$400", new Object[]{x0, x1});
        x0.trackRegistrationSuccess(x1);
    }

    static /* synthetic */ void access$700(LoginManager x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$700", new Object[]{x0});
        x0.setChanged();
    }

    static /* synthetic */ void access$800(LoginManager x0, URLNavigationActivity x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$800", new Object[]{x0, x1});
        x0.startLiteSmsActivity(x1);
    }

    static /* synthetic */ void access$900(LoginManager x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$900", new Object[]{x0});
        x0.setChanged();
    }

    public static LoginManager getInstance() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "getInstance", null);
        if (sInstance == null) {
            sInstance = new LoginManager();
        }
        return sInstance;
    }

    public static void destroy() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "destroy", null);
        sInstance = null;
    }

    private LoginManager() {
    }

    public void loadRegisterConfig() {
        Ensighten.evaluateEvent(this, "loadRegisterConfig", null);
        Object config = (Map) Configuration.getSharedInstance().getValueForKey("interface.register");
        Gson gson = new Gson();
        String toJson = !(gson instanceof Gson) ? gson.toJson(config) : GsonInstrumentation.toJson(gson, config);
        Class cls = Register.class;
        this.mRegisterSettings = (Register) (!(gson instanceof Gson) ? gson.fromJson(toJson, cls) : GsonInstrumentation.fromJson(gson, toJson, cls));
        String toJson2 = !(gson instanceof Gson) ? gson.toJson(config) : GsonInstrumentation.toJson(gson, config);
        Class cls2 = RegisterToggle.class;
        this.mRegisterToggleSettings = (RegisterToggle) (!(gson instanceof Gson) ? gson.fromJson(toJson2, cls2) : GsonInstrumentation.fromJson(gson, toJson2, cls2));
        this.mVisibleFields = new ArrayList();
        this.mMandatoryFields = new HashMap();
        this.mDefaultStates = new HashMap();
        this.mOptionFields = new HashMap();
        this.mMandatoryToggles = new ArrayList();
        for (FormField field : this.mRegisterSettings.getFields()) {
            if (field.doShow()) {
                this.mVisibleFields.add(field.getName());
            }
            this.mMandatoryFields.put(field.getName(), Boolean.valueOf(field.isRequired()));
            this.mOptionFields.put(field.getName(), field.getOptions());
        }
        List<RegisterToggle> togglesList = this.mRegisterSettings.getToggles();
        if (togglesList != null) {
            for (RegisterToggle toggle : togglesList) {
                this.mVisibleFields.add(toggle.getName());
                this.mDefaultStates.put(toggle.getName(), Boolean.valueOf(toggle.getDefaultState()));
                if (toggle.isRequired()) {
                    this.mMandatoryToggles.add(toggle.getName());
                }
            }
        }
    }

    public boolean showField(String field) {
        Ensighten.evaluateEvent(this, "showField", new Object[]{field});
        return this.mVisibleFields.contains(field);
    }

    public boolean getDefaultState(String field) {
        Ensighten.evaluateEvent(this, "getDefaultState", new Object[]{field});
        if (this.mDefaultStates.containsKey(field)) {
            return ((Boolean) this.mDefaultStates.get(field)).booleanValue();
        }
        return false;
    }

    public boolean fieldIsMandatory(String field) {
        Ensighten.evaluateEvent(this, "fieldIsMandatory", new Object[]{field});
        if (this.mMandatoryFields.containsKey(field)) {
            return ((Boolean) this.mMandatoryFields.get(field)).booleanValue();
        }
        return false;
    }

    public List<String> getMandatoryToggles() {
        Ensighten.evaluateEvent(this, "getMandatoryToggles", null);
        return this.mMandatoryToggles;
    }

    public Register getRegisterSettings() {
        Ensighten.evaluateEvent(this, "getRegisterSettings", null);
        return this.mRegisterSettings;
    }

    public void addLoginMethod(final URLNavigationActivity activity) {
        Ensighten.evaluateEvent(this, "addLoginMethod", new Object[]{activity});
        this.mCustomerModule.addLoginMethod(this.mCustomerProfile, new AsyncListener<MWJSONResponse>() {
            public void onResponse(MWJSONResponse response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                UIUtils.stopActivityIndicator();
                if (activity != null) {
                    Intent intent = new Intent(activity, MainActivity.class);
                    intent.putExtra(URLNavigationActivity.ARG_FRAGMENT, "dashboard");
                    activity.startActivity(intent);
                }
            }
        });
    }

    public void forceRegister(final URLNavigationActivity activity) {
        Ensighten.evaluateEvent(this, "forceRegister", new Object[]{activity});
        this.mCustomerModule.registerExtSocialNetworkForced(this.mCustomerProfile, new AsyncListener<MWJSONResponse>() {
            public void onResponse(MWJSONResponse response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                UIUtils.stopActivityIndicator();
                if (exception != null) {
                    AsyncException.report(exception);
                } else if (activity != null) {
                    LoginManager.access$000(LoginManager.this, activity);
                }
            }
        });
    }

    public void register(final URLNavigationActivity activity) {
        Ensighten.evaluateEvent(this, JiceArgs.EVENT_REGISTER, new Object[]{activity});
        this.mCustomerProfile.setSocialProvider(getSocialProvider(this.mCustomerProfile));
        this.mCustomerModule.register(this.mCustomerProfile, new AsyncListener<CustomerProfile>() {

            /* renamed from: com.mcdonalds.app.util.LoginManager$3$1 */
            class C38561 implements OnClickListener {
                C38561() {
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
                    LoginManager.access$300(LoginManager.this, activity);
                    activity.finish();
                }
            }

            /* renamed from: com.mcdonalds.app.util.LoginManager$3$2 */
            class C38572 implements OnClickListener {
                C38572() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    dialog.dismiss();
                }
            }

            /* renamed from: com.mcdonalds.app.util.LoginManager$3$3 */
            class C38583 implements OnClickListener {
                C38583() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                }
            }

            public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                boolean requiresActivation = true;
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (exception == null) {
                    if (!(activity == null || activity.isFinishing())) {
                        if ((Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$100", new Object[]{LoginManager.this}).isUsingSocialLogin() || !Configuration.getSharedInstance().getBooleanForKey(DCSProfile.KEY_REQUIRES_ACTIVATION)) && !(Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$100", new Object[]{LoginManager.this}).isUsingSocialLogin() && Configuration.getSharedInstance().getBooleanForKey("requireActivationSocial"))) {
                            requiresActivation = false;
                        }
                        if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$200", new Object[]{LoginManager.this}).chooseSignInMethodEnabled()) {
                            UIUtils.stopActivityIndicator();
                            LoginManager.access$000(LoginManager.this, activity);
                        } else if (requiresActivation) {
                            UIUtils.stopActivityIndicator();
                            MCDAlertDialogBuilder.withContext(activity).setCancelable(false).setNeutralButton((int) C2658R.string.f6083ok, new C38561()).setMessage((int) C2658R.string.dialog_registration_email_sent).create().show();
                        } else {
                            LoginManager.this.login(LoginManager.this.getAuthenticationParameters(), activity);
                        }
                    }
                    LoginManager.access$400(LoginManager.this, response);
                    return;
                }
                UIUtils.stopActivityIndicator();
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$500", new Object[]{LoginManager.this, exception})) {
                    if (activity != null && !activity.isFinishing()) {
                        MCDAlertDialogBuilder.withContext(activity).setMessage(activity.getString(C2658R.string.text_email_take_over)).setPositiveButton((int) C2658R.string.f6083ok, new C38572()).create().show();
                        DataLayerManager.getInstance().recordError("Email address invalid");
                    }
                } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$600", new Object[]{LoginManager.this, exception})) {
                    Log.d("LoginManager", "CustomerSocialLoginPhoneIsAlreadyVerified");
                    if (activity instanceof SignUpActivity) {
                        ((SignUpActivity) activity).changeFragment(new SignInExistingAccountFragment());
                    }
                } else if (exception.getErrorCode() == -1010) {
                    MCDAlertDialogBuilder.withContext(activity).setMessage(activity.getString(C2658R.string.invalid_email_warning)).setTitle(activity.getString(C2658R.string.title_registration_failed)).setPositiveButton((int) C2658R.string.f6083ok, new C38583()).create().show();
                    DataLayerManager.getInstance().recordError("Email address invalid");
                } else {
                    AsyncException.report(exception);
                }
            }
        });
    }

    private void trackRegistrationSuccess(CustomerProfile response) {
        Ensighten.evaluateEvent(this, "trackRegistrationSuccess", new Object[]{response});
        if (response != null) {
            String signInType;
            String socialSite = response.getSocialProvider();
            if (TextUtils.isEmpty(socialSite)) {
                signInType = BusinessArgs.VALUE_MCD;
                socialSite = "";
            } else {
                signInType = "social";
            }
            String kochavaMarketId = "";
            if (Configuration.getSharedInstance().hasKey("analytics.EnhancedKochava.marketId")) {
                kochavaMarketId = Configuration.getSharedInstance().getStringForKey("analytics.EnhancedKochava.marketId");
            }
            AnalyticsUtils.trackEvent(new ArgBuilder().setLabel(BusinessArgs.EVENT_REGISTRATION_SUCCESS).setMapping(BusinessArgs.KEY_SIGNIN_TYPE, signInType).setMapping(BusinessArgs.KEY_SOCIAL_SITE, socialSite).setMapping(BusinessArgs.KEY_ECP_ID, kochavaMarketId).setMapping(BusinessArgs.KEY_CUSTOMER_ID, response.getCustomerId() == 0 ? "" : Long.toString(response.getCustomerId())).build());
        }
    }

    private void startLiteSmsActivity(URLNavigationActivity activity) {
        Ensighten.evaluateEvent(this, "startLiteSmsActivity", new Object[]{activity});
        Intent intent = new Intent(activity, LiteSmsVerificationActivity.class);
        if (activity instanceof MainActivity) {
            activity.showFragment("gmalite_sms_verification");
            return;
        }
        activity.startActivity(intent);
        activity.finish();
    }

    private void startLiteEmailVerificationActivity(URLNavigationActivity activity) {
        Ensighten.evaluateEvent(this, "startLiteEmailVerificationActivity", new Object[]{activity});
        if (activity instanceof MainActivity) {
            activity.showFragment("liteverifyemail");
            return;
        }
        activity.startActivity(LiteEmailVerificationActivity.class);
        activity.finish();
    }

    private boolean isEmailRegistered(AsyncException exception) {
        Ensighten.evaluateEvent(this, "isEmailRegistered", new Object[]{exception});
        if (exception instanceof MWException) {
            MWException mwException = (MWException) exception;
            if (mwException.getErrorCode() == -1032 || mwException.getErrorCode() == -2201) {
                return true;
            }
        }
        return false;
    }

    private boolean isCustomerSocialLoginPhoneIsAlreadyVerified(AsyncException exception) {
        Ensighten.evaluateEvent(this, "isCustomerSocialLoginPhoneIsAlreadyVerified", new Object[]{exception});
        if ((exception instanceof MWException) && ((MWException) exception).getErrorCode() == -2120) {
            return true;
        }
        return false;
    }

    public AuthenticationParameters getAuthenticationParameters() {
        Ensighten.evaluateEvent(this, "getAuthenticationParameters", null);
        AuthenticationParameters parameters = new AuthenticationParameters();
        if (this.mCustomerProfile == null) {
            this.mCustomerProfile = this.mCustomerModule.getCurrentProfile();
        }
        parameters.setUserName(this.mCustomerProfile.getUserName());
        parameters.setEmailAddress(this.mCustomerProfile.getEmailAddress());
        parameters.setPassword(this.mCustomerProfile.getPassword());
        if (this.mCustomerProfile.isUsingSocialLogin()) {
            parameters.setUsingSocialLogin(true);
            parameters.setAllowSocialLoginWithoutEmail(this.mCustomerProfile.isUsingSocialLoginWithoutEmail());
            parameters.setSocialServiceID(this.mCustomerProfile.getSocialServiceAuthenticationID());
            parameters.setSocialAuthenticationToken(this.mCustomerProfile.getSocialAuthenticationToken());
            parameters.setSocialUserID(this.mCustomerProfile.getSocialUserID());
        }
        trackSuccessfulRegister(this.mCustomerProfile.isUsingSocialLogin(), Integer.valueOf(this.mCustomerProfile.getSocialServiceAuthenticationID()), "", this.mCustomerProfile.getCustomerId());
        return parameters;
    }

    private void trackSuccessfulRegister(boolean isSocial, Integer socialType, String ecpId, long customerId) {
        String str;
        Ensighten.evaluateEvent(this, "trackSuccessfulRegister", new Object[]{new Boolean(isSocial), socialType, ecpId, new Long(customerId)});
        ArgBuilder argBuilder = new ArgBuilder();
        if (socialType == null) {
            str = "";
        } else {
            str = socialType.toString();
        }
        Analytics.track(AnalyticType.Event, argBuilder.setBusiness(BusinessArgs.getRegistrationSuccess(isSocial, str, ecpId, String.valueOf(customerId))).build());
    }

    public void login(final AuthenticationParameters parameters, final URLNavigationActivity activity) {
        Ensighten.evaluateEvent(this, JiceArgs.EVENT_DASHBOARD_LOGIN, new Object[]{parameters, activity});
        parameters.setSocialProvider(getSocialProvider(parameters));
        this.mCustomerModule.authenticate(parameters, new AsyncListener<CustomerProfile>() {

            /* renamed from: com.mcdonalds.app.util.LoginManager$4$1 */
            class C38601 implements AsyncListener<List<StoreFavoriteInfo>> {
                C38601() {
                }

                public void onResponse(List<StoreFavoriteInfo> response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                }
            }

            public void onResponse(CustomerProfile profile, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{profile, token, exception});
                UIUtils.stopActivityIndicator();
                if (exception == null && activity != null) {
                    if (!ConfigurationUtils.isGmaLiteFlow()) {
                        ServiceUtils.getSharedInstance().retrieveFavoriteStores(profile, new C38601());
                        LoginManager.access$102(LoginManager.this, profile);
                        LoginManager.access$1100(LoginManager.this, parameters, activity);
                    } else if (profile.getVerificationType() == AccountVerificationType.SMS) {
                        LoginManager.this.setProfile(profile);
                        LoginManager.this.persistProfile();
                        LoginManager.access$700(LoginManager.this);
                        LoginManager.this.notifyObservers(Integer.valueOf(3001));
                        LoginManager.access$800(LoginManager.this, activity);
                    } else if (profile.getVerificationType() == AccountVerificationType.EMAIL) {
                        LoginManager.this.setProfile(profile);
                        LoginManager.this.persistProfile();
                        LoginManager.access$900(LoginManager.this);
                        LoginManager.this.notifyObservers(Integer.valueOf(3001));
                        LoginManager.access$1000(LoginManager.this, activity);
                    } else {
                        LoginManager.this.setProfile(profile);
                        if (activity instanceof MainActivity) {
                            activity.showFragment("dashboard");
                        } else {
                            activity.finish();
                        }
                    }
                    DataLayerManager.getInstance().setUser(profile, "Signed-in", AppUtils.getCurrentMenuType());
                } else if (!(exception instanceof MWException)) {
                    AsyncException.report(exception);
                } else if (exception.getErrorCode() == SplashActivity.SOCIAL_LOGIN_LOGIN_FAIL) {
                    LocalDataManager.getSharedInstance().setPrefSavedLoginPass("");
                }
            }
        });
    }

    @NonNull
    private String getSocialProvider(AuthenticationParameters parameters) {
        Ensighten.evaluateEvent(this, "getSocialProvider", new Object[]{parameters});
        String socialProvider = "";
        if (parameters.getSocialProvider() != null) {
            return parameters.getSocialProvider();
        }
        if (this.mCustomerModule.getSocialNetworkById(parameters.getSocialServiceID()) != null) {
            return this.mCustomerModule.getSocialNetworkById(parameters.getSocialServiceID()).getSocialNetworkName();
        }
        return socialProvider;
    }

    @NonNull
    private String getSocialProvider(CustomerProfile profile) {
        Ensighten.evaluateEvent(this, "getSocialProvider", new Object[]{profile});
        String socialProvider = "";
        if (profile.getSocialProvider() != null) {
            return profile.getSocialProvider();
        }
        if (this.mCustomerModule.getSocialNetworkById(profile.getSocialServiceAuthenticationID()) != null) {
            return this.mCustomerModule.getSocialNetworkById(profile.getSocialServiceAuthenticationID()).getSocialNetworkName();
        }
        return socialProvider;
    }

    private void onAuthentication(AuthenticationParameters parameters, URLNavigationActivity activity) {
        Ensighten.evaluateEvent(this, "onAuthentication", new Object[]{parameters, activity});
        updateProfileWithSocial(parameters);
        if (this.mCustomerProfile.isPasswordChangeRequired()) {
            showSignInChangePasswordFragment(activity);
        } else if (this.mCustomerProfile.isEmailActivated()) {
            finishSignIn(activity);
        } else {
            startSignInActivity(activity);
            activity.finish();
        }
    }

    private void showVerificationScreen(URLNavigationActivity activity) {
        Ensighten.evaluateEvent(this, "showVerificationScreen", new Object[]{activity});
        int method = this.mCustomerProfile.getLoginPreference();
        Bundle bundle = new Bundle();
        bundle.putInt("validation_method", method);
        bundle.putBoolean("social_login_without_email", this.mCustomerProfile.isUsingSocialLoginWithoutEmail());
        startOffersRequestActivity(activity, bundle);
    }

    private void finishSignIn(URLNavigationActivity activity) {
        Ensighten.evaluateEvent(this, "finishSignIn", new Object[]{activity});
        persistProfile();
        checkForOneLastThing(activity);
        setChanged();
        notifyObservers(Integer.valueOf(3001));
    }

    private void updateProfileWithSocial(AuthenticationParameters parameters) {
        Ensighten.evaluateEvent(this, "updateProfileWithSocial", new Object[]{parameters});
        if (parameters.isUsingSocialLogin()) {
            this.mCustomerProfile.setUsingSocialLogin(true);
            this.mCustomerProfile.setSocialServiceAuthenticationID(parameters.getSocialServiceID());
            this.mCustomerProfile.setSocialUserID(parameters.getSocialUserID());
        }
    }

    private void checkForOneLastThing(URLNavigationActivity activity) {
        Ensighten.evaluateEvent(this, "checkForOneLastThing", new Object[]{activity});
        Bundle arguments = new Bundle();
        arguments.putSerializable("ARG_CUSTOMER_PROFILE", this.mCustomerProfile);
        if (this.mCustomerModule.isFirstTimeLoginOnDevice(this.mCustomerProfile)) {
            startPushNotificationRequestActivity(activity, arguments);
        } else {
            startMainActivity(activity);
        }
    }

    private void startMainActivity(URLNavigationActivity activity) {
        Ensighten.evaluateEvent(this, "startMainActivity", new Object[]{activity});
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    private void startPushNotificationRequestActivity(URLNavigationActivity activity, Bundle arguments) {
        Ensighten.evaluateEvent(this, "startPushNotificationRequestActivity", new Object[]{activity, arguments});
        Intent intent = new Intent(activity, PushNotificationRequestActivity.class);
        if (arguments != null) {
            intent.putExtras(arguments);
        }
        activity.startActivity(intent);
    }

    private void startOffersRequestActivity(URLNavigationActivity activity, Bundle bundle) {
        Ensighten.evaluateEvent(this, "startOffersRequestActivity", new Object[]{activity, bundle});
        Intent intent = new Intent(activity, OffersRequestActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.putExtra(URLNavigationActivity.ARG_FRAGMENT, "mail_validation");
        activity.startActivity(intent);
    }

    private void showSignInChangePasswordFragment(URLNavigationActivity activity) {
        Ensighten.evaluateEvent(this, "showSignInChangePasswordFragment", new Object[]{activity});
        activity.showFragment("signin_change_password");
    }

    private void startSignInActivity(Context context) {
        Ensighten.evaluateEvent(this, "startSignInActivity", new Object[]{context});
        Intent intent = new Intent(context, SignInActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(URLNavigationActivity.DATA_PASSER_KEY, this.mCustomerProfile);
        bundle.putBoolean(URLNavigationActivity.SHOW_ACTIVATION_DIALOG, true);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public void persistProfile() {
        Ensighten.evaluateEvent(this, "persistProfile", null);
        if (this.mCustomerProfile != null) {
            LocalDataManager dataManager = LocalDataManager.getSharedInstance();
            dataManager.setPrefSavedLogin(this.mCustomerProfile.getUserName());
            if (this.mCustomerProfile.isUsingSocialLogin()) {
                dataManager.setPrefSavedSocialNetworkId(this.mCustomerProfile.getSocialServiceAuthenticationID());
                if (this.mCustomerProfile.getSocialServiceAuthenticationID() == 3) {
                    dataManager.setPrefSavedLoginPass(this.mCustomerProfile.getSocialAuthenticationToken());
                    return;
                }
                return;
            }
            dataManager.setPrefSavedLoginPass(this.mCustomerProfile.getPassword());
        }
    }

    public CustomerProfile getProfile() {
        Ensighten.evaluateEvent(this, "getProfile", null);
        if (this.mCustomerProfile == null && this.mCustomerModule != null) {
            this.mCustomerProfile = this.mCustomerModule.getCurrentProfile();
        }
        return this.mCustomerProfile;
    }

    public void setProfile(CustomerProfile profile) {
        Ensighten.evaluateEvent(this, "setProfile", new Object[]{profile});
        this.mCustomerProfile = profile;
    }

    public void updateProfile() {
        Ensighten.evaluateEvent(this, "updateProfile", null);
        if (this.mCustomerModule != null) {
            this.mCustomerModule.updateCustomerProfile(this.mCustomerProfile, this.mCustomerProfileListener);
        }
    }

    public void resendVerification(int option) {
        Ensighten.evaluateEvent(this, "resendVerification", new Object[]{new Integer(option)});
        this.mCustomerProfile.setActivationOption(option);
        this.mCustomerModule.resendActivation(this.mCustomerProfile, this.mResendListener);
    }

    public boolean isLoggedIn() {
        Ensighten.evaluateEvent(this, "isLoggedIn", null);
        return this.mCustomerModule != null && this.mCustomerModule.isLoggedIn();
    }

    public Set<String> getVisibleFields() {
        Ensighten.evaluateEvent(this, "getVisibleFields", null);
        return new HashSet(this.mVisibleFields);
    }

    public HashMap<String, String[]> getOptionFields() {
        Ensighten.evaluateEvent(this, "getOptionFields", null);
        return this.mOptionFields;
    }

    public void resendSMSCode(final URLNavigationActivity activity) {
        Ensighten.evaluateEvent(this, "resendSMSCode", new Object[]{activity});
        UIUtils.startActivityIndicator((Context) activity, (int) C2658R.string.lite_dialog_sms_send);
        this.mCustomerModule.sendSMSCode(this.mCustomerProfile, new AsyncListener<Boolean>() {

            /* renamed from: com.mcdonalds.app.util.LoginManager$7$1 */
            class C38641 implements OnClickListener {
                C38641() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    dialog.dismiss();
                }
            }

            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                UIUtils.stopActivityIndicator();
                if (exception == null) {
                    MCDAlertDialogBuilder.withContext(activity).setTitle((int) C2658R.string.lite_alrt_sms_sent).setMessage(activity.getString(C2658R.string.lite_alrt_msg_sms_sent)).setPositiveButton((int) C2658R.string.f6083ok, new C38641()).create().show();
                } else {
                    AsyncException.report(exception);
                }
            }
        });
    }

    public void verifySMSCode(String smsCode, final URLNavigationActivity activity) {
        Ensighten.evaluateEvent(this, "verifySMSCode", new Object[]{smsCode, activity});
        UIUtils.startActivityIndicator((Context) activity, (int) C2658R.string.lite_dialog_sms_verf);
        this.mCustomerModule.verifySMS(smsCode, this.mCustomerProfile, new AsyncListener<Boolean>() {
            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                UIUtils.stopActivityIndicator();
                if (exception == null) {
                    boolean shouldGoToPrevious = activity.getIntent().getBooleanExtra("shouldGoToPreviousView", false);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$100", new Object[]{LoginManager.this}).getCustomerLoginInfo().setDefaultPhoneNumberVerified(true);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$1400", new Object[]{LoginManager.this}).setCurrentProfile(Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.LoginManager", "access$100", new Object[]{LoginManager.this}));
                    if (shouldGoToPrevious) {
                        activity.finish();
                        return;
                    } else {
                        LoginManager.access$1500(LoginManager.this, activity);
                        return;
                    }
                }
                AsyncException.report(exception);
            }
        });
    }
}
