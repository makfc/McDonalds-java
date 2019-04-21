package com.amap.api.mapcore.util;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: CityObject */
/* renamed from: com.amap.api.mapcore.util.bh */
class C0759bh implements Creator<CityObject> {
    C0759bh() {
    }

    /* renamed from: a */
    public CityObject createFromParcel(Parcel parcel) {
        return new CityObject(parcel);
    }

    /* renamed from: a */
    public CityObject[] newArray(int i) {
        return new CityObject[i];
    }
}
