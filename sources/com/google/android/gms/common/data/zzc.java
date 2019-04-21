package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzz;

public abstract class zzc {
    protected final DataHolder zzamz;
    protected int zzapa;
    private int zzapb;

    public zzc(DataHolder dataHolder, int i) {
        this.zzamz = (DataHolder) zzaa.zzz(dataHolder);
        zzbN(i);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzc)) {
            return false;
        }
        zzc zzc = (zzc) obj;
        return zzz.equal(Integer.valueOf(zzc.zzapa), Integer.valueOf(this.zzapa)) && zzz.equal(Integer.valueOf(zzc.zzapb), Integer.valueOf(this.zzapb)) && zzc.zzamz == this.zzamz;
    }

    /* Access modifiers changed, original: protected */
    public String getString(String str) {
        return this.zzamz.zzd(str, this.zzapa, this.zzapb);
    }

    public int hashCode() {
        return zzz.hashCode(Integer.valueOf(this.zzapa), Integer.valueOf(this.zzapb), this.zzamz);
    }

    /* Access modifiers changed, original: protected */
    public void zzbN(int i) {
        boolean z = i >= 0 && i < this.zzamz.getCount();
        zzaa.zzai(z);
        this.zzapa = i;
        this.zzapb = this.zzamz.zzbP(this.zzapa);
    }

    public boolean zzcY(String str) {
        return this.zzamz.zzcY(str);
    }

    /* Access modifiers changed, original: protected */
    public boolean zzda(String str) {
        return this.zzamz.zzi(str, this.zzapa, this.zzapb);
    }
}
