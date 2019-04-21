package com.baidu.android.pushservice.util;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.amap.api.location.LocationManagerProxy;
import com.baidu.android.pushservice.C1328a;
import com.baidu.android.pushservice.PushSettings;
import com.baidu.android.pushservice.p031c.C1332b;
import com.baidu.android.pushservice.p031c.C1339h;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p039k.C1471e;
import com.baidu.loctp.str.BDLocManager;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.util.i */
public class C1545i {
    /* renamed from: a */
    private static int f5398a = 4;

    /* renamed from: a */
    public static String m6928a() {
        try {
            String str;
            CharSequence str2;
            CharSequence charSequence = "";
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        str2 = inetAddress.getHostAddress().toString();
                        break;
                    }
                }
                str2 = charSequence;
                Object charSequence2 = str2;
            }
            if (TextUtils.isEmpty(charSequence2)) {
                return "";
            }
            int indexOf = charSequence2.indexOf(37);
            if (indexOf != -1) {
                str2 = charSequence2.substring(0, indexOf);
            } else {
                str2 = charSequence2;
            }
            return str2 == null ? "" : str2;
        } catch (SocketException e) {
            C1425a.m6444e("LbsUtils", "error : " + e.toString());
            return "";
        }
    }

    /* renamed from: a */
    public static String m6929a(Context context) {
        return context == null ? null : C1550n.m6955a(context, "com.baidu.android.pushservice.lbscache");
    }

    /* renamed from: a */
    public static String m6930a(Context context, JSONObject jSONObject) {
        if (context == null || jSONObject == null) {
            return null;
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (jSONObject.has("cityCode")) {
                jSONObject2.put("city_code", jSONObject.optString("cityCode"));
            }
            if (jSONObject.has(LocationManagerProxy.KEY_LOCATION_CHANGED)) {
                JSONObject jSONObject3 = jSONObject.getJSONObject(LocationManagerProxy.KEY_LOCATION_CHANGED);
                JSONObject jSONObject4 = new JSONObject();
                if (jSONObject3 != null) {
                    jSONObject4.put("latitude", jSONObject3.getString("lat"));
                    jSONObject4.put("longitude", jSONObject3.getString("lng"));
                }
                if (jSONObject.has("accuracy")) {
                    jSONObject4.put("accuracy", jSONObject.optString("accuracy"));
                }
                jSONObject2.put(LocationManagerProxy.KEY_LOCATION_CHANGED, jSONObject4);
            }
            if (jSONObject2 != null) {
                C1550n.m6958a(context, "com.baidu.android.pushservice.lbscache", !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : JSONObjectInstrumentation.toString(jSONObject2));
            }
            return !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : JSONObjectInstrumentation.toString(jSONObject2);
        } catch (JSONException e) {
            C1425a.m6444e("LbsUtils", "error " + e.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    public static String m6931a(Context context, boolean z) {
        String a = PushSettings.m5874a(context);
        if (!TextUtils.isEmpty(a)) {
            String d = C1545i.m6935d(context);
            if (!TextUtils.isEmpty(d)) {
                if (!z && C1545i.m6933b(context) && !TextUtils.isEmpty(C1545i.m6929a(context))) {
                    return null;
                }
                String a2 = C1545i.m6928a();
                C1332b a3 = C1332b.m6020a(context);
                ArrayList arrayList = new ArrayList();
                JSONArray jSONArray = new JSONArray();
                JSONObject jSONObject = new JSONObject();
                ArrayList arrayList2 = (ArrayList) a3.f4721a.clone();
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= arrayList2.size()) {
                        break;
                    }
                    if (!TextUtils.isEmpty(((C1339h) arrayList2.get(i2)).mo13584a())) {
                        JSONObject jSONObject2 = new JSONObject();
                        try {
                            jSONObject2.put("userid", C1578v.m7069a(((C1339h) arrayList2.get(i2)).f4739e));
                            jSONObject2.put("appid", ((C1339h) arrayList2.get(i2)).mo13584a());
                        } catch (Exception e) {
                            C1425a.m6444e("LbsUtils", "error " + e.getMessage());
                        }
                        jSONArray.put(jSONObject2);
                    }
                    i = i2 + 1;
                }
                if (jSONArray.length() > 0) {
                    try {
                        jSONObject.put("channelid", a);
                        jSONObject.put("cuid", C1471e.m6687a(context));
                        jSONObject.put("nettype", C1578v.m7145t(context.getApplicationContext()));
                        jSONObject.put("clients", jSONArray);
                        jSONObject.put("apinfo", d);
                        jSONObject.put("cip", a2);
                        jSONObject.put("model", Build.MODEL);
                        jSONObject.put("version", VERSION.RELEASE);
                        jSONObject.put("sdkversion", C1328a.m6003a());
                    } catch (JSONException e2) {
                        C1425a.m6444e("LbsUtils", "error " + e2.getMessage());
                    }
                    return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static void m6932a(Context context, long j) {
        C1550n.m6957a(context, "com.baidu.pushservice.clt", j);
    }

    /* renamed from: b */
    public static boolean m6933b(Context context) {
        if (context == null) {
            return false;
        }
        String e = C1545i.m6936e(context);
        String a = C1550n.m6955a(context, "com.baidu.android.pushservice.lac");
        if (TextUtils.isEmpty(e)) {
            return false;
        }
        if (TextUtils.equals(e, a)) {
            C1545i.m6932a(context, System.currentTimeMillis());
            return true;
        }
        C1550n.m6958a(context, "com.baidu.android.pushservice.lac", e);
        return false;
    }

    /* renamed from: c */
    public static long m6934c(Context context) {
        if (context != null) {
            return C1550n.m6961b(context, "com.baidu.pushservice.clt");
        }
        C1425a.m6444e("LbsUtils", "getLastSendLbsTime mContext == null");
        return 0;
    }

    /* renamed from: d */
    public static String m6935d(Context context) {
        return new BDLocManager(context.getApplicationContext()).getLocString(f5398a);
    }

    /* renamed from: e */
    private static String m6936e(Context context) {
        String bssid = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getBSSID();
        if (!TextUtils.isEmpty(bssid)) {
            return bssid;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        CellLocation cellLocation = telephonyManager.getCellLocation();
        if (cellLocation instanceof GsmCellLocation) {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
            return gsmCellLocation.getCid() + "" + gsmCellLocation.getLac();
        } else if (!(cellLocation instanceof CdmaCellLocation)) {
            return "";
        } else {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) telephonyManager.getCellLocation();
            int baseStationId = cdmaCellLocation.getBaseStationId();
            int networkId = cdmaCellLocation.getNetworkId();
            return baseStationId + "" + networkId + "" + cdmaCellLocation.getSystemId();
        }
    }
}
