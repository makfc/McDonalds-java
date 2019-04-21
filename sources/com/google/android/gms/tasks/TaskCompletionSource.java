package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public class TaskCompletionSource<TResult> {
    private final zzh<TResult> zzbwx = new zzh();

    public void setException(@NonNull Exception exception) {
        this.zzbwx.setException(exception);
    }

    public void setResult(TResult tResult) {
        this.zzbwx.setResult(tResult);
    }
}
