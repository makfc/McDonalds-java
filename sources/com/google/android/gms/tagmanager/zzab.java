package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzab extends zzal {
    /* renamed from: ID */
    private static final String f6479ID = zzad.DEVICE_NAME.toString();

    public zzab() {
        super(f6479ID, new String[0]);
    }

    public boolean zzJf() {
        return true;
    }

    public zza zzV(Map<String, zza> map) {
        String str = Build.MANUFACTURER;
        Object obj = Build.MODEL;
        if (!(obj.startsWith(str) || str.equals("unknown"))) {
            obj = new StringBuilder((String.valueOf(str).length() + 1) + String.valueOf(obj).length()).append(str).append(" ").append(obj).toString();
        }
        return zzdl.zzQ(obj);
    }
}
