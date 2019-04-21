package com.google.android.gms.location.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzk;
import com.google.android.gms.location.internal.zzi.zza;

public class zzb extends zzk<zzi> {
    private final String zzaVI;
    protected final zzp<zzi> zzaVJ = new C22911();

    /* renamed from: com.google.android.gms.location.internal.zzb$1 */
    class C22911 implements zzp<zzi> {
        C22911() {
        }

        /* renamed from: zzCu */
        public zzi zztm() throws DeadObjectException {
            return (zzi) zzb.this.zztm();
        }

        public void zztl() {
            zzb.this.zztl();
        }
    }

    public zzb(Context context, Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str, zzg zzg) {
        super(context, looper, 23, zzg, connectionCallbacks, onConnectionFailedListener);
        this.zzaVI = str;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: zzco */
    public zzi zzab(IBinder iBinder) {
        return zza.zzcr(iBinder);
    }

    /* Access modifiers changed, original: protected */
    public String zzhT() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    /* Access modifiers changed, original: protected */
    public String zzhU() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    /* Access modifiers changed, original: protected */
    public Bundle zzoO() {
        Bundle bundle = new Bundle();
        bundle.putString("client_name", this.zzaVI);
        return bundle;
    }
}
