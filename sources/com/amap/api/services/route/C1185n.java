package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.amap.api.services.route.RouteSearch.BusRouteQuery;

/* compiled from: RouteSearch */
/* renamed from: com.amap.api.services.route.n */
class C1185n implements Creator<BusRouteQuery> {
    C1185n() {
    }

    /* renamed from: a */
    public BusRouteQuery createFromParcel(Parcel parcel) {
        return new BusRouteQuery(parcel);
    }

    /* renamed from: a */
    public BusRouteQuery[] newArray(int i) {
        return new BusRouteQuery[i];
    }
}
