package com.mcdonalds.app.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;

public class ChangeGenderFragment extends URLNavigationFragment {
    public static final String NAME = ChangeGenderFragment.class.getSimpleName();
    private static Spinner mGender;
    private static View mSaveButton;
    private CustomerProfile mCustomerProfile;
    private boolean mFirstLoad;
    private final OnItemSelectedListener mGenderSelected = new C29362();
    private ArrayAdapter<String> mGendersAdapter;
    private final OnClickListener mOnClickSave = new C29351();
    private String mSelectedGender;

    /* renamed from: com.mcdonalds.app.account.ChangeGenderFragment$1 */
    class C29351 implements OnClickListener {

        /* renamed from: com.mcdonalds.app.account.ChangeGenderFragment$1$1 */
        class C29341 implements AsyncListener<CustomerProfile> {
            C29341() {
            }

            public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                UIUtils.stopActivityIndicator();
                if (exception != null || response == null) {
                    ChangeGenderFragment.access$200(ChangeGenderFragment.this);
                    AsyncException.report(exception);
                    return;
                }
                LoginManager.getInstance().getProfile().setGender(response.getGender());
                Intent resultIntent = new Intent();
                resultIntent.putExtra("gender", Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeGenderFragment", "access$100", null).getSelectedItem().toString());
                ChangeGenderFragment.this.getNavigationActivity().setResult(-1, resultIntent);
                ChangeGenderFragment.this.getNavigationActivity().finish();
            }
        }

        C29351() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            CustomerModule module = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            String oldGender = module.getCurrentProfile().getGender();
            CustomerProfile newCustomerProfile = new CustomerProfile();
            ChangeGenderFragment.access$002(ChangeGenderFragment.this, !Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeGenderFragment", "access$000", new Object[]{ChangeGenderFragment.this}).equals("Gender") ? Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeGenderFragment", "access$000", new Object[]{ChangeGenderFragment.this}) : "");
            newCustomerProfile.setGender(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeGenderFragment", "access$000", new Object[]{ChangeGenderFragment.this}).toLowerCase());
            AnalyticsUtils.trackOnClickEvent(ChangeGenderFragment.this.getAnalyticsTitle(), "Save");
            UIUtils.startActivityIndicator(ChangeGenderFragment.this.getActivity(), (int) C2658R.string.lite_dialog_acct_gender);
            module.updateCustomerProfile(newCustomerProfile, new C29341());
        }
    }

    /* renamed from: com.mcdonalds.app.account.ChangeGenderFragment$2 */
    class C29362 implements OnItemSelectedListener {
        C29362() {
        }

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Ensighten.evaluateEvent(this, "onItemSelected", new Object[]{parent, view, new Integer(position), new Long(id)});
            ChangeGenderFragment.access$002(ChangeGenderFragment.this, (String) Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeGenderFragment", "access$300", new Object[]{ChangeGenderFragment.this}).getItem(position));
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeGenderFragment", "access$400", new Object[]{ChangeGenderFragment.this})) {
                ChangeGenderFragment.access$402(ChangeGenderFragment.this, false);
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeGenderFragment", "access$500", null).setEnabled(true);
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {
            Ensighten.evaluateEvent(this, "onNothingSelected", new Object[]{parent});
        }
    }

    static /* synthetic */ String access$002(ChangeGenderFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeGenderFragment", "access$002", new Object[]{x0, x1});
        x0.mSelectedGender = x1;
        return x1;
    }

    static /* synthetic */ void access$200(ChangeGenderFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeGenderFragment", "access$200", new Object[]{x0});
        x0.loadSavedGender();
    }

    static /* synthetic */ boolean access$402(ChangeGenderFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeGenderFragment", "access$402", new Object[]{x0, new Boolean(x1)});
        x0.mFirstLoad = x1;
        return x1;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCustomerProfile = LoginManager.getInstance().getProfile();
    }

    public void onResume() {
        super.onResume();
        mSaveButton.setEnabled(false);
        this.mFirstLoad = true;
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.lite_title_acct_gender);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_account_edit_gender);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_change_gender, container, false);
        mGender = (Spinner) view.findViewById(C2358R.C2357id.gender);
        mGender.setOnItemSelectedListener(this.mGenderSelected);
        this.mGendersAdapter = new ArrayAdapter(getActivity(), C2658R.layout.item_spinner, (Object[]) LoginManager.getInstance().getOptionFields().get("gender"));
        this.mGendersAdapter.setDropDownViewResource(17367049);
        mGender.setAdapter(this.mGendersAdapter);
        loadSavedGender();
        mSaveButton = view.findViewById(C2358R.C2357id.save_btn);
        mSaveButton.setOnClickListener(this.mOnClickSave);
        mSaveButton.setEnabled(false);
        return view;
    }

    private void setGenderSelectedPicker(int pos) {
        Ensighten.evaluateEvent(this, "setGenderSelectedPicker", new Object[]{new Integer(pos)});
        mGender.setSelection(pos);
    }

    private int getGenderPosition(String gender) {
        Ensighten.evaluateEvent(this, "getGenderPosition", new Object[]{gender});
        return this.mGendersAdapter.getPosition(gender.substring(0, 1).toUpperCase() + gender.substring(1));
    }

    private void loadSavedGender() {
        Ensighten.evaluateEvent(this, "loadSavedGender", null);
        this.mSelectedGender = !TextUtils.isEmpty(this.mCustomerProfile.getGender()) ? this.mCustomerProfile.getGender() : "";
        if (TextUtils.isEmpty(this.mSelectedGender)) {
            setGenderSelectedPicker(0);
        } else {
            setGenderSelectedPicker(getGenderPosition(this.mSelectedGender));
        }
    }
}
