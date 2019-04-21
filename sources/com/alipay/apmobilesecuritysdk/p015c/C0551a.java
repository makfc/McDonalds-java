package com.alipay.apmobilesecuritysdk.p015c;

import android.content.Context;
import android.os.Build;
import com.alipay.security.mobile.module.p023d.C0701a;
import com.alipay.security.mobile.module.p023d.C0704d;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* renamed from: com.alipay.apmobilesecuritysdk.c.a */
public final class C0551a {
    /* renamed from: a */
    public static synchronized void m642a(Context context, String str, String str2, String str3) {
        synchronized (C0551a.class) {
            C0701a b = C0551a.m645b(context, str, str2, str3);
            C0704d.m1261a(context.getFilesDir().getAbsolutePath() + "/log/ap", new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".log", b.toString());
        }
    }

    /* renamed from: a */
    public static synchronized void m643a(String str) {
        synchronized (C0551a.class) {
            C0704d.m1260a(str);
        }
    }

    /* renamed from: a */
    public static synchronized void m644a(Throwable th) {
        synchronized (C0551a.class) {
            C0704d.m1262a(th);
        }
    }

    /* renamed from: b */
    private static C0701a m645b(Context context, String str, String str2, String str3) {
        String str4 = "";
        try {
            str4 = context.getPackageName();
        } catch (Throwable th) {
        }
        return new C0701a(Build.MODEL, str4, "APPSecuritySDK-ALIPAY", "3.2.2-20180331", str, str2, str3);
    }
}
