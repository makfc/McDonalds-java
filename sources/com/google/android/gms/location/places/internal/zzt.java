package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;

public abstract class zzt extends zzc {
    private final String TAG = "SafeDataBufferRef";

    public zzt(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    /* Access modifiers changed, original: protected */
    public String zzM(String str, String str2) {
        return (!zzcY(str) || zzda(str)) ? str2 : getString(str);
    }
}
