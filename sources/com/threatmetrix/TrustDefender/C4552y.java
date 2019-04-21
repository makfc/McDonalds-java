package com.threatmetrix.TrustDefender;

/* renamed from: com.threatmetrix.TrustDefender.y */
final class C4552y extends Thread {
    /* renamed from: a */
    private Runnable f7853a = null;

    public C4552y(Runnable runnable) {
        this.f7853a = runnable;
    }

    /* renamed from: a */
    public final C4537o mo34268a() {
        if (this.f7853a instanceof C4537o) {
            return (C4537o) this.f7853a;
        }
        return null;
    }

    public final void run() {
        this.f7853a.run();
    }

    public final void interrupt() {
        if (this.f7853a instanceof C4537o) {
            this.f7853a.mo34246c();
        }
        super.interrupt();
    }
}
