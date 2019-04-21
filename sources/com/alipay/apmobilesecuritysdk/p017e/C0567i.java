package com.alipay.apmobilesecuritysdk.p017e;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p015c.C0551a;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.alipay.apmobilesecuritysdk.e.i */
public final class C0567i {
    /* renamed from: a */
    private static String f430a = "";
    /* renamed from: b */
    private static String f431b = "";
    /* renamed from: c */
    private static String f432c = "";
    /* renamed from: d */
    private static String f433d = "";
    /* renamed from: e */
    private static String f434e = "";
    /* renamed from: f */
    private static Map<String, String> f435f = new HashMap();

    /* JADX WARNING: Missing block: B:6:0x0026, code skipped:
            if (com.alipay.security.mobile.module.p019a.C0689a.m1172b(r0) != false) goto L_0x0028;
     */
    /* renamed from: a */
    public static synchronized java.lang.String m712a(java.lang.String r3) {
        /*
        r1 = com.alipay.apmobilesecuritysdk.p017e.C0567i.class;
        monitor-enter(r1);
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x002d }
        r2 = "apdidTokenCache";
        r0.<init>(r2);	 Catch:{ all -> 0x002d }
        r0 = r0.append(r3);	 Catch:{ all -> 0x002d }
        r0 = r0.toString();	 Catch:{ all -> 0x002d }
        r2 = f435f;	 Catch:{ all -> 0x002d }
        r2 = r2.containsKey(r0);	 Catch:{ all -> 0x002d }
        if (r2 == 0) goto L_0x002a;
    L_0x001a:
        r2 = f435f;	 Catch:{ all -> 0x002d }
        r0 = r2.get(r0);	 Catch:{ all -> 0x002d }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x002d }
        r2 = com.alipay.security.mobile.module.p019a.C0689a.m1172b(r0);	 Catch:{ all -> 0x002d }
        if (r2 == 0) goto L_0x002a;
    L_0x0028:
        monitor-exit(r1);
        return r0;
    L_0x002a:
        r0 = "";
        goto L_0x0028;
    L_0x002d:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.p017e.C0567i.m712a(java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public static synchronized void m713a() {
        synchronized (C0567i.class) {
        }
    }

    /* renamed from: a */
    public static synchronized void m714a(C0560b c0560b) {
        synchronized (C0567i.class) {
            if (c0560b != null) {
                f430a = c0560b.mo7936a();
                f431b = c0560b.mo7937b();
                f432c = c0560b.mo7938c();
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m715a(C0561c c0561c) {
        synchronized (C0567i.class) {
            if (c0561c != null) {
                f430a = c0561c.mo7939a();
                f431b = c0561c.mo7940b();
                f433d = c0561c.mo7942d();
                f434e = c0561c.mo7943e();
                f432c = c0561c.mo7941c();
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m716a(String str, String str2) {
        synchronized (C0567i.class) {
            String str3 = "apdidTokenCache" + str;
            if (f435f.containsKey(str3)) {
                f435f.remove(str3);
            }
            f435f.put(str3, str2);
        }
    }

    /* renamed from: a */
    public static synchronized boolean m717a(Context context, String str) {
        boolean z;
        long j = 86400000;
        synchronized (C0567i.class) {
            try {
                long a = C0566h.m695a(context);
                if (a >= 0) {
                    j = a;
                }
            } catch (Throwable th) {
            }
            try {
                if (Math.abs(System.currentTimeMillis() - C0566h.m711h(context, str)) < j) {
                    z = true;
                }
            } catch (Throwable th2) {
                C0551a.m644a(th2);
            }
            z = false;
        }
        return z;
    }

    /* renamed from: b */
    public static synchronized String m718b() {
        String str;
        synchronized (C0567i.class) {
            str = f430a;
        }
        return str;
    }

    /* renamed from: b */
    public static void m719b(String str) {
        f430a = str;
    }

    /* renamed from: c */
    public static synchronized String m720c() {
        String str;
        synchronized (C0567i.class) {
            str = f431b;
        }
        return str;
    }

    /* renamed from: c */
    public static void m721c(String str) {
        f431b = str;
    }

    /* renamed from: d */
    public static synchronized String m722d() {
        String str;
        synchronized (C0567i.class) {
            str = f433d;
        }
        return str;
    }

    /* renamed from: d */
    public static void m723d(String str) {
        f432c = str;
    }

    /* renamed from: e */
    public static synchronized String m724e() {
        String str;
        synchronized (C0567i.class) {
            str = f434e;
        }
        return str;
    }

    /* renamed from: e */
    public static void m725e(String str) {
        f433d = str;
    }

    /* renamed from: f */
    public static synchronized String m726f() {
        String str;
        synchronized (C0567i.class) {
            str = f432c;
        }
        return str;
    }

    /* renamed from: f */
    public static void m727f(String str) {
        f434e = str;
    }

    /* renamed from: g */
    public static synchronized C0561c m728g() {
        C0561c c0561c;
        synchronized (C0567i.class) {
            c0561c = new C0561c(f430a, f431b, f432c, f433d, f434e);
        }
        return c0561c;
    }

    /* renamed from: h */
    public static void m729h() {
        f435f.clear();
        f430a = "";
        f431b = "";
        f433d = "";
        f434e = "";
        f432c = "";
    }
}
