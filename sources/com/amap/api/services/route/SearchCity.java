package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class SearchCity implements Parcelable {
    public static final Creator<SearchCity> CREATOR = new C1190s();
    /* renamed from: a */
    private String f4118a;
    /* renamed from: b */
    private String f4119b;
    /* renamed from: c */
    private String f4120c;

    public String getSearchCityName() {
        return this.f4118a;
    }

    public void setSearchCityName(String str) {
        this.f4118a = str;
    }

    public String getSearchCitycode() {
        return this.f4119b;
    }

    public void setSearchCitycode(String str) {
        this.f4119b = str;
    }

    public String getSearchCityAdCode() {
        return this.f4120c;
    }

    public void setSearchCityhAdCode(String str) {
        this.f4120c = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4118a);
        parcel.writeString(this.f4119b);
        parcel.writeString(this.f4120c);
    }

    public SearchCity(Parcel parcel) {
        this.f4118a = parcel.readString();
        this.f4119b = parcel.readString();
        this.f4120c = parcel.readString();
    }
}
