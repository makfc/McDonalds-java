package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.C4433k;

/* renamed from: com.tencent.wxop.stat.m */
final class C4449m implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f7186a;
    /* renamed from: b */
    final /* synthetic */ StatSpecifyReportedInfo f7187b;

    C4449m(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f7186a = context;
        this.f7187b = statSpecifyReportedInfo;
    }

    public final void run() {
        if (this.f7186a == null) {
            StatServiceImpl.f6934q.error((Object) "The Context of StatService.onPause() can not be null!");
        } else {
            StatServiceImpl.trackEndPage(this.f7186a, C4433k.m8125h(this.f7186a), this.f7187b);
        }
    }
}
