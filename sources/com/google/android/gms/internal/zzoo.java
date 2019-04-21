package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public interface zzoo {

    public interface zza {
        void zzc(int i, boolean z);

        void zzd(ConnectionResult connectionResult);

        void zzk(Bundle bundle);
    }

    ConnectionResult blockingConnect(long j, TimeUnit timeUnit);

    void connect();

    void disconnect();

    void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    boolean isConnected();

    boolean isConnecting();

    <A extends zzb, R extends Result, T extends com.google.android.gms.internal.zznt.zza<R, A>> T zzc(@NonNull T t);

    <A extends zzb, T extends com.google.android.gms.internal.zznt.zza<? extends Result, A>> T zzd(@NonNull T t);

    void zzrN();
}
