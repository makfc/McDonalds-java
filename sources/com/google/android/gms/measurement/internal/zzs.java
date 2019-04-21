package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzsn.zzd;

class zzs {
    double zzbdA;
    long zzbdB;
    double zzbdC;
    final boolean zzbdD;
    final boolean zzbdv;
    final int zzbdw;
    long zzbdx;
    double zzbdy;
    long zzbdz;

    public zzs(zzd zzd) {
        boolean z;
        boolean z2 = true;
        zzaa.zzz(zzd);
        if (zzd.zzbgw == null || zzd.zzbgw.intValue() == 0) {
            z = false;
        } else {
            if (zzd.zzbgw.intValue() != 4) {
                if (zzd.zzbgy == null) {
                    z = false;
                }
            } else if (zzd.zzbgz == null || zzd.zzbgA == null) {
                z = false;
            }
            z = true;
        }
        if (z) {
            this.zzbdw = zzd.zzbgw.intValue();
            if (zzd.zzbgx == null || !zzd.zzbgx.booleanValue()) {
                z2 = false;
            }
            this.zzbdv = z2;
            if (zzd.zzbgw.intValue() == 4) {
                if (this.zzbdv) {
                    this.zzbdA = Double.parseDouble(zzd.zzbgz);
                    this.zzbdC = Double.parseDouble(zzd.zzbgA);
                } else {
                    this.zzbdz = Long.parseLong(zzd.zzbgz);
                    this.zzbdB = Long.parseLong(zzd.zzbgA);
                }
            } else if (this.zzbdv) {
                this.zzbdy = Double.parseDouble(zzd.zzbgy);
            } else {
                this.zzbdx = Long.parseLong(zzd.zzbgy);
            }
        } else {
            this.zzbdw = 0;
            this.zzbdv = false;
        }
        this.zzbdD = z;
    }

    public Boolean zzah(long j) {
        boolean z = true;
        if (!this.zzbdD) {
            return null;
        }
        if (this.zzbdv) {
            return null;
        }
        switch (this.zzbdw) {
            case 1:
                if (j >= this.zzbdx) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 2:
                if (j <= this.zzbdx) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 3:
                if (j != this.zzbdx) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 4:
                if (j < this.zzbdz || j > this.zzbdB) {
                    z = false;
                }
                return Boolean.valueOf(z);
            default:
                return null;
        }
    }

    public Boolean zzk(double d) {
        boolean z = true;
        boolean z2 = false;
        if (!this.zzbdD) {
            return null;
        }
        if (!this.zzbdv) {
            return null;
        }
        switch (this.zzbdw) {
            case 1:
                if (d >= this.zzbdy) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 2:
                if (d <= this.zzbdy) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 3:
                if (d == this.zzbdy || Math.abs(d - this.zzbdy) < 2.0d * Math.max(Math.ulp(d), Math.ulp(this.zzbdy))) {
                    z2 = true;
                }
                return Boolean.valueOf(z2);
            case 4:
                if (d < this.zzbdA || d > this.zzbdC) {
                    z = false;
                }
                return Boolean.valueOf(z);
            default:
                return null;
        }
    }
}
