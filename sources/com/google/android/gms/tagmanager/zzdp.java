package com.google.android.gms.tagmanager;

import com.facebook.stetho.common.Utf8Charset;
import com.google.android.gms.internal.zzag.zza;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class zzdp {
    private static zzcd<zza> zza(zzcd<zza> zzcd) {
        try {
            return new zzcd(zzdl.zzQ(zzgU(zzdl.zzg((zza) zzcd.getObject()))), zzcd.zzKc());
        } catch (UnsupportedEncodingException e) {
            zzbn.zzb("Escape URI: unsupported encoding", e);
            return zzcd;
        }
    }

    private static zzcd<zza> zza(zzcd<zza> zzcd, int i) {
        if (zzn((zza) zzcd.getObject())) {
            switch (i) {
                case 12:
                    return zza(zzcd);
                default:
                    zzbn.m7501e("Unsupported Value Escaping: " + i);
                    return zzcd;
            }
        }
        zzbn.m7501e("Escaping can only be applied to strings.");
        return zzcd;
    }

    static zzcd<zza> zza(zzcd<zza> zzcd, int... iArr) {
        zzcd zzcd2;
        for (int zza : iArr) {
            zzcd2 = zza(zzcd2, zza);
        }
        return zzcd2;
    }

    static String zzgU(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, Utf8Charset.NAME).replaceAll("\\+", "%20");
    }

    private static boolean zzn(zza zza) {
        return zzdl.zzl(zza) instanceof String;
    }
}
