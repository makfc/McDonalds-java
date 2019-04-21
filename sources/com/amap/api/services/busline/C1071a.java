package com.amap.api.services.busline;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: BusLineItem */
/* renamed from: com.amap.api.services.busline.a */
class C1071a implements Creator<BusLineItem> {
    C1071a() {
    }

    /* renamed from: a */
    public BusLineItem createFromParcel(Parcel parcel) {
        return new BusLineItem(parcel);
    }

    /* renamed from: a */
    public BusLineItem[] newArray(int i) {
        return null;
    }
}
