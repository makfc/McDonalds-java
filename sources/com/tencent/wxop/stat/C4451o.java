package com.tencent.wxop.stat;

import android.content.Context;

/* renamed from: com.tencent.wxop.stat.o */
final class C4451o implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f7189a;

    public final void run() {
        StatServiceImpl.flushDataToDB(this.f7189a);
    }
}
