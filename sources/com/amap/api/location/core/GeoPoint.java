package com.amap.api.location.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class GeoPoint implements Parcelable {
    public static final Creator<GeoPoint> CREATOR = new C0728f();
    /* renamed from: a */
    private long f879a;
    /* renamed from: b */
    private long f880b;
    /* renamed from: c */
    private double f881c;
    /* renamed from: d */
    private double f882d;

    public GeoPoint() {
        this.f879a = Long.MIN_VALUE;
        this.f880b = Long.MIN_VALUE;
        this.f881c = Double.MIN_VALUE;
        this.f882d = Double.MIN_VALUE;
        this.f879a = 0;
        this.f880b = 0;
    }

    public GeoPoint(int i, int i2) {
        this.f879a = Long.MIN_VALUE;
        this.f880b = Long.MIN_VALUE;
        this.f881c = Double.MIN_VALUE;
        this.f882d = Double.MIN_VALUE;
        this.f879a = (long) i;
        this.f880b = (long) i2;
    }

    public GeoPoint(long j, long j2) {
        this.f879a = Long.MIN_VALUE;
        this.f880b = Long.MIN_VALUE;
        this.f881c = Double.MIN_VALUE;
        this.f882d = Double.MIN_VALUE;
        this.f879a = j;
        this.f880b = j2;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        GeoPoint geoPoint = (GeoPoint) obj;
        if (this.f881c == geoPoint.f881c && this.f882d == geoPoint.f882d && this.f879a == geoPoint.f879a && this.f880b == geoPoint.f880b) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) ((this.f882d * 7.0d) + (this.f881c * 11.0d));
    }

    public String toString() {
        return "" + this.f879a + "," + this.f880b;
    }

    public int getLongitudeE6() {
        return (int) this.f880b;
    }

    public int getLatitudeE6() {
        return (int) this.f879a;
    }

    private GeoPoint(Parcel parcel) {
        this.f879a = Long.MIN_VALUE;
        this.f880b = Long.MIN_VALUE;
        this.f881c = Double.MIN_VALUE;
        this.f882d = Double.MIN_VALUE;
        this.f879a = parcel.readLong();
        this.f880b = parcel.readLong();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f879a);
        parcel.writeLong(this.f880b);
    }
}
