package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.amap.api.services.route.RouteSearch.DriveRouteQuery;

/* compiled from: RouteSearch */
/* renamed from: com.amap.api.services.route.o */
class C1186o implements Creator<DriveRouteQuery> {
    C1186o() {
    }

    /* renamed from: a */
    public DriveRouteQuery createFromParcel(Parcel parcel) {
        return new DriveRouteQuery(parcel);
    }

    /* renamed from: a */
    public DriveRouteQuery[] newArray(int i) {
        return new DriveRouteQuery[i];
    }
}
