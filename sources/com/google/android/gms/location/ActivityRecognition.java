package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.location.internal.zzl;

public class ActivityRecognition {
    public static final Api<NoOptions> API = new Api("ActivityRecognition.API", zzaaA, zzaaz);
    public static final ActivityRecognitionApi ActivityRecognitionApi = new com.google.android.gms.location.internal.zza();
    private static final com.google.android.gms.common.api.Api.zza<zzl, NoOptions> zzaaA = new C22831();
    private static final zzf<zzl> zzaaz = new zzf();

    /* renamed from: com.google.android.gms.location.ActivityRecognition$1 */
    class C22831 extends com.google.android.gms.common.api.Api.zza<zzl, NoOptions> {
        C22831() {
        }

        /* renamed from: zzn */
        public zzl zza(Context context, Looper looper, zzg zzg, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzl(context, looper, connectionCallbacks, onConnectionFailedListener, "activity_recognition");
        }
    }

    public static abstract class zza<R extends Result> extends com.google.android.gms.internal.zznt.zza<R, zzl> {
    }

    private ActivityRecognition() {
    }
}
