package com.baidu.android.pushservice.p036h;

import android.content.Context;
import android.util.Log;
import com.baidu.android.pushservice.PushSettings;

/* renamed from: com.baidu.android.pushservice.h.b */
public class C1426b {
    /* renamed from: a */
    public static void m6445a(String str, String str2, Context context) {
        if (PushSettings.m5884c(context) && str2 != null) {
            Log.d("BDPushSDK-" + str, str2);
        }
    }

    /* renamed from: a */
    public static void m6446a(String str, Throwable th, Context context) {
        C1426b.m6447b(str, C1425a.m6437a(th), context);
    }

    /* renamed from: b */
    public static void m6447b(String str, String str2, Context context) {
        if (PushSettings.m5884c(context) && str2 != null) {
            Log.e("BDPushSDK-" + str, str2);
        }
    }

    /* renamed from: c */
    public static void m6448c(String str, String str2, Context context) {
        if (PushSettings.m5884c(context) && str2 != null) {
            Log.i("BDPushSDK-" + str, str2);
        }
    }

    /* renamed from: d */
    public static void m6449d(String str, String str2, Context context) {
        if (PushSettings.m5884c(context) && str2 != null) {
            Log.w("BDPushSDK-" + str, str2);
        }
    }
}
