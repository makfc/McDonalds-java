package com.amap.api.maps.model;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

public class MyLocationStyle implements Parcelable {
    /* renamed from: a */
    private BitmapDescriptor f3206a;
    /* renamed from: b */
    private float f3207b = 0.5f;
    /* renamed from: c */
    private float f3208c = 0.5f;
    /* renamed from: d */
    private int f3209d = Color.argb(100, 0, 0, 180);
    /* renamed from: e */
    private int f3210e = Color.argb(255, 0, 0, 220);
    /* renamed from: f */
    private float f3211f = 1.0f;

    public MyLocationStyle myLocationIcon(BitmapDescriptor bitmapDescriptor) {
        this.f3206a = bitmapDescriptor;
        return this;
    }

    public MyLocationStyle anchor(float f, float f2) {
        this.f3207b = f;
        this.f3208c = f2;
        return this;
    }

    public MyLocationStyle radiusFillColor(int i) {
        this.f3209d = i;
        return this;
    }

    public MyLocationStyle strokeColor(int i) {
        this.f3210e = i;
        return this;
    }

    public MyLocationStyle strokeWidth(float f) {
        this.f3211f = f;
        return this;
    }

    public BitmapDescriptor getMyLocationIcon() {
        return this.f3206a;
    }

    public float getAnchorU() {
        return this.f3207b;
    }

    public float getAnchorV() {
        return this.f3208c;
    }

    public int getRadiusFillColor() {
        return this.f3209d;
    }

    public int getStrokeColor() {
        return this.f3210e;
    }

    public float getStrokeWidth() {
        return this.f3211f;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3206a, i);
        parcel.writeFloat(this.f3207b);
        parcel.writeFloat(this.f3208c);
        parcel.writeInt(this.f3209d);
        parcel.writeInt(this.f3210e);
        parcel.writeFloat(this.f3211f);
    }
}
