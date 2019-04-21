package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.C1025el.C1026a;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* compiled from: ThreadPool */
/* renamed from: com.amap.api.mapcore2d.ek */
public final class C1034ek {
    /* renamed from: a */
    private static C1034ek f2921a = null;
    /* renamed from: b */
    private ExecutorService f2922b;
    /* renamed from: c */
    private ConcurrentHashMap<C1025el, Future<?>> f2923c = new ConcurrentHashMap();
    /* renamed from: d */
    private C1026a f2924d = new C10331();

    /* compiled from: ThreadPool */
    /* renamed from: com.amap.api.mapcore2d.ek$1 */
    class C10331 implements C1026a {
        C10331() {
        }

        /* renamed from: a */
        public void mo10282a(C1025el c1025el) {
        }

        /* renamed from: b */
        public void mo10283b(C1025el c1025el) {
            C1034ek.this.m4296a(c1025el, false);
        }
    }

    /* renamed from: a */
    public static synchronized C1034ek m4294a(int i) {
        C1034ek c1034ek;
        synchronized (C1034ek.class) {
            if (f2921a == null) {
                f2921a = new C1034ek(i);
            }
            c1034ek = f2921a;
        }
        return c1034ek;
    }

    private C1034ek(int i) {
        try {
            this.f2922b = Executors.newFixedThreadPool(i);
        } catch (Throwable th) {
            C0990dd.m4098b(th, "TPool", "ThreadPool");
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private synchronized void m4296a(C1025el c1025el, boolean z) {
        try {
            Future future = (Future) this.f2923c.remove(c1025el);
            if (z && future != null) {
                future.cancel(true);
            }
        } catch (Throwable th) {
            C0990dd.m4098b(th, "TPool", "removeQueue");
            th.printStackTrace();
        }
        return;
    }
}
