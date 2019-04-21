package com.baidu.android.pushservice.p037i;

import android.text.TextUtils;

/* renamed from: com.baidu.android.pushservice.i.a */
public class C1433a {
    /* renamed from: a */
    private int f5025a;
    /* renamed from: b */
    private String f5026b;
    /* renamed from: c */
    private long f5027c;
    /* renamed from: d */
    private String f5028d;
    /* renamed from: e */
    private int f5029e;
    /* renamed from: f */
    private String f5030f;
    /* renamed from: g */
    private int f5031g;
    /* renamed from: h */
    private String f5032h;
    /* renamed from: i */
    private int f5033i;
    /* renamed from: j */
    private String f5034j;
    /* renamed from: k */
    private String f5035k;

    /* renamed from: a */
    public String mo13834a() {
        return this.f5026b;
    }

    /* renamed from: a */
    public void mo13835a(int i) {
        this.f5025a = i;
    }

    /* renamed from: a */
    public void mo13836a(long j) {
        this.f5027c = j;
    }

    /* renamed from: a */
    public void mo13837a(String str) {
        this.f5026b = str;
    }

    /* renamed from: b */
    public String mo13838b() {
        return this.f5034j;
    }

    /* renamed from: b */
    public void mo13839b(int i) {
        this.f5029e = i;
    }

    /* renamed from: b */
    public void mo13840b(String str) {
        this.f5028d = str;
    }

    /* renamed from: c */
    public C1435r mo13841c() {
        return new C1435r(this.f5026b, this.f5027c, this.f5028d, this.f5033i, this.f5034j);
    }

    /* renamed from: c */
    public void mo13842c(int i) {
        this.f5031g = i;
    }

    /* renamed from: c */
    public void mo13843c(String str) {
        this.f5030f = str;
    }

    /* renamed from: d */
    public C1446k mo13844d() {
        return new C1446k(mo13841c());
    }

    /* renamed from: d */
    public void mo13845d(int i) {
        this.f5033i = i;
    }

    /* renamed from: d */
    public void mo13846d(String str) {
        this.f5032h = str;
    }

    /* renamed from: e */
    public C1436b mo13847e() {
        C1436b c1436b = new C1436b(mo13841c());
        c1436b.f5054c = this.f5029e;
        c1436b.f5052a = this.f5030f;
        c1436b.f5053b = this.f5031g;
        if (!TextUtils.isEmpty(this.f5035k)) {
            c1436b.f5056e = this.f5035k;
        }
        if (!TextUtils.isEmpty(this.f5032h)) {
            c1436b.f5055d = this.f5032h;
        }
        return c1436b;
    }

    /* renamed from: e */
    public void mo13848e(String str) {
        this.f5034j = str;
    }

    /* renamed from: f */
    public void mo13849f(String str) {
        this.f5035k = str;
    }
}
