package com.amap.api.maps2d;

import android.graphics.Point;
import com.amap.api.mapcore2d.CameraUpdateFactoryDelegate;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;

public final class CameraUpdateFactory {
    public static CameraUpdate zoomIn() {
        return new CameraUpdate(CameraUpdateFactoryDelegate.m4327b());
    }

    public static CameraUpdate zoomOut() {
        return new CameraUpdate(CameraUpdateFactoryDelegate.m4330c());
    }

    public static CameraUpdate scrollBy(float f, float f2) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.m4318a(f, f2));
    }

    public static CameraUpdate zoomTo(float f) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.m4317a(f));
    }

    public static CameraUpdate zoomBy(float f) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.m4328b(f));
    }

    public static CameraUpdate zoomBy(float f, Point point) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.m4319a(f, point));
    }

    public static CameraUpdate newCameraPosition(CameraPosition cameraPosition) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.m4321a(cameraPosition));
    }

    public static CameraUpdate newLatLng(LatLng latLng) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.m4329b(latLng));
    }

    public static CameraUpdate newLatLngZoom(LatLng latLng, float f) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.m4323a(latLng, f));
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds latLngBounds, int i) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.m4325a(latLngBounds, i));
    }

    public static CameraUpdate changeLatLng(LatLng latLng) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.m4322a(latLng));
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds latLngBounds, int i, int i2, int i3) {
        return new CameraUpdate(CameraUpdateFactoryDelegate.m4326a(latLngBounds, i, i2, i3));
    }
}
