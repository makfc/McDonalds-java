package com.tencent.wxop.stat.p069a;

import android.content.Context;
import com.tencent.wxop.stat.StatConfig;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.C4439q;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.a.i */
public class C4385i extends C4377e {
    /* renamed from: a */
    public static final StatSpecifyReportedInfo f6980a;

    static {
        StatSpecifyReportedInfo statSpecifyReportedInfo = new StatSpecifyReportedInfo();
        f6980a = statSpecifyReportedInfo;
        statSpecifyReportedInfo.setAppKey("A9VH9B8L4GX4");
    }

    public C4385i(Context context) {
        super(context, 0, f6980a);
    }

    /* renamed from: a */
    public C4382f mo33883a() {
        return C4382f.NETWORK_DETECTOR;
    }

    /* renamed from: a */
    public boolean mo33884a(JSONObject jSONObject) {
        C4439q.m8159a(jSONObject, "actky", StatConfig.getAppKey(this.f6954l));
        return true;
    }
}
