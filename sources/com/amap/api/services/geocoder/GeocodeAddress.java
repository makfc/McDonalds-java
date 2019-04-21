package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public final class GeocodeAddress implements Parcelable {
    public static final Creator<GeocodeAddress> CREATOR = new C1148b();
    /* renamed from: a */
    private String f3849a;
    /* renamed from: b */
    private String f3850b;
    /* renamed from: c */
    private String f3851c;
    /* renamed from: d */
    private String f3852d;
    /* renamed from: e */
    private String f3853e;
    /* renamed from: f */
    private String f3854f;
    /* renamed from: g */
    private String f3855g;
    /* renamed from: h */
    private String f3856h;
    /* renamed from: i */
    private LatLonPoint f3857i;
    /* renamed from: j */
    private String f3858j;

    /* synthetic */ GeocodeAddress(Parcel parcel, C1148b c1148b) {
        this(parcel);
    }

    public String getFormatAddress() {
        return this.f3849a;
    }

    public void setFormatAddress(String str) {
        this.f3849a = str;
    }

    public String getProvince() {
        return this.f3850b;
    }

    public void setProvince(String str) {
        this.f3850b = str;
    }

    public String getCity() {
        return this.f3851c;
    }

    public void setCity(String str) {
        this.f3851c = str;
    }

    public String getDistrict() {
        return this.f3852d;
    }

    public void setDistrict(String str) {
        this.f3852d = str;
    }

    public String getTownship() {
        return this.f3853e;
    }

    public void setTownship(String str) {
        this.f3853e = str;
    }

    public String getNeighborhood() {
        return this.f3854f;
    }

    public void setNeighborhood(String str) {
        this.f3854f = str;
    }

    public String getBuilding() {
        return this.f3855g;
    }

    public void setBuilding(String str) {
        this.f3855g = str;
    }

    public String getAdcode() {
        return this.f3856h;
    }

    public void setAdcode(String str) {
        this.f3856h = str;
    }

    public LatLonPoint getLatLonPoint() {
        return this.f3857i;
    }

    public void setLatLonPoint(LatLonPoint latLonPoint) {
        this.f3857i = latLonPoint;
    }

    public String getLevel() {
        return this.f3858j;
    }

    public void setLevel(String str) {
        this.f3858j = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3849a);
        parcel.writeString(this.f3850b);
        parcel.writeString(this.f3851c);
        parcel.writeString(this.f3852d);
        parcel.writeString(this.f3853e);
        parcel.writeString(this.f3854f);
        parcel.writeString(this.f3855g);
        parcel.writeString(this.f3856h);
        parcel.writeValue(this.f3857i);
        parcel.writeString(this.f3858j);
    }

    private GeocodeAddress(Parcel parcel) {
        this.f3849a = parcel.readString();
        this.f3850b = parcel.readString();
        this.f3851c = parcel.readString();
        this.f3852d = parcel.readString();
        this.f3853e = parcel.readString();
        this.f3854f = parcel.readString();
        this.f3855g = parcel.readString();
        this.f3856h = parcel.readString();
        this.f3857i = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.f3858j = parcel.readString();
    }
}
