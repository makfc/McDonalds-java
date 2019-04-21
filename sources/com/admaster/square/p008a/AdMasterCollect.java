package com.admaster.square.p008a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.admaster.square.utils.C0493n;
import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.admaster.square.a.a */
public class AdMasterCollect {
    /* renamed from: a */
    private static final String f136a = AdMasterCollect.class.getSimpleName();
    /* renamed from: b */
    private static AdMasterCollect f137b = null;
    /* renamed from: c */
    private Context f138c;
    /* renamed from: d */
    private String f139d = null;

    private AdMasterCollect(Context context) {
        this.f138c = context;
    }

    /* renamed from: a */
    public static AdMasterCollect m247a(Context context) {
        if (context == null) {
            throw new NullPointerException("context can not be null!");
        }
        if (f137b == null) {
            f137b = new AdMasterCollect(context);
        }
        return f137b;
    }

    /* renamed from: a */
    private static void m248a(String str) {
    }

    @SuppressLint({"SimpleDateFormat"})
    /* renamed from: a */
    public String mo7718a() {
        String str = "unreachable";
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
            AdMasterCollect.m248a("mt:" + simpleDateFormat.format(Long.valueOf(elapsedRealtime)) + "  bt:" + elapsedRealtime);
            return String.valueOf(elapsedRealtime / 1000);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    /* renamed from: b */
    public String mo7719b() {
        String str = "unreachable";
        try {
            Display defaultDisplay = ((WindowManager) this.f138c.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            AdMasterCollect.m248a("sp`w:" + displayMetrics.widthPixels + "  h:" + displayMetrics.heightPixels);
            return new StringBuilder(String.valueOf(i)).append("x").append(i2).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    /* renamed from: i */
    private String m249i() {
        JSONArray jSONArray = new JSONArray();
        try {
            List<Sensor> sensorList = ((SensorManager) this.f138c.getSystemService("sensor")).getSensorList(-1);
            if (sensorList != null) {
                for (Sensor sensor : sensorList) {
                    Object obj;
                    Object obj2;
                    if (sensor.getName() == null) {
                        obj = "";
                    } else {
                        String obj3 = URLEncoder.encode(sensor.getName(), Utf8Charset.NAME);
                    }
                    if (sensor.getVendor() == null) {
                        obj2 = "";
                    } else {
                        obj2 = URLEncoder.encode(sensor.getVendor(), Utf8Charset.NAME);
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("stype", sensor.getType());
                    jSONObject.put("sname", obj3);
                    jSONObject.put("svendor", obj2);
                    jSONArray.put(jSONObject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jSONArray instanceof JSONArray) {
            return JSONArrayInstrumentation.toString(jSONArray);
        }
        return jSONArray.toString();
    }

    /* renamed from: c */
    public String mo7720c() {
        if (this.f139d == null) {
            this.f139d = m249i();
        }
        return this.f139d;
    }

    /* renamed from: d */
    public String mo7721d() {
        return m250j();
    }

    /* renamed from: j */
    private String m250j() {
        JSONArray jSONArray = new JSONArray();
        try {
            Object obj;
            WifiManager wifiManager = (WifiManager) this.f138c.getSystemService("wifi");
            List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            String str = "";
            int networkId;
            if (connectionInfo != null) {
                if (connectionInfo.getSSID() == null) {
                    str = "unknown";
                } else {
                    str = connectionInfo.getSSID().replace("\"", "");
                }
                networkId = connectionInfo.getNetworkId();
                obj = str;
            } else {
                networkId = -1;
                String obj2 = str;
            }
            if (configuredNetworks != null && configuredNetworks.size() > 0) {
                for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                    JSONObject jSONObject = new JSONObject();
                    if (wifiConfiguration.SSID == null) {
                        str = "";
                    } else {
                        str = wifiConfiguration.SSID.replace("\"", "");
                    }
                    int i = wifiConfiguration.networkId;
                    if (!str.equals(obj2) || i != networkId) {
                        String str2;
                        if (wifiConfiguration.BSSID == null) {
                            str2 = "";
                        } else {
                            str2 = wifiConfiguration.BSSID.toString();
                        }
                        jSONObject.put("ssid", URLEncoder.encode(str, Utf8Charset.NAME));
                        jSONObject.put("bssid", URLEncoder.encode(str2, Utf8Charset.NAME));
                        jSONArray.put(jSONObject);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jSONArray instanceof JSONArray) {
            return JSONArrayInstrumentation.toString(jSONArray);
        }
        return jSONArray.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0053 A:{SYNTHETIC, Splitter:B:31:0x0053} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0053 A:{SYNTHETIC, Splitter:B:31:0x0053} */
    /* renamed from: e */
    public java.lang.String mo7722e() {
        /*
        r7 = this;
        r2 = 0;
        r4 = "unreachable";
        r0 = java.lang.Runtime.getRuntime();	 Catch:{ Exception -> 0x0031, all -> 0x004a }
        r1 = "cat /sys/class/net/wlan0/address";
        r3 = r0.exec(r1);	 Catch:{ Exception -> 0x0031, all -> 0x004a }
        if (r3 == 0) goto L_0x006f;
    L_0x0010:
        r1 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0066 }
        r0 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x0066 }
        r5 = r3.getInputStream();	 Catch:{ Exception -> 0x0066 }
        r6 = "GBK";
        r0.<init>(r5, r6);	 Catch:{ Exception -> 0x0066 }
        r1.<init>(r0);	 Catch:{ Exception -> 0x0066 }
        r0 = r1.readLine();	 Catch:{ Exception -> 0x0069, all -> 0x0063 }
        if (r0 == 0) goto L_0x006d;
    L_0x0026:
        if (r3 == 0) goto L_0x002b;
    L_0x0028:
        r3.destroy();
    L_0x002b:
        if (r1 == 0) goto L_0x0030;
    L_0x002d:
        r1.close();	 Catch:{ Exception -> 0x005c }
    L_0x0030:
        return r0;
    L_0x0031:
        r0 = move-exception;
        r1 = r0;
        r3 = r2;
    L_0x0034:
        r0 = "unreachable";
        r1.printStackTrace();	 Catch:{ all -> 0x0061 }
        if (r3 == 0) goto L_0x003f;
    L_0x003c:
        r3.destroy();
    L_0x003f:
        if (r2 == 0) goto L_0x0030;
    L_0x0041:
        r2.close();	 Catch:{ Exception -> 0x0045 }
        goto L_0x0030;
    L_0x0045:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0030;
    L_0x004a:
        r0 = move-exception;
        r3 = r2;
    L_0x004c:
        if (r3 == 0) goto L_0x0051;
    L_0x004e:
        r3.destroy();
    L_0x0051:
        if (r2 == 0) goto L_0x0056;
    L_0x0053:
        r2.close();	 Catch:{ Exception -> 0x0057 }
    L_0x0056:
        throw r0;
    L_0x0057:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0056;
    L_0x005c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0030;
    L_0x0061:
        r0 = move-exception;
        goto L_0x004c;
    L_0x0063:
        r0 = move-exception;
        r2 = r1;
        goto L_0x004c;
    L_0x0066:
        r0 = move-exception;
        r1 = r0;
        goto L_0x0034;
    L_0x0069:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        goto L_0x0034;
    L_0x006d:
        r0 = r4;
        goto L_0x0026;
    L_0x006f:
        r1 = r2;
        r0 = r4;
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.admaster.square.p008a.AdMasterCollect.mo7722e():java.lang.String");
    }

    /* renamed from: f */
    public String mo7723f() {
        return m251k();
    }

    /* renamed from: k */
    private String m251k() {
        JSONArray jSONArray = new JSONArray();
        try {
            List<PackageInfo> installedPackages = this.f138c.getPackageManager().getInstalledPackages(0);
            if (installedPackages != null) {
                for (PackageInfo packageInfo : installedPackages) {
                    if ((packageInfo.applicationInfo.flags & 1) > 0) {
                        Object obj;
                        JSONObject jSONObject = new JSONObject();
                        if (packageInfo.packageName == null) {
                            obj = "";
                        } else {
                            obj = URLEncoder.encode(packageInfo.packageName, Utf8Charset.NAME);
                        }
                        jSONObject.put("pkname", obj);
                        jSONObject.put("firstime", packageInfo.firstInstallTime);
                        jSONArray.put(jSONObject);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jSONArray instanceof JSONArray) {
            return JSONArrayInstrumentation.toString(jSONArray);
        }
        return jSONArray.toString();
    }

    /* renamed from: g */
    public String mo7724g() {
        JSONObject jSONObject = new JSONObject();
        try {
            PackageInfo packageInfo = this.f138c.getPackageManager().getPackageInfo("android", 0);
            if (packageInfo != null) {
                Object obj;
                if (packageInfo.packageName == null) {
                    obj = "";
                } else {
                    obj = URLEncoder.encode(packageInfo.packageName, Utf8Charset.NAME);
                }
                jSONObject.put("flag", packageInfo.applicationInfo.flags);
                jSONObject.put("pkname", obj);
                jSONObject.put("firstime", packageInfo.firstInstallTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jSONObject instanceof JSONObject) {
            return JSONObjectInstrumentation.toString(jSONObject);
        }
        return jSONObject.toString();
    }

    /* renamed from: l */
    private long m252l() {
        long j = -1;
        try {
            List<PackageInfo> installedPackages = this.f138c.getPackageManager().getInstalledPackages(0);
            HashMap hashMap = new HashMap();
            if (installedPackages != null) {
                for (PackageInfo packageInfo : installedPackages) {
                    if ((packageInfo.applicationInfo.flags & 1) > 0) {
                        long j2 = packageInfo.firstInstallTime;
                        if (hashMap.containsKey(Long.valueOf(j2))) {
                            hashMap.put(Long.valueOf(j2), Integer.valueOf(((Integer) hashMap.get(Long.valueOf(j2))).intValue() + 1));
                        } else {
                            hashMap.put(Long.valueOf(j2), Integer.valueOf(1));
                        }
                    }
                }
            }
            int i = -1;
            for (Long l : hashMap.keySet()) {
                int intValue = ((Integer) hashMap.get(l)).intValue();
                if (intValue > i) {
                    j = l.longValue();
                    i = intValue;
                } else if (intValue == i && l.longValue() < j) {
                    j = l.longValue();
                }
            }
            return j;
        } catch (Exception e) {
            Exception exception = e;
            long j3 = -1;
            exception.printStackTrace();
            return j3;
        }
    }

    /* renamed from: h */
    public String mo7725h() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            String d = mo7721d();
            stringBuffer.append(String.valueOf(JSONArrayInstrumentation.init(d).length()));
            stringBuffer.append("|");
            stringBuffer.append(C0493n.m447a(d));
            stringBuffer.append("|");
            stringBuffer.append(String.valueOf(m252l()));
            stringBuffer.append("|");
            stringBuffer.append(C0493n.m447a(mo7720c()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
