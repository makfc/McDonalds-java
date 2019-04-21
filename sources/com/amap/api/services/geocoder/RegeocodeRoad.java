package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public final class RegeocodeRoad implements Parcelable {
    public static final Creator<RegeocodeRoad> CREATOR = new C1151e();
    /* renamed from: a */
    private String f3889a;
    /* renamed from: b */
    private String f3890b;
    /* renamed from: c */
    private float f3891c;
    /* renamed from: d */
    private String f3892d;
    /* renamed from: e */
    private LatLonPoint f3893e;

    /* synthetic */ RegeocodeRoad(Parcel parcel, C1151e c1151e) {
        this(parcel);
    }

    public String getId() {
        return this.f3889a;
    }

    public void setId(String str) {
        this.f3889a = str;
    }

    public String getName() {
        return this.f3890b;
    }

    public void setName(String str) {
        this.f3890b = str;
    }

    public float getDistance() {
        return this.f3891c;
    }

    public void setDistance(float f) {
        this.f3891c = f;
    }

    public String getDirection() {
        return this.f3892d;
    }

    public void setDirection(String str) {
        this.f3892d = str;
    }

    public LatLonPoint getLatLngPoint() {
        return this.f3893e;
    }

    public void setLatLngPoint(LatLonPoint latLonPoint) {
        this.f3893e = latLonPoint;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3889a);
        parcel.writeString(this.f3890b);
        parcel.writeFloat(this.f3891c);
        parcel.writeString(this.f3892d);
        parcel.writeValue(this.f3893e);
    }

    private RegeocodeRoad(Parcel parcel) {
        this.f3889a = parcel.readString();
        this.f3890b = parcel.readString();
        this.f3891c = parcel.readFloat();
        this.f3892d = parcel.readString();
        this.f3893e = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
    }
}
