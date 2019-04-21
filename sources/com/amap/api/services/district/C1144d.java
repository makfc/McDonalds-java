package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: DistrictSearchQuery */
/* renamed from: com.amap.api.services.district.d */
class C1144d implements Creator<DistrictSearchQuery> {
    C1144d() {
    }

    /* renamed from: a */
    public DistrictSearchQuery createFromParcel(Parcel parcel) {
        boolean z = true;
        DistrictSearchQuery districtSearchQuery = new DistrictSearchQuery();
        districtSearchQuery.setKeywords(parcel.readString());
        districtSearchQuery.setKeywordsLevel(parcel.readString());
        districtSearchQuery.setPageNum(parcel.readInt());
        districtSearchQuery.setPageSize(parcel.readInt());
        if (parcel.readByte() != (byte) 1) {
            z = false;
        }
        districtSearchQuery.setShowChild(z);
        return districtSearchQuery;
    }

    /* renamed from: a */
    public DistrictSearchQuery[] newArray(int i) {
        return new DistrictSearchQuery[i];
    }
}
