package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;

class zzm<K, V> {
    final zza<K, V> zzbnP = new C27391();

    public interface zza<K, V> {
        int sizeOf(K k, V v);
    }

    /* renamed from: com.google.android.gms.tagmanager.zzm$1 */
    class C27391 implements zza<K, V> {
        C27391() {
        }

        public int sizeOf(K k, V v) {
            return 1;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int zzJh() {
        return VERSION.SDK_INT;
    }

    public zzl<K, V> zza(int i, zza<K, V> zza) {
        if (i > 0) {
            return zzJh() < 12 ? new zzdd(i, zza) : new zzbh(i, zza);
        } else {
            throw new IllegalArgumentException("maxSize <= 0");
        }
    }
}
