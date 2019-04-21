package com.alipay.security.mobile.module.http.model;

import com.alipay.security.mobile.module.p019a.C0689a;

/* renamed from: com.alipay.security.mobile.module.http.model.c */
public class C0712c extends C0710a {
    /* renamed from: h */
    public String f750h;
    /* renamed from: i */
    public String f751i;
    /* renamed from: j */
    public String f752j;
    /* renamed from: k */
    public String f753k;
    /* renamed from: l */
    public String f754l;
    /* renamed from: m */
    public String f755m;
    /* renamed from: n */
    public String f756n;
    /* renamed from: o */
    public String f757o;
    /* renamed from: p */
    public String f758p = "";

    /* renamed from: b */
    public void mo8183b(String str) {
        this.f758p = str;
    }

    /* renamed from: c */
    public String mo8184c() {
        return this.f758p;
    }

    /* renamed from: d */
    public int mo8185d() {
        return this.f748a ? C0689a.m1169a(this.f750h) ? 2 : 1 : "APPKEY_ERROR".equals(this.f749b) ? 3 : 2;
    }

    /* renamed from: e */
    public boolean mo8186e() {
        return "1".equals(this.f752j);
    }

    /* renamed from: f */
    public String mo8187f() {
        return this.f753k == null ? "0" : this.f753k;
    }

    /* renamed from: g */
    public String mo8188g() {
        return this.f750h;
    }

    /* renamed from: h */
    public String mo8189h() {
        return this.f751i;
    }

    /* renamed from: j */
    public String mo8190j() {
        return this.f754l;
    }

    /* renamed from: k */
    public String mo8191k() {
        return this.f756n;
    }

    /* renamed from: l */
    public String mo8192l() {
        return this.f755m;
    }

    /* renamed from: m */
    public String mo8193m() {
        return this.f757o;
    }
}
