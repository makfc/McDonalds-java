package com.alipay.sdk.widget;

import android.app.Activity;
import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import com.alipay.sdk.app.C0580b;
import com.alipay.sdk.app.C0588j;
import com.alipay.sdk.app.C0589k;
import com.alipay.sdk.util.C0657m;

/* renamed from: com.alipay.sdk.widget.h */
public class C0671h extends C0670g {
    /* renamed from: b */
    private C0580b f695b;
    /* renamed from: c */
    private WebView f696c;

    public C0671h(Activity activity) {
        super(activity);
        this.f696c = new WebView(activity);
        m1125a(this.f696c, activity);
        addView(this.f696c);
        this.f695b = new C0580b(activity);
        this.f696c.setWebViewClient(this.f695b);
    }

    /* renamed from: b */
    public boolean mo8150b() {
        if (!this.f696c.canGoBack()) {
            C0588j.m790a(C0588j.m793c());
            this.f694a.finish();
        } else if (this.f695b.mo7996b()) {
            C0589k b = C0589k.m796b(C0589k.NETWORK_ERROR.mo8007a());
            C0588j.m790a(C0588j.m789a(b.mo8007a(), b.mo8008b(), ""));
            this.f694a.finish();
        }
        return true;
    }

    /* renamed from: a */
    public void mo8147a() {
        this.f695b.mo7995a();
        removeAllViews();
    }

    /* renamed from: a */
    private void m1125a(WebView webView, Context context) {
        WebSettings settings = this.f696c.getSettings();
        settings.setUserAgentString(settings.getUserAgentString() + C0657m.m1068c(context));
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
        settings.setAllowFileAccess(false);
        settings.setTextSize(TextSize.NORMAL);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(1);
        this.f696c.resumeTimers();
        this.f696c.setVerticalScrollbarOverlay(true);
        this.f696c.setDownloadListener(new C0672i(this));
        try {
            this.f696c.removeJavascriptInterface("searchBoxJavaBridge_");
            this.f696c.removeJavascriptInterface("accessibility");
            this.f696c.removeJavascriptInterface("accessibilityTraversal");
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public void mo8148a(String str) {
        this.f696c.loadUrl(str);
    }
}
