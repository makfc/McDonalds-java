package com.autonavi.amap.mapcore.interfaces;

import android.graphics.Point;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.IPoint;

public class CameraUpdateFactoryDelegate {
    public float amount;
    public float bearing;
    public LatLngBounds bounds;
    public CameraPosition cameraPosition;
    public Point focus = null;
    public IPoint geoPoint;
    public int height;
    public boolean isChangeFinished = false;
    public boolean isUseAnchor = false;
    public Type nowType = Type.none;
    public int padding;
    public float tilt;
    public int width;
    public float xPixel;
    public float yPixel;
    public float zoom;

    public enum Type {
        none,
        zoomIn,
        changeCenter,
        changeTilt,
        changeBearing,
        changeBearingGeoCenter,
        changeGeoCenterZoom,
        zoomOut,
        zoomTo,
        zoomBy,
        scrollBy,
        newCameraPosition,
        newLatLngBounds,
        newLatLngBoundsWithSize,
        changeGeoCenterZoomTiltBearing
    }

    private CameraUpdateFactoryDelegate() {
    }

    public static CameraUpdateFactoryDelegate newInstance() {
        return new CameraUpdateFactoryDelegate();
    }

    public static CameraUpdateFactoryDelegate zoomIn() {
        CameraUpdateFactoryDelegate newInstance = newInstance();
        newInstance.nowType = Type.zoomIn;
        return newInstance;
    }

    public static CameraUpdateFactoryDelegate zoomOut() {
        CameraUpdateFactoryDelegate newInstance = newInstance();
        newInstance.nowType = Type.zoomOut;
        return newInstance;
    }

    public static CameraUpdateFactoryDelegate scrollBy(float f, float f2) {
        CameraUpdateFactoryDelegate newInstance = newInstance();
        newInstance.nowType = Type.scrollBy;
        newInstance.xPixel = f;
        newInstance.yPixel = f2;
        return newInstance;
    }

    public static CameraUpdateFactoryDelegate zoomTo(float f) {
        CameraUpdateFactoryDelegate newInstance = newInstance();
        newInstance.nowType = Type.zoomTo;
        newInstance.zoom = f;
        return newInstance;
    }

    public static CameraUpdateFactoryDelegate zoomBy(float f) {
        return zoomBy(f, null);
    }

    public static CameraUpdateFactoryDelegate zoomBy(float f, Point point) {
        CameraUpdateFactoryDelegate newInstance = newInstance();
        newInstance.nowType = Type.zoomBy;
        newInstance.amount = f;
        newInstance.focus = point;
        return newInstance;
    }

    public static CameraUpdateFactoryDelegate newCameraPosition(CameraPosition cameraPosition) {
        CameraUpdateFactoryDelegate newInstance = newInstance();
        newInstance.nowType = Type.newCameraPosition;
        newInstance.cameraPosition = cameraPosition;
        return newInstance;
    }

    public static CameraUpdateFactoryDelegate changeGeoCenter(IPoint iPoint) {
        CameraUpdateFactoryDelegate newInstance = newInstance();
        newInstance.nowType = Type.changeCenter;
        newInstance.geoPoint = iPoint;
        return newInstance;
    }

    public static CameraUpdateFactoryDelegate changeTilt(float f) {
        CameraUpdateFactoryDelegate newInstance = newInstance();
        newInstance.nowType = Type.changeTilt;
        newInstance.tilt = f;
        return newInstance;
    }

    public static CameraUpdateFactoryDelegate changeBearing(float f) {
        CameraUpdateFactoryDelegate newInstance = newInstance();
        newInstance.nowType = Type.changeBearing;
        newInstance.bearing = f;
        return newInstance;
    }

    public static CameraUpdateFactoryDelegate changeBearingGeoCenter(float f, IPoint iPoint) {
        CameraUpdateFactoryDelegate newInstance = newInstance();
        newInstance.nowType = Type.changeBearingGeoCenter;
        newInstance.bearing = f;
        newInstance.geoPoint = iPoint;
        return newInstance;
    }

    public static CameraUpdateFactoryDelegate changeGeoCenterZoom(IPoint iPoint, float f) {
        CameraUpdateFactoryDelegate newInstance = newInstance();
        newInstance.nowType = Type.changeGeoCenterZoom;
        newInstance.geoPoint = iPoint;
        newInstance.zoom = f;
        return newInstance;
    }

    public static CameraUpdateFactoryDelegate newLatLng(LatLng latLng) {
        return newCameraPosition(CameraPosition.builder().target(latLng).build());
    }

    public static CameraUpdateFactoryDelegate newLatLngZoom(LatLng latLng, float f) {
        return newCameraPosition(CameraPosition.builder().target(latLng).zoom(f).build());
    }

    public static CameraUpdateFactoryDelegate newCamera(LatLng latLng, float f, float f2, float f3) {
        return newCameraPosition(CameraPosition.builder().target(latLng).zoom(f).bearing(f2).tilt(f3).build());
    }

    public static CameraUpdateFactoryDelegate newCamera(IPoint iPoint, float f, float f2, float f3) {
        CameraUpdateFactoryDelegate newInstance = newInstance();
        newInstance.nowType = Type.changeGeoCenterZoomTiltBearing;
        newInstance.geoPoint = iPoint;
        newInstance.zoom = f;
        newInstance.bearing = f2;
        newInstance.tilt = f3;
        return newInstance;
    }

    public static CameraUpdateFactoryDelegate newLatLngBounds(LatLngBounds latLngBounds, int i) {
        CameraUpdateFactoryDelegate newInstance = newInstance();
        newInstance.nowType = Type.newLatLngBounds;
        newInstance.bounds = latLngBounds;
        newInstance.padding = i;
        return newInstance;
    }

    public static CameraUpdateFactoryDelegate newLatLngBoundsWithSize(LatLngBounds latLngBounds, int i, int i2, int i3) {
        CameraUpdateFactoryDelegate newInstance = newInstance();
        newInstance.nowType = Type.newLatLngBoundsWithSize;
        newInstance.bounds = latLngBounds;
        newInstance.padding = i3;
        newInstance.width = i;
        newInstance.height = i2;
        return newInstance;
    }
}
