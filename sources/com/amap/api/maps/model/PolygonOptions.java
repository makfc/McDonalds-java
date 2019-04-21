package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.ViewCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PolygonOptions implements Parcelable {
    public static final PolygonOptionsCreator CREATOR = new PolygonOptionsCreator();
    /* renamed from: a */
    String f3232a;
    /* renamed from: b */
    private final List<LatLng> f3233b = new ArrayList();
    /* renamed from: c */
    private float f3234c = 10.0f;
    /* renamed from: d */
    private int f3235d = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: e */
    private int f3236e = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: f */
    private float f3237f = 0.0f;
    /* renamed from: g */
    private boolean f3238g = true;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f3233b);
        parcel.writeFloat(this.f3234c);
        parcel.writeInt(this.f3235d);
        parcel.writeInt(this.f3236e);
        parcel.writeFloat(this.f3237f);
        parcel.writeByte((byte) (this.f3238g ? 1 : 0));
        parcel.writeString(this.f3232a);
    }

    public PolygonOptions add(LatLng latLng) {
        this.f3233b.add(latLng);
        return this;
    }

    public PolygonOptions add(LatLng... latLngArr) {
        this.f3233b.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public PolygonOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            this.f3233b.add(add);
        }
        return this;
    }

    public PolygonOptions strokeWidth(float f) {
        this.f3234c = f;
        return this;
    }

    public PolygonOptions strokeColor(int i) {
        this.f3235d = i;
        return this;
    }

    public PolygonOptions fillColor(int i) {
        this.f3236e = i;
        return this;
    }

    public PolygonOptions zIndex(float f) {
        this.f3237f = f;
        return this;
    }

    public PolygonOptions visible(boolean z) {
        this.f3238g = z;
        return this;
    }

    public List<LatLng> getPoints() {
        return this.f3233b;
    }

    public float getStrokeWidth() {
        return this.f3234c;
    }

    public int getStrokeColor() {
        return this.f3235d;
    }

    public int getFillColor() {
        return this.f3236e;
    }

    public float getZIndex() {
        return this.f3237f;
    }

    public boolean isVisible() {
        return this.f3238g;
    }
}
