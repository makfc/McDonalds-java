package com.mcdonalds.app.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.widget.ValidationListener;
import com.mcdonalds.app.widget.ValidationListener.Callback;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.Observable;
import java.util.Observer;

public class ChangeMobileFragment extends URLNavigationFragment implements Callback, Observer {
    public static String NAME = "change_mobile";
    private static View mSaveButton;
    private String OriginalMobileNumber;
    private CustomerProfile mCustomerProfile;
    private EditText mMobileNumber;
    private View mNotVerifiedWarning;
    private OnClickListener mOnClickOkButton = new C29381();
    private final OnClickListener mOnClickSave = new C29413();
    private final OnClickListener mOnClickVerify = new C29392();
    private View mSmsSendedView;
    private ValidationListener mValidation;
    private View mVerifyMobileButton;

    /* renamed from: com.mcdonalds.app.account.ChangeMobileFragment$1 */
    class C29381 implements OnClickListener {
        C29381() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeMobileFragment", "access$000", new Object[]{ChangeMobileFragment.this}).setVisibility(8);
        }
    }

    /* renamed from: com.mcdonalds.app.account.ChangeMobileFragment$2 */
    class C29392 implements OnClickListener {
        C29392() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            UIUtils.dismissKeyboard(ChangeMobileFragment.this.getContext(), v);
            ChangeMobileFragment.access$100(ChangeMobileFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.account.ChangeMobileFragment$3 */
    class C29413 implements OnClickListener {

        /* renamed from: com.mcdonalds.app.account.ChangeMobileFragment$3$1 */
        class C29401 implements AsyncListener<CustomerProfile> {
            C29401() {
            }

            public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                UIUtils.stopActivityIndicator();
                if (exception != null || response == null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeMobileFragment", "access$300", new Object[]{ChangeMobileFragment.this}).setMobileNumber(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeMobileFragment", "access$400", new Object[]{ChangeMobileFragment.this}));
                    AsyncException.report(exception);
                    return;
                }
                Intent resultIntent = new Intent();
                resultIntent.putExtra("mobilenumber", Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeMobileFragment", "access$200", new Object[]{ChangeMobileFragment.this}).getText().toString());
                ChangeMobileFragment.this.getNavigationActivity().setResult(-1, resultIntent);
                ChangeMobileFragment.this.getNavigationActivity().finish();
            }
        }

        C29413() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            CustomerModule module = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeMobileFragment", "access$300", new Object[]{ChangeMobileFragment.this}).setMobileNumber(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeMobileFragment", "access$200", new Object[]{ChangeMobileFragment.this}).getText().toString());
            AnalyticsUtils.trackOnClickEvent(ChangeMobileFragment.this.getAnalyticsTitle(), "Name");
            UIUtils.startActivityIndicator(ChangeMobileFragment.this.getNavigationActivity(), (int) C2658R.string.lite_account_update_mobile_dialog_changing);
            module.updateCustomerProfile(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeMobileFragment", "access$300", new Object[]{ChangeMobileFragment.this}), new C29401());
        }
    }

    static /* synthetic */ void access$100(ChangeMobileFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeMobileFragment", "access$100", new Object[]{x0});
        x0.verify();
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.label_update_mobile);
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
        View v = inflater.inflate(C2658R.layout.fragment_change_mobile, container, false);
        this.mMobileNumber = (EditText) v.findViewById(C2358R.C2357id.mobile_number);
        this.mNotVerifiedWarning = v.findViewById(C2358R.C2357id.warning_no_mobile_verified);
        this.mVerifyMobileButton = v.findViewById(C2358R.C2357id.button_verify_mobile);
        this.mVerifyMobileButton.setOnClickListener(this.mOnClickVerify);
        this.mSmsSendedView = v.findViewById(C2358R.C2357id.sms_sended_view);
        v.findViewById(C2358R.C2357id.ok_button).setOnClickListener(this.mOnClickOkButton);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.disableInteraction")) {
            UIUtils.disableInteraction(this.mMobileNumber);
        }
        this.OriginalMobileNumber = this.mCustomerProfile.getMobileNumber();
        mSaveButton = v.findViewById(C2358R.C2357id.save_btn);
        mSaveButton.setOnClickListener(this.mOnClickSave);
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        validate();
    }

    public void validate() {
        boolean verified;
        Ensighten.evaluateEvent(this, "validate", null);
        this.mValidation = new ValidationListener(this.mMobileNumber, 5, true, true);
        this.mMobileNumber.addTextChangedListener(this.mValidation);
        this.mMobileNumber.setText(this.mCustomerProfile.getMobileNumber());
        this.mValidation.setValidationCallback(this);
        if (this.mCustomerProfile.isMobileVerified() && this.mValidation.isValidated()) {
            verified = true;
        } else {
            verified = false;
        }
        if (verified) {
            this.mVerifyMobileButton.setEnabled(false);
            this.mNotVerifiedWarning.setVisibility(8);
        } else {
            this.mVerifyMobileButton.setEnabled(true);
            this.mNotVerifiedWarning.setVisibility(8);
        }
        if (this.mMobileNumber.getText().toString().equals(this.OriginalMobileNumber) || !this.mValidation.isValidated()) {
            mSaveButton.setEnabled(false);
        } else {
            mSaveButton.setEnabled(true);
        }
    }

    public void update(Observable observable, Object data) {
        Ensighten.evaluateEvent(this, "update", new Object[]{observable, data});
        int code = ((Integer) data).intValue();
        if (code == 3002) {
            this.mCustomerProfile = LoginManager.getInstance().getProfile();
            LoginManager.getInstance().resendVerification(2);
        } else if (code == 3003) {
            this.mSmsSendedView.setVisibility(0);
        }
    }

    public void onFieldValidationStateChanged(boolean isValidated) {
        Ensighten.evaluateEvent(this, "onFieldValidationStateChanged", new Object[]{new Boolean(isValidated)});
        this.mVerifyMobileButton.setEnabled(isValidated);
        if (this.mMobileNumber.getText().toString().equals(this.OriginalMobileNumber) || !isValidated) {
            mSaveButton.setEnabled(false);
        } else {
            mSaveButton.setEnabled(true);
        }
    }

    private void verify() {
        Ensighten.evaluateEvent(this, "verify", null);
        if (this.mValidation.isValidated()) {
            this.mVerifyMobileButton.setEnabled(false);
            String mobile = this.mMobileNumber.getText().toString().trim();
            if (mobile.equals(this.mCustomerProfile.getMobileNumber())) {
                LoginManager.getInstance().resendVerification(2);
                return;
            }
            if (this.mCustomerProfile.getLoginPreference() == 2) {
                this.mCustomerProfile.setNewUserName(mobile);
            }
            this.mCustomerProfile.setMobileNumber(mobile);
            LoginManager.getInstance().updateProfile();
        }
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_account_edit_phone);
    }
}
