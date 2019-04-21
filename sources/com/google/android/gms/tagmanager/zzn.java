package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzn extends zzal {
    /* renamed from: ID */
    private static final String f6515ID = zzad.CONSTANT.toString();
    private static final String VALUE = zzae.VALUE.toString();

    public zzn() {
        super(f6515ID, VALUE);
    }

    public static String zzJi() {
        return f6515ID;
    }

    public static String zzJj() {
        return VALUE;
    }

    public boolean zzJf() {
        return true;
    }

    public zza zzV(Map<String, zza> map) {
        return (zza) map.get(VALUE);
    }
}
