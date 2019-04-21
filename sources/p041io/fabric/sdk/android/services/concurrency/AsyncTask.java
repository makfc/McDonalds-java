package p041io.fabric.sdk.android.services.concurrency;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask */
public abstract class AsyncTask<Params, Progress, Result> {
    private static final int CORE_POOL_SIZE = (CPU_COUNT + 1);
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int MAXIMUM_POOL_SIZE = ((CPU_COUNT * 2) + 1);
    public static final Executor SERIAL_EXECUTOR = new SerialExecutor();
    public static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 1, TimeUnit.SECONDS, poolWorkQueue, threadFactory);
    private static volatile Executor defaultExecutor = SERIAL_EXECUTOR;
    private static final InternalHandler handler = new InternalHandler();
    private static final BlockingQueue<Runnable> poolWorkQueue = new LinkedBlockingQueue(128);
    private static final ThreadFactory threadFactory = new C45841();
    private final AtomicBoolean cancelled = new AtomicBoolean();
    private final FutureTask<Result> future = new FutureTask<Result>(this.worker) {
        /* Access modifiers changed, original: protected */
        public void done() {
            try {
                AsyncTask.this.postResultIfNotInvoked(get());
            } catch (InterruptedException e) {
                Log.w("AsyncTask", e);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            } catch (CancellationException e3) {
                AsyncTask.this.postResultIfNotInvoked(null);
            }
        }
    };
    private volatile Status status = Status.PENDING;
    private final AtomicBoolean taskInvoked = new AtomicBoolean();
    private final WorkerRunnable<Params, Result> worker = new C45852();

    /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask$1 */
    static class C45841 implements ThreadFactory {
        private final AtomicInteger count = new AtomicInteger(1);

        C45841() {
        }

        public Thread newThread(Runnable r) {
            return new Thread(r, "AsyncTask #" + this.count.getAndIncrement());
        }
    }

    /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask$WorkerRunnable */
    private static abstract class WorkerRunnable<Params, Result> implements Callable<Result> {
        Params[] params;

        private WorkerRunnable() {
        }

        /* synthetic */ WorkerRunnable(C45841 x0) {
            this();
        }
    }

    /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask$2 */
    class C45852 extends WorkerRunnable<Params, Result> {
        C45852() {
            super();
        }

        public Result call() throws Exception {
            AsyncTask.this.taskInvoked.set(true);
            Process.setThreadPriority(10);
            return AsyncTask.this.postResult(AsyncTask.this.doInBackground(this.params));
        }
    }

    /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask$AsyncTaskResult */
    private static class AsyncTaskResult<Data> {
        final Data[] data;
        final AsyncTask task;

        AsyncTaskResult(AsyncTask task, Data... data) {
            this.task = task;
            this.data = data;
        }
    }

    /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask$InternalHandler */
    private static class InternalHandler extends Handler {
        public InternalHandler() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message msg) {
            AsyncTaskResult result = msg.obj;
            switch (msg.what) {
                case 1:
                    result.task.finish(result.data[0]);
                    return;
                case 2:
                    result.task.onProgressUpdate(result.data);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask$SerialExecutor */
    private static class SerialExecutor implements Executor {
        Runnable active;
        final LinkedList<Runnable> tasks;

        private SerialExecutor() {
            this.tasks = new LinkedList();
        }

        /* synthetic */ SerialExecutor(C45841 x0) {
            this();
        }

        public synchronized void execute(final Runnable r) {
            this.tasks.offer(new Runnable() {
                public void run() {
                    try {
                        r.run();
                    } finally {
                        SerialExecutor.this.scheduleNext();
                    }
                }
            });
            if (this.active == null) {
                scheduleNext();
            }
        }

        /* Access modifiers changed, original: protected|declared_synchronized */
        public synchronized void scheduleNext() {
            Runnable runnable = (Runnable) this.tasks.poll();
            this.active = runnable;
            if (runnable != null) {
                AsyncTask.THREAD_POOL_EXECUTOR.execute(this.active);
            }
        }
    }

    /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask$Status */
    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    public abstract Result doInBackground(Params... paramsArr);

    private void postResultIfNotInvoked(Result result) {
        if (!this.taskInvoked.get()) {
            postResult(result);
        }
    }

    private Result postResult(Result result) {
        handler.obtainMessage(1, new AsyncTaskResult(this, result)).sendToTarget();
        return result;
    }

    public final Status getStatus() {
        return this.status;
    }

    /* Access modifiers changed, original: protected */
    public void onPreExecute() {
    }

    /* Access modifiers changed, original: protected */
    public void onPostExecute(Result result) {
    }

    /* Access modifiers changed, original: protected|varargs */
    public void onProgressUpdate(Progress... progressArr) {
    }

    /* Access modifiers changed, original: protected */
    public void onCancelled(Result result) {
        onCancelled();
    }

    /* Access modifiers changed, original: protected */
    public void onCancelled() {
    }

    public final boolean isCancelled() {
        return this.cancelled.get();
    }

    public final boolean cancel(boolean mayInterruptIfRunning) {
        this.cancelled.set(true);
        return this.future.cancel(mayInterruptIfRunning);
    }

    public final AsyncTask<Params, Progress, Result> executeOnExecutor(Executor exec, Params... params) {
        if (this.status != Status.PENDING) {
            switch (this.status) {
                case RUNNING:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case FINISHED:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.status = Status.RUNNING;
        onPreExecute();
        this.worker.params = params;
        exec.execute(this.future);
        return this;
    }

    private void finish(Result result) {
        if (isCancelled()) {
            onCancelled(result);
        } else {
            onPostExecute(result);
        }
        this.status = Status.FINISHED;
    }
}
