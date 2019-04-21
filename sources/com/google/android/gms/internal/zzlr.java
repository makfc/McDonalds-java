package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzg;
import com.google.android.gms.common.internal.zzaa;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzlr extends zzg<zzlr> {
    private final Map<String, Object> zzAd = new HashMap();

    private String zzbr(String str) {
        zzaa.zzdl(str);
        if (str != null && str.startsWith("&")) {
            str = str.substring(1);
        }
        zzaa.zzh(str, "Name can not be empty or \"&\"");
        return str;
    }

    public void set(String str, String str2) {
        this.zzAd.put(zzbr(str), str2);
    }

    public String toString() {
        return zzg.zzj(this.zzAd);
    }

    /* renamed from: zza */
    public void zzb(zzlr zzlr) {
        zzaa.zzz(zzlr);
        zzlr.zzAd.putAll(this.zzAd);
    }

    public Map<String, Object> zzlf() {
        return Collections.unmodifiableMap(this.zzAd);
    }
}
