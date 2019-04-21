package com.alipay.android.phone.mrpc.core;

import android.content.Context;

/* renamed from: com.alipay.android.phone.mrpc.core.s */
public final class C0545s {
    /* renamed from: a */
    private static Boolean f398a = null;

    /* renamed from: a */
    public static final boolean m627a(Context context) {
        if (f398a != null) {
            return f398a.booleanValue();
        }
        try {
            Boolean valueOf = Boolean.valueOf((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 2) != 0);
            f398a = valueOf;
            return valueOf.booleanValue();
        } catch (Exception e) {
            return false;
        }
    }
}
