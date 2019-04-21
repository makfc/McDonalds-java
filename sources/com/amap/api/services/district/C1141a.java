package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: DistrictItem */
/* renamed from: com.amap.api.services.district.a */
class C1141a implements Creator<DistrictItem> {
    C1141a() {
    }

    /* renamed from: a */
    public DistrictItem createFromParcel(Parcel parcel) {
        return new DistrictItem(parcel);
    }

    /* renamed from: a */
    public DistrictItem[] newArray(int i) {
        return new DistrictItem[i];
    }
}
