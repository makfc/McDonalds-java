package com.google.android.gms.internal;

public class zzm<T> {
    public final T result;
    public final com.google.android.gms.internal.zzb.zza zzaf;
    public final zzr zzag;
    public boolean zzah;

    public interface zza {
        void zze(zzr zzr);
    }

    public interface zzb<T> {
        void zzb(T t);
    }

    private zzm(zzr zzr) {
        this.zzah = false;
        this.result = null;
        this.zzaf = null;
        this.zzag = zzr;
    }

    private zzm(T t, com.google.android.gms.internal.zzb.zza zza) {
        this.zzah = false;
        this.result = t;
        this.zzaf = zza;
        this.zzag = null;
    }

    public static <T> zzm<T> zza(T t, com.google.android.gms.internal.zzb.zza zza) {
        return new zzm(t, zza);
    }

    public static <T> zzm<T> zzd(zzr zzr) {
        return new zzm(zzr);
    }

    public boolean isSuccess() {
        return this.zzag == null;
    }
}
