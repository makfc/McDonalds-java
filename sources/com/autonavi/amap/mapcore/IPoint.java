package com.autonavi.amap.mapcore;

public class IPoint {
    /* renamed from: x */
    public int f4562x;
    /* renamed from: y */
    public int f4563y;

    public IPoint(int i, int i2) {
        this.f4562x = i;
        this.f4563y = i2;
    }

    public Object clone() {
        try {
            return (IPoint) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
