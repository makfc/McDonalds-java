package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;
import com.amap.api.maps2d.AMap;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

/* renamed from: com.amap.api.mapcore2d.ay */
class Mediator {
    /* renamed from: a */
    static double f2382a = 0.6499999761581421d;
    /* renamed from: b */
    public C0911e f2383b;
    /* renamed from: c */
    public C0910d f2384c;
    /* renamed from: d */
    public C0908b f2385d;
    /* renamed from: e */
    public C0907a f2386e;
    /* renamed from: f */
    public C0909c f2387f;
    /* renamed from: g */
    public C1047t f2388g;
    /* renamed from: h */
    public AMapDelegateImpGLSurfaceView f2389h;
    /* renamed from: i */
    public MapProjection f2390i = null;
    /* renamed from: j */
    private C0886am f2391j;

    /* compiled from: Mediator */
    /* renamed from: com.amap.api.mapcore2d.ay$a */
    public class C0907a {
        /* renamed from: a */
        public SyncList<C0886am> f2355a;
        /* renamed from: b */
        public boolean f2356b;
        /* renamed from: c */
        public boolean f2357c;
        /* renamed from: d */
        String f2358d;
        /* renamed from: e */
        int f2359e;
        /* renamed from: f */
        int f2360f;
        /* renamed from: g */
        String f2361g;
        /* renamed from: h */
        String f2362h;
        /* renamed from: i */
        String f2363i;
        /* renamed from: j */
        String f2364j;
        /* renamed from: l */
        private boolean f2366l;
        /* renamed from: m */
        private boolean f2367m;
        /* renamed from: n */
        private Context f2368n;
        /* renamed from: o */
        private boolean f2369o;

        /* compiled from: Mediator */
        /* renamed from: com.amap.api.mapcore2d.ay$a$1 */
        class C09051 extends UrlFormater {
            C09051() {
            }

            /* renamed from: a */
            public String mo9873a(int i, int i2, int i3) {
                if (C1042p.f3038h == null || C1042p.f3038h.equals("")) {
                    Mediator.this.f2391j.f2244h = true;
                    String b = MapServerUrl.m3230a().mo9860b();
                    Object[] objArr = new Object[5];
                    objArr[0] = Integer.valueOf(i3);
                    objArr[1] = Integer.valueOf(i);
                    objArr[2] = Integer.valueOf(i2);
                    objArr[3] = C1042p.f3042l == 2 ? "wprd" : "webrd";
                    objArr[4] = C0907a.this.f2358d;
                    return String.format(b, objArr);
                }
                Mediator.this.f2391j.f2244h = false;
                return String.format(Locale.US, C1042p.f3038h, new Object[]{Integer.valueOf(i3), Integer.valueOf(i), Integer.valueOf(i2)});
            }
        }

        private C0907a(Context context) {
            this.f2366l = false;
            this.f2367m = true;
            this.f2355a = null;
            this.f2356b = false;
            this.f2357c = false;
            this.f2358d = AMap.CHINESE;
            this.f2359e = 0;
            this.f2360f = 0;
            this.f2362h = "SatelliteMap3";
            this.f2363i = "GridTmc3";
            this.f2364j = "SateliteTmc3";
            this.f2369o = false;
            if (context != null) {
                this.f2368n = context;
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
                int c = (displayMetrics.widthPixels / Mediator.this.f2390i.f2305a) + m3298c();
                int c2 = (displayMetrics.heightPixels / Mediator.this.f2390i.f2305a) + m3298c();
                this.f2359e = (c + (c * c2)) + c2;
                this.f2360f = (this.f2359e / 8) + 1;
                if (this.f2360f == 0) {
                    this.f2360f = 1;
                } else if (this.f2360f > 5) {
                    this.f2360f = 5;
                }
                m3294a(context, AMap.CHINESE);
            }
        }

        /* renamed from: c */
        private int m3298c() {
            return 3;
        }

        /* renamed from: a */
        public void mo9876a(String str) {
            if (str != null && !str.equals("") && !this.f2358d.equals(str)) {
                if (str.equals(AMap.CHINESE) || str.equals("en")) {
                    if (C1042p.f3037g != null && !C1042p.f3037g.equals("")) {
                        this.f2361g = C1042p.f3037g;
                    } else if (str.equals(AMap.CHINESE)) {
                        this.f2361g = "GridMapV3";
                    } else if (str.equals("en")) {
                        this.f2361g = "GridMapEnV3";
                    }
                    Mediator.this.f2391j = mo9882b(this.f2361g);
                    if (Mediator.this.f2391j == null) {
                        Mediator.this.f2391j = new C0886am(Mediator.this.f2390i);
                        Mediator.this.f2391j.f2236q = new TileServer(Mediator.this, this.f2368n, Mediator.this.f2391j);
                        Mediator.this.f2391j.f2246j = new C09051();
                        Mediator.this.f2391j.f2238b = this.f2361g;
                        Mediator.this.f2391j.f2241e = true;
                        Mediator.this.f2391j.mo9758a(true);
                        Mediator.this.f2391j.f2242f = true;
                        Mediator.this.f2391j.f2239c = C1042p.f3033c;
                        Mediator.this.f2391j.f2240d = C1042p.f3034d;
                        mo9880a(Mediator.this.f2391j, this.f2368n);
                    }
                    mo9881a(this.f2361g, true);
                    this.f2358d = str;
                }
            }
        }

        /* renamed from: a */
        private void m3294a(Context context, String str) {
            if (this.f2355a == null) {
                this.f2355a = new SyncList();
            }
            if (C1042p.f3037g != null && !C1042p.f3037g.equals("")) {
                this.f2361g = C1042p.f3037g;
            } else if (str.equals(AMap.CHINESE)) {
                this.f2361g = "GridMapV3";
            } else if (str.equals("en")) {
                this.f2361g = "GridMapEnV3";
            }
            final C0886am c0886am = new C0886am(Mediator.this.f2390i);
            c0886am.f2246j = new UrlFormater() {
                /* renamed from: a */
                public String mo9873a(int i, int i2, int i3) {
                    if (C1042p.f3038h == null || C1042p.f3038h.equals("")) {
                        c0886am.f2244h = true;
                        String b = MapServerUrl.m3230a().mo9860b();
                        Object[] objArr = new Object[5];
                        objArr[0] = Integer.valueOf(i3);
                        objArr[1] = Integer.valueOf(i);
                        objArr[2] = Integer.valueOf(i2);
                        objArr[3] = C1042p.f3042l == 2 ? "wprd" : "webrd";
                        objArr[4] = C0907a.this.f2358d;
                        return String.format(b, objArr);
                    }
                    c0886am.f2244h = false;
                    return String.format(Locale.US, C1042p.f3038h, new Object[]{Integer.valueOf(i3), Integer.valueOf(i), Integer.valueOf(i2)});
                }
            };
            c0886am.f2238b = this.f2361g;
            c0886am.f2241e = true;
            c0886am.f2242f = true;
            c0886am.f2239c = C1042p.f3033c;
            c0886am.f2240d = C1042p.f3034d;
            c0886am.f2236q = new TileServer(Mediator.this, this.f2368n, c0886am);
            c0886am.mo9758a(true);
            mo9880a(c0886am, context);
        }

        /* Access modifiers changed, original: 0000 */
        /* renamed from: a */
        public boolean mo9881a(String str, boolean z) {
            if (str.equals("")) {
                return false;
            }
            int size = this.f2355a.size();
            for (int i = 0; i < size; i++) {
                C0886am c0886am = (C0886am) this.f2355a.get(i);
                if (c0886am != null && c0886am.f2238b.equals(str)) {
                    c0886am.mo9758a(z);
                    if (!c0886am.f2241e) {
                        return true;
                    }
                    if (z) {
                        if (c0886am.f2239c > c0886am.f2240d) {
                            Mediator.this.f2384c.mo9894a(c0886am.f2239c);
                            Mediator.this.f2384c.mo9900b(c0886am.f2240d);
                        }
                        m3300c(str);
                        Mediator.this.f2384c.mo9898a(false, false);
                        return true;
                    }
                }
            }
            return false;
        }

        /* renamed from: c */
        private void m3300c(String str) {
            if (!str.equals("")) {
                int size = this.f2355a.size();
                for (int i = 0; i < size; i++) {
                    C0886am c0886am = (C0886am) this.f2355a.get(i);
                    if (c0886am != null && !c0886am.f2238b.equals(str) && c0886am.f2241e && c0886am.mo9759a()) {
                        c0886am.mo9758a(false);
                    }
                }
            }
        }

        /* renamed from: d */
        private boolean m3302d(String str) {
            if (this.f2355a == null) {
                return false;
            }
            int size = this.f2355a.size();
            for (int i = 0; i < size; i++) {
                C0886am c0886am = (C0886am) this.f2355a.get(i);
                if (c0886am != null && c0886am.f2238b.equals(str)) {
                    return true;
                }
            }
            return false;
        }

        /* Access modifiers changed, original: 0000 */
        /* renamed from: a */
        public boolean mo9880a(C0886am c0886am, Context context) {
            if (c0886am == null || c0886am.f2238b.equals("") || m3302d(c0886am.f2238b)) {
                return false;
            }
            boolean add;
            c0886am.f2252p = new SyncList();
            c0886am.f2250n = new MemoryTileManager(this.f2359e, this.f2360f, c0886am.f2243g, c0886am.f2245i, c0886am);
            c0886am.f2251o = new DiskCachManager(context, Mediator.this.f2384c.f2377c.f2483d, c0886am);
            c0886am.f2251o.mo10323a(c0886am.f2250n);
            int size = this.f2355a.size();
            if (!c0886am.f2241e || size == 0) {
                add = this.f2355a.add(c0886am);
            } else {
                for (int i = size - 1; i >= 0; i--) {
                    C0886am c0886am2 = (C0886am) this.f2355a.get(i);
                    if (c0886am2 != null && c0886am2.f2241e) {
                        this.f2355a.add(i, c0886am);
                        add = false;
                        break;
                    }
                }
                add = false;
            }
            m3301d();
            if (c0886am.mo9759a()) {
                mo9881a(c0886am.f2238b, true);
            }
            return add;
        }

        /* renamed from: d */
        private void m3301d() {
            int size = this.f2355a.size();
            for (int i = 0; i < size; i++) {
                C0886am c0886am = (C0886am) this.f2355a.get(i);
                if (c0886am != null) {
                    c0886am.f2248l = i;
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        /* renamed from: b */
        public C0886am mo9882b(String str) {
            if (str.equals("") || this.f2355a == null || this.f2355a.size() == 0) {
                return null;
            }
            int size = this.f2355a.size();
            for (int i = 0; i < size; i++) {
                C0886am c0886am = (C0886am) this.f2355a.get(i);
                if (c0886am != null && c0886am.f2238b.equals(str)) {
                    return c0886am;
                }
            }
            return null;
        }

        /* renamed from: a */
        public void mo9874a() {
            if (Mediator.this.f2386e.f2355a != null) {
                Iterator it = Mediator.this.f2386e.f2355a.iterator();
                while (it.hasNext()) {
                    C0886am c0886am = (C0886am) it.next();
                    if (c0886am != null) {
                        c0886am.mo9760b();
                    }
                }
                Mediator.this.f2386e.f2355a.clear();
                Mediator.this.f2386e.f2355a = null;
            }
        }

        /* renamed from: b */
        public void mo9883b() {
            if (Mediator.this.f2384c != null && Mediator.this.f2384c.f2377c != null) {
                Mediator.this.f2384c.f2377c.postInvalidate();
            }
        }

        /* renamed from: a */
        public void mo9877a(boolean z) {
            this.f2366l = z;
        }

        /* renamed from: a */
        public void mo9875a(Canvas canvas, Matrix matrix, float f, float f2) {
            if (this.f2366l) {
                canvas.save();
                canvas.translate(f, f2);
                canvas.concat(matrix);
                m3295a(canvas);
                if (Mediator.this.f2389h.f2488i.mo10103a()) {
                    m3297b(canvas);
                }
                Mediator.this.f2389h.f2488i.mo10100a(canvas);
                canvas.restore();
                if (!Mediator.this.f2389h.f2488i.mo10103a()) {
                    m3297b(canvas);
                }
                if (!(this.f2356b || this.f2357c)) {
                    mo9877a(false);
                    Mediator.this.f2384c.f2377c.mo10024b(new Matrix());
                    Mediator.this.f2384c.f2377c.mo10029d(1.0f);
                    Mediator.this.f2384c.f2377c.mo10012K();
                }
            } else {
                m3295a(canvas);
                Mediator.this.f2389h.f2488i.mo10100a(canvas);
                m3297b(canvas);
            }
            m3299c(canvas);
        }

        /* renamed from: b */
        public void mo9884b(boolean z) {
            this.f2367m = z;
        }

        /* renamed from: a */
        private void m3295a(Canvas canvas) {
            int size = this.f2355a.size();
            for (int i = 0; i < size; i++) {
                C0886am c0886am = (C0886am) this.f2355a.get(i);
                if (c0886am != null && c0886am.mo9759a()) {
                    c0886am.mo9757a(canvas);
                }
            }
        }

        /* renamed from: b */
        private void m3297b(Canvas canvas) {
            if (this.f2367m) {
                Mediator.this.f2388g.mo10328a(canvas);
            }
        }

        /* renamed from: c */
        private void m3299c(Canvas canvas) {
            Mediator.this.f2389h.f2489j.mo9822a(canvas);
        }

        /* renamed from: a */
        public boolean mo9878a(int i, KeyEvent keyEvent) {
            return false;
        }

        /* renamed from: b */
        public boolean mo9885b(int i, KeyEvent keyEvent) {
            return false;
        }

        /* renamed from: a */
        public boolean mo9879a(MotionEvent motionEvent) {
            return false;
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: b */
        public boolean mo9886b(MotionEvent motionEvent) {
            return false;
        }
    }

    /* compiled from: Mediator */
    /* renamed from: com.amap.api.mapcore2d.ay$b */
    public class C0908b {
        /* renamed from: a */
        public boolean f2370a = false;
        /* renamed from: b */
        int f2371b = 0;

        public C0908b() {
            mo9891e();
        }

        /* renamed from: a */
        public void mo9887a() {
            if (Mediator.this.f2386e.f2369o) {
                Mediator.this.f2386e.mo9883b();
            }
            this.f2371b++;
            if (this.f2371b >= 20 && this.f2371b % 20 == 0 && Mediator.this.f2386e.f2355a != null && Mediator.this.f2386e.f2355a.size() != 0) {
                int size = Mediator.this.f2386e.f2355a.size();
                for (int i = 0; i < size; i++) {
                    ((C0886am) Mediator.this.f2386e.f2355a.get(i)).f2236q.mo9859i();
                }
            }
        }

        /* renamed from: b */
        public void mo9888b() {
            Mediator.this.f2384c.f2375a = false;
            if (Mediator.this.f2386e.f2355a != null && Mediator.this.f2386e.f2355a.size() != 0) {
                int size = Mediator.this.f2386e.f2355a.size();
                for (int i = 0; i < size; i++) {
                    ((C0886am) Mediator.this.f2386e.f2355a.get(i)).f2236q.mo9855b();
                }
            }
        }

        /* renamed from: c */
        public void mo9889c() {
            if (Mediator.this.f2386e.f2355a != null && Mediator.this.f2386e.f2355a.size() != 0) {
                int size = Mediator.this.f2386e.f2355a.size();
                for (int i = 0; i < size; i++) {
                    ((C0886am) Mediator.this.f2386e.f2355a.get(i)).f2236q.mo9857d();
                }
            }
        }

        /* renamed from: d */
        public void mo9890d() {
            if (Mediator.this.f2386e.f2355a != null && Mediator.this.f2386e.f2355a.size() != 0) {
                int size = Mediator.this.f2386e.f2355a.size();
                for (int i = 0; i < size; i++) {
                    ((C0886am) Mediator.this.f2386e.f2355a.get(i)).f2236q.mo9856c();
                }
            }
        }

        /* renamed from: e */
        public void mo9891e() {
            if (Mediator.this.f2386e.f2355a != null && Mediator.this.f2386e.f2355a.size() != 0) {
                int size = Mediator.this.f2386e.f2355a.size();
                for (int i = 0; i < size; i++) {
                    ((C0886am) Mediator.this.f2386e.f2355a.get(i)).f2236q.mo9858h();
                }
            }
        }
    }

    /* compiled from: Mediator */
    /* renamed from: com.amap.api.mapcore2d.ay$c */
    public class C0909c {
        /* renamed from: b */
        private final Context f2374b;

        private C0909c(Mediator mediator, Context context) {
            this.f2374b = context;
        }
    }

    /* compiled from: Mediator */
    /* renamed from: com.amap.api.mapcore2d.ay$d */
    public class C0910d {
        /* renamed from: a */
        public boolean f2375a;
        /* renamed from: c */
        private AMapDelegateImpGLSurfaceView f2377c;
        /* renamed from: d */
        private ArrayList<ViewProportyChangedListenner> f2378d;

        private C0910d(AMapDelegateImpGLSurfaceView aMapDelegateImpGLSurfaceView) {
            this.f2375a = true;
            this.f2377c = aMapDelegateImpGLSurfaceView;
            this.f2378d = new ArrayList();
        }

        /* renamed from: a */
        public void mo9893a(float f) {
            if (f != Mediator.this.f2390i.f2314j) {
                double d;
                Mediator.this.f2390i.f2314j = f;
                int i = (int) f;
                double d2 = Mediator.this.f2390i.f2308d / ((double) (1 << i));
                if (((double) (f - ((float) i))) < Mediator.f2382a) {
                    Mediator.this.f2390i.f2305a = (int) (((double) Mediator.this.f2390i.f2306b) * (1.0d + (((double) (f - ((float) i))) * 0.4d)));
                    d = d2 / (((double) Mediator.this.f2390i.f2305a) / ((double) Mediator.this.f2390i.f2306b));
                } else {
                    float f2 = 2.0f - ((1.0f - (f - ((float) i))) * 0.4f);
                    Mediator.this.f2390i.f2305a = (int) (((float) Mediator.this.f2390i.f2306b) / (2.0f / f2));
                    d = (d2 / 2.0d) / (((double) Mediator.this.f2390i.f2305a) / ((double) Mediator.this.f2390i.f2306b));
                }
                Mediator.this.f2390i.f2315k = d;
                Mediator.this.f2389h.f2482c[1] = f;
                Mediator.this.f2389h.f2485f.mo10149a(f);
            }
            mo9898a(false, false);
        }

        /* renamed from: a */
        public void mo9895a(int i, int i2) {
            if (i != C1042p.f3043m || i2 != C1042p.f3044n) {
                C1042p.f3043m = i;
                C1042p.f3044n = i2;
                mo9898a(true, false);
            }
        }

        /* renamed from: a */
        public void mo9897a(GeoPoint geoPoint) {
            if (geoPoint != null) {
                if (C1042p.f3047q) {
                    Mediator.this.f2390i.f2316l = Mediator.this.f2390i.mo9846a(geoPoint);
                }
                mo9898a(false, false);
            }
        }

        /* renamed from: b */
        public void mo9902b(GeoPoint geoPoint) {
            GeoPoint f = Mediator.this.f2384c.mo9906f();
            if (geoPoint != null && !geoPoint.equals(f)) {
                if (C1042p.f3047q) {
                    Mediator.this.f2390i.f2316l = Mediator.this.f2390i.mo9846a(geoPoint);
                }
                mo9898a(false, true);
            }
        }

        /* renamed from: a */
        public int mo9892a() {
            String str = "getMaxZoomLevel";
            try {
                return Mediator.this.f2390i.f2313i;
            } catch (Throwable th) {
                C0955ck.m3888a(th, "Mediator", str);
                return 0;
            }
        }

        /* renamed from: a */
        public void mo9894a(int i) {
            String str = "setMaxZoomLevel";
            if (i > 0) {
                try {
                    MapProjection mapProjection = Mediator.this.f2390i;
                    C1042p.f3033c = i;
                    mapProjection.f2313i = i;
                } catch (Throwable th) {
                    C0955ck.m3888a(th, "Mediator", str);
                }
            }
        }

        /* renamed from: b */
        public int mo9899b() {
            String str = "getMinZoomLevel";
            try {
                return Mediator.this.f2390i.f2312h;
            } catch (Throwable th) {
                C0955ck.m3888a(th, "Mediator", str);
                return 0;
            }
        }

        /* renamed from: b */
        public void mo9900b(int i) {
            String str = "setMinZoomLevel";
            if (i > 0) {
                try {
                    MapProjection mapProjection = Mediator.this.f2390i;
                    C1042p.f3034d = i;
                    mapProjection.f2312h = i;
                } catch (Throwable th) {
                    C0955ck.m3888a(th, "Mediator", str);
                }
            }
        }

        /* renamed from: c */
        public int mo9903c() {
            return C1042p.f3043m;
        }

        /* renamed from: d */
        public int mo9904d() {
            return C1042p.f3044n;
        }

        /* renamed from: e */
        public float mo9905e() {
            String str = "getZoomLevel";
            try {
                return Mediator.this.f2390i.f2314j;
            } catch (Throwable th) {
                C0955ck.m3888a(th, "Mediator", str);
                return 0.0f;
            }
        }

        /* renamed from: f */
        public GeoPoint mo9906f() {
            GeoPoint b = Mediator.this.f2390i.mo9854b(Mediator.this.f2390i.f2316l);
            if (Mediator.this.f2385d == null || !Mediator.this.f2385d.f2370a) {
                return b;
            }
            return Mediator.this.f2390i.f2317m;
        }

        /* renamed from: a */
        public void mo9896a(ViewProportyChangedListenner viewProportyChangedListenner) {
            this.f2378d.add(viewProportyChangedListenner);
        }

        /* renamed from: b */
        public void mo9901b(ViewProportyChangedListenner viewProportyChangedListenner) {
            this.f2378d.remove(viewProportyChangedListenner);
        }

        /* renamed from: a */
        public void mo9898a(boolean z, boolean z2) {
            Iterator it = this.f2378d.iterator();
            while (it.hasNext()) {
                ((ViewProportyChangedListenner) it.next()).mo10118a(z, z2);
            }
            if (Mediator.this.f2389h != null && Mediator.this.f2389h.f2488i != null) {
                Mediator.this.f2389h.f2488i.mo10102a(true);
                Mediator.this.f2389h.postInvalidate();
            }
        }

        /* renamed from: g */
        public AMapDelegateImpGLSurfaceView mo9907g() {
            return this.f2377c;
        }
    }

    /* compiled from: Mediator */
    /* renamed from: com.amap.api.mapcore2d.ay$e */
    public class C0911e implements Projection {
        /* renamed from: b */
        private float f2380b = 0.0f;
        /* renamed from: c */
        private HashMap<Float, Float> f2381c = new HashMap();

        /* renamed from: a */
        public Point mo9908a(GeoPoint geoPoint, Point point) {
            int i;
            int i2;
            PointF b = Mediator.this.f2390i.mo9852b(geoPoint, Mediator.this.f2390i.f2316l, Mediator.this.f2390i.f2318n, Mediator.this.f2390i.f2315k);
            MultiTouchGestureDetector G = Mediator.this.f2384c.f2377c.mo10010G();
            Point point2 = Mediator.this.f2384c.f2377c.mo10022b().f2390i.f2318n;
            float f;
            if (G.f2522m) {
                boolean z = true;
                try {
                    z = Mediator.this.f2389h.f2487h.mo9727f();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                if (G.f2521l && z) {
                    float f2 = ((MultiTouchGestureDetector.f2506j * (((float) ((int) b.x)) - G.f2516f.x)) + G.f2516f.x) + (G.f2517g.x - G.f2516f.x);
                    f = ((MultiTouchGestureDetector.f2506j * (((float) ((int) b.y)) - G.f2516f.y)) + G.f2516f.y) + (G.f2517g.y - G.f2516f.y);
                    i = (int) f2;
                    i2 = (int) f;
                    if (((double) f2) >= ((double) i) + 0.5d) {
                        i++;
                    }
                    if (((double) f) >= ((double) i2) + 0.5d) {
                        i2++;
                    }
                } else {
                    i = (int) b.x;
                    i2 = (int) b.y;
                }
            } else {
                float f3 = ((float) point2.x) + (Mediator.this.f2390i.f2307c * ((float) (((int) b.x) - point2.x)));
                f = (Mediator.this.f2390i.f2307c * ((float) (((int) b.y) - point2.y))) + ((float) point2.y);
                i = (int) f3;
                i2 = (int) f;
                if (((double) f3) >= ((double) i) + 0.5d) {
                    i++;
                }
                if (((double) f) >= ((double) i2) + 0.5d) {
                    i2++;
                }
            }
            Point point3 = new Point(i, i2);
            if (point != null) {
                point.x = point3.x;
                point.y = point3.y;
            }
            return point3;
        }

        /* renamed from: a */
        public GeoPoint mo9909a(int i, int i2) {
            return Mediator.this.f2390i.mo9845a(new PointF((float) i, (float) i2), Mediator.this.f2390i.f2316l, Mediator.this.f2390i.f2318n, Mediator.this.f2390i.f2315k, Mediator.this.f2390i.f2319o);
        }

        /* renamed from: a */
        public float mo9910a(float f) {
            float e = Mediator.this.f2384c.mo9905e();
            if (this.f2381c.size() > 30 || e != this.f2380b) {
                this.f2380b = e;
                this.f2381c.clear();
            }
            if (!this.f2381c.containsKey(Float.valueOf(f))) {
                e = Mediator.this.f2390i.mo9841a(mo9909a(0, 0), mo9909a(0, 100));
                if (e <= 0.0f) {
                    return 0.0f;
                }
                this.f2381c.put(Float.valueOf(f), Float.valueOf(100.0f * (f / e)));
            }
            return ((Float) this.f2381c.get(Float.valueOf(f))).floatValue();
        }

        /* renamed from: a */
        public int mo9911a(int i, int i2, int i3) {
            return m3340a(i, i2, i3, false);
        }

        /* renamed from: b */
        public int mo9912b(int i, int i2, int i3) {
            return m3340a(i, i2, i3, true);
        }

        /* renamed from: a */
        private int m3340a(int i, int i2, int i3, boolean z) {
            if (i <= 0) {
                i = Mediator.this.f2384c.mo9903c();
            }
            if (i2 <= 0) {
                i2 = Mediator.this.f2384c.mo9904d();
            }
            GeoPoint a = mo9909a(i3, i2 - i3);
            GeoPoint a2 = mo9909a(i - i3, i3);
            if (z) {
                return Math.abs(a.mo10332a() - a2.mo10332a());
            }
            return Math.abs(a.mo10334b() - a2.mo10334b());
        }
    }

    public Mediator(Context context, AMapDelegateImpGLSurfaceView aMapDelegateImpGLSurfaceView, int i) {
        this.f2389h = aMapDelegateImpGLSurfaceView;
        this.f2384c = new C0910d(aMapDelegateImpGLSurfaceView);
        this.f2390i = new MapProjection(this.f2384c);
        this.f2390i.f2305a = i;
        this.f2390i.f2306b = i;
        this.f2390i.mo9848a();
        mo9914a(context);
        this.f2387f = new C0909c(this, context);
        this.f2386e = new C0907a(context);
        this.f2383b = new C0911e();
        this.f2385d = new C0908b();
        this.f2388g = new C1047t();
        this.f2384c.mo9898a(false, false);
    }

    /* renamed from: a */
    public void mo9914a(Context context) {
        Field field;
        String str = "initialize";
        DisplayMetrics displayMetrics = new DisplayMetrics();
        DisplayMetrics displayMetrics2 = context.getApplicationContext().getResources().getDisplayMetrics();
        try {
            field = displayMetrics2.getClass().getField("densityDpi");
        } catch (SecurityException e) {
            C0955ck.m3888a(e, "Mediator", str);
            field = null;
        } catch (NoSuchFieldException e2) {
            C0955ck.m3888a(e2, "Mediator", str);
            field = null;
        }
        if (field != null) {
            int i;
            long j = (long) (displayMetrics2.widthPixels * displayMetrics2.heightPixels);
            try {
                i = field.getInt(displayMetrics2);
            } catch (IllegalArgumentException e3) {
                C0955ck.m3888a(e3, "Mediator", str);
                i = 160;
            } catch (IllegalAccessException e4) {
                C0955ck.m3888a(e4, "Mediator", str);
                i = 160;
            }
            if (i <= 120) {
                C1042p.f3042l = 1;
                return;
            } else if (i <= 160) {
                C1042p.f3042l = 3;
                return;
            } else if (i <= 240) {
                C1042p.f3042l = 2;
                return;
            } else if (j > 153600) {
                C1042p.f3042l = 2;
                return;
            } else if (j < 153600) {
                C1042p.f3042l = 1;
                return;
            } else {
                C1042p.f3042l = 3;
                return;
            }
        }
        long j2 = (long) (displayMetrics2.widthPixels * displayMetrics2.heightPixels);
        if (j2 > 153600) {
            C1042p.f3042l = 2;
        } else if (j2 < 153600) {
            C1042p.f3042l = 1;
        } else {
            C1042p.f3042l = 3;
        }
    }

    /* renamed from: a */
    public void mo9913a() {
        this.f2386e.mo9874a();
        this.f2383b = null;
        this.f2384c = null;
        this.f2385d = null;
        this.f2386e = null;
        this.f2387f = null;
    }

    /* renamed from: a */
    public void mo9915a(boolean z) {
        this.f2386e.mo9884b(z);
    }
}
