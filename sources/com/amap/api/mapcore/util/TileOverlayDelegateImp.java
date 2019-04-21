package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import com.amap.api.mapcore.util.AsyncTask.C0746d;
import com.amap.api.mapcore.util.ImageCache.C0808a;
import com.amap.api.mapcore.util.ImageWorker.C0809a;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.model.TileProvider;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.ITileOverlayDelegate;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.util.av */
public class TileOverlayDelegateImp implements ITileOverlayDelegate {
    /* renamed from: g */
    private static int f1247g = 0;
    /* renamed from: a */
    private TileOverlayView f1248a;
    /* renamed from: b */
    private TileProvider f1249b;
    /* renamed from: c */
    private Float f1250c;
    /* renamed from: d */
    private boolean f1251d;
    /* renamed from: e */
    private boolean f1252e;
    /* renamed from: f */
    private IAMapDelegate f1253f;
    /* renamed from: h */
    private int f1254h;
    /* renamed from: i */
    private int f1255i;
    /* renamed from: j */
    private int f1256j;
    /* renamed from: k */
    private ImageFetcher f1257k;
    /* renamed from: l */
    private CopyOnWriteArrayList<C0738a> f1258l;
    /* renamed from: m */
    private boolean f1259m;
    /* renamed from: n */
    private C0747b f1260n;
    /* renamed from: o */
    private String f1261o;
    /* renamed from: p */
    private FloatBuffer f1262p;

    /* compiled from: TileOverlayDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.av$a */
    public class C0738a implements Cloneable {
        /* renamed from: a */
        public int f1207a;
        /* renamed from: b */
        public int f1208b;
        /* renamed from: c */
        public int f1209c;
        /* renamed from: d */
        public int f1210d;
        /* renamed from: e */
        public IPoint f1211e;
        /* renamed from: f */
        public int f1212f = 0;
        /* renamed from: g */
        public boolean f1213g = false;
        /* renamed from: h */
        public FloatBuffer f1214h = null;
        /* renamed from: i */
        public Bitmap f1215i = null;
        /* renamed from: j */
        public C0809a f1216j = null;
        /* renamed from: k */
        public int f1217k = 0;

        public C0738a(int i, int i2, int i3, int i4) {
            this.f1207a = i;
            this.f1208b = i2;
            this.f1209c = i3;
            this.f1210d = i4;
        }

        public C0738a(C0738a c0738a) {
            this.f1207a = c0738a.f1207a;
            this.f1208b = c0738a.f1208b;
            this.f1209c = c0738a.f1209c;
            this.f1210d = c0738a.f1210d;
            this.f1211e = c0738a.f1211e;
            this.f1214h = c0738a.f1214h;
        }

        /* renamed from: a */
        public C0738a clone() {
            try {
                C0738a c0738a = (C0738a) super.clone();
                c0738a.f1207a = this.f1207a;
                c0738a.f1208b = this.f1208b;
                c0738a.f1209c = this.f1209c;
                c0738a.f1210d = this.f1210d;
                c0738a.f1211e = (IPoint) this.f1211e.clone();
                c0738a.f1214h = this.f1214h.asReadOnlyBuffer();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return new C0738a(this);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof C0738a)) {
                return false;
            }
            C0738a c0738a = (C0738a) obj;
            if (this.f1207a == c0738a.f1207a && this.f1208b == c0738a.f1208b && this.f1209c == c0738a.f1209c && this.f1210d == c0738a.f1210d) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (((this.f1207a * 7) + (this.f1208b * 11)) + (this.f1209c * 13)) + this.f1210d;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.f1207a);
            stringBuilder.append("-");
            stringBuilder.append(this.f1208b);
            stringBuilder.append("-");
            stringBuilder.append(this.f1209c);
            stringBuilder.append("-");
            stringBuilder.append(this.f1210d);
            return stringBuilder.toString();
        }

        /* renamed from: a */
        public void mo8698a(Bitmap bitmap) {
            if (bitmap != null && !bitmap.isRecycled()) {
                try {
                    this.f1216j = null;
                    this.f1215i = Util.m2346a(bitmap, Util.m2339a(bitmap.getWidth()), Util.m2339a(bitmap.getHeight()));
                    TileOverlayDelegateImp.this.f1253f.setRunLowFrame(false);
                } catch (Throwable th) {
                    SDKLogHandler.m2563a(th, "TileOverlayDelegateImp", "setBitmap");
                    th.printStackTrace();
                    if (this.f1217k < 3) {
                        TileOverlayDelegateImp.this.f1257k.mo9253a(true, this);
                        this.f1217k++;
                    }
                }
            } else if (this.f1217k < 3) {
                TileOverlayDelegateImp.this.f1257k.mo9253a(true, this);
                this.f1217k++;
            }
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }

        /* renamed from: b */
        public void mo8699b() {
            try {
                ImageWorker.m2282a(this);
                if (this.f1213g) {
                    TileOverlayDelegateImp.this.f1248a.f1268c.add(Integer.valueOf(this.f1212f));
                }
                this.f1213g = false;
                this.f1212f = 0;
                if (!(this.f1215i == null || this.f1215i.isRecycled())) {
                    this.f1215i.recycle();
                }
                this.f1215i = null;
                if (this.f1214h != null) {
                    this.f1214h.clear();
                }
                this.f1214h = null;
                this.f1216j = null;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* compiled from: TileOverlayDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.av$b */
    private class C0747b extends AsyncTask<IAMapDelegate, Void, List<C0738a>> {
        /* renamed from: e */
        private int f1245e;
        /* renamed from: f */
        private boolean f1246f;

        public C0747b(boolean z) {
            this.f1246f = z;
        }

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: a */
        public List<C0738a> mo8706a(IAMapDelegate... iAMapDelegateArr) {
            try {
                int mapWidth = iAMapDelegateArr[0].getMapWidth();
                int mapHeight = iAMapDelegateArr[0].getMapHeight();
                this.f1245e = (int) iAMapDelegateArr[0].getZoomLevel();
                if (mapWidth <= 0 || mapHeight <= 0) {
                    return null;
                }
                return TileOverlayDelegateImp.this.m1680a(this.f1245e, mapWidth, mapHeight);
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public void mo8707a(List<C0738a> list) {
            if (list != null) {
                try {
                    if (list.size() > 0) {
                        TileOverlayDelegateImp.this.m1685a((List) list, this.f1245e, this.f1246f);
                        list.clear();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    private static String m1679a(String str) {
        f1247g++;
        return str + f1247g;
    }

    public TileOverlayDelegateImp(TileOverlayOptions tileOverlayOptions, TileOverlayView tileOverlayView) {
        this.f1252e = false;
        this.f1254h = 256;
        this.f1255i = 256;
        this.f1256j = -1;
        this.f1258l = new CopyOnWriteArrayList();
        this.f1259m = false;
        this.f1260n = null;
        this.f1261o = null;
        this.f1262p = null;
        this.f1248a = tileOverlayView;
        this.f1249b = tileOverlayOptions.getTileProvider();
        this.f1254h = this.f1249b.getTileWidth();
        this.f1255i = this.f1249b.getTileHeight();
        int a = Util.m2339a(this.f1254h);
        float f = ((float) this.f1254h) / ((float) a);
        float a2 = ((float) this.f1255i) / ((float) Util.m2339a(this.f1255i));
        this.f1262p = Util.m2355a(new float[]{0.0f, a2, f, a2, f, 0.0f, 0.0f, 0.0f});
        this.f1250c = Float.valueOf(tileOverlayOptions.getZIndex());
        this.f1251d = tileOverlayOptions.isVisible();
        this.f1261o = getId();
        this.f1253f = this.f1248a.mo8743a();
        this.f1256j = Integer.valueOf(this.f1261o.substring("TileOverlay".length())).intValue();
        C0808a c0808a = new C0808a(this.f1248a.getContext(), this.f1261o);
        c0808a.mo9239a(tileOverlayOptions.getMemoryCacheEnabled());
        c0808a.mo9241b(tileOverlayOptions.getDiskCacheEnabled());
        c0808a.mo9237a(tileOverlayOptions.getMemCacheSize());
        c0808a.mo9240b(tileOverlayOptions.getDiskCacheSize());
        String diskCacheDir = tileOverlayOptions.getDiskCacheDir();
        if (!(diskCacheDir == null || diskCacheDir.equals(""))) {
            c0808a.mo9238a(diskCacheDir);
        }
        this.f1257k = new ImageFetcher(this.f1248a.getContext(), this.f1254h, this.f1255i);
        this.f1257k.mo9267a(this.f1249b);
        this.f1257k.mo9251a(c0808a);
        refresh(true);
    }

    public TileOverlayDelegateImp(TileOverlayOptions tileOverlayOptions, TileOverlayView tileOverlayView, boolean z) {
        this(tileOverlayOptions, tileOverlayView);
        this.f1252e = z;
    }

    public void remove() {
        if (this.f1260n != null && this.f1260n.mo8704a() == C0746d.RUNNING) {
            this.f1260n.mo8708a(true);
        }
        Iterator it = this.f1258l.iterator();
        while (it.hasNext()) {
            ((C0738a) it.next()).mo8699b();
        }
        this.f1258l.clear();
        this.f1257k.mo9261h();
        this.f1248a.mo8749b((ITileOverlayDelegate) this);
        this.f1253f.setRunLowFrame(false);
    }

    public void clearTileCache() {
        this.f1257k.mo9259f();
    }

    public String getId() {
        if (this.f1261o == null) {
            this.f1261o = TileOverlayDelegateImp.m1679a("TileOverlay");
        }
        return this.f1261o;
    }

    public void setZIndex(float f) {
        this.f1250c = Float.valueOf(f);
        this.f1248a.mo8750c();
    }

    public float getZIndex() {
        return this.f1250c.floatValue();
    }

    public void setVisible(boolean z) {
        this.f1251d = z;
        this.f1253f.setRunLowFrame(false);
        if (z) {
            refresh(true);
        }
    }

    public boolean isVisible() {
        return this.f1251d;
    }

    public boolean equalsRemote(ITileOverlayDelegate iTileOverlayDelegate) {
        if (equals(iTileOverlayDelegate) || iTileOverlayDelegate.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    public int hashCodeRemote() {
        return super.hashCode();
    }

    /* renamed from: a */
    private boolean m1683a(C0738a c0738a) {
        MapProjection mapProjection = this.f1253f.getMapProjection();
        float f = (float) c0738a.f1209c;
        int i = this.f1254h;
        int i2 = this.f1255i;
        int i3 = c0738a.f1211e.f4562x;
        int i4 = c0738a.f1211e.f4563y + ((1 << (20 - ((int) f))) * i2);
        r6 = new float[12];
        FPoint fPoint = new FPoint();
        mapProjection.geo2Map(i3, i4, fPoint);
        FPoint fPoint2 = new FPoint();
        mapProjection.geo2Map(((1 << (20 - ((int) f))) * i) + i3, i4, fPoint2);
        FPoint fPoint3 = new FPoint();
        mapProjection.geo2Map((i * (1 << (20 - ((int) f)))) + i3, i4 - ((1 << (20 - ((int) f))) * i2), fPoint3);
        FPoint fPoint4 = new FPoint();
        mapProjection.geo2Map(i3, i4 - ((1 << (20 - ((int) f))) * i2), fPoint4);
        r6[0] = fPoint.f4560x;
        r6[1] = fPoint.f4561y;
        r6[2] = 0.0f;
        r6[3] = fPoint2.f4560x;
        r6[4] = fPoint2.f4561y;
        r6[5] = 0.0f;
        r6[6] = fPoint3.f4560x;
        r6[7] = fPoint3.f4561y;
        r6[8] = 0.0f;
        r6[9] = fPoint4.f4560x;
        r6[10] = fPoint4.f4561y;
        r6[11] = 0.0f;
        if (c0738a.f1214h == null) {
            c0738a.f1214h = Util.m2355a(r6);
        } else {
            c0738a.f1214h = Util.m2356a(r6, c0738a.f1214h);
        }
        return true;
    }

    /* renamed from: a */
    private void m1682a(GL10 gl10, int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (floatBuffer != null && floatBuffer2 != null) {
            gl10.glEnable(3042);
            gl10.glTexEnvf(8960, 8704, 8448.0f);
            gl10.glBlendFunc(1, 771);
            gl10.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            gl10.glEnable(3553);
            gl10.glEnableClientState(32884);
            gl10.glEnableClientState(32888);
            gl10.glBindTexture(3553, i);
            gl10.glVertexPointer(3, 5126, 0, floatBuffer);
            gl10.glTexCoordPointer(2, 5126, 0, floatBuffer2);
            gl10.glDrawArrays(6, 0, 4);
            gl10.glDisableClientState(32884);
            gl10.glDisableClientState(32888);
            gl10.glDisable(3553);
            gl10.glDisable(3042);
        }
    }

    public void drawTiles(GL10 gl10) {
        if (this.f1258l != null && this.f1258l.size() != 0) {
            Iterator it = this.f1258l.iterator();
            while (it.hasNext()) {
                C0738a c0738a = (C0738a) it.next();
                if (!c0738a.f1213g) {
                    try {
                        IPoint iPoint = c0738a.f1211e;
                        if (!(c0738a.f1215i == null || c0738a.f1215i.isRecycled() || iPoint == null)) {
                            c0738a.f1212f = Util.m2341a(gl10, c0738a.f1215i);
                            if (c0738a.f1212f != 0) {
                                c0738a.f1213g = true;
                            }
                            c0738a.f1215i = null;
                        }
                    } catch (Throwable th) {
                        SDKLogHandler.m2563a(th, "TileOverlayDelegateImp", "drawTiles");
                    }
                }
                if (c0738a.f1213g) {
                    m1683a(c0738a);
                    m1682a(gl10, c0738a.f1212f, c0738a.f1214h, this.f1262p);
                }
            }
        }
    }

    /* renamed from: a */
    private ArrayList<C0738a> m1680a(int i, int i2, int i3) {
        MapProjection mapProjection = this.f1253f.getMapProjection();
        FPoint fPoint = new FPoint();
        IPoint iPoint = new IPoint();
        IPoint iPoint2 = new IPoint();
        mapProjection.win2Map(0, 0, fPoint);
        mapProjection.map2Geo(fPoint.f4560x, fPoint.f4561y, iPoint);
        int min = Math.min(Integer.MAX_VALUE, iPoint.f4562x);
        int max = Math.max(0, iPoint.f4562x);
        int min2 = Math.min(Integer.MAX_VALUE, iPoint.f4563y);
        int max2 = Math.max(0, iPoint.f4563y);
        mapProjection.win2Map(i2, 0, fPoint);
        mapProjection.map2Geo(fPoint.f4560x, fPoint.f4561y, iPoint);
        min = Math.min(min, iPoint.f4562x);
        max = Math.max(max, iPoint.f4562x);
        min2 = Math.min(min2, iPoint.f4563y);
        max2 = Math.max(max2, iPoint.f4563y);
        mapProjection.win2Map(0, i3, fPoint);
        mapProjection.map2Geo(fPoint.f4560x, fPoint.f4561y, iPoint);
        min = Math.min(min, iPoint.f4562x);
        max = Math.max(max, iPoint.f4562x);
        min2 = Math.min(min2, iPoint.f4563y);
        max2 = Math.max(max2, iPoint.f4563y);
        mapProjection.win2Map(i2, i3, fPoint);
        mapProjection.map2Geo(fPoint.f4560x, fPoint.f4561y, iPoint);
        min = Math.min(min, iPoint.f4562x);
        int max3 = Math.max(max, iPoint.f4562x);
        max = Math.min(min2, iPoint.f4563y);
        int max4 = Math.max(max2, iPoint.f4563y);
        int i4 = min - ((1 << (20 - i)) * this.f1254h);
        int i5 = max - ((1 << (20 - i)) * this.f1255i);
        mapProjection.getGeoCenter(iPoint2);
        max = (iPoint2.f4562x >> (20 - i)) / this.f1254h;
        min2 = (iPoint2.f4563y >> (20 - i)) / this.f1255i;
        int i6 = (max << (20 - i)) * this.f1254h;
        int i7 = (min2 << (20 - i)) * this.f1255i;
        C0738a c0738a = new C0738a(max, min2, i, this.f1256j);
        c0738a.f1211e = new IPoint(i6, i7);
        ArrayList arrayList = new ArrayList();
        arrayList.add(c0738a);
        int i8 = 1;
        while (true) {
            IPoint iPoint3;
            C0738a c0738a2;
            min = i8;
            Object obj = null;
            for (i6 = max - min; i6 <= max + min; i6++) {
                i7 = min2 + min;
                iPoint3 = new IPoint((i6 << (20 - i)) * this.f1254h, (i7 << (20 - i)) * this.f1255i);
                if (iPoint3.f4562x < max3 && iPoint3.f4562x > i4 && iPoint3.f4563y < max4 && iPoint3.f4563y > i5) {
                    if (obj == null) {
                        obj = 1;
                    }
                    c0738a2 = new C0738a(i6, i7, i, this.f1256j);
                    c0738a2.f1211e = iPoint3;
                    arrayList.add(c0738a2);
                }
                i7 = min2 - min;
                iPoint3 = new IPoint((i6 << (20 - i)) * this.f1254h, (i7 << (20 - i)) * this.f1255i);
                if (iPoint3.f4562x < max3 && iPoint3.f4562x > i4 && iPoint3.f4563y < max4 && iPoint3.f4563y > i5) {
                    if (obj == null) {
                        obj = 1;
                    }
                    c0738a2 = new C0738a(i6, i7, i, this.f1256j);
                    c0738a2.f1211e = iPoint3;
                    arrayList.add(c0738a2);
                }
            }
            for (i7 = (min2 + min) - 1; i7 > min2 - min; i7--) {
                i6 = max + min;
                iPoint3 = new IPoint((i6 << (20 - i)) * this.f1254h, (i7 << (20 - i)) * this.f1255i);
                if (iPoint3.f4562x < max3 && iPoint3.f4562x > i4 && iPoint3.f4563y < max4 && iPoint3.f4563y > i5) {
                    if (obj == null) {
                        obj = 1;
                    }
                    c0738a2 = new C0738a(i6, i7, i, this.f1256j);
                    c0738a2.f1211e = iPoint3;
                    arrayList.add(c0738a2);
                }
                i6 = max - min;
                iPoint3 = new IPoint((i6 << (20 - i)) * this.f1254h, (i7 << (20 - i)) * this.f1255i);
                if (iPoint3.f4562x < max3 && iPoint3.f4562x > i4 && iPoint3.f4563y < max4 && iPoint3.f4563y > i5) {
                    if (obj == null) {
                        obj = 1;
                    }
                    c0738a2 = new C0738a(i6, i7, i, this.f1256j);
                    c0738a2.f1211e = iPoint3;
                    arrayList.add(c0738a2);
                }
            }
            if (obj == null) {
                return arrayList;
            }
            i8 = min + 1;
        }
    }

    /* renamed from: a */
    private boolean m1685a(List<C0738a> list, int i, boolean z) {
        int i2 = 0;
        if (list == null) {
            return false;
        }
        if (this.f1258l == null) {
            return false;
        }
        C0738a c0738a;
        int i3;
        Iterator it = this.f1258l.iterator();
        while (it.hasNext()) {
            c0738a = (C0738a) it.next();
            for (C0738a c0738a2 : list) {
                if (c0738a.equals(c0738a2) && c0738a.f1213g) {
                    c0738a2.f1213g = c0738a.f1213g;
                    c0738a2.f1212f = c0738a.f1212f;
                    i3 = 1;
                    break;
                }
            }
            i3 = 0;
            if (i3 == 0) {
                c0738a.mo8699b();
            }
        }
        this.f1258l.clear();
        if (i > ((int) this.f1253f.getMaxZoomLevel()) || i < ((int) this.f1253f.getMinZoomLevel())) {
            return false;
        }
        i3 = list.size();
        if (i3 <= 0) {
            return false;
        }
        while (i2 < i3) {
            c0738a = (C0738a) list.get(i2);
            if (c0738a != null && (!this.f1252e || (c0738a.f1209c >= 10 && !RegionUtil.m2326a(c0738a.f1207a, c0738a.f1208b, c0738a.f1209c)))) {
                this.f1258l.add(c0738a);
                if (!c0738a.f1213g) {
                    this.f1257k.mo9253a(z, c0738a);
                }
            }
            i2++;
        }
        return true;
    }

    public void refresh(boolean z) {
        if (!this.f1259m) {
            if (this.f1260n != null && this.f1260n.mo8704a() == C0746d.RUNNING) {
                this.f1260n.mo8708a(true);
            }
            this.f1260n = new C0747b(z);
            this.f1260n.mo8712c((Object[]) new IAMapDelegate[]{this.f1253f});
        }
    }

    public void onPause() {
        this.f1257k.mo9255b(false);
        this.f1257k.mo9252a(true);
        this.f1257k.mo9260g();
    }

    public void onResume() {
        this.f1257k.mo9252a(false);
    }

    public void onFling(boolean z) {
        if (this.f1259m != z) {
            this.f1259m = z;
            this.f1257k.mo9255b(z);
        }
    }

    public void reLoadTexture() {
        if (this.f1258l != null && this.f1258l.size() != 0) {
            Iterator it = this.f1258l.iterator();
            while (it.hasNext()) {
                C0738a c0738a = (C0738a) it.next();
                c0738a.f1213g = false;
                c0738a.f1212f = 0;
            }
        }
    }
}
