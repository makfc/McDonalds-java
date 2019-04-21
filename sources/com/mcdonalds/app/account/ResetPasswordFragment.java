package com.mcdonalds.app.account;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.customer.SignUpActivity;
import com.mcdonalds.app.customer.TermsOfServiceActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.widget.ValidationListener;
import com.mcdonalds.app.widget.ValidationListener.OnUpdateListener;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class ResetPasswordFragment extends URLNavigationFragment implements OnClickListener, OnUpdateListener {
    private static final String LOG_TAG = ResetPasswordFragment.class.getSimpleName();
    private CustomerModule mCustomerModule;
    private EditText mEmail;
    private String mEmailAddress;
    private ValidationListener mEmailValidation;
    private EditText mMobile;
    private String mMobileNumber;
    private ValidationListener mMobileValidation;
    private View mRegisterButton;
    private View mResetPasswordButton;
    private final AsyncListener<Void> mResetPasswordListener = new C30101();

    /* renamed from: com.mcdonalds.app.account.ResetPasswordFragment$1 */
    class C30101 implements AsyncListener<Void> {
        C30101() {
        }

        public void onResponse(Void response, AsyncToken token, AsyncException exception) {
            int i = 1;
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception != null) {
                UIUtils.stopActivityIndicator();
                AsyncException.report(exception);
                return;
            }
            UIUtils.stopActivityIndicator();
            if (ResetPasswordFragment.this.getNavigationActivity() != null) {
                Bundle arguments = new Bundle();
                String str = "validation_method";
                if (!TextUtils.isEmpty(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ResetPasswordFragment", "access$000", new Object[]{ResetPasswordFragment.this}))) {
                    i = 2;
                }
                arguments.putInt(str, i);
                arguments.putString("validation_method_value", TextUtils.isEmpty(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ResetPasswordFragment", "access$100", new Object[]{ResetPasswordFragment.this})) ? Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ResetPasswordFragment", "access$000", new Object[]{ResetPasswordFragment.this}) : Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ResetPasswordFragment", "access$100", new Object[]{ResetPasswordFragment.this}));
                ResetPasswordFragment.this.startActivity(ProfileUpdateActivity.class, "reset_password_confirmation", arguments);
            }
            DataLayerManager.getInstance().setUser(null, "Signed-out", AppUtils.getCurrentMenuType());
        }
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_log_in_reset_password);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        DataLayerManager.getInstance().setFormName("Forgot Password Simple Form");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C2658R.layout.fragment_reset_password, container, false);
        this.mEmail = (EditText) v.findViewById(2131821086);
        this.mMobile = (EditText) v.findViewById(C2358R.C2357id.phone);
        this.mResetPasswordButton = v.findViewById(C2358R.C2357id.button_reset_password);
        this.mRegisterButton = v.findViewById(C2358R.C2357id.need_an_account);
        this.mRegisterButton.setOnClickListener(this);
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isUseEmailAsUserName()) {
            this.mEmailValidation = new ValidationListener(this.mEmail, 0, true, true);
        } else {
            this.mEmailValidation = new ValidationListener(this.mEmail, 4, true, true);
        }
        this.mEmailValidation.setUpdateListener(this);
        this.mEmail.addTextChangedListener(this.mEmailValidation);
        this.mMobileValidation = new ValidationListener(this.mMobile, 5, true, false);
        this.mMobileValidation.setUpdateListener(this);
        this.mMobile.addTextChangedListener(this.mMobileValidation);
        this.mResetPasswordButton.setOnClickListener(this);
        refresh();
    }

    private boolean isUseEmailAsUserName() {
        Ensighten.evaluateEvent(this, "isUseEmailAsUserName", null);
        return Configuration.getSharedInstance().getBooleanForKey("interface.useEmailAsUsername");
    }

    public void onFieldUpdate() {
        Ensighten.evaluateEvent(this, "onFieldUpdate", null);
        refresh();
    }

    private void refresh() {
        Ensighten.evaluateEvent(this, "refresh", null);
        boolean enabled = this.mEmailValidation.isValidated() && this.mMobileValidation.isValidated();
        this.mResetPasswordButton.setEnabled(enabled);
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        if (v == this.mResetPasswordButton) {
            performSubmitAction();
        } else if (v == this.mRegisterButton) {
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Start Registration");
            if (AppUtils.hideTermsAndConditionsView()) {
                startActivity(SignUpActivity.class);
            } else {
                startActivity(TermsOfServiceActivity.class);
            }
        }
    }

    private void performSubmitAction() {
        Ensighten.evaluateEvent(this, "performSubmitAction", null);
        hideKeyboard();
        if (this.mCustomerModule != null) {
            this.mEmailAddress = UIUtils.getText(this.mEmail);
            this.mMobileNumber = UIUtils.getText(this.mMobile);
            this.mCustomerModule.resetPassword(TextUtils.isEmpty(this.mEmailAddress) ? this.mMobileNumber : this.mEmailAddress, this.mEmailAddress, this.mMobileNumber, this.mResetPasswordListener);
            UIUtils.startActivityIndicator(getActivity(), (int) C2658R.string.resetting_pwd);
        }
    }

    private void hideKeyboard() {
        Ensighten.evaluateEvent(this, "hideKeyboard", null);
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 2);
        }
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.lost_password_title);
    }
}
