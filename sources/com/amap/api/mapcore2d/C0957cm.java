package com.amap.api.mapcore2d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.security.MessageDigest;
import java.util.Locale;

/* compiled from: AppInfo */
/* renamed from: com.amap.api.mapcore2d.cm */
public class C0957cm {
    /* renamed from: a */
    private static String f2701a = "";
    /* renamed from: b */
    private static String f2702b = "";
    /* renamed from: c */
    private static String f2703c = "";
    /* renamed from: d */
    private static String f2704d = "";
    /* renamed from: e */
    private static String f2705e = null;

    /* renamed from: a */
    public static String m3900a(Context context) {
        try {
            return C0957cm.m3907g(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return f2704d;
        }
    }

    /* renamed from: b */
    public static String m3902b(Context context) {
        try {
            if (!"".equals(f2701a)) {
                return f2701a;
            }
            PackageManager packageManager = context.getPackageManager();
            f2701a = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
            return f2701a;
        } catch (Throwable th) {
            C0982da.m4076a(th, "AppInfo", "getApplicationName");
        }
    }

    /* renamed from: c */
    public static String m3903c(Context context) {
        try {
            if (f2702b != null && !"".equals(f2702b)) {
                return f2702b;
            }
            f2702b = context.getApplicationContext().getPackageName();
            return f2702b;
        } catch (Throwable th) {
            C0982da.m4076a(th, "AppInfo", "getPackageName");
        }
    }

    /* renamed from: d */
    public static String m3904d(Context context) {
        try {
            if (!"".equals(f2703c)) {
                return f2703c;
            }
            f2703c = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            return f2703c;
        } catch (Throwable th) {
            C0982da.m4076a(th, "AppInfo", "getApplicationVersion");
        }
    }

    /* renamed from: e */
    public static String m3905e(Context context) {
        try {
            if (f2705e != null && !"".equals(f2705e)) {
                return f2705e;
            }
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            byte[] digest = MessageDigest.getInstance("SHA1").digest(packageInfo.signatures[0].toByteArray());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String toUpperCase = Integer.toHexString(b & 255).toUpperCase(Locale.US);
                if (toUpperCase.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(toUpperCase);
                stringBuffer.append(":");
            }
            stringBuffer.append(packageInfo.packageName);
            f2705e = stringBuffer.toString();
            return f2705e;
        } catch (Throwable th) {
            C0982da.m4076a(th, "AppInfo", "getSHA1AndPackage");
            return f2705e;
        }
    }

    /* renamed from: a */
    static void m3901a(String str) {
        f2704d = str;
    }

    /* renamed from: f */
    public static String m3906f(Context context) {
        try {
            return C0957cm.m3907g(context);
        } catch (Throwable th) {
            C0982da.m4076a(th, "AppInfo", "getKey");
            return f2704d;
        }
    }

    /* renamed from: g */
    private static String m3907g(Context context) throws NameNotFoundException {
        if (f2704d == null || f2704d.equals("")) {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null) {
                return f2704d;
            }
            f2704d = applicationInfo.metaData.getString("com.amap.api.v2.apikey");
        }
        return f2704d;
    }
}
