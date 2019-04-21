package com.amap.api.mapcore.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.System;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.io.File;
import java.util.List;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* compiled from: DeviceInfo */
/* renamed from: com.amap.api.mapcore.util.dq */
public class C0820dq {
    /* renamed from: a */
    private static String f1802a = "";
    /* renamed from: b */
    private static boolean f1803b = false;
    /* renamed from: c */
    private static String f1804c = "";
    /* renamed from: d */
    private static String f1805d = "";
    /* renamed from: e */
    private static String f1806e = "";
    /* renamed from: f */
    private static String f1807f = "";

    /* compiled from: DeviceInfo */
    /* renamed from: com.amap.api.mapcore.util.dq$a */
    static class C0819a extends DefaultHandler {
        C0819a() {
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            if (str2.equals("string") && "UTDID".equals(attributes.getValue("name"))) {
                C0820dq.f1803b = true;
            }
        }

        public void characters(char[] cArr, int i, int i2) throws SAXException {
            if (C0820dq.f1803b) {
                C0820dq.f1802a = new String(cArr, i, i2);
            }
        }

        public void endElement(String str, String str2, String str3) throws SAXException {
            C0820dq.f1803b = false;
        }
    }

    /* renamed from: a */
    static String m2421a(Context context) {
        try {
            return C0820dq.m2447u(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* renamed from: b */
    static String m2427b(Context context) {
        String str = "";
        try {
            return C0820dq.m2450x(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return str;
        }
    }

    /* renamed from: c */
    static int m2429c(Context context) {
        int i = -1;
        try {
            return C0820dq.m2451y(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return i;
        }
    }

    /* renamed from: d */
    static int m2430d(Context context) {
        int i = -1;
        try {
            return C0820dq.m2448v(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return i;
        }
    }

    /* renamed from: e */
    static String m2431e(Context context) {
        try {
            return C0820dq.m2446t(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public static void m2424a() {
        try {
            if (VERSION.SDK_INT > 14) {
                TrafficStats.class.getDeclaredMethod("setThreadStatsTag", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(40964)});
            }
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DeviceInfo", "setTraficTag");
        }
    }

    /* renamed from: f */
    public static String m2432f(Context context) {
        try {
            if (f1802a != null && !"".equals(f1802a)) {
                return f1802a;
            }
            if (C0820dq.m2425a(context, "android.permission.WRITE_SETTINGS")) {
                f1802a = System.getString(context.getContentResolver(), "mqBRboGZkQPcAkyk");
            }
            if (!(f1802a == null || "".equals(f1802a))) {
                return f1802a;
            }
            try {
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.UTSystemConfig/Global/Alvin2.xml");
                    if (file.exists()) {
                        SAXParserFactory.newInstance().newSAXParser().parse(file, new C0819a());
                    }
                }
            } catch (Throwable th) {
                BasicLogHandler.m2542a(th, "DeviceInfo", "getUTDID");
            }
            return f1802a;
        } catch (Throwable th2) {
            BasicLogHandler.m2542a(th2, "DeviceInfo", "getUTDID");
        }
    }

    /* renamed from: a */
    private static boolean m2425a(Context context, String str) {
        return context != null && context.checkCallingOrSelfPermission(str) == 0;
    }

    /* renamed from: g */
    static String m2433g(Context context) {
        if (context != null) {
            try {
                if (C0820dq.m2425a(context, "android.permission.ACCESS_WIFI_STATE")) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                    if (wifiManager.isWifiEnabled()) {
                        return wifiManager.getConnectionInfo().getBSSID();
                    }
                    return null;
                }
            } catch (Throwable th) {
                BasicLogHandler.m2542a(th, "DeviceInfo", "getWifiMacs");
            }
        }
        return null;
    }

    /* renamed from: h */
    static String m2434h(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        if (context != null) {
            try {
                if (C0820dq.m2425a(context, "android.permission.ACCESS_WIFI_STATE")) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                    if (wifiManager.isWifiEnabled()) {
                        List scanResults = wifiManager.getScanResults();
                        if (scanResults == null || scanResults.size() == 0) {
                            return stringBuilder.toString();
                        }
                        List a = C0820dq.m2423a(scanResults);
                        Object obj = 1;
                        int i = 0;
                        while (i < a.size() && i < 7) {
                            ScanResult scanResult = (ScanResult) a.get(i);
                            if (obj != null) {
                                obj = null;
                            } else {
                                stringBuilder.append(";");
                            }
                            stringBuilder.append(scanResult.BSSID);
                            i++;
                        }
                    }
                    return stringBuilder.toString();
                }
            } catch (Throwable th) {
                BasicLogHandler.m2542a(th, "DeviceInfo", "getWifiMacs");
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: i */
    public static String m2435i(Context context) {
        try {
            if (f1804c != null && !"".equals(f1804c)) {
                return f1804c;
            }
            if (!C0820dq.m2425a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return f1804c;
            }
            f1804c = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            return f1804c;
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DeviceInfo", "getDeviceMac");
        }
    }

    /* renamed from: j */
    static String[] m2436j(Context context) {
        try {
            if (C0820dq.m2425a(context, "android.permission.READ_PHONE_STATE") && C0820dq.m2425a(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                CellLocation cellLocation = ((TelephonyManager) context.getSystemService("phone")).getCellLocation();
                int cid;
                int lac;
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    cid = gsmCellLocation.getCid();
                    lac = gsmCellLocation.getLac();
                    return new String[]{lac + "||" + cid, "gsm"};
                }
                if (cellLocation instanceof CdmaCellLocation) {
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                    cid = cdmaCellLocation.getSystemId();
                    lac = cdmaCellLocation.getNetworkId();
                    int baseStationId = cdmaCellLocation.getBaseStationId();
                    if (cid < 0 || lac < 0 || baseStationId < 0) {
                    }
                    return new String[]{cid + "||" + lac + "||" + baseStationId, "cdma"};
                }
                return new String[]{"", ""};
            }
            return new String[]{"", ""};
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DeviceInfo", "cellInfo");
        }
    }

    /* renamed from: k */
    static String m2437k(Context context) {
        String str = "";
        try {
            if (!C0820dq.m2425a(context, "android.permission.READ_PHONE_STATE")) {
                return str;
            }
            String networkOperator = C0820dq.m2452z(context).getNetworkOperator();
            if (!TextUtils.isEmpty(networkOperator) || networkOperator.length() >= 3) {
                return networkOperator.substring(3);
            }
            return str;
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DeviceInfo", "getMNC");
            return str;
        }
    }

    /* renamed from: l */
    public static int m2438l(Context context) {
        int i = -1;
        try {
            return C0820dq.m2451y(context);
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DeviceInfo", "getNetWorkType");
            return i;
        }
    }

    /* renamed from: m */
    public static int m2439m(Context context) {
        int i = -1;
        try {
            return C0820dq.m2448v(context);
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DeviceInfo", "getActiveNetWorkType");
            return i;
        }
    }

    /* renamed from: n */
    public static NetworkInfo m2440n(Context context) {
        if (!C0820dq.m2425a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return null;
        }
        ConnectivityManager w = C0820dq.m2449w(context);
        if (w != null) {
            return w.getActiveNetworkInfo();
        }
        return null;
    }

    /* renamed from: o */
    static String m2441o(Context context) {
        try {
            NetworkInfo n = C0820dq.m2440n(context);
            if (n == null) {
                return null;
            }
            return n.getExtraInfo();
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DeviceInfo", "getNetworkExtraInfo");
            return null;
        }
    }

    /* renamed from: p */
    static String m2442p(Context context) {
        try {
            if (f1805d != null && !"".equals(f1805d)) {
                return f1805d;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            f1805d = i2 > i ? i + "*" + i2 : i2 + "*" + i;
            return f1805d;
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DeviceInfo", "getReslution");
        }
    }

    /* renamed from: q */
    public static String m2443q(Context context) {
        try {
            if (f1806e != null && !"".equals(f1806e)) {
                return f1806e;
            }
            if (!C0820dq.m2425a(context, "android.permission.READ_PHONE_STATE")) {
                return f1806e;
            }
            f1806e = C0820dq.m2452z(context).getDeviceId();
            if (f1806e == null) {
                f1806e = "";
            }
            return f1806e;
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DeviceInfo", "getDeviceID");
        }
    }

    /* renamed from: r */
    public static String m2444r(Context context) {
        String str = "";
        try {
            return C0820dq.m2446t(context);
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DeviceInfo", "getSubscriberId");
            return str;
        }
    }

    /* renamed from: s */
    static String m2445s(Context context) {
        try {
            return C0820dq.m2447u(context);
        } catch (Throwable th) {
            BasicLogHandler.m2542a(th, "DeviceInfo", "getNetworkOperatorName");
            return "";
        }
    }

    /* renamed from: t */
    private static String m2446t(Context context) {
        if (f1807f != null && !"".equals(f1807f)) {
            return f1807f;
        }
        if (!C0820dq.m2425a(context, "android.permission.READ_PHONE_STATE")) {
            return f1807f;
        }
        f1807f = C0820dq.m2452z(context).getSubscriberId();
        if (f1807f == null) {
            f1807f = "";
        }
        return f1807f;
    }

    /* renamed from: u */
    private static String m2447u(Context context) {
        if (!C0820dq.m2425a(context, "android.permission.READ_PHONE_STATE")) {
            return null;
        }
        String simOperatorName = C0820dq.m2452z(context).getSimOperatorName();
        if (TextUtils.isEmpty(simOperatorName)) {
            return C0820dq.m2452z(context).getNetworkOperatorName();
        }
        return simOperatorName;
    }

    /* renamed from: v */
    private static int m2448v(Context context) {
        if (context == null || !C0820dq.m2425a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return -1;
        }
        ConnectivityManager w = C0820dq.m2449w(context);
        if (w == null) {
            return -1;
        }
        NetworkInfo activeNetworkInfo = w.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.getType();
        }
        return -1;
    }

    /* renamed from: w */
    private static ConnectivityManager m2449w(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    /* renamed from: x */
    private static String m2450x(Context context) {
        String str = "";
        String r = C0820dq.m2444r(context);
        if (r == null || r.length() < 5) {
            return str;
        }
        return r.substring(3, 5);
    }

    /* renamed from: y */
    private static int m2451y(Context context) {
        if (C0820dq.m2425a(context, "android.permission.READ_PHONE_STATE")) {
            return C0820dq.m2452z(context).getNetworkType();
        }
        return -1;
    }

    /* renamed from: z */
    private static TelephonyManager m2452z(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    /* renamed from: a */
    private static List<ScanResult> m2423a(List<ScanResult> list) {
        int size = list.size();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= size - 1) {
                return list;
            }
            i = 1;
            while (true) {
                int i3 = i;
                if (i3 >= size - i2) {
                    break;
                }
                if (((ScanResult) list.get(i3 - 1)).level > ((ScanResult) list.get(i3)).level) {
                    ScanResult scanResult = (ScanResult) list.get(i3 - 1);
                    list.set(i3 - 1, list.get(i3));
                    list.set(i3, scanResult);
                }
                i = i3 + 1;
            }
            i = i2 + 1;
        }
    }
}
