package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzcy extends zzal {
    /* renamed from: ID */
    private static final String f6504ID = zzad.SDK_VERSION.toString();

    public zzcy() {
        super(f6504ID, new String[0]);
    }

    public boolean zzJf() {
        return true;
    }

    public zza zzV(Map<String, zza> map) {
        return zzdl.zzQ(Integer.valueOf(VERSION.SDK_INT));
    }
}
