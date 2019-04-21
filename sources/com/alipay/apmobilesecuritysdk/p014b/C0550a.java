package com.alipay.apmobilesecuritysdk.p014b;

import com.alipay.security.mobile.module.http.C0709d;
import com.alipay.security.mobile.module.p019a.C0689a;

/* renamed from: com.alipay.apmobilesecuritysdk.b.a */
public final class C0550a {
    /* renamed from: b */
    private static C0550a f412b = new C0550a();
    /* renamed from: a */
    private int f413a = 0;

    /* renamed from: a */
    public static C0550a m638a() {
        return f412b;
    }

    /* renamed from: a */
    public final void mo7933a(int i) {
        this.f413a = i;
    }

    /* renamed from: b */
    public final int mo7934b() {
        return this.f413a;
    }

    /* renamed from: c */
    public final String mo7935c() {
        String a = C0709d.m1273a();
        if (C0689a.m1172b(a)) {
            return a;
        }
        switch (this.f413a) {
            case 1:
                return "http://mobilegw.stable.alipay.net/mgw.htm";
            case 2:
                return "https://mobilegw.alipay.com/mgw.htm";
            case 3:
                return "http://mobilegw-1-64.test.alipay.net/mgw.htm";
            case 4:
                return "http://mobilegw.aaa.alipay.net/mgw.htm";
            default:
                return "https://mobilegw.alipay.com/mgw.htm";
        }
    }
}
