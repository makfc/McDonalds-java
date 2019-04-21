package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import com.google.android.gms.common.internal.zzaa;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.HashMap;

public final class zzlw extends zzg<zzlw> {
    private String zzVA;
    private String zzVB;
    private boolean zzVC;
    private String zzVD;
    private boolean zzVE;
    private double zzVF;
    private String zzVy;
    private String zzVz;

    public String getUserId() {
        return this.zzVA;
    }

    public void setClientId(String str) {
        this.zzVz = str;
    }

    public void setSampleRate(double d) {
        boolean z = d >= 0.0d && d <= 100.0d;
        zzaa.zzb(z, (Object) "Sample rate must be between 0% and 100%");
        this.zzVF = d;
    }

    public void setUserId(String str) {
        this.zzVA = str;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("hitType", this.zzVy);
        hashMap.put("clientId", this.zzVz);
        hashMap.put(AnalyticAttribute.USER_ID_ATTRIBUTE, this.zzVA);
        hashMap.put("androidAdId", this.zzVB);
        hashMap.put("AdTargetingEnabled", Boolean.valueOf(this.zzVC));
        hashMap.put("sessionControl", this.zzVD);
        hashMap.put("nonInteraction", Boolean.valueOf(this.zzVE));
        hashMap.put("sampleRate", Double.valueOf(this.zzVF));
        return zzg.zzj(hashMap);
    }

    public void zzN(boolean z) {
        this.zzVC = z;
    }

    public void zzO(boolean z) {
        this.zzVE = z;
    }

    /* renamed from: zza */
    public void zzb(zzlw zzlw) {
        if (!TextUtils.isEmpty(this.zzVy)) {
            zzlw.zzbv(this.zzVy);
        }
        if (!TextUtils.isEmpty(this.zzVz)) {
            zzlw.setClientId(this.zzVz);
        }
        if (!TextUtils.isEmpty(this.zzVA)) {
            zzlw.setUserId(this.zzVA);
        }
        if (!TextUtils.isEmpty(this.zzVB)) {
            zzlw.zzbw(this.zzVB);
        }
        if (this.zzVC) {
            zzlw.zzN(true);
        }
        if (!TextUtils.isEmpty(this.zzVD)) {
            zzlw.zzbx(this.zzVD);
        }
        if (this.zzVE) {
            zzlw.zzO(this.zzVE);
        }
        if (this.zzVF != 0.0d) {
            zzlw.setSampleRate(this.zzVF);
        }
    }

    public void zzbv(String str) {
        this.zzVy = str;
    }

    public void zzbw(String str) {
        this.zzVB = str;
    }

    public void zzbx(String str) {
        this.zzVD = str;
    }

    public String zzku() {
        return this.zzVz;
    }

    public String zzlr() {
        return this.zzVy;
    }

    public String zzls() {
        return this.zzVB;
    }

    public boolean zzlt() {
        return this.zzVC;
    }

    public String zzlu() {
        return this.zzVD;
    }

    public boolean zzlv() {
        return this.zzVE;
    }

    public double zzlw() {
        return this.zzVF;
    }
}
