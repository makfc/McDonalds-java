package com.mcdonalds.app.storelocator.maps.model;

import com.ensighten.Ensighten;
import java.io.Serializable;

public class LatLng implements Serializable {
    public double latitude;
    public double longitude;

    public LatLng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public com.amap.api.maps.model.LatLng toAMap() {
        Ensighten.evaluateEvent(this, "toAMap", null);
        return new com.amap.api.maps.model.LatLng(this.latitude, this.longitude);
    }

    public com.amap.api.maps2d.model.LatLng toAMap2D() {
        Ensighten.evaluateEvent(this, "toAMap2D", null);
        return new com.amap.api.maps2d.model.LatLng(this.latitude, this.longitude);
    }

    public com.google.android.gms.maps.model.LatLng toGmaps() {
        Ensighten.evaluateEvent(this, "toGmaps", null);
        return new com.google.android.gms.maps.model.LatLng(this.latitude, this.longitude);
    }

    public static LatLng fromGMap(com.google.android.gms.maps.model.LatLng original) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.model.LatLng", "fromGMap", new Object[]{original});
        return new LatLng(original.latitude, original.longitude);
    }

    public static LatLng fromAMaps(com.amap.api.maps.model.LatLng original) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.model.LatLng", "fromAMaps", new Object[]{original});
        return new LatLng(original.latitude, original.longitude);
    }

    public static LatLng fromAMaps2D(com.amap.api.maps2d.model.LatLng original) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.model.LatLng", "fromAMaps2D", new Object[]{original});
        return new LatLng(original.latitude, original.longitude);
    }
}
