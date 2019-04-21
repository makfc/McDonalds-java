package com.mcdonalds.app.account;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.customer.SignUpFragment;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
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

public class ModifyZipCodeFragment extends URLNavigationFragment implements OnUpdateListener {
    public static final String NAME = ModifyZipCodeFragment.class.getSimpleName();
    private CustomerProfile mCustomerProfile;
    private final OnClickListener mOnClickDialog = new C30003();
    private final View.OnClickListener mOnClickSave = new C29981();
    private final AsyncListener<CustomerProfile> mProfileUpdateListener = new C29992();
    private View mSaveButton;
    private ValidationListener mValidation;
    private EditText mZipCode;

    /* renamed from: com.mcdonalds.app.account.ModifyZipCodeFragment$1 */
    class C29981 implements View.OnClickListener {
        C29981() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyZipCodeFragment", "access$000", new Object[]{ModifyZipCodeFragment.this}).isValidated()) {
                AnalyticsUtils.trackOnClickEvent(ModifyZipCodeFragment.this.getAnalyticsTitle(), "Zip Code");
                CustomerModule module = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
                CustomerProfile profile = module.getCurrentProfile();
                if (profile != null) {
                    profile.setZipCode(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyZipCodeFragment", "access$100", new Object[]{ModifyZipCodeFragment.this}).getText().toString().trim());
                    UIUtils.startActivityIndicator(ModifyZipCodeFragment.this.getActivity(), (int) C2658R.string.dialog_changing_zipcode);
                    module.updateCustomerProfile(profile, Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyZipCodeFragment", "access$200", new Object[]{ModifyZipCodeFragment.this}));
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.account.ModifyZipCodeFragment$2 */
    class C29992 implements AsyncListener<CustomerProfile> {
        C29992() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (exception != null) {
                AsyncException.report(exception);
                return;
            }
            UIUtils.showGlobalAlertDialog(ModifyZipCodeFragment.this.getActivity(), ModifyZipCodeFragment.this.getString(C2658R.string.dialog_title_success), ModifyZipCodeFragment.this.getString(C2658R.string.text_zipcode_updated), Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyZipCodeFragment", "access$300", new Object[]{ModifyZipCodeFragment.this}));
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ModifyZipCodeFragment", "access$400", new Object[]{ModifyZipCodeFragment.this}).setEnabled(false);
            if (response != null) {
                LoginManager.getInstance().setProfile(response);
            }
            DataLayerManager.getInstance().setUser(response, "Signed-in", AppUtils.getCurrentMenuType());
        }
    }

    /* renamed from: com.mcdonalds.app.account.ModifyZipCodeFragment$3 */
    class C30003 implements OnClickListener {
        C30003() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.title_zip_code);
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
