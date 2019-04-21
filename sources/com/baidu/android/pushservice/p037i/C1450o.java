package com.baidu.android.pushservice.p037i;

import android.text.TextUtils;
import com.mcdonalds.sdk.modules.models.Promotion;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.i.o */
public class C1450o extends C1435r {
    /* renamed from: a */
    public String f5119a = "";
    /* renamed from: b */
    public int f5120b = -1;
    /* renamed from: c */
    public int f5121c = -1;
    /* renamed from: d */
    public String f5122d;

    public C1450o(C1435r c1435r) {
        super(c1435r);
    }

    /* renamed from: a */
    public JSONObject mo13920a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Promotion.COLUMN_ACTION_NAME, this.f5036f);
        jSONObject.put(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE, this.f5037g);
        jSONObject.put("network_status", this.f5038h);
        if (this.f5121c != -1) {
            jSONObject.put("msg_type", this.f5121c);
        }
        if (!TextUtils.isEmpty(this.f5119a)) {
            jSONObject.put("msg_id", this.f5119a);
        }
        if (this.f5120b > 0) {
            jSONObject.put("msg_len", this.f5120b);
        }
        if (this.f5122d != null) {
            jSONObject.put("msg_open_by", this.f5122d);
        }
        jSONObject.put("err_code", this.f5039i);
        return jSONObject;
    }
}
