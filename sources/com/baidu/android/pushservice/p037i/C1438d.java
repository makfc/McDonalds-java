package com.baidu.android.pushservice.p037i;

import android.text.TextUtils;
import com.mcdonalds.sdk.modules.models.Promotion;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.i.d */
public class C1438d extends C1435r {
    /* renamed from: a */
    public String f5063a;
    /* renamed from: b */
    public String f5064b;
    /* renamed from: c */
    public String f5065c;

    public C1438d(C1435r c1435r) {
        super(c1435r);
    }

    /* renamed from: a */
    public JSONObject mo13851a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Promotion.COLUMN_ACTION_NAME, this.f5036f);
        jSONObject.put(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE, this.f5037g);
        jSONObject.put("network_status", this.f5038h);
        jSONObject.put("msg_result", this.f5063a);
        jSONObject.put("request_id", this.f5064b);
        jSONObject.put("err_code", this.f5039i);
        if (!TextUtils.isEmpty(this.f5065c)) {
            jSONObject.put("channel", this.f5065c);
        }
        return jSONObject;
    }
}
