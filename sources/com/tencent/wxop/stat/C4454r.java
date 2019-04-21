package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p069a.C4381d;

/* renamed from: com.tencent.wxop.stat.r */
final class C4454r implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Throwable f7195a;
    /* renamed from: b */
    final /* synthetic */ Context f7196b;
    /* renamed from: c */
    final /* synthetic */ StatSpecifyReportedInfo f7197c;

    public final void run() {
        if (this.f7195a == null) {
            StatServiceImpl.f6934q.error((Object) "The Throwable error message of StatService.reportException() can not be null!");
        } else {
            new C4406aq(new C4381d(this.f7196b, StatServiceImpl.m7935a(this.f7196b, false, this.f7197c), 1, this.f7195a, this.f7197c)).mo33922a();
        }
    }
}
