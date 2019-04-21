package com.alipay.security.mobile.module.http;

import android.content.Context;
import com.alipay.android.phone.mrpc.core.C0518aa;
import com.alipay.android.phone.mrpc.core.C0530w;
import com.alipay.android.phone.mrpc.core.C0531h;
import com.alipay.security.mobile.module.p019a.C0689a;
import com.alipay.tscenter.biz.rpc.p025a.C0716a;
import com.alipay.tscenter.biz.rpc.report.general.C0717a;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import com.facebook.Response;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;

/* renamed from: com.alipay.security.mobile.module.http.b */
public class C0706b implements C0705a {
    /* renamed from: d */
    private static C0706b f741d = null;
    /* renamed from: e */
    private static DataReportResult f742e;
    /* renamed from: a */
    private C0530w f743a = null;
    /* renamed from: b */
    private C0716a f744b = null;
    /* renamed from: c */
    private C0717a f745c = null;

    private C0706b(Context context, String str) {
        C0518aa c0518aa = new C0518aa();
        c0518aa.mo7868a(str);
        this.f743a = new C0531h(context);
        this.f744b = (C0716a) this.f743a.mo7896a(C0716a.class, c0518aa);
        this.f745c = (C0717a) this.f743a.mo7896a(C0717a.class, c0518aa);
    }

    /* renamed from: a */
    public static synchronized C0706b m1266a(Context context, String str) {
        C0706b c0706b;
        synchronized (C0706b.class) {
            if (f741d == null) {
                f741d = new C0706b(context, str);
            }
            c0706b = f741d;
        }
        return c0706b;
    }

    /* renamed from: a */
    public DataReportResult mo8179a(DataReportRequest dataReportRequest) {
        if (dataReportRequest == null) {
            return null;
        }
        if (this.f745c != null) {
            f742e = null;
            new Thread(new C0707c(this, dataReportRequest)).start();
            int i = 300000;
            while (f742e == null && i >= 0) {
                Thread.sleep(50);
                i -= 50;
            }
        }
        return f742e;
    }

    /* renamed from: a */
    public boolean mo8180a(String str) {
        if (C0689a.m1169a(str)) {
            return false;
        }
        boolean booleanValue;
        if (this.f744b != null) {
            String str2 = null;
            try {
                C0716a c0716a = this.f744b;
                C0689a.m1176f(str);
                str2 = c0716a.mo8213a();
            } catch (Throwable th) {
            }
            if (!C0689a.m1169a(str2)) {
                booleanValue = ((Boolean) JSONObjectInstrumentation.init(str2).get(Response.SUCCESS_KEY)).booleanValue();
                return booleanValue;
            }
        }
        booleanValue = false;
        return booleanValue;
    }
}
