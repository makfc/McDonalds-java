package com.tencent.wxop.stat;

import android.content.Context;

/* renamed from: com.tencent.wxop.stat.w */
final class C4459w implements Runnable {
    /* renamed from: a */
    final /* synthetic */ String f7207a;
    /* renamed from: b */
    final /* synthetic */ Context f7208b;
    /* renamed from: c */
    final /* synthetic */ StatSpecifyReportedInfo f7209c;

    C4459w(String str, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f7207a = str;
        this.f7208b = context;
        this.f7209c = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            synchronized (StatServiceImpl.f6932o) {
                if (StatServiceImpl.f6932o.size() >= StatConfig.getMaxParallelTimmingEvents()) {
                    StatServiceImpl.f6934q.error("The number of page events exceeds the maximum value " + Integer.toString(StatConfig.getMaxParallelTimmingEvents()));
                    return;
                }
                StatServiceImpl.f6930m = this.f7207a;
                if (StatServiceImpl.f6932o.containsKey(StatServiceImpl.f6930m)) {
                    StatServiceImpl.f6934q.mo33948e("Duplicate PageID : " + StatServiceImpl.f6930m + ", onResume() repeated?");
                    return;
                }
                StatServiceImpl.f6932o.put(StatServiceImpl.f6930m, Long.valueOf(System.currentTimeMillis()));
                StatServiceImpl.m7935a(this.f7208b, true, this.f7209c);
            }
        } catch (Throwable th) {
            StatServiceImpl.f6934q.mo33949e(th);
            StatServiceImpl.m7941a(this.f7208b, th);
        }
    }
}
