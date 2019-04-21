package com.alipay.apmobilesecuritysdk.p016d;

import android.content.Context;
import com.alipay.security.mobile.module.p019a.C0689a;
import com.alipay.security.mobile.module.p021b.C0691a;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.alipay.apmobilesecuritysdk.d.a */
public final class C0554a {
    /* renamed from: a */
    public static synchronized Map<String, String> m648a(Context context, Map<String, String> map) {
        HashMap hashMap;
        synchronized (C0554a.class) {
            String a = C0689a.m1168a(map, "appchannel", "");
            hashMap = new HashMap();
            hashMap.put("AA1", context.getPackageName());
            C0691a.m1179a();
            hashMap.put("AA2", C0691a.m1180a(context));
            hashMap.put("AA3", "APPSecuritySDK-ALIPAY");
            hashMap.put("AA4", "3.2.2-20180331");
            hashMap.put("AA6", a);
        }
        return hashMap;
    }
}
