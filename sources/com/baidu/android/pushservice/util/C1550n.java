package com.baidu.android.pushservice.util;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.baidu.android.pushservice.p036h.C1425a;

/* renamed from: com.baidu.android.pushservice.util.n */
public class C1550n {
    /* renamed from: a */
    public static String m6955a(Context context, String str) {
        return context.getSharedPreferences("pst", 0).getString(str, "");
    }

    /* renamed from: a */
    public static void m6956a(Context context, String str, int i) {
        try {
            Editor edit = context.getSharedPreferences("pst", 0).edit();
            edit.putInt(str, i);
            edit.commit();
        } catch (Exception e) {
            C1425a.m6440a("PrivateParams", e);
        }
    }

    /* renamed from: a */
    public static void m6957a(Context context, String str, long j) {
        try {
            Editor edit = context.getSharedPreferences("pst", 0).edit();
            edit.putLong(str, j);
            edit.commit();
        } catch (Exception e) {
            C1425a.m6440a("PrivateParams", e);
        }
    }

    /* renamed from: a */
    public static void m6958a(Context context, String str, String str2) {
        try {
            Editor edit = context.getSharedPreferences("pst", 4).edit();
            edit.putString(str, str2);
            edit.commit();
        } catch (Exception e) {
            C1425a.m6440a("PrivateParams", e);
        }
    }

    /* renamed from: a */
    public static void m6959a(Context context, String str, String str2, String str3, String str4, boolean z, int i, long j, String str5, String str6) {
        try {
            Editor edit = context.getSharedPreferences("com.baidu.pushservice.BIND_CACHE", 0).edit();
            if (j != 0) {
                edit.putLong("currbindtime", j);
            }
            if (!TextUtils.isEmpty(str5)) {
                edit.putString("access_token", str5);
            }
            if (!TextUtils.isEmpty(str6)) {
                edit.putString("secret_key", str6);
            }
            edit.putString("appid", str);
            edit.putString("channel_id", str2);
            edit.putString("request_id", str3);
            edit.putString("user_id", str4);
            edit.putBoolean("bind_status", true);
            edit.putLong("version_code", (long) C1578v.m7108d(context, context.getPackageName()));
            edit.commit();
        } catch (Exception e) {
            C1425a.m6440a("PrivateParams", e);
        }
    }

    /* renamed from: b */
    public static int m6960b(Context context, String str, int i) {
        return context.getSharedPreferences("pst", 0).getInt(str, i);
    }

    /* renamed from: b */
    public static long m6961b(Context context, String str) {
        return context.getSharedPreferences("pst", 0).getLong(str, 0);
    }

    /* renamed from: c */
    public static void m6962c(Context context, String str, int i) {
        try {
            Editor edit = context.getSharedPreferences("pst", 4).edit();
            edit.putInt(str, i);
            edit.commit();
        } catch (Exception e) {
            C1425a.m6440a("PrivateParams", e);
        }
    }

    /* renamed from: d */
    public static int m6963d(Context context, String str, int i) {
        return context.getSharedPreferences("pst", 4).getInt(str, i);
    }
}
