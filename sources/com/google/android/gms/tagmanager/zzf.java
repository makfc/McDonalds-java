package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzf extends zzal {
    /* renamed from: ID */
    private static final String f6510ID = zzad.APP_ID.toString();
    private final Context mContext;

    public zzf(Context context) {
        super(f6510ID, new String[0]);
        this.mContext = context;
    }

    public boolean zzJf() {
        return true;
    }

    public zza zzV(Map<String, zza> map) {
        return zzdl.zzQ(this.mContext.getPackageName());
    }
}
