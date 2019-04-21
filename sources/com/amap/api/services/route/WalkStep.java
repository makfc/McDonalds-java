package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

public class WalkStep implements Parcelable {
    public static final Creator<WalkStep> CREATOR = new C1193v();
    /* renamed from: a */
    private String f4124a;
    /* renamed from: b */
    private String f4125b;
    /* renamed from: c */
    private String f4126c;
    /* renamed from: d */
    private float f4127d;
    /* renamed from: e */
    private float f4128e;
    /* renamed from: f */
    private List<LatLonPoint> f4129f = new ArrayList();
    /* renamed from: g */
    private String f4130g;
    /* renamed from: h */
    private String f4131h;

    public String getInstruction() {
        return this.f4124a;
    }

    public void setInstruction(String str) {
        this.f4124a = str;
    }

    public String getOrientation() {
        return this.f4125b;
    }

    public void setOrientation(String str) {
        this.f4125b = str;
    }

    public String getRoad() {
        return this.f4126c;
    }

    public void setRoad(String str) {
        this.f4126c = str;
    }

    public float getDistance() {
        return this.f4127d;
    }

    public void setDistance(float f) {
        this.f4127d = f;
    }

    public float getDuration() {
        return this.f4128e;
    }

    public void setDuration(float f) {
        this.f4128e = f;
    }

    public List<LatLonPoint> getPolyline() {
        return this.f4129f;
    }

    public void setPolyline(List<LatLonPoint> list) {
        this.f4129f = list;
    }

    public String getAction() {
        return this.f4130g;
    }

    public void setAction(String str) {
        this.f4130g = str;
    }

    public String getAssistantAction() {
        return this.f4131h;
    }

    public void setAssistantAction(String str) {
        this.f4131h = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4124a);
        parcel.writeString(this.f4125b);
        parcel.writeString(this.f4126c);
        parcel.writeFloat(this.f4127d);
        parcel.writeFloat(this.f4128e);
        parcel.writeTypedList(this.f4129f);
        parcel.writeString(this.f4130g);
        parcel.writeString(this.f4131h);
    }

    public WalkStep(Parcel parcel) {
        this.f4124a = parcel.readString();
        this.f4125b = parcel.readString();
        this.f4126c = parcel.readString();
        this.f4127d = parcel.readFloat();
        this.f4128e = parcel.readFloat();
        this.f4129f = parcel.createTypedArrayList(LatLonPoint.CREATOR);
        this.f4130g = parcel.readString();
        this.f4131h = parcel.readString();
    }
}
