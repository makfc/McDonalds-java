package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzr extends zzdg {
    /* renamed from: ID */
    private static final String f6517ID = zzad.CONTAINS.toString();

    public zzr() {
        super(f6517ID);
    }

    /* Access modifiers changed, original: protected */
    public boolean zza(String str, String str2, Map<String, zza> map) {
        return str.contains(str2);
    }
}
