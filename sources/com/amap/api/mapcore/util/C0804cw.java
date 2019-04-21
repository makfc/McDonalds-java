package com.amap.api.mapcore.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: AsyncTask */
/* renamed from: com.amap.api.mapcore.util.cw */
class C0804cw implements ThreadFactory {
    /* renamed from: a */
    private final AtomicInteger f1690a = new AtomicInteger(1);

    C0804cw() {
    }

    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, "AsyncTask #" + this.f1690a.getAndIncrement());
    }
}
