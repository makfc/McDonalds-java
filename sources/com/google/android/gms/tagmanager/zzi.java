package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzi extends zzal {
    /* renamed from: ID */
    private static final String f6513ID = zzad.APP_VERSION_NAME.toString();
    private final Context mContext;

    public zzi(Context context) {
        super(f6513ID, new String[0]);
        this.mContext = context;
    }

    public boolean zzJf() {
        return true;
    }

    public zza zzV(Map<String, zza> map) {
        try {
            return zzdl.zzQ(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName);
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(this.mContext.getPackageName());
            String valueOf2 = String.valueOf(e.getMessage());
            zzbn.m7501e(new StringBuilder((String.valueOf(valueOf).length() + 25) + String.valueOf(valueOf2).length()).append("Package name ").append(valueOf).append(" not found. ").append(valueOf2).toString());
            return zzdl.zzKT();
        }
    }
}
