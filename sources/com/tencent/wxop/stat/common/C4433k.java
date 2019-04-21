package com.tencent.wxop.stat.common;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.mcdonalds.sdk.connectors.middleware.response.MWDeliveryStatusResponse;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpHost;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.common.k */
public class C4433k {
    /* renamed from: a */
    private static String f7139a = null;
    /* renamed from: b */
    private static String f7140b = null;
    /* renamed from: c */
    private static String f7141c = null;
    /* renamed from: d */
    private static String f7142d = null;
    /* renamed from: e */
    private static Random f7143e = null;
    /* renamed from: f */
    private static DisplayMetrics f7144f = null;
    /* renamed from: g */
    private static String f7145g = null;
    /* renamed from: h */
    private static String f7146h = "";
    /* renamed from: i */
    private static String f7147i = "";
    /* renamed from: j */
    private static int f7148j = -1;
    /* renamed from: k */
    private static StatLogger f7149k = null;
    /* renamed from: l */
    private static String f7150l = null;
    /* renamed from: m */
    private static String f7151m = null;
    /* renamed from: n */
    private static volatile int f7152n = -1;
    /* renamed from: o */
    private static String f7153o = null;
    /* renamed from: p */
    private static String f7154p = null;
    /* renamed from: q */
    private static long f7155q = -1;
    /* renamed from: r */
    private static String f7156r = "";
    /* renamed from: s */
    private static C4436n f7157s = null;
    /* renamed from: t */
    private static String f7158t = "__MTA_FIRST_ACTIVATE__";
    /* renamed from: u */
    private static int f7159u = -1;
    /* renamed from: v */
    private static long f7160v = -1;
    /* renamed from: w */
    private static int f7161w = 0;
    /* renamed from: x */
    private static String f7162x = "";

    /* renamed from: A */
    public static int m8095A(Context context) {
        return C4438p.m8149a(context, "mta.qq.com.difftime", 0);
    }

    /* renamed from: B */
    public static boolean m8096B(Context context) {
        if (context == null) {
            return false;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return false;
        }
        String packageName = context.getPackageName();
        for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.startsWith(packageName)) {
                return runningAppProcessInfo.importance == MWDeliveryStatusResponse.STATUS_ORDER_DELIVERED;
            }
        }
        return false;
    }

    /* renamed from: C */
    public static String m8097C(Context context) {
        if (context == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        return (resolveActivity.activityInfo == null || resolveActivity.activityInfo.packageName.equals("android")) ? null : resolveActivity.activityInfo.packageName;
    }

    /* renamed from: D */
    private static long m8098D(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    /* renamed from: a */
    public static int m8099a() {
        return C4433k.m8123g().nextInt(Integer.MAX_VALUE);
    }

    /* renamed from: a */
    public static int m8100a(Context context, boolean z) {
        if (z) {
            f7161w = C4433k.m8095A(context);
        }
        return f7161w;
    }

    /* renamed from: a */
    public static Long m8101a(String str, String str2, int i, int i2, Long l) {
        if (str == null || str2 == null) {
            return l;
        }
        if (str2.equalsIgnoreCase(".") || str2.equalsIgnoreCase("|")) {
            str2 = "\\" + str2;
        }
        String[] split = str.split(str2);
        if (split.length != i2) {
            return l;
        }
        try {
            Long valueOf = Long.valueOf(0);
            int i3 = 0;
            while (i3 < split.length) {
                Long valueOf2 = Long.valueOf(((long) i) * (valueOf.longValue() + Long.valueOf(split[i3]).longValue()));
                i3++;
                valueOf = valueOf2;
            }
            return valueOf;
        } catch (NumberFormatException e) {
            return l;
        }
    }

    /* renamed from: a */
    public static String m8102a(int i) {
        Calendar instance = Calendar.getInstance();
        instance.roll(6, i);
        return new SimpleDateFormat("yyyyMMdd").format(instance.getTime());
    }

    /* renamed from: a */
    public static String m8103a(long j) {
        return new SimpleDateFormat("yyyyMMdd").format(new Date(j));
    }

    /* renamed from: a */
    public static String m8104a(Context context, String str) {
        if (!StatConfig.isEnableConcurrentProcess()) {
            return str;
        }
        if (f7151m == null) {
            f7151m = C4433k.m8134q(context);
        }
        return f7151m != null ? str + "_" + f7151m : str;
    }

    /* renamed from: a */
    public static String m8105a(String str) {
        if (str == null) {
            return "0";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (Throwable th) {
            return "0";
        }
    }

    /* renamed from: a */
    public static HttpHost m8106a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) != 0) {
                return null;
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getTypeName() != null && activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                return null;
            }
            String extraInfo = activeNetworkInfo.getExtraInfo();
            if (extraInfo == null) {
                return null;
            }
            if (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap")) {
                return new HttpHost("10.0.0.172", 80);
            }
            if (extraInfo.equals("ctwap")) {
                return new HttpHost("10.0.0.200", 80);
            }
            String defaultHost = Proxy.getDefaultHost();
            if (defaultHost != null && defaultHost.trim().length() > 0) {
                return new HttpHost(defaultHost, Proxy.getDefaultPort());
            }
            return null;
        } catch (Throwable th) {
            f7149k.mo33949e(th);
        }
    }

    /* renamed from: a */
    public static void m8107a(Context context, int i) {
        f7161w = i;
        C4438p.m8153b(context, "mta.qq.com.difftime", i);
    }

    /* renamed from: a */
    public static boolean m8108a(StatSpecifyReportedInfo statSpecifyReportedInfo) {
        return statSpecifyReportedInfo == null ? false : C4433k.m8115c(statSpecifyReportedInfo.getAppKey());
    }

    /* renamed from: a */
    public static byte[] m8109a(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
        byte[] bArr2 = new byte[4096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length * 2);
        while (true) {
            int read = gZIPInputStream.read(bArr2);
            if (read != -1) {
                byteArrayOutputStream.write(bArr2, 0, read);
            } else {
                bArr2 = byteArrayOutputStream.toByteArray();
                byteArrayInputStream.close();
                gZIPInputStream.close();
                byteArrayOutputStream.close();
                return bArr2;
            }
        }
    }

    /* renamed from: b */
    public static long m8110b(String str) {
        return C4433k.m8101a(str, ".", 100, 3, Long.valueOf(0)).longValue();
    }

    /* renamed from: b */
    public static synchronized StatLogger m8111b() {
        StatLogger statLogger;
        synchronized (C4433k.class) {
            if (f7149k == null) {
                statLogger = new StatLogger("MtaSDK");
                f7149k = statLogger;
                statLogger.setDebugEnable(false);
            }
            statLogger = f7149k;
        }
        return statLogger;
    }

    /* renamed from: b */
    public static synchronized String m8112b(Context context) {
        String a;
        synchronized (C4433k.class) {
            if (f7139a == null || f7139a.trim().length() == 0) {
                a = C4439q.m8156a(context);
                f7139a = a;
                if (a == null || f7139a.trim().length() == 0) {
                    f7139a = Integer.toString(C4433k.m8123g().nextInt(Integer.MAX_VALUE));
                }
                a = f7139a;
            } else {
                a = f7139a;
            }
        }
        return a;
    }

    /* renamed from: c */
    public static long m8113c() {
        try {
            Calendar instance = Calendar.getInstance();
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            instance.set(14, 0);
            return instance.getTimeInMillis() + 86400000;
        } catch (Throwable th) {
            f7149k.mo33949e(th);
            return System.currentTimeMillis() + 86400000;
        }
    }

    /* renamed from: c */
    public static synchronized String m8114c(Context context) {
        String str;
        synchronized (C4433k.class) {
            if (f7141c == null || f7141c.trim().length() == 0) {
                f7141c = C4439q.m8161b(context);
            }
            str = f7141c;
        }
        return str;
    }

    /* renamed from: c */
    public static boolean m8115c(String str) {
        return (str == null || str.trim().length() == 0) ? false : true;
    }

    /* renamed from: d */
    public static DisplayMetrics m8116d(Context context) {
        if (f7144f == null) {
            f7144f = new DisplayMetrics();
            ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(f7144f);
        }
        return f7144f;
    }

    /* renamed from: d */
    public static String m8117d() {
        if (C4433k.m8115c(f7154p)) {
            return f7154p;
        }
        long e = C4433k.m8118e() / 1000000;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        String str = String.valueOf((((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / 1000000) + "/" + String.valueOf(e);
        f7154p = str;
        return str;
    }

    /* renamed from: e */
    public static long m8118e() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
    }

    /* renamed from: e */
    public static boolean m8119e(Context context) {
        try {
            if (C4439q.m8160a(context, "android.permission.ACCESS_WIFI_STATE")) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
                    if (allNetworkInfo != null) {
                        int i = 0;
                        while (i < allNetworkInfo.length) {
                            if (allNetworkInfo[i].getTypeName().equalsIgnoreCase("WIFI") && allNetworkInfo[i].isConnected()) {
                                return true;
                            }
                            i++;
                        }
                    }
                }
                return false;
            }
            f7149k.warn("can not get the permission of android.permission.ACCESS_WIFI_STATE");
            return false;
        } catch (Throwable th) {
            f7149k.mo33949e(th);
        }
    }

    /* renamed from: f */
    public static String m8121f(Context context) {
        if (f7140b != null) {
            return f7140b;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("TA_APPKEY");
                if (string != null) {
                    f7140b = string;
                    return string;
                }
                f7149k.mo33952i("Could not read APPKEY meta-data from AndroidManifest.xml");
            }
        } catch (Throwable th) {
            f7149k.mo33952i("Could not read APPKEY meta-data from AndroidManifest.xml");
        }
        return null;
    }

    /* renamed from: g */
    public static String m8122g(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                Object obj = applicationInfo.metaData.get("InstallChannel");
                if (obj != null) {
                    return obj.toString();
                }
                f7149k.mo33956w("Could not read InstallChannel meta-data from AndroidManifest.xml");
            }
        } catch (Throwable th) {
            f7149k.mo33948e((Object) "Could not read InstallChannel meta-data from AndroidManifest.xml");
        }
        return null;
    }

    /* renamed from: g */
    private static synchronized Random m8123g() {
        Random random;
        synchronized (C4433k.class) {
            if (f7143e == null) {
                f7143e = new Random();
            }
            random = f7143e;
        }
        return random;
    }

    /* renamed from: h */
    private static long m8124h() {
        if (f7155q > 0) {
            return f7155q;
        }
        long j = 1;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            j = (long) (Integer.valueOf(bufferedReader.readLine().split("\\s+")[1]).intValue() * 1024);
            bufferedReader.close();
        } catch (Exception e) {
        }
        f7155q = j;
        return j;
    }

    /* renamed from: h */
    public static String m8125h(Context context) {
        return context == null ? null : context.getClass().getName();
    }

    /* renamed from: i */
    public static String m8126i(Context context) {
        if (f7145g != null) {
            return f7145g;
        }
        try {
            if (!C4439q.m8160a(context, "android.permission.READ_PHONE_STATE")) {
                f7149k.mo33948e((Object) "Could not get permission of android.permission.READ_PHONE_STATE");
            } else if (C4433k.m8128k(context)) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    f7145g = telephonyManager.getSimOperator();
                }
            }
        } catch (Throwable th) {
            f7149k.mo33949e(th);
        }
        return f7145g;
    }

    /* renamed from: j */
    public static String m8127j(Context context) {
        if (C4433k.m8115c(f7146h)) {
            return f7146h;
        }
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            f7146h = str;
            if (str == null) {
                return "";
            }
        } catch (Throwable th) {
            f7149k.mo33949e(th);
        }
        return f7146h;
    }

    /* renamed from: k */
    public static boolean m8128k(Context context) {
        return context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0;
    }

    /* renamed from: l */
    public static String m8129l(Context context) {
        String str = "";
        try {
            if (C4439q.m8160a(context, "android.permission.INTERNET") && C4439q.m8160a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                String typeName;
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    typeName = activeNetworkInfo.getTypeName();
                    String extraInfo = activeNetworkInfo.getExtraInfo();
                    if (typeName != null) {
                        if (typeName.equalsIgnoreCase("WIFI")) {
                            return "WIFI";
                        }
                        if (typeName.equalsIgnoreCase("MOBILE")) {
                            return extraInfo != null ? extraInfo : "MOBILE";
                        } else {
                            if (extraInfo != null) {
                                return extraInfo;
                            }
                            return typeName;
                        }
                    }
                }
                typeName = str;
                return typeName;
            }
            f7149k.mo33948e((Object) "can not get the permission of android.permission.ACCESS_WIFI_STATE");
            return str;
        } catch (Throwable th) {
            f7149k.mo33949e(th);
            return str;
        }
    }

    /* renamed from: m */
    public static Integer m8130m(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return Integer.valueOf(telephonyManager.getNetworkType());
            }
        } catch (Throwable th) {
        }
        return null;
    }

    /* renamed from: n */
    public static String m8131n(Context context) {
        if (C4433k.m8115c(f7147i)) {
            return f7147i;
        }
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            f7147i = str;
            if (str == null || f7147i.length() == 0) {
                return "unknown";
            }
        } catch (Throwable th) {
            f7149k.mo33949e(th);
        }
        return f7147i;
    }

    /* renamed from: o */
    public static int m8132o(Context context) {
        if (f7148j != -1) {
            return f7148j;
        }
        try {
            if (C4437o.m8148a()) {
                f7148j = 1;
            }
        } catch (Throwable th) {
            f7149k.mo33949e(th);
        }
        f7148j = 0;
        return 0;
    }

    /* renamed from: p */
    public static String m8133p(Context context) {
        if (C4433k.m8115c(f7150l)) {
            return f7150l;
        }
        try {
            if (C4439q.m8160a(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                String externalStorageState = Environment.getExternalStorageState();
                if (externalStorageState != null && externalStorageState.equals("mounted")) {
                    externalStorageState = Environment.getExternalStorageDirectory().getPath();
                    if (externalStorageState != null) {
                        StatFs statFs = new StatFs(externalStorageState);
                        long blockCount = (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 1000000;
                        externalStorageState = String.valueOf((((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks())) / 1000000) + "/" + String.valueOf(blockCount);
                        f7150l = externalStorageState;
                        return externalStorageState;
                    }
                }
                return null;
            }
            f7149k.warn("can not get the permission of android.permission.WRITE_EXTERNAL_STORAGE");
            return null;
        } catch (Throwable th) {
            f7149k.mo33949e(th);
        }
    }

    /* renamed from: q */
    static String m8134q(Context context) {
        try {
            if (f7151m != null) {
                return f7151m;
            }
            int myPid = Process.myPid();
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    f7151m = runningAppProcessInfo.processName;
                    break;
                }
            }
            return f7151m;
        } catch (Throwable th) {
        }
    }

    /* renamed from: r */
    public static String m8135r(Context context) {
        return C4433k.m8104a(context, StatConstants.DATABASE_NAME);
    }

    /* renamed from: s */
    public static synchronized Integer m8136s(Context context) {
        Integer valueOf;
        int i = 0;
        synchronized (C4433k.class) {
            if (f7152n <= 0) {
                f7152n = C4438p.m8149a(context, "MTA_EVENT_INDEX", 0);
                C4438p.m8153b(context, "MTA_EVENT_INDEX", f7152n + 1000);
            } else if (f7152n % 1000 == 0) {
                try {
                    int i2 = f7152n + 1000;
                    if (f7152n < 2147383647) {
                        i = i2;
                    }
                    C4438p.m8153b(context, "MTA_EVENT_INDEX", i);
                } catch (Throwable th) {
                    f7149k.mo33956w(th);
                }
            }
            i = f7152n + 1;
            f7152n = i;
            valueOf = Integer.valueOf(i);
        }
        return valueOf;
    }

    /* renamed from: t */
    public static String m8137t(Context context) {
        try {
            return String.valueOf(C4433k.m8098D(context) / 1000000) + "/" + String.valueOf(C4433k.m8124h() / 1000000);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: u */
    public static JSONObject m8138u(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("n", C4434l.m8144a());
            String d = C4434l.m8147d();
            if (d != null && d.length() > 0) {
                jSONObject.put("na", d);
            }
            int b = C4434l.m8145b();
            if (b > 0) {
                jSONObject.put("fx", b / 1000000);
            }
            b = C4434l.m8146c();
            if (b > 0) {
                jSONObject.put("fn", b / 1000000);
            }
        } catch (Throwable th) {
            Log.w("MtaSDK", "get cpu error", th);
        }
        return jSONObject;
    }

    /* renamed from: v */
    public static String m8139v(Context context) {
        if (C4433k.m8115c(f7156r)) {
            return f7156r;
        }
        try {
            SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            if (sensorManager != null) {
                List sensorList = sensorManager.getSensorList(-1);
                if (sensorList != null) {
                    StringBuilder stringBuilder = new StringBuilder(sensorList.size() * 10);
                    for (int i = 0; i < sensorList.size(); i++) {
                        stringBuilder.append(((Sensor) sensorList.get(i)).getType());
                        if (i != sensorList.size() - 1) {
                            stringBuilder.append(",");
                        }
                    }
                    f7156r = stringBuilder.toString();
                }
            }
        } catch (Throwable th) {
            f7149k.mo33949e(th);
        }
        return f7156r;
    }

    /* renamed from: w */
    public static synchronized int m8140w(Context context) {
        int i;
        synchronized (C4433k.class) {
            if (f7159u != -1) {
                i = f7159u;
            } else {
                C4433k.m8141x(context);
                i = f7159u;
            }
        }
        return i;
    }

    /* renamed from: x */
    public static void m8141x(Context context) {
        int a = C4438p.m8149a(context, f7158t, 1);
        f7159u = a;
        if (a == 1) {
            C4438p.m8153b(context, f7158t, 0);
        }
    }

    /* renamed from: y */
    public static boolean m8142y(Context context) {
        if (f7160v < 0) {
            f7160v = C4438p.m8150a(context, "mta.qq.com.checktime", 0);
        }
        return Math.abs(System.currentTimeMillis() - f7160v) > 86400000;
    }

    /* renamed from: z */
    public static void m8143z(Context context) {
        f7160v = System.currentTimeMillis();
        C4438p.m8154b(context, "mta.qq.com.checktime", f7160v);
    }
}
