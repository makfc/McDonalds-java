package com.amap.api.mapcore.util;

/* renamed from: com.amap.api.mapcore.util.cl */
public abstract class CityStateImp {
    /* renamed from: a */
    protected int f1688a;
    /* renamed from: b */
    protected CityObject f1689b;

    /* renamed from: c */
    public abstract void mo9212c();

    public CityStateImp(int i, CityObject cityObject) {
        this.f1688a = i;
        this.f1689b = cityObject;
    }

    /* renamed from: b */
    public int mo9210b() {
        return this.f1688a;
    }

    /* renamed from: a */
    public boolean mo9209a(CityStateImp cityStateImp) {
        return cityStateImp.mo9210b() == mo9210b();
    }

    /* renamed from: b */
    public void mo9211b(CityStateImp cityStateImp) {
        Utility.m2180a(mo9210b() + " ==> " + cityStateImp.mo9210b() + "   " + getClass() + "==>" + cityStateImp.getClass());
    }

    /* renamed from: d */
    public void mo9213d() {
        Utility.m2180a("Wrong call start()  State: " + mo9210b() + "  " + getClass());
    }

    /* renamed from: e */
    public void mo9214e() {
        Utility.m2180a("Wrong call continueDownload()  State: " + mo9210b() + "  " + getClass());
    }

    /* renamed from: f */
    public void mo9215f() {
        Utility.m2180a("Wrong call pause()  State: " + mo9210b() + "  " + getClass());
    }

    /* renamed from: a */
    public void mo9208a() {
        Utility.m2180a("Wrong call delete()  State: " + mo9210b() + "  " + getClass());
    }

    /* renamed from: g */
    public void mo9216g() {
        Utility.m2180a("Wrong call fail()  State: " + mo9210b() + "  " + getClass());
    }

    /* renamed from: h */
    public void mo9217h() {
        Utility.m2180a("Wrong call hasNew()  State: " + mo9210b() + "  " + getClass());
    }

    /* renamed from: i */
    public void mo9218i() {
        Utility.m2180a("Wrong call complete()  State: " + mo9210b() + "  " + getClass());
    }
}
