package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p069a.C4379b;
import com.tencent.wxop.stat.p069a.C4380c;

/* renamed from: com.tencent.wxop.stat.z */
final class C4462z implements Runnable {
    /* renamed from: a */
    final /* synthetic */ String f7217a;
    /* renamed from: b */
    final /* synthetic */ C4380c f7218b;
    /* renamed from: c */
    final /* synthetic */ Context f7219c;
    /* renamed from: d */
    final /* synthetic */ StatSpecifyReportedInfo f7220d;

    public final void run() {
        try {
            if (StatServiceImpl.m7943a(this.f7217a)) {
                StatServiceImpl.f6934q.error((Object) "The event_id of StatService.trackCustomEndEvent() can not be null or empty.");
                return;
            }
            Long l = (Long) StatServiceImpl.f6922e.remove(this.f7218b);
            if (l != null) {
                C4379b c4379b = new C4379b(this.f7219c, StatServiceImpl.m7935a(this.f7219c, false, this.f7220d), this.f7218b.f6958a, this.f7220d);
                c4379b.mo33892b().f6960c = this.f7218b.f6960c;
                l = Long.valueOf((System.currentTimeMillis() - l.longValue()) / 1000);
                c4379b.mo33891a(Long.valueOf(l.longValue() <= 0 ? 1 : l.longValue()).longValue());
                new C4406aq(c4379b).mo33922a();
                return;
            }
            StatServiceImpl.f6934q.warn("No start time found for custom event: " + this.f7218b.toString() + ", lost trackCustomBeginKVEvent()?");
        } catch (Throwable th) {
            StatServiceImpl.f6934q.mo33949e(th);
            StatServiceImpl.m7941a(this.f7219c, th);
        }
    }
}
