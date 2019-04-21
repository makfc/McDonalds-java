package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.internal.zzaa;

public class zzf {
    private static zzf zzakC;
    private final Context mContext;

    private zzf(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static zzf zzaq(Context context) {
        zzaa.zzz(context);
        synchronized (zzf.class) {
            if (zzakC == null) {
                zzd.zzah(context);
                zzakC = new zzf(context);
            }
        }
        return zzakC;
    }

    /* Access modifiers changed, original: varargs */
    public zza zza(PackageInfo packageInfo, zza... zzaArr) {
        if (packageInfo.signatures == null) {
            return null;
        }
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zzb zzb = new zzb(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < zzaArr.length; i++) {
            if (zzaArr[i].equals(zzb)) {
                return zzaArr[i];
            }
        }
        String valueOf = String.valueOf(packageInfo.packageName);
        String valueOf2 = String.valueOf(Base64.encodeToString(zzb.getBytes(), 0));
        Log.v("GoogleSignatureVerifier", new StringBuilder((String.valueOf(valueOf).length() + 31) + String.valueOf(valueOf2).length()).append(valueOf).append(" signature not valid.  Found: \n").append(valueOf2).toString());
        return null;
    }

    public boolean zza(PackageInfo packageInfo, boolean z) {
        if (!(packageInfo == null || packageInfo.signatures == null)) {
            zza zza;
            if (z) {
                zza = zza(packageInfo, zzd.zzakt);
            } else {
                zza = zza(packageInfo, zzd.zzakt[0]);
            }
            if (zza != null) {
                return true;
            }
        }
        return false;
    }

    public boolean zza(PackageManager packageManager, PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (zze.zzal(this.mContext)) {
            return zza(packageInfo, true);
        }
        boolean zza = zza(packageInfo, false);
        if (zza || !zza(packageInfo, true)) {
            return zza;
        }
        Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        return zza;
    }
}
