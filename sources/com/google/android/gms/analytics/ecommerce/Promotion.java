package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.analytics.zzg;
import com.google.android.gms.common.internal.zzaa;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Promotion {
    Map<String, String> zzVR = new HashMap();

    /* Access modifiers changed, original: 0000 */
    public void put(String str, String str2) {
        zzaa.zzb((Object) str, (Object) "Name should be non-null");
        this.zzVR.put(str, str2);
    }

    public Promotion setCreative(String str) {
        put("cr", str);
        return this;
    }

    public Promotion setId(String str) {
        put("id", str);
        return this;
    }

    public Promotion setName(String str) {
        put("nm", str);
        return this;
    }

    public Promotion setPosition(String str) {
        put("ps", str);
        return this;
    }

    public String toString() {
        return zzg.zzO(this.zzVR);
    }

    public Map<String, String> zzbD(String str) {
        HashMap hashMap = new HashMap();
        for (Entry entry : this.zzVR.entrySet()) {
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf((String) entry.getKey());
            hashMap.put(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), (String) entry.getValue());
        }
        return hashMap;
    }
}
