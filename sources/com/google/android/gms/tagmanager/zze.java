package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zze extends zzal {
    /* renamed from: ID */
    private static final String f6509ID = zzad.ADWORDS_CLICK_REFERRER.toString();
    private static final String zzbnI = zzae.COMPONENT.toString();
    private static final String zzbnJ = zzae.CONVERSION_ID.toString();
    private final Context zzov;

    public zze(Context context) {
        super(f6509ID, zzbnJ);
        this.zzov = context;
    }

    public boolean zzJf() {
        return true;
    }

    public zza zzV(Map<String, zza> map) {
        zza zza = (zza) map.get(zzbnJ);
        if (zza == null) {
            return zzdl.zzKT();
        }
        String zzg = zzdl.zzg(zza);
        zza = (zza) map.get(zzbnI);
        String zzf = zzbe.zzf(this.zzov, zzg, zza != null ? zzdl.zzg(zza) : null);
        return zzf != null ? zzdl.zzQ(zzf) : zzdl.zzKT();
    }
}
