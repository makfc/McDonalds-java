package com.amap.api.maps.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.ViewCompat;

public final class CircleOptions implements Parcelable {
    public static final CircleOptionsCreator CREATOR = new CircleOptionsCreator();
    /* renamed from: a */
    String f3139a;
    /* renamed from: b */
    private LatLng f3140b = null;
    /* renamed from: c */
    private double f3141c = 0.0d;
    /* renamed from: d */
    private float f3142d = 10.0f;
    /* renamed from: e */
    private int f3143e = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: f */
    private int f3144f = 0;
    /* renamed from: g */
    private float f3145g = 0.0f;
    /* renamed from: h */
    private boolean f3146h = true;

    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        if (this.f3140b != null) {
            bundle.putDouble("lat", this.f3140b.latitude);
            bundle.putDouble("lng", this.f3140b.longitude);
        }
        parcel.writeBundle(bundle);
        parcel.writeDouble(this.f3141c);
        parcel.writeFloat(this.f3142d);
        parcel.writeInt(this.f3143e);
        parcel.writeInt(this.f3144f);
        parcel.writeFloat(this.f3145g);
        parcel.writeByte((byte) (this.f3146h ? 1 : 0));
        parcel.writeString(this.f3139a);
    }

    public int describeContents() {
        return 0;
    }

    public CircleOptions center(LatLng latLng) {
        this.f3140b = latLng;
        return this;
    }

    public CircleOptions radius(double d) {
        this.f3141c = d;
        return this;
    }

    public CircleOptions strokeWidth(float f) {
        this.f3142d = f;
        return this;
    }

    public CircleOptions strokeColor(int i) {
        this.f3143e = i;
        return this;
    }

    public CircleOptions fillColor(int i) {
        this.f3144f = i;
        return this;
    }

    public CircleOptions zIndex(float f) {
        this.f3145g = f;
        return this;
    }

    public CircleOptions visible(boolean z) {
        this.f3146h = z;
        return this;
    }

    public LatLng getCenter() {
        return this.f3140b;
    }

    public double getRadius() {
        return this.f3141c;
    }

    public float getStrokeWidth() {
        return this.f3142d;
    }

    public int getStrokeColor() {
        return this.f3143e;
    }

    public int getFillColor() {
        return this.f3144f;
    }

    public float getZIndex() {
        return this.f3145g;
    }

    public boolean isVisible() {
        return this.f3146h;
    }
}
