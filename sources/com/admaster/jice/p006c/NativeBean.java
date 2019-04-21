package com.admaster.jice.p006c;

import java.io.Serializable;

/* renamed from: com.admaster.jice.c.d */
public class NativeBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String androidIDFA;
    private String androidId;
    private String deviceMac;
    private String deviceMd5;
    private String imei;
    private String imsi;
    private String mac1;

    public String getDeviceMac() {
        return this.deviceMac;
    }

    public void setDeviceMac(String str) {
        this.deviceMac = str;
    }

    public String getImei() {
        return this.imei;
    }

    public void setImei(String str) {
        this.imei = str;
    }

    public String getImsi() {
        return this.imsi;
    }

    public void setImsi(String str) {
        this.imsi = str;
    }

    public String getAndroidId() {
        return this.androidId;
    }

    public void setAndroidId(String str) {
        this.androidId = str;
    }

    public String getAndroidIDFA() {
        return this.androidIDFA;
    }

    public void setAndroidIDFA(String str) {
        this.androidIDFA = str;
    }

    public String getMac1() {
        return this.mac1;
    }

    public void setMac1(String str) {
        this.mac1 = str;
    }

    public String getDeviceMd5() {
        return this.deviceMd5;
    }

    public void setDeviceMd5(String str) {
        this.deviceMd5 = str;
    }
}
