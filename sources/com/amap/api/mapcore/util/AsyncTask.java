package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.amap.api.mapcore.util.cv */
public abstract class AsyncTask<Params, Progress, Result> {
    /* renamed from: a */
    private static final ThreadFactory f1219a = new C0804cw();
    /* renamed from: b */
    public static final Executor f1220b = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, f1223e, f1219a, new DiscardOldestPolicy());
    /* renamed from: c */
    public static final Executor f1221c;
    /* renamed from: d */
    public static final Executor f1222d = Executors.newFixedThreadPool(2, f1219a);
    /* renamed from: e */
    private static final BlockingQueue<Runnable> f1223e = new LinkedBlockingQueue(10);
    /* renamed from: f */
    private static final C0743b f1224f = new C0743b();
    /* renamed from: g */
    private static volatile Executor f1225g = f1221c;
    /* renamed from: h */
    private final C0739e<Params, Result> f1226h = new C07401();
    /* renamed from: i */
    private final FutureTask<Result> f1227i = new FutureTask<Result>(this.f1226h) {
        /* Access modifiers changed, original: protected */
        public void done() {
            try {
                AsyncTask.this.m1659c(AsyncTask.this.f1227i.get());
            } catch (InterruptedException e) {
                Log.w("AsyncTask", e);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            } catch (CancellationException e3) {
                AsyncTask.this.m1659c(null);
            }
        }
    };
    /* renamed from: j */
    private volatile C0746d f1228j = C0746d.PENDING;
    /* renamed from: k */
    private final AtomicBoolean f1229k = new AtomicBoolean();
    /* renamed from: l */
    private final AtomicBoolean f1230l = new AtomicBoolean();

    /* compiled from: AsyncTask */
    /* renamed from: com.amap.api.mapcore.util.cv$e */
    private static abstract class C0739e<Params, Result> implements Callable<Result> {
        /* renamed from: b */
        Params[] f1231b;

        private C0739e() {
        }

        /* synthetic */ C0739e(C0804cw c0804cw) {
            this();
        }
    }

    /* compiled from: AsyncTask */
    /* renamed from: com.amap.api.mapcore.util.cv$1 */
    class C07401 extends C0739e<Params, Result> {
        C07401() {
            super();
        }

        public Result call() throws Exception {
            AsyncTask.this.f1230l.set(true);
            Process.setThreadPriority(10);
            return AsyncTask.this.m1660d(AsyncTask.this.mo8706a(this.f1231b));
        }
    }

    /* compiled from: AsyncTask */
    /* renamed from: com.amap.api.mapcore.util.cv$a */
    private static class C0742a<Data> {
        /* renamed from: a */
        final AsyncTask f1234a;
        /* renamed from: b */
        final Data[] f1235b;

        C0742a(AsyncTask asyncTask, Data... dataArr) {
            this.f1234a = asyncTask;
            this.f1235b = dataArr;
        }
    }

    /* compiled from: AsyncTask */
    /* renamed from: com.amap.api.mapcore.util.cv$b */
    private static class C0743b extends Handler {
        private C0743b() {
        }

        /* synthetic */ C0743b(C0804cw c0804cw) {
            this();
        }

        public void handleMessage(Message message) {
            C0742a c0742a = (C0742a) message.obj;
            switch (message.what) {
                case 1:
                    c0742a.f1234a.m1661e(c0742a.f1235b[0]);
                    return;
                case 2:
                    c0742a.f1234a.mo8711b(c0742a.f1235b);
                    return;
                default:
                    return;
            }
        }
    }

    /* compiled from: AsyncTask */
    /* renamed from: com.amap.api.mapcore.util.cv$c */
    private static class C0744c implements Executor {
        /* renamed from: a */
        final ArrayDeque<Runnable> f1236a;
        /* renamed from: b */
        Runnable f1237b;

        private C0744c() {
            this.f1236a = new ArrayDeque();
        }

        /* synthetic */ C0744c(C0804cw c0804cw) {
            this();
        }

        public synchronized void execute(final Runnable runnable) {
            this.f1236a.offer(new Runnable() {
                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        C0744c.this.mo8718a();
                    }
                }
            });
            if (this.f1237b == null) {
                mo8718a();
            }
        }

        /* Access modifiers changed, original: protected|declared_synchronized */
        /* renamed from: a */
        public synchronized void mo8718a() {
            Runnable runnable = (Runnable) this.f1236a.poll();
            this.f1237b = runnable;
            if (runnable != null) {
                AsyncTask.f1220b.execute(this.f1237b);
            }
        }
    }

    /* compiled from: AsyncTask */
    /* renamed from: com.amap.api.mapcore.util.cv$d */
    public enum C0746d {
        PENDING,
        RUNNING,
        FINISHED
    }

    /* renamed from: a */
    public abstract Result mo8706a(Params... paramsArr);

    static {
        Executor c0744c;
        if (Util.m2374c()) {
            c0744c = new C0744c();
        } else {
            c0744c = Executors.newSingleThreadExecutor(f1219a);
        }
        f1221c = c0744c;
    }

    /* renamed from: c */
    private void m1659c(Result result) {
        if (!this.f1230l.get()) {
            m1660d(result);
        }
    }

    /* renamed from: d */
    private Result m1660d(Result result) {
        f1224f.obtainMessage(1, new C0742a(this, result)).sendToTarget();
        return result;
    }

    /* renamed from: a */
    public final C0746d mo8704a() {
        return this.f1228j;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public void mo8709b() {
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo8707a(Result result) {
    }

    /* Access modifiers changed, original: protected|varargs */
    /* renamed from: b */
    public void mo8711b(Progress... progressArr) {
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public void mo8710b(Result result) {
        mo8713c();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: c */
    public void mo8713c() {
    }

    /* renamed from: d */
    public final boolean mo8714d() {
        return this.f1229k.get();
    }

    /* renamed from: a */
    public final boolean mo8708a(boolean z) {
        this.f1229k.set(true);
        return this.f1227i.cancel(z);
    }

    /* renamed from: c */
    public final AsyncTask<Params, Progress, Result> mo8712c(Params... paramsArr) {
        return mo8705a(f1225g, (Object[]) paramsArr);
    }

    /* renamed from: a */
    public final AsyncTask<Params, Progress, Result> mo8705a(Executor executor, Params... paramsArr) {
        if (this.f1228j != C0746d.PENDING) {
            switch (C0805cx.f1691a[this.f1228j.ordinal()]) {
                case 1:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case 2:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.f1228j = C0746d.RUNNING;
        mo8709b();
        this.f1226h.f1231b = paramsArr;
        executor.execute(this.f1227i);
        return this;
    }

    /* renamed from: e */
    private void m1661e(Result result) {
        if (mo8714d()) {
            mo8710b((Object) result);
        } else {
            mo8707a((Object) result);
        }
        this.f1228j = C0746d.FINISHED;
    }
}
