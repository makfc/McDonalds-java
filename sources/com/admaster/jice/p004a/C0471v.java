package com.admaster.jice.p004a;

import android.text.TextUtils;
import com.admaster.jice.p005b.HttpConfig;
import com.admaster.jice.p005b.JicePushConfig;
import com.admaster.jice.p007d.HttpURLRequest;
import com.admaster.jice.p007d.LOG;
import com.admaster.jice.p007d.ManagerUtils;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JiceTestPushManager */
/* renamed from: com.admaster.jice.a.v */
class C0471v extends Thread {
    /* renamed from: a */
    final /* synthetic */ JiceTestPushManager f92a;
    /* renamed from: b */
    private JSONObject f93b = null;
    /* renamed from: c */
    private String f94c = "";
    /* renamed from: d */
    private String f95d = null;

    public C0471v(JiceTestPushManager jiceTestPushManager, String str, String str2) {
        this.f92a = jiceTestPushManager;
        this.f93b = ManagerUtils.m238c(jiceTestPushManager.f87a, str2);
        this.f94c = str2;
        this.f95d = str;
    }

    public void run() {
        CharSequence charSequence = null;
        try {
            if (this.f92a.f89c.mo7713a(this.f92a.f87a)) {
                Object jSONObject;
                String jSONObjectInstrumentation;
                JSONObject jSONObject2 = new JSONObject();
                if (this.f93b == null) {
                    this.f93b = new JSONObject();
                }
                jSONObject2.put("system", this.f93b);
                String b = StoreManager.m130a(this.f92a.f87a).mo7637b();
                if (TextUtils.isEmpty(b)) {
                    jSONObject = new JSONObject();
                } else {
                    jSONObject = JSONObjectInstrumentation.init(b);
                }
                jSONObject2.put("profile", jSONObject);
                b = HttpConfig.m165d() + "?testId=" + this.f95d;
                HttpURLRequest b2 = this.f92a.f89c;
                if (jSONObject2 instanceof JSONObject) {
                    jSONObjectInstrumentation = JSONObjectInstrumentation.toString(jSONObject2);
                } else {
                    jSONObjectInstrumentation = jSONObject2.toString();
                }
                byte[] b3 = b2.mo7716b(b, jSONObjectInstrumentation, null);
                if (b3 != null) {
                    charSequence = new String(b3);
                }
                try {
                    if (!TextUtils.isEmpty(charSequence)) {
                        this.f92a.m125a((String) charSequence, this.f94c);
                        this.f92a.m123a(new JicePushConfig(this.f94c, JSONArrayInstrumentation.init(charSequence).getJSONObject(0)));
                    }
                } catch (JSONException e) {
                    LOG.m225a("JiceSDK.JiceTestPushManager", "Constructor JiceTestConfig failed!", e);
                }
                this.f92a.m124a("requestTestPushConfig:" + charSequence);
                return;
            }
            this.f92a.m124a("network is disable!");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
