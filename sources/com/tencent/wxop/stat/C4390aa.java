package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p069a.C4384h;

/* renamed from: com.tencent.wxop.stat.aa */
final class C4390aa implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f6998a;
    /* renamed from: b */
    final /* synthetic */ StatSpecifyReportedInfo f6999b;
    /* renamed from: c */
    final /* synthetic */ StatAppMonitor f7000c;

    public final void run() {
        try {
            new C4406aq(new C4384h(this.f6998a, StatServiceImpl.m7935a(this.f6998a, false, this.f6999b), this.f7000c, this.f6999b)).mo33922a();
        } catch (Throwable th) {
            StatServiceImpl.f6934q.mo33949e(th);
            StatServiceImpl.m7941a(this.f6998a, th);
        }
    }
}
