package com.google.android.gms.common.api;

public final class BatchResult implements Result {
    private final Status zzaaO;
    private final PendingResult<?>[] zzakO;

    BatchResult(Status status, PendingResult<?>[] pendingResultArr) {
        this.zzaaO = status;
        this.zzakO = pendingResultArr;
    }

    public Status getStatus() {
        return this.zzaaO;
    }
}
