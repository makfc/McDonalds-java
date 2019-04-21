package com.tencent.wxop.stat.p069a;

import android.content.Context;
import com.tencent.wxop.stat.StatAccount;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.C4439q;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.a */
public class C4378a extends C4377e {
    /* renamed from: a */
    private StatAccount f6955a = null;

    public C4378a(Context context, int i, StatAccount statAccount, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.f6955a = statAccount;
    }

    /* renamed from: a */
    public C4382f mo33883a() {
        return C4382f.ADDITION;
    }

    /* renamed from: a */
    public boolean mo33884a(JSONObject jSONObject) {
        C4439q.m8159a(jSONObject, "qq", this.f6955a.getAccount());
        jSONObject.put("acc", this.f6955a.toJsonString());
        return true;
    }
}
