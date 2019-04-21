package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

abstract class zzdj extends zzal {
    public zzdj(String str, String... strArr) {
        super(str, strArr);
    }

    public boolean zzJf() {
        return false;
    }

    public zza zzV(Map<String, zza> map) {
        zzX(map);
        return zzdl.zzKT();
    }

    public abstract void zzX(Map<String, zza> map);
}
