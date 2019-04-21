package com.amap.api.mapcore2d;

/* compiled from: ThreadPool */
/* renamed from: com.amap.api.mapcore2d.bn */
class C0935bn {
    /* renamed from: a */
    private Thread[] f2593a;

    public C0935bn(int i, Runnable runnable, Runnable runnable2) {
        this.f2593a = new Thread[i];
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 != 0 || i <= 1) {
                this.f2593a[i2] = new Thread(runnable2);
            } else {
                this.f2593a[i2] = new Thread(runnable);
            }
        }
    }

    /* renamed from: a */
    public void mo10092a() {
        for (Thread thread : this.f2593a) {
            thread.setDaemon(true);
            thread.start();
        }
    }

    /* renamed from: b */
    public void mo10093b() {
        if (this.f2593a != null) {
            int length = this.f2593a.length;
            for (int i = 0; i < length; i++) {
                this.f2593a[i].interrupt();
                this.f2593a[i] = null;
            }
            this.f2593a = null;
        }
    }
}
