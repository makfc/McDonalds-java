package com.tencent.wxop.stat.common;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.mcdonalds.sdk.modules.notification.PushConstants;
import com.newrelic.agent.android.instrumentation.JSONArrayInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.tencent.wxop.stat.C4389a;
import com.tencent.wxop.stat.C4411au;
import com.tencent.wxop.stat.StatConfig;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.common.d */
class C4426d {
    /* renamed from: a */
    String f7101a;
    /* renamed from: b */
    String f7102b;
    /* renamed from: c */
    DisplayMetrics f7103c;
    /* renamed from: d */
    int f7104d;
    /* renamed from: e */
    String f7105e;
    /* renamed from: f */
    String f7106f;
    /* renamed from: g */
    String f7107g;
    /* renamed from: h */
    String f7108h;
    /* renamed from: i */
    String f7109i;
    /* renamed from: j */
    String f7110j;
    /* renamed from: k */
    String f7111k;
    /* renamed from: l */
    int f7112l;
    /* renamed from: m */
    String f7113m;
    /* renamed from: n */
    String f7114n;
    /* renamed from: o */
    Context f7115o;
    /* renamed from: p */
    private String f7116p;
    /* renamed from: q */
    private String f7117q;
    /* renamed from: r */
    private String f7118r;
    /* renamed from: s */
    private String f7119s;

    private C4426d(Context context) {
        this.f7102b = "2.0.3";
        this.f7104d = VERSION.SDK_INT;
        this.f7105e = Build.MODEL;
        this.f7106f = Build.MANUFACTURER;
        this.f7107g = Locale.getDefault().getLanguage();
        this.f7112l = 0;
        this.f7113m = null;
        this.f7114n = null;
        this.f7115o = null;
        this.f7116p = null;
        this.f7117q = null;
        this.f7118r = null;
        this.f7119s = null;
        this.f7115o = context.getApplicationContext();
        this.f7103c = C4433k.m8116d(this.f7115o);
        this.f7101a = C4433k.m8127j(this.f7115o);
        this.f7108h = StatConfig.getInstallChannel(this.f7115o);
        this.f7109i = C4433k.m8126i(this.f7115o);
        this.f7110j = TimeZone.getDefault().getID();
        this.f7112l = C4433k.m8132o(this.f7115o);
        this.f7111k = C4433k.m8133p(this.f7115o);
        this.f7113m = this.f7115o.getPackageName();
        if (this.f7104d >= 14) {
            this.f7116p = C4433k.m8139v(this.f7115o);
        }
        JSONObject u = C4433k.m8138u(this.f7115o);
        this.f7117q = !(u instanceof JSONObject) ? u.toString() : JSONObjectInstrumentation.toString(u);
        this.f7118r = C4433k.m8137t(this.f7115o);
        this.f7119s = C4433k.m8117d();
        this.f7114n = C4433k.m8097C(this.f7115o);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo33965a(JSONObject jSONObject, Thread thread) {
        if (thread == null) {
            if (this.f7103c != null) {
                jSONObject.put("sr", this.f7103c.widthPixels + "*" + this.f7103c.heightPixels);
                jSONObject.put("dpi", this.f7103c.xdpi + "*" + this.f7103c.ydpi);
            }
            if (C4389a.m7995a(this.f7115o).mo33903e()) {
                JSONObject jSONObject2 = new JSONObject();
                C4439q.m8159a(jSONObject2, "bs", C4439q.m8164d(this.f7115o));
                C4439q.m8159a(jSONObject2, "ss", C4439q.m8165e(this.f7115o));
                if (jSONObject2.length() > 0) {
                    C4439q.m8159a(jSONObject, "wf", !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : JSONObjectInstrumentation.toString(jSONObject2));
                }
            }
            JSONArray a = C4439q.m8158a(this.f7115o, 10);
            if (a != null && a.length() > 0) {
                C4439q.m8159a(jSONObject, "wflist", !(a instanceof JSONArray) ? a.toString() : JSONArrayInstrumentation.toString(a));
            }
            C4439q.m8159a(jSONObject, "sen", this.f7116p);
        } else {
            C4439q.m8159a(jSONObject, "thn", thread.getName());
            C4439q.m8159a(jSONObject, "qq", StatConfig.getQQ(this.f7115o));
            C4439q.m8159a(jSONObject, "cui", StatConfig.getCustomUserId(this.f7115o));
            if (C4433k.m8115c(this.f7118r) && this.f7118r.split("/").length == 2) {
                C4439q.m8159a(jSONObject, "fram", this.f7118r.split("/")[0]);
            }
            if (C4433k.m8115c(this.f7119s) && this.f7119s.split("/").length == 2) {
                C4439q.m8159a(jSONObject, PushConstants.FROM_ID, this.f7119s.split("/")[0]);
            }
            if (C4411au.m8029a(this.f7115o).mo33931b(this.f7115o) != null) {
                jSONObject.put("ui", C4411au.m8029a(this.f7115o).mo33931b(this.f7115o).mo33960b());
            }
            C4439q.m8159a(jSONObject, "mid", StatConfig.getLocalMidOnly(this.f7115o));
        }
        C4439q.m8159a(jSONObject, "pcn", C4433k.m8134q(this.f7115o));
        C4439q.m8159a(jSONObject, "osn", VERSION.RELEASE);
        C4439q.m8159a(jSONObject, "av", this.f7101a);
        C4439q.m8159a(jSONObject, "ch", this.f7108h);
        C4439q.m8159a(jSONObject, "mf", this.f7106f);
        C4439q.m8159a(jSONObject, "sv", this.f7102b);
        C4439q.m8159a(jSONObject, "osd", Build.DISPLAY);
        C4439q.m8159a(jSONObject, "prod", Build.PRODUCT);
        C4439q.m8159a(jSONObject, "tags", Build.TAGS);
        C4439q.m8159a(jSONObject, "id", Build.ID);
        C4439q.m8159a(jSONObject, "fng", Build.FINGERPRINT);
        C4439q.m8159a(jSONObject, "lch", this.f7114n);
        C4439q.m8159a(jSONObject, "ov", Integer.toString(this.f7104d));
        jSONObject.put("os", 1);
        C4439q.m8159a(jSONObject, "op", this.f7109i);
        C4439q.m8159a(jSONObject, "lg", this.f7107g);
        C4439q.m8159a(jSONObject, "md", this.f7105e);
        C4439q.m8159a(jSONObject, "tz", this.f7110j);
        if (this.f7112l != 0) {
            jSONObject.put("jb", this.f7112l);
        }
        C4439q.m8159a(jSONObject, "sd", this.f7111k);
        C4439q.m8159a(jSONObject, "apn", this.f7113m);
        C4439q.m8159a(jSONObject, "cpu", this.f7117q);
        C4439q.m8159a(jSONObject, "abi", Build.CPU_ABI);
        C4439q.m8159a(jSONObject, "abi2", Build.CPU_ABI2);
        C4439q.m8159a(jSONObject, "ram", this.f7118r);
        C4439q.m8159a(jSONObject, "rom", this.f7119s);
    }
}
