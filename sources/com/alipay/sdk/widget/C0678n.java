package com.alipay.sdk.widget;

import android.webkit.SslErrorHandler;

/* renamed from: com.alipay.sdk.widget.n */
class C0678n implements Runnable {
    /* renamed from: a */
    final /* synthetic */ SslErrorHandler f711a;
    /* renamed from: b */
    final /* synthetic */ C0674j f712b;

    C0678n(C0674j c0674j, SslErrorHandler sslErrorHandler) {
        this.f712b = c0674j;
        this.f711a = sslErrorHandler;
    }

    public void run() {
        C0668e.m1120a(this.f712b.f694a, "安全警告", "安全連接證書校驗無效，將無法保證訪問資料的安全性，可能存在風險，請選擇是否繼續？", "繼續", new C0679o(this), "退出", new C0680p(this));
    }
}
