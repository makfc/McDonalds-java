package com.alipay.sdk.app;

import android.webkit.SslErrorHandler;
import com.alipay.sdk.widget.C0668e;

/* renamed from: com.alipay.sdk.app.c */
class C0581c implements Runnable {
    /* renamed from: a */
    final /* synthetic */ SslErrorHandler f485a;
    /* renamed from: b */
    final /* synthetic */ C0580b f486b;

    C0581c(C0580b c0580b, SslErrorHandler sslErrorHandler) {
        this.f486b = c0580b;
        this.f485a = sslErrorHandler;
    }

    public void run() {
        C0668e.m1120a(this.f486b.f480a, "安全警告", "安全连接证书校验无效，将无法保证访问数据的安全性，可能存在风险，请选择是否继续？", "继续", new C0582d(this), "退出", new C0583e(this));
    }
}
