package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api.zzg;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

public class zzag<T extends IInterface> extends zzk<T> {
    private final zzg<T> zzasr;

    public zzag(Context context, Looper looper, int i, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, zzg zzg, zzg<T> zzg2) {
        super(context, looper, i, zzg, connectionCallbacks, onConnectionFailedListener);
        this.zzasr = zzg2;
    }

    /* Access modifiers changed, original: protected */
    public T zzab(IBinder iBinder) {
        return this.zzasr.zzab(iBinder);
    }

    /* Access modifiers changed, original: protected */
    public void zzc(int i, T t) {
        this.zzasr.zza(i, t);
    }

    /* Access modifiers changed, original: protected */
    public String zzhT() {
        return this.zzasr.zzhT();
    }

    /* Access modifiers changed, original: protected */
    public String zzhU() {
        return this.zzasr.zzhU();
    }

    public zzg<T> zztX() {
        return this.zzasr;
    }
}
