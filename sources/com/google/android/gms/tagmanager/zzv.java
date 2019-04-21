package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzv extends zzal {
    /* renamed from: ID */
    private static final String f6519ID = zzad.CUSTOM_VAR.toString();
    private static final String NAME = zzae.NAME.toString();
    private static final String zzboF = zzae.DEFAULT_VALUE.toString();
    private final DataLayer zzbnS;

    public zzv(DataLayer dataLayer) {
        super(f6519ID, NAME);
        this.zzbnS = dataLayer;
    }

    public boolean zzJf() {
        return false;
    }

    public zza zzV(Map<String, zza> map) {
        Object obj = this.zzbnS.get(zzdl.zzg((zza) map.get(NAME)));
        if (obj != null) {
            return zzdl.zzQ(obj);
        }
        zza zza = (zza) map.get(zzboF);
        return zza != null ? zza : zzdl.zzKT();
    }
}
