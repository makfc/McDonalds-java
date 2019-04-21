package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: RegeocodeAddress */
/* renamed from: com.amap.api.services.geocoder.d */
class C1150d implements Creator<RegeocodeAddress> {
    C1150d() {
    }

    /* renamed from: a */
    public RegeocodeAddress createFromParcel(Parcel parcel) {
        return new RegeocodeAddress(parcel, null);
    }

    /* renamed from: a */
    public RegeocodeAddress[] newArray(int i) {
        return null;
    }
}
