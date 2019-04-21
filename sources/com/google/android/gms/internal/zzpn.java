package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zznt.zzb;

public final class zzpn implements zzpm {

    private static class zza extends zzpk {
        private final zzb<Status> zzasz;

        public zza(zzb<Status> zzb) {
            this.zzasz = zzb;
        }

        public void zzck(int i) throws RemoteException {
            this.zzasz.setResult(new Status(i));
        }
    }

    public PendingResult<Status> zzf(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zza(googleApiClient) {
            /* Access modifiers changed, original: protected */
            public void zza(zzpp zzpp) throws RemoteException {
                ((zzpr) zzpp.zztm()).zza(new zza(this));
            }
        });
    }
}
