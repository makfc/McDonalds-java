package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.analytics.zzg;
import com.google.android.gms.common.internal.zzaa;
import com.mcdonalds.sdk.services.analytics.tagmanager.Parameters;
import java.util.HashMap;
import java.util.UUID;

public final class zzlx extends zzg<zzlx> {
    private String zzVG;
    private int zzVH;
    private int zzVI;
    private String zzVJ;
    private String zzVK;
    private boolean zzVL;
    private boolean zzVM;

    public zzlx() {
        this(false);
    }

    public zzlx(boolean z) {
        this(z, zzlx());
    }

    public zzlx(boolean z, int i) {
        zzaa.zzce(i);
        this.zzVH = i;
        this.zzVM = z;
    }

    private void zzlB() {
    }

    static int zzlx() {
        UUID randomUUID = UUID.randomUUID();
        int leastSignificantBits = (int) (randomUUID.getLeastSignificantBits() & 2147483647L);
        if (leastSignificantBits != 0) {
            return leastSignificantBits;
        }
        leastSignificantBits = (int) (randomUUID.getMostSignificantBits() & 2147483647L);
        if (leastSignificantBits != 0) {
            return leastSignificantBits;
        }
        Log.e("GAv4", "UUID.randomUUID() returned 0.");
        return Integer.MAX_VALUE;
    }

    public void setScreenName(String str) {
        zzlB();
        this.zzVG = str;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put(Parameters.SCREEN_NAME, this.zzVG);
        hashMap.put("interstitial", Boolean.valueOf(this.zzVL));
        hashMap.put("automatic", Boolean.valueOf(this.zzVM));
        hashMap.put("screenId", Integer.valueOf(this.zzVH));
        hashMap.put("referrerScreenId", Integer.valueOf(this.zzVI));
        hashMap.put("referrerScreenName", this.zzVJ);
        hashMap.put("referrerUri", this.zzVK);
        return zzg.zzj(hashMap);
    }

    public void zzP(boolean z) {
        zzlB();
        this.zzVM = z;
    }

    public void zzQ(boolean z) {
        zzlB();
        this.zzVL = z;
    }

    /* renamed from: zza */
    public void zzb(zzlx zzlx) {
        if (!TextUtils.isEmpty(this.zzVG)) {
            zzlx.setScreenName(this.zzVG);
        }
        if (this.zzVH != 0) {
            zzlx.zzas(this.zzVH);
        }
        if (this.zzVI != 0) {
            zzlx.zzat(this.zzVI);
        }
        if (!TextUtils.isEmpty(this.zzVJ)) {
            zzlx.zzby(this.zzVJ);
        }
        if (!TextUtils.isEmpty(this.zzVK)) {
            zzlx.zzbz(this.zzVK);
        }
        if (this.zzVL) {
            zzlx.zzQ(this.zzVL);
        }
        if (this.zzVM) {
            zzlx.zzP(this.zzVM);
        }
    }

    public void zzas(int i) {
        zzlB();
        this.zzVH = i;
    }

    public void zzat(int i) {
        zzlB();
        this.zzVI = i;
    }

    public void zzby(String str) {
        zzlB();
        this.zzVJ = str;
    }

    public void zzbz(String str) {
        zzlB();
        if (TextUtils.isEmpty(str)) {
            this.zzVK = null;
        } else {
            this.zzVK = str;
        }
    }

    public String zzlA() {
        return this.zzVK;
    }

    public String zzly() {
        return this.zzVG;
    }

    public int zzlz() {
        return this.zzVH;
    }
}
