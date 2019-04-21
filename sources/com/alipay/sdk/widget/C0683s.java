package com.alipay.sdk.widget;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* renamed from: com.alipay.sdk.widget.s */
class C0683s extends WebChromeClient {
    /* renamed from: a */
    final /* synthetic */ WebViewWindow f718a;

    C0683s(WebViewWindow webViewWindow) {
        this.f718a = webViewWindow;
    }

    public void onProgressChanged(WebView webView, int i) {
        if (i == 100) {
            this.f718a.f673d.setVisibility(4);
            return;
        }
        if (4 == this.f718a.f673d.getVisibility()) {
            this.f718a.f673d.setVisibility(0);
        }
        this.f718a.f673d.setProgress(i);
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        return this.f718a.f675g.mo8119a(this.f718a, str, str2, str3, jsPromptResult);
    }

    public void onReceivedTitle(WebView webView, String str) {
        this.f718a.f675g.mo8118a(this.f718a, str);
    }
}
