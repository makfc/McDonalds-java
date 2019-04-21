package com.alipay.apmobilesecuritysdk.p017e;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p015c.C0551a;
import com.alipay.apmobilesecuritysdk.p018f.C0568a;
import com.alipay.security.mobile.module.p019a.C0689a;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONObject;

/* renamed from: com.alipay.apmobilesecuritysdk.e.d */
public final class C0562d {
    /* renamed from: a */
    private static C0561c m673a(String str) {
        try {
            if (!C0689a.m1169a(str)) {
                JSONObject init = JSONObjectInstrumentation.init(str);
                return new C0561c(init.optString("apdid"), init.optString("deviceInfoHash"), init.optString(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE), init.optString("tid"), init.optString("utdid"));
            }
        } catch (Exception e) {
            C0551a.m644a(e);
        }
        return null;
    }

    /* renamed from: a */
    public static synchronized void m674a() {
        synchronized (C0562d.class) {
        }
    }

    /* renamed from: a */
    public static synchronized void m675a(Context context) {
        synchronized (C0562d.class) {
            C0568a.m732a(context, "vkeyid_profiles_v4", "key_deviceid_v4", "");
            C0568a.m733a("wxcasxx_v4", "key_wxcasxx_v4", "");
        }
    }

    /* renamed from: a */
    public static synchronized void m676a(Context context, C0561c c0561c) {
        synchronized (C0562d.class) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("apdid", c0561c.mo7939a());
                jSONObject.put("deviceInfoHash", c0561c.mo7940b());
                jSONObject.put(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE, c0561c.mo7941c());
                jSONObject.put("tid", c0561c.mo7942d());
                jSONObject.put("utdid", c0561c.mo7943e());
                String jSONObject2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
                C0568a.m732a(context, "vkeyid_profiles_v4", "key_deviceid_v4", jSONObject2);
                C0568a.m733a("wxcasxx_v4", "key_wxcasxx_v4", jSONObject2);
            } catch (Exception e) {
                C0551a.m644a(e);
            }
        }
        return;
    }

    /* renamed from: b */
    public static synchronized C0561c m677b() {
        C0561c a;
        synchronized (C0562d.class) {
            String a2 = C0568a.m731a("wxcasxx_v4", "key_wxcasxx_v4");
            a = C0689a.m1169a(a2) ? null : C0562d.m673a(a2);
        }
        return a;
    }

    /* renamed from: b */
    public static synchronized C0561c m678b(Context context) {
        C0561c a;
        synchronized (C0562d.class) {
            String a2 = C0568a.m730a(context, "vkeyid_profiles_v4", "key_deviceid_v4");
            if (C0689a.m1169a(a2)) {
                a2 = C0568a.m731a("wxcasxx_v4", "key_wxcasxx_v4");
            }
            a = C0562d.m673a(a2);
        }
        return a;
    }

    /* renamed from: c */
    public static synchronized C0561c m679c(Context context) {
        C0561c a;
        synchronized (C0562d.class) {
            String a2 = C0568a.m730a(context, "vkeyid_profiles_v4", "key_deviceid_v4");
            a = C0689a.m1169a(a2) ? null : C0562d.m673a(a2);
        }
        return a;
    }
}
