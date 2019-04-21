package com.baidu.android.pushservice.p037i;

import android.text.TextUtils;

/* renamed from: com.baidu.android.pushservice.i.g */
public class C1440g {
    /* renamed from: a */
    private int f5075a;
    /* renamed from: b */
    private String f5076b;
    /* renamed from: c */
    private long f5077c;
    /* renamed from: d */
    private String f5078d;
    /* renamed from: e */
    private int f5079e;
    /* renamed from: f */
    private String f5080f;
    /* renamed from: g */
    private int f5081g;
    /* renamed from: h */
    private String f5082h;
    /* renamed from: i */
    private String f5083i;
    /* renamed from: j */
    private int f5084j;
    /* renamed from: k */
    private int f5085k;
    /* renamed from: l */
    private String f5086l;
    /* renamed from: m */
    private String f5087m;
    /* renamed from: n */
    private String f5088n;

    /* renamed from: a */
    public String mo13870a() {
        return this.f5076b;
    }

    /* renamed from: a */
    public void mo13871a(int i) {
        this.f5075a = i;
    }

    /* renamed from: a */
    public void mo13872a(long j) {
        this.f5077c = j;
    }

    /* renamed from: a */
    public void mo13873a(String str) {
        this.f5076b = str;
    }

    /* renamed from: b */
    public String mo13874b() {
        return this.f5086l;
    }

    /* renamed from: b */
    public void mo13875b(int i) {
        this.f5079e = i;
    }

    /* renamed from: b */
    public void mo13876b(String str) {
        this.f5078d = str;
    }

    /* renamed from: c */
    public C1435r mo13877c() {
        return new C1435r(this.f5076b, this.f5077c, this.f5078d, this.f5085k, this.f5086l);
    }

    /* renamed from: c */
    public void mo13878c(int i) {
        this.f5081g = i;
    }

    /* renamed from: c */
    public void mo13879c(String str) {
        this.f5080f = str;
    }

    /* renamed from: d */
    public C1446k mo13880d() {
        C1446k c1446k = new C1446k(mo13877c());
        c1446k.f5105a = this.f5084j;
        c1446k.f5042l = this.f5082h;
        return c1446k;
    }

    /* renamed from: d */
    public void mo13881d(int i) {
        this.f5084j = i;
    }

    /* renamed from: d */
    public void mo13882d(String str) {
        this.f5082h = str;
    }

    /* renamed from: e */
    public C1450o mo13883e() {
        C1450o c1450o = new C1450o(mo13877c());
        c1450o.f5121c = this.f5079e;
        c1450o.f5119a = this.f5080f;
        c1450o.f5120b = this.f5081g;
        String str = this.f5088n;
        if (!TextUtils.isEmpty(str)) {
            c1450o.f5122d = str;
        }
        return c1450o;
    }

    /* renamed from: e */
    public void mo13884e(int i) {
        this.f5085k = i;
    }

    /* renamed from: e */
    public void mo13885e(String str) {
        this.f5083i = str;
    }

    /* renamed from: f */
    public C1438d mo13886f() {
        C1438d c1438d = new C1438d(mo13877c());
        c1438d.f5063a = this.f5082h;
        c1438d.f5064b = this.f5083i;
        c1438d.f5065c = this.f5087m;
        return c1438d;
    }

    /* renamed from: f */
    public void mo13887f(String str) {
        this.f5086l = str;
    }

    /* renamed from: g */
    public C1441h mo13888g() {
        C1441h c1441h = new C1441h(mo13877c());
        c1441h.f5089a = this.f5082h;
        return c1441h;
    }

    /* renamed from: g */
    public void mo13889g(String str) {
        this.f5087m = str;
    }

    /* renamed from: h */
    public void mo13890h(String str) {
        this.f5088n = str;
    }
}
