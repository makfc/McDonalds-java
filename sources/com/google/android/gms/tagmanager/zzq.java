package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzq extends zzal {
    /* renamed from: ID */
    private static final String f6516ID = zzad.CONTAINER_VERSION.toString();
    private final String zzahE;

    public zzq(String str) {
        super(f6516ID, new String[0]);
        this.zzahE = str;
    }

    public boolean zzJf() {
        return true;
    }

    public zza zzV(Map<String, zza> map) {
        return this.zzahE == null ? zzdl.zzKT() : zzdl.zzQ(this.zzahE);
    }
}
