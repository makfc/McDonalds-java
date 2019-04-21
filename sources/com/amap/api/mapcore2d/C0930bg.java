package com.amap.api.mapcore2d;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.RemoteException;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.VisibleRegion;

/* compiled from: ProjectionDelegateImp */
/* renamed from: com.amap.api.mapcore2d.bg */
class C0930bg implements IProjectionDelegate {
    /* renamed from: a */
    private String f2559a = "ProjectionDelegateImp";
    /* renamed from: b */
    private IAMapDelegate f2560b;

    public C0930bg(IAMapDelegate iAMapDelegate) {
        this.f2560b = iAMapDelegate;
    }

    /* renamed from: a */
    public LatLng mo9679a(Point point) throws RemoteException {
        C1044r c1044r = new C1044r();
        this.f2560b.mo9949a(point.x, point.y, c1044r);
        return new LatLng(c1044r.f3049b, c1044r.f3048a);
    }

    /* renamed from: a */
    public Point mo9678a(LatLng latLng) throws RemoteException {
        IPoint iPoint = new IPoint();
        this.f2560b.mo9971b(latLng.latitude, latLng.longitude, iPoint);
        return new Point(iPoint.f2229a, iPoint.f2230b);
    }

    /* renamed from: a */
    public VisibleRegion mo9680a() throws RemoteException {
        LatLng a;
        LatLng a2;
        LatLng a3;
        LatLng a4;
        Throwable th;
        Object a42;
        Object a32;
        LatLngBounds latLngBounds = null;
        String str = "getVisibleRegion";
        try {
            int c = this.f2560b.mo9977c();
            int d = this.f2560b.mo9981d();
            a = mo9679a(new Point(0, 0));
            try {
                a2 = mo9679a(new Point(c, 0));
                try {
                    a32 = mo9679a(new Point(0, d));
                    try {
                        a42 = mo9679a(new Point(c, d));
                        try {
                            latLngBounds = LatLngBounds.builder().include(a32).include(a42).include(a).include(a2).build();
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        a42 = latLngBounds;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    a42 = latLngBounds;
                    a32 = latLngBounds;
                }
            } catch (Throwable th5) {
                th = th5;
                a42 = latLngBounds;
                a32 = latLngBounds;
                Object a22 = latLngBounds;
            }
        } catch (Throwable th6) {
            th = th6;
            a42 = latLngBounds;
            a32 = latLngBounds;
            a22 = latLngBounds;
            a = latLngBounds;
        }
        return new VisibleRegion(a32, a42, a, a22, latLngBounds);
        C0955ck.m3888a(th, this.f2559a, str);
        return new VisibleRegion(a32, a42, a, a22, latLngBounds);
    }

    /* renamed from: b */
    public PointF mo9681b(LatLng latLng) throws RemoteException {
        C1044r c1044r = new C1044r();
        this.f2560b.mo9947a(latLng.latitude, latLng.longitude, c1044r);
        return new PointF((float) c1044r.f3048a, (float) c1044r.f3049b);
    }
}
