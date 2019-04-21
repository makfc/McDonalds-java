package com.google.android.gms.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.internal.zznt.zza;

public class zzok<O extends ApiOptions> extends zzob {
    private final zzc<O> zzaoe;

    public Looper getLooper() {
        return this.zzaoe.getLooper();
    }

    public void zza(zzpe zzpe) {
        this.zzaoe.zzrj();
    }

    public void zzb(zzpe zzpe) {
        this.zzaoe.zzrk();
    }

    public <A extends zzb, R extends Result, T extends zza<R, A>> T zzc(@NonNull T t) {
        return this.zzaoe.zza(t);
    }

    public <A extends zzb, T extends zza<? extends Result, A>> T zzd(@NonNull T t) {
        return this.zzaoe.zzb(t);
    }
}
