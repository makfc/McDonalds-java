package com.google.android.gms.internal;

import java.util.Map.Entry;
import java.util.Set;

public final class zzaki extends zzakf {
    private final zzald<String, zzakf> zzbWs = new zzald();

    public Set<Entry<String, zzakf>> entrySet() {
        return this.zzbWs.entrySet();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof zzaki) && ((zzaki) obj).zzbWs.equals(this.zzbWs));
    }

    public int hashCode() {
        return this.zzbWs.hashCode();
    }

    public void zza(String str, zzakf zzakf) {
        Object zzakf2;
        if (zzakf2 == null) {
            zzakf2 = zzakh.zzbWr;
        }
        this.zzbWs.put(str, zzakf2);
    }
}
