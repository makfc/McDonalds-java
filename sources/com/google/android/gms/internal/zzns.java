package com.google.android.gms.internal;

import android.support.p000v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.common.api.zzb;
import java.util.Set;

public final class zzns extends zznv<zzb> {
    private int zzalM;
    private boolean zzalN;

    private void zza(ConnectionResult connectionResult) {
        ArrayMap arrayMap = null;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < arrayMap.size()) {
                zza((zznq) arrayMap.keyAt(i2), connectionResult);
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public void zza(zznq<?> zznq, ConnectionResult connectionResult) {
        synchronized (null) {
            ArrayMap arrayMap = null;
            try {
                arrayMap.put(zznq, connectionResult);
                this.zzalM--;
                boolean isSuccess = connectionResult.isSuccess();
                if (!isSuccess) {
                    this.zzalN = isSuccess;
                }
                if (this.zzalM == 0) {
                    Status status = this.zzalN ? new Status(13) : Status.zzalw;
                    arrayMap = null;
                    zzb(arrayMap.size() == 1 ? new zza(status, null) : new zzb(status, null));
                }
            } finally {
            }
        }
    }

    public Set<zznq<?>> zzrC() {
        ArrayMap arrayMap = null;
        return arrayMap.keySet();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: zzw */
    public zzb zzc(Status status) {
        zzb zzb;
        synchronized (null) {
            try {
                zza(new ConnectionResult(8));
                ArrayMap arrayMap = null;
                if (arrayMap.size() != 1) {
                    zzb = new zzb(status, null);
                }
            } finally {
            }
        }
        return zzb;
    }
}
