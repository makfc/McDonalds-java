package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzamd<M extends zzamd<M>> extends zzamj {
    protected zzamf zzcaa;

    public M clone() throws CloneNotSupportedException {
        zzamd zzamd = (zzamd) super.clone();
        zzamh.zza(this, zzamd);
        return zzamd;
    }

    public final <T> T getExtension(zzame<M, T> zzame) {
        if (this.zzcaa == null) {
            return null;
        }
        zzamg zzok = this.zzcaa.zzok(zzamm.zzoo(zzame.tag));
        return zzok != null ? zzok.zza((zzame) zzame) : null;
    }

    public void writeTo(zzamc zzamc) throws IOException {
        if (this.zzcaa != null) {
            for (int i = 0; i < this.zzcaa.size(); i++) {
                this.zzcaa.zzol(i).writeTo(zzamc);
            }
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final boolean zza(zzamb zzamb, int i) throws IOException {
        int position = zzamb.getPosition();
        if (!zzamb.zznU(i)) {
            return false;
        }
        int zzoo = zzamm.zzoo(i);
        zzaml zzaml = new zzaml(i, zzamb.zzD(position, zzamb.getPosition() - position));
        zzamg zzamg = null;
        if (this.zzcaa == null) {
            this.zzcaa = new zzamf();
        } else {
            zzamg = this.zzcaa.zzok(zzoo);
        }
        if (zzamg == null) {
            zzamg = new zzamg();
            this.zzcaa.zza(zzoo, zzamg);
        }
        zzamg.zza(zzaml);
        return true;
    }

    /* Access modifiers changed, original: protected */
    public int zzy() {
        int i = 0;
        if (this.zzcaa == null) {
            return 0;
        }
        int i2 = 0;
        while (i < this.zzcaa.size()) {
            i2 += this.zzcaa.zzol(i).zzy();
            i++;
        }
        return i2;
    }
}
