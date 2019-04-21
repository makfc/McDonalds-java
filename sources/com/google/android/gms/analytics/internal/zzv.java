package com.google.android.gms.analytics.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.internal.zzaa;

public class zzv extends zzd {
    private boolean zzXA;
    private boolean zzXB;
    private AlarmManager zzXC = ((AlarmManager) getContext().getSystemService("alarm"));

    protected zzv(zzf zzf) {
        super(zzf);
    }

    private PendingIntent zznH() {
        Intent intent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        intent.setComponent(new ComponentName(getContext(), "com.google.android.gms.analytics.AnalyticsReceiver"));
        return PendingIntent.getBroadcast(getContext(), 0, intent, 0);
    }

    public void cancel() {
        zzma();
        this.zzXB = false;
        this.zzXC.cancel(zznH());
    }

    public void schedule() {
        zzma();
        zzaa.zza(zznG(), "Receiver not registered");
        long zznf = zzlS().zznf();
        if (zznf > 0) {
            cancel();
            long elapsedRealtime = zzlQ().elapsedRealtime() + zznf;
            this.zzXB = true;
            this.zzXC.setInexactRepeating(2, elapsedRealtime, 0, zznH());
        }
    }

    public boolean zzbW() {
        return this.zzXB;
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
        try {
            this.zzXC.cancel(zznH());
            if (zzlS().zznf() > 0) {
                ActivityInfo receiverInfo = getContext().getPackageManager().getReceiverInfo(new ComponentName(getContext(), "com.google.android.gms.analytics.AnalyticsReceiver"), 2);
                if (receiverInfo != null && receiverInfo.enabled) {
                    zzbG("Receiver registered. Using alarm for local dispatch.");
                    this.zzXA = true;
                }
            }
        } catch (NameNotFoundException e) {
        }
    }

    public boolean zznG() {
        return this.zzXA;
    }
}
