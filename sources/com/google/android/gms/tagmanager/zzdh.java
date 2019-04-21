package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzdh extends zzal {
    /* renamed from: ID */
    private static final String f6506ID = zzad.TIME.toString();

    public zzdh() {
        super(f6506ID, new String[0]);
    }

    public boolean zzJf() {
        return false;
    }

    public zza zzV(Map<String, zza> map) {
        return zzdl.zzQ(Long.valueOf(System.currentTimeMillis()));
    }
}
