package com.admaster.jice.p007d;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.admaster.jice.d.f */
public class Md5Util {
    /* renamed from: a */
    private static char[] f133a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    /* renamed from: b */
    private static MessageDigest f134b;

    static {
        f134b = null;
        try {
            f134b = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
        }
    }

    /* renamed from: a */
    public static String m241a(String str) {
        return Md5Util.m242a(str.getBytes());
    }

    /* renamed from: a */
    public static String m242a(byte[] bArr) {
        f134b.update(bArr);
        return Md5Util.m245b(f134b.digest());
    }

    /* renamed from: b */
    private static String m245b(byte[] bArr) {
        return Md5Util.m243a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    private static String m243a(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer(i2 * 2);
        int i3 = i + i2;
        while (i < i3) {
            Md5Util.m244a(bArr[i], stringBuffer);
            i++;
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    private static void m244a(byte b, StringBuffer stringBuffer) {
        char c = f133a[(b & 240) >> 4];
        char c2 = f133a[b & 15];
        stringBuffer.append(c);
        stringBuffer.append(c2);
    }
}
