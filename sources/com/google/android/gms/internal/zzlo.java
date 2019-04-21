package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import java.util.HashMap;

public final class zzlo extends zzg<zzlo> {
    private String mName;
    private String zzAj;
    private String zzBc;
    private String zzVg;
    private String zzVh;
    private String zzVi;
    private String zzVj;
    private String zzVk;
    private String zzVl;
    private String zzVm;

    public String getContent() {
        return this.zzAj;
    }

    public String getId() {
        return this.zzBc;
    }

    public String getName() {
        return this.mName;
    }

    public String getSource() {
        return this.zzVg;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("name", this.mName);
        hashMap.put("source", this.zzVg);
        hashMap.put("medium", this.zzVh);
        hashMap.put("keyword", this.zzVi);
        hashMap.put("content", this.zzAj);
        hashMap.put("id", this.zzBc);
        hashMap.put("adNetworkId", this.zzVj);
        hashMap.put("gclid", this.zzVk);
        hashMap.put("dclid", this.zzVl);
        hashMap.put("aclid", this.zzVm);
        return zzg.zzj(hashMap);
    }

    /* renamed from: zza */
    public void zzb(zzlo zzlo) {
        if (!TextUtils.isEmpty(this.mName)) {
            zzlo.setName(this.mName);
        }
        if (!TextUtils.isEmpty(this.zzVg)) {
            zzlo.zzbi(this.zzVg);
        }
        if (!TextUtils.isEmpty(this.zzVh)) {
            zzlo.zzbj(this.zzVh);
        }
        if (!TextUtils.isEmpty(this.zzVi)) {
            zzlo.zzbk(this.zzVi);
        }
        if (!TextUtils.isEmpty(this.zzAj)) {
            zzlo.zzbl(this.zzAj);
        }
        if (!TextUtils.isEmpty(this.zzBc)) {
            zzlo.zzbm(this.zzBc);
        }
        if (!TextUtils.isEmpty(this.zzVj)) {
            zzlo.zzbn(this.zzVj);
        }
        if (!TextUtils.isEmpty(this.zzVk)) {
            zzlo.zzbo(this.zzVk);
        }
        if (!TextUtils.isEmpty(this.zzVl)) {
            zzlo.zzbp(this.zzVl);
        }
        if (!TextUtils.isEmpty(this.zzVm)) {
            zzlo.zzbq(this.zzVm);
        }
    }

    public void zzbi(String str) {
        this.zzVg = str;
    }

    public void zzbj(String str) {
        this.zzVh = str;
    }

    public void zzbk(String str) {
        this.zzVi = str;
    }

    public void zzbl(String str) {
        this.zzAj = str;
    }

    public void zzbm(String str) {
        this.zzBc = str;
    }

    public void zzbn(String str) {
        this.zzVj = str;
    }

    public void zzbo(String str) {
        this.zzVk = str;
    }

    public void zzbp(String str) {
        this.zzVl = str;
    }

    public void zzbq(String str) {
        this.zzVm = str;
    }

    public String zzkX() {
        return this.zzVh;
    }

    public String zzkY() {
        return this.zzVi;
    }

    public String zzkZ() {
        return this.zzVj;
    }

    public String zzla() {
        return this.zzVk;
    }

    public String zzlb() {
        return this.zzVl;
    }

    public String zzlc() {
        return this.zzVm;
    }
}
