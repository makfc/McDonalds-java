package com.baidu.android.pushservice.p037i;

import com.mcdonalds.sdk.modules.models.Promotion;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.i.h */
public class C1441h extends C1435r {
    /* renamed from: a */
    public String f5089a;

    public C1441h(C1435r c1435r) {
        super(c1435r);
    }

    /* renamed from: a */
    public JSONObject mo13891a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Promotion.COLUMN_ACTION_NAME, this.f5036f);
        jSONObject.put(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE, this.f5037g);
        jSONObject.put("network_status", this.f5038h);
        jSONObject.put("crash_stack", this.f5089a);
        return jSONObject;
    }
}
