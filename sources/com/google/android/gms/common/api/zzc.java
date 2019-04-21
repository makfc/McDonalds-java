package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.internal.zznq;
import com.google.android.gms.internal.zznt.zza;
import com.google.android.gms.internal.zzoj;
import com.google.android.gms.internal.zzov;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzc<O extends ApiOptions> {
    private final Context mContext;
    private final int mId;
    private final zzov zzakS;
    private final Api<O> zzakT;
    private final O zzakU;
    private final zznq<O> zzakV;
    private final Looper zzakW;
    private final zzoj zzakX;
    private final AtomicBoolean zzakZ;
    private final AtomicInteger zzala;

    private <A extends zzb, T extends zza<? extends Result, A>> T zza(int i, @NonNull T t) {
        t.zzrI();
        this.zzakX.zza(this, i, t);
        return t;
    }

    public Context getApplicationContext() {
        return this.mContext;
    }

    public int getInstanceId() {
        return this.mId;
    }

    public Looper getLooper() {
        return this.zzakW;
    }

    public void release() {
        boolean z = true;
        if (!this.zzakZ.getAndSet(true)) {
            this.zzakS.release();
            zzoj zzoj = this.zzakX;
            int i = this.mId;
            if (this.zzala.get() <= 0) {
                z = false;
            }
            zzoj.zzd(i, z);
        }
    }

    public <A extends zzb, T extends zza<? extends Result, A>> T zza(@NonNull T t) {
        return zza(0, t);
    }

    public <A extends zzb, T extends zza<? extends Result, A>> T zzb(@NonNull T t) {
        return zza(1, t);
    }

    public void zzrj() {
        this.zzala.incrementAndGet();
    }

    public void zzrk() {
        if (this.zzala.decrementAndGet() == 0 && this.zzakZ.get()) {
            this.zzakX.zzd(this.mId, false);
        }
    }

    public Api<O> zzrl() {
        return this.zzakT;
    }

    public O zzrm() {
        return this.zzakU;
    }

    public zznq<O> zzrn() {
        return this.zzakV;
    }
}
