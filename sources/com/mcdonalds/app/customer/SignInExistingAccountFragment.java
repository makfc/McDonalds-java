package com.mcdonalds.app.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.modules.models.SocialLoginAuthenticationResults;

public class SignInExistingAccountFragment extends SignInFragment {
    private int SocialServiceAuthenticationID;
    private CustomerProfile mCustomerProfile = null;
    private Button mDoNotHaveAccount;
    private EditText mEmailEditText;
    private LoginManager mManager;
    URLNavigationActivity mParent;
    private String mPassword;
    private EditText mPasswordTextView;
    private View mSocialContainer;
    private TextView mTitle;
    private String mUserName;

    /* renamed from: com.mcdonalds.app.customer.SignInExistingAccountFragment$1 */
    class C30301 implements OnClickListener {
        C30301() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            SignInExistingAccountFragment.this.onDoNotHaveAccount();
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignInExistingAccountFragment$2 */
    class C30312 implements AsyncListener<CustomerProfile> {
        C30312() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (exception == null) {
                response.setUsingSocialLogin(true);
                response.setUsingSocialLoginWithoutEmail(true);
                response.setSocialServiceAuthenticationID(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInExistingAccountFragment", "access$000", new Object[]{SignInExistingAccountFragment.this}));
                response.setSocialAuthenticationToken(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInExistingAccountFragment", "access$100", new Object[]{SignInExistingAccountFragment.this}));
                response.setSocialUserID(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInExistingAccountFragment", "access$200", new Object[]{SignInExistingAccountFragment.this}));
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignInExistingAccountFragment", "access$300", new Object[]{SignInExistingAccountFragment.this}).setProfile(response);
                UIUtils.startActivityIndicator(SignInExistingAccountFragment.this.getActivity(), SignInExistingAccountFragment.this.getString(C2658R.string.dialog_registering));
                LoginManager.getInstance().addLoginMethod(SignInExistingAccountFragment.this.getNavigationActivity());
                return;
            }
            AsyncException.report(exception);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mParent = getNavigationActivity();
        if (this.mCustomerProfile == null) {
            this.mCustomerProfile = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getCurrentProfile();
        }
        this.mCustomerProfile = LoginManager.getInstance().getProfile();
        this.mUserName = this.mCustomerProfile.getUserName();
        this.mPassword = this.mCustomerProfile.getPassword();
        this.SocialServiceAuthenticationID = this.mCustomerProfile.getSocialServiceAuthenticationID();
        this.acces_token = this.mCustomerProfile.getSocialAuthenticationToken();
        this.openid = this.mCustomerProfile.getSocialUserID();
        this.mManager = LoginManager.getInstance();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        this.mSocialContainer = rootView.findViewById(C2358R.C2357id.social_login);
        this.mSocialContainer.setVisibility(8);
        this.mTitle = (TextView) rootView.findViewById(C2358R.C2357id.signin_title);
        this.mTitle.setText(C2658R.string.already_have_account);
        this.mEmailEditText = (EditText) rootView.findViewById(2131821086);
        this.mPasswordTextView = (EditText) rootView.findViewById(C2358R.C2357id.password);
        this.mDoNotHaveAccount = (Button) rootView.findViewById(C2358R.C2357id.do_not_have_account);
        this.mDoNotHaveAccount.setVisibility(0);
        return rootView;
    }

    public void onResume() {
        super.onResume();
        this.mEmailEditText.setText(this.mCustomerProfile.getEmailAddress());
        this.mEmailEditText.setEnabled(false);
        this.mDoNotHaveAccount.setOnClickListener(new C30301());
    }

    /* Access modifiers changed, original: protected */
    public void onDoNotHaveAccount() {
        Ensighten.evaluateEvent(this, "onDoNotHaveAccount", null);
        this.mManager.setProfile(this.mCustomerProfile);
        UIUtils.startActivityIndicator(getActivity(), getString(C2658R.string.dialog_registering));
        LoginManager.getInstance().forceRegister(getNavigationActivity());
    }

    /* Access modifiers changed, original: protected */
    public void onSubmitDoSignin(SocialLoginAuthenticationResults socialLoginInfo) {
        Ensighten.evaluateEvent(this, "onSubmitDoSignin", new Object[]{socialLoginInfo});
        if (AppUtils.isNetworkConnected(getNavigationActivity())) {
            UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.dialog_signing_in);
            CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            AuthenticationParameters parameters = new AuthenticationParameters();
            parameters.setUserName(this.mEmailEditText.getText().toString().trim());
            parameters.setPassword(this.mPasswordTextView.getText().toString().trim());
            customerModule.authenticate(parameters, new C30312());
            return;
        }
        UIUtils.showNoNetworkAlert(getNavigationActivity());
    }
}
