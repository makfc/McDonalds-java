package com.baidu.android.pushservice.p026j;

import com.baidu.android.pushservice.p036h.C1425a;
import java.util.concurrent.TimeUnit;

/* renamed from: com.baidu.android.pushservice.j.d */
public class C1462d {
    /* renamed from: a */
    private static C1459a f5153a;
    /* renamed from: b */
    private static C1462d f5154b;

    /* renamed from: com.baidu.android.pushservice.j.d$1 */
    class C14611 extends Thread {
        C14611() {
        }

        public void run() {
            C1462d.this.mo13939b();
        }
    }

    public C1462d() {
        Runtime.getRuntime().addShutdownHook(new C14611());
        f5153a = new C1459a(3, 100, 1, TimeUnit.MINUTES, new C1460b());
    }

    /* renamed from: a */
    public static C1462d m6637a() {
        if (f5154b == null || f5153a == null || f5153a.isShutdown() || f5153a.isTerminated()) {
            f5154b = new C1462d();
        }
        return f5154b;
    }

    /* renamed from: a */
    public boolean mo13938a(C1281c c1281c) {
        try {
            f5153a.submit(c1281c);
            return true;
        } catch (Exception e) {
            C1425a.m6444e("ThreadPool", "submitRunnable e: " + e);
            return false;
        }
    }

    /* renamed from: b */
    public void mo13939b() {
        if (f5153a != null) {
            try {
                f5153a.getQueue().clear();
                f5153a.shutdown();
            } catch (Exception e) {
                C1425a.m6442c("ThreadPool", " ThreadPool shutdown e: " + e);
            }
        }
    }
}
