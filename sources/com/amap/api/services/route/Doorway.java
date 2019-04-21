package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class Doorway implements Parcelable {
    public static final Creator<Doorway> CREATOR = new C1176e();
    /* renamed from: a */
    private String f4064a;
    /* renamed from: b */
    private LatLonPoint f4065b;

    public String getName() {
        return this.f4064a;
    }

    public void setName(String str) {
        this.f4064a = str;
    }

    public LatLonPoint getLatLonPoint() {
        return this.f4065b;
    }

    public void setLatLonPoint(LatLonPoint latLonPoint) {
        this.f4065b = latLonPoint;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4064a);
        parcel.writeParcelable(this.f4065b, i);
    }

    public Doorway(Parcel parcel) {
        this.f4064a = parcel.readString();
        this.f4065b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
    }
}
