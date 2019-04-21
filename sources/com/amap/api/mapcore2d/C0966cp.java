package com.amap.api.mapcore2d;

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

/* compiled from: ClientInfo */
/* renamed from: com.amap.api.mapcore2d.cp */
public class C0966cp {

    /* compiled from: ClientInfo */
    /* renamed from: com.amap.api.mapcore2d.cp$a */
    private static class C0965a {
        /* renamed from: a */
        String f2738a;
        /* renamed from: b */
        String f2739b;
        /* renamed from: c */
        String f2740c;
        /* renamed from: d */
        String f2741d;
        /* renamed from: e */
        String f2742e;
        /* renamed from: f */
        String f2743f;
        /* renamed from: g */
        String f2744g;
        /* renamed from: h */
        String f2745h;
        /* renamed from: i */
        String f2746i;
        /* renamed from: j */
        String f2747j;
        /* renamed from: k */
        String f2748k;
        /* renamed from: l */
        String f2749l;
        /* renamed from: m */
        String f2750m;
        /* renamed from: n */
        String f2751n;
        /* renamed from: o */
        String f2752o;
        /* renamed from: p */
        String f2753p;
        /* renamed from: q */
        String f2754q;
        /* renamed from: r */
        String f2755r;
        /* renamed from: s */
        String f2756s;
        /* renamed from: t */
        String f2757t;

        private C0965a() {
        }
    }

    /* renamed from: a */
    public static String m3940a(Context context, String str, String str2) {
        String str3 = null;
        try {
            return C0970cs.m3995b(C0957cm.m3905e(context) + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            C0982da.m4076a(th, "CInfo", "Scode");
            return str3;
        }
    }

    /* renamed from: a */
    public static String m3935a() {
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
        C0982da.m4076a(th2, "CInfo", "getTS");
        return str;
    }

    /* renamed from: a */
    public static String m3936a(Context context) {
        String str = null;
        try {
            C0965a c0965a = new C0965a();
            c0965a.f2741d = C0957cm.m3903c(context);
            c0965a.f2746i = C0957cm.m3904d(context);
            return C0966cp.m3937a(context, c0965a);
        } catch (Throwable th) {
            C0982da.m4076a(th, "CInfo", "InitXInfo");
            return str;
        }
    }

    /* renamed from: a */
    public static byte[] m3944a(Context context, byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        if (instance == null) {
            return null;
        }
        instance.init(256);
        byte[] encoded = instance.generateKey().getEncoded();
        Key a = C0978cw.m4045a(context);
        if (a == null) {
            return null;
        }
        byte[] a2 = C0969cr.m3986a(encoded, a);
        encoded = C0969cr.m3987a(encoded, bArr);
        byte[] bArr2 = new byte[(a2.length + encoded.length)];
        System.arraycopy(a2, 0, bArr2, 0, a2.length);
        System.arraycopy(encoded, 0, bArr2, a2.length, encoded.length);
        return bArr2;
    }

    /* renamed from: a */
    public static byte[] m3943a(Context context, boolean z) {
        try {
            return C0966cp.m3948b(context, C0966cp.m3945b(context, z));
        } catch (Throwable th) {
            C0982da.m4076a(th, "CInfo", "getGZipXInfo");
            return null;
        }
    }

    @Deprecated
    /* renamed from: a */
    public static String m3939a(Context context, C0977cv c0977cv, Map<String, String> map, boolean z) {
        try {
            return C0966cp.m3937a(context, C0966cp.m3945b(context, z));
        } catch (Throwable th) {
            C0982da.m4076a(th, "CInfo", "rsaLocClineInfo");
            return null;
        }
    }

    /* renamed from: b */
    public static String m3947b(Context context, byte[] bArr) {
        try {
            return C0966cp.m3950d(context, bArr);
        } catch (Throwable th) {
            C0982da.m4076a(th, "CInfo", "AESData");
            return "";
        }
    }

    /* renamed from: c */
    public static byte[] m3949c(Context context, byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Key a = C0978cw.m4045a(context);
        if (bArr.length <= 117) {
            return C0969cr.m3986a(bArr, a);
        }
        byte[] bArr2 = new byte[117];
        System.arraycopy(bArr, 0, bArr2, 0, 117);
        bArr2 = C0969cr.m3986a(bArr2, a);
        byte[] bArr3 = new byte[((bArr.length + 128) - 117)];
        System.arraycopy(bArr2, 0, bArr3, 0, 128);
        System.arraycopy(bArr, 117, bArr3, 128, bArr.length - 117);
        return bArr3;
    }

    /* renamed from: a */
    private static String m3937a(Context context, C0965a c0965a) {
        return C0969cr.m3985a(C0966cp.m3948b(context, c0965a));
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0092 A:{SYNTHETIC, Splitter:B:23:0x0092} */
    /* renamed from: b */
    private static byte[] m3948b(android.content.Context r5, com.amap.api.mapcore2d.C0966cp.C0965a r6) {
        /*
        r0 = 0;
        r2 = new java.io.ByteArrayOutputStream;	 Catch:{ Throwable -> 0x0079, all -> 0x008d }
        r2.<init>();	 Catch:{ Throwable -> 0x0079, all -> 0x008d }
        r1 = r6.f2738a;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2739b;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2740c;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2741d;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2742e;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2743f;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2744g;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2745h;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2746i;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2747j;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2748k;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2749l;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2750m;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2751n;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2752o;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2753p;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2754q;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2755r;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2756s;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r1 = r6.f2757t;	 Catch:{ Throwable -> 0x009d }
        com.amap.api.mapcore2d.C0966cp.m3941a(r2, r1);	 Catch:{ Throwable -> 0x009d }
        r0 = com.amap.api.mapcore2d.C0966cp.m3942a(r5, r2);	 Catch:{ Throwable -> 0x009d }
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
        com.amap.api.mapcore2d.C0982da.m4076a(r1, r3, r4);	 Catch:{ all -> 0x009b }
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
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.C0966cp.m3948b(android.content.Context, com.amap.api.mapcore2d.cp$a):byte[]");
    }

    /* renamed from: a */
    private static byte[] m3942a(Context context, ByteArrayOutputStream byteArrayOutputStream) throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        return C0966cp.m3949c(context, C0978cw.m4052b(byteArrayOutputStream.toByteArray()));
    }

    /* renamed from: d */
    static String m3950d(Context context, byte[] bArr) throws InvalidKeyException, IOException, InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, CertificateException {
        byte[] b = C0978cw.m4052b(C0966cp.m3944a(context, bArr));
        if (b != null) {
            return C0969cr.m3985a(b);
        }
        return "";
    }

    /* renamed from: a */
    public static void m3941a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (TextUtils.isEmpty(str)) {
            C0978cw.m4046a(byteArrayOutputStream, (byte) 0, new byte[0]);
            return;
        }
        byte b;
        if (str.getBytes().length > 255) {
            b = (byte) -1;
        } else {
            b = (byte) str.getBytes().length;
        }
        C0978cw.m4046a(byteArrayOutputStream, b, C0978cw.m4050a(str));
    }

    /* renamed from: e */
    public static String m3951e(Context context, byte[] bArr) {
        try {
            return C0966cp.m3950d(context, bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* renamed from: b */
    public static String m3946b(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"key\":\"").append(C0957cm.m3906f(context)).append("\",\"platform\":\"android\",\"diu\":\"").append(C0968cq.m3974q(context)).append("\",\"pkg\":\"").append(C0957cm.m3903c(context)).append("\",\"model\":\"").append(Build.MODEL).append("\",\"appname\":\"").append(C0957cm.m3902b(context)).append("\",\"appversion\":\"").append(C0957cm.m3904d(context)).append("\",\"sysversion\":\"").append(VERSION.RELEASE).append("\",");
        } catch (Throwable th) {
            C0982da.m4076a(th, "CInfo", "getPublicJSONInfo");
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static String m3938a(Context context, C0977cv c0977cv) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"sim\":\"").append(C0968cq.m3962e(context)).append("\",\"sdkversion\":\"").append(c0977cv.mo10174b()).append("\",\"product\":\"").append(c0977cv.mo10172a()).append("\",\"ed\":\"").append(c0977cv.mo10176d()).append("\",\"nt\":\"").append(C0968cq.m3960c(context)).append("\",\"np\":\"").append(C0968cq.m3952a(context)).append("\",\"mnc\":\"").append(C0968cq.m3958b(context)).append("\",\"ant\":\"").append(C0968cq.m3961d(context)).append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    private static C0965a m3945b(Context context, boolean z) {
        C0965a c0965a = new C0965a();
        c0965a.f2738a = C0968cq.m3974q(context);
        c0965a.f2739b = C0968cq.m3966i(context);
        String f = C0968cq.m3963f(context);
        if (f == null) {
            f = "";
        }
        c0965a.f2740c = f;
        c0965a.f2741d = C0957cm.m3903c(context);
        c0965a.f2742e = Build.MODEL;
        c0965a.f2743f = Build.MANUFACTURER;
        c0965a.f2744g = Build.DEVICE;
        c0965a.f2745h = C0957cm.m3902b(context);
        c0965a.f2746i = C0957cm.m3904d(context);
        c0965a.f2747j = String.valueOf(VERSION.SDK_INT);
        c0965a.f2748k = C0968cq.m3975r(context);
        c0965a.f2749l = C0968cq.m3973p(context);
        c0965a.f2750m = C0968cq.m3970m(context) + "";
        c0965a.f2751n = C0968cq.m3969l(context) + "";
        c0965a.f2752o = C0968cq.m3976s(context);
        c0965a.f2753p = C0968cq.m3968k(context);
        if (z) {
            c0965a.f2754q = "";
        } else {
            c0965a.f2754q = C0968cq.m3965h(context);
        }
        if (z) {
            c0965a.f2755r = "";
        } else {
            c0965a.f2755r = C0968cq.m3964g(context);
        }
        if (z) {
            c0965a.f2756s = "";
            c0965a.f2757t = "";
        } else {
            String[] j = C0968cq.m3967j(context);
            c0965a.f2756s = j[0];
            c0965a.f2757t = j[1];
        }
        return c0965a;
    }
}
