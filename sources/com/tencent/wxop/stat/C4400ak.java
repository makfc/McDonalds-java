package com.tencent.wxop.stat;

import android.content.Context;

/* renamed from: com.tencent.wxop.stat.ak */
final class C4400ak implements Runnable {
    /* renamed from: a */
    final /* synthetic */ String f7019a;
    /* renamed from: b */
    final /* synthetic */ Context f7020b;
    /* renamed from: c */
    final /* synthetic */ StatSpecifyReportedInfo f7021c;

    public final void run() {
        if (this.f7019a == null || this.f7019a.trim().length() == 0) {
            StatServiceImpl.f6934q.mo33956w("qq num is null or empty.");
            return;
        }
        StatConfig.f6892f = this.f7019a;
        StatServiceImpl.m7946b(this.f7020b, new StatAccount(this.f7019a), this.f7021c);
    }
}
