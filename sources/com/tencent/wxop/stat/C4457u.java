package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p069a.C4379b;
import com.tencent.wxop.stat.p069a.C4380c;

/* renamed from: com.tencent.wxop.stat.u */
final class C4457u implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f7201a;
    /* renamed from: b */
    final /* synthetic */ StatSpecifyReportedInfo f7202b;
    /* renamed from: c */
    final /* synthetic */ C4380c f7203c;

    C4457u(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo, C4380c c4380c) {
        this.f7201a = context;
        this.f7202b = statSpecifyReportedInfo;
        this.f7203c = c4380c;
    }

    public final void run() {
        try {
            C4379b c4379b = new C4379b(this.f7201a, StatServiceImpl.m7935a(this.f7201a, false, this.f7202b), this.f7203c.f6958a, this.f7202b);
            c4379b.mo33892b().f6960c = this.f7203c.f6960c;
            new C4406aq(c4379b).mo33922a();
        } catch (Throwable th) {
            StatServiceImpl.f6934q.mo33949e(th);
            StatServiceImpl.m7941a(this.f7201a, th);
        }
    }
}
