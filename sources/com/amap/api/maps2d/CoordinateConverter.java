package com.amap.api.maps2d;

import com.amap.api.mapcore2d.B2GCoordConver;
import com.amap.api.mapcore2d.MapbarCoordConver;
import com.amap.api.mapcore2d.NaviveCoordConver;
import com.amap.api.maps2d.model.LatLng;

public class CoordinateConverter {
    /* renamed from: a */
    private CoordType f3365a = null;
    /* renamed from: b */
    private LatLng f3366b = null;

    public enum CoordType {
        BAIDU,
        MAPBAR,
        MAPABC,
        SOSOMAP,
        ALIYUN,
        GOOGLE,
        GPS
    }

    public CoordinateConverter from(CoordType coordType) {
        this.f3365a = coordType;
        return this;
    }

    public CoordinateConverter coord(LatLng latLng) {
        this.f3366b = latLng;
        return this;
    }

    public LatLng convert() {
        if (this.f3365a == null || this.f3366b == null) {
            return null;
        }
        switch (this.f3365a) {
            case BAIDU:
                return B2GCoordConver.m3861a(this.f3366b);
            case MAPBAR:
                return MapbarCoordConver.m3866a(this.f3366b);
            case MAPABC:
            case SOSOMAP:
            case ALIYUN:
            case GOOGLE:
                return this.f3366b;
            case GPS:
                return NaviveCoordConver.m3869a(this.f3366b);
            default:
                return null;
        }
    }
}
