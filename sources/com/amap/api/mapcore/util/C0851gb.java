package com.amap.api.mapcore.util;

import com.amap.api.mapcore.util.ThreadTask.C0764a;

/* compiled from: ThreadPool */
/* renamed from: com.amap.api.mapcore.util.gb */
class C0851gb implements C0764a {
    /* renamed from: a */
    final /* synthetic */ ThreadPool f2019a;

    C0851gb(ThreadPool threadPool) {
        this.f2019a = threadPool;
    }

    /* renamed from: a */
    public void mo8934a(ThreadTask threadTask) {
    }

    /* renamed from: b */
    public void mo8935b(ThreadTask threadTask) {
        this.f2019a.m2835a(threadTask, false);
    }

    /* renamed from: c */
    public void mo8936c(ThreadTask threadTask) {
        this.f2019a.m2835a(threadTask, true);
    }
}
