package com.alipay.apmobilesecuritysdk.p017e;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p015c.C0551a;
import com.alipay.apmobilesecuritysdk.p018f.C0568a;
import com.alipay.security.mobile.module.p019a.C0689a;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONObject;

/* renamed from: com.alipay.apmobilesecuritysdk.e.a */
public final class C0559a {
    /* renamed from: a */
    private static C0560b m658a(String str) {
        try {
            if (!C0689a.m1169a(str)) {
                JSONObject init = JSONObjectInstrumentation.init(str);
                return new C0560b(init.optString("apdid"), init.optString("deviceInfoHash"), init.optString(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE));
            }
        } catch (Exception e) {
            C0551a.m644a(e);
        }
        return null;
    }

    /* renamed from: a */
    public static synchronized void m659a() {
        synchronized (C0559a.class) {
        }
    }

    /* renamed from: a */
    public static synchronized void m660a(Context context) {
        synchronized (C0559a.class) {
            C0568a.m732a(context, "vkeyid_profiles_v3", "deviceid", "");
            C0568a.m733a("wxcasxx_v3", "wxcasxx", "");
        }
    }

    /* renamed from: a */
    public static synchronized void m661a(Context context, C0560b c0560b) {
        synchronized (C0559a.class) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("apdid", c0560b.mo7936a());
                jSONObject.put("deviceInfoHash", c0560b.mo7937b());
                jSONObject.put(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE, c0560b.mo7938c());
                String jSONObject2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
                C0568a.m732a(context, "vkeyid_profiles_v3", "deviceid", jSONObject2);
                C0568a.m733a("wxcasxx_v3", "wxcasxx", jSONObject2);
            } catch (Exception e) {
                C0551a.m644a(e);
            }
        }
        return;
    }

    /* renamed from: b */
    public static synchronized C0560b m662b() {
        C0560b a;
        synchronized (C0559a.class) {
            String a2 = C0568a.m731a("wxcasxx_v3", "wxcasxx");
            a = C0689a.m1169a(a2) ? null : C0559a.m658a(a2);
        }
        return a;
    }

    /* renamed from: b */
    public static synchronized C0560b m663b(Context context) {
        C0560b a;
        synchronized (C0559a.class) {
            String a2 = C0568a.m730a(context, "vkeyid_profiles_v3", "deviceid");
            if (C0689a.m1169a(a2)) {
                a2 = C0568a.m731a("wxcasxx_v3", "wxcasxx");
            }
            a = C0559a.m658a(a2);
        }
        return a;
    }

    /* renamed from: c */
    public static synchronized C0560b m664c(Context context) {
        C0560b a;
        synchronized (C0559a.class) {
            String a2 = C0568a.m730a(context, "vkeyid_profiles_v3", "deviceid");
            a = C0689a.m1169a(a2) ? null : C0559a.m658a(a2);
        }
        return a;
    }
}
