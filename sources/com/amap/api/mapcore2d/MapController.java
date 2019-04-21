package com.amap.api.mapcore2d;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.autonavi.amap.mapcore.VTMCDataCache;
import java.util.LinkedList;

/* renamed from: com.amap.api.mapcore2d.ap */
final class MapController implements OnKeyListener {
    /* renamed from: a */
    private float f2270a = 0.0f;
    /* renamed from: b */
    private float f2271b = 0.0f;
    /* renamed from: c */
    private Mediator f2272c;
    /* renamed from: d */
    private boolean f2273d;
    /* renamed from: e */
    private C0892b f2274e;
    /* renamed from: f */
    private C0891a f2275f;

    /* compiled from: MapController */
    /* renamed from: com.amap.api.mapcore2d.ap$a */
    private class C0891a implements TransAnimListener {
        /* renamed from: b */
        private TransAnim f2264b;
        /* renamed from: c */
        private Message f2265c;
        /* renamed from: d */
        private Runnable f2266d;

        private C0891a() {
            this.f2264b = null;
            this.f2265c = null;
            this.f2266d = null;
        }

        /* renamed from: a */
        public boolean mo9771a() {
            if (this.f2264b != null) {
                return this.f2264b.mo10133f();
            }
            return false;
        }

        /* renamed from: a */
        public void mo9770a(GeoPoint geoPoint, Message message, Runnable runnable, int i) {
            MapController.this.f2272c.f2385d.f2370a = true;
            MapController.this.f2272c.f2390i.f2317m = geoPoint.mo10341g();
            this.f2264b = m3111a(geoPoint, i);
            this.f2265c = message;
            this.f2266d = runnable;
            this.f2264b.mo10131d();
        }

        /* renamed from: a */
        private TransAnim m3111a(GeoPoint geoPoint, int i) {
            int i2 = VTMCDataCache.MAXSIZE;
            if (i >= VTMCDataCache.MAXSIZE) {
                i2 = i;
            }
            return new TransAnim(i2, 10, MapController.this.f2272c.f2390i.f2316l, geoPoint, i, this);
        }

        /* renamed from: d */
        private void m3112d() {
            this.f2264b = null;
            this.f2265c = null;
            this.f2266d = null;
        }

        /* renamed from: b */
        public void mo9772b() {
            if (this.f2264b != null) {
                this.f2264b.mo10132e();
            }
        }

        /* renamed from: a */
        public void mo9768a(GeoPoint geoPoint) {
            if (geoPoint != null) {
                if (geoPoint.mo10337d() == Long.MIN_VALUE || geoPoint.mo10336c() == Long.MIN_VALUE) {
                    MapController.this.mo9783a(MapController.this.f2272c.f2390i.mo9854b(geoPoint));
                    return;
                }
                MapController.this.mo9783a(geoPoint);
            }
        }

        /* renamed from: c */
        public void mo9769c() {
            if (this.f2265c != null) {
                this.f2265c.getTarget().sendMessage(this.f2265c);
            }
            if (this.f2266d != null) {
                this.f2266d.run();
            }
            m3112d();
            if (MapController.this.f2272c.f2385d != null) {
                MapController.this.f2272c.f2385d.f2370a = false;
            }
        }
    }

    /* compiled from: MapController */
    /* renamed from: com.amap.api.mapcore2d.ap$b */
    private class C0892b implements AnimationListener {
        /* renamed from: b */
        private LinkedList<Animation> f2268b;
        /* renamed from: c */
        private ZoomCtlAnim f2269c;

        private C0892b() {
            this.f2268b = new LinkedList();
            this.f2269c = null;
        }

        /* renamed from: a */
        public void mo9773a() {
            this.f2268b.clear();
        }

        /* renamed from: a */
        public void mo9774a(int i, int i2, float f, boolean z, boolean z2) {
            if (z) {
                m3119b(f, i, i2, z2);
            } else {
                m3118a(f, i, i2, z2);
            }
        }

        /* renamed from: a */
        private void m3118a(float f, int i, int i2, boolean z) {
            if (this.f2269c == null) {
                this.f2269c = new ZoomCtlAnim(MapController.this.f2272c.f2384c.mo9907g(), this);
            }
            this.f2269c.f2685d = z;
            this.f2269c.f2684c = f;
            this.f2269c.mo10153a(f, false, (float) i, (float) i2);
        }

        /* renamed from: b */
        private void m3119b(float f, int i, int i2, boolean z) {
            if (this.f2269c == null) {
                this.f2269c = new ZoomCtlAnim(MapController.this.f2272c.f2384c.mo9907g(), this);
            }
            this.f2269c.f2684c = f;
            this.f2269c.f2685d = z;
            if (this.f2269c.f2685d) {
                Point point = new Point(i, i2);
                GeoPoint a = MapController.this.f2272c.f2384c.mo9907g().mo9999s().mo9909a(i, i2);
                MapController.this.f2272c.f2390i.f2316l = MapController.this.f2272c.f2390i.mo9846a(a);
                MapController.this.f2272c.f2390i.mo9849a(point);
            }
            this.f2269c.mo10153a(f, true, (float) i, (float) i2);
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            AMapDelegateImpGLSurfaceView g = MapController.this.f2272c.f2384c.mo9907g();
            if (this.f2268b.size() == 0) {
                MapController.this.f2272c.f2386e.mo9883b();
            } else {
                g.startAnimation((Animation) this.f2268b.remove());
            }
        }
    }

    MapController(Mediator mediator) {
        this.f2272c = mediator;
        this.f2273d = false;
        this.f2274e = new C0892b();
        this.f2275f = new C0891a();
    }

    /* renamed from: a */
    public float mo9778a() {
        return this.f2270a;
    }

    /* renamed from: b */
    public float mo9789b() {
        return this.f2271b;
    }

    /* renamed from: a */
    public void mo9779a(float f) {
        this.f2270a = f;
    }

    /* renamed from: b */
    public void mo9790b(float f) {
        this.f2271b = f;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) {
            return false;
        }
        boolean z = true;
        switch (i) {
            case 19:
                mo9791b(0, -10);
                break;
            case 20:
                mo9791b(0, 10);
                break;
            case 21:
                mo9791b(-10, 0);
                break;
            case 22:
                mo9791b(10, 0);
                break;
            default:
                z = false;
                break;
        }
        return z;
    }

    /* renamed from: a */
    public void mo9783a(GeoPoint geoPoint) {
        if (m3125b(geoPoint)) {
            m3126c(geoPoint);
        }
    }

    /* renamed from: b */
    private boolean m3125b(GeoPoint geoPoint) {
        if (geoPoint == null || this.f2272c == null || this.f2272c.f2384c == null) {
            return false;
        }
        GeoPoint f = this.f2272c.f2384c.mo9906f();
        if (f == null) {
            return false;
        }
        if (geoPoint.mo10334b() == f.mo10334b() && geoPoint.mo10332a() == f.mo10332a()) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public void mo9784a(GeoPoint geoPoint, float f) {
        if (m3125b(geoPoint) || m3128f(f)) {
            m3126c(geoPoint);
            m3127e(f);
        }
    }

    /* renamed from: c */
    private void m3126c(GeoPoint geoPoint) {
        this.f2272c.f2389h.mo10014M();
        this.f2272c.f2384c.mo9897a(geoPoint);
    }

    /* renamed from: c */
    public float mo9793c(float f) {
        if (m3128f(f)) {
            m3127e(f);
        }
        return f;
    }

    /* renamed from: e */
    private float m3127e(float f) {
        String str = "setZoom";
        AMapDelegateImpGLSurfaceView g = this.f2272c.f2384c.mo9907g();
        g.mo10014M();
        float a = g.mo10016a(f);
        this.f2272c.f2384c.mo9893a(a);
        try {
            if (this.f2272c.f2389h.mo9997q().mo9716a()) {
                this.f2272c.f2389h.mo10015N();
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "MapController", str);
        }
        return a;
    }

    /* renamed from: f */
    private boolean m3128f(float f) {
        if (this.f2272c == null || this.f2272c.f2384c == null || f == this.f2272c.f2384c.mo9905e()) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public void mo9780a(float f, float f2) {
        mo9781a(f, f2, 0, 0, 0);
    }

    /* renamed from: a */
    public void mo9781a(float f, float f2, int i, int i2, int i3) {
        Throwable e;
        String str = "zoomToSpan";
        if (f > 0.0f && f2 > 0.0f && this.f2272c != null && this.f2272c.f2384c != null && this.f2272c.f2383b != null) {
            float f3 = 0.0f;
            float d;
            try {
                float e2 = this.f2272c.f2384c.mo9905e();
                int b = this.f2272c.f2383b.mo9912b(i, i2, i3);
                int a = this.f2272c.f2383b.mo9911a(i, i2, i3);
                if (b == 0 && a == 0) {
                    this.f2270a = f;
                    this.f2271b = f2;
                    return;
                }
                int i4;
                try {
                    double min = this.f2272c.f2390i.f2315k / Math.min((double) (((float) a) / f), (double) (((float) b) / f2));
                    a = 0;
                    double d2 = this.f2272c.f2390i.f2308d;
                    while (true) {
                        d2 /= 2.0d;
                        if (d2 <= min) {
                            break;
                        }
                        a++;
                    }
                    f3 = (float) ((Math.log((this.f2272c.f2390i.f2308d / ((double) (1 << a))) / min) / Math.log(2.0d)) + ((double) a));
                    d = mo9795d(f3);
                    i4 = (int) d;
                    e2 = d - ((float) i4);
                } catch (Exception e3) {
                    e = e3;
                    d = e2;
                    C0955ck.m3888a(e, "MapController", str);
                    Log.e("MapController", "zoom:" + d);
                    mo9793c(d);
                }
                try {
                    if (((double) e2) > 1.0d - ((1.0d - Mediator.f2382a) * 0.4d)) {
                        d = ((float) Mediator.f2382a) + ((float) i4);
                    } else if (((double) e2) > Mediator.f2382a) {
                        d = ((float) (Mediator.f2382a - 9.999999747378752E-5d)) + ((float) i4);
                    } else if (e2 == ((float) (Mediator.f2382a - 9.999999747378752E-5d))) {
                        d = ((float) (Mediator.f2382a - 9.999999747378752E-5d)) + ((float) i4);
                    }
                } catch (Exception e4) {
                    e = e4;
                    C0955ck.m3888a(e, "MapController", str);
                    Log.e("MapController", "zoom:" + d);
                    mo9793c(d);
                }
                Log.e("MapController", "zoom:" + d);
                mo9793c(d);
            } catch (Exception e32) {
                Throwable th = e32;
                d = f3;
                e = th;
                C0955ck.m3888a(e, "MapController", str);
                Log.e("MapController", "zoom:" + d);
                mo9793c(d);
            }
        }
    }

    /* renamed from: d */
    public float mo9795d(float f) {
        if (f < ((float) this.f2272c.f2384c.mo9899b())) {
            f = (float) this.f2272c.f2384c.mo9899b();
        }
        if (f > ((float) this.f2272c.f2384c.mo9892a())) {
            return (float) this.f2272c.f2384c.mo9892a();
        }
        return f;
    }

    /* renamed from: c */
    public boolean mo9794c() {
        return mo9787a(1);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public boolean mo9787a(int i) {
        return m3124a(this.f2272c.f2384c.mo9903c() / 2, this.f2272c.f2384c.mo9904d() / 2, true, false, i);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public boolean mo9792b(int i) {
        return m3124a(this.f2272c.f2384c.mo9903c() / 2, this.f2272c.f2384c.mo9904d() / 2, false, false, i);
    }

    /* renamed from: d */
    public boolean mo9796d() {
        return mo9792b(1);
    }

    /* renamed from: a */
    public boolean mo9788a(int i, int i2) {
        return m3123a(i, i2, true, true);
    }

    /* renamed from: a */
    public void mo9785a(GeoPoint geoPoint, int i) {
        this.f2275f.mo9770a(geoPoint, null, null, i);
    }

    /* renamed from: a */
    public void mo9786a(boolean z) {
        this.f2274e.mo9773a();
        this.f2275f.mo9772b();
    }

    /* renamed from: b */
    public void mo9791b(int i, int i2) {
        if (this.f2273d) {
            this.f2273d = false;
        } else if (i != 0 || i2 != 0) {
            if (C1042p.f3047q) {
                this.f2272c.f2390i.mo9850a(new PointF(0.0f, 0.0f), new PointF((float) i, (float) i2), this.f2272c.f2384c.mo9905e());
            }
            this.f2272c.f2384c.mo9898a(false, false);
        }
    }

    /* renamed from: e */
    public void mo9797e() {
        this.f2273d = true;
    }

    /* renamed from: a */
    public void mo9782a(int i, int i2, float f, boolean z, boolean z2) {
        this.f2274e.mo9774a(i, i2, f, z, z2);
    }

    /* renamed from: a */
    private boolean m3124a(int i, int i2, boolean z, boolean z2, int i3) {
        float e;
        String str = "zoomWithAnimation";
        this.f2272c.f2384c.mo9907g().mo10014M();
        boolean z3 = false;
        if (z) {
            e = this.f2272c.f2384c.mo9905e() + ((float) i3);
        } else {
            e = this.f2272c.f2384c.mo9905e() - ((float) i3);
        }
        float a = this.f2272c.f2384c.mo9907g().mo10016a(e);
        if (a != this.f2272c.f2384c.mo9905e()) {
            mo9782a(i, i2, a, z, z2);
            z3 = true;
        }
        try {
            if (this.f2272c.f2389h.mo9997q().mo9716a()) {
                this.f2272c.f2389h.mo10015N();
            }
        } catch (RemoteException e2) {
            C0955ck.m3888a(e2, "MapController", str);
        }
        return z3;
    }

    /* renamed from: a */
    private boolean m3123a(int i, int i2, boolean z, boolean z2) {
        return m3124a(i, i2, z, z2, 1);
    }

    /* renamed from: f */
    public boolean mo9798f() {
        return this.f2275f.mo9771a();
    }

    /* renamed from: g */
    public void mo9799g() {
        this.f2275f.mo9772b();
    }
}
