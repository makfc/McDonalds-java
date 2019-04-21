package com.mcdonalds.app.customer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.customer.push.PushNotificationRequestActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.LoginManager;
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
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.analytics.conversionmaster.RegisterDoneAction;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.newrelic.agent.android.harvest.type.HarvestErrorCodes;
import java.util.HashMap;

public class UserValidationFragment extends URLNavigationFragment {
    private EditText mCode;
    private boolean mCodeValidated;
    private View mContent;
    private final OnClickListener mOnClickDone = new C30894();
    private final OnClickListener mOnClickLater = new C30873();
    private final OnClickListener mOnClickResend = new C30862();
    private View mProgress;
    private int mValidationMethod;
    private CustomerProfile profile;

    /* renamed from: com.mcdonalds.app.customer.UserValidationFragment$2 */
    class C30862 implements OnClickListener {

        /* renamed from: com.mcdonalds.app.customer.UserValidationFragment$2$1 */
        class C30851 implements AsyncListener<Void> {
            C30851() {
            }

            public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                UserValidationFragment.access$400(UserValidationFragment.this);
            }
        }

        C30862() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            CustomerProfile profile = LoginManager.getInstance().getProfile();
            CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            UserValidationFragment.access$300(UserValidationFragment.this);
            customerModule.resendActivation(profile, new C30851());
        }
    }

    /* renamed from: com.mcdonalds.app.customer.UserValidationFragment$3 */
    class C30873 implements OnClickListener {
        C30873() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            URLNavigationActivity activity = UserValidationFragment.this.getNavigationActivity();
            if (activity != null) {
                Bundle bundle = new Bundle();
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$500", new Object[]{UserValidationFragment.this}) != 2) {
                    PreferenceManager.getDefaultSharedPreferences(activity).edit().putBoolean("email_verification_alert", false).apply();
                    bundle.putBoolean("EXTRA_REFRESH_DASHBOARD", true);
                }
                UserValidationFragment.this.startActivity(MainActivity.class, bundle);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.customer.UserValidationFragment$4 */
    class C30894 implements OnClickListener {
        C30894() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (Configuration.getSharedInstance().getBooleanForKey("interface.register.useActivationCodeForAccountVerification")) {
                AuthenticationParameters parameters;
                String code = Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$600", new Object[]{UserValidationFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$200", new Object[]{UserValidationFragment.this})});
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$700", new Object[]{UserValidationFragment.this}) == null) {
                    parameters = LoginManager.getInstance().getAuthenticationParameters();
                } else {
                    parameters = new AuthenticationParameters();
                    parameters.setUserName(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$700", new Object[]{UserValidationFragment.this}).getUserName());
                    parameters.setEmailAddress(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$700", new Object[]{UserValidationFragment.this}).getEmailAddress());
                    parameters.setPassword(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$700", new Object[]{UserValidationFragment.this}).getPassword());
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$700", new Object[]{UserValidationFragment.this}).isUsingSocialLogin()) {
                        parameters.setUsingSocialLogin(true);
                        parameters.setAllowSocialLoginWithoutEmail(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$700", new Object[]{UserValidationFragment.this}).isUsingSocialLoginWithoutEmail());
                        parameters.setSocialServiceID(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$700", new Object[]{UserValidationFragment.this}).getSocialServiceAuthenticationID());
                        parameters.setSocialAuthenticationToken(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$700", new Object[]{UserValidationFragment.this}).getSocialAuthenticationToken());
                        parameters.setSocialUserID(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$700", new Object[]{UserValidationFragment.this}).getSocialUserID());
                    }
                }
                if (code.isEmpty()) {
                    UserValidationFragment.this.doSignIn(parameters);
                } else {
                    ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).resendActivationCode(code, new AsyncListener<Void>() {
                        public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                            if (exception == null) {
                                UserValidationFragment.this.doSignIn(parameters);
                            } else if (!(exception instanceof MWException)) {
                                AsyncException.report(exception);
                            } else if (exception.getErrorCode() == -2111 || exception.getErrorCode() == HarvestErrorCodes.NSURLErrorBadServerResponse) {
                                UserValidationFragment.access$800(UserValidationFragment.this, parameters, UserValidationFragment.this.getString(C2658R.string.activation_code_invalid_error));
                            } else if (exception.getErrorCode() == -2203) {
                                UserValidationFragment.this.doSignIn(parameters);
                            } else {
                                AsyncException.report(exception);
                            }
                        }
                    });
                }
            } else {
                UserValidationFragment.this.doSignIn(LoginManager.getInstance().getAuthenticationParameters());
            }
            HashMap<String, Object> jiceMap = new HashMap();
            jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_REGISTER);
            jiceMap.put(JiceArgs.LABEL_ACTIVATE_DONE_KEY, JiceArgs.LABEL_ACTIVATE_DONE_VAL);
            Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).setConversionMaster(new RegisterDoneAction()).build());
        }
    }

    static /* synthetic */ boolean access$002(UserValidationFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$002", new Object[]{x0, new Boolean(x1)});
        x0.mCodeValidated = x1;
        return x1;
    }

    static /* synthetic */ void access$300(UserValidationFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$300", new Object[]{x0});
        x0.showProgress();
    }

    static /* synthetic */ void access$400(UserValidationFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$400", new Object[]{x0});
        x0.hideProgress();
    }

    static /* synthetic */ void access$800(UserValidationFragment x0, AuthenticationParameters x1, String x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$800", new Object[]{x0, x1, x2});
        x0.trySignIn(x1, x2);
    }

    static /* synthetic */ void access$900(UserValidationFragment x0, CustomerProfile x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$900", new Object[]{x0, x1});
        x0.checkForOneLastThing(x1);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mValidationMethod = arguments.getInt("validation_method");
            if (arguments.getBoolean("social_login_without_email")) {
                this.mValidationMethod = 2;
            }
            this.profile = (CustomerProfile) arguments.getParcelable("EXTRA_USER_PROFILR");
        }
        HashMap<String, Object> jiceMap = new HashMap();
        jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_REGISTER);
        jiceMap.put(JiceArgs.LABEL_ACTIVATE_KEY, JiceArgs.LABEL_ACTIVATE_VAL);
        Analytics.track(AnalyticType.Event, new ArgBuilder().setJice(jiceMap).build());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_user_validation, container, false);
        this.mCode = (EditText) view.findViewById(C2358R.C2357id.activationCode);
        view.findViewById(C2358R.C2357id.button_resend).setOnClickListener(this.mOnClickResend);
        view.findViewById(C2358R.C2357id.button_later).setOnClickListener(this.mOnClickLater);
        final Button done = (Button) view.findViewById(C2358R.C2357id.button_done);
        done.setOnClickListener(this.mOnClickDone);
        TextView instructions = (TextView) view.findViewById(C2358R.C2357id.instructions);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.register.useActivationCodeForAccountVerification")) {
            instructions.setText(C2658R.string.instructions_activation_code_phone_text);
            this.mCode.setVisibility(0);
            done.setText(C2658R.string.button_text_submit);
            this.mCode.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{s, new Integer(start), new Integer(count), new Integer(after)});
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{s, new Integer(start), new Integer(before), new Integer(count)});
                    UserValidationFragment.access$002(UserValidationFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$100", new Object[]{UserValidationFragment.this, s}));
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$000", new Object[]{UserValidationFragment.this})) {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$200", new Object[]{UserValidationFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_valid, 0);
                    } else {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$200", new Object[]{UserValidationFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
                    }
                }

                public void afterTextChanged(Editable s) {
                    Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{s});
                    done.setEnabled(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$000", new Object[]{UserValidationFragment.this}));
                }
            });
        }
        instructions.setText(this.mValidationMethod != 2 ? C2658R.string.text_validation_link_sent : C2658R.string.text_validation_sms_sent);
        this.mContent = view.findViewById(C2358R.C2357id.content_container);
        this.mProgress = view.findViewById(C2358R.C2357id.login_progress);
        return view;
    }

    private boolean validate(CharSequence cs) {
        Ensighten.evaluateEvent(this, "validate", new Object[]{cs});
        String text = cs.toString();
        boolean isCode = text.matches("[\\d\\w]{6}");
        this.mCode.setFilters(new InputFilter[]{new LengthFilter(6)});
        if (text.isEmpty() || isCode) {
            return true;
        }
        return false;
    }

    private void showProgress() {
        Ensighten.evaluateEvent(this, "showProgress", null);
        this.mContent.setVisibility(8);
        this.mProgress.setVisibility(0);
    }

    private void hideProgress() {
        Ensighten.evaluateEvent(this, "hideProgress", null);
        this.mContent.setVisibility(0);
        this.mProgress.setVisibility(8);
    }

    private String getTrimmedText(EditText field) {
        Ensighten.evaluateEvent(this, "getTrimmedText", new Object[]{field});
        if (field.getText() != null) {
            return field.getText().toString().trim();
        }
        return "";
    }

    private void trySignIn(final AuthenticationParameters parameters, final String msg) {
        Ensighten.evaluateEvent(this, "trySignIn", new Object[]{parameters, msg});
        UIUtils.startActivityIndicator(getActivity(), getActivity().getString(C2658R.string.dialog_signing_in));
        ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).authenticate(parameters, new AsyncListener<CustomerProfile>() {
            public void onResponse(CustomerProfile profile, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{profile, token, exception});
                UIUtils.stopActivityIndicator();
                if (exception == null) {
                    if (parameters.isUsingSocialLogin()) {
                        profile.setUsingSocialLogin(true);
                        profile.setSocialServiceAuthenticationID(parameters.getSocialServiceID());
                        profile.setSocialUserID(parameters.getSocialUserID());
                    }
                    if (profile.isEmailActivated()) {
                        LoginManager.getInstance().setProfile(profile);
                        LoginManager.getInstance().persistProfile();
                        UserValidationFragment.access$900(UserValidationFragment.this, profile);
                        return;
                    }
                    MCDAlertDialogBuilder.withContext(UserValidationFragment.this.getNavigationActivity()).setNeutralButton((int) C2658R.string.f6083ok, null).setMessage(msg).create().show();
                    return;
                }
                AsyncException.report(exception);
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void doSignIn(final AuthenticationParameters parameters) {
        Ensighten.evaluateEvent(this, "doSignIn", new Object[]{parameters});
        UIUtils.startActivityIndicator(getActivity(), getActivity().getString(C2658R.string.dialog_signing_in));
        final CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        customerModule.authenticate(parameters, new AsyncListener<CustomerProfile>() {
            public void onResponse(final CustomerProfile profile, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{profile, token, exception});
                UIUtils.stopActivityIndicator();
                if (exception == null) {
                    if (parameters.isUsingSocialLogin()) {
                        profile.setUsingSocialLogin(true);
                        profile.setSocialServiceAuthenticationID(parameters.getSocialServiceID());
                        profile.setSocialUserID(parameters.getSocialUserID());
                    }
                    if (profile.isEmailActivated()) {
                        LoginManager.getInstance().setProfile(profile);
                        LoginManager.getInstance().persistProfile();
                        UserValidationFragment.access$900(UserValidationFragment.this, profile);
                        return;
                    }
                    DialogInterface.OnClickListener onClick = new DialogInterface.OnClickListener() {

                        /* renamed from: com.mcdonalds.app.customer.UserValidationFragment$6$1$1 */
                        class C30911 implements AsyncListener<Void> {
                            C30911() {
                            }

                            public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                            }
                        }

                        public void onClick(DialogInterface dialog, int which) {
                            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                            AnalyticsUtils.trackOnClickEvent(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.UserValidationFragment", "access$1000", new Object[]{UserValidationFragment.this}), "Resend Activation Email");
                            customerModule.resendActivation(profile, new C30911());
                            if (Configuration.getSharedInstance().getBooleanForKey("interface.useMobileNumberRegisterOnly") || profile.isUsingSocialLoginWithoutEmail()) {
                                MCDAlertDialogBuilder.withContext(UserValidationFragment.this.getNavigationActivity()).setCancelable(false).setNeutralButton((int) C2658R.string.f6083ok, null).setTitle(UserValidationFragment.this.getString(C2658R.string.resend_sms_title)).setMessage((int) C2658R.string.activation_sms_sent).create().show();
                            } else {
                                MCDAlertDialogBuilder.withContext(UserValidationFragment.this.getNavigationActivity()).setCancelable(false).setNeutralButton((int) C2658R.string.f6083ok, null).setTitle(UserValidationFragment.this.getString(C2658R.string.resend_email_title)).setMessage((int) C2658R.string.activation_email_sent).create().show();
                            }
                        }
                    };
                    if (Configuration.getSharedInstance().getBooleanForKey("interface.useMobileNumberRegisterOnly") || profile.isUsingSocialLoginWithoutEmail()) {
                        MCDAlertDialogBuilder.withContext(UserValidationFragment.this.getActivity()).setTitle(UserValidationFragment.this.getString(C2658R.string.resend_sms_title)).setMessage(UserValidationFragment.this.getString(C2658R.string.resend_sms_message)).setPositiveButton(UserValidationFragment.this.getString(C2658R.string.button_resend), onClick).setNegativeButton(UserValidationFragment.this.getString(C2658R.string.button_cancel), null).create().show();
                        return;
                    } else {
                        MCDAlertDialogBuilder.withContext(UserValidationFragment.this.getActivity()).setTitle(UserValidationFragment.this.getString(C2658R.string.resend_email_title)).setMessage(UserValidationFragment.this.getString(C2658R.string.resend_email_message)).setPositiveButton(UserValidationFragment.this.getString(C2658R.string.button_resend), onClick).setNegativeButton(UserValidationFragment.this.getString(C2658R.string.button_cancel), null).create().show();
                        return;
                    }
                }
                AsyncException.report(exception);
            }
        });
    }

    private void checkForOneLastThing(CustomerProfile profile) {
        Ensighten.evaluateEvent(this, "checkForOneLastThing", new Object[]{profile});
        Bundle arguments = new Bundle();
        arguments.putSerializable("ARG_CUSTOMER_PROFILE", profile);
        if (((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).isFirstTimeLoginOnDevice(profile)) {
            startPushNotificationRequestActivity(getNavigationActivity(), arguments);
        } else {
            startMainActivity(getNavigationActivity());
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
}
