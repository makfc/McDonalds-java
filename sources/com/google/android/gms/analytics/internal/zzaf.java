package com.google.android.gms.analytics.internal;

import android.util.Log;
import com.google.android.gms.common.internal.zzaa;
import java.util.Map;
import java.util.Map.Entry;

public class zzaf extends zzd {
    private static String zzYM = "3";
    private static String zzYN = "01VDIWEA?";
    private static zzaf zzYO;

    public zzaf(zzf zzf) {
        super(zzf);
    }

    public static zzaf zznZ() {
        return zzYO;
    }

    public void zza(int i, String str, Object obj, Object obj2, Object obj3) {
        String str2 = (String) zzy.zzXF.get();
        if (Log.isLoggable(str2, i)) {
            Log.println(i, str2, zzc.zzc(str, obj, obj2, obj3));
        }
        if (i >= 5) {
            zzb(i, str, obj, obj2, obj3);
        }
    }

    public void zza(zzab zzab, String str) {
        Object str2;
        if (str2 == null) {
            str2 = "no reason provided";
        }
        Object zzab2 = zzab != null ? zzab.toString() : "no hit data";
        String str3 = "Discarding hit. ";
        String valueOf = String.valueOf(str2);
        zzd(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3), zzab2);
    }

    public synchronized void zzb(int i, String str, Object obj, Object obj2, Object obj3) {
        int i2 = 0;
        synchronized (this) {
            zzaa.zzz(str);
            if (i >= 0) {
                i2 = i;
            }
            int length = i2 >= zzYN.length() ? zzYN.length() - 1 : i2;
            char c = zzlS().zzmX() ? zzlS().zzmW() ? 'P' : 'C' : zzlS().zzmW() ? 'p' : 'c';
            String str2 = zzYM;
            char charAt = zzYN.charAt(length);
            String str3 = zze.VERSION;
            String valueOf = String.valueOf(zzc.zzc(str, zzm(obj), zzm(obj2), zzm(obj3)));
            String stringBuilder = new StringBuilder(((String.valueOf(str2).length() + 3) + String.valueOf(str3).length()) + String.valueOf(valueOf).length()).append(str2).append(charAt).append(c).append(str3).append(":").append(valueOf).toString();
            if (stringBuilder.length() > 1024) {
                stringBuilder = stringBuilder.substring(0, 1024);
            }
            zzai zzmf = zzlO().zzmf();
            if (zzmf != null) {
                zzmf.zzom().zzbU(stringBuilder);
            }
        }
    }

    public void zzh(Map<String, String> map, String str) {
        Object str2;
        Object stringBuilder;
        if (str2 == null) {
            str2 = "no reason provided";
        }
        if (map != null) {
            StringBuilder stringBuilder2 = new StringBuilder();
            for (Entry entry : map.entrySet()) {
                if (stringBuilder2.length() > 0) {
                    stringBuilder2.append(',');
                }
                stringBuilder2.append((String) entry.getKey());
                stringBuilder2.append('=');
                stringBuilder2.append((String) entry.getValue());
            }
            stringBuilder = stringBuilder2.toString();
        } else {
            stringBuilder = "no hit data";
        }
        String str3 = "Discarding hit. ";
        String valueOf = String.valueOf(str2);
        zzd(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3), stringBuilder);
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
        synchronized (zzaf.class) {
            zzYO = this;
        }
    }

    /* Access modifiers changed, original: protected */
    public String zzm(Object obj) {
        if (obj == null) {
            return null;
        }
        Object l = obj instanceof Integer ? new Long((long) ((Integer) obj).intValue()) : obj;
        if (!(l instanceof Long)) {
            return l instanceof Boolean ? String.valueOf(l) : l instanceof Throwable ? l.getClass().getCanonicalName() : "-";
        } else {
            if (Math.abs(((Long) l).longValue()) < 100) {
                return String.valueOf(l);
            }
            String str = String.valueOf(l).charAt(0) == '-' ? "-" : "";
            String valueOf = String.valueOf(Math.abs(((Long) l).longValue()));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(Math.round(Math.pow(10.0d, (double) (valueOf.length() - 1))));
            stringBuilder.append("...");
            stringBuilder.append(str);
            stringBuilder.append(Math.round(Math.pow(10.0d, (double) valueOf.length()) - 1.0d));
            return stringBuilder.toString();
        }
    }
}
