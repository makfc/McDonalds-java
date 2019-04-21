package com.amap.api.mapcore.util;

/* compiled from: AMapDelegateImp */
/* renamed from: com.amap.api.mapcore.util.h */
class C0853h implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AMapDelegateImp f2021a;

    C0853h(AMapDelegateImp aMapDelegateImp) {
        this.f2021a = aMapDelegateImp;
    }

    public void run() {
        if (this.f2021a.f1594aj != null) {
            this.f2021a.f1577aS = false;
            this.f2021a.f1594aj.setVisibility(8);
        }
    }
}
