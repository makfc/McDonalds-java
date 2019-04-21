package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: DriveRouteResult */
/* renamed from: com.amap.api.services.route.g */
class C1178g implements Creator<DriveRouteResult> {
    C1178g() {
    }

    /* renamed from: a */
    public DriveRouteResult createFromParcel(Parcel parcel) {
        return new DriveRouteResult(parcel);
    }

    /* renamed from: a */
    public DriveRouteResult[] newArray(int i) {
        return new DriveRouteResult[i];
    }
}
