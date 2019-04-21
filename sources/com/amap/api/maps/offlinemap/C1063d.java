package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: Province */
/* renamed from: com.amap.api.maps.offlinemap.d */
class C1063d implements Creator<Province> {
    C1063d() {
    }

    /* renamed from: a */
    public Province createFromParcel(Parcel parcel) {
        return new Province(parcel);
    }

    /* renamed from: a */
    public Province[] newArray(int i) {
        return new Province[i];
    }
}
