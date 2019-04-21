package com.baidu.android.pushservice.util;

import android.content.Context;
import android.provider.Settings.System;
import android.text.TextUtils;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p036h.C1426b;

/* renamed from: com.baidu.android.pushservice.util.c */
public class C1535c {
    /* renamed from: a */
    private static final Object f5373a = new Object();

    /* renamed from: a */
    public static String m6900a(Context context, String str) {
        if (context == null) {
            return "";
        }
        CharSequence charSequence = null;
        try {
            charSequence = System.getString(context.getContentResolver(), str);
        } catch (Exception e) {
            C1426b.m6445a("CommonParams", "getString " + str + " exception. ", context.getApplicationContext());
        }
        if (!TextUtils.isEmpty(charSequence)) {
            return charSequence;
        }
        try {
            Object b = C1535c.m6905b(context, str);
            if (b != null && (b instanceof String)) {
                return String.valueOf(b);
            }
            C1425a.m6442c("CommonParams", "no sp or external storage found: " + str);
            return charSequence;
        } catch (Exception e2) {
            C1426b.m6447b("CommonParams", "cast to String " + str + " Exception. ", context.getApplicationContext());
            return charSequence;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0083 A:{SYNTHETIC, Splitter:B:26:0x0083} */
    /* renamed from: a */
    private static org.json.JSONObject m6901a(android.content.Context r6) {
        /*
        r0 = new org.json.JSONObject;
        r0.<init>();
        r2 = 0;
        r1 = new java.io.File;	 Catch:{ Exception -> 0x0056 }
        r3 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ Exception -> 0x0056 }
        r4 = "baidu/pushservice/files";
        r1.<init>(r3, r4);	 Catch:{ Exception -> 0x0056 }
        r3 = r1.exists();	 Catch:{ Exception -> 0x0056 }
        if (r3 != 0) goto L_0x001a;
    L_0x0017:
        r1.mkdirs();	 Catch:{ Exception -> 0x0056 }
    L_0x001a:
        r4 = new java.io.File;	 Catch:{ Exception -> 0x0056 }
        r3 = ".info";
        r4.<init>(r1, r3);	 Catch:{ Exception -> 0x0056 }
        r1 = r4.exists();	 Catch:{ Exception -> 0x0056 }
        if (r1 == 0) goto L_0x0094;
    L_0x0027:
        r3 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0056 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x0056 }
        r1 = r3.available();	 Catch:{ Exception -> 0x0091, all -> 0x008e }
        r1 = new byte[r1];	 Catch:{ Exception -> 0x0091, all -> 0x008e }
        r3.read(r1);	 Catch:{ Exception -> 0x0091, all -> 0x008e }
        r2 = new java.lang.String;	 Catch:{ Exception -> 0x0091, all -> 0x008e }
        r4 = "utf-8";
        r2.<init>(r1, r4);	 Catch:{ Exception -> 0x0091, all -> 0x008e }
        r1 = "";
        r1 = com.baidu.android.pushservice.jni.BaiduAppSSOJni.getDecrypted(r6, r1, r2);	 Catch:{ Exception -> 0x0091, all -> 0x008e }
        r2 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0091, all -> 0x008e }
        r0 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.init(r1);	 Catch:{ Exception -> 0x0091, all -> 0x008e }
    L_0x0049:
        if (r3 == 0) goto L_0x004e;
    L_0x004b:
        r3.close();	 Catch:{ IOException -> 0x004f }
    L_0x004e:
        return r0;
    L_0x004f:
        r1 = move-exception;
        r2 = "CommonParams";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x004e;
    L_0x0056:
        r1 = move-exception;
    L_0x0057:
        r3 = "CommonParams";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0080 }
        r4.<init>();	 Catch:{ all -> 0x0080 }
        r5 = "get params exception: ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0080 }
        r1 = r4.append(r1);	 Catch:{ all -> 0x0080 }
        r1 = r1.toString();	 Catch:{ all -> 0x0080 }
        r4 = r6.getApplicationContext();	 Catch:{ all -> 0x0080 }
        com.baidu.android.pushservice.p036h.C1426b.m6447b(r3, r1, r4);	 Catch:{ all -> 0x0080 }
        if (r2 == 0) goto L_0x004e;
    L_0x0075:
        r2.close();	 Catch:{ IOException -> 0x0079 }
        goto L_0x004e;
    L_0x0079:
        r1 = move-exception;
        r2 = "CommonParams";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x004e;
    L_0x0080:
        r0 = move-exception;
    L_0x0081:
        if (r2 == 0) goto L_0x0086;
    L_0x0083:
        r2.close();	 Catch:{ IOException -> 0x0087 }
    L_0x0086:
        throw r0;
    L_0x0087:
        r1 = move-exception;
        r2 = "CommonParams";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);
        goto L_0x0086;
    L_0x008e:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0081;
    L_0x0091:
        r1 = move-exception;
        r2 = r3;
        goto L_0x0057;
    L_0x0094:
        r3 = r2;
        goto L_0x0049;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1535c.m6901a(android.content.Context):org.json.JSONObject");
    }

    /* renamed from: a */
    public static void m6902a(Context context, String str, int i) {
        if (context != null) {
            boolean z = false;
            try {
                if (C1578v.m7142r(context, "android.permission.WRITE_SETTINGS")) {
                    z = System.putInt(context.getContentResolver(), str, i);
                }
            } catch (Exception e) {
                C1426b.m6445a("CommonParams", "putInt " + str + " Exception. ", context.getApplicationContext());
            }
            if (!z) {
                C1535c.m6904a(context, str, Integer.valueOf(i));
            }
        }
    }

    /* renamed from: a */
    public static void m6903a(Context context, String str, String str2) {
        if (context != null) {
            boolean z = false;
            try {
                z = System.putString(context.getContentResolver(), str, str2);
            } catch (Exception e) {
                C1426b.m6445a("CommonParams", "putString " + str + " Exception. ", context.getApplicationContext());
            }
            if (!z) {
                C1535c.m6904a(context, str, (Object) str2);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x00bc A:{SYNTHETIC, Splitter:B:60:0x00bc} */
    /* renamed from: a */
    private static boolean m6904a(android.content.Context r7, java.lang.String r8, java.lang.Object r9) {
        /*
        r1 = 0;
        r0 = "android.permission.WRITE_EXTERNAL_STORAGE";
        r0 = com.baidu.android.pushservice.util.C1578v.m7142r(r7, r0);
        if (r0 == 0) goto L_0x00c7;
    L_0x0009:
        r4 = f5373a;
        monitor-enter(r4);
        r0 = com.baidu.android.pushservice.util.C1535c.m6901a(r7);	 Catch:{ all -> 0x0062 }
        r3 = r0.opt(r8);	 Catch:{ all -> 0x0062 }
        r2 = 0;
        if (r3 == 0) goto L_0x001a;
    L_0x0017:
        r0.remove(r8);	 Catch:{ all -> 0x0062 }
    L_0x001a:
        r0.put(r8, r9);	 Catch:{ Exception -> 0x008c }
        r3 = new java.io.File;	 Catch:{ Exception -> 0x008c }
        r5 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ Exception -> 0x008c }
        r6 = "baidu/pushservice/files";
        r3.<init>(r5, r6);	 Catch:{ Exception -> 0x008c }
        r5 = r3.exists();	 Catch:{ Exception -> 0x008c }
        if (r5 != 0) goto L_0x0031;
    L_0x002e:
        r3.mkdirs();	 Catch:{ Exception -> 0x008c }
    L_0x0031:
        r5 = new java.io.File;	 Catch:{ Exception -> 0x008c }
        r6 = ".info";
        r5.<init>(r3, r6);	 Catch:{ Exception -> 0x008c }
        r3 = "";
        r6 = r0 instanceof org.json.JSONObject;	 Catch:{ Exception -> 0x008c }
        if (r6 != 0) goto L_0x0054;
    L_0x003e:
        r0 = r0.toString();	 Catch:{ Exception -> 0x008c }
    L_0x0042:
        r0 = com.baidu.android.pushservice.jni.BaiduAppSSOJni.getEncrypted(r7, r3, r0);	 Catch:{ Exception -> 0x008c }
        r3 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x008c }
        if (r3 == 0) goto L_0x0065;
    L_0x004c:
        if (r2 == 0) goto L_0x0051;
    L_0x004e:
        r2.close();	 Catch:{ IOException -> 0x005b }
    L_0x0051:
        monitor-exit(r4);	 Catch:{ all -> 0x0062 }
        r0 = r1;
    L_0x0053:
        return r0;
    L_0x0054:
        r0 = (org.json.JSONObject) r0;	 Catch:{ Exception -> 0x008c }
        r0 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.toString(r0);	 Catch:{ Exception -> 0x008c }
        goto L_0x0042;
    L_0x005b:
        r0 = move-exception;
        r2 = "CommonParams";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r0);	 Catch:{ all -> 0x0062 }
        goto L_0x0051;
    L_0x0062:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0062 }
        throw r0;
    L_0x0065:
        r3 = r5.exists();	 Catch:{ Exception -> 0x008c }
        if (r3 == 0) goto L_0x006e;
    L_0x006b:
        r5.delete();	 Catch:{ Exception -> 0x008c }
    L_0x006e:
        r5.createNewFile();	 Catch:{ Exception -> 0x008c }
        r3 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x008c }
        r3.<init>(r5);	 Catch:{ Exception -> 0x008c }
        r0 = r0.getBytes();	 Catch:{ Exception -> 0x00d7, all -> 0x00d4 }
        r3.write(r0);	 Catch:{ Exception -> 0x00d7, all -> 0x00d4 }
        r0 = 1;
        if (r3 == 0) goto L_0x0083;
    L_0x0080:
        r3.close();	 Catch:{ IOException -> 0x0085 }
    L_0x0083:
        monitor-exit(r4);	 Catch:{ all -> 0x0062 }
        goto L_0x0053;
    L_0x0085:
        r1 = move-exception;
        r2 = "CommonParams";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);	 Catch:{ all -> 0x0062 }
        goto L_0x0083;
    L_0x008c:
        r0 = move-exception;
    L_0x008d:
        r3 = "CommonParams";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b9 }
        r5.<init>();	 Catch:{ all -> 0x00b9 }
        r6 = "write params exception: ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x00b9 }
        r0 = r5.append(r0);	 Catch:{ all -> 0x00b9 }
        r0 = r0.toString();	 Catch:{ all -> 0x00b9 }
        r5 = r7.getApplicationContext();	 Catch:{ all -> 0x00b9 }
        com.baidu.android.pushservice.p036h.C1426b.m6447b(r3, r0, r5);	 Catch:{ all -> 0x00b9 }
        if (r2 == 0) goto L_0x00af;
    L_0x00ac:
        r2.close();	 Catch:{ IOException -> 0x00b2 }
    L_0x00af:
        monitor-exit(r4);	 Catch:{ all -> 0x0062 }
    L_0x00b0:
        r0 = r1;
        goto L_0x0053;
    L_0x00b2:
        r0 = move-exception;
        r2 = "CommonParams";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r0);	 Catch:{ all -> 0x0062 }
        goto L_0x00af;
    L_0x00b9:
        r0 = move-exception;
    L_0x00ba:
        if (r2 == 0) goto L_0x00bf;
    L_0x00bc:
        r2.close();	 Catch:{ IOException -> 0x00c0 }
    L_0x00bf:
        throw r0;	 Catch:{ all -> 0x0062 }
    L_0x00c0:
        r1 = move-exception;
        r2 = "CommonParams";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r1);	 Catch:{ all -> 0x0062 }
        goto L_0x00bf;
    L_0x00c7:
        r0 = "CommonParams";
        r2 = "write permission not granted";
        r3 = r7.getApplicationContext();
        com.baidu.android.pushservice.p036h.C1426b.m6445a(r0, r2, r3);
        goto L_0x00b0;
    L_0x00d4:
        r0 = move-exception;
        r2 = r3;
        goto L_0x00ba;
    L_0x00d7:
        r0 = move-exception;
        r2 = r3;
        goto L_0x008d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1535c.m6904a(android.content.Context, java.lang.String, java.lang.Object):boolean");
    }

    /* renamed from: b */
    private static Object m6905b(Context context, String str) {
        if (C1578v.m7142r(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            Object opt;
            synchronized (f5373a) {
                opt = C1535c.m6901a(context).opt(str);
            }
            return opt;
        }
        C1426b.m6445a("CommonParams", "write permission not granted", context.getApplicationContext());
        return null;
    }
}
