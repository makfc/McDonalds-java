package com.tencent.wxop.stat.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.facebook.stetho.common.Utf8Charset;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.common.q */
public class C4439q {
    /* renamed from: a */
    private static String f7165a = "";

    /* renamed from: a */
    public static String m8156a(Context context) {
        try {
            if (C4439q.m8160a(context, "android.permission.READ_PHONE_STATE")) {
                String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                if (deviceId != null) {
                    return deviceId;
                }
            }
            Log.e("MtaSDK", "Could not get permission of android.permission.READ_PHONE_STATE");
        } catch (Throwable th) {
            Log.e("MtaSDK", "get device id error", th);
        }
        return null;
    }

    /* renamed from: a */
    public static String m8157a(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(C4428f.m8087b(C4429g.m8089a(str.getBytes(Utf8Charset.NAME), 0)), Utf8Charset.NAME);
        } catch (Throwable th) {
            Log.e("MtaSDK", "decode error", th);
            return str;
        }
    }

    /* renamed from: a */
    public static JSONArray m8158a(Context context, int i) {
        try {
            if (C4439q.m8160a(context, "android.permission.INTERNET") && C4439q.m8160a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (wifiManager != null) {
                    List scanResults = wifiManager.getScanResults();
                    if (scanResults != null && scanResults.size() > 0) {
                        Collections.sort(scanResults, new C4440r());
                        JSONArray jSONArray = new JSONArray();
                        int i2 = 0;
                        while (i2 < scanResults.size() && i2 < i) {
                            ScanResult scanResult = (ScanResult) scanResults.get(i2);
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("bs", scanResult.BSSID);
                            jSONObject.put("ss", scanResult.SSID);
                            jSONArray.put(jSONObject);
                            i2++;
                        }
                        return jSONArray;
                    }
                }
                return null;
            }
            Log.e("MtaSDK", "can not get the permisson of android.permission.INTERNET");
            return null;
        } catch (Throwable th) {
            Log.e("MtaSDK", "isWifiNet error", th);
        }
    }

    /* renamed from: a */
    public static void m8159a(JSONObject jSONObject, String str, String str2) {
        if (str2 != null) {
            try {
                if (str2.length() > 0) {
                    jSONObject.put(str, str2);
                }
            } catch (Throwable th) {
                Log.e("MtaSDK", "jsonPut error", th);
            }
        }
    }

    /* renamed from: a */
    public static boolean m8160a(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Throwable th) {
            Log.e("MtaSDK", "checkPermission error", th);
            return false;
        }
    }

    /* renamed from: b */
    public static String m8161b(Context context) {
        if (C4439q.m8160a(context, "android.permission.ACCESS_WIFI_STATE")) {
            try {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                return wifiManager == null ? "" : wifiManager.getConnectionInfo().getMacAddress();
            } catch (Exception e) {
                Log.e("MtaSDK", "get wifi address error", e);
                return "";
            }
        }
        Log.e("MtaSDK", "Could not get permission of android.permission.ACCESS_WIFI_STATE");
        return "";
    }

    /* renamed from: b */
    public static String m8162b(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(C4429g.m8091b(C4428f.m8085a(str.getBytes(Utf8Charset.NAME)), 0), Utf8Charset.NAME);
        } catch (Throwable th) {
            Log.e("MtaSDK", "encode error", th);
            return str;
        }
    }

    /* renamed from: c */
    public static WifiInfo m8163c(Context context) {
        if (C4439q.m8160a(context, "android.permission.ACCESS_WIFI_STATE")) {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            if (wifiManager != null) {
                return wifiManager.getConnectionInfo();
            }
        }
        return null;
    }

    /* renamed from: d */
    public static String m8164d(Context context) {
        try {
            WifiInfo c = C4439q.m8163c(context);
            if (c != null) {
                return c.getBSSID();
            }
        } catch (Throwable th) {
            Log.e("MtaSDK", "encode error", th);
        }
        return null;
    }

    /* renamed from: e */
    public static String m8165e(Context context) {
        try {
            WifiInfo c = C4439q.m8163c(context);
            if (c != null) {
                return c.getSSID();
            }
        } catch (Throwable th) {
            Log.e("MtaSDK", "encode error", th);
        }
        return null;
    }

    /* renamed from: f */
    public static boolean m8166f(Context context) {
        try {
            if (C4439q.m8160a(context, "android.permission.INTERNET") && C4439q.m8160a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                        return true;
                    }
                    Log.w("MtaSDK", "Network error");
                    return false;
                }
                return false;
            }
            Log.e("MtaSDK", "can not get the permisson of android.permission.INTERNET");
            return false;
        } catch (Throwable th) {
            Log.e("MtaSDK", "isNetworkAvailable error", th);
        }
    }
}
