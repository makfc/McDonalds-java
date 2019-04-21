package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p069a.C4379b;
import com.tencent.wxop.stat.p069a.C4380c;

/* renamed from: com.tencent.wxop.stat.x */
final class C4460x implements Runnable {
    /* renamed from: a */
    final /* synthetic */ String f7210a;
    /* renamed from: b */
    final /* synthetic */ C4380c f7211b;
    /* renamed from: c */
    final /* synthetic */ Context f7212c;
    /* renamed from: d */
    final /* synthetic */ StatSpecifyReportedInfo f7213d;

    public final void run() {
        try {
            if (StatServiceImpl.m7943a(this.f7210a)) {
                StatServiceImpl.f6934q.error((Object) "The event_id of StatService.trackCustomEndEvent() can not be null or empty.");
                return;
            }
            Long l = (Long) StatServiceImpl.f6922e.remove(this.f7211b);
            if (l != null) {
                C4379b c4379b = new C4379b(this.f7212c, StatServiceImpl.m7935a(this.f7212c, false, this.f7213d), this.f7211b.f6958a, this.f7213d);
                c4379b.mo33892b().f6959b = this.f7211b.f6959b;
                l = Long.valueOf((System.currentTimeMillis() - l.longValue()) / 1000);
                c4379b.mo33891a(Long.valueOf(l.longValue() == 0 ? 1 : l.longValue()).longValue());
                new C4406aq(c4379b).mo33922a();
                return;
            }
            StatServiceImpl.f6934q.error("No start time found for custom event: " + this.f7211b.toString() + ", lost trackCustomBeginEvent()?");
        } catch (Throwable th) {
            StatServiceImpl.f6934q.mo33949e(th);
            StatServiceImpl.m7941a(this.f7212c, th);
        }
    }
}
