package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.security.MessageDigest;
import java.util.Locale;

/* renamed from: com.amap.api.mapcore.util.dl */
public class AppInfo {
    /* renamed from: a */
    private static String f1753a = "";
    /* renamed from: b */
    private static String f1754b = "";
    /* renamed from: c */
    private static String f1755c = "";
    /* renamed from: d */
    private static String f1756d = "";
    /* renamed from: e */
    private static String f1757e = null;

    /* renamed from: a */
    public static String m2381a(Context context) {
        try {
            return AppInfo.m2388g(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return f1756d;
        }
    }

    /* renamed from: b */
    public static String m2383b(Context context) {
        try {
            if (!"".equals(f1753a)) {
                return f1753a;
            }
            PackageManager packageManager = context.getPackageManager();
            f1753a = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
            return f1753a;
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "AppInfo", "getApplicationName");
        }
    }

    /* renamed from: c */
    public static String m2384c(Context context) {
        try {
            if (f1754b != null && !"".equals(f1754b)) {
                return f1754b;
            }
            f1754b = context.getApplicationContext().getPackageName();
            return f1754b;
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "AppInfo", "getPackageName");
        }
    }

    /* renamed from: d */
    public static String m2385d(Context context) {
        try {
            if (!"".equals(f1755c)) {
                return f1755c;
            }
            f1755c = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            return f1755c;
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "AppInfo", "getApplicationVersion");
        }
    }

    /* renamed from: e */
    public static String m2386e(Context context) {
        try {
            if (f1757e != null && !"".equals(f1757e)) {
                return f1757e;
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
            f1757e = stringBuffer.toString();
            return f1757e;
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "AppInfo", "getSHA1AndPackage");
            return f1757e;
        }
    }

    /* renamed from: a */
    static void m2382a(String str) {
        f1756d = str;
    }

    /* renamed from: f */
    public static String m2387f(Context context) {
        try {
            return AppInfo.m2388g(context);
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "AppInfo", "getKey");
            return f1756d;
        }
    }

    /* renamed from: g */
    private static String m2388g(Context context) throws NameNotFoundException {
        if (f1756d == null || f1756d.equals("")) {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null) {
                return f1756d;
            }
            f1756d = applicationInfo.metaData.getString("com.amap.api.v2.apikey");
        }
        return f1756d;
    }
}
