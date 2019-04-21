package com.baidu.android.pushservice.p026j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.baidu.android.pushservice.j.a */
public class C1459a extends ThreadPoolExecutor {

    /* renamed from: com.baidu.android.pushservice.j.a$a */
    protected class C1458a<V> extends FutureTask<V> implements Comparable<C1458a<V>> {
        /* renamed from: b */
        private Object f5151b;

        public C1458a(Runnable runnable, V v) {
            super(runnable, v);
            this.f5151b = runnable;
        }

        public C1458a(Callable<V> callable) {
            super(callable);
            this.f5151b = callable;
        }

        /* renamed from: a */
        public int compareTo(C1458a<V> c1458a) {
            return this == c1458a ? 0 : c1458a == null ? -1 : (this.f5151b == null || c1458a.f5151b == null || !(this.f5151b instanceof C1281c) || !(c1458a.f5151b instanceof C1281c)) ? 0 : ((C1281c) c1458a.f5151b).mo13490d() - ((C1281c) this.f5151b).mo13490d();
        }
    }

    public C1459a(int i, int i2, long j, TimeUnit timeUnit, C1460b<Runnable> c1460b) {
        super(i, i2, j, timeUnit, c1460b);
    }

    public synchronized void execute(Runnable runnable) {
        if (getQueue().size() >= 19) {
            if (getPoolSize() >= getMaximumPoolSize()) {
                getQueue().clear();
            } else {
                Runnable runnable2 = (Runnable) getQueue().poll();
                getQueue().offer(runnable);
                runnable = runnable2;
            }
        }
        super.execute(runnable);
    }

    /* Access modifiers changed, original: protected */
    public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new C1458a(runnable, t);
    }

    /* Access modifiers changed, original: protected */
    public <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new C1458a(callable);
    }
}
