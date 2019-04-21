package com.mcdonalds.app.customer;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import java.util.regex.Pattern;

public class SignUpZipCodeFragment extends URLNavigationFragment {
    public static final String NAME = SignUpZipCodeFragment.class.getSimpleName();
    private CustomerProfile mCustomerProfile = null;
    private EditText mNewZipCodeEditText = null;

    /* renamed from: com.mcdonalds.app.customer.SignUpZipCodeFragment$1 */
    class C30801 implements OnClickListener {
        C30801() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            SignUpZipCodeFragment.access$000(SignUpZipCodeFragment.this);
        }
    }

    static /* synthetic */ void access$000(SignUpZipCodeFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpZipCodeFragment", "access$000", new Object[]{x0});
        x0.onSave();
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.title_register_zipcode);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_account_zip_code);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_register_zip_code, container, false);
        this.mCustomerProfile = LoginManager.getInstance().getProfile();
        this.mNewZipCodeEditText = (EditText) rootView.findViewById(C2358R.C2357id.new_inc);
        if (this.mCustomerProfile.getZipCode() != null) {
            this.mNewZipCodeEditText.setText(this.mCustomerProfile.getZipCode());
        }
        this.mNewZipCodeEditText.setFilters(new InputFilter[]{new LengthFilter(5)});
        ((Button) rootView.findViewById(C2358R.C2357id.save_btn)).setOnClickListener(new C30801());
        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
                getActivity().finish();
                break;
        }
        return true;
    }

    private void onSave() {
        Ensighten.evaluateEvent(this, "onSave", null);
        String zipCode = this.mNewZipCodeEditText.getText().toString();
        if (isAValidZipCode(zipCode)) {
            this.mCustomerProfile.setZipCode(zipCode);
            getActivity().setResult(-1);
            getActivity().finish();
        } else if (getNavigationActivity() != null) {
            UIUtils.showGlobalAlertDialog(getNavigationActivity(), getResources().getString(C2658R.string.invalid_entry), getResources().getString(C2658R.string.dialog_zip_code_invalid), null);
            DataLayerManager.getInstance().recordError("Invalid Zipcode");
        }
    }

    public static boolean isAValidZipCode(String zip) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignUpZipCodeFragment", "isAValidZipCode", new Object[]{zip});
        return Pattern.matches("^\\d{5}(-\\d{4})?$", zip) && !Pattern.matches("([089])\\1{4}", zip);
    }
}
