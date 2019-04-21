package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Province implements Parcelable {
    public static final Creator<Province> CREATOR = new C1063d();
    /* renamed from: a */
    private String f3309a = "";
    /* renamed from: b */
    private String f3310b;
    /* renamed from: c */
    private String f3311c;
    /* renamed from: d */
    private String f3312d = "";

    public String getProvinceName() {
        return this.f3309a;
    }

    public String getJianpin() {
        return this.f3310b;
    }

    public String getPinyin() {
        return this.f3311c;
    }

    public void setProvinceName(String str) {
        this.f3309a = str;
    }

    public void setJianpin(String str) {
        this.f3310b = str;
    }

    public void setPinyin(String str) {
        this.f3311c = str;
    }

    public void setProvinceCode(String str) {
        this.f3312d = str;
    }

    public String getProvinceCode() {
        return this.f3312d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3309a);
        parcel.writeString(this.f3310b);
        parcel.writeString(this.f3311c);
        parcel.writeString(this.f3312d);
    }

    public Province(Parcel parcel) {
        this.f3309a = parcel.readString();
        this.f3310b = parcel.readString();
        this.f3311c = parcel.readString();
        this.f3312d = parcel.readString();
    }
}
