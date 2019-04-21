package com.alipay.sdk.app.statistic;

import android.content.Context;

/* renamed from: com.alipay.sdk.app.statistic.a */
public class C0590a {
    /* renamed from: b */
    private static C0592c f511b;

    /* renamed from: a */
    public static void m799a(Context context) {
        if (f511b == null) {
            f511b = new C0592c(context);
        }
    }

    /* renamed from: a */
    public static void m800a(Context context, String str) {
        new Thread(new C0591b(context, str)).start();
    }

    /* renamed from: b */
    public static synchronized void m805b(Context context, String str) {
        synchronized (C0590a.class) {
            if (f511b != null) {
                C0590a.m800a(context, f511b.mo8010a(str));
                f511b = null;
            }
        }
    }

    /* renamed from: a */
    public static void m804a(String str, Throwable th) {
        if (f511b != null && th != null && th.getClass() != null) {
            f511b.mo8013a(str, th.getClass().getSimpleName(), th);
        }
    }

    /* renamed from: a */
    public static void m803a(String str, String str2, Throwable th, String str3) {
        if (f511b != null) {
            f511b.mo8014a(str, str2, th, str3);
        }
    }

    /* renamed from: a */
    public static void m802a(String str, String str2, Throwable th) {
        if (f511b != null) {
            f511b.mo8013a(str, str2, th);
        }
    }

    /* renamed from: a */
    public static void m801a(String str, String str2, String str3) {
        if (f511b != null) {
            f511b.mo8011a(str, str2, str3);
        }
    }
}
