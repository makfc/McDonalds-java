package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.amap.api.services.route.RouteSearch.FromAndTo;

/* compiled from: RouteSearch */
/* renamed from: com.amap.api.services.route.p */
class C1187p implements Creator<FromAndTo> {
    C1187p() {
    }

    /* renamed from: a */
    public FromAndTo createFromParcel(Parcel parcel) {
        return new FromAndTo(parcel);
    }

    /* renamed from: a */
    public FromAndTo[] newArray(int i) {
        return new FromAndTo[i];
    }
}
