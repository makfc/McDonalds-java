package com.tencent.wxop.stat;

import com.tencent.wxop.stat.common.C4433k;
import java.util.TimerTask;

/* renamed from: com.tencent.wxop.stat.e */
class C4442e extends TimerTask {
    /* renamed from: a */
    final /* synthetic */ C4441d f7169a;

    C4442e(C4441d c4441d) {
        this.f7169a = c4441d;
    }

    public void run() {
        if (StatConfig.isDebugEnable()) {
            C4433k.m8111b().mo33952i("TimerTask run");
        }
        StatServiceImpl.m7954e(this.f7169a.f7168c);
        cancel();
        this.f7169a.mo33972a();
    }
}
