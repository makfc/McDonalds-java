package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

public final class zzali<E> extends zzakr<Object> {
    public static final zzaks zzbXD = new C21871();
    private final Class<E> zzbXE;
    private final zzakr<E> zzbXF;

    /* renamed from: com.google.android.gms.internal.zzali$1 */
    static class C21871 implements zzaks {
        C21871() {
        }

        public <T> zzakr<T> zza(zzajz zzajz, zzalv<T> zzalv) {
            Type zzWm = zzalv.zzWm();
            if (!(zzWm instanceof GenericArrayType) && (!(zzWm instanceof Class) || !((Class) zzWm).isArray())) {
                return null;
            }
            zzWm = zzaky.zzh(zzWm);
            return new zzali(zzajz, zzajz.zza(zzalv.zzl(zzWm)), zzaky.zzf(zzWm));
        }
    }

    public zzali(zzajz zzajz, zzakr<E> zzakr, Class<E> cls) {
        this.zzbXF = new zzalt(zzajz, zzakr, cls);
        this.zzbXE = cls;
    }

    public void zza(zzaly zzaly, Object obj) throws IOException {
        if (obj == null) {
            zzaly.zzWk();
            return;
        }
        zzaly.zzWg();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.zzbXF.zza(zzaly, Array.get(obj, i));
        }
        zzaly.zzWh();
    }
}
