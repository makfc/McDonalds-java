package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: DistrictResult */
/* renamed from: com.amap.api.services.district.b */
class C1142b implements Creator<DistrictResult> {
    /* renamed from: a */
    final /* synthetic */ DistrictResult f3845a;

    C1142b(DistrictResult districtResult) {
        this.f3845a = districtResult;
    }

    /* renamed from: a */
    public DistrictResult createFromParcel(Parcel parcel) {
        return new DistrictResult(parcel);
    }

    /* renamed from: a */
    public DistrictResult[] newArray(int i) {
        return new DistrictResult[i];
    }
}
