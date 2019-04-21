package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzh extends zzal {
    /* renamed from: ID */
    private static final String f6512ID = zzad.APP_VERSION.toString();
    private final Context mContext;

    public zzh(Context context) {
        super(f6512ID, new String[0]);
        this.mContext = context;
    }

    public boolean zzJf() {
        return true;
    }

    public zza zzV(Map<String, zza> map) {
        try {
            return zzdl.zzQ(Integer.valueOf(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionCode));
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(this.mContext.getPackageName());
            String valueOf2 = String.valueOf(e.getMessage());
            zzbn.m7501e(new StringBuilder((String.valueOf(valueOf).length() + 25) + String.valueOf(valueOf2).length()).append("Package name ").append(valueOf).append(" not found. ").append(valueOf2).toString());
            return zzdl.zzKT();
        }
    }
}
