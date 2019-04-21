package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class FusedLocationProviderResult extends AbstractSafeParcelable implements Result {
    public static final Creator<FusedLocationProviderResult> CREATOR = new zze();
    public static final FusedLocationProviderResult zzaVS = new FusedLocationProviderResult(Status.zzalw);
    private final int mVersionCode;
    private final Status zzaaO;

    FusedLocationProviderResult(int i, Status status) {
        this.mVersionCode = i;
        this.zzaaO = status;
    }

    public FusedLocationProviderResult(Status status) {
        this(1, status);
    }

    public Status getStatus() {
        return this.zzaaO;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze.zza(this, parcel, i);
    }
}
