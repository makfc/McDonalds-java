package com.tencent.wxop.stat;

import java.util.List;

/* renamed from: com.tencent.wxop.stat.aw */
class C4413aw implements Runnable {
    /* renamed from: a */
    final /* synthetic */ List f7061a;
    /* renamed from: b */
    final /* synthetic */ boolean f7062b;
    /* renamed from: c */
    final /* synthetic */ boolean f7063c;
    /* renamed from: d */
    final /* synthetic */ C4411au f7064d;

    C4413aw(C4411au c4411au, List list, boolean z, boolean z2) {
        this.f7064d = c4411au;
        this.f7061a = list;
        this.f7062b = z;
        this.f7063c = z2;
    }

    public void run() {
        this.f7064d.m8040a(this.f7061a, this.f7062b);
        if (this.f7063c) {
            this.f7061a.clear();
        }
    }
}
