package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzk;
import com.google.android.gms.internal.zzpr.zza;

public class zzpp extends zzk<zzpr> {
    public zzpp(Context context, Looper looper, zzg zzg, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 39, zzg, connectionCallbacks, onConnectionFailedListener);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: zzba */
    public zzpr zzab(IBinder iBinder) {
        return zza.zzbc(iBinder);
    }

    public String zzhT() {
        return "com.google.android.gms.common.service.START";
    }

    /* Access modifiers changed, original: protected */
    public String zzhU() {
        return "com.google.android.gms.common.internal.service.ICommonService";
    }
}
