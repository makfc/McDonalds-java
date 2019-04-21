package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: City */
/* renamed from: com.amap.api.maps.offlinemap.a */
class C1060a implements Creator<City> {
    C1060a() {
    }

    /* renamed from: a */
    public City createFromParcel(Parcel parcel) {
        return new City(parcel);
    }

    /* renamed from: a */
    public City[] newArray(int i) {
        return new City[i];
    }
}
