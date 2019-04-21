package com.amap.api.mapcore2d;

import java.util.Random;

/* renamed from: com.amap.api.mapcore2d.aw */
class MapServerUrl {
    /* renamed from: b */
    private static MapServerUrl f2326b;
    /* renamed from: a */
    private String f2327a = "http://tm.amap.com";

    private MapServerUrl() {
    }

    /* renamed from: a */
    public static synchronized MapServerUrl m3230a() {
        MapServerUrl mapServerUrl;
        synchronized (MapServerUrl.class) {
            if (f2326b == null) {
                f2326b = new MapServerUrl();
            }
            mapServerUrl = f2326b;
        }
        return mapServerUrl;
    }

    /* renamed from: b */
    public String mo9860b() {
        return "http://grid.amap.com/grid/%d/%d/%d?dpiType=%s&lang=%s";
    }

    /* renamed from: c */
    public String mo9861c() {
        return this.f2327a;
    }

    /* renamed from: d */
    public String mo9862d() {
        int nextInt = new Random(System.currentTimeMillis()).nextInt(100000) % 4;
        return String.format("http://mt%d.google.cn/vt/lyrs=m", new Object[]{Integer.valueOf(nextInt + 1)}) + "@285000000&hl=zh-CN&gl=CN&src=app&expIds=201527&rlbl=1&x=%d&y=%d&z=%d&s=Gali";
    }

    /* renamed from: e */
    public String mo9863e() {
        return String.format("http://mst0%d.is.autonavi.com", new Object[]{Integer.valueOf((new Random(System.currentTimeMillis()).nextInt(100000) % 4) + 1)});
    }
}
