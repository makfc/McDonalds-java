package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import com.mcdonalds.sdk.services.analytics.tagmanager.Parameters;
import java.util.HashMap;

public final class zzlu extends zzg<zzlu> {
    private String mCategory;
    private String zzVt;
    private String zzVu;
    private long zzVv;

    public String getAction() {
        return this.zzVt;
    }

    public String getLabel() {
        return this.zzVu;
    }

    public long getValue() {
        return this.zzVv;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("category", this.mCategory);
        hashMap.put(Parameters.ACTION, this.zzVt);
        hashMap.put("label", this.zzVu);
        hashMap.put("value", Long.valueOf(this.zzVv));
        return zzg.zzj(hashMap);
    }

    /* renamed from: zza */
    public void zzb(zzlu zzlu) {
        if (!TextUtils.isEmpty(this.mCategory)) {
            zzlu.zzbs(this.mCategory);
        }
        if (!TextUtils.isEmpty(this.zzVt)) {
            zzlu.zzbt(this.zzVt);
        }
        if (!TextUtils.isEmpty(this.zzVu)) {
            zzlu.zzbu(this.zzVu);
        }
        if (this.zzVv != 0) {
            zzlu.zzo(this.zzVv);
        }
    }

    public void zzbs(String str) {
        this.mCategory = str;
    }

    public void zzbt(String str) {
        this.zzVt = str;
    }

    public void zzbu(String str) {
        this.zzVu = str;
    }

    public String zzlp() {
        return this.mCategory;
    }

    public void zzo(long j) {
        this.zzVv = j;
    }
}
