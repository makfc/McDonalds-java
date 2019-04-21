package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

public class DriveStep implements Parcelable {
    public static final Creator<DriveStep> CREATOR = new C1179h();
    /* renamed from: a */
    private String f4073a;
    /* renamed from: b */
    private String f4074b;
    /* renamed from: c */
    private String f4075c;
    /* renamed from: d */
    private float f4076d;
    /* renamed from: e */
    private float f4077e;
    /* renamed from: f */
    private float f4078f;
    /* renamed from: g */
    private String f4079g;
    /* renamed from: h */
    private float f4080h;
    /* renamed from: i */
    private List<LatLonPoint> f4081i = new ArrayList();
    /* renamed from: j */
    private String f4082j;
    /* renamed from: k */
    private String f4083k;
    /* renamed from: l */
    private List<RouteSearchCity> f4084l = new ArrayList();

    public String getInstruction() {
        return this.f4073a;
    }

    public void setInstruction(String str) {
        this.f4073a = str;
    }

    public String getOrientation() {
        return this.f4074b;
    }

    public void setOrientation(String str) {
        this.f4074b = str;
    }

    public String getRoad() {
        return this.f4075c;
    }

    public void setRoad(String str) {
        this.f4075c = str;
    }

    public float getDistance() {
        return this.f4076d;
    }

    public void setDistance(float f) {
        this.f4076d = f;
    }

    public float getTolls() {
        return this.f4077e;
    }

    public void setTolls(float f) {
        this.f4077e = f;
    }

    public float getTollDistance() {
        return this.f4078f;
    }

    public void setTollDistance(float f) {
        this.f4078f = f;
    }

    public String getTollRoad() {
        return this.f4079g;
    }

    public void setTollRoad(String str) {
        this.f4079g = str;
    }

    public float getDuration() {
        return this.f4080h;
    }

    public void setDuration(float f) {
        this.f4080h = f;
    }

    public List<LatLonPoint> getPolyline() {
        return this.f4081i;
    }

    public void setPolyline(List<LatLonPoint> list) {
        this.f4081i = list;
    }

    public String getAction() {
        return this.f4082j;
    }

    public void setAction(String str) {
        this.f4082j = str;
    }

    public String getAssistantAction() {
        return this.f4083k;
    }

    public void setAssistantAction(String str) {
        this.f4083k = str;
    }

    public List<RouteSearchCity> getRouteSearchCityList() {
        return this.f4084l;
    }

    public void setRouteSearchCityList(List<RouteSearchCity> list) {
        this.f4084l = list;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4073a);
        parcel.writeString(this.f4074b);
        parcel.writeString(this.f4075c);
        parcel.writeFloat(this.f4076d);
        parcel.writeFloat(this.f4077e);
        parcel.writeFloat(this.f4078f);
        parcel.writeString(this.f4079g);
        parcel.writeFloat(this.f4080h);
        parcel.writeTypedList(this.f4081i);
        parcel.writeString(this.f4082j);
        parcel.writeString(this.f4083k);
        parcel.writeTypedList(this.f4084l);
    }

    public DriveStep(Parcel parcel) {
        this.f4073a = parcel.readString();
        this.f4074b = parcel.readString();
        this.f4075c = parcel.readString();
        this.f4076d = parcel.readFloat();
        this.f4077e = parcel.readFloat();
        this.f4078f = parcel.readFloat();
        this.f4079g = parcel.readString();
        this.f4080h = parcel.readFloat();
        this.f4081i = parcel.createTypedArrayList(LatLonPoint.CREATOR);
        this.f4082j = parcel.readString();
        this.f4083k = parcel.readString();
        this.f4084l = parcel.createTypedArrayList(RouteSearchCity.CREATOR);
    }
}
