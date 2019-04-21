package com.mcdonalds.app.account;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.p043ui.NavigationDrawerFragment;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.app.widget.ValidationListener;
import com.mcdonalds.app.widget.ValidationListener.Callback;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;

public class ChangePasswordFragment extends URLNavigationFragment {
    public static final String NAME = ChangePasswordFragment.class.getSimpleName();
    private final String TIME_DISMISS_ALERT_VIEW_KEY = "timeDismissAlertView";
    private CustomerProfile mCustomerProfile;
    private EditText mNewPasswordEditText;
    private final OnClickListener mOnClickDialog = new C29454();
    private EditText mOriginal;
    private TextView mPasswordErrorText;
    private final InputFilter mPasswordInputFilter = new C29465();
    private Button mSaveButton;
    private Callback mValidationCallback = new C29476();
    private SparseArray<ValidationListener> mValidations = new SparseArray();
    private EditText mVerifyPasswordEditText;

    /* renamed from: com.mcdonalds.app.account.ChangePasswordFragment$1 */
    class C29421 implements View.OnClickListener {
        C29421() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            ChangePasswordFragment.access$000(ChangePasswordFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.account.ChangePasswordFragment$2 */
    class C29432 implements AsyncListener<CustomerProfile> {
        C29432() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception == null && ChangePasswordFragment.this.getNavigationActivity() != null) {
                ChangePasswordFragment.access$200(ChangePasswordFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangePasswordFragment", "access$100", new Object[]{ChangePasswordFragment.this}).getText().toString());
                ChangePasswordFragment.this.getNavigationActivity().setResult(-1);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangePasswordFragment", "access$300", new Object[]{ChangePasswordFragment.this}).setPasswordChangeRequired(false);
                UIUtils.stopActivityIndicator();
                MCDAlertDialogBuilder.withContext(ChangePasswordFragment.this.getNavigationActivity()).setMessage(ChangePasswordFragment.this.getResources().getString(C2658R.string.text_password_updated)).setPositiveButton((int) C2658R.string.f6083ok, null).create().show();
                ((NavigationDrawerFragment) ChangePasswordFragment.this.getNavigationActivity().getSupportFragmentManager().findFragmentById(C2358R.C2357id.navigation_drawer)).setLoggedInDrawerState(true);
                ChangePasswordFragment.this.startActivity(MainActivity.class);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.account.ChangePasswordFragment$4 */
    class C29454 implements OnClickListener {
        C29454() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            if (ChangePasswordFragment.this.getActivity() != null && !ChangePasswordFragment.this.getActivity().isFinishing()) {
                ChangePasswordFragment.this.getActivity().setResult(-1);
                ChangePasswordFragment.this.getActivity().finish();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.account.ChangePasswordFragment$5 */
    class C29465 implements InputFilter {
        C29465() {
        }

        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Ensighten.evaluateEvent(this, Parameters.FILTER, new Object[]{source, new Integer(start), new Integer(end), dest, new Integer(dstart), new Integer(dend)});
            for (int i = start; i < end; i++) {
                if (Character.isWhitespace(source.charAt(i))) {
                    return "";
                }
            }
            return null;
        }
    }

    /* renamed from: com.mcdonalds.app.account.ChangePasswordFragment$6 */
    class C29476 implements Callback {
        C29476() {
        }

        public void onFieldValidationStateChanged(boolean isValidated) {
            Ensighten.evaluateEvent(this, "onFieldValidationStateChanged", new Object[]{new Boolean(isValidated)});
            ChangePasswordFragment.access$500(ChangePasswordFragment.this);
        }
    }

    static /* synthetic */ void access$000(ChangePasswordFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangePasswordFragment", "access$000", new Object[]{x0});
        x0.onSave();
    }

    static /* synthetic */ void access$200(ChangePasswordFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangePasswordFragment", "access$200", new Object[]{x0, x1});
        x0.persistPassword(x1);
    }

    static /* synthetic */ void access$500(ChangePasswordFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangePasswordFragment", "access$500", new Object[]{x0});
        x0.validateData();
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.title_change_password);
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
        View rootView = inflater.inflate(C2658R.layout.fragment_change_password_verify, container, false);
        this.mOriginal = (EditText) rootView.findViewById(C2358R.C2357id.existing_inc);
        this.mNewPasswordEditText = (EditText) rootView.findViewById(C2358R.C2357id.new_inc);
        this.mVerifyPasswordEditText = (EditText) rootView.findViewById(C2358R.C2357id.new_verify_inc);
        this.mPasswordErrorText = (TextView) rootView.findViewById(C2358R.C2357id.passwords_error);
        addValidation(this.mOriginal, 1);
        addPasswordValidation();
        InputFilter[] passwordFilters = new InputFilter[]{this.mPasswordInputFilter};
        this.mOriginal.setFilters(passwordFilters);
        this.mNewPasswordEditText.setFilters(passwordFilters);
        this.mVerifyPasswordEditText.setFilters(passwordFilters);
        this.mSaveButton = (Button) rootView.findViewById(C2358R.C2357id.save_btn);
        this.mSaveButton.setEnabled(false);
        this.mSaveButton.setOnClickListener(new C29421());
        TextView mPasswordDescription = (TextView) rootView.findViewById(C2358R.C2357id.password_description);
        if (Configuration.getSharedInstance().getMarket().equals("CN")) {
            mPasswordDescription.setText(C2658R.string.password_description_china);
        }
        return rootView;
    }

    private void addPasswordValidation() {
        Ensighten.evaluateEvent(this, "addPasswordValidation", null);
        ValidationListener passwordValidationListener = new ValidationListener(this.mNewPasswordEditText, this.mVerifyPasswordEditText, this.mPasswordErrorText, 10, false);
        addValidation(passwordValidationListener);
        this.mVerifyPasswordEditText.addTextChangedListener(passwordValidationListener);
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
        if (!this.mCustomerProfile.getPassword().equals(this.mOriginal.getText().toString())) {
            UIUtils.showGlobalAlertDialog(getNavigationActivity(), getString(C2658R.string.please_correct), getString(C2658R.string.current_pwd_does_not_match_existing), null);
            DataLayerManager.getInstance().recordError("Password doesn't match existing");
        } else if (this.mOriginal.getText().toString().equals(this.mNewPasswordEditText.getText().toString())) {
            UIUtils.showGlobalAlertDialog(getNavigationActivity(), getString(C2658R.string.please_correct), getString(C2658R.string.new_pwd_must_be_different_from_previous), null);
            DataLayerManager.getInstance().recordError("New password must be different than previous password");
        } else {
            final String newPassword = this.mVerifyPasswordEditText.getText().toString();
            if (AppUtils.isNetworkConnected(getNavigationActivity())) {
                UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.dialog_changing_password);
                CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
                if (this.mCustomerProfile == null) {
                    this.mCustomerProfile = customerModule.getCurrentProfile();
                }
                if (this.mCustomerProfile == null || !this.mCustomerProfile.isPasswordChangeRequired()) {
                    AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Password");
                    customerModule.changePassword(newPassword, null, new AsyncListener<Void>() {
                        public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                            if (exception == null && ChangePasswordFragment.this.getNavigationActivity() != null) {
                                ChangePasswordFragment.access$200(ChangePasswordFragment.this, newPassword);
                                MCDAlertDialogBuilder.withContext(ChangePasswordFragment.this.getNavigationActivity()).setTitle((int) C2658R.string.dialog_title_success).setMessage(ChangePasswordFragment.this.getResources().getString(C2658R.string.text_password_updated)).setPositiveButton((int) C2658R.string.f6083ok, Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangePasswordFragment", "access$400", new Object[]{ChangePasswordFragment.this})).setDelayToDismissInSeconds(Integer.valueOf(Configuration.getSharedInstance().getIntForKey("timeDismissAlertView")), Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangePasswordFragment", "access$400", new Object[]{ChangePasswordFragment.this})).create().show();
                            }
                            UIUtils.stopActivityIndicator();
                        }
                    });
                    return;
                }
                AuthenticationParameters parameters = new AuthenticationParameters();
                parameters.setUserName(this.mCustomerProfile.getUserName());
                parameters.setPassword(this.mCustomerProfile.getPassword());
                parameters.setNewPassword(newPassword);
                customerModule.authenticate(parameters, new C29432());
                return;
            }
            UIUtils.showNoNetworkAlert(getNavigationActivity());
        }
    }

    private void persistPassword(String newPassword) {
        Ensighten.evaluateEvent(this, "persistPassword", new Object[]{newPassword});
        this.mCustomerProfile.setPassword(newPassword);
        LocalDataManager.getSharedInstance().setPrefSavedLoginPass(newPassword);
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
        EditText validationEditText = validation.getTextField();
        validationEditText.addTextChangedListener(validation);
        validationEditText.setOnEditorActionListener(onDoneKeyPressed(validation));
        validation.setValidationCallback(this.mValidationCallback);
        this.mValidations.put(validationEditText.getId(), validation);
        return validation;
    }

    private void validateData() {
        Ensighten.evaluateEvent(this, "validateData", null);
        this.mSaveButton.setEnabled(false);
        int i = 0;
        int size = this.mValidations.size();
        while (i < size) {
            if (((ValidationListener) this.mValidations.get(this.mValidations.keyAt(i))).isValidated()) {
                i++;
            } else {
                return;
            }
        }
        this.mSaveButton.setEnabled(true);
    }

    @NonNull
    private OnEditorActionListener onDoneKeyPressed(final ValidationListener validation) {
        Ensighten.evaluateEvent(this, "onDoneKeyPressed", new Object[]{validation});
        return new OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Ensighten.evaluateEvent(this, "onEditorAction", new Object[]{v, new Integer(actionId), event});
                if (actionId != 6) {
                    return false;
                }
                if (!validation.isValidated()) {
                    validation.displayError();
                    return true;
                } else if (v.getId() != Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangePasswordFragment", "access$600", new Object[]{ChangePasswordFragment.this}).getId()) {
                    return true;
                } else {
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangePasswordFragment", "access$700", new Object[]{ChangePasswordFragment.this}).isEnabled()) {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangePasswordFragment", "access$700", new Object[]{ChangePasswordFragment.this}).callOnClick();
                        return true;
                    }
                    int i = 0;
                    while (i < Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangePasswordFragment", "access$800", new Object[]{ChangePasswordFragment.this}).size()) {
                        ValidationListener listener = (ValidationListener) Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangePasswordFragment", "access$800", new Object[]{ChangePasswordFragment.this}).get(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangePasswordFragment", "access$800", new Object[]{ChangePasswordFragment.this}).keyAt(i));
                        if (listener.isValidated()) {
                            i++;
                        } else {
                            listener.getTextField().requestFocus();
                            listener.displayError();
                            return true;
                        }
                    }
                    return true;
                }
            }
        };
    }
}
