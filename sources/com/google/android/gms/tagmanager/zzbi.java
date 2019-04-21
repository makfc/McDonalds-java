package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class zzbi extends zzal {
    /* renamed from: ID */
    private static final String f6491ID = zzad.LANGUAGE.toString();

    public zzbi() {
        super(f6491ID, new String[0]);
    }

    public /* bridge */ /* synthetic */ String zzJO() {
        return super.zzJO();
    }

    public /* bridge */ /* synthetic */ Set zzJP() {
        return super.zzJP();
    }

    public boolean zzJf() {
        return false;
    }

    public zza zzV(Map<String, zza> map) {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return zzdl.zzKT();
        }
        String language = locale.getLanguage();
        return language == null ? zzdl.zzKT() : zzdl.zzQ(language.toLowerCase());
    }
}
