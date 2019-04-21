package com.amap.api.mapcore.util;

/* compiled from: AMapDelegateImp */
/* renamed from: com.amap.api.mapcore.util.j */
class C0855j implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AMapDelegateImp f2023a;

    C0855j(AMapDelegateImp aMapDelegateImp) {
        this.f2023a = aMapDelegateImp;
    }

    public synchronized void run() {
        if (this.f2023a.f1612ba) {
            this.f2023a.f1584aZ = true;
            this.f2023a.f1612ba = false;
        }
    }
}
