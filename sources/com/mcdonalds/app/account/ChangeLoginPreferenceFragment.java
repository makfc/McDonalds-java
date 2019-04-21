package com.mcdonalds.app.account;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.customer.ChooseMethodView;
import com.mcdonalds.app.customer.ChooseMethodView.SelectionListener;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import java.util.Observable;
import java.util.Observer;

public class ChangeLoginPreferenceFragment extends URLNavigationFragment implements SelectionListener, Observer {
    public static String NAME = "change_login_preference";
    private ChooseMethodView mChoosePreference;
    private View mCommunicationsWarning;
    private CustomerProfile mCustomerProfile;
    private AlertDialog mDialog;
    private View mNoEmailWarning;
    private View mNoMobileWarning;

    /* renamed from: com.mcdonalds.app.account.ChangeLoginPreferenceFragment$1 */
    class C29371 implements Runnable {
        C29371() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            ChangeLoginPreferenceFragment.access$000(ChangeLoginPreferenceFragment.this);
        }
    }

    static /* synthetic */ void access$000(ChangeLoginPreferenceFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.ChangeLoginPreferenceFragment", "access$000", new Object[]{x0});
        x0.dismissDialog();
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.account_sign_in_prefs);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginManager.getInstance().addObserver(this);
        this.mCustomerProfile = LoginManager.getInstance().getProfile();
    }

    public void onDestroy() {
        super.onDestroy();
        LoginManager.getInstance().deleteObserver(this);
        dismissDialog();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C2658R.layout.fragment_change_login_preference, container, false);
        this.mChoosePreference = (ChooseMethodView) v.findViewById(C2358R.C2357id.choose_method);
        this.mNoMobileWarning = v.findViewById(C2358R.C2357id.warning_no_mobile_verified);
        this.mNoEmailWarning = v.findViewById(C2358R.C2357id.warning_no_mail_verified);
        this.mCommunicationsWarning = v.findViewById(C2358R.C2357id.warning_communications);
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        boolean enabled;
        super.onActivityCreated(savedInstanceState);
        int selection = this.mCustomerProfile.getLoginPreference();
        if (this.mCustomerProfile.isEmailVerified() && this.mCustomerProfile.isMobileVerified() && !this.mCustomerProfile.isUsingSocialLoginWithoutEmail()) {
            enabled = true;
        } else {
            enabled = false;
        }
        if (selection != 3) {
            this.mChoosePreference.setSelection(selection);
        } else if (this.mCustomerProfile.isUsingSocialLoginWithoutEmail()) {
            selection = 2;
            this.mChoosePreference.setSelection(2);
        } else {
            selection = 1;
            this.mChoosePreference.setSelection(1);
        }
        this.mChoosePreference.setEnabled(enabled);
        this.mChoosePreference.setSelectionListener(this);
        if (enabled) {
            this.mCommunicationsWarning.setVisibility(0);
        } else if (selection == 1) {
            this.mNoMobileWarning.setVisibility(0);
        } else if (!this.mCustomerProfile.isUsingSocialLoginWithoutEmail()) {
            this.mNoEmailWarning.setVisibility(0);
        }
    }

    public void onMethodSelected(int selection) {
        Ensighten.evaluateEvent(this, "onMethodSelected", new Object[]{new Integer(selection)});
        this.mCustomerProfile.setLoginPreference(selection);
        LoginManager.getInstance().updateProfile();
        createDialog();
    }

    public void update(Observable observable, Object data) {
        Ensighten.evaluateEvent(this, "update", new Object[]{observable, data});
        this.mCustomerProfile = LoginManager.getInstance().getProfile();
    }

    private void createDialog() {
        Ensighten.evaluateEvent(this, "createDialog", null);
        Context context = getActivity();
        if (getActivity() != null) {
            View view = LayoutInflater.from(context).inflate(C2658R.layout.dialog_acct_deleted, null, false);
            ((TextView) view.findViewById(C2358R.C2357id.deletedtext)).setText(getString(C2658R.string.sign_in_preferences_updated));
            this.mDialog = new Builder(context).setView(view).setCancelable(false).create();
            this.mDialog.show();
            view.postDelayed(new C29371(), 2000);
        }
    }

    private void dismissDialog() {
        Ensighten.evaluateEvent(this, "dismissDialog", null);
        if (this.mDialog != null) {
            try {
                this.mDialog.dismiss();
            } catch (IllegalStateException e) {
            } finally {
                this.mDialog = null;
            }
        }
    }
}
