package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import java.util.HashMap;

public final class zzlz extends zzg<zzlz> {
    public String mCategory;
    public String zzVP;
    public long zzVQ;
    public String zzVu;

    public String getLabel() {
        return this.zzVu;
    }

    public long getTimeInMillis() {
        return this.zzVQ;
    }

    public void setTimeInMillis(long j) {
        this.zzVQ = j;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("variableName", this.zzVP);
        hashMap.put("timeInMillis", Long.valueOf(this.zzVQ));
        hashMap.put("category", this.mCategory);
        hashMap.put("label", this.zzVu);
        return zzg.zzj(hashMap);
    }

    /* renamed from: zza */
    public void zzb(zzlz zzlz) {
        if (!TextUtils.isEmpty(this.zzVP)) {
            zzlz.zzbC(this.zzVP);
        }
        if (this.zzVQ != 0) {
            zzlz.setTimeInMillis(this.zzVQ);
        }
        if (!TextUtils.isEmpty(this.mCategory)) {
            zzlz.zzbs(this.mCategory);
        }
        if (!TextUtils.isEmpty(this.zzVu)) {
            zzlz.zzbu(this.zzVu);
        }
    }

    public void zzbC(String str) {
        this.zzVP = str;
    }

    public void zzbs(String str) {
        this.mCategory = str;
    }

    public void zzbu(String str) {
        this.zzVu = str;
    }

    public String zzlD() {
        return this.zzVP;
    }

    public String zzlp() {
        return this.mCategory;
    }
}
