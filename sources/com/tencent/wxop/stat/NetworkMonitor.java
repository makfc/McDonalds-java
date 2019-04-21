package com.tencent.wxop.stat;

import org.json.JSONException;
import org.json.JSONObject;

public class NetworkMonitor {
    /* renamed from: a */
    private long f6852a = 0;
    /* renamed from: b */
    private int f6853b = 0;
    /* renamed from: c */
    private String f6854c = "";
    /* renamed from: d */
    private int f6855d = 0;
    /* renamed from: e */
    private String f6856e = "";

    public void setDomain(String str) {
        this.f6854c = str;
    }

    public void setMillisecondsConsume(long j) {
        this.f6852a = j;
    }

    public void setPort(int i) {
        this.f6855d = i;
    }

    public void setRemoteIp(String str) {
        this.f6856e = str;
    }

    public void setStatusCode(int i) {
        this.f6853b = i;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("tm", this.f6852a);
            jSONObject.put("st", this.f6853b);
            if (this.f6854c != null) {
                jSONObject.put("dm", this.f6854c);
            }
            jSONObject.put("pt", this.f6855d);
            if (this.f6856e != null) {
                jSONObject.put("rip", this.f6856e);
            }
            jSONObject.put("ts", System.currentTimeMillis() / 1000);
        } catch (JSONException e) {
        }
        return jSONObject;
    }
}
