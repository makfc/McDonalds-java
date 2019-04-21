package com.amap.api.mapcore2d;

import com.amap.api.maps2d.model.LatLng;
import java.math.BigDecimal;

/* renamed from: com.amap.api.mapcore2d.cd */
public class B2GCoordConver {
    /* renamed from: a */
    public static LatLng m3861a(LatLng latLng) {
        if (latLng != null) {
            return B2GCoordConver.m3864b(latLng);
        }
        return null;
    }

    /* renamed from: a */
    private static double m3857a(double d) {
        return Math.sin((3000.0d * d) * 0.017453292519943295d) * 2.0E-5d;
    }

    /* renamed from: b */
    private static double m3863b(double d) {
        return Math.cos((3000.0d * d) * 0.017453292519943295d) * 3.0E-6d;
    }

    /* renamed from: a */
    private static DPoint m3859a(double d, double d2) {
        DPoint dPoint = new DPoint();
        double sin = (Math.sin(B2GCoordConver.m3863b(d) + Math.atan2(d2, d)) * (B2GCoordConver.m3857a(d2) + Math.sqrt((d * d) + (d2 * d2)))) + 0.006d;
        dPoint.f2695a = B2GCoordConver.m3858a((Math.cos(B2GCoordConver.m3863b(d) + Math.atan2(d2, d)) * (B2GCoordConver.m3857a(d2) + Math.sqrt((d * d) + (d2 * d2)))) + 0.0065d, 8);
        dPoint.f2696b = B2GCoordConver.m3858a(sin, 8);
        return dPoint;
    }

    /* renamed from: a */
    private static double m3858a(double d, int i) {
        return new BigDecimal(d).setScale(i, 4).doubleValue();
    }

    /* renamed from: b */
    private static LatLng m3864b(LatLng latLng) {
        return B2GCoordConver.m3862a(latLng, 2);
    }

    /* renamed from: a */
    private static LatLng m3862a(LatLng latLng, int i) {
        double d = 0.006401062d;
        double d2 = 0.0060424805d;
        int i2 = 0;
        LatLng latLng2 = null;
        while (i2 < i) {
            LatLng a = B2GCoordConver.m3860a(latLng.longitude, latLng.latitude, d, d2);
            d = latLng.longitude - a.longitude;
            d2 = latLng.latitude - a.latitude;
            i2++;
            latLng2 = a;
        }
        return latLng2;
    }

    /* renamed from: a */
    private static LatLng m3860a(double d, double d2, double d3, double d4) {
        DPoint dPoint = new DPoint();
        double d5 = d - d3;
        double d6 = d2 - d4;
        DPoint a = B2GCoordConver.m3859a(d5, d6);
        dPoint.f2695a = B2GCoordConver.m3858a((d5 + d) - a.f2695a, 8);
        dPoint.f2696b = B2GCoordConver.m3858a((d2 + d6) - a.f2696b, 8);
        return new LatLng(dPoint.f2696b, dPoint.f2695a);
    }
}
