package com.tencent.wxop.stat;

import android.content.Context;
import java.util.Map;

/* renamed from: com.tencent.wxop.stat.af */
final class C4395af implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f7008a;
    /* renamed from: b */
    final /* synthetic */ Map f7009b;
    /* renamed from: c */
    final /* synthetic */ StatSpecifyReportedInfo f7010c;

    public final void run() {
        try {
            new Thread(new C4405ap(this.f7008a, this.f7009b, this.f7010c), "NetworkMonitorTask").start();
        } catch (Throwable th) {
            StatServiceImpl.f6934q.mo33949e(th);
            StatServiceImpl.m7941a(this.f7008a, th);
        }
    }
}
