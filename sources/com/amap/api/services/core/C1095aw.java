package com.amap.api.services.core;

/* compiled from: ThreadTask */
/* renamed from: com.amap.api.services.core.aw */
public abstract class C1095aw implements Runnable {
    /* renamed from: a */
    C1093a f3686a;

    /* compiled from: ThreadTask */
    /* renamed from: com.amap.api.services.core.aw$a */
    interface C1093a {
        /* renamed from: a */
        void mo12033a(C1095aw c1095aw);

        /* renamed from: b */
        void mo12034b(C1095aw c1095aw);
    }

    /* renamed from: a */
    public abstract void mo12035a();

    public final void run() {
        try {
            if (this.f3686a != null) {
                this.f3686a.mo12033a(this);
            }
            if (!Thread.interrupted()) {
                mo12035a();
                if (!Thread.interrupted() && this.f3686a != null) {
                    this.f3686a.mo12034b(this);
                }
            }
        } catch (Throwable th) {
            C1099ax.m4800a(th, "ThreadTask", "run");
            th.printStackTrace();
        }
    }
}
