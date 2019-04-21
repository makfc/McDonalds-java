package com.mcdonalds.app.web;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.MailTo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.configuration.Configuration;

@SuppressLint({"ValidFragment"})
public class WebFragment extends URLNavigationFragment {
    private String mAnalyticsTitle = null;
    private FullScreenChromeClient mClient;
    private FrameLayout mContentView;
    private View mCustomView;
    private CustomViewCallback mCustomViewCallback;
    private boolean mFullScreenVisible = false;
    private String mLink;
    private WebFragmentListener mListener;
    private FrameLayout mTargetView;
    private String mTitle;
    private int mViewTitle;
    private WebView mWebView;
    private final WebViewClient mWebviewClient = new C38681();

    /* renamed from: com.mcdonalds.app.web.WebFragment$1 */
    class C38681 extends WebViewClient {
        C38681() {
        }

        public void onPageFinished(WebView view, String url) {
            Ensighten.evaluateEvent(this, "onPageFinished", new Object[]{view, url});
            super.onPageFinished(view, url);
            if (WebFragment.this.getNavigationActivity() != null) {
                WebFragment.access$102(WebFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$200", new Object[]{WebFragment.this}) != 0 ? WebFragment.this.getString(Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$200", new Object[]{WebFragment.this})) : view.getTitle());
                WebFragment.this.getNavigationActivity().setTitle(Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$100", new Object[]{WebFragment.this}));
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$300", new Object[]{WebFragment.this}) != null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$300", new Object[]{WebFragment.this}).onPageLoaded();
                }
                view.loadUrl("javascript:(function(){document.getElementsByTagName('header')[0].style.display='none';})();");
            }
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Ensighten.evaluateEvent(this, "shouldOverrideUrlLoading", new Object[]{view, url});
            if (url != null && url.startsWith(URLNavigationActivity.URI_SCHEME) && WebFragment.this.getNavigationActivity() != null) {
                WebFragment.this.openSelfUrl(url);
                return true;
            } else if (url != null && url.startsWith("mailto:")) {
                MailTo mt = MailTo.parse(url);
                WebFragment.this.startActivity(WebFragment.newEmailIntent(mt.getTo(), mt.getSubject(), mt.getBody(), mt.getCc()));
                return true;
            } else if (url == null || !url.startsWith("tel:")) {
                return super.shouldOverrideUrlLoading(view, url);
            } else {
                WebFragment.this.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(url)));
                return true;
            }
        }
    }

    private class FullScreenChromeClient extends WebChromeClient {
        private FullScreenChromeClient() {
        }

        /* synthetic */ FullScreenChromeClient(WebFragment x0, C38681 x1) {
            this();
        }

        public void onGeolocationPermissionsShowPrompt(String origin, Callback callback) {
            Ensighten.evaluateEvent(this, "onGeolocationPermissionsShowPrompt", new Object[]{origin, callback});
            callback.invoke(origin, true, false);
        }

        public void onShowCustomView(View view, CustomViewCallback callback) {
            Ensighten.evaluateEvent(this, "onShowCustomView", new Object[]{view, callback});
            super.onShowCustomView(view, callback);
            WebFragment.access$402(WebFragment.this, callback);
            WebFragment.access$502(WebFragment.this, view);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$600", new Object[]{WebFragment.this}).setVisibility(8);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$700", new Object[]{WebFragment.this}).addView(view);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$700", new Object[]{WebFragment.this}).setVisibility(0);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$700", new Object[]{WebFragment.this}).bringToFront();
            WebFragment.access$802(WebFragment.this, true);
        }

        public void onHideCustomView() {
            Ensighten.evaluateEvent(this, "onHideCustomView", null);
            super.onHideCustomView();
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$500", new Object[]{WebFragment.this}) != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$500", new Object[]{WebFragment.this}).setVisibility(8);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$700", new Object[]{WebFragment.this}).removeView(Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$500", new Object[]{WebFragment.this}));
                WebFragment.access$502(WebFragment.this, null);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$700", new Object[]{WebFragment.this}).setVisibility(8);
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$400", new Object[]{WebFragment.this}).onCustomViewHidden();
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$600", new Object[]{WebFragment.this}).setVisibility(0);
                WebFragment.access$802(WebFragment.this, false);
            }
        }
    }

    public interface WebFragmentListener {
        void onPageLoaded();
    }

    static /* synthetic */ String access$102(WebFragment x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$102", new Object[]{x0, x1});
        x0.mTitle = x1;
        return x1;
    }

    static /* synthetic */ CustomViewCallback access$402(WebFragment x0, CustomViewCallback x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$402", new Object[]{x0, x1});
        x0.mCustomViewCallback = x1;
        return x1;
    }

    static /* synthetic */ View access$502(WebFragment x0, View x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$502", new Object[]{x0, x1});
        x0.mCustomView = x1;
        return x1;
    }

    static /* synthetic */ boolean access$802(WebFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "access$802", new Object[]{x0, new Boolean(x1)});
        x0.mFullScreenVisible = x1;
        return x1;
    }

    @SuppressLint({"ValidFragment"})
    public WebFragment(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return this.mAnalyticsTitle;
    }

    /* Access modifiers changed, original: protected */
    public String getDataLayerPage() {
        Ensighten.evaluateEvent(this, "getDataLayerPage", null);
        Configuration config = Configuration.getSharedInstance();
        if (this.mLink == null) {
            return super.getDataLayerPage();
        }
        if (this.mLink.equals(AppUtils.getLocalisedLegalUrl("registerTOC"))) {
            return "Terms";
        }
        if (this.mLink.equals(AppUtils.getLocalisedLegalUrl("privacy"))) {
            return "Privacy";
        }
        if (this.mLink.equals(AppUtils.getLocalisedLegalUrl("faq"))) {
            return "FAQ";
        }
        if (this.mLink.equals(config.getStringForKey("interface.aboutMcDonald.careers"))) {
            return "Career";
        }
        if (this.mLink.equals(config.getStringForKey("interface.aboutMcDonald.contactUs"))) {
            return "ContactUs";
        }
        if (this.mLink.equals(config.getStringForKey("interface.aboutMcDonald.charity"))) {
            return "RMHC";
        }
        if (this.mLink.equals(config.getStringForKey("interface.nutritionalInfo.legalDisclaimer"))) {
            return "NutritionInfo";
        }
        if (this.mLink.equals(AppUtils.getLocalisedLegalUrl("eula"))) {
            return "EULA";
        }
        if (this.mTitle.equals("Customer Support")) {
            return "CustomerSupport";
        }
        return super.getDataLayerPage();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(C2658R.layout.fragment_web, container, false);
        getNavigationActivity().setTitle(this.mTitle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mViewTitle = arguments.getInt("view_title", 0);
            this.mLink = arguments.getString("link");
            this.mAnalyticsTitle = arguments.getString("analytics_title");
        }
        this.mWebView = (WebView) mainView.findViewById(C2358R.C2357id.main_webview);
        if (VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(this.mWebView, true);
        }
        this.mClient = new FullScreenChromeClient(this, null);
        this.mContentView = (FrameLayout) mainView.findViewById(C2358R.C2357id.main_content);
        this.mTargetView = (FrameLayout) mainView.findViewById(C2358R.C2357id.target_view);
        if (this.mWebView != null) {
            if (this.mWebView.getSettings() != null) {
                this.mWebView.getSettings().setSupportZoom(true);
                this.mWebView.getSettings().setLoadWithOverviewMode(true);
                this.mWebView.getSettings().setBuiltInZoomControls(true);
                this.mWebView.getSettings().setDisplayZoomControls(true);
                this.mWebView.getSettings().setUseWideViewPort(true);
                this.mWebView.getSettings().setGeolocationEnabled(true);
                this.mWebView.getSettings().setJavaScriptEnabled(true);
                this.mWebView.getSettings().setDomStorageEnabled(true);
            }
            this.mWebView.setWebChromeClient(this.mClient);
            this.mWebView.setWebViewClient(this.mWebviewClient);
            this.mWebView.loadUrl(this.mLink);
        }
        return mainView;
    }

    public boolean canGoBack() {
        Ensighten.evaluateEvent(this, "canGoBack", null);
        return this.mWebView.canGoBack();
    }

    public void goBack() {
        Ensighten.evaluateEvent(this, "goBack", null);
        if (!this.mFullScreenVisible) {
            this.mWebView.goBack();
        } else if (this.mCustomView != null) {
            this.mClient.onHideCustomView();
        }
    }

    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return this.mTitle;
    }

    public static Intent newEmailIntent(String address, String subject, String body, String cc) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.web.WebFragment", "newEmailIntent", new Object[]{address, subject, body, cc});
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{address});
        intent.putExtra("android.intent.extra.TEXT", body);
        intent.putExtra("android.intent.extra.SUBJECT", subject);
        intent.putExtra("android.intent.extra.CC", cc);
        intent.setType("message/rfc822");
        return intent;
    }
}
