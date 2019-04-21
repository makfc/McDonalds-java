package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zze;

class zzal {
    private long zzFn;
    private final zze zzsd;

    public zzal(zze zze) {
        zzaa.zzz(zze);
        this.zzsd = zze;
    }

    public zzal(zze zze, long j) {
        zzaa.zzz(zze);
        this.zzsd = zze;
        this.zzFn = j;
    }

    public void clear() {
        this.zzFn = 0;
    }

    public void start() {
        this.zzFn = this.zzsd.elapsedRealtime();
    }

    public boolean zzx(long j) {
        return this.zzFn == 0 || this.zzsd.elapsedRealtime() - this.zzFn > j;
    }
}
