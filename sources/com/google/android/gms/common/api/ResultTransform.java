package com.google.android.gms.common.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

public abstract class ResultTransform<R extends Result, S extends Result> {
    @NonNull
    public Status onFailure(@NonNull Status status) {
        return status;
    }

    @Nullable
    @WorkerThread
    public abstract PendingResult<S> onSuccess(@NonNull R r);
}
