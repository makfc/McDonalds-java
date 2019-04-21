package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public final class StreetNumber implements Parcelable {
    public static final Creator<StreetNumber> CREATOR = new C1152f();
    /* renamed from: a */
    private String f3894a;
    /* renamed from: b */
    private String f3895b;
    /* renamed from: c */
    private LatLonPoint f3896c;
    /* renamed from: d */
    private String f3897d;
    /* renamed from: e */
    private float f3898e;

    /* synthetic */ StreetNumber(Parcel parcel, C1152f c1152f) {
        this(parcel);
    }

    public String getStreet() {
        return this.f3894a;
    }

    public void setStreet(String str) {
        this.f3894a = str;
    }

    public String getNumber() {
        return this.f3895b;
    }

    public void setNumber(String str) {
        this.f3895b = str;
    }

    public LatLonPoint getLatLonPoint() {
        return this.f3896c;
    }

    public void setLatLonPoint(LatLonPoint latLonPoint) {
        this.f3896c = latLonPoint;
    }

    public String getDirection() {
        return this.f3897d;
    }

    public void setDirection(String str) {
        this.f3897d = str;
    }

    public float getDistance() {
        return this.f3898e;
    }

    public void setDistance(float f) {
        this.f3898e = f;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3894a);
        parcel.writeString(this.f3895b);
        parcel.writeValue(this.f3896c);
        parcel.writeString(this.f3897d);
        parcel.writeFloat(this.f3898e);
    }

    private StreetNumber(Parcel parcel) {
        this.f3894a = parcel.readString();
        this.f3895b = parcel.readString();
        this.f3896c = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.f3897d = parcel.readString();
        this.f3898e = parcel.readFloat();
    }
}
