package com.mcdonalds.app.customer;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
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
import android.widget.TextView.OnEditorActionListener;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;

public class LostPasswordFragment extends URLNavigationFragment {
    public static final String NAME = LostPasswordFragment.class.getSimpleName();
    private CustomerModule mCustomerModule;
    private EditText mEmailAddressEditText;
    private Button mSubmitButton;

    /* renamed from: com.mcdonalds.app.customer.LostPasswordFragment$1 */
    class C30131 implements OnEditorActionListener {
        C30131() {
        }

        public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
            Ensighten.evaluateEvent(this, "onEditorAction", new Object[]{view, new Integer(actionId), event});
            return false;
        }
    }

    /* renamed from: com.mcdonalds.app.customer.LostPasswordFragment$2 */
    class C30142 implements TextWatcher {
        C30142() {
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
            Button access$100 = Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.LostPasswordFragment", "access$100", new Object[]{LostPasswordFragment.this});
            if (TextUtils.isEmpty(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.LostPasswordFragment", "access$000", new Object[]{LostPasswordFragment.this}).getText())) {
                z = false;
            }
            access$100.setEnabled(z);
            if (UIUtils.isEmailValid(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.LostPasswordFragment", "access$000", new Object[]{LostPasswordFragment.this}).getText().toString())) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.LostPasswordFragment", "access$000", new Object[]{LostPasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_valid, 0);
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.LostPasswordFragment", "access$000", new Object[]{LostPasswordFragment.this}).setCompoundDrawablesWithIntrinsicBounds(0, 0, C2358R.C2359drawable.icon_warn, 0);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.customer.LostPasswordFragment$3 */
    class C30153 implements OnClickListener {
        C30153() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            LostPasswordFragment.access$200(LostPasswordFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.LostPasswordFragment$4 */
    class C30164 implements OnEditorActionListener {
        C30164() {
        }

        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            Ensighten.evaluateEvent(this, "onEditorAction", new Object[]{v, new Integer(actionId), event});
            if ((event != null && event.getKeyCode() == 66) || actionId == 6) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.LostPasswordFragment", "access$100", new Object[]{LostPasswordFragment.this}).callOnClick();
            }
            return false;
        }
    }

    /* renamed from: com.mcdonalds.app.customer.LostPasswordFragment$5 */
    class C30175 implements OnClickListener {
        C30175() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(LostPasswordFragment.this.getAnalyticsTitle(), "Start Registration");
            if (AppUtils.hideTermsAndConditionsView()) {
                LostPasswordFragment.this.startActivity(SignUpActivity.class);
            } else {
                LostPasswordFragment.this.startActivity(TermsOfServiceActivity.class);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.customer.LostPasswordFragment$6 */
    class C30196 implements AsyncListener<Void> {

        /* renamed from: com.mcdonalds.app.customer.LostPasswordFragment$6$1 */
        class C30181 implements OnDismissListener {
            C30181() {
            }

            public void onDismiss(DialogInterface dialogInterface) {
                Ensighten.evaluateEvent(this, "onDismiss", new Object[]{dialogInterface});
                LostPasswordFragment.this.startActivity(MainActivity.class);
            }
        }

        C30196() {
        }

        public void onResponse(Void response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (exception == null) {
                if (LostPasswordFragment.this.isActivityAlive()) {
                    UIUtils.showGlobalAlertDialog(LostPasswordFragment.this.getNavigationActivity(), LostPasswordFragment.this.getString(C2658R.string.dialog_title_email_sent), LostPasswordFragment.this.getString(C2658R.string.dialog_body_email_sent), null).setOnDismissListener(new C30181());
                }
                DataLayerManager.getInstance().setUser(null, "Signed-out", AppUtils.getCurrentMenuType());
                return;
            }
            AsyncException.report(exception);
        }
    }

    static /* synthetic */ void access$200(LostPasswordFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.LostPasswordFragment", "access$200", new Object[]{x0});
        x0.sendLostPasswordEmail();
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
        getNavigationActivity().setTitle(getString(C2658R.string.lost_password_title));
        setHasOptionsMenu(true);
        DataLayerManager.getInstance().setFormName("Forgot Password Form");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_lost_password, container, false);
        this.mEmailAddressEditText = (EditText) view.findViewById(C2358R.C2357id.lost_password_email);
        this.mEmailAddressEditText.setImeActionLabel("Submit", 66);
        this.mEmailAddressEditText.setOnEditorActionListener(new C30131());
        this.mEmailAddressEditText.addTextChangedListener(new C30142());
        this.mSubmitButton = (Button) view.findViewById(C2358R.C2357id.lost_password_submit_button);
        this.mSubmitButton.setOnClickListener(new C30153());
        this.mEmailAddressEditText.setOnEditorActionListener(new C30164());
        view.findViewById(C2358R.C2357id.need_an_account).setOnClickListener(new C30175());
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.cancel, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        switch (item.getItemId()) {
            case C2358R.C2357id.action_cancel /*2131820688*/:
                AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Cancel");
                startActivity(SignInActivity.class);
                break;
        }
        return true;
    }

    private void sendLostPasswordEmail() {
        Ensighten.evaluateEvent(this, "sendLostPasswordEmail", null);
        if (TextUtils.isEmpty(this.mEmailAddressEditText.getText().toString().trim()) || !UIUtils.isEmailValid(this.mEmailAddressEditText.getText().toString())) {
            AsyncException.report(getString(C2658R.string.error_invalid_email_format));
            return;
        }
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Email Enter");
        UIUtils.startActivityIndicator(getActivity(), getString(C2658R.string.dialog_send_lost_password));
        this.mCustomerModule.resetPassword(this.mEmailAddressEditText.getText().toString().trim(), new C30196());
    }
}
