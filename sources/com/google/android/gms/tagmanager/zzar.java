package com.google.android.gms.tagmanager;

import android.text.TextUtils;

class zzar {
    private final long zzYC;
    private final long zzbpi;
    private final long zzbpj;
    private String zzbpk;

    zzar(long j, long j2, long j3) {
        this.zzbpi = j;
        this.zzYC = j2;
        this.zzbpj = j3;
    }

    /* Access modifiers changed, original: 0000 */
    public long zzJQ() {
        return this.zzbpi;
    }

    /* Access modifiers changed, original: 0000 */
    public long zzJR() {
        return this.zzbpj;
    }

    /* Access modifiers changed, original: 0000 */
    public String zzJS() {
        return this.zzbpk;
    }

    /* Access modifiers changed, original: 0000 */
    public void zzgz(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            this.zzbpk = str;
        }
    }
}
