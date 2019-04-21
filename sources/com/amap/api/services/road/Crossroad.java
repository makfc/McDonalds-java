package com.amap.api.services.road;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class Crossroad extends Road implements Parcelable {
    public static final Creator<Crossroad> CREATOR = new C1167a();
    /* renamed from: a */
    private float f4040a;
    /* renamed from: b */
    private String f4041b;
    /* renamed from: c */
    private String f4042c;
    /* renamed from: d */
    private String f4043d;
    /* renamed from: e */
    private String f4044e;
    /* renamed from: f */
    private String f4045f;

    /* synthetic */ Crossroad(Parcel parcel, C1167a c1167a) {
        this(parcel);
    }

    public float getDistance() {
        return this.f4040a;
    }

    public void setDistance(float f) {
        this.f4040a = f;
    }

    public String getDirection() {
        return this.f4041b;
    }

    public void setDirection(String str) {
        this.f4041b = str;
    }

    public String getFirstRoadId() {
        return this.f4042c;
    }

    public void setFirstRoadId(String str) {
        this.f4042c = str;
    }

    public String getFirstRoadName() {
        return this.f4043d;
    }

    public void setFirstRoadName(String str) {
        this.f4043d = str;
    }

    public String getSecondRoadId() {
        return this.f4044e;
    }

    public void setSecondRoadId(String str) {
        this.f4044e = str;
    }

    public String getSecondRoadName() {
        return this.f4045f;
    }

    public void setSecondRoadName(String str) {
        this.f4045f = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f4040a);
        parcel.writeString(this.f4041b);
        parcel.writeString(this.f4042c);
        parcel.writeString(this.f4043d);
        parcel.writeString(this.f4044e);
        parcel.writeString(this.f4045f);
    }

    private Crossroad(Parcel parcel) {
        this.f4040a = parcel.readFloat();
        this.f4041b = parcel.readString();
        this.f4042c = parcel.readString();
        this.f4043d = parcel.readString();
        this.f4044e = parcel.readString();
        this.f4045f = parcel.readString();
    }
}
