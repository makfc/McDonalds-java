package com.mcdonalds.app.gmalite.account;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
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
import com.mcdonalds.sdk.services.data.LocalDataManager;

public class LiteChangePasswordFragment extends URLNavigationFragment implements Callback {
    public static final String NAME = LiteChangePasswordFragment.class.getSimpleName();
    private CustomerProfile mCustomerProfile;
    private EditText mNewPassword;
    private TextWatcher mNewPasswordChanged = new C31402();
    private EditText mNewPasswordConfirm;
    private boolean mNewPasswordFieldsValidated;
    private boolean mOldPasswordValidated;
    private final OnClickListener mOnClickDialog = new C31424();
    private EditText mOriginal;
    private ValidationListener mPasswordValidation;
    private Button mSubmitButton;

    /* renamed from: com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment$1 */
    class C31391 implements View.OnClickListener {
        C31391() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            LiteChangePasswordFragment.access$000(LiteChangePasswordFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment$2 */
    class C31402 implements TextWatcher {
        C31402() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{s, new Integer(start), new Integer(count), new Integer(after)});
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{s, new Integer(start), new Integer(before), new Integer(count)});
            boolean validPassword = UIUtils.isPasswordValid(Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$100", new Object[]{LiteChangePasswordFragment.this}).getText().toString());
            boolean passwordMatches = Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$100", new Object[]{LiteChangePasswordFragment.this}).getText().toString().equals(Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$200", new Object[]{LiteChangePasswordFragment.this}).getText().toString());
            LiteChangePasswordFragment liteChangePasswordFragment = LiteChangePasswordFragment.this;
            boolean z = validPassword && passwordMatches;
            LiteChangePasswordFragment.access$302(liteChangePasswordFragment, z);
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$400", new Object[]{LiteChangePasswordFragment.this}) && Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$300", new Object[]{LiteChangePasswordFragment.this})) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$500", new Object[]{LiteChangePasswordFragment.this}).setEnabled(true);
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$500", new Object[]{LiteChangePasswordFragment.this}).setEnabled(false);
            }
            if (validPassword) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$100", new Object[]{LiteChangePasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_valid, 0);
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$100", new Object[]{LiteChangePasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
            }
            if (TextUtils.isEmpty(Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$100", new Object[]{LiteChangePasswordFragment.this}).getText().toString()) || !passwordMatches) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$200", new Object[]{LiteChangePasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$200", new Object[]{LiteChangePasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_valid, 0);
            }
        }

        public void afterTextChanged(Editable s) {
            Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{s});
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment$3 */
    class C31413 implements AsyncListener<CustomerProfile> {
        C31413() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (exception == null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$600", new Object[]{LiteChangePasswordFragment.this}).setPassword(Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$100", new Object[]{LiteChangePasswordFragment.this}).getText().toString());
                LiteChangePasswordFragment.access$700(LiteChangePasswordFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$100", new Object[]{LiteChangePasswordFragment.this}).getText().toString());
                LiteChangePasswordFragment.this.getNavigationActivity().setResult(-1);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$600", new Object[]{LiteChangePasswordFragment.this}).setPasswordChangeRequired(false);
                LiteChangePasswordFragment.this.getNavigationActivity().finish();
                return;
            }
            AsyncException.report(exception);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment$4 */
    class C31424 implements OnClickListener {
        C31424() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            LiteChangePasswordFragment.this.getActivity().setResult(-1);
            LiteChangePasswordFragment.this.getActivity().finish();
        }
    }

    static /* synthetic */ void access$000(LiteChangePasswordFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$000", new Object[]{x0});
        x0.onSave();
    }

    static /* synthetic */ boolean access$302(LiteChangePasswordFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$302", new Object[]{x0, new Boolean(x1)});
        x0.mNewPasswordFieldsValidated = x1;
        return x1;
    }

    static /* synthetic */ void access$700(LiteChangePasswordFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangePasswordFragment", "access$700", new Object[]{x0, x1});
        x0.persistPassword(x1);
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.lite_title_acct_pswd);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_account_edit_password);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCustomerProfile = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentProfile();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_lite_change_password_verify, container, false);
        this.mOriginal = (EditText) rootView.findViewById(C2358R.C2357id.existing_inc);
        this.mNewPassword = (EditText) rootView.findViewById(C2358R.C2357id.new_inc);
        this.mNewPasswordConfirm = (EditText) rootView.findViewById(C2358R.C2357id.new_verify_inc);
        this.mSubmitButton = (Button) rootView.findViewById(C2358R.C2357id.save_btn);
        this.mSubmitButton.setOnClickListener(new C31391());
        this.mPasswordValidation = new ValidationListener(this.mOriginal, 1, true, true);
        this.mPasswordValidation.setValidationCallback(this);
        this.mOriginal.addTextChangedListener(this.mPasswordValidation);
        this.mNewPassword.addTextChangedListener(this.mNewPasswordChanged);
        this.mNewPasswordConfirm.addTextChangedListener(this.mNewPasswordChanged);
        return rootView;
    }

    private boolean validateNewPassword() {
        Ensighten.evaluateEvent(this, "validateNewPassword", null);
        return this.mOldPasswordValidated && this.mNewPasswordFieldsValidated;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.save, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        switch (item.getItemId()) {
            case C2358R.C2357id.action_save /*2131821893*/:
                onSave();
                break;
        }
        return true;
    }

    private void onSave() {
        Ensighten.evaluateEvent(this, "onSave", null);
        if (!validateNewPassword()) {
            return;
        }
        if (AppUtils.isNetworkConnected(getNavigationActivity())) {
            UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.lite_dialog_acct_pswd);
            CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            if (this.mCustomerProfile == null) {
                this.mCustomerProfile = customerModule.getCurrentProfile();
            }
            String oldPassword = this.mCustomerProfile.getPassword();
            CustomerProfile newProfile = new CustomerProfile();
            newProfile.setPasswordChangeRequired(true);
            newProfile.setPassword(this.mOriginal.getText().toString().trim());
            newProfile.setNewPassword(this.mNewPassword.getText().toString().trim());
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Save");
            customerModule.updateCustomerProfile(newProfile, new C31413());
            return;
        }
        UIUtils.showNoNetworkAlert(getNavigationActivity());
    }

    private void persistPassword(String newPassword) {
        Ensighten.evaluateEvent(this, "persistPassword", new Object[]{newPassword});
        this.mCustomerProfile.setPassword(newPassword);
        LocalDataManager.getSharedInstance().setPrefSavedLoginPass(newPassword);
    }

    public void onFieldValidationStateChanged(boolean isValidated) {
        Ensighten.evaluateEvent(this, "onFieldValidationStateChanged", new Object[]{new Boolean(isValidated)});
        this.mOldPasswordValidated = isValidated;
        if (this.mOldPasswordValidated && this.mNewPasswordFieldsValidated) {
            this.mSubmitButton.setEnabled(true);
        } else {
            this.mSubmitButton.setEnabled(false);
        }
    }
}
