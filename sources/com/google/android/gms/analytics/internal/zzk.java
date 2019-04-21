package com.google.android.gms.analytics.internal;

import com.google.android.gms.internal.zzln;

public class zzk extends zzd {
    private final zzln zzUD = new zzln();

    zzk(zzf zzf) {
        super(zzf);
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
        zzlT().zzkL().zzb(this.zzUD);
        zzks();
    }

    public void zzks() {
        zzap zzkx = zzkx();
        String zzkU = zzkx.zzkU();
        if (zzkU != null) {
            this.zzUD.setAppName(zzkU);
        }
        String zzkV = zzkx.zzkV();
        if (zzkV != null) {
            this.zzUD.setAppVersion(zzkV);
        }
    }

    public zzln zzmx() {
        zzma();
        return this.zzUD;
    }
}
