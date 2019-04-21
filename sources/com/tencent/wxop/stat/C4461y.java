package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p069a.C4380c;

/* renamed from: com.tencent.wxop.stat.y */
final class C4461y implements Runnable {
    /* renamed from: a */
    final /* synthetic */ String f7214a;
    /* renamed from: b */
    final /* synthetic */ C4380c f7215b;
    /* renamed from: c */
    final /* synthetic */ Context f7216c;

    public final void run() {
        try {
            if (StatServiceImpl.m7943a(this.f7214a)) {
                StatServiceImpl.f6934q.error((Object) "The event_id of StatService.trackCustomBeginEvent() can not be null or empty.");
                return;
            }
            if (StatConfig.isDebugEnable()) {
                StatServiceImpl.f6934q.mo33952i("add begin key:" + this.f7215b);
            }
            if (StatServiceImpl.f6922e.containsKey(this.f7215b)) {
                StatServiceImpl.f6934q.warn("Duplicate CustomEvent key: " + this.f7215b.toString() + ", trackCustomBeginKVEvent() repeated?");
            } else if (StatServiceImpl.f6922e.size() <= StatConfig.getMaxParallelTimmingEvents()) {
                StatServiceImpl.f6922e.put(this.f7215b, Long.valueOf(System.currentTimeMillis()));
            } else {
                StatServiceImpl.f6934q.error("The number of timedEvent exceeds the maximum value " + Integer.toString(StatConfig.getMaxParallelTimmingEvents()));
            }
        } catch (Throwable th) {
            StatServiceImpl.f6934q.mo33949e(th);
            StatServiceImpl.m7941a(this.f7216c, th);
        }
    }
}
