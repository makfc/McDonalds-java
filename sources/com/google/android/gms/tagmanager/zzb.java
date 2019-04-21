package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzb extends zzal {
    /* renamed from: ID */
    private static final String f6488ID = zzad.ADVERTISER_ID.toString();
    private final zza zzbnH;

    public zzb(Context context) {
        this(zza.zzbg(context));
    }

    zzb(zza zza) {
        super(f6488ID, new String[0]);
        this.zzbnH = zza;
    }

    public boolean zzJf() {
        return false;
    }

    public zza zzV(Map<String, zza> map) {
        String zzIZ = this.zzbnH.zzIZ();
        return zzIZ == null ? zzdl.zzKT() : zzdl.zzQ(zzIZ);
    }
}
