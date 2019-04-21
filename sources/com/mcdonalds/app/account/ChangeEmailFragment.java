package com.mcdonalds.app.account;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.p043ui.NavigationDrawerFragment;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.widget.ValidationListener;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class ChangeEmailFragment extends URLNavigationFragment {
    public static final String NAME = ChangeEmailFragment.class.getSimpleName();
    private CustomerModule mCustomerModule;
    private CustomerProfile mCustomerProfile;
    private EditText mNewEmailEditText;
    private EditText mNewEmailVerifyEditText;
    private String mOriginalEmail;
    private EditText mPassword;
    private Button mSaveButton;
    private TextWatcher mSaveButtonTextWatcher = new C29312();
    private boolean mUsernameEqualsEmail;
    private SparseArray<ValidationListener> mValidations;

    /* renamed from: com.mcdonalds.app.account.ChangeEmailFragment$1 */
    class C29301 implements OnClickListener {
        C29301() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeEmailFragment", "access$000", new Object[]{ChangeEmailFragment.this})) {
                ChangeEmailFragment.access$100(ChangeEmailFragment.this);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.account.ChangeEmailFragment$2 */
    class C29312 implements TextWatcher {
        C29312() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{s, new Integer(start), new Integer(count), new Integer(after)});
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{s, new Integer(start), new Integer(before), new Integer(count)});
        }

        public void afterTextChanged(Editable s) {
            Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{s});
            ChangeEmailFragment.access$200(ChangeEmailFragment.this);
        }
    }

    static /* synthetic */ void access$100(ChangeEmailFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeEmailFragment", "access$100", new Object[]{x0});
        x0.onSave();
    }

    static /* synthetic */ void access$200(ChangeEmailFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeEmailFragment", "access$200", new Object[]{x0});
        x0.shouldEnableSubmitButton();
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.title_change_email_fragment);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_account_edit_email);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mOriginalEmail = LoginManager.getInstance().getProfile().getEmailAddress();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_change_profile_verify, container, false);
        this.mPassword = (EditText) rootView.findViewById(C2358R.C2357id.existing_inc);
        this.mNewEmailEditText = (EditText) rootView.findViewById(C2358R.C2357id.new_inc);
        this.mNewEmailVerifyEditText = (EditText) rootView.findViewById(C2358R.C2357id.new_verify_inc);
        this.mSaveButton = (Button) rootView.findViewById(C2358R.C2357id.save_btn);
        this.mSaveButton.setEnabled(false);
        this.mPassword.addTextChangedListener(this.mSaveButtonTextWatcher);
        this.mNewEmailEditText.addTextChangedListener(this.mSaveButtonTextWatcher);
        this.mNewEmailVerifyEditText.addTextChangedListener(this.mSaveButtonTextWatcher);
        this.mSaveButton.setOnClickListener(new C29301());
        configure();
        if (Configuration.getSharedInstance().getBooleanForKey("interface.disableInteraction")) {
            UIUtils.disableInteraction(this.mNewEmailEditText);
            UIUtils.disableInteraction(this.mNewEmailVerifyEditText);
        }
        return rootView;
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

    private void configure() {
        Ensighten.evaluateEvent(this, "configure", null);
        this.mValidations = new SparseArray();
        setMailValidation();
        setConfirmEmailValidation();
    }

    private ValidationListener addValidation(EditText field, int type) {
        boolean z = false;
        Ensighten.evaluateEvent(this, "addValidation", new Object[]{field, new Integer(type)});
        if (type != 4) {
            z = true;
        }
        return addValidation(new ValidationListener(field, type, z, true));
    }

    private ValidationListener addValidation(ValidationListener validation) {
        Ensighten.evaluateEvent(this, "addValidation", new Object[]{validation});
        validation.getTextField().addTextChangedListener(validation);
        this.mValidations.put(validation.getTextField().getId(), validation);
        return validation;
    }

    private void setMailValidation() {
        Ensighten.evaluateEvent(this, "setMailValidation", null);
        this.mValidations.put(this.mNewEmailEditText.getId(), addValidation(this.mNewEmailEditText, 0));
    }

    private boolean validateData() {
        Ensighten.evaluateEvent(this, "validateData", null);
        int i = 0;
        while (i < this.mValidations.size()) {
            ValidationListener validation = (ValidationListener) this.mValidations.get(this.mValidations.keyAt(i));
            if (validation.isValidated()) {
                i++;
            } else {
                validation.displayError();
                return false;
            }
        }
        return true;
    }

    private void setConfirmEmailValidation() {
        Ensighten.evaluateEvent(this, "setConfirmEmailValidation", null);
        addValidation(new ValidationListener(this.mNewEmailVerifyEditText, this.mNewEmailEditText, 0, true, true, true));
    }

    private void shouldEnableSubmitButton() {
        Ensighten.evaluateEvent(this, "shouldEnableSubmitButton", null);
        if (TextUtils.isEmpty(this.mPassword.getText().toString()) || TextUtils.isEmpty(this.mNewEmailEditText.getText().toString()) || TextUtils.isEmpty(this.mNewEmailVerifyEditText.getText().toString())) {
            this.mSaveButton.setEnabled(false);
        } else {
            this.mSaveButton.setEnabled(true);
        }
    }

    private void onSave() {
        Ensighten.evaluateEvent(this, "onSave", null);
        String emailUpdate = this.mNewEmailEditText.getText().toString().toLowerCase();
        String password = this.mPassword.getText().toString();
        if (emailUpdate.equalsIgnoreCase(this.mOriginalEmail)) {
            UIUtils.showGlobalAlertDialog(getNavigationActivity(), null, getString(C2658R.string.email_already_registered), null);
            DataLayerManager.getInstance().recordError("Email address invalid");
            return;
        }
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Email");
        UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.dialog_changing_username);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mCustomerProfile = this.mCustomerModule.getCurrentProfile();
        final String existingEmail = this.mCustomerProfile.getEmailAddress();
        final String existingUserName = this.mCustomerProfile.getUserName();
        if (this.mCustomerProfile.getUserName().equals(this.mCustomerProfile.getEmailAddress())) {
            this.mUsernameEqualsEmail = true;
        }
        if (!emailUpdate.isEmpty()) {
            this.mCustomerProfile.setEmailAddress(emailUpdate);
            this.mCustomerProfile.setPassword(password);
            if (this.mUsernameEqualsEmail) {
                this.mCustomerProfile.setNewUserName(emailUpdate);
            }
            this.mCustomerModule.updateCustomerProfile(this.mCustomerProfile, new AsyncListener<CustomerProfile>() {

                /* renamed from: com.mcdonalds.app.account.ChangeEmailFragment$3$1 */
                class C29321 implements AsyncListener<Void> {
                    C29321() {
                    }

                    public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        if (ChangeEmailFragment.this.getNavigationActivity() != null) {
                            if (exception != null) {
                                AsyncException.report(exception);
                                return;
                            }
                            ((NavigationDrawerFragment) ChangeEmailFragment.this.getNavigationActivity().getSupportFragmentManager().findFragmentById(C2358R.C2357id.navigation_drawer)).setLoggedInDrawerState(false);
                            DataLayerManager.getInstance().setUser(null, "Signed-out", AppUtils.getCurrentMenuType());
                            UIUtils.stopActivityIndicator();
                            Bundle b = new Bundle();
                            b.putBoolean("show_email_changed_message", true);
                            ChangeEmailFragment.this.startActivity(MainActivity.class, b);
                        }
                    }
                }

                public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    UIUtils.stopActivityIndicator();
                    if (exception != null) {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeEmailFragment", "access$300", new Object[]{ChangeEmailFragment.this}).setEmailAddress(existingEmail);
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeEmailFragment", "access$300", new Object[]{ChangeEmailFragment.this}).setNewUserName(existingUserName);
                        UIUtils.showGlobalAlertDialog(ChangeEmailFragment.this.getNavigationActivity(), null, exception.getLocalizedMessage(), null);
                    } else if (ChangeEmailFragment.this.getNavigationActivity() != null) {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeEmailFragment", "access$400", new Object[]{ChangeEmailFragment.this}).logout(new C29321());
                    }
                }
            });
        }
    }
}
