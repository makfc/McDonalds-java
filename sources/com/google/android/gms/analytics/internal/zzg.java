package com.google.android.gms.analytics.internal;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;

public class zzg {
    private final Context zzWy;
    private final Context zztm;

    public zzg(Context context) {
        zzaa.zzz(context);
        Object applicationContext = context.getApplicationContext();
        zzaa.zzb(applicationContext, (Object) "Application context can't be null");
        this.zztm = applicationContext;
        this.zzWy = applicationContext;
    }

    public Context getApplicationContext() {
        return this.zztm;
    }

    /* Access modifiers changed, original: protected */
    public zzi zzY(Context context) {
        return zzi.zzW(context);
    }

    /* Access modifiers changed, original: protected */
    public zzu zza(zzf zzf) {
        return new zzu(zzf);
    }

    /* Access modifiers changed, original: protected */
    public zzk zzb(zzf zzf) {
        return new zzk(zzf);
    }

    /* Access modifiers changed, original: protected */
    public zza zzc(zzf zzf) {
        return new zza(zzf);
    }

    /* Access modifiers changed, original: protected */
    public zzn zzd(zzf zzf) {
        return new zzn(zzf);
    }

    /* Access modifiers changed, original: protected */
    public zzap zze(zzf zzf) {
        return new zzap(zzf);
    }

    /* Access modifiers changed, original: protected */
    public zzaf zzf(zzf zzf) {
        return new zzaf(zzf);
    }

    /* Access modifiers changed, original: protected */
    public zzr zzg(zzf zzf) {
        return new zzr(zzf);
    }

    /* Access modifiers changed, original: protected */
    public zze zzh(zzf zzf) {
        return zzh.zzuW();
    }

    /* Access modifiers changed, original: protected */
    public GoogleAnalytics zzi(zzf zzf) {
        return new GoogleAnalytics(zzf);
    }

    /* Access modifiers changed, original: 0000 */
    public zzl zzj(zzf zzf) {
        return new zzl(zzf, this);
    }

    /* Access modifiers changed, original: 0000 */
    public zzag zzk(zzf zzf) {
        return new zzag(zzf);
    }

    /* Access modifiers changed, original: protected */
    public zzb zzl(zzf zzf) {
        return new zzb(zzf, this);
    }

    public zzj zzm(zzf zzf) {
        return new zzj(zzf);
    }

    public Context zzmc() {
        return this.zzWy;
    }

    public zzah zzn(zzf zzf) {
        return new zzah(zzf);
    }

    public zzi zzo(zzf zzf) {
        return new zzi(zzf);
    }

    public zzv zzp(zzf zzf) {
        return new zzv(zzf);
    }

    public zzai zzq(zzf zzf) {
        return new zzai(zzf);
    }
}
