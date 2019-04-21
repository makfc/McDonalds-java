package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class District implements Parcelable {
    public static final Creator<District> CREATOR = new C1175d();
    /* renamed from: a */
    private String f4062a;
    /* renamed from: b */
    private String f4063b;

    public String getDistrictName() {
        return this.f4062a;
    }

    public void setDistrictName(String str) {
        this.f4062a = str;
    }

    public String getDistrictAdcode() {
        return this.f4063b;
    }

    public void setDistrictAdcode(String str) {
        this.f4063b = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4062a);
        parcel.writeString(this.f4063b);
    }

    public District(Parcel parcel) {
        this.f4062a = parcel.readString();
        this.f4063b = parcel.readString();
    }
}
