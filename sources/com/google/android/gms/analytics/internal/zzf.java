package com.google.android.gms.analytics.internal;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import java.lang.Thread.UncaughtExceptionHandler;

public class zzf {
    private static zzf zzWj;
    private final Context mContext;
    private final Context zzWk;
    private final zzr zzWl;
    private final zzaf zzWm;
    private final zzi zzWn;
    private final zzb zzWo;
    private final zzv zzWp;
    private final zzap zzWq;
    private final zzai zzWr;
    private final GoogleAnalytics zzWs;
    private final zzn zzWt;
    private final zza zzWu;
    private final zzk zzWv;
    private final zzu zzWw;
    private final zze zzsd;

    /* renamed from: com.google.android.gms.analytics.internal.zzf$1 */
    class C20801 implements UncaughtExceptionHandler {
        C20801() {
        }

        public void uncaughtException(Thread thread, Throwable th) {
            zzaf zzmd = zzf.this.zzmd();
            if (zzmd != null) {
                zzmd.zze("Job execution failed", th);
            }
        }
    }

    protected zzf(zzg zzg) {
        Object applicationContext = zzg.getApplicationContext();
        zzaa.zzb(applicationContext, (Object) "Application context can't be null");
        Context zzmc = zzg.zzmc();
        zzaa.zzz(zzmc);
        this.mContext = applicationContext;
        this.zzWk = zzmc;
        this.zzsd = zzg.zzh(this);
        this.zzWl = zzg.zzg(this);
        zzaf zzf = zzg.zzf(this);
        zzf.initialize();
        this.zzWm = zzf;
        String str;
        if (zzlS().zzmW()) {
            zzf = zzlR();
            str = zze.VERSION;
            zzf.zzbI(new StringBuilder(String.valueOf(str).length() + 33).append("Google Analytics ").append(str).append(" is starting up.").toString());
        } else {
            zzf = zzlR();
            str = zze.VERSION;
            zzf.zzbI(new StringBuilder(String.valueOf(str).length() + 134).append("Google Analytics ").append(str).append(" is starting up. To enable debug logging on a device run:\n  adb shell setprop log.tag.GAv4 DEBUG\n  adb logcat -s GAv4").toString());
        }
        zzai zzq = zzg.zzq(this);
        zzq.initialize();
        this.zzWr = zzq;
        zzap zze = zzg.zze(this);
        zze.initialize();
        this.zzWq = zze;
        zzb zzl = zzg.zzl(this);
        zzn zzd = zzg.zzd(this);
        zza zzc = zzg.zzc(this);
        zzk zzb = zzg.zzb(this);
        zzu zza = zzg.zza(this);
        zzi zzY = zzg.zzY(applicationContext);
        zzY.zza(zzmb());
        this.zzWn = zzY;
        GoogleAnalytics zzi = zzg.zzi(this);
        zzd.initialize();
        this.zzWt = zzd;
        zzc.initialize();
        this.zzWu = zzc;
        zzb.initialize();
        this.zzWv = zzb;
        zza.initialize();
        this.zzWw = zza;
        zzv zzp = zzg.zzp(this);
        zzp.initialize();
        this.zzWp = zzp;
        zzl.initialize();
        this.zzWo = zzl;
        if (zzlS().zzmW()) {
            zzlR().zzb("Device AnalyticsService version", zze.VERSION);
        }
        zzi.initialize();
        this.zzWs = zzi;
        zzl.start();
    }

    public static zzf zzX(Context context) {
        zzaa.zzz(context);
        if (zzWj == null) {
            synchronized (zzf.class) {
                if (zzWj == null) {
                    zze zzuW = zzh.zzuW();
                    long elapsedRealtime = zzuW.elapsedRealtime();
                    zzf zzf = new zzf(new zzg(context));
                    zzWj = zzf;
                    GoogleAnalytics.zzkt();
                    elapsedRealtime = zzuW.elapsedRealtime() - elapsedRealtime;
                    long longValue = ((Long) zzy.zzYt.get()).longValue();
                    if (elapsedRealtime > longValue) {
                        zzf.zzlR().zzc("Slow initialization (ms)", Long.valueOf(elapsedRealtime), Long.valueOf(longValue));
                    }
                }
            }
        }
        return zzWj;
    }

    private void zza(zzd zzd) {
        zzaa.zzb((Object) zzd, (Object) "Analytics service not created/initialized");
        zzaa.zzb(zzd.isInitialized(), (Object) "Analytics service not initialized");
    }

    public Context getContext() {
        return this.mContext;
    }

    public void zzkN() {
        zzi.zzkN();
    }

    public zzb zzkw() {
        zza(this.zzWo);
        return this.zzWo;
    }

    public zzap zzkx() {
        zza(this.zzWq);
        return this.zzWq;
    }

    public zze zzlQ() {
        return this.zzsd;
    }

    public zzaf zzlR() {
        zza(this.zzWm);
        return this.zzWm;
    }

    public zzr zzlS() {
        return this.zzWl;
    }

    public zzi zzlT() {
        zzaa.zzz(this.zzWn);
        return this.zzWn;
    }

    public zzv zzlU() {
        zza(this.zzWp);
        return this.zzWp;
    }

    public zzai zzlV() {
        zza(this.zzWr);
        return this.zzWr;
    }

    public zzk zzlY() {
        zza(this.zzWv);
        return this.zzWv;
    }

    public zzu zzlZ() {
        return this.zzWw;
    }

    /* Access modifiers changed, original: protected */
    public UncaughtExceptionHandler zzmb() {
        return new C20801();
    }

    public Context zzmc() {
        return this.zzWk;
    }

    public zzaf zzmd() {
        return this.zzWm;
    }

    public GoogleAnalytics zzme() {
        zzaa.zzz(this.zzWs);
        zzaa.zzb(this.zzWs.isInitialized(), (Object) "Analytics instance not initialized");
        return this.zzWs;
    }

    public zzai zzmf() {
        return (this.zzWr == null || !this.zzWr.isInitialized()) ? null : this.zzWr;
    }

    public zza zzmg() {
        zza(this.zzWu);
        return this.zzWu;
    }

    public zzn zzmh() {
        zza(this.zzWt);
        return this.zzWt;
    }
}
