package com.google.android.gms.playlog.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzd.zzb;
import com.google.android.gms.common.internal.zzd.zzc;
import com.google.android.gms.internal.zztd.zza;

public class zzd implements zzb, zzc {
    private zzf zzbkM;
    private final zza zzbkX;
    private boolean zzbkY;

    public void onConnected(Bundle bundle) {
        this.zzbkM.zzaB(false);
        if (this.zzbkY && this.zzbkX != null) {
            this.zzbkX.zzIe();
        }
        this.zzbkY = false;
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzbkM.zzaB(true);
        if (this.zzbkY && this.zzbkX != null) {
            if (connectionResult.hasResolution()) {
                this.zzbkX.zzc(connectionResult.getResolution());
            } else {
                this.zzbkX.zzIf();
            }
        }
        this.zzbkY = false;
    }

    public void onConnectionSuspended(int i) {
        this.zzbkM.zzaB(true);
    }

    public void zzaA(boolean z) {
        this.zzbkY = z;
    }
}
