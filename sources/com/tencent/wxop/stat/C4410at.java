package com.tencent.wxop.stat;

/* renamed from: com.tencent.wxop.stat.at */
class C4410at implements C4407h {
    /* renamed from: a */
    final /* synthetic */ C4406aq f7041a;

    C4410at(C4406aq c4406aq) {
        this.f7041a = c4406aq;
    }

    /* renamed from: a */
    public void mo33923a() {
        StatServiceImpl.m7950c();
        if (C4411au.m8043b().f7045a > 0) {
            StatServiceImpl.commitEvents(this.f7041a.f7037d, -1);
        }
    }

    /* renamed from: b */
    public void mo33924b() {
        C4411au.m8043b().mo33927a(this.f7041a.f7034a, null, this.f7041a.f7036c, true);
        StatServiceImpl.m7951d();
    }
}
