package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

abstract class zzdg extends zzch {
    public zzdg(String str) {
        super(str);
    }

    /* Access modifiers changed, original: protected */
    public boolean zza(zza zza, zza zza2, Map<String, zza> map) {
        String zzg = zzdl.zzg(zza);
        String zzg2 = zzdl.zzg(zza2);
        return (zzg == zzdl.zzKS() || zzg2 == zzdl.zzKS()) ? false : zza(zzg, zzg2, (Map) map);
    }

    public abstract boolean zza(String str, String str2, Map<String, zza> map);
}
