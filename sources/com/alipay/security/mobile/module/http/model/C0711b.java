package com.alipay.security.mobile.module.http.model;

import com.alipay.security.mobile.module.p019a.C0689a;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.alipay.security.mobile.module.http.model.b */
public class C0711b {
    /* renamed from: a */
    public static C0712c m1276a(DataReportResult dataReportResult) {
        C0712c c0712c = new C0712c();
        if (dataReportResult == null) {
            return null;
        }
        c0712c.f748a = dataReportResult.success;
        c0712c.f749b = dataReportResult.resultCode;
        Map map = dataReportResult.resultData;
        if (map != null) {
            c0712c.f750h = (String) map.get("apdid");
            c0712c.f751i = (String) map.get("apdidToken");
            c0712c.f754l = (String) map.get("dynamicKey");
            c0712c.f755m = (String) map.get("timeInterval");
            c0712c.f756n = (String) map.get("webrtcUrl");
            c0712c.f757o = "";
            String str = (String) map.get("drmSwitch");
            if (C0689a.m1172b(str)) {
                if (str.length() > 0) {
                    c0712c.f752j = str.charAt(0);
                }
                if (str.length() >= 3) {
                    c0712c.f753k = str.charAt(2);
                }
            }
            if (map.containsKey("apse_degrade")) {
                c0712c.mo8183b((String) map.get("apse_degrade"));
            }
        }
        return c0712c;
    }

    /* renamed from: a */
    public static DataReportRequest m1277a(C0713d c0713d) {
        DataReportRequest dataReportRequest = new DataReportRequest();
        if (c0713d == null) {
            return null;
        }
        dataReportRequest.f771os = c0713d.mo8197b();
        dataReportRequest.rpcVersion = c0713d.mo8194a();
        dataReportRequest.bizType = "1";
        dataReportRequest.bizData = new HashMap();
        dataReportRequest.bizData.put("apdid", c0713d.mo8199c());
        dataReportRequest.bizData.put("apdidToken", c0713d.mo8201d());
        dataReportRequest.bizData.put("umidToken", c0713d.mo8203e());
        dataReportRequest.bizData.put("dynamicKey", c0713d.mo8207g());
        dataReportRequest.deviceData = c0713d.mo8205f();
        return dataReportRequest;
    }
}
