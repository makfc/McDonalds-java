package com.admaster.jice.p004a;

import android.text.TextUtils;
import com.admaster.jice.p005b.HttpConfig;
import com.admaster.jice.p005b.JicePushConfig;
import com.admaster.jice.p007d.HttpURLRequest;
import com.admaster.jice.p007d.ManagerUtils;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JicePushManager */
/* renamed from: com.admaster.jice.a.n */
class C0465n extends Thread {
    /* renamed from: a */
    final /* synthetic */ JicePushManager f69a;
    /* renamed from: b */
    private JSONObject f70b = null;

    public C0465n(JicePushManager jicePushManager, String str) {
        this.f69a = jicePushManager;
        this.f70b = ManagerUtils.m238c(jicePushManager.f58g, str);
        jicePushManager.f57f = true;
    }

    public void run() {
        String jSONObjectInstrumentation;
        Exception e;
        JSONObject jSONObject = new JSONObject();
        try {
            Object jSONObject2;
            if (this.f70b == null) {
                this.f70b = new JSONObject();
            }
            jSONObject.put("system", this.f70b);
            String b = StoreManager.m130a(this.f69a.f58g).mo7637b();
            if (TextUtils.isEmpty(b)) {
                jSONObject2 = new JSONObject();
            } else {
                jSONObject2 = JSONObjectInstrumentation.init(b);
            }
            jSONObject.put("profile", jSONObject2);
        } catch (JSONException e2) {
            e2.printStackTrace();
            this.f69a.f57f = false;
        }
        try {
            HttpURLRequest d = this.f69a.f60i;
            String d2 = HttpConfig.m165d();
            if (jSONObject instanceof JSONObject) {
                jSONObjectInstrumentation = JSONObjectInstrumentation.toString(jSONObject);
            } else {
                jSONObjectInstrumentation = jSONObject.toString();
            }
            byte[] b2 = d.mo7716b(d2, jSONObjectInstrumentation, HttpConfig.m167f());
            if (b2 != null) {
                jSONObjectInstrumentation = new String(b2);
            } else {
                CharSequence charSequence = null;
            }
            try {
                if (!TextUtils.isEmpty(jSONObjectInstrumentation)) {
                    this.f69a.m75a(jSONObjectInstrumentation);
                    JSONArray init = JSONArrayInstrumentation.init(jSONObjectInstrumentation);
                    this.f69a.f53b.clear();
                    int length = init.length();
                    for (int i = 0; i < length; i++) {
                        JicePushConfig jicePushConfig = new JicePushConfig(this.f69a.f63l, init.getJSONObject(i));
                        this.f69a.f53b.add(jicePushConfig);
                        this.f69a.m74a(jicePushConfig);
                    }
                }
            } catch (Exception e3) {
                e = e3;
                e.printStackTrace();
                this.f69a.f57f = false;
                this.f69a.f57f = false;
                this.f69a.m85c("JicePushFinder work finished:" + jSONObjectInstrumentation + "  confilist`len:" + this.f69a.f53b.size());
            }
        } catch (Exception e4) {
            e = e4;
            jSONObjectInstrumentation = null;
        }
        this.f69a.f57f = false;
        this.f69a.m85c("JicePushFinder work finished:" + jSONObjectInstrumentation + "  confilist`len:" + this.f69a.f53b.size());
    }
}
