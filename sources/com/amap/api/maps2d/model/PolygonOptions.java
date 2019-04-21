package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.ViewCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PolygonOptions implements Parcelable {
    public static final PolygonOptionsCreator CREATOR = new PolygonOptionsCreator();
    /* renamed from: a */
    String f3440a;
    /* renamed from: b */
    private final List<LatLng> f3441b = new ArrayList();
    /* renamed from: c */
    private float f3442c = 10.0f;
    /* renamed from: d */
    private int f3443d = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: e */
    private int f3444e = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: f */
    private float f3445f = 0.0f;
    /* renamed from: g */
    private boolean f3446g = true;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f3441b);
        parcel.writeFloat(this.f3442c);
        parcel.writeInt(this.f3443d);
        parcel.writeInt(this.f3444e);
        parcel.writeFloat(this.f3445f);
        parcel.writeByte((byte) (this.f3446g ? 0 : 1));
        parcel.writeString(this.f3440a);
    }

    public PolygonOptions add(LatLng latLng) {
        this.f3441b.add(latLng);
        return this;
    }

    public PolygonOptions add(LatLng... latLngArr) {
        this.f3441b.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public PolygonOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            this.f3441b.add(add);
        }
        return this;
    }

    public PolygonOptions strokeWidth(float f) {
        this.f3442c = f;
        return this;
    }

    public PolygonOptions strokeColor(int i) {
        this.f3443d = i;
        return this;
    }

    public PolygonOptions fillColor(int i) {
        this.f3444e = i;
        return this;
    }

    public PolygonOptions zIndex(float f) {
        this.f3445f = f;
        return this;
    }

    public PolygonOptions visible(boolean z) {
        this.f3446g = z;
        return this;
    }

    public List<LatLng> getPoints() {
        return this.f3441b;
    }

    public float getStrokeWidth() {
        return this.f3442c;
    }

    public int getStrokeColor() {
        return this.f3443d;
    }

    public int getFillColor() {
        return this.f3444e;
    }

    public float getZIndex() {
        return this.f3445f;
    }

    public boolean isVisible() {
        return this.f3446g;
    }
}
