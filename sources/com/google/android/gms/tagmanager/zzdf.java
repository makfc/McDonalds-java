package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzdf extends zzdg {
    /* renamed from: ID */
    private static final String f6505ID = zzad.STARTS_WITH.toString();

    public zzdf() {
        super(f6505ID);
    }

    /* Access modifiers changed, original: protected */
    public boolean zza(String str, String str2, Map<String, zza> map) {
        return str.startsWith(str2);
    }
}
