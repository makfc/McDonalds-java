package com.baidu.android.pushservice.message;

/* renamed from: com.baidu.android.pushservice.message.f */
public class C1506f {
    /* renamed from: a */
    protected short f5258a;
    /* renamed from: b */
    protected short f5259b;
    /* renamed from: c */
    protected byte[] f5260c;
    /* renamed from: d */
    protected boolean f5261d;
    /* renamed from: e */
    protected boolean f5262e = false;
    /* renamed from: f */
    protected boolean f5263f;
    /* renamed from: g */
    private C1512k f5264g;

    public C1506f(short s) {
        this.f5258a = s;
    }

    /* renamed from: a */
    public void mo13983a(C1512k c1512k) {
        this.f5264g = c1512k;
    }

    /* renamed from: a */
    public void mo13984a(boolean z) {
        this.f5263f = z;
    }

    /* renamed from: a */
    public byte[] mo13985a() {
        return this.f5260c;
    }

    /* renamed from: b */
    public boolean mo13986b() {
        return this.f5261d;
    }

    /* renamed from: c */
    public boolean mo13987c() {
        return this.f5263f;
    }

    /* renamed from: d */
    public C1512k mo13988d() {
        return this.f5264g;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("type : ");
        stringBuffer.append(this.f5258a);
        stringBuffer.append(", version: ");
        stringBuffer.append(this.f5259b);
        stringBuffer.append(", needReply: ");
        stringBuffer.append(this.f5261d);
        return stringBuffer.toString();
    }
}
