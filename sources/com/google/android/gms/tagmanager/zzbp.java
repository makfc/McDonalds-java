package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzbp extends zzal {
    /* renamed from: ID */
    private static final String f6494ID = zzad.LOWERCASE_STRING.toString();
    private static final String zzbpb = zzae.ARG0.toString();

    public zzbp() {
        super(f6494ID, zzbpb);
    }

    public boolean zzJf() {
        return true;
    }

    public zza zzV(Map<String, zza> map) {
        return zzdl.zzQ(zzdl.zzg((zza) map.get(zzbpb)).toLowerCase());
    }
}
