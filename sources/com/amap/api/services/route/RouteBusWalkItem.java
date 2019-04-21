package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class RouteBusWalkItem extends WalkPath implements Parcelable {
    public static final Creator<RouteBusWalkItem> CREATOR = new C1182k();
    /* renamed from: a */
    private LatLonPoint f4092a;
    /* renamed from: b */
    private LatLonPoint f4093b;

    public LatLonPoint getOrigin() {
        return this.f4092a;
    }

    public void setOrigin(LatLonPoint latLonPoint) {
        this.f4092a = latLonPoint;
    }

    public LatLonPoint getDestination() {
        return this.f4093b;
    }

    public void setDestination(LatLonPoint latLonPoint) {
        this.f4093b = latLonPoint;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.f4092a, i);
        parcel.writeParcelable(this.f4093b, i);
    }

    public RouteBusWalkItem(Parcel parcel) {
        super(parcel);
        this.f4092a = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.f4093b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
    }
}
