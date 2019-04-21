package com.baidu.android.pushservice.p037i;

import android.text.TextUtils;
import com.mcdonalds.sdk.modules.models.Promotion;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.i.b */
public class C1436b extends C1435r {
    /* renamed from: a */
    public String f5052a = "";
    /* renamed from: b */
    public int f5053b = -1;
    /* renamed from: c */
    public int f5054c = -1;
    /* renamed from: d */
    public String f5055d = "";
    /* renamed from: e */
    public String f5056e = "";

    public C1436b(C1435r c1435r) {
        super(c1435r);
    }

    /* renamed from: a */
    public JSONObject mo13850a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Promotion.COLUMN_ACTION_NAME, this.f5036f);
        jSONObject.put(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE, this.f5037g);
        jSONObject.put("network_status", this.f5038h);
        if (this.f5054c != -1) {
            jSONObject.put("msg_type", this.f5054c);
        }
        if (!TextUtils.isEmpty(this.f5052a)) {
            jSONObject.put("msg_id", this.f5052a);
        }
        if (this.f5053b > 0) {
            jSONObject.put("msg_len", this.f5053b);
        }
        if (this.f5055d != null) {
            jSONObject.put("request_id", this.f5055d);
        }
        if (this.f5056e != null) {
            jSONObject.put("msg_open_by", this.f5056e);
        }
        return jSONObject;
    }
}
