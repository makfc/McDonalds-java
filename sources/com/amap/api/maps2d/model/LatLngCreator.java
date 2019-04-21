package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.maps2d.model.f */
class LatLngCreator implements Creator<LatLng> {
    LatLngCreator() {
    }

    /* renamed from: a */
    public LatLng createFromParcel(Parcel parcel) {
        return new LatLng(parcel.readDouble(), parcel.readDouble());
    }

    /* renamed from: a */
    public LatLng[] newArray(int i) {
        return new LatLng[i];
    }
}
