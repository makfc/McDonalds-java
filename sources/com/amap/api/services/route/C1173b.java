package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: BusRouteResult */
/* renamed from: com.amap.api.services.route.b */
class C1173b implements Creator<BusRouteResult> {
    C1173b() {
    }

    /* renamed from: a */
    public BusRouteResult createFromParcel(Parcel parcel) {
        return new BusRouteResult(parcel);
    }

    /* renamed from: a */
    public BusRouteResult[] newArray(int i) {
        return new BusRouteResult[i];
    }
}
