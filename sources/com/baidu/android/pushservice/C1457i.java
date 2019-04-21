package com.baidu.android.pushservice;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.text.TextUtils;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p034f.C1403b;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1578v;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Properties;

/* renamed from: com.baidu.android.pushservice.i */
public final class C1457i {
    /* renamed from: a */
    public static int f5136a = 5287;
    /* renamed from: b */
    public static String[] f5137b = new String[]{"202.108.23.105", "180.149.132.107", "111.13.12.162", "180.149.131.209", "111.13.12.110", "111.13.100.86", " 111.13.100.85", " 61.135.185.18", "220.181.163.183", "220.181.163.182", " 115.239.210.219", "115.239.210.246"};
    /* renamed from: c */
    public static String f5138c = "http://m.baidu.com";
    /* renamed from: d */
    public static final String f5139d = f5147l;
    /* renamed from: e */
    public static final String f5140e = (f5139d + "/searchbox?action=publicsrv&type=issuedcode");
    /* renamed from: f */
    private static String f5141f = "api.tuisong.baidu.com";
    /* renamed from: g */
    private static String[] f5142g = new String[]{"api0.tuisong.baidu.com", "api1.tuisong.baidu.com", "api2.tuisong.baidu.com", "api3.tuisong.baidu.com", "api4.tuisong.baidu.com", "api5.tuisong.baidu.com", "api6.tuisong.baidu.com", "api7.tuisong.baidu.com", "api8.tuisong.baidu.com", "api9.tuisong.baidu.com"};
    /* renamed from: h */
    private static String f5143h = "sa.tuisong.baidu.com";
    /* renamed from: i */
    private static String[] f5144i = new String[]{"sa0.tuisong.baidu.com", "sa1.tuisong.baidu.com", "sa2.tuisong.baidu.com", "sa3.tuisong.baidu.com", "sa4.tuisong.baidu.com", "sa5.tuisong.baidu.com", "sa6.tuisong.baidu.com", "sa7.tuisong.baidu.com", "sa8.tuisong.baidu.com", "sa9.tuisong.baidu.com"};
    /* renamed from: j */
    private static final String[] f5145j = new String[]{"202.108.23.109", "180.149.132.103", "111.13.12.174", "111.13.12.61"};
    /* renamed from: k */
    private static boolean f5146k = true;
    /* renamed from: l */
    private static String f5147l = "http://m.baidu.com";
    /* renamed from: m */
    private static ArrayList<String> f5148m = null;
    /* renamed from: n */
    private static ArrayList<String> f5149n = null;

    /* renamed from: a */
    public static String m6620a() {
        return "http://" + f5141f;
    }

    /* renamed from: a */
    public static String m6621a(Context context, boolean z) {
        if (f5149n == null || f5149n.isEmpty() || f5146k) {
            f5149n = C1457i.m6622a(context, ".baidu.push.sa");
        }
        if (f5149n != null && f5149n.size() > 0) {
            if (!z) {
                f5149n.remove(0);
            }
            if (f5149n.size() > 0) {
                return (String) f5149n.get(0);
            }
        }
        return null;
    }

    /* renamed from: a */
    private static ArrayList<String> m6622a(Context context, String str) {
        int i = 0;
        ArrayList b = C1457i.m6627b(context, str);
        if (b.size() <= 0) {
            String[] strArr;
            int length;
            if (str.equals(".baidu.push.http")) {
                strArr = f5145j;
                length = strArr.length;
                while (i < length) {
                    b.add(strArr[i]);
                    i++;
                }
            } else {
                strArr = f5137b;
                length = strArr.length;
                while (i < length) {
                    b.add(strArr[i]);
                    i++;
                }
            }
            f5146k = true;
            C1457i.m6628b(context);
        } else {
            f5146k = false;
        }
        return b;
    }

    /* renamed from: a */
    public static void m6623a(Context context) {
        Throwable e;
        File file = new File(Environment.getExternalStorageDirectory(), "pushservice.cfg");
        if (file.exists()) {
            Properties properties = new Properties();
            FileInputStream fileInputStream;
            try {
                if (C1578v.m7142r(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                    fileInputStream = new FileInputStream(file);
                    try {
                        properties.load(fileInputStream);
                        String property = properties.getProperty("http_server");
                        if (!TextUtils.isEmpty(property)) {
                            if (property.startsWith("http://")) {
                                property = property.replace("http://", "");
                            }
                            f5141f = property;
                        }
                        property = properties.getProperty("socket_server");
                        if (!TextUtils.isEmpty(property)) {
                            f5143h = property;
                        }
                        property = properties.getProperty("socket_server_port");
                        if (!TextUtils.isEmpty(property)) {
                            f5136a = Integer.parseInt(property);
                        }
                        property = properties.getProperty("config_server");
                        if (!TextUtils.isEmpty(property)) {
                            f5147l = property;
                        }
                        if (!TextUtils.isEmpty(properties.getProperty("lightapp_server"))) {
                            f5138c = property;
                        }
                        C1403b.m6265a(fileInputStream);
                        return;
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            C1425a.m6440a("PushUrl", e);
                            C1403b.m6265a(fileInputStream);
                            return;
                        } catch (Throwable th) {
                            e = th;
                            C1403b.m6265a(fileInputStream);
                            throw e;
                        }
                    }
                }
                C1403b.m6265a(null);
                return;
            } catch (Exception e3) {
                e = e3;
                fileInputStream = null;
            } catch (Throwable th2) {
                e = th2;
                fileInputStream = null;
                C1403b.m6265a(fileInputStream);
                throw e;
            }
        }
        String a = PushSettings.m5874a(context);
        if (!TextUtils.isEmpty(a) && a.length() > 0) {
            try {
                int parseInt = Integer.parseInt(a.substring(a.length() - 1));
                f5141f = f5142g[parseInt % 10];
                f5143h = f5144i[parseInt % 10];
            } catch (Exception e4) {
                C1425a.m6439a("PushUrl", "parseInt err: " + a, e4);
            }
        }
    }

    /* renamed from: b */
    public static String m6625b() {
        return "https://" + f5141f;
    }

    /* renamed from: b */
    public static String m6626b(Context context, boolean z) {
        if (f5148m == null || f5148m.isEmpty()) {
            f5148m = C1457i.m6622a(context, ".baidu.push.http");
        }
        if (f5148m != null && f5148m.size() > 0) {
            if (!z) {
                f5148m.remove(0);
            }
            if (f5148m.size() > 0) {
                return (String) f5148m.get(0);
            }
        }
        return null;
    }

    /* renamed from: b */
    private static ArrayList<String> m6627b(Context context, String str) {
        int i = 0;
        ArrayList arrayList = new ArrayList();
        String string = context.getSharedPreferences("pst", 0).getString(str, null);
        if (!TextUtils.isEmpty(string)) {
            String[] split = string.split(":");
            if (split != null && split.length > 0) {
                while (i < split.length) {
                    arrayList.add(split[i]);
                    i++;
                }
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public static void m6628b(final Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("pst", 0);
        long j = sharedPreferences.getLong(".baidu.push.dns.refresh", 0);
        long currentTimeMillis = System.currentTimeMillis();
        C1425a.m6442c("PushUrl", " update last: " + j + " current: " + currentTimeMillis);
        if (currentTimeMillis - j > 86400000) {
            C1462d.m6637a().mo13938a(new C1281c("updateBackupIp", (short) 95) {
                /* renamed from: a */
                public void mo13487a() {
                    boolean a = C1457i.m6629b(context.getApplicationContext(), C1457i.f5143h, ".baidu.push.sa");
                    boolean a2 = C1457i.m6629b(context.getApplicationContext(), C1457i.f5141f, ".baidu.push.http");
                    if (a && a2) {
                        Editor edit = sharedPreferences.edit();
                        edit.putLong(".baidu.push.dns.refresh", System.currentTimeMillis());
                        edit.commit();
                    }
                }
            });
        }
    }

    /* renamed from: b */
    private static boolean m6629b(Context context, String str, String str2) {
        boolean z = false;
        try {
            if (str.startsWith("http://")) {
                str = str.replace("http://", "");
            }
            InetAddress[] allByName = InetAddress.getAllByName(str);
            if (!(context == null || allByName == null || allByName.length <= 0)) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("pst", 0);
                String str3 = "";
                int length = allByName.length;
                int i = 0;
                while (i < length) {
                    i++;
                    str3 = str3 + ":" + allByName[i].getHostAddress();
                }
                if (str3.length() > 1) {
                    str3 = str3.substring(1);
                    Editor edit = sharedPreferences.edit();
                    edit.putString(str2, str3);
                    edit.commit();
                    z = true;
                }
                C1425a.m6442c("PushUrl", "  --- write bck " + str2 + " : " + str3);
            }
        } catch (Exception e) {
            C1425a.m6444e("PushUrl", "  --- write bck Exception " + e);
        }
        return z;
    }

    /* renamed from: c */
    public static String m6630c() {
        return f5143h;
    }

    /* renamed from: d */
    public static int m6631d() {
        return f5136a;
    }

    /* renamed from: e */
    public static String m6632e() {
        return C1457i.m6625b() + "/rest/2.0/channel/channel";
    }

    /* renamed from: f */
    public static String m6633f() {
        return C1457i.m6625b() + "/rest/2.0/channel/";
    }
}
