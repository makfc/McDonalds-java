package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: LatLonPoint */
/* renamed from: com.amap.api.services.core.k */
class C1129k implements Creator<LatLonPoint> {
    C1129k() {
    }

    /* renamed from: a */
    public LatLonPoint createFromParcel(Parcel parcel) {
        return new LatLonPoint(parcel, null);
    }

    /* renamed from: a */
    public LatLonPoint[] newArray(int i) {
        return new LatLonPoint[i];
    }
}
