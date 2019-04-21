package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzz;
import java.util.List;

public class AliasedPlace implements SafeParcelable {
    public static final zza CREATOR = new zza();
    final int mVersionCode;
    private final String zzaWV;
    private final List<String> zzaYs;

    AliasedPlace(int i, @NonNull String str, @NonNull List<String> list) {
        this.mVersionCode = i;
        this.zzaWV = str;
        this.zzaYs = list;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AliasedPlace)) {
            return false;
        }
        AliasedPlace aliasedPlace = (AliasedPlace) obj;
        return this.zzaWV.equals(aliasedPlace.zzaWV) && this.zzaYs.equals(aliasedPlace.zzaYs);
    }

    public String getPlaceId() {
        return this.zzaWV;
    }

    public int hashCode() {
        return zzz.hashCode(this.zzaWV, this.zzaYs);
    }

    public String toString() {
        return zzz.zzy(this).zzg("placeId", this.zzaWV).zzg("placeAliases", this.zzaYs).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    public List<String> zzDk() {
        return this.zzaYs;
    }
}
