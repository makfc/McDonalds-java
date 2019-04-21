package com.google.firebase.iid;

import android.support.annotation.Nullable;
import android.text.TextUtils;

public class zze {
    private static final Object zzrs = new Object();
    private final zzg zzbSQ;

    zze(zzg zzg) {
        this.zzbSQ = zzg;
    }

    /* Access modifiers changed, original: 0000 */
    @Nullable
    public String zzUu() {
        String str = null;
        synchronized (zzrs) {
            String string = this.zzbSQ.zzUv().getString("topic_operaion_queue", null);
            if (string != null) {
                String[] split = string.split(",");
                if (split.length > 1 && !TextUtils.isEmpty(split[1])) {
                    str = split[1];
                }
            }
        }
        return str;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean zziC(String str) {
        boolean z;
        synchronized (zzrs) {
            String string = this.zzbSQ.zzUv().getString("topic_operaion_queue", "");
            String valueOf = String.valueOf(",");
            String valueOf2 = String.valueOf(str);
            if (string.startsWith(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))) {
                valueOf = String.valueOf(",");
                valueOf2 = String.valueOf(str);
                this.zzbSQ.zzUv().edit().putString("topic_operaion_queue", string.substring((valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).length())).apply();
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }
}
