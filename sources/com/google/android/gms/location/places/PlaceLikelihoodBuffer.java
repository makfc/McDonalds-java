package com.google.android.gms.location.places;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.location.places.internal.zzn;

public class PlaceLikelihoodBuffer extends AbstractDataBuffer<PlaceLikelihood> implements Result {
    private final Context mContext;
    private final String zzaWM;
    private final Status zzaaO;
    private final int zzwP;

    public static class zza {
        static int zziq(int i) {
            switch (i) {
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                    return i;
                default:
                    throw new IllegalArgumentException("invalid source: " + i);
            }
        }
    }

    public PlaceLikelihoodBuffer(DataHolder dataHolder, int i, Context context) {
        super(dataHolder);
        this.mContext = context;
        this.zzaaO = PlacesStatusCodes.zzcA(dataHolder.getStatusCode());
        this.zzwP = zza.zziq(i);
        if (dataHolder == null || dataHolder.zzsO() == null) {
            this.zzaWM = null;
        } else {
            this.zzaWM = dataHolder.zzsO().getString("com.google.android.gms.location.places.PlaceLikelihoodBuffer.ATTRIBUTIONS_EXTRA_KEY");
        }
    }

    public static int zzI(Bundle bundle) {
        return bundle.getInt("com.google.android.gms.location.places.PlaceLikelihoodBuffer.SOURCE_EXTRA_KEY");
    }

    public PlaceLikelihood get(int i) {
        return new zzn(this.zzamz, i, this.mContext);
    }

    public Status getStatus() {
        return this.zzaaO;
    }

    public String toString() {
        return zzz.zzy(this).zzg("status", getStatus()).zzg("attributions", this.zzaWM).toString();
    }
}
