package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.zze;

class zzbl implements zzck {
    private final String zzVt;
    private final long zzYG;
    private final int zzYH;
    private double zzYI;
    private long zzYJ;
    private final Object zzYK = new Object();
    private final long zzbpE;
    private final zze zzsd;

    public zzbl(int i, long j, long j2, String str, zze zze) {
        this.zzYH = i;
        this.zzYI = (double) this.zzYH;
        this.zzYG = j;
        this.zzbpE = j2;
        this.zzVt = str;
        this.zzsd = zze;
    }

    public boolean zznY() {
        boolean z = false;
        synchronized (this.zzYK) {
            long currentTimeMillis = this.zzsd.currentTimeMillis();
            String str;
            if (currentTimeMillis - this.zzYJ < this.zzbpE) {
                str = this.zzVt;
                zzbn.zzaW(new StringBuilder(String.valueOf(str).length() + 34).append("Excessive ").append(str).append(" detected; call ignored.").toString());
            } else {
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
                    str = this.zzVt;
                    zzbn.zzaW(new StringBuilder(String.valueOf(str).length() + 34).append("Excessive ").append(str).append(" detected; call ignored.").toString());
                }
            }
        }
        return z;
    }
}
