package com.tencent.wxop.stat;

import java.util.List;

/* renamed from: com.tencent.wxop.stat.ba */
class C4418ba implements C4407h {
    /* renamed from: a */
    final /* synthetic */ List f7074a;
    /* renamed from: b */
    final /* synthetic */ boolean f7075b;
    /* renamed from: c */
    final /* synthetic */ C4411au f7076c;

    C4418ba(C4411au c4411au, List list, boolean z) {
        this.f7076c = c4411au;
        this.f7074a = list;
        this.f7075b = z;
    }

    /* renamed from: a */
    public void mo33923a() {
        StatServiceImpl.m7950c();
        this.f7076c.mo33930a(this.f7074a, this.f7075b, true);
    }

    /* renamed from: b */
    public void mo33924b() {
        StatServiceImpl.m7951d();
        this.f7076c.mo33929a(this.f7074a, 1, this.f7075b, true);
    }
}
