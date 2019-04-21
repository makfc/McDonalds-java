package com.google.android.gms.analytics;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.facebook.android.Facebook;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.internal.zzab;
import com.google.android.gms.analytics.internal.zzao;
import com.google.android.gms.analytics.internal.zzc;
import com.google.android.gms.analytics.internal.zze;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.analytics.internal.zzh;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzln;
import com.google.android.gms.internal.zzlo;
import com.google.android.gms.internal.zzlp;
import com.google.android.gms.internal.zzlq;
import com.google.android.gms.internal.zzlr;
import com.google.android.gms.internal.zzls;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zzlu;
import com.google.android.gms.internal.zzlv;
import com.google.android.gms.internal.zzlw;
import com.google.android.gms.internal.zzlx;
import com.google.android.gms.internal.zzly;
import com.google.android.gms.internal.zzlz;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class zzb extends zzc implements zzk {
    private static DecimalFormat zzTI;
    private final zzf zzTE;
    private final String zzTJ;
    private final Uri zzTK;
    private final boolean zzTL;
    private final boolean zzTM;

    public zzb(zzf zzf, String str) {
        this(zzf, str, true, false);
    }

    public zzb(zzf zzf, String str, boolean z, boolean z2) {
        super(zzf);
        zzaa.zzdl(str);
        this.zzTE = zzf;
        this.zzTJ = str;
        this.zzTL = z;
        this.zzTM = z2;
        this.zzTK = zzbh(this.zzTJ);
    }

    private static String zzN(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append((String) entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append((String) entry.getValue());
        }
        return stringBuilder.toString();
    }

    private static void zza(Map<String, String> map, String str, double d) {
        if (d != 0.0d) {
            map.put(str, zzb(d));
        }
    }

    private static void zza(Map<String, String> map, String str, int i, int i2) {
        if (i > 0 && i2 > 0) {
            map.put(str, i + "x" + i2);
        }
    }

    private static void zza(Map<String, String> map, String str, boolean z) {
        if (z) {
            map.put(str, "1");
        }
    }

    static String zzb(double d) {
        if (zzTI == null) {
            zzTI = new DecimalFormat("0.######");
        }
        return zzTI.format(d);
    }

    private static void zzb(Map<String, String> map, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            map.put(str, str2);
        }
    }

    static Uri zzbh(String str) {
        zzaa.zzdl(str);
        Builder builder = new Builder();
        builder.scheme("uri");
        builder.authority("google-analytics.com");
        builder.path(str);
        return builder.build();
    }

    public static Map<String, String> zzc(zze zze) {
        String zzab;
        Map hashMap = new HashMap();
        zzlr zzlr = (zzlr) zze.zza(zzlr.class);
        if (zzlr != null) {
            for (Entry entry : zzlr.zzlf().entrySet()) {
                String zzi = zzi(entry.getValue());
                if (zzi != null) {
                    hashMap.put((String) entry.getKey(), zzi);
                }
            }
        }
        zzlw zzlw = (zzlw) zze.zza(zzlw.class);
        if (zzlw != null) {
            zzb(hashMap, "t", zzlw.zzlr());
            zzb(hashMap, "cid", zzlw.zzku());
            zzb(hashMap, "uid", zzlw.getUserId());
            zzb(hashMap, "sc", zzlw.zzlu());
            zza(hashMap, "sf", zzlw.zzlw());
            zza(hashMap, "ni", zzlw.zzlv());
            zzb(hashMap, "adid", zzlw.zzls());
            zza(hashMap, "ate", zzlw.zzlt());
        }
        zzlx zzlx = (zzlx) zze.zza(zzlx.class);
        if (zzlx != null) {
            zzb(hashMap, "cd", zzlx.zzly());
            zza(hashMap, "a", (double) zzlx.zzlz());
            zzb(hashMap, "dr", zzlx.zzlA());
        }
        zzlu zzlu = (zzlu) zze.zza(zzlu.class);
        if (zzlu != null) {
            zzb(hashMap, "ec", zzlu.zzlp());
            zzb(hashMap, "ea", zzlu.getAction());
            zzb(hashMap, "el", zzlu.getLabel());
            zza(hashMap, "ev", (double) zzlu.getValue());
        }
        zzlo zzlo = (zzlo) zze.zza(zzlo.class);
        if (zzlo != null) {
            zzb(hashMap, "cn", zzlo.getName());
            zzb(hashMap, "cs", zzlo.getSource());
            zzb(hashMap, "cm", zzlo.zzkX());
            zzb(hashMap, "ck", zzlo.zzkY());
            zzb(hashMap, "cc", zzlo.getContent());
            zzb(hashMap, "ci", zzlo.getId());
            zzb(hashMap, "anid", zzlo.zzkZ());
            zzb(hashMap, "gclid", zzlo.zzla());
            zzb(hashMap, "dclid", zzlo.zzlb());
            zzb(hashMap, "aclid", zzlo.zzlc());
        }
        zzlv zzlv = (zzlv) zze.zza(zzlv.class);
        if (zzlv != null) {
            zzb(hashMap, "exd", zzlv.getDescription());
            zza(hashMap, "exf", zzlv.zzlq());
        }
        zzly zzly = (zzly) zze.zza(zzly.class);
        if (zzly != null) {
            zzb(hashMap, "sn", zzly.zzlC());
            zzb(hashMap, "sa", zzly.getAction());
            zzb(hashMap, "st", zzly.getTarget());
        }
        zzlz zzlz = (zzlz) zze.zza(zzlz.class);
        if (zzlz != null) {
            zzb(hashMap, "utv", zzlz.zzlD());
            zza(hashMap, "utt", (double) zzlz.getTimeInMillis());
            zzb(hashMap, "utc", zzlz.zzlp());
            zzb(hashMap, "utl", zzlz.getLabel());
        }
        zzlp zzlp = (zzlp) zze.zza(zzlp.class);
        if (zzlp != null) {
            for (Entry entry2 : zzlp.zzld().entrySet()) {
                zzab = zzc.zzab(((Integer) entry2.getKey()).intValue());
                if (!TextUtils.isEmpty(zzab)) {
                    hashMap.put(zzab, (String) entry2.getValue());
                }
            }
        }
        zzlq zzlq = (zzlq) zze.zza(zzlq.class);
        if (zzlq != null) {
            for (Entry entry22 : zzlq.zzle().entrySet()) {
                zzab = zzc.zzad(((Integer) entry22.getKey()).intValue());
                if (!TextUtils.isEmpty(zzab)) {
                    hashMap.put(zzab, zzb(((Double) entry22.getValue()).doubleValue()));
                }
            }
        }
        zzlt zzlt = (zzlt) zze.zza(zzlt.class);
        if (zzlt != null) {
            ProductAction zzll = zzlt.zzll();
            if (zzll != null) {
                for (Entry entry3 : zzll.build().entrySet()) {
                    if (((String) entry3.getKey()).startsWith("&")) {
                        hashMap.put(((String) entry3.getKey()).substring(1), (String) entry3.getValue());
                    } else {
                        hashMap.put((String) entry3.getKey(), (String) entry3.getValue());
                    }
                }
            }
            int i = 1;
            for (Promotion zzbD : zzlt.zzlo()) {
                hashMap.putAll(zzbD.zzbD(zzc.zzah(i)));
                i++;
            }
            i = 1;
            for (Product zzbD2 : zzlt.zzlm()) {
                hashMap.putAll(zzbD2.zzbD(zzc.zzaf(i)));
                i++;
            }
            i = 1;
            for (Entry entry222 : zzlt.zzln().entrySet()) {
                List<Product> list = (List) entry222.getValue();
                String zzak = zzc.zzak(i);
                int i2 = 1;
                for (Product zzbD22 : list) {
                    String valueOf = String.valueOf(zzak);
                    String valueOf2 = String.valueOf(zzc.zzai(i2));
                    hashMap.putAll(zzbD22.zzbD(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)));
                    i2++;
                }
                if (!TextUtils.isEmpty((CharSequence) entry222.getKey())) {
                    String valueOf3 = String.valueOf(zzak);
                    zzab = String.valueOf("nm");
                    hashMap.put(zzab.length() != 0 ? valueOf3.concat(zzab) : new String(valueOf3), (String) entry222.getKey());
                }
                i++;
            }
        }
        zzls zzls = (zzls) zze.zza(zzls.class);
        if (zzls != null) {
            zzb(hashMap, "ul", zzls.getLanguage());
            zza(hashMap, "sd", (double) zzls.zzlg());
            zza(hashMap, "sr", zzls.zzlh(), zzls.zzli());
            zza(hashMap, "vp", zzls.zzlj(), zzls.zzlk());
        }
        zzln zzln = (zzln) zze.zza(zzln.class);
        if (zzln != null) {
            zzb(hashMap, "an", zzln.zzkU());
            zzb(hashMap, Facebook.ATTRIBUTION_ID_COLUMN_NAME, zzln.zziC());
            zzb(hashMap, "aiid", zzln.zzkW());
            zzb(hashMap, "av", zzln.zzkV());
        }
        return hashMap;
    }

    private static String zzi(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            return TextUtils.isEmpty(str) ? null : str;
        } else if (!(obj instanceof Double)) {
            return obj instanceof Boolean ? obj != Boolean.FALSE ? "1" : null : String.valueOf(obj);
        } else {
            Double d = (Double) obj;
            return d.doubleValue() != 0.0d ? zzb(d.doubleValue()) : null;
        }
    }

    public void zzb(zze zze) {
        zzaa.zzz(zze);
        zzaa.zzb(zze.zzkD(), (Object) "Can't deliver not submitted measurement");
        zzaa.zzdd("deliver should be called on worker thread");
        zze zzky = zze.zzky();
        zzlw zzlw = (zzlw) zzky.zzb(zzlw.class);
        if (TextUtils.isEmpty(zzlw.zzlr())) {
            zzlR().zzh(zzc(zzky), "Ignoring measurement without type");
        } else if (TextUtils.isEmpty(zzlw.zzku())) {
            zzlR().zzh(zzc(zzky), "Ignoring measurement without client id");
        } else if (!this.zzTE.zzme().getAppOptOut()) {
            double zzlw2 = zzlw.zzlw();
            if (zzao.zza(zzlw2, zzlw.zzku())) {
                zzb("Sampling enabled. Hit sampled out. sampling rate", Double.valueOf(zzlw2));
                return;
            }
            Map zzc = zzc(zzky);
            zzc.put("v", "1");
            zzc.put("_v", zze.zzWi);
            zzc.put("tid", this.zzTJ);
            if (this.zzTE.zzme().isDryRunEnabled()) {
                zzc("Dry run is enabled. GoogleAnalytics would have sent", zzN(zzc));
                return;
            }
            HashMap hashMap = new HashMap();
            zzao.zzc(hashMap, "uid", zzlw.getUserId());
            zzln zzln = (zzln) zze.zza(zzln.class);
            if (zzln != null) {
                zzao.zzc(hashMap, "an", zzln.zzkU());
                zzao.zzc(hashMap, Facebook.ATTRIBUTION_ID_COLUMN_NAME, zzln.zziC());
                zzao.zzc(hashMap, "av", zzln.zzkV());
                zzao.zzc(hashMap, "aiid", zzln.zzkW());
            }
            zzc.put("_s", String.valueOf(zzkw().zza(new zzh(0, zzlw.zzku(), this.zzTJ, !TextUtils.isEmpty(zzlw.zzls()), 0, hashMap))));
            zzkw().zza(new zzab(zzlR(), zzc, zze.zzkB(), true));
        }
    }

    public Uri zzkn() {
        return this.zzTK;
    }
}
