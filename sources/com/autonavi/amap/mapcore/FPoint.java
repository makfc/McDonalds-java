package com.autonavi.amap.mapcore;

public class FPoint {
    /* renamed from: x */
    public float f4560x;
    /* renamed from: y */
    public float f4561y;

    public FPoint(float f, float f2) {
        this.f4560x = f;
        this.f4561y = f2;
    }

    public boolean equals(Object obj) {
        FPoint fPoint = (FPoint) obj;
        if (fPoint != null && this.f4560x == fPoint.f4560x && this.f4561y == fPoint.f4561y) {
            return true;
        }
        return false;
    }
}
