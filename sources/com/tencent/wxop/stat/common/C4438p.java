package com.tencent.wxop.stat.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/* renamed from: com.tencent.wxop.stat.common.p */
public class C4438p {
    /* renamed from: a */
    private static SharedPreferences f7164a = null;

    /* renamed from: a */
    public static int m8149a(Context context, String str, int i) {
        return C4438p.m8151a(context).getInt(C4433k.m8104a(context, "wxop_" + str), i);
    }

    /* renamed from: a */
    public static long m8150a(Context context, String str, long j) {
        return C4438p.m8151a(context).getLong(C4433k.m8104a(context, "wxop_" + str), j);
    }

    /* renamed from: a */
    static synchronized SharedPreferences m8151a(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (C4438p.class) {
            sharedPreferences = context.getSharedPreferences(".mta-wxop", 0);
            f7164a = sharedPreferences;
            if (sharedPreferences == null) {
                f7164a = PreferenceManager.getDefaultSharedPreferences(context);
            }
            sharedPreferences = f7164a;
        }
        return sharedPreferences;
    }

    /* renamed from: a */
    public static String m8152a(Context context, String str, String str2) {
        return C4438p.m8151a(context).getString(C4433k.m8104a(context, "wxop_" + str), str2);
    }

    /* renamed from: b */
    public static void m8153b(Context context, String str, int i) {
        String a = C4433k.m8104a(context, "wxop_" + str);
        Editor edit = C4438p.m8151a(context).edit();
        edit.putInt(a, i);
        edit.commit();
    }

    /* renamed from: b */
    public static void m8154b(Context context, String str, long j) {
        String a = C4433k.m8104a(context, "wxop_" + str);
        Editor edit = C4438p.m8151a(context).edit();
        edit.putLong(a, j);
        edit.commit();
    }

    /* renamed from: b */
    public static void m8155b(Context context, String str, String str2) {
        String a = C4433k.m8104a(context, "wxop_" + str);
        Editor edit = C4438p.m8151a(context).edit();
        edit.putString(a, str2);
        edit.commit();
    }
}
