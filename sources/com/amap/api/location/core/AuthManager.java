package com.amap.api.location.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.util.Log;
import com.aps.NetManager;
import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.json.JSONObject;

/* renamed from: com.amap.api.location.core.a */
public class AuthManager {
    /* renamed from: a */
    static String f894a = "";
    /* renamed from: b */
    private static int f895b = -1;
    /* renamed from: c */
    private static Context f896c;
    /* renamed from: d */
    private static String f897d = "提示信息";
    /* renamed from: e */
    private static String f898e = "确认";
    /* renamed from: f */
    private static String f899f = "取消";
    /* renamed from: g */
    private static String f900g = "";
    /* renamed from: h */
    private static String f901h = "";
    /* renamed from: i */
    private static String f902i = "";
    /* renamed from: j */
    private static String f903j = "1";
    /* renamed from: k */
    private static String f904k = "0";
    /* renamed from: l */
    private static SharedPreferences f905l = null;
    /* renamed from: m */
    private static Editor f906m = null;
    /* renamed from: n */
    private static Method f907n;

    /* renamed from: a */
    public static int m1397a() {
        return f895b;
    }

    /* renamed from: a */
    public static synchronized boolean m1401a(Context context) {
        boolean e;
        synchronized (AuthManager.class) {
            try {
                byte[] f = AuthManager.m1413f("resType=json&encode=UTF-8&key=" + ClientInfoUtil.m1422a());
                String a = NetManager.m5684a().mo13279a(context, AuthManager.m1417j(), f, "loc");
                if (a != null) {
                    e = AuthManager.m1411e(a);
                } else {
                    f895b = 0;
                    e = true;
                }
                if (f895b != 1) {
                    f895b = 0;
                }
            } catch (Throwable th) {
                if (f895b != 1) {
                    f895b = 0;
                }
            }
        }
        return e;
    }

    /* renamed from: b */
    public static synchronized boolean m1404b(Context context) {
        boolean z;
        synchronized (AuthManager.class) {
            f896c = context;
            z = true;
            try {
                byte[] f = AuthManager.m1413f("resType=json&encode=UTF-8&opertype=callamap;fast&output=json&key=" + ClientInfoUtil.m1422a());
                String a = NetManager.m5684a().mo13279a(context, AuthManager.m1418k(), f, "lswu");
                if (a != null) {
                    z = AuthManager.m1409d(a);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return z;
    }

    /* renamed from: b */
    public static String m1402b() {
        return f897d;
    }

    /* renamed from: c */
    public static String m1406c() {
        return f898e;
    }

    /* renamed from: d */
    public static String m1408d() {
        return f899f;
    }

    /* renamed from: e */
    public static String m1410e() {
        return f900g;
    }

    /* renamed from: f */
    public static String m1412f() {
        return f901h;
    }

    /* renamed from: g */
    public static String m1414g() {
        return f902i;
    }

    /* renamed from: d */
    private static boolean m1409d(String str) {
        try {
            JSONObject init = JSONObjectInstrumentation.init(str);
            if (init.has("result")) {
                init = init.getJSONObject("result");
                if (init.has("callamap")) {
                    JSONObject jSONObject = init.getJSONObject("callamap");
                    if (jSONObject.has("callamapflag")) {
                        f903j = jSONObject.getString("callamapflag");
                    }
                }
                if (init.has("ca")) {
                    init = init.getJSONObject("ca");
                    if (init.has("f")) {
                        f904k = init.getString("f");
                        if ("1".equals(f904k)) {
                            long c = AuthManager.m1405c(f896c);
                            long elapsedRealtime = SystemClock.elapsedRealtime();
                            if (elapsedRealtime - c > 3600000) {
                                AuthManager.m1399a(f896c, elapsedRealtime);
                            }
                            if (elapsedRealtime > c && elapsedRealtime - c < 3600000) {
                                f904k = "0";
                            }
                            if (elapsedRealtime < c) {
                                f904k = "0";
                                AuthManager.m1399a(f896c, elapsedRealtime);
                            }
                        } else {
                            f904k = "0";
                        }
                    }
                    if (init.has("a")) {
                        f897d = init.getString("a");
                    }
                    if (init.has("o")) {
                        f898e = init.getString("o");
                    }
                    if (init.has("c")) {
                        f899f = init.getString("c");
                    }
                    if (init.has("i")) {
                        f900g = init.getString("i");
                    }
                    if (init.has("u")) {
                        f901h = init.getString("u");
                    }
                    if (init.has("t")) {
                        f902i = init.getString("t");
                    }
                    if (("".equals(f900g) || f900g == null) && ("".equals(f901h) || f901h == null)) {
                        f904k = "0";
                    }
                }
            }
            return true;
        } catch (Throwable th) {
            f904k = "0";
            th.printStackTrace();
            return false;
        }
    }

    /* renamed from: j */
    private static String m1417j() {
        return "http://apiinit.amap.com/v3/log/init";
    }

    /* renamed from: k */
    private static String m1418k() {
        return "http://restapi.amap.com/v3/fastconnect?";
    }

    /* renamed from: e */
    private static boolean m1411e(String str) {
        try {
            JSONObject init = JSONObjectInstrumentation.init(str);
            if (init.has("status")) {
                int i = init.getInt("status");
                if (i == 1) {
                    f895b = 1;
                } else if (i == 0) {
                    f895b = 0;
                }
            }
            if (init.has("info")) {
                f894a = init.getString("info");
            }
            if (f895b == 0) {
                Log.i("AuthFailure", f894a);
            }
        } catch (Exception e) {
            e.printStackTrace();
            f895b = 0;
        }
        if (f895b == 1) {
            return true;
        }
        return false;
    }

    /* renamed from: h */
    public static boolean m1415h() {
        return "1".equals(f903j);
    }

    /* renamed from: f */
    private static byte[] m1413f(String str) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            return AuthManager.m1403b(AuthManager.m1398a(stringBuffer.toString())).toString().getBytes(Utf8Charset.NAME);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m1398a(String str) {
        String[] split = str.split("&");
        Arrays.sort(split);
        StringBuffer stringBuffer = new StringBuffer();
        for (String append : split) {
            stringBuffer.append(append);
            stringBuffer.append("&");
        }
        String stringBuffer2 = stringBuffer.toString();
        if (stringBuffer2.length() > 1) {
            return (String) stringBuffer2.subSequence(0, stringBuffer2.length() - 1);
        }
        return str;
    }

    /* renamed from: b */
    public static String m1403b(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        String a = CoreUtil.m1455a();
        stringBuffer.append("&ts=" + a);
        stringBuffer.append("&scode=" + CoreUtil.m1456a(a, str));
        return stringBuffer.toString();
    }

    /* renamed from: i */
    public static boolean m1416i() {
        return "1".equals(f904k);
    }

    /* renamed from: c */
    public static void m1407c(String str) {
        f904k = str;
    }

    /* renamed from: a */
    public static void m1399a(Context context, long j) {
        try {
            if (f905l == null) {
                f905l = context.getSharedPreferences("abcd", 0);
            }
            if (f906m == null) {
                f906m = f905l.edit();
            }
            f906m.putLong("abc", j);
            AuthManager.m1400a(f906m);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: c */
    public static long m1405c(Context context) {
        long j = 0;
        try {
            return context.getSharedPreferences("abcd", 0).getLong("abc", 0);
        } catch (Throwable th) {
            return j;
        }
    }

    /* renamed from: a */
    private static void m1400a(Editor editor) {
        if (editor != null) {
            if (VERSION.SDK_INT >= 9) {
                try {
                    if (f907n == null) {
                        f907n = Editor.class.getDeclaredMethod("apply", new Class[0]);
                    }
                    f907n.invoke(editor, new Object[0]);
                    return;
                } catch (Throwable th) {
                    th.printStackTrace();
                    editor.commit();
                    return;
                }
            }
            editor.commit();
        }
    }
}
