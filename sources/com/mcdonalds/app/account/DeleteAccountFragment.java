package com.mcdonalds.app.account;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.p043ui.NavigationDrawerFragment;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;

public class DeleteAccountFragment extends URLNavigationFragment implements OnClickListener {
    public static final String NAME = DeleteAccountFragment.class.getSimpleName();
    private boolean mDeleteCalled;
    private AsyncListener<String> mDeregisterAsyncListener = new C29602();
    private AlertDialog mDialog;
    private boolean mGoHome;
    private final AsyncListener<Void> mLogoutAsyncListener = new C29613();
    private boolean mStopped;

    /* renamed from: com.mcdonalds.app.account.DeleteAccountFragment$1 */
    class C29581 implements DialogInterface.OnClickListener {
        C29581() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            DeleteAccountFragment.access$002(DeleteAccountFragment.this, true);
            DeleteAccountFragment.access$100(DeleteAccountFragment.this, false);
            UIUtils.startActivityIndicator(DeleteAccountFragment.this.getNavigationActivity(), (int) C2658R.string.deleting_account);
            AnalyticsUtils.trackOnClickEvent(DeleteAccountFragment.this.getAnalyticsTitle(), "Account Delete");
            ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).deregister("no reason supplied", Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.DeleteAccountFragment", "access$200", new Object[]{DeleteAccountFragment.this}));
        }
    }

    /* renamed from: com.mcdonalds.app.account.DeleteAccountFragment$2 */
    class C29602 implements AsyncListener<String> {

        /* renamed from: com.mcdonalds.app.account.DeleteAccountFragment$2$1 */
        class C29591 implements Runnable {
            C29591() {
            }

            public void run() {
                Ensighten.evaluateEvent(this, "run", null);
                ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).logout(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.DeleteAccountFragment", "access$400", new Object[]{DeleteAccountFragment.this}));
                DeleteAccountFragment.access$500(DeleteAccountFragment.this);
            }
        }

        C29602() {
        }

        public void onResponse(String response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (response == null || !response.equals("Pass") || DeleteAccountFragment.this.getNavigationActivity() == null) {
                if (exception != null) {
                    AsyncException.report(exception);
                }
                DeleteAccountFragment.access$002(DeleteAccountFragment.this, false);
                DeleteAccountFragment.access$100(DeleteAccountFragment.this, true);
                return;
            }
            DeleteAccountFragment.access$300(DeleteAccountFragment.this);
            new Handler().postDelayed(new C29591(), 2000);
        }
    }

    /* renamed from: com.mcdonalds.app.account.DeleteAccountFragment$3 */
    class C29613 implements AsyncListener<Void> {
        C29613() {
        }

        public void onResponse(Void response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (exception != null) {
                AsyncException.report(exception);
            } else {
                NavigationDrawerFragment navFragment = (NavigationDrawerFragment) DeleteAccountFragment.this.getNavigationActivity().getSupportFragmentManager().findFragmentById(C2358R.C2357id.navigation_drawer);
                if (navFragment != null) {
                    navFragment.setLoggedInDrawerState(false);
                }
                DataLayerManager.getInstance().setUser(null, "Signed-out", AppUtils.getCurrentMenuType());
            }
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.DeleteAccountFragment", "access$600", new Object[]{DeleteAccountFragment.this})) {
                DeleteAccountFragment.access$802(DeleteAccountFragment.this, true);
            } else {
                DeleteAccountFragment.access$700(DeleteAccountFragment.this);
            }
            DeleteAccountFragment.access$002(DeleteAccountFragment.this, false);
        }
    }

    static /* synthetic */ boolean access$002(DeleteAccountFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.DeleteAccountFragment", "access$002", new Object[]{x0, new Boolean(x1)});
        x0.mDeleteCalled = x1;
        return x1;
    }

    static /* synthetic */ void access$100(DeleteAccountFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.DeleteAccountFragment", "access$100", new Object[]{x0, new Boolean(x1)});
        x0.allowActivityExit(x1);
    }

    static /* synthetic */ void access$300(DeleteAccountFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.DeleteAccountFragment", "access$300", new Object[]{x0});
        x0.createDialog();
    }

    static /* synthetic */ void access$500(DeleteAccountFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.DeleteAccountFragment", "access$500", new Object[]{x0});
        x0.dismissDialog();
    }

    static /* synthetic */ void access$700(DeleteAccountFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.DeleteAccountFragment", "access$700", new Object[]{x0});
        x0.navigateHome();
    }

    static /* synthetic */ boolean access$802(DeleteAccountFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.DeleteAccountFragment", "access$802", new Object[]{x0, new Boolean(x1)});
        x0.mGoHome = x1;
        return x1;
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_account_delete_account);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_delete_account, container, false);
        ((Button) view.findViewById(C2358R.C2357id.delete_btn)).setOnClickListener(this);
        getNavigationActivity().setTitle(C2658R.string.title_activity_delete_account);
        return view;
    }

    public void onDestroy() {
        super.onDestroy();
        dismissDialog();
    }

    public void onStop() {
        super.onStop();
        this.mStopped = true;
    }

    public void onStart() {
        super.onStart();
        this.mStopped = false;
        if (this.mGoHome) {
            this.mGoHome = false;
            navigateHome();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        return true;
    }

    public void onClick(View view) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
        if (!this.mDeleteCalled) {
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setMessage((int) C2658R.string.deletion_warning).setPositiveButton((int) C2658R.string.f6083ok, deleteAccount()).setNegativeButton((int) C2658R.string.cancel, null).create().show();
        }
    }

    private DialogInterface.OnClickListener deleteAccount() {
        Ensighten.evaluateEvent(this, "deleteAccount", null);
        return new C29581();
    }

    private void createDialog() {
        Ensighten.evaluateEvent(this, "createDialog", null);
        Context context = getActivity();
        if (getActivity() != null) {
            this.mDialog = new Builder(context).setView(LayoutInflater.from(context).inflate(C2658R.layout.dialog_acct_deleted, null, false)).setCancelable(false).create();
            this.mDialog.show();
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

    private void allowActivityExit(boolean canExit) {
        Ensighten.evaluateEvent(this, "allowActivityExit", new Object[]{new Boolean(canExit)});
        ((DeleteAccountActivity) getActivity()).setCanExitActivity(canExit);
    }

    private void navigateHome() {
        Ensighten.evaluateEvent(this, "navigateHome", null);
        if (getNavigationActivity() != null) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.setFlags(268468224);
            intent.putExtra(URLNavigationActivity.ARG_FRAGMENT, "dashboard");
            startActivity(intent);
        }
    }
}
