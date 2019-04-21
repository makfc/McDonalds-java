package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zznt.zzb;
import com.google.android.gms.location.GeofencingApi;
import com.google.android.gms.location.GeofencingRequest;
import java.util.List;

public class zzf implements GeofencingApi {

    private static abstract class zza extends com.google.android.gms.location.LocationServices.zza<Status> {
        /* renamed from: zzb */
        public Status zzc(Status status) {
            return status;
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzf$1 */
    class C23021 extends zza {
        final /* synthetic */ PendingIntent zzaHc;
        final /* synthetic */ GeofencingRequest zzaVT;

        /* Access modifiers changed, original: protected */
        public void zza(zzl zzl) throws RemoteException {
            zzl.zza(this.zzaVT, this.zzaHc, (zzb) this);
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzf$2 */
    class C23032 extends zza {
        final /* synthetic */ PendingIntent zzaHc;

        /* Access modifiers changed, original: protected */
        public void zza(zzl zzl) throws RemoteException {
            zzl.zza(this.zzaHc, (zzb) this);
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzf$3 */
    class C23043 extends zza {
        final /* synthetic */ List zzaVV;

        /* Access modifiers changed, original: protected */
        public void zza(zzl zzl) throws RemoteException {
            zzl.zza(this.zzaVV, (zzb) this);
        }
    }
}
