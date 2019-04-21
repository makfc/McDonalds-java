package com.tencent.wxop.stat.p069a;

import android.content.Context;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.tencent.p059a.p060a.p061a.p062a.C4344h;
import com.tencent.wxop.stat.C4411au;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.C4423a;
import com.tencent.wxop.stat.common.C4433k;
import com.tencent.wxop.stat.common.C4439q;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.e */
public abstract class C4377e {
    /* renamed from: j */
    protected static String f6943j = null;
    /* renamed from: a */
    private StatSpecifyReportedInfo f6944a = null;
    /* renamed from: b */
    protected String f6945b = null;
    /* renamed from: c */
    protected long f6946c;
    /* renamed from: d */
    protected int f6947d;
    /* renamed from: e */
    protected C4423a f6948e = null;
    /* renamed from: f */
    protected int f6949f;
    /* renamed from: g */
    protected String f6950g = null;
    /* renamed from: h */
    protected String f6951h = null;
    /* renamed from: i */
    protected String f6952i = null;
    /* renamed from: k */
    protected boolean f6953k = false;
    /* renamed from: l */
    protected Context f6954l;

    C4377e(Context context, int i, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f6954l = context;
        this.f6946c = System.currentTimeMillis() / 1000;
        this.f6947d = i;
        this.f6951h = StatConfig.getInstallChannel(context);
        this.f6952i = C4433k.m8127j(context);
        this.f6945b = StatConfig.getAppKey(context);
        if (statSpecifyReportedInfo != null) {
            this.f6944a = statSpecifyReportedInfo;
            if (C4433k.m8115c(statSpecifyReportedInfo.getAppKey())) {
                this.f6945b = statSpecifyReportedInfo.getAppKey();
            }
            if (C4433k.m8115c(statSpecifyReportedInfo.getInstallChannel())) {
                this.f6951h = statSpecifyReportedInfo.getInstallChannel();
            }
            if (C4433k.m8115c(statSpecifyReportedInfo.getVersion())) {
                this.f6952i = statSpecifyReportedInfo.getVersion();
            }
            this.f6953k = statSpecifyReportedInfo.isImportant();
        }
        this.f6950g = StatConfig.getCustomUserId(context);
        this.f6948e = C4411au.m8029a(context).mo33931b(context);
        if (mo33883a() != C4382f.NETWORK_DETECTOR) {
            this.f6949f = C4433k.m8136s(context).intValue();
        } else {
            this.f6949f = -C4382f.NETWORK_DETECTOR.mo33896a();
        }
        if (!C4344h.m7875c(f6943j)) {
            String localMidOnly = StatConfig.getLocalMidOnly(context);
            f6943j = localMidOnly;
            if (!C4433k.m8115c(localMidOnly)) {
                f6943j = "0";
            }
        }
    }

    /* renamed from: a */
    public abstract C4382f mo33883a();

    /* renamed from: a */
    public abstract boolean mo33884a(JSONObject jSONObject);

    /* renamed from: b */
    public boolean mo33885b(JSONObject jSONObject) {
        try {
            C4439q.m8159a(jSONObject, "ky", this.f6945b);
            jSONObject.put("et", mo33883a().mo33896a());
            if (this.f6948e != null) {
                jSONObject.put("ui", this.f6948e.mo33960b());
                C4439q.m8159a(jSONObject, "mc", this.f6948e.mo33961c());
                int d = this.f6948e.mo33962d();
                jSONObject.put("ut", d);
                if (d == 0 && C4433k.m8140w(this.f6954l) == 1) {
                    jSONObject.put("ia", 1);
                }
            }
            C4439q.m8159a(jSONObject, "cui", this.f6950g);
            if (mo33883a() != C4382f.SESSION_ENV) {
                C4439q.m8159a(jSONObject, "av", this.f6952i);
                C4439q.m8159a(jSONObject, "ch", this.f6951h);
            }
            if (this.f6953k) {
                jSONObject.put("impt", 1);
            }
            C4439q.m8159a(jSONObject, "mid", f6943j);
            jSONObject.put("idx", this.f6949f);
            jSONObject.put("si", this.f6947d);
            jSONObject.put("ts", this.f6946c);
            jSONObject.put("dts", C4433k.m8100a(this.f6954l, false));
            return mo33884a(jSONObject);
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: c */
    public long mo33886c() {
        return this.f6946c;
    }

    /* renamed from: d */
    public StatSpecifyReportedInfo mo33887d() {
        return this.f6944a;
    }

    /* renamed from: e */
    public Context mo33888e() {
        return this.f6954l;
    }

    /* renamed from: f */
    public boolean mo33889f() {
        return this.f6953k;
    }

    /* renamed from: g */
    public String mo33890g() {
        try {
            JSONObject jSONObject = new JSONObject();
            mo33885b(jSONObject);
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
        } catch (Throwable th) {
            return "";
        }
    }
}
