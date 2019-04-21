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

public class EmailAddressTakeOverFragment extends URLNavigationFragment implements Callback {
    private CustomerModule mCustomerModule;
    private CustomerProfile mCustomerProfile;
    private EditText mMail;
    private ValidationListener mMailValidation;
    private final OnClickListener mOnClickVerify = new C29751();
    private final AsyncListener<CustomerProfile> mProfileListener = new C29762();
    private final AsyncListener<Void> mResendListener = new C29773();
    private View mVerifyButton;

    /* renamed from: com.mcdonalds.app.account.EmailAddressTakeOverFragment$1 */
    class C29751 implements OnClickListener {
        C29751() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EmailAddressTakeOverFragment", "access$000", new Object[]{EmailAddressTakeOverFragment.this}).isValidated()) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EmailAddressTakeOverFragment", "access$200", new Object[]{EmailAddressTakeOverFragment.this}).setEmailAddress(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EmailAddressTakeOverFragment", "access$100", new Object[]{EmailAddressTakeOverFragment.this}).getText().toString().trim());
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EmailAddressTakeOverFragment", "access$400", new Object[]{EmailAddressTakeOverFragment.this}).updateCustomerProfile(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EmailAddressTakeOverFragment", "access$200", new Object[]{EmailAddressTakeOverFragment.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EmailAddressTakeOverFragment", "access$300", new Object[]{EmailAddressTakeOverFragment.this}));
                return;
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EmailAddressTakeOverFragment", "access$500", new Object[]{EmailAddressTakeOverFragment.this}).setEnabled(false);
        }
    }

    /* renamed from: com.mcdonalds.app.account.EmailAddressTakeOverFragment$2 */
    class C29762 implements AsyncListener<CustomerProfile> {
        C29762() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception != null) {
                AsyncException.report(exception);
            } else if (response != null) {
                EmailAddressTakeOverFragment.access$202(EmailAddressTakeOverFragment.this, response);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EmailAddressTakeOverFragment", "access$400", new Object[]{EmailAddressTakeOverFragment.this}).resendActivation(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EmailAddressTakeOverFragment", "access$200", new Object[]{EmailAddressTakeOverFragment.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EmailAddressTakeOverFragment", "access$600", new Object[]{EmailAddressTakeOverFragment.this}));
                LoginManager.getInstance().setProfile(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EmailAddressTakeOverFragment", "access$200", new Object[]{EmailAddressTakeOverFragment.this}));
            }
        }
    }

    /* renamed from: com.mcdonalds.app.account.EmailAddressTakeOverFragment$3 */
    class C29773 implements AsyncListener<Void> {
        C29773() {
        }

        public void onResponse(Void response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (exception != null) {
                AsyncException.report(exception);
            } else if (EmailAddressTakeOverFragment.this.getNavigationActivity() != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("validation_method", 1);
                EmailAddressTakeOverFragment.this.startActivity(ProfileUpdateActivity.class, "mail_validation", bundle);
            }
        }
    }

    static /* synthetic */ CustomerProfile access$202(EmailAddressTakeOverFragment x0, CustomerProfile x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EmailAddressTakeOverFragment", "access$202", new Object[]{x0, x1});
        x0.mCustomerProfile = x1;
        return x1;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mCustomerProfile = this.mCustomerModule.getCurrentProfile();
        getActivity().setTitle("Add Email Address");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C2658R.layout.fragment_email_take_over, container, false);
        this.mMail = (EditText) v.findViewById(2131821086);
        this.mVerifyButton = v.findViewById(C2358R.C2357id.button_verify_email);
        this.mVerifyButton.setOnClickListener(this.mOnClickVerify);
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mMailValidation = new ValidationListener(this.mMail, 0, true, true);
        this.mMailValidation.setValidationCallback(this);
        this.mMail.addTextChangedListener(this.mMailValidation);
    }

    public void onFieldValidationStateChanged(boolean isValidated) {
        Ensighten.evaluateEvent(this, "onFieldValidationStateChanged", new Object[]{new Boolean(isValidated)});
        this.mVerifyButton.setEnabled(isValidated);
    }
}
