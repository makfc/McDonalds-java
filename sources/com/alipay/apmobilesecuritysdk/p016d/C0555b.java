package com.alipay.apmobilesecuritysdk.p016d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p017e.C0566h;
import com.alipay.security.mobile.module.p019a.C0689a;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.alipay.apmobilesecuritysdk.d.b */
public final class C0555b {
    /* renamed from: a */
    public static synchronized Map<String, String> m649a(Context context, Map<String, String> map) {
        HashMap hashMap;
        synchronized (C0555b.class) {
            hashMap = new HashMap();
            String a = C0689a.m1168a(map, "tid", "");
            String a2 = C0689a.m1168a(map, "utdid", "");
            String a3 = C0689a.m1168a(map, AnalyticAttribute.USER_ID_ATTRIBUTE, "");
            String a4 = C0689a.m1168a(map, AnalyticAttribute.APP_NAME_ATTRIBUTE, "");
            String a5 = C0689a.m1168a(map, "appKeyClient", "");
            String a6 = C0689a.m1168a(map, "tmxSessionId", "");
            String f = C0566h.m708f(context);
            String a7 = C0689a.m1168a(map, "sessionId", "");
            hashMap.put("AC1", a);
            hashMap.put("AC2", a2);
            hashMap.put("AC3", "");
            hashMap.put("AC4", f);
            hashMap.put("AC5", a3);
            hashMap.put("AC6", a6);
            hashMap.put("AC7", "");
            hashMap.put("AC8", a4);
            hashMap.put("AC9", a5);
            if (C0689a.m1172b(a7)) {
                hashMap.put("AC10", a7);
            }
        }
        return hashMap;
    }
}
