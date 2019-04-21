package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class zzt extends zzal {
    /* renamed from: ID */
    private static final String f6518ID = zzad.FUNCTION_CALL.toString();
    private static final String zzbnK = zzae.ADDITIONAL_PARAMS.toString();
    private static final String zzbou = zzae.FUNCTION_CALL_NAME.toString();
    private final zza zzbov;

    public interface zza {
        Object zzd(String str, Map<String, Object> map);
    }

    public zzt(zza zza) {
        super(f6518ID, zzbou);
        this.zzbov = zza;
    }

    public boolean zzJf() {
        return false;
    }

    public com.google.android.gms.internal.zzag.zza zzV(Map<String, com.google.android.gms.internal.zzag.zza> map) {
        String zzg = zzdl.zzg((com.google.android.gms.internal.zzag.zza) map.get(zzbou));
        HashMap hashMap = new HashMap();
        com.google.android.gms.internal.zzag.zza zza = (com.google.android.gms.internal.zzag.zza) map.get(zzbnK);
        if (zza != null) {
            Object zzl = zzdl.zzl(zza);
            if (zzl instanceof Map) {
                for (Entry entry : ((Map) zzl).entrySet()) {
                    hashMap.put(entry.getKey().toString(), entry.getValue());
                }
            } else {
                zzbn.zzaW("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
                return zzdl.zzKT();
            }
        }
        try {
            return zzdl.zzQ(this.zzbov.zzd(zzg, hashMap));
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            zzbn.zzaW(new StringBuilder((String.valueOf(zzg).length() + 34) + String.valueOf(valueOf).length()).append("Custom macro/tag ").append(zzg).append(" threw exception ").append(valueOf).toString());
            return zzdl.zzKT();
        }
    }
}
