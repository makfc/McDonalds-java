package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzae extends zzal {
    /* renamed from: ID */
    private static final String f6480ID = zzad.ENCODE.toString();
    private static final String zzbpb = com.google.android.gms.internal.zzae.ARG0.toString();
    private static final String zzbpc = com.google.android.gms.internal.zzae.NO_PADDING.toString();
    private static final String zzbpd = com.google.android.gms.internal.zzae.INPUT_FORMAT.toString();
    private static final String zzbpe = com.google.android.gms.internal.zzae.OUTPUT_FORMAT.toString();

    public zzae() {
        super(f6480ID, zzbpb);
    }

    public boolean zzJf() {
        return true;
    }

    public zza zzV(Map<String, zza> map) {
        zza zza = (zza) map.get(zzbpb);
        if (zza == null || zza == zzdl.zzKT()) {
            return zzdl.zzKT();
        }
        Object obj;
        String obj2;
        Object obj3;
        String obj32;
        String zzg = zzdl.zzg(zza);
        zza = (zza) map.get(zzbpd);
        if (zza == null) {
            obj2 = "text";
        } else {
            obj2 = zzdl.zzg(zza);
        }
        zza = (zza) map.get(zzbpe);
        if (zza == null) {
            obj32 = "base16";
        } else {
            obj32 = zzdl.zzg(zza);
        }
        zza = (zza) map.get(zzbpc);
        int i = (zza == null || !zzdl.zzk(zza).booleanValue()) ? 2 : 3;
        try {
            byte[] bytes;
            String valueOf;
            Object zzk;
            if ("text".equals(obj2)) {
                bytes = zzg.getBytes();
            } else if ("base16".equals(obj2)) {
                bytes = zzk.zzgi(zzg);
            } else if ("base64".equals(obj2)) {
                bytes = Base64.decode(zzg, i);
            } else if ("base64url".equals(obj2)) {
                bytes = Base64.decode(zzg, i | 8);
            } else {
                obj32 = "Encode: unknown input format: ";
                valueOf = String.valueOf(obj2);
                zzbn.m7501e(valueOf.length() != 0 ? obj32.concat(valueOf) : new String(obj32));
                return zzdl.zzKT();
            }
            if ("base16".equals(obj32)) {
                zzk = zzk.zzk(bytes);
            } else if ("base64".equals(obj32)) {
                zzk = Base64.encodeToString(bytes, i);
            } else if ("base64url".equals(obj32)) {
                zzk = Base64.encodeToString(bytes, i | 8);
            } else {
                obj2 = "Encode: unknown output format: ";
                valueOf = String.valueOf(obj32);
                zzbn.m7501e(valueOf.length() != 0 ? obj2.concat(valueOf) : new String(obj2));
                return zzdl.zzKT();
            }
            return zzdl.zzQ(zzk);
        } catch (IllegalArgumentException e) {
            zzbn.m7501e("Encode: invalid input:");
            return zzdl.zzKT();
        }
    }
}
