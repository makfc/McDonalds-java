package com.amap.api.mapcore.util;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.RemoteException;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.TileProjection;
import com.amap.api.maps.model.VisibleRegion;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IProjectionDelegate;

/* renamed from: com.amap.api.mapcore.util.aq */
class ProjectionDelegateImp implements IProjectionDelegate {
    /* renamed from: a */
    private IAMapDelegate f1159a;

    public ProjectionDelegateImp(IAMapDelegate iAMapDelegate) {
        this.f1159a = iAMapDelegate;
    }

    public LatLng fromScreenLocation(Point point) throws RemoteException {
        if (point == null) {
            return null;
        }
        DPoint dPoint = new DPoint();
        this.f1159a.getPixel2LatLng(point.x, point.y, dPoint);
        return new LatLng(dPoint.f4559y, dPoint.f4558x);
    }

    public Point toScreenLocation(LatLng latLng) throws RemoteException {
        if (latLng == null) {
            return null;
        }
        IPoint iPoint = new IPoint();
        this.f1159a.getLatLng2Pixel(latLng.latitude, latLng.longitude, iPoint);
        return new Point(iPoint.f4562x, iPoint.f4563y);
    }

    public VisibleRegion getVisibleRegion() throws RemoteException {
        int mapWidth = this.f1159a.getMapWidth();
        int mapHeight = this.f1159a.getMapHeight();
        LatLng fromScreenLocation = fromScreenLocation(new Point(0, 0));
        LatLng fromScreenLocation2 = fromScreenLocation(new Point(mapWidth, 0));
        LatLng fromScreenLocation3 = fromScreenLocation(new Point(0, mapHeight));
        LatLng fromScreenLocation4 = fromScreenLocation(new Point(mapWidth, mapHeight));
        return new VisibleRegion(fromScreenLocation3, fromScreenLocation4, fromScreenLocation, fromScreenLocation2, LatLngBounds.builder().include(fromScreenLocation3).include(fromScreenLocation4).include(fromScreenLocation).include(fromScreenLocation2).build());
    }

    public PointF toMapLocation(LatLng latLng) throws RemoteException {
        if (latLng == null) {
            return null;
        }
        FPoint fPoint = new FPoint();
        this.f1159a.getLatLng2Map(latLng.latitude, latLng.longitude, fPoint);
        return new PointF(fPoint.f4560x, fPoint.f4561y);
    }

    public float toMapLenWithWin(int i) {
        if (i <= 0) {
            return 0.0f;
        }
        return this.f1159a.toMapLenWithWin(i);
    }

    public TileProjection fromBoundsToTile(LatLngBounds latLngBounds, int i, int i2) throws RemoteException {
        if (latLngBounds == null || i < 0 || i > 20 || i2 <= 0) {
            return null;
        }
        IPoint iPoint = new IPoint();
        IPoint iPoint2 = new IPoint();
        this.f1159a.latlon2Geo(latLngBounds.southwest.latitude, latLngBounds.southwest.longitude, iPoint);
        this.f1159a.latlon2Geo(latLngBounds.northeast.latitude, latLngBounds.northeast.longitude, iPoint2);
        int i3 = (iPoint.f4562x >> (20 - i)) / i2;
        int i4 = (iPoint2.f4563y >> (20 - i)) / i2;
        return new TileProjection((iPoint.f4562x - ((i3 << (20 - i)) * i2)) >> (20 - i), (iPoint2.f4563y - ((i4 << (20 - i)) * i2)) >> (20 - i), i3, (iPoint2.f4562x >> (20 - i)) / i2, i4, (iPoint.f4563y >> (20 - i)) / i2);
    }

    public LatLngBounds getMapBounds(LatLng latLng, float f) throws RemoteException {
        if (this.f1159a == null || latLng == null) {
            return null;
        }
        return this.f1159a.getMapBounds(latLng, Util.m2337a(f));
    }
}
