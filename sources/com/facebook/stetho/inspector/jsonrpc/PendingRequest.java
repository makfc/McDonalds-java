package com.facebook.stetho.inspector.jsonrpc;

import javax.annotation.Nullable;

public class PendingRequest {
    @Nullable
    public final PendingRequestCallback callback;
    public final long requestId;

    public PendingRequest(long requestId, @Nullable PendingRequestCallback callback) {
        this.requestId = requestId;
        this.callback = callback;
    }
}
