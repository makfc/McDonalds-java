package com.amap.api.services.core;

public class ServiceSettings {
    public static final int HTTP = 1;
    public static final int HTTPS = 2;
    /* renamed from: b */
    private static ServiceSettings f3603b;
    /* renamed from: a */
    private int f3604a = 1;

    private ServiceSettings() {
    }

    public static ServiceSettings getInstance() {
        if (f3603b == null) {
            f3603b = new ServiceSettings();
        }
        return f3603b;
    }

    public void setProtocol(int i) {
        this.f3604a = i;
    }

    public int getProtocol() {
        return this.f3604a;
    }

    public void setApiKey(String str) {
        C1135w.m5089a(str);
    }
}
