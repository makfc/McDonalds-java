package com.google.android.gms.location.places.internal;

import android.content.Context;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.Place;

public class zzr extends zzt implements Place {
    private final String zzaWV = zzM("place_id", "");

    public zzr(DataHolder dataHolder, int i, Context context) {
        super(dataHolder, i);
    }

    public CharSequence getName() {
        return zzM("place_name", "");
    }
}
