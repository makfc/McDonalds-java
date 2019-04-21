package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Path implements Parcelable {
    public static final Creator<Path> CREATOR = new C1180i();
    /* renamed from: a */
    private float f4046a;
    /* renamed from: b */
    private long f4047b;

    public float getDistance() {
        return this.f4046a;
    }

    public void setDistance(float f) {
        this.f4046a = f;
    }

    public long getDuration() {
        return this.f4047b;
    }

    public void setDuration(long j) {
        this.f4047b = j;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f4046a);
        parcel.writeLong(this.f4047b);
    }

    public Path(Parcel parcel) {
        this.f4046a = parcel.readFloat();
        this.f4047b = parcel.readLong();
    }
}
