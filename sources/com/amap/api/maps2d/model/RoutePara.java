package com.amap.api.maps2d.model;

public class RoutePara {
    /* renamed from: a */
    private int f3456a = 0;
    /* renamed from: b */
    private int f3457b = 0;
    /* renamed from: c */
    private LatLng f3458c;
    /* renamed from: d */
    private LatLng f3459d;
    /* renamed from: e */
    private String f3460e;
    /* renamed from: f */
    private String f3461f;

    public int getDrivingRouteStyle() {
        return this.f3456a;
    }

    public void setDrivingRouteStyle(int i) {
        if (i >= 0 && i < 9) {
            this.f3456a = i;
        }
    }

    public int getTransitRouteStyle() {
        return this.f3457b;
    }

    public void setTransitRouteStyle(int i) {
        if (i >= 0 && i < 6) {
            this.f3457b = i;
        }
    }

    public LatLng getStartPoint() {
        return this.f3458c;
    }

    public void setStartPoint(LatLng latLng) {
        this.f3458c = latLng;
    }

    public LatLng getEndPoint() {
        return this.f3459d;
    }

    public void setEndPoint(LatLng latLng) {
        this.f3459d = latLng;
    }

    public String getEndName() {
        return this.f3461f;
    }

    public void setEndName(String str) {
        this.f3461f = str;
    }

    public String getStartName() {
        return this.f3460e;
    }

    public void setStartName(String str) {
        this.f3460e = str;
    }
}
