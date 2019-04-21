package com.amap.api.mapcore2d;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.amap.api.mapcore2d.k */
class CameraChangeFinishObserver {
    /* renamed from: a */
    private static CameraChangeFinishObserver f2978a = new CameraChangeFinishObserver();
    /* renamed from: b */
    private ArrayList<C0923a> f2979b = new ArrayList();

    /* compiled from: CameraChangeFinishObserver */
    /* renamed from: com.amap.api.mapcore2d.k$a */
    public interface C0923a {
        /* renamed from: Q */
        void mo9934Q();
    }

    CameraChangeFinishObserver() {
    }

    /* renamed from: a */
    public static CameraChangeFinishObserver m4312a() {
        return f2978a;
    }

    /* renamed from: b */
    public void mo10303b() {
        Iterator it = this.f2979b.iterator();
        while (it.hasNext()) {
            C0923a c0923a = (C0923a) it.next();
            if (c0923a != null) {
                c0923a.mo9934Q();
            }
        }
    }

    /* renamed from: a */
    public void mo10302a(C0923a c0923a) {
        if (c0923a != null) {
            this.f2979b.add(c0923a);
        }
    }

    /* renamed from: b */
    public void mo10304b(C0923a c0923a) {
        if (c0923a != null) {
            this.f2979b.remove(c0923a);
        }
    }
}
