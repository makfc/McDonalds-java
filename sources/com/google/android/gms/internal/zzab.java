package com.google.android.gms.internal;

import com.google.android.gms.internal.zzm.zzb;
import java.io.UnsupportedEncodingException;

public class zzab extends zzk<String> {
    private final zzb<String> zzaG;

    /* Access modifiers changed, original: protected */
    public zzm<String> zza(zzi zzi) {
        Object str;
        try {
            str = new String(zzi.data, zzx.zza(zzi.zzz));
        } catch (UnsupportedEncodingException e) {
            str = new String(zzi.data);
        }
        return zzm.zza(str, zzx.zzb(zzi));
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: zzi */
    public void zza(String str) {
        this.zzaG.zzb(str);
    }
}
