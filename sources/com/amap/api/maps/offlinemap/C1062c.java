package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: OfflineMapProvince */
/* renamed from: com.amap.api.maps.offlinemap.c */
class C1062c implements Creator<OfflineMapProvince> {
    C1062c() {
    }

    /* renamed from: a */
    public OfflineMapProvince createFromParcel(Parcel parcel) {
        return new OfflineMapProvince(parcel);
    }

    /* renamed from: a */
    public OfflineMapProvince[] newArray(int i) {
        return new OfflineMapProvince[i];
    }
}
