package com.google.android.gms.tagmanager;

import com.google.android.gms.tagmanager.zzm.zza;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

class zzdd<K, V> implements zzl<K, V> {
    private final Map<K, V> zzbrp = new HashMap();
    private final int zzbrq;
    private final zza<K, V> zzbrr;
    private int zzbrs;

    zzdd(int i, zza<K, V> zza) {
        this.zzbrq = i;
        this.zzbrr = zza;
    }

    public synchronized V get(K k) {
        return this.zzbrp.get(k);
    }

    public synchronized void zzi(K k, V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        this.zzbrs += this.zzbrr.sizeOf(k, v);
        if (this.zzbrs > this.zzbrq) {
            Iterator it = this.zzbrp.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                this.zzbrs -= this.zzbrr.sizeOf(entry.getKey(), entry.getValue());
                it.remove();
                if (this.zzbrs <= this.zzbrq) {
                    break;
                }
            }
        }
        this.zzbrp.put(k, v);
    }
}
