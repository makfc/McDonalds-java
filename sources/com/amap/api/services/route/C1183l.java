package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: RouteResult */
/* renamed from: com.amap.api.services.route.l */
class C1183l implements Creator<RouteResult> {
    C1183l() {
    }

    /* renamed from: a */
    public RouteResult createFromParcel(Parcel parcel) {
        return new RouteResult(parcel);
    }

    /* renamed from: a */
    public RouteResult[] newArray(int i) {
        return new RouteResult[i];
    }
}
