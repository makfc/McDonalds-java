package com.alipay.sdk.util;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.C0590a;
import com.alipay.sdk.encrypt.C0623e;

/* renamed from: com.alipay.sdk.util.i */
public class C0652i {
    /* renamed from: a */
    private static String f661a = null;

    /* renamed from: b */
    public static void m1037b(Context context, String str) {
        try {
            PreferenceManager.getDefaultSharedPreferences(context).edit().remove(str).commit();
        } catch (Throwable th) {
            C0646c.m1016a(th);
        }
    }

    /* renamed from: a */
    public static void m1035a(Context context, String str, String str2) {
        try {
            String a = C0623e.m906a(C0652i.m1034a(context), str2);
            if (!TextUtils.isEmpty(str2) && TextUtils.isEmpty(a)) {
                C0590a.m801a("cp", "TriDesDecryptError", String.format("%s,%s", new Object[]{str, str2}));
            }
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(str, a).commit();
        } catch (Throwable th) {
            C0646c.m1016a(th);
        }
    }

    /* renamed from: b */
    public static String m1036b(Context context, String str, String str2) {
        String b;
        Throwable e;
        try {
            String string = PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2);
            if (TextUtils.isEmpty(string)) {
                CharSequence b2 = null;
            } else {
                b2 = C0623e.m908b(C0652i.m1034a(context), string);
            }
            try {
                if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(r0)) {
                    C0590a.m801a("cp", "TriDesEncryptError", String.format("%s,%s", new Object[]{str, string}));
                }
            } catch (Exception e2) {
                e = e2;
                C0646c.m1016a(e);
                return b2;
            }
        } catch (Exception e3) {
            Throwable th = e3;
            b2 = null;
            e = th;
            C0646c.m1016a(e);
            return b2;
        }
        return b2;
    }

    /* renamed from: a */
    private static String m1034a(Context context) {
        if (TextUtils.isEmpty(f661a)) {
            String str = "";
            try {
                str = context.getApplicationContext().getPackageName();
            } catch (Throwable th) {
                C0646c.m1016a(th);
            }
            f661a = (str + "0000000000000000000000000000").substring(0, 24);
        }
        return f661a;
    }
}
