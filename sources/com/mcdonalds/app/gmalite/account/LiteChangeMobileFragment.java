package com.mcdonalds.app.gmalite.account;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.gmalite.customer.LiteSmsVerificationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.StringUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.widget.EditTextPhone;
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
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.Observable;
import java.util.Observer;

public class LiteChangeMobileFragment extends URLNavigationFragment implements Callback, Observer {
    public static String NAME = "gmalite_change_mobile";
    private CustomerModule mCustomerModule;
    private CustomerProfile mCustomerProfile;
    private EditText mMobileNumber;
    private LinearLayout mNotVerifiedComponent;
    private OnClickListener mOnClickOk = new C31384();
    private final View.OnClickListener mOnClickSaveChanges = new C31321();
    private final View.OnClickListener mOnClickVerify = new C31342();
    private View mSaveChanges;
    private ValidationListener mValidation;
    private TextView mVerifyNumber;

    /* renamed from: com.mcdonalds.app.gmalite.account.LiteChangeMobileFragment$1 */
    class C31321 implements View.OnClickListener {
        C31321() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            LiteChangeMobileFragment.access$000(LiteChangeMobileFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.account.LiteChangeMobileFragment$2 */
    class C31342 implements View.OnClickListener {

        /* renamed from: com.mcdonalds.app.gmalite.account.LiteChangeMobileFragment$2$1 */
        class C31331 implements AsyncListener<Boolean> {
            C31331() {
            }

            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                UIUtils.stopActivityIndicator();
                Intent intent = new Intent(LiteChangeMobileFragment.this.getNavigationActivity(), LiteSmsVerificationActivity.class);
                intent.putExtra("shouldGoToPreviousView", true);
                LiteChangeMobileFragment.this.startActivity(intent);
                LiteChangeMobileFragment.this.getNavigationActivity().finish();
            }
        }

        C31342() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            UIUtils.startActivityIndicator(LiteChangeMobileFragment.this.getNavigationActivity(), (int) C2658R.string.lite_dialog_sms_send);
            AnalyticsUtils.trackOnClickEvent(LiteChangeMobileFragment.this.getAnalyticsTitle(), "Verify Account");
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeMobileFragment", "access$200", new Object[]{LiteChangeMobileFragment.this}).sendSMSCode(Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeMobileFragment", "access$100", new Object[]{LiteChangeMobileFragment.this}), new C31331());
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.account.LiteChangeMobileFragment$4 */
    class C31384 implements OnClickListener {

        /* renamed from: com.mcdonalds.app.gmalite.account.LiteChangeMobileFragment$4$1 */
        class C31371 implements AsyncListener<Boolean> {
            C31371() {
            }

            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                Intent intent = new Intent(LiteChangeMobileFragment.this.getNavigationActivity(), LiteSmsVerificationActivity.class);
                intent.putExtra("shouldGoToPreviousView", true);
                LiteChangeMobileFragment.this.startActivity(intent);
            }
        }

        C31384() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeMobileFragment", "access$200", new Object[]{LiteChangeMobileFragment.this}).sendSMSCode(Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeMobileFragment", "access$100", new Object[]{LiteChangeMobileFragment.this}), new C31371());
        }
    }

    static /* synthetic */ void access$000(LiteChangeMobileFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeMobileFragment", "access$000", new Object[]{x0});
        x0.updatePhoneNumber();
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.lite_title_acct_phone);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_account_edit_phone);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginManager.getInstance().addObserver(this);
        this.mCustomerProfile = LoginManager.getInstance().getProfile();
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
    }

    public void onDestroy() {
        super.onDestroy();
        LoginManager.getInstance().deleteObserver(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C2658R.layout.fragment_lite_change_mobile, container, false);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.register.phoneNumberShowCountryCode")) {
            this.mMobileNumber = (EditTextPhone) v.findViewById(C2358R.C2357id.mobile_number_with_country_code);
            ((EditTextPhone) this.mMobileNumber).setCountryCode((String) Configuration.getSharedInstance().getValueForKey("interface.register.phoneNumberCountryCode"));
        } else {
            this.mMobileNumber = (EditText) v.findViewById(C2358R.C2357id.mobile_number);
        }
        this.mNotVerifiedComponent = (LinearLayout) v.findViewById(C2358R.C2357id.not_verified_component);
        this.mVerifyNumber = (TextView) this.mNotVerifiedComponent.findViewById(C2358R.C2357id.verify_link);
        this.mVerifyNumber.setOnClickListener(this.mOnClickVerify);
        this.mSaveChanges = v.findViewById(C2358R.C2357id.button_verify_mobile);
        this.mSaveChanges.setOnClickListener(this.mOnClickSaveChanges);
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mValidation = new ValidationListener(this.mMobileNumber, 5, true, true);
        this.mMobileNumber.addTextChangedListener(this.mValidation);
        this.mMobileNumber.setText(this.mCustomerProfile.getMobileNumber());
        this.mValidation.setValidationCallback(this);
    }

    public void onResume() {
        super.onResume();
        boolean verified = this.mCustomerProfile.isMobileVerified();
        if (this.mCustomerProfile.getVerificationType() != AccountVerificationType.SMS || verified) {
            this.mNotVerifiedComponent.setVisibility(8);
        } else {
            this.mNotVerifiedComponent.setVisibility(0);
        }
    }

    public void update(Observable observable, Object data) {
        Ensighten.evaluateEvent(this, "update", new Object[]{observable, data});
    }

    public void onFieldValidationStateChanged(boolean isValidated) {
        Ensighten.evaluateEvent(this, "onFieldValidationStateChanged", new Object[]{new Boolean(isValidated)});
        this.mSaveChanges.setEnabled(isValidated);
    }

    private void updatePhoneNumber() {
        Ensighten.evaluateEvent(this, "updatePhoneNumber", null);
        if (this.mValidation.isValidated()) {
            this.mSaveChanges.setEnabled(false);
            if (TextUtils.isEmpty((String) Configuration.getSharedInstance().getValueForKey("interface.register.phoneNumberCountryCode"))) {
                String countryCode = "";
            }
            final String mobile = this.mMobileNumber.getText().toString().trim();
            final String currentPhone = LoginManager.getInstance().getProfile().getMobileNumber();
            CustomerProfile newProfile = new CustomerProfile();
            newProfile.setMobileNumber(mobile);
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Save");
            UIUtils.startActivityIndicator(getActivity(), getString(C2658R.string.lite_dialog_acct_phone));
            this.mCustomerModule.updateCustomerProfile(newProfile, new AsyncListener<CustomerProfile>() {

                /* renamed from: com.mcdonalds.app.gmalite.account.LiteChangeMobileFragment$3$1 */
                class C31351 implements AsyncListener<Boolean> {
                    C31351() {
                    }

                    public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        Intent intent = new Intent(LiteChangeMobileFragment.this.getNavigationActivity(), LiteSmsVerificationActivity.class);
                        intent.putExtra("shouldGoToPreviousView", true);
                        LiteChangeMobileFragment.this.startActivity(intent);
                        LiteChangeMobileFragment.this.getNavigationActivity().finish();
                    }
                }

                public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    UIUtils.stopActivityIndicator();
                    if (exception != null || response == null) {
                        String mobileNumberFormatted = StringUtils.getMobileNumberWithoutCountryCode(Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeMobileFragment", "access$100", new Object[]{LiteChangeMobileFragment.this}).getMobileNumber(), (String) Configuration.getSharedInstance().getValueForKey("interface.register.phoneNumberCountryCode"));
                        if (ConfigurationUtils.shouldShowCountryCode()) {
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeMobileFragment", "access$300", new Object[]{LiteChangeMobileFragment.this}).setText(currentPhone);
                        } else {
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeMobileFragment", "access$300", new Object[]{LiteChangeMobileFragment.this}).setText(mobileNumberFormatted);
                        }
                        AsyncException.report(exception);
                        return;
                    }
                    LoginManager.getInstance().getProfile().setMobileNumber(mobile);
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeMobileFragment", "access$100", new Object[]{LiteChangeMobileFragment.this}).getVerificationType() == AccountVerificationType.SMS) {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeMobileFragment", "access$100", new Object[]{LiteChangeMobileFragment.this}).getCustomerLoginInfo().setDefaultPhoneNumberVerified(false);
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeMobileFragment", "access$200", new Object[]{LiteChangeMobileFragment.this}).sendSMSCode(Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeMobileFragment", "access$100", new Object[]{LiteChangeMobileFragment.this}), new C31351());
                        return;
                    }
                    LiteChangeMobileFragment.this.getNavigationActivity().setResult(-1);
                    LiteChangeMobileFragment.this.getNavigationActivity().finish();
                }
            });
        }
    }
}
