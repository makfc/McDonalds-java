package com.google.android.gms.internal;

import android.os.Process;
import com.google.android.gms.internal.zzb.zza;
import java.util.concurrent.BlockingQueue;

public class zzc extends Thread {
    private static final boolean DEBUG = zzs.DEBUG;
    private final BlockingQueue<zzk<?>> zzg;
    private final BlockingQueue<zzk<?>> zzh;
    private final zzb zzi;
    private final zzn zzj;
    private volatile boolean zzk;

    public void run() {
        if (DEBUG) {
            zzs.zza("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.zzi.initialize();
        while (true) {
            try {
                final zzk zzk = (zzk) this.zzg.take();
                zzk.zzc("cache-queue-take");
                if (zzk.isCanceled()) {
                    zzk.zzd("cache-discard-canceled");
                } else {
                    zza zza = this.zzi.zza(zzk.zzg());
                    if (zza == null) {
                        zzk.zzc("cache-miss");
                        this.zzh.put(zzk);
                    } else if (zza.zza()) {
                        zzk.zzc("cache-hit-expired");
                        zzk.zza(zza);
                        this.zzh.put(zzk);
                    } else {
                        zzk.zzc("cache-hit");
                        zzm zza2 = zzk.zza(new zzi(zza.data, zza.zzf));
                        zzk.zzc("cache-hit-parsed");
                        if (zza.zzb()) {
                            zzk.zzc("cache-hit-refresh-needed");
                            zzk.zza(zza);
                            zza2.zzah = true;
                            this.zzj.zza(zzk, zza2, new Runnable() {
                                public void run() {
                                    try {
                                        zzc.this.zzh.put(zzk);
                                    } catch (InterruptedException e) {
                                    }
                                }
                            });
                        } else {
                            this.zzj.zza(zzk, zza2);
                        }
                    }
                }
            } catch (InterruptedException e) {
                if (this.zzk) {
                    return;
                }
            }
        }
    }
}
