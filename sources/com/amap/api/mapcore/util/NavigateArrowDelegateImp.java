package com.amap.api.mapcore.util;

import android.graphics.Color;
import android.os.RemoteException;
import android.support.p000v4.view.ViewCompat;
import android.util.Log;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.autonavi.amap.mapcore.AMapNativeRenderer;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.INavigateArrowDelegate;
import com.autonavi.amap.mapcore.interfaces.IOverlayDelegate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.util.ak */
class NavigateArrowDelegateImp implements INavigateArrowDelegate {
    /* renamed from: a */
    float f1059a;
    /* renamed from: b */
    float f1060b;
    /* renamed from: c */
    float f1061c;
    /* renamed from: d */
    float f1062d;
    /* renamed from: e */
    float f1063e;
    /* renamed from: f */
    float f1064f;
    /* renamed from: g */
    float f1065g;
    /* renamed from: h */
    float f1066h;
    /* renamed from: i */
    float[] f1067i;
    /* renamed from: j */
    private IAMapDelegate f1068j;
    /* renamed from: k */
    private float f1069k = 10.0f;
    /* renamed from: l */
    private int f1070l = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: m */
    private int f1071m = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: n */
    private float f1072n = 0.0f;
    /* renamed from: o */
    private boolean f1073o = true;
    /* renamed from: p */
    private String f1074p;
    /* renamed from: q */
    private CopyOnWriteArrayList<IPoint> f1075q = new CopyOnWriteArrayList();
    /* renamed from: r */
    private int f1076r = 0;
    /* renamed from: s */
    private boolean f1077s = false;
    /* renamed from: t */
    private LatLngBounds f1078t = null;

    public NavigateArrowDelegateImp(IAMapDelegate iAMapDelegate) {
        this.f1068j = iAMapDelegate;
        try {
            this.f1074p = getId();
        } catch (RemoteException e) {
            SDKLogHandler.m2563a(e, "NavigateArrowDelegateImp", "create");
            e.printStackTrace();
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8620a(List<LatLng> list) throws RemoteException {
        Builder builder = LatLngBounds.builder();
        this.f1075q.clear();
        if (list != null) {
            Object obj = null;
            for (LatLng latLng : list) {
                if (!(latLng == null || latLng.equals(obj))) {
                    IPoint iPoint = new IPoint();
                    this.f1068j.latlon2Geo(latLng.latitude, latLng.longitude, iPoint);
                    this.f1075q.add(iPoint);
                    builder.include(latLng);
                    obj = latLng;
                }
            }
        }
        this.f1078t = builder.build();
        this.f1076r = 0;
        this.f1068j.setRunLowFrame(false);
    }

    public void remove() throws RemoteException {
        this.f1068j.removeGLOverlay(getId());
        this.f1068j.setRunLowFrame(false);
    }

    public String getId() throws RemoteException {
        if (this.f1074p == null) {
            this.f1074p = GLOverlayLayer.m2918a("NavigateArrow");
        }
        return this.f1074p;
    }

    public void setPoints(List<LatLng> list) throws RemoteException {
        mo8620a(list);
    }

    public List<LatLng> getPoints() throws RemoteException {
        return m1581a();
    }

    /* renamed from: a */
    private List<LatLng> m1581a() throws RemoteException {
        if (this.f1075q == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f1075q.iterator();
        while (it.hasNext()) {
            IPoint iPoint = (IPoint) it.next();
            if (iPoint != null) {
                DPoint dPoint = new DPoint();
                this.f1068j.geo2Latlng(iPoint.f4562x, iPoint.f4563y, dPoint);
                arrayList.add(new LatLng(dPoint.f4559y, dPoint.f4558x));
            }
        }
        return arrayList;
    }

    public void setWidth(float f) throws RemoteException {
        this.f1069k = f;
        this.f1068j.setRunLowFrame(false);
    }

    public float getWidth() throws RemoteException {
        return this.f1069k;
    }

    public void setTopColor(int i) throws RemoteException {
        this.f1070l = i;
        this.f1059a = ((float) Color.alpha(i)) / 255.0f;
        this.f1060b = ((float) Color.red(i)) / 255.0f;
        this.f1061c = ((float) Color.green(i)) / 255.0f;
        this.f1062d = ((float) Color.blue(i)) / 255.0f;
        this.f1068j.setRunLowFrame(false);
    }

    public int getTopColor() throws RemoteException {
        return this.f1070l;
    }

    public void setSideColor(int i) throws RemoteException {
        this.f1071m = i;
        this.f1063e = ((float) Color.alpha(i)) / 255.0f;
        this.f1064f = ((float) Color.red(i)) / 255.0f;
        this.f1065g = ((float) Color.green(i)) / 255.0f;
        this.f1066h = ((float) Color.blue(i)) / 255.0f;
        this.f1068j.setRunLowFrame(false);
    }

    public int getSideColor() throws RemoteException {
        return this.f1071m;
    }

    public void setZIndex(float f) throws RemoteException {
        this.f1072n = f;
        this.f1068j.changeGLOverlayIndex();
        this.f1068j.setRunLowFrame(false);
    }

    public float getZIndex() throws RemoteException {
        return this.f1072n;
    }

    public void setVisible(boolean z) throws RemoteException {
        this.f1073o = z;
        this.f1068j.setRunLowFrame(false);
    }

    public boolean isVisible() throws RemoteException {
        return this.f1073o;
    }

    public boolean equalsRemote(IOverlayDelegate iOverlayDelegate) throws RemoteException {
        if (equals(iOverlayDelegate) || iOverlayDelegate.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    public int hashCodeRemote() throws RemoteException {
        return super.hashCode();
    }

    public boolean checkInBounds() {
        if (this.f1078t == null) {
            return false;
        }
        LatLngBounds mapBounds = this.f1068j.getMapBounds();
        if (mapBounds == null) {
            return true;
        }
        if (mapBounds.contains(this.f1078t) || this.f1078t.intersects(mapBounds)) {
            return true;
        }
        return false;
    }

    public void calMapFPoint() throws RemoteException {
        boolean z = false;
        this.f1077s = false;
        FPoint fPoint = new FPoint();
        this.f1067i = new float[(this.f1075q.size() * 3)];
        Iterator it = this.f1075q.iterator();
        while (true) {
            boolean z2 = z;
            if (it.hasNext()) {
                IPoint iPoint = (IPoint) it.next();
                this.f1068j.geo2Map(iPoint.f4563y, iPoint.f4562x, fPoint);
                this.f1067i[z2 * 3] = fPoint.f4560x;
                this.f1067i[(z2 * 3) + 1] = fPoint.f4561y;
                this.f1067i[(z2 * 3) + 2] = 0.0f;
                z = z2 + 1;
            } else {
                this.f1076r = this.f1075q.size();
                return;
            }
        }
    }

    public void draw(GL10 gl10) throws RemoteException {
        if (this.f1075q != null && this.f1075q.size() != 0 && this.f1069k > 0.0f) {
            if (this.f1076r == 0) {
                calMapFPoint();
            }
            if (this.f1067i != null && this.f1076r > 0) {
                float mapLenWithWin = this.f1068j.getMapProjection().getMapLenWithWin((int) this.f1069k);
                this.f1068j.getMapProjection().getMapLenWithWin(1);
                AMapNativeRenderer.nativeDrawLineByTextureID(this.f1067i, this.f1067i.length, mapLenWithWin, this.f1068j.getLineTextureID(), this.f1060b, this.f1061c, this.f1062d, this.f1059a, 0.0f, false, true, true);
            }
            this.f1077s = true;
        }
    }

    public void destroy() {
        try {
            if (this.f1067i != null) {
                this.f1067i = null;
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "NavigateArrowDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "NavigateArrowDelegateImp destroy");
        }
    }

    public boolean isDrawFinish() {
        return this.f1077s;
    }
}
