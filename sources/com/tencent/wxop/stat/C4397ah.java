package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p069a.C4387k;

/* renamed from: com.tencent.wxop.stat.ah */
final class C4397ah implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f7012a;
    /* renamed from: b */
    final /* synthetic */ String f7013b;
    /* renamed from: c */
    final /* synthetic */ StatSpecifyReportedInfo f7014c;

    C4397ah(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f7012a = context;
        this.f7013b = str;
        this.f7014c = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            Long l;
            StatServiceImpl.flushDataToDB(this.f7012a);
            synchronized (StatServiceImpl.f6932o) {
                l = (Long) StatServiceImpl.f6932o.remove(this.f7013b);
            }
            if (l != null) {
                Long valueOf = Long.valueOf((System.currentTimeMillis() - l.longValue()) / 1000);
                if (valueOf.longValue() <= 0) {
                    valueOf = Long.valueOf(1);
                }
                String j = StatServiceImpl.f6931n;
                if (j != null && j.equals(this.f7013b)) {
                    j = "-";
                }
                C4387k c4387k = new C4387k(this.f7012a, j, this.f7013b, StatServiceImpl.m7935a(this.f7012a, false, this.f7014c), valueOf, this.f7014c);
                if (!this.f7013b.equals(StatServiceImpl.f6930m)) {
                    StatServiceImpl.f6934q.warn("Invalid invocation since previous onResume on diff page.");
                }
                new C4406aq(c4387k).mo33922a();
                StatServiceImpl.f6931n = this.f7013b;
                return;
            }
            StatServiceImpl.f6934q.mo33948e("Starttime for PageID:" + this.f7013b + " not found, lost onResume()?");
        } catch (Throwable th) {
            StatServiceImpl.f6934q.mo33949e(th);
            StatServiceImpl.m7941a(this.f7012a, th);
        }
    }
}
