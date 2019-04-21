package com.mcdonalds.app.customer;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.account.OfferData;
import com.mcdonalds.app.account.ProfileUpdateActivity;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.customer.push.NotificationManager;
import com.mcdonalds.app.customer.push.OffersRequestActivity;
import com.mcdonalds.app.customer.push.PushNotificationRequestActivity;
import com.mcdonalds.app.customer.push.PushNotificationRequestFragment;
import com.mcdonalds.app.msa.MSASettings;
import com.mcdonalds.app.ordering.basket.BasketActivity;
import com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity;
import com.mcdonalds.app.p043ui.NavigationDrawerFragment;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.social.SocialLoginFragment;
import com.mcdonalds.app.storelocator.StoreDetailsActivity;
import com.mcdonalds.app.storelocator.StoreDetailsFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.LanguageUtil;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.ServiceUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.middleware.MWException;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.modules.models.OfferCategory;
import com.mcdonalds.sdk.modules.models.SocialLoginAuthenticationResults;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.modules.models.StoreFavoriteInfo;
import com.mcdonalds.sdk.modules.offers.OffersModule;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.analytics.conversionmaster.LoginAction;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.mcdonalds.sdk.services.log.SafeLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SignInFragment extends SocialLoginFragment {
    public static final String LOG_TAG = SignInFragment.class.getSimpleName();
    private String autoLoginPass = null;
    private String autoLoginUser = null;
    private boolean isShouldReturnToBasket;
    private boolean mAutoEnrollOffersByDefault;
    private EditText mConfirmPassword;
    private EditText mEmail;
    private boolean mHideOffersOptInPage = false;
    private int mLoginAttempts;
    private int mMaximumLoginAttempts;
    private boolean mNeedToReturnToBasket;
    private EditText mNewPassword;
    private List<OfferCategory> mOfferCategories;
    private OffersModule mOffersModule;
    protected URLNavigationActivity mParent;
    private EditText mPassword;
    private Class<? extends URLNavigationActivity> mResultContainerClass;
    private String mResultFragmentName;
    private View mSocialContainer;
    private int mSocialLoginId = -1;
    private Button mSubmitButton;
    private TextWatcher mSubmitButtonEnableTextWatcher = new C30424();

    /* renamed from: com.mcdonalds.app.customer.SignInFragment$1 */
    class C30391 implements OnClickListener {
        C30391() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(SignInFragment.this.getAnalyticsTitle(), "Forgot password");
            if (LoginManager.getInstance().getRegisterSettings().chooseSignInMethodEnabled()) {
                SignInFragment.access$000(SignInFragment.this);
                return;
            }
            SignInFragment.this.startActivity(LostPasswordActivity.class);
            SignInFragment.this.getNavigationActivity().finish();
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignInFragment$2 */
    class C30402 implements OnClickListener {
        C30402() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(SignInFragment.this.getAnalyticsTitle(), "Sign In");
            SignInFragment.this.onSubmitDoSignin(null);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignInFragment$3 */
    class C30413 implements OnClickListener {
        C30413() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(SignInFragment.this.getAnalyticsTitle(), "Start Registration");
            if (AppUtils.hideTermsAndConditionsView()) {
                SignInFragment.this.startActivity(SignUpActivity.class);
            } else {
                SignInFragment.this.startActivity(TermsOfServiceActivity.class);
            }
            Activity activity = SignInFragment.this.getActivity();
            if (!(activity instanceof MainActivity)) {
                activity.finish();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignInFragment$4 */
    class C30424 implements TextWatcher {
        C30424() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{s, new Integer(start), new Integer(count), new Integer(after)});
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{s, new Integer(start), new Integer(before), new Integer(count)});
        }

        public void afterTextChanged(Editable s) {
            Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{s});
            SignInFragment.access$100(SignInFragment.this);
        }
    }

    static /* synthetic */ void access$000(SignInFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$000", new Object[]{x0});
        x0.openResetPassword();
    }

    static /* synthetic */ void access$100(SignInFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$100", new Object[]{x0});
        x0.shouldEnableSubmitButton();
    }

    static /* synthetic */ Class access$1002(SignInFragment x0, Class x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$1002", new Object[]{x0, x1});
        x0.mResultContainerClass = x1;
        return x1;
    }

    static /* synthetic */ void access$1200(SignInFragment x0, Bundle x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$1200", new Object[]{x0, x1});
        x0.startNextActivity(x1);
    }

    static /* synthetic */ void access$200(SignInFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$200", new Object[]{x0});
        x0.showSignInChangePasswordFragment();
    }

    static /* synthetic */ int access$302(SignInFragment x0, int x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$302", new Object[]{x0, new Integer(x1)});
        x0.mLoginAttempts = x1;
        return x1;
    }

    static /* synthetic */ int access$308(SignInFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$308", new Object[]{x0});
        int i = x0.mLoginAttempts;
        x0.mLoginAttempts = i + 1;
        return i;
    }

    static /* synthetic */ OffersModule access$602(SignInFragment x0, OffersModule x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$602", new Object[]{x0, x1});
        x0.mOffersModule = x1;
        return x1;
    }

    static /* synthetic */ boolean access$802(SignInFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$802", new Object[]{x0, new Boolean(x1)});
        x0.isShouldReturnToBasket = x1;
        return x1;
    }

    static /* synthetic */ String access$902(SignInFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$902", new Object[]{x0, x1});
        x0.mResultFragmentName = x1;
        return x1;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginManager.getInstance().loadRegisterConfig();
        this.mParent = getNavigationActivity();
        this.mMaximumLoginAttempts = Configuration.getSharedInstance().getIntForKey("loginAttemptsToRequestPasswordReset");
        this.mHideOffersOptInPage = Configuration.getSharedInstance().getBooleanForKey("interface.register.hideOffersOptinPage");
        this.mAutoEnrollOffersByDefault = Configuration.getSharedInstance().getBooleanForKey("interface.signin.autoEnrollOffersByDefault");
        if (getArguments() != null) {
            this.mResultFragmentName = getArguments().getString("EXTRA_RESULT_FRAGMENT_NAME");
            this.mResultContainerClass = (Class) getArguments().getSerializable("EXTRA_RESULT_CONTAINER_CLASS");
            this.mNeedToReturnToBasket = getArguments().getBoolean("NEED_TO_RETURN_TO_BASKET");
            this.isShouldReturnToBasket = true;
        }
        setUserPwd();
        setHasOptionsMenu(true);
        checkForOffersModule();
        DataLayerManager.getInstance().setFormName("Sign In Form");
    }

    private void setUserPwd() {
        Ensighten.evaluateEvent(this, "setUserPwd", null);
        LocalDataManager localDataManager = LocalDataManager.getSharedInstance();
        String prefSavedLogin = localDataManager.getPrefSavedLogin();
        String prefSavedLoginPass = localDataManager.getPrefSavedLoginPass();
        int prefSavedSocialID = localDataManager.getPrefSavedSocialNetworkId();
        boolean autoLogin = (TextUtils.isEmpty(prefSavedLogin) || (TextUtils.isEmpty(prefSavedLoginPass) && prefSavedSocialID == -1)) ? false : true;
        if (autoLogin && prefSavedSocialID != 3) {
            this.autoLoginUser = prefSavedLogin;
            this.autoLoginPass = prefSavedLoginPass;
        }
    }

    private void checkForOffersModule() {
        Ensighten.evaluateEvent(this, "checkForOffersModule", null);
        if (this.mOffersModule == null) {
            this.mOffersModule = (OffersModule) ModuleManager.getModule(OffersModule.NAME);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.sign_in, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        return false;
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        Ensighten.evaluateEvent(this, "getLayoutResourceId", null);
        return C2658R.layout.fragment_sign_in;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        if (rootView != null) {
            boolean enablePhoneLogin = Configuration.getSharedInstance().getBooleanForKey("interface.register.chooseEmailOrPhoneAsUsername");
            this.mSocialContainer = rootView.findViewById(C2358R.C2357id.social_container);
            this.mEmail = (EditText) rootView.findViewById(2131821086);
            if (enablePhoneLogin) {
                this.mEmail.setHint(C2658R.string.text_hint_phone_email_address);
            }
            this.mPassword = (EditText) rootView.findViewById(C2358R.C2357id.password);
            TextView forget_password = (TextView) rootView.findViewById(C2358R.C2357id.forget_password);
            forget_password.getPaint().setFlags(8);
            forget_password.setOnClickListener(new C30391());
            this.mPassword.addTextChangedListener(this.mSubmitButtonEnableTextWatcher);
            this.mEmail.addTextChangedListener(this.mSubmitButtonEnableTextWatcher);
            this.mNewPassword = (EditText) rootView.findViewById(C2358R.C2357id.new_password);
            this.mConfirmPassword = (EditText) rootView.findViewById(C2358R.C2357id.confirm_password);
            if (Configuration.getSharedInstance().getBooleanForKey("interface.disableInteraction")) {
                UIUtils.disableInteraction(this.mEmail);
            }
            this.mSubmitButton = (Button) rootView.findViewById(C2358R.C2357id.button_submit);
            this.mSubmitButton.setEnabled(false);
            this.mSubmitButton.setOnClickListener(new C30402());
            ((TextView) rootView.findViewById(C2358R.C2357id.need_an_account)).setOnClickListener(new C30413());
        }
        getNavigationActivity().setTitle(getString(C2658R.string.sign_in));
        return rootView;
    }

    private boolean validate(String text) {
        Ensighten.evaluateEvent(this, "validate", new Object[]{text});
        if (Configuration.getSharedInstance().getBooleanForKey("interface.signin.usernameStrictValidation")) {
            boolean isEmail = UIUtils.isEmailValid(text);
            boolean isPhone = UIUtils.isPhoneValid(text);
            if (isEmail || isPhone) {
                return true;
            }
            return false;
        } else if (TextUtils.isEmpty(text)) {
            return false;
        } else {
            return true;
        }
    }

    private void shouldEnableSubmitButton() {
        Ensighten.evaluateEvent(this, "shouldEnableSubmitButton", null);
        if (TextUtils.isEmpty(this.mPassword.getText().toString()) || !validate(this.mEmail.getText().toString())) {
            this.mSubmitButton.setEnabled(false);
        } else {
            this.mSubmitButton.setEnabled(true);
        }
    }

    public void onStart() {
        super.onStart();
        if (this.autoLoginUser != null && this.autoLoginPass != null) {
            this.mEmail.setText(this.autoLoginUser);
            this.mPassword.setText(this.autoLoginPass);
        }
    }

    public void onResume() {
        super.onResume();
        if (LoginManager.getInstance().getProfile() != null) {
            boolean shouldSaveUsername = Configuration.getSharedInstance().getBooleanForKey("interface.signin.clearUserNameAfterSignOut");
            String username = LocalDataManager.getSharedInstance().getPrefSavedLogin();
            if (shouldSaveUsername && !TextUtils.isEmpty(username) && LocalDataManager.getSharedInstance().getPrefSavedSocialNetworkId() != 3) {
                this.mEmail.setText(username);
            }
        }
    }

    public void onPause() {
        super.onPause();
        UIUtils.stopActivityIndicator();
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.title_activity_signin);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_login);
    }

    public void onSocialNetworkAvailable() {
        Ensighten.evaluateEvent(this, "onSocialNetworkAvailable", null);
        if (!Configuration.getSharedInstance().getBooleanForKey("interface.register.hideSocialLogin")) {
            this.mSocialContainer.setAlpha(0.0f);
            this.mSocialContainer.setVisibility(0);
            this.mSocialContainer.animate().alpha(1.0f).setDuration(150).start();
        }
    }

    public void onSocialNetworkSelected(SocialNetwork socialNetwork) {
        Ensighten.evaluateEvent(this, "onSocialNetworkSelected", new Object[]{socialNetwork});
        super.onSocialNetworkSelected(socialNetwork);
        this.mSocialLoginId = socialNetwork.getSocialNetworkID();
    }

    public void onSocialNetworkAuthenticationComplete(SocialLoginAuthenticationResults results) {
        Ensighten.evaluateEvent(this, "onSocialNetworkAuthenticationComplete", new Object[]{results});
        if (results.isAllowSocialLoginWithoutEmail() || results.getEmailAddress() != null) {
            onSubmitDoSignin(results);
        } else {
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setMessage(getResources().getString(C2658R.string.validate_social_network)).setPositiveButton((int) C2658R.string.f6083ok, null).create().show();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void persistProfile(CustomerProfile profile) {
        Ensighten.evaluateEvent(this, "persistProfile", new Object[]{profile});
        LocalDataManager dataManager = LocalDataManager.getSharedInstance();
        dataManager.setPrefSavedLogin(profile.getUserName());
        if (profile.isUsingSocialLogin()) {
            dataManager.setPrefSavedSocialNetworkId(profile.getSocialServiceAuthenticationID());
            if (profile.getSocialServiceAuthenticationID() == 3) {
                dataManager.setPrefSavedLoginPass(profile.getSocialAuthenticationToken());
            }
        } else {
            dataManager.setPrefSavedLoginPass(profile.getPassword());
        }
        CustomerModule module = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        SparseArray<String> customDimensions = new SparseArray();
        if (module.getCurrentStore() != null) {
            customDimensions.put(1, String.valueOf(module.getCurrentStore().getStoreId()));
        }
        customDimensions.put(2, String.valueOf(profile.getCustomerId()));
        customDimensions.put(22, this.mParent.getString(C2658R.string.analytics_signed));
        HashMap<String, Object> jiceMap = new HashMap();
        jiceMap.put(JiceArgs.EVENT_LOGIN, null);
        Analytics.track(AnalyticType.Custom, new ArgBuilder().setCustom(customDimensions).setJice(jiceMap).setConversionMaster(new LoginAction(String.valueOf(profile.getCustomerId()))).build());
    }

    /* Access modifiers changed, original: protected */
    public void onSubmitDoSignin(SocialLoginAuthenticationResults socialLoginInfo) {
        boolean useSocialLogin;
        Ensighten.evaluateEvent(this, "onSubmitDoSignin", new Object[]{socialLoginInfo});
        if (socialLoginInfo != null) {
            useSocialLogin = true;
        } else {
            useSocialLogin = false;
        }
        if (!useSocialLogin && (TextUtils.isEmpty(getTrimmedString(this.mEmail)) || TextUtils.isEmpty(getTrimmedString(this.mPassword)))) {
            return;
        }
        if (AppUtils.isNetworkConnected(this.mParent)) {
            UIUtils.startActivityIndicator(getActivity(), this.mParent.getString(C2658R.string.dialog_signing_in));
            final AuthenticationParameters parameters = new AuthenticationParameters();
            if (useSocialLogin) {
                if (socialLoginInfo.isAllowSocialLoginWithoutEmail()) {
                    parameters.setUserName(socialLoginInfo.getUserId());
                    parameters.setAllowSocialLoginWithoutEmail(true);
                } else {
                    parameters.setUserName(socialLoginInfo.getEmailAddress());
                    parameters.setAllowSocialLoginWithoutEmail(false);
                }
                parameters.setEmailAddress(socialLoginInfo.getEmailAddress());
                parameters.setUsingSocialLogin(true);
                parameters.setSocialServiceID(this.mSocialLoginId);
                parameters.setFirstName(socialLoginInfo.getFirstName());
                parameters.setLastName(socialLoginInfo.getLastName());
                parameters.setSocialAuthenticationToken(socialLoginInfo.getAccessToken());
                parameters.setSocialProvider(this.provider);
                parameters.setSocialUserID(socialLoginInfo.getUserId());
            } else {
                parameters.setUserName(getTrimmedString(this.mEmail));
                parameters.setPassword(getTrimmedString(this.mPassword));
            }
            final CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            customerModule.authenticate(parameters, new AsyncListener<CustomerProfile>() {

                /* renamed from: com.mcdonalds.app.customer.SignInFragment$5$3 */
                class C30483 implements AsyncListener<List<StoreFavoriteInfo>> {
                    C30483() {
                    }

                    public void onResponse(List<StoreFavoriteInfo> response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    }
                }

                /* renamed from: com.mcdonalds.app.customer.SignInFragment$5$4 */
                class C30494 implements AsyncListener<CustomerProfile> {
                    C30494() {
                    }

                    public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        UIUtils.stopActivityIndicator();
                        if (SignInFragment.this.getNavigationActivity() != null) {
                            SignInFragment.this.getNavigationActivity().setResult(-1);
                            SignInFragment.this.getNavigationActivity().finish();
                        }
                    }
                }

                /* renamed from: com.mcdonalds.app.customer.SignInFragment$5$5 */
                class C30505 implements AsyncListener<Boolean> {
                    C30505() {
                    }

                    public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        if (AppUtils.isTrue(response)) {
                            MCDLog.info("SignInFragment: Notification registration SUCCESS");
                        } else {
                            MCDLog.info("SignInFragment: Notification registration FAILURE");
                        }
                    }
                }

                public void onResponse(CustomerProfile profile, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{profile, token, exception});
                    UIUtils.stopActivityIndicator();
                    final CustomerProfile customerProfile;
                    DialogInterface.OnClickListener onClick;
                    if (exception == null && SignInFragment.this.getNavigationActivity() != null) {
                        if (parameters.isUsingSocialLogin()) {
                            profile.setUsingSocialLogin(true);
                            profile.setSocialServiceAuthenticationID(parameters.getSocialServiceID());
                            if (profile.getSocialAuthenticationToken() == null || profile.getSocialAuthenticationToken().isEmpty()) {
                                profile.setSocialAuthenticationToken(parameters.getSocialAuthenticationToken());
                            }
                            profile.setSocialUserID(parameters.getSocialUserID());
                        }
                        if (profile.isPasswordChangeRequired()) {
                            LoginManager.getInstance().setProfile(profile);
                            SignInFragment.access$200(SignInFragment.this);
                        } else if (profile.isUsingSocialLogin() && !profile.isSocialAccountLoginRegistered()) {
                            customerProfile = profile;
                            MCDAlertDialogBuilder.withContext(SignInFragment.this.getActivity()).setTitle(SignInFragment.this.getString(C2658R.string.need_an_account)).setMessage((int) C2658R.string.social_account_not_register).setPositiveButton(SignInFragment.this.getString(C2658R.string.register), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                                    AnalyticsUtils.trackOnClickEvent(SignInFragment.this.getAnalyticsTitle(), "Need an Account");
                                    LoginManager mManager = LoginManager.getInstance();
                                    if (mManager != null) {
                                        mManager.setProfile(customerProfile);
                                    }
                                    SignUpFragment.isSocialRegister = true;
                                    SignInFragment.this.startActivity(TermsOfServiceActivity.class);
                                    SignInFragment.this.getActivity().finish();
                                }
                            }).setNegativeButton(SignInFragment.this.getString(C2658R.string.button_cancel), null).create().show();
                            SignInFragment.access$308(SignInFragment.this);
                        } else if (profile.isEmailActivated()) {
                            ServiceUtils.getSharedInstance().retrieveFavoriteStores(profile, new C30483());
                            SignInFragment.this.persistProfile(profile);
                            if (!profile.getSubscribedToOffers().booleanValue()) {
                                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$400", new Object[]{SignInFragment.this}) && Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$500", new Object[]{SignInFragment.this})) {
                                    profile.setSubscribedToOffers(true);
                                    profile.setReceivePromotions(Boolean.valueOf(true));
                                    SignInFragment.access$602(SignInFragment.this, (OffersModule) ModuleManager.getModule(OffersModule.NAME));
                                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$700", new Object[]{SignInFragment.this}) != null) {
                                        List<Integer> preferredOfferCategories = new ArrayList();
                                        for (OfferCategory offerCategory : Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$700", new Object[]{SignInFragment.this})) {
                                            Integer categoryNum = offerCategory.getId();
                                            OfferData offerData = new OfferData();
                                            offerData.setId(categoryNum);
                                            offerData.setDescription(offerCategory.getDescription());
                                            offerData.setState(Boolean.valueOf(true));
                                            preferredOfferCategories.add(offerData.getId());
                                        }
                                        profile.setPreferredOfferCategories(preferredOfferCategories);
                                    }
                                }
                                customerModule.updateCustomerProfile(profile, new C30494());
                            }
                            boolean showOneLastThing = customerModule.isFirstTimeLoginOnDevice(profile);
                            boolean hidePushNotificationScreen = Configuration.getSharedInstance().getBooleanForKey("interface.signin.hidePushNotificationScreen");
                            ((NavigationDrawerFragment) SignInFragment.this.getNavigationActivity().getSupportFragmentManager().findFragmentById(C2358R.C2357id.navigation_drawer)).setLoggedInDrawerState(true);
                            Bundle arguments = SignInFragment.this.getArguments();
                            if (arguments == null) {
                                arguments = new Bundle();
                            }
                            if (arguments.containsKey(URLNavigationActivity.MODAL)) {
                                arguments.remove(URLNavigationActivity.MODAL);
                            }
                            if (!showOneLastThing || hidePushNotificationScreen) {
                                if (Configuration.getSharedInstance().getBooleanForKey("interface.signin.autoEnrollPushNotificationByDefault")) {
                                    NotificationManager.register(new C30505());
                                }
                                if (profile.isEmailTakeOver()) {
                                    SignInFragment.access$802(SignInFragment.this, false);
                                    SignInFragment.access$902(SignInFragment.this, "email_take_over");
                                    SignInFragment.access$1002(SignInFragment.this, ProfileUpdateActivity.class);
                                } else if (profile.isMobileTakeOver()) {
                                    SignInFragment.access$802(SignInFragment.this, false);
                                    SignInFragment.access$902(SignInFragment.this, "mobile_take_over");
                                    SignInFragment.access$1002(SignInFragment.this, ProfileUpdateActivity.class);
                                } else if (!profile.isMobileVerified() && !Configuration.getSharedInstance().getBooleanForKey("interface.hideMobileVerificatinScreen")) {
                                    SignInFragment.access$802(SignInFragment.this, false);
                                    SignInFragment.access$902(SignInFragment.this, "mobile_verify");
                                    SignInFragment.access$1002(SignInFragment.this, ProfileUpdateActivity.class);
                                } else if (arguments.getBoolean("EXTRA_SAVING_FAVORITE", false)) {
                                    SignInFragment.access$802(SignInFragment.this, false);
                                    boolean isDetail = arguments.getBoolean("saving_fav_detail", false);
                                    SignInFragment.access$902(SignInFragment.this, isDetail ? StoreDetailsFragment.NAME : "store_locator");
                                    SignInFragment.access$1002(SignInFragment.this, isDetail ? StoreDetailsActivity.class : MainActivity.class);
                                } else if (arguments.getBoolean("GO_TO_MSA", false)) {
                                    SignInFragment.access$802(SignInFragment.this, false);
                                    SignInFragment.access$902(SignInFragment.this, "msa_logged_in");
                                    SignInFragment.access$1002(SignInFragment.this, MainActivity.class);
                                } else if (arguments.getBoolean("EXTRA_SAVING_FAVORITE_PRODUCT", false)) {
                                    SignInFragment.access$802(SignInFragment.this, false);
                                    SignInFragment.access$902(SignInFragment.this, null);
                                    SignInFragment.access$1002(SignInFragment.this, ProductDetailsActivity.class);
                                } else {
                                    SignInFragment.access$902(SignInFragment.this, "dashboard");
                                    SignInFragment.access$1002(SignInFragment.this, MainActivity.class);
                                }
                            } else {
                                SignInFragment.access$802(SignInFragment.this, false);
                                SignInFragment.access$902(SignInFragment.this, PushNotificationRequestFragment.NAME);
                                SignInFragment.access$1002(SignInFragment.this, PushNotificationRequestActivity.class);
                            }
                            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$800", new Object[]{SignInFragment.this}) && Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$1100", new Object[]{SignInFragment.this})) {
                                SignInFragment.this.startActivity(BasketActivity.class, arguments);
                                SignInFragment.this.getNavigationActivity().finish();
                            } else {
                                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$1100", new Object[]{SignInFragment.this})) {
                                    arguments.putBoolean("NEED_TO_RETURN_TO_BASKET", true);
                                }
                                SignInFragment.access$1200(SignInFragment.this, arguments);
                            }
                            SignInFragment.this.getNavigationActivity().setResult(-1);
                            if (!(SignInFragment.this.getNavigationActivity() instanceof MainActivity)) {
                                SignInFragment.this.getNavigationActivity().finish();
                            }
                            ServiceUtils.getSharedInstance().removeOffersCache();
                            if (Configuration.getSharedInstance().getBooleanForKey("interface.isMSAEnabled")) {
                                MSASettings.scheduleNextAlarm(SignInFragment.this.getActivity());
                            }
                            DataLayerManager.getInstance().setUser(profile, "Signed-in", AppUtils.getCurrentMenuType());
                        } else {
                            customerProfile = profile;
                            onClick = new DialogInterface.OnClickListener() {

                                /* renamed from: com.mcdonalds.app.customer.SignInFragment$5$2$1 */
                                class C30461 implements AsyncListener<Void> {
                                    C30461() {
                                    }

                                    public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                                        SafeLog.m7744d(SignInFragment.LOG_TAG, "mail resend");
                                    }
                                }

                                /* renamed from: com.mcdonalds.app.customer.SignInFragment$5$2$2 */
                                class C30472 implements DialogInterface.OnClickListener {
                                    C30472() {
                                    }

                                    public void onClick(DialogInterface dialog, int which) {
                                        Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("validation_method", customerProfile.getLoginPreference());
                                        bundle.putBoolean("social_login_without_email", customerProfile.isUsingSocialLoginWithoutEmail());
                                        bundle.putParcelable("EXTRA_USER_PROFILR", customerProfile);
                                        SignInFragment.this.startActivity(OffersRequestActivity.class, "mail_validation", bundle);
                                    }
                                }

                                public void onClick(DialogInterface dialog, int which) {
                                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                                    AnalyticsUtils.trackOnClickEvent(SignInFragment.this.getAnalyticsTitle(), "Resend Activation Email");
                                    customerModule.resendActivation(customerProfile, new C30461());
                                    DialogInterface.OnClickListener onClickOK = new C30472();
                                    boolean useMobileRegisterOnly = Configuration.getSharedInstance().getBooleanForKey("interface.useMobileNumberRegisterOnly");
                                    if (customerProfile.isUsingSocialLoginWithoutEmail() || useMobileRegisterOnly) {
                                        MCDAlertDialogBuilder.withContext(SignInFragment.this.getNavigationActivity()).setCancelable(false).setPositiveButton((int) C2658R.string.f6083ok, onClickOK).setTitle(SignInFragment.this.getString(C2658R.string.resend_sms_title)).setMessage((int) C2658R.string.activation_sms_sent).create().show();
                                    } else {
                                        MCDAlertDialogBuilder.withContext(SignInFragment.this.getNavigationActivity()).setCancelable(false).setNeutralButton((int) C2658R.string.f6083ok, null).setTitle(SignInFragment.this.getString(C2658R.string.resend_email_title)).setMessage((int) C2658R.string.activation_email_sent).create().show();
                                    }
                                }
                            };
                            boolean useMobileRegisterOnly = Configuration.getSharedInstance().getBooleanForKey("interface.useMobileNumberRegisterOnly");
                            if (profile.isUsingSocialLoginWithoutEmail() || useMobileRegisterOnly) {
                                MCDAlertDialogBuilder.withContext(SignInFragment.this.getActivity()).setTitle(SignInFragment.this.getString(C2658R.string.resend_sms_title)).setMessage(SignInFragment.this.getString(C2658R.string.resend_sms_message)).setPositiveButton(SignInFragment.this.getString(C2658R.string.button_resend), onClick).setNegativeButton(SignInFragment.this.getString(C2658R.string.button_cancel), null).create().show();
                            } else {
                                MCDAlertDialogBuilder.withContext(SignInFragment.this.getActivity()).setTitle(SignInFragment.this.getString(C2658R.string.resend_email_title)).setMessage(SignInFragment.this.getString(C2658R.string.resend_email_message)).setPositiveButton(SignInFragment.this.getString(C2658R.string.button_resend), onClick).setNegativeButton(SignInFragment.this.getString(C2658R.string.button_cancel), null).create().show();
                            }
                            SignInFragment.access$308(SignInFragment.this);
                        }
                        LanguageUtil.setPrefRememberLogin(false);
                    } else if (exception instanceof MWException) {
                        MWException exp = (MWException) exception;
                        customerProfile = profile;
                        onClick = new DialogInterface.OnClickListener() {

                            /* renamed from: com.mcdonalds.app.customer.SignInFragment$5$6$1 */
                            class C30521 implements AsyncListener<Void> {
                                C30521() {
                                }

                                public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                                    SafeLog.m7744d(SignInFragment.LOG_TAG, "mail resend");
                                }
                            }

                            /* renamed from: com.mcdonalds.app.customer.SignInFragment$5$6$2 */
                            class C30532 implements DialogInterface.OnClickListener {
                                C30532() {
                                }

                                public void onClick(DialogInterface dialog, int which) {
                                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("validation_method", customerProfile.getLoginPreference());
                                    bundle.putBoolean("social_login_without_email", customerProfile.isUsingSocialLoginWithoutEmail());
                                    bundle.putParcelable("EXTRA_USER_PROFILR", customerProfile);
                                    SignInFragment.this.startActivity(OffersRequestActivity.class, "mail_validation", bundle);
                                }
                            }

                            public void onClick(DialogInterface dialog, int which) {
                                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                                customerModule.resendActivation(customerProfile, new C30521());
                                DialogInterface.OnClickListener onClickOK = new C30532();
                                if (customerProfile.getActivationOption() == 2) {
                                    MCDAlertDialogBuilder.withContext(SignInFragment.this.getNavigationActivity()).setCancelable(false).setPositiveButton((int) C2658R.string.f6083ok, onClickOK).setTitle(SignInFragment.this.getString(C2658R.string.resend_sms_title)).setMessage((int) C2658R.string.activation_sms_sent).create().show();
                                } else if (customerProfile.getActivationOption() == 1) {
                                    MCDAlertDialogBuilder.withContext(SignInFragment.this.getNavigationActivity()).setCancelable(false).setPositiveButton((int) C2658R.string.f6083ok, onClickOK).setTitle(SignInFragment.this.getString(C2658R.string.resend_email_title)).setMessage((int) C2658R.string.activation_email_sent).create().show();
                                }
                            }
                        };
                        if (exp.getErrorCode() == -2219) {
                            MCDAlertDialogBuilder.withContext(SignInFragment.this.getActivity()).setTitle(SignInFragment.this.getString(C2658R.string.resend_email_title)).setMessage(SignInFragment.this.getString(C2658R.string.resend_email_message)).setPositiveButton(SignInFragment.this.getString(C2658R.string.button_resend), onClick).setNegativeButton(SignInFragment.this.getString(C2658R.string.button_cancel), null).create().show();
                        } else if (exp.getErrorCode() == -2213) {
                            MCDAlertDialogBuilder.withContext(SignInFragment.this.getActivity()).setTitle(SignInFragment.this.getString(C2658R.string.resend_sms_title)).setMessage(SignInFragment.this.getString(C2658R.string.resend_sms_message)).setPositiveButton(SignInFragment.this.getString(C2658R.string.button_resend), onClick).setNegativeButton(SignInFragment.this.getString(C2658R.string.button_cancel), null).create().show();
                        } else if (exp.getErrorCode() == -1032) {
                            LoginManager.getInstance().setProfile(profile);
                            ((SignInActivity) SignInFragment.this.getActivity()).changeFragment(new SignInExistingAccountFragment());
                        } else {
                            AsyncException.report((AsyncException) exp);
                            SignInFragment.access$308(SignInFragment.this);
                        }
                    } else {
                        AsyncException.report(exception);
                        SignInFragment.access$308(SignInFragment.this);
                    }
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$1300", new Object[]{SignInFragment.this}) != 0 && Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$300", new Object[]{SignInFragment.this}) >= Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInFragment", "access$1300", new Object[]{SignInFragment.this})) {
                        SignInFragment.access$302(SignInFragment.this, 0);
                        SignInFragment.access$000(SignInFragment.this);
                    }
                }
            });
            return;
        }
        UIUtils.showNoNetworkAlert(getNavigationActivity());
    }

    @NonNull
    private String getTrimmedString(EditText field) {
        Ensighten.evaluateEvent(this, "getTrimmedString", new Object[]{field});
        return field.getText().toString().trim();
    }

    private void startNextActivity(Bundle arguments) {
        Ensighten.evaluateEvent(this, "startNextActivity", new Object[]{arguments});
        startActivity(this.mResultContainerClass, this.mResultFragmentName, arguments);
    }

    private void showSignInChangePasswordFragment() {
        Ensighten.evaluateEvent(this, "showSignInChangePasswordFragment", null);
        showFragment("signin_change_password");
    }

    private void openResetPassword() {
        Ensighten.evaluateEvent(this, "openResetPassword", null);
        Bundle bundle = new Bundle();
        bundle.putBoolean(URLNavigationActivity.MODAL, true);
        startActivity(ProfileUpdateActivity.class, "reset_password", bundle);
    }
}
