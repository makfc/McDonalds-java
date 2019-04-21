package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.zzf;
import com.google.android.gms.location.places.zzf.zzb;
import com.google.android.gms.location.places.zzl;
import com.google.android.gms.location.places.zzl.zza;
import com.google.android.gms.location.places.zzl.zzc;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Arrays;

public class zzd implements GeoDataApi {

    /* renamed from: com.google.android.gms.location.places.internal.zzd$1 */
    class C23091 extends zzc<zze> {
        final /* synthetic */ AddPlaceRequest zzaXB;

        /* Access modifiers changed, original: protected */
        public void zza(zze zze) throws RemoteException {
            zze.zza(new zzl((zzc) this, zze.getContext()), this.zzaXB);
        }
    }

    /* renamed from: com.google.android.gms.location.places.internal.zzd$2 */
    class C23102 extends zzc<zze> {
        final /* synthetic */ String[] zzaXD;

        /* Access modifiers changed, original: protected */
        public void zza(zze zze) throws RemoteException {
            zze.zza(new zzl((zzc) this, zze.getContext()), Arrays.asList(this.zzaXD));
        }
    }

    /* renamed from: com.google.android.gms.location.places.internal.zzd$3 */
    class C23113 extends zza<zze> {
        final /* synthetic */ String zzaXE;
        final /* synthetic */ LatLngBounds zzaXF;
        final /* synthetic */ AutocompleteFilter zzaXG;

        /* Access modifiers changed, original: protected */
        public void zza(zze zze) throws RemoteException {
            zze.zza(new zzl((zza) this), this.zzaXE, this.zzaXF, this.zzaXG);
        }
    }

    /* renamed from: com.google.android.gms.location.places.internal.zzd$4 */
    class C23124 extends zzb<zze> {
        final /* synthetic */ String zzaXH;

        /* Access modifiers changed, original: protected */
        public void zza(zze zze) throws RemoteException {
            zze.zza(new zzf((zzb) this), this.zzaXH);
        }
    }
}
