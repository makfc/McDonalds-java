package p041io.fabric.sdk.android.services.concurrency.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/* renamed from: io.fabric.sdk.android.services.concurrency.internal.AbstractFuture */
public abstract class AbstractFuture<V> implements Future<V> {
    private final Sync<V> sync = new Sync();

    /* renamed from: io.fabric.sdk.android.services.concurrency.internal.AbstractFuture$Sync */
    static final class Sync<V> extends AbstractQueuedSynchronizer {
        private Throwable exception;
        private V value;

        Sync() {
        }

        /* Access modifiers changed, original: protected */
        public int tryAcquireShared(int ignored) {
            if (isDone()) {
                return 1;
            }
            return -1;
        }

        /* Access modifiers changed, original: protected */
        public boolean tryReleaseShared(int finalState) {
            setState(finalState);
            return true;
        }

        /* Access modifiers changed, original: 0000 */
        public V get(long nanos) throws TimeoutException, CancellationException, ExecutionException, InterruptedException {
            if (tryAcquireSharedNanos(-1, nanos)) {
                return getValue();
            }
            throw new TimeoutException("Timeout waiting for task.");
        }

        /* Access modifiers changed, original: 0000 */
        public V get() throws CancellationException, ExecutionException, InterruptedException {
            acquireSharedInterruptibly(-1);
            return getValue();
        }

        private V getValue() throws CancellationException, ExecutionException {
            int state = getState();
            switch (state) {
                case 2:
                    if (this.exception == null) {
                        return this.value;
                    }
                    throw new ExecutionException(this.exception);
                case 4:
                case 8:
                    throw AbstractFuture.cancellationExceptionWithCause("Task was cancelled.", this.exception);
                default:
                    throw new IllegalStateException("Error, synchronizer in invalid state: " + state);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public boolean isDone() {
            return (getState() & 14) != 0;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean isCancelled() {
            return (getState() & 12) != 0;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean set(V v) {
            return complete(v, null, 2);
        }

        /* Access modifiers changed, original: 0000 */
        public boolean setException(Throwable t) {
            return complete(null, t, 2);
        }

        /* Access modifiers changed, original: 0000 */
        public boolean cancel(boolean interrupt) {
            return complete(null, null, interrupt ? 8 : 4);
        }

        private boolean complete(V v, Throwable t, int finalState) {
            boolean doCompletion = compareAndSetState(0, 1);
            if (doCompletion) {
                this.value = v;
                if ((finalState & 12) != 0) {
                    t = new CancellationException("Future.cancel() was called.");
                }
                this.exception = t;
                releaseShared(finalState);
            } else if (getState() == 1) {
                acquireShared(-1);
            }
            return doCompletion;
        }
    }

    protected AbstractFuture() {
    }

    static final CancellationException cancellationExceptionWithCause(String message, Throwable cause) {
        CancellationException exception = new CancellationException(message);
        exception.initCause(cause);
        return exception;
    }

    public V get(long timeout, TimeUnit unit) throws InterruptedException, TimeoutException, ExecutionException {
        return this.sync.get(unit.toNanos(timeout));
    }

    public V get() throws InterruptedException, ExecutionException {
        return this.sync.get();
    }

    public boolean isDone() {
        return this.sync.isDone();
    }

    public boolean isCancelled() {
        return this.sync.isCancelled();
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        if (!this.sync.cancel(mayInterruptIfRunning)) {
            return false;
        }
        if (mayInterruptIfRunning) {
            interruptTask();
        }
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void interruptTask() {
    }

    /* Access modifiers changed, original: protected */
    public boolean set(V value) {
        return this.sync.set(value);
    }

    /* Access modifiers changed, original: protected */
    public boolean setException(Throwable throwable) {
        if (throwable != null) {
            return this.sync.setException(throwable);
        }
        throw new NullPointerException();
    }
}
