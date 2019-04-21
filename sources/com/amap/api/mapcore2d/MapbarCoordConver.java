package com.amap.api.mapcore2d;

import com.amap.api.maps2d.model.LatLng;

/* renamed from: com.amap.api.mapcore2d.cf */
public class MapbarCoordConver {
    /* renamed from: a */
    static double f2697a = 3.141592653589793d;

    /* renamed from: a */
    public static LatLng m3866a(LatLng latLng) {
        return NaviveCoordConver.m3869a(MapbarCoordConver.m3868c(latLng.longitude, latLng.latitude));
    }

    /* renamed from: a */
    public static double m3865a(double d, double d2) {
        return (Math.cos(d2 / 100000.0d) * (d / 18000.0d)) + (Math.sin(d / 100000.0d) * (d2 / 9000.0d));
    }

    /* renamed from: b */
    public static double m3867b(double d, double d2) {
        return (Math.sin(d2 / 100000.0d) * (d / 18000.0d)) + (Math.cos(d / 100000.0d) * (d2 / 9000.0d));
    }

    /* renamed from: c */
    private static LatLng m3868c(double d, double d2) {
        double d3 = (double) (((long) (100000.0d * d)) % 36000000);
        double d4 = (double) (((long) (100000.0d * d2)) % 36000000);
        int i = (int) ((-MapbarCoordConver.m3867b(d3, d4)) + d4);
        int i2 = (int) (((double) (d3 > 0.0d ? 1 : -1)) + ((-MapbarCoordConver.m3865a((double) ((int) ((-MapbarCoordConver.m3865a(d3, d4)) + d3)), (double) i)) + d3));
        return new LatLng(((double) ((int) (((double) (d4 > 0.0d ? 1 : -1)) + ((-MapbarCoordConver.m3867b((double) i2, (double) i)) + d4)))) / 100000.0d, ((double) i2) / 100000.0d);
    }
}
