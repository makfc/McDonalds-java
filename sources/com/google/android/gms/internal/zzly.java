package com.google.android.gms.internal;

import android.text.TextUtils;
import com.amap.api.location.LocationManagerProxy;
import com.google.android.gms.analytics.zzg;
import com.mcdonalds.sdk.services.analytics.tagmanager.Parameters;
import java.util.HashMap;

public final class zzly extends zzg<zzly> {
    public String zzVN;
    public String zzVO;
    public String zzVt;

    public String getAction() {
        return this.zzVt;
    }

    public String getTarget() {
        return this.zzVO;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put(LocationManagerProxy.NETWORK_PROVIDER, this.zzVN);
        hashMap.put(Parameters.ACTION, this.zzVt);
        hashMap.put("target", this.zzVO);
        return zzg.zzj(hashMap);
    }

    /* renamed from: zza */
    public void zzb(zzly zzly) {
        if (!TextUtils.isEmpty(this.zzVN)) {
            zzly.zzbA(this.zzVN);
        }
        if (!TextUtils.isEmpty(this.zzVt)) {
            zzly.zzbt(this.zzVt);
        }
        if (!TextUtils.isEmpty(this.zzVO)) {
            zzly.zzbB(this.zzVO);
        }
    }

    public void zzbA(String str) {
        this.zzVN = str;
    }

    public void zzbB(String str) {
        this.zzVO = str;
    }

    public void zzbt(String str) {
        this.zzVt = str;
    }

    public String zzlC() {
        return this.zzVN;
    }
}
