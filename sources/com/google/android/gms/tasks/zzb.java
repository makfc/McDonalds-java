package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzb<TResult, TContinuationResult> implements OnFailureListener, OnSuccessListener<TContinuationResult>, zzf<TResult> {
    private final Executor zzboH;
    private final Continuation<TResult, Task<TContinuationResult>> zzbwk;
    private final zzh<TContinuationResult> zzbwl;

    public void cancel() {
        throw new UnsupportedOperationException();
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        this.zzboH.execute(new Runnable() {
            public void run() {
                try {
                    Task task = (Task) zzb.this.zzbwk.then(task);
                    if (task == null) {
                        zzb.this.onFailure(new NullPointerException("Continuation returned null"));
                        return;
                    }
                    task.addOnSuccessListener(TaskExecutors.zzbwy, zzb.this);
                    task.addOnFailureListener(TaskExecutors.zzbwy, zzb.this);
                } catch (RuntimeExecutionException e) {
                    if (e.getCause() instanceof Exception) {
                        zzb.this.zzbwl.setException((Exception) e.getCause());
                    } else {
                        zzb.this.zzbwl.setException(e);
                    }
                } catch (Exception e2) {
                    zzb.this.zzbwl.setException(e2);
                }
            }
        });
    }

    public void onFailure(@NonNull Exception exception) {
        this.zzbwl.setException(exception);
    }

    public void onSuccess(TContinuationResult tContinuationResult) {
        this.zzbwl.setResult(tContinuationResult);
    }
}
