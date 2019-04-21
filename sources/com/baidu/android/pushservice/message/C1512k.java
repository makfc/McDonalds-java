package com.baidu.android.pushservice.message;

import java.io.Serializable;

/* renamed from: com.baidu.android.pushservice.message.k */
public class C1512k implements Serializable {
    /* renamed from: a */
    private String f5288a;
    /* renamed from: b */
    private long f5289b;
    /* renamed from: c */
    private int f5290c;
    /* renamed from: d */
    private byte[] f5291d;
    /* renamed from: e */
    private long f5292e;
    /* renamed from: f */
    private long f5293f;
    /* renamed from: g */
    private long f5294g;
    /* renamed from: h */
    private boolean f5295h = false;

    /* renamed from: a */
    public void mo13995a(int i) {
        this.f5290c = i;
    }

    /* renamed from: a */
    public void mo13996a(long j) {
        this.f5292e = j;
    }

    /* renamed from: a */
    public void mo13997a(String str) {
        this.f5288a = str;
    }

    /* renamed from: a */
    public void mo13998a(boolean z) {
        this.f5295h = z;
    }

    /* renamed from: a */
    public void mo13999a(byte[] bArr) {
        this.f5291d = bArr;
    }

    /* renamed from: a */
    public boolean mo14000a() {
        return this.f5295h;
    }

    /* renamed from: b */
    public long mo14001b() {
        return this.f5292e;
    }

    /* renamed from: b */
    public void mo14002b(long j) {
        this.f5293f = j;
    }

    /* renamed from: c */
    public long mo14003c() {
        return this.f5293f;
    }

    /* renamed from: c */
    public void mo14004c(long j) {
        this.f5294g = j;
    }

    /* renamed from: d */
    public long mo14005d() {
        return this.f5294g;
    }

    /* renamed from: d */
    public void mo14006d(long j) {
        this.f5289b = j;
    }

    /* renamed from: e */
    public String mo14007e() {
        return this.f5288a;
    }

    /* renamed from: f */
    public long mo14008f() {
        return this.f5289b;
    }

    /* renamed from: g */
    public String mo14009g() {
        return String.valueOf(this.f5289b);
    }

    /* renamed from: h */
    public int mo14010h() {
        return this.f5290c;
    }

    /* renamed from: i */
    public byte[] mo14011i() {
        return this.f5291d;
    }

    public String toString() {
        return "type:" + this.f5290c + " appid:" + this.f5288a + " msgId:" + this.f5289b + " isAlarm:  " + this.f5295h;
    }
}
