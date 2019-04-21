package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzaj extends zzal {
    /* renamed from: ID */
    private static final String f6483ID = zzad.EVENT.toString();
    private final zzcw zzbnT;

    public zzaj(zzcw zzcw) {
        super(f6483ID, new String[0]);
        this.zzbnT = zzcw;
    }

    public boolean zzJf() {
        return false;
    }

    public zza zzV(Map<String, zza> map) {
        String zzKr = this.zzbnT.zzKr();
        return zzKr == null ? zzdl.zzKT() : zzdl.zzQ(zzKr);
    }
}
