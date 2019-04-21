package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class LatLonPoint implements Parcelable {
    public static final Creator<LatLonPoint> CREATOR = new C1129k();
    /* renamed from: a */
    private double f3582a;
    /* renamed from: b */
    private double f3583b;

    public LatLonPoint(double d, double d2) {
        this.f3582a = d;
        this.f3583b = d2;
    }

    public double getLongitude() {
        return this.f3583b;
    }

    public void setLongitude(double d) {
        this.f3583b = d;
    }

    public double getLatitude() {
        return this.f3582a;
    }

    public void setLatitude(double d) {
        this.f3582a = d;
    }

    public LatLonPoint copy() {
        return new LatLonPoint(this.f3582a, this.f3583b);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.f3582a);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.f3583b);
        return (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        LatLonPoint latLonPoint = (LatLonPoint) obj;
        if (Double.doubleToLongBits(this.f3582a) != Double.doubleToLongBits(latLonPoint.f3582a)) {
            return false;
        }
        if (Double.doubleToLongBits(this.f3583b) != Double.doubleToLongBits(latLonPoint.f3583b)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "" + this.f3582a + "," + this.f3583b;
    }

    private LatLonPoint(Parcel parcel) {
        this.f3582a = parcel.readDouble();
        this.f3583b = parcel.readDouble();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.f3582a);
        parcel.writeDouble(this.f3583b);
    }
}
