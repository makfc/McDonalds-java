package com.mcdonalds.app.gmalite.account;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.facebook.internal.ServerProtocol;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.account.ChangeBirthdateActivity;
import com.mcdonalds.app.account.ChangeBirthdateFragment;
import com.mcdonalds.app.account.ChangeGenderActivity;
import com.mcdonalds.app.account.ChangeGenderFragment;
import com.mcdonalds.app.gmalite.customer.LiteEmailVerificationActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.app.web.WebActivity;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.customer.CustomerProfile.AccountDeleteType;
import com.mcdonalds.sdk.modules.customer.CustomerProfile.AccountVerificationType;
import com.mcdonalds.sdk.services.log.MCDLog;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

public class LiteAccountProfileFragment extends URLNavigationFragment implements OnClickListener, Observer {
    private TextView mBirthdateButton;
    private View mChangePassword;
    private CustomerModule mCustomerModule;
    private TextView mEmail;
    private TextView mGender;
    private TextView mMobile;
    private OnClickListener mOnClickCustomerSupport = new C31221();
    private DialogInterface.OnClickListener mOnClickDisableAccount = new C31263();
    private boolean mReady;
    private Button mSignOutButton;
    private TextView mUserName;
    private TextView mZipCode;

    /* renamed from: com.mcdonalds.app.gmalite.account.LiteAccountProfileFragment$1 */
    class C31221 implements OnClickListener {
        C31221() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(LiteAccountProfileFragment.this.getAnalyticsTitle(), "Customer Support");
            String link = ConfigurationUtils.getCustomerSupportUrl();
            if (link != null) {
                Bundle attributes = new Bundle();
                attributes.putString("link", link);
                attributes.putString("analytics_title", LiteAccountProfileFragment.this.getString(C2658R.string.analytics_screen_customer_support));
                LiteAccountProfileFragment.this.getNavigationActivity().startActivity(WebActivity.class, "web", attributes);
                return;
            }
            AsyncException.report("No Customer Support URL Defined");
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.account.LiteAccountProfileFragment$2 */
    class C31232 implements DialogInterface.OnClickListener {
        C31232() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.account.LiteAccountProfileFragment$3 */
    class C31263 implements DialogInterface.OnClickListener {
        C31263() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            final CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            CustomerProfile newProfile = new CustomerProfile();
            newProfile.setDeactivateAccountTimeStamp(UIUtils.getCurrentTimestampUsingFormat("yyyy-MM-dd HH:mm:ss Z", "UTC"));
            UIUtils.startActivityIndicator(LiteAccountProfileFragment.this.getNavigationActivity(), LiteAccountProfileFragment.this.getString(C2658R.string.lite_dialog_acct_disable));
            customerModule.updateCustomerProfile(newProfile, new AsyncListener<CustomerProfile>() {

                /* renamed from: com.mcdonalds.app.gmalite.account.LiteAccountProfileFragment$3$1$1 */
                class C31241 implements AsyncListener<Void> {
                    C31241() {
                    }

                    public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        LiteAccountProfileFragment.this.getNavigationActivity().showFragment(ConfigurationUtils.getHomeScreenFragment());
                    }
                }

                public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    UIUtils.stopActivityIndicator();
                    if (exception == null) {
                        customerModule.logout(new C31241());
                    } else {
                        AsyncException.report(exception);
                    }
                }
            });
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.account.LiteAccountProfileFragment$4 */
    class C31274 implements DialogInterface.OnClickListener {
        C31274() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.account.LiteAccountProfileFragment$5 */
    class C31305 implements DialogInterface.OnClickListener {
        C31305() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            final CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            new CustomerProfile().setEmailAddress(customerModule.getCurrentProfile().getEmailAddress());
            UIUtils.startActivityIndicator(LiteAccountProfileFragment.this.getNavigationActivity(), LiteAccountProfileFragment.this.getString(C2658R.string.lite_dialog_acct_dlt));
            customerModule.deregister(null, new AsyncListener<String>() {

                /* renamed from: com.mcdonalds.app.gmalite.account.LiteAccountProfileFragment$5$1$1 */
                class C31281 implements AsyncListener<Void> {
                    C31281() {
                    }

                    public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        if (LiteAccountProfileFragment.this.getNavigationActivity() instanceof MainActivity) {
                            LiteAccountProfileFragment.this.getNavigationActivity().showFragment("dashboard");
                        } else {
                            LiteAccountProfileFragment.this.getNavigationActivity().finish();
                        }
                    }
                }

                public void onResponse(String response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    UIUtils.stopActivityIndicator();
                    if (exception == null) {
                        customerModule.logout(new C31281());
                    } else {
                        AsyncException.report(exception);
                    }
                }
            });
        }
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.lite_title_acct);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_account);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginManager.getInstance().loadRegisterConfig();
        LoginManager.getInstance().addObserver(this);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        configCustomerProfile();
    }

    public void onDestroy() {
        super.onDestroy();
        LoginManager.getInstance().deleteObserver(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_lite_account_profile, container, false);
        this.mUserName = (TextView) view.findViewById(C2358R.C2357id.user_name);
        this.mUserName.setOnClickListener(this);
        this.mBirthdateButton = (TextView) view.findViewById(C2358R.C2357id.account_birthdate);
        this.mBirthdateButton.setOnClickListener(this);
        this.mGender = (TextView) view.findViewById(C2358R.C2357id.account_gender);
        this.mGender.setOnClickListener(this);
        this.mEmail = (TextView) view.findViewById(2131821086);
        this.mEmail.setOnClickListener(this);
        this.mMobile = (TextView) view.findViewById(C2358R.C2357id.mobile);
        this.mChangePassword = view.findViewById(C2358R.C2357id.change_password);
        this.mChangePassword.setOnClickListener(this);
        this.mSignOutButton = (Button) view.findViewById(C2358R.C2357id.sign_out_btn);
        this.mSignOutButton.setOnClickListener(this);
        this.mZipCode = (TextView) view.findViewById(C2358R.C2357id.zip_code);
        configureDeleteAccount(view);
        ((TextView) view.findViewById(C2358R.C2357id.customer_support_link)).setOnClickListener(this.mOnClickCustomerSupport);
        return view;
    }

    private void configureDeleteAccount(View view) {
        Ensighten.evaluateEvent(this, "configureDeleteAccount", new Object[]{view});
        TextView deleteAccount = (TextView) view.findViewById(C2358R.C2357id.delete_acct);
        AccountDeleteType accountDeleteType = this.mCustomerModule.getCurrentProfile().getAccountDeleteType();
        if (accountDeleteType != AccountDeleteType.NONE) {
            deleteAccount.setVisibility(0);
            deleteAccount.setOnClickListener(this);
            switch (accountDeleteType) {
                case DELETE:
                    deleteAccount.setText(getString(C2658R.string.lite_btn_dlt_acct));
                    return;
                case DEACTIVATE:
                    deleteAccount.setText(getString(C2658R.string.lite_btn_disable_acct));
                    return;
                default:
                    return;
            }
        }
    }

    public void onResume() {
        super.onResume();
        populateUserFields(LoginManager.getInstance().getProfile());
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CustomerProfile customerProfile = LoginManager.getInstance().getProfile();
        if (customerProfile != null) {
            config(customerProfile);
        } else {
            this.mReady = true;
        }
    }

    private void config(CustomerProfile customerProfile) {
        Ensighten.evaluateEvent(this, "config", new Object[]{customerProfile});
        display(this.mZipCode, "zipcode");
        display(this.mMobile, "phoneNumber");
        if (customerProfile != null) {
            populateUserFields(customerProfile);
        }
    }

    private void display(View field, String name) {
        Ensighten.evaluateEvent(this, ServerProtocol.DIALOG_PARAM_DISPLAY, new Object[]{field, name});
        if (LoginManager.getInstance().showField(name)) {
            field.setOnClickListener(this);
        } else {
            field.setVisibility(8);
        }
    }

    public void onClick(View view) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
        if (getNavigationActivity() == null) {
            return;
        }
        if (view == this.mSignOutButton) {
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Sign Out");
            Bundle extras = new Bundle();
            extras.putBoolean(URLNavigationActivity.MODAL, true);
            showFragment("signout", extras);
            return;
        }
        navigateToSection(view.getId());
    }

    private void navigateToSection(int id) {
        Ensighten.evaluateEvent(this, "navigateToSection", new Object[]{new Integer(id)});
        String fragmentName = null;
        Class<? extends URLNavigationActivity> container = null;
        int requestCode = 666;
        switch (id) {
            case C2358R.C2357id.user_name /*2131821083*/:
                fragmentName = LiteChangeUserNameFragment.NAME;
                container = LiteChangeUserNameActivity.class;
                requestCode = 2001;
                break;
            case C2358R.C2357id.account_gender /*2131821084*/:
                fragmentName = ChangeGenderFragment.NAME;
                container = ChangeGenderActivity.class;
                requestCode = 2008;
                break;
            case C2358R.C2357id.account_birthdate /*2131821085*/:
                fragmentName = ChangeBirthdateFragment.NAME;
                container = ChangeBirthdateActivity.class;
                requestCode = 2007;
                break;
            case 2131821086:
                fragmentName = "liteverifyemail";
                container = LiteEmailVerificationActivity.class;
                requestCode = 2002;
                break;
            case C2358R.C2357id.mobile /*2131821087*/:
                fragmentName = LiteChangeMobileFragment.NAME;
                container = LiteChangeMobileActivity.class;
                requestCode = 2005;
                break;
            case C2358R.C2357id.change_password /*2131821088*/:
                fragmentName = LiteChangePasswordFragment.NAME;
                container = LiteChangePasswordActivity.class;
                requestCode = 2003;
                break;
            case C2358R.C2357id.zip_code /*2131821092*/:
                fragmentName = LiteChangeZipCodeFragment.NAME;
                container = LiteChangeZipCodeActivity.class;
                requestCode = 2009;
                break;
            case C2358R.C2357id.delete_acct /*2131821095*/:
                deleteAccount();
                fragmentName = null;
                break;
        }
        if (fragmentName == null) {
            return;
        }
        if (requestCode != 666) {
            startActivityForResult((Class) container, fragmentName, requestCode);
        } else {
            startActivity((Class) container, fragmentName);
        }
    }

    private void deleteAccount() {
        Ensighten.evaluateEvent(this, "deleteAccount", null);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Account Delete");
        switch (this.mCustomerModule.getCurrentProfile().getAccountDeleteType()) {
            case DELETE:
                deleteAccountHard();
                return;
            case DEACTIVATE:
                deleteAccountSoft();
                return;
            default:
                MCDLog.debug("Invalid Account Delete Type");
                return;
        }
    }

    private void deleteAccountSoft() {
        Ensighten.evaluateEvent(this, "deleteAccountSoft", null);
        MCDLog.debug("Clicked Deactivate Account SOFT");
        MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle(getString(C2658R.string.lite_alrt_acct_disable)).setCancelable(true).setMessage(getString(C2658R.string.lite_alrt_msg_acct_disable)).setPositiveButton(getString(C2658R.string.lite_alrt_btn_disable), this.mOnClickDisableAccount).setNegativeButton(getString(C2658R.string.lite_alrt_btn_cancel), new C31232()).create().show();
    }

    private void deleteAccountHard() {
        Ensighten.evaluateEvent(this, "deleteAccountHard", null);
        MCDLog.debug("Clicked Delete Account HARD");
        MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle(getString(C2658R.string.lite_alrt_acct_dlt)).setCancelable(true).setMessage(getString(C2658R.string.lite_alrt_msg_acct_dlt)).setPositiveButton(getString(C2658R.string.lite_alrt_btn_dlt), new C31305()).setNegativeButton(getString(C2658R.string.lite_alrt_btn_cancel), new C31274()).create().show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == -1) {
            switch (requestCode) {
                case 2001:
                    String firstName = data.getStringExtra("first");
                    String lastName = data.getStringExtra("last");
                    String fullName = "";
                    if (!(firstName == null || firstName.isEmpty())) {
                        fullName = firstName;
                    }
                    if (!(lastName == null || lastName.isEmpty())) {
                        fullName = fullName + " " + lastName;
                    }
                    this.mUserName.setText(fullName);
                    return;
                case 2002:
                    updateEmailField();
                    return;
                case 2005:
                    this.mMobile.setText(data.getStringExtra("mobile"));
                    return;
                case 2007:
                    this.mBirthdateButton.setText(data.getStringExtra("birthdate"));
                    return;
                case 2008:
                    this.mGender.setText(data.getStringExtra("gender"));
                    return;
                case 2009:
                    this.mZipCode.setText(data.getStringExtra("zipCode"));
                    return;
                default:
                    return;
            }
        }
    }

    private void populateUserFields(CustomerProfile customerProfile) {
        Ensighten.evaluateEvent(this, "populateUserFields", new Object[]{customerProfile});
        String name = customerProfile.getFirstName();
        if (!LoginManager.getInstance().getRegisterSettings().isSingleFieldName()) {
            name = String.format("%s %s", new Object[]{name, customerProfile.getLastName()});
        }
        if (LoginManager.getInstance().showField("firstname")) {
            this.mUserName.setVisibility(0);
            this.mUserName.setText(name);
        }
        if (LoginManager.getInstance().showField("emailaddress")) {
            this.mEmail.setVisibility(0);
            this.mEmail.setText(customerProfile.getEmailAddress());
            updateEmailField();
        }
        if (LoginManager.getInstance().showField("phoneNumber")) {
            this.mMobile.setVisibility(0);
            this.mMobile.setText(customerProfile.getMobileNumber());
            if (customerProfile.getVerificationType() != AccountVerificationType.SMS || customerProfile.isMobileVerified()) {
                this.mMobile.setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.selector_arrow_gray, 0);
            } else {
                this.mMobile.setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
            }
        }
        if (LoginManager.getInstance().showField("birthDate")) {
            this.mBirthdateButton.setVisibility(0);
            DateFormat dateFormat = DateFormat.getDateInstance(1, Locale.getDefault());
            Calendar birthdate = LoginManager.getInstance().getProfile().getBirthDate();
            if (birthdate != null) {
                this.mBirthdateButton.setText(dateFormat.format(birthdate.getTime()));
            } else {
                this.mBirthdateButton.setText(getResources().getString(C2658R.string.lite_hint_birthdate));
            }
        }
        if (LoginManager.getInstance().showField("gender")) {
            this.mGender.setVisibility(0);
            String gender = (customerProfile.getGender() == null || TextUtils.isEmpty(customerProfile.getGender())) ? getResources().getString(C2658R.string.signup_gender) : customerProfile.getGender();
            this.mGender.setText(gender.substring(0, 1).toUpperCase() + gender.substring(1));
            this.mGender.invalidate();
        }
        if (LoginManager.getInstance().showField("zipcode")) {
            this.mZipCode.setVisibility(0);
            this.mZipCode.setText(TextUtils.isEmpty(customerProfile.getZipCode()) ? getString(C2658R.string.lite_hint_zip) : customerProfile.getZipCode());
        }
    }

    private void configCustomerProfile() {
        Ensighten.evaluateEvent(this, "configCustomerProfile", null);
        CustomerProfile customerProfile = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentProfile();
        LoginManager.getInstance().setProfile(customerProfile);
        if (this.mReady) {
            config(customerProfile);
        }
    }

    public void update(Observable observable, Object data) {
        Ensighten.evaluateEvent(this, "update", new Object[]{observable, data});
        populateUserFields(LoginManager.getInstance().getProfile());
    }

    private void updateEmailField() {
        Ensighten.evaluateEvent(this, "updateEmailField", null);
        AccountVerificationType verificationType = this.mCustomerModule.getCurrentProfile().getVerificationType();
        boolean emailVerified = this.mCustomerModule.getCurrentProfile().isEmailVerified();
        if (verificationType != AccountVerificationType.EMAIL || emailVerified) {
            this.mEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            this.mEmail.setClickable(false);
            return;
        }
        this.mEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
        this.mEmail.setClickable(true);
    }
}
