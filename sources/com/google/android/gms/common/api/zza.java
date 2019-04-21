package com.google.android.gms.common.api;

import android.support.p000v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.zznq;

public class zza extends zzb {
    private final ConnectionResult zzakJ;

    public zza(Status status, ArrayMap<zznq<?>, ConnectionResult> arrayMap) {
        super(status, arrayMap);
        this.zzakJ = (ConnectionResult) arrayMap.get(arrayMap.keyAt(0));
    }
}
