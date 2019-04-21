package com.alipay.sdk.widget;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* renamed from: com.alipay.sdk.widget.t */
class C0684t extends WebViewClient {
    /* renamed from: a */
    final /* synthetic */ WebViewWindow f719a;

    C0684t(WebViewWindow webViewWindow) {
        this.f719a = webViewWindow;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.f719a.f676h.mo8122b(this.f719a, str)) {
            return true;
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }

    public void onPageFinished(WebView webView, String str) {
        if (!this.f719a.f676h.mo8123c(this.f719a, str)) {
            super.onPageFinished(webView, str);
        }
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        if (!this.f719a.f676h.mo8120a(this.f719a, i, str, str2)) {
            super.onReceivedError(webView, i, str, str2);
        }
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (!this.f719a.f676h.mo8121a(this.f719a, sslErrorHandler, sslError)) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }
    }
}
