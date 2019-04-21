package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.opengl.GLES10;
import android.os.Build.VERSION;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IMarkerDelegate;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.util.ai */
class MarkerDelegateImp implements IMarkerDelegate {
    /* renamed from: a */
    private static int f1004a = 0;
    /* renamed from: A */
    private boolean f1005A = false;
    /* renamed from: B */
    private boolean f1006B = true;
    /* renamed from: C */
    private MapOverlayImageView f1007C;
    /* renamed from: D */
    private FloatBuffer f1008D;
    /* renamed from: E */
    private Object f1009E;
    /* renamed from: F */
    private boolean f1010F = false;
    /* renamed from: G */
    private CopyOnWriteArrayList<BitmapDescriptor> f1011G = null;
    /* renamed from: H */
    private boolean f1012H = false;
    /* renamed from: I */
    private boolean f1013I = false;
    /* renamed from: J */
    private boolean f1014J = true;
    /* renamed from: K */
    private int f1015K = 0;
    /* renamed from: L */
    private int f1016L = 20;
    /* renamed from: M */
    private boolean f1017M = false;
    /* renamed from: N */
    private int f1018N;
    /* renamed from: O */
    private int f1019O;
    /* renamed from: P */
    private long f1020P = 0;
    /* renamed from: b */
    private boolean f1021b = false;
    /* renamed from: c */
    private boolean f1022c = false;
    /* renamed from: d */
    private boolean f1023d = false;
    /* renamed from: e */
    private float f1024e = 0.0f;
    /* renamed from: f */
    private float f1025f = 0.0f;
    /* renamed from: g */
    private boolean f1026g = false;
    /* renamed from: h */
    private int f1027h = 0;
    /* renamed from: i */
    private int f1028i = 0;
    /* renamed from: j */
    private int f1029j = 0;
    /* renamed from: k */
    private int f1030k = 0;
    /* renamed from: l */
    private int f1031l;
    /* renamed from: m */
    private int f1032m;
    /* renamed from: n */
    private FPoint f1033n = new FPoint();
    /* renamed from: o */
    private float[] f1034o;
    /* renamed from: p */
    private int[] f1035p = null;
    /* renamed from: q */
    private float f1036q = 0.0f;
    /* renamed from: r */
    private boolean f1037r = false;
    /* renamed from: s */
    private FloatBuffer f1038s = null;
    /* renamed from: t */
    private String f1039t;
    /* renamed from: u */
    private LatLng f1040u;
    /* renamed from: v */
    private LatLng f1041v;
    /* renamed from: w */
    private String f1042w;
    /* renamed from: x */
    private String f1043x;
    /* renamed from: y */
    private float f1044y = 0.5f;
    /* renamed from: z */
    private float f1045z = 1.0f;

    /* renamed from: a */
    private static String m1554a(String str) {
        f1004a++;
        return str + f1004a;
    }

    public void setRotateAngle(float f) {
        this.f1025f = f;
        this.f1024e = (((-f) % 360.0f) + 360.0f) % 360.0f;
        if (isInfoWindowShown()) {
            this.f1007C.mo8513f(this);
            this.f1007C.mo8511e(this);
        }
        m1558c();
    }

    public boolean isDestory() {
        return this.f1037r;
    }

    public void realDestroy() {
        if (this.f1037r) {
            try {
                if (this.f1011G != null) {
                    Iterator it = this.f1011G.iterator();
                    while (it.hasNext()) {
                        ((BitmapDescriptor) it.next()).recycle();
                    }
                    this.f1011G = null;
                }
                if (this.f1008D != null) {
                    this.f1008D.clear();
                    this.f1008D = null;
                }
                if (this.f1038s != null) {
                    this.f1038s.clear();
                    this.f1038s = null;
                }
                this.f1040u = null;
                this.f1009E = null;
                this.f1035p = null;
            } catch (Throwable th) {
                SDKLogHandler.m2563a(th, "MarkerDelegateImp", "realdestroy");
                th.printStackTrace();
                Log.d("destroy erro", "MarkerDelegateImp destroy");
            }
        }
    }

    public void destroy() {
        int i = 0;
        try {
            int i2;
            this.f1037r = true;
            remove();
            if (this.f1007C != null) {
                this.f1007C.f986a.callRunDestroy();
                i2 = 0;
                while (this.f1035p != null && i2 < this.f1035p.length) {
                    this.f1007C.mo8498a(Integer.valueOf(this.f1035p[i2]));
                    this.f1007C.mo8496a(this.f1035p[i2]);
                    i2++;
                }
            }
            while (true) {
                i2 = i;
                if (this.f1011G != null && i2 < this.f1011G.size()) {
                    ((BitmapDescriptor) this.f1011G.get(i2)).recycle();
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "MarkerDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "MarkerDelegateImp destroy");
        }
    }

    /* Access modifiers changed, original: declared_synchronized */
    /* renamed from: a */
    public synchronized void mo8587a() {
        if (this.f1011G == null) {
            this.f1011G = new CopyOnWriteArrayList();
        } else {
            this.f1011G.clear();
        }
    }

    /* renamed from: a */
    public synchronized void mo8588a(ArrayList<BitmapDescriptor> arrayList) {
        mo8587a();
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                BitmapDescriptor bitmapDescriptor = (BitmapDescriptor) it.next();
                if (bitmapDescriptor != null) {
                    this.f1011G.add(bitmapDescriptor);
                }
            }
        }
    }

    public MarkerDelegateImp(MarkerOptions markerOptions, MapOverlayImageView mapOverlayImageView) {
        this.f1007C = mapOverlayImageView;
        this.f1040u = markerOptions.getPosition();
        IPoint iPoint = new IPoint();
        this.f1012H = markerOptions.isGps();
        if (markerOptions.getPosition() != null) {
            if (this.f1012H) {
                try {
                    double[] a = C0852gd.m2843a(markerOptions.getPosition().longitude, markerOptions.getPosition().latitude);
                    this.f1041v = new LatLng(a[1], a[0]);
                    MapProjection.lonlat2Geo(a[0], a[1], iPoint);
                } catch (Throwable th) {
                    SDKLogHandler.m2563a(th, "MarkerDelegateImp", "create");
                    this.f1041v = markerOptions.getPosition();
                }
            } else {
                MapProjection.lonlat2Geo(this.f1040u.longitude, this.f1040u.latitude, iPoint);
            }
        }
        this.f1031l = iPoint.f4562x;
        this.f1032m = iPoint.f4563y;
        this.f1044y = markerOptions.getAnchorU();
        this.f1045z = markerOptions.getAnchorV();
        this.f1027h = markerOptions.getInfoWindowOffsetX();
        this.f1028i = markerOptions.getInfoWindowOffsetY();
        this.f1016L = markerOptions.getPeriod();
        this.f1036q = markerOptions.getZIndex();
        calFPoint();
        mo8588a(markerOptions.getIcons());
        this.f1006B = markerOptions.isVisible();
        this.f1043x = markerOptions.getSnippet();
        this.f1042w = markerOptions.getTitle();
        this.f1005A = markerOptions.isDraggable();
        this.f1039t = getId();
        this.f1010F = markerOptions.isPerspective();
        this.f1026g = markerOptions.isFlat();
    }

    /* renamed from: b */
    public IPoint mo8589b() {
        if (this.f1040u == null && !this.f1017M) {
            return null;
        }
        IPoint iPoint = new IPoint();
        this.f1007C.mo8493a().getMapProjection().map2Win(this.f1033n.f4560x, this.f1033n.f4561y, iPoint);
        return iPoint;
    }

    public int getWidth() {
        try {
            return getBitmapDescriptor().getWidth();
        } catch (Throwable th) {
            return 0;
        }
    }

    public int getHeight() {
        try {
            return getBitmapDescriptor().getHeight();
        } catch (Throwable th) {
            return 0;
        }
    }

    public FPoint anchorUVoff() {
        FPoint fPoint = new FPoint();
        if (!(this.f1011G == null || this.f1011G.size() == 0)) {
            fPoint.f4560x = ((float) getWidth()) * this.f1044y;
            fPoint.f4561y = ((float) getHeight()) * this.f1045z;
        }
        return fPoint;
    }

    public boolean isContains() {
        return this.f1007C.mo8501a((IMarkerDelegate) this);
    }

    public IPoint getAnchor() {
        IPoint b = mo8589b();
        if (b == null) {
            return null;
        }
        return b;
    }

    public Rect getRect() {
        if (this.f1034o == null) {
            return new Rect(0, 0, 0, 0);
        }
        try {
            Rect rect;
            MapProjection mapProjection = this.f1007C.f986a.getMapProjection();
            int width = getWidth();
            int height = getHeight();
            IPoint iPoint = new IPoint();
            IPoint iPoint2 = new IPoint();
            mapProjection.map2Win(this.f1033n.f4560x, this.f1033n.f4561y, iPoint);
            if (this.f1026g) {
                mapProjection.map2Win(this.f1034o[0], this.f1034o[1], iPoint2);
                rect = new Rect(iPoint2.f4562x, iPoint2.f4563y, iPoint2.f4562x, iPoint2.f4563y);
                mapProjection.map2Win(this.f1034o[3], this.f1034o[4], iPoint2);
                rect.union(iPoint2.f4562x, iPoint2.f4563y);
                mapProjection.map2Win(this.f1034o[6], this.f1034o[7], iPoint2);
                rect.union(iPoint2.f4562x, iPoint2.f4563y);
                mapProjection.map2Win(this.f1034o[9], this.f1034o[10], iPoint2);
                rect.union(iPoint2.f4562x, iPoint2.f4563y);
            } else {
                m1555a((-this.f1044y) * ((float) width), (this.f1045z - 1.0f) * ((float) height), iPoint2);
                rect = new Rect(iPoint.f4562x + iPoint2.f4562x, iPoint.f4563y - iPoint2.f4563y, iPoint.f4562x + iPoint2.f4562x, iPoint.f4563y - iPoint2.f4563y);
                m1555a((-this.f1044y) * ((float) width), this.f1045z * ((float) height), iPoint2);
                rect.union(iPoint.f4562x + iPoint2.f4562x, iPoint.f4563y - iPoint2.f4563y);
                m1555a((1.0f - this.f1044y) * ((float) width), this.f1045z * ((float) height), iPoint2);
                rect.union(iPoint.f4562x + iPoint2.f4562x, iPoint.f4563y - iPoint2.f4563y);
                m1555a((1.0f - this.f1044y) * ((float) width), (this.f1045z - 1.0f) * ((float) height), iPoint2);
                rect.union(iPoint.f4562x + iPoint2.f4562x, iPoint.f4563y - iPoint2.f4563y);
            }
            this.f1029j = rect.centerX() - iPoint.f4562x;
            this.f1030k = rect.top - iPoint.f4563y;
            return rect;
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "MarkerDelegateImp", "getRect");
            th.printStackTrace();
            return new Rect(0, 0, 0, 0);
        }
    }

    public boolean remove() {
        m1558c();
        this.f1006B = false;
        if (this.f1007C != null) {
            return this.f1007C.mo8507c(this);
        }
        return false;
    }

    /* renamed from: c */
    private void m1558c() {
        if (this.f1007C.f986a != null) {
            this.f1007C.f986a.setRunLowFrame(false);
        }
    }

    public LatLng getPosition() {
        if (!this.f1017M || this.f1033n == null) {
            return this.f1040u;
        }
        DPoint dPoint = new DPoint();
        IPoint iPoint = new IPoint();
        calFPoint();
        this.f1007C.f986a.map2Geo(this.f1033n.f4560x, this.f1033n.f4561y, iPoint);
        MapProjection.geo2LonLat(iPoint.f4562x, iPoint.f4563y, dPoint);
        return new LatLng(dPoint.f4559y, dPoint.f4558x);
    }

    public String getId() {
        if (this.f1039t == null) {
            this.f1039t = MarkerDelegateImp.m1554a("Marker");
        }
        return this.f1039t;
    }

    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            SDKLogHandler.m2563a(new AMapException("非法坐标值 latlng is null"), "setPosition", "Marker");
            return;
        }
        this.f1040u = latLng;
        IPoint iPoint = new IPoint();
        if (this.f1012H) {
            try {
                double[] a = C0852gd.m2843a(latLng.longitude, latLng.latitude);
                this.f1041v = new LatLng(a[1], a[0]);
                MapProjection.lonlat2Geo(a[0], a[1], iPoint);
            } catch (Throwable th) {
                this.f1041v = latLng;
            }
        } else {
            MapProjection.lonlat2Geo(latLng.longitude, latLng.latitude, iPoint);
        }
        this.f1031l = iPoint.f4562x;
        this.f1032m = iPoint.f4563y;
        this.f1017M = false;
        calFPoint();
        m1558c();
    }

    public void setTitle(String str) {
        this.f1042w = str;
        m1558c();
    }

    public String getTitle() {
        return this.f1042w;
    }

    public void setSnippet(String str) {
        this.f1043x = str;
        m1558c();
    }

    public String getSnippet() {
        return this.f1043x;
    }

    public void setDraggable(boolean z) {
        this.f1005A = z;
        m1558c();
    }

    public synchronized void setIcons(ArrayList<BitmapDescriptor> arrayList) {
        if (arrayList != null) {
            try {
                if (this.f1011G != null) {
                    mo8588a((ArrayList) arrayList);
                    this.f1013I = false;
                    this.f1021b = false;
                    if (this.f1008D != null) {
                        this.f1008D.clear();
                        this.f1008D = null;
                    }
                    this.f1035p = null;
                    if (isInfoWindowShown()) {
                        this.f1007C.mo8513f(this);
                        this.f1007C.mo8511e(this);
                    }
                    m1558c();
                }
            } catch (Throwable th) {
                SDKLogHandler.m2563a(th, "MarkerDelegateImp", "setIcons");
                th.printStackTrace();
            }
        }
        return;
    }

    public synchronized ArrayList<BitmapDescriptor> getIcons() {
        ArrayList<BitmapDescriptor> arrayList;
        if (this.f1011G == null || this.f1011G.size() <= 0) {
            arrayList = null;
        } else {
            ArrayList<BitmapDescriptor> arrayList2 = new ArrayList();
            Iterator it = this.f1011G.iterator();
            while (it.hasNext()) {
                arrayList2.add((BitmapDescriptor) it.next());
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public synchronized void setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            try {
                if (this.f1011G != null) {
                    this.f1011G.clear();
                    this.f1011G.add(bitmapDescriptor);
                    this.f1013I = false;
                    this.f1021b = false;
                    this.f1035p = null;
                    if (this.f1008D != null) {
                        this.f1008D.clear();
                        this.f1008D = null;
                    }
                    if (isInfoWindowShown()) {
                        this.f1007C.mo8513f(this);
                        this.f1007C.mo8511e(this);
                    }
                    m1558c();
                }
            } catch (Throwable th) {
                SDKLogHandler.m2563a(th, "MarkerDelegateImp", "setIcon");
                th.printStackTrace();
            }
        }
        return;
    }

    public synchronized BitmapDescriptor getBitmapDescriptor() {
        BitmapDescriptor bitmapDescriptor;
        try {
            if (this.f1011G == null || this.f1011G.size() == 0) {
                mo8587a();
                this.f1011G.add(BitmapDescriptorFactory.defaultMarker());
            } else if (this.f1011G.get(0) == null) {
                this.f1011G.clear();
                bitmapDescriptor = getBitmapDescriptor();
            }
            bitmapDescriptor = (BitmapDescriptor) this.f1011G.get(0);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "MarkerDelegateImp", "getBitmapDescriptor");
            th.printStackTrace();
            bitmapDescriptor = null;
        }
        return bitmapDescriptor;
    }

    public boolean isDraggable() {
        return this.f1005A;
    }

    public void showInfoWindow() {
        if (this.f1006B) {
            this.f1007C.mo8511e(this);
            m1558c();
        }
    }

    public void hideInfoWindow() {
        if (isInfoWindowShown()) {
            this.f1007C.mo8513f(this);
            m1558c();
            this.f1022c = false;
        }
        this.f1023d = false;
    }

    public void setInfoWindowShown(boolean z) {
        this.f1022c = z;
        if (this.f1022c && this.f1017M) {
            this.f1023d = true;
        }
    }

    public boolean isInfoWindowShown() {
        return this.f1022c;
    }

    public void setVisible(boolean z) {
        if (this.f1006B != z) {
            this.f1006B = z;
            if (!z && isInfoWindowShown()) {
                this.f1007C.mo8513f(this);
            }
            m1558c();
        }
    }

    public boolean isVisible() {
        return this.f1006B;
    }

    public void setAnchor(float f, float f2) {
        if (this.f1044y != f || this.f1045z != f2) {
            this.f1044y = f;
            this.f1045z = f2;
            if (isInfoWindowShown()) {
                this.f1007C.mo8513f(this);
                this.f1007C.mo8511e(this);
            }
            m1558c();
        }
    }

    public float getAnchorU() {
        return this.f1044y;
    }

    public float getAnchorV() {
        return this.f1045z;
    }

    public boolean equalsRemote(IMarkerDelegate iMarkerDelegate) throws RemoteException {
        if (equals(iMarkerDelegate) || iMarkerDelegate.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    public int hashCodeRemote() {
        return super.hashCode();
    }

    public boolean calFPoint() {
        if (this.f1017M) {
            this.f1007C.f986a.getMapProjection().win2Map(this.f1018N, this.f1019O, this.f1033n);
        } else {
            this.f1007C.f986a.getMapProjection().geo2Map(this.f1031l, this.f1032m, this.f1033n);
        }
        return true;
    }

    /* renamed from: a */
    private void m1557a(IAMapDelegate iAMapDelegate) throws RemoteException {
        float[] a = Util.m2365a(iAMapDelegate, this.f1026g ? 1 : 0, this.f1033n, this.f1024e, getWidth(), getHeight(), this.f1044y, this.f1045z);
        this.f1034o = (float[]) a.clone();
        if (this.f1038s == null) {
            this.f1038s = Util.m2355a(a);
        } else {
            this.f1038s = Util.m2356a(a, this.f1038s);
        }
        if (this.f1011G != null && this.f1011G.size() > 0) {
            this.f1015K++;
            if (this.f1015K >= this.f1016L * this.f1011G.size()) {
                this.f1015K = 0;
            }
            int i = this.f1015K / this.f1016L;
            if (!this.f1014J) {
                m1558c();
            }
            if (this.f1035p != null && this.f1035p.length > 0) {
                m1556a(this.f1035p[i % this.f1011G.size()], this.f1038s, this.f1008D);
            }
        }
    }

    /* renamed from: a */
    private void m1555a(float f, float f2, IPoint iPoint) {
        float f3 = (float) ((3.141592653589793d * ((double) this.f1024e)) / 180.0d);
        iPoint.f4562x = (int) ((((double) f) * Math.cos((double) f3)) + (((double) f2) * Math.sin((double) f3)));
        iPoint.f4563y = (int) ((((double) f2) * Math.cos((double) f3)) - (Math.sin((double) f3) * ((double) f)));
    }

    /* renamed from: a */
    private void m1556a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (i != 0 && floatBuffer != null && floatBuffer2 != null) {
            GLES10.glEnable(3042);
            GLES10.glBlendFunc(1, 771);
            GLES10.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GLES10.glEnable(3553);
            GLES10.glEnableClientState(32884);
            GLES10.glEnableClientState(32888);
            GLES10.glBindTexture(3553, i);
            GLES10.glVertexPointer(3, 5126, 0, floatBuffer);
            GLES10.glTexCoordPointer(2, 5126, 0, floatBuffer2);
            GLES10.glDrawArrays(6, 0, 4);
            GLES10.glDisableClientState(32884);
            GLES10.glDisableClientState(32888);
            GLES10.glDisable(3553);
            GLES10.glDisable(3042);
        }
    }

    public void drawMarker(GL10 gl10, IAMapDelegate iAMapDelegate) {
        int i = 0;
        if (this.f1006B && !this.f1037r) {
            if (this.f1040u == null && !this.f1017M) {
                return;
            }
            if (getBitmapDescriptor() != null || this.f1011G != null) {
                int i2;
                int i3;
                BitmapDescriptor bitmapDescriptor;
                if (!this.f1013I) {
                    try {
                        if (this.f1011G != null) {
                            this.f1035p = new int[this.f1011G.size()];
                            i2 = VERSION.SDK_INT >= 12 ? 1 : 0;
                            Iterator it = this.f1011G.iterator();
                            i3 = 0;
                            while (it.hasNext()) {
                                bitmapDescriptor = (BitmapDescriptor) it.next();
                                if (i2 != 0) {
                                    i = this.f1007C.mo8492a(bitmapDescriptor);
                                }
                                if (i == 0) {
                                    Bitmap bitmap = bitmapDescriptor.getBitmap();
                                    if (!(bitmap == null || bitmap.isRecycled())) {
                                        i = m1553a(gl10);
                                        if (i2 != 0) {
                                            this.f1007C.mo8497a(new OverlayTextureItem(bitmapDescriptor, i));
                                        }
                                        Util.m2367b(gl10, i, bitmap, false);
                                    }
                                }
                                int i4 = i;
                                this.f1035p[i3] = i4;
                                i3++;
                                i = i4;
                            }
                            if (this.f1011G.size() == 1) {
                                this.f1014J = true;
                            } else {
                                this.f1014J = false;
                            }
                            this.f1013I = true;
                        }
                    } catch (Throwable th) {
                        SDKLogHandler.m2563a(th, "MarkerDelegateImp", "loadtexture");
                        return;
                    }
                }
                try {
                    if (!this.f1021b) {
                        if (this.f1008D == null) {
                            bitmapDescriptor = getBitmapDescriptor();
                            if (bitmapDescriptor != null) {
                                i = bitmapDescriptor.getWidth();
                                i3 = bitmapDescriptor.getHeight();
                                i2 = bitmapDescriptor.getBitmap().getHeight();
                                float width = ((float) i) / ((float) bitmapDescriptor.getBitmap().getWidth());
                                float f = ((float) i3) / ((float) i2);
                                this.f1008D = Util.m2355a(new float[]{0.0f, f, width, f, width, 0.0f, 0.0f, 0.0f});
                            } else {
                                return;
                            }
                        }
                        calFPoint();
                        this.f1020P = System.currentTimeMillis();
                        this.f1021b = true;
                    }
                    if (this.f1017M) {
                        iAMapDelegate.pixel2Map(this.f1018N, this.f1019O, this.f1033n);
                    }
                    m1557a(iAMapDelegate);
                    if (this.f1023d && isInfoWindowShown()) {
                        this.f1007C.mo8518k();
                        if (System.currentTimeMillis() - this.f1020P > 1000) {
                            this.f1023d = false;
                        }
                    }
                } catch (Throwable th2) {
                    SDKLogHandler.m2563a(th2, "MarkerDelegateImp", "drawMarker");
                }
            }
        }
    }

    /* renamed from: a */
    private int m1553a(GL10 gl10) {
        int texsureId = this.f1007C.f986a.getTexsureId();
        if (texsureId != 0) {
            return texsureId;
        }
        int[] iArr = new int[]{0};
        gl10.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    public boolean isAllowLow() {
        return this.f1014J;
    }

    public void setPeriod(int i) {
        if (i <= 1) {
            this.f1016L = 1;
        } else {
            this.f1016L = i;
        }
    }

    public void setObject(Object obj) {
        this.f1009E = obj;
    }

    public Object getObject() {
        return this.f1009E;
    }

    public void setPerspective(boolean z) {
        this.f1010F = z;
    }

    public boolean isPerspective() {
        return this.f1010F;
    }

    public int getTextureId() {
        try {
            if (this.f1011G == null || this.f1011G.size() <= 0) {
                return 0;
            }
            return this.f1035p[0];
        } catch (Throwable th) {
            return 0;
        }
    }

    public int getPeriod() {
        return this.f1016L;
    }

    public LatLng getRealPosition() {
        if (!this.f1017M) {
            return this.f1012H ? this.f1041v : this.f1040u;
        } else {
            this.f1007C.f986a.getMapProjection().win2Map(this.f1018N, this.f1019O, this.f1033n);
            DPoint dPoint = new DPoint();
            this.f1007C.f986a.getPixel2LatLng(this.f1018N, this.f1019O, dPoint);
            return new LatLng(dPoint.f4559y, dPoint.f4559y);
        }
    }

    public void set2Top() {
        this.f1007C.mo8508d(this);
    }

    public void setFlat(boolean z) throws RemoteException {
        this.f1026g = z;
        m1558c();
    }

    public boolean isFlat() {
        return this.f1026g;
    }

    public float getRotateAngle() {
        return this.f1025f;
    }

    public void setInfoWindowOffset(int i, int i2) throws RemoteException {
        this.f1027h = i;
        this.f1028i = i2;
    }

    public int getInfoWindowOffsetX() {
        return this.f1027h;
    }

    public int getInfoWindowOffsetY() {
        return this.f1028i;
    }

    public void setPositionByPixels(int i, int i2) {
        int i3 = 1;
        this.f1018N = i;
        this.f1019O = i2;
        this.f1017M = true;
        calFPoint();
        try {
            IAMapDelegate iAMapDelegate = this.f1007C.f986a;
            if (!this.f1026g) {
                i3 = 0;
            }
            this.f1034o = Util.m2365a(iAMapDelegate, i3, this.f1033n, this.f1024e, getWidth(), getHeight(), this.f1044y, this.f1045z);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "MarkerDelegateImp", "setPositionByPixels");
        }
        m1558c();
        if (isInfoWindowShown()) {
            showInfoWindow();
        }
    }

    public int getRealInfoWindowOffsetX() {
        return this.f1029j;
    }

    public int getRealInfoWindowOffsetY() {
        return this.f1030k;
    }

    public FPoint getMapPosition() {
        return this.f1033n;
    }

    public boolean isViewMode() {
        return this.f1017M;
    }

    public void setZIndex(float f) {
        this.f1036q = f;
        this.f1007C.mo8516i();
    }

    public float getZIndex() {
        return this.f1036q;
    }

    public boolean checkInBounds() {
        Rect rect = this.f1007C.f986a.getRect();
        if (this.f1017M || rect == null) {
            return true;
        }
        IPoint iPoint = new IPoint();
        if (this.f1033n != null) {
            this.f1007C.f986a.getMapProjection().map2Win(this.f1033n.f4560x, this.f1033n.f4561y, iPoint);
        }
        return rect.contains(iPoint.f4562x, iPoint.f4563y);
    }

    public void setGeoPoint(IPoint iPoint) {
        this.f1017M = false;
        this.f1031l = iPoint.f4562x;
        this.f1032m = iPoint.f4563y;
        DPoint dPoint = new DPoint();
        MapProjection.geo2LonLat(this.f1031l, this.f1032m, dPoint);
        this.f1040u = new LatLng(dPoint.f4559y, dPoint.f4558x, false);
        this.f1007C.f986a.getMapProjection().geo2Map(this.f1031l, this.f1032m, this.f1033n);
    }

    public IPoint getGeoPoint() {
        IPoint iPoint = new IPoint();
        if (!this.f1017M) {
            return new IPoint(this.f1031l, this.f1032m);
        }
        this.f1007C.f986a.getPixel2Geo(this.f1018N, this.f1019O, iPoint);
        return iPoint;
    }

    public void reLoadTexture() {
        this.f1013I = false;
        if (this.f1035p != null) {
            Arrays.fill(this.f1035p, 0);
        }
    }
}
