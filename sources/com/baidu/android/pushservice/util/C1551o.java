package com.baidu.android.pushservice.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.baidu.android.pushservice.p036h.C1425a;

/* renamed from: com.baidu.android.pushservice.util.o */
public class C1551o {
    /* renamed from: a */
    public static String m6964a(Context context) {
        return context.getSharedPreferences("push_client_self_info", 4).getString("bd_use_huawei_token", null);
    }

    /* renamed from: a */
    public static void m6965a(Context context, String str) {
        int i = 20;
        try {
            Editor edit;
            SharedPreferences sharedPreferences = context.getSharedPreferences("push_client_self_info", 4);
            do {
                edit = sharedPreferences.edit();
                i--;
                if (edit != null) {
                    break;
                }
            } while (i > 0);
            edit.putString("bd_use_huawei_token", str).commit();
        } catch (Exception e) {
            C1425a.m6440a("ProxySharepreferences", e);
        }
    }

    /* renamed from: a */
    public static void m6966a(Context context, String str, boolean z) {
        int i = 20;
        try {
            Editor edit;
            SharedPreferences sharedPreferences = context.getSharedPreferences("push_client_self_info", 4);
            do {
                edit = sharedPreferences.edit();
                i--;
                if (edit != null) {
                    break;
                }
            } while (i > 0);
            edit.putBoolean(str, z).commit();
        } catch (Exception e) {
            C1425a.m6440a("ProxySharepreferences", e);
        }
    }

    /* renamed from: b */
    public static String m6967b(Context context) {
        return context.getSharedPreferences("push_client_self_info", 4).getString("bd_use_xiaomi_regid", null);
    }

    /* renamed from: b */
    public static synchronized void m6968b(Context context, String str) {
        synchronized (C1551o.class) {
            int i = 5;
            try {
                Editor edit;
                SharedPreferences sharedPreferences = context.getSharedPreferences("push_client_self_info", 4);
                do {
                    edit = sharedPreferences.edit();
                    i--;
                    if (edit != null) {
                        break;
                    }
                } while (i > 0);
                edit.putString("bd_use_xiaomi_regid", str).commit();
            } catch (Exception e) {
                C1425a.m6440a("ProxySharepreferences", e);
            }
        }
        return;
    }

    /* renamed from: c */
    public static void m6969c(Context context) {
        int i = 5;
        try {
            Editor edit;
            SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
            do {
                edit = sharedPreferences.edit();
                i--;
                if (edit != null) {
                    break;
                }
            } while (i > 0);
            edit.putLong("last_reg_request", -1).commit();
        } catch (Exception e) {
            C1425a.m6440a("ProxySharepreferences", e);
        }
    }
}
