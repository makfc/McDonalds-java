package com.amap.api.maps2d.overlay;

import android.graphics.Bitmap;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AMapServicesUtil */
/* renamed from: com.amap.api.maps2d.overlay.a */
class C1068a {
    /* renamed from: a */
    private static int f3520a = 2048;

    /* renamed from: a */
    public static LatLng m4636a(LatLonPoint latLonPoint) {
        return new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
    }

    /* renamed from: a */
    public static ArrayList<LatLng> m4637a(List<LatLonPoint> list) {
        ArrayList arrayList = new ArrayList();
        for (LatLonPoint a : list) {
            arrayList.add(C1068a.m4636a(a));
        }
        return arrayList;
    }

    /* renamed from: a */
    public static Bitmap m4635a(Bitmap bitmap, float f) {
        if (bitmap == null) {
            return null;
        }
        return Bitmap.createScaledBitmap(bitmap, (int) (((float) bitmap.getWidth()) * f), (int) (((float) bitmap.getHeight()) * f), true);
    }
}
