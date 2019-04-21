package com.mcdonalds.app.account;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
import android.view.ViewGroup;
import android.widget.EditText;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;

public class ChangeUserNameFragment extends URLNavigationFragment implements TextWatcher {
    public static String NAME = ChangeUserNameFragment.class.getSimpleName();
    private String OriginalLastName;
    private String OriginalName;
    private CustomerModule mCustomerModule;
    private CustomerProfile mCustomerProfile;
    private boolean mEmailAsUsername;
    private EditText mFirstName;
    private EditText mLastName;
    private final OnClickListener mOnClickDialog = new C29545();
    private View mSaveButton;
    private InputFilter nameBlockedCharactersFilter = new C29513();

    /* renamed from: com.mcdonalds.app.account.ChangeUserNameFragment$1 */
    class C29491 implements View.OnClickListener {
        C29491() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            ChangeUserNameFragment.this.getActivity().finish();
        }
    }

    /* renamed from: com.mcdonalds.app.account.ChangeUserNameFragment$2 */
    class C29502 implements View.OnClickListener {
        C29502() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            ChangeUserNameFragment.access$000(ChangeUserNameFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.account.ChangeUserNameFragment$3 */
    class C29513 implements InputFilter {
        C29513() {
        }

        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Ensighten.evaluateEvent(this, Parameters.FILTER, new Object[]{source, new Integer(start), new Integer(end), dest, new Integer(dstart), new Integer(dend)});
            if (source == null || !"~`!@#$%^&*()<>_+=.,/?:;\"[]|\\{}".contains(source)) {
                return null;
            }
            return "";
        }
    }

    /* renamed from: com.mcdonalds.app.account.ChangeUserNameFragment$5 */
    class C29545 implements OnClickListener {
        C29545() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            Intent resultIntent = new Intent();
            resultIntent.putExtra("first", Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeUserNameFragment", "access$300", new Object[]{ChangeUserNameFragment.this}).getText().toString());
            resultIntent.putExtra("last", Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeUserNameFragment", "access$400", new Object[]{ChangeUserNameFragment.this}).getText().toString());
            ChangeUserNameFragment.this.getNavigationActivity().setResult(-1, resultIntent);
            ChangeUserNameFragment.this.getNavigationActivity().finish();
        }
    }

    static /* synthetic */ void access$000(ChangeUserNameFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeUserNameFragment", "access$000", new Object[]{x0});
        x0.onSave();
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.title_change_name_fragment);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_account_edit_name);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_change_profile_item, container, false);
        view.findViewById(C2358R.C2357id.cancel_btn).setOnClickListener(new C29491());
        this.mSaveButton = view.findViewById(C2358R.C2357id.save_btn);
        this.mSaveButton.setEnabled(false);
        this.mSaveButton.setOnClickListener(new C29502());
        this.mFirstName = (EditText) view.findViewById(C2358R.C2357id.first_inc);
        this.mFirstName.addTextChangedListener(this);
        this.mLastName = (EditText) view.findViewById(C2358R.C2357id.second_inc);
        this.mLastName.addTextChangedListener(this);
        if (LoginManager.getInstance().getRegisterSettings().isSingleFieldName()) {
            this.mLastName.setVisibility(8);
        }
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mCustomerProfile = this.mCustomerModule.getCurrentProfile();
        this.OriginalName = this.mCustomerProfile.getFirstName();
        this.OriginalLastName = this.mCustomerProfile.getLastName();
        if (this.mCustomerProfile.isUsingSocialLogin()) {
            this.mLastName.setVisibility(0);
        }
        return view;
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
        final String originalFirstName = this.mCustomerProfile.getFirstName();
        final String originalLastName = this.mCustomerProfile.getLastName();
        final String originalEmail = this.mCustomerProfile.getEmailAddress();
        String firstName = this.mFirstName.getText().toString();
        String lastName = this.mLastName.getText().toString();
        this.mCustomerProfile.setFirstName(firstName);
        this.mCustomerProfile.setLastName(lastName);
        if (this.mEmailAsUsername && firstName.contains("*@*")) {
            this.mCustomerProfile.setEmailAddress(firstName);
        }
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Name");
        UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.dialog_changing_username);
        this.mCustomerModule.updateCustomerProfile(this.mCustomerProfile, new AsyncListener<CustomerProfile>() {

            /* renamed from: com.mcdonalds.app.account.ChangeUserNameFragment$4$1 */
            class C29521 implements OnClickListener {
                C29521() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    dialog.dismiss();
                }
            }

            public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                UIUtils.stopActivityIndicator();
                if (exception != null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeUserNameFragment", "access$200", new Object[]{ChangeUserNameFragment.this}).setFirstName(originalFirstName);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeUserNameFragment", "access$200", new Object[]{ChangeUserNameFragment.this}).setLastName(originalLastName);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeUserNameFragment", "access$200", new Object[]{ChangeUserNameFragment.this}).setEmailAddress(originalEmail);
                    if (ChangeUserNameFragment.this.getNavigationActivity() != null) {
                        MCDAlertDialogBuilder.withContext(ChangeUserNameFragment.this.getNavigationActivity()).setMessage(exception.getLocalizedMessage()).setPositiveButton((int) C2658R.string.f6083ok, new C29521()).create().show();
                    }
                } else if (ChangeUserNameFragment.this.getNavigationActivity() != null) {
                    MCDAlertDialogBuilder.withContext(ChangeUserNameFragment.this.getNavigationActivity()).setTitle(ChangeUserNameFragment.this.getResources().getString(C2658R.string.dialog_title_success)).setMessage(ChangeUserNameFragment.this.getResources().getString(C2658R.string.text_name_updated)).setPositiveButton((int) C2658R.string.f6083ok, Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeUserNameFragment", "access$100", new Object[]{ChangeUserNameFragment.this})).create().show();
                }
            }
        });
    }

    private boolean validateFields() {
        Ensighten.evaluateEvent(this, "validateFields", null);
        if (LoginManager.getInstance().getRegisterSettings().isSingleFieldName()) {
            if (this.mFirstName.getText().length() <= 0 || this.mFirstName.getText().length() >= 35) {
                return false;
            }
            return true;
        } else if (this.mFirstName.getText().length() <= 0 || this.mFirstName.getText().length() >= 35 || this.mLastName.getText().length() < 0 || this.mLastName.getText().length() >= 35) {
            return false;
        } else {
            return true;
        }
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{s, new Integer(start), new Integer(count), new Integer(after)});
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{s, new Integer(start), new Integer(before), new Integer(count)});
    }

    public void afterTextChanged(Editable s) {
        Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{s});
        if (this.mFirstName.getText().toString().equals(this.OriginalName) && this.mLastName.getText().toString().equals(this.OriginalLastName)) {
            this.mSaveButton.setEnabled(false);
        } else {
            this.mSaveButton.setEnabled(validateFields());
        }
    }
}
