package com.amap.api.mapcore.util;

import com.autonavi.amap.mapcore.DPoint;

/* renamed from: com.amap.api.mapcore.util.cy */
public class Bounds {
    /* renamed from: a */
    public final double f1692a;
    /* renamed from: b */
    public final double f1693b;
    /* renamed from: c */
    public final double f1694c;
    /* renamed from: d */
    public final double f1695d;
    /* renamed from: e */
    public final double f1696e;
    /* renamed from: f */
    public final double f1697f;

    public Bounds(double d, double d2, double d3, double d4) {
        this.f1692a = d;
        this.f1693b = d3;
        this.f1694c = d2;
        this.f1695d = d4;
        this.f1696e = (d + d2) / 2.0d;
        this.f1697f = (d3 + d4) / 2.0d;
    }

    /* renamed from: a */
    public boolean mo9220a(double d, double d2) {
        return this.f1692a <= d && d <= this.f1694c && this.f1693b <= d2 && d2 <= this.f1695d;
    }

    /* renamed from: a */
    public boolean mo9223a(DPoint dPoint) {
        return mo9220a(dPoint.f4558x, dPoint.f4559y);
    }

    /* renamed from: a */
    public boolean mo9221a(double d, double d2, double d3, double d4) {
        return d < this.f1694c && this.f1692a < d2 && d3 < this.f1695d && this.f1693b < d4;
    }

    /* renamed from: a */
    public boolean mo9222a(Bounds bounds) {
        return mo9221a(bounds.f1692a, bounds.f1694c, bounds.f1693b, bounds.f1695d);
    }

    /* renamed from: b */
    public boolean mo9224b(Bounds bounds) {
        return bounds.f1692a >= this.f1692a && bounds.f1694c <= this.f1694c && bounds.f1693b >= this.f1693b && bounds.f1695d <= this.f1695d;
    }
}
