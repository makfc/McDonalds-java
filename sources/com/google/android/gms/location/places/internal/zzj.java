package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.location.places.PlaceDetectionApi;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.zzl;
import com.google.android.gms.location.places.zzl.zzd;
import com.google.android.gms.location.places.zzl.zzf;

public class zzj implements PlaceDetectionApi {

    /* renamed from: com.google.android.gms.location.places.internal.zzj$1 */
    class C23141 extends zzd<zzk> {
        final /* synthetic */ PlaceFilter zzaXK;

        /* Access modifiers changed, original: protected */
        public void zza(zzk zzk) throws RemoteException {
            zzk.zza(new zzl((zzd) this, zzk.getContext()), this.zzaXK);
        }
    }

    /* renamed from: com.google.android.gms.location.places.internal.zzj$2 */
    class C23152 extends zzf<zzk> {
        final /* synthetic */ PlaceReport zzaXM;

        /* Access modifiers changed, original: protected */
        public void zza(zzk zzk) throws RemoteException {
            zzk.zza(new zzl((zzf) this), this.zzaXM);
        }
    }
}
