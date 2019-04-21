package com.amap.api.maps;

import android.graphics.Point;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;

public final class CameraUpdateFactory {
    public static CameraUpdate zoomIn() {
        return new CameraUpdate(CameraUpdateFactoryDelegate.zoomIn());
    }

    public static CameraUpdate zoomOut() {
        return new CameraUpdate(CameraUpdateFactoryDelegate.zoomOut());
    }

    public static CameraUpdate scrollBy(float f, float f2) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.scrollBy(f, f2));
    }

    public static CameraUpdate zoomTo(float f) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.zoomTo(f));
    }

    public static CameraUpdate zoomBy(float f) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.zoomBy(f));
    }

    public static CameraUpdate zoomBy(float f, Point point) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.zoomBy(f, point));
    }

    public static CameraUpdate newCameraPosition(CameraPosition cameraPosition) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.newCameraPosition(cameraPosition));
    }

    public static CameraUpdate newLatLng(LatLng latLng) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.newLatLng(latLng));
    }

    public static CameraUpdate newLatLngZoom(LatLng latLng, float f) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.newLatLngZoom(latLng, f));
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds latLngBounds, int i) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.newLatLngBounds(latLngBounds, i));
    }

    public static CameraUpdate changeLatLng(LatLng latLng) {
        IPoint iPoint = new IPoint();
        MapProjection.lonlat2Geo(latLng.longitude, latLng.latitude, iPoint);
        return new CameraUpdate(CameraUpdateFactoryDelegate.changeGeoCenter(iPoint));
    }

    public static CameraUpdate changeBearing(float f) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.changeBearing(f % 360.0f));
    }

    public static CameraUpdate changeBearingGeoCenter(float f, IPoint iPoint) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.changeBearingGeoCenter(f % 360.0f, iPoint));
    }

    public static CameraUpdate changeTilt(float f) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.changeTilt(f));
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds latLngBounds, int i, int i2, int i3) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.newLatLngBoundsWithSize(latLngBounds, i, i2, i3));
    }
}
