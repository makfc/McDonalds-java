package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: WalkRouteResult */
/* renamed from: com.amap.api.services.route.u */
class C1192u implements Creator<WalkRouteResult> {
    C1192u() {
    }

    /* renamed from: a */
    public WalkRouteResult createFromParcel(Parcel parcel) {
        return new WalkRouteResult(parcel);
    }

    /* renamed from: a */
    public WalkRouteResult[] newArray(int i) {
        return new WalkRouteResult[i];
    }
}
