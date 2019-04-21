package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public abstract class ResultCallbacks<R extends Result> implements ResultCallback<R> {
    public abstract void onFailure(@NonNull Status status);

    public abstract void onSuccess(@NonNull R r);
}
