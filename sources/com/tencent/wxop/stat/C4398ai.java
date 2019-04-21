package com.tencent.wxop.stat;

import android.content.Context;

/* renamed from: com.tencent.wxop.stat.ai */
final class C4398ai implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f7015a;
    /* renamed from: b */
    final /* synthetic */ StatSpecifyReportedInfo f7016b;

    public final void run() {
        try {
            StatServiceImpl.stopSession();
            StatServiceImpl.m7935a(this.f7015a, true, this.f7016b);
        } catch (Throwable th) {
            StatServiceImpl.f6934q.mo33949e(th);
            StatServiceImpl.m7941a(this.f7015a, th);
        }
    }
}
