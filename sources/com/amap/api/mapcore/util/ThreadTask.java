package com.amap.api.mapcore.util;

/* renamed from: com.amap.api.mapcore.util.gc */
public abstract class ThreadTask implements Runnable {
    /* renamed from: d */
    C0764a f1413d;

    /* compiled from: ThreadTask */
    /* renamed from: com.amap.api.mapcore.util.gc$a */
    interface C0764a {
        /* renamed from: a */
        void mo8934a(ThreadTask threadTask);

        /* renamed from: b */
        void mo8935b(ThreadTask threadTask);

        /* renamed from: c */
        void mo8936c(ThreadTask threadTask);
    }

    /* renamed from: a */
    public abstract void mo8931a();

    public final void run() {
        try {
            if (this.f1413d != null) {
                this.f1413d.mo8934a(this);
            }
            if (!Thread.interrupted()) {
                mo8931a();
                if (!Thread.interrupted() && this.f1413d != null) {
                    this.f1413d.mo8935b(this);
                }
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "ThreadTask", "run");
            th.printStackTrace();
        }
    }

    /* renamed from: e */
    public final void mo8932e() {
        try {
            if (this.f1413d != null) {
                this.f1413d.mo8936c(this);
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "ThreadTask", "cancelTask");
            th.printStackTrace();
        }
    }
}
