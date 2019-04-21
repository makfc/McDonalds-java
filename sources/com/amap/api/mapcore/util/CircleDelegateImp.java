package com.amap.api.mapcore.util;

import android.os.RemoteException;
import android.support.p000v4.view.ViewCompat;
import android.util.Log;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.ICircleDelegate;
import com.autonavi.amap.mapcore.interfaces.IOverlayDelegate;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.util.p */
class CircleDelegateImp implements ICircleDelegate {
    /* renamed from: m */
    private static float f2095m = 4.0075016E7f;
    /* renamed from: n */
    private static int f2096n = 256;
    /* renamed from: o */
    private static int f2097o = 20;
    /* renamed from: a */
    private LatLng f2098a = null;
    /* renamed from: b */
    private double f2099b = 0.0d;
    /* renamed from: c */
    private float f2100c = 10.0f;
    /* renamed from: d */
    private int f2101d = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: e */
    private int f2102e = 0;
    /* renamed from: f */
    private float f2103f = 0.0f;
    /* renamed from: g */
    private boolean f2104g = true;
    /* renamed from: h */
    private String f2105h;
    /* renamed from: i */
    private IAMapDelegate f2106i;
    /* renamed from: j */
    private FloatBuffer f2107j;
    /* renamed from: k */
    private int f2108k = 0;
    /* renamed from: l */
    private boolean f2109l = false;

    public CircleDelegateImp(IAMapDelegate iAMapDelegate) {
        this.f2106i = iAMapDelegate;
        try {
            this.f2105h = getId();
        } catch (RemoteException e) {
            SDKLogHandler.m2563a(e, "CircleDelegateImp", "create");
            e.printStackTrace();
        }
    }

    public boolean checkInBounds() {
        return true;
    }

    public void remove() throws RemoteException {
        this.f2106i.removeGLOverlay(getId());
        this.f2106i.setRunLowFrame(false);
    }

    public String getId() throws RemoteException {
        if (this.f2105h == null) {
            this.f2105h = GLOverlayLayer.m2918a("Circle");
        }
        return this.f2105h;
    }

    public void setZIndex(float f) throws RemoteException {
        this.f2103f = f;
        this.f2106i.changeGLOverlayIndex();
        this.f2106i.setRunLowFrame(false);
    }

    public float getZIndex() throws RemoteException {
        return this.f2103f;
    }

    public void setVisible(boolean z) throws RemoteException {
        this.f2104g = z;
        this.f2106i.setRunLowFrame(false);
    }

    public boolean isVisible() throws RemoteException {
        return this.f2104g;
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
        this.f2109l = false;
        LatLng latLng = this.f2098a;
        if (latLng != null) {
            FPoint[] fPointArr = new FPoint[360];
            float[] fArr = new float[(fPointArr.length * 3)];
            double b = m2892b(this.f2098a.latitude) * this.f2099b;
            IPoint iPoint = new IPoint();
            MapProjection mapProjection = this.f2106i.getMapProjection();
            MapProjection.lonlat2Geo(latLng.longitude, latLng.latitude, iPoint);
            while (i < 360) {
                double d = (((double) i) * 3.141592653589793d) / 180.0d;
                double sin = Math.sin(d) * b;
                int i2 = (int) (sin + ((double) iPoint.f4562x));
                int cos = (int) ((Math.cos(d) * b) + ((double) iPoint.f4563y));
                FPoint fPoint = new FPoint();
                mapProjection.geo2Map(i2, cos, fPoint);
                fPointArr[i] = fPoint;
                fArr[i * 3] = fPointArr[i].f4560x;
                fArr[(i * 3) + 1] = fPointArr[i].f4561y;
                fArr[(i * 3) + 2] = 0.0f;
                i++;
            }
            this.f2108k = fPointArr.length;
            this.f2107j = Util.m2355a(fArr);
        }
    }

    public void draw(GL10 gl10) throws RemoteException {
        if (this.f2098a != null && this.f2099b > 0.0d && this.f2104g) {
            if (this.f2107j == null || this.f2108k == 0) {
                calMapFPoint();
            }
            if (this.f2107j != null && this.f2108k > 0) {
                GLESUtility.m2904b(gl10, this.f2102e, this.f2101d, this.f2107j, this.f2100c, this.f2108k);
            }
            this.f2109l = true;
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo9536a() {
        this.f2108k = 0;
        if (this.f2107j != null) {
            this.f2107j.clear();
        }
        this.f2106i.setRunLowFrame(false);
    }

    public void setCenter(LatLng latLng) throws RemoteException {
        this.f2098a = latLng;
        mo9536a();
    }

    public LatLng getCenter() throws RemoteException {
        return this.f2098a;
    }

    public void setRadius(double d) throws RemoteException {
        this.f2099b = d;
        mo9536a();
    }

    public double getRadius() throws RemoteException {
        return this.f2099b;
    }

    public void setStrokeWidth(float f) throws RemoteException {
        this.f2100c = f;
        this.f2106i.setRunLowFrame(false);
    }

    public float getStrokeWidth() throws RemoteException {
        return this.f2100c;
    }

    public void setStrokeColor(int i) throws RemoteException {
        this.f2101d = i;
    }

    public int getStrokeColor() throws RemoteException {
        return this.f2101d;
    }

    public void setFillColor(int i) throws RemoteException {
        this.f2102e = i;
        this.f2106i.setRunLowFrame(false);
    }

    public int getFillColor() throws RemoteException {
        return this.f2102e;
    }

    /* renamed from: a */
    private float m2891a(double d) {
        return (float) ((Math.cos((3.141592653589793d * d) / 180.0d) * ((double) f2095m)) / ((double) (f2096n << f2097o)));
    }

    /* renamed from: b */
    private double m2892b(double d) {
        return 1.0d / ((double) m2891a(d));
    }

    public void destroy() {
        try {
            this.f2098a = null;
            if (this.f2107j != null) {
                this.f2107j.clear();
                this.f2107j = null;
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "CircleDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "CircleDelegateImp destroy");
        }
    }

    public boolean contains(LatLng latLng) throws RemoteException {
        if (this.f2099b >= ((double) AMapUtils.calculateLineDistance(this.f2098a, latLng))) {
            return true;
        }
        return false;
    }

    public boolean isDrawFinish() {
        return this.f2109l;
    }
}
