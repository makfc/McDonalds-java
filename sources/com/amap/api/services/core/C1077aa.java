package com.amap.api.services.core;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: MD5 */
/* renamed from: com.amap.api.services.core.aa */
public class C1077aa {
    /* renamed from: a */
    public static String m4682a(String str) {
        if (str == null) {
            return null;
        }
        return C1082ad.m4714c(C1077aa.m4686c(str));
    }

    /* renamed from: a */
    public static String m4683a(byte[] bArr) {
        return C1082ad.m4714c(C1077aa.m4685b(bArr));
    }

    /* renamed from: b */
    public static String m4684b(String str) {
        return C1082ad.m4715d(C1077aa.m4687d(str));
    }

    /* renamed from: b */
    private static byte[] m4685b(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            return instance.digest();
        } catch (NoSuchAlgorithmException e) {
            C1099ax.m4800a(e, "MD5", "getMd5Bytes");
            e.printStackTrace();
            return bArr2;
        } catch (Throwable e2) {
            C1099ax.m4800a(e2, "MD5", "getMd5Bytes1");
            e2.printStackTrace();
            return bArr2;
        }
    }

    /* renamed from: c */
    public static byte[] m4686c(String str) {
        try {
            return C1077aa.m4688e(str);
        } catch (NoSuchAlgorithmException e) {
            C1099ax.m4800a(e, "MD5", "getMd5Bytes");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e2) {
            C1099ax.m4800a(e2, "MD5", "getMd5Bytes");
            e2.printStackTrace();
        } catch (Throwable e22) {
            C1099ax.m4800a(e22, "MD5", "getMd5Bytes");
            e22.printStackTrace();
        }
        return new byte[0];
    }

    /* renamed from: d */
    private static byte[] m4687d(String str) {
        try {
            return C1077aa.m4688e(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return new byte[0];
    }

    /* renamed from: e */
    private static byte[] m4688e(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(str.getBytes("utf-8"));
        return instance.digest();
    }
}
