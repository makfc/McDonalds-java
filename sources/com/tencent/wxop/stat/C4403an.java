package com.tencent.wxop.stat;

import android.content.Context;

/* renamed from: com.tencent.wxop.stat.an */
final class C4403an implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f7028a;
    /* renamed from: b */
    final /* synthetic */ StatSpecifyReportedInfo f7029b;

    C4403an(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f7028a = context;
        this.f7029b = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            StatServiceImpl.m7935a(this.f7028a, false, this.f7029b);
        } catch (Throwable th) {
            StatServiceImpl.f6934q.mo33949e(th);
        }
    }
}
