package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class zzcl extends zzal {
    /* renamed from: ID */
    private static final String f6500ID = zzad.REGEX_GROUP.toString();
    private static final String zzbql = zzae.ARG0.toString();
    private static final String zzbqm = zzae.ARG1.toString();
    private static final String zzbqn = zzae.IGNORE_CASE.toString();
    private static final String zzbqo = zzae.GROUP.toString();

    public zzcl() {
        super(f6500ID, zzbql, zzbqm);
    }

    public boolean zzJf() {
        return true;
    }

    public zza zzV(Map<String, zza> map) {
        zza zza = (zza) map.get(zzbql);
        zza zza2 = (zza) map.get(zzbqm);
        if (zza == null || zza == zzdl.zzKT() || zza2 == null || zza2 == zzdl.zzKT()) {
            return zzdl.zzKT();
        }
        int intValue;
        int i = 64;
        if (zzdl.zzk((zza) map.get(zzbqn)).booleanValue()) {
            i = 66;
        }
        zza zza3 = (zza) map.get(zzbqo);
        if (zza3 != null) {
            Long zzi = zzdl.zzi(zza3);
            if (zzi == zzdl.zzKO()) {
                return zzdl.zzKT();
            }
            intValue = zzi.intValue();
            if (intValue < 0) {
                return zzdl.zzKT();
            }
        }
        intValue = 1;
        try {
            String zzg = zzdl.zzg(zza);
            Object obj = null;
            Matcher matcher = Pattern.compile(zzdl.zzg(zza2), i).matcher(zzg);
            if (matcher.find() && matcher.groupCount() >= intValue) {
                obj = matcher.group(intValue);
            }
            return obj == null ? zzdl.zzKT() : zzdl.zzQ(obj);
        } catch (PatternSyntaxException e) {
            return zzdl.zzKT();
        }
    }
}
