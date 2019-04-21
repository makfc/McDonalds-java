package com.admaster.square.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import java.util.regex.Pattern;

/* renamed from: com.admaster.square.utils.o */
public class NetWorkInfoUtil {
    /* renamed from: a */
    private static String f299a = "";
    /* renamed from: b */
    private static String f300b = "";
    /* renamed from: c */
    private static String f301c = "";
    /* renamed from: d */
    private static String f302d = "";

    /* renamed from: f */
    private static NetworkInfo m457f(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                return connectivityManager.getActiveNetworkInfo();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m452a(Context context) {
        if (context == null) {
            return false;
        }
        NetworkInfo f = NetWorkInfoUtil.m457f(context);
        if (f == null || !f.isAvailable()) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    public static String m453b(Context context) {
        String str = "";
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return "";
            }
            if (activeNetworkInfo.getType() == 1) {
                return "wifi";
            }
            if (activeNetworkInfo.getType() == 0) {
                int subtype = activeNetworkInfo.getSubtype();
                if (subtype == 4 || subtype == 1 || subtype == 2) {
                    return "2g";
                }
                if (subtype == 3 || subtype == 8 || subtype == 6 || subtype == 5 || subtype == 12 || subtype == 10 || subtype == 9 || subtype == 14 || subtype == 15) {
                    return "3g";
                }
                if (subtype == 13) {
                    return "4g";
                }
            }
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: c */
    public static String m454c(Context context) {
        if (!TextUtils.isEmpty(f299a) || context == null) {
            return f299a;
        }
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo == null || TextUtils.isEmpty(connectionInfo.getMacAddress())) {
                return "";
            }
            f299a = connectionInfo.getMacAddress();
            return f299a;
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: d */
    public static String m455d(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo == null || TextUtils.isEmpty(connectionInfo.getBSSID())) {
                f300b = "";
                return f300b;
            }
            f300b = connectionInfo.getBSSID();
            return f300b;
        } catch (Exception e) {
            f300b = "";
        }
    }

    /* renamed from: e */
    public static String m456e(Context context) {
        String str;
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo == null || TextUtils.isEmpty(connectionInfo.getSSID())) {
                str = "";
                f301c = str;
                return str;
            }
            f301c = connectionInfo.getSSID();
            f301c = Pattern.compile("[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]").matcher(f301c).replaceAll("").trim();
            f301c = f301c.replaceAll("\"", "").replaceAll("\\s+", "").trim();
            return f301c;
        } catch (Exception e) {
            str = "";
            f301c = str;
            return str;
        }
    }
}
