package com.mcdonalds.app.gmalite.customer;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
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

public class LiteForgotPasswordFragment extends URLNavigationFragment {
    public static final String NAME = LiteForgotPasswordFragment.class.getSimpleName();
    private CustomerModule mCustomerModule;
    private EditText mEmailAddressEditText;
    private OnClickListener mOnClickCustomerSupport = new C31604();
    private TextView mResendInstructions;
    private Button mSubmitButton;

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteForgotPasswordFragment$1 */
    class C31571 implements TextWatcher {
        C31571() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{s, new Integer(start), new Integer(count), new Integer(after)});
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{s, new Integer(start), new Integer(before), new Integer(count)});
        }

        public void afterTextChanged(Editable s) {
            Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{s});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteForgotPasswordFragment", "access$100", new Object[]{LiteForgotPasswordFragment.this}).setEnabled(UIUtils.isEmailValid(Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteForgotPasswordFragment", "access$000", new Object[]{LiteForgotPasswordFragment.this}).getText().toString()));
            if (UIUtils.isEmailValid(Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteForgotPasswordFragment", "access$000", new Object[]{LiteForgotPasswordFragment.this}).getText().toString())) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteForgotPasswordFragment", "access$000", new Object[]{LiteForgotPasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_valid, 0);
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteForgotPasswordFragment", "access$000", new Object[]{LiteForgotPasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteForgotPasswordFragment$2 */
    class C31582 implements OnClickListener {
        C31582() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            LiteForgotPasswordFragment.access$200(LiteForgotPasswordFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteForgotPasswordFragment$3 */
    class C31593 implements OnEditorActionListener {
        C31593() {
        }

        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            Ensighten.evaluateEvent(this, "onEditorAction", new Object[]{v, new Integer(actionId), event});
            if ((event != null && event.getKeyCode() == 66) || actionId == 6) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteForgotPasswordFragment", "access$100", new Object[]{LiteForgotPasswordFragment.this}).callOnClick();
            }
            return false;
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteForgotPasswordFragment$4 */
    class C31604 implements OnClickListener {
        C31604() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(LiteForgotPasswordFragment.this.getAnalyticsTitle(), "Customer Support");
            String link = ConfigurationUtils.getCustomerSupportUrl();
            if (link != null) {
                Bundle attributes = new Bundle();
                attributes.putString("link", link);
                attributes.putString("analytics_title", LiteForgotPasswordFragment.this.getString(C2658R.string.analytics_screen_customer_support));
                LiteForgotPasswordFragment.this.getNavigationActivity().startActivity(WebActivity.class, "web", attributes);
                return;
            }
            AsyncException.report("No Customer Support URL Defined");
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteForgotPasswordFragment$5 */
    class C31615 implements AsyncListener<Void> {
        C31615() {
        }

        public void onResponse(Void response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (exception == null) {
                UIUtils.showGlobalAlertDialog(LiteForgotPasswordFragment.this.getNavigationActivity(), LiteForgotPasswordFragment.this.getString(C2658R.string.lite_alrt_email_sent), LiteForgotPasswordFragment.this.getString(C2658R.string.lite_alrt_msg_reset_pswd_email_sent), null);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteForgotPasswordFragment", "access$300", new Object[]{LiteForgotPasswordFragment.this}).setVisibility(0);
                return;
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteForgotPasswordFragment", "access$300", new Object[]{LiteForgotPasswordFragment.this}).setVisibility(8);
            AsyncException.report(exception);
        }
    }

    static /* synthetic */ void access$200(LiteForgotPasswordFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteForgotPasswordFragment", "access$200", new Object[]{x0});
        x0.sendForgotPasswordEmail();
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_log_in_forgot_password);
    }

    public void onStart() {
        super.onStart();
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getNavigationActivity().setTitle(getString(C2658R.string.lite_title_forgot_pswd));
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_lite_forgot_password, container, false);
        this.mEmailAddressEditText = (EditText) view.findViewById(C2358R.C2357id.lost_password_email);
        this.mEmailAddressEditText.setImeActionLabel("Submit", 66);
        this.mEmailAddressEditText.addTextChangedListener(new C31571());
        this.mSubmitButton = (Button) view.findViewById(C2358R.C2357id.lost_password_submit_button);
        this.mSubmitButton.setOnClickListener(new C31582());
        this.mEmailAddressEditText.setOnEditorActionListener(new C31593());
        this.mResendInstructions = (TextView) view.findViewById(C2358R.C2357id.resend_instructions);
        ((TextView) view.findViewById(C2358R.C2357id.customer_support_link)).setOnClickListener(this.mOnClickCustomerSupport);
        return view;
    }

    public void onResume() {
        super.onResume();
        String email = getActivity().getIntent().getStringExtra("email");
        if (!TextUtils.isEmpty(email)) {
            this.mEmailAddressEditText.setText(email);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void sendForgotPasswordEmail() {
        Ensighten.evaluateEvent(this, "sendForgotPasswordEmail", null);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Continue");
        if (TextUtils.isEmpty(this.mEmailAddressEditText.getText().toString().trim()) || !UIUtils.isEmailValid(this.mEmailAddressEditText.getText().toString())) {
            AsyncException.report(getString(C2658R.string.error_invalid_email_format));
            return;
        }
        UIUtils.startActivityIndicator(getActivity(), getString(C2658R.string.lite_dialog_forgot_pswd));
        this.mCustomerModule.resetPassword(this.mEmailAddressEditText.getText().toString().trim(), new C31615());
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.lite_title_forgot_pswd);
    }
}
