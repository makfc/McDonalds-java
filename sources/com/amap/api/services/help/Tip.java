package com.amap.api.services.help;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Tip implements Parcelable {
    public static final Creator<Tip> CREATOR = new C1155b();
    /* renamed from: a */
    private String f3906a;
    /* renamed from: b */
    private String f3907b;
    /* renamed from: c */
    private String f3908c;

    /* synthetic */ Tip(Parcel parcel, C1155b c1155b) {
        this(parcel);
    }

    public String getName() {
        return this.f3906a;
    }

    public void setName(String str) {
        this.f3906a = str;
    }

    public String getDistrict() {
        return this.f3907b;
    }

    public void setDistrict(String str) {
        this.f3907b = str;
    }

    public String getAdcode() {
        return this.f3908c;
    }

    public void setAdcode(String str) {
        this.f3908c = str;
    }

    public String toString() {
        return "name:" + this.f3906a + " district:" + this.f3907b + " adcode:" + this.f3908c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3906a);
        parcel.writeString(this.f3908c);
        parcel.writeString(this.f3907b);
    }

    private Tip(Parcel parcel) {
        this.f3906a = parcel.readString();
        this.f3908c = parcel.readString();
        this.f3907b = parcel.readString();
    }
}
