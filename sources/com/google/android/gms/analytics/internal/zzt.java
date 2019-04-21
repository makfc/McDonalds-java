package com.google.android.gms.analytics.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.internal.zzaa;

abstract class zzt {
    private static volatile Handler zzXx;
    private final zzf zzWg;
    private volatile long zzXy;
    private final Runnable zzw = new C20811();

    /* renamed from: com.google.android.gms.analytics.internal.zzt$1 */
    class C20811 implements Runnable {
        C20811() {
        }

        public void run() {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                zzt.this.zzWg.zzlT().zzf(this);
                return;
            }
            boolean zzbW = zzt.this.zzbW();
            zzt.this.zzXy = 0;
            if (zzbW && !false) {
                zzt.this.run();
            }
        }
    }

    zzt(zzf zzf) {
        zzaa.zzz(zzf);
        this.zzWg = zzf;
    }

    private Handler getHandler() {
        if (zzXx != null) {
            return zzXx;
        }
        Handler handler;
        synchronized (zzt.class) {
            if (zzXx == null) {
                zzXx = new Handler(this.zzWg.getContext().getMainLooper());
            }
            handler = zzXx;
        }
        return handler;
    }

    public void cancel() {
        this.zzXy = 0;
        getHandler().removeCallbacks(this.zzw);
    }

    public abstract void run();

    public boolean zzbW() {
        return this.zzXy != 0;
    }

    public long zznD() {
        return this.zzXy == 0 ? 0 : Math.abs(this.zzWg.zzlQ().currentTimeMillis() - this.zzXy);
    }

    public void zzv(long j) {
        cancel();
        if (j >= 0) {
            this.zzXy = this.zzWg.zzlQ().currentTimeMillis();
            if (!getHandler().postDelayed(this.zzw, j)) {
                this.zzWg.zzlR().zze("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }

    public void zzw(long j) {
        long j2 = 0;
        if (!zzbW()) {
            return;
        }
        if (j < 0) {
            cancel();
            return;
        }
        long abs = j - Math.abs(this.zzWg.zzlQ().currentTimeMillis() - this.zzXy);
        if (abs >= 0) {
            j2 = abs;
        }
        getHandler().removeCallbacks(this.zzw);
        if (!getHandler().postDelayed(this.zzw, j2)) {
            this.zzWg.zzlR().zze("Failed to adjust delayed post. time", Long.valueOf(j2));
        }
    }
}
