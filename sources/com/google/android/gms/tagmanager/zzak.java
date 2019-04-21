package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf.zzc;
import com.google.android.gms.internal.zzaf.zzd;
import com.google.android.gms.internal.zzaf.zzi;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzak {
    private static void zza(DataLayer dataLayer, zzd zzd) {
        for (zza zzg : zzd.zziP) {
            dataLayer.zzgr(zzdl.zzg(zzg));
        }
    }

    public static void zza(DataLayer dataLayer, zzi zzi) {
        if (zzi.zzjE == null) {
            zzbn.zzaW("supplemental missing experimentSupplemental");
            return;
        }
        zza(dataLayer, zzi.zzjE);
        zzb(dataLayer, zzi.zzjE);
        zzc(dataLayer, zzi.zzjE);
    }

    private static void zzb(DataLayer dataLayer, zzd zzd) {
        for (zza zzc : zzd.zziO) {
            Map zzc2 = zzc(zzc);
            if (zzc2 != null) {
                dataLayer.push(zzc2);
            }
        }
    }

    private static Map<String, Object> zzc(zza zza) {
        Object zzl = zzdl.zzl(zza);
        if (zzl instanceof Map) {
            return (Map) zzl;
        }
        String valueOf = String.valueOf(zzl);
        zzbn.zzaW(new StringBuilder(String.valueOf(valueOf).length() + 36).append("value: ").append(valueOf).append(" is not a map value, ignored.").toString());
        return null;
    }

    private static void zzc(DataLayer dataLayer, zzd zzd) {
        for (zzc zzc : zzd.zziQ) {
            if (zzc.zzaB == null) {
                zzbn.zzaW("GaExperimentRandom: No key");
            } else {
                Object obj = dataLayer.get(zzc.zzaB);
                Long valueOf = !(obj instanceof Number) ? null : Long.valueOf(((Number) obj).longValue());
                long j = zzc.zziK;
                long j2 = zzc.zziL;
                if (!zzc.zziM || valueOf == null || valueOf.longValue() < j || valueOf.longValue() > j2) {
                    if (j <= j2) {
                        obj = Long.valueOf(Math.round((Math.random() * ((double) (j2 - j))) + ((double) j)));
                    } else {
                        zzbn.zzaW("GaExperimentRandom: random range invalid");
                    }
                }
                dataLayer.zzgr(zzc.zzaB);
                Map zzo = dataLayer.zzo(zzc.zzaB, obj);
                if (zzc.zziN > 0) {
                    if (zzo.containsKey("gtm")) {
                        Object obj2 = zzo.get("gtm");
                        if (obj2 instanceof Map) {
                            ((Map) obj2).put("lifetime", Long.valueOf(zzc.zziN));
                        } else {
                            zzbn.zzaW("GaExperimentRandom: gtm not a map");
                        }
                    } else {
                        zzo.put("gtm", DataLayer.mapOf("lifetime", Long.valueOf(zzc.zziN)));
                    }
                }
                dataLayer.push(zzo);
            }
        }
    }
}
