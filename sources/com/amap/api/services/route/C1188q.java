package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.amap.api.services.route.RouteSearch.WalkRouteQuery;

/* compiled from: RouteSearch */
/* renamed from: com.amap.api.services.route.q */
class C1188q implements Creator<WalkRouteQuery> {
    C1188q() {
    }

    /* renamed from: a */
    public WalkRouteQuery createFromParcel(Parcel parcel) {
        return new WalkRouteQuery(parcel);
    }

    /* renamed from: a */
    public WalkRouteQuery[] newArray(int i) {
        return new WalkRouteQuery[i];
    }
}
