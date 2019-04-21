package com.amap.api.mapcore2d;

import android.os.Handler;
import android.os.Looper;

/* renamed from: com.amap.api.mapcore2d.d */
abstract class AnimBase {
    /* renamed from: a */
    protected int f2630a;
    /* renamed from: b */
    protected int f2631b;
    /* renamed from: c */
    private Handler f2632c = null;
    /* renamed from: d */
    private int f2633d = 0;
    /* renamed from: e */
    private boolean f2634e = false;
    /* renamed from: f */
    private boolean f2635f = true;
    /* renamed from: g */
    private Runnable f2636g = new C09431();

    /* compiled from: AnimBase */
    /* renamed from: com.amap.api.mapcore2d.d$1 */
    class C09431 implements Runnable {
        C09431() {
        }

        public void run() {
            String str = "run";
            AnimBase.this.m3795h();
            if (AnimBase.this.mo10133f()) {
                long currentTimeMillis = System.currentTimeMillis();
                AnimBase.this.mo10126a();
                AnimBase.this.m3796i();
                long currentTimeMillis2 = System.currentTimeMillis();
                if (currentTimeMillis2 - currentTimeMillis < ((long) AnimBase.this.f2631b)) {
                    try {
                        Thread.sleep(((long) AnimBase.this.f2631b) - (currentTimeMillis2 - currentTimeMillis));
                        return;
                    } catch (InterruptedException e) {
                        C0955ck.m3888a(e, "AnimBase", str);
                        return;
                    }
                }
                return;
            }
            AnimBase.this.f2632c.removeCallbacks(this);
            AnimBase.this.f2632c = null;
            if (AnimBase.this.f2635f) {
                AnimBase.this.mo10130c();
            } else {
                AnimBase.this.mo10128b();
            }
        }
    }

    /* renamed from: a */
    public abstract void mo10126a();

    /* renamed from: b */
    public abstract void mo10128b();

    /* renamed from: c */
    public abstract void mo10130c();

    public AnimBase(int i, int i2) {
        this.f2630a = i;
        this.f2631b = i2;
    }

    /* renamed from: d */
    public void mo10131d() {
        if (!mo10133f()) {
            this.f2632c = new Handler(Looper.getMainLooper());
            this.f2634e = true;
            this.f2635f = false;
            this.f2633d = 0;
        }
        m3796i();
    }

    /* renamed from: e */
    public void mo10132e() {
        CancelAnimObserver.m4331a().mo10306b();
        mo10135g();
        this.f2636g.run();
    }

    /* renamed from: g */
    private void mo10135g() {
        this.f2634e = false;
    }

    /* renamed from: h */
    private void m3795h() {
        this.f2633d += this.f2631b;
        if (this.f2630a != -1 && this.f2633d > this.f2630a) {
            mo10135g();
            mo10129b(true);
        }
    }

    /* renamed from: a */
    public void mo10127a(boolean z) {
        this.f2634e = z;
    }

    /* renamed from: f */
    public boolean mo10133f() {
        return this.f2634e;
    }

    /* renamed from: b */
    public void mo10129b(boolean z) {
        this.f2635f = z;
    }

    /* renamed from: i */
    private void m3796i() {
        if (this.f2632c != null) {
            this.f2632c.post(this.f2636g);
        }
    }
}
