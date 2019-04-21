package com.amap.api.services.core;

/* compiled from: Util */
/* renamed from: com.amap.api.services.core.as */
class C1090as {
    C1090as() {
    }

    /* renamed from: a */
    static String m4783a(String str) {
        if (str == null) {
            return null;
        }
        String b = C1139z.m5130b(str.getBytes());
        return ((char) ((b.length() % 26) + 65)) + b;
    }

    /* renamed from: b */
    static String m4784b(String str) {
        if (str.length() < 2) {
            return "";
        }
        return C1139z.m5126a(str.substring(1));
    }
}
