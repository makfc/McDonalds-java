package com.mcdonalds.app.customer;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.SparseArray;
import com.ensighten.Ensighten;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.social.SocialLogin;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.ServiceUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;

public class SignOutFragment extends URLNavigationFragment {
    private final AsyncListener<Void> mLogoutListener = new C30563();
    private final OnCancelListener mOnCancelDialog = new C30541();
    private OnClickListener mOnClickDialog = new C30552();
    private CustomerModule module;

    /* renamed from: com.mcdonalds.app.customer.SignOutFragment$1 */
    class C30541 implements OnCancelListener {
        C30541() {
        }

        public void onCancel(DialogInterface dialog) {
            Ensighten.evaluateEvent(this, "onCancel", new Object[]{dialog});
            SignOutFragment.access$000(SignOutFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignOutFragment$2 */
    class C30552 implements OnClickListener {
        C30552() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            if (SignOutFragment.this.getNavigationActivity() == null) {
                return;
            }
            if (which == -1) {
                UIUtils.startActivityIndicator(SignOutFragment.this.getNavigationActivity(), SignOutFragment.this.getResources().getString(C2658R.string.signing_out_of_account));
                LoginManager manager = LoginManager.getInstance();
                if (!Configuration.getSharedInstance().getBooleanForKey("interface.signin.clearUserNameAfterSignOut")) {
                    LocalDataManager.getSharedInstance().setPrefSavedLogin(LoginManager.getInstance().getProfile().getUserName());
                    if (manager.getProfile().isUsingSocialLogin()) {
                        LocalDataManager.getSharedInstance().setPrefSavedSocialNetworkId(manager.getProfile().getSocialServiceAuthenticationID());
                    }
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignOutFragment", "access$100", new Object[]{SignOutFragment.this}).setLoggedInState(false);
                AnalyticsUtils.trackOnClickEvent(SignOutFragment.this.getAnalyticsTitle(), "Sign Out");
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignOutFragment", "access$100", new Object[]{SignOutFragment.this}).logout(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignOutFragment", "access$200", new Object[]{SignOutFragment.this}));
                return;
            }
            UIUtils.stopActivityIndicator();
            SignOutFragment.access$000(SignOutFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.SignOutFragment$3 */
    class C30563 implements AsyncListener<Void> {
        C30563() {
        }

        public void onResponse(Void response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (SignOutFragment.this.getNavigationActivity() == null) {
                return;
            }
            if (exception != null) {
                AsyncException.report(exception);
                return;
            }
            LoginManager.getInstance().setProfile(null);
            ServiceUtils.getSharedInstance().removeFavoriteStoresCache();
            SparseArray<String> customDimensions = new SparseArray();
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignOutFragment", "access$100", new Object[]{SignOutFragment.this}).getCurrentStore() != null) {
                customDimensions.put(1, Integer.toString(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignOutFragment", "access$100", new Object[]{SignOutFragment.this}).getCurrentStore().getStoreId()));
            }
            customDimensions.put(2, SignOutFragment.this.getString(C2658R.string.analytics_guest));
            customDimensions.put(22, SignOutFragment.this.getString(C2658R.string.analytics_not_signed));
            Analytics.track(AnalyticType.Custom, new ArgBuilder().setCustom(customDimensions).build());
            SocialLogin.clearSocialLogins(SignOutFragment.this.getNavigationActivity());
            DataLayerManager.getInstance().setUser(null, "Signed-out", AppUtils.getCurrentMenuType());
            UIUtils.stopActivityIndicator();
            SignOutFragment.this.startActivity(MainActivity.class, "dashboard");
        }
    }

    static /* synthetic */ void access$000(SignOutFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.SignOutFragment", "access$000", new Object[]{x0});
        x0.close();
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_log_out);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.module = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        MCDAlertDialogBuilder.withContext(getNavigationActivity()).setCancelable(true).setOnCancelListener(this.mOnCancelDialog).setPositiveButton((int) C2658R.string.appmenu_sign_out, this.mOnClickDialog).setNegativeButton((int) C2658R.string.cancel, this.mOnClickDialog).setTitle((int) C2658R.string.sign_out_dialog_title).setMessage((int) C2658R.string.sign_out_dialog_message).create().show();
    }

    private void close() {
        Ensighten.evaluateEvent(this, "close", null);
        if (getNavigationActivity() != null) {
            getNavigationActivity().onBackPressed();
        }
    }
}
