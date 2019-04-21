package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzaa;
import java.util.Iterator;

public class zzh {
    final String mName;
    final String zzPx;
    final long zzajg;
    final String zzbch;
    final long zzbci;
    final EventParams zzbcj;

    zzh(zzx zzx, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        zzaa.zzdl(str2);
        zzaa.zzdl(str3);
        this.zzPx = str2;
        this.mName = str3;
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzbch = str;
        this.zzajg = j;
        this.zzbci = j2;
        if (this.zzbci != 0 && this.zzbci > this.zzajg) {
            zzx.zzFm().zzFG().log("Event created with reverse previous/current timestamps");
        }
        this.zzbcj = zza(zzx, bundle);
    }

    private zzh(zzx zzx, String str, String str2, String str3, long j, long j2, EventParams eventParams) {
        zzaa.zzdl(str2);
        zzaa.zzdl(str3);
        zzaa.zzz(eventParams);
        this.zzPx = str2;
        this.mName = str3;
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzbch = str;
        this.zzajg = j;
        this.zzbci = j2;
        if (this.zzbci != 0 && this.zzbci > this.zzajg) {
            zzx.zzFm().zzFG().log("Event created with reverse previous/current timestamps");
        }
        this.zzbcj = eventParams;
    }

    public String toString() {
        String str = this.zzPx;
        String str2 = this.mName;
        String valueOf = String.valueOf(this.zzbcj);
        return new StringBuilder(((String.valueOf(str).length() + 33) + String.valueOf(str2).length()) + String.valueOf(valueOf).length()).append("Event{appId='").append(str).append("'").append(", name='").append(str2).append("'").append(", params=").append(valueOf).append("}").toString();
    }

    /* Access modifiers changed, original: 0000 */
    public EventParams zza(zzx zzx, Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return new EventParams(new Bundle());
        }
        Bundle bundle2 = new Bundle(bundle);
        Iterator it = bundle2.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str == null) {
                zzx.zzFm().zzFE().log("Param name can't be null");
                it.remove();
            } else {
                Object zzl = zzx.zzFi().zzl(str, bundle2.get(str));
                if (zzl == null) {
                    zzx.zzFm().zzFG().zzj("Param value can't be null", str);
                    it.remove();
                } else {
                    zzx.zzFi().zza(bundle2, str, zzl);
                }
            }
        }
        return new EventParams(bundle2);
    }

    /* Access modifiers changed, original: 0000 */
    public zzh zza(zzx zzx, long j) {
        return new zzh(zzx, this.zzbch, this.zzPx, this.mName, this.zzajg, j, this.zzbcj);
    }
}
