package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.location.internal.zzd;
import com.google.android.gms.location.internal.zzf;
import com.google.android.gms.location.internal.zzl;
import com.google.android.gms.location.internal.zzq;

public class LocationServices {
    public static final Api<NoOptions> API = new Api("LocationServices.API", zzaaA, zzaaz);
    public static final FusedLocationProviderApi FusedLocationApi = new zzd();
    public static final GeofencingApi GeofencingApi = new zzf();
    public static final SettingsApi SettingsApi = new zzq();
    private static final com.google.android.gms.common.api.Api.zza<zzl, NoOptions> zzaaA = new C22861();
    private static final Api.zzf<zzl> zzaaz = new Api.zzf();

    /* renamed from: com.google.android.gms.location.LocationServices$1 */
    class C22861 extends com.google.android.gms.common.api.Api.zza<zzl, NoOptions> {
        C22861() {
        }

        /* renamed from: zzn */
        public zzl zza(Context context, Looper looper, zzg zzg, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzl(context, looper, connectionCallbacks, onConnectionFailedListener, "locationServices", zzg);
        }
    }

    public static abstract class zza<R extends Result> extends com.google.android.gms.internal.zznt.zza<R, zzl> {
        public zza(GoogleApiClient googleApiClient) {
            super(LocationServices.zzaaz, googleApiClient);
        }
    }

    private LocationServices() {
    }

    public static zzl zzi(GoogleApiClient googleApiClient) {
        boolean z = true;
        zzaa.zzb(googleApiClient != null, (Object) "GoogleApiClient parameter is required.");
        zzl zzl = (zzl) googleApiClient.zza(zzaaz);
        if (zzl == null) {
            z = false;
        }
        zzaa.zza(z, "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        return zzl;
    }
}
