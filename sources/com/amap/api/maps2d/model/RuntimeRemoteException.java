package com.amap.api.maps2d.model;

import android.os.RemoteException;

public final class RuntimeRemoteException extends RuntimeException {
    public RuntimeRemoteException(RemoteException remoteException) {
        super(remoteException);
    }
}
