package com.amap.api.mapcore.util;

/* compiled from: Utils */
/* renamed from: com.amap.api.mapcore.util.ff */
public class C0836ff {
    /* renamed from: a */
    public static int m2731a(String str, String str2) {
        int i = 0;
        try {
            String[] split = str.split("\\.");
            String[] split2 = str2.split("\\.");
            int min = Math.min(split.length, split2.length);
            for (int i2 = 0; i2 < min; i2++) {
                i = split[i2].length() - split2[i2].length();
                if (i != 0) {
                    break;
                }
                i = split[i2].compareTo(split2[i2]);
                if (i != 0) {
                    break;
                }
            }
            if (i != 0) {
                return i;
            }
            return split.length - split2.length;
        } catch (Exception e) {
            BasicLogHandler.m2542a(e, "Utils", "compareVersion");
            return -1;
        }
    }
}
