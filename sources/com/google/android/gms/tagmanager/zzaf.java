package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzaf extends zzdg {
    /* renamed from: ID */
    private static final String f6481ID = zzad.ENDS_WITH.toString();

    public zzaf() {
        super(f6481ID);
    }

    /* Access modifiers changed, original: protected */
    public boolean zza(String str, String str2, Map<String, zza> map) {
        return str.endsWith(str2);
    }
}
