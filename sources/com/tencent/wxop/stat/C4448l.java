package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.C4433k;

/* renamed from: com.tencent.wxop.stat.l */
final class C4448l implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f7185a;

    C4448l(Context context) {
        this.f7185a = context;
    }

    public final void run() {
        C4389a.m7995a(StatServiceImpl.f6937t).mo33906h();
        C4433k.m8100a(this.f7185a, true);
        C4411au.m8029a(this.f7185a);
        C4445i.m8180b(this.f7185a);
        StatServiceImpl.f6935r = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new C4404ao());
        if (StatConfig.getStatSendStrategy() == StatReportStrategy.APP_LAUNCH) {
            StatServiceImpl.commitEvents(this.f7185a, -1);
        }
        if (StatConfig.isDebugEnable()) {
            StatServiceImpl.f6934q.mo33946d("Init MTA StatService success.");
        }
    }
}
