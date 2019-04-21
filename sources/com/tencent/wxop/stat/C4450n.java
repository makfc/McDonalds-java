package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.C4433k;

/* renamed from: com.tencent.wxop.stat.n */
final class C4450n implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f7188a;

    public final void run() {
        if (this.f7188a == null) {
            StatServiceImpl.f6934q.error((Object) "The Context of StatService.onStop() can not be null!");
            return;
        }
        StatServiceImpl.flushDataToDB(this.f7188a);
        if (!StatServiceImpl.m7942a()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (C4433k.m8096B(this.f7188a)) {
                if (StatConfig.isDebugEnable()) {
                    StatServiceImpl.f6934q.mo33952i("onStop isBackgroundRunning flushDataToDB");
                }
                StatServiceImpl.commitEvents(this.f7188a, -1);
            }
        }
    }
}
