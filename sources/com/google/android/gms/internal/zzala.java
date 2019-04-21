package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public final class zzala implements zzaks, Cloneable {
    public static final zzala zzbWT = new zzala();
    private double zzbWU = -1.0d;
    private int zzbWV = 136;
    private boolean zzbWW = true;
    private List<zzajv> zzbWX = Collections.emptyList();
    private List<zzajv> zzbWY = Collections.emptyList();

    private boolean zza(zzakv zzakv) {
        return zzakv == null || zzakv.zzVS() <= this.zzbWU;
    }

    private boolean zza(zzakv zzakv, zzakw zzakw) {
        return zza(zzakv) && zza(zzakw);
    }

    private boolean zza(zzakw zzakw) {
        return zzakw == null || zzakw.zzVS() > this.zzbWU;
    }

    private boolean zzm(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private boolean zzn(Class<?> cls) {
        return cls.isMemberClass() && !zzo(cls);
    }

    private boolean zzo(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: zzVU */
    public zzala clone() {
        try {
            return (zzala) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public <T> zzakr<T> zza(zzajz zzajz, zzalv<T> zzalv) {
        Class zzWl = zzalv.zzWl();
        final boolean zza = zza(zzWl, true);
        final boolean zza2 = zza(zzWl, false);
        if (!zza && !zza2) {
            return null;
        }
        final zzajz zzajz2 = zzajz;
        final zzalv<T> zzalv2 = zzalv;
        return new zzakr<T>() {
            private zzakr<T> zzbWi;

            private zzakr<T> zzVQ() {
                zzakr zzakr = this.zzbWi;
                if (zzakr != null) {
                    return zzakr;
                }
                zzakr = zzajz2.zza(zzala.this, zzalv2);
                this.zzbWi = zzakr;
                return zzakr;
            }

            public void zza(zzaly zzaly, T t) throws IOException {
                if (zza) {
                    zzaly.zzWk();
                } else {
                    zzVQ().zza(zzaly, t);
                }
            }
        };
    }

    public boolean zza(Class<?> cls, boolean z) {
        if (this.zzbWU != -1.0d && !zza((zzakv) cls.getAnnotation(zzakv.class), (zzakw) cls.getAnnotation(zzakw.class))) {
            return true;
        }
        if (!this.zzbWW && zzn(cls)) {
            return true;
        }
        if (zzm(cls)) {
            return true;
        }
        for (zzajv zzh : z ? this.zzbWX : this.zzbWY) {
            if (zzh.zzh(cls)) {
                return true;
            }
        }
        return false;
    }

    public boolean zza(Field field, boolean z) {
        if ((this.zzbWV & field.getModifiers()) != 0) {
            return true;
        }
        if (this.zzbWU != -1.0d && !zza((zzakv) field.getAnnotation(zzakv.class), (zzakw) field.getAnnotation(zzakw.class))) {
            return true;
        }
        if (field.isSynthetic()) {
            return true;
        }
        if (!this.zzbWW && zzn(field.getType())) {
            return true;
        }
        if (zzm(field.getType())) {
            return true;
        }
        List<zzajv> list = z ? this.zzbWX : this.zzbWY;
        if (!list.isEmpty()) {
            zzajw zzajw = new zzajw(field);
            for (zzajv zza : list) {
                if (zza.zza(zzajw)) {
                    return true;
                }
            }
        }
        return false;
    }
}
