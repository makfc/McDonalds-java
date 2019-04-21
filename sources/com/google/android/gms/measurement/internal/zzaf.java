package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.zze;

public class zzaf extends zzaa {
    private Handler mHandler;
    private long zzbfQ;
    private final Runnable zzbfR = new C26881();
    private final zzf zzbfS = new zzf(this.zzbbl) {
        @WorkerThread
        public void run() {
            zzaf.this.zzGK();
        }
    };
    private final zzf zzbfT = new zzf(this.zzbbl) {
        @WorkerThread
        public void run() {
            zzaf.this.zzGL();
        }
    };

    /* renamed from: com.google.android.gms.measurement.internal.zzaf$1 */
    class C26881 implements Runnable {

        /* renamed from: com.google.android.gms.measurement.internal.zzaf$1$1 */
        class C26871 implements Runnable {
            C26871() {
            }

            public void run() {
                zzaf.this.zzGJ();
            }
        }

        C26881() {
        }

        @MainThread
        public void run() {
            zzaf.this.zzFl().zzg(new C26871());
        }
    }

    zzaf(zzx zzx) {
        super(zzx);
    }

    private void zzGH() {
        synchronized (this) {
            if (this.mHandler == null) {
                this.mHandler = new Handler(Looper.getMainLooper());
            }
        }
    }

    @WorkerThread
    private void zzGK() {
        zzkN();
        zzFm().zzFL().zzj("Session started, time", Long.valueOf(zzlQ().elapsedRealtime()));
        zzFn().zzbdR.set(false);
        zzFd().zze("auto", "_s", new Bundle());
    }

    @WorkerThread
    private void zzGL() {
        zzkN();
        long elapsedRealtime = zzlQ().elapsedRealtime();
        if (this.zzbfQ == 0) {
            this.zzbfQ = elapsedRealtime - 3600000;
        }
        long j = zzFn().zzbdT.get() + (elapsedRealtime - this.zzbfQ);
        zzFn().zzbdT.set(j);
        zzFm().zzFL().zzj("Recording user engagement, ms", Long.valueOf(j));
        Bundle bundle = new Bundle();
        bundle.putLong("_et", j);
        zzFd().zze("auto", "_e", bundle);
        zzFn().zzbdT.set(0);
        this.zzbfQ = elapsedRealtime;
        this.zzbfT.zzv(Math.max(0, 3600000 - zzFn().zzbdT.get()));
    }

    @WorkerThread
    private void zzaj(long j) {
        zzkN();
        zzGH();
        this.zzbfS.cancel();
        this.zzbfT.cancel();
        zzFm().zzFL().zzj("Activity resumed, time", Long.valueOf(j));
        this.zzbfQ = j;
        if (zzlQ().currentTimeMillis() - zzFn().zzbdQ.get() > zzFn().zzbdS.get()) {
            zzFn().zzbdR.set(true);
            zzFn().zzbdT.set(0);
        }
        if (zzFn().zzbdR.get()) {
            this.zzbfS.zzv(Math.max(0, zzFn().zzbdP.get() - zzFn().zzbdT.get()));
        } else {
            this.zzbfT.zzv(Math.max(0, 3600000 - zzFn().zzbdT.get()));
        }
    }

    @WorkerThread
    private void zzak(long j) {
        zzkN();
        zzGH();
        this.zzbfS.cancel();
        this.zzbfT.cancel();
        zzFm().zzFL().zzj("Activity paused, time", Long.valueOf(j));
        if (this.zzbfQ != 0) {
            zzFn().zzbdT.set(zzFn().zzbdT.get() + (j - this.zzbfQ));
        }
        zzFn().zzbdS.set(zzlQ().currentTimeMillis());
        synchronized (this) {
            if (!zzFn().zzbdR.get()) {
                this.mHandler.postDelayed(this.zzbfR, 1000);
            }
        }
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public /* bridge */ /* synthetic */ void zzFb() {
        super.zzFb();
    }

    public /* bridge */ /* synthetic */ zzc zzFc() {
        return super.zzFc();
    }

    public /* bridge */ /* synthetic */ zzac zzFd() {
        return super.zzFd();
    }

    public /* bridge */ /* synthetic */ zzn zzFe() {
        return super.zzFe();
    }

    public /* bridge */ /* synthetic */ zzg zzFf() {
        return super.zzFf();
    }

    public /* bridge */ /* synthetic */ zzad zzFg() {
        return super.zzFg();
    }

    public /* bridge */ /* synthetic */ zze zzFh() {
        return super.zzFh();
    }

    public /* bridge */ /* synthetic */ zzal zzFi() {
        return super.zzFi();
    }

    public /* bridge */ /* synthetic */ zzv zzFj() {
        return super.zzFj();
    }

    public /* bridge */ /* synthetic */ zzaf zzFk() {
        return super.zzFk();
    }

    public /* bridge */ /* synthetic */ zzw zzFl() {
        return super.zzFl();
    }

    public /* bridge */ /* synthetic */ zzp zzFm() {
        return super.zzFm();
    }

    public /* bridge */ /* synthetic */ zzt zzFn() {
        return super.zzFn();
    }

    public /* bridge */ /* synthetic */ zzd zzFo() {
        return super.zzFo();
    }

    /* Access modifiers changed, original: protected */
    @MainThread
    public void zzGG() {
        synchronized (this) {
            zzGH();
            this.mHandler.removeCallbacks(this.zzbfR);
        }
        final long elapsedRealtime = zzlQ().elapsedRealtime();
        zzFl().zzg(new Runnable() {
            public void run() {
                zzaf.this.zzaj(elapsedRealtime);
            }
        });
    }

    /* Access modifiers changed, original: protected */
    @MainThread
    public void zzGI() {
        final long elapsedRealtime = zzlQ().elapsedRealtime();
        zzFl().zzg(new Runnable() {
            public void run() {
                zzaf.this.zzak(elapsedRealtime);
            }
        });
    }

    @WorkerThread
    public void zzGJ() {
        zzkN();
        zzFm().zzFK().log("Application backgrounded. Logging engagement");
        long j = zzFn().zzbdT.get();
        if (j > 0) {
            Bundle bundle = new Bundle();
            bundle.putLong("_et", j);
            zzFd().zze("auto", "_e", bundle);
            zzFn().zzbdT.set(0);
            return;
        }
        zzFm().zzFG().zzj("Not logging non-positive engagement time", Long.valueOf(j));
    }

    public /* bridge */ /* synthetic */ void zzkN() {
        super.zzkN();
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
    }

    public /* bridge */ /* synthetic */ void zzlP() {
        super.zzlP();
    }

    public /* bridge */ /* synthetic */ zze zzlQ() {
        return super.zzlQ();
    }
}
