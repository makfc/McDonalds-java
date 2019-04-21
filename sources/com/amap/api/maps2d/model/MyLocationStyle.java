package com.amap.api.maps2d.model;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

public class MyLocationStyle implements Parcelable {
    /* renamed from: a */
    private BitmapDescriptor f3425a;
    /* renamed from: b */
    private float f3426b = 0.5f;
    /* renamed from: c */
    private float f3427c = 0.5f;
    /* renamed from: d */
    private int f3428d = Color.argb(100, 0, 0, 180);
    /* renamed from: e */
    private int f3429e = Color.argb(255, 0, 0, 220);
    /* renamed from: f */
    private float f3430f = 1.0f;

    public MyLocationStyle myLocationIcon(BitmapDescriptor bitmapDescriptor) {
        this.f3425a = bitmapDescriptor;
        return this;
    }

    public MyLocationStyle anchor(float f, float f2) {
        this.f3426b = f;
        this.f3427c = f2;
        return this;
    }

    public MyLocationStyle radiusFillColor(int i) {
        this.f3428d = i;
        return this;
    }

    public MyLocationStyle strokeColor(int i) {
        this.f3429e = i;
        return this;
    }

    public MyLocationStyle strokeWidth(float f) {
        this.f3430f = f;
        return this;
    }

    public BitmapDescriptor getMyLocationIcon() {
        return this.f3425a;
    }

    public float getAnchorU() {
        return this.f3426b;
    }

    public float getAnchorV() {
        return this.f3427c;
    }

    public int getRadiusFillColor() {
        return this.f3428d;
    }

    public int getStrokeColor() {
        return this.f3429e;
    }

    public float getStrokeWidth() {
        return this.f3430f;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3425a, i);
        parcel.writeFloat(this.f3426b);
        parcel.writeFloat(this.f3427c);
        parcel.writeInt(this.f3428d);
        parcel.writeInt(this.f3429e);
        parcel.writeFloat(this.f3430f);
    }
}
