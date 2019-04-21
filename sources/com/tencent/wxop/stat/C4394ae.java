package com.tencent.wxop.stat;

import android.content.Context;

/* renamed from: com.tencent.wxop.stat.ae */
final class C4394ae implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f7007a;

    C4394ae(Context context) {
        this.f7007a = context;
    }

    public final void run() {
        try {
            new Thread(new C4405ap(this.f7007a, null, null), "NetworkMonitorTask").start();
        } catch (Throwable th) {
            StatServiceImpl.f6934q.mo33949e(th);
            StatServiceImpl.m7941a(this.f7007a, th);
        }
    }
}
