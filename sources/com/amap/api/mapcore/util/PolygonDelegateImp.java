package com.amap.api.mapcore.util;

import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.services.poisearch.PoiSearch.SearchBound;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IOverlayDelegate;
import com.autonavi.amap.mapcore.interfaces.IPolygonDelegate;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.util.an */
class PolygonDelegateImp implements IPolygonDelegate {
    /* renamed from: u */
    private static float f1081u = 1.0E10f;
    /* renamed from: a */
    private IAMapDelegate f1082a;
    /* renamed from: b */
    private float f1083b = 0.0f;
    /* renamed from: c */
    private boolean f1084c = true;
    /* renamed from: d */
    private boolean f1085d;
    /* renamed from: e */
    private String f1086e;
    /* renamed from: f */
    private float f1087f;
    /* renamed from: g */
    private int f1088g;
    /* renamed from: h */
    private int f1089h;
    /* renamed from: i */
    private List<LatLng> f1090i;
    /* renamed from: j */
    private List<LatLng> f1091j;
    /* renamed from: k */
    private CopyOnWriteArrayList<IPoint> f1092k = new CopyOnWriteArrayList();
    /* renamed from: l */
    private List<FPoint> f1093l = new ArrayList();
    /* renamed from: m */
    private FloatBuffer f1094m;
    /* renamed from: n */
    private FloatBuffer f1095n;
    /* renamed from: o */
    private int f1096o = 0;
    /* renamed from: p */
    private int f1097p = 0;
    /* renamed from: q */
    private LatLngBounds f1098q = null;
    /* renamed from: r */
    private boolean f1099r = false;
    /* renamed from: s */
    private float f1100s = 0.0f;
    /* renamed from: t */
    private Object f1101t = new Object();

    public PolygonDelegateImp(IAMapDelegate iAMapDelegate) {
        this.f1082a = iAMapDelegate;
        try {
            this.f1086e = getId();
        } catch (RemoteException e) {
            SDKLogHandler.m2563a(e, "PolygonDelegateImp", "create");
            e.printStackTrace();
        }
    }

    public void remove() throws RemoteException {
        this.f1082a.removeGLOverlay(getId());
        this.f1082a.setRunLowFrame(false);
    }

    public String getId() throws RemoteException {
        if (this.f1086e == null) {
            this.f1086e = GLOverlayLayer.m2918a(SearchBound.POLYGON_SHAPE);
        }
        return this.f1086e;
    }

    public void setPoints(List<LatLng> list) throws RemoteException {
        synchronized (this.f1101t) {
            this.f1091j = list;
            mo8636a((List) list);
            calMapFPoint();
            this.f1082a.setRunLowFrame(false);
        }
    }

    public List<LatLng> getPoints() throws RemoteException {
        return this.f1091j;
    }

    public void setZIndex(float f) throws RemoteException {
        this.f1083b = f;
        this.f1082a.changeGLOverlayIndex();
        this.f1082a.setRunLowFrame(false);
    }

    public float getZIndex() throws RemoteException {
        return this.f1083b;
    }

    public void setVisible(boolean z) throws RemoteException {
        this.f1084c = z;
        this.f1082a.setRunLowFrame(false);
    }

    public boolean isVisible() throws RemoteException {
        return this.f1084c;
    }

    public boolean equalsRemote(IOverlayDelegate iOverlayDelegate) throws RemoteException {
        if (equals(iOverlayDelegate) || iOverlayDelegate.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8636a(List<LatLng> list) throws RemoteException {
        Builder builder = LatLngBounds.builder();
        this.f1092k.clear();
        if (list != null) {
            Object obj = null;
            for (LatLng latLng : list) {
                if (!latLng.equals(obj)) {
                    IPoint iPoint = new IPoint();
                    this.f1082a.latlon2Geo(latLng.latitude, latLng.longitude, iPoint);
                    this.f1092k.add(iPoint);
                    builder.include(latLng);
                    obj = latLng;
                }
            }
            int size = this.f1092k.size();
            if (size > 1) {
                IPoint iPoint2 = (IPoint) this.f1092k.get(0);
                IPoint iPoint3 = (IPoint) this.f1092k.get(size - 1);
                if (iPoint2.f4562x == iPoint3.f4562x && iPoint2.f4563y == iPoint3.f4563y) {
                    this.f1092k.remove(size - 1);
                }
            }
        }
        this.f1098q = builder.build();
        if (this.f1094m != null) {
            this.f1094m.clear();
        }
        if (this.f1095n != null) {
            this.f1095n.clear();
        }
        this.f1096o = 0;
        this.f1097p = 0;
        this.f1082a.setRunLowFrame(false);
    }

    public void calMapFPoint() throws RemoteException {
        synchronized (this.f1101t) {
            this.f1093l.clear();
            this.f1099r = false;
            Iterator it = this.f1092k.iterator();
            while (it.hasNext()) {
                IPoint iPoint = (IPoint) it.next();
                FPoint fPoint = new FPoint();
                this.f1082a.geo2Map(iPoint.f4563y, iPoint.f4562x, fPoint);
                this.f1093l.add(fPoint);
            }
            m1597b();
        }
    }

    public int hashCodeRemote() throws RemoteException {
        return super.hashCode();
    }

    public boolean checkInBounds() {
        if (this.f1098q == null) {
            return false;
        }
        LatLngBounds mapBounds = this.f1082a.getMapBounds();
        if (mapBounds == null) {
            return true;
        }
        if (this.f1098q.contains(mapBounds) || this.f1098q.intersects(mapBounds)) {
            return true;
        }
        return false;
    }

    public void draw(GL10 gl10) throws RemoteException {
        if (this.f1092k != null && this.f1092k.size() != 0) {
            if (this.f1094m == null || this.f1095n == null || this.f1096o == 0 || this.f1097p == 0) {
                calMapFPoint();
            }
            List list = this.f1093l;
            if (m1594a()) {
                synchronized (this.f1101t) {
                    list = Util.m2357a(this.f1082a, this.f1093l, true);
                }
            }
            if (list.size() > 2) {
                m1598b(list);
                if (this.f1094m != null && this.f1095n != null && this.f1096o > 0 && this.f1097p > 0) {
                    GLESUtility.m2903a(gl10, this.f1088g, this.f1089h, this.f1094m, this.f1087f, this.f1095n, this.f1096o, this.f1097p);
                }
            }
            this.f1099r = true;
        }
    }

    /* renamed from: a */
    private boolean m1594a() {
        float zoomLevel = this.f1082a.getZoomLevel();
        m1597b();
        if (zoomLevel <= 10.0f) {
            return false;
        }
        try {
            if (this.f1082a == null) {
                return false;
            }
            Rect rect = new Rect(-100, -100, this.f1082a.getMapWidth() + 100, this.f1082a.getMapHeight() + 100);
            LatLng latLng = this.f1098q.northeast;
            LatLng latLng2 = this.f1098q.southwest;
            IPoint iPoint = new IPoint();
            this.f1082a.getLatLng2Pixel(latLng.latitude, latLng2.longitude, iPoint);
            IPoint iPoint2 = new IPoint();
            this.f1082a.getLatLng2Pixel(latLng.latitude, latLng.longitude, iPoint2);
            IPoint iPoint3 = new IPoint();
            this.f1082a.getLatLng2Pixel(latLng2.latitude, latLng.longitude, iPoint3);
            IPoint iPoint4 = new IPoint();
            this.f1082a.getLatLng2Pixel(latLng2.latitude, latLng2.longitude, iPoint4);
            if (rect.contains(iPoint.f4562x, iPoint.f4563y) && rect.contains(iPoint2.f4562x, iPoint2.f4563y) && rect.contains(iPoint3.f4562x, iPoint3.f4563y) && rect.contains(iPoint4.f4562x, iPoint4.f4563y)) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: b */
    private void m1598b(List<FPoint> list) throws RemoteException {
        int i = 0;
        m1597b();
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        if (size >= 2) {
            FPoint fPoint = (FPoint) list.get(0);
            arrayList.add(fPoint);
            int i2 = 1;
            while (true) {
                int i3 = i2;
                FPoint fPoint2 = fPoint;
                if (i3 >= size - 1) {
                    break;
                }
                fPoint = (FPoint) list.get(i3);
                if (m1595a(fPoint2, fPoint)) {
                    arrayList.add(fPoint);
                } else {
                    fPoint = fPoint2;
                }
                i2 = i3 + 1;
            }
            arrayList.add(list.get(size - 1));
            float[] fArr = new float[(arrayList.size() * 3)];
            FPoint[] fPointArr = new FPoint[arrayList.size()];
            Iterator it = arrayList.iterator();
            i2 = 0;
            while (it.hasNext()) {
                fPoint = (FPoint) it.next();
                fArr[i2 * 3] = fPoint.f4560x;
                fArr[(i2 * 3) + 1] = fPoint.f4561y;
                fArr[(i2 * 3) + 2] = 0.0f;
                fPointArr[i2] = fPoint;
                i2++;
            }
            FPoint[] a = PolygonDelegateImp.m1596a(fPointArr);
            if (a.length == 0) {
                if (f1081u == 1.0E10f) {
                    f1081u = 1.0E8f;
                } else {
                    f1081u = 1.0E10f;
                }
                a = PolygonDelegateImp.m1596a(fPointArr);
            }
            float[] fArr2 = new float[(a.length * 3)];
            int length = a.length;
            i2 = 0;
            while (i < length) {
                FPoint fPoint3 = a[i];
                fArr2[i2 * 3] = fPoint3.f4560x;
                fArr2[(i2 * 3) + 1] = fPoint3.f4561y;
                fArr2[(i2 * 3) + 2] = 0.0f;
                i2++;
                i++;
            }
            this.f1096o = fPointArr.length;
            this.f1097p = a.length;
            this.f1094m = Util.m2355a(fArr);
            this.f1095n = Util.m2355a(fArr2);
        }
    }

    /* renamed from: a */
    private boolean m1595a(FPoint fPoint, FPoint fPoint2) {
        return Math.abs(fPoint2.f4560x - fPoint.f4560x) >= this.f1100s || Math.abs(fPoint2.f4561y - fPoint.f4561y) >= this.f1100s;
    }

    /* renamed from: b */
    private void m1597b() {
        float zoomLevel = this.f1082a.getZoomLevel();
        if (this.f1092k.size() <= 5000 || zoomLevel > 12.0f) {
            this.f1100s = this.f1082a.getMapProjection().getMapLenWithWin(10);
            return;
        }
        zoomLevel = (zoomLevel / 2.0f) + (this.f1087f / 2.0f);
        if (zoomLevel > 200.0f) {
            zoomLevel = 200.0f;
        }
        this.f1100s = this.f1082a.getMapProjection().getMapLenWithWin((int) zoomLevel);
    }

    public void setStrokeWidth(float f) throws RemoteException {
        this.f1087f = f;
        this.f1082a.setRunLowFrame(false);
    }

    public float getStrokeWidth() throws RemoteException {
        return this.f1087f;
    }

    public void setFillColor(int i) throws RemoteException {
        this.f1088g = i;
        this.f1082a.setRunLowFrame(false);
    }

    public int getFillColor() throws RemoteException {
        return this.f1088g;
    }

    public void setStrokeColor(int i) throws RemoteException {
        this.f1089h = i;
        this.f1082a.setRunLowFrame(false);
    }

    public int getStrokeColor() throws RemoteException {
        return this.f1089h;
    }

    public void setHoles(List<LatLng> list) throws RemoteException {
        this.f1090i = list;
        this.f1082a.setRunLowFrame(false);
    }

    public List<LatLng> getHoles() {
        return this.f1090i;
    }

    public void setGeodesic(boolean z) {
        this.f1085d = z;
        this.f1082a.setRunLowFrame(false);
    }

    public boolean isGeodesic() {
        return this.f1085d;
    }

    /* renamed from: a */
    static FPoint[] m1596a(FPoint[] fPointArr) {
        int i = 0;
        int length = fPointArr.length;
        float[] fArr = new float[(length * 2)];
        for (int i2 = 0; i2 < length; i2++) {
            fArr[i2 * 2] = fPointArr[i2].f4560x * f1081u;
            fArr[(i2 * 2) + 1] = fPointArr[i2].f4561y * f1081u;
        }
        ShortArray a = new EarClippingTriangulator().mo9225a(fArr);
        length = a.f1746b;
        FPoint[] fPointArr2 = new FPoint[length];
        while (i < length) {
            fPointArr2[i] = new FPoint();
            fPointArr2[i].f4560x = fArr[a.mo9275a(i) * 2] / f1081u;
            fPointArr2[i].f4561y = fArr[(a.mo9275a(i) * 2) + 1] / f1081u;
            i++;
        }
        return fPointArr2;
    }

    public void destroy() {
        try {
            if (this.f1094m != null) {
                this.f1094m.clear();
                this.f1094m = null;
            }
            if (this.f1095n != null) {
                this.f1095n = null;
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "PolygonDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "PolygonDelegateImp destroy");
        }
    }

    public boolean contains(LatLng latLng) throws RemoteException {
        try {
            return Util.m2363a(latLng, getPoints());
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "PolygonDelegateImp", "contains");
            th.printStackTrace();
            return false;
        }
    }

    public boolean isDrawFinish() {
        return this.f1099r;
    }
}
