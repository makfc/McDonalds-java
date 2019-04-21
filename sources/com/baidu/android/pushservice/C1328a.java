package com.baidu.android.pushservice;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.baidu.android.pushservice.p036h.C1425a;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.baidu.android.pushservice.a */
public final class C1328a {
    /* renamed from: a */
    private static int f4706a = 1;

    /* renamed from: a */
    public static short m6003a() {
        return (short) 54;
    }

    /* renamed from: a */
    public static void m6004a(Context context, boolean z) {
        int i = 54;
        if (z) {
            i = 0;
        }
        Editor edit = context.getSharedPreferences("pst", 4).edit();
        edit.putInt("com.baidu.push.nd_restart", i);
        edit.commit();
    }

    /* renamed from: a */
    public static boolean m6005a(Context context) {
        return PushSettings.m5884c(context);
    }

    /* renamed from: b */
    public static int m6006b() {
        if (f4706a != 0) {
            try {
                if (new Date().getTime() - new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-08-07 14:37:04").getTime() > 0) {
                    f4706a = 0;
                }
            } catch (Exception e) {
                f4706a = 0;
                C1425a.m6440a("Constants", e);
            }
        }
        return f4706a;
    }

    /* renamed from: b */
    public static void m6007b(Context context, boolean z) {
        String str;
        SharedPreferences sharedPreferences = context.getSharedPreferences("pst", 4);
        if (z) {
            if ("disabled".equals(C1328a.m6009c(context))) {
                C1328a.m6004a(context, true);
            }
            str = "enabled";
        } else {
            str = "disabled";
        }
        Editor edit = sharedPreferences.edit();
        edit.putString("s_e", str);
        edit.commit();
    }

    /* renamed from: b */
    public static boolean m6008b(Context context) {
        return 54 > context.getSharedPreferences("pst", 4).getInt("com.baidu.push.nd_restart", 0);
    }

    /* renamed from: c */
    public static String m6009c(Context context) {
        return context.getSharedPreferences("pst", 4).getString("s_e", "default");
    }
}
