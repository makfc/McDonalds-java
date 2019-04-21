package com.amap.api.services.core;

import com.amap.api.services.core.C1095aw.C1093a;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* compiled from: ThreadPool */
/* renamed from: com.amap.api.services.core.au */
public final class C1092au {
    /* renamed from: a */
    private static C1092au f3681a = null;
    /* renamed from: b */
    private ExecutorService f3682b;
    /* renamed from: c */
    private ConcurrentHashMap<C1095aw, Future<?>> f3683c = new ConcurrentHashMap();
    /* renamed from: d */
    private C1093a f3684d = new C1094av(this);

    /* renamed from: a */
    public static synchronized C1092au m4785a(int i) {
        C1092au c1092au;
        synchronized (C1092au.class) {
            if (f3681a == null) {
                f3681a = new C1092au(i);
            }
            c1092au = f3681a;
        }
        return c1092au;
    }

    private C1092au(int i) {
        try {
            this.f3682b = Executors.newFixedThreadPool(i);
        } catch (Throwable th) {
            C1099ax.m4800a(th, "TPool", "ThreadPool");
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private synchronized void m4787a(C1095aw c1095aw, boolean z) {
        try {
            Future future = (Future) this.f3683c.remove(c1095aw);
            if (z && future != null) {
                future.cancel(true);
            }
        } catch (Throwable th) {
            C1099ax.m4800a(th, "TPool", "removeQueue");
            th.printStackTrace();
        }
        return;
    }
}
