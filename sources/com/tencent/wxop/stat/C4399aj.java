package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.C4433k;

/* renamed from: com.tencent.wxop.stat.aj */
final class C4399aj implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f7017a;
    /* renamed from: b */
    final /* synthetic */ StatSpecifyReportedInfo f7018b;

    C4399aj(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f7017a = context;
        this.f7018b = statSpecifyReportedInfo;
    }

    public final void run() {
        if (this.f7017a == null) {
            StatServiceImpl.f6934q.error((Object) "The Context of StatService.onResume() can not be null!");
        } else {
            StatServiceImpl.trackBeginPage(this.f7017a, C4433k.m8125h(this.f7017a), this.f7018b);
        }
    }
}
