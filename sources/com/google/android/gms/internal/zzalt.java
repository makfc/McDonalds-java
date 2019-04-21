package com.google.android.gms.internal;

import com.google.android.gms.internal.zzalq.zza;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class zzalt<T> extends zzakr<T> {
    private final zzakr<T> zzbWi;
    private final zzajz zzbYe;
    private final Type zzbYf;

    zzalt(zzajz zzajz, zzakr<T> zzakr, Type type) {
        this.zzbYe = zzajz;
        this.zzbWi = zzakr;
        this.zzbYf = type;
    }

    private Type zzb(Type type, Object obj) {
        return obj != null ? (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type : type;
    }

    public void zza(zzaly zzaly, T t) throws IOException {
        zzakr zzakr = this.zzbWi;
        Type zzb = zzb(this.zzbYf, t);
        if (zzb != this.zzbYf) {
            zzakr = this.zzbYe.zza(zzalv.zzl(zzb));
            if ((zzakr instanceof zza) && !(this.zzbWi instanceof zza)) {
                zzakr = this.zzbWi;
            }
        }
        zzakr.zza(zzaly, t);
    }
}
