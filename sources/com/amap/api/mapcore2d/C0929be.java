package com.amap.api.mapcore2d;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.os.RemoteException;
import android.support.p000v4.view.ViewCompat;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.LatLngBounds.Builder;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PolylineDelegateImp */
/* renamed from: com.amap.api.mapcore2d.be */
class C0929be implements IPolylineDelegate {
    /* renamed from: a */
    private AMapDelegateImpGLSurfaceView f2548a;
    /* renamed from: b */
    private float f2549b = 10.0f;
    /* renamed from: c */
    private int f2550c = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: d */
    private float f2551d = 0.0f;
    /* renamed from: e */
    private boolean f2552e = true;
    /* renamed from: f */
    private boolean f2553f = false;
    /* renamed from: g */
    private boolean f2554g = false;
    /* renamed from: h */
    private String f2555h;
    /* renamed from: i */
    private List<IPoint> f2556i = new ArrayList();
    /* renamed from: j */
    private List<LatLng> f2557j = new ArrayList();
    /* renamed from: k */
    private LatLngBounds f2558k = null;

    public C0929be(AMapDelegateImpGLSurfaceView aMapDelegateImpGLSurfaceView) {
        String str = "PolylineDelegateImp";
        this.f2548a = aMapDelegateImpGLSurfaceView;
        try {
            this.f2555h = mo9654c();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "PolylineDelegateImp", str);
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo10066b(List<LatLng> list) throws RemoteException {
        if (list != null && list.size() != 0) {
            Builder builder = LatLngBounds.builder();
            this.f2556i.clear();
            if (list != null) {
                Object obj = null;
                for (LatLng latLng : list) {
                    if (!(latLng == null || latLng.equals(obj))) {
                        IPoint iPoint;
                        if (!this.f2554g) {
                            iPoint = new IPoint();
                            this.f2548a.mo10017a(latLng.latitude, latLng.longitude, iPoint);
                            this.f2556i.add(iPoint);
                            builder.include(latLng);
                        } else if (obj != null) {
                            if (Math.abs(latLng.longitude - obj.longitude) < 0.01d) {
                                iPoint = new IPoint();
                                this.f2548a.mo10017a(obj.latitude, obj.longitude, iPoint);
                                this.f2556i.add(iPoint);
                                builder.include(obj);
                                iPoint = new IPoint();
                                this.f2548a.mo10017a(latLng.latitude, latLng.longitude, iPoint);
                                this.f2556i.add(iPoint);
                                builder.include(latLng);
                            } else {
                                mo10064a(obj, latLng, this.f2556i, builder);
                            }
                        }
                        obj = latLng;
                    }
                }
            }
            if (this.f2556i.size() > 0) {
                this.f2558k = builder.build();
            }
        }
    }

    /* renamed from: b */
    public void mo9653b() throws RemoteException {
        this.f2548a.mo9969a(mo9654c());
    }

    /* renamed from: c */
    public String mo9654c() throws RemoteException {
        if (this.f2555h == null) {
            this.f2555h = C1047t.m4384a("Polyline");
        }
        return this.f2555h;
    }

    /* renamed from: a */
    public void mo9669a(List<LatLng> list) throws RemoteException {
        if (this.f2554g || this.f2553f) {
            this.f2557j = list;
        }
        mo10066b((List) list);
    }

    /* renamed from: i */
    public List<LatLng> mo9675i() throws RemoteException {
        if (this.f2554g || this.f2553f) {
            return this.f2557j;
        }
        return m3636m();
    }

    /* renamed from: m */
    private List<LatLng> m3636m() throws RemoteException {
        if (this.f2556i == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (IPoint iPoint : this.f2556i) {
            if (iPoint != null) {
                C1044r c1044r = new C1044r();
                this.f2548a.mo10023b(iPoint.f2229a, iPoint.f2230b, c1044r);
                arrayList.add(new LatLng(c1044r.f3049b, c1044r.f3048a));
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public void mo9670b(float f) throws RemoteException {
        this.f2549b = f;
    }

    /* renamed from: g */
    public float mo9673g() throws RemoteException {
        return this.f2549b;
    }

    /* renamed from: b */
    public void mo9671b(boolean z) {
        this.f2553f = z;
    }

    /* renamed from: j */
    public boolean mo9676j() {
        return this.f2553f;
    }

    /* renamed from: c */
    public void mo9672c(boolean z) throws RemoteException {
        if (this.f2554g != z) {
            this.f2554g = z;
        }
    }

    /* renamed from: k */
    public boolean mo9677k() {
        return this.f2554g;
    }

    /* renamed from: a */
    public void mo9668a(int i) throws RemoteException {
        this.f2550c = i;
    }

    /* renamed from: h */
    public int mo9674h() throws RemoteException {
        return this.f2550c;
    }

    /* renamed from: a */
    public void mo9648a(float f) throws RemoteException {
        this.f2551d = f;
        this.f2548a.invalidate();
    }

    /* renamed from: d */
    public float mo9655d() throws RemoteException {
        return this.f2551d;
    }

    /* renamed from: a */
    public void mo9650a(boolean z) throws RemoteException {
        this.f2552e = z;
    }

    /* renamed from: e */
    public boolean mo9656e() throws RemoteException {
        return this.f2552e;
    }

    /* renamed from: a */
    public boolean mo9652a(IOverlayDelegate iOverlayDelegate) throws RemoteException {
        if (equals(iOverlayDelegate) || iOverlayDelegate.mo9654c().equals(mo9654c())) {
            return true;
        }
        return false;
    }

    /* renamed from: f */
    public int mo9657f() throws RemoteException {
        return super.hashCode();
    }

    /* renamed from: a */
    public boolean mo9651a() {
        if (this.f2558k == null) {
            return false;
        }
        LatLngBounds x = this.f2548a.mo10055x();
        if (x == null) {
            return true;
        }
        if (x.contains(this.f2558k) || this.f2558k.intersects(x)) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public void mo9649a(Canvas canvas) throws RemoteException {
        if (this.f2556i != null && this.f2556i.size() != 0 && this.f2549b > 0.0f) {
            int i;
            Path path = new Path();
            Point a = this.f2548a.mo9999s().mo9908a(new GeoPoint(((IPoint) this.f2556i.get(0)).f2230b, ((IPoint) this.f2556i.get(0)).f2229a), new Point());
            path.moveTo((float) a.x, (float) a.y);
            for (i = 1; i < this.f2556i.size(); i++) {
                a = this.f2548a.mo9999s().mo9908a(new GeoPoint(((IPoint) this.f2556i.get(i)).f2230b, ((IPoint) this.f2556i.get(i)).f2229a), new Point());
                path.lineTo((float) a.x, (float) a.y);
            }
            Paint paint = new Paint();
            paint.setColor(mo9674h());
            paint.setAntiAlias(true);
            paint.setStrokeWidth(mo9673g());
            paint.setStyle(Style.STROKE);
            paint.setStrokeJoin(Join.ROUND);
            if (this.f2553f) {
                i = (int) mo9673g();
                paint.setPathEffect(new DashPathEffect(new float[]{(float) (i * 3), (float) i, (float) (i * 3), (float) i}, 1.0f));
            }
            canvas.drawPath(path, paint);
        }
    }

    /* renamed from: l */
    public void mo9658l() {
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public IPoint mo10063a(IPoint iPoint, IPoint iPoint2, IPoint iPoint3, double d, int i) {
        IPoint iPoint4 = new IPoint();
        double d2 = (double) (iPoint2.f2229a - iPoint.f2229a);
        double d3 = (double) (iPoint2.f2230b - iPoint.f2230b);
        iPoint4.f2230b = (int) (((((double) i) * d) / Math.sqrt(((d3 * d3) / (d2 * d2)) + 1.0d)) + ((double) iPoint3.f2230b));
        iPoint4.f2229a = (int) (((d3 * ((double) (iPoint3.f2230b - iPoint4.f2230b))) / d2) + ((double) iPoint3.f2229a));
        return iPoint4;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo10065a(List<IPoint> list, List<IPoint> list2, double d) {
        if (list.size() == 3) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 <= 10) {
                    float f = ((float) i2) / 10.0f;
                    IPoint iPoint = new IPoint();
                    double d2 = ((((1.0d - ((double) f)) * (1.0d - ((double) f))) * ((double) ((IPoint) list.get(0)).f2229a)) + (((((double) (2.0f * f)) * (1.0d - ((double) f))) * ((double) ((IPoint) list.get(1)).f2229a)) * d)) + ((double) (((float) ((IPoint) list.get(2)).f2229a) * (f * f)));
                    double d3 = ((((1.0d - ((double) f)) * (1.0d - ((double) f))) * ((double) ((IPoint) list.get(0)).f2230b)) + (((((double) (2.0f * f)) * (1.0d - ((double) f))) * ((double) ((IPoint) list.get(1)).f2230b)) * d)) + ((double) (((float) ((IPoint) list.get(2)).f2230b) * (f * f)));
                    double d4 = (((1.0d - ((double) f)) * (1.0d - ((double) f))) + ((((double) (2.0f * f)) * (1.0d - ((double) f))) * d)) + ((double) (f * f));
                    iPoint.f2229a = (int) (d2 / ((((1.0d - ((double) f)) * (1.0d - ((double) f))) + ((((double) (2.0f * f)) * (1.0d - ((double) f))) * d)) + ((double) (f * f))));
                    iPoint.f2230b = (int) (d3 / d4);
                    list2.add(iPoint);
                    i = (int) (((float) i2) + 1.0f);
                } else {
                    return;
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo10064a(LatLng latLng, LatLng latLng2, List<IPoint> list, Builder builder) {
        double abs = (Math.abs(latLng.longitude - latLng2.longitude) * 3.141592653589793d) / 180.0d;
        LatLng latLng3 = new LatLng((latLng2.latitude + latLng.latitude) / 2.0d, (latLng2.longitude + latLng.longitude) / 2.0d);
        builder.include(latLng).include(latLng3).include(latLng2);
        int i = latLng3.latitude > 0.0d ? 1 : -1;
        IPoint iPoint = new IPoint();
        this.f2548a.mo10017a(latLng.latitude, latLng.longitude, iPoint);
        IPoint iPoint2 = new IPoint();
        this.f2548a.mo10017a(latLng2.latitude, latLng2.longitude, iPoint2);
        IPoint iPoint3 = new IPoint();
        this.f2548a.mo10017a(latLng3.latitude, latLng3.longitude, iPoint3);
        double cos = Math.cos(0.5d * abs);
        IPoint a = mo10063a(iPoint, iPoint2, iPoint3, (Math.hypot((double) (iPoint.f2229a - iPoint2.f2229a), (double) (iPoint.f2230b - iPoint2.f2230b)) * 0.5d) * Math.tan(0.5d * abs), i);
        ArrayList arrayList = new ArrayList();
        arrayList.add(iPoint);
        arrayList.add(a);
        arrayList.add(iPoint2);
        mo10065a(arrayList, list, cos);
    }
}
