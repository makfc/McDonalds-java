package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.signin.internal.zzg;

public final class zztu {
    public static final Api<zztw> API = new Api("SignIn.API", zzaaA, zzaaz);
    public static final zzf<zzg> zzaCB = new zzf();
    public static final com.google.android.gms.common.api.Api.zza<zzg, zztw> zzaaA = new C22781();
    public static final zzf<zzg> zzaaz = new zzf();
    public static final Scope zzacw = new Scope("profile");
    public static final Scope zzacx = new Scope("email");
    public static final Api<zza> zzavg = new Api("SignIn.INTERNAL_API", zzbne, zzaCB);
    static final com.google.android.gms.common.api.Api.zza<zzg, zza> zzbne = new C22792();

    /* renamed from: com.google.android.gms.internal.zztu$1 */
    class C22781 extends com.google.android.gms.common.api.Api.zza<zzg, zztw> {
        C22781() {
        }

        public zzg zza(Context context, Looper looper, com.google.android.gms.common.internal.zzg zzg, zztw zztw, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzg(context, looper, true, zzg, zztw == null ? zztw.zzbnf : zztw, connectionCallbacks, onConnectionFailedListener);
        }
    }

    /* renamed from: com.google.android.gms.internal.zztu$2 */
    class C22792 extends com.google.android.gms.common.api.Api.zza<zzg, zza> {
        C22792() {
        }

        public zzg zza(Context context, Looper looper, com.google.android.gms.common.internal.zzg zzg, zza zza, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzg(context, looper, false, zzg, zza.zzIO(), connectionCallbacks, onConnectionFailedListener);
        }
    }

    public static class zza implements HasOptions {
        public Bundle zzIO() {
            return null;
        }
    }
}
