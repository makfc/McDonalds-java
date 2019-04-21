package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzbd extends zzal {
    /* renamed from: ID */
    private static final String f6489ID = zzad.INSTALL_REFERRER.toString();
    private static final String zzbnI = zzae.COMPONENT.toString();
    private final Context zzov;

    public zzbd(Context context) {
        super(f6489ID, new String[0]);
        this.zzov = context;
    }

    public boolean zzJf() {
        return true;
    }

    public zza zzV(Map<String, zza> map) {
        String zzs = zzbe.zzs(this.zzov, ((zza) map.get(zzbnI)) != null ? zzdl.zzg((zza) map.get(zzbnI)) : null);
        return zzs != null ? zzdl.zzQ(zzs) : zzdl.zzKT();
    }
}
