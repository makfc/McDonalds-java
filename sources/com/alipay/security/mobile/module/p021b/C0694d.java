package com.alipay.security.mobile.module.p021b;

import android.os.Build;
import android.os.Build.VERSION;
import java.io.File;

/* renamed from: com.alipay.security.mobile.module.b.d */
public final class C0694d {
    /* renamed from: a */
    private static C0694d f727a = new C0694d();

    private C0694d() {
    }

    /* renamed from: a */
    public static C0694d m1230a() {
        return f727a;
    }

    /* renamed from: a */
    private static String m1231a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{str, str2});
        } catch (Exception e) {
            return str2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003b A:{Catch:{ Exception -> 0x0068 }} */
    /* renamed from: a */
    public static boolean m1232a(android.content.Context r7) {
        /*
        r2 = 0;
        r1 = 1;
        r0 = android.os.Build.HARDWARE;	 Catch:{ Exception -> 0x0068 }
        r3 = "goldfish";
        r0 = r0.contains(r3);	 Catch:{ Exception -> 0x0068 }
        if (r0 != 0) goto L_0x0020;
    L_0x000c:
        r0 = android.os.Build.PRODUCT;	 Catch:{ Exception -> 0x0068 }
        r3 = "sdk";
        r0 = r0.contains(r3);	 Catch:{ Exception -> 0x0068 }
        if (r0 != 0) goto L_0x0020;
    L_0x0016:
        r0 = android.os.Build.FINGERPRINT;	 Catch:{ Exception -> 0x0068 }
        r3 = "generic";
        r0 = r0.contains(r3);	 Catch:{ Exception -> 0x0068 }
        if (r0 == 0) goto L_0x0022;
    L_0x0020:
        r0 = r1;
    L_0x0021:
        return r0;
    L_0x0022:
        r0 = "phone";
        r0 = r7.getSystemService(r0);	 Catch:{ Exception -> 0x0068 }
        r0 = (android.telephony.TelephonyManager) r0;	 Catch:{ Exception -> 0x0068 }
        if (r0 == 0) goto L_0x0059;
    L_0x002c:
        r3 = r0.getDeviceId();	 Catch:{ Exception -> 0x0068 }
        if (r3 == 0) goto L_0x0038;
    L_0x0032:
        r4 = r3.length();	 Catch:{ Exception -> 0x0068 }
        if (r4 != 0) goto L_0x003d;
    L_0x0038:
        r0 = r1;
    L_0x0039:
        if (r0 == 0) goto L_0x0059;
    L_0x003b:
        r0 = r1;
        goto L_0x0021;
    L_0x003d:
        r0 = r2;
    L_0x003e:
        if (r0 >= r4) goto L_0x0057;
    L_0x0040:
        r5 = r3.charAt(r0);	 Catch:{ Exception -> 0x0068 }
        r5 = java.lang.Character.isWhitespace(r5);	 Catch:{ Exception -> 0x0068 }
        if (r5 != 0) goto L_0x0054;
    L_0x004a:
        r5 = r3.charAt(r0);	 Catch:{ Exception -> 0x0068 }
        r6 = 48;
        if (r5 == r6) goto L_0x0054;
    L_0x0052:
        r0 = r2;
        goto L_0x0039;
    L_0x0054:
        r0 = r0 + 1;
        goto L_0x003e;
    L_0x0057:
        r0 = r1;
        goto L_0x0039;
    L_0x0059:
        r0 = r7.getContentResolver();	 Catch:{ Exception -> 0x0068 }
        r1 = "android_id";
        r0 = android.provider.Settings.Secure.getString(r0, r1);	 Catch:{ Exception -> 0x0068 }
        r0 = com.alipay.security.mobile.module.p019a.C0689a.m1169a(r0);	 Catch:{ Exception -> 0x0068 }
        goto L_0x0021;
    L_0x0068:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p021b.C0694d.m1232a(android.content.Context):boolean");
    }

    /* renamed from: b */
    public static String m1233b() {
        return "android";
    }

    /* renamed from: c */
    public static boolean m1234c() {
        String[] strArr = new String[]{"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < 5) {
            try {
                if (new File(strArr[i] + "su").exists()) {
                    return true;
                }
                i++;
            } catch (Exception e) {
            }
        }
        return false;
    }

    /* renamed from: d */
    public static String m1235d() {
        return Build.BOARD;
    }

    /* renamed from: e */
    public static String m1236e() {
        return Build.BRAND;
    }

    /* renamed from: f */
    public static String m1237f() {
        return Build.DEVICE;
    }

    /* renamed from: g */
    public static String m1238g() {
        return Build.DISPLAY;
    }

    /* renamed from: h */
    public static String m1239h() {
        return VERSION.INCREMENTAL;
    }

    /* renamed from: i */
    public static String m1240i() {
        return Build.MANUFACTURER;
    }

    /* renamed from: j */
    public static String m1241j() {
        return Build.MODEL;
    }

    /* renamed from: k */
    public static String m1242k() {
        return Build.PRODUCT;
    }

    /* renamed from: l */
    public static String m1243l() {
        return VERSION.RELEASE;
    }

    /* renamed from: m */
    public static String m1244m() {
        return VERSION.SDK;
    }

    /* renamed from: n */
    public static String m1245n() {
        return Build.TAGS;
    }

    /* renamed from: o */
    public static String m1246o() {
        return C0694d.m1231a("ro.kernel.qemu", "0");
    }
}
