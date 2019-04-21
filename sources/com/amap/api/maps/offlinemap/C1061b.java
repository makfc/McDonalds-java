package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: OfflineMapCity */
/* renamed from: com.amap.api.maps.offlinemap.b */
class C1061b implements Creator<OfflineMapCity> {
    C1061b() {
    }

    /* renamed from: a */
    public OfflineMapCity createFromParcel(Parcel parcel) {
        return new OfflineMapCity(parcel);
    }

    /* renamed from: a */
    public OfflineMapCity[] newArray(int i) {
        return new OfflineMapCity[i];
    }
}
