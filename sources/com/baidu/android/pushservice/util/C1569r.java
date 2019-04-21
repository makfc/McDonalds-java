package com.baidu.android.pushservice.util;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.util.r */
public class C1569r {
    /* renamed from: a */
    public long f5513a = 0;
    /* renamed from: b */
    public long f5514b = 0;
    /* renamed from: c */
    public String f5515c = "";
    /* renamed from: d */
    public String f5516d = "";
    /* renamed from: e */
    public String f5517e = "";
    /* renamed from: f */
    public String f5518f = "";
    /* renamed from: g */
    public String f5519g = "";
    /* renamed from: h */
    public String f5520h = "";
    /* renamed from: i */
    public String f5521i = "";

    /* renamed from: a */
    public JSONObject mo14082a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.f5513a > -1) {
            jSONObject.put("push_priority", this.f5513a);
        }
        if (this.f5514b > -1) {
            jSONObject.put("push_version", this.f5514b);
        }
        jSONObject.put("push_channelid", this.f5515c);
        jSONObject.put("push_curpkgname", this.f5516d);
        jSONObject.put("push_webappbindinfo", this.f5517e);
        jSONObject.put("push_lightappbindinfo", this.f5518f);
        jSONObject.put("push_sdkclientbindinfo", this.f5519g);
        jSONObject.put("push_clientsbindinfo", this.f5520h);
        jSONObject.put("push_selfbindinfo", this.f5521i);
        return jSONObject;
    }
}
