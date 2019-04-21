package com.alipay.sdk.auth;

import android.webkit.SslErrorHandler;
import com.alipay.sdk.auth.AuthActivity.C0595c;
import com.alipay.sdk.widget.C0668e;

/* renamed from: com.alipay.sdk.auth.d */
class C0600d implements Runnable {
    /* renamed from: a */
    final /* synthetic */ SslErrorHandler f538a;
    /* renamed from: b */
    final /* synthetic */ C0595c f539b;

    C0600d(C0595c c0595c, SslErrorHandler sslErrorHandler) {
        this.f539b = c0595c;
        this.f538a = sslErrorHandler;
    }

    public void run() {
        C0668e.m1120a(AuthActivity.this, "安全警告", "由于您的设备缺少根证书，将无法校验该访问站点的安全性，可能存在风险，请选择是否继续？", "继续", new C0601e(this), "退出", new C0602f(this));
    }
}
