package com.amap.api.services.core;

import android.content.Context;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.amap.api.services.core.n */
class ManifestManager extends C1075bs {
    /* renamed from: a */
    private Context f3798a;
    /* renamed from: b */
    private String f3799b;

    public ManifestManager(Context context) {
        this.f3798a = context;
        this.f3799b = C1134v.m5087f(context);
    }

    /* renamed from: a */
    public ManifestResult mo12109a() {
        String str = "feachManifest";
        try {
            C1123br a = C1123br.m4953a(false);
            mo11970a(C1078ab.m4690a(this.f3798a));
            return m5059a(a.mo12094a((C1075bs) this));
        } catch (Exception e) {
            C1128d.m4975a(e, "ManifestManager", str);
            return null;
        }
    }

    /* renamed from: a */
    private JSONObject m5060a(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.optJSONObject(str);
    }

    /* renamed from: b */
    private String m5062b(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.optString(str);
    }

    /* renamed from: a */
    private boolean m5061a(String str) {
        if (str != null && str.equals("1")) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private ManifestResult m5059a(byte[] bArr) {
        String str = "loadData";
        if (bArr == null) {
            return null;
        }
        try {
            JSONObject init = JSONObjectInstrumentation.init(new String(bArr));
            String optString = init.optString("status");
            if ("0".equals(optString) || !"1".equals(optString)) {
                return null;
            }
            init = m5060a(init, "result");
            return new ManifestResult(m5061a(m5062b(m5060a(m5060a(init, "common"), "commoninfo"), "com_isupload")), m5061a(m5062b(m5060a(m5060a(init, "exception"), "exceptinfo"), "ex_isupload")));
        } catch (JSONException e) {
            C1128d.m4975a(e, "ManifestManager", str);
            return null;
        } catch (Exception e2) {
            C1128d.m4975a(e2, "ManifestManager", str);
            return null;
        }
    }

    /* renamed from: d_ */
    public Map<String, String> mo11973d_() {
        HashMap hashMap = new HashMap();
        hashMap.put("User-Agent", "AMAP SDK Android Search 2.4.0");
        return hashMap;
    }

    /* renamed from: c_ */
    public Map<String, String> mo11972c_() {
        HashMap hashMap = new HashMap();
        hashMap.put(Parameters.API_KEY, this.f3799b);
        hashMap.put("opertype", "common;exception");
        hashMap.put("plattype", "android");
        hashMap.put("product", "sea");
        hashMap.put("version", "2.4.0");
        hashMap.put("ext", "standard");
        hashMap.put("output", "json");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(this.f3799b);
        stringBuffer.append("&opertype=common;exception");
        stringBuffer.append("&plattype=android");
        stringBuffer.append("&product=").append("sea");
        stringBuffer.append("&version=").append("2.4.0");
        stringBuffer.append("&ext=standard");
        stringBuffer.append("&output=json");
        String a = C1082ad.m4710a(stringBuffer.toString());
        String a2 = C1136x.m5090a();
        hashMap.put("ts", a2);
        hashMap.put("scode", C1136x.m5094a(this.f3798a, a2, a));
        return hashMap;
    }

    /* renamed from: b */
    public String mo11971b() {
        return C1127c.m4969a() + "/config/resource";
    }

    /* renamed from: e */
    public HttpEntity mo11974e() {
        return null;
    }
}
