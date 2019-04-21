package com.mcdonalds.app.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.account.ProfileUpdateActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
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

public class MobileVerifyFragmet extends URLNavigationFragment implements Callback {
    private CustomerModule mCustomerModule;
    private CustomerProfile mCustomerProfile;
    private EditText mMobile;
    private ValidationListener mMobileValidation;
    private final OnClickListener mOnClickVerify = new C30201();
    private final AsyncListener<CustomerProfile> mProfileListener = new C30212();
    private final AsyncListener<Void> mResendListener = new C30223();
    private View mVerifyButton;

    /* renamed from: com.mcdonalds.app.customer.MobileVerifyFragmet$1 */
    class C30201 implements OnClickListener {
        C30201() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.MobileVerifyFragmet", "access$000", new Object[]{MobileVerifyFragmet.this}).isValidated()) {
                String mobile = Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.MobileVerifyFragmet", "access$100", new Object[]{MobileVerifyFragmet.this}).getText().toString().trim();
                if (mobile.equalsIgnoreCase(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.MobileVerifyFragmet", "access$200", new Object[]{MobileVerifyFragmet.this}).getMobileNumber())) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.MobileVerifyFragmet", "access$200", new Object[]{MobileVerifyFragmet.this}).setActivationOption(2);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.MobileVerifyFragmet", "access$400", new Object[]{MobileVerifyFragmet.this}).resendActivation(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.MobileVerifyFragmet", "access$200", new Object[]{MobileVerifyFragmet.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.MobileVerifyFragmet", "access$300", new Object[]{MobileVerifyFragmet.this}));
                    return;
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.MobileVerifyFragmet", "access$200", new Object[]{MobileVerifyFragmet.this}).setMobileNumber(mobile);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.MobileVerifyFragmet", "access$400", new Object[]{MobileVerifyFragmet.this}).updateCustomerProfile(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.MobileVerifyFragmet", "access$200", new Object[]{MobileVerifyFragmet.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.MobileVerifyFragmet", "access$500", new Object[]{MobileVerifyFragmet.this}));
                return;
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.MobileVerifyFragmet", "access$600", new Object[]{MobileVerifyFragmet.this}).setEnabled(false);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.MobileVerifyFragmet$2 */
    class C30212 implements AsyncListener<CustomerProfile> {
        C30212() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception != null) {
                AsyncException.report(exception);
            } else if (response != null) {
                MobileVerifyFragmet.access$202(MobileVerifyFragmet.this, response);
                LoginManager.getInstance().setProfile(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.MobileVerifyFragmet", "access$200", new Object[]{MobileVerifyFragmet.this}));
                response.setActivationOption(2);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.MobileVerifyFragmet", "access$400", new Object[]{MobileVerifyFragmet.this}).resendActivation(response, Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.MobileVerifyFragmet", "access$300", new Object[]{MobileVerifyFragmet.this}));
            }
        }
    }

    /* renamed from: com.mcdonalds.app.customer.MobileVerifyFragmet$3 */
    class C30223 implements AsyncListener<Void> {
        C30223() {
        }

        public void onResponse(Void response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (exception != null) {
                AsyncException.report(exception);
            } else if (MobileVerifyFragmet.this.getNavigationActivity() != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("validation_method", 2);
                MobileVerifyFragmet.this.startActivity(ProfileUpdateActivity.class, "mail_validation", bundle);
            }
        }
    }

    static /* synthetic */ CustomerProfile access$202(MobileVerifyFragmet x0, CustomerProfile x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.MobileVerifyFragmet", "access$202", new Object[]{x0, x1});
        x0.mCustomerProfile = x1;
        return x1;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mCustomerProfile = this.mCustomerModule.getCurrentProfile();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C2658R.layout.fragment_mobile_verify, container, false);
        this.mMobile = (EditText) v.findViewById(C2358R.C2357id.mobile_number);
        this.mVerifyButton = v.findViewById(C2358R.C2357id.button_verify_mobile);
        this.mVerifyButton.setOnClickListener(this.mOnClickVerify);
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mMobileValidation = new ValidationListener(this.mMobile, 5, true, true);
        this.mMobileValidation.setValidationCallback(this);
        this.mMobile.addTextChangedListener(this.mMobileValidation);
        this.mMobile.setText(this.mCustomerProfile.getMobileNumber());
    }

    public void onFieldValidationStateChanged(boolean isValidated) {
        Ensighten.evaluateEvent(this, "onFieldValidationStateChanged", new Object[]{new Boolean(isValidated)});
        this.mVerifyButton.setEnabled(isValidated);
    }
}
