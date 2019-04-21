package com.baidu.android.pushservice.util;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import com.baidu.android.pushservice.C1328a;
import com.baidu.android.pushservice.C1463j;
import com.baidu.android.pushservice.config.ModeConfig;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p036h.C1426b;
import java.util.List;

/* renamed from: com.baidu.android.pushservice.util.u */
public class C1577u {
    /* renamed from: a */
    public static void m7044a(Context context) {
        C1426b.m6445a("ServiceUtils", "--- Start Service from " + context.getPackageName(), context.getApplicationContext());
        if (C1328a.m6008b(context)) {
            C1577u.m7048b(context);
            C1328a.m6004a(context, false);
            return;
        }
        C1577u.m7051d(context);
    }

    /* renamed from: a */
    public static void m7045a(Context context, Intent intent) {
        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            intent = C1577u.m7050c(context);
        }
        if (ModeConfig.isProxyMode(context)) {
            C1463j.m6642a(context).mo13940a(intent);
            return;
        }
        String v = C1578v.m7149v(context);
        C1425a.m6442c("ServiceUtils", "package: " + v);
        C1577u.m7046a(context, intent, v);
    }

    /* renamed from: a */
    private static void m7046a(Context context, Intent intent, String str) {
        if (!TextUtils.isEmpty(str)) {
            intent.setPackage(str);
        }
        C1426b.m6445a("ServiceUtils", "startPushService go on pkgName = " + str, context.getApplicationContext());
        try {
            if (!TextUtils.isEmpty(str)) {
                intent.setClassName(str, "com.baidu.android.pushservice.PushService");
                context.startService(intent);
                C1426b.m6445a("ServiceUtils", "startPushService by startService", context.getApplicationContext());
                return;
            }
        } catch (Exception e) {
            C1426b.m6447b("ServiceUtils", "START SERVICE E: " + e, context.getApplicationContext());
        }
        try {
            String d = C1578v.m7110d(context, str, intent.getAction());
            if (!TextUtils.isEmpty(d)) {
                intent.setClassName(str, d);
                context.sendBroadcast(intent);
                C1426b.m6445a("ServiceUtils", "startPushService by sendBroadcast", context.getApplicationContext());
                return;
            }
        } catch (Exception e2) {
            C1426b.m6447b("ServiceUtils", "START SERVICE E-2: " + e2, context.getApplicationContext());
        }
        context.sendBroadcast(intent);
        C1426b.m6445a("ServiceUtils", "startPushService by sendBroadcast all", context.getApplicationContext());
    }

    /* renamed from: a */
    public static void m7047a(Context context, String str) {
        Intent c = C1577u.m7050c(context);
        c.putExtra("method", "pushservice_restart_v2");
        if (!TextUtils.isEmpty(str) && str.equals(context.getPackageName())) {
            c.putExtra("priority2", Long.MAX_VALUE);
        }
        C1577u.m7046a(context, c, str);
    }

    /* renamed from: b */
    public static void m7048b(Context context) {
        if (context != null) {
            String u = C1578v.m7147u(context);
            long g = C1578v.m7119g(context, u);
            if (!TextUtils.isEmpty(u) && !u.equals(context.getPackageName()) && ((ModeConfig.getInstance(context).getCurrentMode() != ModeConfig.MODE_C_C && g < C1578v.m7128k(context)) || ModeConfig.getInstance(context).getCurrentMode() == ModeConfig.MODE_C_H)) {
                C1577u.m7047a(context, u);
            } else if (TextUtils.isEmpty(u) || u.equals(context.getPackageName())) {
                List<String> r = C1578v.m7141r(context);
                if (!r.isEmpty()) {
                    for (String u2 : r) {
                        if (!context.getPackageName().equals(u2)) {
                            C1577u.m7047a(context, u2);
                        }
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public static void m7049b(Context context, Intent intent) {
        C1577u.m7046a(context, intent, context.getPackageName());
    }

    /* renamed from: c */
    public static Intent m7050c(Context context) {
        Intent intent = new Intent("com.baidu.android.pushservice.action.METHOD");
        intent.addFlags(32);
        intent.putExtra("pkg_name", context.getPackageName());
        intent.putExtra("method_version", "V2");
        intent.putExtra("priority2", C1578v.m7128k(context));
        return intent;
    }

    /* renamed from: d */
    public static void m7051d(Context context) {
        ModeConfig.getInstance(context).caculateCurrentConfig(Build.MANUFACTURER);
        if (ModeConfig.isProxyMode(context)) {
            C1425a.m6442c("ServiceUtils", "proxy mode, quit checkAndStartPushService");
            return;
        }
        String u = C1578v.m7147u(context);
        final String v = C1578v.m7149v(context);
        C1425a.m6442c("ServiceUtils", "curPkg in checkAndStartPushService: " + u + " highest: " + v);
        if (TextUtils.isEmpty(u) || !u.equals(v)) {
            C1425a.m6442c("ServiceUtils", "curPkg in checkAndStartPushService: " + u);
            C1577u.m7047a(context, u);
        }
        final Context applicationContext = context.getApplicationContext();
        C1462d.m6637a().mo13938a(new C1281c("checkAndStartPushService", (short) 98) {
            /* JADX WARNING: Removed duplicated region for block: B:56:? A:{SYNTHETIC, RETURN} */
            /* JADX WARNING: Removed duplicated region for block: B:23:0x0088  */
            /* JADX WARNING: Removed duplicated region for block: B:23:0x0088  */
            /* JADX WARNING: Removed duplicated region for block: B:56:? A:{SYNTHETIC, RETURN} */
            /* JADX WARNING: Removed duplicated region for block: B:56:? A:{SYNTHETIC, RETURN} */
            /* JADX WARNING: Removed duplicated region for block: B:23:0x0088  */
            /* renamed from: a */
            public void mo13487a() {
                /*
                r9 = this;
                r1 = 1;
                r3 = 0;
                r0 = r0;	 Catch:{ Exception -> 0x00c1 }
                r2 = "activity";
                r0 = r0.getSystemService(r2);	 Catch:{ Exception -> 0x00c1 }
                r0 = (android.app.ActivityManager) r0;	 Catch:{ Exception -> 0x00c1 }
                r2 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
                r0 = r0.getRunningServices(r2);	 Catch:{ Exception -> 0x00c1 }
                r2 = r1;	 Catch:{ Exception -> 0x00c1 }
                r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x00c1 }
                if (r2 != 0) goto L_0x00d3;
            L_0x001a:
                if (r0 == 0) goto L_0x00d3;
            L_0x001c:
                r2 = r0.isEmpty();	 Catch:{ Exception -> 0x00c1 }
                if (r2 != 0) goto L_0x00d3;
            L_0x0022:
                r2 = r0.iterator();	 Catch:{ Exception -> 0x00c1 }
            L_0x0026:
                r0 = r2.hasNext();	 Catch:{ Exception -> 0x00c1 }
                if (r0 == 0) goto L_0x00d3;
            L_0x002c:
                r0 = r2.next();	 Catch:{ Exception -> 0x00c1 }
                r0 = (android.app.ActivityManager.RunningServiceInfo) r0;	 Catch:{ Exception -> 0x00c1 }
                r4 = r0.service;	 Catch:{ Exception -> 0x00c1 }
                r4 = r4.getPackageName();	 Catch:{ Exception -> 0x00c1 }
                r0 = r0.service;	 Catch:{ Exception -> 0x00c1 }
                r0 = r0.getClassName();	 Catch:{ Exception -> 0x00c1 }
                r5 = "com.baidu.android.pushservice.PushService";
                r0 = r0.equals(r5);	 Catch:{ Exception -> 0x00c1 }
                if (r0 == 0) goto L_0x0026;
            L_0x0046:
                r0 = r1;	 Catch:{ Exception -> 0x00c1 }
                r0 = r0.equals(r4);	 Catch:{ Exception -> 0x00c1 }
                if (r0 == 0) goto L_0x0026;
            L_0x004e:
                r0 = "netstat -ant";
                r2 = 0;
                r0 = com.baidu.android.pushservice.p039k.C1466c.m6681a(r0, r2);	 Catch:{ Exception -> 0x00c1 }
                r5 = r0.iterator();	 Catch:{ Exception -> 0x00c1 }
                r2 = r3;
            L_0x005a:
                r0 = r5.hasNext();	 Catch:{ Exception -> 0x00cf }
                if (r0 == 0) goto L_0x0068;
            L_0x0060:
                r0 = r5.next();	 Catch:{ Exception -> 0x00cf }
                r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x00cf }
                if (r2 == 0) goto L_0x0093;
            L_0x0068:
                r0 = "ServiceUtils";
                r1 = new java.lang.StringBuilder;
                r1.<init>();
                r3 = "checkAndStartPushService, running is ";
                r1 = r1.append(r3);
                r1 = r1.append(r2);
                r1 = r1.toString();
                r3 = r0;
                r3 = r3.getApplicationContext();
                com.baidu.android.pushservice.p036h.C1426b.m6445a(r0, r1, r3);
                if (r2 != 0) goto L_0x0092;
            L_0x0088:
                r0 = r0;
                r1 = new android.content.Intent;
                r1.<init>();
                com.baidu.android.pushservice.util.C1577u.m7045a(r0, r1);
            L_0x0092:
                return;
            L_0x0093:
                r4 = r0.toUpperCase();	 Catch:{ Exception -> 0x00cf }
                r6 = "ESTABLISHED";
                r4 = r4.contains(r6);	 Catch:{ Exception -> 0x00cf }
                if (r4 == 0) goto L_0x00d1;
            L_0x009f:
                r4 = com.baidu.android.pushservice.C1457i.f5136a;	 Catch:{ Exception -> 0x00cf }
                r4 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x00cf }
                r4 = r0.contains(r4);	 Catch:{ Exception -> 0x00cf }
                if (r4 == 0) goto L_0x00ad;
            L_0x00ab:
                r2 = r1;
                goto L_0x0068;
            L_0x00ad:
                r6 = com.baidu.android.pushservice.C1457i.f5137b;	 Catch:{ Exception -> 0x00cf }
                r7 = r6.length;	 Catch:{ Exception -> 0x00cf }
                r4 = r3;
            L_0x00b1:
                if (r4 >= r7) goto L_0x00d1;
            L_0x00b3:
                r8 = r6[r4];	 Catch:{ Exception -> 0x00cf }
                r8 = r0.contains(r8);	 Catch:{ Exception -> 0x00cf }
                if (r8 == 0) goto L_0x00be;
            L_0x00bb:
                r0 = r1;
            L_0x00bc:
                r2 = r0;
                goto L_0x005a;
            L_0x00be:
                r4 = r4 + 1;
                goto L_0x00b1;
            L_0x00c1:
                r0 = move-exception;
                r2 = r3;
            L_0x00c3:
                r1 = "ServiceUtils";
                r0 = r0.getMessage();
                r3 = r0;
                com.baidu.android.pushservice.p036h.C1426b.m6447b(r1, r0, r3);
                goto L_0x0068;
            L_0x00cf:
                r0 = move-exception;
                goto L_0x00c3;
            L_0x00d1:
                r0 = r2;
                goto L_0x00bc;
            L_0x00d3:
                r2 = r3;
                goto L_0x0068;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.util.C1577u$C15761.mo13487a():void");
            }
        });
    }
}
