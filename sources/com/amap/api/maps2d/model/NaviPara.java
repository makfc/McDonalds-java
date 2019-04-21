package com.amap.api.maps2d.model;

public class NaviPara {
    @Deprecated
    public static final int DRIVING_AVOID_CONGESTION = 4;
    @Deprecated
    public static final int DRIVING_DEFAULT = 0;
    @Deprecated
    public static final int DRIVING_NO_HIGHWAY = 3;
    @Deprecated
    public static final int DRIVING_NO_HIGHWAY_AVOID_CONGESTION = 6;
    @Deprecated
    public static final int DRIVING_NO_HIGHWAY_AVOID_SHORT_MONEY = 5;
    @Deprecated
    public static final int DRIVING_NO_HIGHWAY_SAVE_MONEY_AVOID_CONGESTION = 8;
    @Deprecated
    public static final int DRIVING_SAVE_MONEY = 1;
    @Deprecated
    public static final int DRIVING_SAVE_MONEY_AVOID_CONGESTION = 7;
    @Deprecated
    public static final int DRIVING_SHORT_DISTANCE = 2;
    /* renamed from: a */
    private int f3435a = 0;
    /* renamed from: b */
    private LatLng f3436b;

    public void setTargetPoint(LatLng latLng) {
        this.f3436b = latLng;
    }

    public void setNaviStyle(int i) {
        if (i >= 0 && i < 9) {
            this.f3435a = i;
        }
    }

    public LatLng getTargetPoint() {
        return this.f3436b;
    }

    public int getNaviStyle() {
        return this.f3435a;
    }
}
