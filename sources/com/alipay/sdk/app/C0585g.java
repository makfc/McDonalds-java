package com.alipay.sdk.app;

/* renamed from: com.alipay.sdk.app.g */
class C0585g implements Runnable {
    /* renamed from: a */
    final /* synthetic */ String f490a;
    /* renamed from: b */
    final /* synthetic */ boolean f491b;
    /* renamed from: c */
    final /* synthetic */ H5PayCallback f492c;
    /* renamed from: d */
    final /* synthetic */ PayTask f493d;

    C0585g(PayTask payTask, String str, boolean z, H5PayCallback h5PayCallback) {
        this.f493d = payTask;
        this.f490a = str;
        this.f491b = z;
        this.f492c = h5PayCallback;
    }

    public void run() {
        this.f492c.onPayResult(this.f493d.h5Pay(this.f490a, this.f491b));
    }
}
