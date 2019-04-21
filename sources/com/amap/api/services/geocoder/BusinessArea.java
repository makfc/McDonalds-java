package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class BusinessArea implements Parcelable {
    public static final Creator<BusinessArea> CREATOR = new C1147a();
    /* renamed from: a */
    private LatLonPoint f3847a;
    /* renamed from: b */
    private String f3848b;

    public LatLonPoint getCenterPoint() {
        return this.f3847a;
    }

    public void setCenterPoint(LatLonPoint latLonPoint) {
        this.f3847a = latLonPoint;
    }

    public String getName() {
        return this.f3848b;
    }

    public void setName(String str) {
        this.f3848b = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3847a, i);
        parcel.writeString(this.f3848b);
    }

    public BusinessArea(Parcel parcel) {
        this.f3847a = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.f3848b = parcel.readString();
    }
}
