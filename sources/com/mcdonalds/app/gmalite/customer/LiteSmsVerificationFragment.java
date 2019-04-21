package com.mcdonalds.app.gmalite.customer;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.web.WebActivity;
import com.mcdonalds.app.widget.ValidationListener;
import com.mcdonalds.app.widget.ValidationListener.Callback;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;

public class LiteSmsVerificationFragment extends URLNavigationFragment {
    private OnClickListener mOnClickCustomerSupport = new C32161();
    private OnClickListener mOnClickResendSMSLink = new C32194();
    private OnClickListener mOnClickSubmitSMSCodeButton = new C32183();
    protected URLNavigationActivity mParent;
    private TextView mResendSMSLink;
    private EditText mSMSCodeInput;
    private Button mSubmitSMSCodeButton;
    private TextView mUserPhoneNumber;
    private Callback mValidationCallback = new C32172();
    private TextView mWelcomeTextView;

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSmsVerificationFragment$1 */
    class C32161 implements OnClickListener {
        C32161() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(LiteSmsVerificationFragment.this.getAnalyticsTitle(), "Customer Support");
            String link = ConfigurationUtils.getCustomerSupportUrl();
            if (link != null) {
                Bundle attributes = new Bundle();
                attributes.putString("link", link);
                attributes.putString("analytics_title", LiteSmsVerificationFragment.this.getString(C2658R.string.analytics_screen_customer_support));
                LiteSmsVerificationFragment.this.getNavigationActivity().startActivity(WebActivity.class, "web", attributes);
                return;
            }
            AsyncException.report("No Customer Support URL Defined");
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSmsVerificationFragment$2 */
    class C32172 implements Callback {
        C32172() {
        }

        public void onFieldValidationStateChanged(boolean isValidated) {
            Ensighten.evaluateEvent(this, "onFieldValidationStateChanged", new Object[]{new Boolean(isValidated)});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSmsVerificationFragment", "access$000", new Object[]{LiteSmsVerificationFragment.this}).setEnabled(isValidated);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSmsVerificationFragment$3 */
    class C32183 implements OnClickListener {
        C32183() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            String smsCode = Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSmsVerificationFragment", "access$100", new Object[]{LiteSmsVerificationFragment.this}).getText().toString();
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSmsVerificationFragment", "access$100", new Object[]{LiteSmsVerificationFragment.this}).setText("");
            AnalyticsUtils.trackOnClickEvent(LiteSmsVerificationFragment.this.getAnalyticsTitle(), "Continue");
            LoginManager.getInstance().verifySMSCode(smsCode, LiteSmsVerificationFragment.this.getNavigationActivity());
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSmsVerificationFragment$4 */
    class C32194 implements OnClickListener {
        C32194() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteSmsVerificationFragment", "access$100", new Object[]{LiteSmsVerificationFragment.this}).setText("");
            AnalyticsUtils.trackOnClickEvent(LiteSmsVerificationFragment.this.getAnalyticsTitle(), "SMS Resend");
            LoginManager.getInstance().resendSMSCode(LiteSmsVerificationFragment.this.getNavigationActivity());
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteSmsVerificationFragment$5 */
    class C32205 implements OnEditorActionListener {
        final /* synthetic */ ValidationListener val$validation;

        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            Ensighten.evaluateEvent(this, "onEditorAction", new Object[]{v, new Integer(actionId), event});
            if (actionId != 6) {
                return false;
            }
            if (this.val$validation.isValidated()) {
                return true;
            }
            this.val$validation.displayError();
            return true;
        }
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_account_verification);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mParent = getNavigationActivity();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_sms_verification, container, false);
        if (view == null) {
            throw new RuntimeException("LiteSmsVerificationFragment super.onCreateView is null");
        }
        this.mWelcomeTextView = (TextView) view.findViewById(C2358R.C2357id.sms_verification_welcome_name);
        this.mWelcomeTextView.setText(getString(C2658R.string.lite_label_welcome_android, LoginManager.getInstance().getProfile().getFirstName()));
        this.mUserPhoneNumber = (TextView) view.findViewById(C2358R.C2357id.sms_verification_phone_number);
        this.mSMSCodeInput = (EditText) view.findViewById(C2358R.C2357id.sms_verification_enter_phone_number_field);
        ValidationListener validation = new ValidationListener(this.mSMSCodeInput, 6, true, true);
        validation.setValidationCallback(this.mValidationCallback);
        this.mSMSCodeInput.addTextChangedListener(validation);
        this.mSubmitSMSCodeButton = (Button) view.findViewById(C2358R.C2357id.sms_verification_enter_code_button);
        this.mSubmitSMSCodeButton.setOnClickListener(this.mOnClickSubmitSMSCodeButton);
        this.mResendSMSLink = (TextView) view.findViewById(C2358R.C2357id.sms_verification_resend_button);
        this.mResendSMSLink.setOnClickListener(this.mOnClickResendSMSLink);
        ((TextView) view.findViewById(C2358R.C2357id.customer_support_link)).setOnClickListener(this.mOnClickCustomerSupport);
        return view;
    }

    public void onResume() {
        super.onResume();
        CustomerProfile profile = LoginManager.getInstance().getProfile();
        if (profile != null) {
            String welcomeText = getResources().getString(C2658R.string.sms_verification_welcome_name_text);
            this.mWelcomeTextView.setText(String.format(welcomeText, new Object[]{profile.getFirstName()}));
            this.mUserPhoneNumber.setText(profile.getMobileNumber());
        }
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getResources().getString(C2658R.string.lite_title_acct_verif);
    }

    public void onStart() {
        super.onStart();
        ModuleManager.getModule(CustomerModule.NAME);
    }
}
