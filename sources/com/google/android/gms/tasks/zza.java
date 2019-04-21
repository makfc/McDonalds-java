package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zza<TResult, TContinuationResult> implements zzf<TResult> {
    private final Executor zzboH;
    private final Continuation<TResult, TContinuationResult> zzbwk;
    private final zzh<TContinuationResult> zzbwl;

    public void cancel() {
        throw new UnsupportedOperationException();
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        this.zzboH.execute(new Runnable() {
            public void run() {
                try {
                    zza.this.zzbwl.setResult(zza.this.zzbwk.then(task));
                } catch (RuntimeExecutionException e) {
                    if (e.getCause() instanceof Exception) {
                        zza.this.zzbwl.setException((Exception) e.getCause());
                    } else {
                        zza.this.zzbwl.setException(e);
                    }
                } catch (Exception e2) {
                    zza.this.zzbwl.setException(e2);
                }
            }
        });
    }
}
