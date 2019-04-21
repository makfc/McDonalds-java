package com.amap.api.mapcore2d;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.os.RemoteException;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.LatLngBounds.Builder;
import com.amap.api.services.poisearch.PoiSearch.SearchBound;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PolygonDelegateImp */
/* renamed from: com.amap.api.mapcore2d.bd */
class C0928bd implements IPolygonDelegate {
    /* renamed from: a */
    private AMapDelegateImpGLSurfaceView f2539a;
    /* renamed from: b */
    private float f2540b = 0.0f;
    /* renamed from: c */
    private boolean f2541c = true;
    /* renamed from: d */
    private String f2542d;
    /* renamed from: e */
    private float f2543e;
    /* renamed from: f */
    private int f2544f;
    /* renamed from: g */
    private int f2545g;
    /* renamed from: h */
    private List<IPoint> f2546h = new ArrayList();
    /* renamed from: i */
    private LatLngBounds f2547i = null;

    public C0928bd(AMapDelegateImpGLSurfaceView aMapDelegateImpGLSurfaceView) {
        String str = "PolygonDelegateImp";
        this.f2539a = aMapDelegateImpGLSurfaceView;
        try {
            this.f2542d = mo9654c();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "PolygonDelegateImp", str);
        }
    }

    /* renamed from: b */
    public void mo9653b() throws RemoteException {
        this.f2539a.mo9969a(mo9654c());
    }

    /* renamed from: c */
    public String mo9654c() throws RemoteException {
        if (this.f2542d == null) {
            this.f2542d = C1047t.m4384a(SearchBound.POLYGON_SHAPE);
        }
        return this.f2542d;
    }

    /* renamed from: a */
    public void mo9660a(List<LatLng> list) throws RemoteException {
        mo10061b((List) list);
    }

    /* renamed from: i */
    public List<LatLng> mo9666i() throws RemoteException {
        return mo10062k();
    }

    /* renamed from: a */
    public void mo9648a(float f) throws RemoteException {
        this.f2540b = f;
        this.f2539a.invalidate();
    }

    /* renamed from: d */
    public float mo9655d() throws RemoteException {
        return this.f2540b;
    }

    /* renamed from: a */
    public void mo9650a(boolean z) throws RemoteException {
        this.f2541c = z;
    }

    /* renamed from: e */
    public boolean mo9656e() throws RemoteException {
        return this.f2541c;
    }

    /* renamed from: a */
    public boolean mo9652a(IOverlayDelegate iOverlayDelegate) throws RemoteException {
        if (equals(iOverlayDelegate) || iOverlayDelegate.mo9654c().equals(mo9654c())) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo10061b(List<LatLng> list) throws RemoteException {
        Builder builder = LatLngBounds.builder();
        this.f2546h.clear();
        if (list != null) {
            Object obj = null;
            for (LatLng latLng : list) {
                if (!latLng.equals(obj)) {
                    IPoint iPoint = new IPoint();
                    this.f2539a.mo10017a(latLng.latitude, latLng.longitude, iPoint);
                    this.f2546h.add(iPoint);
                    builder.include(latLng);
                    obj = latLng;
                }
            }
            int size = this.f2546h.size();
            if (size > 1) {
                IPoint iPoint2 = (IPoint) this.f2546h.get(0);
                IPoint iPoint3 = (IPoint) this.f2546h.get(size - 1);
                if (iPoint2.f2229a == iPoint3.f2229a && iPoint2.f2230b == iPoint3.f2230b) {
                    this.f2546h.remove(size - 1);
                }
            }
        }
        this.f2547i = builder.build();
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: k */
    public List<LatLng> mo10062k() throws RemoteException {
        if (this.f2546h == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (IPoint iPoint : this.f2546h) {
            if (iPoint != null) {
                C1044r c1044r = new C1044r();
                this.f2539a.mo10023b(iPoint.f2229a, iPoint.f2230b, c1044r);
                arrayList.add(new LatLng(c1044r.f3049b, c1044r.f3048a));
            }
        }
        return arrayList;
    }

    /* renamed from: f */
    public int mo9657f() throws RemoteException {
        return super.hashCode();
    }

    /* renamed from: a */
    public boolean mo9651a() {
        if (this.f2547i == null) {
            return false;
        }
        LatLngBounds x = this.f2539a.mo10055x();
        if (x == null) {
            return true;
        }
        if (this.f2547i.contains(x) || this.f2547i.intersects(x)) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public void mo9649a(Canvas canvas) throws RemoteException {
        if (this.f2546h != null && this.f2546h.size() != 0) {
            Path path = new Path();
            Point a = this.f2539a.mo9999s().mo9908a(new GeoPoint(((IPoint) this.f2546h.get(0)).f2230b, ((IPoint) this.f2546h.get(0)).f2229a), new Point());
            path.moveTo((float) a.x, (float) a.y);
            for (int i = 1; i < this.f2546h.size(); i++) {
                a = this.f2539a.mo9999s().mo9908a(new GeoPoint(((IPoint) this.f2546h.get(i)).f2230b, ((IPoint) this.f2546h.get(i)).f2229a), new Point());
                path.lineTo((float) a.x, (float) a.y);
            }
            Paint paint = new Paint();
            paint.setColor(mo9665h());
            paint.setAntiAlias(true);
            path.close();
            paint.setStyle(Style.FILL);
            canvas.drawPath(path, paint);
            paint.setStyle(Style.STROKE);
            paint.setColor(mo9667j());
            paint.setStrokeWidth(mo9664g());
            canvas.drawPath(path, paint);
        }
    }

    /* renamed from: b */
    public void mo9662b(float f) throws RemoteException {
        this.f2543e = f;
    }

    /* renamed from: g */
    public float mo9664g() throws RemoteException {
        return this.f2543e;
    }

    /* renamed from: a */
    public void mo9659a(int i) throws RemoteException {
        this.f2544f = i;
    }

    /* renamed from: h */
    public int mo9665h() throws RemoteException {
        return this.f2544f;
    }

    /* renamed from: b */
    public void mo9663b(int i) throws RemoteException {
        this.f2545g = i;
    }

    /* renamed from: j */
    public int mo9667j() throws RemoteException {
        return this.f2545g;
    }

    /* renamed from: l */
    public void mo9658l() {
    }

    /* renamed from: a */
    public boolean mo9661a(LatLng latLng) throws RemoteException {
        return C0955ck.m3892a(latLng, mo9666i());
    }
}
