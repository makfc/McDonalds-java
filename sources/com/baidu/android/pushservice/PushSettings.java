package com.baidu.android.pushservice;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.baidu.android.pushservice.config.ModeConfig;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1535c;
import com.baidu.android.pushservice.util.C1550n;
import com.baidu.android.pushservice.util.C1574s;
import com.baidu.android.pushservice.util.C1575t;
import com.baidu.android.pushservice.util.C1577u;
import com.baidu.android.pushservice.util.C1578v;

public class PushSettings {
    /* renamed from: a */
    private static int f4649a = -1;
    /* renamed from: b */
    private static int f4650b = -1;
    /* renamed from: c */
    private static int f4651c = -1;

    /* renamed from: a */
    public static String m5874a(Context context) {
        String a = C1535c.m6900a(context, "com.baidu.pushservice.channel_id");
        if (TextUtils.isEmpty(a)) {
            a = C1550n.m6955a(context, "com.baidu.pushservice.channel_id");
            if (TextUtils.isEmpty(a)) {
                a = C1574s.m7023c(context);
                if (TextUtils.isEmpty(a)) {
                    for (ResolveInfo resolveInfo : C1578v.m7135o(context.getApplicationContext())) {
                        a = C1575t.m7034a(context, resolveInfo.activityInfo.packageName);
                        if (!TextUtils.isEmpty(a)) {
                            m5877a(context, a);
                            break;
                        }
                    }
                }
            }
        }
        return a;
    }

    /* renamed from: a */
    public static void m5875a(Context context, int i) {
        if (context == null) {
            C1425a.m6443d("PushSettings", "setStatisticSendDisabled mContext == null");
        } else {
            C1550n.m6956a(context, "com.baidu.pushservice.sd", i);
        }
    }

    /* renamed from: a */
    public static void m5876a(Context context, long j) {
        if (context == null) {
            C1425a.m6443d("PushSettings", "setLastSendStatisticTime mContext == null");
        } else {
            C1550n.m6957a(context, "com.baidu.pushservice.cst", j);
        }
    }

    /* renamed from: a */
    protected static void m5877a(Context context, String str) {
        C1535c.m6903a(context, "com.baidu.pushservice.channel_id", str);
        C1550n.m6958a(context, "com.baidu.pushservice.channel_id", str);
        C1574s.m7019a(context, str);
    }

    /* renamed from: b */
    public static String m5878b(Context context) {
        return C1550n.m6955a(context, "com.baidu.pushservice.app_id");
    }

    /* renamed from: b */
    public static void m5879b(Context context, int i) {
        if (context == null) {
            C1425a.m6443d("PushSettings", "setCurPeriod mContext == null");
        } else {
            C1550n.m6956a(context, "com.baidu.pushservice.lsi", i * 1000);
        }
    }

    /* renamed from: b */
    public static void m5880b(Context context, long j) {
        if (context == null) {
            C1425a.m6443d("PushSettings", "setLastSendStatisticTime mContext == null");
        } else {
            C1550n.m6957a(context, "com.baidu.pushservice.st", j);
        }
    }

    /* renamed from: b */
    protected static void m5881b(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            C1550n.m6958a(context, "com.baidu.pushservice.app_id", str);
        }
    }

    /* renamed from: b */
    private static void m5882b(Context context, boolean z) {
        try {
            String packageName = context.getPackageName();
            String v = C1578v.m7149v(context);
            String str = "com.baidu.android.pushservice.action.OPENDEBUGMODE";
            if (!TextUtils.isEmpty(v) && !packageName.equals(v)) {
                if (!z) {
                    str = "com.baidu.android.pushservice.action.CLOSEDEBUGMODE";
                }
                C1577u.m7045a(context, new Intent(str));
            }
        } catch (Exception e) {
            C1425a.m6440a("PushSettings", e);
        }
    }

    /* renamed from: c */
    public static void m5883c(Context context, String str) {
        if (context == null) {
            C1425a.m6443d("PushSettings", "removeUninstalledAppLbsSwitch mContext == null");
        } else if (!TextUtils.isEmpty(str)) {
            String a = C1550n.m6955a(context, "com.baidu.pushservice.le");
            if (!TextUtils.isEmpty(a)) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String str2 : a.trim().split(",")) {
                    if (!str2.equals(str)) {
                        stringBuilder.append(str2 + ",");
                    }
                }
                C1550n.m6958a(context, "com.baidu.pushservice.le", stringBuilder.toString());
            }
        }
    }

    /* renamed from: c */
    public static boolean m5884c(Context context) {
        boolean z = true;
        if (context == null) {
            return false;
        }
        if (f4649a == -1) {
            f4649a = C1550n.m6963d(context, "com.baidu.android.pushservice.PushSettings.debug_mode", -1);
        }
        if (f4649a != 1) {
            z = false;
        }
        return z;
    }

    /* renamed from: d */
    public static long m5885d(Context context) {
        if (context != null) {
            return C1550n.m6961b(context, "com.baidu.pushservice.cst");
        }
        C1425a.m6444e("PushSettings", "getLastSendStatisticTime mContext == null");
        return 0;
    }

    /* renamed from: e */
    public static long m5886e(Context context) {
        long b = C1550n.m6961b(context, "com.baidu.pushservice.st");
        return b <= 0 ? 43200000 : b;
    }

    public static void enableDebugMode(Context context, boolean z) {
        if (z) {
            C1550n.m6962c(context, "com.baidu.android.pushservice.PushSettings.debug_mode", 1);
        } else {
            C1550n.m6962c(context, "com.baidu.android.pushservice.PushSettings.debug_mode", 0);
        }
        if (!ModeConfig.isProxyMode(context)) {
            m5882b(context, z);
        }
    }

    /* renamed from: f */
    public static boolean m5887f(Context context) {
        return C1550n.m6960b(context, "com.baidu.pushservice.sd", 0) == 1;
    }

    /* renamed from: g */
    public static int m5888g(Context context) {
        if (context == null) {
            C1425a.m6444e("PushSettings", "getLbsSendInterval mContext == null");
            return 0;
        }
        int b = C1550n.m6960b(context, "com.baidu.pushservice.lsi", -1);
        return b < 0 ? 1800000 : b;
    }

    /* renamed from: h */
    public static boolean m5889h(Context context) {
        return !TextUtils.isEmpty(C1550n.m6955a(context, "com.baidu.pushservice.le"));
    }

    /* renamed from: i */
    public static boolean m5890i(Context context) {
        return TextUtils.equals(C1550n.m6955a(context, "com.baidu.pushservice.lms"), "off");
    }

    /* renamed from: j */
    public static void m5891j(Context context) {
        if (context == null) {
            C1425a.m6443d("PushSettings", "tofms mContext == null");
        }
        C1550n.m6958a(context, "com.baidu.pushservice.lms", "off");
    }

    /* renamed from: k */
    public static void m5892k(Context context) {
        if (context == null) {
            C1425a.m6443d("PushSettings", "toms mContext == null");
        }
        C1550n.m6958a(context, "com.baidu.pushservice.lms", "");
    }

    /* renamed from: l */
    public static void m5893l(Context context) {
        if (context == null) {
            C1425a.m6443d("PushSettings", "refreshLbsSwitchInfo mContext == null");
            return;
        }
        String a = C1550n.m6955a(context, "com.baidu.pushservice.le");
        if (!TextUtils.isEmpty(a)) {
            StringBuilder stringBuilder = new StringBuilder();
            String[] split = a.trim().split(",");
            PackageManager packageManager = context.getPackageManager();
            for (String str : split) {
                PackageInfo packageInfo = null;
                try {
                    packageInfo = packageManager.getPackageInfo(str, 0);
                } catch (NameNotFoundException e) {
                    C1425a.m6443d("PushSettings", C1425a.m6437a(e));
                }
                if (packageInfo != null) {
                    stringBuilder.append(str + ",");
                }
            }
            C1550n.m6958a(context, "com.baidu.pushservice.le", stringBuilder.toString());
        }
    }

    /* renamed from: m */
    public static boolean m5894m(Context context) {
        boolean z = true;
        if (context == null) {
            return false;
        }
        if (f4650b == -1) {
            f4650b = C1550n.m6960b(context, "com.baidu.android.pushservice.PushSettings.xm_proxy_mode", -1);
        }
        if (f4650b != 1) {
            z = false;
        }
        return z;
    }

    /* renamed from: n */
    public static boolean m5895n(Context context) {
        boolean z = true;
        if (context == null) {
            return false;
        }
        if (f4651c == -1) {
            f4651c = C1550n.m6960b(context, "com.baidu.android.pushservice.PushSettings.hw_proxy_mode", -1);
        }
        if (f4651c != 1) {
            z = false;
        }
        return z;
    }
}
