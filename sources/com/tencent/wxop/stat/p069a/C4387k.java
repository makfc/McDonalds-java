package com.tencent.wxop.stat.p069a;

import android.content.Context;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.C4439q;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.k */
public class C4387k extends C4377e {
    /* renamed from: a */
    Long f6984a = null;
    /* renamed from: m */
    String f6985m;
    /* renamed from: n */
    String f6986n;

    public C4387k(Context context, String str, String str2, int i, Long l, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.f6986n = str;
        this.f6985m = str2;
        this.f6984a = l;
    }

    /* renamed from: a */
    public C4382f mo33883a() {
        return C4382f.PAGE_VIEW;
    }

    /* renamed from: a */
    public boolean mo33884a(JSONObject jSONObject) {
        C4439q.m8159a(jSONObject, "pi", this.f6985m);
        C4439q.m8159a(jSONObject, "rf", this.f6986n);
        if (this.f6984a != null) {
            jSONObject.put("du", this.f6984a);
        }
        return true;
    }
}
