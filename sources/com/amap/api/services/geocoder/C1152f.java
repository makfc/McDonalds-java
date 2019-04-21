package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: StreetNumber */
/* renamed from: com.amap.api.services.geocoder.f */
class C1152f implements Creator<StreetNumber> {
    C1152f() {
    }

    /* renamed from: a */
    public StreetNumber createFromParcel(Parcel parcel) {
        return new StreetNumber(parcel, null);
    }

    /* renamed from: a */
    public StreetNumber[] newArray(int i) {
        return null;
    }
}
