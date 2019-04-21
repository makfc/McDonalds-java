package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzc extends zzal {
    /* renamed from: ID */
    private static final String f6496ID = zzad.ADVERTISING_TRACKING_ENABLED.toString();
    private final zza zzbnH;

    public zzc(Context context) {
        this(zza.zzbg(context));
    }

    zzc(zza zza) {
        super(f6496ID, new String[0]);
        this.zzbnH = zza;
    }

    public boolean zzJf() {
        return false;
    }

    public zza zzV(Map<String, zza> map) {
        return zzdl.zzQ(Boolean.valueOf(!this.zzbnH.isLimitAdTrackingEnabled()));
    }
}
