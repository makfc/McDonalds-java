package com.alipay.sdk.authjs;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.authjs.a */
public class C0606a {
    /* renamed from: h */
    private String f553h;
    /* renamed from: i */
    private String f554i;
    /* renamed from: j */
    private String f555j;
    /* renamed from: k */
    private String f556k;
    /* renamed from: l */
    private JSONObject f557l;
    /* renamed from: m */
    private boolean f558m = false;

    /* renamed from: com.alipay.sdk.authjs.a$a */
    public enum C0605a {
        NONE_ERROR,
        FUNCTION_NOT_FOUND,
        INVALID_PARAMETER,
        RUNTIME_ERROR,
        NONE_PERMISS
    }

    public C0606a(String str) {
        mo8043d(str);
    }

    /* renamed from: b */
    public String mo8039b() {
        return this.f553h;
    }

    /* renamed from: a */
    public void mo8037a(String str) {
        this.f553h = str;
    }

    /* renamed from: b */
    public void mo8040b(String str) {
        this.f554i = str;
    }

    /* renamed from: d */
    public String mo8042d() {
        return this.f555j;
    }

    /* renamed from: c */
    public void mo8041c(String str) {
        this.f555j = str;
    }

    /* renamed from: d */
    public void mo8043d(String str) {
        this.f556k = str;
    }

    /* renamed from: f */
    public JSONObject mo8044f() {
        return this.f557l;
    }

    /* renamed from: a */
    public void mo8038a(JSONObject jSONObject) {
        this.f557l = jSONObject;
    }

    /* renamed from: g */
    public String mo8045g() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("clientId", this.f553h);
        jSONObject.put("func", this.f555j);
        jSONObject.put("param", this.f557l);
        jSONObject.put("msgType", this.f556k);
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
    }
}
