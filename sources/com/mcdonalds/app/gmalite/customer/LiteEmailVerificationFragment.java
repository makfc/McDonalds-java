package com.mcdonalds.app.gmalite.customer;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.app.web.WebActivity;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.services.log.MCDLog;

public class LiteEmailVerificationFragment extends URLNavigationFragment {
    private CustomerModule mCustomerModule;
    private OnClickListener mOnClickCustomerSupport = new C31501();
    private OnClickListener mOnClickResendEmailLink = new C31545();
    private DialogInterface.OnClickListener mOnClickVerifyOk = new C31523();
    protected URLNavigationActivity mParent;
    private TextView mResendEmailLink;
    private TextView mUserEmail;
    private String mVerificationCode;
    private TextView mWelcomeTextView;

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteEmailVerificationFragment$1 */
    class C31501 implements OnClickListener {
        C31501() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(LiteEmailVerificationFragment.this.getAnalyticsTitle(), "Customer Support");
            String link = ConfigurationUtils.getCustomerSupportUrl();
            if (link != null) {
                Bundle attributes = new Bundle();
                attributes.putString("link", link);
                attributes.putString("analytics_title", LiteEmailVerificationFragment.this.getString(C2658R.string.analytics_screen_customer_support));
                LiteEmailVerificationFragment.this.getNavigationActivity().startActivity(WebActivity.class, "web", attributes);
                return;
            }
            AsyncException.report("No Customer Support URL Defined");
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteEmailVerificationFragment$2 */
    class C31512 implements AsyncListener<Boolean> {
        C31512() {
        }

        public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (exception == null) {
                MCDLog.debug("Verified Email Successfully.");
                MCDAlertDialogBuilder.withContext(LiteEmailVerificationFragment.this.getNavigationActivity()).setTitle(LiteEmailVerificationFragment.this.getString(C2658R.string.lite_alrt_acct_verif)).setCancelable(false).setMessage(LiteEmailVerificationFragment.this.getString(C2658R.string.lite_alrt_msg_acct_verif)).setPositiveButton(LiteEmailVerificationFragment.this.getString(C2658R.string.f6083ok), Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteEmailVerificationFragment", "access$000", new Object[]{LiteEmailVerificationFragment.this})).create().show();
                return;
            }
            LiteEmailVerificationFragment.access$100(LiteEmailVerificationFragment.this, exception);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteEmailVerificationFragment$3 */
    class C31523 implements DialogInterface.OnClickListener {
        C31523() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteEmailVerificationFragment", "access$200", new Object[]{LiteEmailVerificationFragment.this}).isLoggedIn()) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteEmailVerificationFragment", "access$200", new Object[]{LiteEmailVerificationFragment.this}).getCurrentProfile().getCustomerLoginInfo().setEmailAddressVerified(true);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteEmailVerificationFragment", "access$200", new Object[]{LiteEmailVerificationFragment.this}).getCurrentProfile().setEmailActivated(true);
                LiteEmailVerificationFragment.this.getNavigationActivity().setResult(-1);
                LiteEmailVerificationFragment.this.startActivity(MainActivity.class, ConfigurationUtils.getHomeScreenFragment());
                return;
            }
            LiteEmailVerificationFragment.this.getNavigationActivity().showFragment("gmalite_signin");
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteEmailVerificationFragment$4 */
    class C31534 implements DialogInterface.OnClickListener {
        C31534() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            if (LiteEmailVerificationFragment.this.getNavigationActivity() instanceof MainActivity) {
                LiteEmailVerificationFragment.this.getNavigationActivity().showFragment("gmalite_signin");
                return;
            }
            LiteEmailVerificationFragment.this.getNavigationActivity().startActivity(LiteSignInActivity.class);
            LiteEmailVerificationFragment.this.getNavigationActivity().finish();
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteEmailVerificationFragment$5 */
    class C31545 implements OnClickListener {
        C31545() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(LiteEmailVerificationFragment.this.getAnalyticsTitle(), "Resend Activation Email");
            MCDLog.debug("Clicked Resend Email from Email Verification Activity");
            LiteEmailVerificationFragment.access$300(LiteEmailVerificationFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LiteEmailVerificationFragment$6 */
    class C31566 implements AsyncListener<Void> {

        /* renamed from: com.mcdonalds.app.gmalite.customer.LiteEmailVerificationFragment$6$1 */
        class C31551 implements DialogInterface.OnClickListener {
            C31551() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                dialog.dismiss();
            }
        }

        C31566() {
        }

        public void onResponse(Void response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (exception == null) {
                MCDLog.debug("Email Verification Sent Successfully.");
                MCDAlertDialogBuilder.withContext(LiteEmailVerificationFragment.this.getNavigationActivity()).setTitle(LiteEmailVerificationFragment.this.getString(C2658R.string.lite_alrt_email_sent)).setCancelable(true).setMessage(LiteEmailVerificationFragment.this.getString(C2658R.string.lite_alrt_msg_email_verif_sent)).setNegativeButton(LiteEmailVerificationFragment.this.getString(C2658R.string.lite_alrt_btn_ok), new C31551());
                return;
            }
            AsyncException.report(exception);
        }
    }

    static /* synthetic */ void access$100(LiteEmailVerificationFragment x0, AsyncException x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteEmailVerificationFragment", "access$100", new Object[]{x0, x1});
        x0.handleEmailVerificationError(x1);
    }

    static /* synthetic */ void access$300(LiteEmailVerificationFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LiteEmailVerificationFragment", "access$300", new Object[]{x0});
        x0.resendEmailVerification();
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_account_verification);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mParent = getNavigationActivity();
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_lite_email_verification, container, false);
        this.mWelcomeTextView = (TextView) view.findViewById(C2358R.C2357id.sms_verification_welcome_name);
        if (LoginManager.getInstance().getProfile() != null) {
            this.mWelcomeTextView.setText(getString(C2658R.string.lite_label_welcome_android, LoginManager.getInstance().getProfile().getFirstName()));
            String email = LoginManager.getInstance().getProfile().getEmailAddress();
            this.mUserEmail = (TextView) view.findViewById(2131821086);
            this.mUserEmail.setText(email);
        }
        this.mResendEmailLink = (TextView) view.findViewById(C2358R.C2357id.email_resend_button);
        this.mResendEmailLink.setOnClickListener(this.mOnClickResendEmailLink);
        ((TextView) view.findViewById(C2358R.C2357id.customer_support_link)).setOnClickListener(this.mOnClickCustomerSupport);
        return view;
    }

    public void onResume() {
        super.onResume();
        CustomerProfile profile = LoginManager.getInstance().getProfile();
        if (profile != null) {
            String welcomeText = getResources().getString(C2658R.string.sms_verification_welcome_name_text);
            this.mWelcomeTextView.setText(String.format(welcomeText, new Object[]{""}));
            if (!TextUtils.isEmpty(profile.getFirstName())) {
                this.mWelcomeTextView.setText(String.format(welcomeText, new Object[]{profile.getFirstName()}));
            }
            if (!this.mCustomerModule.isLoggedIn() || getNavigationActivity().getCallingActivity() == null) {
                String uriCode = null;
                Uri data = null;
                Bundle bundle = getArguments();
                if (bundle != null) {
                    data = (Uri) bundle.getParcelable("Uri");
                }
                if (data != null) {
                    uriCode = data.getQueryParameter("verification_code");
                }
                if (TextUtils.isEmpty(uriCode)) {
                    uriCode = "";
                }
                this.mVerificationCode = uriCode;
                if (!TextUtils.isEmpty(this.mVerificationCode)) {
                    verifyEmail();
                    return;
                }
                return;
            }
            resendEmailVerification();
        }
    }

    private void verifyEmail() {
        Ensighten.evaluateEvent(this, "verifyEmail", null);
        MCDLog.debug("Verifying Email");
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Verify Account");
        UIUtils.startActivityIndicator(getNavigationActivity(), getString(C2658R.string.lite_dialog_email_verify));
        this.mCustomerModule.verifyEmail(this.mVerificationCode, LoginManager.getInstance().getProfile(), new C31512());
    }

    private void handleEmailVerificationError(AsyncException exception) {
        Ensighten.evaluateEvent(this, "handleEmailVerificationError", new Object[]{exception});
        if (TextUtils.isEmpty(this.mCustomerModule.getCurrentProfile().getEmailAddress())) {
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle(getString(C2658R.string.error_title)).setCancelable(false).setMessage(getString(C2658R.string.lite_alrt_msg_email_verif_fail)).setPositiveButton(getString(C2658R.string.lite_alrt_btn_ok), new C31534()).create().show();
        } else {
            AsyncException.report(exception);
        }
    }

    private void resendEmailVerification() {
        Ensighten.evaluateEvent(this, "resendEmailVerification", null);
        CustomerProfile profile = LoginManager.getInstance().getProfile();
        UIUtils.startActivityIndicator(getNavigationActivity(), getString(C2658R.string.lite_dialog_email_send));
        this.mCustomerModule.resendActivation(profile, new C31566());
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getResources().getString(C2658R.string.lite_title_acct_verif);
    }
}
