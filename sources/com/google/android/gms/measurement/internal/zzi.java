package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.zzaa;

class zzi {
    final String mName;
    final String zzPx;
    final long zzbck;
    final long zzbcl;
    final long zzbcm;

    zzi(String str, String str2, long j, long j2, long j3) {
        boolean z = true;
        zzaa.zzdl(str);
        zzaa.zzdl(str2);
        zzaa.zzaj(j >= 0);
        if (j2 < 0) {
            z = false;
        }
        zzaa.zzaj(z);
        this.zzPx = str;
        this.mName = str2;
        this.zzbck = j;
        this.zzbcl = j2;
        this.zzbcm = j3;
    }

    /* Access modifiers changed, original: 0000 */
    public zzi zzFA() {
        return new zzi(this.zzPx, this.mName, this.zzbck + 1, this.zzbcl + 1, this.zzbcm);
    }

    /* Access modifiers changed, original: 0000 */
    public zzi zzag(long j) {
        return new zzi(this.zzPx, this.mName, this.zzbck, this.zzbcl, j);
    }
}
