package com.alipay.mobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.alipay.apmobilesecuritysdk.p013a.C0549a;
import com.alipay.security.mobile.module.p019a.C0689a;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.HashMap;
import java.util.Map;

public class SecurityClientMobile {
    public static synchronized String GetApdid(Context context, Map<String, String> map) {
        String a;
        synchronized (SecurityClientMobile.class) {
            HashMap hashMap = new HashMap();
            hashMap.put("utdid", C0689a.m1168a(map, "utdid", ""));
            hashMap.put("tid", C0689a.m1168a(map, "tid", ""));
            hashMap.put(AnalyticAttribute.USER_ID_ATTRIBUTE, C0689a.m1168a(map, AnalyticAttribute.USER_ID_ATTRIBUTE, ""));
            APSecuritySdk.getInstance(context).initToken(0, hashMap, null);
            a = C0549a.m631a(context);
        }
        return a;
    }
}
