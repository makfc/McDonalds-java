package com.alipay.security.mobile.module.p021b;

import android.app.KeyguardManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.alipay.security.mobile.module.p019a.C0689a;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.alipay.security.mobile.module.b.b */
public final class C0692b {
    /* renamed from: a */
    private static C0692b f725a = new C0692b();

    private C0692b() {
    }

    /* renamed from: A */
    private static String m1181A() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Throwable th) {
        }
        return "";
    }

    /* renamed from: a */
    public static C0692b m1182a() {
        return f725a;
    }

    /* renamed from: a */
    private static String m1183a(BluetoothAdapter bluetoothAdapter) {
        try {
            Field declaredField = BluetoothAdapter.class.getDeclaredField("mService");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(bluetoothAdapter);
            if (obj == null) {
                return null;
            }
            Method declaredMethod = obj.getClass().getDeclaredMethod("getAddress", new Class[0]);
            declaredMethod.setAccessible(true);
            obj = declaredMethod.invoke(obj, new Object[0]);
            if (obj != null && (obj instanceof String)) {
                return (String) obj;
            }
            return null;
        } catch (Throwable th) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001e  */
    /* renamed from: a */
    public static java.lang.String m1184a(android.content.Context r2) {
        /*
        r1 = 0;
        r0 = "android.permission.READ_PHONE_STATE";
        r0 = com.alipay.security.mobile.module.p021b.C0692b.m1185a(r2, r0);
        if (r0 == 0) goto L_0x000c;
    L_0x0009:
        r0 = "";
    L_0x000b:
        return r0;
    L_0x000c:
        if (r2 == 0) goto L_0x0022;
    L_0x000e:
        r0 = "phone";
        r0 = r2.getSystemService(r0);	 Catch:{ Throwable -> 0x0021 }
        r0 = (android.telephony.TelephonyManager) r0;	 Catch:{ Throwable -> 0x0021 }
        if (r0 == 0) goto L_0x0022;
    L_0x0018:
        r0 = r0.getDeviceId();	 Catch:{ Throwable -> 0x0021 }
    L_0x001c:
        if (r0 != 0) goto L_0x000b;
    L_0x001e:
        r0 = "";
        goto L_0x000b;
    L_0x0021:
        r0 = move-exception;
    L_0x0022:
        r0 = r1;
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p021b.C0692b.m1184a(android.content.Context):java.lang.String");
    }

    /* renamed from: a */
    private static boolean m1185a(Context context, String str) {
        return !(context.getPackageManager().checkPermission(str, context.getPackageName()) == 0);
    }

    /* renamed from: b */
    public static String m1186b() {
        long j = 0;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
        }
        return String.valueOf(j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001f  */
    /* renamed from: b */
    public static java.lang.String m1187b(android.content.Context r2) {
        /*
        r1 = "";
        r0 = "android.permission.READ_PHONE_STATE";
        r0 = com.alipay.security.mobile.module.p021b.C0692b.m1185a(r2, r0);
        if (r0 == 0) goto L_0x000d;
    L_0x000a:
        r0 = "";
    L_0x000c:
        return r0;
    L_0x000d:
        if (r2 == 0) goto L_0x0023;
    L_0x000f:
        r0 = "phone";
        r0 = r2.getSystemService(r0);	 Catch:{ Throwable -> 0x0022 }
        r0 = (android.telephony.TelephonyManager) r0;	 Catch:{ Throwable -> 0x0022 }
        if (r0 == 0) goto L_0x0023;
    L_0x0019:
        r0 = r0.getSubscriberId();	 Catch:{ Throwable -> 0x0022 }
    L_0x001d:
        if (r0 != 0) goto L_0x000c;
    L_0x001f:
        r0 = "";
        goto L_0x000c;
    L_0x0022:
        r0 = move-exception;
    L_0x0023:
        r0 = r1;
        goto L_0x001d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p021b.C0692b.m1187b(android.content.Context):java.lang.String");
    }

    /* renamed from: c */
    public static String m1188c() {
        long j = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(C0689a.m1166a().getPath());
                j = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
            }
        } catch (Throwable th) {
        }
        return String.valueOf(j);
    }

    /* renamed from: c */
    public static String m1189c(Context context) {
        int i = 0;
        try {
            i = System.getInt(context.getContentResolver(), "airplane_mode_on", 0);
        } catch (Throwable th) {
        }
        return i == 1 ? "1" : "0";
    }

    /* renamed from: d */
    public static String m1190d() {
        return "";
    }

    /* renamed from: d */
    public static String m1191d(Context context) {
        int i = 1;
        JSONObject jSONObject = new JSONObject();
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            if (audioManager.getRingerMode() != 0) {
                i = 0;
            }
            int streamVolume = audioManager.getStreamVolume(0);
            int streamVolume2 = audioManager.getStreamVolume(1);
            int streamVolume3 = audioManager.getStreamVolume(2);
            int streamVolume4 = audioManager.getStreamVolume(3);
            int streamVolume5 = audioManager.getStreamVolume(4);
            jSONObject.put("ringermode", String.valueOf(i));
            jSONObject.put("call", String.valueOf(streamVolume));
            jSONObject.put("system", String.valueOf(streamVolume2));
            jSONObject.put("ring", String.valueOf(streamVolume3));
            jSONObject.put("music", String.valueOf(streamVolume4));
            jSONObject.put("alarm", String.valueOf(streamVolume5));
        } catch (Throwable th) {
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
    }

    /* JADX WARNING: Removed duplicated region for block: B:79:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x005b A:{SYNTHETIC, Splitter:B:32:0x005b} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0060 A:{SYNTHETIC, Splitter:B:35:0x0060} */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0065 A:{SYNTHETIC, Splitter:B:38:0x0065} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:79:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0072 A:{SYNTHETIC, Splitter:B:46:0x0072} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0077 A:{SYNTHETIC, Splitter:B:49:0x0077} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x007c A:{SYNTHETIC, Splitter:B:52:0x007c} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x005b A:{SYNTHETIC, Splitter:B:32:0x005b} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0060 A:{SYNTHETIC, Splitter:B:35:0x0060} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0065 A:{SYNTHETIC, Splitter:B:38:0x0065} */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:79:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0072 A:{SYNTHETIC, Splitter:B:46:0x0072} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0077 A:{SYNTHETIC, Splitter:B:49:0x0077} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x007c A:{SYNTHETIC, Splitter:B:52:0x007c} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x005b A:{SYNTHETIC, Splitter:B:32:0x005b} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0060 A:{SYNTHETIC, Splitter:B:35:0x0060} */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0065 A:{SYNTHETIC, Splitter:B:38:0x0065} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:79:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0072 A:{SYNTHETIC, Splitter:B:46:0x0072} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0077 A:{SYNTHETIC, Splitter:B:49:0x0077} */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x007c A:{SYNTHETIC, Splitter:B:52:0x007c} */
    /* renamed from: e */
    public static java.lang.String m1192e() {
        /*
        r1 = 0;
        r4 = "0000000000000000";
        r3 = new java.io.FileInputStream;	 Catch:{ Throwable -> 0x0056, all -> 0x006d }
        r0 = new java.io.File;	 Catch:{ Throwable -> 0x0056, all -> 0x006d }
        r2 = "/proc/cpuinfo";
        r0.<init>(r2);	 Catch:{ Throwable -> 0x0056, all -> 0x006d }
        r3.<init>(r0);	 Catch:{ Throwable -> 0x0056, all -> 0x006d }
        r2 = new java.io.InputStreamReader;	 Catch:{ Throwable -> 0x0098, all -> 0x008e }
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0098, all -> 0x008e }
        r0 = new java.io.LineNumberReader;	 Catch:{ Throwable -> 0x009c, all -> 0x0091 }
        r0.<init>(r2);	 Catch:{ Throwable -> 0x009c, all -> 0x0091 }
        r1 = 1;
    L_0x001a:
        r5 = 100;
        if (r1 >= r5) goto L_0x00a7;
    L_0x001e:
        r5 = r0.readLine();	 Catch:{ Throwable -> 0x00a1, all -> 0x0093 }
        if (r5 == 0) goto L_0x00a7;
    L_0x0024:
        r6 = "Serial";
        r6 = r5.indexOf(r6);	 Catch:{ Throwable -> 0x00a1, all -> 0x0093 }
        if (r6 < 0) goto L_0x0050;
    L_0x002c:
        r1 = ":";
        r1 = r5.indexOf(r1);	 Catch:{ Throwable -> 0x00a1, all -> 0x0093 }
        r1 = r1 + 1;
        r6 = r5.length();	 Catch:{ Throwable -> 0x00a1, all -> 0x0093 }
        r1 = r5.substring(r1, r6);	 Catch:{ Throwable -> 0x00a1, all -> 0x0093 }
        r4 = r1.trim();	 Catch:{ Throwable -> 0x00a1, all -> 0x0093 }
        r1 = r4;
    L_0x0041:
        r0.close();	 Catch:{ Throwable -> 0x0080 }
    L_0x0044:
        r2.close();	 Catch:{ Throwable -> 0x0082 }
    L_0x0047:
        r3.close();	 Catch:{ Throwable -> 0x0053 }
        r0 = r1;
    L_0x004b:
        if (r0 != 0) goto L_0x004f;
    L_0x004d:
        r0 = "";
    L_0x004f:
        return r0;
    L_0x0050:
        r1 = r1 + 1;
        goto L_0x001a;
    L_0x0053:
        r0 = move-exception;
        r0 = r1;
        goto L_0x004b;
    L_0x0056:
        r0 = move-exception;
        r0 = r1;
        r2 = r1;
    L_0x0059:
        if (r0 == 0) goto L_0x005e;
    L_0x005b:
        r0.close();	 Catch:{ Throwable -> 0x0084 }
    L_0x005e:
        if (r1 == 0) goto L_0x0063;
    L_0x0060:
        r1.close();	 Catch:{ Throwable -> 0x0086 }
    L_0x0063:
        if (r2 == 0) goto L_0x00a5;
    L_0x0065:
        r2.close();	 Catch:{ Throwable -> 0x006a }
        r0 = r4;
        goto L_0x004b;
    L_0x006a:
        r0 = move-exception;
        r0 = r4;
        goto L_0x004b;
    L_0x006d:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x0070:
        if (r1 == 0) goto L_0x0075;
    L_0x0072:
        r1.close();	 Catch:{ Throwable -> 0x0088 }
    L_0x0075:
        if (r2 == 0) goto L_0x007a;
    L_0x0077:
        r2.close();	 Catch:{ Throwable -> 0x008a }
    L_0x007a:
        if (r3 == 0) goto L_0x007f;
    L_0x007c:
        r3.close();	 Catch:{ Throwable -> 0x008c }
    L_0x007f:
        throw r0;
    L_0x0080:
        r0 = move-exception;
        goto L_0x0044;
    L_0x0082:
        r0 = move-exception;
        goto L_0x0047;
    L_0x0084:
        r0 = move-exception;
        goto L_0x005e;
    L_0x0086:
        r0 = move-exception;
        goto L_0x0063;
    L_0x0088:
        r1 = move-exception;
        goto L_0x0075;
    L_0x008a:
        r1 = move-exception;
        goto L_0x007a;
    L_0x008c:
        r1 = move-exception;
        goto L_0x007f;
    L_0x008e:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0070;
    L_0x0091:
        r0 = move-exception;
        goto L_0x0070;
    L_0x0093:
        r1 = move-exception;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x0070;
    L_0x0098:
        r0 = move-exception;
        r0 = r1;
        r2 = r3;
        goto L_0x0059;
    L_0x009c:
        r0 = move-exception;
        r0 = r1;
        r1 = r2;
        r2 = r3;
        goto L_0x0059;
    L_0x00a1:
        r1 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x0059;
    L_0x00a5:
        r0 = r4;
        goto L_0x004b;
    L_0x00a7:
        r1 = r4;
        goto L_0x0041;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p021b.C0692b.m1192e():java.lang.String");
    }

    /* renamed from: e */
    public static String m1193e(Context context) {
        Object networkOperatorName;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    networkOperatorName = telephonyManager.getNetworkOperatorName();
                    return (networkOperatorName != null || SafeJsonPrimitive.NULL_STRING.equals(networkOperatorName)) ? "" : networkOperatorName;
                }
            } catch (Throwable th) {
            }
        }
        networkOperatorName = null;
        if (networkOperatorName != null) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0049  */
    /* renamed from: f */
    public static java.lang.String m1194f(android.content.Context r5) {
        /*
        r1 = 0;
        if (r5 == 0) goto L_0x0046;
    L_0x0003:
        r0 = "sensor";
        r0 = r5.getSystemService(r0);	 Catch:{ Throwable -> 0x0045 }
        r0 = (android.hardware.SensorManager) r0;	 Catch:{ Throwable -> 0x0045 }
        if (r0 == 0) goto L_0x0046;
    L_0x000d:
        r2 = -1;
        r0 = r0.getSensorList(r2);	 Catch:{ Throwable -> 0x0045 }
        if (r0 == 0) goto L_0x0046;
    L_0x0014:
        r2 = r0.size();	 Catch:{ Throwable -> 0x0045 }
        if (r2 <= 0) goto L_0x0046;
    L_0x001a:
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0045 }
        r2.<init>();	 Catch:{ Throwable -> 0x0045 }
        r3 = r0.iterator();	 Catch:{ Throwable -> 0x0045 }
    L_0x0023:
        r0 = r3.hasNext();	 Catch:{ Throwable -> 0x0045 }
        if (r0 == 0) goto L_0x004c;
    L_0x0029:
        r0 = r3.next();	 Catch:{ Throwable -> 0x0045 }
        r0 = (android.hardware.Sensor) r0;	 Catch:{ Throwable -> 0x0045 }
        r4 = r0.getName();	 Catch:{ Throwable -> 0x0045 }
        r2.append(r4);	 Catch:{ Throwable -> 0x0045 }
        r4 = r0.getVersion();	 Catch:{ Throwable -> 0x0045 }
        r2.append(r4);	 Catch:{ Throwable -> 0x0045 }
        r0 = r0.getVendor();	 Catch:{ Throwable -> 0x0045 }
        r2.append(r0);	 Catch:{ Throwable -> 0x0045 }
        goto L_0x0023;
    L_0x0045:
        r0 = move-exception;
    L_0x0046:
        r0 = r1;
    L_0x0047:
        if (r0 != 0) goto L_0x004b;
    L_0x0049:
        r0 = "";
    L_0x004b:
        return r0;
    L_0x004c:
        r0 = r2.toString();	 Catch:{ Throwable -> 0x0045 }
        r0 = com.alipay.security.mobile.module.p019a.C0689a.m1175e(r0);	 Catch:{ Throwable -> 0x0045 }
        goto L_0x0047;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p021b.C0692b.m1194f(android.content.Context):java.lang.String");
    }

    /* renamed from: g */
    public static String m1195g() {
        String x = C0692b.m1226x();
        return !C0689a.m1169a(x) ? x : C0692b.m1227y();
    }

    /* renamed from: g */
    public static String m1196g(Context context) {
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
                if (sensorManager != null) {
                    List<Sensor> sensorList = sensorManager.getSensorList(-1);
                    if (sensorList != null && sensorList.size() > 0) {
                        for (Sensor sensor : sensorList) {
                            if (sensor != null) {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("name", sensor.getName());
                                jSONObject.put("version", sensor.getVersion());
                                jSONObject.put("vendor", sensor.getVendor());
                                jSONArray.put(jSONObject);
                            }
                        }
                    }
                }
            } catch (Throwable th) {
            }
        }
        return !(jSONArray instanceof JSONArray) ? jSONArray.toString() : JSONArrayInstrumentation.toString(jSONArray);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0035 A:{SYNTHETIC, Splitter:B:23:0x0035} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x003a A:{SYNTHETIC, Splitter:B:26:0x003a} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0047 A:{SYNTHETIC, Splitter:B:32:0x0047} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x004c A:{SYNTHETIC, Splitter:B:35:0x004c} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0035 A:{SYNTHETIC, Splitter:B:23:0x0035} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x003a A:{SYNTHETIC, Splitter:B:26:0x003a} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0047 A:{SYNTHETIC, Splitter:B:32:0x0047} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x004c A:{SYNTHETIC, Splitter:B:35:0x004c} */
    /* renamed from: h */
    public static java.lang.String m1197h() {
        /*
        r0 = 0;
        r5 = 1;
        r2 = new java.io.FileReader;	 Catch:{ Throwable -> 0x0031, all -> 0x0040 }
        r1 = "/proc/cpuinfo";
        r2.<init>(r1);	 Catch:{ Throwable -> 0x0031, all -> 0x0040 }
        r1 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x0065, all -> 0x005e }
        r1.<init>(r2);	 Catch:{ Throwable -> 0x0065, all -> 0x005e }
        r0 = r1.readLine();	 Catch:{ Throwable -> 0x0068, all -> 0x0063 }
        r3 = ":\\s+";
        r4 = 2;
        r0 = r0.split(r3, r4);	 Catch:{ Throwable -> 0x0068, all -> 0x0063 }
        if (r0 == 0) goto L_0x0028;
    L_0x001b:
        r3 = r0.length;	 Catch:{ Throwable -> 0x0068, all -> 0x0063 }
        if (r3 <= r5) goto L_0x0028;
    L_0x001e:
        r3 = 1;
        r0 = r0[r3];	 Catch:{ Throwable -> 0x0068, all -> 0x0063 }
        r2.close();	 Catch:{ Throwable -> 0x0050 }
    L_0x0024:
        r1.close();	 Catch:{ Throwable -> 0x0052 }
    L_0x0027:
        return r0;
    L_0x0028:
        r2.close();	 Catch:{ Throwable -> 0x0054 }
    L_0x002b:
        r1.close();	 Catch:{ Throwable -> 0x0056 }
    L_0x002e:
        r0 = "";
        goto L_0x0027;
    L_0x0031:
        r1 = move-exception;
        r1 = r0;
    L_0x0033:
        if (r1 == 0) goto L_0x0038;
    L_0x0035:
        r1.close();	 Catch:{ Throwable -> 0x0058 }
    L_0x0038:
        if (r0 == 0) goto L_0x002e;
    L_0x003a:
        r0.close();	 Catch:{ Throwable -> 0x003e }
        goto L_0x002e;
    L_0x003e:
        r0 = move-exception;
        goto L_0x002e;
    L_0x0040:
        r1 = move-exception;
        r2 = r0;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x0045:
        if (r2 == 0) goto L_0x004a;
    L_0x0047:
        r2.close();	 Catch:{ Throwable -> 0x005a }
    L_0x004a:
        if (r1 == 0) goto L_0x004f;
    L_0x004c:
        r1.close();	 Catch:{ Throwable -> 0x005c }
    L_0x004f:
        throw r0;
    L_0x0050:
        r2 = move-exception;
        goto L_0x0024;
    L_0x0052:
        r1 = move-exception;
        goto L_0x0027;
    L_0x0054:
        r0 = move-exception;
        goto L_0x002b;
    L_0x0056:
        r0 = move-exception;
        goto L_0x002e;
    L_0x0058:
        r1 = move-exception;
        goto L_0x0038;
    L_0x005a:
        r2 = move-exception;
        goto L_0x004a;
    L_0x005c:
        r1 = move-exception;
        goto L_0x004f;
    L_0x005e:
        r1 = move-exception;
        r6 = r1;
        r1 = r0;
        r0 = r6;
        goto L_0x0045;
    L_0x0063:
        r0 = move-exception;
        goto L_0x0045;
    L_0x0065:
        r1 = move-exception;
        r1 = r2;
        goto L_0x0033;
    L_0x0068:
        r0 = move-exception;
        r0 = r1;
        r1 = r2;
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p021b.C0692b.m1197h():java.lang.String");
    }

    /* renamed from: h */
    public static String m1198h(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return Integer.toString(displayMetrics.widthPixels) + "*" + Integer.toString(displayMetrics.heightPixels);
        } catch (Throwable th) {
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0034 A:{SYNTHETIC, Splitter:B:19:0x0034} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0039 A:{SYNTHETIC, Splitter:B:22:0x0039} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0044 A:{SYNTHETIC, Splitter:B:28:0x0044} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0049 A:{SYNTHETIC, Splitter:B:31:0x0049} */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0034 A:{SYNTHETIC, Splitter:B:19:0x0034} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0039 A:{SYNTHETIC, Splitter:B:22:0x0039} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0044 A:{SYNTHETIC, Splitter:B:28:0x0044} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0049 A:{SYNTHETIC, Splitter:B:31:0x0049} */
    /* renamed from: i */
    public static java.lang.String m1199i() {
        /*
        r3 = 0;
        r4 = "/proc/meminfo";
        r0 = 0;
        r2 = new java.io.FileReader;	 Catch:{ Throwable -> 0x0030, all -> 0x003f }
        r2.<init>(r4);	 Catch:{ Throwable -> 0x0030, all -> 0x003f }
        r4 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x005c, all -> 0x0057 }
        r5 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r4.<init>(r2, r5);	 Catch:{ Throwable -> 0x005c, all -> 0x0057 }
        r3 = r4.readLine();	 Catch:{ Throwable -> 0x005e, all -> 0x005a }
        if (r3 == 0) goto L_0x0025;
    L_0x0017:
        r5 = "\\s+";
        r3 = r3.split(r5);	 Catch:{ Throwable -> 0x005e, all -> 0x005a }
        r5 = 1;
        r3 = r3[r5];	 Catch:{ Throwable -> 0x005e, all -> 0x005a }
        r0 = java.lang.Integer.parseInt(r3);	 Catch:{ Throwable -> 0x005e, all -> 0x005a }
        r0 = (long) r0;
    L_0x0025:
        r2.close();	 Catch:{ Throwable -> 0x004d }
    L_0x0028:
        r4.close();	 Catch:{ Throwable -> 0x004f }
    L_0x002b:
        r0 = java.lang.String.valueOf(r0);
        return r0;
    L_0x0030:
        r2 = move-exception;
        r2 = r3;
    L_0x0032:
        if (r2 == 0) goto L_0x0037;
    L_0x0034:
        r2.close();	 Catch:{ Throwable -> 0x0051 }
    L_0x0037:
        if (r3 == 0) goto L_0x002b;
    L_0x0039:
        r3.close();	 Catch:{ Throwable -> 0x003d }
        goto L_0x002b;
    L_0x003d:
        r2 = move-exception;
        goto L_0x002b;
    L_0x003f:
        r0 = move-exception;
        r2 = r3;
        r4 = r3;
    L_0x0042:
        if (r2 == 0) goto L_0x0047;
    L_0x0044:
        r2.close();	 Catch:{ Throwable -> 0x0053 }
    L_0x0047:
        if (r4 == 0) goto L_0x004c;
    L_0x0049:
        r4.close();	 Catch:{ Throwable -> 0x0055 }
    L_0x004c:
        throw r0;
    L_0x004d:
        r2 = move-exception;
        goto L_0x0028;
    L_0x004f:
        r2 = move-exception;
        goto L_0x002b;
    L_0x0051:
        r2 = move-exception;
        goto L_0x0037;
    L_0x0053:
        r1 = move-exception;
        goto L_0x0047;
    L_0x0055:
        r1 = move-exception;
        goto L_0x004c;
    L_0x0057:
        r0 = move-exception;
        r4 = r3;
        goto L_0x0042;
    L_0x005a:
        r0 = move-exception;
        goto L_0x0042;
    L_0x005c:
        r4 = move-exception;
        goto L_0x0032;
    L_0x005e:
        r3 = move-exception;
        r3 = r4;
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p021b.C0692b.m1199i():java.lang.String");
    }

    /* renamed from: i */
    public static String m1200i(Context context) {
        try {
            return context.getResources().getDisplayMetrics().widthPixels;
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: j */
    public static String m1201j() {
        long j = 0;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
        }
        return String.valueOf(j);
    }

    /* renamed from: j */
    public static String m1202j(Context context) {
        try {
            return context.getResources().getDisplayMetrics().heightPixels;
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: k */
    public static String m1203k() {
        long j = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                j = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
            }
        } catch (Throwable th) {
        }
        return String.valueOf(j);
    }

    /* renamed from: k */
    public static String m1204k(Context context) {
        if (C0692b.m1185a(context, "android.permission.ACCESS_WIFI_STATE")) {
            return "";
        }
        String str = "";
        try {
            String macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (macAddress != null) {
                try {
                    if (!(macAddress.length() == 0 || "02:00:00:00:00:00".equals(macAddress))) {
                        return macAddress;
                    }
                } catch (Throwable th) {
                    return macAddress;
                }
            }
            return C0692b.m1225w();
        } catch (Throwable th2) {
            return str;
        }
    }

    /* renamed from: l */
    public static String m1205l() {
        String str;
        String str2 = "";
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls.newInstance(), new Object[]{"gsm.version.baseband", "no message"});
        } catch (Throwable th) {
            str = str2;
        }
        return str == null ? "" : str;
    }

    /* renamed from: l */
    public static String m1206l(Context context) {
        if (C0692b.m1185a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        String str = "";
        try {
            String simSerialNumber = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
            if (simSerialNumber != null) {
                if (simSerialNumber == null) {
                    return simSerialNumber;
                }
                try {
                    if (simSerialNumber.length() != 0) {
                        return simSerialNumber;
                    }
                } catch (Throwable th) {
                    return simSerialNumber;
                }
            }
            return "";
        } catch (Throwable th2) {
            return str;
        }
    }

    /* renamed from: m */
    public static String m1207m() {
        String str = "";
        try {
            str = Build.SERIAL;
        } catch (Throwable th) {
        }
        return str == null ? "" : str;
    }

    /* renamed from: m */
    public static String m1208m(Context context) {
        String str = "";
        try {
            str = Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable th) {
        }
        return str == null ? "" : str;
    }

    /* renamed from: n */
    public static String m1209n() {
        String str = "";
        try {
            str = Locale.getDefault().toString();
        } catch (Throwable th) {
        }
        return str == null ? "" : str;
    }

    /* JADX WARNING: Missing block: B:9:0x001d, code skipped:
            if ("02:00:00:00:00:00".equals(r0) == false) goto L_0x0029;
     */
    /* renamed from: n */
    public static java.lang.String m1210n(android.content.Context r3) {
        /*
        r0 = "android.permission.BLUETOOTH";
        r0 = com.alipay.security.mobile.module.p021b.C0692b.m1185a(r3, r0);
        if (r0 == 0) goto L_0x000b;
    L_0x0008:
        r0 = "";
    L_0x000a:
        return r0;
    L_0x000b:
        r0 = com.alipay.security.mobile.module.p021b.C0692b.m1228z();
        if (r0 == 0) goto L_0x001f;
    L_0x0011:
        r1 = r0.length();	 Catch:{ Throwable -> 0x002e }
        if (r1 == 0) goto L_0x001f;
    L_0x0017:
        r1 = "02:00:00:00:00:00";
        r1 = r1.equals(r0);	 Catch:{ Throwable -> 0x002e }
        if (r1 == 0) goto L_0x0029;
    L_0x001f:
        r1 = r3.getContentResolver();	 Catch:{ Throwable -> 0x002e }
        r2 = "bluetooth_address";
        r0 = android.provider.Settings.Secure.getString(r1, r2);	 Catch:{ Throwable -> 0x002e }
    L_0x0029:
        if (r0 != 0) goto L_0x000a;
    L_0x002b:
        r0 = "";
        goto L_0x000a;
    L_0x002e:
        r1 = move-exception;
        goto L_0x000a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p021b.C0692b.m1210n(android.content.Context):java.lang.String");
    }

    /* renamed from: o */
    public static String m1211o() {
        String str = "";
        try {
            str = TimeZone.getDefault().getDisplayName(false, 0);
        } catch (Throwable th) {
        }
        return str == null ? "" : str;
    }

    /* renamed from: o */
    public static String m1212o(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return String.valueOf(telephonyManager.getNetworkType());
            }
        } catch (Throwable th) {
        }
        return "";
    }

    /* renamed from: p */
    public static String m1213p() {
        try {
            long currentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
            return (currentTimeMillis - (currentTimeMillis % 1000));
        } catch (Throwable th) {
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* renamed from: p */
    public static java.lang.String m1214p(android.content.Context r3) {
        /*
        r1 = "";
        r0 = "android.permission.ACCESS_WIFI_STATE";
        r0 = com.alipay.security.mobile.module.p021b.C0692b.m1185a(r3, r0);
        if (r0 == 0) goto L_0x000d;
    L_0x000a:
        r0 = "";
    L_0x000c:
        return r0;
    L_0x000d:
        r0 = "wifi";
        r0 = r3.getSystemService(r0);	 Catch:{ Throwable -> 0x0029 }
        r0 = (android.net.wifi.WifiManager) r0;	 Catch:{ Throwable -> 0x0029 }
        r2 = r0.isWifiEnabled();	 Catch:{ Throwable -> 0x0029 }
        if (r2 == 0) goto L_0x002a;
    L_0x001c:
        r0 = r0.getConnectionInfo();	 Catch:{ Throwable -> 0x0029 }
        r0 = r0.getBSSID();	 Catch:{ Throwable -> 0x0029 }
    L_0x0024:
        if (r0 != 0) goto L_0x000c;
    L_0x0026:
        r0 = "";
        goto L_0x000c;
    L_0x0029:
        r0 = move-exception;
    L_0x002a:
        r0 = r1;
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p021b.C0692b.m1214p(android.content.Context):java.lang.String");
    }

    /* renamed from: q */
    public static String m1215q() {
        try {
            return SystemClock.elapsedRealtime();
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: q */
    public static String m1216q(Context context) {
        String str = "";
        try {
            String t = C0692b.m1222t(context);
            return (C0689a.m1172b(t) && C0689a.m1172b(C0692b.m1181A())) ? t + ":" + C0692b.m1181A() : str;
        } catch (Throwable th) {
            return str;
        }
    }

    /* renamed from: r */
    public static String m1217r() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String[] strArr = new String[]{"/dev/qemu_pipe", "/dev/socket/qemud", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/genyd", "/dev/socket/baseband_genyd"};
            stringBuilder.append("00" + ":");
            for (int i = 0; i < 7; i++) {
                if (new File(strArr[i]).exists()) {
                    stringBuilder.append("1");
                } else {
                    stringBuilder.append("0");
                }
            }
            return stringBuilder.toString();
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: r */
    public static String m1218r(Context context) {
        try {
            if (!((KeyguardManager) context.getSystemService("keyguard")).isKeyguardSecure()) {
                return "0:0";
            }
            String[] strArr = new String[]{"/data/system/password.key", "/data/system/gesture.key", "/data/system/gatekeeper.password.key", "/data/system/gatekeeper.gesture.key", "/data/system/gatekeeper.pattern.key"};
            long j = 0;
            for (int i = 0; i < 5; i++) {
                long j2 = -1;
                try {
                    j2 = new File(strArr[i]).lastModified();
                } catch (Throwable th) {
                }
                j = Math.max(j2, j);
            }
            return "1:" + j;
        } catch (Throwable th2) {
            return "";
        }
    }

    /* renamed from: s */
    public static String m1219s() {
        String[] strArr = new String[]{"dalvik.system.Taint"};
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("00");
        stringBuilder.append(":");
        for (int i = 0; i <= 0; i++) {
            try {
                Class.forName(strArr[0]);
                stringBuilder.append("1");
            } catch (Throwable th) {
                stringBuilder.append("0");
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: s */
    public static String m1220s(Context context) {
        try {
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = registerReceiver.getIntExtra("level", -1);
            int intExtra2 = registerReceiver.getIntExtra("status", -1);
            Object obj = (intExtra2 == 2 || intExtra2 == 5) ? 1 : null;
            return (obj != null ? "1" : "0") + ":" + intExtra;
        } catch (Throwable th) {
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x0041 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0085 A:{SYNTHETIC, Splitter:B:22:0x0085} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0091 A:{SYNTHETIC, Splitter:B:30:0x0091} */
    /* renamed from: t */
    public static java.lang.String m1221t() {
        /*
        r2 = 48;
        r0 = "00";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = new java.util.LinkedHashMap;
        r5.<init>();
        r1 = "/system/build.prop";
        r3 = "ro.product.name=sdk";
        r5.put(r1, r3);
        r1 = "/proc/tty/drivers";
        r3 = "goldfish";
        r5.put(r1, r3);
        r1 = "/proc/cpuinfo";
        r3 = "goldfish";
        r5.put(r1, r3);
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r1 = ":";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r4.append(r0);
        r0 = r5.keySet();
        r6 = r0.iterator();
    L_0x0041:
        r0 = r6.hasNext();
        if (r0 == 0) goto L_0x0095;
    L_0x0047:
        r0 = r6.next();
        r0 = (java.lang.String) r0;
        r1 = 0;
        r3 = new java.io.LineNumberReader;	 Catch:{ Throwable -> 0x007e, all -> 0x008b }
        r7 = new java.io.InputStreamReader;	 Catch:{ Throwable -> 0x007e, all -> 0x008b }
        r8 = new java.io.FileInputStream;	 Catch:{ Throwable -> 0x007e, all -> 0x008b }
        r8.<init>(r0);	 Catch:{ Throwable -> 0x007e, all -> 0x008b }
        r7.<init>(r8);	 Catch:{ Throwable -> 0x007e, all -> 0x008b }
        r3.<init>(r7);	 Catch:{ Throwable -> 0x007e, all -> 0x008b }
    L_0x005d:
        r1 = r3.readLine();	 Catch:{ Throwable -> 0x009f, all -> 0x009c }
        if (r1 == 0) goto L_0x00a2;
    L_0x0063:
        r7 = r1.toLowerCase();	 Catch:{ Throwable -> 0x009f, all -> 0x009c }
        r1 = r5.get(r0);	 Catch:{ Throwable -> 0x009f, all -> 0x009c }
        r1 = (java.lang.CharSequence) r1;	 Catch:{ Throwable -> 0x009f, all -> 0x009c }
        r1 = r7.contains(r1);	 Catch:{ Throwable -> 0x009f, all -> 0x009c }
        if (r1 == 0) goto L_0x005d;
    L_0x0073:
        r0 = 49;
    L_0x0075:
        r4.append(r0);
        r3.close();	 Catch:{ Throwable -> 0x007c }
        goto L_0x0041;
    L_0x007c:
        r0 = move-exception;
        goto L_0x0041;
    L_0x007e:
        r0 = move-exception;
        r0 = r1;
    L_0x0080:
        r4.append(r2);
        if (r0 == 0) goto L_0x0041;
    L_0x0085:
        r0.close();	 Catch:{ Throwable -> 0x0089 }
        goto L_0x0041;
    L_0x0089:
        r0 = move-exception;
        goto L_0x0041;
    L_0x008b:
        r0 = move-exception;
    L_0x008c:
        r4.append(r2);
        if (r1 == 0) goto L_0x0094;
    L_0x0091:
        r1.close();	 Catch:{ Throwable -> 0x009a }
    L_0x0094:
        throw r0;
    L_0x0095:
        r0 = r4.toString();
        return r0;
    L_0x009a:
        r1 = move-exception;
        goto L_0x0094;
    L_0x009c:
        r0 = move-exception;
        r1 = r3;
        goto L_0x008c;
    L_0x009f:
        r0 = move-exception;
        r0 = r3;
        goto L_0x0080;
    L_0x00a2:
        r0 = r2;
        goto L_0x0075;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p021b.C0692b.m1221t():java.lang.String");
    }

    /* renamed from: t */
    private static String m1222t(Context context) {
        if (C0692b.m1185a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return "";
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            String str;
            if (activeNetworkInfo.getType() == 0) {
                int subtype = activeNetworkInfo.getSubtype();
                if (subtype == 4 || subtype == 1 || subtype == 2 || subtype == 7 || subtype == 11) {
                    return "2G";
                }
                if (subtype == 3 || subtype == 5 || subtype == 6 || subtype == 8 || subtype == 9 || subtype == 10 || subtype == 12 || subtype == 14 || subtype == 15) {
                    return "3G";
                }
                if (subtype == 13) {
                    return "4G";
                }
                str = "UNKNOW";
            } else {
                str = null;
            }
            return str;
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: u */
    public static String m1223u() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("00" + ":");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("BRAND", "generic");
        linkedHashMap.put("BOARD", "unknown");
        linkedHashMap.put("DEVICE", "generic");
        linkedHashMap.put("HARDWARE", "goldfish");
        linkedHashMap.put("PRODUCT", "sdk");
        linkedHashMap.put("MODEL", "sdk");
        for (String str : linkedHashMap.keySet()) {
            try {
                String str2 = (String) Build.class.getField(str).get(null);
                String str3 = (String) linkedHashMap.get(str3);
                str2 = str2 != null ? str2.toLowerCase() : null;
                char c = (str2 == null || !str2.contains(str3)) ? '0' : '1';
                stringBuilder.append(c);
            } catch (Throwable th) {
                stringBuilder.append('0');
                throw th;
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: v */
    public static String m1224v() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("00" + ":");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("ro.hardware", "goldfish");
        linkedHashMap.put("ro.kernel.qemu", "1");
        linkedHashMap.put("ro.product.device", "generic");
        linkedHashMap.put("ro.product.model", "sdk");
        linkedHashMap.put("ro.product.brand", "generic");
        linkedHashMap.put("ro.product.name", "sdk");
        linkedHashMap.put("ro.build.fingerprint", "test-keys");
        linkedHashMap.put("ro.product.manufacturer", "unknow");
        for (String str : linkedHashMap.keySet()) {
            String str2 = (String) linkedHashMap.get(str);
            String str3 = C0689a.m1171b(str3, "");
            char c = (str3 == null || !str3.contains(str2)) ? '0' : '1';
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    /* renamed from: w */
    private static String m1225w() {
        try {
            ArrayList<NetworkInterface> list = Collections.list(NetworkInterface.getNetworkInterfaces());
            if (list != null) {
                for (NetworkInterface networkInterface : list) {
                    if (networkInterface != null && networkInterface.getName() != null && networkInterface.getName().equalsIgnoreCase("wlan0")) {
                        byte[] hardwareAddress = networkInterface.getHardwareAddress();
                        if (hardwareAddress == null) {
                            return "02:00:00:00:00:00";
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        int length = hardwareAddress.length;
                        for (int i = 0; i < length; i++) {
                            stringBuilder.append(String.format("%02X:", new Object[]{Integer.valueOf(hardwareAddress[i] & 255)}));
                        }
                        if (stringBuilder.length() > 0) {
                            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                        }
                        return stringBuilder.toString();
                    }
                }
            }
        } catch (Throwable th) {
        }
        return "02:00:00:00:00:00";
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0031 A:{SYNTHETIC, Splitter:B:21:0x0031} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0036 A:{SYNTHETIC, Splitter:B:24:0x0036} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0043 A:{SYNTHETIC, Splitter:B:30:0x0043} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0048 A:{SYNTHETIC, Splitter:B:33:0x0048} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0031 A:{SYNTHETIC, Splitter:B:21:0x0031} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0036 A:{SYNTHETIC, Splitter:B:24:0x0036} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0043 A:{SYNTHETIC, Splitter:B:30:0x0043} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0048 A:{SYNTHETIC, Splitter:B:33:0x0048} */
    /* renamed from: x */
    private static java.lang.String m1226x() {
        /*
        r0 = 0;
        r1 = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";
        r2 = new java.io.FileReader;	 Catch:{ Throwable -> 0x002d, all -> 0x003c }
        r2.<init>(r1);	 Catch:{ Throwable -> 0x002d, all -> 0x003c }
        r1 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x0061, all -> 0x005a }
        r3 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r1.<init>(r2, r3);	 Catch:{ Throwable -> 0x0061, all -> 0x005a }
        r0 = r1.readLine();	 Catch:{ Throwable -> 0x0064, all -> 0x005f }
        r3 = com.alipay.security.mobile.module.p019a.C0689a.m1169a(r0);	 Catch:{ Throwable -> 0x0064, all -> 0x005f }
        if (r3 != 0) goto L_0x0024;
    L_0x0019:
        r0 = r0.trim();	 Catch:{ Throwable -> 0x0064, all -> 0x005f }
        r1.close();	 Catch:{ Throwable -> 0x004c }
    L_0x0020:
        r2.close();	 Catch:{ Throwable -> 0x004e }
    L_0x0023:
        return r0;
    L_0x0024:
        r1.close();	 Catch:{ Throwable -> 0x0050 }
    L_0x0027:
        r2.close();	 Catch:{ Throwable -> 0x0052 }
    L_0x002a:
        r0 = "";
        goto L_0x0023;
    L_0x002d:
        r1 = move-exception;
        r1 = r0;
    L_0x002f:
        if (r0 == 0) goto L_0x0034;
    L_0x0031:
        r0.close();	 Catch:{ Throwable -> 0x0054 }
    L_0x0034:
        if (r1 == 0) goto L_0x002a;
    L_0x0036:
        r1.close();	 Catch:{ Throwable -> 0x003a }
        goto L_0x002a;
    L_0x003a:
        r0 = move-exception;
        goto L_0x002a;
    L_0x003c:
        r1 = move-exception;
        r2 = r0;
        r4 = r0;
        r0 = r1;
        r1 = r4;
    L_0x0041:
        if (r1 == 0) goto L_0x0046;
    L_0x0043:
        r1.close();	 Catch:{ Throwable -> 0x0056 }
    L_0x0046:
        if (r2 == 0) goto L_0x004b;
    L_0x0048:
        r2.close();	 Catch:{ Throwable -> 0x0058 }
    L_0x004b:
        throw r0;
    L_0x004c:
        r1 = move-exception;
        goto L_0x0020;
    L_0x004e:
        r1 = move-exception;
        goto L_0x0023;
    L_0x0050:
        r0 = move-exception;
        goto L_0x0027;
    L_0x0052:
        r0 = move-exception;
        goto L_0x002a;
    L_0x0054:
        r0 = move-exception;
        goto L_0x0034;
    L_0x0056:
        r1 = move-exception;
        goto L_0x0046;
    L_0x0058:
        r1 = move-exception;
        goto L_0x004b;
    L_0x005a:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x0041;
    L_0x005f:
        r0 = move-exception;
        goto L_0x0041;
    L_0x0061:
        r1 = move-exception;
        r1 = r2;
        goto L_0x002f;
    L_0x0064:
        r0 = move-exception;
        r0 = r1;
        r1 = r2;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p021b.C0692b.m1226x():java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0046 A:{SYNTHETIC, Splitter:B:25:0x0046} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x004b A:{SYNTHETIC, Splitter:B:28:0x004b} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0056 A:{SYNTHETIC, Splitter:B:34:0x0056} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x005b A:{SYNTHETIC, Splitter:B:37:0x005b} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0046 A:{SYNTHETIC, Splitter:B:25:0x0046} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x004b A:{SYNTHETIC, Splitter:B:28:0x004b} */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0056 A:{SYNTHETIC, Splitter:B:34:0x0056} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x005b A:{SYNTHETIC, Splitter:B:37:0x005b} */
    /* JADX WARNING: Missing block: B:18:?, code skipped:
            r3.close();
     */
    /* renamed from: y */
    private static java.lang.String m1227y() {
        /*
        r2 = 0;
        r6 = 1;
        r1 = "/proc/cpuinfo";
        r0 = "";
        r3 = new java.io.FileReader;	 Catch:{ Throwable -> 0x0042, all -> 0x0051 }
        r3.<init>(r1);	 Catch:{ Throwable -> 0x0042, all -> 0x0051 }
        r1 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x006e, all -> 0x0069 }
        r4 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r1.<init>(r3, r4);	 Catch:{ Throwable -> 0x006e, all -> 0x0069 }
    L_0x0012:
        r2 = r1.readLine();	 Catch:{ Throwable -> 0x0072, all -> 0x006c }
        if (r2 == 0) goto L_0x003b;
    L_0x0018:
        r4 = com.alipay.security.mobile.module.p019a.C0689a.m1169a(r2);	 Catch:{ Throwable -> 0x0072, all -> 0x006c }
        if (r4 != 0) goto L_0x0012;
    L_0x001e:
        r4 = ":";
        r2 = r2.split(r4);	 Catch:{ Throwable -> 0x0072, all -> 0x006c }
        if (r2 == 0) goto L_0x0012;
    L_0x0026:
        r4 = r2.length;	 Catch:{ Throwable -> 0x0072, all -> 0x006c }
        if (r4 <= r6) goto L_0x0012;
    L_0x0029:
        r4 = 0;
        r4 = r2[r4];	 Catch:{ Throwable -> 0x0072, all -> 0x006c }
        r5 = "BogoMIPS";
        r4 = r4.contains(r5);	 Catch:{ Throwable -> 0x0072, all -> 0x006c }
        if (r4 == 0) goto L_0x0012;
    L_0x0034:
        r4 = 1;
        r2 = r2[r4];	 Catch:{ Throwable -> 0x0072, all -> 0x006c }
        r0 = r2.trim();	 Catch:{ Throwable -> 0x0072, all -> 0x006c }
    L_0x003b:
        r3.close();	 Catch:{ Throwable -> 0x005f }
    L_0x003e:
        r1.close();	 Catch:{ Throwable -> 0x0061 }
    L_0x0041:
        return r0;
    L_0x0042:
        r1 = move-exception;
        r1 = r2;
    L_0x0044:
        if (r2 == 0) goto L_0x0049;
    L_0x0046:
        r2.close();	 Catch:{ Throwable -> 0x0063 }
    L_0x0049:
        if (r1 == 0) goto L_0x0041;
    L_0x004b:
        r1.close();	 Catch:{ Throwable -> 0x004f }
        goto L_0x0041;
    L_0x004f:
        r1 = move-exception;
        goto L_0x0041;
    L_0x0051:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
    L_0x0054:
        if (r3 == 0) goto L_0x0059;
    L_0x0056:
        r3.close();	 Catch:{ Throwable -> 0x0065 }
    L_0x0059:
        if (r1 == 0) goto L_0x005e;
    L_0x005b:
        r1.close();	 Catch:{ Throwable -> 0x0067 }
    L_0x005e:
        throw r0;
    L_0x005f:
        r2 = move-exception;
        goto L_0x003e;
    L_0x0061:
        r1 = move-exception;
        goto L_0x0041;
    L_0x0063:
        r2 = move-exception;
        goto L_0x0049;
    L_0x0065:
        r2 = move-exception;
        goto L_0x0059;
    L_0x0067:
        r1 = move-exception;
        goto L_0x005e;
    L_0x0069:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0054;
    L_0x006c:
        r0 = move-exception;
        goto L_0x0054;
    L_0x006e:
        r1 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x0044;
    L_0x0072:
        r2 = move-exception;
        r2 = r3;
        goto L_0x0044;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.p021b.C0692b.m1227y():java.lang.String");
    }

    /* renamed from: z */
    private static String m1228z() {
        String address;
        BluetoothAdapter bluetoothAdapter;
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null && !defaultAdapter.isEnabled()) {
                return "";
            }
            BluetoothAdapter bluetoothAdapter2 = defaultAdapter;
            address = defaultAdapter.getAddress();
            bluetoothAdapter = bluetoothAdapter2;
            if (address == null || address.endsWith("00:00:00:00:00")) {
                try {
                    address = C0692b.m1183a(bluetoothAdapter);
                } catch (Throwable th) {
                }
            }
            return address == null ? "" : address;
        } catch (Throwable th2) {
            address = "";
            bluetoothAdapter = null;
        }
    }

    /* renamed from: f */
    public final String mo8174f() {
        try {
            return String.valueOf(new File("/sys/devices/system/cpu/").listFiles(new C0693c(this)).length);
        } catch (Throwable th) {
            return "1";
        }
    }
}
