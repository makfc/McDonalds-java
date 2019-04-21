package com.mcdonalds.app.customer;

import android.content.ActivityNotFoundException;
import android.graphics.Bitmap;
import android.net.MailTo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.web.WebFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.services.analytics.BusinessArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class TermsOfServiceFragment extends URLNavigationFragment implements OnCheckedChangeListener {
    private String mAgeVerification;
    private Button mAgreeButton;
    private CheckBox mAgreeCheckBox;
    private CheckBox mDrivingWarningCheckBox;
    private String mLink;
    private boolean mShowDrivingWarning;
    private String mTitle;
    private WebView mWebView;

    /* renamed from: com.mcdonalds.app.customer.TermsOfServiceFragment$1 */
    class C30811 implements OnClickListener {
        C30811() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(TermsOfServiceFragment.this.getAnalyticsTitle(), "I Agree");
            AnalyticsUtils.trackEvent(BusinessArgs.EVENT_ACCEPT_PRIVACY_TERMS);
            TermsOfServiceFragment.this.startActivity(SignUpActivity.class);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.TermsOfServiceFragment$2 */
    class C30822 extends WebChromeClient {
        C30822() {
        }

        public void onGeolocationPermissionsShowPrompt(String origin, Callback callback) {
            Ensighten.evaluateEvent(this, "onGeolocationPermissionsShowPrompt", new Object[]{origin, callback});
            callback.invoke(origin, true, false);
        }
    }

    /* renamed from: com.mcdonalds.app.customer.TermsOfServiceFragment$3 */
    class C30833 extends WebViewClient {
        C30833() {
        }

        public void onPageFinished(WebView view, String url) {
            Ensighten.evaluateEvent(this, "onPageFinished", new Object[]{view, url});
            super.onPageFinished(view, url);
            if (TermsOfServiceFragment.this.getNavigationActivity() != null) {
                if (TextUtils.isEmpty(view.getTitle())) {
                    TermsOfServiceFragment.this.getNavigationActivity().setTitle("");
                } else {
                    TermsOfServiceFragment.this.getNavigationActivity().setTitle(TermsOfServiceFragment.this.getResources().getString(C2658R.string.tac_privacy_policy_header));
                }
                TermsOfServiceFragment.this.getNavigationActivity().setTitle("");
                if (TextUtils.isEmpty(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.TermsOfServiceFragment", "access$000", new Object[]{TermsOfServiceFragment.this}))) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.TermsOfServiceFragment", "access$100", new Object[]{TermsOfServiceFragment.this}).setEnabled(true);
                }
                if (TextUtils.isEmpty(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.TermsOfServiceFragment", "access$000", new Object[]{TermsOfServiceFragment.this})) && !Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.TermsOfServiceFragment", "access$200", new Object[]{TermsOfServiceFragment.this})) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.TermsOfServiceFragment", "access$100", new Object[]{TermsOfServiceFragment.this}).setEnabled(true);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.TermsOfServiceFragment", "access$100", new Object[]{TermsOfServiceFragment.this}).setBackgroundDrawable(TermsOfServiceFragment.this.getResources().getDrawable(C2358R.C2359drawable.button_red));
                }
            }
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Ensighten.evaluateEvent(this, "shouldOverrideUrlLoading", new Object[]{view, url});
            if (url != null && url.startsWith(URLNavigationActivity.URI_SCHEME) && TermsOfServiceFragment.this.getNavigationActivity() != null) {
                TermsOfServiceFragment.this.openSelfUrl(url);
                return true;
            } else if (url == null || !url.startsWith("mailto:")) {
                return false;
            } else {
                MailTo mt = MailTo.parse(url);
                try {
                    TermsOfServiceFragment.this.startActivity(WebFragment.newEmailIntent(mt.getTo(), mt.getSubject(), mt.getBody(), mt.getCc()));
                    return true;
                } catch (ActivityNotFoundException e) {
                    return true;
                }
            }
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Ensighten.evaluateEvent(this, "onPageStarted", new Object[]{view, url, favicon});
            super.onPageStarted(view, url, favicon);
            TermsOfServiceFragment.this.getNavigationActivity().setTitle(Ensighten.evaluateEvent(null, "com.mcdonalds.app.customer.TermsOfServiceFragment", "access$300", new Object[]{TermsOfServiceFragment.this}));
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(C2658R.layout.fragment_terms_of_service, container, false);
        this.mAgreeButton = (Button) mainView.findViewById(C2358R.C2357id.agree_button);
        this.mAgreeButton.setEnabled(false);
        this.mAgreeButton.setOnClickListener(new C30811());
        this.mAgeVerification = (String) Configuration.getSharedInstance().getValueForKey("minimunRequiredAge");
        if (TextUtils.isEmpty(this.mAgeVerification)) {
            mainView.findViewById(C2358R.C2357id.ll_checkbox_layout_agree).setVisibility(8);
        } else {
            this.mAgreeCheckBox = (CheckBox) mainView.findViewById(C2358R.C2357id.agree_checkbox);
            this.mAgreeCheckBox.setText(getResources().getString(C2658R.string.age_verification, new Object[]{this.mAgeVerification}));
            this.mAgreeCheckBox.setChecked(false);
            this.mAgreeCheckBox.setOnCheckedChangeListener(this);
        }
        this.mShowDrivingWarning = Configuration.getSharedInstance().getBooleanForKey("showDrivingWarning");
        if (this.mShowDrivingWarning) {
            this.mDrivingWarningCheckBox = (CheckBox) mainView.findViewById(C2358R.C2357id.driving_warning_checkbox);
            this.mDrivingWarningCheckBox.setChecked(false);
            this.mDrivingWarningCheckBox.setOnCheckedChangeListener(this);
        } else {
            mainView.findViewById(C2358R.C2357id.ll_checkbox_layout_driving).setVisibility(8);
        }
        this.mTitle = getString(C2658R.string.title_loading);
        String tocURL = AppUtils.getLocalisedLegalUrl("registerTOC");
        if (tocURL != null) {
            new Bundle().putString("link", tocURL);
            this.mLink = tocURL;
        } else {
            AsyncException.report("No terms URL Defined");
        }
        this.mWebView = (WebView) mainView.findViewById(C2358R.C2357id.main_webview);
        if (this.mWebView != null) {
            this.mWebView.getSettings().setSupportZoom(true);
            this.mWebView.getSettings().setLoadWithOverviewMode(true);
            this.mWebView.getSettings().setBuiltInZoomControls(true);
            this.mWebView.getSettings().setDisplayZoomControls(true);
            this.mWebView.getSettings().setUseWideViewPort(true);
            this.mWebView.getSettings().setGeolocationEnabled(true);
            this.mWebView.getSettings().setJavaScriptEnabled(true);
            this.mWebView.setWebChromeClient(new C30822());
            this.mWebView.setWebViewClient(new C30833());
            this.mWebView.loadUrl(this.mLink);
        }
        return mainView;
    }

    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return "";
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_register_terms);
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        boolean isDrivingChecked;
        boolean z = true;
        Ensighten.evaluateEvent(this, "onCheckedChanged", new Object[]{buttonView, new Boolean(isChecked)});
        if (this.mDrivingWarningCheckBox == null || this.mDrivingWarningCheckBox.isChecked()) {
            isDrivingChecked = true;
        } else {
            isDrivingChecked = false;
        }
        boolean isAgreeChecked;
        if (this.mAgreeCheckBox == null || this.mAgreeCheckBox.isChecked()) {
            isAgreeChecked = true;
        } else {
            isAgreeChecked = false;
        }
        Button button = this.mAgreeButton;
        if (!(isDrivingChecked && isAgreeChecked)) {
            z = false;
        }
        button.setEnabled(z);
    }
}
