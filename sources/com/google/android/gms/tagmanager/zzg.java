package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzg extends zzal {
    /* renamed from: ID */
    private static final String f6511ID = zzad.APP_NAME.toString();
    private final Context mContext;

    public zzg(Context context) {
        super(f6511ID, new String[0]);
        this.mContext = context;
    }

    public boolean zzJf() {
        return true;
    }

    public zza zzV(Map<String, zza> map) {
        try {
            PackageManager packageManager = this.mContext.getPackageManager();
            return zzdl.zzQ(packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.mContext.getPackageName(), 0)).toString());
        } catch (NameNotFoundException e) {
            zzbn.zzb("App name is not found.", e);
            return zzdl.zzKT();
        }
    }
}
