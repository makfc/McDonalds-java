package com.amap.api.maps;

import com.amap.api.maps.CoordinateConverter.CoordType;

/* compiled from: CoordinateConverter */
/* renamed from: com.amap.api.maps.a */
/* synthetic */ class C1050a {
    /* renamed from: a */
    static final /* synthetic */ int[] f3121a = new int[CoordType.values().length];

    static {
        try {
            f3121a[CoordType.BAIDU.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f3121a[CoordType.MAPBAR.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f3121a[CoordType.MAPABC.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f3121a[CoordType.SOSOMAP.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            f3121a[CoordType.ALIYUN.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            f3121a[CoordType.GOOGLE.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            f3121a[CoordType.GPS.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
    }
}
