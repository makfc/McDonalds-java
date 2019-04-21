package com.mcdonalds.app.account;

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
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.delivery.DeliveryModule;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

public class AccountProfileFragment extends URLNavigationFragment implements OnClickListener, Observer {
    private final String SUPPORT_ZIP_CODE_KEY = "interface.register.supportsZipCode";
    private TextView mAccountDelete;
    private View mAddresses;
    private TextView mBirthdateButton;
    private View mChangePassword;
    private TextView mEmail;
    private TextView mGender;
    private TextView mMobile;
    private boolean mReady;
    private View mSavedCards;
    private Button mSignOutButton;
    private TextView mUserName;
    private View mZipCode;

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.title_activity_account);
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
        configCustomerProfile();
    }

    public void onDestroy() {
        super.onDestroy();
        LoginManager.getInstance().deleteObserver(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_account_profile, container, false);
        this.mUserName = (TextView) view.findViewById(C2358R.C2357id.user_name);
        this.mUserName.setOnClickListener(this);
        this.mBirthdateButton = (TextView) view.findViewById(C2358R.C2357id.account_birthdate);
        this.mBirthdateButton.setOnClickListener(this);
        this.mGender = (TextView) view.findViewById(C2358R.C2357id.account_gender);
        this.mGender.setOnClickListener(this);
        this.mEmail = (TextView) view.findViewById(2131821086);
        this.mEmail.setOnClickListener(this);
        this.mMobile = (TextView) view.findViewById(C2358R.C2357id.mobile);
        this.mMobile.setOnClickListener(this);
        this.mAccountDelete = (TextView) view.findViewById(C2358R.C2357id.delete_acct);
        this.mChangePassword = view.findViewById(C2358R.C2357id.change_password);
        this.mChangePassword.setOnClickListener(this);
        this.mAddresses = view.findViewById(C2358R.C2357id.addresses);
        if (Configuration.getSharedInstance().getBooleanForKey("modules.delivery.sendToDeliveryWebsite")) {
            this.mAddresses.setVisibility(8);
        } else {
            this.mAddresses.setOnClickListener(this);
        }
        this.mSavedCards = view.findViewById(C2358R.C2357id.saved_cards);
        String checkoutFlow = Configuration.getSharedInstance().getStringForKey("interface.checkoutFlow");
        if (checkoutFlow == null || !checkoutFlow.equals("one_time_payment_flow")) {
            this.mSavedCards.setOnClickListener(this);
        } else {
            this.mSavedCards.setVisibility(8);
        }
        this.mSignOutButton = (Button) view.findViewById(C2358R.C2357id.sign_out_btn);
        this.mSignOutButton.setOnClickListener(this);
        this.mZipCode = view.findViewById(C2358R.C2357id.zip_code);
        if (!Configuration.getSharedInstance().getBooleanForKey("interface.register.chooseEmailOrPhoneAsUsername") || Configuration.getSharedInstance().getBooleanForKey("interface.useMobileNumberRegisterOnly")) {
            view.findViewById(C2358R.C2357id.signin_prefs).setVisibility(8);
        } else {
            view.findViewById(C2358R.C2357id.signin_prefs).setOnClickListener(this);
            this.mMobile.setOnClickListener(this);
        }
        view.findViewById(C2358R.C2357id.offer_pref).setOnClickListener(this);
        if (Configuration.getSharedInstance().hasKey("interface.availableCommunicationPreferences")) {
            view.findViewById(C2358R.C2357id.comm_pref).setVisibility(0);
            view.findViewById(C2358R.C2357id.comm_pref).setOnClickListener(this);
        } else {
            view.findViewById(C2358R.C2357id.comm_pref).setVisibility(8);
        }
        view.findViewById(C2358R.C2357id.delete_acct).setOnClickListener(this);
        toggleAccountFields();
        return view;
    }

    private void toggleAccountFields() {
        Ensighten.evaluateEvent(this, "toggleAccountFields", null);
        if (getArguments().getBoolean("deleteAccount")) {
            this.mAccountDelete.setVisibility(0);
        }
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
        if (!ModuleManager.isModuleEnabled("ordering").booleanValue() || Configuration.getSharedInstance().getBooleanForKey("modules.delivery.sendToDeliveryWebsite")) {
            this.mSavedCards.setVisibility(8);
        }
        if (!ModuleManager.isModuleEnabled(DeliveryModule.NAME).booleanValue()) {
            this.mAddresses.setVisibility(8);
        }
        if (Configuration.getSharedInstance().getBooleanForKey("interface.register.supportsZipCode", true)) {
            display(this.mZipCode, "zipcode");
        }
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
            openSelfUrl("mcdmobileapp://signout", extras);
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
                fragmentName = ChangeUserNameFragment.NAME;
                container = ChangeUserNameActivity.class;
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
                if (!LoginManager.getInstance().getRegisterSettings().chooseSignInMethodEnabled()) {
                    this.mEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
                    break;
                }
                fragmentName = ChangeEmailAddressFragment.NAME;
                container = ProfileUpdateActivity.class;
                break;
            case C2358R.C2357id.mobile /*2131821087*/:
                fragmentName = ChangeMobileFragment.NAME;
                container = ProfileUpdateActivity.class;
                requestCode = 2005;
                break;
            case C2358R.C2357id.change_password /*2131821088*/:
                fragmentName = ChangePasswordFragment.NAME;
                container = ChangePasswordActivity.class;
                requestCode = 2003;
                break;
            case C2358R.C2357id.signin_prefs /*2131821089*/:
                fragmentName = ChangeLoginPreferenceFragment.NAME;
                container = ProfileUpdateActivity.class;
                requestCode = 2006;
                break;
            case C2358R.C2357id.addresses /*2131821090*/:
                fragmentName = ModifyAddressesFragment.NAME;
                container = ModifyAddressesActivity.class;
                break;
            case C2358R.C2357id.saved_cards /*2131821091*/:
                fragmentName = ModifyCardsFragment.NAME;
                container = ModifyCardsActivity.class;
                break;
            case C2358R.C2357id.zip_code /*2131821092*/:
                fragmentName = ModifyZipCodeFragment.NAME;
                container = ModifyZipCodeActivity.class;
                break;
            case C2358R.C2357id.offer_pref /*2131821093*/:
                fragmentName = OfferPreferencesFragment.NAME;
                container = OfferPreferencesActivity.class;
                break;
            case C2358R.C2357id.comm_pref /*2131821094*/:
                fragmentName = CommunicationsPreferencesFragment.NAME;
                container = CommunicationsPreferencesActivity.class;
                break;
            case C2358R.C2357id.delete_acct /*2131821095*/:
                fragmentName = DeleteAccountFragment.NAME;
                container = DeleteAccountActivity.class;
                requestCode = 2004;
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
                    if (!(lastName == null || lastName.isEmpty() || lastName.equalsIgnoreCase("-"))) {
                        fullName = fullName + " " + lastName;
                    }
                    this.mUserName.setText(fullName);
                    return;
                case 2002:
                    this.mEmail.setText(data.getStringExtra("email"));
                    return;
                case 2005:
                    this.mMobile.setText(data.getStringExtra("mobilenumber"));
                    return;
                case 2007:
                    String birthdate = data.getStringExtra("birthdate");
                    if (birthdate.equals("")) {
                        this.mBirthdateButton.setText(getResources().getString(C2658R.string.account_birthdate));
                        return;
                    }
                    this.mBirthdateButton.setText(birthdate);
                    this.mBirthdateButton.setEnabled(false);
                    return;
                case 2008:
                    this.mGender.setText(data.getStringExtra("gender"));
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
        if (customerProfile.isUsingSocialLogin()) {
            name = name + " " + customerProfile.getLastName();
        }
        this.mUserName.setText(name);
        if (customerProfile.isUsingSocialLogin()) {
            if (customerProfile.getEmailAddress().isEmpty() || customerProfile.getEmailAddress() == null) {
                this.mEmail.setVisibility(8);
            } else {
                this.mEmail.setText(customerProfile.getEmailAddress());
            }
            this.mChangePassword.setVisibility(8);
            if ("" == customerProfile.getMobileNumber()) {
                this.mMobile.setText(C2658R.string.text_hint_phone);
            } else {
                this.mMobile.setText(customerProfile.getMobileNumber());
            }
        } else {
            this.mEmail.setText(customerProfile.getEmailAddress());
            if (this.mMobile.getVisibility() == 0) {
                if ("" == customerProfile.getMobileNumber()) {
                    this.mMobile.setText(C2658R.string.text_hint_phone);
                } else {
                    this.mMobile.setText(customerProfile.getMobileNumber());
                }
            }
        }
        if (!(customerProfile.getCustomerLoginInfo() == null || customerProfile.getCustomerLoginInfo().isEmailAddressVerified())) {
            this.mEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
        }
        if (customerProfile.isMobileVerified()) {
            this.mMobile.setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
        }
        if (LoginManager.getInstance().showField("birthDate")) {
            this.mBirthdateButton.setVisibility(0);
            DateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
            Calendar birthdate = LoginManager.getInstance().getProfile().getBirthDate();
            if (birthdate != null) {
                this.mBirthdateButton.setText(dateFormat.format(birthdate.getTime()));
                this.mBirthdateButton.setEnabled(false);
            } else {
                this.mBirthdateButton.setText(getResources().getString(C2658R.string.account_birthdate));
            }
        }
        if (LoginManager.getInstance().showField("gender")) {
            this.mGender.setVisibility(0);
            String gender = (customerProfile.getGender() == null || TextUtils.isEmpty(customerProfile.getGender())) ? getResources().getString(C2658R.string.signup_gender) : customerProfile.getGender();
            this.mGender.setText(gender);
            this.mGender.invalidate();
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
}
