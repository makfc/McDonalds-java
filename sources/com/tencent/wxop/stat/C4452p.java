package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p069a.C4381d;

/* renamed from: com.tencent.wxop.stat.p */
final class C4452p implements Runnable {
    /* renamed from: a */
    final /* synthetic */ String f7190a;
    /* renamed from: b */
    final /* synthetic */ Context f7191b;
    /* renamed from: c */
    final /* synthetic */ StatSpecifyReportedInfo f7192c;

    public final void run() {
        try {
            if (StatServiceImpl.m7943a(this.f7190a)) {
                StatServiceImpl.f6934q.error((Object) "Error message in StatService.reportError() is empty.");
            } else {
                new C4406aq(new C4381d(this.f7191b, StatServiceImpl.m7935a(this.f7191b, false, this.f7192c), this.f7190a, 0, StatConfig.getMaxReportEventLength(), null, this.f7192c)).mo33922a();
            }
        } catch (Throwable th) {
            StatServiceImpl.f6934q.mo33949e(th);
            StatServiceImpl.m7941a(this.f7191b, th);
        }
    }
}
