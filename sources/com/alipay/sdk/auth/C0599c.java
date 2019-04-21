package com.alipay.sdk.auth;

/* renamed from: com.alipay.sdk.auth.c */
class C0599c implements Runnable {
    /* renamed from: a */
    final /* synthetic */ String f536a;
    /* renamed from: b */
    final /* synthetic */ AuthActivity f537b;

    C0599c(AuthActivity authActivity, String str) {
        this.f537b = authActivity;
        this.f536a = str;
    }

    public void run() {
        try {
            this.f537b.f528c.loadUrl("javascript:" + this.f536a);
        } catch (Exception e) {
        }
    }
}
