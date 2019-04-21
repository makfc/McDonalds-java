package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Account;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.internal.zze;

public final class zztf implements Account {

    private static abstract class zza extends com.google.android.gms.plus.Plus.zza<Status> {
        /* renamed from: zzb */
        public Status zzc(Status status) {
            return status;
        }
    }

    /* renamed from: com.google.android.gms.internal.zztf$1 */
    class C22711 extends zza {
        /* Access modifiers changed, original: protected */
        public void zza(zze zze) {
            zze.zzu(this);
        }
    }

    public void clearDefaultAccount(GoogleApiClient googleApiClient) {
        zze zzf = Plus.zzf(googleApiClient, false);
        if (zzf != null) {
            zzf.zzIk();
        }
    }

    public String getAccountName(GoogleApiClient googleApiClient) {
        return Plus.zzf(googleApiClient, true).getAccountName();
    }
}
