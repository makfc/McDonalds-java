package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: PoiItem */
/* renamed from: com.amap.api.services.core.p */
class C1132p implements Creator<PoiItem> {
    C1132p() {
    }

    /* renamed from: a */
    public PoiItem createFromParcel(Parcel parcel) {
        return new PoiItem(parcel);
    }

    /* renamed from: a */
    public PoiItem[] newArray(int i) {
        return new PoiItem[i];
    }
}
