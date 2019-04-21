package com.baidu.android.pushservice.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.baidu.android.pushservice.C1457i;
import com.baidu.android.pushservice.p034f.C1402a;
import com.baidu.android.pushservice.p034f.C1403b;
import com.baidu.android.pushservice.p036h.C1425a;

/* renamed from: com.baidu.android.pushservice.util.l */
public class C1548l {
    /* renamed from: a */
    private static ConnectivityManager f5403a = null;

    /* renamed from: a */
    public static boolean m6948a(Context context) {
        NetworkInfo c = C1548l.m6950c(context);
        return c != null ? c.isConnectedOrConnecting() : false;
    }

    /* renamed from: b */
    public static boolean m6949b(Context context) {
        NetworkInfo c = C1548l.m6950c(context);
        return c != null && c.getType() == 1;
    }

    /* renamed from: c */
    public static NetworkInfo m6950c(Context context) {
        NetworkInfo networkInfo = null;
        try {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                C1425a.m6443d("NetworkCheck", "context is null !!!");
            }
            ConnectivityManager f = C1548l.m6953f(applicationContext);
            if (f != null) {
                networkInfo = f.getActiveNetworkInfo();
                if (networkInfo == null) {
                    C1425a.m6443d("NetworkCheck", "networkInfo is null !!!");
                }
            } else {
                C1425a.m6443d("NetworkCheck", "connManager is null !!!");
            }
        } catch (Exception e) {
            C1425a.m6444e("NetworkCheck", "exp: " + e.getMessage());
        }
        return networkInfo;
    }

    /* renamed from: d */
    public static String m6951d(Context context) {
        String str = "connectionless";
        if (!C1548l.m6948a(context)) {
            return str;
        }
        NetworkInfo c = C1548l.m6950c(context);
        int i = -1;
        if (c != null) {
            i = c.getType();
        }
        switch (i) {
            case 0:
                return "mobile";
            case 1:
                return "wifi";
            case 2:
                return "mobile_mms";
            case 3:
                return "mobile_supl";
            case 4:
                return "mobile_dun";
            case 5:
                return "mobile_hipri";
            case 6:
                return "wimax";
            default:
                return str;
        }
    }

    /* renamed from: e */
    public static boolean m6952e(Context context) {
        boolean a = C1548l.m6948a(context);
        if (a || !C1578v.m7142r(context, "android.permission.INTERNET")) {
            return a;
        }
        try {
            C1402a a2 = C1403b.m6259a(C1457i.m6620a(), "GET", null);
            return (a2.mo13745b() == 0 || a2.mo13742a() == null) ? a : true;
        } catch (Exception e) {
            return a;
        }
    }

    /* renamed from: f */
    private static ConnectivityManager m6953f(Context context) {
        if (context == null) {
            return f5403a;
        }
        if (f5403a == null) {
            f5403a = (ConnectivityManager) context.getSystemService("connectivity");
        }
        return f5403a;
    }
}
