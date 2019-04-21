package com.google.android.gms.location.places.internal;

import android.content.Context;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.PlaceLikelihood;

public class zzn extends zzt implements PlaceLikelihood {
    private final Context mContext;

    public zzn(DataHolder dataHolder, int i, Context context) {
        super(dataHolder, i);
        this.mContext = context;
    }
}
