package com.aps;

import android.util.SparseArray;
import com.newrelic.agent.android.api.common.WanType;

/* renamed from: com.aps.g */
public class Const {
    /* renamed from: a */
    static String f4434a = null;
    /* renamed from: b */
    static String f4435b = null;
    /* renamed from: c */
    static String f4436c = null;
    /* renamed from: d */
    static String f4437d = "";
    /* renamed from: e */
    static String f4438e = "";
    /* renamed from: f */
    static String f4439f = "";
    /* renamed from: g */
    public static int f4440g = 30000;
    /* renamed from: h */
    static boolean f4441h = false;
    /* renamed from: i */
    static boolean f4442i = true;
    /* renamed from: j */
    static long f4443j = 10000;
    /* renamed from: k */
    static long f4444k = 30000;
    /* renamed from: l */
    static boolean f4445l = true;
    /* renamed from: m */
    static final SparseArray<String> f4446m = new SparseArray();
    /* renamed from: n */
    static final String[] f4447n = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.INTERNET", "android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE"};

    private Const() {
    }

    static {
        f4446m.append(0, "UNKNOWN");
        f4446m.append(1, WanType.GPRS);
        f4446m.append(2, WanType.EDGE);
        f4446m.append(3, WanType.UMTS);
        f4446m.append(4, WanType.CDMA);
        f4446m.append(5, "EVDO_0");
        f4446m.append(6, "EVDO_A");
        f4446m.append(7, WanType.RTT);
        f4446m.append(8, WanType.HSDPA);
        f4446m.append(9, WanType.HSUPA);
        f4446m.append(10, WanType.HSPA);
        f4446m.append(11, WanType.IDEN);
        f4446m.append(12, "EVDO_B");
        f4446m.append(13, WanType.LTE);
        f4446m.append(14, "EHRPD");
        f4446m.append(15, WanType.HSPAP);
    }

    /* renamed from: a */
    static void m5625a(String str) {
        f4437d = str;
    }

    /* renamed from: b */
    static void m5627b(String str) {
        f4438e = str;
    }

    /* renamed from: c */
    static void m5628c(String str) {
        f4439f = str;
    }

    /* renamed from: a */
    static void m5626a(boolean z) {
        f4441h = z;
    }
}
