package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p069a.C4383g;

/* renamed from: com.tencent.wxop.stat.am */
final class C4402am implements Runnable {
    /* renamed from: a */
    final /* synthetic */ StatGameUser f7025a;
    /* renamed from: b */
    final /* synthetic */ Context f7026b;
    /* renamed from: c */
    final /* synthetic */ StatSpecifyReportedInfo f7027c;

    public final void run() {
        if (this.f7025a == null) {
            StatServiceImpl.f6934q.error((Object) "The gameUser of StatService.reportGameUser() can not be null!");
        } else if (this.f7025a.getAccount() == null || this.f7025a.getAccount().length() == 0) {
            StatServiceImpl.f6934q.error((Object) "The account of gameUser on StatService.reportGameUser() can not be null or empty!");
        } else {
            try {
                new C4406aq(new C4383g(this.f7026b, StatServiceImpl.m7935a(this.f7026b, false, this.f7027c), this.f7025a, this.f7027c)).mo33922a();
            } catch (Throwable th) {
                StatServiceImpl.f6934q.mo33949e(th);
                StatServiceImpl.m7941a(this.f7026b, th);
            }
        }
    }
}
