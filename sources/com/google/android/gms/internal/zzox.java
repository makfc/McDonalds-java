package com.google.android.gms.internal;

import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import java.util.concurrent.TimeUnit;

public final class zzox<R extends Result> extends OptionalPendingResult<R> {
    private final zznv<R> zzaoy;

    public R await(long j, TimeUnit timeUnit) {
        return this.zzaoy.await(j, timeUnit);
    }

    public void cancel() {
        this.zzaoy.cancel();
    }

    public void setResultCallback(ResultCallback<? super R> resultCallback) {
        this.zzaoy.setResultCallback(resultCallback);
    }

    public void setResultCallback(ResultCallback<? super R> resultCallback, long j, TimeUnit timeUnit) {
        this.zzaoy.setResultCallback(resultCallback, j, timeUnit);
    }

    public Integer zzrv() {
        return this.zzaoy.zzrv();
    }
}
