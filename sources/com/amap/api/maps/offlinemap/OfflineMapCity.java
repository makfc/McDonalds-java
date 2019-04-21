package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class OfflineMapCity extends City {
    public static final Creator<OfflineMapCity> CREATOR = new C1061b();
    /* renamed from: a */
    private String f1348a = "";
    /* renamed from: b */
    private long f1349b = 0;
    /* renamed from: c */
    private int f1350c = 6;
    /* renamed from: d */
    private String f1351d = "";
    /* renamed from: e */
    private int f1352e = 0;

    public String getUrl() {
        return this.f1348a;
    }

    public void setUrl(String str) {
        this.f1348a = str;
    }

    public long getSize() {
        return this.f1349b;
    }

    public void setSize(long j) {
        this.f1349b = j;
    }

    public int getState() {
        return this.f1350c;
    }

    public void setState(int i) {
        this.f1350c = i;
    }

    public String getVersion() {
        return this.f1351d;
    }

    public void setVersion(String str) {
        this.f1351d = str;
    }

    public int getcompleteCode() {
        return this.f1352e;
    }

    public void setCompleteCode(int i) {
        this.f1352e = i;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f1348a);
        parcel.writeLong(this.f1349b);
        parcel.writeInt(this.f1350c);
        parcel.writeString(this.f1351d);
        parcel.writeInt(this.f1352e);
    }

    public OfflineMapCity(Parcel parcel) {
        super(parcel);
        this.f1348a = parcel.readString();
        this.f1349b = parcel.readLong();
        this.f1350c = parcel.readInt();
        this.f1351d = parcel.readString();
        this.f1352e = parcel.readInt();
    }
}
