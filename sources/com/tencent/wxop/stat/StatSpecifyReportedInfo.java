package com.tencent.wxop.stat;

public class StatSpecifyReportedInfo {
    /* renamed from: a */
    private String f6938a = null;
    /* renamed from: b */
    private String f6939b = null;
    /* renamed from: c */
    private String f6940c = null;
    /* renamed from: d */
    private boolean f6941d = false;
    /* renamed from: e */
    private boolean f6942e = false;

    public String getAppKey() {
        return this.f6938a;
    }

    public String getInstallChannel() {
        return this.f6939b;
    }

    public String getVersion() {
        return this.f6940c;
    }

    public boolean isImportant() {
        return this.f6942e;
    }

    public boolean isSendImmediately() {
        return this.f6941d;
    }

    public void setAppKey(String str) {
        this.f6938a = str;
    }

    public String toString() {
        return "StatSpecifyReportedInfo [appKey=" + this.f6938a + ", installChannel=" + this.f6939b + ", version=" + this.f6940c + ", sendImmediately=" + this.f6941d + ", isImportant=" + this.f6942e + "]";
    }
}
