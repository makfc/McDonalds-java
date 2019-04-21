package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class Photo implements Parcelable {
    public static final Creator<Photo> CREATOR = new C1163f();
    /* renamed from: a */
    private String f3973a;
    /* renamed from: b */
    private String f3974b;

    public Photo(String str, String str2) {
        this.f3973a = str;
        this.f3974b = str2;
    }

    public String getTitle() {
        return this.f3973a;
    }

    public void setTitle(String str) {
        this.f3973a = str;
    }

    public String getUrl() {
        return this.f3974b;
    }

    public void setUrl(String str) {
        this.f3974b = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3973a);
        parcel.writeString(this.f3974b);
    }

    public Photo(Parcel parcel) {
        this.f3973a = parcel.readString();
        this.f3974b = parcel.readString();
    }
}
