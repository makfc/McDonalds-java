package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import com.newrelic.agent.android.agentdata.HexAttributes;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.HashMap;

public final class zzln extends zzg<zzln> {
    private String zzPx;
    private String zzVd;
    private String zzVe;
    private String zzVf;

    public void setAppId(String str) {
        this.zzPx = str;
    }

    public void setAppInstallerId(String str) {
        this.zzVf = str;
    }

    public void setAppName(String str) {
        this.zzVd = str;
    }

    public void setAppVersion(String str) {
        this.zzVe = str;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put(AnalyticAttribute.APP_NAME_ATTRIBUTE, this.zzVd);
        hashMap.put(HexAttributes.HEX_ATTR_APP_VERSION, this.zzVe);
        hashMap.put(AnalyticAttribute.APP_ID_ATTRIBUTE, this.zzPx);
        hashMap.put("appInstallerId", this.zzVf);
        return zzg.zzj(hashMap);
    }

    /* renamed from: zza */
    public void zzb(zzln zzln) {
        if (!TextUtils.isEmpty(this.zzVd)) {
            zzln.setAppName(this.zzVd);
        }
        if (!TextUtils.isEmpty(this.zzVe)) {
            zzln.setAppVersion(this.zzVe);
        }
        if (!TextUtils.isEmpty(this.zzPx)) {
            zzln.setAppId(this.zzPx);
        }
        if (!TextUtils.isEmpty(this.zzVf)) {
            zzln.setAppInstallerId(this.zzVf);
        }
    }

    public String zziC() {
        return this.zzPx;
    }

    public String zzkU() {
        return this.zzVd;
    }

    public String zzkV() {
        return this.zzVe;
    }

    public String zzkW() {
        return this.zzVf;
    }
}
