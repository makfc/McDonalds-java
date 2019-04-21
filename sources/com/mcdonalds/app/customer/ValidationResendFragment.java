package com.mcdonalds.app.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.widget.ValidationListener;
import com.mcdonalds.app.widget.ValidationListener.Callback;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;

public class ValidationResendFragment extends URLNavigationFragment implements Callback {
    private View mContent;
    private CustomerModule mCustomerModule;
    private final AsyncListener<CustomerProfile> mCustomerUpdated = new C30952();
    private boolean mDoNotChangeContact;
    private boolean mIsMobile;
    private final OnClickListener mOnClickResend = new C30963();
    private View mProgress;
    private View mResendButton;
    private final AsyncListener<Void> mResendListener = new C30941();
    private ValidationListener mValidation;
    private EditText mValidationInput;

    /* renamed from: com.mcdonalds.app.customer.ValidationResendFragment$1 */
    class C30941 implements AsyncListener<Void> {
        C30941() {
        }

        public void onResponse(Void response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            ValidationResendFragment.access$000(ValidationResendFragment.this);
            if (exception != null) {
                AsyncException.report(exception);
            } else if (ValidationResendFragment.this.getNavigationActivity() != null) {
                ValidationResendFragment.this.showFragment("mail_validation");
            }
        }
    }

    /* renamed from: com.mcdonalds.app.customer.ValidationResendFragment$2 */
    class C30952 implements AsyncListener<CustomerProfile> {
        C30952() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            ValidationResendFragment.access$000(ValidationResendFragment.this);
            if (exception != null) {
                AsyncException.report(exception);
            } else if (response != null) {
                LoginManager.getInstance().setProfile(response);
                ValidationResendFragment.access$100(ValidationResendFragment.this);
            } else {
                AsyncException.report(new AsyncException(ValidationResendFragment.this.getString(C2658R.string.error_couldnt_update_email)));
            }
        }
    }

    /* renamed from: com.mcdonalds.app.customer.ValidationResendFragment$3 */
    class C30963 implements OnClickListener {
        C30963() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.ValidationResendFragment", "access$200", new Object[]{ValidationResendFragment.this}).isValidated()) {
                ValidationResendFragment.access$100(ValidationResendFragment.this);
                return;
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.ValidationResendFragment", "access$200", new Object[]{ValidationResendFragment.this}).displayError();
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.ValidationResendFragment", "access$300", new Object[]{ValidationResendFragment.this}).requestFocus();
        }
    }

    static /* synthetic */ void access$000(ValidationResendFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.ValidationResendFragment", "access$000", new Object[]{x0});
        x0.hideProgress();
    }

    static /* synthetic */ void access$100(ValidationResendFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.ValidationResendFragment", "access$100", new Object[]{x0});
        x0.resend();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mIsMobile = arguments.getInt("validation_method") == 2;
            this.mDoNotChangeContact = arguments.getBoolean("do_not_change_contact");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(this.mIsMobile ? C2658R.layout.fragment_resend_mobile_activation : C2658R.layout.fragment_resend_mail_activation, container, false);
        this.mValidationInput = (EditText) view.findViewById(C2358R.C2357id.validation_input);
        this.mResendButton = view.findViewById(C2358R.C2357id.button_resend);
        this.mResendButton.setEnabled(false);
        this.mResendButton.setOnClickListener(this.mOnClickResend);
        this.mContent = view.findViewById(C2358R.C2357id.content_container);
        this.mProgress = view.findViewById(C2358R.C2357id.login_progress);
        if (this.mDoNotChangeContact) {
            this.mValidationInput.setEnabled(false);
        }
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mValidation = new ValidationListener(this.mValidationInput, this.mIsMobile ? 5 : 0, true, true);
        this.mValidation.setValidationCallback(this);
        this.mValidationInput.addTextChangedListener(this.mValidation);
        CustomerProfile profile = LoginManager.getInstance().getProfile();
        if (profile != null) {
            this.mValidationInput.setText(this.mIsMobile ? profile.getMobileNumber() : profile.getEmailAddress());
        }
        if (this.mValidation.isValidated()) {
            this.mResendButton.setEnabled(true);
        }
    }

    public void onFieldValidationStateChanged(boolean isValidated) {
        Ensighten.evaluateEvent(this, "onFieldValidationStateChanged", new Object[]{new Boolean(isValidated)});
        this.mResendButton.setEnabled(isValidated);
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

    private void resend() {
        Ensighten.evaluateEvent(this, "resend", null);
        CustomerProfile profile = LoginManager.getInstance().getProfile();
        if (profile != null) {
            showProgress();
            if (this.mIsMobile) {
                updateMobile(profile);
            } else {
                updateEmail(profile);
            }
        }
    }

    private void updateEmail(CustomerProfile profile) {
        Ensighten.evaluateEvent(this, "updateEmail", new Object[]{profile});
        String email = this.mValidationInput.getText().toString().trim();
        if (profile.getEmailAddress().equals(email)) {
            profile.setActivationOption(1);
            this.mCustomerModule.resendActivation(profile, this.mResendListener);
            return;
        }
        profile.setEmailAddress(email);
        profile.setNewUserName(email);
        this.mCustomerModule.updateCustomerProfile(profile, this.mCustomerUpdated);
    }

    private void updateMobile(CustomerProfile profile) {
        Ensighten.evaluateEvent(this, "updateMobile", new Object[]{profile});
        String mobile = this.mValidationInput.getText().toString().trim();
        if (profile.getMobileNumber().equals(mobile)) {
            this.mCustomerModule.resendActivation(profile, this.mResendListener);
            return;
        }
        profile.setMobileNumber(mobile);
        if (!profile.isUsingSocialLoginWithoutEmail()) {
            profile.setNewUserName(mobile);
        }
        this.mCustomerModule.updateCustomerProfile(profile, this.mCustomerUpdated);
    }
}
