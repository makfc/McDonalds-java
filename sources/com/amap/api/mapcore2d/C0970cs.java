package com.amap.api.mapcore2d;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: MD5 */
/* renamed from: com.amap.api.mapcore2d.cs */
public class C0970cs {
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0085 A:{SYNTHETIC, Splitter:B:46:0x0085} */
    /* renamed from: a */
    public static java.lang.String m3992a(java.lang.String r6) {
        /*
        r0 = 0;
        r1 = 0;
        r2 = android.text.TextUtils.isEmpty(r6);	 Catch:{ Throwable -> 0x0094, all -> 0x0080 }
        if (r2 == 0) goto L_0x0017;
    L_0x0008:
        if (r0 == 0) goto L_0x000d;
    L_0x000a:
        r1.close();	 Catch:{ IOException -> 0x000e }
    L_0x000d:
        return r0;
    L_0x000e:
        r1 = move-exception;
        r2 = "MD5";
        r3 = "getMd5FromFile";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r3);
        goto L_0x000d;
    L_0x0017:
        r3 = new java.io.File;	 Catch:{ Throwable -> 0x0094, all -> 0x0080 }
        r3.<init>(r6);	 Catch:{ Throwable -> 0x0094, all -> 0x0080 }
        r2 = r3.isFile();	 Catch:{ Throwable -> 0x0094, all -> 0x0080 }
        if (r2 == 0) goto L_0x0028;
    L_0x0022:
        r2 = r3.exists();	 Catch:{ Throwable -> 0x0094, all -> 0x0080 }
        if (r2 != 0) goto L_0x0037;
    L_0x0028:
        if (r0 == 0) goto L_0x000d;
    L_0x002a:
        r1.close();	 Catch:{ IOException -> 0x002e }
        goto L_0x000d;
    L_0x002e:
        r1 = move-exception;
        r2 = "MD5";
        r3 = "getMd5FromFile";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r3);
        goto L_0x000d;
    L_0x0037:
        r1 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r1 = new byte[r1];	 Catch:{ Throwable -> 0x0094, all -> 0x0080 }
        r2 = "MD5";
        r4 = java.security.MessageDigest.getInstance(r2);	 Catch:{ Throwable -> 0x0094, all -> 0x0080 }
        r2 = new java.io.FileInputStream;	 Catch:{ Throwable -> 0x0094, all -> 0x0080 }
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0094, all -> 0x0080 }
    L_0x0046:
        r3 = r2.read(r1);	 Catch:{ Throwable -> 0x0052 }
        r5 = -1;
        if (r3 == r5) goto L_0x0069;
    L_0x004d:
        r5 = 0;
        r4.update(r1, r5, r3);	 Catch:{ Throwable -> 0x0052 }
        goto L_0x0046;
    L_0x0052:
        r1 = move-exception;
    L_0x0053:
        r3 = "MD5";
        r4 = "getMd5FromFile";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r3, r4);	 Catch:{ all -> 0x0092 }
        if (r2 == 0) goto L_0x000d;
    L_0x005c:
        r2.close();	 Catch:{ IOException -> 0x0060 }
        goto L_0x000d;
    L_0x0060:
        r1 = move-exception;
        r2 = "MD5";
        r3 = "getMd5FromFile";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r3);
        goto L_0x000d;
    L_0x0069:
        r1 = r4.digest();	 Catch:{ Throwable -> 0x0052 }
        r0 = com.amap.api.mapcore2d.C0978cw.m4056d(r1);	 Catch:{ Throwable -> 0x0052 }
        if (r2 == 0) goto L_0x000d;
    L_0x0073:
        r2.close();	 Catch:{ IOException -> 0x0077 }
        goto L_0x000d;
    L_0x0077:
        r1 = move-exception;
        r2 = "MD5";
        r3 = "getMd5FromFile";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r3);
        goto L_0x000d;
    L_0x0080:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0083:
        if (r2 == 0) goto L_0x0088;
    L_0x0085:
        r2.close();	 Catch:{ IOException -> 0x0089 }
    L_0x0088:
        throw r0;
    L_0x0089:
        r1 = move-exception;
        r2 = "MD5";
        r3 = "getMd5FromFile";
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r2, r3);
        goto L_0x0088;
    L_0x0092:
        r0 = move-exception;
        goto L_0x0083;
    L_0x0094:
        r1 = move-exception;
        r2 = r0;
        goto L_0x0053;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.C0970cs.m3992a(java.lang.String):java.lang.String");
    }

    /* renamed from: b */
    public static String m3995b(String str) {
        if (str == null) {
            return null;
        }
        return C0978cw.m4056d(C0970cs.m3998d(str));
    }

    /* renamed from: a */
    public static String m3993a(byte[] bArr) {
        return C0978cw.m4056d(C0970cs.m3996b(bArr));
    }

    /* renamed from: c */
    public static String m3997c(String str) {
        return C0978cw.m4057e(C0970cs.m3999e(str));
    }

    /* renamed from: a */
    public static byte[] m3994a(byte[] bArr, String str) {
        byte[] bArr2 = null;
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return instance.digest();
        } catch (Throwable th) {
            C0982da.m4076a(th, "MD5", "getMd5Bytes1");
            return bArr2;
        }
    }

    /* renamed from: b */
    private static byte[] m3996b(byte[] bArr) {
        return C0970cs.m3994a(bArr, "MD5");
    }

    /* renamed from: d */
    public static byte[] m3998d(String str) {
        try {
            return C0970cs.m4000f(str);
        } catch (Throwable th) {
            C0982da.m4076a(th, "MD5", "getMd5Bytes");
            return new byte[0];
        }
    }

    /* renamed from: e */
    private static byte[] m3999e(String str) {
        try {
            return C0970cs.m4000f(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[0];
        }
    }

    /* renamed from: f */
    private static byte[] m4000f(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(C0978cw.m4050a(str));
        return instance.digest();
    }
}
