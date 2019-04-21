package com.tencent.wxop.stat.common;

import android.content.Context;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.tencent.wxop.stat.C4389a;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.common.b */
public class C4424b {
    /* renamed from: a */
    static C4426d f7096a;
    /* renamed from: d */
    private static StatLogger f7097d = C4433k.m8111b();
    /* renamed from: e */
    private static JSONObject f7098e = new JSONObject();
    /* renamed from: b */
    Integer f7099b = null;
    /* renamed from: c */
    String f7100c = null;

    public C4424b(Context context) {
        try {
            C4424b.m8080a(context);
            this.f7099b = C4433k.m8130m(context.getApplicationContext());
            this.f7100c = C4389a.m7995a(context).mo33900b();
        } catch (Throwable th) {
            f7097d.mo33949e(th);
        }
    }

    /* renamed from: a */
    static synchronized C4426d m8080a(Context context) {
        C4426d c4426d;
        synchronized (C4424b.class) {
            if (f7096a == null) {
                f7096a = new C4426d(context.getApplicationContext());
            }
            c4426d = f7096a;
        }
        return c4426d;
    }

    /* renamed from: a */
    public void mo33964a(JSONObject jSONObject, Thread thread) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (f7096a != null) {
                f7096a.mo33965a(jSONObject2, thread);
            }
            C4439q.m8159a(jSONObject2, "cn", this.f7100c);
            if (this.f7099b != null) {
                jSONObject2.put("tn", this.f7099b);
            }
            if (thread == null) {
                jSONObject.put("ev", jSONObject2);
            } else {
                jSONObject.put("errkv", !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : JSONObjectInstrumentation.toString(jSONObject2));
            }
            if (f7098e != null && f7098e.length() > 0) {
                jSONObject.put("eva", f7098e);
            }
        } catch (Throwable th) {
            f7097d.mo33949e(th);
        }
    }
}
