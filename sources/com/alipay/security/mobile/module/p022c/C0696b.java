package com.alipay.security.mobile.module.p022c;

import com.alipay.security.mobile.module.p019a.C0689a;
import java.io.File;

/* renamed from: com.alipay.security.mobile.module.c.b */
public final class C0696b {
    /* renamed from: a */
    public static String m1249a(String str) {
        String str2 = "";
        try {
            str2 = C0700f.m1255a(str);
        } catch (Throwable th) {
        }
        return C0689a.m1169a(str2) ? C0697c.m1250a(".SystemConfig" + File.separator + str) : str2;
    }
}
