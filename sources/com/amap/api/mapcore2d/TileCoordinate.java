package com.amap.api.mapcore2d;

import android.graphics.PointF;

/* renamed from: com.amap.api.mapcore2d.bp */
class TileCoordinate implements Cloneable {
    /* renamed from: a */
    public int f2594a = 0;
    /* renamed from: b */
    public final int f2595b;
    /* renamed from: c */
    public final int f2596c;
    /* renamed from: d */
    public final int f2597d;
    /* renamed from: e */
    public final int f2598e;
    /* renamed from: f */
    public PointF f2599f;
    /* renamed from: g */
    public int f2600g = -1;
    /* renamed from: h */
    public boolean f2601h = false;

    public TileCoordinate(int i, int i2, int i3, int i4) {
        this.f2595b = i;
        this.f2596c = i2;
        this.f2597d = i3;
        this.f2598e = i4;
    }

    public TileCoordinate(TileCoordinate tileCoordinate) {
        this.f2595b = tileCoordinate.f2595b;
        this.f2596c = tileCoordinate.f2596c;
        this.f2597d = tileCoordinate.f2597d;
        this.f2598e = tileCoordinate.f2598e;
        this.f2599f = tileCoordinate.f2599f;
        this.f2594a = tileCoordinate.f2594a;
    }

    /* renamed from: a */
    public TileCoordinate clone() {
        return new TileCoordinate(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TileCoordinate)) {
            return false;
        }
        TileCoordinate tileCoordinate = (TileCoordinate) obj;
        if (this.f2595b == tileCoordinate.f2595b && this.f2596c == tileCoordinate.f2596c && this.f2597d == tileCoordinate.f2597d && this.f2598e == tileCoordinate.f2598e) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.f2595b * 7) + (this.f2596c * 11)) + (this.f2597d * 13)) + this.f2598e;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f2595b);
        stringBuilder.append("-");
        stringBuilder.append(this.f2596c);
        stringBuilder.append("-");
        stringBuilder.append(this.f2597d);
        stringBuilder.append("-");
        stringBuilder.append(this.f2598e);
        return stringBuilder.toString();
    }
}
