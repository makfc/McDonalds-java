package com.mcdonalds.app.gmalite.customer;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.web.WebActivity;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.TenderType;

public class LiteResetPasswordFragment extends URLNavigationFragment {
    private String mAuthorizationCode;
    private CustomerModule mCustomerModule;
    private EditText mNewPassword;
    private EditText mNewPasswordConfirm;
    private OnClickListener mOnClickCustomerSupport = new C31712();
    private Button mSubmitButton;
    private TextWatcher passwordWatcher = new C31744();

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteResetPasswordFragment$1 */
    class C31701 implements OnClickListener {
        C31701() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            LiteResetPasswordFragment.access$000(LiteResetPasswordFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteResetPasswordFragment$2 */
    class C31712 implements OnClickListener {
        C31712() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(LiteResetPasswordFragment.this.getAnalyticsTitle(), "Customer Support");
            String link = ConfigurationUtils.getCustomerSupportUrl();
            if (link != null) {
                Bundle attributes = new Bundle();
                attributes.putString("link", link);
                attributes.putString("analytics_title", LiteResetPasswordFragment.this.getString(C2658R.string.analytics_screen_customer_support));
                LiteResetPasswordFragment.this.getNavigationActivity().startActivity(WebActivity.class, "web", attributes);
                return;
            }
            AsyncException.report("No Customer Support URL Defined");
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteResetPasswordFragment$3 */
    class C31733 implements AsyncListener<Void> {

        /* renamed from: com.mcdonalds.app.gmalite.customer.LiteResetPasswordFragment$3$1 */
        class C31721 implements OnDismissListener {
            C31721() {
            }

            public void onDismiss(DialogInterface dialogInterface) {
                Ensighten.evaluateEvent(this, "onDismiss", new Object[]{dialogInterface});
                LiteResetPasswordFragment.this.startActivity(LiteSignInActivity.class);
                LiteResetPasswordFragment.this.getActivity().finish();
            }
        }

        C31733() {
        }

        public void onResponse(Void response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (exception == null) {
                UIUtils.showGlobalAlertDialog(LiteResetPasswordFragment.this.getNavigationActivity(), LiteResetPasswordFragment.this.getString(C2658R.string.lite_alrt_reset_pswd), LiteResetPasswordFragment.this.getString(C2658R.string.lite_alrt_msg_reset_pswd), null).setOnDismissListener(new C31721());
            } else {
                AsyncException.report(exception);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteResetPasswordFragment$4 */
    class C31744 implements TextWatcher {
        C31744() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{s, new Integer(start), new Integer(count), new Integer(after)});
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{s, new Integer(start), new Integer(before), new Integer(count)});
        }

        public void afterTextChanged(Editable s) {
            boolean z = true;
            Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{s});
            boolean validPassword = UIUtils.isPasswordValid(Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteResetPasswordFragment", "access$100", new Object[]{LiteResetPasswordFragment.this}).getText().toString());
            boolean passwordMatches = Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteResetPasswordFragment", "access$100", new Object[]{LiteResetPasswordFragment.this}).getText().toString().equals(Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteResetPasswordFragment", "access$200", new Object[]{LiteResetPasswordFragment.this}).getText().toString());
            Button access$300 = Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteResetPasswordFragment", "access$300", new Object[]{LiteResetPasswordFragment.this});
            if (!(validPassword && passwordMatches)) {
                z = false;
            }
            access$300.setEnabled(z);
            if (validPassword) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteResetPasswordFragment", "access$100", new Object[]{LiteResetPasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_valid, 0);
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteResetPasswordFragment", "access$100", new Object[]{LiteResetPasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
            }
            if (TextUtils.isEmpty(Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteResetPasswordFragment", "access$100", new Object[]{LiteResetPasswordFragment.this}).getText().toString()) || !passwordMatches) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteResetPasswordFragment", "access$200", new Object[]{LiteResetPasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteResetPasswordFragment", "access$200", new Object[]{LiteResetPasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_valid, 0);
            }
        }
    }

    static /* synthetic */ void access$000(LiteResetPasswordFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteResetPasswordFragment", "access$000", new Object[]{x0});
        x0.changePassword();
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_log_in_reset_password);
    }

    public void onStart() {
        super.onStart();
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getNavigationActivity().setTitle(getString(C2658R.string.lite_title_reset_pswd));
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_lite_reset_password, container, false);
        this.mNewPassword = (EditText) view.findViewById(C2358R.C2357id.password);
        this.mNewPasswordConfirm = (EditText) view.findViewById(C2358R.C2357id.confirm_password);
        this.mNewPassword.addTextChangedListener(this.passwordWatcher);
        this.mNewPasswordConfirm.addTextChangedListener(this.passwordWatcher);
        this.mSubmitButton = (Button) view.findViewById(C2358R.C2357id.button_reset_password);
        this.mSubmitButton.setOnClickListener(new C31701());
        ((TextView) view.findViewById(C2358R.C2357id.customer_support_link)).setOnClickListener(this.mOnClickCustomerSupport);
        return view;
    }

    public void onResume() {
        super.onResume();
        String uriCode = ((Uri) getArguments().getParcelable("Uri")).getQueryParameter(TenderType.COLUMN_CODE);
        if (TextUtils.isEmpty(uriCode)) {
            uriCode = "";
        }
        this.mAuthorizationCode = uriCode;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void changePassword() {
        Ensighten.evaluateEvent(this, "changePassword", null);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Continue");
        UIUtils.startActivityIndicator(getActivity(), getString(C2658R.string.lite_dialog_reset_pswd));
        this.mCustomerModule.changePassword(this.mNewPassword.getText().toString().trim(), this.mAuthorizationCode, new C31733());
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.lite_title_reset_pswd);
    }
}
