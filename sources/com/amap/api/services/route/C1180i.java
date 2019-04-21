package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: Path */
/* renamed from: com.amap.api.services.route.i */
class C1180i implements Creator<Path> {
    C1180i() {
    }

    /* renamed from: a */
    public Path createFromParcel(Parcel parcel) {
        return new Path(parcel);
    }

    /* renamed from: a */
    public Path[] newArray(int i) {
        return null;
    }
}
