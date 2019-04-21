package com.tencent.wxop.stat.p069a;

import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.Properties;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.c */
public class C4380c {
    /* renamed from: a */
    public String f6958a;
    /* renamed from: b */
    public JSONArray f6959b;
    /* renamed from: c */
    public JSONObject f6960c = null;

    public C4380c(String str, String[] strArr, Properties properties) {
        this.f6958a = str;
        if (properties != null) {
            this.f6960c = new JSONObject(properties);
        } else if (strArr != null) {
            this.f6959b = new JSONArray();
            for (Object put : strArr) {
                this.f6959b.put(put);
            }
        } else {
            this.f6960c = new JSONObject();
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C4380c)) {
            return false;
        }
        return toString().equals(((C4380c) obj).toString());
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append(this.f6958a).append(",");
        if (this.f6959b != null) {
            JSONArray jSONArray = this.f6959b;
            stringBuilder.append(!(jSONArray instanceof JSONArray) ? jSONArray.toString() : JSONArrayInstrumentation.toString(jSONArray));
        }
        if (this.f6960c != null) {
            JSONObject jSONObject = this.f6960c;
            stringBuilder.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject));
        }
        return stringBuilder.toString();
    }
}
