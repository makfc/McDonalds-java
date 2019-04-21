package com.google.android.gms.internal;

import android.os.Handler;
import java.util.concurrent.Executor;

public class zze implements zzn {
    private final Executor zzr;

    /* renamed from: com.google.android.gms.internal.zze$1 */
    class C22311 implements Executor {
        final /* synthetic */ Handler zzs;

        public void execute(Runnable runnable) {
            this.zzs.post(runnable);
        }
    }

    private class zza implements Runnable {
        private final zzk zzu;
        private final zzm zzv;
        private final Runnable zzw;

        public zza(zzk zzk, zzm zzm, Runnable runnable) {
            this.zzu = zzk;
            this.zzv = zzm;
            this.zzw = runnable;
        }

        public void run() {
            if (this.zzu.isCanceled()) {
                this.zzu.zzd("canceled-at-delivery");
                return;
            }
            if (this.zzv.isSuccess()) {
                this.zzu.zza(this.zzv.result);
            } else {
                this.zzu.zzc(this.zzv.zzag);
            }
            if (this.zzv.zzah) {
                this.zzu.zzc("intermediate-response");
            } else {
                this.zzu.zzd("done");
            }
            if (this.zzw != null) {
                this.zzw.run();
            }
        }
    }

    public void zza(zzk<?> zzk, zzm<?> zzm) {
        zza(zzk, zzm, null);
    }

    public void zza(zzk<?> zzk, zzm<?> zzm, Runnable runnable) {
        zzk.zzu();
        zzk.zzc("post-response");
        this.zzr.execute(new zza(zzk, zzm, runnable));
    }

    public void zza(zzk<?> zzk, zzr zzr) {
        zzk.zzc("post-error");
        this.zzr.execute(new zza(zzk, zzm.zzd(zzr), null));
    }
}
