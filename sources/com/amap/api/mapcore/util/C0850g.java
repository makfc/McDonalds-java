package com.amap.api.mapcore.util;

/* compiled from: AMapDelegateImp */
/* renamed from: com.amap.api.mapcore.util.g */
class C0850g implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AMapDelegateImp f2014a;

    C0850g(AMapDelegateImp aMapDelegateImp) {
        this.f2014a = aMapDelegateImp;
    }

    public void run() {
        if (this.f2014a.f1594aj != null) {
            this.f2014a.f1577aS = true;
            if (this.f2014a.f1596al != null) {
                this.f2014a.f1596al.setVisible(false);
            }
        }
    }
}
