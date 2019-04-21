package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: GeocodeAddress */
/* renamed from: com.amap.api.services.geocoder.b */
class C1148b implements Creator<GeocodeAddress> {
    C1148b() {
    }

    /* renamed from: a */
    public GeocodeAddress[] newArray(int i) {
        return null;
    }

    /* renamed from: a */
    public GeocodeAddress createFromParcel(Parcel parcel) {
        return new GeocodeAddress(parcel, null);
    }
}
