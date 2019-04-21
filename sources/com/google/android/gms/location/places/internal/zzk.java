package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.PlacesOptions.Builder;
import com.google.android.gms.location.places.zzl;
import java.util.Locale;

public class zzk extends com.google.android.gms.common.internal.zzk<zzf> {
    private final PlacesParams zzaXI;
    private final Locale zzaXJ;

    public static class zza extends com.google.android.gms.common.api.Api.zza<zzk, PlacesOptions> {
        /* renamed from: zzb */
        public zzk zza(Context context, Looper looper, zzg zzg, PlacesOptions placesOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            PlacesOptions build = placesOptions == null ? new Builder().build() : placesOptions;
            String packageName = context.getPackageName();
            if (build.zzaXl != null) {
                packageName = build.zzaXl;
            }
            return new zzk(context, looper, zzg, connectionCallbacks, onConnectionFailedListener, packageName, build);
        }
    }

    private zzk(Context context, Looper looper, zzg zzg, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str, PlacesOptions placesOptions) {
        super(context, looper, 67, zzg, connectionCallbacks, onConnectionFailedListener);
        this.zzaXJ = Locale.getDefault();
        String str2 = null;
        if (zzg.getAccount() != null) {
            str2 = zzg.getAccount().name;
        }
        this.zzaXI = new PlacesParams(str, this.zzaXJ, str2, placesOptions.zzaXm, placesOptions.zzaXn);
    }

    public void zza(zzl zzl, PlaceFilter placeFilter) throws RemoteException {
        if (placeFilter == null) {
            placeFilter = PlaceFilter.zzCM();
        }
        ((zzf) zztm()).zza(placeFilter, this.zzaXI, (zzi) zzl);
    }

    public void zza(zzl zzl, PlaceReport placeReport) throws RemoteException {
        zzaa.zzz(placeReport);
        ((zzf) zztm()).zza(placeReport, this.zzaXI, (zzi) zzl);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: zzcy */
    public zzf zzab(IBinder iBinder) {
        return com.google.android.gms.location.places.internal.zzf.zza.zzcu(iBinder);
    }

    /* Access modifiers changed, original: protected */
    public String zzhT() {
        return "com.google.android.gms.location.places.PlaceDetectionApi";
    }

    /* Access modifiers changed, original: protected */
    public String zzhU() {
        return "com.google.android.gms.location.places.internal.IGooglePlaceDetectionService";
    }
}
