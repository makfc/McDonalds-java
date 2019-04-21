package com.admaster.jice.p005b;

import android.text.TextUtils;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* renamed from: com.admaster.jice.b.a */
public class HttpConfig {
    /* renamed from: a */
    public static TrustManager[] f113a = new TrustManager[]{new C0474b()};
    /* renamed from: b */
    private static SSLSocketFactory f114b;
    /* renamed from: c */
    private static String f115c = null;
    /* renamed from: d */
    private static String f116d = null;
    /* renamed from: e */
    private static boolean f117e = false;
    /* renamed from: f */
    private static boolean f118f = false;
    /* renamed from: g */
    private static boolean f119g = false;
    /* renamed from: h */
    private static String f120h = "";
    /* renamed from: i */
    private static final String f121i;
    /* renamed from: j */
    private static final String f122j;
    /* renamed from: k */
    private static String f123k;

    static {
        switch (null) {
            case null:
                f121i = "jice.fw4.me";
                f122j = "https://" + f121i;
                f123k = "https://ac.jice.io";
                break;
            case 1:
                f121i = "jice.fw4.me";
                f122j = "https://" + f121i;
                f123k = "http://jice.stonephp.com";
                break;
            default:
                f121i = "58.215.168.191";
                f122j = "http://" + f121i + ":8888";
                break;
        }
    }

    /* renamed from: a */
    public static void m156a(java.lang.String r5) {
        /*
        r0 = 0;
        r1 = 0;
        switch(r1) {
            case 0: goto L_0x001d;
            case 1: goto L_0x0031;
            default: goto L_0x0005;
        };
    L_0x0005:
        r1 = "TLS";
        r1 = javax.net.ssl.SSLContext.getInstance(r1);	 Catch:{ GeneralSecurityException -> 0x0040 }
        r2 = 0;
        r3 = f113a;	 Catch:{ GeneralSecurityException -> 0x0040 }
        r4 = new java.security.SecureRandom;	 Catch:{ GeneralSecurityException -> 0x0040 }
        r4.<init>();	 Catch:{ GeneralSecurityException -> 0x0040 }
        r1.init(r2, r3, r4);	 Catch:{ GeneralSecurityException -> 0x0040 }
        r0 = r1.getSocketFactory();	 Catch:{ GeneralSecurityException -> 0x0040 }
    L_0x001a:
        f114b = r0;
        return;
    L_0x001d:
        r1 = "/api_v1/apps/%s/campaigns/actives";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x002c }
        r3 = 0;
        r2[r3] = r5;	 Catch:{ Exception -> 0x002c }
        r1 = java.lang.String.format(r1, r2);	 Catch:{ Exception -> 0x002c }
        f120h = r1;	 Catch:{ Exception -> 0x002c }
        goto L_0x0005;
    L_0x002c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0005;
    L_0x0031:
        r1 = "/push/apps/%s/campaigns/actives";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x002c }
        r3 = 0;
        r2[r3] = r5;	 Catch:{ Exception -> 0x002c }
        r1 = java.lang.String.format(r1, r2);	 Catch:{ Exception -> 0x002c }
        f120h = r1;	 Catch:{ Exception -> 0x002c }
        goto L_0x0005;
    L_0x0040:
        r1 = move-exception;
        r2 = "JiceSDK";
        r3 = "System has no SSL support.";
        com.admaster.jice.p007d.LOG.m225a(r2, r3, r1);
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.admaster.jice.p005b.HttpConfig.m156a(java.lang.String):void");
    }

    /* renamed from: a */
    public static void m157a(boolean z) {
        f117e = z;
    }

    /* renamed from: a */
    public static boolean m158a() {
        return f117e;
    }

    /* renamed from: b */
    public static void m160b(boolean z) {
        f119g = z;
    }

    /* renamed from: b */
    public static boolean m161b() {
        return f119g;
    }

    /* renamed from: c */
    public static void m163c(boolean z) {
        f118f = z;
    }

    /* renamed from: c */
    public static boolean m164c() {
        return f118f;
    }

    /* renamed from: d */
    public static String m165d() {
        if (TextUtils.isEmpty(f115c)) {
            f115c = f123k;
        }
        return f115c + f120h;
    }

    /* renamed from: b */
    public static void m159b(String str) {
        f115c = str;
    }

    /* renamed from: e */
    public static String m166e() {
        if (TextUtils.isEmpty(f116d)) {
            f116d = f122j;
        }
        return f116d + "/jc.gif";
    }

    /* renamed from: c */
    public static void m162c(String str) {
        f116d = str;
    }

    /* renamed from: f */
    public static SSLSocketFactory m167f() {
        return f114b;
    }
}
