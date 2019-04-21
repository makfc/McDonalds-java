package com.amap.api.maps.model;

import com.amap.api.mapcore.util.Bounds;
import com.autonavi.amap.mapcore.DPoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: com.amap.api.maps.model.c */
class PointQuadTree {
    /* renamed from: a */
    private final Bounds f3287a;
    /* renamed from: b */
    private final int f3288b;
    /* renamed from: c */
    private List<WeightedLatLng> f3289c;
    /* renamed from: d */
    private List<PointQuadTree> f3290d;

    protected PointQuadTree(Bounds bounds) {
        this(bounds, 0);
    }

    private PointQuadTree(double d, double d2, double d3, double d4, int i) {
        this(new Bounds(d, d2, d3, d4), i);
    }

    private PointQuadTree(Bounds bounds, int i) {
        this.f3290d = null;
        this.f3287a = bounds;
        this.f3288b = i;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo11075a(WeightedLatLng weightedLatLng) {
        DPoint point = weightedLatLng.getPoint();
        if (this.f3287a.mo9220a(point.f4558x, point.f4559y)) {
            m4489a(point.f4558x, point.f4559y, weightedLatLng);
        }
    }

    /* renamed from: a */
    private void m4489a(double d, double d2, WeightedLatLng weightedLatLng) {
        if (this.f3290d == null) {
            if (this.f3289c == null) {
                this.f3289c = new ArrayList();
            }
            this.f3289c.add(weightedLatLng);
            if (this.f3289c.size() > 50 && this.f3288b < 40) {
                m4488a();
            }
        } else if (d2 < this.f3287a.f1697f) {
            if (d < this.f3287a.f1696e) {
                ((PointQuadTree) this.f3290d.get(0)).m4489a(d, d2, weightedLatLng);
            } else {
                ((PointQuadTree) this.f3290d.get(1)).m4489a(d, d2, weightedLatLng);
            }
        } else if (d < this.f3287a.f1696e) {
            ((PointQuadTree) this.f3290d.get(2)).m4489a(d, d2, weightedLatLng);
        } else {
            ((PointQuadTree) this.f3290d.get(3)).m4489a(d, d2, weightedLatLng);
        }
    }

    /* renamed from: a */
    private void m4488a() {
        this.f3290d = new ArrayList(4);
        this.f3290d.add(new PointQuadTree(this.f3287a.f1692a, this.f3287a.f1696e, this.f3287a.f1693b, this.f3287a.f1697f, this.f3288b + 1));
        this.f3290d.add(new PointQuadTree(this.f3287a.f1696e, this.f3287a.f1694c, this.f3287a.f1693b, this.f3287a.f1697f, this.f3288b + 1));
        this.f3290d.add(new PointQuadTree(this.f3287a.f1692a, this.f3287a.f1696e, this.f3287a.f1697f, this.f3287a.f1695d, this.f3288b + 1));
        this.f3290d.add(new PointQuadTree(this.f3287a.f1696e, this.f3287a.f1694c, this.f3287a.f1697f, this.f3287a.f1695d, this.f3288b + 1));
        List<WeightedLatLng> list = this.f3289c;
        this.f3289c = null;
        for (WeightedLatLng weightedLatLng : list) {
            m4489a(weightedLatLng.getPoint().f4558x, weightedLatLng.getPoint().f4559y, weightedLatLng);
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public Collection<WeightedLatLng> mo11074a(Bounds bounds) {
        ArrayList arrayList = new ArrayList();
        m4490a(bounds, arrayList);
        return arrayList;
    }

    /* renamed from: a */
    private void m4490a(Bounds bounds, Collection<WeightedLatLng> collection) {
        if (!this.f3287a.mo9222a(bounds)) {
            return;
        }
        if (this.f3290d != null) {
            for (PointQuadTree a : this.f3290d) {
                a.m4490a(bounds, collection);
            }
        } else if (this.f3289c == null) {
        } else {
            if (bounds.mo9224b(this.f3287a)) {
                collection.addAll(this.f3289c);
                return;
            }
            for (WeightedLatLng weightedLatLng : this.f3289c) {
                if (bounds.mo9223a(weightedLatLng.getPoint())) {
                    collection.add(weightedLatLng);
                }
            }
        }
    }
}
