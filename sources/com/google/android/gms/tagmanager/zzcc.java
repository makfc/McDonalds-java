package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

abstract class zzcc extends zzch {
    public zzcc(String str) {
        super(str);
    }

    /* Access modifiers changed, original: protected */
    public boolean zza(zza zza, zza zza2, Map<String, zza> map) {
        zzdk zzh = zzdl.zzh(zza);
        zzdk zzh2 = zzdl.zzh(zza2);
        return (zzh == zzdl.zzKR() || zzh2 == zzdl.zzKR()) ? false : zza(zzh, zzh2, (Map) map);
    }

    public abstract boolean zza(zzdk zzdk, zzdk zzdk2, Map<String, zza> map);
}
