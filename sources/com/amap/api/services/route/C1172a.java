package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: BusPath */
/* renamed from: com.amap.api.services.route.a */
class C1172a implements Creator<BusPath> {
    C1172a() {
    }

    /* renamed from: a */
    public BusPath createFromParcel(Parcel parcel) {
        return new BusPath(parcel);
    }

    /* renamed from: a */
    public BusPath[] newArray(int i) {
        return null;
    }
}
