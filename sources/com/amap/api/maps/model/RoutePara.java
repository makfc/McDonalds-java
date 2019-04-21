package com.amap.api.maps.model;

public class RoutePara {
    /* renamed from: a */
    private int f3254a = 0;
    /* renamed from: b */
    private int f3255b = 0;
    /* renamed from: c */
    private LatLng f3256c;
    /* renamed from: d */
    private LatLng f3257d;
    /* renamed from: e */
    private String f3258e;
    /* renamed from: f */
    private String f3259f;

    public int getDrivingRouteStyle() {
        return this.f3254a;
    }

    public void setDrivingRouteStyle(int i) {
        if (i >= 0 && i < 9) {
            this.f3254a = i;
        }
    }

    public int getTransitRouteStyle() {
        return this.f3255b;
    }

    public void setTransitRouteStyle(int i) {
        if (i >= 0 && i < 6) {
            this.f3255b = i;
        }
    }

    public LatLng getStartPoint() {
        return this.f3256c;
    }

    public void setStartPoint(LatLng latLng) {
        this.f3256c = latLng;
    }

    public LatLng getEndPoint() {
        return this.f3257d;
    }

    public void setEndPoint(LatLng latLng) {
        this.f3257d = latLng;
    }

    public String getEndName() {
        return this.f3259f;
    }

    public void setEndName(String str) {
        this.f3259f = str;
    }

    public String getStartName() {
        return this.f3258e;
    }

    public void setStartName(String str) {
        this.f3258e = str;
    }
}
