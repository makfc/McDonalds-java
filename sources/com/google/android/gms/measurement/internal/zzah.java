package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zze;

class zzah {
    private long zzFn;
    private final zze zzsd;

    public zzah(zze zze) {
        zzaa.zzz(zze);
        this.zzsd = zze;
    }

    public void clear() {
        this.zzFn = 0;
    }

    public void start() {
        this.zzFn = this.zzsd.elapsedRealtime();
    }

    public boolean zzx(long j) {
        return this.zzFn == 0 || this.zzsd.elapsedRealtime() - this.zzFn >= j;
    }
}
