package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzcj extends zzal {
    /* renamed from: ID */
    private static final String f6499ID = zzad.RANDOM.toString();
    private static final String zzbqj = zzae.MIN.toString();
    private static final String zzbqk = zzae.MAX.toString();

    public zzcj() {
        super(f6499ID, new String[0]);
    }

    public boolean zzJf() {
        return false;
    }

    public zza zzV(Map<String, zza> map) {
        double doubleValue;
        double d;
        zza zza = (zza) map.get(zzbqj);
        zza zza2 = (zza) map.get(zzbqk);
        if (!(zza == null || zza == zzdl.zzKT() || zza2 == null || zza2 == zzdl.zzKT())) {
            zzdk zzh = zzdl.zzh(zza);
            zzdk zzh2 = zzdl.zzh(zza2);
            if (!(zzh == zzdl.zzKR() || zzh2 == zzdl.zzKR())) {
                double doubleValue2 = zzh.doubleValue();
                doubleValue = zzh2.doubleValue();
                if (doubleValue2 <= doubleValue) {
                    d = doubleValue2;
                    return zzdl.zzQ(Long.valueOf(Math.round(((doubleValue - d) * Math.random()) + d)));
                }
            }
        }
        doubleValue = 2.147483647E9d;
        d = 0.0d;
        return zzdl.zzQ(Long.valueOf(Math.round(((doubleValue - d) * Math.random()) + d)));
    }
}
