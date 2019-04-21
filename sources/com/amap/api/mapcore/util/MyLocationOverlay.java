package com.amap.api.mapcore.util;

import android.content.Context;
import android.location.Location;
import android.os.RemoteException;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.ICircleDelegate;

/* renamed from: com.amap.api.mapcore.util.aj */
class MyLocationOverlay {
    /* renamed from: a */
    private IAMapDelegate f1046a;
    /* renamed from: b */
    private Marker f1047b;
    /* renamed from: c */
    private ICircleDelegate f1048c;
    /* renamed from: d */
    private MyLocationStyle f1049d;
    /* renamed from: e */
    private LatLng f1050e;
    /* renamed from: f */
    private double f1051f;
    /* renamed from: g */
    private Context f1052g;
    /* renamed from: h */
    private SensorEventHelper f1053h;
    /* renamed from: i */
    private int f1054i = 1;
    /* renamed from: j */
    private boolean f1055j = false;
    /* renamed from: k */
    private final String f1056k = "location_map_gps_locked.png";
    /* renamed from: l */
    private final String f1057l = "location_map_gps_3d.png";
    /* renamed from: m */
    private boolean f1058m = false;

    MyLocationOverlay(IAMapDelegate iAMapDelegate, Context context) {
        this.f1052g = context;
        this.f1046a = iAMapDelegate;
        this.f1053h = new SensorEventHelper(this.f1052g, iAMapDelegate);
    }

    /* renamed from: a */
    public void mo8594a(MyLocationStyle myLocationStyle) {
        try {
            this.f1049d = myLocationStyle;
            if (this.f1047b != null || this.f1048c != null) {
                m1570k();
                this.f1053h.mo8679a(this.f1047b);
                m1569j();
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "MyLocationOverlay", "setMyLocationStyle");
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo8592a(int i) {
        this.f1054i = i;
        this.f1055j = false;
        switch (this.f1054i) {
            case 1:
                m1565f();
                return;
            case 2:
                m1566g();
                return;
            case 3:
                m1567h();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void mo8590a() {
        if (this.f1054i == 3 && this.f1053h != null) {
            this.f1053h.mo8678a();
        }
    }

    /* renamed from: f */
    private void m1565f() {
        if (this.f1047b != null) {
            m1564c(0.0f);
            this.f1053h.mo8680b();
            if (!this.f1058m) {
                this.f1047b.setIcon(BitmapDescriptorFactory.fromAsset("location_map_gps_locked.png"));
            }
            this.f1047b.setFlat(false);
            m1562b(0.0f);
        }
    }

    /* renamed from: g */
    private void m1566g() {
        if (this.f1047b != null) {
            m1564c(0.0f);
            this.f1053h.mo8680b();
            if (!this.f1058m) {
                this.f1047b.setIcon(BitmapDescriptorFactory.fromAsset("location_map_gps_locked.png"));
            }
            this.f1047b.setFlat(false);
            m1562b(0.0f);
        }
    }

    /* renamed from: h */
    private void m1567h() {
        if (this.f1047b != null) {
            this.f1047b.setRotateAngle(0.0f);
            this.f1053h.mo8678a();
            if (!this.f1058m) {
                this.f1047b.setIcon(BitmapDescriptorFactory.fromAsset("location_map_gps_3d.png"));
            }
            this.f1047b.setFlat(true);
            try {
                this.f1046a.moveCamera(CameraUpdateFactoryDelegate.zoomTo(17.0f));
                m1562b(45.0f);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: b */
    private void m1562b(float f) {
        if (this.f1046a != null) {
            try {
                this.f1046a.moveCamera(CameraUpdateFactoryDelegate.changeTilt(f));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: c */
    private void m1564c(float f) {
        if (this.f1046a != null) {
            try {
                this.f1046a.moveCamera(CameraUpdateFactoryDelegate.changeBearing(f));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public void mo8593a(Location location) {
        if (location != null) {
            this.f1050e = new LatLng(location.getLatitude(), location.getLongitude());
            this.f1051f = (double) location.getAccuracy();
            if (this.f1047b == null && this.f1048c == null) {
                m1569j();
            }
            if (this.f1047b != null) {
                this.f1047b.setPosition(this.f1050e);
            }
            if (this.f1048c != null) {
                try {
                    this.f1048c.setCenter(this.f1050e);
                    if (this.f1051f != -1.0d) {
                        this.f1048c.setRadius(this.f1051f);
                    }
                } catch (RemoteException e) {
                    SDKLogHandler.m2563a(e, "MyLocationOverlay", "setCentAndRadius");
                    e.printStackTrace();
                }
                m1568i();
                if (this.f1054i != 3) {
                    m1563b(location);
                }
                this.f1055j = true;
            }
        }
    }

    /* renamed from: b */
    private void m1563b(Location location) {
        float bearing = location.getBearing() % 360.0f;
        if (bearing > 180.0f) {
            bearing -= 360.0f;
        } else if (bearing < -180.0f) {
            bearing += 360.0f;
        }
        if (this.f1047b != null) {
            this.f1047b.setRotateAngle(-bearing);
        }
    }

    /* renamed from: i */
    private void m1568i() {
        if (this.f1054i != 1 || !this.f1055j) {
            try {
                IPoint iPoint = new IPoint();
                MapProjection.lonlat2Geo(this.f1050e.longitude, this.f1050e.latitude, iPoint);
                this.f1046a.animateCamera(CameraUpdateFactoryDelegate.changeGeoCenter(iPoint));
            } catch (RemoteException e) {
                SDKLogHandler.m2563a(e, "MyLocationOverlay", "locaitonFollow");
                e.printStackTrace();
            }
        }
    }

    /* renamed from: j */
    private void m1569j() {
        if (this.f1049d == null) {
            this.f1049d = new MyLocationStyle();
            this.f1049d.myLocationIcon(BitmapDescriptorFactory.fromAsset("location_map_gps_locked.png"));
            m1571l();
            return;
        }
        this.f1058m = true;
        m1571l();
    }

    /* renamed from: b */
    public void mo8595b() throws RemoteException {
        m1570k();
        if (this.f1053h != null) {
            this.f1053h.mo8680b();
            this.f1053h = null;
        }
    }

    /* renamed from: k */
    private void m1570k() {
        if (this.f1048c != null) {
            try {
                this.f1046a.removeGLOverlay(this.f1048c.getId());
            } catch (RemoteException e) {
                SDKLogHandler.m2563a(e, "MyLocationOverlay", "locationIconRemove");
                e.printStackTrace();
            }
            this.f1048c = null;
        }
        if (this.f1047b != null) {
            this.f1047b.remove();
            this.f1047b.destroy();
            this.f1047b = null;
            this.f1053h.mo8679a(null);
        }
    }

    /* renamed from: l */
    private void m1571l() {
        try {
            this.f1048c = this.f1046a.addCircle(new CircleOptions().strokeWidth(this.f1049d.getStrokeWidth()).fillColor(this.f1049d.getRadiusFillColor()).strokeColor(this.f1049d.getStrokeColor()).center(new LatLng(0.0d, 0.0d)).zIndex(1.0f));
            if (this.f1050e != null) {
                this.f1048c.setCenter(this.f1050e);
            }
            this.f1048c.setRadius(this.f1051f);
            this.f1047b = this.f1046a.addMarker(new MarkerOptions().visible(false).anchor(this.f1049d.getAnchorU(), this.f1049d.getAnchorV()).icon(this.f1049d.getMyLocationIcon()).position(new LatLng(0.0d, 0.0d)));
            mo8592a(this.f1054i);
            if (this.f1050e != null) {
                this.f1047b.setPosition(this.f1050e);
                this.f1047b.setVisible(true);
            }
            this.f1053h.mo8679a(this.f1047b);
        } catch (RemoteException e) {
            SDKLogHandler.m2563a(e, "MyLocationOverlay", "myLocStyle");
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo8591a(float f) {
        if (this.f1047b != null) {
            this.f1047b.setRotateAngle(f);
        }
    }

    /* renamed from: c */
    public String mo8596c() {
        if (this.f1047b != null) {
            return this.f1047b.getId();
        }
        return null;
    }

    /* renamed from: d */
    public String mo8597d() throws RemoteException {
        if (this.f1048c != null) {
            return this.f1048c.getId();
        }
        return null;
    }

    /* renamed from: e */
    public void mo8598e() {
        this.f1048c = null;
        this.f1047b = null;
    }
}
