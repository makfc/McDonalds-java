package com.tencent.wxop.stat.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.tencent.wxop.stat.common.e */
public class C4427e {
    /* renamed from: a */
    ExecutorService f7120a;

    public C4427e() {
        this.f7120a = null;
        this.f7120a = Executors.newSingleThreadExecutor();
    }

    /* renamed from: a */
    public void mo33966a(Runnable runnable) {
        this.f7120a.execute(runnable);
    }
}
