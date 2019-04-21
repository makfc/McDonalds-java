package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzg;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class zzlp extends zzg<zzlp> {
    private Map<Integer, String> zzVn = new HashMap(4);

    public String toString() {
        HashMap hashMap = new HashMap();
        for (Entry entry : this.zzVn.entrySet()) {
            String valueOf = String.valueOf(entry.getKey());
            hashMap.put(new StringBuilder(String.valueOf(valueOf).length() + 9).append("dimension").append(valueOf).toString(), entry.getValue());
        }
        return zzg.zzj(hashMap);
    }

    /* renamed from: zza */
    public void zzb(zzlp zzlp) {
        zzlp.zzVn.putAll(this.zzVn);
    }

    public Map<Integer, String> zzld() {
        return Collections.unmodifiableMap(this.zzVn);
    }
}
