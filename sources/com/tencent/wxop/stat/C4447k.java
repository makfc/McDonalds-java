package com.tencent.wxop.stat;

import java.util.List;

/* renamed from: com.tencent.wxop.stat.k */
class C4447k implements Runnable {
    /* renamed from: a */
    final /* synthetic */ List f7182a;
    /* renamed from: b */
    final /* synthetic */ C4407h f7183b;
    /* renamed from: c */
    final /* synthetic */ C4445i f7184c;

    C4447k(C4445i c4445i, List list, C4407h c4407h) {
        this.f7184c = c4445i;
        this.f7182a = list;
        this.f7183b = c4407h;
    }

    public void run() {
        this.f7184c.mo33981a(this.f7182a, this.f7183b);
    }
}
