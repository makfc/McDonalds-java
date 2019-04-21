package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p069a.C4379b;
import com.tencent.wxop.stat.p069a.C4380c;

/* renamed from: com.tencent.wxop.stat.ac */
final class C4392ac implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f7001a;
    /* renamed from: b */
    final /* synthetic */ StatSpecifyReportedInfo f7002b;
    /* renamed from: c */
    final /* synthetic */ C4380c f7003c;
    /* renamed from: d */
    final /* synthetic */ int f7004d;

    public final void run() {
        try {
            C4379b c4379b = new C4379b(this.f7001a, StatServiceImpl.m7935a(this.f7001a, false, this.f7002b), this.f7003c.f6958a, this.f7002b);
            c4379b.mo33892b().f6960c = this.f7003c.f6960c;
            Long valueOf = Long.valueOf((long) this.f7004d);
            c4379b.mo33891a(Long.valueOf(valueOf.longValue() <= 0 ? 1 : valueOf.longValue()).longValue());
            new C4406aq(c4379b).mo33922a();
        } catch (Throwable th) {
            StatServiceImpl.f6934q.mo33949e(th);
            StatServiceImpl.m7941a(this.f7001a, th);
        }
    }
}
