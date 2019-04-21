package com.amap.api.maps.overlay;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.amap.api.maps.overlay.a */
class AMapServicesUtil {
    /* renamed from: a */
    public static int f3346a = 2048;

    AMapServicesUtil() {
    }

    /* renamed from: a */
    public static LatLng m4556a(LatLonPoint latLonPoint) {
        return new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
    }

    /* renamed from: a */
    public static ArrayList<LatLng> m4557a(List<LatLonPoint> list) {
        ArrayList arrayList = new ArrayList();
        for (LatLonPoint a : list) {
            arrayList.add(AMapServicesUtil.m4556a(a));
        }
        return arrayList;
    }
}
