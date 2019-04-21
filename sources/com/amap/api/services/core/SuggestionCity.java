package com.amap.api.services.core;

public class SuggestionCity {
    /* renamed from: a */
    private String f3605a;
    /* renamed from: b */
    private String f3606b;
    /* renamed from: c */
    private String f3607c;
    /* renamed from: d */
    private int f3608d;

    protected SuggestionCity() {
    }

    public SuggestionCity(String str, String str2, String str3, int i) {
        this.f3605a = str;
        this.f3606b = str2;
        this.f3607c = str3;
        this.f3608d = i;
    }

    public String getCityName() {
        return this.f3605a;
    }

    public void setCityName(String str) {
        this.f3605a = str;
    }

    public String getCityCode() {
        return this.f3606b;
    }

    public void setCityCode(String str) {
        this.f3606b = str;
    }

    public String getAdCode() {
        return this.f3607c;
    }

    public void setAdCode(String str) {
        this.f3607c = str;
    }

    public int getSuggestionNum() {
        return this.f3608d;
    }

    public void setSuggestionNum(int i) {
        this.f3608d = i;
    }
}
