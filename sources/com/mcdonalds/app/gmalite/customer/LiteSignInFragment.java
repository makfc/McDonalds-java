package com.mcdonalds.app.gmalite.customer;

import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.account.ProfileUpdateActivity;
import com.mcdonalds.app.customer.ChooseMethodView;
import com.mcdonalds.app.customer.LostPasswordActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.social.SocialLoginFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.app.web.WebActivity;
import com.mcdonalds.app.widget.ValidationListener;
import com.mcdonalds.app.widget.ValidationListener.Callback;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.customer.CustomerProfile.AccountVerificationType;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.modules.models.SocialLoginAuthenticationResults;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.notifications.NotificationCenter;

public class LiteSignInFragment extends SocialLoginFragment {
    private static final String LOG_TAG = LiteSignInFragment.class.getSimpleName();
    private String autoLoginPass = null;
    private String autoLoginUser = null;
    private boolean isShouldReturnToBasket;
    private ChooseMethodView mChooseMethod;
    private EditText mEmailEditText;
    private boolean mEmailValidated;
    private int mLoginAttempts;
    private int mMaximumLoginAttempts;
    private boolean mNeedToReturnToBasket;
    private OnClickListener mOnClickCustomerSupport = new C31908();
    private final OnFocusChangeListener mOnFocusChangeListener = new C31919();
    protected URLNavigationActivity mParent;
    private EditText mPasswordEditText;
    private boolean mPasswordValidated;
    private Class<? extends URLNavigationActivity> mResultContainerClass;
    private String mResultFragmentName;
    private BroadcastReceiver mSignInNotificationReceiver;
    private View mSocialContainer;
    private int mSocialLoginId = -1;
    private Button mSubmitButton;
    private Callback mValidationCallback = new C317611();
    private SparseArray<ValidationListener> mValidations;

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignInFragment$11 */
    class C317611 implements Callback {
        C317611() {
        }

        public void onFieldValidationStateChanged(boolean isValidated) {
            Ensighten.evaluateEvent(this, "onFieldValidationStateChanged", new Object[]{new Boolean(isValidated)});
            LiteSignInFragment.access$900(LiteSignInFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignInFragment$13 */
    class C318013 implements AsyncListener<Boolean> {
        C318013() {
        }

        public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception != null) {
                AsyncException.report(exception);
            } else if (LiteSignInFragment.this.getNavigationActivity() instanceof MainActivity) {
                LiteSignInFragment.this.showFragment("gmalite_sms_verification");
            } else {
                LiteSignInFragment.this.startActivity(LiteSmsVerificationActivity.class);
                LiteSignInFragment.this.getNavigationActivity().finish();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignInFragment$14 */
    class C318114 implements AsyncListener<Void> {
        C318114() {
        }

        public void onResponse(Void response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception != null) {
                AsyncException.report(exception);
            } else if (LiteSignInFragment.this.getNavigationActivity() instanceof MainActivity) {
                LiteSignInFragment.this.getNavigationActivity().showFragment("liteverifyemail");
            } else {
                LiteSignInFragment.this.startActivity(LiteEmailVerificationActivity.class);
                LiteSignInFragment.this.getNavigationActivity().finish();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignInFragment$15 */
    static /* synthetic */ class C318215 {
        /* renamed from: $SwitchMap$com$mcdonalds$sdk$modules$customer$CustomerProfile$AccountVerificationType */
        static final /* synthetic */ int[] f6657x8d1a63aa = new int[AccountVerificationType.values().length];

        static {
            try {
                f6657x8d1a63aa[AccountVerificationType.SMS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f6657x8d1a63aa[AccountVerificationType.EMAIL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignInFragment$1 */
    class C31831 implements OnClickListener {
        C31831() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(LiteSignInFragment.this.getAnalyticsTitle(), "Forgot password");
            if (LoginManager.getInstance().getRegisterSettings().chooseSignInMethodEnabled()) {
                LiteSignInFragment.access$000(LiteSignInFragment.this);
                return;
            }
            LiteSignInFragment.this.startActivity(LostPasswordActivity.class);
            LiteSignInFragment.this.getNavigationActivity().finish();
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignInFragment$2 */
    class C31842 implements TextWatcher {
        C31842() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{s, new Integer(start), new Integer(count), new Integer(after)});
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{s, new Integer(start), new Integer(before), new Integer(count)});
        }

        public void afterTextChanged(Editable s) {
            Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{s});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$100", new Object[]{LiteSignInFragment.this}).getText().length() > 0) {
                LiteSignInFragment.access$202(LiteSignInFragment.this, true);
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$300", new Object[]{LiteSignInFragment.this})) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$400", new Object[]{LiteSignInFragment.this}).setEnabled(true);
                    return;
                }
                return;
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$400", new Object[]{LiteSignInFragment.this}).setEnabled(false);
            LiteSignInFragment.access$202(LiteSignInFragment.this, false);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignInFragment$3 */
    class C31853 implements TextWatcher {
        C31853() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{s, new Integer(start), new Integer(count), new Integer(after)});
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{s, new Integer(start), new Integer(before), new Integer(count)});
        }

        public void afterTextChanged(Editable s) {
            Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{s});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$500", new Object[]{LiteSignInFragment.this}).getText().length() > 0) {
                LiteSignInFragment.access$302(LiteSignInFragment.this, true);
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$200", new Object[]{LiteSignInFragment.this})) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$400", new Object[]{LiteSignInFragment.this}).setEnabled(true);
                    return;
                }
                return;
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$400", new Object[]{LiteSignInFragment.this}).setEnabled(false);
            LiteSignInFragment.access$302(LiteSignInFragment.this, false);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignInFragment$4 */
    class C31864 implements OnEditorActionListener {
        C31864() {
        }

        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            Ensighten.evaluateEvent(this, "onEditorAction", new Object[]{v, new Integer(actionId), event});
            if ((event != null && event.getKeyCode() == 66) || actionId == 6) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$400", new Object[]{LiteSignInFragment.this}).callOnClick();
            }
            return false;
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignInFragment$5 */
    class C31875 implements OnClickListener {
        C31875() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(LiteSignInFragment.this.getAnalyticsTitle(), "Continue");
            LiteSignInFragment.access$600(LiteSignInFragment.this, null);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignInFragment$6 */
    class C31886 implements OnClickListener {
        C31886() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(LiteSignInFragment.this.getAnalyticsTitle(), "Register");
            if (LiteSignInFragment.this.getNavigationActivity() instanceof MainActivity) {
                LiteSignInFragment.this.getNavigationActivity().showFragment("gmalite_sign_up");
                return;
            }
            LiteSignInFragment.this.startActivity(LiteSignUpActivity.class);
            LiteSignInFragment.this.getNavigationActivity().finish();
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignInFragment$7 */
    class C31897 implements OnClickListener {
        C31897() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(LiteSignInFragment.this.getAnalyticsTitle(), "Forgot password");
            Intent intent = new Intent(LiteSignInFragment.this.getNavigationActivity(), LiteForgotPasswordActivity.class);
            intent.putExtra("email", Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$100", new Object[]{LiteSignInFragment.this}).getText().toString());
            LiteSignInFragment.this.startActivity(intent);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignInFragment$8 */
    class C31908 implements OnClickListener {
        C31908() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(LiteSignInFragment.this.getAnalyticsTitle(), "Customer Support");
            String link = ConfigurationUtils.getCustomerSupportUrl();
            if (link != null) {
                Bundle attributes = new Bundle();
                attributes.putString("link", link);
                attributes.putString("analytics_title", LiteSignInFragment.this.getString(C2658R.string.analytics_screen_customer_support));
                LiteSignInFragment.this.getNavigationActivity().startActivity(WebActivity.class, "web", attributes);
                return;
            }
            AsyncException.report("No Customer Support URL Defined");
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSignInFragment$9 */
    class C31919 implements OnFocusChangeListener {
        C31919() {
        }

        public void onFocusChange(View v, boolean hasFocus) {
            Ensighten.evaluateEvent(this, "onFocusChange", new Object[]{v, new Boolean(hasFocus)});
            if (!hasFocus) {
                ValidationListener validation = (ValidationListener) Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$700", new Object[]{LiteSignInFragment.this}).get(v.getId());
                if (validation != null) {
                    if (!validation.isValidated()) {
                        validation.displayError();
                    }
                } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$800", new Object[]{LiteSignInFragment.this}).getSelection() == 0) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$800", new Object[]{LiteSignInFragment.this}).displayError();
                }
            }
        }
    }

    static /* synthetic */ void access$000(LiteSignInFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$000", new Object[]{x0});
        x0.openResetPassword();
    }

    static /* synthetic */ void access$1000(LiteSignInFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$1000", new Object[]{x0});
        x0.showSignInChangePasswordFragment();
    }

    static /* synthetic */ void access$1100(LiteSignInFragment x0, CustomerProfile x1, CustomerModule x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$1100", new Object[]{x0, x1, x2});
        x0.finishLogin(x1, x2);
    }

    static /* synthetic */ void access$1200(LiteSignInFragment x0, CustomerModule x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$1200", new Object[]{x0, x1});
        x0.startSmsVerification(x1);
    }

    static /* synthetic */ void access$1300(LiteSignInFragment x0, CustomerModule x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$1300", new Object[]{x0, x1});
        x0.startEmailVerification(x1);
    }

    static /* synthetic */ int access$1408(LiteSignInFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$1408", new Object[]{x0});
        int i = x0.mLoginAttempts;
        x0.mLoginAttempts = i + 1;
        return i;
    }

    static /* synthetic */ boolean access$202(LiteSignInFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$202", new Object[]{x0, new Boolean(x1)});
        x0.mEmailValidated = x1;
        return x1;
    }

    static /* synthetic */ boolean access$302(LiteSignInFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$302", new Object[]{x0, new Boolean(x1)});
        x0.mPasswordValidated = x1;
        return x1;
    }

    static /* synthetic */ void access$600(LiteSignInFragment x0, SocialLoginAuthenticationResults x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$600", new Object[]{x0, x1});
        x0.onSubmitDoSignin(x1);
    }

    static /* synthetic */ void access$900(LiteSignInFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$900", new Object[]{x0});
        x0.validateData();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginManager.getInstance().loadRegisterConfig();
        this.mParent = getNavigationActivity();
        this.mMaximumLoginAttempts = Configuration.getSharedInstance().getIntForKey("loginAttemptsToRequestPasswordReset");
        if (getArguments() != null) {
            this.mResultFragmentName = getArguments().getString("EXTRA_RESULT_FRAGMENT_NAME");
            this.mResultContainerClass = (Class) getArguments().getSerializable("EXTRA_RESULT_CONTAINER_CLASS");
            this.mNeedToReturnToBasket = getArguments().getBoolean("NEED_TO_RETURN_TO_BASKET");
            this.isShouldReturnToBasket = true;
            this.autoLoginUser = getArguments().getString("EXTRA_AUTO_LOGIN_USERNAME");
            this.autoLoginPass = getArguments().getString("EXTRA_AUTO_LOGIN_PASSWORD");
        }
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        return false;
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutResourceId() {
        Ensighten.evaluateEvent(this, "getLayoutResourceId", null);
        return C2658R.layout.fragment_lite_sign_in;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        Log.d("NAV_TEST", "Launched Lite Sign In Fragment!");
        this.mSocialContainer = rootView.findViewById(C2358R.C2357id.social_container);
        this.mEmailEditText = (EditText) rootView.findViewById(2131821086);
        TextView forget_password = (TextView) rootView.findViewById(C2358R.C2357id.forget_password);
        forget_password.getPaint().setFlags(8);
        forget_password.setOnClickListener(new C31831());
        if (Configuration.getSharedInstance().getBooleanForKey("interface.register.chooseEmailOrPhoneAsUsername")) {
            this.mEmailEditText.setHint(C2658R.string.text_hint_phone_email_address);
        }
        this.mEmailEditText.addTextChangedListener(new C31842());
        this.mPasswordEditText = (EditText) rootView.findViewById(C2358R.C2357id.password);
        this.mPasswordEditText.setTypeface(Typeface.DEFAULT);
        this.mPasswordEditText.setTransformationMethod(new PasswordTransformationMethod());
        this.mPasswordEditText.addTextChangedListener(new C31853());
        this.mPasswordEditText.setOnEditorActionListener(new C31864());
        this.mSubmitButton = (Button) rootView.findViewById(C2358R.C2357id.button_submit);
        this.mSubmitButton.setOnClickListener(new C31875());
        ((TextView) rootView.findViewById(C2358R.C2357id.sign_up)).setOnClickListener(new C31886());
        ((TextView) rootView.findViewById(C2358R.C2357id.forgot_password_link)).setOnClickListener(new C31897());
        ((TextView) rootView.findViewById(C2358R.C2357id.customer_support_link)).setOnClickListener(this.mOnClickCustomerSupport);
        configure(rootView);
        return rootView;
    }

    private void configure(View view) {
        Ensighten.evaluateEvent(this, "configure", new Object[]{view});
        this.mValidations = new SparseArray();
        setMailValidation(view);
        addValidation(this.mPasswordEditText, 4);
    }

    private ValidationListener addValidation(EditText field, int type) {
        boolean z = false;
        Ensighten.evaluateEvent(this, "addValidation", new Object[]{field, new Integer(type)});
        if (type != 4) {
            z = true;
        }
        return addValidation(new ValidationListener(field, type, z, true));
    }

    private ValidationListener addValidation(ValidationListener validation) {
        Ensighten.evaluateEvent(this, "addValidation", new Object[]{validation});
        validation.getTextField().addTextChangedListener(validation);
        validation.getTextField().setOnFocusChangeListener(this.mOnFocusChangeListener);
        validation.getTextField().setOnEditorActionListener(onDoneKeyPressed(validation));
        validation.setValidationCallback(this.mValidationCallback);
        this.mValidations.put(validation.getTextField().getId(), validation);
        return validation;
    }

    private void setMailValidation(View view) {
        Ensighten.evaluateEvent(this, "setMailValidation", new Object[]{view});
        TextView mailErrorDisplay = (TextView) view.findViewById(C2358R.C2357id.email_error);
        String mailErrorMessage = getString(C2658R.string.error_check_email_format);
        String mailEmptyMessage = getString(C2658R.string.error_empty_mail);
        ValidationListener mailValidation = addValidation(this.mEmailEditText, 0);
        mailValidation.setErrorDisplay(mailErrorDisplay);
        mailValidation.setErrorMessage(mailErrorMessage);
        mailValidation.setEmptyMessage(mailEmptyMessage);
    }

    @NonNull
    private OnEditorActionListener onDoneKeyPressed(final ValidationListener validation) {
        Ensighten.evaluateEvent(this, "onDoneKeyPressed", new Object[]{validation});
        return new OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Ensighten.evaluateEvent(this, "onEditorAction", new Object[]{v, new Integer(actionId), event});
                if (actionId != 6) {
                    return false;
                }
                if (validation.isValidated()) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSignInFragment", "access$400", new Object[]{LiteSignInFragment.this}).callOnClick();
                    return true;
                }
                validation.displayError();
                return true;
            }
        };
    }

    private void validateData() {
        Ensighten.evaluateEvent(this, "validateData", null);
        int i = 0;
        while (i < this.mValidations.size()) {
            if (((ValidationListener) this.mValidations.get(this.mValidations.keyAt(i))).isValidated()) {
                i++;
            } else {
                return;
            }
        }
        this.mSubmitButton.setEnabled(true);
    }

    public void onStart() {
        super.onStart();
        if (this.autoLoginUser != null && this.autoLoginPass != null) {
            this.mEmailEditText.setText(this.autoLoginUser);
            this.mPasswordEditText.setText(this.autoLoginPass);
        }
    }

    public void onResume() {
        super.onResume();
        if (LoginManager.getInstance().getProfile() != null) {
            String username = LocalDataManager.getSharedInstance().getPrefSavedLogin();
            if (!TextUtils.isEmpty(username)) {
                this.mEmailEditText.setText(username);
            }
        }
    }

    public void onPause() {
        super.onPause();
        UIUtils.stopActivityIndicator();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mSignInNotificationReceiver != null) {
            NotificationCenter.getSharedInstance().removeObserver(this.mSignInNotificationReceiver);
        }
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.lite_title_sign_in);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_login);
    }

    public void onSocialNetworkAvailable() {
        Ensighten.evaluateEvent(this, "onSocialNetworkAvailable", null);
        this.mSocialContainer.setAlpha(0.0f);
        this.mSocialContainer.setVisibility(0);
        this.mSocialContainer.animate().alpha(1.0f).setDuration(150).start();
    }

    public void onSocialNetworkSelected(SocialNetwork socialNetwork) {
        Ensighten.evaluateEvent(this, "onSocialNetworkSelected", new Object[]{socialNetwork});
        super.onSocialNetworkSelected(socialNetwork);
        this.mSocialLoginId = socialNetwork.getSocialNetworkID();
    }

    public void onSocialNetworkAuthenticationComplete(SocialLoginAuthenticationResults results) {
        Ensighten.evaluateEvent(this, "onSocialNetworkAuthenticationComplete", new Object[]{results});
        if (results.getEmailAddress() == null) {
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setMessage(getResources().getString(C2658R.string.validate_social_network)).setPositiveButton((int) C2658R.string.f6083ok, null).create().show();
        } else {
            onSubmitDoSignin(results);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void persistProfile(CustomerProfile profile) {
        Ensighten.evaluateEvent(this, "persistProfile", new Object[]{profile});
        LocalDataManager dataManager = LocalDataManager.getSharedInstance();
        dataManager.setPrefSavedLogin(profile.getUserName());
        if (profile.isUsingSocialLogin()) {
            dataManager.setPrefSavedSocialNetworkId(profile.getSocialServiceAuthenticationID());
            return;
        }
        dataManager.setPrefSavedLogin(profile.getEmailAddress());
        dataManager.setPrefSavedLoginPass(profile.getPassword());
    }

    private void onSubmitDoSignin(SocialLoginAuthenticationResults socialLoginInfo) {
        boolean useSocialLogin = false;
        Ensighten.evaluateEvent(this, "onSubmitDoSignin", new Object[]{socialLoginInfo});
        if (socialLoginInfo != null) {
            useSocialLogin = true;
        }
        if (!useSocialLogin && (TextUtils.isEmpty(this.mEmailEditText.getText().toString().trim()) || TextUtils.isEmpty(this.mPasswordEditText.getText().toString().trim()))) {
            return;
        }
        if (AppUtils.isNetworkConnected(this.mParent)) {
            UIUtils.startActivityIndicator(getActivity(), this.mParent.getString(C2658R.string.lite_dialog_auth));
            final AuthenticationParameters parameters = new AuthenticationParameters();
            if (useSocialLogin) {
                parameters.setUserName(socialLoginInfo.getEmailAddress());
                parameters.setUsingSocialLogin(true);
                parameters.setSocialServiceID(this.mSocialLoginId);
                parameters.setFirstName(socialLoginInfo.getFirstName());
                parameters.setLastName(socialLoginInfo.getLastName());
                parameters.setEmailAddress(socialLoginInfo.getEmailAddress());
                parameters.setSocialAuthenticationToken(socialLoginInfo.getAccessToken());
                parameters.setSocialUserID(socialLoginInfo.getUserId());
            } else {
                parameters.setUserName(this.mEmailEditText.getText().toString().trim());
                parameters.setPassword(this.mPasswordEditText.getText().toString().trim());
            }
            final CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            customerModule.authenticate(parameters, new AsyncListener<CustomerProfile>() {
                public void onResponse(final CustomerProfile profile, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{profile, token, exception});
                    UIUtils.stopActivityIndicator();
                    if (exception != null || LiteSignInFragment.this.getNavigationActivity() == null) {
                        AsyncException.report(exception);
                        LiteSignInFragment.access$1408(LiteSignInFragment.this);
                    } else if (parameters.isUsingSocialLogin()) {
                        profile.setUsingSocialLogin(true);
                        profile.setSocialServiceAuthenticationID(parameters.getSocialServiceID());
                        profile.setSocialAuthenticationToken(parameters.getSocialAuthenticationToken());
                        profile.setSocialUserID(parameters.getSocialUserID());
                    } else if (profile.isPasswordChangeRequired()) {
                        LiteSignInFragment.access$1000(LiteSignInFragment.this);
                    } else if (profile.shouldUpdateTermsAndCondition() || profile.shouldUpdatePrivacyPolicy()) {
                        LiteSignInFragment.access$1100(LiteSignInFragment.this, profile, customerModule);
                    } else if (profile.getVerificationType() == AccountVerificationType.NONE || profile.isAccountVerified()) {
                        LiteSignInFragment.access$1100(LiteSignInFragment.this, profile, customerModule);
                    } else {
                        DialogInterface.OnClickListener mOnClickVerifyAccount = new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                                AnalyticsUtils.trackOnClickEvent(LiteSignInFragment.this.getAnalyticsTitle(), "Verify Account");
                                switch (C318215.f6657x8d1a63aa[profile.getVerificationType().ordinal()]) {
                                    case 1:
                                        LiteSignInFragment.access$1200(LiteSignInFragment.this, customerModule);
                                        return;
                                    case 2:
                                        LiteSignInFragment.access$1300(LiteSignInFragment.this, customerModule);
                                        return;
                                    default:
                                        return;
                                }
                            }
                        };
                        DialogInterface.OnClickListener mOnClickCancel = new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                                AnalyticsUtils.trackOnClickEvent(LiteSignInFragment.this.getAnalyticsTitle(), "Cancel");
                                if (LiteSignInFragment.this.getNavigationActivity().getIntent().getBooleanExtra("shouldGoToPreviousView", false)) {
                                    LiteSignInFragment.this.getNavigationActivity().finish();
                                } else {
                                    LiteSignInFragment.access$1100(LiteSignInFragment.this, profile, customerModule);
                                }
                            }
                        };
                        if (LiteSignInFragment.this.getNavigationActivity().getIntent().getBooleanExtra("shouldGoToPreviousView", false)) {
                            LiteSignInFragment.this.getNavigationActivity().finish();
                        } else if (LoginManager.getInstance().getProfile().getVerificationType() != AccountVerificationType.NONE) {
                            MCDAlertDialogBuilder.withContext(LiteSignInFragment.this.getActivity()).setTitle(LiteSignInFragment.this.getString(C2658R.string.lite_alrt_acct_not_verif)).setMessage(LiteSignInFragment.this.getString(C2658R.string.lite_alrt_msg_acct_not_verif)).setPositiveButton(LiteSignInFragment.this.getString(C2658R.string.button_verify_account), mOnClickVerifyAccount).setNegativeButton(LiteSignInFragment.this.getString(C2658R.string.button_cancel), mOnClickCancel).create().show();
                        }
                    }
                }
            });
            return;
        }
        UIUtils.showNoNetworkAlert(getNavigationActivity());
    }

    private void finishLogin(CustomerProfile profile, CustomerModule customerModule) {
        Ensighten.evaluateEvent(this, "finishLogin", new Object[]{profile, customerModule});
        persistProfile(profile);
        if (profile.shouldUpdateTermsAndCondition() || profile.shouldUpdatePrivacyPolicy()) {
            this.mResultFragmentName = "litepolicyupdates";
            this.mResultContainerClass = LitePolicyUpdatesActivity.class;
        } else {
            this.mResultFragmentName = "dashboard";
            this.mResultContainerClass = MainActivity.class;
        }
        getNavigationActivity().setResult(-1);
        if (getNavigationActivity() instanceof MainActivity) {
            getNavigationActivity().showFragment(this.mResultFragmentName);
        } else {
            getNavigationActivity().finish();
        }
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

    private void startSmsVerification(CustomerModule customerModule) {
        Ensighten.evaluateEvent(this, "startSmsVerification", new Object[]{customerModule});
        customerModule.sendSMSCode(LoginManager.getInstance().getProfile(), new C318013());
    }

    private void startEmailVerification(CustomerModule customerModule) {
        Ensighten.evaluateEvent(this, "startEmailVerification", new Object[]{customerModule});
        customerModule.resendActivation(customerModule.getCurrentProfile(), new C318114());
    }
}
