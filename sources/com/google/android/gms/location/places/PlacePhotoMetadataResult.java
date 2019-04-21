package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class PlacePhotoMetadataResult extends AbstractSafeParcelable implements Result {
    public static final Creator<PlacePhotoMetadataResult> CREATOR = new zzh();
    final int mVersionCode;
    final DataHolder zzaWS;
    private final PlacePhotoMetadataBuffer zzaWT;
    private final Status zzaaO;

    PlacePhotoMetadataResult(int i, Status status, DataHolder dataHolder) {
        this.mVersionCode = i;
        this.zzaaO = status;
        this.zzaWS = dataHolder;
        if (dataHolder == null) {
            this.zzaWT = null;
        } else {
            this.zzaWT = new PlacePhotoMetadataBuffer(this.zzaWS);
        }
    }

    public PlacePhotoMetadataResult(Status status, DataHolder dataHolder) {
        this(0, status, dataHolder);
    }

    public Status getStatus() {
        return this.zzaaO;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzh.zza(this, parcel, i);
    }
}
