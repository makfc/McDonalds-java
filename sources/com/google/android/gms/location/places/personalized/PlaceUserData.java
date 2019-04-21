package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import java.util.List;

@Deprecated
public class PlaceUserData extends AbstractSafeParcelable {
    public static final zzf CREATOR = new zzf();
    final int mVersionCode;
    private final String zzaWV;
    private final List<PlaceAlias> zzaYs;
    private final String zzaaR;

    PlaceUserData(int i, String str, String str2, List<PlaceAlias> list) {
        this.mVersionCode = i;
        this.zzaaR = str;
        this.zzaWV = str2;
        this.zzaYs = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceUserData)) {
            return false;
        }
        PlaceUserData placeUserData = (PlaceUserData) obj;
        return this.zzaaR.equals(placeUserData.zzaaR) && this.zzaWV.equals(placeUserData.zzaWV) && this.zzaYs.equals(placeUserData.zzaYs);
    }

    public String getPlaceId() {
        return this.zzaWV;
    }

    public int hashCode() {
        return zzz.hashCode(this.zzaaR, this.zzaWV, this.zzaYs);
    }

    public String toString() {
        return zzz.zzy(this).zzg("accountName", this.zzaaR).zzg("placeId", this.zzaWV).zzg("placeAliases", this.zzaYs).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }

    public List<PlaceAlias> zzDk() {
        return this.zzaYs;
    }

    public String zzDn() {
        return this.zzaaR;
    }
}
