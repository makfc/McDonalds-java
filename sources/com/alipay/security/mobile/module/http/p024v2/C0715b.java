package com.alipay.security.mobile.module.http.p024v2;

import android.content.Context;
import com.alipay.security.mobile.module.http.C0705a;
import com.alipay.security.mobile.module.http.C0709d;
import com.alipay.security.mobile.module.http.model.C0711b;
import com.alipay.security.mobile.module.http.model.C0712c;
import com.alipay.security.mobile.module.http.model.C0713d;

/* renamed from: com.alipay.security.mobile.module.http.v2.b */
public class C0715b implements C0714a {
    /* renamed from: a */
    private static C0714a f769a = null;
    /* renamed from: b */
    private static C0705a f770b = null;

    /* renamed from: a */
    public static C0714a m1308a(Context context, String str) {
        if (context == null) {
            return null;
        }
        if (f769a == null) {
            f770b = C0709d.m1272a(context, str);
            f769a = new C0715b();
        }
        return f769a;
    }

    /* renamed from: a */
    public C0712c mo8211a(C0713d c0713d) {
        return C0711b.m1276a(f770b.mo8179a(C0711b.m1277a(c0713d)));
    }

    /* renamed from: a */
    public boolean mo8212a(String str) {
        return f770b.mo8180a(str);
    }
}
