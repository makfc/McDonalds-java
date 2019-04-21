package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;

class zzcz implements zzck {
    private final long zzYG;
    private final int zzYH;
    private double zzYI;
    private final Object zzYK;
    private long zzbqY;
    private final zze zzsd;

    public zzcz() {
        this(60, 2000);
    }

    public zzcz(int i, long j) {
        this.zzYK = new Object();
        this.zzYH = i;
        this.zzYI = (double) this.zzYH;
        this.zzYG = j;
        this.zzsd = zzh.zzuW();
    }

    public boolean zznY() {
        boolean z;
        synchronized (this.zzYK) {
            long currentTimeMillis = this.zzsd.currentTimeMillis();
            if (this.zzYI < ((double) this.zzYH)) {
                double d = ((double) (currentTimeMillis - this.zzbqY)) / ((double) this.zzYG);
                if (d > 0.0d) {
                    this.zzYI = Math.min((double) this.zzYH, d + this.zzYI);
                }
            }
            this.zzbqY = currentTimeMillis;
            if (this.zzYI >= 1.0d) {
                this.zzYI -= 1.0d;
                z = true;
            } else {
                zzbn.zzaW("No more tokens available.");
                z = false;
            }
        }
        return z;
    }
}
