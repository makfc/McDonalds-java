package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

class zzaq extends zzal {
    /* renamed from: ID */
    private static final String f6487ID = zzad.HASH.toString();
    private static final String zzbpb = zzae.ARG0.toString();
    private static final String zzbpd = zzae.INPUT_FORMAT.toString();
    private static final String zzbph = zzae.ALGORITHM.toString();

    public zzaq() {
        super(f6487ID, zzbpb);
    }

    private byte[] zzf(String str, byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance(str);
        instance.update(bArr);
        return instance.digest();
    }

    public boolean zzJf() {
        return true;
    }

    public zza zzV(Map<String, zza> map) {
        zza zza = (zza) map.get(zzbpb);
        if (zza == null || zza == zzdl.zzKT()) {
            return zzdl.zzKT();
        }
        byte[] bytes;
        String valueOf;
        String zzg = zzdl.zzg(zza);
        zza = (zza) map.get(zzbph);
        String zzg2 = zza == null ? "MD5" : zzdl.zzg(zza);
        zza = (zza) map.get(zzbpd);
        Object zzg3 = zza == null ? "text" : zzdl.zzg(zza);
        if ("text".equals(zzg3)) {
            bytes = zzg.getBytes();
        } else if ("base16".equals(zzg3)) {
            bytes = zzk.zzgi(zzg);
        } else {
            zzg2 = "Hash: unknown input format: ";
            valueOf = String.valueOf(zzg3);
            zzbn.m7501e(valueOf.length() != 0 ? zzg2.concat(valueOf) : new String(zzg2));
            return zzdl.zzKT();
        }
        try {
            return zzdl.zzQ(zzk.zzk(zzf(zzg2, bytes)));
        } catch (NoSuchAlgorithmException e) {
            zzg = "Hash: unknown algorithm: ";
            valueOf = String.valueOf(zzg2);
            zzbn.m7501e(valueOf.length() != 0 ? zzg.concat(valueOf) : new String(zzg));
            return zzdl.zzKT();
        }
    }
}
