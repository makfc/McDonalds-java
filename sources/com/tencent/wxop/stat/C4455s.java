package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p069a.C4379b;
import com.tencent.wxop.stat.p069a.C4380c;

/* renamed from: com.tencent.wxop.stat.s */
final class C4455s implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f7198a;
    /* renamed from: b */
    final /* synthetic */ StatSpecifyReportedInfo f7199b;
    /* renamed from: c */
    final /* synthetic */ C4380c f7200c;

    public final void run() {
        try {
            C4379b c4379b = new C4379b(this.f7198a, StatServiceImpl.m7935a(this.f7198a, false, this.f7199b), this.f7200c.f6958a, this.f7199b);
            c4379b.mo33892b().f6959b = this.f7200c.f6959b;
            new C4406aq(c4379b).mo33922a();
        } catch (Throwable th) {
            StatServiceImpl.f6934q.mo33949e(th);
            StatServiceImpl.m7941a(this.f7198a, th);
        }
    }
}
