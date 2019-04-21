package com.amap.api.maps2d;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.RemoteException;
import com.amap.api.mapcore2d.C0955ck;
import com.amap.api.mapcore2d.IProjectionDelegate;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.RuntimeRemoteException;
import com.amap.api.maps2d.model.VisibleRegion;

public class Projection {
    /* renamed from: a */
    private final IProjectionDelegate f3372a;

    Projection(IProjectionDelegate iProjectionDelegate) {
        this.f3372a = iProjectionDelegate;
    }

    public LatLng fromScreenLocation(Point point) {
        String str = "fromScreenLocation";
        try {
            return this.f3372a.mo9679a(point);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Projection", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public Point toScreenLocation(LatLng latLng) {
        String str = "toScreenLocation";
        try {
            return this.f3372a.mo9678a(latLng);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Projection", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public PointF toMapLocation(LatLng latLng) {
        String str = "toMapLocation";
        try {
            return this.f3372a.mo9681b(latLng);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Projection", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public VisibleRegion getVisibleRegion() {
        String str = "getVisibleRegion";
        try {
            return this.f3372a.mo9680a();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "Projection", str);
            throw new RuntimeRemoteException(e);
        }
    }
}
