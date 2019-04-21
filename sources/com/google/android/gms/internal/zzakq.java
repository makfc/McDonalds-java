package com.google.android.gms.internal;

import java.io.IOException;

final class zzakq<T> extends zzakr<T> {
    private final zzalv<T> zzbWA;
    private final zzaks zzbWB;
    private zzakr<T> zzbWi;
    private final zzakn<T> zzbWx;
    private final zzake<T> zzbWy;
    private final zzajz zzbWz;

    private static class zza implements zzaks {
        private final zzalv<?> zzbWC;
        private final boolean zzbWD;
        private final Class<?> zzbWE;
        private final zzakn<?> zzbWx;
        private final zzake<?> zzbWy;

        public <T> zzakr<T> zza(zzajz zzajz, zzalv<T> zzalv) {
            boolean isAssignableFrom = this.zzbWC != null ? this.zzbWC.equals(zzalv) || (this.zzbWD && this.zzbWC.zzWm() == zzalv.zzWl()) : this.zzbWE.isAssignableFrom(zzalv.zzWl());
            return isAssignableFrom ? new zzakq(this.zzbWx, this.zzbWy, zzajz, zzalv, this) : null;
        }
    }

    private zzakq(zzakn<T> zzakn, zzake<T> zzake, zzajz zzajz, zzalv<T> zzalv, zzaks zzaks) {
        this.zzbWx = zzakn;
        this.zzbWy = zzake;
        this.zzbWz = zzajz;
        this.zzbWA = zzalv;
        this.zzbWB = zzaks;
    }

    private zzakr<T> zzVQ() {
        zzakr zzakr = this.zzbWi;
        if (zzakr != null) {
            return zzakr;
        }
        zzakr = this.zzbWz.zza(this.zzbWB, this.zzbWA);
        this.zzbWi = zzakr;
        return zzakr;
    }

    public void zza(zzaly zzaly, T t) throws IOException {
        if (this.zzbWx == null) {
            zzVQ().zza(zzaly, t);
        } else if (t == null) {
            zzaly.zzWk();
        } else {
            zzalg.zzb(this.zzbWx.zza(t, this.zzbWA.zzWm(), this.zzbWz.zzbWg), zzaly);
        }
    }
}
