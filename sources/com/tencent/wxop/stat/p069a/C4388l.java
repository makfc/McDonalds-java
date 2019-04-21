package com.tencent.wxop.stat.p069a;

import android.content.Context;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.C4424b;
import com.tencent.wxop.stat.common.C4433k;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.l */
public class C4388l extends C4377e {
    /* renamed from: a */
    private C4424b f6987a;
    /* renamed from: m */
    private JSONObject f6988m = null;

    public C4388l(Context context, int i, JSONObject jSONObject, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.f6987a = new C4424b(context);
        this.f6988m = jSONObject;
    }

    /* renamed from: a */
    public C4382f mo33883a() {
        return C4382f.SESSION_ENV;
    }

    /* renamed from: a */
    public boolean mo33884a(JSONObject jSONObject) {
        if (this.f6948e != null) {
            jSONObject.put("ut", this.f6948e.mo33962d());
        }
        if (this.f6988m != null) {
            jSONObject.put("cfg", this.f6988m);
        }
        if (C4433k.m8142y(this.f6954l)) {
            jSONObject.put("ncts", 1);
        }
        this.f6987a.mo33964a(jSONObject, null);
        return true;
    }
}
