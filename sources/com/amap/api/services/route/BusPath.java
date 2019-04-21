package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class BusPath extends Path implements Parcelable {
    public static final Creator<BusPath> CREATOR = new C1172a();
    /* renamed from: a */
    private float f4048a;
    /* renamed from: b */
    private boolean f4049b;
    /* renamed from: c */
    private float f4050c;
    /* renamed from: d */
    private float f4051d;
    /* renamed from: e */
    private List<BusStep> f4052e = new ArrayList();

    public float getCost() {
        return this.f4048a;
    }

    public void setCost(float f) {
        this.f4048a = f;
    }

    public boolean isNightBus() {
        return this.f4049b;
    }

    public void setNightBus(boolean z) {
        this.f4049b = z;
    }

    public float getDistance() {
        return this.f4050c + this.f4051d;
    }

    public float getWalkDistance() {
        return this.f4050c;
    }

    public void setWalkDistance(float f) {
        this.f4050c = f;
    }

    public float getBusDistance() {
        return this.f4051d;
    }

    public void setBusDistance(float f) {
        this.f4051d = f;
    }

    public List<BusStep> getSteps() {
        return this.f4052e;
    }

    public void setSteps(List<BusStep> list) {
        this.f4052e = list;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.f4048a);
        parcel.writeBooleanArray(new boolean[]{this.f4049b});
        parcel.writeFloat(this.f4050c);
        parcel.writeFloat(this.f4051d);
        parcel.writeTypedList(this.f4052e);
    }

    public BusPath(Parcel parcel) {
        super(parcel);
        this.f4048a = parcel.readFloat();
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.f4049b = zArr[0];
        this.f4050c = parcel.readFloat();
        this.f4051d = parcel.readFloat();
        this.f4052e = parcel.createTypedArrayList(BusStep.CREATOR);
    }
}
