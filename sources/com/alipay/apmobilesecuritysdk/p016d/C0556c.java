package com.alipay.apmobilesecuritysdk.p016d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p015c.C0551a;
import com.alipay.apmobilesecuritysdk.p017e.C0563e;
import com.alipay.apmobilesecuritysdk.p017e.C0564f;
import com.alipay.apmobilesecuritysdk.p018f.C0568a;
import com.alipay.security.mobile.module.p019a.C0689a;
import com.alipay.security.mobile.module.p021b.C0692b;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.alipay.apmobilesecuritysdk.d.c */
public final class C0556c {
    /* renamed from: a */
    public static Map<String, String> m650a(Context context) {
        String str;
        C0692b a = C0692b.m1182a();
        HashMap hashMap = new HashMap();
        C0564f a2 = C0563e.m680a(context);
        String a3 = C0692b.m1184a(context);
        String b = C0692b.m1187b(context);
        String k = C0692b.m1204k(context);
        String n = C0692b.m1210n(context);
        String m = C0692b.m1208m(context);
        if (a2 != null) {
            if (C0689a.m1169a(a3)) {
                a3 = a2.mo7944a();
            }
            if (C0689a.m1169a(b)) {
                b = a2.mo7946b();
            }
            if (C0689a.m1169a(k)) {
                k = a2.mo7948c();
            }
            if (C0689a.m1169a(n)) {
                n = a2.mo7950d();
            }
            if (C0689a.m1169a(m)) {
                m = a2.mo7952e();
            }
            str = m;
            m = n;
            n = k;
            k = b;
            b = a3;
        } else {
            str = m;
            m = n;
            n = k;
            k = b;
            b = a3;
        }
        C0564f c0564f = new C0564f(b, k, n, m, str);
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("imei", c0564f.mo7944a());
                jSONObject.put("imsi", c0564f.mo7946b());
                jSONObject.put("mac", c0564f.mo7948c());
                jSONObject.put("bluetoothmac", c0564f.mo7950d());
                jSONObject.put("gsi", c0564f.mo7952e());
                a3 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
                C0568a.m733a("device_feature_file_name", "device_feature_file_key", a3);
                C0568a.m732a(context, "device_feature_prefs_name", "device_feature_prefs_key", a3);
            } catch (Exception e) {
                C0551a.m644a(e);
            }
        }
        hashMap.put("AD1", b);
        hashMap.put("AD2", k);
        hashMap.put("AD3", C0692b.m1194f(context));
        hashMap.put("AD5", C0692b.m1198h(context));
        hashMap.put("AD6", C0692b.m1200i(context));
        hashMap.put("AD7", C0692b.m1202j(context));
        hashMap.put("AD8", n);
        hashMap.put("AD9", C0692b.m1206l(context));
        hashMap.put("AD10", str);
        hashMap.put("AD11", C0692b.m1192e());
        hashMap.put("AD12", a.mo8174f());
        hashMap.put("AD13", C0692b.m1195g());
        hashMap.put("AD14", C0692b.m1199i());
        hashMap.put("AD15", C0692b.m1201j());
        hashMap.put("AD16", C0692b.m1203k());
        hashMap.put("AD17", "");
        hashMap.put("AD18", m);
        hashMap.put("AD19", C0692b.m1212o(context));
        hashMap.put("AD20", C0692b.m1205l());
        hashMap.put("AD21", C0692b.m1190d());
        hashMap.put("AD22", "");
        hashMap.put("AD23", C0692b.m1207m());
        hashMap.put("AD24", C0689a.m1177g(C0692b.m1196g(context)));
        hashMap.put("AD26", C0692b.m1193e(context));
        hashMap.put("AD27", C0692b.m1217r());
        hashMap.put("AD28", C0692b.m1221t());
        hashMap.put("AD29", C0692b.m1224v());
        hashMap.put("AD30", C0692b.m1219s());
        hashMap.put("AD31", C0692b.m1223u());
        hashMap.put("AD32", C0692b.m1213p());
        hashMap.put("AD33", C0692b.m1215q());
        hashMap.put("AD34", C0692b.m1218r(context));
        hashMap.put("AD35", C0692b.m1220s(context));
        hashMap.put("AD36", C0692b.m1216q(context));
        hashMap.put("AD37", C0692b.m1211o());
        hashMap.put("AD38", C0692b.m1209n());
        hashMap.put("AD39", C0692b.m1189c(context));
        hashMap.put("AD40", C0692b.m1191d(context));
        hashMap.put("AD41", C0692b.m1186b());
        hashMap.put("AD42", C0692b.m1188c());
        hashMap.put("AL3", C0692b.m1214p(context));
        return hashMap;
    }
}
