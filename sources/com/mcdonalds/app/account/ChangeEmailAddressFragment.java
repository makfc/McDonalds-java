package com.mcdonalds.app.account;

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
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.widget.ValidationListener;
import com.mcdonalds.app.widget.ValidationListener.Callback;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.Observable;
import java.util.Observer;

public class ChangeEmailAddressFragment extends URLNavigationFragment implements Callback, Observer {
    public static String NAME = "change_email";
    private CustomerProfile mCustomerProfile;
    private EditText mEmailAddress;
    private View mEmailSendedView;
    private View mNotVerifiedWarning;
    private OnClickListener mOnClickOkButton = new C29281();
    private final OnClickListener mOnClickVerify = new C29292();
    private ValidationListener mValidation;
    private View mVerifyEmailButton;

    /* renamed from: com.mcdonalds.app.account.ChangeEmailAddressFragment$1 */
    class C29281 implements OnClickListener {
        C29281() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeEmailAddressFragment", "access$000", new Object[]{ChangeEmailAddressFragment.this}).setVisibility(8);
        }
    }

    /* renamed from: com.mcdonalds.app.account.ChangeEmailAddressFragment$2 */
    class C29292 implements OnClickListener {
        C29292() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            ChangeEmailAddressFragment.access$100(ChangeEmailAddressFragment.this);
        }
    }

    static /* synthetic */ void access$100(ChangeEmailAddressFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeEmailAddressFragment", "access$100", new Object[]{x0});
        x0.verify();
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.title_change_email_fragment);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginManager.getInstance().addObserver(this);
        this.mCustomerProfile = LoginManager.getInstance().getProfile();
    }

    public void onDestroy() {
        super.onDestroy();
        LoginManager.getInstance().deleteObserver(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C2658R.layout.fragment_change_email, container, false);
        this.mEmailAddress = (EditText) v.findViewById(2131821086);
        this.mNotVerifiedWarning = v.findViewById(C2358R.C2357id.warning_no_mail_verified);
        this.mVerifyEmailButton = v.findViewById(C2358R.C2357id.button_verify_email);
        this.mVerifyEmailButton.setOnClickListener(this.mOnClickVerify);
        this.mEmailSendedView = v.findViewById(C2358R.C2357id.email_sended_view);
        v.findViewById(C2358R.C2357id.ok_button).setOnClickListener(this.mOnClickOkButton);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.disableInteraction")) {
            UIUtils.disableInteraction(this.mEmailAddress);
        }
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        boolean verified;
        super.onActivityCreated(savedInstanceState);
        this.mValidation = new ValidationListener(this.mEmailAddress, 0, true, true);
        this.mEmailAddress.addTextChangedListener(this.mValidation);
        this.mEmailAddress.setText(this.mCustomerProfile.getEmailAddress());
        this.mValidation.setValidationCallback(this);
        if (this.mCustomerProfile.getCustomerLoginInfo() != null && this.mCustomerProfile.getCustomerLoginInfo().isEmailAddressVerified() && this.mValidation.isValidated()) {
            verified = true;
        } else {
            verified = false;
        }
        if (verified) {
            this.mVerifyEmailButton.setEnabled(false);
            this.mNotVerifiedWarning.setVisibility(8);
            return;
        }
        this.mVerifyEmailButton.setEnabled(true);
        this.mNotVerifiedWarning.setVisibility(0);
    }

    public void update(Observable observable, Object data) {
        Ensighten.evaluateEvent(this, "update", new Object[]{observable, data});
        int code = ((Integer) data).intValue();
        if (code == 3002) {
            this.mCustomerProfile = LoginManager.getInstance().getProfile();
            LoginManager.getInstance().resendVerification(1);
        } else if (code == 3003) {
            this.mEmailSendedView.setVisibility(0);
        }
    }

    public void onFieldValidationStateChanged(boolean isValidated) {
        Ensighten.evaluateEvent(this, "onFieldValidationStateChanged", new Object[]{new Boolean(isValidated)});
        this.mVerifyEmailButton.setEnabled(isValidated);
    }

    private void verify() {
        Ensighten.evaluateEvent(this, "verify", null);
        if (this.mValidation.isValidated()) {
            this.mVerifyEmailButton.setEnabled(false);
            String mail = this.mEmailAddress.getText().toString().trim();
            if (mail.equals(this.mCustomerProfile.getEmailAddress())) {
                LoginManager.getInstance().resendVerification(1);
                return;
            }
            if (this.mCustomerProfile.getLoginPreference() == 1) {
                this.mCustomerProfile.setNewUserName(mail);
            }
            this.mCustomerProfile.setEmailAddress(mail);
            LoginManager.getInstance().updateProfile();
        }
    }
}
