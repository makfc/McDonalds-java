package com.amap.api.services.road;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class Road implements Parcelable {
    public static final Creator<Road> CREATOR = new C1168b();
    /* renamed from: a */
    private String f4034a;
    /* renamed from: b */
    private String f4035b;
    /* renamed from: c */
    private String f4036c;
    /* renamed from: d */
    private float f4037d;
    /* renamed from: e */
    private String f4038e;
    /* renamed from: f */
    private LatLonPoint f4039f;

    public Road(String str, String str2) {
        this.f4034a = str;
        this.f4035b = str2;
    }

    public void setId(String str) {
        this.f4034a = str;
    }

    public void setName(String str) {
        this.f4035b = str;
    }

    public String getCityCode() {
        return this.f4036c;
    }

    public void setCityCode(String str) {
        this.f4036c = str;
    }

    public float getRoadWidth() {
        return this.f4037d;
    }

    public void setRoadWidth(float f) {
        this.f4037d = f;
    }

    public String getType() {
        return this.f4038e;
    }

    public void setType(String str) {
        this.f4038e = str;
    }

    public LatLonPoint getCenterPoint() {
        return this.f4039f;
    }

    public void setCenterPoint(LatLonPoint latLonPoint) {
        this.f4039f = latLonPoint;
    }

    public String getId() {
        return this.f4034a;
    }

    public String getName() {
        return this.f4035b;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4034a);
        parcel.writeString(this.f4035b);
        parcel.writeString(this.f4036c);
        parcel.writeFloat(this.f4037d);
        parcel.writeString(this.f4038e);
        parcel.writeValue(this.f4039f);
    }

    private Road(Parcel parcel) {
        this.f4034a = parcel.readString();
        this.f4035b = parcel.readString();
        this.f4036c = parcel.readString();
        this.f4037d = parcel.readFloat();
        this.f4038e = parcel.readString();
        this.f4039f = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
    }
}
