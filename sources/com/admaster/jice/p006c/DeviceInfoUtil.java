package com.admaster.jice.p006c;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.admaster.jice.p007d.ManagerUtils;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.File;
import java.util.regex.Pattern;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
/* renamed from: com.admaster.jice.c.c */
public class DeviceInfoUtil {
    /* renamed from: a */
    public static String m170a() {
        try {
            return Build.BRAND;
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: b */
    public static String m174b() {
        try {
            return VERSION.RELEASE;
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: c */
    public static String m176c() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: a */
    public static String m171a(Context context) {
        try {
            if (DeviceInfoUtil.m172a(context, "android.permission.READ_PHONE_STATE")) {
                return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: b */
    public static String m175b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (packageInfo == null || TextUtils.isEmpty(packageInfo.versionName)) {
                return "";
            }
            return packageInfo.packageName;
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: c */
    public static String m177c(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (packageInfo == null || TextUtils.isEmpty(packageInfo.versionName)) {
                return "";
            }
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: d */
    public static String m179d(Context context) {
        String str = "";
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    return connectionInfo.getMacAddress();
                }
            }
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: a */
    private static boolean m172a(Context context, String str) {
        if (VERSION.SDK_INT < 23) {
            return true;
        }
        try {
            if (!Reflection.m192a(context) || Reflection.m191a(context, str) == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    /* renamed from: e */
    public static String m181e(Context context) {
        try {
            String h = DeviceInfoUtil.m184h(context);
            if (TextUtils.isEmpty(h)) {
                return h;
            }
            return h.substring(0, 3);
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: f */
    public static String m182f(Context context) {
        try {
            String h = DeviceInfoUtil.m184h(context);
            if (TextUtils.isEmpty(h)) {
                return h;
            }
            return h.substring(3);
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: g */
    public static String m183g(Context context) {
        if (context != null) {
            try {
                if (!DeviceInfoUtil.m172a(context, "android.permission.READ_PHONE_STATE")) {
                    return "";
                }
                String subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
                if (!TextUtils.isEmpty(subscriberId)) {
                    return subscriberId;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }

    /* renamed from: h */
    public static String m184h(Context context) {
        try {
            String str = "";
            String str2 = "";
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager == null) {
                    return str;
                }
                String networkOperator = telephonyManager.getNetworkOperator();
                if (networkOperator == null) {
                    return str2;
                }
                return networkOperator;
            } catch (Exception e) {
                return str2;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    /* renamed from: d */
    public static String m178d() {
        String[] strArr = new String[]{"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        int i2 = 0;
        while (i < strArr.length) {
            try {
                File file = new File(strArr[i] + "su");
                if (file != null && file.exists() && DeviceInfoUtil.m173a(strArr[i] + "su")) {
                    i2 = 1;
                }
                i++;
            } catch (Exception e) {
                i2 = 0;
            }
        }
        if (i2 != 0) {
            return "1";
        }
        return "0";
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004c A:{ExcHandler: IOException (e java.io.IOException), Splitter:B:1:0x0001} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x005f A:{SYNTHETIC, Splitter:B:29:0x005f} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:20:0x004d, code skipped:
            if (r0 != null) goto L_0x004f;
     */
    /* JADX WARNING: Missing block: B:22:?, code skipped:
            r0.exitValue();
     */
    /* JADX WARNING: Missing block: B:25:0x0055, code skipped:
            r0.destroy();
     */
    /* JADX WARNING: Missing block: B:30:?, code skipped:
            r1.exitValue();
     */
    /* JADX WARNING: Missing block: B:33:0x0064, code skipped:
            r1.destroy();
     */
    /* JADX WARNING: Missing block: B:39:0x0073, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:40:0x0074, code skipped:
            r4 = r1;
            r1 = r0;
            r0 = r4;
     */
    /* renamed from: a */
    private static boolean m173a(java.lang.String r5) {
        /*
        r0 = 0;
        r1 = java.lang.Runtime.getRuntime();	 Catch:{ IOException -> 0x004c, all -> 0x0059 }
        r2 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x004c, all -> 0x0059 }
        r3 = "ls -l ";
        r2.<init>(r3);	 Catch:{ IOException -> 0x004c, all -> 0x0059 }
        r2 = r2.append(r5);	 Catch:{ IOException -> 0x004c, all -> 0x0059 }
        r2 = r2.toString();	 Catch:{ IOException -> 0x004c, all -> 0x0059 }
        r0 = r1.exec(r2);	 Catch:{ IOException -> 0x004c, all -> 0x0059 }
        r1 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x004c, all -> 0x0073 }
        r2 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x004c, all -> 0x0073 }
        r3 = r0.getInputStream();	 Catch:{ IOException -> 0x004c, all -> 0x0073 }
        r2.<init>(r3);	 Catch:{ IOException -> 0x004c, all -> 0x0073 }
        r1.<init>(r2);	 Catch:{ IOException -> 0x004c, all -> 0x0073 }
        r1 = r1.readLine();	 Catch:{ IOException -> 0x004c, all -> 0x0073 }
        if (r1 == 0) goto L_0x0068;
    L_0x002c:
        r2 = r1.length();	 Catch:{ IOException -> 0x004c, all -> 0x0073 }
        r3 = 4;
        if (r2 < r3) goto L_0x0068;
    L_0x0033:
        r2 = 3;
        r1 = r1.charAt(r2);	 Catch:{ IOException -> 0x004c, all -> 0x0073 }
        r2 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        if (r1 == r2) goto L_0x0040;
    L_0x003c:
        r2 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        if (r1 != r2) goto L_0x0068;
    L_0x0040:
        if (r0 == 0) goto L_0x0045;
    L_0x0042:
        r0.exitValue();	 Catch:{ IllegalThreadStateException -> 0x0047 }
    L_0x0045:
        r0 = 1;
    L_0x0046:
        return r0;
    L_0x0047:
        r1 = move-exception;
        r0.destroy();
        goto L_0x0045;
    L_0x004c:
        r1 = move-exception;
        if (r0 == 0) goto L_0x0052;
    L_0x004f:
        r0.exitValue();	 Catch:{ IllegalThreadStateException -> 0x0054 }
    L_0x0052:
        r0 = 0;
        goto L_0x0046;
    L_0x0054:
        r1 = move-exception;
        r0.destroy();
        goto L_0x0052;
    L_0x0059:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x005d:
        if (r1 == 0) goto L_0x0062;
    L_0x005f:
        r1.exitValue();	 Catch:{ IllegalThreadStateException -> 0x0063 }
    L_0x0062:
        throw r0;
    L_0x0063:
        r2 = move-exception;
        r1.destroy();
        goto L_0x0062;
    L_0x0068:
        if (r0 == 0) goto L_0x0052;
    L_0x006a:
        r0.exitValue();	 Catch:{ IllegalThreadStateException -> 0x006e }
        goto L_0x0052;
    L_0x006e:
        r1 = move-exception;
        r0.destroy();
        goto L_0x0052;
    L_0x0073:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x005d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.admaster.jice.p006c.DeviceInfoUtil.m173a(java.lang.String):boolean");
    }

    /* renamed from: i */
    public static String m185i(Context context) {
        String str = "";
        try {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            return new StringBuilder(String.valueOf(i)).append("x").append(displayMetrics.heightPixels).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: j */
    public static String m186j(Context context) {
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

    /* renamed from: k */
    public static String m187k(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo == null || TextUtils.isEmpty(connectionInfo.getBSSID())) {
                return "";
            }
            return connectionInfo.getBSSID();
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: l */
    public static String m188l(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            String str = "";
            if (connectionInfo == null || TextUtils.isEmpty(connectionInfo.getSSID())) {
                return "";
            }
            return Pattern.compile("[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]").matcher(connectionInfo.getSSID()).replaceAll("").trim().replaceAll("\"", "").replaceAll("\\s+", "").trim();
        } catch (Exception e) {
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0049 A:{SYNTHETIC, Splitter:B:31:0x0049} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x004e A:{SYNTHETIC, Splitter:B:34:0x004e} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0049 A:{SYNTHETIC, Splitter:B:31:0x0049} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x004e A:{SYNTHETIC, Splitter:B:34:0x004e} */
    /* renamed from: e */
    public static java.lang.String m180e() {
        /*
        r2 = 0;
        r0 = "";
        r1 = java.lang.Runtime.getRuntime();	 Catch:{ Exception -> 0x0031, all -> 0x0045 }
        r3 = "cat /sys/class/net/wlan0/address";
        r3 = r1.exec(r3);	 Catch:{ Exception -> 0x0031, all -> 0x0045 }
        if (r3 == 0) goto L_0x006c;
    L_0x000f:
        r1 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0065, all -> 0x0060 }
        r4 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x0065, all -> 0x0060 }
        r5 = r3.getInputStream();	 Catch:{ Exception -> 0x0065, all -> 0x0060 }
        r6 = "GBK";
        r4.<init>(r5, r6);	 Catch:{ Exception -> 0x0065, all -> 0x0060 }
        r1.<init>(r4);	 Catch:{ Exception -> 0x0065, all -> 0x0060 }
        r2 = r1.readLine();	 Catch:{ Exception -> 0x0069, all -> 0x0062 }
        if (r2 == 0) goto L_0x0026;
    L_0x0025:
        r0 = r2;
    L_0x0026:
        if (r3 == 0) goto L_0x002b;
    L_0x0028:
        r3.exitValue();	 Catch:{ IllegalThreadStateException -> 0x0057 }
    L_0x002b:
        if (r1 == 0) goto L_0x0030;
    L_0x002d:
        r1.close();	 Catch:{ Exception -> 0x005e }
    L_0x0030:
        return r0;
    L_0x0031:
        r1 = move-exception;
        r1 = r2;
    L_0x0033:
        if (r2 == 0) goto L_0x0038;
    L_0x0035:
        r2.exitValue();	 Catch:{ IllegalThreadStateException -> 0x0040 }
    L_0x0038:
        if (r1 == 0) goto L_0x0030;
    L_0x003a:
        r1.close();	 Catch:{ Exception -> 0x003e }
        goto L_0x0030;
    L_0x003e:
        r1 = move-exception;
        goto L_0x0030;
    L_0x0040:
        r3 = move-exception;
        r2.destroy();
        goto L_0x0038;
    L_0x0045:
        r0 = move-exception;
        r3 = r2;
    L_0x0047:
        if (r3 == 0) goto L_0x004c;
    L_0x0049:
        r3.exitValue();	 Catch:{ IllegalThreadStateException -> 0x0052 }
    L_0x004c:
        if (r2 == 0) goto L_0x0051;
    L_0x004e:
        r2.close();	 Catch:{ Exception -> 0x005c }
    L_0x0051:
        throw r0;
    L_0x0052:
        r1 = move-exception;
        r3.destroy();
        goto L_0x004c;
    L_0x0057:
        r2 = move-exception;
        r3.destroy();
        goto L_0x002b;
    L_0x005c:
        r1 = move-exception;
        goto L_0x0051;
    L_0x005e:
        r1 = move-exception;
        goto L_0x0030;
    L_0x0060:
        r0 = move-exception;
        goto L_0x0047;
    L_0x0062:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0047;
    L_0x0065:
        r1 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x0033;
    L_0x0069:
        r2 = move-exception;
        r2 = r3;
        goto L_0x0033;
    L_0x006c:
        r1 = r2;
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.admaster.jice.p006c.DeviceInfoUtil.m180e():java.lang.String");
    }

    @SuppressLint({"NewApi"})
    /* renamed from: m */
    public static String m189m(Context context) {
        try {
            if (!ManagerUtils.m236b(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                return "";
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (TextUtils.isEmpty(telephonyManager.getNetworkOperator())) {
                return "";
            }
            CellLocation cellLocation = telephonyManager.getCellLocation();
            int baseStationId;
            JSONObject jSONObject;
            if (cellLocation instanceof CdmaCellLocation) {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) telephonyManager.getCellLocation();
                if (cdmaCellLocation == null) {
                    throw new Exception("errr");
                }
                baseStationId = cdmaCellLocation.getBaseStationId();
                int networkId = cdmaCellLocation.getNetworkId();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(cdmaCellLocation.getSystemId());
                jSONObject = new JSONObject();
                jSONObject.put("bid", baseStationId);
                jSONObject.put("nid", networkId);
                jSONObject.put("sid", stringBuilder.toString());
                jSONObject.put("latitude", new StringBuilder(String.valueOf(cdmaCellLocation.getBaseStationLatitude())).toString());
                jSONObject.put("longitude", new StringBuilder(String.valueOf(cdmaCellLocation.getBaseStationLongitude())).toString());
                return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
            } else {
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
                    if (gsmCellLocation == null) {
                        throw new Exception("errr");
                    }
                    baseStationId = gsmCellLocation.getLac();
                    jSONObject = new JSONObject();
                    jSONObject.put("cid", gsmCellLocation.getCid());
                    jSONObject.put("lac", baseStationId);
                    jSONObject.put("psc", gsmCellLocation.getPsc());
                    return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
                }
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: n */
    public static String m190n(Context context) {
        try {
            if (DeviceInfoUtil.m172a(context, "android.permission.READ_PHONE_STATE")) {
                return Secure.getString(context.getContentResolver(), "android_id");
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
