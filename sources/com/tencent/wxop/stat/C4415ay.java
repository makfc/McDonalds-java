package com.tencent.wxop.stat;

import com.tencent.wxop.stat.p069a.C4377e;

/* renamed from: com.tencent.wxop.stat.ay */
class C4415ay implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C4377e f7066a;
    /* renamed from: b */
    final /* synthetic */ C4407h f7067b;
    /* renamed from: c */
    final /* synthetic */ boolean f7068c;
    /* renamed from: d */
    final /* synthetic */ boolean f7069d;
    /* renamed from: e */
    final /* synthetic */ C4411au f7070e;

    C4415ay(C4411au c4411au, C4377e c4377e, C4407h c4407h, boolean z, boolean z2) {
        this.f7070e = c4411au;
        this.f7066a = c4377e;
        this.f7067b = c4407h;
        this.f7068c = z;
        this.f7069d = z2;
    }

    public void run() {
        this.f7070e.m8045b(this.f7066a, this.f7067b, this.f7068c, this.f7069d);
    }
}
