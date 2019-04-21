package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzk;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.PlacesOptions.Builder;
import com.google.android.gms.location.places.zzf;
import com.google.android.gms.location.places.zzl;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;
import java.util.Locale;

public class zze extends zzk<zzg> {
    private final PlacesParams zzaXI;
    private final Locale zzaXJ;

    public static class zza extends com.google.android.gms.common.api.Api.zza<zze, PlacesOptions> {
        public zze zza(Context context, Looper looper, zzg zzg, PlacesOptions placesOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            PlacesOptions build = placesOptions == null ? new Builder().build() : placesOptions;
            String packageName = context.getPackageName();
            if (build.zzaXl != null) {
                packageName = build.zzaXl;
            }
            return new zze(context, looper, zzg, connectionCallbacks, onConnectionFailedListener, packageName, build);
        }
    }

    private zze(Context context, Looper looper, zzg zzg, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str, PlacesOptions placesOptions) {
        super(context, looper, 65, zzg, connectionCallbacks, onConnectionFailedListener);
        this.zzaXJ = Locale.getDefault();
        String str2 = null;
        if (zzg.getAccount() != null) {
            str2 = zzg.getAccount().name;
        }
        this.zzaXI = new PlacesParams(str, this.zzaXJ, str2, placesOptions.zzaXm, placesOptions.zzaXn);
    }

    public void zza(zzf zzf, String str) throws RemoteException {
        zzaa.zzb((Object) str, (Object) "placeId cannot be null");
        ((zzg) zztm()).zza(str, this.zzaXI, (zzh) zzf);
    }

    public void zza(zzf zzf, String str, int i, int i2, int i3) throws RemoteException {
        boolean z = true;
        zzaa.zzb((Object) str, (Object) "fifeUrl cannot be null");
        zzaa.zzb(i > 0, (Object) "width should be > 0");
        if (i <= 0) {
            z = false;
        }
        zzaa.zzb(z, (Object) "height should be > 0");
        ((zzg) zztm()).zza(str, i, i2, i3, this.zzaXI, (zzh) zzf);
    }

    public void zza(zzl zzl, AddPlaceRequest addPlaceRequest) throws RemoteException {
        zzaa.zzb((Object) addPlaceRequest, (Object) "userAddedPlace == null");
        ((zzg) zztm()).zza(addPlaceRequest, this.zzaXI, (zzi) zzl);
    }

    public void zza(zzl zzl, String str, @Nullable LatLngBounds latLngBounds, @Nullable AutocompleteFilter autocompleteFilter) throws RemoteException {
        zzaa.zzb((Object) zzl, (Object) "callback == null");
        ((zzg) zztm()).zza(str == null ? "" : str, latLngBounds, autocompleteFilter == null ? new AutocompleteFilter.Builder().build() : autocompleteFilter, this.zzaXI, (zzi) zzl);
    }

    public void zza(zzl zzl, List<String> list) throws RemoteException {
        ((zzg) zztm()).zzb((List) list, this.zzaXI, (zzi) zzl);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: zzct */
    public zzg zzab(IBinder iBinder) {
        return com.google.android.gms.location.places.internal.zzg.zza.zzcv(iBinder);
    }

    /* Access modifiers changed, original: protected */
    public String zzhT() {
        return "com.google.android.gms.location.places.GeoDataApi";
    }

    /* Access modifiers changed, original: protected */
    public String zzhU() {
        return "com.google.android.gms.location.places.internal.IGooglePlacesService";
    }
}
