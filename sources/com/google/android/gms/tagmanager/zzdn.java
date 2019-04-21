package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzdn extends zzal {
    /* renamed from: ID */
    private static final String f6508ID = zzad.UPPERCASE_STRING.toString();
    private static final String zzbpb = zzae.ARG0.toString();

    public zzdn() {
        super(f6508ID, zzbpb);
    }

    public boolean zzJf() {
        return true;
    }

    public zza zzV(Map<String, zza> map) {
        return zzdl.zzQ(zzdl.zzg((zza) map.get(zzbpb)).toUpperCase());
    }
}
