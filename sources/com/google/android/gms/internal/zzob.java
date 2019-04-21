package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class zzob extends GoogleApiClient {
    private final UnsupportedOperationException zzamA;

    public ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        throw this.zzamA;
    }

    public void connect() {
        throw this.zzamA;
    }

    public void disconnect() {
        throw this.zzamA;
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        throw this.zzamA;
    }

    public boolean hasConnectedApi(@NonNull Api<?> api) {
        throw this.zzamA;
    }

    public boolean isConnected() {
        throw this.zzamA;
    }

    public boolean isConnecting() {
        throw this.zzamA;
    }

    public void registerConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        throw this.zzamA;
    }

    public void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        throw this.zzamA;
    }
}
