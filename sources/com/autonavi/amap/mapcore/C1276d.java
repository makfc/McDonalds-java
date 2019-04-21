package com.autonavi.amap.mapcore;

import java.util.Hashtable;

/* compiled from: TilesProcessingCtrl */
/* renamed from: com.autonavi.amap.mapcore.d */
class C1276d {
    /* renamed from: a */
    int f4569a = 0;
    /* renamed from: b */
    long f4570b;
    /* renamed from: c */
    boolean f4571c = true;
    /* renamed from: d */
    private Hashtable<String, TilesProcessingCtrl> f4572d = new Hashtable();

    /* renamed from: a */
    public void mo13426a(String str) {
        this.f4572d.remove(str);
    }

    /* renamed from: b */
    public boolean mo13428b(String str) {
        return this.f4572d.get(str) != null;
    }

    /* renamed from: c */
    public void mo13429c(String str) {
        this.f4572d.put(str, new TilesProcessingCtrl(str, 0));
    }

    /* renamed from: a */
    public void mo13425a() {
        this.f4572d.clear();
    }

    public C1276d() {
        mo13427b();
    }

    /* renamed from: b */
    public void mo13427b() {
        this.f4570b = System.currentTimeMillis();
    }
}
