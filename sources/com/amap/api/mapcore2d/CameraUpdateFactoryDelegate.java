package com.amap.api.mapcore2d;

import android.graphics.Point;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;

/* renamed from: com.amap.api.mapcore2d.l */
public class CameraUpdateFactoryDelegate {
    /* renamed from: a */
    C1036a f2993a = C1036a.none;
    /* renamed from: b */
    float f2994b;
    /* renamed from: c */
    float f2995c;
    /* renamed from: d */
    float f2996d;
    /* renamed from: e */
    float f2997e;
    /* renamed from: f */
    CameraPosition f2998f;
    /* renamed from: g */
    LatLngBounds f2999g;
    /* renamed from: h */
    int f3000h;
    /* renamed from: i */
    int f3001i;
    /* renamed from: j */
    int f3002j;
    /* renamed from: k */
    Point f3003k = null;
    /* renamed from: l */
    boolean f3004l = false;
    /* renamed from: m */
    private float f3005m;
    /* renamed from: n */
    private float f3006n;
    /* renamed from: o */
    private IPoint f3007o;

    /* compiled from: CameraUpdateFactoryDelegate */
    /* renamed from: com.amap.api.mapcore2d.l$a */
    enum C1036a {
        none,
        zoomIn,
        changeCenter,
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

    /* renamed from: a */
    public static CameraUpdateFactoryDelegate m4316a() {
        return new CameraUpdateFactoryDelegate();
    }

    /* renamed from: b */
    public static CameraUpdateFactoryDelegate m4327b() {
        CameraUpdateFactoryDelegate a = CameraUpdateFactoryDelegate.m4316a();
        a.f2993a = C1036a.zoomIn;
        return a;
    }

    /* renamed from: c */
    public static CameraUpdateFactoryDelegate m4330c() {
        CameraUpdateFactoryDelegate a = CameraUpdateFactoryDelegate.m4316a();
        a.f2993a = C1036a.zoomOut;
        return a;
    }

    /* renamed from: a */
    public static CameraUpdateFactoryDelegate m4318a(float f, float f2) {
        CameraUpdateFactoryDelegate a = CameraUpdateFactoryDelegate.m4316a();
        a.f2993a = C1036a.scrollBy;
        a.f2994b = f;
        a.f2995c = f2;
        return a;
    }

    /* renamed from: a */
    public static CameraUpdateFactoryDelegate m4317a(float f) {
        CameraUpdateFactoryDelegate a = CameraUpdateFactoryDelegate.m4316a();
        a.f2993a = C1036a.zoomTo;
        a.f2996d = f;
        return a;
    }

    /* renamed from: b */
    public static CameraUpdateFactoryDelegate m4328b(float f) {
        return CameraUpdateFactoryDelegate.m4319a(f, null);
    }

    /* renamed from: a */
    public static CameraUpdateFactoryDelegate m4319a(float f, Point point) {
        CameraUpdateFactoryDelegate a = CameraUpdateFactoryDelegate.m4316a();
        a.f2993a = C1036a.zoomBy;
        a.f2997e = f;
        a.f3003k = point;
        return a;
    }

    /* renamed from: a */
    public static CameraUpdateFactoryDelegate m4321a(CameraPosition cameraPosition) {
        CameraUpdateFactoryDelegate a = CameraUpdateFactoryDelegate.m4316a();
        a.f2993a = C1036a.newCameraPosition;
        a.f2998f = cameraPosition;
        return a;
    }

    /* renamed from: a */
    public static CameraUpdateFactoryDelegate m4322a(LatLng latLng) {
        CameraUpdateFactoryDelegate a = CameraUpdateFactoryDelegate.m4316a();
        a.f2993a = C1036a.changeCenter;
        a.f2998f = new CameraPosition(latLng, 0.0f, 0.0f, 0.0f);
        return a;
    }

    /* renamed from: b */
    public static CameraUpdateFactoryDelegate m4329b(LatLng latLng) {
        return CameraUpdateFactoryDelegate.m4321a(CameraPosition.builder().target(latLng).build());
    }

    /* renamed from: a */
    public static CameraUpdateFactoryDelegate m4323a(LatLng latLng, float f) {
        return CameraUpdateFactoryDelegate.m4321a(CameraPosition.builder().target(latLng).zoom(f).build());
    }

    /* renamed from: a */
    public static CameraUpdateFactoryDelegate m4324a(LatLng latLng, float f, float f2, float f3) {
        return CameraUpdateFactoryDelegate.m4321a(CameraPosition.builder().target(latLng).zoom(f).bearing(f2).tilt(f3).build());
    }

    /* renamed from: a */
    static CameraUpdateFactoryDelegate m4320a(IPoint iPoint, float f, float f2, float f3) {
        CameraUpdateFactoryDelegate a = CameraUpdateFactoryDelegate.m4316a();
        a.f2993a = C1036a.changeGeoCenterZoomTiltBearing;
        a.f3007o = iPoint;
        a.f2996d = f;
        a.f3006n = f2;
        a.f3005m = f3;
        return a;
    }

    /* renamed from: a */
    public static CameraUpdateFactoryDelegate m4325a(LatLngBounds latLngBounds, int i) {
        CameraUpdateFactoryDelegate a = CameraUpdateFactoryDelegate.m4316a();
        a.f2993a = C1036a.newLatLngBounds;
        a.f2999g = latLngBounds;
        a.f3000h = i;
        return a;
    }

    /* renamed from: a */
    public static CameraUpdateFactoryDelegate m4326a(LatLngBounds latLngBounds, int i, int i2, int i3) {
        CameraUpdateFactoryDelegate a = CameraUpdateFactoryDelegate.m4316a();
        a.f2993a = C1036a.newLatLngBoundsWithSize;
        a.f2999g = latLngBounds;
        a.f3000h = i3;
        a.f3001i = i;
        a.f3002j = i2;
        return a;
    }
}
