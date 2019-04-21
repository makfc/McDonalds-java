package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.android.Facebook;
import com.google.android.gms.analytics.internal.zzab;
import com.google.android.gms.analytics.internal.zzad;
import com.google.android.gms.analytics.internal.zzan;
import com.google.android.gms.analytics.internal.zzao;
import com.google.android.gms.analytics.internal.zzd;
import com.google.android.gms.analytics.internal.zze;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.analytics.internal.zzh;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzln;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Tracker extends zzd {
    private final Map<String, String> zzAd = new HashMap();
    private boolean zzUK;
    private final Map<String, String> zzUL = new HashMap();
    private final zzad zzUM;
    private final zza zzUN;
    private zzan zzUP;

    private class zza extends zzd implements zza {
        private boolean zzUY;
        private int zzUZ;
        private long zzVa = -1;
        private boolean zzVb;
        private long zzVc;

        protected zza(zzf zzf) {
            super(zzf);
        }

        /* Access modifiers changed, original: protected */
        public void zzkO() {
        }

        public synchronized boolean zzkR() {
            boolean z;
            z = this.zzVb;
            this.zzVb = false;
            return z;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean zzkT() {
            return zzlQ().elapsedRealtime() >= this.zzVc + Math.max(1000, this.zzVa);
        }

        public void zzo(Activity activity) {
            if (this.zzUZ == 0 && zzkT()) {
                this.zzVb = true;
            }
            this.zzUZ++;
            if (this.zzUY) {
                Intent intent = activity.getIntent();
                if (intent != null) {
                    Tracker.this.setCampaignParamsOnNextHit(intent.getData());
                }
                HashMap hashMap = new HashMap();
                hashMap.put("&t", "screenview");
                Tracker.this.set("&cd", Tracker.this.zzUP != null ? Tracker.this.zzUP.zzr(activity) : activity.getClass().getCanonicalName());
                if (TextUtils.isEmpty((CharSequence) hashMap.get("&dr"))) {
                    String zzq = Tracker.zzq(activity);
                    if (!TextUtils.isEmpty(zzq)) {
                        hashMap.put("&dr", zzq);
                    }
                }
                Tracker.this.send(hashMap);
            }
        }

        public void zzp(Activity activity) {
            this.zzUZ--;
            this.zzUZ = Math.max(0, this.zzUZ);
            if (this.zzUZ == 0) {
                this.zzVc = zzlQ().elapsedRealtime();
            }
        }
    }

    Tracker(zzf zzf, String str, zzad zzad) {
        super(zzf);
        if (str != null) {
            this.zzAd.put("&tid", str);
        }
        this.zzAd.put("useSecure", "1");
        this.zzAd.put("&a", Integer.toString(new Random().nextInt(Integer.MAX_VALUE) + 1));
        if (zzad == null) {
            this.zzUM = new zzad(JiceArgs.EVENT_SUMMARY_TRACK, zzlQ());
        } else {
            this.zzUM = zzad;
        }
        this.zzUN = new zza(zzf);
    }

    private static boolean zza(Entry<String, String> entry) {
        String str = (String) entry.getKey();
        String str2 = (String) entry.getValue();
        return str.startsWith("&") && str.length() >= 2;
    }

    private static String zzb(Entry<String, String> entry) {
        return !zza((Entry) entry) ? null : ((String) entry.getKey()).substring(1);
    }

    private static void zzb(Map<String, String> map, Map<String, String> map2) {
        zzaa.zzz(map2);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                String zzb = zzb(entry);
                if (zzb != null) {
                    map2.put(zzb, (String) entry.getValue());
                }
            }
        }
    }

    private static void zzc(Map<String, String> map, Map<String, String> map2) {
        zzaa.zzz(map2);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                String zzb = zzb(entry);
                if (!(zzb == null || map2.containsKey(zzb))) {
                    map2.put(zzb, (String) entry.getValue());
                }
            }
        }
    }

    static String zzq(Activity activity) {
        zzaa.zzz(activity);
        Intent intent = activity.getIntent();
        if (intent == null) {
            return null;
        }
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return !TextUtils.isEmpty(stringExtra) ? stringExtra : null;
    }

    public void enableAdvertisingIdCollection(boolean z) {
        this.zzUK = z;
    }

    public void send(Map<String, String> map) {
        final long currentTimeMillis = zzlQ().currentTimeMillis();
        if (zzkq().getAppOptOut()) {
            zzbH("AppOptOut is set to true. Not sending Google Analytics hit");
            return;
        }
        final boolean isDryRunEnabled = zzkq().isDryRunEnabled();
        final HashMap hashMap = new HashMap();
        zzb(this.zzAd, hashMap);
        zzb(map, hashMap);
        final boolean zzi = zzao.zzi((String) this.zzAd.get("useSecure"), true);
        zzc(this.zzUL, hashMap);
        this.zzUL.clear();
        final String str = (String) hashMap.get("t");
        if (TextUtils.isEmpty(str)) {
            zzlR().zzh(hashMap, "Missing hit type parameter");
            return;
        }
        final String str2 = (String) hashMap.get("tid");
        if (TextUtils.isEmpty(str2)) {
            zzlR().zzh(hashMap, "Missing tracking id parameter");
            return;
        }
        final boolean zzkQ = zzkQ();
        synchronized (this) {
            if ("screenview".equalsIgnoreCase(str) || "pageview".equalsIgnoreCase(str) || "appview".equalsIgnoreCase(str) || TextUtils.isEmpty(str)) {
                int parseInt = Integer.parseInt((String) this.zzAd.get("&a")) + 1;
                if (parseInt >= Integer.MAX_VALUE) {
                    parseInt = 1;
                }
                this.zzAd.put("&a", Integer.toString(parseInt));
            }
        }
        zzlT().zzf(new Runnable() {
            public void run() {
                boolean z = true;
                if (Tracker.this.zzUN.zzkR()) {
                    hashMap.put("sc", "start");
                }
                zzao.zzd(hashMap, "cid", Tracker.this.zzkq().zzku());
                String str = (String) hashMap.get("sf");
                if (str != null) {
                    double zza = zzao.zza(str, 100.0d);
                    if (zzao.zza(zza, (String) hashMap.get("cid"))) {
                        Tracker.this.zzb("Sampling enabled. Hit sampled out. sample rate", Double.valueOf(zza));
                        return;
                    }
                }
                com.google.android.gms.analytics.internal.zza zzb = Tracker.this.zzlX();
                if (zzkQ) {
                    zzao.zzb(hashMap, "ate", zzb.zzlt());
                    zzao.zzc(hashMap, "adid", zzb.zzlE());
                } else {
                    hashMap.remove("ate");
                    hashMap.remove("adid");
                }
                zzln zzmx = Tracker.this.zzlY().zzmx();
                zzao.zzc(hashMap, "an", zzmx.zzkU());
                zzao.zzc(hashMap, "av", zzmx.zzkV());
                zzao.zzc(hashMap, Facebook.ATTRIBUTION_ID_COLUMN_NAME, zzmx.zziC());
                zzao.zzc(hashMap, "aiid", zzmx.zzkW());
                hashMap.put("v", "1");
                hashMap.put("_v", zze.zzWi);
                zzao.zzc(hashMap, "ul", Tracker.this.zzlZ().zznE().getLanguage());
                zzao.zzc(hashMap, "sr", Tracker.this.zzlZ().zznF());
                boolean z2 = str.equals("transaction") || str.equals("item");
                if (z2 || Tracker.this.zzUM.zznY()) {
                    long zzbX = zzao.zzbX((String) hashMap.get("ht"));
                    if (zzbX == 0) {
                        zzbX = currentTimeMillis;
                    }
                    if (isDryRunEnabled) {
                        Tracker.this.zzlR().zzc("Dry run enabled. Would have sent hit", new zzab(Tracker.this, hashMap, zzbX, zzi));
                        return;
                    }
                    String str2 = (String) hashMap.get("cid");
                    HashMap hashMap = new HashMap();
                    zzao.zza(hashMap, "uid", hashMap);
                    zzao.zza(hashMap, "an", hashMap);
                    zzao.zza(hashMap, Facebook.ATTRIBUTION_ID_COLUMN_NAME, hashMap);
                    zzao.zza(hashMap, "av", hashMap);
                    zzao.zza(hashMap, "aiid", hashMap);
                    String str3 = str2;
                    if (TextUtils.isEmpty((CharSequence) hashMap.get("adid"))) {
                        z = false;
                    }
                    hashMap.put("_s", String.valueOf(Tracker.this.zzkw().zza(new zzh(0, str2, str3, z, 0, hashMap))));
                    Tracker.this.zzkw().zza(new zzab(Tracker.this, hashMap, zzbX, zzi));
                    return;
                }
                Tracker.this.zzlR().zzh(hashMap, "Too many hits sent too quickly, rate limiting invoked");
            }
        });
    }

    public void set(String str, String str2) {
        zzaa.zzb((Object) str, (Object) "Key should be non-null");
        if (!TextUtils.isEmpty(str)) {
            this.zzAd.put(str, str2);
        }
    }

    public void setCampaignParamsOnNextHit(Uri uri) {
        if (uri != null && !uri.isOpaque()) {
            String queryParameter = uri.getQueryParameter("referrer");
            if (!TextUtils.isEmpty(queryParameter)) {
                String str = "http://hostname/?";
                queryParameter = String.valueOf(queryParameter);
                Uri parse = Uri.parse(queryParameter.length() != 0 ? str.concat(queryParameter) : new String(str));
                str = parse.getQueryParameter("utm_id");
                if (str != null) {
                    this.zzUL.put("&ci", str);
                }
                str = parse.getQueryParameter("anid");
                if (str != null) {
                    this.zzUL.put("&anid", str);
                }
                str = parse.getQueryParameter("utm_campaign");
                if (str != null) {
                    this.zzUL.put("&cn", str);
                }
                str = parse.getQueryParameter("utm_content");
                if (str != null) {
                    this.zzUL.put("&cc", str);
                }
                str = parse.getQueryParameter("utm_medium");
                if (str != null) {
                    this.zzUL.put("&cm", str);
                }
                str = parse.getQueryParameter("utm_source");
                if (str != null) {
                    this.zzUL.put("&cs", str);
                }
                str = parse.getQueryParameter("utm_term");
                if (str != null) {
                    this.zzUL.put("&ck", str);
                }
                str = parse.getQueryParameter("dclid");
                if (str != null) {
                    this.zzUL.put("&dclid", str);
                }
                str = parse.getQueryParameter("gclid");
                if (str != null) {
                    this.zzUL.put("&gclid", str);
                }
                queryParameter = parse.getQueryParameter("aclid");
                if (queryParameter != null) {
                    this.zzUL.put("&aclid", queryParameter);
                }
            }
        }
    }

    public void setScreenName(String str) {
        set("&cd", str);
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
        this.zzUN.initialize();
        String zzkU = zzkx().zzkU();
        if (zzkU != null) {
            set("&an", zzkU);
        }
        zzkU = zzkx().zzkV();
        if (zzkU != null) {
            set("&av", zzkU);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zzkQ() {
        return this.zzUK;
    }
}
