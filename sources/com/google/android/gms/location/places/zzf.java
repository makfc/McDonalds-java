package com.google.android.gms.location.places;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Status;

public class zzf extends com.google.android.gms.location.places.internal.zzh.zza {
    private final zzb zzaWK;
    private final zza zzaWL;

    public static abstract class zzb<A extends zze> extends com.google.android.gms.location.places.zzl.zzb<PlacePhotoMetadataResult, A> {
        /* Access modifiers changed, original: protected */
        /* renamed from: zzbg */
        public PlacePhotoMetadataResult zzc(Status status) {
            return new PlacePhotoMetadataResult(status, null);
        }
    }

    public static abstract class zza<A extends zze> extends com.google.android.gms.location.places.zzl.zzb<PlacePhotoResult, A> {
        /* Access modifiers changed, original: protected */
        /* renamed from: zzbf */
        public PlacePhotoResult zzc(Status status) {
            return new PlacePhotoResult(status, null);
        }
    }

    public zzf(zza zza) {
        this.zzaWK = null;
        this.zzaWL = zza;
    }

    public zzf(zzb zzb) {
        this.zzaWK = zzb;
        this.zzaWL = null;
    }

    public void zza(PlacePhotoMetadataResult placePhotoMetadataResult) throws RemoteException {
        this.zzaWK.zzb(placePhotoMetadataResult);
    }

    public void zza(PlacePhotoResult placePhotoResult) throws RemoteException {
        this.zzaWL.zzb(placePhotoResult);
    }
}
