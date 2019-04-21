package com.amap.api.mapcore2d;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.amap.api.mapcore2d.bj */
class StopAnimObserver {
    /* renamed from: a */
    private static StopAnimObserver f2573a = new StopAnimObserver();
    /* renamed from: b */
    private ArrayList<C0922a> f2574b = new ArrayList();

    /* compiled from: StopAnimObserver */
    /* renamed from: com.amap.api.mapcore2d.bj$a */
    public interface C0922a {
        /* renamed from: P */
        void mo9933P();
    }

    StopAnimObserver() {
    }

    /* renamed from: a */
    public static StopAnimObserver m3684a() {
        return f2573a;
    }

    /* renamed from: b */
    public synchronized void mo10085b() {
        Iterator it = this.f2574b.iterator();
        while (it.hasNext()) {
            C0922a c0922a = (C0922a) it.next();
            if (c0922a != null) {
                c0922a.mo9933P();
            }
        }
    }

    /* renamed from: a */
    public synchronized void mo10084a(C0922a c0922a) {
        if (c0922a != null) {
            this.f2574b.remove(c0922a);
        }
    }
}
