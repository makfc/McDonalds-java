package com.google.android.gms.analytics.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.zzaa;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzb extends zzd {
    private final zzl zzVY;

    /* renamed from: com.google.android.gms.analytics.internal.zzb$1 */
    class C20731 implements Runnable {
        final /* synthetic */ int zzVZ;
        final /* synthetic */ zzb zzWa;

        public void run() {
            this.zzWa.zzVY.zzu(((long) this.zzVZ) * 1000);
        }
    }

    /* renamed from: com.google.android.gms.analytics.internal.zzb$5 */
    class C20775 implements Runnable {
        final /* synthetic */ zzb zzWa;

        public void run() {
            this.zzWa.zzVY.zzlI();
        }
    }

    /* renamed from: com.google.android.gms.analytics.internal.zzb$7 */
    class C20797 implements Callable<Void> {
        C20797() {
        }

        /* renamed from: zzaV */
        public Void call() throws Exception {
            zzb.this.zzVY.zzmF();
            return null;
        }
    }

    public zzb(zzf zzf, zzg zzg) {
        super(zzf);
        zzaa.zzz(zzg);
        this.zzVY = zzg.zzj(zzf);
    }

    /* Access modifiers changed, original: 0000 */
    public void onServiceConnected() {
        zzkN();
        this.zzVY.onServiceConnected();
    }

    public void start() {
        this.zzVY.start();
    }

    public void zzR(final boolean z) {
        zza("Network connectivity status changed", Boolean.valueOf(z));
        zzlT().zzf(new Runnable() {
            public void run() {
                zzb.this.zzVY.zzR(z);
            }
        });
    }

    public long zza(zzh zzh) {
        zzma();
        zzaa.zzz(zzh);
        zzkN();
        long zza = this.zzVY.zza(zzh, true);
        if (zza == 0) {
            this.zzVY.zzc(zzh);
        }
        return zza;
    }

    public void zza(final zzab zzab) {
        zzaa.zzz(zzab);
        zzma();
        zzb("Hit delivery requested", zzab);
        zzlT().zzf(new Runnable() {
            public void run() {
                zzb.this.zzVY.zza(zzab);
            }
        });
    }

    public void zza(final zzw zzw) {
        zzma();
        zzlT().zzf(new Runnable() {
            public void run() {
                zzb.this.zzVY.zzb(zzw);
            }
        });
    }

    public void zza(final String str, final Runnable runnable) {
        zzaa.zzh(str, "campaign param can't be empty");
        zzlT().zzf(new Runnable() {
            public void run() {
                zzb.this.zzVY.zzbO(str);
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
        this.zzVY.initialize();
    }

    public void zzlJ() {
        zzma();
        Context context = getContext();
        if (zzaj.zzU(context) && zzak.zzV(context)) {
            Intent intent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            intent.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
            context.startService(intent);
            return;
        }
        zza(null);
    }

    public boolean zzlK() {
        zzma();
        try {
            zzlT().zzc(new C20797()).get(4, TimeUnit.SECONDS);
            return true;
        } catch (InterruptedException e) {
            zzd("syncDispatchLocalHits interrupted", e);
            return false;
        } catch (ExecutionException e2) {
            zze("syncDispatchLocalHits failed", e2);
            return false;
        } catch (TimeoutException e3) {
            zzd("syncDispatchLocalHits timed out", e3);
            return false;
        }
    }

    public void zzlL() {
        zzma();
        zzi.zzkN();
        this.zzVY.zzlL();
    }

    public void zzlM() {
        zzbG("Radio powered up");
        zzlJ();
    }

    /* Access modifiers changed, original: 0000 */
    public void zzlN() {
        zzkN();
        this.zzVY.zzlN();
    }
}
