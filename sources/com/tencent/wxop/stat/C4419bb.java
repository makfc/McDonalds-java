package com.tencent.wxop.stat;

/* renamed from: com.tencent.wxop.stat.bb */
class C4419bb implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f7077a;
    /* renamed from: b */
    final /* synthetic */ C4411au f7078b;

    C4419bb(C4411au c4411au, int i) {
        this.f7078b = c4411au;
        this.f7077a = i;
    }

    public void run() {
        this.f7078b.m8044b(this.f7077a, true);
        this.f7078b.m8044b(this.f7077a, false);
    }
}
