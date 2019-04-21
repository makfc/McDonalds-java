package com.amap.api.mapcore2d;

import android.graphics.Matrix;
import android.graphics.Point;
import android.view.animation.Animation.AnimationListener;

/* renamed from: com.amap.api.mapcore2d.cc */
class ZoomCtlAnim extends AnimBase {
    /* renamed from: c */
    public float f2684c = -1.0f;
    /* renamed from: d */
    public boolean f2685d = false;
    /* renamed from: e */
    private AnimationListener f2686e;
    /* renamed from: f */
    private AMapDelegateImpGLSurfaceView f2687f;
    /* renamed from: g */
    private float f2688g;
    /* renamed from: h */
    private float f2689h;
    /* renamed from: i */
    private float f2690i;
    /* renamed from: j */
    private float f2691j;
    /* renamed from: k */
    private float f2692k;
    /* renamed from: l */
    private boolean f2693l;
    /* renamed from: m */
    private boolean f2694m = false;

    public ZoomCtlAnim(AMapDelegateImpGLSurfaceView aMapDelegateImpGLSurfaceView, AnimationListener animationListener) {
        super(160, 40);
        this.f2687f = aMapDelegateImpGLSurfaceView;
        this.f2686e = animationListener;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo10126a() {
        MapProjection mapProjection;
        if (this.f2693l) {
            mapProjection = this.f2687f.f2448a.f2390i;
            mapProjection.f2307c += this.f2692k;
        } else {
            mapProjection = this.f2687f.f2448a.f2390i;
            mapProjection.f2307c -= this.f2692k;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(this.f2687f.f2448a.f2390i.f2307c, this.f2687f.f2448a.f2390i.f2307c, this.f2688g, this.f2689h);
        this.f2687f.mo10029d(this.f2687f.f2448a.f2390i.f2307c);
        this.f2687f.mo10024b(matrix);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: c */
    public void mo10130c() {
        mo10128b();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public void mo10128b() {
        if (!this.f2694m) {
            try {
                if (this.f2687f != null && this.f2687f.mo10022b() != null) {
                    Point point;
                    GeoPoint a;
                    this.f2687f.mo10022b().f2386e.f2356b = false;
                    if (this.f2685d) {
                        point = new Point((int) this.f2688g, (int) this.f2689h);
                        a = this.f2687f.mo9999s().mo9909a((int) this.f2688g, (int) this.f2689h);
                        this.f2687f.mo10022b().f2390i.f2316l = this.f2687f.mo10022b().f2390i.mo9846a(a);
                        this.f2687f.mo10022b().f2390i.mo9849a(point);
                        this.f2687f.mo10022b().f2384c.mo9898a(false, false);
                    }
                    this.f2687f.mo10007D().mo9793c(this.f2684c);
                    this.f2686e.onAnimationEnd(null);
                    if (this.f2685d) {
                        point = new Point(this.f2687f.mo10022b().f2384c.mo9903c() / 2, this.f2687f.mo10022b().f2384c.mo9904d() / 2);
                        a = this.f2687f.mo9999s().mo9909a(this.f2687f.mo10022b().f2384c.mo9903c() / 2, this.f2687f.mo10022b().f2384c.mo9904d() / 2);
                        this.f2687f.mo10022b().f2390i.f2316l = this.f2687f.mo10022b().f2390i.mo9846a(a);
                        this.f2687f.mo10022b().f2390i.mo9849a(point);
                        this.f2687f.mo10022b().f2384c.mo9898a(false, false);
                    }
                    this.f2687f.f2448a.f2390i.f2307c = 1.0f;
                    MultiTouchGestureDetector.f2506j = 1.0f;
                    this.f2687f.mo10022b().mo9915a(true);
                    CameraChangeFinishObserver.m4312a().mo10303b();
                }
            } catch (Exception e) {
                C0955ck.m3888a(e, "ZoomCtlAnim", "onStop");
            }
        }
    }

    /* renamed from: a */
    public void mo10152a(float f, float f2, boolean z, float f3, float f4) {
        this.f2693l = z;
        this.f2688g = f3;
        this.f2689h = f4;
        this.f2690i = f;
        this.f2687f.f2448a.f2390i.f2307c = this.f2690i;
        if (this.f2693l) {
            this.f2692k = (this.f2690i * ((float) this.f2631b)) / ((float) this.f2630a);
            this.f2691j = this.f2690i * 2.0f;
            return;
        }
        this.f2692k = ((this.f2690i * 0.5f) * ((float) this.f2631b)) / ((float) this.f2630a);
        this.f2691j = this.f2690i * 0.5f;
    }

    /* renamed from: a */
    public void mo10153a(float f, boolean z, float f2, float f3) {
        this.f2687f.f2482c[0] = this.f2687f.f2482c[1];
        this.f2687f.f2482c[1] = f;
        if (this.f2687f.f2482c[0] != this.f2687f.f2482c[1]) {
            this.f2687f.mo10022b().mo9915a(this.f2687f.mo10005B());
            if (mo10133f()) {
                this.f2694m = true;
                mo10132e();
                mo10152a(this.f2691j, f, z, f2, f3);
                this.f2687f.mo10022b().f2386e.mo9877a(true);
                this.f2687f.mo10022b().f2386e.f2356b = true;
                this.f2686e.onAnimationStart(null);
                super.mo10131d();
                this.f2694m = false;
                return;
            }
            this.f2630a = 160;
            mo10152a(this.f2687f.mo10011J(), f, z, f2, f3);
            this.f2687f.mo10022b().f2386e.mo9877a(true);
            this.f2687f.mo10022b().f2386e.f2356b = true;
            this.f2686e.onAnimationStart(null);
            super.mo10131d();
        }
    }
}
