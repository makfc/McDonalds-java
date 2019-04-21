package com.amap.api.mapcore2d;

import com.amap.api.maps2d.model.LatLng;

/* renamed from: com.amap.api.mapcore2d.cg */
public class NaviveCoordConver {
    /* renamed from: a */
    public static LatLng m3869a(LatLng latLng) {
        double[] a = C1035em.m4298a(latLng.longitude, latLng.latitude);
        return new LatLng(a[1], a[0]);
    }
}
