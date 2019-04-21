package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;

public final class zzae extends zzg<zzw> {
    private static final zzae zzasq = new zzae();

    private zzae() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View zzb(Context context, int i, int i2, Scope[] scopeArr) throws zza {
        return zzasq.zzc(context, i, i2, scopeArr);
    }

    private View zzc(Context context, int i, int i2, Scope[] scopeArr) throws zza {
        try {
            SignInButtonConfig signInButtonConfig = new SignInButtonConfig(i, i2, scopeArr);
            return (View) zze.zzx(((zzw) zzaI(context)).zza(zze.zzD(context), signInButtonConfig));
        } catch (Exception e) {
            throw new zza("Could not get button with size " + i + " and color " + i2, e);
        }
    }

    /* renamed from: zzaZ */
    public zzw zzc(IBinder iBinder) {
        return zzw.zza.zzaY(iBinder);
    }
}
