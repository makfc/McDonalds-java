package com.amap.api.mapcore2d;

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
/* renamed from: com.amap.api.mapcore2d.cq */
public class C0968cq {
    /* renamed from: a */
    private static String f2758a = "";
    /* renamed from: b */
    private static boolean f2759b = false;
    /* renamed from: c */
    private static String f2760c = "";
    /* renamed from: d */
    private static String f2761d = "";
    /* renamed from: e */
    private static String f2762e = "";
    /* renamed from: f */
    private static String f2763f = "";

    /* compiled from: DeviceInfo */
    /* renamed from: com.amap.api.mapcore2d.cq$a */
    static class C0967a extends DefaultHandler {
        C0967a() {
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            if (str2.equals("string") && "UTDID".equals(attributes.getValue("name"))) {
                C0968cq.f2759b = true;
            }
        }

        public void characters(char[] cArr, int i, int i2) throws SAXException {
            if (C0968cq.f2759b) {
                C0968cq.f2758a = new String(cArr, i, i2);
            }
        }

        public void endElement(String str, String str2, String str3) throws SAXException {
            C0968cq.f2759b = false;
        }
    }

    /* renamed from: a */
    static String m3952a(Context context) {
        try {
            return C0968cq.m3978u(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* renamed from: b */
    static String m3958b(Context context) {
        String str = "";
        try {
            return C0968cq.m3981x(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return str;
        }
    }

    /* renamed from: c */
    static int m3960c(Context context) {
        int i = -1;
        try {
            return C0968cq.m3982y(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return i;
        }
    }

    /* renamed from: d */
    static int m3961d(Context context) {
        int i = -1;
        try {
            return C0968cq.m3979v(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return i;
        }
    }

    /* renamed from: e */
    static String m3962e(Context context) {
        try {
            return C0968cq.m3977t(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public static void m3955a() {
        try {
            if (VERSION.SDK_INT > 14) {
                TrafficStats.class.getDeclaredMethod("setThreadStatsTag", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(40964)});
            }
        } catch (Throwable th) {
            C0982da.m4076a(th, "DeviceInfo", "setTraficTag");
        }
    }

    /* renamed from: f */
    public static String m3963f(Context context) {
        try {
            if (f2758a != null && !"".equals(f2758a)) {
                return f2758a;
            }
            if (C0968cq.m3956a(context, "android.permission.WRITE_SETTINGS")) {
                f2758a = System.getString(context.getContentResolver(), "mqBRboGZkQPcAkyk");
            }
            if (!(f2758a == null || "".equals(f2758a))) {
                return f2758a;
            }
            try {
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.UTSystemConfig/Global/Alvin2.xml");
                    if (file.exists()) {
                        SAXParserFactory.newInstance().newSAXParser().parse(file, new C0967a());
                    }
                }
            } catch (Throwable th) {
                C0982da.m4076a(th, "DeviceInfo", "getUTDID");
            }
            return f2758a;
        } catch (Throwable th2) {
            C0982da.m4076a(th2, "DeviceInfo", "getUTDID");
        }
    }

    /* renamed from: a */
    private static boolean m3956a(Context context, String str) {
        return context != null && context.checkCallingOrSelfPermission(str) == 0;
    }

    /* renamed from: g */
    static String m3964g(Context context) {
        if (context != null) {
            try {
                if (C0968cq.m3956a(context, "android.permission.ACCESS_WIFI_STATE")) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                    if (wifiManager.isWifiEnabled()) {
                        return wifiManager.getConnectionInfo().getBSSID();
                    }
                    return null;
                }
            } catch (Throwable th) {
                C0982da.m4076a(th, "DeviceInfo", "getWifiMacs");
            }
        }
        return null;
    }

    /* renamed from: h */
    static String m3965h(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        if (context != null) {
            try {
                if (C0968cq.m3956a(context, "android.permission.ACCESS_WIFI_STATE")) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                    if (wifiManager.isWifiEnabled()) {
                        List scanResults = wifiManager.getScanResults();
                        if (scanResults == null || scanResults.size() == 0) {
                            return stringBuilder.toString();
                        }
                        List a = C0968cq.m3954a(scanResults);
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
                C0982da.m4076a(th, "DeviceInfo", "getWifiMacs");
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: i */
    public static String m3966i(Context context) {
        try {
            if (f2760c != null && !"".equals(f2760c)) {
                return f2760c;
            }
            if (!C0968cq.m3956a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return f2760c;
            }
            f2760c = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            return f2760c;
        } catch (Throwable th) {
            C0982da.m4076a(th, "DeviceInfo", "getDeviceMac");
        }
    }

    /* renamed from: j */
    static String[] m3967j(Context context) {
        try {
            if (C0968cq.m3956a(context, "android.permission.READ_PHONE_STATE") && C0968cq.m3956a(context, "android.permission.ACCESS_COARSE_LOCATION")) {
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
            C0982da.m4076a(th, "DeviceInfo", "cellInfo");
        }
    }

    /* renamed from: k */
    static String m3968k(Context context) {
        String str = "";
        try {
            if (!C0968cq.m3956a(context, "android.permission.READ_PHONE_STATE")) {
                return str;
            }
            String networkOperator = C0968cq.m3983z(context).getNetworkOperator();
            if (!TextUtils.isEmpty(networkOperator) || networkOperator.length() >= 3) {
                return networkOperator.substring(3);
            }
            return str;
        } catch (Throwable th) {
            C0982da.m4076a(th, "DeviceInfo", "getMNC");
            return str;
        }
    }

    /* renamed from: l */
    public static int m3969l(Context context) {
        int i = -1;
        try {
            return C0968cq.m3982y(context);
        } catch (Throwable th) {
            C0982da.m4076a(th, "DeviceInfo", "getNetWorkType");
            return i;
        }
    }

    /* renamed from: m */
    public static int m3970m(Context context) {
        int i = -1;
        try {
            return C0968cq.m3979v(context);
        } catch (Throwable th) {
            C0982da.m4076a(th, "DeviceInfo", "getActiveNetWorkType");
            return i;
        }
    }

    /* renamed from: n */
    public static NetworkInfo m3971n(Context context) {
        if (!C0968cq.m3956a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return null;
        }
        ConnectivityManager w = C0968cq.m3980w(context);
        if (w != null) {
            return w.getActiveNetworkInfo();
        }
        return null;
    }

    /* renamed from: o */
    static String m3972o(Context context) {
        try {
            NetworkInfo n = C0968cq.m3971n(context);
            if (n == null) {
                return null;
            }
            return n.getExtraInfo();
        } catch (Throwable th) {
            C0982da.m4076a(th, "DeviceInfo", "getNetworkExtraInfo");
            return null;
        }
    }

    /* renamed from: p */
    static String m3973p(Context context) {
        try {
            if (f2761d != null && !"".equals(f2761d)) {
                return f2761d;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            f2761d = i2 > i ? i + "*" + i2 : i2 + "*" + i;
            return f2761d;
        } catch (Throwable th) {
            C0982da.m4076a(th, "DeviceInfo", "getReslution");
        }
    }

    /* renamed from: q */
    public static String m3974q(Context context) {
        try {
            if (f2762e != null && !"".equals(f2762e)) {
                return f2762e;
            }
            if (!C0968cq.m3956a(context, "android.permission.READ_PHONE_STATE")) {
                return f2762e;
            }
            f2762e = C0968cq.m3983z(context).getDeviceId();
            if (f2762e == null) {
                f2762e = "";
            }
            return f2762e;
        } catch (Throwable th) {
            C0982da.m4076a(th, "DeviceInfo", "getDeviceID");
        }
    }

    /* renamed from: r */
    public static String m3975r(Context context) {
        String str = "";
        try {
            return C0968cq.m3977t(context);
        } catch (Throwable th) {
            C0982da.m4076a(th, "DeviceInfo", "getSubscriberId");
            return str;
        }
    }

    /* renamed from: s */
    static String m3976s(Context context) {
        try {
            return C0968cq.m3978u(context);
        } catch (Throwable th) {
            C0982da.m4076a(th, "DeviceInfo", "getNetworkOperatorName");
            return "";
        }
    }

    /* renamed from: t */
    private static String m3977t(Context context) {
        if (f2763f != null && !"".equals(f2763f)) {
            return f2763f;
        }
        if (!C0968cq.m3956a(context, "android.permission.READ_PHONE_STATE")) {
            return f2763f;
        }
        f2763f = C0968cq.m3983z(context).getSubscriberId();
        if (f2763f == null) {
            f2763f = "";
        }
        return f2763f;
    }

    /* renamed from: u */
    private static String m3978u(Context context) {
        if (!C0968cq.m3956a(context, "android.permission.READ_PHONE_STATE")) {
            return null;
        }
        String simOperatorName = C0968cq.m3983z(context).getSimOperatorName();
        if (TextUtils.isEmpty(simOperatorName)) {
            return C0968cq.m3983z(context).getNetworkOperatorName();
        }
        return simOperatorName;
    }

    /* renamed from: v */
    private static int m3979v(Context context) {
        if (context == null || !C0968cq.m3956a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return -1;
        }
        ConnectivityManager w = C0968cq.m3980w(context);
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
    private static ConnectivityManager m3980w(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    /* renamed from: x */
    private static String m3981x(Context context) {
        String str = "";
        String r = C0968cq.m3975r(context);
        if (r == null || r.length() < 5) {
            return str;
        }
        return r.substring(3, 5);
    }

    /* renamed from: y */
    private static int m3982y(Context context) {
        if (C0968cq.m3956a(context, "android.permission.READ_PHONE_STATE")) {
            return C0968cq.m3983z(context).getNetworkType();
        }
        return -1;
    }

    /* renamed from: z */
    private static TelephonyManager m3983z(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    /* renamed from: a */
    private static List<ScanResult> m3954a(List<ScanResult> list) {
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
