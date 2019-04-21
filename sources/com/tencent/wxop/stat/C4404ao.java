package com.tencent.wxop.stat;

import com.tencent.wxop.stat.p069a.C4381d;
import java.lang.Thread.UncaughtExceptionHandler;

/* renamed from: com.tencent.wxop.stat.ao */
class C4404ao implements UncaughtExceptionHandler {
    C4404ao() {
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (StatConfig.isEnableStatService() && StatServiceImpl.f6937t != null) {
            if (StatConfig.isAutoExceptionCaught()) {
                C4411au.m8029a(StatServiceImpl.f6937t).mo33927a(new C4381d(StatServiceImpl.f6937t, StatServiceImpl.m7935a(StatServiceImpl.f6937t, false, null), 2, th, thread, null), null, false, true);
                StatServiceImpl.f6934q.debug("MTA has caught the following uncaught exception:");
                StatServiceImpl.f6934q.error(th);
            }
            StatServiceImpl.flushDataToDB(StatServiceImpl.f6937t);
            if (StatServiceImpl.f6935r != null) {
                StatServiceImpl.f6934q.mo33946d("Call the original uncaught exception handler.");
                if (!(StatServiceImpl.f6935r instanceof C4404ao)) {
                    StatServiceImpl.f6935r.uncaughtException(thread, th);
                }
            }
        }
    }
}
