package com.amap.api.maps.model;

import com.amap.api.mapcore.util.Util;
import com.autonavi.amap.mapcore.DPoint;

public class WeightedLatLng {
    public static final double DEFAULT_INTENSITY = 1.0d;
    /* renamed from: a */
    private DPoint f3286a;
    public final double intensity;
    public final LatLng latLng;

    public WeightedLatLng(LatLng latLng, double d) {
        if (latLng == null) {
            throw new IllegalArgumentException("latLng can not null");
        }
        this.latLng = latLng;
        this.f3286a = Util.m2348a(latLng);
        if (d >= 0.0d) {
            this.intensity = d;
        } else {
            this.intensity = 1.0d;
        }
    }

    public WeightedLatLng(LatLng latLng) {
        this(latLng, 1.0d);
    }

    /* Access modifiers changed, original: protected */
    public DPoint getPoint() {
        return this.f3286a;
    }
}
