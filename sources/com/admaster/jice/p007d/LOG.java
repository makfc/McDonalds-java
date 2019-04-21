package com.admaster.jice.p007d;

import android.util.Log;
import com.admaster.jice.p005b.HttpConfig;

/* renamed from: com.admaster.jice.d.d */
public class LOG {
    /* renamed from: a */
    public static void m224a(String str, String str2) {
        if (HttpConfig.m158a()) {
            Log.e(str, str2);
        }
    }

    /* renamed from: a */
    public static void m225a(String str, String str2, Throwable th) {
        if (HttpConfig.m158a()) {
            Log.e(str, str2, th);
        }
    }

    /* renamed from: b */
    public static void m226b(String str, String str2) {
        if (HttpConfig.m158a()) {
            Log.d(str, str2);
        }
    }

    /* renamed from: c */
    public static void m228c(String str, String str2) {
        if (HttpConfig.m158a()) {
            Log.w(str, str2);
        }
    }

    /* renamed from: b */
    public static void m227b(String str, String str2, Throwable th) {
        if (HttpConfig.m158a()) {
            Log.w(str, str2, th);
        }
    }
}
