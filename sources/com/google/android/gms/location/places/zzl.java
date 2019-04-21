package com.google.android.gms.location.places;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zzu;

public class zzl extends com.google.android.gms.location.places.internal.zzi.zza {
    private static final String TAG = zzl.class.getSimpleName();
    private final Context mContext;
    private final zzd zzaXg;
    private final zza zzaXh;
    private final zze zzaXi;
    private final zzf zzaXj;
    private final zzc zzaXk;

    public static abstract class zzb<R extends Result, A extends com.google.android.gms.common.api.Api.zze> extends com.google.android.gms.internal.zznt.zza<R, A> {
    }

    public static abstract class zzc<A extends com.google.android.gms.common.api.Api.zze> extends zzb<PlaceBuffer, A> {
        /* Access modifiers changed, original: protected */
        /* renamed from: zzbj */
        public PlaceBuffer zzc(Status status) {
            return new PlaceBuffer(DataHolder.zzbQ(status.getStatusCode()), null);
        }
    }

    public static abstract class zza<A extends com.google.android.gms.common.api.Api.zze> extends zzb<AutocompletePredictionBuffer, A> {
        /* Access modifiers changed, original: protected */
        /* renamed from: zzbi */
        public AutocompletePredictionBuffer zzc(Status status) {
            return new AutocompletePredictionBuffer(DataHolder.zzbQ(status.getStatusCode()));
        }
    }

    public static abstract class zzd<A extends com.google.android.gms.common.api.Api.zze> extends zzb<PlaceLikelihoodBuffer, A> {
        /* Access modifiers changed, original: protected */
        /* renamed from: zzbk */
        public PlaceLikelihoodBuffer zzc(Status status) {
            return new PlaceLikelihoodBuffer(DataHolder.zzbQ(status.getStatusCode()), 100, null);
        }
    }

    public static abstract class zzf<A extends com.google.android.gms.common.api.Api.zze> extends zzb<Status, A> {
        /* Access modifiers changed, original: protected */
        /* renamed from: zzb */
        public Status zzc(Status status) {
            return status;
        }
    }

    public static abstract class zze<A extends com.google.android.gms.common.api.Api.zze> extends zzb<com.google.android.gms.location.places.personalized.zze, A> {
    }

    public zzl(zza zza) {
        this.zzaXg = null;
        this.zzaXh = zza;
        this.zzaXi = null;
        this.zzaXj = null;
        this.zzaXk = null;
        this.mContext = null;
    }

    public zzl(zzc zzc, Context context) {
        this.zzaXg = null;
        this.zzaXh = null;
        this.zzaXi = null;
        this.zzaXj = null;
        this.zzaXk = zzc;
        this.mContext = context.getApplicationContext();
    }

    public zzl(zzd zzd, Context context) {
        this.zzaXg = zzd;
        this.zzaXh = null;
        this.zzaXi = null;
        this.zzaXj = null;
        this.zzaXk = null;
        this.mContext = context.getApplicationContext();
    }

    public zzl(zzf zzf) {
        this.zzaXg = null;
        this.zzaXh = null;
        this.zzaXi = null;
        this.zzaXj = zzf;
        this.zzaXk = null;
        this.mContext = null;
    }

    public void zzam(DataHolder dataHolder) throws RemoteException {
        zzaa.zza(this.zzaXg != null, "placeEstimator cannot be null");
        if (dataHolder == null) {
            if (Log.isLoggable(TAG, 6)) {
                String str = TAG;
                String str2 = "onPlaceEstimated received null DataHolder: ";
                String valueOf = String.valueOf(zzu.zzvk());
                Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
            this.zzaXg.zzx(Status.zzaly);
            return;
        }
        Bundle zzsO = dataHolder.zzsO();
        this.zzaXg.zzb(new PlaceLikelihoodBuffer(dataHolder, zzsO == null ? 100 : PlaceLikelihoodBuffer.zzI(zzsO), this.mContext));
    }

    public void zzan(DataHolder dataHolder) throws RemoteException {
        if (dataHolder == null) {
            if (Log.isLoggable(TAG, 6)) {
                String str = TAG;
                String str2 = "onAutocompletePrediction received null DataHolder: ";
                String valueOf = String.valueOf(zzu.zzvk());
                Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
            this.zzaXh.zzx(Status.zzaly);
            return;
        }
        this.zzaXh.zzb(new AutocompletePredictionBuffer(dataHolder));
    }

    public void zzao(DataHolder dataHolder) throws RemoteException {
        zze zze = null;
        if (dataHolder == null) {
            if (Log.isLoggable(TAG, 6)) {
                String str = TAG;
                String str2 = "onPlaceUserDataFetched received null DataHolder: ";
                String valueOf = String.valueOf(zzu.zzvk());
                Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
            zze.zzx(Status.zzaly);
            return;
        }
        zze.zzb(new com.google.android.gms.location.places.personalized.zze(dataHolder));
    }

    public void zzap(DataHolder dataHolder) throws RemoteException {
        this.zzaXk.zzb(new PlaceBuffer(dataHolder, this.mContext));
    }

    public void zzbh(Status status) throws RemoteException {
        this.zzaXj.zzb(status);
    }
}
