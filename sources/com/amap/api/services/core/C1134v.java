package com.amap.api.services.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/* compiled from: AppInfo */
/* renamed from: com.amap.api.services.core.v */
public class C1134v {
    /* renamed from: a */
    private static String f3806a = "";
    /* renamed from: b */
    private static String f3807b = null;
    /* renamed from: c */
    private static String f3808c = "";
    /* renamed from: d */
    private static String f3809d;
    /* renamed from: e */
    private static String f3810e = null;

    /* renamed from: a */
    public static String m5081a(Context context) {
        try {
            if (!"".equals(f3806a)) {
                return f3806a;
            }
            PackageManager packageManager = context.getPackageManager();
            f3806a = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
            return f3806a;
        } catch (NameNotFoundException e) {
            C1099ax.m4800a(e, "AppInfo", "getApplicationName");
            e.printStackTrace();
        } catch (Throwable e2) {
            C1099ax.m4800a(e2, "AppInfo", "getApplicationName");
            e2.printStackTrace();
        }
    }

    /* renamed from: b */
    public static String m5083b(Context context) {
        try {
            if (f3807b != null && !"".equals(f3807b)) {
                return f3807b;
            }
            f3807b = context.getApplicationContext().getPackageName();
            return f3807b;
        } catch (Throwable th) {
            C1099ax.m4800a(th, "AppInfo", "getPackageName");
            th.printStackTrace();
        }
    }

    /* renamed from: c */
    public static String m5084c(Context context) {
        try {
            if (!"".equals(f3808c)) {
                return f3808c;
            }
            f3808c = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            return f3808c;
        } catch (NameNotFoundException e) {
            C1099ax.m4800a(e, "AppInfo", "getApplicationVersion");
            e.printStackTrace();
        } catch (Throwable e2) {
            C1099ax.m4800a(e2, "AppInfo", "getApplicationVersion");
            e2.printStackTrace();
        }
    }

    /* renamed from: d */
    public static String m5085d(Context context) {
        try {
            if (f3810e != null && !"".equals(f3810e)) {
                return f3810e;
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
            f3810e = stringBuffer.toString();
            return f3810e;
        } catch (NameNotFoundException e) {
            C1099ax.m4800a(e, "AppInfo", "getSHA1AndPackage");
            e.printStackTrace();
            return f3810e;
        } catch (NoSuchAlgorithmException e2) {
            C1099ax.m4800a(e2, "AppInfo", "getSHA1AndPackage");
            e2.printStackTrace();
            return f3810e;
        } catch (Throwable e22) {
            C1099ax.m4800a(e22, "AppInfo", "getSHA1AndPackage");
            e22.printStackTrace();
            return f3810e;
        }
    }

    /* renamed from: a */
    static void m5082a(String str) {
        f3809d = str;
    }

    /* renamed from: g */
    private static String m5088g(Context context) throws NameNotFoundException {
        if (f3809d == null || f3809d.equals("")) {
            f3809d = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("com.amap.api.v2.apikey");
        }
        return f3809d;
    }

    /* renamed from: e */
    public static String m5086e(Context context) {
        try {
            return C1134v.m5088g(context);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return f3809d;
    }

    /* renamed from: f */
    public static String m5087f(Context context) {
        try {
            return C1134v.m5088g(context);
        } catch (NameNotFoundException e) {
            C1099ax.m4800a(e, "AppInfo", "getKey");
            e.printStackTrace();
        } catch (Throwable e2) {
            C1099ax.m4800a(e2, "AppInfo", "getKey");
            e2.printStackTrace();
        }
        return f3809d;
    }
}
