package com.amap.api.mapcore2d;

/* compiled from: ThreadTask */
/* renamed from: com.amap.api.mapcore2d.el */
public abstract class C1025el implements Runnable {
    /* renamed from: d */
    C1026a f2909d;

    /* compiled from: ThreadTask */
    /* renamed from: com.amap.api.mapcore2d.el$a */
    interface C1026a {
        /* renamed from: a */
        void mo10282a(C1025el c1025el);

        /* renamed from: b */
        void mo10283b(C1025el c1025el);
    }

    /* renamed from: a */
    public abstract void mo10280a();

    public final void run() {
        try {
            if (this.f2909d != null) {
                this.f2909d.mo10282a(this);
            }
            if (!Thread.interrupted()) {
                mo10280a();
                if (!Thread.interrupted() && this.f2909d != null) {
                    this.f2909d.mo10283b(this);
                }
            }
        } catch (Throwable th) {
            C0990dd.m4098b(th, "ThreadTask", "run");
            th.printStackTrace();
        }
    }
}
