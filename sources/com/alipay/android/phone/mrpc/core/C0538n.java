package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.alipay.android.phone.mrpc.core.n */
final class C0538n implements ThreadFactory {
    /* renamed from: a */
    private final AtomicInteger f364a = new AtomicInteger(1);

    C0538n() {
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "com.alipay.mobile.common.transport.http.HttpManager.HttpWorker #" + this.f364a.getAndIncrement());
        thread.setPriority(4);
        return thread;
    }
}
