package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.common.util.zza;
import java.util.Iterator;

public class zzny extends zznu {
    public void onStop() {
        zza zza = null;
        super.onStop();
        Iterator it = zza.iterator();
        while (it.hasNext()) {
            ((zzc) it.next()).release();
        }
        zza.clear();
        zza.zza(this);
    }

    /* Access modifiers changed, original: protected */
    public void zza(ConnectionResult connectionResult, int i) {
        zzoj zzoj = null;
        zzoj.zza(connectionResult, i);
    }

    /* Access modifiers changed, original: protected */
    public void zzrA() {
        zzoj zzoj = null;
        zzoj.zzrA();
    }
}
