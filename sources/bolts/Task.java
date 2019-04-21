package bolts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Task<TResult> {
    public static final ExecutorService BACKGROUND_EXECUTOR = BoltsExecutors.background();
    private static final Executor IMMEDIATE_EXECUTOR = BoltsExecutors.immediate();
    public static final Executor UI_THREAD_EXECUTOR = AndroidExecutors.uiThread();
    private boolean cancelled;
    private boolean complete;
    private List<Continuation<TResult, Void>> continuations = new ArrayList();
    private Exception error;
    private final Object lock = new Object();
    private TResult result;

    /* renamed from: bolts.Task$1 */
    class C04411 implements Continuation<TResult, Task<Void>> {
        public Task<Void> then(Task<TResult> task) throws Exception {
            if (task.isCancelled()) {
                return Task.cancelled();
            }
            if (task.isFaulted()) {
                return Task.forError(task.getError());
            }
            return Task.forResult(null);
        }
    }

    /* renamed from: bolts.Task$2 */
    static class C04422 implements Runnable {
        final /* synthetic */ Callable val$callable;
        final /* synthetic */ TaskCompletionSource val$tcs;

        public void run() {
            try {
                this.val$tcs.setResult(this.val$callable.call());
            } catch (Exception e) {
                this.val$tcs.setError(e);
            }
        }
    }

    /* renamed from: bolts.Task$3 */
    static class C04433 implements Continuation<Object, Void> {
        final /* synthetic */ TaskCompletionSource val$allFinished;
        final /* synthetic */ ArrayList val$causes;
        final /* synthetic */ AtomicInteger val$count;
        final /* synthetic */ Object val$errorLock;
        final /* synthetic */ AtomicBoolean val$isCancelled;

        public Void then(Task<Object> task) {
            if (task.isFaulted()) {
                synchronized (this.val$errorLock) {
                    this.val$causes.add(task.getError());
                }
            }
            if (task.isCancelled()) {
                this.val$isCancelled.set(true);
            }
            if (this.val$count.decrementAndGet() == 0) {
                if (this.val$causes.size() != 0) {
                    if (this.val$causes.size() == 1) {
                        this.val$allFinished.setError((Exception) this.val$causes.get(0));
                    } else {
                        this.val$allFinished.setError(new AggregateException(String.format("There were %d exceptions.", new Object[]{Integer.valueOf(this.val$causes.size())}), (Throwable[]) this.val$causes.toArray(new Throwable[this.val$causes.size()])));
                    }
                } else if (this.val$isCancelled.get()) {
                    this.val$allFinished.setCancelled();
                } else {
                    this.val$allFinished.setResult(null);
                }
            }
            return null;
        }
    }

    /* renamed from: bolts.Task$4 */
    class C04444 implements Continuation<Void, Task<Void>> {
        final /* synthetic */ Continuation val$continuation;
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ Callable val$predicate;
        final /* synthetic */ Capture val$predicateContinuation;

        public Task<Void> then(Task<Void> task) throws Exception {
            if (((Boolean) this.val$predicate.call()).booleanValue()) {
                return Task.forResult(null).onSuccessTask(this.val$continuation, this.val$executor).onSuccessTask((Continuation) this.val$predicateContinuation.get(), this.val$executor);
            }
            return Task.forResult(null);
        }
    }

    public class TaskCompletionSource {
        /* synthetic */ TaskCompletionSource(Task x0, C04411 x1) {
            this();
        }

        private TaskCompletionSource() {
        }

        public Task<TResult> getTask() {
            return Task.this;
        }

        public boolean trySetCancelled() {
            boolean z = true;
            synchronized (Task.this.lock) {
                if (Task.this.complete) {
                    z = false;
                } else {
                    Task.this.complete = true;
                    Task.this.cancelled = true;
                    Task.this.lock.notifyAll();
                    Task.this.runContinuations();
                }
            }
            return z;
        }

        public boolean trySetResult(TResult result) {
            boolean z = true;
            synchronized (Task.this.lock) {
                if (Task.this.complete) {
                    z = false;
                } else {
                    Task.this.complete = true;
                    Task.this.result = result;
                    Task.this.lock.notifyAll();
                    Task.this.runContinuations();
                }
            }
            return z;
        }

        public boolean trySetError(Exception error) {
            boolean z = true;
            synchronized (Task.this.lock) {
                if (Task.this.complete) {
                    z = false;
                } else {
                    Task.this.complete = true;
                    Task.this.error = error;
                    Task.this.lock.notifyAll();
                    Task.this.runContinuations();
                }
            }
            return z;
        }

        public void setCancelled() {
            if (!trySetCancelled()) {
                throw new IllegalStateException("Cannot cancel a completed task.");
            }
        }

        public void setResult(TResult result) {
            if (!trySetResult(result)) {
                throw new IllegalStateException("Cannot set the result of a completed task.");
            }
        }

        public void setError(Exception error) {
            if (!trySetError(error)) {
                throw new IllegalStateException("Cannot set the error on a completed task.");
            }
        }
    }

    private Task() {
    }

    public static <TResult> TaskCompletionSource create() {
        Task<TResult> task = new Task();
        task.getClass();
        return new TaskCompletionSource(task, null);
    }

    public boolean isCompleted() {
        boolean z;
        synchronized (this.lock) {
            z = this.complete;
        }
        return z;
    }

    public boolean isCancelled() {
        boolean z;
        synchronized (this.lock) {
            z = this.cancelled;
        }
        return z;
    }

    public boolean isFaulted() {
        boolean z;
        synchronized (this.lock) {
            z = this.error != null;
        }
        return z;
    }

    public TResult getResult() {
        Object obj;
        synchronized (this.lock) {
            obj = this.result;
        }
        return obj;
    }

    public Exception getError() {
        Exception exception;
        synchronized (this.lock) {
            exception = this.error;
        }
        return exception;
    }

    public static <TResult> Task<TResult> forResult(TResult value) {
        TaskCompletionSource tcs = create();
        tcs.setResult(value);
        return tcs.getTask();
    }

    public static <TResult> Task<TResult> forError(Exception error) {
        TaskCompletionSource tcs = create();
        tcs.setError(error);
        return tcs.getTask();
    }

    public static <TResult> Task<TResult> cancelled() {
        TaskCompletionSource tcs = create();
        tcs.setCancelled();
        return tcs.getTask();
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(final Continuation<TResult, TContinuationResult> continuation, final Executor executor) {
        boolean completed;
        final TaskCompletionSource tcs = create();
        synchronized (this.lock) {
            completed = isCompleted();
            if (!completed) {
                this.continuations.add(new Continuation<TResult, Void>() {
                    public Void then(Task<TResult> task) {
                        Task.completeImmediately(tcs, continuation, task, executor);
                        return null;
                    }
                });
            }
        }
        if (completed) {
            completeImmediately(tcs, continuation, this, executor);
        }
        return tcs.getTask();
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation) {
        return continueWith(continuation, IMMEDIATE_EXECUTOR);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(final Continuation<TResult, Task<TContinuationResult>> continuation, final Executor executor) {
        boolean completed;
        final TaskCompletionSource tcs = create();
        synchronized (this.lock) {
            completed = isCompleted();
            if (!completed) {
                this.continuations.add(new Continuation<TResult, Void>() {
                    public Void then(Task<TResult> task) {
                        Task.completeAfterTask(tcs, continuation, task, executor);
                        return null;
                    }
                });
            }
        }
        if (completed) {
            completeAfterTask(tcs, continuation, this, executor);
        }
        return tcs.getTask();
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return continueWithTask(continuation, IMMEDIATE_EXECUTOR);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(final Continuation<TResult, TContinuationResult> continuation, Executor executor) {
        return continueWithTask(new Continuation<TResult, Task<TContinuationResult>>() {
            public Task<TContinuationResult> then(Task<TResult> task) {
                if (task.isFaulted()) {
                    return Task.forError(task.getError());
                }
                if (task.isCancelled()) {
                    return Task.cancelled();
                }
                return task.continueWith(continuation);
            }
        }, executor);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(Continuation<TResult, TContinuationResult> continuation) {
        return onSuccess(continuation, IMMEDIATE_EXECUTOR);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(final Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor) {
        return continueWithTask(new Continuation<TResult, Task<TContinuationResult>>() {
            public Task<TContinuationResult> then(Task<TResult> task) {
                if (task.isFaulted()) {
                    return Task.forError(task.getError());
                }
                if (task.isCancelled()) {
                    return Task.cancelled();
                }
                return task.continueWithTask(continuation);
            }
        }, executor);
    }

    private static <TContinuationResult, TResult> void completeImmediately(final TaskCompletionSource tcs, final Continuation<TResult, TContinuationResult> continuation, final Task<TResult> task, Executor executor) {
        executor.execute(new Runnable() {
            public void run() {
                try {
                    tcs.setResult(continuation.then(task));
                } catch (Exception e) {
                    tcs.setError(e);
                }
            }
        });
    }

    private static <TContinuationResult, TResult> void completeAfterTask(final TaskCompletionSource tcs, final Continuation<TResult, Task<TContinuationResult>> continuation, final Task<TResult> task, Executor executor) {
        executor.execute(new Runnable() {

            /* renamed from: bolts.Task$10$1 */
            class C04391 implements Continuation<TContinuationResult, Void> {
                C04391() {
                }

                public Void then(Task<TContinuationResult> task) {
                    if (task.isCancelled()) {
                        tcs.setCancelled();
                    } else if (task.isFaulted()) {
                        tcs.setError(task.getError());
                    } else {
                        tcs.setResult(task.getResult());
                    }
                    return null;
                }
            }

            public void run() {
                try {
                    Task<TContinuationResult> result = (Task) continuation.then(task);
                    if (result == null) {
                        tcs.setResult(null);
                    } else {
                        result.continueWith(new C04391());
                    }
                } catch (Exception e) {
                    tcs.setError(e);
                }
            }
        });
    }

    private void runContinuations() {
        synchronized (this.lock) {
            for (Continuation<TResult, ?> continuation : this.continuations) {
                try {
                    continuation.then(this);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.continuations = null;
        }
    }
}
