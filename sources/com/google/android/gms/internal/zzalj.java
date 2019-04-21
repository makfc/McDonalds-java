package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public final class zzalj implements zzaks {
    private final zzakz zzbWa;

    private static final class zza<E> extends zzakr<Collection<E>> {
        private final zzakr<E> zzbXG;
        private final zzale<? extends Collection<E>> zzbXH;

        public zza(zzajz zzajz, Type type, zzakr<E> zzakr, zzale<? extends Collection<E>> zzale) {
            this.zzbXG = new zzalt(zzajz, zzakr, type);
            this.zzbXH = zzale;
        }

        public void zza(zzaly zzaly, Collection<E> collection) throws IOException {
            if (collection == null) {
                zzaly.zzWk();
                return;
            }
            zzaly.zzWg();
            for (E zza : collection) {
                this.zzbXG.zza(zzaly, zza);
            }
            zzaly.zzWh();
        }
    }

    public zzalj(zzakz zzakz) {
        this.zzbWa = zzakz;
    }

    public <T> zzakr<T> zza(zzajz zzajz, zzalv<T> zzalv) {
        Type zzWm = zzalv.zzWm();
        Class zzWl = zzalv.zzWl();
        if (!Collection.class.isAssignableFrom(zzWl)) {
            return null;
        }
        Type zza = zzaky.zza(zzWm, zzWl);
        return new zza(zzajz, zza, zzajz.zza(zzalv.zzl(zza)), this.zzbWa.zzb(zzalv));
    }
}
