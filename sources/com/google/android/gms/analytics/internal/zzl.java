package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.facebook.android.Facebook;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.CampaignTrackingService;
import com.google.android.gms.analytics.zza;
import com.google.android.gms.analytics.zze;
import com.google.android.gms.analytics.zzg;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzln;
import com.google.android.gms.internal.zzlo;
import com.google.android.gms.internal.zzlr;
import com.google.android.gms.internal.zzlw;
import java.util.HashMap;
import java.util.Map.Entry;

class zzl extends zzd {
    private boolean mStarted;
    private final zzj zzWT;
    private final zzah zzWU;
    private final zzag zzWV;
    private final zzi zzWW;
    private long zzWX = Long.MIN_VALUE;
    private final zzt zzWY;
    private final zzt zzWZ;
    private final zzal zzXa;
    private long zzXb;
    private boolean zzXc;

    /* renamed from: com.google.android.gms.analytics.internal.zzl$3 */
    class C20873 implements Runnable {
        C20873() {
        }

        public void run() {
            zzl.this.zzmz();
        }
    }

    /* renamed from: com.google.android.gms.analytics.internal.zzl$4 */
    class C20884 implements zzw {
        C20884() {
        }

        public void zzd(Throwable th) {
            zzl.this.zzmG();
        }
    }

    protected zzl(zzf zzf, zzg zzg) {
        super(zzf);
        zzaa.zzz(zzg);
        this.zzWV = zzg.zzk(zzf);
        this.zzWT = zzg.zzm(zzf);
        this.zzWU = zzg.zzn(zzf);
        this.zzWW = zzg.zzo(zzf);
        this.zzXa = new zzal(zzlQ());
        this.zzWY = new zzt(zzf) {
            public void run() {
                zzl.this.zzmA();
            }
        };
        this.zzWZ = new zzt(zzf) {
            public void run() {
                zzl.this.zzmB();
            }
        };
    }

    private void zza(zzh zzh, zzlo zzlo) {
        zzaa.zzz(zzh);
        zzaa.zzz(zzlo);
        zza zza = new zza(zzlO());
        zza.zzbf(zzh.zzmj());
        zza.enableAdvertisingIdCollection(zzh.zzmk());
        zze zzkk = zza.zzkk();
        zzlw zzlw = (zzlw) zzkk.zzb(zzlw.class);
        zzlw.zzbv(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH);
        zzlw.zzO(true);
        zzkk.zza((zzg) zzlo);
        zzlr zzlr = (zzlr) zzkk.zzb(zzlr.class);
        zzln zzln = (zzln) zzkk.zzb(zzln.class);
        for (Entry entry : zzh.zzm().entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if ("an".equals(str)) {
                zzln.setAppName(str2);
            } else if ("av".equals(str)) {
                zzln.setAppVersion(str2);
            } else if (Facebook.ATTRIBUTION_ID_COLUMN_NAME.equals(str)) {
                zzln.setAppId(str2);
            } else if ("aiid".equals(str)) {
                zzln.setAppInstallerId(str2);
            } else if ("uid".equals(str)) {
                zzlw.setUserId(str2);
            } else {
                zzlr.set(str, str2);
            }
        }
        zzb("Sending installation campaign to", zzh.zzmj(), zzlo);
        zzkk.zzn(zzlV().zzoh());
        zzkk.zzkC();
    }

    private boolean zzbN(String str) {
        return getContext().checkCallingOrSelfPermission(str) == 0;
    }

    private void zzmA() {
        zzb(new C20884());
    }

    private void zzmB() {
        try {
            this.zzWT.zzms();
            zzmG();
        } catch (SQLiteException e) {
            zzd("Failed to delete stale hits", e);
        }
        this.zzWZ.zzv(zzlS().zzny());
    }

    private boolean zzmH() {
        return this.zzXc ? false : (!zzlS().zzmW() || zzlS().zzmX()) && zzmN() > 0;
    }

    private void zzmI() {
        zzv zzlU = zzlU();
        if (zzlU.zznG() && !zzlU.zzbW()) {
            long zzmt = zzmt();
            if (zzmt != 0 && Math.abs(zzlQ().currentTimeMillis() - zzmt) <= zzlS().zzng()) {
                zza("Dispatch alarm scheduled (ms)", Long.valueOf(zzlS().zznf()));
                zzlU.schedule();
            }
        }
    }

    private void zzmJ() {
        zzmI();
        long zzmN = zzmN();
        long zzoj = zzlV().zzoj();
        if (zzoj != 0) {
            zzoj = zzmN - Math.abs(zzlQ().currentTimeMillis() - zzoj);
            if (zzoj <= 0) {
                zzoj = Math.min(zzlS().zznd(), zzmN);
            }
        } else {
            zzoj = Math.min(zzlS().zznd(), zzmN);
        }
        zza("Dispatch scheduled (ms)", Long.valueOf(zzoj));
        if (this.zzWY.zzbW()) {
            this.zzWY.zzw(Math.max(1, zzoj + this.zzWY.zznD()));
            return;
        }
        this.zzWY.zzv(zzoj);
    }

    private void zzmK() {
        zzmL();
        zzmM();
    }

    private void zzmL() {
        if (this.zzWY.zzbW()) {
            zzbG("All hits dispatched or no network/service. Going to power save mode");
        }
        this.zzWY.cancel();
    }

    private void zzmM() {
        zzv zzlU = zzlU();
        if (zzlU.zzbW()) {
            zzlU.cancel();
        }
    }

    private void zzmy() {
        zzkN();
        Context context = zzlO().getContext();
        if (!zzaj.zzU(context)) {
            zzbJ("AnalyticsReceiver is not registered or is disabled. Register the receiver for reliable dispatching on non-Google Play devices. See http://goo.gl/8Rd3yj for instructions.");
        } else if (!zzak.zzV(context)) {
            zzbK("AnalyticsService is not registered or is disabled. Analytics service at risk of not starting. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!CampaignTrackingReceiver.zzU(context)) {
            zzbJ("CampaignTrackingReceiver is not registered, not exported or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        } else if (!CampaignTrackingService.zzV(context)) {
            zzbJ("CampaignTrackingService is not registered or is disabled. Installation campaign tracking is not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
    }

    /* Access modifiers changed, original: protected */
    public void onServiceConnected() {
        zzkN();
        if (!zzlS().zzmW()) {
            zzmD();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void start() {
        zzma();
        zzaa.zza(!this.mStarted, "Analytics backend already started");
        this.mStarted = true;
        zzlT().zzf(new C20873());
    }

    public void zzR(boolean z) {
        zzmG();
    }

    public long zza(zzh zzh, boolean z) {
        zzaa.zzz(zzh);
        zzma();
        zzkN();
        try {
            this.zzWT.beginTransaction();
            this.zzWT.zza(zzh.zzmi(), zzh.zzku());
            long zza = this.zzWT.zza(zzh.zzmi(), zzh.zzku(), zzh.zzmj());
            if (z) {
                zzh.zzp(1 + zza);
            } else {
                zzh.zzp(zza);
            }
            this.zzWT.zzb(zzh);
            this.zzWT.setTransactionSuccessful();
            try {
                this.zzWT.endTransaction();
                return zza;
            } catch (SQLiteException e) {
                zze("Failed to end transaction", e);
                return zza;
            }
        } catch (SQLiteException e2) {
            zze("Failed to update Analytics property", e2);
            try {
                this.zzWT.endTransaction();
            } catch (SQLiteException e22) {
                zze("Failed to end transaction", e22);
            }
            return -1;
        } catch (Throwable th) {
            try {
                this.zzWT.endTransaction();
            } catch (SQLiteException e3) {
                zze("Failed to end transaction", e3);
            }
            throw th;
        }
    }

    public void zza(zzab zzab) {
        zzaa.zzz(zzab);
        zzi.zzkN();
        zzma();
        if (this.zzXc) {
            zzbH("Hit delivery not possible. Missing network permissions. See http://goo.gl/8Rd3yj for instructions");
        } else {
            zza("Delivering hit", zzab);
        }
        zzab zzf = zzf(zzab);
        zzmC();
        if (this.zzWW.zzb(zzf)) {
            zzbH("Hit sent to the device AnalyticsService for delivery");
        } else if (zzlS().zzmW()) {
            zzlR().zza(zzf, "Service unavailable on package side");
        } else {
            try {
                this.zzWT.zzc(zzf);
                zzmG();
            } catch (SQLiteException e) {
                zze("Delivery failed to save hit to a database", e);
                zzlR().zza(zzf, "deliver: failed to insert hit to database");
            }
        }
    }

    public void zza(final zzw zzw, final long j) {
        zzi.zzkN();
        zzma();
        long j2 = -1;
        long zzoj = zzlV().zzoj();
        if (zzoj != 0) {
            j2 = Math.abs(zzlQ().currentTimeMillis() - zzoj);
        }
        zzb("Dispatching local hits. Elapsed time since last dispatch (ms)", Long.valueOf(j2));
        if (!zzlS().zzmW()) {
            zzmC();
        }
        try {
            if (zzmE()) {
                zzlT().zzf(new Runnable() {
                    public void run() {
                        zzl.this.zza(zzw, j);
                    }
                });
                return;
            }
            zzlV().zzok();
            zzmG();
            if (zzw != null) {
                zzw.zzd(null);
            }
            if (this.zzXb != j) {
                this.zzWV.zzoc();
            }
        } catch (Throwable th) {
            zze("Local dispatch failed", th);
            zzlV().zzok();
            zzmG();
            if (zzw != null) {
                zzw.zzd(th);
            }
        }
    }

    public void zzb(zzw zzw) {
        zza(zzw, this.zzXb);
    }

    public void zzbO(String str) {
        zzaa.zzdl(str);
        zzkN();
        zzlP();
        zzlo zza = zzao.zza(zzlR(), str);
        if (zza == null) {
            zzd("Parsing failed. Ignoring invalid campaign data", str);
            return;
        }
        String zzol = zzlV().zzol();
        if (str.equals(zzol)) {
            zzbJ("Ignoring duplicate install campaign");
        } else if (TextUtils.isEmpty(zzol)) {
            zzlV().zzbT(str);
            if (zzlV().zzoi().zzx(zzlS().zznB())) {
                zzd("Campaign received too late, ignoring", zza);
                return;
            }
            zzb("Received installation campaign", zza);
            for (zzh zza2 : this.zzWT.zzt(0)) {
                zza(zza2, zza);
            }
        } else {
            zzd("Ignoring multiple install campaigns. original, new", zzol, str);
        }
    }

    /* Access modifiers changed, original: protected */
    public void zzc(zzh zzh) {
        zzkN();
        zzb("Sending first hit to property", zzh.zzmj());
        if (!zzlV().zzoi().zzx(zzlS().zznB())) {
            String zzol = zzlV().zzol();
            if (!TextUtils.isEmpty(zzol)) {
                zzlo zza = zzao.zza(zzlR(), zzol);
                zzb("Found relevant installation campaign", zza);
                zza(zzh, zza);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public zzab zzf(zzab zzab) {
        if (!TextUtils.isEmpty(zzab.zznX())) {
            return zzab;
        }
        Pair zzop = zzlV().zzom().zzop();
        if (zzop == null) {
            return zzab;
        }
        Long l = (Long) zzop.second;
        String str = (String) zzop.first;
        String valueOf = String.valueOf(l);
        valueOf = new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(str).length()).append(valueOf).append(":").append(str).toString();
        HashMap hashMap = new HashMap(zzab.zzm());
        hashMap.put("_m", valueOf);
        return zzab.zza(this, zzab, hashMap);
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
        this.zzWT.initialize();
        this.zzWU.initialize();
        this.zzWW.initialize();
    }

    public void zzlI() {
        zzi.zzkN();
        zzma();
        if (!zzlS().zzmW()) {
            zzbG("Delete all hits from local store");
            try {
                this.zzWT.zzmq();
                this.zzWT.zzmr();
                zzmG();
            } catch (SQLiteException e) {
                zzd("Failed to delete hits from store", e);
            }
        }
        zzmC();
        if (this.zzWW.zzmm()) {
            zzbG("Device service unavailable. Can't clear hits stored on the device service.");
        }
    }

    public void zzlL() {
        zzi.zzkN();
        zzma();
        zzbG("Service disconnected");
    }

    /* Access modifiers changed, original: 0000 */
    public void zzlN() {
        zzkN();
        this.zzXb = zzlQ().currentTimeMillis();
    }

    /* Access modifiers changed, original: protected */
    public void zzmC() {
        if (!this.zzXc && zzlS().zzmY() && !this.zzWW.isConnected()) {
            if (this.zzXa.zzx(zzlS().zznt())) {
                this.zzXa.start();
                zzbG("Connecting to service");
                if (this.zzWW.connect()) {
                    zzbG("Connected to service");
                    this.zzXa.clear();
                    onServiceConnected();
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0062 A:{LOOP_START, LOOP:1: B:18:0x0062->B:17:?} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0048 A:{SYNTHETIC} */
    public void zzmD() {
        /*
        r6 = this;
        com.google.android.gms.analytics.zzi.zzkN();
        r6.zzma();
        r6.zzlP();
        r0 = r6.zzlS();
        r0 = r0.zzmY();
        if (r0 != 0) goto L_0x0018;
    L_0x0013:
        r0 = "Service client disabled. Can't dispatch local hits to device AnalyticsService";
        r6.zzbJ(r0);
    L_0x0018:
        r0 = r6.zzWW;
        r0 = r0.isConnected();
        if (r0 != 0) goto L_0x0026;
    L_0x0020:
        r0 = "Service not connected";
        r6.zzbG(r0);
    L_0x0025:
        return;
    L_0x0026:
        r0 = r6.zzWT;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0025;
    L_0x002e:
        r0 = "Dispatching local hits to device AnalyticsService";
        r6.zzbG(r0);
    L_0x0033:
        r0 = r6.zzWT;	 Catch:{ SQLiteException -> 0x004c }
        r1 = r6.zzlS();	 Catch:{ SQLiteException -> 0x004c }
        r1 = r1.zznh();	 Catch:{ SQLiteException -> 0x004c }
        r2 = (long) r1;	 Catch:{ SQLiteException -> 0x004c }
        r1 = r0.zzr(r2);	 Catch:{ SQLiteException -> 0x004c }
        r0 = r1.isEmpty();	 Catch:{ SQLiteException -> 0x004c }
        if (r0 == 0) goto L_0x0062;
    L_0x0048:
        r6.zzmG();	 Catch:{ SQLiteException -> 0x004c }
        goto L_0x0025;
    L_0x004c:
        r0 = move-exception;
        r1 = "Failed to read hits from store";
        r6.zze(r1, r0);
        r6.zzmK();
        goto L_0x0025;
    L_0x0056:
        r1.remove(r0);
        r2 = r6.zzWT;	 Catch:{ SQLiteException -> 0x007b }
        r4 = r0.zznS();	 Catch:{ SQLiteException -> 0x007b }
        r2.zzs(r4);	 Catch:{ SQLiteException -> 0x007b }
    L_0x0062:
        r0 = r1.isEmpty();
        if (r0 != 0) goto L_0x0033;
    L_0x0068:
        r0 = 0;
        r0 = r1.get(r0);
        r0 = (com.google.android.gms.analytics.internal.zzab) r0;
        r2 = r6.zzWW;
        r2 = r2.zzb(r0);
        if (r2 != 0) goto L_0x0056;
    L_0x0077:
        r6.zzmG();
        goto L_0x0025;
    L_0x007b:
        r0 = move-exception;
        r1 = "Failed to remove hit that was send for delivery";
        r6.zze(r1, r0);
        r6.zzmK();
        goto L_0x0025;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzl.zzmD():void");
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing block: B:32:0x00a8, code skipped:
            zzd("Database contains successfully uploaded hit", java.lang.Long.valueOf(r4), java.lang.Integer.valueOf(r8.size()));
            zzmK();
     */
    public boolean zzmE() {
        /*
        r12 = this;
        r1 = 1;
        r2 = 0;
        com.google.android.gms.analytics.zzi.zzkN();
        r12.zzma();
        r0 = "Dispatching a batch of local hits";
        r12.zzbG(r0);
        r0 = r12.zzWW;
        r0 = r0.isConnected();
        if (r0 != 0) goto L_0x0032;
    L_0x0015:
        r0 = r12.zzlS();
        r0 = r0.zzmW();
        if (r0 != 0) goto L_0x0032;
    L_0x001f:
        r0 = r1;
    L_0x0020:
        r3 = r12.zzWU;
        r3 = r3.zzod();
        if (r3 != 0) goto L_0x0034;
    L_0x0028:
        if (r0 == 0) goto L_0x0036;
    L_0x002a:
        if (r1 == 0) goto L_0x0036;
    L_0x002c:
        r0 = "No network or service available. Will retry later";
        r12.zzbG(r0);
    L_0x0031:
        return r2;
    L_0x0032:
        r0 = r2;
        goto L_0x0020;
    L_0x0034:
        r1 = r2;
        goto L_0x0028;
    L_0x0036:
        r0 = r12.zzlS();
        r0 = r0.zznh();
        r1 = r12.zzlS();
        r1 = r1.zzni();
        r0 = java.lang.Math.max(r0, r1);
        r6 = (long) r0;
        r3 = new java.util.ArrayList;
        r3.<init>();
        r4 = 0;
    L_0x0052:
        r0 = r12.zzWT;	 Catch:{ all -> 0x01eb }
        r0.beginTransaction();	 Catch:{ all -> 0x01eb }
        r3.clear();	 Catch:{ all -> 0x01eb }
        r0 = r12.zzWT;	 Catch:{ SQLiteException -> 0x00d3 }
        r8 = r0.zzr(r6);	 Catch:{ SQLiteException -> 0x00d3 }
        r0 = r8.isEmpty();	 Catch:{ SQLiteException -> 0x00d3 }
        if (r0 == 0) goto L_0x0083;
    L_0x0066:
        r0 = "Store is empty, nothing to dispatch";
        r12.zzbG(r0);	 Catch:{ SQLiteException -> 0x00d3 }
        r12.zzmK();	 Catch:{ SQLiteException -> 0x00d3 }
        r0 = r12.zzWT;	 Catch:{ SQLiteException -> 0x0079 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x0079 }
        r0 = r12.zzWT;	 Catch:{ SQLiteException -> 0x0079 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x0079 }
        goto L_0x0031;
    L_0x0079:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzmK();
        goto L_0x0031;
    L_0x0083:
        r0 = "Hits loaded from store. count";
        r1 = r8.size();	 Catch:{ SQLiteException -> 0x00d3 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ SQLiteException -> 0x00d3 }
        r12.zza(r0, r1);	 Catch:{ SQLiteException -> 0x00d3 }
        r1 = r8.iterator();	 Catch:{ all -> 0x01eb }
    L_0x0094:
        r0 = r1.hasNext();	 Catch:{ all -> 0x01eb }
        if (r0 == 0) goto L_0x00f3;
    L_0x009a:
        r0 = r1.next();	 Catch:{ all -> 0x01eb }
        r0 = (com.google.android.gms.analytics.internal.zzab) r0;	 Catch:{ all -> 0x01eb }
        r10 = r0.zznS();	 Catch:{ all -> 0x01eb }
        r0 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1));
        if (r0 != 0) goto L_0x0094;
    L_0x00a8:
        r0 = "Database contains successfully uploaded hit";
        r1 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x01eb }
        r3 = r8.size();	 Catch:{ all -> 0x01eb }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ all -> 0x01eb }
        r12.zzd(r0, r1, r3);	 Catch:{ all -> 0x01eb }
        r12.zzmK();	 Catch:{ all -> 0x01eb }
        r0 = r12.zzWT;	 Catch:{ SQLiteException -> 0x00c8 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x00c8 }
        r0 = r12.zzWT;	 Catch:{ SQLiteException -> 0x00c8 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x00c8 }
        goto L_0x0031;
    L_0x00c8:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzmK();
        goto L_0x0031;
    L_0x00d3:
        r0 = move-exception;
        r1 = "Failed to read hits from persisted store";
        r12.zzd(r1, r0);	 Catch:{ all -> 0x01eb }
        r12.zzmK();	 Catch:{ all -> 0x01eb }
        r0 = r12.zzWT;	 Catch:{ SQLiteException -> 0x00e8 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x00e8 }
        r0 = r12.zzWT;	 Catch:{ SQLiteException -> 0x00e8 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x00e8 }
        goto L_0x0031;
    L_0x00e8:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzmK();
        goto L_0x0031;
    L_0x00f3:
        r0 = r12.zzWW;	 Catch:{ all -> 0x01eb }
        r0 = r0.isConnected();	 Catch:{ all -> 0x01eb }
        if (r0 == 0) goto L_0x0202;
    L_0x00fb:
        r0 = r12.zzlS();	 Catch:{ all -> 0x01eb }
        r0 = r0.zzmW();	 Catch:{ all -> 0x01eb }
        if (r0 != 0) goto L_0x0202;
    L_0x0105:
        r0 = "Service connected, sending hits to the service";
        r12.zzbG(r0);	 Catch:{ all -> 0x01eb }
    L_0x010a:
        r0 = r8.isEmpty();	 Catch:{ all -> 0x01eb }
        if (r0 != 0) goto L_0x0202;
    L_0x0110:
        r0 = 0;
        r0 = r8.get(r0);	 Catch:{ all -> 0x01eb }
        r0 = (com.google.android.gms.analytics.internal.zzab) r0;	 Catch:{ all -> 0x01eb }
        r1 = r12.zzWW;	 Catch:{ all -> 0x01eb }
        r1 = r1.zzb(r0);	 Catch:{ all -> 0x01eb }
        if (r1 != 0) goto L_0x0148;
    L_0x011f:
        r0 = r4;
    L_0x0120:
        r4 = r12.zzWU;	 Catch:{ all -> 0x01eb }
        r4 = r4.zzod();	 Catch:{ all -> 0x01eb }
        if (r4 == 0) goto L_0x0196;
    L_0x0128:
        r4 = r12.zzWU;	 Catch:{ all -> 0x01eb }
        r8 = r4.zzs(r8);	 Catch:{ all -> 0x01eb }
        r9 = r8.iterator();	 Catch:{ all -> 0x01eb }
        r4 = r0;
    L_0x0133:
        r0 = r9.hasNext();	 Catch:{ all -> 0x01eb }
        if (r0 == 0) goto L_0x018d;
    L_0x0139:
        r0 = r9.next();	 Catch:{ all -> 0x01eb }
        r0 = (java.lang.Long) r0;	 Catch:{ all -> 0x01eb }
        r0 = r0.longValue();	 Catch:{ all -> 0x01eb }
        r4 = java.lang.Math.max(r4, r0);	 Catch:{ all -> 0x01eb }
        goto L_0x0133;
    L_0x0148:
        r10 = r0.zznS();	 Catch:{ all -> 0x01eb }
        r4 = java.lang.Math.max(r4, r10);	 Catch:{ all -> 0x01eb }
        r8.remove(r0);	 Catch:{ all -> 0x01eb }
        r1 = "Hit sent do device AnalyticsService for delivery";
        r12.zzb(r1, r0);	 Catch:{ all -> 0x01eb }
        r1 = r12.zzWT;	 Catch:{ SQLiteException -> 0x016d }
        r10 = r0.zznS();	 Catch:{ SQLiteException -> 0x016d }
        r1.zzs(r10);	 Catch:{ SQLiteException -> 0x016d }
        r0 = r0.zznS();	 Catch:{ SQLiteException -> 0x016d }
        r0 = java.lang.Long.valueOf(r0);	 Catch:{ SQLiteException -> 0x016d }
        r3.add(r0);	 Catch:{ SQLiteException -> 0x016d }
        goto L_0x010a;
    L_0x016d:
        r0 = move-exception;
        r1 = "Failed to remove hit that was send for delivery";
        r12.zze(r1, r0);	 Catch:{ all -> 0x01eb }
        r12.zzmK();	 Catch:{ all -> 0x01eb }
        r0 = r12.zzWT;	 Catch:{ SQLiteException -> 0x0182 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x0182 }
        r0 = r12.zzWT;	 Catch:{ SQLiteException -> 0x0182 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x0182 }
        goto L_0x0031;
    L_0x0182:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzmK();
        goto L_0x0031;
    L_0x018d:
        r0 = r12.zzWT;	 Catch:{ SQLiteException -> 0x01b3 }
        r0.zzq(r8);	 Catch:{ SQLiteException -> 0x01b3 }
        r3.addAll(r8);	 Catch:{ SQLiteException -> 0x01b3 }
        r0 = r4;
    L_0x0196:
        r4 = r3.isEmpty();	 Catch:{ all -> 0x01eb }
        if (r4 == 0) goto L_0x01d3;
    L_0x019c:
        r0 = r12.zzWT;	 Catch:{ SQLiteException -> 0x01a8 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x01a8 }
        r0 = r12.zzWT;	 Catch:{ SQLiteException -> 0x01a8 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x01a8 }
        goto L_0x0031;
    L_0x01a8:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzmK();
        goto L_0x0031;
    L_0x01b3:
        r0 = move-exception;
        r1 = "Failed to remove successfully uploaded hits";
        r12.zze(r1, r0);	 Catch:{ all -> 0x01eb }
        r12.zzmK();	 Catch:{ all -> 0x01eb }
        r0 = r12.zzWT;	 Catch:{ SQLiteException -> 0x01c8 }
        r0.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x01c8 }
        r0 = r12.zzWT;	 Catch:{ SQLiteException -> 0x01c8 }
        r0.endTransaction();	 Catch:{ SQLiteException -> 0x01c8 }
        goto L_0x0031;
    L_0x01c8:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzmK();
        goto L_0x0031;
    L_0x01d3:
        r4 = r12.zzWT;	 Catch:{ SQLiteException -> 0x01e0 }
        r4.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x01e0 }
        r4 = r12.zzWT;	 Catch:{ SQLiteException -> 0x01e0 }
        r4.endTransaction();	 Catch:{ SQLiteException -> 0x01e0 }
        r4 = r0;
        goto L_0x0052;
    L_0x01e0:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzmK();
        goto L_0x0031;
    L_0x01eb:
        r0 = move-exception;
        r1 = r12.zzWT;	 Catch:{ SQLiteException -> 0x01f7 }
        r1.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x01f7 }
        r1 = r12.zzWT;	 Catch:{ SQLiteException -> 0x01f7 }
        r1.endTransaction();	 Catch:{ SQLiteException -> 0x01f7 }
        throw r0;
    L_0x01f7:
        r0 = move-exception;
        r1 = "Failed to commit local dispatch transaction";
        r12.zze(r1, r0);
        r12.zzmK();
        goto L_0x0031;
    L_0x0202:
        r0 = r4;
        goto L_0x0120;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzl.zzmE():boolean");
    }

    public void zzmF() {
        zzi.zzkN();
        zzma();
        zzbH("Sync dispatching local hits");
        long j = this.zzXb;
        if (!zzlS().zzmW()) {
            zzmC();
        }
        do {
            try {
            } catch (Throwable th) {
                zze("Sync local dispatch failed", th);
                zzmG();
                return;
            }
        } while (zzmE());
        zzlV().zzok();
        zzmG();
        if (this.zzXb != j) {
            this.zzWV.zzoc();
        }
    }

    public void zzmG() {
        zzlO().zzkN();
        zzma();
        if (!zzmH()) {
            this.zzWV.unregister();
            zzmK();
        } else if (this.zzWT.isEmpty()) {
            this.zzWV.unregister();
            zzmK();
        } else {
            boolean z;
            if (((Boolean) zzy.zzYm.get()).booleanValue()) {
                z = true;
            } else {
                this.zzWV.zzoa();
                z = this.zzWV.isConnected();
            }
            if (z) {
                zzmJ();
                return;
            }
            zzmK();
            zzmI();
        }
    }

    public long zzmN() {
        if (this.zzWX != Long.MIN_VALUE) {
            return this.zzWX;
        }
        return zzkx().zznN() ? ((long) zzkx().zzoE()) * 1000 : zzlS().zzne();
    }

    public void zzmO() {
        zzma();
        zzkN();
        this.zzXc = true;
        this.zzWW.disconnect();
        zzmG();
    }

    public long zzmt() {
        zzi.zzkN();
        zzma();
        try {
            return this.zzWT.zzmt();
        } catch (SQLiteException e) {
            zze("Failed to get min/max hit times from local store", e);
            return 0;
        }
    }

    /* Access modifiers changed, original: protected */
    public void zzmz() {
        zzma();
        if (!zzlS().zzmW()) {
            zzmy();
        }
        zzlV().zzoh();
        if (!zzbN("android.permission.ACCESS_NETWORK_STATE")) {
            zzbK("Missing required android.permission.ACCESS_NETWORK_STATE. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzmO();
        }
        if (!zzbN("android.permission.INTERNET")) {
            zzbK("Missing required android.permission.INTERNET. Google Analytics disabled. See http://goo.gl/8Rd3yj for instructions");
            zzmO();
        }
        if (zzak.zzV(getContext())) {
            zzbG("AnalyticsService registered in the app manifest and enabled");
        } else if (zzlS().zzmW()) {
            zzbK("Device AnalyticsService not registered! Hits will not be delivered reliably.");
        } else {
            zzbJ("AnalyticsService not registered in the app manifest. Hits might not be delivered reliably. See http://goo.gl/8Rd3yj for instructions.");
        }
        if (!(this.zzXc || zzlS().zzmW() || this.zzWT.isEmpty())) {
            zzmC();
        }
        zzmG();
    }

    public void zzu(long j) {
        zzi.zzkN();
        zzma();
        if (j < 0) {
            j = 0;
        }
        this.zzWX = j;
        zzmG();
    }
}
