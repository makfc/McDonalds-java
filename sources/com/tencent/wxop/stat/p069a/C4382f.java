package com.tencent.wxop.stat.p069a;

/* renamed from: com.tencent.wxop.stat.a.f */
public enum C4382f {
    PAGE_VIEW(1),
    SESSION_ENV(2),
    ERROR(3),
    CUSTOM(1000),
    ADDITION(1001),
    MONITOR_STAT(1002),
    MTA_GAME_USER(1003),
    NETWORK_MONITOR(1004),
    NETWORK_DETECTOR(1005);
    
    /* renamed from: j */
    private int f6975j;

    private C4382f(int i) {
        this.f6975j = i;
    }

    /* renamed from: a */
    public final int mo33896a() {
        return this.f6975j;
    }
}
