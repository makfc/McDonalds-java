package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class City implements Parcelable {
    public static final Creator<City> CREATOR = new C1060a();
    /* renamed from: a */
    private String f1343a = "";
    /* renamed from: b */
    private String f1344b = "";
    /* renamed from: c */
    private String f1345c;
    /* renamed from: d */
    private String f1346d;
    /* renamed from: e */
    private String f1347e = "";

    public void setCity(String str) {
        this.f1343a = str;
    }

    public String getCity() {
        return this.f1343a;
    }

    public void setCode(String str) {
        if (str != null && !str.equals("[]")) {
            this.f1344b = str;
        }
    }

    public String getCode() {
        return this.f1344b;
    }

    public String getJianpin() {
        return this.f1345c;
    }

    public void setJianpin(String str) {
        this.f1345c = str;
    }

    public String getPinyin() {
        return this.f1346d;
    }

    public void setPinyin(String str) {
        this.f1346d = str;
    }

    public String getAdcode() {
        return this.f1347e;
    }

    public void setAdcode(String str) {
        this.f1347e = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1343a);
        parcel.writeString(this.f1344b);
        parcel.writeString(this.f1345c);
        parcel.writeString(this.f1346d);
        parcel.writeString(this.f1347e);
    }

    public City(Parcel parcel) {
        this.f1343a = parcel.readString();
        this.f1344b = parcel.readString();
        this.f1345c = parcel.readString();
        this.f1346d = parcel.readString();
        this.f1347e = parcel.readString();
    }
}
