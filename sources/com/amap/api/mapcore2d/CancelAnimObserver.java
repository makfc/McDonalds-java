package com.amap.api.mapcore2d;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.amap.api.mapcore2d.m */
class CancelAnimObserver {
    /* renamed from: a */
    private static CancelAnimObserver f3008a = new CancelAnimObserver();
    /* renamed from: b */
    private ArrayList<C0924a> f3009b = new ArrayList();

    /* compiled from: CancelAnimObserver */
    /* renamed from: com.amap.api.mapcore2d.m$a */
    public interface C0924a {
        /* renamed from: O */
        void mo9935O();
    }

    CancelAnimObserver() {
    }

    /* renamed from: a */
    public static CancelAnimObserver m4331a() {
        return f3008a;
    }

    /* renamed from: b */
    public synchronized void mo10306b() {
        Iterator it = this.f3009b.iterator();
        while (it.hasNext()) {
            C0924a c0924a = (C0924a) it.next();
            if (c0924a != null) {
                c0924a.mo9935O();
            }
        }
    }

    /* renamed from: a */
    public synchronized void mo10305a(C0924a c0924a) {
        if (c0924a != null) {
            this.f3009b.add(c0924a);
        }
    }

    /* renamed from: b */
    public synchronized void mo10307b(C0924a c0924a) {
        if (c0924a != null) {
            this.f3009b.remove(c0924a);
        }
    }
}
