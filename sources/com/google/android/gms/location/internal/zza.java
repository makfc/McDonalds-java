package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognitionApi;

public class zza implements ActivityRecognitionApi {

    private static abstract class zza extends com.google.android.gms.location.ActivityRecognition.zza<Status> {
        /* renamed from: zzb */
        public Status zzc(Status status) {
            return status;
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zza$1 */
    class C22891 extends zza {
        final /* synthetic */ long zzaVF;
        final /* synthetic */ PendingIntent zzaVG;

        /* Access modifiers changed, original: protected */
        public void zza(zzl zzl) throws RemoteException {
            zzl.zza(this.zzaVF, this.zzaVG);
            zzb(Status.zzalw);
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zza$2 */
    class C22902 extends zza {
        final /* synthetic */ PendingIntent zzaVG;

        /* Access modifiers changed, original: protected */
        public void zza(zzl zzl) throws RemoteException {
            zzl.zza(this.zzaVG);
            zzb(Status.zzalw);
        }
    }
}
