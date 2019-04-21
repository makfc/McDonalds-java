package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.util.LruCache;
import com.google.android.gms.tagmanager.zzm.zza;

@TargetApi(12)
class zzbh<K, V> implements zzl<K, V> {
    private LruCache<K, V> zzbpB;

    zzbh(int i, final zza<K, V> zza) {
        this.zzbpB = new LruCache<K, V>(i) {
            /* Access modifiers changed, original: protected */
            public int sizeOf(K k, V v) {
                return zza.sizeOf(k, v);
            }
        };
    }

    public V get(K k) {
        return this.zzbpB.get(k);
    }

    public void zzi(K k, V v) {
        this.zzbpB.put(k, v);
    }
}
