package com.alipay.apmobilesecuritysdk.p017e;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p015c.C0551a;
import com.alipay.apmobilesecuritysdk.p018f.C0568a;
import com.alipay.security.mobile.module.p019a.C0689a;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONObject;

/* renamed from: com.alipay.apmobilesecuritysdk.e.e */
public final class C0563e {
    /* renamed from: a */
    public static C0564f m680a(Context context) {
        if (context == null) {
            return null;
        }
        String a = C0568a.m730a(context, "device_feature_prefs_name", "device_feature_prefs_key");
        if (C0689a.m1169a(a)) {
            a = C0568a.m731a("device_feature_file_name", "device_feature_file_key");
        }
        if (C0689a.m1169a(a)) {
            return null;
        }
        try {
            JSONObject init = JSONObjectInstrumentation.init(a);
            C0564f c0564f = new C0564f();
            c0564f.mo7945a(init.getString("imei"));
            c0564f.mo7947b(init.getString("imsi"));
            c0564f.mo7949c(init.getString("mac"));
            c0564f.mo7951d(init.getString("bluetoothmac"));
            c0564f.mo7953e(init.getString("gsi"));
            return c0564f;
        } catch (Exception e) {
            C0551a.m644a(e);
            return null;
        }
    }
}
