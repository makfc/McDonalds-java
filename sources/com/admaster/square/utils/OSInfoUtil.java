package com.admaster.square.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.admaster.square.api.Logger;
import com.facebook.stetho.common.Utf8Charset;
import java.io.File;
import java.net.URLEncoder;

/* renamed from: com.admaster.square.utils.p */
public class OSInfoUtil {
    /* renamed from: a */
    public static String f303a = "";
    /* renamed from: b */
    private static String f304b = "";
    /* renamed from: c */
    private static String f305c = "";
    /* renamed from: d */
    private static String f306d = "";
    /* renamed from: e */
    private static String f307e = "";
    /* renamed from: f */
    private static int f308f = 0;
    /* renamed from: g */
    private static String f309g = "";

    /* renamed from: a */
    public static String m458a() {
        try {
            if (TextUtils.isEmpty(f304b)) {
                f304b = VERSION.RELEASE;
            }
            return f304b;
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: b */
    public static String m460b() {
        try {
            if (TextUtils.isEmpty(f305c)) {
                f305c = URLEncoder.encode(Build.MODEL, Utf8Charset.NAME);
            }
        } catch (Exception e) {
            f305c = "";
            Logger.m362a("getFactoryModelName uee ->: " + e.getMessage());
        }
        return f305c;
    }

    /* renamed from: c */
    public static String m462c() {
        try {
            if (TextUtils.isEmpty(f306d)) {
                f306d = URLEncoder.encode(Build.BRAND, Utf8Charset.NAME);
            }
        } catch (Exception e) {
            f306d = "";
            Logger.m362a("getFactoryBrandName uee ->: " + e.getMessage());
        }
        return f306d;
    }

    /* renamed from: a */
    public static String m459a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (!(packageInfo == null || TextUtils.isEmpty(packageInfo.versionName))) {
                f307e = packageInfo.versionName;
            }
        } catch (Exception e) {
            Logger.m362a("getAppVersionNameFromPackage Exception ->: " + e.getMessage());
            f307e = "";
        }
        return f307e;
    }

    /* renamed from: b */
    public static String m461b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (!(packageInfo == null || TextUtils.isEmpty(packageInfo.versionName))) {
                f303a = packageInfo.packageName;
            }
        } catch (Exception e) {
            Logger.m362a("getAppVersionNameFromPackage Exception ->: " + e.getMessage());
            f303a = "";
        }
        return f303a;
    }

    /* renamed from: c */
    public static String m463c(Context context) {
        String str = null;
        if (context == null) {
            return str;
        }
        try {
            return Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    /* renamed from: d */
    public static boolean m464d() {
        String[] strArr = new String[]{"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < strArr.length) {
            try {
                File file = new File(strArr[i] + "su");
                if (file != null && file.exists()) {
                    return true;
                }
                i++;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /* renamed from: e */
    public static boolean m465e() {
        if (OSInfoUtil.m466f() || OSInfoUtil.m467g() || OSInfoUtil.m464d()) {
            return true;
        }
        return false;
    }

    /* renamed from: f */
    public static boolean m466f() {
        try {
            String str = Build.TAGS;
            if (str != null && str.contains("test-keys")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* renamed from: g */
    public static boolean m467g() {
        try {
            if (new File("/system/app/Superuser.apk").exists()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
