package com.tencent.wxop.stat;

import android.content.Context;

/* renamed from: com.tencent.wxop.stat.al */
final class C4401al implements Runnable {
    /* renamed from: a */
    final /* synthetic */ StatAccount f7022a;
    /* renamed from: b */
    final /* synthetic */ Context f7023b;
    /* renamed from: c */
    final /* synthetic */ StatSpecifyReportedInfo f7024c;

    public final void run() {
        if (this.f7022a == null || this.f7022a.getAccount().trim().length() == 0) {
            StatServiceImpl.f6934q.mo33956w("account is null or empty.");
            return;
        }
        StatConfig.setQQ(this.f7023b, this.f7022a.getAccount());
        StatServiceImpl.m7946b(this.f7023b, this.f7022a, this.f7024c);
    }
}
