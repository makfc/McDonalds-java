package com.baidu.android.pushservice.util;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.baidu.android.pushservice.p036h.C1425a;

/* renamed from: com.baidu.android.pushservice.util.w */
public class C1579w {
    /* renamed from: a */
    public static String m7157a(Context context, String str, String str2) {
        return context.getSharedPreferences(str, 5).getString(str2, "");
    }

    /* renamed from: a */
    public static void m7158a(Context context, String str, String str2, String str3) {
        try {
            Editor edit = context.getSharedPreferences(str, 5).edit();
            edit.putString(str2, str3);
            edit.commit();
        } catch (Exception e) {
            C1425a.m6440a("WorldReadableParams", e);
        }
    }
}
