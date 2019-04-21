package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class zzcm extends zzdg {
    /* renamed from: ID */
    private static final String f6501ID = zzad.REGEX.toString();
    private static final String zzbqn = zzae.IGNORE_CASE.toString();

    public zzcm() {
        super(f6501ID);
    }

    /* Access modifiers changed, original: protected */
    public boolean zza(String str, String str2, Map<String, zza> map) {
        try {
            return Pattern.compile(str2, zzdl.zzk((zza) map.get(zzbqn)).booleanValue() ? 66 : 64).matcher(str).find();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }
}
