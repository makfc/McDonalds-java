package com.amap.api.location.core;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.aps.C1269v;
import com.facebook.stetho.common.Utf8Charset;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import javax.crypto.KeyGenerator;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* renamed from: com.amap.api.location.core.c */
public class ClientInfoUtil {
    /* renamed from: a */
    public static String f910a = "";
    /* renamed from: b */
    static HashMap<String, String> f911b = new HashMap();
    /* renamed from: c */
    private static ClientInfoUtil f912c = null;
    /* renamed from: d */
    private static String f913d = "http://apilocate.amap.com/mobile/binary";
    /* renamed from: e */
    private static String f914e = "http://abroad.apilocate.amap.com/mobile/binary";
    /* renamed from: f */
    private static Context f915f = null;
    /* renamed from: g */
    private static TelephonyManager f916g;
    /* renamed from: h */
    private static String f917h;
    /* renamed from: i */
    private static String f918i;
    /* renamed from: j */
    private static String f919j;
    /* renamed from: k */
    private static String f920k;
    /* renamed from: l */
    private static String f921l;
    /* renamed from: m */
    private static String f922m = "";
    /* renamed from: n */
    private static String f923n = null;
    /* renamed from: o */
    private static boolean f924o = false;
    /* renamed from: p */
    private static String f925p = null;
    /* renamed from: q */
    private static String f926q = null;
    /* renamed from: r */
    private static String f927r = null;

    /* compiled from: ClientInfoUtil */
    /* renamed from: com.amap.api.location.core.c$a */
    static class C0727a extends DefaultHandler {
        C0727a() {
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            if (str2.equals("string") && "UTDID".equals(attributes.getValue("name"))) {
                ClientInfoUtil.f924o = true;
            }
        }

        public void characters(char[] cArr, int i, int i2) throws SAXException {
            if (ClientInfoUtil.f924o) {
                ClientInfoUtil.f923n = new String(cArr, i, i2);
            }
        }

        public void endElement(String str, String str2, String str3) throws SAXException {
            ClientInfoUtil.f924o = false;
        }
    }

    /* renamed from: a */
    public static String m1422a() {
        return f910a;
    }

    /* renamed from: a */
    public static ClientInfoUtil m1421a(Context context) {
        if (f912c == null) {
            try {
                f912c = new ClientInfoUtil();
                f915f = context;
                f916g = (TelephonyManager) f915f.getSystemService("phone");
                f917h = f915f.getApplicationContext().getPackageName();
                f919j = ClientInfoUtil.m1449m();
                f920k = ClientInfoUtil.m1436f();
                f921l = ClientInfoUtil.m1437g();
                f910a = ClientInfoUtil.m1439h(f915f);
                f918i = ClientInfoUtil.m1443j(context);
                f923n = ClientInfoUtil.m1426b(context);
                if (C1269v.m5733a(ClientInfoUtil.m1441i(context))) {
                    f913d = f914e;
                } else {
                    ClientInfoUtil.m1450n();
                    f922m = Encrypt.m1463a(f910a);
                    ClientInfoUtil.m1434e(f922m);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return f912c;
    }

    /* renamed from: b */
    public static String m1425b() {
        return f917h;
    }

    /* renamed from: m */
    private static String m1449m() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) f915f.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        f919j = i2 > i ? i + "*" + i2 : i2 + "*" + i;
        return f919j;
    }

    private ClientInfoUtil() {
    }

    /* renamed from: a */
    public String mo8404a(String str) {
        KeyGenerator instance;
        try {
            instance = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            instance = null;
        }
        if (instance == null) {
            return "";
        }
        instance.init(256);
        byte[] encoded = instance.generateKey().getEncoded();
        try {
            byte[] a = Encrypt.m1468a(encoded, Encrypt.m1466a(f915f));
            byte[] b = Encrypt.m1474b(encoded, mo8405b(str).getBytes(Utf8Charset.NAME));
            encoded = new byte[(a.length + b.length)];
            try {
                System.arraycopy(a, 0, encoded, 0, a.length);
                System.arraycopy(b, 0, encoded, a.length, b.length);
            } catch (Throwable th) {
            }
        } catch (Throwable th2) {
            encoded = null;
        }
        return Base64Util.m1419a(C1269v.m5734a(encoded));
    }

    /* renamed from: b */
    public String mo8405b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        String i = ClientInfoUtil.m1440i();
        String j = ClientInfoUtil.m1442j();
        stringBuilder.append("diu=");
        stringBuilder.append(m1431d(i));
        stringBuilder.append("&sim=");
        stringBuilder.append(m1431d(j));
        stringBuilder.append("&pkg=");
        stringBuilder.append(m1431d(f917h));
        stringBuilder.append("&model=");
        stringBuilder.append(m1431d(Build.MODEL));
        stringBuilder.append("&manufacture=").append(m1431d(Build.MANUFACTURER));
        stringBuilder.append("&device=").append(m1431d(Build.DEVICE));
        stringBuilder.append("&appname=").append(m1431d(f920k));
        stringBuilder.append("&appversion=").append(m1431d(f921l));
        stringBuilder.append("&sysversion=");
        stringBuilder.append(m1431d(VERSION.SDK_INT + ""));
        stringBuilder.append("&resolution=" + m1431d(f919j));
        stringBuilder.append("&mac=").append(m1431d(ClientInfoUtil.m1438g(f915f)));
        stringBuilder.append("&ant=").append(m1431d(ClientInfoUtil.m1435f(f915f) + ""));
        stringBuilder.append("&nt=");
        stringBuilder.append(m1431d(ClientInfoUtil.m1432e(f915f) + ""));
        i = ClientInfoUtil.m1430d(f915f);
        stringBuilder.append("&np=");
        stringBuilder.append(m1431d(i));
        stringBuilder.append("&mnc=").append(m1431d(ClientInfoUtil.m1427c(f915f)));
        i = ClientInfoUtil.m1426b(f915f);
        if (i == null) {
            i = "";
        }
        stringBuilder.append("&tid=").append(m1431d(i));
        return stringBuilder.toString();
    }

    /* renamed from: d */
    private String m1431d(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /* renamed from: b */
    public static String m1426b(Context context) {
        try {
            if (f923n != null && !"".equals(f923n)) {
                return f923n;
            }
            if (context.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0) {
                f923n = System.getString(context.getContentResolver(), "mqBRboGZkQPcAkyk");
            }
            if (!(f923n == null || "".equals(f923n))) {
                return f923n;
            }
            try {
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.UTSystemConfig/Global/Alvin2.xml");
                    if (file.exists()) {
                        SAXParserFactory.newInstance().newSAXParser().parse(file, new C0727a());
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
            return f923n;
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    /* renamed from: c */
    static String m1427c(Context context) {
        String networkOperator;
        try {
            if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
                return null;
            }
            networkOperator = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
            if (TextUtils.isEmpty(networkOperator) && networkOperator.length() < 3) {
                return null;
            }
            networkOperator = networkOperator.substring(3);
            return networkOperator;
        } catch (Throwable th) {
            th.printStackTrace();
            networkOperator = null;
        }
    }

    /* renamed from: d */
    static String m1430d(Context context) {
        try {
            return ClientInfoUtil.m1445k(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: k */
    private static String m1445k(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return null;
        }
        return ((TelephonyManager) context.getSystemService("phone")).getSimOperatorName();
    }

    /* renamed from: l */
    private static int m1446l(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return -1;
        }
        return ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
    }

    /* renamed from: e */
    static int m1432e(Context context) {
        int i = -1;
        try {
            return ClientInfoUtil.m1446l(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return i;
        }
    }

    /* renamed from: f */
    public static int m1435f(Context context) {
        int i = -1;
        try {
            return ClientInfoUtil.m1448m(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return i;
        }
    }

    /* renamed from: m */
    private static int m1448m(Context context) {
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

    /* renamed from: g */
    static String m1438g(Context context) {
        try {
            if (f925p != null && !"".equals(f925p)) {
                return f925p;
            }
            if (context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") != 0) {
                return f925p;
            }
            f925p = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            return f925p;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: c */
    public String mo8406c() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mod=");
        stringBuilder.append(ClientInfoUtil.m1433e());
        stringBuilder.append("&sv=");
        stringBuilder.append(ClientInfoUtil.m1429d());
        stringBuilder.append("&nt=");
        stringBuilder.append(ClientInfoUtil.m1432e(f915f));
        String networkOperatorName = f916g.getNetworkOperatorName();
        stringBuilder.append("&np=");
        stringBuilder.append(networkOperatorName);
        return stringBuilder.toString();
    }

    /* renamed from: d */
    public static String m1429d() {
        return VERSION.RELEASE;
    }

    /* renamed from: e */
    public static String m1433e() {
        return Build.MODEL;
    }

    /* renamed from: h */
    public static String m1439h(Context context) {
        if (f910a == null || f910a.equals("")) {
            try {
                f910a = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("com.amap.api.v2.apikey");
            } catch (Throwable th) {
                Log.e("AuthLocation", "key鉴权失败");
            }
        }
        return f910a;
    }

    /* renamed from: i */
    public static String m1441i(Context context) {
        String str = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return str;
            }
            String networkOperator = telephonyManager.getNetworkOperator();
            if (networkOperator == null || networkOperator.length() < 3) {
                networkOperator = str;
            } else {
                networkOperator = networkOperator.substring(0, 3);
            }
            return networkOperator;
        } catch (Throwable th) {
            th.printStackTrace();
            return str;
        }
    }

    /* renamed from: f */
    public static String m1436f() {
        String str = "";
        try {
            PackageManager packageManager = f915f.getPackageManager();
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(f915f.getPackageName(), 0));
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return str;
        }
    }

    /* renamed from: g */
    public static String m1437g() {
        String str = "";
        try {
            return f915f.getPackageManager().getPackageInfo(f915f.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return str;
        }
    }

    /* renamed from: h */
    public String mo8407h() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(f918i).append(f917h);
        return stringBuffer.toString();
    }

    /* renamed from: j */
    public static String m1443j(Context context) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String toUpperCase = Integer.toHexString(b & 255).toUpperCase(Locale.US);
                if (toUpperCase.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(toUpperCase);
                stringBuffer.append(":");
            }
            return stringBuffer.toString();
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: n */
    private static void m1450n() {
        try {
            if (f911b == null) {
                f911b = new HashMap();
            }
            f911b.clear();
            f911b.put("a9a9d23668a1a7ea93de9b21d67e436a", "0151A2608A672882D5CF273660580953A9D5A7F787F6E0DE4AFAF5C4950A3957D8765EF79F885FFFDC4DC17DB726827514A96E4D8EE1B621F3188265766F41D7");
            f911b.put("fe643c382e5c3b3962141f1a2e815a78", "F1721D9E2EC066558986B60084D0A9124FA354CD62DFA34F4B4512D920CF5615529C1DFB4E1757CADCEEA1CC4504F37F");
            f911b.put("b2e8bd171989cb2c3c13bd89b4c1067a", "E9FD1623CA093DE6FC8EF95553A10FD156A2992AFE89A29927491E24ACE42759650D6EF3A8F46CA4BD2761B775B48DB0");
            f911b.put("9a571aa113ad987d626c0457828962e6", "6970E5DA79F86F5D12FF4DC281C662029F6782B9D29E1EF5913F43B71CCE0D05B7ADE26E37C8A0D7DA070B078046CCFDA813DFEE6A4A611548B1F531E569238A");
            f911b.put("668319f11506def6208d6afe320dfd52", "EA36C39255025E5DB4FBD8257739C71641729A816259783B63AAA127EC6282E7D3DD0FA9A765C48EE43D5D885CC9CDFF");
            f911b.put("256b0f26bb2a9506be6cfdb84028ae08", "8A72631ECA335440D5349C39B2C8C26F04E53AF03DA9FF9B1F9A99AC018EB3E4B692A3B3E7C9FAA158B9C743F11C0277");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: e */
    private static void m1434e(String str) {
        if (str != null) {
            try {
                if (str.length() != 0 && f911b != null && f911b.containsKey(str)) {
                    String str2 = (String) f911b.get(str);
                    if (str2 == null || str2.length() <= 0) {
                        str2 = null;
                    } else {
                        str2 = Encrypt.m1470b(str2, str);
                    }
                    if (str2 != null && str2.length() > 0 && str2.contains("http:")) {
                        f913d = str2;
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public static boolean m1423a(double d, double d2) {
        int i = (int) ((d2 - 73.0d) / 0.5d);
        int i2 = (int) ((d - 3.5d) / 0.5d);
        if (i2 < 0 || i2 >= 101 || i < 0 || i >= 124) {
            return false;
        }
        try {
            if ("00000000000000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001100000001011000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011101010111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000110111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111101111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001000110111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011010111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001110011100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000110000001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001010011100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111100110001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111000111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111110011000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111000000000111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111100000000000010111110100000011000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111110000000001111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111000000111111111111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111101111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000101111111111111111111111111111111111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000011110000000001111111111111111111111111111111111111111111110000000000000000000000000000000000000000000000000000000000011000011111100000000111111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000000001111111100111111111100110111111111111111111111111111111111111111111111110000000000000000000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000000000101111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111011111000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111100100000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111100011100000000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000111110000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110011111110000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110111111110000000000000000000000111011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111110000000000000000000000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000000000000000000011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111000000000000000000000000011111111111111111111111111111111111111111111111100001111111111111111111111111111111111111111111111111010000000000000000000000111111111111111111111111111111111111111111110000000000000001111111111111111111111111111111111111111111100000000000000000000011111111111111111111111111111111100000000000000000000000000001111111111111111111111111111111111111111110000000000000000000001111111111111111111111111111111100000000000000000000000000000001111111111111111111111111111111111111111000000000000000000000111111111111111111111111111111110000000000000000000000000000001111111111111111111111111111111111111111100000000000000000000111111111111111111111111111111000000000000000000000000000000000111111111111111111111111111111111111111111000000000000000000001111111111111111111111111110000000000000000000000000000000000001110011111111111111111111111111111111111111100000000000000000000011111111111111111100000000000000000000000000000000000000000000000001111111111111111111111111111111111111000000000000000000001111111111111111111000000000000000000000000000000000000000000000000011111111111111111111111111111111111100000000000000000000011111111111111111100000000000000000000000000000000000000000000000000011111111111111111111111111111111111000000000000000000001111111111111111100000000000000000000000000000000000000000000000000000000111111111111111111111111111111110000000000000000000000000111111111100000000000000000000000000000000000000000000000000111111111111111111111111111111111111111000000000000000000000000011111111100000000000000000000000000000000000000000000000000011111111111111111111111111111110001111100000000000000000000000000111110000000000000000000000000000000000000000000000000000001111111111111111111111111111111000000000000000000000000000000000001110000000000000000000000000000000000000000000000000000000011111111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000111111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111111100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000011111111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111110000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010110000000000000000000000".charAt(i + (i2 * 124)) != '1') {
                return false;
            }
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    /* renamed from: i */
    public static String m1440i() {
        if (f926q != null && !"".equals(f926q)) {
            return f926q;
        }
        if (f916g == null) {
            f916g = (TelephonyManager) f915f.getSystemService("phone");
        }
        f926q = f916g.getDeviceId();
        return f926q;
    }

    /* renamed from: j */
    public static String m1442j() {
        if (f927r != null && !"".equals(f927r)) {
            return f927r;
        }
        if (f916g == null) {
            f916g = (TelephonyManager) f915f.getSystemService("phone");
        }
        f927r = f916g.getSubscriberId();
        return f927r;
    }

    /* renamed from: k */
    public static String m1444k() {
        return f913d;
    }
}
