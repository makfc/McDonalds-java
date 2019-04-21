package com.alipay.security.mobile.module.p022c;

import android.content.Context;
import com.alipay.security.mobile.module.p019a.C0689a;
import com.alipay.security.mobile.module.p019a.p020a.C0688c;
import java.util.HashMap;

/* renamed from: com.alipay.security.mobile.module.c.d */
public final class C0698d {
    /* renamed from: a */
    public static synchronized void m1252a(Context context, String str, String str2, String str3) {
        synchronized (C0698d.class) {
            if (!(C0689a.m1169a(str) || C0689a.m1169a(str2) || context == null)) {
                try {
                    String a = C0688c.m1159a(C0688c.m1158a(), str3);
                    HashMap hashMap = new HashMap();
                    hashMap.put(str2, a);
                    C0699e.m1254a(context, str, hashMap);
                } catch (Throwable th) {
                }
            }
        }
    }
}
