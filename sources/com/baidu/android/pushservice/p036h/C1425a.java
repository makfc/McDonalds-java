package com.baidu.android.pushservice.p036h;

import android.text.TextUtils;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.baidu.android.pushservice.h.a */
public final class C1425a {
    /* renamed from: a */
    private static boolean f4995a = false;
    /* renamed from: b */
    private static boolean f4996b = false;
    /* renamed from: c */
    private static Logger f4997c;

    /* renamed from: a */
    public static String m6437a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    /* renamed from: a */
    public static void m6438a(String str, String str2) {
        if (f4995a) {
            String str3 = "BDPushSDK-" + str;
            if (!f4996b || f4997c == null) {
                Log.v(str3, str2);
            } else {
                f4997c.log(Level.INFO, str3 + ": " + str2);
            }
        }
    }

    /* renamed from: a */
    public static void m6439a(String str, String str2, Throwable th) {
        C1425a.m6444e(str, str2 + 10 + C1425a.m6437a(th));
    }

    /* renamed from: a */
    public static void m6440a(String str, Throwable th) {
        C1425a.m6444e(str, C1425a.m6437a(th));
    }

    /* renamed from: b */
    public static void m6441b(String str, String str2) {
        if (f4995a) {
            String str3 = "BDPushSDK-" + str;
            if (!f4996b || f4997c == null) {
                Log.i(str3, str2);
            } else {
                f4997c.log(Level.INFO, str3 + ": " + str2);
            }
        }
    }

    /* renamed from: c */
    public static void m6442c(String str, String str2) {
        if (f4995a) {
            String str3 = "BDPushSDK-" + str;
            if (!f4996b || f4997c == null) {
                Log.d(str3, str2);
            } else {
                f4997c.log(Level.INFO, str3 + ": " + str2);
            }
        }
    }

    /* renamed from: d */
    public static void m6443d(String str, String str2) {
        if (f4995a) {
            String str3 = "BDPushSDK-" + str;
            if (!f4996b || f4997c == null) {
                Log.w(str3, str2);
            } else {
                f4997c.log(Level.WARNING, str3 + ": " + str2);
            }
        }
    }

    /* renamed from: e */
    public static void m6444e(String str, String str2) {
        if (f4995a) {
            String str3 = "BDPushSDK-" + str;
            if (f4996b && f4997c != null) {
                f4997c.log(Level.SEVERE, str3 + ": " + str2);
            } else if (!TextUtils.isEmpty(str2)) {
                Log.e(str3, str2);
            }
        }
    }
}
