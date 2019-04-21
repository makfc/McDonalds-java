package com.tencent.wxop.stat;

import android.content.Context;

/* renamed from: com.tencent.wxop.stat.ad */
final class C4393ad implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f7005a;
    /* renamed from: b */
    final /* synthetic */ int f7006b;

    C4393ad(Context context, int i) {
        this.f7005a = context;
        this.f7006b = i;
    }

    public final void run() {
        try {
            StatServiceImpl.flushDataToDB(this.f7005a);
            C4411au.m8029a(this.f7005a).mo33926a(this.f7006b);
        } catch (Throwable th) {
            StatServiceImpl.f6934q.mo33949e(th);
            StatServiceImpl.m7941a(this.f7005a, th);
        }
    }
}
