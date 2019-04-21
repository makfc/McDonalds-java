package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.location.places.internal.zzb;

public class AutocompletePredictionBuffer extends AbstractDataBuffer<AutocompletePrediction> implements Result {
    public AutocompletePredictionBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    public AutocompletePrediction get(int i) {
        return new zzb(this.zzamz, i);
    }

    public Status getStatus() {
        return PlacesStatusCodes.zzcA(this.zzamz.getStatusCode());
    }

    public String toString() {
        return zzz.zzy(this).zzg("status", getStatus()).toString();
    }
}
