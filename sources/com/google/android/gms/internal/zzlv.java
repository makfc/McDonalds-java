package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import java.util.HashMap;

public final class zzlv extends zzg<zzlv> {
    public String zzVw;
    public boolean zzVx;

    public String getDescription() {
        return this.zzVw;
    }

    public void setDescription(String str) {
        this.zzVw = str;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("description", this.zzVw);
        hashMap.put("fatal", Boolean.valueOf(this.zzVx));
        return zzg.zzj(hashMap);
    }

    public void zzM(boolean z) {
        this.zzVx = z;
    }

    /* renamed from: zza */
    public void zzb(zzlv zzlv) {
        if (!TextUtils.isEmpty(this.zzVw)) {
            zzlv.setDescription(this.zzVw);
        }
        if (this.zzVx) {
            zzlv.zzM(this.zzVx);
        }
    }

    public boolean zzlq() {
        return this.zzVx;
    }
}
