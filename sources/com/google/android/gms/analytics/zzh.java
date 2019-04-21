package com.google.android.gms.analytics;

import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zze;
import java.util.ArrayList;
import java.util.List;

public abstract class zzh<T extends zzh> {
    private final zzi zzUw;
    protected final zze zzUx;
    private final List<zzf> zzUy = new ArrayList();

    protected zzh(zzi zzi, zze zze) {
        zzaa.zzz(zzi);
        this.zzUw = zzi;
        zze zze2 = new zze(this, zze);
        zze2.zzkI();
        this.zzUx = zze2;
    }

    /* Access modifiers changed, original: protected */
    public void zza(zze zze) {
    }

    /* Access modifiers changed, original: protected */
    public void zzd(zze zze) {
        for (zzf zza : this.zzUy) {
            zza.zza(this, zze);
        }
    }

    /* Access modifiers changed, original: protected */
    public zzi zzkG() {
        return this.zzUw;
    }

    public zze zzkJ() {
        return this.zzUx;
    }

    public List<zzk> zzkK() {
        return this.zzUx.zzkA();
    }

    public zze zzkk() {
        zze zzky = this.zzUx.zzky();
        zzd(zzky);
        return zzky;
    }
}
