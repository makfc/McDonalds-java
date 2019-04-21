package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class AliasedPlacesResult implements Result, SafeParcelable {
    public static final Creator<AliasedPlacesResult> CREATOR = new zzb();
    final int mVersionCode;
    private final List<AliasedPlace> zzaYt;
    private final Status zzaaO;

    AliasedPlacesResult(int i, Status status, List<AliasedPlace> list) {
        this.mVersionCode = i;
        this.zzaaO = status;
        this.zzaYt = list;
    }

    public int describeContents() {
        return 0;
    }

    public Status getStatus() {
        return this.zzaaO;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public List<AliasedPlace> zzDl() {
        return this.zzaYt;
    }
}
