package com.tencent.wxop.stat;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.f */
class C4443f {
    /* renamed from: a */
    int f7170a;
    /* renamed from: b */
    JSONObject f7171b = new JSONObject();
    /* renamed from: c */
    String f7172c = "";
    /* renamed from: d */
    int f7173d = 0;

    public C4443f(int i) {
        this.f7170a = i;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public String mo33975a() {
        JSONObject jSONObject = this.f7171b;
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
    }
}
