package com.aps;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/* renamed from: com.aps.s */
public class Storage {
    /* renamed from: a */
    private static Storage f4530a = null;

    private Storage() {
    }

    /* renamed from: a */
    static String m5711a(Object obj, String str) {
        DecimalFormat decimalFormat = new DecimalFormat("#", new DecimalFormatSymbols(Locale.US));
        decimalFormat.applyPattern(str);
        return decimalFormat.format(obj);
    }

    /* renamed from: a */
    static byte[] m5714a(String str) {
        return Storage.m5712a(Integer.parseInt(str));
    }

    /* renamed from: a */
    static byte[] m5712a(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }

    /* renamed from: b */
    static byte[] m5716b(String str) {
        return Storage.m5715b(Integer.parseInt(str));
    }

    /* renamed from: b */
    static byte[] m5715b(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255)};
    }

    /* renamed from: a */
    public static byte[] m5713a(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) ((int) ((j >> (i * 8)) & 255));
        }
        return bArr;
    }
}
