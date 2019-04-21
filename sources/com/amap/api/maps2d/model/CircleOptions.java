package com.amap.api.maps2d.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.ViewCompat;

public final class CircleOptions implements Parcelable {
    public static final CircleOptionsCreator CREATOR = new CircleOptionsCreator();
    /* renamed from: a */
    String f3385a;
    /* renamed from: b */
    private LatLng f3386b = null;
    /* renamed from: c */
    private double f3387c = 0.0d;
    /* renamed from: d */
    private float f3388d = 10.0f;
    /* renamed from: e */
    private int f3389e = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: f */
    private int f3390f = 0;
    /* renamed from: g */
    private float f3391g = 0.0f;
    /* renamed from: h */
    private boolean f3392h = true;

    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        if (this.f3386b != null) {
            bundle.putDouble("lat", this.f3386b.latitude);
            bundle.putDouble("lng", this.f3386b.longitude);
        }
        parcel.writeBundle(bundle);
        parcel.writeDouble(this.f3387c);
        parcel.writeFloat(this.f3388d);
        parcel.writeInt(this.f3389e);
        parcel.writeInt(this.f3390f);
        parcel.writeFloat(this.f3391g);
        parcel.writeByte((byte) (this.f3392h ? 1 : 0));
        parcel.writeString(this.f3385a);
    }

    public int describeContents() {
        return 0;
    }

    public CircleOptions center(LatLng latLng) {
        this.f3386b = latLng;
        return this;
    }

    public CircleOptions radius(double d) {
        this.f3387c = d;
        return this;
    }

    public CircleOptions strokeWidth(float f) {
        this.f3388d = f;
        return this;
    }

    public CircleOptions strokeColor(int i) {
        this.f3389e = i;
        return this;
    }

    public CircleOptions fillColor(int i) {
        this.f3390f = i;
        return this;
    }

    public CircleOptions zIndex(float f) {
        this.f3391g = f;
        return this;
    }

    public CircleOptions visible(boolean z) {
        this.f3392h = z;
        return this;
    }

    public LatLng getCenter() {
        return this.f3386b;
    }

    public double getRadius() {
        return this.f3387c;
    }

    public float getStrokeWidth() {
        return this.f3388d;
    }

    public int getStrokeColor() {
        return this.f3389e;
    }

    public int getFillColor() {
        return this.f3390f;
    }

    public float getZIndex() {
        return this.f3391g;
    }

    public boolean isVisible() {
        return this.f3392h;
    }
}
