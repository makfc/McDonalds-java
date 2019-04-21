package com.alipay.sdk.util;

import com.newrelic.agent.android.api.common.WanType;

/* renamed from: com.alipay.sdk.util.d */
public enum C0647d {
    WIFI(0, "WIFI"),
    NETWORK_TYPE_1(1, "unicom2G"),
    NETWORK_TYPE_2(2, "mobile2G"),
    NETWORK_TYPE_4(4, "telecom2G"),
    NETWORK_TYPE_5(5, "telecom3G"),
    NETWORK_TYPE_6(6, "telecom3G"),
    NETWORK_TYPE_12(12, "telecom3G"),
    NETWORK_TYPE_8(8, "unicom3G"),
    NETWORK_TYPE_3(3, "unicom3G"),
    NETWORK_TYPE_13(13, WanType.LTE),
    NETWORK_TYPE_11(11, WanType.IDEN),
    NETWORK_TYPE_9(9, WanType.HSUPA),
    NETWORK_TYPE_10(10, WanType.HSPA),
    NETWORK_TYPE_15(15, WanType.HSPAP),
    NONE(-1, "none");
    
    /* renamed from: p */
    private int f650p;
    /* renamed from: q */
    private String f651q;

    private C0647d(int i, String str) {
        this.f650p = i;
        this.f651q = str;
    }

    /* renamed from: a */
    public final int mo8108a() {
        return this.f650p;
    }

    /* renamed from: b */
    public final String mo8109b() {
        return this.f651q;
    }

    /* renamed from: a */
    public static C0647d m1020a(int i) {
        for (C0647d c0647d : C0647d.values()) {
            if (c0647d.mo8108a() == i) {
                return c0647d;
            }
        }
        return NONE;
    }
}
