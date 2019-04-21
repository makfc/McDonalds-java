package com.amap.api.mapcore.util;

import java.util.concurrent.Callable;

/* compiled from: DiskLruCache */
/* renamed from: com.amap.api.mapcore.util.fl */
class C0843fl implements Callable<Void> {
    /* renamed from: a */
    final /* synthetic */ DiskLruCache f1981a;

    C0843fl(DiskLruCache diskLruCache) {
        this.f1981a = diskLruCache;
    }

    /* renamed from: a */
    public Void call() throws Exception {
        synchronized (this.f1981a) {
            if (this.f1981a.f1975k == null) {
            } else {
                this.f1981a.m2786j();
                if (this.f1981a.m2784h()) {
                    this.f1981a.m2783g();
                    this.f1981a.f1977m = 0;
                }
            }
        }
        return null;
    }
}
