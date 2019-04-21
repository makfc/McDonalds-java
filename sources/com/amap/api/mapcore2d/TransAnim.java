package com.amap.api.mapcore2d;

/* renamed from: com.amap.api.mapcore2d.bv */
class TransAnim extends AnimBase {
    /* renamed from: c */
    private GeoPoint f2638c;
    /* renamed from: d */
    private GeoPoint f2639d;
    /* renamed from: e */
    private int f2640e = ((int) this.f2638c.mo10338e());
    /* renamed from: f */
    private int f2641f = ((int) this.f2638c.mo10340f());
    /* renamed from: g */
    private int f2642g;
    /* renamed from: h */
    private int f2643h;
    /* renamed from: i */
    private int f2644i;
    /* renamed from: j */
    private int f2645j;
    /* renamed from: k */
    private int f2646k;
    /* renamed from: l */
    private TransAnimListener f2647l;

    public TransAnim(int i, int i2, GeoPoint geoPoint, GeoPoint geoPoint2, int i3, TransAnimListener transAnimListener) {
        super(i, i2);
        this.f2638c = geoPoint;
        this.f2639d = geoPoint2;
        this.f2647l = transAnimListener;
        this.f2644i = (int) Math.abs(geoPoint2.mo10338e() - this.f2638c.mo10338e());
        this.f2645j = (int) Math.abs(geoPoint2.mo10340f() - this.f2638c.mo10340f());
        m3806a(i3);
    }

    /* renamed from: a */
    private void m3806a(int i) {
        int i2 = 2;
        int i3 = (i / 10) / 10;
        if (i3 >= 2) {
            i2 = i3;
        }
        this.f2642g = this.f2644i / i2;
        this.f2643h = this.f2645j / i2;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: c */
    public void mo10130c() {
        this.f2647l.mo9769c();
        CameraChangeFinishObserver.m4312a().mo10303b();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public void mo10128b() {
        this.f2647l.mo9769c();
        CancelAnimObserver.m4331a().mo10306b();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo10126a() {
        int e = (int) this.f2639d.mo10338e();
        int f = (int) this.f2639d.mo10340f();
        if (mo10133f()) {
            this.f2646k++;
            this.f2640e = m3805a(this.f2640e, e, this.f2642g);
            this.f2641f = m3805a(this.f2641f, f, this.f2643h);
            this.f2647l.mo9768a(new GeoPoint((double) this.f2641f, (double) this.f2640e, false));
            if (this.f2640e == e && this.f2641f == f) {
                mo10127a(false);
                mo10129b(true);
                mo10135g();
                return;
            }
            return;
        }
        this.f2640e = e;
        this.f2641f = f;
        this.f2647l.mo9768a(new GeoPoint((double) this.f2641f, (double) this.f2640e, false));
    }

    /* renamed from: a */
    private int m3805a(int i, int i2, int i3) {
        int i4;
        if (i2 > i) {
            i4 = i + i3;
            if (i4 >= i2) {
                this.f2646k = 0;
                return i2;
            }
        }
        i4 = i - i3;
        if (i4 <= i2) {
            this.f2646k = 0;
            return i2;
        }
        return i4;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: g */
    public void mo10135g() {
        StopAnimObserver.m3684a().mo10085b();
    }
}
