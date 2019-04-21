package com.aps;

/* renamed from: com.aps.k */
public class Fence {
    /* renamed from: a */
    public double f4482a = 0.0d;
    /* renamed from: b */
    public double f4483b = 0.0d;
    /* renamed from: c */
    public float f4484c = 0.0f;
    /* renamed from: d */
    int f4485d = -1;
    /* renamed from: e */
    private long f4486e = -1;

    /* renamed from: a */
    public long mo13274a() {
        return this.f4486e;
    }

    /* renamed from: a */
    public void mo13275a(long j) {
        if (j >= 0) {
            this.f4486e = C1269v.m5737b() + j;
        } else {
            this.f4486e = j;
        }
    }

    /* renamed from: b */
    public String mo13276b() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f4482a).append("#").append(this.f4483b).append("#").append(this.f4484c);
        return stringBuilder.toString();
    }
}
