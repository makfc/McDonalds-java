package com.tencent.wxop.stat;

/* renamed from: com.tencent.wxop.stat.ar */
class C4408ar implements C4407h {
    /* renamed from: a */
    final /* synthetic */ C4406aq f7039a;

    C4408ar(C4406aq c4406aq) {
        this.f7039a = c4406aq;
    }

    /* renamed from: a */
    public void mo33923a() {
        StatServiceImpl.m7950c();
        if (C4411au.m8043b().mo33925a() >= StatConfig.getMaxBatchReportCount()) {
            C4411au.m8043b().mo33926a(StatConfig.getMaxBatchReportCount());
        }
    }

    /* renamed from: b */
    public void mo33924b() {
        StatServiceImpl.m7951d();
    }
}
