package com.aps;

/* renamed from: com.aps.bf */
public final class C1249bf {
    /* renamed from: a */
    protected static boolean f4368a = false;
    /* renamed from: b */
    protected static final String[] f4369b = new String[]{"android.permission.READ_PHONE_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE"};

    /* renamed from: a */
    protected static boolean m5543a(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        for (String equals : strArr) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
