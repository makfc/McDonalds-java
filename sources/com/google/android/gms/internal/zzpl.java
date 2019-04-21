package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;

public final class zzpl {
    public static final Api<NoOptions> API = new Api("Common.API", zzaaA, zzaaz);
    private static final zza<zzpp, NoOptions> zzaaA = new C22611();
    public static final zzf<zzpp> zzaaz = new zzf();
    public static final zzpm zzasx = new zzpn();

    /* renamed from: com.google.android.gms.internal.zzpl$1 */
    class C22611 extends zza<zzpp, NoOptions> {
        C22611() {
        }

        /* renamed from: zze */
        public zzpp zza(Context context, Looper looper, zzg zzg, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzpp(context, looper, zzg, connectionCallbacks, onConnectionFailedListener);
        }
    }
}
