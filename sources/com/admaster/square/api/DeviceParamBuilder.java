package com.admaster.square.api;

import com.admaster.square.utils.AndroidUtil;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.admaster.square.api.l */
class DeviceParamBuilder {
    /* renamed from: a */
    private DeviceInfo f233a;

    public DeviceParamBuilder(DeviceInfo deviceInfo) {
        this.f233a = deviceInfo;
    }

    /* renamed from: a */
    public ConcurrentHashMap<String, String> mo7773a() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        m360a(concurrentHashMap);
        return concurrentHashMap;
    }

    /* renamed from: a */
    private void m360a(ConcurrentHashMap<String, String> concurrentHashMap) {
        AndroidUtil.m393a(concurrentHashMap, "mac", this.f233a.f207a, true);
        AndroidUtil.m393a(concurrentHashMap, "idfa", this.f233a.f211e, true);
        AndroidUtil.m393a(concurrentHashMap, "imei", this.f233a.f208b, true);
        AndroidUtil.m393a(concurrentHashMap, "imsi", this.f233a.f209c, true);
        AndroidUtil.m393a(concurrentHashMap, "androidID", this.f233a.f210d, true);
        AndroidUtil.m393a(concurrentHashMap, "osv", this.f233a.f212f, true);
        AndroidUtil.m393a(concurrentHashMap, "brand", this.f233a.f213g, true);
        AndroidUtil.m393a(concurrentHashMap, "model", this.f233a.f214h, true);
        AndroidUtil.m393a(concurrentHashMap, "net", this.f233a.f215i, true);
        AndroidUtil.m393a(concurrentHashMap, "mcc", this.f233a.f216j, true);
        AndroidUtil.m393a(concurrentHashMap, "mnc", this.f233a.f217k, true);
        AndroidUtil.m393a(concurrentHashMap, "ap_mac", this.f233a.f218l, true);
        AndroidUtil.m393a(concurrentHashMap, "is_root", this.f233a.f220n, true);
        AndroidUtil.m393a(concurrentHashMap, "ap_name", this.f233a.f219m, true);
        AndroidUtil.m393a(concurrentHashMap, "iccid", this.f233a.f225s, true);
        AndroidUtil.m393a(concurrentHashMap, "cpuinfoid", this.f233a.f226t, true);
        AndroidUtil.m393a(concurrentHashMap, "sdfullsize", String.valueOf(this.f233a.f231y), true);
        AndroidUtil.m393a(concurrentHashMap, "sdfreesize", String.valueOf(this.f233a.f232z), true);
        AndroidUtil.m393a(concurrentHashMap, "screenpix", this.f233a.f228v, true);
        AndroidUtil.m393a(concurrentHashMap, "MAC1", this.f233a.f229w, true);
        AndroidUtil.m393a(concurrentHashMap, "mt", this.f233a.f230x, true);
        AndroidUtil.m393a(concurrentHashMap, "emufeatureserial", this.f233a.f201A, true);
        AndroidUtil.m393a(concurrentHashMap, "sysid", this.f233a.f202B, true);
        AndroidUtil.m393a(concurrentHashMap, "sysappid", this.f233a.f203C, true);
        AndroidUtil.m393a(concurrentHashMap, "sensorlistid", this.f233a.f204D, true);
        AndroidUtil.m393a(concurrentHashMap, "convisionid", this.f233a.f227u, true);
    }
}
