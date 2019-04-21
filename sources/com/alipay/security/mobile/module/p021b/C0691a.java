package com.alipay.security.mobile.module.p021b;

import android.content.Context;

/* renamed from: com.alipay.security.mobile.module.b.a */
public final class C0691a {
    /* renamed from: a */
    private static C0691a f724a = new C0691a();

    private C0691a() {
    }

    /* renamed from: a */
    public static C0691a m1179a() {
        return f724a;
    }

    /* renamed from: a */
    public static String m1180a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16).versionName;
        } catch (Exception e) {
            return "0.0.0";
        }
    }
}
