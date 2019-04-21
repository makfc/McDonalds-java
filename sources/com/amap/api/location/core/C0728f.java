package com.amap.api.location.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: GeoPoint */
/* renamed from: com.amap.api.location.core.f */
class C0728f implements Creator<GeoPoint> {
    C0728f() {
    }

    /* renamed from: a */
    public GeoPoint createFromParcel(Parcel parcel) {
        return new GeoPoint(parcel, null);
    }

    /* renamed from: a */
    public GeoPoint[] newArray(int i) {
        return new GeoPoint[i];
    }
}
