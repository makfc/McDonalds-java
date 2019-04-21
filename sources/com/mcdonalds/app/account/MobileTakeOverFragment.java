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
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;

public class MobileTakeOverFragment extends URLNavigationFragment implements Callback {
    private CustomerModule mCustomerModule;
    private CustomerProfile mCustomerProfile;
    private EditText mMobile;
    private ValidationListener mMobileValidation;
    private final OnClickListener mOnClickVerify = new C29781();
    private final AsyncListener<CustomerProfile> mProfileListener = new C29792();
    private final AsyncListener<Void> mResendListener = new C29803();
    private View mVerifyButton;

    /* renamed from: com.mcdonalds.app.account.MobileTakeOverFragment$1 */
    class C29781 implements OnClickListener {
        C29781() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.MobileTakeOverFragment", "access$000", new Object[]{MobileTakeOverFragment.this}).isValidated()) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.MobileTakeOverFragment", "access$200", new Object[]{MobileTakeOverFragment.this}).setMobileNumber(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.MobileTakeOverFragment", "access$100", new Object[]{MobileTakeOverFragment.this}).getText().toString().trim());
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.MobileTakeOverFragment", "access$400", new Object[]{MobileTakeOverFragment.this}).updateCustomerProfile(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.MobileTakeOverFragment", "access$200", new Object[]{MobileTakeOverFragment.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.MobileTakeOverFragment", "access$300", new Object[]{MobileTakeOverFragment.this}));
                return;
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.MobileTakeOverFragment", "access$500", new Object[]{MobileTakeOverFragment.this}).setEnabled(false);
        }
    }

    /* renamed from: com.mcdonalds.app.account.MobileTakeOverFragment$2 */
    class C29792 implements AsyncListener<CustomerProfile> {
        C29792() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception != null) {
                AsyncException.report(exception);
            } else if (response != null) {
                MobileTakeOverFragment.access$202(MobileTakeOverFragment.this, response);
                LoginManager.getInstance().setProfile(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.MobileTakeOverFragment", "access$200", new Object[]{MobileTakeOverFragment.this}));
                response.setActivationOption(2);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.MobileTakeOverFragment", "access$400", new Object[]{MobileTakeOverFragment.this}).resendActivation(response, Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.MobileTakeOverFragment", "access$600", new Object[]{MobileTakeOverFragment.this}));
            }
        }
    }

    /* renamed from: com.mcdonalds.app.account.MobileTakeOverFragment$3 */
    class C29803 implements AsyncListener<Void> {
        C29803() {
        }

        public void onResponse(Void response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (exception != null) {
                AsyncException.report(exception);
            } else if (MobileTakeOverFragment.this.getNavigationActivity() != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("validation_method", 2);
                MobileTakeOverFragment.this.startActivity(ProfileUpdateActivity.class, "mail_validation", bundle);
            }
        }
    }

    static /* synthetic */ CustomerProfile access$202(MobileTakeOverFragment x0, CustomerProfile x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.MobileTakeOverFragment", "access$202", new Object[]{x0, x1});
        x0.mCustomerProfile = x1;
        return x1;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mCustomerProfile = this.mCustomerModule.getCurrentProfile();
        getActivity().setTitle(C2658R.string.title_add_mobile_number);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C2658R.layout.fragment_mobile_take_over, container, false);
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
    }

    public void onFieldValidationStateChanged(boolean isValidated) {
        Ensighten.evaluateEvent(this, "onFieldValidationStateChanged", new Object[]{new Boolean(isValidated)});
        this.mVerifyButton.setEnabled(isValidated);
    }
}
