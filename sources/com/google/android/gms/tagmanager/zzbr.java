package com.google.android.gms.tagmanager;

import android.content.Context;
import android.provider.Settings.Secure;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzbr extends zzal {
    /* renamed from: ID */
    private static final String f6495ID = zzad.MOBILE_ADWORDS_UNIQUE_ID.toString();
    private final Context mContext;

    public zzbr(Context context) {
        super(f6495ID, new String[0]);
        this.mContext = context;
    }

    public boolean zzJf() {
        return true;
    }

    public zza zzV(Map<String, zza> map) {
        String zzbi = zzbi(this.mContext);
        return zzbi == null ? zzdl.zzKT() : zzdl.zzQ(zzbi);
    }

    /* Access modifiers changed, original: protected */
    public String zzbi(Context context) {
        return Secure.getString(context.getContentResolver(), "android_id");
    }
}
