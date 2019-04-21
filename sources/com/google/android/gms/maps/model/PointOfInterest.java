package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class PointOfInterest implements SafeParcelable {
    public static final zzg CREATOR = new zzg();
    private final int mVersionCode;
    public final String name;
    public final LatLng zzbaW;
    public final String zzbaX;

    PointOfInterest(int i, LatLng latLng, String str, String str2) {
        this.mVersionCode = i;
        this.zzbaW = latLng;
        this.zzbaX = str;
        this.name = str2;
    }

    public int describeContents() {
        return 0;
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }
}
