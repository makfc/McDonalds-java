package com.tencent.wxop.stat;

/* renamed from: com.tencent.wxop.stat.ag */
/* synthetic */ class C4396ag {
    /* renamed from: a */
    static final /* synthetic */ int[] f7011a = new int[StatReportStrategy.values().length];

    static {
        try {
            f7011a[StatReportStrategy.INSTANT.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f7011a[StatReportStrategy.PERIOD.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f7011a[StatReportStrategy.APP_LAUNCH.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f7011a[StatReportStrategy.DEVELOPER.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            f7011a[StatReportStrategy.BATCH.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            f7011a[StatReportStrategy.ONLY_WIFI.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            f7011a[StatReportStrategy.ONLY_WIFI_NO_CACHE.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
    }
}
