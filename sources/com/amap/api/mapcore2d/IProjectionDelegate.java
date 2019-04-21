package com.amap.api.mapcore2d;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.RemoteException;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.VisibleRegion;

/* renamed from: com.amap.api.mapcore2d.ag */
public interface IProjectionDelegate {
    /* renamed from: a */
    Point mo9678a(LatLng latLng) throws RemoteException;

    /* renamed from: a */
    LatLng mo9679a(Point point) throws RemoteException;

    /* renamed from: a */
    VisibleRegion mo9680a() throws RemoteException;

    /* renamed from: b */
    PointF mo9681b(LatLng latLng) throws RemoteException;
}
