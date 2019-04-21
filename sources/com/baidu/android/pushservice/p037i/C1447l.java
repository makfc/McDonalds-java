package com.baidu.android.pushservice.p037i;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.i.l */
public class C1447l {
    /* renamed from: a */
    public int f5106a = 0;
    /* renamed from: b */
    public int f5107b = 0;
    /* renamed from: c */
    public int f5108c = 0;
    /* renamed from: d */
    public int f5109d = 0;
    /* renamed from: e */
    public long f5110e = 0;

    /* renamed from: a */
    public JSONObject mo13905a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.f5106a > -1) {
            jSONObject.put("pushad_switch", this.f5106a);
        }
        if (this.f5107b > -1) {
            jSONObject.put("pushad_maxcount", this.f5107b);
        }
        if (this.f5108c > -1) {
            jSONObject.put("pushad_servermaxcount", this.f5108c);
        }
        if (this.f5109d > -1) {
            jSONObject.put("pushad_curcount", this.f5107b);
        }
        if (this.f5110e > -1) {
            jSONObject.put("pushad_curtimestamp", this.f5110e);
        }
        return jSONObject;
    }
}
