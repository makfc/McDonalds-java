package com.mcdonalds.app.storelocator.maps.model;

import com.ensighten.Ensighten;
import com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule.NativeMapsSDK;

public class Marker {
    /* renamed from: id */
    private String f6668id;
    private com.amap.api.maps2d.model.Marker mAMap2DMarker;
    private com.amap.api.maps.model.Marker mAMapMarker;
    private com.google.android.gms.maps.model.Marker mGoogleMarker;
    private NativeMapsSDK mMapsSDK;
    private LatLng position;

    public LatLng getPosition() {
        Ensighten.evaluateEvent(this, "getPosition", null);
        return this.position;
    }

    public void showInfoWindow() {
        Ensighten.evaluateEvent(this, "showInfoWindow", null);
        switch (this.mMapsSDK) {
            case Google:
                this.mGoogleMarker.showInfoWindow();
                return;
            case AutoNavi:
                this.mAMapMarker.showInfoWindow();
                return;
            case AutoNavi2D:
                this.mAMap2DMarker.showInfoWindow();
                return;
            default:
                return;
        }
    }

    public void setIcon(int resId) {
        Ensighten.evaluateEvent(this, "setIcon", new Object[]{new Integer(resId)});
        switch (this.mMapsSDK) {
            case Google:
                this.mGoogleMarker.setIcon(BitmapDescriptorFactory.fromResourceToGMap(resId));
                return;
            case AutoNavi:
                this.mAMapMarker.setIcon(BitmapDescriptorFactory.fromResourceToAMap(resId));
                return;
            case AutoNavi2D:
                this.mAMap2DMarker.setIcon(BitmapDescriptorFactory.fromResourceToAMap2D(resId));
                return;
            default:
                return;
        }
    }

    public String getId() {
        Ensighten.evaluateEvent(this, "getId", null);
        return this.f6668id;
    }

    public static Marker fromGMap(com.google.android.gms.maps.model.Marker marker) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.model.Marker", "fromGMap", new Object[]{marker});
        Marker clone = new Marker();
        clone.position = LatLng.fromGMap(marker.getPosition());
        clone.f6668id = marker.getId();
        clone.mGoogleMarker = marker;
        clone.mMapsSDK = NativeMapsSDK.Google;
        return clone;
    }

    public static Marker fromAMap(com.amap.api.maps.model.Marker marker) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.model.Marker", "fromAMap", new Object[]{marker});
        Marker clone = new Marker();
        clone.position = LatLng.fromAMaps(marker.getPosition());
        clone.f6668id = marker.getId();
        clone.mAMapMarker = marker;
        clone.mMapsSDK = NativeMapsSDK.AutoNavi;
        return clone;
    }

    public static Marker fromAMap2D(com.amap.api.maps2d.model.Marker marker) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.model.Marker", "fromAMap2D", new Object[]{marker});
        Marker clone = new Marker();
        clone.position = LatLng.fromAMaps2D(marker.getPosition());
        clone.f6668id = marker.getId();
        clone.mAMap2DMarker = marker;
        clone.mMapsSDK = NativeMapsSDK.AutoNavi2D;
        return clone;
    }
}
