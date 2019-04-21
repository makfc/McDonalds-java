package com.mcdonalds.app.gmalite.customer;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.StringUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.web.WebActivity;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class LitePolicyUpdatesFragment extends URLNavigationFragment {
    private Button mContinueButton;
    private TextView mContinueWithoutAccepting;
    private CustomerModule mCustomerModule;
    private OnCheckedChangeListener mOnCheckChanged = new C31654();
    private OnClickListener mOnClickContinue = new C31675();
    private OnClickListener mOnClickContinueWithoutAccepting = new C31696();
    private OnClickListener mOnClickCustomerSupport = new C31643();
    private CheckBox toggle;

    /* renamed from: com.mcdonalds.app.gmalite.customer.LitePolicyUpdatesFragment$1 */
    class C31621 extends ClickableSpan {
        C31621() {
        }

        public void onClick(View widget) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{widget});
            LitePolicyUpdatesFragment.access$000(LitePolicyUpdatesFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LitePolicyUpdatesFragment$2 */
    class C31632 extends ClickableSpan {
        C31632() {
        }

        public void onClick(View widget) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{widget});
            LitePolicyUpdatesFragment.access$100(LitePolicyUpdatesFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LitePolicyUpdatesFragment$3 */
    class C31643 implements OnClickListener {
        C31643() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(LitePolicyUpdatesFragment.this.getAnalyticsTitle(), "Customer Support");
            String link = ConfigurationUtils.getCustomerSupportUrl();
            if (link != null) {
                Bundle attributes = new Bundle();
                attributes.putString("link", link);
                attributes.putString("analytics_title", LitePolicyUpdatesFragment.this.getString(C2658R.string.analytics_screen_customer_support));
                LitePolicyUpdatesFragment.this.getNavigationActivity().startActivity(WebActivity.class, "web", attributes);
                return;
            }
            AsyncException.report("No Customer Support URL Defined");
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LitePolicyUpdatesFragment$4 */
    class C31654 implements OnCheckedChangeListener {
        C31654() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Ensighten.evaluateEvent(this, "onCheckedChanged", new Object[]{buttonView, new Boolean(isChecked)});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LitePolicyUpdatesFragment", "access$200", new Object[]{LitePolicyUpdatesFragment.this}).setEnabled(isChecked);
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LitePolicyUpdatesFragment$5 */
    class C31675 implements OnClickListener {
        C31675() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            String timestamp = UIUtils.getCurrentTimestampUsingFormat("yyyy-MM-dd HH:mm:ss Z", null);
            final String oldTermsAndConditionVersion = LoginManager.getInstance().getProfile().getmTermsAndConditionVersion();
            final String oldPrivacyPolicyVersion = LoginManager.getInstance().getProfile().getmTermsAndConditionVersion();
            LoginManager.getInstance().getProfile().setmTermsAndConditionVersion(timestamp);
            LoginManager.getInstance().getProfile().setmPrivacyPolicyVersion(timestamp);
            AnalyticsUtils.trackOnClickEvent(LitePolicyUpdatesFragment.this.getAnalyticsTitle(), "Continue");
            UIUtils.startActivityIndicator(LitePolicyUpdatesFragment.this.getActivity(), (int) C2658R.string.lite_dialog_policy_updt);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LitePolicyUpdatesFragment", "access$300", new Object[]{LitePolicyUpdatesFragment.this}).updateCustomerProfile(LoginManager.getInstance().getProfile(), new AsyncListener<CustomerProfile>() {
                public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                    UIUtils.stopActivityIndicator();
                    if (exception == null) {
                        LitePolicyUpdatesFragment.this.startActivity(MainActivity.class);
                        LitePolicyUpdatesFragment.this.getNavigationActivity().finish();
                        return;
                    }
                    LoginManager.getInstance().getProfile().setmTermsAndConditionVersion(oldTermsAndConditionVersion);
                    LoginManager.getInstance().getProfile().setmPrivacyPolicyVersion(oldPrivacyPolicyVersion);
                    AsyncException.report(exception);
                }
            });
        }
    }

    /* renamed from: com.mcdonalds.app.gmalite.customer.LitePolicyUpdatesFragment$6 */
    class C31696 implements OnClickListener {

        /* renamed from: com.mcdonalds.app.gmalite.customer.LitePolicyUpdatesFragment$6$1 */
        class C31681 implements AsyncListener<Void> {
            C31681() {
            }

            public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                UIUtils.stopActivityIndicator();
                LitePolicyUpdatesFragment.this.startActivity(MainActivity.class);
                LitePolicyUpdatesFragment.this.getNavigationActivity().finish();
            }
        }

        C31696() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(LitePolicyUpdatesFragment.this.getAnalyticsTitle(), "Decline");
            UIUtils.startActivityIndicator(LitePolicyUpdatesFragment.this.getActivity(), (int) C2658R.string.lite_dialog_policy_updt);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LitePolicyUpdatesFragment", "access$300", new Object[]{LitePolicyUpdatesFragment.this}).logout(new C31681());
        }
    }

    static /* synthetic */ void access$000(LitePolicyUpdatesFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LitePolicyUpdatesFragment", "access$000", new Object[]{x0});
        x0.clickedTermsAndConditions();
    }

    static /* synthetic */ void access$100(LitePolicyUpdatesFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.gmalite.customer.LitePolicyUpdatesFragment", "access$100", new Object[]{x0});
        x0.clickedPrivacyPolicy();
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_policy_updates);
    }

    public void onStart() {
        super.onStart();
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getNavigationActivity().setTitle(getString(C2658R.string.lite_title_policy_updates));
        setHasOptionsMenu(false);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_lite_policy_updates, container, false);
        this.toggle = (CheckBox) view.findViewById(C2358R.C2357id.terms_and_conditions_checkbox);
        this.toggle.setOnCheckedChangeListener(this.mOnCheckChanged);
        configurePolicyUpdatesString((TextView) view.findViewById(C2358R.C2357id.policy_updates_agree));
        this.mContinueButton = (Button) view.findViewById(C2358R.C2357id.continue_button);
        this.mContinueButton.setOnClickListener(this.mOnClickContinue);
        this.mContinueWithoutAccepting = (TextView) view.findViewById(C2358R.C2357id.continue_without_accepting);
        this.mContinueWithoutAccepting.setOnClickListener(this.mOnClickContinueWithoutAccepting);
        ((TextView) view.findViewById(C2358R.C2357id.customer_support_link)).setOnClickListener(this.mOnClickCustomerSupport);
        return view;
    }

    private void configurePolicyUpdatesString(TextView textView) {
        String minDrivingAge;
        Ensighten.evaluateEvent(this, "configurePolicyUpdatesString", new Object[]{textView});
        String minAge = (String) Configuration.getSharedInstance().getValueForKey("interface.termsAndConditions.minimumRequiredAge");
        if (TextUtils.isEmpty(minAge)) {
            minDrivingAge = "";
        } else {
            minDrivingAge = minAge;
        }
        String template = getString(C2658R.string.lite_label_policy_agree);
        SpannableString terms = new SpannableString(getString(C2658R.string.lite_btn_tnc));
        terms.setSpan(new C31621(), 0, terms.length(), 33);
        SpannableString privacyPolicy = new SpannableString(getString(C2658R.string.lite_btn_privacy_policy));
        privacyPolicy.setSpan(new C31632(), 0, privacyPolicy.length(), 33);
        textView.setText(StringUtils.formatWithSpans(template, terms, privacyPolicy, minDrivingAge));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void clickedTermsAndConditions() {
        Ensighten.evaluateEvent(this, "clickedTermsAndConditions", null);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Terms & Conditions");
        String link = AppUtils.getLocalisedLegalUrl("registerTOC");
        if (link != null) {
            Bundle attributes = new Bundle();
            attributes.putString("link", link);
            attributes.putString("analytics_title", getString(C2658R.string.analytics_screen_register_terms));
            getNavigationActivity().startActivity(WebActivity.class, "web", attributes);
            return;
        }
        AsyncException.report("No Privacy Policy URL Defined");
    }

    private void clickedPrivacyPolicy() {
        Ensighten.evaluateEvent(this, "clickedPrivacyPolicy", null);
        AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Privacy Policy");
        String privacyURL = AppUtils.getLocalisedLegalUrl("privacy");
        if (privacyURL != null) {
            Bundle attributes = new Bundle();
            attributes.putString("link", privacyURL);
            attributes.putString("analytics_title", getString(C2658R.string.analytics_screen_register_privacy));
            startActivity(WebActivity.class, attributes);
            return;
        }
        AsyncException.report("No Privacy Policy URL Defined");
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.lite_title_policy_updates);
    }
}
