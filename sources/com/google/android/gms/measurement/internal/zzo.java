package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzd.zzb;
import com.google.android.gms.common.internal.zzd.zzc;
import com.google.android.gms.measurement.internal.zzm.zza;

public class zzo extends zzd<zzm> {
    public zzo(Context context, Looper looper, zzb zzb, zzc zzc) {
        super(context, looper, 93, zzb, zzc, null);
    }

    /* renamed from: zzdw */
    public zzm zzab(IBinder iBinder) {
        return zza.zzdv(iBinder);
    }

    /* Access modifiers changed, original: protected */
    public String zzhT() {
        return "com.google.android.gms.measurement.START";
    }

    /* Access modifiers changed, original: protected */
    public String zzhU() {
        return "com.google.android.gms.measurement.internal.IMeasurementService";
    }
}
