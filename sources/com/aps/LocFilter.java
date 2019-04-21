package com.aps;

/* renamed from: com.aps.m */
public class LocFilter {
    /* renamed from: a */
    private static LocFilter f4487a = null;
    /* renamed from: b */
    private AmapLoc f4488b = null;
    /* renamed from: c */
    private long f4489c = 0;
    /* renamed from: d */
    private long f4490d = 0;

    private LocFilter() {
    }

    /* renamed from: a */
    public static synchronized LocFilter m5680a() {
        LocFilter locFilter;
        synchronized (LocFilter.class) {
            if (f4487a == null) {
                f4487a = new LocFilter();
            }
            locFilter = f4487a;
        }
        return locFilter;
    }

    /* renamed from: b */
    public synchronized void mo13278b() {
        this.f4488b = null;
        this.f4489c = 0;
        this.f4490d = 0;
    }

    /* renamed from: a */
    public synchronized AmapLoc mo13277a(AmapLoc amapLoc) {
        if (!C1269v.m5732a(this.f4488b) || !C1269v.m5732a(amapLoc)) {
            this.f4489c = C1269v.m5737b();
            this.f4488b = amapLoc;
            amapLoc = this.f4488b;
        } else if (amapLoc.mo13216j() != this.f4488b.mo13216j() || amapLoc.mo13214i() >= 300.0f) {
            if (amapLoc.mo13208f().equals("gps")) {
                this.f4489c = C1269v.m5737b();
                this.f4488b = amapLoc;
                amapLoc = this.f4488b;
            } else if (amapLoc.mo13206e() != this.f4488b.mo13206e()) {
                this.f4489c = C1269v.m5737b();
                this.f4488b = amapLoc;
                amapLoc = this.f4488b;
            } else if (!Const.f4445l) {
                this.f4489c = C1269v.m5737b();
                this.f4488b = amapLoc;
                amapLoc = this.f4488b;
            } else if (amapLoc.mo13198b().equals(this.f4488b.mo13198b())) {
                float a = C1269v.m5722a(amapLoc, this.f4488b);
                float i = this.f4488b.mo13214i();
                float i2 = amapLoc.mo13214i();
                float f = i2 - i;
                long b = C1269v.m5737b();
                long j = b - this.f4489c;
                if ((i < 101.0f && i2 > 299.0f) || (i > 299.0f && i2 > 299.0f)) {
                    if (this.f4490d == 0) {
                        this.f4490d = b;
                    } else if (b - this.f4490d > 30000) {
                        this.f4489c = b;
                        this.f4488b = amapLoc;
                        this.f4490d = 0;
                        amapLoc = this.f4488b;
                    }
                    amapLoc = this.f4488b;
                } else if (i2 >= 100.0f || i <= 299.0f) {
                    if (i2 <= 299.0f) {
                        this.f4490d = 0;
                    }
                    if (a >= 20.0f || ((double) a) <= 0.1d) {
                        if (f < 300.0f) {
                            this.f4489c = C1269v.m5737b();
                            this.f4488b = amapLoc;
                            amapLoc = this.f4488b;
                        } else if (j >= 30000) {
                            this.f4489c = C1269v.m5737b();
                            this.f4488b = amapLoc;
                            amapLoc = this.f4488b;
                        } else {
                            amapLoc = this.f4488b;
                        }
                    } else if (f >= -300.0f) {
                        amapLoc = this.f4488b;
                    } else if (i / i2 >= 2.0f) {
                        this.f4489c = b;
                        this.f4488b = amapLoc;
                        amapLoc = this.f4488b;
                    } else {
                        amapLoc = this.f4488b;
                    }
                } else {
                    this.f4489c = b;
                    this.f4488b = amapLoc;
                    this.f4490d = 0;
                    amapLoc = this.f4488b;
                }
            } else {
                this.f4489c = C1269v.m5737b();
                this.f4488b = amapLoc;
                amapLoc = this.f4488b;
            }
        }
        return amapLoc;
    }
}
