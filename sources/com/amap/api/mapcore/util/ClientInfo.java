package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

/* renamed from: com.amap.api.mapcore.util.dn */
public class ClientInfo {

    /* compiled from: ClientInfo */
    /* renamed from: com.amap.api.mapcore.util.dn$a */
    private static class C0812a {
        /* renamed from: a */
        String f1763a;
        /* renamed from: b */
        String f1764b;
        /* renamed from: c */
        String f1765c;
        /* renamed from: d */
        String f1766d;
        /* renamed from: e */
        String f1767e;
        /* renamed from: f */
        String f1768f;
        /* renamed from: g */
        String f1769g;
        /* renamed from: h */
        String f1770h;
        /* renamed from: i */
        String f1771i;
        /* renamed from: j */
        String f1772j;
        /* renamed from: k */
        String f1773k;
        /* renamed from: l */
        String f1774l;
        /* renamed from: m */
        String f1775m;
        /* renamed from: n */
        String f1776n;
        /* renamed from: o */
        String f1777o;
        /* renamed from: p */
        String f1778p;
        /* renamed from: q */
        String f1779q;
        /* renamed from: r */
        String f1780r;
        /* renamed from: s */
        String f1781s;
        /* renamed from: t */
        String f1782t;

        private C0812a() {
        }

        /* synthetic */ C0812a(C0813do c0813do) {
            this();
        }
    }

    /* renamed from: a */
    public static String m2401a(Context context, String str, String str2) {
        String str3 = null;
        try {
            return C0822ds.m2464b(AppInfo.m2386e(context) + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "CInfo", "Scode");
            return str3;
        }
    }

    /* renamed from: a */
    public static String m2396a() {
        Throwable th;
        String str;
        Throwable th2;
        String str2 = null;
        try {
            str2 = String.valueOf(System.currentTimeMillis());
            try {
                int length = str2.length();
                return str2.substring(0, length - 2) + "1" + str2.substring(length - 1);
            } catch (Throwable th3) {
                th = th3;
                str = str2;
                th2 = th;
            }
        } catch (Throwable th32) {
            th = th32;
            str = str2;
            th2 = th;
        }
        BasicLogHandler.m2542a(th2, "CInfo", "getTS");
        return str;
    }

    /* renamed from: a */
    public static byte[] m2404a(Context context, byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        if (instance == null) {
            return null;
        }
        instance.init(256);
        byte[] encoded = instance.generateKey().getEncoded();
        Key a = Utils.m2510a(context);
        if (a == null) {
            return null;
        }
        byte[] a2 = C0821dr.m2455a(encoded, a);
        encoded = C0821dr.m2456a(encoded, bArr);
        byte[] bArr2 = new byte[(a2.length + encoded.length)];
        System.arraycopy(a2, 0, bArr2, 0, a2.length);
        System.arraycopy(encoded, 0, bArr2, a2.length, encoded.length);
        return bArr2;
    }

    @Deprecated
    /* renamed from: a */
    public static String m2400a(Context context, SDKInfo sDKInfo, Map<String, String> map, boolean z) {
        try {
            return ClientInfo.m2398a(context, ClientInfo.m2395a(context, z));
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "CInfo", "rsaLocClineInfo");
            return null;
        }
    }

    /* renamed from: b */
    public static String m2405b(Context context, byte[] bArr) {
        try {
            return ClientInfo.m2408d(context, bArr);
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "CInfo", "AESData");
            return "";
        }
    }

    /* renamed from: c */
    public static byte[] m2407c(Context context, byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Key a = Utils.m2510a(context);
        if (bArr.length <= 117) {
            return C0821dr.m2455a(bArr, a);
        }
        byte[] bArr2 = new byte[117];
        System.arraycopy(bArr, 0, bArr2, 0, 117);
        bArr2 = C0821dr.m2455a(bArr2, a);
        byte[] bArr3 = new byte[((bArr.length + 128) - 117)];
        System.arraycopy(bArr2, 0, bArr3, 0, 128);
        System.arraycopy(bArr, 117, bArr3, 128, bArr.length - 117);
        return bArr3;
    }

    /* renamed from: a */
    private static String m2398a(Context context, C0812a c0812a) {
        return C0821dr.m2454a(ClientInfo.m2406b(context, c0812a));
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0092 A:{SYNTHETIC, Splitter:B:23:0x0092} */
    /* renamed from: b */
    private static byte[] m2406b(android.content.Context r5, com.amap.api.mapcore.util.ClientInfo.C0812a r6) {
        /*
        r0 = 0;
        r2 = new java.io.ByteArrayOutputStream;	 Catch:{ Throwable -> 0x0079, all -> 0x008d }
        r2.<init>();	 Catch:{ Throwable -> 0x0079, all -> 0x008d }
        r1 = r6.f1763a;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1764b;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1765c;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1766d;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1767e;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1768f;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1769g;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1770h;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1771i;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1772j;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1773k;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1774l;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1775m;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1776n;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1777o;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1778p;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1779q;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1780r;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1781s;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f1782t;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore.util.ClientInfo.m2402a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r0 = com.amap.api.mapcore.util.ClientInfo.m2403a(r5, r2);	 Catch:{ Throwable -> 0x009d }
        if (r2 == 0) goto L_0x0073;
    L_0x0070:
        r2.close();	 Catch:{ Throwable -> 0x0074 }
    L_0x0073:
        return r0;
    L_0x0074:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0073;
    L_0x0079:
        r1 = move-exception;
        r2 = r0;
    L_0x007b:
        r3 = "CInfo";
        r4 = "InitXInfo";
        com.amap.api.mapcore.util.BasicLogHandler.m2542a(r1, r3, r4);	 Catch:{ all -> 0x009b }
        if (r2 == 0) goto L_0x0073;
    L_0x0084:
        r2.close();	 Catch:{ Throwable -> 0x0088 }
        goto L_0x0073;
    L_0x0088:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0073;
    L_0x008d:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0090:
        if (r2 == 0) goto L_0x0095;
    L_0x0092:
        r2.close();	 Catch:{ Throwable -> 0x0096 }
    L_0x0095:
        throw r0;
    L_0x0096:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0095;
    L_0x009b:
        r0 = move-exception;
        goto L_0x0090;
    L_0x009d:
        r1 = move-exception;
        goto L_0x007b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ClientInfo.m2406b(android.content.Context, com.amap.api.mapcore.util.dn$a):byte[]");
    }

    /* renamed from: a */
    private static byte[] m2403a(Context context, ByteArrayOutputStream byteArrayOutputStream) throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        return ClientInfo.m2407c(context, Utils.m2518b(byteArrayOutputStream.toByteArray()));
    }

    /* renamed from: d */
    static String m2408d(Context context, byte[] bArr) throws InvalidKeyException, IOException, InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, CertificateException {
        byte[] b = Utils.m2518b(ClientInfo.m2404a(context, bArr));
        if (b != null) {
            return C0821dr.m2454a(b);
        }
        return "";
    }

    /* renamed from: a */
    public static void m2402a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (TextUtils.isEmpty(str)) {
            Utils.m2511a(byteArrayOutputStream, (byte) 0, new byte[0]);
            return;
        }
        byte b;
        if (str.getBytes().length > 255) {
            b = (byte) -1;
        } else {
            b = (byte) str.getBytes().length;
        }
        Utils.m2511a(byteArrayOutputStream, b, Utils.m2515a(str));
    }

    /* renamed from: e */
    public static String m2409e(Context context, byte[] bArr) {
        try {
            return ClientInfo.m2408d(context, bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public static String m2397a(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"key\":\"").append(AppInfo.m2387f(context)).append("\",\"platform\":\"android\",\"diu\":\"").append(C0820dq.m2443q(context)).append("\",\"pkg\":\"").append(AppInfo.m2384c(context)).append("\",\"model\":\"").append(Build.MODEL).append("\",\"appname\":\"").append(AppInfo.m2383b(context)).append("\",\"appversion\":\"").append(AppInfo.m2385d(context)).append("\",\"sysversion\":\"").append(VERSION.RELEASE).append("\",");
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "CInfo", "getPublicJSONInfo");
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static String m2399a(Context context, SDKInfo sDKInfo) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"sim\":\"").append(C0820dq.m2431e(context)).append("\",\"sdkversion\":\"").append(sDKInfo.mo9294b()).append("\",\"product\":\"").append(sDKInfo.mo9292a()).append("\",\"ed\":\"").append(sDKInfo.mo9296d()).append("\",\"nt\":\"").append(C0820dq.m2429c(context)).append("\",\"np\":\"").append(C0820dq.m2421a(context)).append("\",\"mnc\":\"").append(C0820dq.m2427b(context)).append("\",\"ant\":\"").append(C0820dq.m2430d(context)).append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    private static C0812a m2395a(Context context, boolean z) {
        C0812a c0812a = new C0812a();
        c0812a.f1763a = C0820dq.m2443q(context);
        c0812a.f1764b = C0820dq.m2435i(context);
        String f = C0820dq.m2432f(context);
        if (f == null) {
            f = "";
        }
        c0812a.f1765c = f;
        c0812a.f1766d = AppInfo.m2384c(context);
        c0812a.f1767e = Build.MODEL;
        c0812a.f1768f = Build.MANUFACTURER;
        c0812a.f1769g = Build.DEVICE;
        c0812a.f1770h = AppInfo.m2383b(context);
        c0812a.f1771i = AppInfo.m2385d(context);
        c0812a.f1772j = String.valueOf(VERSION.SDK_INT);
        c0812a.f1773k = C0820dq.m2444r(context);
        c0812a.f1774l = C0820dq.m2442p(context);
        c0812a.f1775m = C0820dq.m2439m(context) + "";
        c0812a.f1776n = C0820dq.m2438l(context) + "";
        c0812a.f1777o = C0820dq.m2445s(context);
        c0812a.f1778p = C0820dq.m2437k(context);
        if (z) {
            c0812a.f1779q = "";
        } else {
            c0812a.f1779q = C0820dq.m2434h(context);
        }
        if (z) {
            c0812a.f1780r = "";
        } else {
            c0812a.f1780r = C0820dq.m2433g(context);
        }
        if (z) {
            c0812a.f1781s = "";
            c0812a.f1782t = "";
        } else {
            String[] j = C0820dq.m2436j(context);
            c0812a.f1781s = j[0];
            c0812a.f1782t = j[1];
        }
        return c0812a;
    }
}
