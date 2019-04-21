package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzce extends zzal {
    /* renamed from: ID */
    private static final String f6497ID = zzad.OS_VERSION.toString();

    public zzce() {
        super(f6497ID, new String[0]);
    }

    public boolean zzJf() {
        return true;
    }

    public zza zzV(Map<String, zza> map) {
        return zzdl.zzQ(VERSION.RELEASE);
    }
}
