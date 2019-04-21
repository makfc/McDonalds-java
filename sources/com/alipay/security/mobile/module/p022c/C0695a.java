package com.alipay.security.mobile.module.p022c;

import android.content.Context;
import com.alipay.security.mobile.module.p019a.C0689a;
import com.alipay.security.mobile.module.p019a.p020a.C0688c;

/* renamed from: com.alipay.security.mobile.module.c.a */
public class C0695a {
    /* renamed from: a */
    public static String m1247a(Context context, String str, String str2) {
        String str3 = null;
        synchronized (C0695a.class) {
            if (context != null) {
                if (!(C0689a.m1169a(str) || C0689a.m1169a(str2))) {
                    try {
                        String a = C0699e.m1253a(context, str, str2, "");
                        if (C0689a.m1169a(a)) {
                        } else {
                            str3 = C0688c.m1163b(C0688c.m1158a(), a);
                        }
                    } catch (Throwable th) {
                    }
                }
            }
        }
        return str3;
    }

    /* JADX WARNING: Missing block: B:17:?, code skipped:
            return;
     */
    /* renamed from: a */
    public static void m1248a(android.content.Context r3, java.lang.String r4, java.lang.String r5, java.lang.String r6) {
        /*
        r1 = com.alipay.security.mobile.module.p022c.C0695a.class;
        monitor-enter(r1);
        r0 = com.alipay.security.mobile.module.p019a.C0689a.m1169a(r4);	 Catch:{ all -> 0x0028 }
        if (r0 != 0) goto L_0x0011;
    L_0x0009:
        r0 = com.alipay.security.mobile.module.p019a.C0689a.m1169a(r5);	 Catch:{ all -> 0x0028 }
        if (r0 != 0) goto L_0x0011;
    L_0x000f:
        if (r3 != 0) goto L_0x0013;
    L_0x0011:
        monitor-exit(r1);	 Catch:{ all -> 0x0028 }
    L_0x0012:
        return;
    L_0x0013:
        r0 = com.alipay.security.mobile.module.p019a.p020a.C0688c.m1158a();	 Catch:{ Throwable -> 0x002b }
        r0 = com.alipay.security.mobile.module.p019a.p020a.C0688c.m1159a(r0, r6);	 Catch:{ Throwable -> 0x002b }
        r2 = new java.util.HashMap;	 Catch:{ Throwable -> 0x002b }
        r2.<init>();	 Catch:{ Throwable -> 0x002b }
        r2.put(r5, r0);	 Catch:{ Throwable -> 0x002b }
        com.alipay.security.mobile.module.p022c.C0699e.m1254a(r3, r4, r2);	 Catch:{ Throwable -> 0x002b }
    L_0x0026:
        monitor-exit(r1);	 Catch:{ all -> 0x0028 }
        goto L_0x0012;
    L_0x0028:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x002b:
        r0 = move-exception;
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p022c.C0695a.m1248a(android.content.Context, java.lang.String, java.lang.String, java.lang.String):void");
    }
}
