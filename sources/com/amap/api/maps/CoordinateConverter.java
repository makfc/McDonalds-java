package com.amap.api.maps;

import android.content.Context;
import com.amap.api.mapcore.util.OffsetUtil;
import com.amap.api.mapcore.util.SDKLogHandler;
import com.amap.api.maps.model.LatLng;

public class CoordinateConverter {
    /* renamed from: a */
    private Context f3101a;
    /* renamed from: b */
    private CoordType f3102b = null;
    /* renamed from: c */
    private LatLng f3103c = null;

    public enum CoordType {
        BAIDU,
        MAPBAR,
        GPS,
        MAPABC,
        SOSOMAP,
        ALIYUN,
        GOOGLE
    }

    public CoordinateConverter(Context context) {
        this.f3101a = context;
    }

    public CoordinateConverter from(CoordType coordType) {
        this.f3102b = coordType;
        return this;
    }

    public CoordinateConverter coord(LatLng latLng) {
        this.f3103c = latLng;
        return this;
    }

    public LatLng convert() {
        if (this.f3102b == null || this.f3103c == null) {
            return null;
        }
        try {
            switch (C1050a.f3121a[this.f3102b.ordinal()]) {
                case 1:
                    return OffsetUtil.m1727a(this.f3103c);
                case 2:
                    return OffsetUtil.m1733b(this.f3101a, this.f3103c);
                case 3:
                case 4:
                case 5:
                case 6:
                    return this.f3103c;
                case 7:
                    return OffsetUtil.m1726a(this.f3101a, this.f3103c);
                default:
                    return null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            SDKLogHandler.m2563a(th, "CoordinateConverter", "convert");
            return this.f3103c;
        }
    }
}
