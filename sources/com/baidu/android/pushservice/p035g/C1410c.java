package com.baidu.android.pushservice.p035g;

import android.graphics.Bitmap;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;

/* renamed from: com.baidu.android.pushservice.g.c */
public class C1410c {
    /* renamed from: a */
    protected String f4938a = "";
    /* renamed from: b */
    protected String f4939b = "";
    /* renamed from: c */
    protected String f4940c = "";
    /* renamed from: d */
    protected byte[] f4941d;
    /* renamed from: e */
    protected String f4942e = "";
    /* renamed from: f */
    protected String f4943f = "";
    /* renamed from: g */
    protected String f4944g = "";
    /* renamed from: h */
    protected String f4945h = "";
    /* renamed from: i */
    protected String f4946i = "";
    /* renamed from: j */
    protected String f4947j = "";
    /* renamed from: k */
    protected boolean f4948k = true;

    public C1410c(String str, String str2) {
        this.f4939b = str;
        this.f4938a = str2;
    }

    /* renamed from: a */
    public Bitmap mo13770a() {
        return this.f4941d != null ? BitmapFactoryInstrumentation.decodeByteArray(this.f4941d, 0, this.f4941d.length, null) : null;
    }

    /* renamed from: a */
    public void mo13771a(String str) {
        this.f4940c = str;
    }

    /* renamed from: a */
    public void mo13772a(boolean z) {
        this.f4948k = z;
    }

    /* renamed from: a */
    public void mo13773a(byte[] bArr) {
        this.f4941d = bArr;
    }

    /* renamed from: b */
    public String mo13774b() {
        return this.f4938a;
    }

    /* renamed from: b */
    public void mo13775b(String str) {
        this.f4938a = str;
    }

    /* renamed from: c */
    public String mo13776c() {
        return this.f4939b;
    }

    /* renamed from: c */
    public void mo13777c(String str) {
        this.f4939b = str;
    }

    /* renamed from: d */
    public String mo13778d() {
        return this.f4940c;
    }

    /* renamed from: d */
    public void mo13779d(String str) {
        this.f4942e = str;
    }

    /* renamed from: e */
    public String mo13780e() {
        return this.f4942e;
    }

    /* renamed from: e */
    public void mo13781e(String str) {
        this.f4943f = str;
    }

    /* renamed from: f */
    public String mo13782f() {
        return this.f4943f;
    }

    /* renamed from: f */
    public void mo13783f(String str) {
        this.f4944g = str;
    }

    /* renamed from: g */
    public void mo13784g(String str) {
        this.f4945h = str;
    }

    /* renamed from: g */
    public boolean mo13785g() {
        return this.f4948k;
    }

    /* renamed from: h */
    public void mo13786h(String str) {
        this.f4946i = str;
    }

    /* renamed from: i */
    public void mo13787i(String str) {
        this.f4947j = str;
    }
}
