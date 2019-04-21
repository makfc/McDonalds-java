package com.google.android.gms.location.places;

import android.content.Context;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.internal.zzr;

public class PlaceBuffer extends AbstractDataBuffer<Place> implements Result {
    private final Context mContext;
    private final String zzaWM;
    private final Status zzaaO;

    public PlaceBuffer(DataHolder dataHolder, Context context) {
        super(dataHolder);
        this.mContext = context;
        this.zzaaO = PlacesStatusCodes.zzcA(dataHolder.getStatusCode());
        if (dataHolder == null || dataHolder.zzsO() == null) {
            this.zzaWM = null;
        } else {
            this.zzaWM = dataHolder.zzsO().getString("com.google.android.gms.location.places.PlaceBuffer.ATTRIBUTIONS_EXTRA_KEY");
        }
    }

    public Place get(int i) {
        return new zzr(this.zzamz, i, this.mContext);
    }

    public Status getStatus() {
        return this.zzaaO;
    }
}
