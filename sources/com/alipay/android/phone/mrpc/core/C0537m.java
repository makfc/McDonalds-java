package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* renamed from: com.alipay.android.phone.mrpc.core.m */
final class C0537m extends FutureTask<C0541u> {
    /* renamed from: a */
    final /* synthetic */ C0543q f362a;
    /* renamed from: b */
    final /* synthetic */ C0536l f363b;

    C0537m(C0536l c0536l, Callable callable, C0543q c0543q) {
        this.f363b = c0536l;
        this.f362a = c0543q;
        super(callable);
    }

    /* Access modifiers changed, original: protected|final */
    public final void done() {
        C0540o a = this.f362a.mo7926a();
        if (a.mo7903f() == null) {
            super.done();
            return;
        }
        try {
            get();
            if (isCancelled() || a.mo7905h()) {
                a.mo7904g();
                if (!isCancelled() || !isDone()) {
                    cancel(false);
                }
            }
        } catch (InterruptedException e) {
            new StringBuilder().append(e);
        } catch (ExecutionException e2) {
            if (e2.getCause() == null || !(e2.getCause() instanceof HttpException)) {
                new StringBuilder().append(e2);
                return;
            }
            HttpException httpException = (HttpException) e2.getCause();
            httpException.getCode();
            httpException.getMsg();
        } catch (CancellationException e3) {
            a.mo7904g();
        } catch (Throwable th) {
            RuntimeException runtimeException = new RuntimeException("An error occured while executing http request", th);
        }
    }
}
