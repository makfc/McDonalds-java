package com.alipay.sdk.packet;

import android.text.TextUtils;
import com.alipay.sdk.util.C0646c;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.packet.b */
public final class C0628b {
    /* renamed from: a */
    private final String f597a;
    /* renamed from: b */
    private final String f598b;

    public C0628b(String str, String str2) {
        this.f597a = str;
        this.f598b = str2;
    }

    /* renamed from: a */
    public String mo8065a() {
        return this.f597a;
    }

    /* renamed from: b */
    public String mo8066b() {
        return this.f598b;
    }

    /* renamed from: c */
    public JSONObject mo8067c() {
        JSONObject jSONObject = null;
        if (TextUtils.isEmpty(this.f598b)) {
            return jSONObject;
        }
        try {
            return JSONObjectInstrumentation.init(this.f598b);
        } catch (Exception e) {
            C0646c.m1016a(e);
            return jSONObject;
        }
    }

    public String toString() {
        return String.format("<Letter envelop=%s body=%s>", new Object[]{this.f597a, this.f598b});
    }
}
