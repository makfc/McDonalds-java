package com.tencent.wxop.stat;

import java.util.List;

/* renamed from: com.tencent.wxop.stat.av */
class C4412av implements Runnable {
    /* renamed from: a */
    final /* synthetic */ List f7056a;
    /* renamed from: b */
    final /* synthetic */ int f7057b;
    /* renamed from: c */
    final /* synthetic */ boolean f7058c;
    /* renamed from: d */
    final /* synthetic */ boolean f7059d;
    /* renamed from: e */
    final /* synthetic */ C4411au f7060e;

    C4412av(C4411au c4411au, List list, int i, boolean z, boolean z2) {
        this.f7060e = c4411au;
        this.f7056a = list;
        this.f7057b = i;
        this.f7058c = z;
        this.f7059d = z2;
    }

    public void run() {
        this.f7060e.m8039a(this.f7056a, this.f7057b, this.f7058c);
        if (this.f7059d) {
            this.f7056a.clear();
        }
    }
}
