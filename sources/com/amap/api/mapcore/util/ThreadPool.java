package com.amap.api.mapcore.util;

import com.amap.api.mapcore.util.ThreadTask.C0764a;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* renamed from: com.amap.api.mapcore.util.ga */
public final class ThreadPool {
    /* renamed from: a */
    private static ThreadPool f2015a = null;
    /* renamed from: b */
    private ExecutorService f2016b;
    /* renamed from: c */
    private ConcurrentHashMap<ThreadTask, Future<?>> f2017c = new ConcurrentHashMap();
    /* renamed from: d */
    private C0764a f2018d = new C0851gb(this);

    /* renamed from: a */
    public static synchronized ThreadPool m2831a(int i) {
        ThreadPool threadPool;
        synchronized (ThreadPool.class) {
            if (f2015a == null) {
                f2015a = new ThreadPool(i);
            }
            threadPool = f2015a;
        }
        return threadPool;
    }

    private ThreadPool(int i) {
        try {
            this.f2016b = Executors.newFixedThreadPool(i);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "TPool", "ThreadPool");
            th.printStackTrace();
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    public void mo9434a(com.amap.api.mapcore.util.ThreadTask r4) throws com.amap.api.mapcore.util.AMapCoreException {
        /*
        r3 = this;
        r0 = r3.m2837b(r4);	 Catch:{ Throwable -> 0x0023 }
        if (r0 == 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r0 = r3.f2016b;	 Catch:{ Throwable -> 0x0023 }
        if (r0 == 0) goto L_0x0006;
    L_0x000b:
        r0 = r3.f2016b;	 Catch:{ Throwable -> 0x0023 }
        r0 = r0.isShutdown();	 Catch:{ Throwable -> 0x0023 }
        if (r0 != 0) goto L_0x0006;
    L_0x0013:
        r0 = r3.f2018d;	 Catch:{ Throwable -> 0x0023 }
        r4.f1413d = r0;	 Catch:{ Throwable -> 0x0023 }
        r0 = r3.f2016b;	 Catch:{ RejectedExecutionException -> 0x0036 }
        r0 = r0.submit(r4);	 Catch:{ RejectedExecutionException -> 0x0036 }
        if (r0 == 0) goto L_0x0006;
    L_0x001f:
        r3.m2834a(r4, r0);	 Catch:{ Throwable -> 0x0023 }
        goto L_0x0006;
    L_0x0023:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = "TPool";
        r2 = "addTask";
        com.amap.api.mapcore.util.SDKLogHandler.m2563a(r0, r1, r2);
        r0 = new com.amap.api.mapcore.util.dk;
        r1 = "thread pool has exception";
        r0.<init>(r1);
        throw r0;
    L_0x0036:
        r0 = move-exception;
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ThreadPool.mo9434a(com.amap.api.mapcore.util.gc):void");
    }

    /* renamed from: a */
    public static synchronized void m2832a() {
        synchronized (ThreadPool.class) {
            try {
                if (f2015a != null) {
                    f2015a.m2836b();
                    f2015a = null;
                }
            } catch (Throwable th) {
                SDKLogHandler.m2563a(th, "TPool", "onDestroy");
                th.printStackTrace();
            }
        }
        return;
    }

    /* renamed from: b */
    private void m2836b() {
        try {
            for (Entry key : this.f2017c.entrySet()) {
                Future future = (Future) this.f2017c.get((ThreadTask) key.getKey());
                if (future != null) {
                    future.cancel(true);
                }
            }
            this.f2017c.clear();
            this.f2016b.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "TPool", "destroy");
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    private synchronized boolean m2837b(ThreadTask threadTask) {
        boolean z;
        z = false;
        try {
            z = this.f2017c.containsKey(threadTask);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "TPool", "contain");
            th.printStackTrace();
        }
        return z;
    }

    /* renamed from: a */
    private synchronized void m2834a(ThreadTask threadTask, Future<?> future) {
        try {
            this.f2017c.put(threadTask, future);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "TPool", "addQueue");
            th.printStackTrace();
        }
        return;
    }

    /* renamed from: a */
    private synchronized void m2835a(ThreadTask threadTask, boolean z) {
        try {
            Future future = (Future) this.f2017c.remove(threadTask);
            if (z && future != null) {
                future.cancel(true);
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "TPool", "removeQueue");
            th.printStackTrace();
        }
        return;
    }
}
