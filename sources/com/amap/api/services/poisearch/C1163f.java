package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: Photo */
/* renamed from: com.amap.api.services.poisearch.f */
class C1163f implements Creator<Photo> {
    C1163f() {
    }

    /* renamed from: a */
    public Photo createFromParcel(Parcel parcel) {
        return new Photo(parcel);
    }

    /* renamed from: a */
    public Photo[] newArray(int i) {
        return null;
    }
}
