package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.autonavi.amap.mapcore.interfaces.CameraAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: MarkerDelegateImp */
/* renamed from: com.amap.api.mapcore2d.ax */
class C0903ax implements IMarkerDelegate {
    /* renamed from: a */
    private static int f2329a = 0;
    /* renamed from: b */
    private int f2330b = 0;
    /* renamed from: c */
    private float f2331c = 0.0f;
    /* renamed from: d */
    private CopyOnWriteArrayList<BitmapDescriptor> f2332d = null;
    /* renamed from: e */
    private int f2333e = 20;
    /* renamed from: f */
    private String f2334f;
    /* renamed from: g */
    private LatLng f2335g;
    /* renamed from: h */
    private LatLng f2336h;
    /* renamed from: i */
    private String f2337i;
    /* renamed from: j */
    private String f2338j;
    /* renamed from: k */
    private float f2339k = 0.5f;
    /* renamed from: l */
    private float f2340l = 1.0f;
    /* renamed from: m */
    private boolean f2341m = false;
    /* renamed from: n */
    private boolean f2342n = true;
    /* renamed from: o */
    private C0897as f2343o;
    /* renamed from: p */
    private Object f2344p;
    /* renamed from: q */
    private boolean f2345q = false;
    /* renamed from: r */
    private C0902a f2346r;
    /* renamed from: s */
    private boolean f2347s = false;
    /* renamed from: t */
    private int f2348t;
    /* renamed from: u */
    private int f2349u;
    /* renamed from: v */
    private float f2350v;
    /* renamed from: w */
    private int f2351w;

    /* compiled from: MarkerDelegateImp */
    /* renamed from: com.amap.api.mapcore2d.ax$a */
    private class C0902a extends Thread {
        private C0902a() {
        }

        public void run() {
            String str = "run";
            setName("MarkerThread");
            while (!Thread.currentThread().isInterrupted() && C0903ax.this.f2332d != null && C0903ax.this.f2332d.size() > 1) {
                if (C0903ax.this.f2330b == C0903ax.this.f2332d.size() - 1) {
                    C0903ax.this.f2330b = 0;
                } else {
                    C0903ax.this.f2330b = C0903ax.this.f2330b + 1;
                }
                C0903ax.this.f2343o.mo9821a().postInvalidate();
                try {
                    Thread.sleep((long) (C0903ax.this.f2333e * CameraAnimator.DEFAULT_DURATION));
                } catch (InterruptedException e) {
                    C0955ck.m3888a(e, "MarkerDelegateImp", str);
                }
                if (C0903ax.this.f2332d == null) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /* renamed from: c */
    private static String m3241c(String str) {
        f2329a++;
        return str + f2329a;
    }

    /* renamed from: l */
    public void mo9642l() {
        String str = "destroy";
        try {
            if (this.f2332d == null) {
                this.f2335g = null;
                this.f2344p = null;
                this.f2346r = null;
                return;
            }
            Iterator it = this.f2332d.iterator();
            while (it.hasNext()) {
                Bitmap bitmap = ((BitmapDescriptor) it.next()).getBitmap();
                if (bitmap != null) {
                    bitmap.recycle();
                }
            }
            this.f2332d = null;
            this.f2335g = null;
            this.f2344p = null;
            this.f2346r = null;
        } catch (Exception e) {
            C0955ck.m3888a(e, "MarkerDelegateImp", str);
            Log.d("destroy erro", "MarkerDelegateImp destroy");
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: w */
    public void mo9869w() {
        if (this.f2332d == null) {
            this.f2332d = new CopyOnWriteArrayList();
        } else {
            this.f2332d.clear();
        }
    }

    /* renamed from: b */
    public void mo9868b(ArrayList<BitmapDescriptor> arrayList) {
        mo9869w();
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                BitmapDescriptor bitmapDescriptor = (BitmapDescriptor) it.next();
                if (bitmapDescriptor != null) {
                    this.f2332d.add(bitmapDescriptor.clone());
                }
            }
            if (arrayList.size() > 1 && this.f2346r == null) {
                this.f2346r = new C0902a();
                this.f2346r.start();
            }
        }
        this.f2343o.mo9821a().postInvalidate();
    }

    /* renamed from: b */
    private void m3239b(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            mo9869w();
            this.f2332d.add(bitmapDescriptor.clone());
        }
        this.f2343o.mo9821a().postInvalidate();
    }

    public C0903ax(MarkerOptions markerOptions, C0897as c0897as) {
        String str = "MarkerDelegateImp";
        this.f2343o = c0897as;
        this.f2345q = markerOptions.isGps();
        this.f2350v = markerOptions.getZIndex();
        if (markerOptions.getPosition() != null) {
            if (this.f2345q) {
                try {
                    double[] a = C1035em.m4298a(markerOptions.getPosition().longitude, markerOptions.getPosition().latitude);
                    this.f2336h = new LatLng(a[1], a[0]);
                } catch (Exception e) {
                    C0955ck.m3888a(e, "MarkerDelegateImp", str);
                    this.f2336h = markerOptions.getPosition();
                }
            }
            this.f2335g = markerOptions.getPosition();
        }
        this.f2339k = markerOptions.getAnchorU();
        this.f2340l = markerOptions.getAnchorV();
        this.f2342n = markerOptions.isVisible();
        this.f2338j = markerOptions.getSnippet();
        this.f2337i = markerOptions.getTitle();
        this.f2341m = markerOptions.isDraggable();
        this.f2333e = markerOptions.getPeriod();
        this.f2334f = mo9634d();
        mo9868b(markerOptions.getIcons());
        if (this.f2332d != null && this.f2332d.size() == 0) {
            m3239b(markerOptions.getIcon());
        }
    }

    /* renamed from: x */
    public IPoint mo9870x() {
        if (mo9615t() == null) {
            return null;
        }
        GeoPoint geoPoint;
        IPoint iPoint = new IPoint();
        if (this.f2345q) {
            geoPoint = new GeoPoint((int) (mo9633c().latitude * 1000000.0d), (int) (mo9633c().longitude * 1000000.0d));
        } else {
            geoPoint = new GeoPoint((int) (mo9615t().latitude * 1000000.0d), (int) (mo9615t().longitude * 1000000.0d));
        }
        Point point = new Point();
        this.f2343o.mo9821a().mo9999s().mo9908a(geoPoint, point);
        iPoint.f2229a = point.x;
        iPoint.f2230b = point.y;
        return iPoint;
    }

    /* renamed from: n */
    public int mo9644n() {
        return mo9865A().getWidth();
    }

    /* renamed from: y */
    public int mo9871y() {
        return mo9865A().getHeight();
    }

    /* renamed from: e */
    public C1044r mo9635e() {
        C1044r c1044r = new C1044r();
        if (!(this.f2332d == null || this.f2332d.size() == 0)) {
            c1044r.f3048a = (double) (((float) mo9644n()) * this.f2339k);
            c1044r.f3049b = (double) (((float) mo9871y()) * this.f2340l);
        }
        return c1044r;
    }

    /* renamed from: z */
    public IPoint mo9872z() {
        IPoint x = mo9870x();
        if (x == null) {
            return null;
        }
        return x;
    }

    /* renamed from: b */
    public Rect mo9630b() {
        String str = "getRect";
        IPoint z = mo9872z();
        if (z == null) {
            return new Rect(0, 0, 0, 0);
        }
        try {
            int n = mo9644n();
            int y = mo9871y();
            Rect rect = new Rect();
            if (this.f2331c == 0.0f) {
                rect.top = (int) (((float) z.f2230b) - (((float) y) * this.f2340l));
                rect.left = (int) (((float) z.f2229a) - (this.f2339k * ((float) n)));
                rect.bottom = (int) ((((float) y) * (1.0f - this.f2340l)) + ((float) z.f2230b));
                rect.right = (int) (((float) z.f2229a) + (((float) n) * (1.0f - this.f2339k)));
                return rect;
            }
            IPoint b = m3238b((-this.f2339k) * ((float) n), (this.f2340l - 1.0f) * ((float) y));
            IPoint b2 = m3238b((-this.f2339k) * ((float) n), this.f2340l * ((float) y));
            IPoint b3 = m3238b((1.0f - this.f2339k) * ((float) n), this.f2340l * ((float) y));
            IPoint b4 = m3238b(((float) n) * (1.0f - this.f2339k), ((float) y) * (this.f2340l - 1.0f));
            rect.top = z.f2230b - Math.max(b.f2230b, Math.max(b2.f2230b, Math.max(b3.f2230b, b4.f2230b)));
            rect.left = z.f2229a + Math.min(b.f2229a, Math.min(b2.f2229a, Math.min(b3.f2229a, b4.f2229a)));
            rect.bottom = z.f2230b - Math.min(b.f2230b, Math.min(b2.f2230b, Math.min(b3.f2230b, b4.f2230b)));
            rect.right = z.f2229a + Math.max(b.f2229a, Math.max(b2.f2229a, Math.max(b3.f2229a, b4.f2229a)));
            return rect;
        } catch (Throwable th) {
            C0955ck.m3888a(th, "MarkerDelegateImp", str);
            return new Rect(0, 0, 0, 0);
        }
    }

    /* renamed from: b */
    private IPoint m3238b(float f, float f2) {
        float f3 = (float) ((3.141592653589793d * ((double) this.f2331c)) / 180.0d);
        IPoint iPoint = new IPoint();
        iPoint.f2229a = (int) ((((double) f) * Math.cos((double) f3)) + (((double) f2) * Math.sin((double) f3)));
        iPoint.f2230b = (int) ((((double) f2) * Math.cos((double) f3)) - (((double) f) * Math.sin((double) f3)));
        return iPoint;
    }

    /* renamed from: a */
    public boolean mo9628a() {
        return this.f2343o.mo9829b((IMarkerDelegate) this);
    }

    /* renamed from: t */
    public LatLng mo9615t() {
        if (!this.f2347s) {
            return this.f2335g;
        }
        C1044r c1044r = new C1044r();
        this.f2343o.f2283a.mo9949a(this.f2348t, this.f2349u, c1044r);
        return new LatLng(c1044r.f3049b, c1044r.f3048a);
    }

    /* renamed from: d */
    public String mo9634d() {
        if (this.f2334f == null) {
            this.f2334f = C0903ax.m3241c("Marker");
        }
        return this.f2334f;
    }

    /* renamed from: b */
    public void mo9612b(LatLng latLng) {
        String str = "setPosition";
        if (this.f2345q) {
            try {
                double[] a = C1035em.m4298a(latLng.longitude, latLng.latitude);
                this.f2336h = new LatLng(a[1], a[0]);
            } catch (Exception e) {
                C0955ck.m3888a(e, "MarkerDelegateImp", str);
                this.f2336h = latLng;
            }
        }
        this.f2347s = false;
        this.f2335g = latLng;
        this.f2343o.mo9821a().postInvalidate();
    }

    /* renamed from: a */
    public void mo9624a(LatLng latLng) {
        if (this.f2345q) {
            this.f2336h = latLng;
        } else {
            this.f2335g = latLng;
        }
    }

    /* renamed from: a */
    public void mo9625a(String str) {
        this.f2337i = str;
    }

    /* renamed from: f */
    public String mo9636f() {
        return this.f2337i;
    }

    /* renamed from: b */
    public void mo9631b(String str) {
        this.f2338j = str;
    }

    /* renamed from: g */
    public String mo9637g() {
        return this.f2338j;
    }

    /* renamed from: a */
    public void mo9627a(boolean z) {
        this.f2341m = z;
    }

    /* renamed from: a */
    public void mo9623a(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null && this.f2332d != null) {
            this.f2332d.clear();
            this.f2332d.add(bitmapDescriptor);
            if (mo9641k()) {
                this.f2343o.mo9835e(this);
                this.f2343o.mo9833d(this);
            }
            this.f2343o.mo9821a().postInvalidate();
        }
    }

    /* renamed from: A */
    public BitmapDescriptor mo9865A() {
        if (this.f2332d == null || this.f2332d.size() == 0) {
            mo9869w();
            this.f2332d.add(BitmapDescriptorFactory.defaultMarker());
        } else if (this.f2332d.get(0) == null) {
            this.f2332d.clear();
            return mo9865A();
        }
        return (BitmapDescriptor) this.f2332d.get(0);
    }

    /* renamed from: h */
    public boolean mo9638h() {
        return this.f2341m;
    }

    /* renamed from: i */
    public void mo9639i() {
        if (mo9614s()) {
            this.f2343o.mo9833d(this);
        }
    }

    /* renamed from: j */
    public void mo9640j() {
        if (mo9641k()) {
            this.f2343o.mo9835e(this);
        }
    }

    /* renamed from: k */
    public boolean mo9641k() {
        return this.f2343o.mo9837f(this);
    }

    /* renamed from: b */
    public void mo9632b(boolean z) {
        this.f2342n = z;
        if (!z && mo9641k()) {
            this.f2343o.mo9835e(this);
        }
        this.f2343o.mo9821a().postInvalidate();
    }

    /* renamed from: s */
    public boolean mo9614s() {
        return this.f2342n;
    }

    /* renamed from: a */
    public void mo9619a(float f, float f2) {
        if (this.f2339k != f || this.f2340l != f2) {
            this.f2339k = f;
            this.f2340l = f2;
            if (mo9641k()) {
                this.f2343o.mo9835e(this);
                this.f2343o.mo9833d(this);
            }
            this.f2343o.mo9821a().postInvalidate();
        }
    }

    /* renamed from: B */
    public float mo9866B() {
        return this.f2339k;
    }

    /* renamed from: C */
    public float mo9867C() {
        return this.f2340l;
    }

    /* renamed from: a */
    public void mo9618a(float f) {
        this.f2331c = (((-f) % 360.0f) + 360.0f) % 360.0f;
        if (mo9641k()) {
            this.f2343o.mo9835e(this);
            this.f2343o.mo9833d(this);
        }
        this.f2343o.mo9821a().postInvalidate();
    }

    /* renamed from: a */
    public boolean mo9629a(IMarkerDelegate iMarkerDelegate) {
        if (equals(iMarkerDelegate) || iMarkerDelegate.mo9634d().equals(mo9634d())) {
            return true;
        }
        return false;
    }

    /* renamed from: m */
    public int mo9643m() {
        return super.hashCode();
    }

    /* renamed from: a */
    public void mo9622a(Canvas canvas, IAMapDelegate iAMapDelegate) {
        if (this.f2342n && mo9615t() != null && mo9865A() != null) {
            IPoint iPoint;
            if (mo9647q()) {
                iPoint = new IPoint(this.f2348t, this.f2349u);
            } else {
                iPoint = mo9872z();
            }
            ArrayList p = mo9646p();
            if (p != null) {
                Bitmap bitmap = p.size() > 1 ? ((BitmapDescriptor) p.get(this.f2330b)).getBitmap() : p.size() == 1 ? ((BitmapDescriptor) p.get(0)).getBitmap() : null;
                if (bitmap != null && !bitmap.isRecycled()) {
                    canvas.save();
                    canvas.rotate(this.f2331c, (float) iPoint.f2229a, (float) iPoint.f2230b);
                    canvas.drawBitmap(bitmap, ((float) iPoint.f2229a) - (mo9866B() * ((float) bitmap.getWidth())), ((float) iPoint.f2230b) - (mo9867C() * ((float) bitmap.getHeight())), null);
                    canvas.restore();
                }
            }
        }
    }

    /* renamed from: a */
    public void mo9609a(Object obj) {
        this.f2344p = obj;
    }

    /* renamed from: u */
    public Object mo9616u() {
        return this.f2344p;
    }

    /* renamed from: c */
    public LatLng mo9633c() {
        if (!this.f2347s) {
            return this.f2345q ? this.f2336h : this.f2335g;
        } else {
            C1044r c1044r = new C1044r();
            this.f2343o.f2283a.mo9949a(this.f2348t, this.f2349u, c1044r);
            return new LatLng(c1044r.f3049b, c1044r.f3048a);
        }
    }

    /* renamed from: a */
    public void mo9620a(int i) throws RemoteException {
        if (i <= 1) {
            this.f2333e = 1;
        } else {
            this.f2333e = i;
        }
    }

    /* renamed from: o */
    public int mo9645o() throws RemoteException {
        return this.f2333e;
    }

    /* renamed from: a */
    public void mo9626a(ArrayList<BitmapDescriptor> arrayList) throws RemoteException {
        if (arrayList != null) {
            mo9868b((ArrayList) arrayList);
            if (this.f2346r == null) {
                this.f2346r = new C0902a();
                this.f2346r.start();
            }
            if (mo9641k()) {
                this.f2343o.mo9835e(this);
                this.f2343o.mo9833d(this);
            }
            this.f2343o.mo9821a().postInvalidate();
        }
    }

    /* renamed from: p */
    public ArrayList<BitmapDescriptor> mo9646p() {
        if (this.f2332d == null || this.f2332d.size() <= 0) {
            return null;
        }
        ArrayList<BitmapDescriptor> arrayList = new ArrayList();
        Iterator it = this.f2332d.iterator();
        while (it.hasNext()) {
            arrayList.add((BitmapDescriptor) it.next());
        }
        return arrayList;
    }

    /* renamed from: a */
    public void mo9621a(int i, int i2) {
        this.f2348t = i;
        this.f2349u = i2;
        this.f2347s = true;
        if (mo9641k()) {
            mo9639i();
        }
    }

    /* renamed from: q */
    public boolean mo9647q() {
        return this.f2347s;
    }

    /* renamed from: b */
    public void mo9610b(float f) {
        this.f2350v = f;
        this.f2343o.mo9832d();
    }

    /* renamed from: r */
    public float mo9613r() {
        return this.f2350v;
    }

    /* renamed from: b */
    public void mo9611b(int i) {
        this.f2351w = i;
    }

    /* renamed from: v */
    public int mo9617v() {
        return this.f2351w;
    }
}
