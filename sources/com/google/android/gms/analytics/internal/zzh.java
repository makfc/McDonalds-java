package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzaa;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzh {
    private final Map<String, String> zzAd;
    private final String zzVz;
    private final String zzWA;
    private final boolean zzWB;
    private long zzWC;
    private final long zzWz;

    public zzh(long j, String str, String str2, boolean z, long j2, Map<String, String> map) {
        zzaa.zzdl(str);
        zzaa.zzdl(str2);
        this.zzWz = j;
        this.zzVz = str;
        this.zzWA = str2;
        this.zzWB = z;
        this.zzWC = j2;
        if (map != null) {
            this.zzAd = new HashMap(map);
        } else {
            this.zzAd = Collections.emptyMap();
        }
    }

    public String zzku() {
        return this.zzVz;
    }

    public Map<String, String> zzm() {
        return this.zzAd;
    }

    public long zzmi() {
        return this.zzWz;
    }

    public String zzmj() {
        return this.zzWA;
    }

    public boolean zzmk() {
        return this.zzWB;
    }

    public long zzml() {
        return this.zzWC;
    }

    public void zzp(long j) {
        this.zzWC = j;
    }
}
