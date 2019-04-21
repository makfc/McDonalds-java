package com.tencent.wxop.stat.p069a;

import android.content.Context;
import com.tencent.wxop.stat.C4389a;
import com.tencent.wxop.stat.StatAppMonitor;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.C4433k;
import com.tencent.wxop.stat.common.C4439q;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.h */
public class C4384h extends C4377e {
    /* renamed from: m */
    private static String f6977m = null;
    /* renamed from: n */
    private static String f6978n = null;
    /* renamed from: a */
    private StatAppMonitor f6979a = null;

    public C4384h(Context context, int i, StatAppMonitor statAppMonitor, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.f6979a = statAppMonitor.clone();
    }

    /* renamed from: a */
    public C4382f mo33883a() {
        return C4382f.MONITOR_STAT;
    }

    /* renamed from: a */
    public boolean mo33884a(JSONObject jSONObject) {
        if (this.f6979a == null) {
            return false;
        }
        jSONObject.put("na", this.f6979a.getInterfaceName());
        jSONObject.put("rq", this.f6979a.getReqSize());
        jSONObject.put("rp", this.f6979a.getRespSize());
        jSONObject.put("rt", this.f6979a.getResultType());
        jSONObject.put("tm", this.f6979a.getMillisecondsConsume());
        jSONObject.put("rc", this.f6979a.getReturnCode());
        jSONObject.put("sp", this.f6979a.getSampling());
        if (f6978n == null) {
            f6978n = C4433k.m8131n(this.f6954l);
        }
        C4439q.m8159a(jSONObject, "av", f6978n);
        if (f6977m == null) {
            f6977m = C4433k.m8126i(this.f6954l);
        }
        C4439q.m8159a(jSONObject, "op", f6977m);
        jSONObject.put("cn", C4389a.m7995a(this.f6954l).mo33900b());
        return true;
    }
}
