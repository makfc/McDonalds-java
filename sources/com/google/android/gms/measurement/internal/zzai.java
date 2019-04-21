package com.google.android.gms.measurement.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zze;

public class zzai extends zzaa {
    private boolean zzXB;
    private final AlarmManager zzXC = ((AlarmManager) getContext().getSystemService("alarm"));

    protected zzai(zzx zzx) {
        super(zzx);
    }

    private PendingIntent zznH() {
        Intent className = new Intent().setClassName(getContext(), "com.google.android.gms.measurement.AppMeasurementReceiver");
        className.setAction("com.google.android.gms.measurement.UPLOAD");
        return PendingIntent.getBroadcast(getContext(), 0, className, 0);
    }

    public void cancel() {
        zzma();
        this.zzXB = false;
        this.zzXC.cancel(zznH());
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

    public /* bridge */ /* synthetic */ void zzkN() {
        super.zzkN();
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
        this.zzXC.cancel(zznH());
    }

    public /* bridge */ /* synthetic */ void zzlP() {
        super.zzlP();
    }

    public /* bridge */ /* synthetic */ zze zzlQ() {
        return super.zzlQ();
    }

    public void zzv(long j) {
        zzma();
        zzaa.zzaj(j > 0);
        zzaa.zza(zzu.zzU(getContext()), "Receiver not registered/enabled");
        zzaa.zza(zzae.zzV(getContext()), "Service not registered/enabled");
        cancel();
        long elapsedRealtime = zzlQ().elapsedRealtime() + j;
        this.zzXB = true;
        this.zzXC.setInexactRepeating(2, elapsedRealtime, Math.max(zzFo().zzEX(), j), zznH());
    }
}
