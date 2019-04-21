package com.google.android.gms.internal;

import java.io.IOException;

public final class zzalp extends zzakr<Object> {
    public static final zzaks zzbXD = new C21921();
    private final zzajz zzbWz;

    /* renamed from: com.google.android.gms.internal.zzalp$1 */
    static class C21921 implements zzaks {
        C21921() {
        }

        public <T> zzakr<T> zza(zzajz zzajz, zzalv<T> zzalv) {
            return zzalv.zzWl() == Object.class ? new zzalp(zzajz, null) : null;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzalp$2 */
    static /* synthetic */ class C21932 {
        static final /* synthetic */ int[] zzbXT = new int[zzalx.values().length];

        static {
            try {
                zzbXT[zzalx.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                zzbXT[zzalx.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                zzbXT[zzalx.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                zzbXT[zzalx.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                zzbXT[zzalx.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                zzbXT[zzalx.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    private zzalp(zzajz zzajz) {
        this.zzbWz = zzajz;
    }

    /* synthetic */ zzalp(zzajz zzajz, C21921 c21921) {
        this(zzajz);
    }

    public void zza(zzaly zzaly, Object obj) throws IOException {
        if (obj == null) {
            zzaly.zzWk();
            return;
        }
        zzakr zzk = this.zzbWz.zzk(obj.getClass());
        if (zzk instanceof zzalp) {
            zzaly.zzWi();
            zzaly.zzWj();
            return;
        }
        zzk.zza(zzaly, obj);
    }
}
