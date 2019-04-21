package com.amap.api.mapcore.util;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.RemoteException;
import android.support.p000v4.view.ViewCompat;
import android.util.Log;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.autonavi.amap.mapcore.AMapNativeRenderer;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IOverlayDelegate;
import com.autonavi.amap.mapcore.interfaces.IPolylineDelegate;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.util.ao */
class PolylineDelegateImp implements IPolylineDelegate {
    /* renamed from: A */
    private float f1102A = 0.0f;
    /* renamed from: B */
    private float f1103B = 0.0f;
    /* renamed from: C */
    private float f1104C;
    /* renamed from: D */
    private float f1105D;
    /* renamed from: E */
    private float f1106E;
    /* renamed from: F */
    private float f1107F;
    /* renamed from: G */
    private float f1108G = 0.0f;
    /* renamed from: H */
    private float f1109H = 0.0f;
    /* renamed from: I */
    private float[] f1110I;
    /* renamed from: J */
    private int[] f1111J;
    /* renamed from: K */
    private int[] f1112K;
    /* renamed from: L */
    private double f1113L = 5.0d;
    /* renamed from: a */
    private GLOverlayLayer f1114a;
    /* renamed from: b */
    private String f1115b;
    /* renamed from: c */
    private List<IPoint> f1116c = new ArrayList();
    /* renamed from: d */
    private List<FPoint> f1117d = new ArrayList();
    /* renamed from: e */
    private List<LatLng> f1118e = new ArrayList();
    /* renamed from: f */
    private List<BitmapDescriptor> f1119f = new ArrayList();
    /* renamed from: g */
    private List<Integer> f1120g = new ArrayList();
    /* renamed from: h */
    private List<Integer> f1121h = new ArrayList();
    /* renamed from: i */
    private FloatBuffer f1122i;
    /* renamed from: j */
    private BitmapDescriptor f1123j = null;
    /* renamed from: k */
    private LatLngBounds f1124k = null;
    /* renamed from: l */
    private Object f1125l = new Object();
    /* renamed from: m */
    private boolean f1126m = true;
    /* renamed from: n */
    private boolean f1127n = true;
    /* renamed from: o */
    private boolean f1128o = false;
    /* renamed from: p */
    private boolean f1129p = false;
    /* renamed from: q */
    private boolean f1130q = false;
    /* renamed from: r */
    private boolean f1131r = true;
    /* renamed from: s */
    private boolean f1132s = false;
    /* renamed from: t */
    private boolean f1133t = false;
    /* renamed from: u */
    private boolean f1134u = true;
    /* renamed from: v */
    private int f1135v = 0;
    /* renamed from: w */
    private int f1136w = 0;
    /* renamed from: x */
    private int f1137x = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: y */
    private int f1138y = 0;
    /* renamed from: z */
    private float f1139z = 10.0f;

    /* renamed from: a */
    public void mo8660a(boolean z) {
        this.f1134u = z;
        this.f1114a.f2170a.setRunLowFrame(false);
    }

    public void setGeodesic(boolean z) throws RemoteException {
        this.f1128o = z;
        this.f1114a.f2170a.setRunLowFrame(false);
    }

    public boolean isGeodesic() {
        return this.f1128o;
    }

    public void setDottedLine(boolean z) {
        if (this.f1135v == 2 || this.f1135v == 0) {
            this.f1129p = z;
            if (z && this.f1127n) {
                this.f1135v = 2;
            }
            this.f1114a.f2170a.setRunLowFrame(false);
        }
    }

    public boolean isDottedLine() {
        return this.f1129p;
    }

    public PolylineDelegateImp(GLOverlayLayer gLOverlayLayer) {
        this.f1114a = gLOverlayLayer;
        try {
            this.f1115b = getId();
        } catch (RemoteException e) {
            SDKLogHandler.m2563a(e, "PolylineDelegateImp", "create");
            e.printStackTrace();
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8658a(List<LatLng> list) throws RemoteException {
        ArrayList arrayList = new ArrayList();
        Builder builder = LatLngBounds.builder();
        if (list != null) {
            Object obj = null;
            for (LatLng latLng : list) {
                if (!(latLng == null || latLng.equals(obj))) {
                    IPoint iPoint;
                    if (!this.f1128o) {
                        iPoint = new IPoint();
                        this.f1114a.f2170a.latlon2Geo(latLng.latitude, latLng.longitude, iPoint);
                        arrayList.add(iPoint);
                        builder.include(latLng);
                    } else if (obj != null) {
                        if (Math.abs(latLng.longitude - obj.longitude) < 0.01d) {
                            iPoint = new IPoint();
                            this.f1114a.f2170a.latlon2Geo(obj.latitude, obj.longitude, iPoint);
                            arrayList.add(iPoint);
                            builder.include(obj);
                            iPoint = new IPoint();
                            this.f1114a.f2170a.latlon2Geo(latLng.latitude, latLng.longitude, iPoint);
                            arrayList.add(iPoint);
                            builder.include(latLng);
                        } else {
                            mo8657a(obj, latLng, arrayList, builder);
                        }
                    }
                    obj = latLng;
                }
            }
        }
        this.f1116c = arrayList;
        this.f1138y = 0;
        if (this.f1116c.size() > 0) {
            this.f1124k = builder.build();
        }
        this.f1114a.f2170a.setRunLowFrame(false);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public IPoint mo8655a(IPoint iPoint, IPoint iPoint2, IPoint iPoint3, double d, int i) {
        IPoint iPoint4 = new IPoint();
        double d2 = (double) (iPoint2.f4562x - iPoint.f4562x);
        double d3 = (double) (iPoint2.f4563y - iPoint.f4563y);
        iPoint4.f4563y = (int) (((((double) i) * d) / Math.sqrt(((d3 * d3) / (d2 * d2)) + 1.0d)) + ((double) iPoint3.f4563y));
        iPoint4.f4562x = (int) (((d3 * ((double) (iPoint3.f4563y - iPoint4.f4563y))) / d2) + ((double) iPoint3.f4562x));
        return iPoint4;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8659a(List<IPoint> list, List<IPoint> list2, double d) {
        if (list.size() == 3) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 <= 10) {
                    float f = ((float) i2) / 10.0f;
                    IPoint iPoint = new IPoint();
                    double d2 = ((((1.0d - ((double) f)) * (1.0d - ((double) f))) * ((double) ((IPoint) list.get(0)).f4562x)) + (((((double) (2.0f * f)) * (1.0d - ((double) f))) * ((double) ((IPoint) list.get(1)).f4562x)) * d)) + ((double) (((float) ((IPoint) list.get(2)).f4562x) * (f * f)));
                    double d3 = ((((1.0d - ((double) f)) * (1.0d - ((double) f))) * ((double) ((IPoint) list.get(0)).f4563y)) + (((((double) (2.0f * f)) * (1.0d - ((double) f))) * ((double) ((IPoint) list.get(1)).f4563y)) * d)) + ((double) (((float) ((IPoint) list.get(2)).f4563y) * (f * f)));
                    double d4 = (((1.0d - ((double) f)) * (1.0d - ((double) f))) + ((((double) (2.0f * f)) * (1.0d - ((double) f))) * d)) + ((double) (f * f));
                    iPoint.f4562x = (int) (d2 / ((((1.0d - ((double) f)) * (1.0d - ((double) f))) + ((((double) (2.0f * f)) * (1.0d - ((double) f))) * d)) + ((double) (f * f))));
                    iPoint.f4563y = (int) (d3 / d4);
                    list2.add(iPoint);
                    i = (int) (((float) i2) + 1.0f);
                } else {
                    return;
                }
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8657a(LatLng latLng, LatLng latLng2, List<IPoint> list, Builder builder) {
        double abs = (Math.abs(latLng.longitude - latLng2.longitude) * 3.141592653589793d) / 180.0d;
        LatLng latLng3 = new LatLng((latLng2.latitude + latLng.latitude) / 2.0d, (latLng2.longitude + latLng.longitude) / 2.0d, false);
        builder.include(latLng).include(latLng3).include(latLng2);
        int i = latLng3.latitude > 0.0d ? -1 : 1;
        IPoint iPoint = new IPoint();
        this.f1114a.f2170a.latlon2Geo(latLng.latitude, latLng.longitude, iPoint);
        IPoint iPoint2 = new IPoint();
        this.f1114a.f2170a.latlon2Geo(latLng2.latitude, latLng2.longitude, iPoint2);
        IPoint iPoint3 = new IPoint();
        this.f1114a.f2170a.latlon2Geo(latLng3.latitude, latLng3.longitude, iPoint3);
        double cos = Math.cos(0.5d * abs);
        IPoint a = mo8655a(iPoint, iPoint2, iPoint3, (Math.hypot((double) (iPoint.f4562x - iPoint2.f4562x), (double) (iPoint.f4563y - iPoint2.f4563y)) * 0.5d) * Math.tan(0.5d * abs), i);
        List arrayList = new ArrayList();
        arrayList.add(iPoint);
        arrayList.add(a);
        arrayList.add(iPoint2);
        mo8659a(arrayList, (List) list, cos);
    }

    public void remove() throws RemoteException {
        this.f1114a.mo9567c(getId());
        if (this.f1112K != null && this.f1112K.length > 0) {
            for (int valueOf : this.f1112K) {
                this.f1114a.mo9562a(Integer.valueOf(valueOf));
            }
        }
        if (this.f1136w > 0) {
            this.f1114a.mo9562a(Integer.valueOf(this.f1136w));
        }
        this.f1114a.f2170a.setRunLowFrame(false);
    }

    public String getId() throws RemoteException {
        if (this.f1115b == null) {
            this.f1115b = GLOverlayLayer.m2918a("Polyline");
        }
        return this.f1115b;
    }

    public void setPoints(List<LatLng> list) throws RemoteException {
        try {
            this.f1118e = list;
            synchronized (this.f1125l) {
                mo8658a((List) list);
            }
            this.f1131r = true;
            this.f1114a.f2170a.setRunLowFrame(false);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "PolylineDelegateImp", "setPoints");
            this.f1116c.clear();
            th.printStackTrace();
        }
    }

    public List<LatLng> getPoints() throws RemoteException {
        return this.f1118e;
    }

    public void setWidth(float f) throws RemoteException {
        this.f1139z = f;
        this.f1114a.f2170a.setRunLowFrame(false);
    }

    public float getWidth() throws RemoteException {
        return this.f1139z;
    }

    public void setColor(int i) {
        if (this.f1135v == 0 || this.f1135v == 2) {
            this.f1137x = i;
            this.f1104C = ((float) Color.alpha(i)) / 255.0f;
            this.f1105D = ((float) Color.red(i)) / 255.0f;
            this.f1106E = ((float) Color.green(i)) / 255.0f;
            this.f1107F = ((float) Color.blue(i)) / 255.0f;
            if (this.f1127n) {
                this.f1135v = 0;
            }
            this.f1114a.f2170a.setRunLowFrame(false);
        }
    }

    public int getColor() throws RemoteException {
        return this.f1137x;
    }

    public void setZIndex(float f) throws RemoteException {
        this.f1102A = f;
        this.f1114a.mo9564b();
        this.f1114a.f2170a.setRunLowFrame(false);
    }

    public float getZIndex() throws RemoteException {
        return this.f1102A;
    }

    public void setVisible(boolean z) throws RemoteException {
        this.f1126m = z;
        this.f1114a.f2170a.setRunLowFrame(false);
    }

    public boolean isVisible() throws RemoteException {
        return this.f1126m;
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
        return true;
    }

    public void calMapFPoint() throws RemoteException {
        synchronized (this.f1125l) {
            this.f1117d.clear();
            this.f1133t = false;
            this.f1110I = new float[(this.f1116c.size() * 3)];
            int i = 0;
            for (IPoint iPoint : this.f1116c) {
                FPoint fPoint = new FPoint();
                this.f1114a.f2170a.geo2Map(iPoint.f4563y, iPoint.f4562x, fPoint);
                this.f1110I[i * 3] = fPoint.f4560x;
                this.f1110I[(i * 3) + 1] = fPoint.f4561y;
                this.f1110I[(i * 3) + 2] = 0.0f;
                this.f1117d.add(fPoint);
                i++;
            }
        }
        if (!this.f1134u) {
            this.f1122i = Util.m2355a(this.f1110I);
        }
        this.f1138y = this.f1116c.size();
        m1603a();
    }

    /* renamed from: a */
    private void m1603a() {
        if (this.f1138y <= 5000 || this.f1103B > 12.0f) {
            this.f1109H = this.f1114a.f2170a.getMapProjection().getMapLenWithWin(10);
            return;
        }
        float f = (this.f1139z / 2.0f) + (this.f1103B / 2.0f);
        if (f > 200.0f) {
            f = 200.0f;
        }
        this.f1109H = this.f1114a.f2170a.getMapProjection().getMapLenWithWin((int) f);
    }

    /* renamed from: b */
    private void m1607b(List<FPoint> list) throws RemoteException {
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        if (size >= 2) {
            FPoint fPoint = (FPoint) list.get(0);
            arrayList.add(fPoint);
            int i = 1;
            while (true) {
                int i2 = i;
                FPoint fPoint2 = fPoint;
                if (i2 >= size - 1) {
                    break;
                }
                fPoint = (FPoint) list.get(i2);
                if (m1606a(fPoint2, fPoint)) {
                    arrayList.add(fPoint);
                } else {
                    fPoint = fPoint2;
                }
                i = i2 + 1;
            }
            arrayList.add(list.get(size - 1));
            this.f1110I = new float[(arrayList.size() * 3)];
            this.f1111J = null;
            this.f1111J = new int[arrayList.size()];
            Iterator it = arrayList.iterator();
            i = 0;
            while (it.hasNext()) {
                fPoint = (FPoint) it.next();
                this.f1111J[i] = i;
                this.f1110I[i * 3] = fPoint.f4560x;
                this.f1110I[(i * 3) + 1] = fPoint.f4561y;
                this.f1110I[(i * 3) + 2] = 0.0f;
                i++;
            }
        }
    }

    /* renamed from: a */
    private boolean m1606a(FPoint fPoint, FPoint fPoint2) {
        return Math.abs(fPoint2.f4560x - fPoint.f4560x) >= this.f1109H || Math.abs(fPoint2.f4561y - fPoint.f4561y) >= this.f1109H;
    }

    /* renamed from: a */
    public void mo8656a(BitmapDescriptor bitmapDescriptor) {
        this.f1127n = false;
        this.f1135v = 1;
        this.f1123j = bitmapDescriptor;
        this.f1114a.f2170a.setRunLowFrame(false);
    }

    public void draw(GL10 gl10) throws RemoteException {
        if (this.f1116c != null && this.f1116c.size() != 0 && this.f1139z > 0.0f) {
            if (this.f1131r) {
                calMapFPoint();
                this.f1131r = false;
            }
            if (this.f1110I != null && this.f1138y > 0) {
                if (this.f1134u) {
                    m1604a(gl10);
                } else {
                    if (this.f1122i == null) {
                        this.f1122i = Util.m2355a(this.f1110I);
                    }
                    GLESUtility.m2902a(gl10, 3, this.f1137x, this.f1122i, this.f1139z, this.f1138y);
                }
            }
            this.f1133t = true;
        }
    }

    /* renamed from: a */
    private void m1604a(GL10 gl10) {
        float mapLenWithWin = this.f1114a.f2170a.getMapProjection().getMapLenWithWin((int) this.f1139z);
        switch (this.f1135v) {
            case 0:
                m1615f(gl10, mapLenWithWin);
                return;
            case 1:
                m1613d(gl10, mapLenWithWin);
                return;
            case 2:
                m1614e(gl10, mapLenWithWin);
                return;
            case 3:
                m1612c(gl10, mapLenWithWin);
                return;
            case 4:
                m1608b(gl10, mapLenWithWin);
                return;
            case 5:
                m1605a(gl10, mapLenWithWin);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m1605a(GL10 gl10, float f) {
        int i = 0;
        if (!this.f1130q) {
            this.f1112K = new int[this.f1119f.size()];
            for (int i2 = 0; i2 < this.f1112K.length; i2++) {
                int texsureId = this.f1114a.f2170a.getTexsureId();
                if (texsureId == 0) {
                    int[] iArr = new int[]{0};
                    gl10.glGenTextures(1, iArr, 0);
                    texsureId = iArr[0];
                }
                int i3 = texsureId;
                Util.m2367b(gl10, i3, ((BitmapDescriptor) this.f1119f.get(i2)).getBitmap(), true);
                this.f1112K[i2] = i3;
            }
            this.f1130q = true;
        }
        int[] iArr2 = new int[this.f1120g.size()];
        while (i < iArr2.length) {
            iArr2[i] = this.f1112K[((Integer) this.f1120g.get(i)).intValue()];
            i++;
        }
        AMapNativeRenderer.nativeDrawLineByMultiTextureID(this.f1110I, this.f1110I.length, f, iArr2, iArr2.length, this.f1111J, this.f1111J.length, this.f1108G);
    }

    /* renamed from: b */
    private void m1608b(GL10 gl10, float f) {
        int[] iArr = new int[this.f1121h.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.f1121h.size()) {
                iArr[i2] = ((Integer) this.f1121h.get(i2)).intValue();
                i = i2 + 1;
            } else {
                AMapNativeRenderer.nativeDrawGradientColorLine(this.f1110I, this.f1110I.length, f, iArr, this.f1121h.size(), this.f1111J, this.f1111J.length, this.f1114a.f2170a.getLineTextureID());
                return;
            }
        }
    }

    /* renamed from: c */
    private void m1612c(GL10 gl10, float f) {
        int[] iArr = new int[this.f1121h.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.f1121h.size()) {
                iArr[i2] = ((Integer) this.f1121h.get(i2)).intValue();
                i = i2 + 1;
            } else {
                AMapNativeRenderer.nativeDrawLineByMultiColor(this.f1110I, this.f1110I.length, f, this.f1114a.f2170a.getLineTextureID(), iArr, this.f1121h.size(), this.f1111J, this.f1111J.length);
                return;
            }
        }
    }

    /* renamed from: d */
    private void m1613d(GL10 gl10, float f) {
        if (!this.f1130q) {
            this.f1136w = this.f1114a.f2170a.getTexsureId();
            if (this.f1136w == 0) {
                int[] iArr = new int[]{0};
                gl10.glGenTextures(1, iArr, 0);
                this.f1136w = iArr[0];
            }
            if (this.f1123j != null) {
                Util.m2367b(gl10, this.f1136w, this.f1123j.getBitmap(), true);
            }
            this.f1130q = true;
        }
        try {
            List list = this.f1117d;
            if (m1609b()) {
                synchronized (this.f1125l) {
                    list = Util.m2357a(this.f1114a.f2170a, this.f1117d, false);
                }
            }
            m1607b(list);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AMapNativeRenderer.nativeDrawLineByTextureID(this.f1110I, this.f1110I.length, f, this.f1136w, this.f1105D, this.f1106E, this.f1107F, this.f1104C, this.f1108G, false, false, false);
    }

    /* renamed from: e */
    private void m1614e(GL10 gl10, float f) {
        AMapNativeRenderer.nativeDrawLineByTextureID(this.f1110I, this.f1110I.length, f, this.f1114a.f2170a.getImaginaryLineTextureID(), this.f1105D, this.f1106E, this.f1107F, this.f1104C, 0.0f, true, true, false);
    }

    /* renamed from: f */
    private void m1615f(GL10 gl10, float f) {
        try {
            List list = this.f1117d;
            if (m1609b()) {
                synchronized (this.f1125l) {
                    list = Util.m2357a(this.f1114a.f2170a, this.f1117d, false);
                }
            }
            m1607b(list);
            AMapNativeRenderer.nativeDrawLineByTextureID(this.f1110I, this.f1110I.length, f, this.f1114a.f2170a.getLineTextureID(), this.f1105D, this.f1106E, this.f1107F, this.f1104C, 0.0f, false, true, false);
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    private boolean m1609b() {
        try {
            this.f1103B = this.f1114a.f2170a.getCameraPosition().zoom;
            m1603a();
            if (this.f1103B <= 10.0f || this.f1135v > 2) {
                return false;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            if (this.f1114a.f2170a == null) {
                return false;
            }
            Rect rect = new Rect(-100, -100, this.f1114a.f2170a.getMapWidth() + 100, this.f1114a.f2170a.getMapHeight() + 100);
            LatLng latLng = this.f1124k.northeast;
            LatLng latLng2 = this.f1124k.southwest;
            IPoint iPoint = new IPoint();
            this.f1114a.f2170a.getLatLng2Pixel(latLng.latitude, latLng2.longitude, iPoint);
            IPoint iPoint2 = new IPoint();
            this.f1114a.f2170a.getLatLng2Pixel(latLng.latitude, latLng.longitude, iPoint2);
            IPoint iPoint3 = new IPoint();
            this.f1114a.f2170a.getLatLng2Pixel(latLng2.latitude, latLng.longitude, iPoint3);
            IPoint iPoint4 = new IPoint();
            this.f1114a.f2170a.getLatLng2Pixel(latLng2.latitude, latLng2.longitude, iPoint4);
            if (rect.contains(iPoint.f4562x, iPoint.f4563y) && rect.contains(iPoint2.f4562x, iPoint2.f4563y) && rect.contains(iPoint3.f4562x, iPoint3.f4563y) && rect.contains(iPoint4.f4562x, iPoint4.f4563y)) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public void destroy() {
        try {
            remove();
            if (this.f1110I != null) {
                this.f1110I = null;
            }
            if (this.f1122i != null) {
                this.f1122i.clear();
                this.f1122i = null;
            }
            if (this.f1119f != null && this.f1119f.size() > 0) {
                for (BitmapDescriptor recycle : this.f1119f) {
                    recycle.recycle();
                }
            }
            if (this.f1123j != null) {
                this.f1123j.recycle();
            }
            if (this.f1121h != null) {
                this.f1121h.clear();
                this.f1121h = null;
            }
            if (this.f1120g != null) {
                this.f1120g.clear();
                this.f1120g = null;
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "PolylineDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "PolylineDelegateImp destroy");
        }
    }

    public boolean isDrawFinish() {
        return this.f1133t;
    }

    public LatLng getNearestLatLng(LatLng latLng) {
        int i = 0;
        if (latLng == null) {
            return null;
        }
        if (this.f1118e == null || this.f1118e.size() == 0) {
            return null;
        }
        float f = 0.0f;
        int i2 = 0;
        while (i < this.f1118e.size()) {
            try {
                float calculateLineDistance;
                int i3;
                if (i == 0) {
                    calculateLineDistance = AMapUtils.calculateLineDistance(latLng, (LatLng) this.f1118e.get(i));
                    i3 = i2;
                } else {
                    calculateLineDistance = AMapUtils.calculateLineDistance(latLng, (LatLng) this.f1118e.get(i));
                    if (f > calculateLineDistance) {
                        i3 = i;
                    } else {
                        calculateLineDistance = f;
                        i3 = i2;
                    }
                }
                i++;
                i2 = i3;
                f = calculateLineDistance;
            } catch (Throwable th) {
                SDKLogHandler.m2563a(th, "PolylineDelegateImp", "getNearestLatLng");
                th.printStackTrace();
                return null;
            }
        }
        return (LatLng) this.f1118e.get(i2);
    }

    public boolean contains(LatLng latLng) {
        float[] fArr = new float[this.f1110I.length];
        System.arraycopy(this.f1110I, 0, fArr, 0, this.f1110I.length);
        if (fArr.length / 3 < 2) {
            return false;
        }
        try {
            ArrayList c = m1610c();
            if (c == null || c.size() < 1) {
                return false;
            }
            double mapLenWithWin = (double) this.f1114a.f2170a.getMapProjection().getMapLenWithWin(((int) this.f1139z) / 4);
            double mapLenWithWin2 = (double) this.f1114a.f2170a.getMapProjection().getMapLenWithWin((int) this.f1113L);
            FPoint a = m1602a(latLng);
            FPoint fPoint = null;
            for (int i = 0; i < c.size() - 1; i++) {
                FPoint fPoint2;
                if (i == 0) {
                    fPoint2 = (FPoint) c.get(i);
                } else {
                    fPoint2 = fPoint;
                }
                fPoint = (FPoint) c.get(i + 1);
                if ((mapLenWithWin2 + mapLenWithWin) - m1601a(a, fPoint2, fPoint) >= 0.0d) {
                    c.clear();
                    return true;
                }
            }
            c.clear();
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: c */
    private ArrayList<FPoint> m1610c() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < this.f1110I.length) {
            float f = this.f1110I[i];
            i++;
            float f2 = this.f1110I[i];
            i++;
            arrayList.add(new FPoint(f, f2));
            i++;
        }
        return arrayList;
    }

    /* renamed from: a */
    private double m1601a(FPoint fPoint, FPoint fPoint2, FPoint fPoint3) {
        return m1600a((double) fPoint.f4560x, (double) fPoint.f4561y, (double) fPoint2.f4560x, (double) fPoint2.f4561y, (double) fPoint3.f4560x, (double) fPoint3.f4561y);
    }

    /* renamed from: a */
    private FPoint m1602a(LatLng latLng) {
        IPoint iPoint = new IPoint();
        this.f1114a.f2170a.latlon2Geo(latLng.latitude, latLng.longitude, iPoint);
        FPoint fPoint = new FPoint();
        this.f1114a.f2170a.geo2Map(iPoint.f4563y, iPoint.f4562x, fPoint);
        return fPoint;
    }

    /* renamed from: a */
    private double m1600a(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = ((d5 - d3) * (d - d3)) + ((d6 - d4) * (d2 - d4));
        if (d7 <= 0.0d) {
            return Math.sqrt(((d - d3) * (d - d3)) + ((d2 - d4) * (d2 - d4)));
        }
        double d8 = ((d5 - d3) * (d5 - d3)) + ((d6 - d4) * (d6 - d4));
        if (d7 >= d8) {
            return Math.sqrt(((d - d5) * (d - d5)) + ((d2 - d6) * (d2 - d6)));
        }
        d7 /= d8;
        d8 = ((d5 - d3) * d7) + d3;
        d7 = (d7 * (d6 - d4)) + d4;
        return Math.sqrt(((d7 - d2) * (d7 - d2)) + ((d - d8) * (d - d8)));
    }

    public void setTransparency(float f) {
        this.f1108G = f;
        this.f1114a.f2170a.setRunLowFrame(false);
    }

    public void setCustomTextureList(List<BitmapDescriptor> list) {
        if (list != null && list.size() != 0) {
            if (list.size() > 1) {
                this.f1127n = false;
                this.f1135v = 5;
                this.f1119f = list;
                this.f1114a.f2170a.setRunLowFrame(false);
                return;
            }
            mo8656a((BitmapDescriptor) list.get(0));
        }
    }

    public void setCustemTextureIndex(List<Integer> list) {
        if (list != null && list.size() != 0) {
            this.f1120g = m1611c(list);
        }
    }

    public void setColorValues(List<Integer> list) {
        if (list != null && list.size() != 0) {
            if (list.size() > 1) {
                this.f1127n = false;
                this.f1121h = m1611c(list);
                this.f1135v = 3;
                this.f1114a.f2170a.setRunLowFrame(false);
                return;
            }
            setColor(((Integer) list.get(0)).intValue());
        }
    }

    public void useGradient(boolean z) {
        if (z && this.f1121h != null && this.f1121h.size() > 1) {
            this.f1132s = z;
            this.f1135v = 4;
            this.f1114a.f2170a.setRunLowFrame(false);
        }
    }

    /* renamed from: c */
    private List<Integer> m1611c(List<Integer> list) {
        int[] iArr = new int[list.size()];
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            int intValue = ((Integer) list.get(i3)).intValue();
            if (i3 == 0) {
                arrayList.add(Integer.valueOf(intValue));
            } else if (intValue != i2) {
                arrayList.add(Integer.valueOf(intValue));
            } else {
            }
            iArr[i] = i3;
            i++;
            i2 = intValue;
        }
        this.f1111J = new int[arrayList.size()];
        System.arraycopy(iArr, 0, this.f1111J, 0, this.f1111J.length);
        return arrayList;
    }

    public void reLoadTexture() {
        this.f1130q = false;
        this.f1136w = 0;
        if (this.f1112K != null) {
            Arrays.fill(this.f1112K, 0);
        }
    }
}
