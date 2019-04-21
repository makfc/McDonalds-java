package com.baidu.android.pushservice.p037i.p038a;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.baidu.android.pushservice.p034f.C1403b;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p039k.C1471e;
import com.baidu.android.pushservice.util.C1548l;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.i.a.b */
public class C1432b {

    /* renamed from: com.baidu.android.pushservice.i.a.b$a */
    public static class C1430a {
        /* renamed from: a */
        public int f5017a;
        /* renamed from: b */
        public int f5018b;
        /* renamed from: c */
        public int f5019c;
        /* renamed from: d */
        public double f5020d;
        /* renamed from: e */
        public long f5021e;
    }

    /* renamed from: com.baidu.android.pushservice.i.a.b$b */
    private static class C1431b {
        /* renamed from: c */
        private static C1431b f5022c = null;
        /* renamed from: a */
        private Context f5023a = null;
        /* renamed from: b */
        private final JSONObject f5024b;

        private C1431b(Context context) {
            this.f5023a = context;
            this.f5024b = new JSONObject();
            try {
                this.f5024b.put("os_name", "Android");
                this.f5024b.put("manufacture", Build.MANUFACTURER);
                this.f5024b.put("os_version", VERSION.RELEASE);
                this.f5024b.put("model", Build.MODEL);
                this.f5024b.put("firmware", Build.FINGERPRINT);
                this.f5024b.put("mem_size", String.valueOf(C1432b.m6483b()));
                this.f5024b.put("screen_width", String.valueOf(C1432b.m6482a(this.f5023a)[0]));
                this.f5024b.put("screen_height", String.valueOf(C1432b.m6482a(this.f5023a)[1]));
                this.f5024b.put("cpu_model", C1432b.m6485c());
                this.f5024b.put("cpu_feature", C1432b.m6487d());
                this.f5024b.put("screen_density", String.valueOf(C1432b.m6482a(this.f5023a)[2]));
                if (((TelephonyManager) this.f5023a.getSystemService("phone")) != null) {
                    this.f5024b.put("wise_cuid", C1471e.m6687a(this.f5023a));
                }
                String string = context.getSharedPreferences("pst", 0).getString("push_mac_id", null);
                if (string == null || string.length() == 0) {
                    try {
                        string = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
                        if (string != null && string.length() > 0) {
                            context.getSharedPreferences("pst", 0).edit().putString("push_mac_id", string).commit();
                        }
                    } catch (Exception e) {
                        C1425a.m6440a("StatUtils", e);
                    }
                }
                if (string == null) {
                    return;
                }
                if (string.length() > 0) {
                    this.f5024b.put("mac_id", string);
                }
            } catch (JSONException e2) {
                C1425a.m6440a("StatUtils", e2);
            }
        }

        /* renamed from: a */
        public static synchronized C1431b m6477a(Context context) {
            C1431b c1431b;
            synchronized (C1431b.class) {
                if (f5022c == null) {
                    f5022c = new C1431b(context);
                }
                c1431b = f5022c;
            }
            return c1431b;
        }

        /* renamed from: a */
        public JSONObject mo13833a() {
            return this.f5024b;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034  */
    /* renamed from: a */
    public static com.baidu.android.pushservice.p037i.p038a.C1432b.C1430a m6479a() {
        /*
        r7 = 1;
        r6 = 0;
        r1 = "";
        r3 = 0;
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r4 = new byte[r0];	 Catch:{ Exception -> 0x003c, all -> 0x004c }
        r2 = new java.io.RandomAccessFile;	 Catch:{ Exception -> 0x003c, all -> 0x004c }
        r0 = "/proc/cpuinfo";
        r5 = "r";
        r2.<init>(r0, r5);	 Catch:{ Exception -> 0x003c, all -> 0x004c }
        r2.read(r4);	 Catch:{ Exception -> 0x0058 }
        r0 = new java.lang.String;	 Catch:{ Exception -> 0x0058 }
        r0.<init>(r4);	 Catch:{ Exception -> 0x0058 }
        r3 = 0;
        r3 = r0.indexOf(r3);	 Catch:{ Exception -> 0x0058 }
        r4 = -1;
        if (r3 == r4) goto L_0x0027;
    L_0x0022:
        r4 = 0;
        r0 = r0.substring(r4, r3);	 Catch:{ Exception -> 0x0058 }
    L_0x0027:
        r1 = new java.io.Closeable[r7];
        r1[r6] = r2;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r1);
    L_0x002e:
        r0 = com.baidu.android.pushservice.p037i.p038a.C1432b.m6480a(r0);
        if (r0 == 0) goto L_0x003b;
    L_0x0034:
        r1 = com.baidu.android.pushservice.p037i.p038a.C1432b.m6489e();
        r2 = (long) r1;
        r0.f5021e = r2;
    L_0x003b:
        return r0;
    L_0x003c:
        r0 = move-exception;
        r2 = r3;
    L_0x003e:
        r3 = "StatUtils";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r3, r0);	 Catch:{ all -> 0x0056 }
        r0 = new java.io.Closeable[r7];
        r0[r6] = r2;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r0);
        r0 = r1;
        goto L_0x002e;
    L_0x004c:
        r0 = move-exception;
        r2 = r3;
    L_0x004e:
        r1 = new java.io.Closeable[r7];
        r1[r6] = r2;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r1);
        throw r0;
    L_0x0056:
        r0 = move-exception;
        goto L_0x004e;
    L_0x0058:
        r0 = move-exception;
        goto L_0x003e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p037i.p038a.C1432b.m6479a():com.baidu.android.pushservice.i.a.b$a");
    }

    /* renamed from: a */
    private static C1430a m6480a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        C1430a c1430a = new C1430a();
        c1430a.f5017a = 0;
        c1430a.f5019c = 0;
        c1430a.f5018b = 1;
        c1430a.f5020d = 0.0d;
        if (str.contains("ARMv5")) {
            c1430a.f5017a = 1;
        } else if (str.contains("ARMv6")) {
            c1430a.f5017a = 16;
        } else if (str.contains("ARMv7")) {
            c1430a.f5017a = 256;
        }
        if (str.contains("neon")) {
            c1430a.f5019c |= 256;
        }
        if (str.contains("vfpv3")) {
            c1430a.f5019c |= 16;
        }
        if (str.contains(" vfp")) {
            c1430a.f5019c |= 1;
        }
        String[] split = str.split("\n");
        if (split == null || split.length <= 0) {
            return c1430a;
        }
        for (String str2 : split) {
            int indexOf;
            if (str2.contains("CPU variant")) {
                indexOf = str2.indexOf(": ");
                if (indexOf >= 0) {
                    try {
                        c1430a.f5018b = Integer.decode(str2.substring(indexOf + 2)).intValue();
                        c1430a.f5018b = c1430a.f5018b == 0 ? 1 : c1430a.f5018b;
                    } catch (NumberFormatException e) {
                        c1430a.f5018b = 1;
                    }
                }
            } else if (str2.contains("BogoMIPS")) {
                indexOf = str2.indexOf(": ");
                if (indexOf >= 0) {
                    try {
                        c1430a.f5020d = Double.parseDouble(str2.substring(indexOf + 2));
                    } catch (NumberFormatException e2) {
                        c1430a.f5020d = 0.0d;
                    }
                }
            }
        }
        return c1430a;
    }

    /* renamed from: a */
    public static String m6481a(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(readLine);
            } catch (IOException e) {
                C1425a.m6440a("StatUtils", e);
                C1403b.m6265a(inputStream, r1, bufferedReader);
            } catch (Throwable th) {
                C1403b.m6265a(inputStream, r1, bufferedReader);
            }
        }
        C1403b.m6265a(inputStream, r1, bufferedReader);
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static int[] m6482a(Context context) {
        int[] iArr = new int[3];
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            return iArr;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (i >= i2) {
            int i3 = i2;
            i2 = i;
            i = i3;
        }
        iArr[0] = i2;
        iArr[1] = i;
        iArr[2] = displayMetrics.densityDpi;
        return iArr;
    }

    /* renamed from: b */
    public static long m6483b() {
        Throwable th;
        FileReader fileReader;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                BufferedReader bufferedReader = new BufferedReader(fileReader, 8192);
                String readLine = bufferedReader.readLine();
                long intValue = readLine != null ? (long) (Integer.valueOf(readLine.split("\\s+")[1]).intValue() / 1024) : 0;
                bufferedReader.close();
                C1403b.m6265a(fileReader);
                return intValue;
            } catch (IOException e) {
                C1403b.m6265a(fileReader);
                return -1;
            } catch (Throwable th2) {
                th = th2;
                C1403b.m6265a(fileReader);
                throw th;
            }
        } catch (IOException e2) {
            fileReader = null;
            C1403b.m6265a(fileReader);
            return -1;
        } catch (Throwable th3) {
            fileReader = null;
            th = th3;
            C1403b.m6265a(fileReader);
            throw th;
        }
    }

    /* renamed from: b */
    public static String m6484b(Context context) {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null) {
                return null;
            }
            String str = null;
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    str = !inetAddress.isLoopbackAddress() ? inetAddress.getHostAddress().toString() : str;
                }
            }
            return str;
        } catch (Exception e) {
            C1425a.m6440a("StatUtils", e);
            return null;
        }
    }

    /* renamed from: c */
    public static String m6485c() {
        String str = "";
        C1430a a = C1432b.m6479a();
        return a != null ? (a.f5017a & 1) == 1 ? "armv5" : (a.f5017a & 16) == 16 ? "armv6" : (a.f5017a & 256) == 256 ? "armv7" : "unknown" : str;
    }

    /* renamed from: c */
    public static String m6486c(Context context) {
        return C1548l.m6951d(context);
    }

    /* renamed from: d */
    public static String m6487d() {
        String str = "";
        C1430a a = C1432b.m6479a();
        return a != null ? (a.f5019c & 256) == 256 ? "neon" : (a.f5019c & 1) == 1 ? "vfp" : (a.f5019c & 16) == 16 ? "vfpv3" : "unknown" : str;
    }

    /* renamed from: d */
    public static boolean m6488d(Context context) {
        return C1548l.m6948a(context);
    }

    /* renamed from: e */
    private static int m6489e() {
        BufferedReader bufferedReader;
        Throwable e;
        Object obj;
        Closeable obj2 = null;
        FileReader fileReader;
        try {
            fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
            try {
                bufferedReader = new BufferedReader(fileReader);
            } catch (Exception e2) {
                e = e2;
                bufferedReader = null;
                obj2 = fileReader;
                try {
                    C1425a.m6440a("StatUtils", e);
                    C1403b.m6265a(obj2, bufferedReader);
                    return 0;
                } catch (Throwable th) {
                    e = th;
                    Closeable fileReader2 = obj2;
                    C1403b.m6265a(fileReader2, bufferedReader);
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                bufferedReader = null;
                C1403b.m6265a(fileReader2, bufferedReader);
                throw e;
            }
            try {
                String readLine = bufferedReader.readLine();
                int parseInt = readLine != null ? Integer.parseInt(readLine.trim()) : 0;
                C1403b.m6265a(fileReader2, bufferedReader);
                return parseInt;
            } catch (Exception e3) {
                e = e3;
                obj2 = fileReader2;
                C1425a.m6440a("StatUtils", e);
                C1403b.m6265a(obj2, bufferedReader);
                return 0;
            } catch (Throwable th3) {
                e = th3;
                C1403b.m6265a(fileReader2, bufferedReader);
                throw e;
            }
        } catch (Exception e4) {
            e = e4;
            bufferedReader = null;
            C1425a.m6440a("StatUtils", e);
            C1403b.m6265a(obj2, bufferedReader);
            return 0;
        } catch (Throwable th4) {
            e = th4;
            bufferedReader = null;
            fileReader2 = null;
            C1403b.m6265a(fileReader2, bufferedReader);
            throw e;
        }
    }

    /* renamed from: e */
    public static JSONObject m6490e(Context context) {
        return C1431b.m6477a(context).mo13833a();
    }

    /* renamed from: f */
    public static JSONObject m6491f(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                jSONObject.put("type", telephonyManager.getNetworkType());
                jSONObject.put("operator", telephonyManager.getNetworkOperatorName());
            }
            jSONObject.put("access_type", C1432b.m6486c(context));
            if (telephonyManager != null) {
                String networkOperator = telephonyManager.getNetworkOperator();
                if (TextUtils.isEmpty(networkOperator) || networkOperator.length() < 4) {
                    jSONObject.put("mcc", -1);
                    jSONObject.put("mnc", -1);
                } else {
                    try {
                        jSONObject.put("mcc", Integer.parseInt(networkOperator.substring(0, 3)));
                        jSONObject.put("mnc", Integer.parseInt(networkOperator.substring(3)));
                    } catch (NumberFormatException e) {
                        jSONObject.put("mcc", -1);
                        jSONObject.put("mnc", -1);
                    }
                }
            }
            jSONObject.put("user_ip", C1432b.m6484b(context));
        } catch (JSONException e2) {
            C1425a.m6440a("StatUtils", e2);
        }
        return jSONObject;
    }
}
