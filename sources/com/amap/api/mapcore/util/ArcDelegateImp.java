package com.amap.api.mapcore.util;

import android.graphics.Color;
import android.os.RemoteException;
import android.support.p000v4.view.ViewCompat;
import android.util.Log;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.AMapNativeRenderer;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IArcDelegate;
import com.autonavi.amap.mapcore.interfaces.IOverlayDelegate;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.util.n */
class ArcDelegateImp implements IArcDelegate {
    /* renamed from: a */
    float f2075a;
    /* renamed from: b */
    float f2076b;
    /* renamed from: c */
    float f2077c;
    /* renamed from: d */
    float f2078d;
    /* renamed from: e */
    private LatLng f2079e;
    /* renamed from: f */
    private LatLng f2080f;
    /* renamed from: g */
    private LatLng f2081g;
    /* renamed from: h */
    private float f2082h = 10.0f;
    /* renamed from: i */
    private int f2083i = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: j */
    private float f2084j = 0.0f;
    /* renamed from: k */
    private boolean f2085k = true;
    /* renamed from: l */
    private String f2086l;
    /* renamed from: m */
    private IAMapDelegate f2087m;
    /* renamed from: n */
    private float[] f2088n;
    /* renamed from: o */
    private int f2089o = 0;
    /* renamed from: p */
    private boolean f2090p = false;
    /* renamed from: q */
    private double f2091q = 0.0d;
    /* renamed from: r */
    private double f2092r = 0.0d;
    /* renamed from: s */
    private double f2093s = 0.0d;

    public ArcDelegateImp(IAMapDelegate iAMapDelegate) {
        this.f2087m = iAMapDelegate;
        try {
            this.f2086l = getId();
        } catch (RemoteException e) {
            SDKLogHandler.m2563a(e, "ArcDelegateImp", "create");
            e.printStackTrace();
        }
    }

    public boolean checkInBounds() {
        return true;
    }

    public void remove() throws RemoteException {
        this.f2087m.removeGLOverlay(getId());
        this.f2087m.setRunLowFrame(false);
    }

    public String getId() throws RemoteException {
        if (this.f2086l == null) {
            this.f2086l = GLOverlayLayer.m2918a("Arc");
        }
        return this.f2086l;
    }

    public void setZIndex(float f) throws RemoteException {
        this.f2084j = f;
        this.f2087m.changeGLOverlayIndex();
        this.f2087m.setRunLowFrame(false);
    }

    public float getZIndex() throws RemoteException {
        return this.f2084j;
    }

    public void setVisible(boolean z) throws RemoteException {
        this.f2085k = z;
        this.f2087m.setRunLowFrame(false);
    }

    public boolean isVisible() throws RemoteException {
        return this.f2085k;
    }

    public boolean equalsRemote(IOverlayDelegate iOverlayDelegate) throws RemoteException {
        if (equals(iOverlayDelegate) || iOverlayDelegate.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    public int hashCodeRemote() throws RemoteException {
        return 0;
    }

    public void calMapFPoint() throws RemoteException {
        int i = 0;
        if (this.f2079e != null && this.f2080f != null && this.f2081g != null && this.f2085k) {
            try {
                this.f2090p = false;
                MapProjection mapProjection = this.f2087m.getMapProjection();
                FPoint fPoint;
                if (m2888a()) {
                    DPoint b = m2889b();
                    int abs = (int) ((Math.abs(this.f2093s - this.f2092r) * 180.0d) / 3.141592653589793d);
                    double d = (this.f2093s - this.f2092r) / ((double) abs);
                    FPoint[] fPointArr = new FPoint[(abs + 1)];
                    this.f2088n = new float[(fPointArr.length * 3)];
                    for (int i2 = 0; i2 <= abs; i2++) {
                        MapProjection mapProjection2;
                        if (i2 == abs) {
                            fPoint = new FPoint();
                            this.f2087m.getLatLng2Map(this.f2081g.latitude, this.f2081g.longitude, fPoint);
                            fPointArr[i2] = fPoint;
                        } else {
                            mapProjection2 = mapProjection;
                            fPointArr[i2] = m2887a(mapProjection2, (((double) i2) * d) + this.f2092r, b.f4558x, b.f4559y);
                        }
                        mapProjection2 = mapProjection;
                        fPointArr[i2] = m2887a(mapProjection2, (((double) i2) * d) + this.f2092r, b.f4558x, b.f4559y);
                        this.f2088n[i2 * 3] = fPointArr[i2].f4560x;
                        this.f2088n[(i2 * 3) + 1] = fPointArr[i2].f4561y;
                        this.f2088n[(i2 * 3) + 2] = 0.0f;
                    }
                    this.f2089o = fPointArr.length;
                    return;
                }
                FPoint[] fPointArr2 = new FPoint[3];
                this.f2088n = new float[(fPointArr2.length * 3)];
                fPoint = new FPoint();
                this.f2087m.getLatLng2Map(this.f2079e.latitude, this.f2079e.longitude, fPoint);
                fPointArr2[0] = fPoint;
                fPoint = new FPoint();
                this.f2087m.getLatLng2Map(this.f2080f.latitude, this.f2080f.longitude, fPoint);
                fPointArr2[1] = fPoint;
                fPoint = new FPoint();
                this.f2087m.getLatLng2Map(this.f2081g.latitude, this.f2081g.longitude, fPoint);
                fPointArr2[2] = fPoint;
                while (i < 3) {
                    this.f2088n[i * 3] = fPointArr2[i].f4560x;
                    this.f2088n[(i * 3) + 1] = fPointArr2[i].f4561y;
                    this.f2088n[(i * 3) + 2] = 0.0f;
                    i++;
                }
                this.f2089o = fPointArr2.length;
            } catch (Throwable th) {
                SDKLogHandler.m2563a(th, "ArcDelegateImp", "calMapFPoint");
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private FPoint m2887a(MapProjection mapProjection, double d, double d2, double d3) {
        int cos = (int) ((Math.cos(d) * this.f2091q) + d2);
        int i = (int) (((-Math.sin(d)) * this.f2091q) + d3);
        FPoint fPoint = new FPoint();
        mapProjection.geo2Map(cos, i, fPoint);
        return fPoint;
    }

    /* renamed from: a */
    private boolean m2888a() {
        if (Math.abs(((this.f2079e.latitude - this.f2080f.latitude) * (this.f2080f.longitude - this.f2081g.longitude)) - ((this.f2079e.longitude - this.f2080f.longitude) * (this.f2080f.latitude - this.f2081g.latitude))) < 1.0E-6d) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    private DPoint m2889b() {
        IPoint iPoint = new IPoint();
        this.f2087m.latlon2Geo(this.f2079e.latitude, this.f2079e.longitude, iPoint);
        IPoint iPoint2 = new IPoint();
        this.f2087m.latlon2Geo(this.f2080f.latitude, this.f2080f.longitude, iPoint2);
        IPoint iPoint3 = new IPoint();
        this.f2087m.latlon2Geo(this.f2081g.latitude, this.f2081g.longitude, iPoint3);
        double d = (double) iPoint.f4562x;
        double d2 = (double) iPoint.f4563y;
        double d3 = (double) iPoint2.f4562x;
        double d4 = (double) iPoint2.f4563y;
        double d5 = (double) iPoint3.f4562x;
        double d6 = (double) iPoint3.f4563y;
        double d7 = (((d6 - d2) * ((((d4 * d4) - (d2 * d2)) + (d3 * d3)) - (d * d))) + ((d4 - d2) * ((((d2 * d2) - (d6 * d6)) + (d * d)) - (d5 * d5)))) / (((2.0d * (d3 - d)) * (d6 - d2)) - ((2.0d * (d5 - d)) * (d4 - d2)));
        double d8 = (((d5 - d) * ((((d3 * d3) - (d * d)) + (d4 * d4)) - (d2 * d2))) + ((d3 - d) * ((((d * d) - (d5 * d5)) + (d2 * d2)) - (d6 * d6)))) / (((2.0d * (d4 - d2)) * (d5 - d)) - ((2.0d * (d6 - d2)) * (d3 - d)));
        this.f2091q = Math.sqrt(((d - d7) * (d - d7)) + ((d2 - d8) * (d2 - d8)));
        this.f2092r = m2886a(d7, d8, d, d2);
        d = m2886a(d7, d8, d3, d4);
        this.f2093s = m2886a(d7, d8, d5, d6);
        if (this.f2092r < this.f2093s) {
            if (d <= this.f2092r || d >= this.f2093s) {
                this.f2093s -= 6.283185307179586d;
            }
        } else if (d <= this.f2093s || d >= this.f2092r) {
            this.f2093s += 6.283185307179586d;
        }
        return new DPoint(d7, d8);
    }

    /* renamed from: a */
    private double m2886a(double d, double d2, double d3, double d4) {
        double d5 = (d2 - d4) / this.f2091q;
        if (Math.abs(d5) > 1.0d) {
            d5 = Math.signum(d5);
        }
        d5 = Math.asin(d5);
        if (d5 >= 0.0d) {
            if (d3 < d) {
                return 3.141592653589793d - Math.abs(d5);
            }
            return d5;
        } else if (d3 < d) {
            return 3.141592653589793d - d5;
        } else {
            return d5 + 6.283185307179586d;
        }
    }

    public void draw(GL10 gl10) throws RemoteException {
        if (this.f2079e != null && this.f2080f != null && this.f2081g != null && this.f2085k) {
            if (this.f2088n == null || this.f2089o == 0) {
                calMapFPoint();
            }
            if (this.f2088n != null && this.f2089o > 0) {
                float mapLenWithWin = this.f2087m.getMapProjection().getMapLenWithWin((int) this.f2082h);
                this.f2087m.getMapProjection().getMapLenWithWin(1);
                AMapNativeRenderer.nativeDrawLineByTextureID(this.f2088n, this.f2088n.length, mapLenWithWin, this.f2087m.getLineTextureID(), this.f2076b, this.f2077c, this.f2078d, this.f2075a, 0.0f, false, true, false);
            }
            this.f2090p = true;
        }
    }

    public void setStrokeWidth(float f) throws RemoteException {
        this.f2082h = f;
        this.f2087m.setRunLowFrame(false);
    }

    public float getStrokeWidth() throws RemoteException {
        return this.f2082h;
    }

    public void setStrokeColor(int i) throws RemoteException {
        this.f2083i = i;
        this.f2075a = ((float) Color.alpha(i)) / 255.0f;
        this.f2076b = ((float) Color.red(i)) / 255.0f;
        this.f2077c = ((float) Color.green(i)) / 255.0f;
        this.f2078d = ((float) Color.blue(i)) / 255.0f;
        this.f2087m.setRunLowFrame(false);
    }

    public int getStrokeColor() throws RemoteException {
        return this.f2083i;
    }

    public void destroy() {
        try {
            this.f2079e = null;
            this.f2080f = null;
            this.f2081g = null;
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "ArcDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "ArcDelegateImp destroy");
        }
    }

    public boolean isDrawFinish() {
        return this.f2090p;
    }

    public void setStart(LatLng latLng) {
        this.f2079e = latLng;
    }

    public void setPassed(LatLng latLng) {
        this.f2080f = latLng;
    }

    public void setEnd(LatLng latLng) {
        this.f2081g = latLng;
    }
}
