package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Point;
import android.os.Message;
import android.os.RemoteException;
import com.amap.api.mapcore.indoor.IndoorBuilding;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.BaseMapCallImplement;
import com.autonavi.amap.mapcore.Convert;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapCore;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.MapSourceGridData;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate.Type;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.util.a */
class AMapCallback extends BaseMapCallImplement {
    /* renamed from: a */
    IPoint f955a = new IPoint();
    /* renamed from: b */
    float f956b;
    /* renamed from: c */
    float f957c;
    /* renamed from: d */
    float f958d;
    /* renamed from: e */
    IPoint f959e = new IPoint();
    /* renamed from: f */
    private AMapDelegateImp f960f;
    /* renamed from: g */
    private float f961g = -1.0f;
    /* renamed from: h */
    private int f962h;
    /* renamed from: i */
    private int f963i;

    public String getMapSvrAddress() {
        return "http://mps.amap.com";
    }

    public AMapCallback(AMapDelegateImp aMapDelegateImp) {
        this.f960f = aMapDelegateImp;
    }

    public void OnMapSurfaceCreate(GL10 gl10, MapCore mapCore) {
        super.OnMapSurfaceCreate(mapCore);
    }

    public void OnMapSurfaceRenderer(GL10 gl10, MapCore mapCore, int i) {
        super.OnMapSurfaceRenderer(gl10, mapCore, i);
        if (i == 3) {
            this.f960f.f1641h.mo9563a(gl10, true, this.f960f.getMapTextZIndex());
            if (this.f960f.f1646m != null) {
                this.f960f.f1646m.onDrawFrame(gl10);
            }
        }
    }

    public void OnMapSufaceChanged(GL10 gl10, MapCore mapCore, int i, int i2) {
    }

    public void OnMapProcessEvent(MapCore mapCore) {
        float f = 0.0f;
        if (this.f960f != null && this.f960f.isNeedRunDestroy()) {
            this.f960f.runDestroy();
        }
        if (this.f960f != null) {
            float zoomLevel = this.f960f.getZoomLevel();
            mo8463a(mapCore);
            while (true) {
                MapMessage a = this.f960f.f1638e.mo8485a();
                if (a == null) {
                    break;
                } else if (a.f981a == 2) {
                    if (a.mo8484a()) {
                        mapCore.setParameter(OrderResponse.ORDER_IS_NOT_READY_CODE, 4, 0, 0, 0);
                    } else {
                        mapCore.setParameter(OrderResponse.ORDER_IS_NOT_READY_CODE, 0, 0, 0, 0);
                    }
                }
            }
            mapCore.setMapstate(this.f960f.getMapProjection());
            if (!(this.f956b < this.f960f.getMinZoomLevel() || this.f961g == zoomLevel || this.f960f.f1645l == null)) {
                this.f960f.f1645l.obtainMessage(21).sendToTarget();
            }
            f = zoomLevel;
        }
        this.f961g = f;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8463a(MapCore mapCore) {
        Object obj = null;
        MapProjection mapProjection = this.f960f.getMapProjection();
        float mapZoomer = mapProjection.getMapZoomer();
        float cameraHeaderAngle = mapProjection.getCameraHeaderAngle();
        float mapAngle = mapProjection.getMapAngle();
        mapProjection.getGeoCenter(this.f959e);
        int i = 0;
        while (this.f960f.isDrawOnce()) {
            CameraUpdateFactoryDelegate c = this.f960f.f1638e.mo8489c();
            if (c == null) {
                break;
            }
            try {
                mo8464a(c);
                i |= c.isChangeFinished;
            } catch (RemoteException e) {
                SDKLogHandler.m2563a(e, "AMapCallback", "runMessage");
                e.printStackTrace();
            }
        }
        this.f956b = mapProjection.getMapZoomer();
        this.f957c = mapProjection.getCameraHeaderAngle();
        this.f958d = mapProjection.getMapAngle();
        mapProjection.getGeoCenter(this.f955a);
        if (!(mapZoomer == this.f956b && this.f957c == cameraHeaderAngle && this.f958d == mapAngle && this.f955a.f4562x == this.f959e.f4562x && this.f955a.f4563y == this.f959e.f4563y)) {
            obj = 1;
        }
        if (obj != null) {
            try {
                this.f960f.setRunLowFrame(false);
                if (this.f960f.getOnCameraChangeListener() != null) {
                    DPoint dPoint = new DPoint();
                    MapProjection.geo2LonLat(this.f955a.f4562x, this.f955a.f4563y, dPoint);
                    this.f960f.mo9168a(new CameraPosition(new LatLng(dPoint.f4559y, dPoint.f4558x, false), this.f956b, this.f957c, this.f958d));
                }
                this.f960f.mo9175e();
            } catch (RemoteException e2) {
                SDKLogHandler.m2563a(e2, "AMapCallback", "runMessage cameraChange");
                e2.printStackTrace();
                return;
            }
        }
        this.f960f.setRunLowFrame(true);
        if (i != 0) {
            if (i != 0) {
                this.f960f.mo9170a(true);
            } else {
                this.f960f.mo9170a(false);
            }
            Message message = new Message();
            message.what = 17;
            this.f960f.f1645l.sendMessage(message);
        }
        if (!(this.f957c == cameraHeaderAngle && this.f958d == mapAngle) && this.f960f.getUiSettings().isCompassEnabled()) {
            this.f960f.mo9163a();
        }
        if (this.f960f.getUiSettings().isScaleControlsEnabled()) {
            this.f960f.mo9172b();
        }
    }

    /* renamed from: b */
    private void m1502b(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) {
        MapCore mapCore = this.f960f.getMapCore();
        MapProjection mapProjection = this.f960f.getMapProjection();
        LatLngBounds latLngBounds = cameraUpdateFactoryDelegate.bounds;
        int i = cameraUpdateFactoryDelegate.width;
        int i2 = cameraUpdateFactoryDelegate.height;
        int i3 = cameraUpdateFactoryDelegate.padding;
        IPoint iPoint = new IPoint();
        IPoint iPoint2 = new IPoint();
        MapProjection.lonlat2Geo(latLngBounds.northeast.longitude, latLngBounds.northeast.latitude, iPoint);
        MapProjection.lonlat2Geo(latLngBounds.southwest.longitude, latLngBounds.southwest.latitude, iPoint2);
        int i4 = iPoint2.f4563y - iPoint.f4563y;
        int i5 = i - (i3 * 2);
        i = i2 - (i3 * 2);
        if (iPoint.f4562x - iPoint2.f4562x >= 0 || i4 >= 0) {
            if (i5 <= 0) {
                i5 = 1;
            }
            if (i <= 0) {
                i = 1;
            }
            float a = m1492a(latLngBounds.northeast, latLngBounds.southwest, i5, i);
            i5 = (iPoint.f4562x + iPoint2.f4562x) / 2;
            int i6 = (iPoint.f4563y + iPoint2.f4563y) / 2;
            mapProjection.setMapZoomer(a);
            mapProjection.setGeoCenter(i5, i6);
            mapProjection.setCameraHeaderAngle(0.0f);
            mapProjection.setMapAngle(0.0f);
            mapCore.setMapstate(mapProjection);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ea A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00fa A:{LOOP_END, LOOP:0: B:10:0x00a3->B:20:0x00fa} */
    /* renamed from: a */
    private float m1492a(com.amap.api.maps.model.LatLng r21, com.amap.api.maps.model.LatLng r22, int r23, int r24) {
        /*
        r20 = this;
        r0 = r20;
        r2 = r0.f960f;
        r16 = r2.getMapProjection();
        r2 = 0;
        r0 = r16;
        r0.setMapAngle(r2);
        r2 = 0;
        r0 = r16;
        r0.setCameraHeaderAngle(r2);
        r16.recalculate();
        r8 = new com.autonavi.amap.mapcore.IPoint;
        r8.<init>();
        r14 = new com.autonavi.amap.mapcore.IPoint;
        r14.<init>();
        r0 = r20;
        r3 = r0.f960f;
        r0 = r21;
        r4 = r0.latitude;
        r0 = r21;
        r6 = r0.longitude;
        r3.getLatLng2Pixel(r4, r6, r8);
        r0 = r20;
        r9 = r0.f960f;
        r0 = r22;
        r10 = r0.latitude;
        r0 = r22;
        r12 = r0.longitude;
        r9.getLatLng2Pixel(r10, r12, r14);
        r2 = r8.f4562x;
        r3 = r14.f4562x;
        r2 = r2 - r3;
        r4 = (double) r2;
        r2 = r14.f4563y;
        r3 = r8.f4563y;
        r2 = r2 - r3;
        r2 = (double) r2;
        r6 = 0;
        r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r6 > 0) goto L_0x0053;
    L_0x0051:
        r4 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
    L_0x0053:
        r6 = 0;
        r6 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r6 > 0) goto L_0x005b;
    L_0x0059:
        r2 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
    L_0x005b:
        r0 = r23;
        r6 = (double) r0;
        r4 = r6 / r4;
        r4 = java.lang.Math.log(r4);
        r6 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r6 = java.lang.Math.log(r6);
        r4 = r4 / r6;
        r0 = r24;
        r6 = (double) r0;
        r2 = r6 / r2;
        r2 = java.lang.Math.log(r2);
        r6 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r6 = java.lang.Math.log(r6);
        r2 = r2 / r6;
        r6 = java.lang.Math.min(r4, r2);
        r2 = r6 - r4;
        r2 = java.lang.Math.abs(r2);
        r4 = 4502148214488346440; // 0x3e7ad7f29abcaf48 float:-7.803816E-23 double:1.0E-7;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 >= 0) goto L_0x00eb;
    L_0x008e:
        r2 = 1;
    L_0x008f:
        r3 = r16.getMapZoomer();
        r4 = (double) r3;
        r6 = java.lang.Math.floor(r6);
        r4 = r4 + r6;
        r3 = (float) r4;
        r3 = com.amap.api.mapcore.util.Util.m2337a(r3);
        r18 = 4591870180066957722; // 0x3fb999999999999a float:-1.5881868E-23 double:0.1;
    L_0x00a3:
        r4 = (double) r3;
        r4 = r4 + r18;
        r3 = (float) r4;
        r15 = com.amap.api.mapcore.util.Util.m2337a(r3);
        r0 = r16;
        r0.setMapZoomer(r15);
        r16.recalculate();
        r0 = r20;
        r3 = r0.f960f;
        r0 = r21;
        r4 = r0.latitude;
        r0 = r21;
        r6 = r0.longitude;
        r3.getLatLng2Pixel(r4, r6, r8);
        r0 = r20;
        r9 = r0.f960f;
        r0 = r22;
        r10 = r0.latitude;
        r0 = r22;
        r12 = r0.longitude;
        r9.getLatLng2Pixel(r10, r12, r14);
        r3 = r8.f4562x;
        r4 = r14.f4562x;
        r3 = r3 - r4;
        r4 = (double) r3;
        r3 = r14.f4563y;
        r6 = r8.f4563y;
        r3 = r3 - r6;
        r6 = (double) r3;
        if (r2 == 0) goto L_0x00ed;
    L_0x00df:
        r0 = r23;
        r6 = (double) r0;
        r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r3 < 0) goto L_0x00f4;
    L_0x00e6:
        r2 = (double) r15;
        r2 = r2 - r18;
        r15 = (float) r2;
    L_0x00ea:
        return r15;
    L_0x00eb:
        r2 = 0;
        goto L_0x008f;
    L_0x00ed:
        r0 = r24;
        r4 = (double) r0;
        r3 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1));
        if (r3 >= 0) goto L_0x00e6;
    L_0x00f4:
        r3 = com.amap.api.mapcore.util.ConfigableConst.f2126f;
        r3 = (r15 > r3 ? 1 : (r15 == r3 ? 0 : -1));
        if (r3 >= 0) goto L_0x00ea;
    L_0x00fa:
        r3 = r15;
        goto L_0x00a3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.AMapCallback.m1492a(com.amap.api.maps.model.LatLng, com.amap.api.maps.model.LatLng, int, int):float");
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8464a(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) throws RemoteException {
        MapCore mapCore = this.f960f.getMapCore();
        MapProjection mapProjection = this.f960f.getMapProjection();
        mapProjection.recalculate();
        cameraUpdateFactoryDelegate.zoom = this.f960f.checkZoomLevel(cameraUpdateFactoryDelegate.zoom);
        float checkZoomLevel;
        switch (C0751b.f1295a[cameraUpdateFactoryDelegate.nowType.ordinal()]) {
            case 1:
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    m1497a(mapProjection, cameraUpdateFactoryDelegate.geoPoint);
                } else {
                    mapProjection.setGeoCenter(cameraUpdateFactoryDelegate.geoPoint.f4562x, cameraUpdateFactoryDelegate.geoPoint.f4563y);
                }
                mapCore.setMapstate(mapProjection);
                return;
            case 2:
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    m1504d(mapProjection, cameraUpdateFactoryDelegate);
                } else {
                    mapProjection.setMapAngle(cameraUpdateFactoryDelegate.bearing);
                }
                mapCore.setMapstate(mapProjection);
                return;
            case 3:
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    m1500a(mapProjection, cameraUpdateFactoryDelegate);
                } else {
                    mapProjection.setMapAngle(cameraUpdateFactoryDelegate.bearing);
                    mapProjection.setGeoCenter(cameraUpdateFactoryDelegate.geoPoint.f4562x, cameraUpdateFactoryDelegate.geoPoint.f4563y);
                }
                mapCore.setMapstate(mapProjection);
                return;
            case 4:
                cameraUpdateFactoryDelegate.tilt = Util.m2338a(cameraUpdateFactoryDelegate.tilt, mapProjection.getMapZoomer());
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    m1503c(mapProjection, cameraUpdateFactoryDelegate);
                } else {
                    mapProjection.setCameraHeaderAngle(cameraUpdateFactoryDelegate.tilt);
                }
                mapCore.setMapstate(mapProjection);
                return;
            case 5:
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    m1501b(mapProjection, cameraUpdateFactoryDelegate);
                } else {
                    mapProjection.setGeoCenter(cameraUpdateFactoryDelegate.geoPoint.f4562x, cameraUpdateFactoryDelegate.geoPoint.f4563y);
                    mapProjection.setMapZoomer(cameraUpdateFactoryDelegate.zoom);
                }
                mapCore.setMapstate(mapProjection);
                return;
            case 6:
                LatLng latLng = cameraUpdateFactoryDelegate.cameraPosition.target;
                IPoint iPoint = new IPoint();
                MapProjection.lonlat2Geo(latLng.longitude, latLng.latitude, iPoint);
                float a = Util.m2337a(cameraUpdateFactoryDelegate.cameraPosition.zoom);
                float f = cameraUpdateFactoryDelegate.cameraPosition.bearing;
                float a2 = Util.m2338a(cameraUpdateFactoryDelegate.cameraPosition.tilt, a);
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    m1498a(mapProjection, iPoint, a, f, a2);
                } else {
                    mapProjection.setGeoCenter(iPoint.f4562x, iPoint.f4563y);
                    mapProjection.setMapZoomer(a);
                    mapProjection.setMapAngle(f);
                    mapProjection.setCameraHeaderAngle(a2);
                }
                mapCore.setMapstate(mapProjection);
                return;
            case 7:
                checkZoomLevel = this.f960f.checkZoomLevel(mapProjection.getMapZoomer() + 1.0f);
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    m1495a(mapProjection, checkZoomLevel);
                } else {
                    mapProjection.setMapZoomer(checkZoomLevel);
                }
                mapCore.setMapstate(mapProjection);
                return;
            case 8:
                checkZoomLevel = this.f960f.checkZoomLevel(mapProjection.getMapZoomer() - 1.0f);
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    m1495a(mapProjection, checkZoomLevel);
                } else {
                    mapProjection.setMapZoomer(checkZoomLevel);
                }
                mapProjection.setMapZoomer(checkZoomLevel);
                mapCore.setMapstate(mapProjection);
                return;
            case 9:
                checkZoomLevel = cameraUpdateFactoryDelegate.zoom;
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    m1495a(mapProjection, checkZoomLevel);
                } else {
                    mapProjection.setMapZoomer(checkZoomLevel);
                }
                mapCore.setMapstate(mapProjection);
                return;
            case 10:
                checkZoomLevel = this.f960f.checkZoomLevel(mapProjection.getMapZoomer() + cameraUpdateFactoryDelegate.amount);
                Point point = cameraUpdateFactoryDelegate.focus;
                if (point != null) {
                    m1496a(mapProjection, checkZoomLevel, point.x, point.y);
                } else if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    m1495a(mapProjection, checkZoomLevel);
                } else {
                    mapProjection.setMapZoomer(checkZoomLevel);
                }
                mapCore.setMapstate(mapProjection);
                return;
            case 11:
                checkZoomLevel = cameraUpdateFactoryDelegate.xPixel;
                checkZoomLevel += ((float) this.f960f.mo9173c()) / 2.0f;
                float d = cameraUpdateFactoryDelegate.yPixel + (((float) this.f960f.mo9174d()) / 2.0f);
                IPoint iPoint2 = new IPoint();
                this.f960f.getPixel2Geo((int) checkZoomLevel, (int) d, iPoint2);
                mapProjection.setGeoCenter(iPoint2.f4562x, iPoint2.f4563y);
                mapCore.setMapstate(mapProjection);
                return;
            case 12:
                cameraUpdateFactoryDelegate.nowType = Type.newLatLngBoundsWithSize;
                cameraUpdateFactoryDelegate.width = this.f960f.mo9173c();
                cameraUpdateFactoryDelegate.height = this.f960f.mo9174d();
                m1502b(cameraUpdateFactoryDelegate);
                return;
            case 13:
                m1502b(cameraUpdateFactoryDelegate);
                return;
            case 14:
                cameraUpdateFactoryDelegate.tilt = Util.m2338a(cameraUpdateFactoryDelegate.tilt, cameraUpdateFactoryDelegate.zoom);
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    m1498a(mapProjection, cameraUpdateFactoryDelegate.geoPoint, cameraUpdateFactoryDelegate.zoom, cameraUpdateFactoryDelegate.bearing, cameraUpdateFactoryDelegate.tilt);
                } else {
                    mapProjection.setGeoCenter(cameraUpdateFactoryDelegate.geoPoint.f4562x, cameraUpdateFactoryDelegate.geoPoint.f4563y);
                    mapProjection.setMapZoomer(cameraUpdateFactoryDelegate.zoom);
                    mapProjection.setMapAngle(cameraUpdateFactoryDelegate.bearing);
                    mapProjection.setCameraHeaderAngle(cameraUpdateFactoryDelegate.tilt);
                }
                mapCore.setMapstate(mapProjection);
                return;
            default:
                mapCore.setMapstate(mapProjection);
                return;
        }
    }

    /* renamed from: a */
    private void m1500a(MapProjection mapProjection, CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) {
        mapProjection.setMapAngle(cameraUpdateFactoryDelegate.bearing);
        m1497a(mapProjection, cameraUpdateFactoryDelegate.geoPoint);
    }

    /* renamed from: a */
    private void m1495a(MapProjection mapProjection, float f) {
        m1496a(mapProjection, f, this.f962h, this.f963i);
    }

    /* renamed from: a */
    private void m1496a(MapProjection mapProjection, float f, int i, int i2) {
        IPoint a = m1494a(mapProjection, i, i2);
        mapProjection.setMapZoomer(f);
        m1499a(mapProjection, a, i, i2);
    }

    /* renamed from: a */
    private void m1498a(MapProjection mapProjection, IPoint iPoint, float f, float f2, float f3) {
        mapProjection.setMapZoomer(f);
        mapProjection.setMapAngle(f2);
        mapProjection.setCameraHeaderAngle(f3);
        m1497a(mapProjection, iPoint);
    }

    /* renamed from: b */
    private void m1501b(MapProjection mapProjection, CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) {
        mapProjection.setMapZoomer(cameraUpdateFactoryDelegate.zoom);
        m1497a(mapProjection, cameraUpdateFactoryDelegate.geoPoint);
    }

    /* renamed from: c */
    private void m1503c(MapProjection mapProjection, CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) {
        IPoint a = m1493a(mapProjection);
        mapProjection.setCameraHeaderAngle(cameraUpdateFactoryDelegate.tilt);
        m1497a(mapProjection, a);
    }

    /* renamed from: d */
    private void m1504d(MapProjection mapProjection, CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) {
        IPoint a = m1493a(mapProjection);
        mapProjection.setMapAngle(cameraUpdateFactoryDelegate.bearing);
        m1497a(mapProjection, a);
    }

    /* renamed from: a */
    private void m1497a(MapProjection mapProjection, IPoint iPoint) {
        m1499a(mapProjection, iPoint, this.f962h, this.f963i);
    }

    /* renamed from: a */
    private void m1499a(MapProjection mapProjection, IPoint iPoint, int i, int i2) {
        mapProjection.recalculate();
        IPoint a = m1494a(mapProjection, i, i2);
        IPoint iPoint2 = new IPoint();
        mapProjection.getGeoCenter(iPoint2);
        mapProjection.setGeoCenter((iPoint2.f4562x + iPoint.f4562x) - a.f4562x, (iPoint2.f4563y + iPoint.f4563y) - a.f4563y);
    }

    /* renamed from: a */
    private IPoint m1493a(MapProjection mapProjection) {
        return m1494a(mapProjection, this.f962h, this.f963i);
    }

    /* renamed from: a */
    private IPoint m1494a(MapProjection mapProjection, int i, int i2) {
        FPoint fPoint = new FPoint();
        mapProjection.win2Map(i, i2, fPoint);
        IPoint iPoint = new IPoint();
        mapProjection.map2Geo(fPoint.f4560x, fPoint.f4561y, iPoint);
        return iPoint;
    }

    public void OnMapDestory(GL10 gl10, MapCore mapCore) {
        super.OnMapDestory(mapCore);
    }

    public void OnMapReferencechanged(MapCore mapCore, String str, String str2) {
        try {
            if (this.f960f.getUiSettings().isCompassEnabled()) {
                this.f960f.mo9163a();
            }
            if (this.f960f.getUiSettings().isScaleControlsEnabled()) {
                this.f960f.mo9172b();
            }
            this.f960f.mo9170a(true);
        } catch (RemoteException e) {
            SDKLogHandler.m2563a(e, "AMapCallback", "OnMapReferencechanged");
            e.printStackTrace();
        }
        this.f960f.mo9176f();
    }

    public Context getContext() {
        return this.f960f.mo9177g();
    }

    public boolean isMapEngineValid() {
        if (this.f960f.getMapCore() != null) {
            return this.f960f.getMapCore().isMapEngineValid();
        }
        return false;
    }

    public void OnMapLoaderError(int i) {
    }

    /* renamed from: a */
    public void mo8462a(int i, int i2) {
        this.f962h = i;
        this.f963i = i2;
    }

    public void requestRender() {
        this.f960f.setRunLowFrame(false);
    }

    public void onIndoorBuildingActivity(MapCore mapCore, byte[] bArr) {
        IndoorBuilding indoorBuilding = null;
        if (bArr != null) {
            try {
                IndoorBuilding indoorBuilding2 = new IndoorBuilding();
                byte b = bArr[0];
                indoorBuilding2.name_cn = new String(bArr, 1, b);
                int i = b + 1;
                int i2 = i + 1;
                b = bArr[i];
                indoorBuilding2.name_en = new String(bArr, i2, b);
                i = b + i2;
                i2 = i + 1;
                b = bArr[i];
                indoorBuilding2.activeFloorName = new String(bArr, i2, b);
                i = b + i2;
                indoorBuilding2.activeFloorIndex = Convert.getInt(bArr, i);
                i += 4;
                i2 = i + 1;
                b = bArr[i];
                indoorBuilding2.poiid = new String(bArr, i2, b);
                i = b + i2;
                indoorBuilding2.numberofFloor = Convert.getInt(bArr, i);
                i += 4;
                indoorBuilding2.floor_indexs = new int[indoorBuilding2.numberofFloor];
                indoorBuilding2.floor_names = new String[indoorBuilding2.numberofFloor];
                indoorBuilding2.floor_nonas = new String[indoorBuilding2.numberofFloor];
                for (int i3 = 0; i3 < indoorBuilding2.numberofFloor; i3++) {
                    indoorBuilding2.floor_indexs[i3] = Convert.getInt(bArr, i);
                    i2 = i + 4;
                    i = i2 + 1;
                    byte b2 = bArr[i2];
                    if (b2 > (byte) 0) {
                        indoorBuilding2.floor_names[i3] = new String(bArr, i, b2);
                        i2 = i + b2;
                    } else {
                        i2 = i;
                    }
                    i = i2 + 1;
                    b2 = bArr[i2];
                    if (b2 > (byte) 0) {
                        indoorBuilding2.floor_nonas[i3] = new String(bArr, i, b2);
                        i += b2;
                    }
                }
                indoorBuilding2.numberofParkFloor = Convert.getInt(bArr, i);
                i += 4;
                if (indoorBuilding2.numberofParkFloor > 0) {
                    indoorBuilding2.park_floor_indexs = new int[indoorBuilding2.numberofParkFloor];
                    int i4 = i;
                    for (i = 0; i < indoorBuilding2.numberofParkFloor; i++) {
                        indoorBuilding2.park_floor_indexs[i] = Convert.getInt(bArr, i4);
                        i4 += 4;
                    }
                }
                indoorBuilding = indoorBuilding2;
            } catch (Throwable th) {
                th.printStackTrace();
                SDKLogHandler.m2563a(th, "AMapCallback", "onIndoorBuildingActivity");
                return;
            }
        }
        this.f960f.mo9165a(indoorBuilding);
    }

    public void onIndoorDataRequired(MapCore mapCore, int i, String[] strArr, int[] iArr, int[] iArr2) {
        if (strArr != null && strArr.length != 0) {
            ArrayList reqGridList = getReqGridList(i);
            if (reqGridList != null) {
                reqGridList.clear();
                for (int i2 = 0; i2 < strArr.length; i2++) {
                    reqGridList.add(new MapSourceGridData(strArr[i2], i, iArr[i2], iArr2[i2]));
                }
                if (i != 5) {
                    proccessRequiredData(mapCore, reqGridList, i);
                }
            }
        }
    }
}
