package com.alipay.security.mobile.module.http;

import com.alipay.security.mobile.module.p019a.C0689a;
import com.alipay.tscenter.biz.rpc.report.general.C0717a;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;

/* renamed from: com.alipay.security.mobile.module.http.c */
class C0707c implements Runnable {
    /* renamed from: a */
    final /* synthetic */ DataReportRequest f746a;
    /* renamed from: b */
    final /* synthetic */ C0706b f747b;

    C0707c(C0706b c0706b, DataReportRequest dataReportRequest) {
        this.f747b = c0706b;
        this.f746a = dataReportRequest;
    }

    public void run() {
        try {
            C0717a a = this.f747b.f745c;
            DataReportRequest dataReportRequest = this.f746a;
            C0706b.f742e = a.mo8214a();
        } catch (Throwable th) {
            C0706b.f742e = new DataReportResult();
            C0706b.f742e.success = false;
            C0706b.f742e.resultCode = "static data rpc upload error, " + C0689a.m1167a(th);
            C0689a.m1167a(th);
        }
    }
}
