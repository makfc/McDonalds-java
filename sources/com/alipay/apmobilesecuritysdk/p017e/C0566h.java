package com.alipay.apmobilesecuritysdk.p017e;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.alipay.security.mobile.module.p019a.C0689a;
import com.alipay.security.mobile.module.p019a.p020a.C0687b;
import com.alipay.security.mobile.module.p022c.C0695a;
import com.alipay.security.mobile.module.p022c.C0699e;
import java.util.UUID;

/* renamed from: com.alipay.apmobilesecuritysdk.e.h */
public class C0566h {
    /* renamed from: a */
    private static String f429a = "";

    /* renamed from: a */
    public static long m695a(Context context) {
        long j = 86400000;
        String a = C0695a.m1247a(context, "vkeyid_settings", "update_time_interval");
        if (!C0689a.m1172b(a)) {
            return j;
        }
        try {
            return Long.parseLong(a);
        } catch (Exception e) {
            return j;
        }
    }

    /* renamed from: a */
    public static void m696a(Context context, String str) {
        C0566h.m698a(context, "update_time_interval", str);
    }

    /* renamed from: a */
    public static void m697a(Context context, String str, long j) {
        C0695a.m1248a(context, "vkeyid_settings", "vkey_valid" + str, String.valueOf(j));
    }

    /* renamed from: a */
    private static void m698a(Context context, String str, String str2) {
        C0695a.m1248a(context, "vkeyid_settings", str, str2);
    }

    /* renamed from: a */
    public static void m699a(Context context, boolean z) {
        C0566h.m698a(context, "log_switch", z ? "1" : "0");
    }

    /* renamed from: b */
    public static String m700b(Context context) {
        return C0695a.m1247a(context, "vkeyid_settings", "last_apdid_env");
    }

    /* renamed from: b */
    public static void m701b(Context context, String str) {
        C0566h.m698a(context, "last_machine_boot_time", str);
    }

    /* renamed from: c */
    public static void m702c(Context context, String str) {
        C0566h.m698a(context, "last_apdid_env", str);
    }

    /* renamed from: c */
    public static boolean m703c(Context context) {
        String a = C0695a.m1247a(context, "vkeyid_settings", "log_switch");
        return a != null && "1".equals(a);
    }

    /* renamed from: d */
    public static String m704d(Context context) {
        return C0695a.m1247a(context, "vkeyid_settings", "dynamic_key");
    }

    /* renamed from: d */
    public static void m705d(Context context, String str) {
        C0566h.m698a(context, "agent_switch", str);
    }

    /* renamed from: e */
    public static String m706e(Context context) {
        return C0695a.m1247a(context, "vkeyid_settings", "apse_degrade");
    }

    /* renamed from: e */
    public static void m707e(Context context, String str) {
        C0566h.m698a(context, "dynamic_key", str);
    }

    /* renamed from: f */
    public static String m708f(Context context) {
        String a;
        synchronized (C0566h.class) {
            if (C0689a.m1169a(f429a)) {
                a = C0699e.m1253a(context, "alipay_vkey_random", "random", "");
                f429a = a;
                if (C0689a.m1169a(a)) {
                    f429a = C0687b.m1157a(UUID.randomUUID().toString());
                    a = "alipay_vkey_random";
                    String str = "random";
                    String str2 = f429a;
                    if (str2 != null) {
                        Editor edit = context.getSharedPreferences(a, 0).edit();
                        if (edit != null) {
                            edit.putString(str, str2);
                            edit.commit();
                        }
                    }
                }
            }
            a = f429a;
        }
        return a;
    }

    /* renamed from: f */
    public static void m709f(Context context, String str) {
        C0566h.m698a(context, "webrtc_url", str);
    }

    /* renamed from: g */
    public static void m710g(Context context, String str) {
        C0566h.m698a(context, "apse_degrade", str);
    }

    /* renamed from: h */
    public static long m711h(Context context, String str) {
        try {
            String a = C0695a.m1247a(context, "vkeyid_settings", "vkey_valid" + str);
            return C0689a.m1169a(a) ? 0 : Long.parseLong(a);
        } catch (Throwable th) {
            return 0;
        }
    }
}
