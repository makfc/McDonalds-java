package com.tencent.wxop.stat;

import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.tencent.wxop.stat.common.C4433k;
import com.tencent.wxop.stat.common.C4439q;
import org.json.JSONException;
import org.json.JSONObject;

public class StatAccount {
    /* renamed from: a */
    private String f6857a = "";
    /* renamed from: b */
    private int f6858b = 0;
    /* renamed from: c */
    private String f6859c = "";
    /* renamed from: d */
    private String f6860d = "";

    public StatAccount(String str) {
        this.f6857a = str;
    }

    public String getAccount() {
        return this.f6857a;
    }

    public String toJsonString() {
        JSONObject jSONObject = new JSONObject();
        if (C4433k.m8115c(this.f6857a)) {
            try {
                C4439q.m8159a(jSONObject, "a", this.f6857a);
                jSONObject.put("t", this.f6858b);
                C4439q.m8159a(jSONObject, "e", this.f6859c);
                C4439q.m8159a(jSONObject, "e1", this.f6860d);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
    }

    public String toString() {
        return "StatAccount [account=" + this.f6857a + ", accountType=" + this.f6858b + ", ext=" + this.f6859c + ", ext1=" + this.f6860d + "]";
    }
}
