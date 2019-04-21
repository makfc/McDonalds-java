package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import com.amap.api.maps2d.model.TileProvider;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/* compiled from: LayerPropertys */
/* renamed from: com.amap.api.mapcore2d.am */
class C0886am extends LayerPropertys {
    /* renamed from: a */
    MapProjection f2237a;
    /* renamed from: b */
    public String f2238b = "";
    /* renamed from: c */
    public int f2239c = 18;
    /* renamed from: d */
    public int f2240d = 3;
    /* renamed from: e */
    public boolean f2241e = true;
    /* renamed from: f */
    public boolean f2242f = false;
    /* renamed from: g */
    public boolean f2243g = false;
    /* renamed from: h */
    public boolean f2244h = true;
    /* renamed from: i */
    public long f2245i = 0;
    /* renamed from: j */
    public UrlFormater f2246j = null;
    /* renamed from: k */
    TileProvider f2247k = null;
    /* renamed from: l */
    public int f2248l = -1;
    /* renamed from: m */
    public String f2249m = "";
    /* renamed from: n */
    MemoryTileManager f2250n = null;
    /* renamed from: o */
    DiskCachManager f2251o = null;
    /* renamed from: p */
    SyncList<TileCoordinate> f2252p = null;
    /* renamed from: r */
    private String f2253r = "LayerPropertys";
    /* renamed from: s */
    private boolean f2254s = false;

    public C0886am(MapProjection mapProjection) {
        this.f2237a = mapProjection;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo9758a(boolean z) {
        this.f2254s = z;
        if (z) {
            this.f2236q.mo9857d();
            return;
        }
        this.f2250n.mo9922c();
        this.f2236q.mo9856c();
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public boolean mo9759a() {
        return this.f2254s;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0886am)) {
            return false;
        }
        return this.f2238b.equals(((C0886am) obj).f2238b);
    }

    public int hashCode() {
        return this.f2248l;
    }

    public String toString() {
        return this.f2238b;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo9757a(Canvas canvas) {
        try {
            if (this.f2252p != null) {
                Iterator it = this.f2252p.iterator();
                while (it.hasNext()) {
                    TileCoordinate tileCoordinate = (TileCoordinate) it.next();
                    if (tileCoordinate.f2600g >= 0) {
                        Bitmap a = this.f2250n.mo9920a(tileCoordinate.f2600g);
                        PointF a2 = this.f2237a.mo9842a(tileCoordinate.f2595b, tileCoordinate.f2596c);
                        if (!(a == null || a2 == null)) {
                            canvas.drawBitmap(a, null, new RectF(a2.x, a2.y, a2.x + ((float) this.f2237a.f2305a), a2.y + ((float) this.f2237a.f2305a)), null);
                        }
                    } else if (this.f2241e) {
                    }
                }
            }
        } catch (ConcurrentModificationException e) {
            C0955ck.m3888a(e, this.f2253r, "drawLayer");
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public void mo9760b() {
        this.f2236q.mo10113e();
        this.f2251o.mo10323a(null);
        this.f2250n.mo9922c();
        this.f2252p.clear();
    }
}
