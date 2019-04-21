package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.List;
import java.util.Map;

class zzx extends zzdj {
    /* renamed from: ID */
    private static final String f6520ID = zzad.DATA_LAYER_WRITE.toString();
    private static final String VALUE = zzae.VALUE.toString();
    private static final String zzboQ = zzae.CLEAR_PERSISTENT_DATA_LAYER_PREFIX.toString();
    private final DataLayer zzbnS;

    public zzx(DataLayer dataLayer) {
        super(f6520ID, VALUE);
        this.zzbnS = dataLayer;
    }

    private void zza(zza zza) {
        if (zza != null && zza != zzdl.zzKN()) {
            String zzg = zzdl.zzg(zza);
            if (zzg != zzdl.zzKS()) {
                this.zzbnS.zzgr(zzg);
            }
        }
    }

    private void zzb(zza zza) {
        if (zza != null && zza != zzdl.zzKN()) {
            Object zzl = zzdl.zzl(zza);
            if (zzl instanceof List) {
                for (Object zzl2 : (List) zzl2) {
                    if (zzl2 instanceof Map) {
                        this.zzbnS.push((Map) zzl2);
                    }
                }
            }
        }
    }

    public void zzX(Map<String, zza> map) {
        zzb((zza) map.get(VALUE));
        zza((zza) map.get(zzboQ));
    }
}
