package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.internal.zztd.zza;

@Deprecated
public class zztc implements zza {
    private final zztd zzbkK;

    public void zzIe() {
        this.zzbkK.stop();
    }

    public void zzIf() {
        Log.w("OneTimePlayLogger", "logger connection failed");
    }

    public void zzc(PendingIntent pendingIntent) {
        String valueOf = String.valueOf(pendingIntent);
        Log.w("OneTimePlayLogger", new StringBuilder(String.valueOf(valueOf).length() + 26).append("logger connection failed: ").append(valueOf).toString());
    }
}
