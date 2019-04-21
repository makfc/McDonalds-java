package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class DrivePath extends Path implements Parcelable {
    public static final Creator<DrivePath> CREATOR = new C1177f();
    /* renamed from: a */
    private String f4066a;
    /* renamed from: b */
    private float f4067b;
    /* renamed from: c */
    private float f4068c;
    /* renamed from: d */
    private List<DriveStep> f4069d = new ArrayList();

    public String getStrategy() {
        return this.f4066a;
    }

    public void setStrategy(String str) {
        this.f4066a = str;
    }

    public float getTolls() {
        return this.f4067b;
    }

    public void setTolls(float f) {
        this.f4067b = f;
    }

    public float getTollDistance() {
        return this.f4068c;
    }

    public void setTollDistance(float f) {
        this.f4068c = f;
    }

    public List<DriveStep> getSteps() {
        return this.f4069d;
    }

    public void setSteps(List<DriveStep> list) {
        this.f4069d = list;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f4066a);
        parcel.writeFloat(this.f4067b);
        parcel.writeFloat(this.f4068c);
        parcel.writeTypedList(this.f4069d);
    }

    public DrivePath(Parcel parcel) {
        super(parcel);
        this.f4066a = parcel.readString();
        this.f4067b = parcel.readFloat();
        this.f4068c = parcel.readFloat();
        this.f4069d = parcel.createTypedArrayList(DriveStep.CREATOR);
    }
}
