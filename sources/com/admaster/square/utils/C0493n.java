package com.admaster.square.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: Md5Util */
/* renamed from: com.admaster.square.utils.n */
public class C0493n {
    /* renamed from: a */
    protected static char[] f297a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    /* renamed from: b */
    protected static MessageDigest f298b;

    static {
        f298b = null;
        try {
            f298b = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
        }
    }

    /* renamed from: a */
    public static String m447a(String str) {
        return C0493n.m448a(str.getBytes());
    }

    /* renamed from: a */
    public static String m448a(byte[] bArr) {
        f298b.update(bArr);
        return C0493n.m451b(f298b.digest());
    }

    /* renamed from: b */
    private static String m451b(byte[] bArr) {
        return C0493n.m449a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    private static String m449a(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer(i2 * 2);
        int i3 = i + i2;
        while (i < i3) {
            C0493n.m450a(bArr[i], stringBuffer);
            i++;
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    private static void m450a(byte b, StringBuffer stringBuffer) {
        char c = f297a[(b & 240) >> 4];
        char c2 = f297a[b & 15];
        stringBuffer.append(c);
        stringBuffer.append(c2);
    }
}
