package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;
import java.util.Set;

public abstract class zzch extends zzal {
    private static final String zzbpZ = zzae.ARG1.toString();
    private static final String zzbpb = zzae.ARG0.toString();

    public zzch(String str) {
        super(str, zzbpb, zzbpZ);
    }

    public /* bridge */ /* synthetic */ String zzJO() {
        return super.zzJO();
    }

    public /* bridge */ /* synthetic */ Set zzJP() {
        return super.zzJP();
    }

    public boolean zzJf() {
        return true;
    }

    public zza zzV(Map<String, zza> map) {
        for (zza zza : map.values()) {
            if (zza == zzdl.zzKT()) {
                return zzdl.zzQ(Boolean.valueOf(false));
            }
        }
        zza zza2 = (zza) map.get(zzbpb);
        zza zza3 = (zza) map.get(zzbpZ);
        boolean zza4 = (zza2 == null || zza3 == null) ? false : zza(zza2, zza3, map);
        return zzdl.zzQ(Boolean.valueOf(zza4));
    }

    public abstract boolean zza(zza zza, zza zza2, Map<String, zza> map);
}
