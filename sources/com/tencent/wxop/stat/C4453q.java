package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p069a.C4381d;
import com.tencent.wxop.stat.p069a.C4385i;

/* renamed from: com.tencent.wxop.stat.q */
final class C4453q implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f7193a;
    /* renamed from: b */
    final /* synthetic */ Throwable f7194b;

    C4453q(Context context, Throwable th) {
        this.f7193a = context;
        this.f7194b = th;
    }

    public final void run() {
        try {
            if (StatConfig.isEnableStatService()) {
                new C4406aq(new C4381d(this.f7193a, StatServiceImpl.m7935a(this.f7193a, false, null), 99, this.f7194b, C4385i.f6980a)).mo33922a();
            }
        } catch (Throwable th) {
            StatServiceImpl.f6934q.mo33948e("reportSdkSelfException error: " + th);
        }
    }
}
