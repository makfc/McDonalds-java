package com.tencent.p059a.p060a.p061a.p062a;

import android.util.Log;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tencent.a.a.a.a.c */
public final class C4340c {
    /* renamed from: T */
    long f6770T = 0;
    /* renamed from: a */
    String f6771a = null;
    /* renamed from: b */
    String f6772b = null;
    /* renamed from: c */
    String f6773c = "0";

    /* renamed from: e */
    static C4340c m7856e(String str) {
        C4340c c4340c = new C4340c();
        if (C4344h.m7874b(str)) {
            try {
                JSONObject init = JSONObjectInstrumentation.init(str);
                if (!init.isNull("ui")) {
                    c4340c.f6771a = init.getString("ui");
                }
                if (!init.isNull("mc")) {
                    c4340c.f6772b = init.getString("mc");
                }
                if (!init.isNull("mid")) {
                    c4340c.f6773c = init.getString("mid");
                }
                if (!init.isNull("ts")) {
                    c4340c.f6770T = init.getLong("ts");
                }
            } catch (JSONException e) {
                Log.w("MID", e);
            }
        }
        return c4340c;
    }

    /* renamed from: n */
    private JSONObject m7857n() {
        JSONObject jSONObject = new JSONObject();
        try {
            C4344h.m7871a(jSONObject, "ui", this.f6771a);
            C4344h.m7871a(jSONObject, "mc", this.f6772b);
            C4344h.m7871a(jSONObject, "mid", this.f6773c);
            jSONObject.put("ts", this.f6770T);
        } catch (JSONException e) {
            Log.w("MID", e);
        }
        return jSONObject;
    }

    /* renamed from: a */
    public final String mo33761a() {
        return this.f6773c;
    }

    public final String toString() {
        JSONObject n = m7857n();
        return !(n instanceof JSONObject) ? n.toString() : JSONObjectInstrumentation.toString(n);
    }
}
