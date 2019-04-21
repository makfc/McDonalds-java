package com.tencent.wxop.stat.common;

import com.facebook.android.Facebook;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.common.a */
public class C4423a {
    /* renamed from: a */
    private String f7089a = null;
    /* renamed from: b */
    private String f7090b = null;
    /* renamed from: c */
    private String f7091c = null;
    /* renamed from: d */
    private String f7092d = "0";
    /* renamed from: e */
    private int f7093e;
    /* renamed from: f */
    private int f7094f = 0;
    /* renamed from: g */
    private long f7095g = 0;

    public C4423a(String str, String str2, int i) {
        this.f7089a = str;
        this.f7090b = str2;
        this.f7093e = i;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public JSONObject mo33958a() {
        JSONObject jSONObject = new JSONObject();
        try {
            C4439q.m8159a(jSONObject, "ui", this.f7089a);
            C4439q.m8159a(jSONObject, "mc", this.f7090b);
            C4439q.m8159a(jSONObject, "mid", this.f7092d);
            C4439q.m8159a(jSONObject, Facebook.ATTRIBUTION_ID_COLUMN_NAME, this.f7091c);
            jSONObject.put("ts", this.f7095g);
            jSONObject.put("ver", this.f7094f);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    /* renamed from: a */
    public void mo33959a(int i) {
        this.f7093e = i;
    }

    /* renamed from: b */
    public String mo33960b() {
        return this.f7089a;
    }

    /* renamed from: c */
    public String mo33961c() {
        return this.f7090b;
    }

    /* renamed from: d */
    public int mo33962d() {
        return this.f7093e;
    }

    public String toString() {
        JSONObject a = mo33958a();
        return !(a instanceof JSONObject) ? a.toString() : JSONObjectInstrumentation.toString(a);
    }
}
