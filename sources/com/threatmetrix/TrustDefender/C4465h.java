package com.threatmetrix.TrustDefender;

import java.util.concurrent.CountDownLatch;

/* renamed from: com.threatmetrix.TrustDefender.h */
class C4465h implements Runnable {
    /* renamed from: a */
    private C4473TrustDefender f7269a = null;
    /* renamed from: b */
    private CountDownLatch f7270b = null;

    public C4465h(C4473TrustDefender profile) {
        this.f7269a = profile;
        this.f7270b = null;
    }

    public void run() {
        this.f7269a.mo34090a();
        if (this.f7270b != null) {
            this.f7270b.countDown();
        }
    }
}
