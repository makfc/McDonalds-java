package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public class OfflineMapProvince extends Province {
    public static final Creator<OfflineMapProvince> CREATOR = new C1062c();
    /* renamed from: a */
    private String f3313a;
    /* renamed from: b */
    private int f3314b = 6;
    /* renamed from: c */
    private long f3315c;
    /* renamed from: d */
    private String f3316d;
    /* renamed from: e */
    private int f3317e = 0;
    /* renamed from: f */
    private ArrayList<OfflineMapCity> f3318f;

    public String getUrl() {
        return this.f3313a;
    }

    public void setUrl(String str) {
        this.f3313a = str;
    }

    public int getState() {
        return this.f3314b;
    }

    public void setState(int i) {
        this.f3314b = i;
    }

    public long getSize() {
        return this.f3315c;
    }

    public void setSize(long j) {
        this.f3315c = j;
    }

    public String getVersion() {
        return this.f3316d;
    }

    public void setVersion(String str) {
        this.f3316d = str;
    }

    public int getcompleteCode() {
        return this.f3317e;
    }

    public void setCompleteCode(int i) {
        this.f3317e = i;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f3313a);
        parcel.writeInt(this.f3314b);
        parcel.writeLong(this.f3315c);
        parcel.writeString(this.f3316d);
        parcel.writeInt(this.f3317e);
        parcel.writeTypedList(this.f3318f);
    }

    public ArrayList<OfflineMapCity> getCityList() {
        if (this.f3318f == null) {
            return new ArrayList();
        }
        return this.f3318f;
    }

    public void setCityList(ArrayList<OfflineMapCity> arrayList) {
        this.f3318f = arrayList;
    }

    public OfflineMapProvince(Parcel parcel) {
        super(parcel);
        this.f3313a = parcel.readString();
        this.f3314b = parcel.readInt();
        this.f3315c = parcel.readLong();
        this.f3316d = parcel.readString();
        this.f3317e = parcel.readInt();
        this.f3318f = parcel.createTypedArrayList(OfflineMapCity.CREATOR);
    }
}
