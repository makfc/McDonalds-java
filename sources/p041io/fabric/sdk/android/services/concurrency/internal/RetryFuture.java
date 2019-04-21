package p041io.fabric.sdk.android.services.concurrency.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: io.fabric.sdk.android.services.concurrency.internal.RetryFuture */
class RetryFuture<T> extends AbstractFuture<T> implements Runnable {
    private final RetryThreadPoolExecutor executor;
    RetryState retryState;
    private final AtomicReference<Thread> runner;
    private final Callable<T> task;

    public void run() {
        if (!isDone() && this.runner.compareAndSet(null, Thread.currentThread())) {
            try {
                set(this.task.call());
                this.runner.getAndSet(null);
            } catch (Throwable th) {
                this.runner.getAndSet(null);
                throw th;
            }
        }
    }

    private RetryPolicy getRetryPolicy() {
        return this.retryState.getRetryPolicy();
    }

    private Backoff getBackoff() {
        return this.retryState.getBackoff();
    }

    private int getRetryCount() {
        return this.retryState.getRetryCount();
    }

    /* Access modifiers changed, original: protected */
    public void interruptTask() {
        Thread thread = (Thread) this.runner.getAndSet(null);
        if (thread != null) {
            thread.interrupt();
        }
    }
}
