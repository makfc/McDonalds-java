package com.google.android.gms.clearcut;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.internal.zznl;
import com.google.android.gms.internal.zznm;

public final class zzb {
    public static final Api<NoOptions> API = new Api("ClearcutLogger.API", zzaaA, zzaaz);
    public static final com.google.android.gms.common.api.Api.zza<zznm, NoOptions> zzaaA = new C21031();
    public static final zzf<zznm> zzaaz = new zzf();
    public static final zzc zzajv = new zznl();

    /* renamed from: com.google.android.gms.clearcut.zzb$1 */
    class C21031 extends com.google.android.gms.common.api.Api.zza<zznm, NoOptions> {
        C21031() {
        }

        /* renamed from: zzd */
        public zznm zza(Context context, Looper looper, zzg zzg, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zznm(context, looper, zzg, connectionCallbacks, onConnectionFailedListener);
        }
    }

    public class zza {
    }

    public interface zzb {
        byte[] zzqP();
    }

    public static class zzc {
    }
}
