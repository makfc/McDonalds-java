package com.alipay.sdk.packet;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.app.C0587i;
import com.alipay.sdk.net.C0626a;
import com.alipay.sdk.net.C0626a.C0624a;
import com.alipay.sdk.net.C0626a.C0625b;
import com.alipay.sdk.sys.C0640b;
import com.alipay.sdk.tid.C0643b;
import com.alipay.sdk.util.C0645b;
import com.alipay.sdk.util.C0646c;
import com.alipay.sdk.util.C0655l;
import com.alipay.sdk.util.C0657m;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.mcdonalds.sdk.services.analytics.tagmanager.Parameters;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.packet.e */
public abstract class C0631e {
    /* renamed from: r */
    protected boolean f603r = true;
    /* renamed from: s */
    protected boolean f604s = true;

    /* renamed from: a */
    public abstract JSONObject mo8080a() throws JSONException;

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public Map<String, String> mo8079a(boolean z, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("msp-gzip", String.valueOf(z));
        hashMap.put("Operation-Type", "alipay.msp.cashier.dispatch.bytes");
        hashMap.put("content-type", "application/octet-stream");
        hashMap.put("Version", "2.0");
        hashMap.put("AppId", "TAOBAO");
        hashMap.put("Msp-Param", C0627a.m915a(str));
        hashMap.put("des-mode", "CBC");
        return hashMap;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public String mo8081b() {
        return "4.9.0";
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: c */
    public String mo8082c() throws JSONException {
        HashMap hashMap = new HashMap();
        hashMap.put("device", Build.MODEL);
        hashMap.put("namespace", "com.alipay.mobilecashier");
        hashMap.put("api_name", "com.alipay.mcpay");
        hashMap.put("api_version", mo8081b());
        return mo8078a(hashMap, new HashMap());
    }

    /* renamed from: a */
    protected static JSONObject m935a(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("type", str);
        jSONObject2.put("method", str2);
        jSONObject.put(Parameters.ACTION, jSONObject2);
        return jSONObject;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo8077a(String str, JSONObject jSONObject) {
        C0640b a = C0640b.m974a();
        C0643b a2 = C0643b.m990a(a.mo8088b());
        JSONObject a3 = C0645b.m1014a(new JSONObject(), jSONObject);
        try {
            a3.put("tid", a2.mo8096a());
            a3.put("user_agent", a.mo8089c().mo8058a(a2));
            a3.put("has_alipay", C0657m.m1065b(a.mo8088b(), C0587i.f495a));
            a3.put("has_msp_app", C0657m.m1057a(a.mo8088b()));
            a3.put("external_info", str);
            a3.put("app_key", "2014052600006128");
            a3.put("utdid", a.mo8090e());
            a3.put("new_client_key", a2.mo8098b());
            a3.put("pa", a.mo8089c().mo8057a(a.mo8088b()));
        } catch (Throwable th) {
            C0646c.m1016a(th);
        }
        return !(a3 instanceof JSONObject) ? a3.toString() : JSONObjectInstrumentation.toString(a3);
    }

    /* renamed from: a */
    private static boolean m936a(C0625b c0625b) {
        return Boolean.valueOf(C0631e.m934a(c0625b, "msp-gzip")).booleanValue();
    }

    /* renamed from: a */
    private static String m934a(C0625b c0625b, String str) {
        if (c0625b == null || str == null) {
            return null;
        }
        if (c0625b.f593a == null) {
            return null;
        }
        List list = (List) c0625b.f593a.get(str);
        if (list == null) {
            return null;
        }
        return TextUtils.join(",", list);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo8078a(HashMap<String, String> hashMap, HashMap<String, String> hashMap2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (hashMap != null) {
            for (Entry entry : hashMap.entrySet()) {
                jSONObject2.put((String) entry.getKey(), entry.getValue());
            }
        }
        if (hashMap2 != null) {
            JSONObject jSONObject3 = new JSONObject();
            for (Entry entry2 : hashMap2.entrySet()) {
                jSONObject3.put((String) entry2.getKey(), entry2.getValue());
            }
            jSONObject2.put("params", jSONObject3);
        }
        jSONObject.put(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH, jSONObject2);
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
    }

    /* renamed from: a */
    private boolean m937a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = JSONObjectInstrumentation.init(str).getJSONObject(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH);
            if (!jSONObject.has("params")) {
                return false;
            }
            String optString = jSONObject.getJSONObject("params").optString("public_key", null);
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            C0640b.m974a().mo8089c().mo8059a(optString);
            return true;
        } catch (JSONException e) {
            C0646c.m1016a(e);
            return false;
        }
    }

    /* renamed from: a */
    public C0628b mo8073a(Context context) throws Throwable {
        return mo8074a(context, "");
    }

    /* renamed from: a */
    public C0628b mo8074a(Context context, String str) throws Throwable {
        return mo8075a(context, str, C0655l.m1045a(context));
    }

    /* renamed from: a */
    public C0628b mo8075a(Context context, String str, String str2) throws Throwable {
        return mo8076a(context, str, str2, true);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public C0628b mo8076a(Context context, String str, String str2, boolean z) throws Throwable {
        C0646c.m1017b("", "PacketTask::request url >" + str2);
        C0629c c0629c = new C0629c(this.f604s);
        C0630d a = c0629c.mo8070a(new C0628b(mo8082c(), mo8077a(str, mo8080a())), this.f603r);
        C0625b a2 = C0626a.m910a(context, new C0624a(str2, mo8079a(a.mo8071a(), str), a.mo8072b()));
        if (a2 == null) {
            throw new RuntimeException("Response is null.");
        }
        C0628b a3 = c0629c.mo8069a(new C0630d(C0631e.m936a(a2), a2.f595c));
        if (a3 != null && m937a(a3.mo8065a()) && z) {
            return mo8076a(context, str, str2, false);
        }
        return a3;
    }
}
