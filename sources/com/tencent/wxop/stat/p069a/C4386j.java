package com.tencent.wxop.stat.p069a;

import android.content.Context;
import com.tencent.wxop.stat.C4389a;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.C4433k;
import com.tencent.wxop.stat.common.C4439q;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.j */
public class C4386j extends C4377e {
    /* renamed from: a */
    private static String f6981a = null;
    /* renamed from: m */
    private String f6982m = null;
    /* renamed from: n */
    private String f6983n = null;

    public C4386j(Context context, int i, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.f6982m = C4389a.m7995a(context).mo33900b();
        if (f6981a == null) {
            f6981a = C4433k.m8126i(context);
        }
    }

    /* renamed from: a */
    public C4382f mo33883a() {
        return C4382f.NETWORK_MONITOR;
    }

    /* renamed from: a */
    public void mo33897a(String str) {
        this.f6983n = str;
    }

    /* renamed from: a */
    public boolean mo33884a(JSONObject jSONObject) {
        C4439q.m8159a(jSONObject, "op", f6981a);
        C4439q.m8159a(jSONObject, "cn", this.f6982m);
        jSONObject.put("sp", this.f6983n);
        return true;
    }
}
