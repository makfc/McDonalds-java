package com.amap.api.services.core;

import java.util.concurrent.Callable;

/* compiled from: DiskLruCache */
/* renamed from: com.amap.api.services.core.bk */
class C1115bk implements Callable<Void> {
    /* renamed from: a */
    final /* synthetic */ C1114bj f3763a;

    C1115bk(C1114bj c1114bj) {
        this.f3763a = c1114bj;
    }

    /* renamed from: a */
    public Void call() throws Exception {
        synchronized (this.f3763a) {
            if (this.f3763a.f3757k == null) {
            } else {
                this.f3763a.m4929j();
                if (this.f3763a.m4927h()) {
                    this.f3763a.m4926g();
                    this.f3763a.f3759m = 0;
                }
            }
        }
        return null;
    }
}
