package com.aps;

import java.util.concurrent.Callable;

/* compiled from: DiskLruCache */
/* renamed from: com.aps.i */
class C1260i implements Callable<Void> {
    /* renamed from: a */
    final /* synthetic */ C1259h f4481a;

    C1260i(C1259h c1259h) {
        this.f4481a = c1259h;
    }

    /* renamed from: a */
    public Void call() throws Exception {
        synchronized (this.f4481a) {
            if (this.f4481a.f4476k == null) {
            } else {
                this.f4481a.m5671h();
                if (this.f4481a.m5669f()) {
                    this.f4481a.m5666e();
                    this.f4481a.f4478m = 0;
                }
            }
        }
        return null;
    }
}
