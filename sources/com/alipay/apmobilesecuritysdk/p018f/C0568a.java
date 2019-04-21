package com.alipay.apmobilesecuritysdk.p018f;

import android.content.Context;
import com.alipay.security.mobile.module.p019a.C0689a;
import com.alipay.security.mobile.module.p019a.p020a.C0688c;
import com.alipay.security.mobile.module.p022c.C0696b;
import com.alipay.security.mobile.module.p022c.C0699e;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;

/* renamed from: com.alipay.apmobilesecuritysdk.f.a */
public class C0568a {
    /* renamed from: a */
    public static String m730a(Context context, String str, String str2) {
        if (context == null || C0689a.m1169a(str) || C0689a.m1169a(str2)) {
            return null;
        }
        try {
            String a = C0699e.m1253a(context, str, str2, "");
            return !C0689a.m1169a(a) ? C0688c.m1163b(C0688c.m1158a(), a) : null;
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: a */
    public static String m731a(String str, String str2) {
        String str3 = null;
        synchronized (C0568a.class) {
            if (C0689a.m1169a(str) || C0689a.m1169a(str2)) {
            } else {
                try {
                    String a = C0696b.m1249a(str);
                    if (C0689a.m1169a(a)) {
                    } else {
                        a = JSONObjectInstrumentation.init(a).getString(str2);
                        if (C0689a.m1169a(a)) {
                        } else {
                            str3 = C0688c.m1163b(C0688c.m1158a(), a);
                        }
                    }
                } catch (Throwable th) {
                }
            }
        }
        return str3;
    }

    /* renamed from: a */
    public static void m732a(Context context, String str, String str2, String str3) {
        if (!C0689a.m1169a(str) && !C0689a.m1169a(str2) && context != null) {
            try {
                String a = C0688c.m1159a(C0688c.m1158a(), str3);
                HashMap hashMap = new HashMap();
                hashMap.put(str2, a);
                C0699e.m1254a(context, str, hashMap);
            } catch (Throwable th) {
            }
        }
    }

    /* JADX WARNING: Missing block: B:44:?, code skipped:
            return;
     */
    /* renamed from: a */
    public static void m733a(java.lang.String r4, java.lang.String r5, java.lang.String r6) {
        /*
        r1 = com.alipay.apmobilesecuritysdk.p018f.C0568a.class;
        monitor-enter(r1);
        r0 = com.alipay.security.mobile.module.p019a.C0689a.m1169a(r4);	 Catch:{ all -> 0x0076 }
        if (r0 != 0) goto L_0x000f;
    L_0x0009:
        r0 = com.alipay.security.mobile.module.p019a.C0689a.m1169a(r5);	 Catch:{ all -> 0x0076 }
        if (r0 == 0) goto L_0x0011;
    L_0x000f:
        monitor-exit(r1);	 Catch:{ all -> 0x0076 }
    L_0x0010:
        return;
    L_0x0011:
        r2 = com.alipay.security.mobile.module.p022c.C0696b.m1249a(r4);	 Catch:{ Throwable -> 0x0080 }
        r0 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x0080 }
        r0.<init>();	 Catch:{ Throwable -> 0x0080 }
        r3 = com.alipay.security.mobile.module.p019a.C0689a.m1172b(r2);	 Catch:{ Throwable -> 0x0080 }
        if (r3 == 0) goto L_0x0026;
    L_0x0020:
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0079 }
        r0 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.init(r2);	 Catch:{ Exception -> 0x0079 }
    L_0x0026:
        r2 = com.alipay.security.mobile.module.p019a.p020a.C0688c.m1158a();	 Catch:{ Throwable -> 0x0080 }
        r2 = com.alipay.security.mobile.module.p019a.p020a.C0688c.m1159a(r2, r6);	 Catch:{ Throwable -> 0x0080 }
        r0.put(r5, r2);	 Catch:{ Throwable -> 0x0080 }
        r2 = r0 instanceof org.json.JSONObject;	 Catch:{ Throwable -> 0x0080 }
        if (r2 != 0) goto L_0x0082;
    L_0x0035:
        r0.toString();	 Catch:{ Throwable -> 0x0080 }
    L_0x0038:
        java.lang.System.clearProperty(r4);	 Catch:{ Throwable -> 0x0088 }
    L_0x003b:
        r0 = com.alipay.security.mobile.module.p022c.C0697c.m1251a();	 Catch:{ Throwable -> 0x0080 }
        if (r0 == 0) goto L_0x0074;
    L_0x0041:
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0080 }
        r2 = ".SystemConfig";
        r0.<init>(r2);	 Catch:{ Throwable -> 0x0080 }
        r2 = java.io.File.separator;	 Catch:{ Throwable -> 0x0080 }
        r0 = r0.append(r2);	 Catch:{ Throwable -> 0x0080 }
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x0080 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x0080 }
        r2 = com.alipay.security.mobile.module.p022c.C0697c.m1251a();	 Catch:{ Exception -> 0x008a }
        if (r2 == 0) goto L_0x0074;
    L_0x005c:
        r2 = new java.io.File;	 Catch:{ Exception -> 0x008a }
        r3 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ Exception -> 0x008a }
        r2.<init>(r3, r0);	 Catch:{ Exception -> 0x008a }
        r0 = r2.exists();	 Catch:{ Exception -> 0x008a }
        if (r0 == 0) goto L_0x0074;
    L_0x006b:
        r0 = r2.isFile();	 Catch:{ Exception -> 0x008a }
        if (r0 == 0) goto L_0x0074;
    L_0x0071:
        r2.delete();	 Catch:{ Exception -> 0x008a }
    L_0x0074:
        monitor-exit(r1);	 Catch:{ all -> 0x0076 }
        goto L_0x0010;
    L_0x0076:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x0079:
        r0 = move-exception;
        r0 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x0080 }
        r0.<init>();	 Catch:{ Throwable -> 0x0080 }
        goto L_0x0026;
    L_0x0080:
        r0 = move-exception;
        goto L_0x0074;
    L_0x0082:
        r0 = (org.json.JSONObject) r0;	 Catch:{ Throwable -> 0x0080 }
        com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.toString(r0);	 Catch:{ Throwable -> 0x0080 }
        goto L_0x0038;
    L_0x0088:
        r0 = move-exception;
        goto L_0x003b;
    L_0x008a:
        r0 = move-exception;
        goto L_0x0074;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.p018f.C0568a.m733a(java.lang.String, java.lang.String, java.lang.String):void");
    }
}
