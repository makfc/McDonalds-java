package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: PoiItemDetail */
/* renamed from: com.amap.api.services.poisearch.h */
class C1164h implements Creator<PoiItemDetail> {
    C1164h() {
    }

    /* renamed from: a */
    public PoiItemDetail createFromParcel(Parcel parcel) {
        return new PoiItemDetail(parcel, null);
    }

    /* renamed from: a */
    public PoiItemDetail[] newArray(int i) {
        return new PoiItemDetail[i];
    }
}
