package com.mcdonalds.app.storelocator.maps.model;

import com.amap.api.maps.model.BitmapDescriptor;
import com.ensighten.Ensighten;

public class BitmapDescriptorFactory {
    public static BitmapDescriptor fromResourceToAMap(int resId) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.model.BitmapDescriptorFactory", "fromResourceToAMap", new Object[]{new Integer(resId)});
        return com.amap.api.maps.model.BitmapDescriptorFactory.fromResource(resId);
    }

    public static com.google.android.gms.maps.model.BitmapDescriptor fromResourceToGMap(int resId) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.model.BitmapDescriptorFactory", "fromResourceToGMap", new Object[]{new Integer(resId)});
        return com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(resId);
    }

    public static com.amap.api.maps2d.model.BitmapDescriptor fromResourceToAMap2D(int resId) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.model.BitmapDescriptorFactory", "fromResourceToAMap2D", new Object[]{new Integer(resId)});
        return com.amap.api.maps2d.model.BitmapDescriptorFactory.fromResource(resId);
    }
}
