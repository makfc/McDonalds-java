package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.util.zze;

public class zzad {
    private final String zzVt;
    private final long zzYG;
    private final int zzYH;
    private double zzYI;
    private long zzYJ;
    private final Object zzYK;
    private final zze zzsd;

    public zzad(int i, long j, String str, zze zze) {
        this.zzYK = new Object();
        this.zzYH = i;
        this.zzYI = (double) this.zzYH;
        this.zzYG = j;
        this.zzVt = str;
        this.zzsd = zze;
    }

    public zzad(String str, zze zze) {
        this(60, 2000, str, zze);
    }

    public boolean zznY() {
        boolean z;
        synchronized (this.zzYK) {
            long currentTimeMillis = this.zzsd.currentTimeMillis();
            if (this.zzYI < ((double) this.zzYH)) {
                double d = ((double) (currentTimeMillis - this.zzYJ)) / ((double) this.zzYG);
                if (d > 0.0d) {
                    this.zzYI = Math.min((double) this.zzYH, d + this.zzYI);
                }
            }
            this.zzYJ = currentTimeMillis;
            if (this.zzYI >= 1.0d) {
                this.zzYI -= 1.0d;
                z = true;
            } else {
                String str = this.zzVt;
                zzae.zzaW(new StringBuilder(String.valueOf(str).length() + 34).append("Excessive ").append(str).append(" detected; call ignored.").toString());
                z = false;
            }
        }
        return z;
    }
}
