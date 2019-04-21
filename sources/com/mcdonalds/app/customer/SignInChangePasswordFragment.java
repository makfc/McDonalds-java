package com.mcdonalds.app.customer;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.account.ProfileUpdateActivity;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.customer.push.NotificationManager;
import com.mcdonalds.app.p043ui.NavigationDrawerFragment;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.services.log.MCDLog;

public class SignInChangePasswordFragment extends URLNavigationFragment {
    private CustomerProfile mCustomerProfile = null;
    private boolean mInitialPasswordCriteriaMet = false;
    private EditText mNewPasswordEditText;
    URLNavigationActivity mParent;
    private String mPassword;
    private Button mSubmitButton;
    private String mUserName;
    private boolean mVerifyPasswordCriteriaMet = false;
    private EditText mVerifyPasswordEditText;

    /* renamed from: com.mcdonalds.app.customer.SignInChangePasswordFragment$1 */
    class C30231 implements OnClickListener {
        C30231() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(SignInChangePasswordFragment.this.getAnalyticsTitle(), "Forgot password");
            if (LoginManager.getInstance().getRegisterSettings().chooseSignInMethodEnabled()) {
                SignInChangePasswordFragment.this.startActivity(ProfileUpdateActivity.class, "reset_password");
            } else {
                SignInChangePasswordFragment.this.startActivity(LostPasswordActivity.class);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignInChangePasswordFragment$2 */
    class C30242 implements TextWatcher {
        C30242() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{s, new Integer(start), new Integer(count), new Integer(after)});
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{s, new Integer(start), new Integer(before), new Integer(count)});
        }

        public void afterTextChanged(Editable s) {
            Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{s});
            SignInChangePasswordFragment.access$102(SignInChangePasswordFragment.this, UIUtils.isPasswordValid(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$000", new Object[]{SignInChangePasswordFragment.this}).getText().toString()));
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$200", new Object[]{SignInChangePasswordFragment.this})) {
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$300", new Object[]{SignInChangePasswordFragment.this})) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$000", new Object[]{SignInChangePasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_valid, 0);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$400", new Object[]{SignInChangePasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_valid, 0);
                } else {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$000", new Object[]{SignInChangePasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
                }
            } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$100", new Object[]{SignInChangePasswordFragment.this})) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$000", new Object[]{SignInChangePasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_valid, 0);
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$000", new Object[]{SignInChangePasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
                DataLayerManager.getInstance().recordError("Password invalid");
            }
            SignInChangePasswordFragment.access$500(SignInChangePasswordFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignInChangePasswordFragment$3 */
    class C30253 implements TextWatcher {
        C30253() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{s, new Integer(start), new Integer(count), new Integer(after)});
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{s, new Integer(start), new Integer(before), new Integer(count)});
        }

        public void afterTextChanged(Editable s) {
            Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{s});
            SignInChangePasswordFragment.access$202(SignInChangePasswordFragment.this, UIUtils.isPasswordValid(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$400", new Object[]{SignInChangePasswordFragment.this}).getText().toString()));
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$100", new Object[]{SignInChangePasswordFragment.this})) {
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$300", new Object[]{SignInChangePasswordFragment.this})) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$400", new Object[]{SignInChangePasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_valid, 0);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$000", new Object[]{SignInChangePasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_valid, 0);
                } else {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$400", new Object[]{SignInChangePasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
                }
            } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$200", new Object[]{SignInChangePasswordFragment.this})) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$400", new Object[]{SignInChangePasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_valid, 0);
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$400", new Object[]{SignInChangePasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
                DataLayerManager.getInstance().recordError("Password invalid");
            }
            SignInChangePasswordFragment.access$500(SignInChangePasswordFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignInChangePasswordFragment$4 */
    class C30264 implements OnClickListener {
        C30264() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            SignInChangePasswordFragment.access$600(SignInChangePasswordFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignInChangePasswordFragment$5 */
    class C30275 implements OnClickListener {
        C30275() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(SignInChangePasswordFragment.this.getAnalyticsTitle(), "Start Registration");
            if (AppUtils.hideTermsAndConditionsView()) {
                SignInChangePasswordFragment.this.startActivity(SignUpActivity.class);
            } else {
                SignInChangePasswordFragment.this.startActivity(TermsOfServiceActivity.class);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignInChangePasswordFragment$6 */
    class C30296 implements AsyncListener<CustomerProfile> {

        /* renamed from: com.mcdonalds.app.customer.SignInChangePasswordFragment$6$1 */
        class C30281 implements AsyncListener<Boolean> {
            C30281() {
            }

            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (AppUtils.isTrue(response)) {
                    MCDLog.info("SignInChangePasswordFragment: Notification registration SUCCESS");
                } else {
                    MCDLog.info("SignInChangePasswordFragment: Notification registration FAILURE");
                }
            }
        }

        C30296() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (SignInChangePasswordFragment.this.getNavigationActivity() != null) {
                UIUtils.stopActivityIndicator();
                if (exception != null) {
                    AsyncException.report(exception);
                    return;
                }
                NotificationManager.register(new C30281());
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$700", new Object[]{SignInChangePasswordFragment.this}).setPasswordChangeRequired(false);
                DataLayerManager.getInstance().setUser(response, "Signed-in", AppUtils.getCurrentMenuType());
                ((NavigationDrawerFragment) SignInChangePasswordFragment.this.getNavigationActivity().getSupportFragmentManager().findFragmentById(C2358R.C2357id.navigation_drawer)).setLoggedInDrawerState(true);
                SignInChangePasswordFragment.this.startActivity(MainActivity.class);
            }
        }
    }

    static /* synthetic */ boolean access$102(SignInChangePasswordFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$102", new Object[]{x0, new Boolean(x1)});
        x0.mInitialPasswordCriteriaMet = x1;
        return x1;
    }

    static /* synthetic */ boolean access$202(SignInChangePasswordFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$202", new Object[]{x0, new Boolean(x1)});
        x0.mVerifyPasswordCriteriaMet = x1;
        return x1;
    }

    static /* synthetic */ void access$500(SignInChangePasswordFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$500", new Object[]{x0});
        x0.areBothPasswordsValid();
    }

    static /* synthetic */ void access$600(SignInChangePasswordFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInChangePasswordFragment", "access$600", new Object[]{x0});
        x0.onSubmitDoChangePassword();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mParent = getNavigationActivity();
        this.mCustomerProfile = LoginManager.getInstance().getProfile();
        if (this.mCustomerProfile == null) {
            this.mCustomerProfile = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentProfile();
        }
        this.mUserName = this.mCustomerProfile.getUserName();
        this.mPassword = this.mCustomerProfile.getPassword();
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.sign_in, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        return false;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_sign_in_new_password, container, false);
        ((TextView) rootView.findViewById(C2358R.C2357id.signin_textview_email)).setText(this.mUserName);
        ((TextView) rootView.findViewById(C2358R.C2357id.signin_textview_password)).setText(this.mPassword);
        TextView forget_password = (TextView) rootView.findViewById(C2358R.C2357id.signin_textview_email);
        forget_password.getPaint().setFlags(8);
        forget_password.setOnClickListener(new C30231());
        this.mNewPasswordEditText = (EditText) rootView.findViewById(C2358R.C2357id.signin_edittext_newPassword);
        this.mNewPasswordEditText.addTextChangedListener(new C30242());
        this.mVerifyPasswordEditText = (EditText) rootView.findViewById(C2358R.C2357id.signin_edittext_newPassword_confirm);
        this.mVerifyPasswordEditText.addTextChangedListener(new C30253());
        this.mSubmitButton = (Button) rootView.findViewById(C2358R.C2357id.signin_button_submit);
        this.mSubmitButton.setOnClickListener(new C30264());
        ((Button) rootView.findViewById(C2358R.C2357id.need_an_account)).setOnClickListener(new C30275());
        return rootView;
    }

    public void onPause() {
        super.onPause();
        UIUtils.stopActivityIndicator();
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getResources().getString(C2658R.string.title_activity_signin);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_update_password);
    }

    private void onSubmitDoChangePassword() {
        Ensighten.evaluateEvent(this, "onSubmitDoChangePassword", null);
        String newPassword = this.mVerifyPasswordEditText.getText().toString().trim();
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "New Password");
        if (!this.mNewPasswordEditText.getText().toString().equals(this.mVerifyPasswordEditText.getText().toString())) {
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setMessage((int) C2658R.string.passwords_do_not_match).setPositiveButton((int) C2658R.string.f6083ok, null).create().show();
            DataLayerManager.getInstance().recordError("Password doesn't match existing");
        } else if (AppUtils.isNetworkConnected(getNavigationActivity())) {
            UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.dialog_changing_password);
            CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            AuthenticationParameters parameters = new AuthenticationParameters();
            parameters.setUserName(this.mCustomerProfile.getUserName());
            parameters.setPassword(this.mCustomerProfile.getPassword());
            parameters.setNewPassword(newPassword);
            customerModule.authenticate(parameters, new C30296());
        } else {
            UIUtils.showNoNetworkAlert(getNavigationActivity());
        }
    }

    private boolean checkPwdMatch() {
        Ensighten.evaluateEvent(this, "checkPwdMatch", null);
        if (this.mNewPasswordEditText == null || this.mVerifyPasswordEditText == null) {
            return false;
        }
        return this.mNewPasswordEditText.getText().toString().equals(this.mVerifyPasswordEditText.getText().toString());
    }

    private void areBothPasswordsValid() {
        Ensighten.evaluateEvent(this, "areBothPasswordsValid", null);
        Button button = this.mSubmitButton;
        boolean z = this.mInitialPasswordCriteriaMet && this.mVerifyPasswordCriteriaMet && checkPwdMatch();
        button.setEnabled(z);
    }
}
