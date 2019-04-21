package com.mcdonalds.app.gmalite.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;

public class LiteChangeUserNameFragment extends URLNavigationFragment implements TextWatcher {
    public static String NAME = LiteChangeUserNameFragment.class.getSimpleName();
    private CustomerModule mCustomerModule;
    private CustomerProfile mCustomerProfile;
    private boolean mEmailAsUsername;
    private EditText mFirstName;
    private EditText mLastName;
    private View mSaveButton;
    private InputFilter nameBlockedCharactersFilter = new C31442();

    /* renamed from: com.mcdonalds.app.gmalite.account.LiteChangeUserNameFragment$1 */
    class C31431 implements OnClickListener {
        C31431() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            LiteChangeUserNameFragment.access$000(LiteChangeUserNameFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.account.LiteChangeUserNameFragment$2 */
    class C31442 implements InputFilter {
        C31442() {
        }

        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Ensighten.evaluateEvent(this, Parameters.FILTER, new Object[]{source, new Integer(start), new Integer(end), dest, new Integer(dstart), new Integer(dend)});
            if (source == null || !"~`!@#$%^&*()<>_+=.,/?:;\"[]|\\{}".contains(source)) {
                return null;
            }
            return "";
        }
    }

    static /* synthetic */ void access$000(LiteChangeUserNameFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeUserNameFragment", "access$000", new Object[]{x0});
        x0.onSave();
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.lite_title_acct_name);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_account_edit_name);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_lite_change_user_name, container, false);
        this.mSaveButton = view.findViewById(C2358R.C2357id.save_btn);
        this.mSaveButton.setOnClickListener(new C31431());
        this.mFirstName = (EditText) view.findViewById(C2358R.C2357id.first_inc);
        this.mFirstName.addTextChangedListener(this);
        this.mLastName = (EditText) view.findViewById(C2358R.C2357id.second_inc);
        this.mLastName.addTextChangedListener(this);
        if (LoginManager.getInstance().getRegisterSettings().isSingleFieldName()) {
            this.mLastName.setVisibility(8);
        }
        return view;
    }

    public void onResume() {
        super.onResume();
        this.mSaveButton.setEnabled(false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mCustomerProfile = this.mCustomerModule.getCurrentProfile();
        InputFilter nameLengthFilter = new LengthFilter(34);
        this.mFirstName.setText(this.mCustomerProfile.getFirstName());
        this.mFirstName.setFilters(new InputFilter[]{this.nameBlockedCharactersFilter, nameLengthFilter});
        this.mLastName.setText(this.mCustomerProfile.getLastName());
        this.mLastName.setFilters(new InputFilter[]{this.nameBlockedCharactersFilter, nameLengthFilter});
        if (this.mCustomerProfile.getFirstName().equals(this.mCustomerProfile.getEmailAddress())) {
            this.mEmailAsUsername = true;
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.save, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        if (item.getItemId() == C2358R.C2357id.action_save) {
            onSave();
        }
        return true;
    }

    private void onSave() {
        Ensighten.evaluateEvent(this, "onSave", null);
        String originalFirstName = this.mCustomerProfile.getFirstName();
        String originalLastName = this.mCustomerProfile.getLastName();
        String originalEmail = this.mCustomerProfile.getEmailAddress();
        final String firstName = this.mFirstName.getText().toString().trim();
        final String lastName = this.mLastName.getText().toString().trim();
        CustomerProfile newCustomerProfile = new CustomerProfile();
        newCustomerProfile.setFirstName(firstName);
        newCustomerProfile.setLastName(lastName);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Save");
        UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.lite_dialog_acct_name);
        this.mCustomerModule.updateCustomerProfile(newCustomerProfile, new AsyncListener<CustomerProfile>() {
            public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                UIUtils.stopActivityIndicator();
                if (exception == null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeUserNameFragment", "access$100", new Object[]{LiteChangeUserNameFragment.this}).setFirstName(firstName);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeUserNameFragment", "access$100", new Object[]{LiteChangeUserNameFragment.this}).setLastName(lastName);
                    if (LiteChangeUserNameFragment.this.getNavigationActivity() != null) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("first", Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeUserNameFragment", "access$200", new Object[]{LiteChangeUserNameFragment.this}).getText().toString());
                        resultIntent.putExtra("last", Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeUserNameFragment", "access$300", new Object[]{LiteChangeUserNameFragment.this}).getText().toString());
                        LiteChangeUserNameFragment.this.getNavigationActivity().setResult(-1, resultIntent);
                        LiteChangeUserNameFragment.this.getNavigationActivity().finish();
                        return;
                    }
                    return;
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeUserNameFragment", "access$200", new Object[]{LiteChangeUserNameFragment.this}).setText(Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeUserNameFragment", "access$100", new Object[]{LiteChangeUserNameFragment.this}).getFirstName());
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeUserNameFragment", "access$300", new Object[]{LiteChangeUserNameFragment.this}).setText(Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.account.LiteChangeUserNameFragment", "access$100", new Object[]{LiteChangeUserNameFragment.this}).getLastName());
                AsyncException.report(exception);
            }
        });
    }

    private boolean validateFields() {
        Ensighten.evaluateEvent(this, "validateFields", null);
        return this.mFirstName.getText().length() > 0 && this.mFirstName.getText().length() < 35 && this.mLastName.getText().length() > 0 && this.mLastName.getText().length() < 35;
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{s, new Integer(start), new Integer(count), new Integer(after)});
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{s, new Integer(start), new Integer(before), new Integer(count)});
    }

    public void afterTextChanged(Editable s) {
        Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{s});
        this.mSaveButton.setEnabled(validateFields());
    }
}
