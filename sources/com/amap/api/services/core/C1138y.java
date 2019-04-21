package com.amap.api.services.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.Settings.System;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* compiled from: DeviceInfo */
/* renamed from: com.amap.api.services.core.y */
public class C1138y {
    /* renamed from: a */
    private static String f3814a = null;
    /* renamed from: b */
    private static boolean f3815b = false;
    /* renamed from: c */
    private static String f3816c = null;
    /* renamed from: d */
    private static String f3817d = null;
    /* renamed from: e */
    private static String f3818e = null;
    /* renamed from: f */
    private static String f3819f = null;

    /* compiled from: DeviceInfo */
    /* renamed from: com.amap.api.services.core.y$a */
    static class C1137a extends DefaultHandler {
        C1137a() {
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            if (str2.equals("string") && "UTDID".equals(attributes.getValue("name"))) {
                C1138y.f3815b = true;
            }
        }

        public void characters(char[] cArr, int i, int i2) throws SAXException {
            if (C1138y.f3815b) {
                C1138y.f3814a = new String(cArr, i, i2);
            }
        }

        public void endElement(String str, String str2, String str3) throws SAXException {
            C1138y.f3815b = false;
        }
    }

    /* renamed from: a */
    public static String m5099a(Context context) {
        try {
            if (f3814a != null && !"".equals(f3814a)) {
                return f3814a;
            }
            if (context.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0) {
                f3814a = System.getString(context.getContentResolver(), "mqBRboGZkQPcAkyk");
            }
            if (!(f3814a == null || "".equals(f3814a))) {
                return f3814a;
            }
            try {
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.UTSystemConfig/Global/Alvin2.xml");
                    if (file.exists()) {
                        SAXParserFactory.newInstance().newSAXParser().parse(file, new C1137a());
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e2) {
                e2.printStackTrace();
            } catch (SAXException e3) {
                e3.printStackTrace();
            } catch (IOException e4) {
                e4.printStackTrace();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return f3814a;
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    /* renamed from: b */
    static String m5104b(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        if (context != null) {
            try {
                if (context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                    if (wifiManager.isWifiEnabled()) {
                        List scanResults = wifiManager.getScanResults();
                        if (scanResults == null || scanResults.size() == 0) {
                            return stringBuilder.toString();
                        }
                        List a = C1138y.m5101a(scanResults);
                        Object obj = 1;
                        int i = 0;
                        while (i < a.size() && i < 10) {
                            ScanResult scanResult = (ScanResult) a.get(i);
                            if (obj != null) {
                                obj = null;
                            } else {
                                stringBuilder.append("||");
                            }
                            stringBuilder.append(scanResult.BSSID);
                            i++;
                        }
                    }
                    return stringBuilder.toString();
                }
            } catch (Throwable th) {
                C1099ax.m4800a(th, "DeviceInfo", "getWifiMacs");
                th.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: c */
    static String m5105c(Context context) {
        try {
            if (f3816c != null && !"".equals(f3816c)) {
                return f3816c;
            }
            if (context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") != 0) {
                return f3816c;
            }
            f3816c = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            return f3816c;
        } catch (Throwable th) {
            C1099ax.m4800a(th, "DeviceInfo", "getDeviceMac");
            th.printStackTrace();
        }
    }

    /* renamed from: d */
    static String m5106d(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
                return stringBuilder.toString();
            }
            CellLocation cellLocation = ((TelephonyManager) context.getSystemService("phone")).getCellLocation();
            if (cellLocation instanceof GsmCellLocation) {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                stringBuilder.append(gsmCellLocation.getLac()).append("||").append(gsmCellLocation.getCid()).append("&bt=gsm");
            } else if (cellLocation instanceof CdmaCellLocation) {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                int systemId = cdmaCellLocation.getSystemId();
                int networkId = cdmaCellLocation.getNetworkId();
                int baseStationId = cdmaCellLocation.getBaseStationId();
                if (systemId < 0 || networkId < 0 || baseStationId < 0) {
                }
                stringBuilder.append(systemId).append("||").append(networkId).append("||").append(baseStationId).append("&bt=cdma");
            }
            return stringBuilder.toString();
        } catch (Throwable th) {
            C1099ax.m4800a(th, "DeviceInfo", "cellInfo");
            th.printStackTrace();
        }
    }

    /* renamed from: e */
    static String m5107e(Context context) {
        String str = "";
        try {
            return C1138y.m5124v(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return str;
        }
    }

    /* renamed from: f */
    static int m5108f(Context context) {
        int i = -1;
        try {
            return C1138y.m5125w(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return i;
        }
    }

    /* renamed from: g */
    public static int m5109g(Context context) {
        int i = -1;
        try {
            return C1138y.m5123u(context);
        } catch (Throwable th) {
            C1099ax.m4800a(th, "DeviceInfo", "getActiveNetWorkType");
            th.printStackTrace();
            return i;
        }
    }

    /* renamed from: h */
    static int m5110h(Context context) {
        int i = -1;
        try {
            return C1138y.m5123u(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return i;
        }
    }

    /* renamed from: i */
    static String m5111i(Context context) {
        String extraInfo;
        try {
            if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
                return null;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return null;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            extraInfo = activeNetworkInfo.getExtraInfo();
            return extraInfo;
        } catch (Throwable th) {
            C1099ax.m4800a(th, "DeviceInfo", "getNetworkExtraInfo");
            th.printStackTrace();
            extraInfo = null;
        }
    }

    /* renamed from: j */
    static String m5112j(Context context) {
        try {
            if (f3817d != null && !"".equals(f3817d)) {
                return f3817d;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            f3817d = i2 > i ? i + "*" + i2 : i2 + "*" + i;
            return f3817d;
        } catch (Throwable th) {
            C1099ax.m4800a(th, "DeviceInfo", "getReslution");
            th.printStackTrace();
        }
    }

    /* renamed from: k */
    static String m5113k(Context context) {
        try {
            return C1138y.m5122t(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: l */
    static String m5114l(Context context) {
        try {
            return C1138y.m5122t(context);
        } catch (Throwable th) {
            C1099ax.m4800a(th, "DeviceInfo", "getActiveNetworkTypeName");
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: m */
    static String m5115m(Context context) {
        try {
            if (f3818e != null && !"".equals(f3818e)) {
                return f3818e;
            }
            if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
                return f3818e;
            }
            f3818e = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            return f3818e;
        } catch (Throwable th) {
            C1099ax.m4800a(th, "DeviceInfo", "getDeviceID");
            th.printStackTrace();
        }
    }

    /* renamed from: n */
    static String m5116n(Context context) {
        String str = null;
        try {
            return C1138y.m5120r(context);
        } catch (Throwable th) {
            C1099ax.m4800a(th, "DeviceInfo", "getSubscriberId");
            th.printStackTrace();
            return str;
        }
    }

    /* renamed from: o */
    static String m5117o(Context context) {
        try {
            return C1138y.m5121s(context);
        } catch (Throwable th) {
            C1099ax.m4800a(th, "DeviceInfo", "getNetworkOperatorName");
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: p */
    static String m5118p(Context context) {
        try {
            return C1138y.m5121s(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: q */
    static String m5119q(Context context) {
        try {
            return C1138y.m5120r(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: r */
    private static String m5120r(Context context) {
        if (f3819f != null && !"".equals(f3819f)) {
            return f3819f;
        }
        if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return f3819f;
        }
        f3819f = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        return f3819f;
    }

    /* renamed from: s */
    private static String m5121s(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return null;
        }
        return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
    }

    /* renamed from: t */
    private static String m5122t(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return "";
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return "";
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return "";
        }
        return activeNetworkInfo.getTypeName();
    }

    /* renamed from: u */
    private static int m5123u(Context context) {
        if (context == null || context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return -1;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return -1;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return -1;
        }
        return activeNetworkInfo.getType();
    }

    /* renamed from: v */
    private static String m5124v(Context context) {
        String str = "";
        String n = C1138y.m5116n(context);
        if (n == null || n.length() < 5) {
            return str;
        }
        return n.substring(3, 5);
    }

    /* renamed from: w */
    private static int m5125w(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return -1;
        }
        return ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
    }

    /* renamed from: a */
    private static List<ScanResult> m5101a(List<ScanResult> list) {
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
