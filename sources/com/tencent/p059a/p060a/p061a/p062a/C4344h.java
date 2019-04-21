package com.tencent.p059a.p060a.p061a.p062a;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import com.facebook.stetho.common.Utf8Charset;
import com.tencent.wxop.stat.common.C4428f;
import org.json.JSONObject;

/* renamed from: com.tencent.a.a.a.a.h */
public final class C4344h {
    /* renamed from: a */
    static String m7869a(Context context) {
        try {
            if (C4344h.m7872a(context, "android.permission.READ_PHONE_STATE")) {
                String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                if (deviceId != null) {
                    return deviceId;
                }
            }
            Log.i("MID", "Could not get permission of android.permission.READ_PHONE_STATE");
        } catch (Throwable th) {
            Log.w("MID", th);
        }
        return "";
    }

    /* renamed from: a */
    private static void m7870a(String str, Throwable th) {
        Log.e("MID", str, th);
    }

    /* renamed from: a */
    static void m7871a(JSONObject jSONObject, String str, String str2) {
        if (C4344h.m7874b(str2)) {
            jSONObject.put(str, str2);
        }
    }

    /* renamed from: a */
    static boolean m7872a(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Throwable th) {
            C4344h.m7870a("checkPermission error", th);
            return false;
        }
    }

    /* renamed from: b */
    static String m7873b(Context context) {
        if (C4344h.m7872a(context, "android.permission.ACCESS_WIFI_STATE")) {
            try {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                return wifiManager == null ? "" : wifiManager.getConnectionInfo().getMacAddress();
            } catch (Exception e) {
                Log.i("MID", "get wifi address error" + e);
                return "";
            }
        }
        Log.i("MID", "Could not get permission of android.permission.ACCESS_WIFI_STATE");
        return "";
    }

    /* renamed from: b */
    static boolean m7874b(String str) {
        return (str == null || str.trim().length() == 0) ? false : true;
    }

    /* renamed from: c */
    public static boolean m7875c(String str) {
        return str != null && str.trim().length() >= 40;
    }

    /* renamed from: f */
    static String m7876f(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(C4428f.m8087b(Base64.decode(str.getBytes(Utf8Charset.NAME), 0)), Utf8Charset.NAME).trim().replace("\t", "").replace("\n", "").replace("\r", "");
        } catch (Throwable th) {
            C4344h.m7870a("decode error", th);
            return str;
        }
    }

    /* renamed from: g */
    static String m7877g(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(Base64.encode(C4428f.m8085a(str.getBytes(Utf8Charset.NAME)), 0), Utf8Charset.NAME).trim().replace("\t", "").replace("\n", "").replace("\r", "");
        } catch (Throwable th) {
            C4344h.m7870a("decode error", th);
            return str;
        }
    }
}
