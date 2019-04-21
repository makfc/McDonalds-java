package com.mcdonalds.sdk.services.network;

import android.os.Binder;

public final class RequestManagerBinder extends Binder {
    private final RequestManager mManager;

    public RequestManagerBinder(RequestManager manager) {
        this.mManager = manager;
    }

    public RequestManager getManager() {
        return this.mManager;
    }
}
