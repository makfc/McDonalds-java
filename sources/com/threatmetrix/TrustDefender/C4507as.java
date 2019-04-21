package com.threatmetrix.TrustDefender;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* renamed from: com.threatmetrix.TrustDefender.as */
class C4507as extends WebChromeClient {
    /* renamed from: a */
    private final C2625u f7556a;
    /* renamed from: b */
    private final String f7557b = C4549w.m8585a(C4507as.class);

    public C4507as(C2625u m_jsInterface) {
        this.f7556a = m_jsInterface;
    }

    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        C4549w.m8594c(this.f7557b, "onJsAlert() -" + message);
        this.f7556a.mo23198a(message);
        result.confirm();
        return true;
    }
}
