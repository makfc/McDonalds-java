package com.mcdonalds.app.gmalite.account;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.customer.SignUpFragment;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.widget.ValidationListener;
import com.mcdonalds.app.widget.ValidationListener.OnUpdateListener;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;

public class LiteChangeZipCodeFragment extends URLNavigationFragment implements OnUpdateListener {
    public static final String NAME = LiteChangeZipCodeFragment.class.getSimpleName();
    private CustomerProfile mCustomerProfile;
    private final OnClickListener mOnClickDialog = new C31493();
    private final View.OnClickListener mOnClickSave = new C31471();
    private final AsyncListener<CustomerProfile> mProfileUpdateListener = new C31482();
    private View mSaveButton;
    private ValidationListener mValidation;
    private EditText mZipCode;

    /* renamed from: com.mcdonalds.app.gmalite.account.LiteChangeZipCodeFragment$1 */
    class C31471 implements View.OnClickListener {
        C31471() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeZipCodeFragment", "access$000", new Object[]{LiteChangeZipCodeFragment.this}).isValidated()) {
                AnalyticsUtils.trackOnClickEvent(LiteChangeZipCodeFragment.this.getAnalyticsTitle(), "Save");
                CustomerModule module = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
                CustomerProfile profile = module.getCurrentProfile();
                CustomerProfile newProfile = new CustomerProfile();
                final String oldZipCode = profile.getZipCode();
                newProfile.setZipCode(Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeZipCodeFragment", "access$100", new Object[]{LiteChangeZipCodeFragment.this}).getText().toString().trim());
                UIUtils.startActivityIndicator(LiteChangeZipCodeFragment.this.getActivity(), (int) C2658R.string.lite_dialog_acct_zip);
                module.updateCustomerProfile(newProfile, new AsyncListener<CustomerProfile>() {
                    public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        UIUtils.stopActivityIndicator();
                        if (exception != null || response == null) {
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeZipCodeFragment", "access$100", new Object[]{LiteChangeZipCodeFragment.this}).setText(oldZipCode);
                            AsyncException.report(exception);
                            return;
                        }
                        LoginManager.getInstance().getProfile().setZipCode(response.getZipCode());
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("zipCode", response.getZipCode());
                        LiteChangeZipCodeFragment.this.getNavigationActivity().setResult(-1, resultIntent);
                        LiteChangeZipCodeFragment.this.getNavigationActivity().finish();
                    }
                });
            }
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.account.LiteChangeZipCodeFragment$2 */
    class C31482 implements AsyncListener<CustomerProfile> {
        C31482() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (exception != null) {
                AsyncException.report(exception);
                return;
            }
            UIUtils.showGlobalAlertDialog(LiteChangeZipCodeFragment.this.getActivity(), LiteChangeZipCodeFragment.this.getString(C2658R.string.text_hint_zipcode), LiteChangeZipCodeFragment.this.getString(C2658R.string.text_zipcode_updated), Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeZipCodeFragment", "access$200", new Object[]{LiteChangeZipCodeFragment.this}));
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeZipCodeFragment", "access$300", new Object[]{LiteChangeZipCodeFragment.this}).setEnabled(false);
            if (response != null) {
                LoginManager.getInstance().setProfile(response);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.account.LiteChangeZipCodeFragment$3 */
    class C31493 implements OnClickListener {
        C31493() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.lite_title_acct_zip);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_account_zip_code);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCustomerProfile = LoginManager.getInstance().getProfile();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_modify_zip_code, container, false);
        this.mZipCode = (EditText) view.findViewById(C2358R.C2357id.zip_code);
        SignUpFragment.setupPostalCode(this.mZipCode);
        this.mSaveButton = view.findViewById(C2358R.C2357id.save_btn);
        this.mSaveButton.setOnClickListener(this.mOnClickSave);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this.mCustomerProfile.getZipCode() != null) {
            this.mZipCode.setText(this.mCustomerProfile.getZipCode());
        }
        this.mValidation = new ValidationListener(this.mZipCode, 3, false, true);
        this.mValidation.setUpdateListener(this);
        this.mZipCode.addTextChangedListener(this.mValidation);
    }

    public void onFieldUpdate() {
        Ensighten.evaluateEvent(this, "onFieldUpdate", null);
        this.mSaveButton.setEnabled(this.mValidation.isValidated());
    }
}
