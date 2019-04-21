package com.tencent.wxop.stat;

public enum StatReportStrategy {
    INSTANT(1),
    ONLY_WIFI(2),
    BATCH(3),
    APP_LAUNCH(4),
    DEVELOPER(5),
    PERIOD(6),
    ONLY_WIFI_NO_CACHE(7);
    
    /* renamed from: a */
    int f6917a;

    private StatReportStrategy(int i) {
        this.f6917a = i;
    }

    public static StatReportStrategy getStatReportStrategy(int i) {
        for (StatReportStrategy statReportStrategy : values()) {
            if (i == statReportStrategy.mo33875a()) {
                return statReportStrategy;
            }
        }
        return null;
    }

    /* renamed from: a */
    public final int mo33875a() {
        return this.f6917a;
    }
}
