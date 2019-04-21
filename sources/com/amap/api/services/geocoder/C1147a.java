package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: BusinessArea */
/* renamed from: com.amap.api.services.geocoder.a */
class C1147a implements Creator<BusinessArea> {
    C1147a() {
    }

    /* renamed from: a */
    public BusinessArea createFromParcel(Parcel parcel) {
        return new BusinessArea(parcel);
    }

    /* renamed from: a */
    public BusinessArea[] newArray(int i) {
        return new BusinessArea[i];
    }
}
