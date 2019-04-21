package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzk;
import com.google.android.gms.internal.zzno.zza;

public class zznm extends zzk<zzno> {
    public zznm(Context context, Looper looper, zzg zzg, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 40, zzg, connectionCallbacks, onConnectionFailedListener);
    }

    public void zza(zznn zznn, LogEventParcelable logEventParcelable) throws RemoteException {
        ((zzno) zztm()).zza(zznn, logEventParcelable);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: zzaO */
    public zzno zzab(IBinder iBinder) {
        return zza.zzaQ(iBinder);
    }

    /* Access modifiers changed, original: protected */
    public String zzhT() {
        return "com.google.android.gms.clearcut.service.START";
    }

    /* Access modifiers changed, original: protected */
    public String zzhU() {
        return "com.google.android.gms.clearcut.internal.IClearcutLoggerService";
    }
}
