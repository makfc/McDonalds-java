package com.alipay.apmobilesecuritysdk.p017e;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.alipay.security.mobile.module.p019a.C0689a;
import com.alipay.security.mobile.module.p019a.p020a.C0688c;
import com.alipay.security.mobile.module.p022c.C0699e;

/* renamed from: com.alipay.apmobilesecuritysdk.e.g */
public final class C0565g {
    /* renamed from: a */
    public static synchronized String m691a(Context context, String str) {
        String a;
        synchronized (C0565g.class) {
            a = C0699e.m1253a(context, "openapi_file_pri", "openApi" + str, "");
            if (C0689a.m1169a(a)) {
                a = "";
            } else {
                a = C0688c.m1163b(C0688c.m1158a(), a);
                if (C0689a.m1169a(a)) {
                    a = "";
                }
            }
        }
        return a;
    }

    /* renamed from: a */
    public static synchronized void m692a() {
        synchronized (C0565g.class) {
        }
    }

    /* renamed from: a */
    public static synchronized void m693a(Context context) {
        synchronized (C0565g.class) {
            Editor edit = context.getSharedPreferences("openapi_file_pri", 0).edit();
            if (edit != null) {
                edit.clear();
                edit.commit();
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m694a(Context context, String str, String str2) {
        synchronized (C0565g.class) {
            try {
                Editor edit = context.getSharedPreferences("openapi_file_pri", 0).edit();
                if (edit != null) {
                    edit.putString("openApi" + str, C0688c.m1159a(C0688c.m1158a(), str2));
                    edit.commit();
                }
            } catch (Throwable th) {
            }
        }
    }
}
