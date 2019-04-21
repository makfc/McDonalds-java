package com.amap.api.services.busline;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: BusStationItem */
/* renamed from: com.amap.api.services.busline.c */
class C1073c implements Creator<BusStationItem> {
    C1073c() {
    }

    /* renamed from: a */
    public BusStationItem createFromParcel(Parcel parcel) {
        return new BusStationItem(parcel, null);
    }

    /* renamed from: a */
    public BusStationItem[] newArray(int i) {
        return null;
    }
}
