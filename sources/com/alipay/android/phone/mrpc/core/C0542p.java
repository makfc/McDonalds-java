package com.alipay.android.phone.mrpc.core;

/* renamed from: com.alipay.android.phone.mrpc.core.p */
public final class C0542p extends C0541u {
    /* renamed from: c */
    private int f375c;
    /* renamed from: d */
    private String f376d;
    /* renamed from: e */
    private long f377e;
    /* renamed from: f */
    private long f378f;
    /* renamed from: g */
    private String f379g;
    /* renamed from: h */
    private HttpUrlHeader f380h;

    public C0542p(HttpUrlHeader httpUrlHeader, int i, String str, byte[] bArr) {
        this.f380h = httpUrlHeader;
        this.f375c = i;
        this.f376d = str;
        this.f373a = bArr;
    }

    /* renamed from: a */
    public final HttpUrlHeader mo7922a() {
        return this.f380h;
    }

    /* renamed from: a */
    public final void mo7923a(long j) {
        this.f377e = j;
    }

    /* renamed from: a */
    public final void mo7924a(String str) {
        this.f379g = str;
    }

    /* renamed from: b */
    public final void mo7925b(long j) {
        this.f378f = j;
    }
}
