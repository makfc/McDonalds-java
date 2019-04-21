package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: RegeocodeRoad */
/* renamed from: com.amap.api.services.geocoder.e */
class C1151e implements Creator<RegeocodeRoad> {
    C1151e() {
    }

    /* renamed from: a */
    public RegeocodeRoad createFromParcel(Parcel parcel) {
        return new RegeocodeRoad(parcel, null);
    }

    /* renamed from: a */
    public RegeocodeRoad[] newArray(int i) {
        return null;
    }
}
