package com.amap.api.mapcore2d;

import android.graphics.Point;
import android.graphics.PointF;
import com.amap.api.mapcore2d.Mediator.C0910d;
import java.util.ArrayList;

/* renamed from: com.amap.api.mapcore2d.au */
class MapProjection {
    /* renamed from: a */
    public int f2305a = 256;
    /* renamed from: b */
    public int f2306b = 256;
    /* renamed from: c */
    float f2307c = 1.0f;
    /* renamed from: d */
    public double f2308d = 156543.0339d;
    /* renamed from: e */
    int f2309e = 0;
    /* renamed from: f */
    double f2310f = -2.003750834E7d;
    /* renamed from: g */
    double f2311g = 2.003750834E7d;
    /* renamed from: h */
    public int f2312h = C1042p.f3034d;
    /* renamed from: i */
    public int f2313i = C1042p.f3033c;
    /* renamed from: j */
    public float f2314j = 10.0f;
    /* renamed from: k */
    public double f2315k = 0.0d;
    /* renamed from: l */
    public GeoPoint f2316l = null;
    /* renamed from: m */
    public GeoPoint f2317m = null;
    /* renamed from: n */
    public Point f2318n = null;
    /* renamed from: o */
    public C0900a f2319o = null;
    /* renamed from: p */
    C0910d f2320p = null;
    /* renamed from: q */
    private double f2321q = 116.39716d;
    /* renamed from: r */
    private double f2322r = 39.91669d;
    /* renamed from: s */
    private double f2323s = 0.01745329251994329d;

    /* compiled from: MapProjection */
    /* renamed from: com.amap.api.mapcore2d.au$a */
    static class C0900a {
        /* renamed from: a */
        float f2301a;
        /* renamed from: b */
        float f2302b;
        /* renamed from: c */
        float f2303c;
        /* renamed from: d */
        float f2304d;

        C0900a() {
        }
    }

    public MapProjection(C0910d c0910d) {
        this.f2320p = c0910d;
    }

    /* renamed from: a */
    public void mo9848a() {
        this.f2308d = (this.f2311g * 2.0d) / ((double) this.f2305a);
        int i = (int) this.f2314j;
        this.f2315k = (this.f2308d / ((double) (1 << i))) / ((double) ((1.0f + this.f2314j) - ((float) i)));
        this.f2316l = mo9846a(new GeoPoint(this.f2322r, this.f2321q, true));
        this.f2317m = this.f2316l.mo10341g();
        this.f2318n = new Point(this.f2320p.mo9903c() / 2, this.f2320p.mo9904d() / 2);
        this.f2319o = new C0900a();
        this.f2319o.f2301a = -2.0037508E7f;
        this.f2319o.f2302b = 2.0037508E7f;
        this.f2319o.f2303c = 2.0037508E7f;
        this.f2319o.f2304d = -2.0037508E7f;
    }

    /* renamed from: a */
    public void mo9849a(Point point) {
        this.f2318n = point;
    }

    /* renamed from: a */
    public GeoPoint mo9846a(GeoPoint geoPoint) {
        if (geoPoint == null) {
            return null;
        }
        return new GeoPoint(((Math.log(Math.tan((((((double) geoPoint.mo10334b()) / 1000000.0d) + 90.0d) * 3.141592653589793d) / 360.0d)) / 0.017453292519943295d) * 2.003750834E7d) / 180.0d, ((((double) geoPoint.mo10332a()) / 1000000.0d) * 2.003750834E7d) / 180.0d, false);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public PointF mo9844a(GeoPoint geoPoint, GeoPoint geoPoint2, Point point, double d) {
        PointF pointF = new PointF();
        pointF.x = (float) (((geoPoint.mo10338e() - geoPoint2.mo10338e()) / d) + ((double) point.x));
        pointF.y = (float) (((double) point.y) - ((geoPoint.mo10340f() - geoPoint2.mo10340f()) / d));
        return pointF;
    }

    /* renamed from: b */
    public GeoPoint mo9854b(GeoPoint geoPoint) {
        return new GeoPoint((int) (((double) ((float) (57.29577951308232d * ((2.0d * Math.atan(Math.exp((((double) ((float) ((geoPoint.mo10340f() * 180.0d) / 2.003750834E7d))) * 3.141592653589793d) / 180.0d))) - 1.5707963267948966d)))) * 1000000.0d), (int) (((double) ((float) ((geoPoint.mo10338e() * 180.0d) / 2.003750834E7d))) * 1000000.0d));
    }

    /* renamed from: a */
    public GeoPoint mo9845a(PointF pointF, GeoPoint geoPoint, Point point, double d, C0900a c0900a) {
        return mo9854b(mo9853b(pointF, geoPoint, point, d, c0900a));
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public GeoPoint mo9853b(PointF pointF, GeoPoint geoPoint, Point point, double d, C0900a c0900a) {
        PointF c = this.f2320p.mo9907g().mo10026c(pointF);
        float f = c.y - ((float) point.y);
        double e = (((double) (c.x - ((float) point.x))) * d) + geoPoint.mo10338e();
        double f2 = geoPoint.mo10340f() - (((double) f) * d);
        while (e < ((double) c0900a.f2301a)) {
            e += (double) (c0900a.f2302b - c0900a.f2301a);
        }
        double d2 = e;
        while (d2 > ((double) c0900a.f2302b)) {
            d2 -= (double) (c0900a.f2302b - c0900a.f2301a);
        }
        e = f2;
        while (e < ((double) c0900a.f2304d)) {
            e += (double) (c0900a.f2303c - c0900a.f2304d);
        }
        f2 = e;
        while (f2 > ((double) c0900a.f2303c)) {
            f2 -= (double) (c0900a.f2303c - c0900a.f2304d);
        }
        return new GeoPoint(f2, d2, false);
    }

    /* renamed from: b */
    public PointF mo9852b(GeoPoint geoPoint, GeoPoint geoPoint2, Point point, double d) {
        return this.f2320p.mo9907g().mo10021b(mo9844a(mo9846a(geoPoint), geoPoint2, point, d));
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public int mo9851b() {
        int i = (int) this.f2314j;
        return ((double) (this.f2314j - ((float) i))) < Mediator.f2382a ? i : i + 1;
    }

    /* renamed from: a */
    public PointF mo9842a(int i, int i2) {
        double d = 0.0d;
        double d2 = this.f2310f + (((double) (this.f2305a * i)) * this.f2315k);
        if (this.f2309e == 0) {
            d = this.f2311g - (((double) (this.f2305a * i2)) * this.f2315k);
        } else if (this.f2309e == 1) {
            d = this.f2315k * ((double) ((i2 + 1) * this.f2305a));
        }
        return mo9844a(new GeoPoint(d, d2, false), this.f2316l, this.f2318n, this.f2315k);
    }

    /* renamed from: a */
    public ArrayList<TileCoordinate> mo9847a(GeoPoint geoPoint, int i, int i2, int i3) {
        int f;
        int i4;
        String str = "getTilesInDomain";
        double d = this.f2315k;
        int e = (int) ((geoPoint.mo10338e() - this.f2310f) / (((double) this.f2305a) * d));
        double d2 = this.f2310f + (((double) (this.f2305a * e)) * d);
        double d3 = 0.0d;
        if (this.f2309e == 0) {
            f = (int) ((this.f2311g - geoPoint.mo10340f()) / (((double) this.f2305a) * d));
            d3 = this.f2311g - (((double) (this.f2305a * f)) * d);
            i4 = f;
        } else if (this.f2309e == 1) {
            f = (int) ((geoPoint.mo10340f() - this.f2311g) / (((double) this.f2305a) * d));
            d3 = ((double) ((f + 1) * this.f2305a)) * d;
            i4 = f;
        } else {
            i4 = 0;
        }
        PointF a = mo9844a(new GeoPoint(d3, d2, false), geoPoint, this.f2318n, d);
        TileCoordinate tileCoordinate = new TileCoordinate(e, i4, mo9851b(), -1);
        tileCoordinate.f2599f = a;
        ArrayList arrayList = new ArrayList();
        arrayList.add(tileCoordinate);
        f = 1;
        while (true) {
            int i5;
            PointF a2;
            TileCoordinate tileCoordinate2;
            PointF a3;
            Object obj;
            TileCoordinate tileCoordinate3;
            int i6 = f;
            Object obj2 = null;
            int i7 = e - i6;
            while (i7 <= e + i6) {
                i5 = i4 + i6;
                try {
                    a2 = mo9843a(i7, i5, e, i4, a, i2, i3);
                    if (a2 != null) {
                        if (obj2 == null) {
                            obj2 = 1;
                        }
                        tileCoordinate2 = new TileCoordinate(i7, i5, mo9851b(), -1);
                        tileCoordinate2.f2599f = a2;
                        arrayList.add(tileCoordinate2);
                    }
                    i5 = i4 - i6;
                    a3 = mo9843a(i7, i5, e, i4, a, i2, i3);
                    if (a3 != null) {
                        if (obj2 == null) {
                            obj = 1;
                        } else {
                            obj = obj2;
                        }
                        tileCoordinate3 = new TileCoordinate(i7, i5, mo9851b(), -1);
                        tileCoordinate3.f2599f = a3;
                        arrayList.add(tileCoordinate3);
                    } else {
                        obj = obj2;
                    }
                    i7++;
                    obj2 = obj;
                } catch (Error e2) {
                    C0955ck.m3888a(e2, "MapProjection", str);
                }
            }
            i5 = (i4 + i6) - 1;
            while (i5 > i4 - i6) {
                i7 = e + i6;
                a2 = mo9843a(i7, i5, e, i4, a, i2, i3);
                if (a2 != null) {
                    if (obj2 == null) {
                        obj2 = 1;
                    }
                    tileCoordinate2 = new TileCoordinate(i7, i5, mo9851b(), -1);
                    tileCoordinate2.f2599f = a2;
                    arrayList.add(tileCoordinate2);
                }
                i7 = e - i6;
                a3 = mo9843a(i7, i5, e, i4, a, i2, i3);
                if (a3 != null) {
                    if (obj2 == null) {
                        obj = 1;
                    } else {
                        obj = obj2;
                    }
                    tileCoordinate3 = new TileCoordinate(i7, i5, mo9851b(), -1);
                    tileCoordinate3.f2599f = a3;
                    arrayList.add(tileCoordinate3);
                } else {
                    obj = obj2;
                }
                i5--;
                obj2 = obj;
            }
            if (obj2 == null) {
                break;
            }
            f = i6 + 1;
        }
        return arrayList;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public PointF mo9843a(int i, int i2, int i3, int i4, PointF pointF, int i5, int i6) {
        PointF pointF2 = new PointF();
        pointF2.x = ((float) ((i - i3) * this.f2305a)) + pointF.x;
        if (this.f2309e == 0) {
            pointF2.y = ((float) ((i2 - i4) * this.f2305a)) + pointF.y;
        } else if (this.f2309e == 1) {
            pointF2.y = pointF.y - ((float) ((i2 - i4) * this.f2305a));
        }
        if (pointF2.x + ((float) this.f2305a) <= 0.0f || pointF2.x >= ((float) i5) || pointF2.y + ((float) this.f2305a) <= 0.0f || pointF2.y >= ((float) i6)) {
            return null;
        }
        return pointF2;
    }

    /* renamed from: a */
    public void mo9850a(PointF pointF, PointF pointF2, float f) {
        double d = this.f2315k;
        GeoPoint b = mo9853b(pointF, this.f2316l, this.f2318n, d, this.f2319o);
        GeoPoint b2 = mo9853b(pointF2, this.f2316l, this.f2318n, d, this.f2319o);
        d = b2.mo10340f() - b.mo10340f();
        double e = this.f2316l.mo10338e() + (b2.mo10338e() - b.mo10338e());
        double f2 = this.f2316l.mo10340f() + d;
        while (e < ((double) this.f2319o.f2301a)) {
            e += (double) (this.f2319o.f2302b - this.f2319o.f2301a);
        }
        while (e > ((double) this.f2319o.f2302b)) {
            e -= (double) (this.f2319o.f2302b - this.f2319o.f2301a);
        }
        while (f2 < ((double) this.f2319o.f2304d)) {
            f2 += (double) (this.f2319o.f2303c - this.f2319o.f2304d);
        }
        while (f2 > ((double) this.f2319o.f2303c)) {
            f2 -= (double) (this.f2319o.f2303c - this.f2319o.f2304d);
        }
        this.f2316l.mo10335b(f2);
        this.f2316l.mo10333a(e);
    }

    /* renamed from: a */
    public float mo9841a(GeoPoint geoPoint, GeoPoint geoPoint2) {
        double a = C1043q.m4373a(geoPoint.mo10336c());
        double a2 = C1043q.m4373a(geoPoint.mo10337d());
        double a3 = C1043q.m4373a(geoPoint2.mo10336c());
        a *= this.f2323s;
        a2 *= this.f2323s;
        a3 *= this.f2323s;
        double a4 = C1043q.m4373a(geoPoint2.mo10337d()) * this.f2323s;
        double sin = Math.sin(a);
        double sin2 = Math.sin(a2);
        a = Math.cos(a);
        a2 = Math.cos(a2);
        double sin3 = Math.sin(a3);
        double sin4 = Math.sin(a4);
        a3 = Math.cos(a3);
        a4 = Math.cos(a4);
        r18 = new double[3];
        double[] dArr = new double[]{a * a2, a2 * sin, sin2};
        dArr[0] = a4 * a3;
        dArr[1] = a4 * sin3;
        dArr[2] = sin4;
        return (float) (Math.asin(Math.sqrt((((r18[0] - dArr[0]) * (r18[0] - dArr[0])) + ((r18[1] - dArr[1]) * (r18[1] - dArr[1]))) + ((r18[2] - dArr[2]) * (r18[2] - dArr[2]))) / 2.0d) * 1.27420015798544E7d);
    }
}
