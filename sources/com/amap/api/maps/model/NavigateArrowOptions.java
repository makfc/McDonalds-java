package com.amap.api.maps.model;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class NavigateArrowOptions implements Parcelable {
    public static final NavigateArrowOptionsCreator CREATOR = new NavigateArrowOptionsCreator();
    /* renamed from: a */
    String f3219a;
    /* renamed from: b */
    private final List<LatLng> f3220b = new ArrayList();
    /* renamed from: c */
    private float f3221c = 10.0f;
    /* renamed from: d */
    private int f3222d = Color.argb(221, 87, 235, 204);
    /* renamed from: e */
    private int f3223e = Color.argb(170, 0, 172, 146);
    /* renamed from: f */
    private float f3224f = 0.0f;
    /* renamed from: g */
    private boolean f3225g = true;

    public NavigateArrowOptions add(LatLng latLng) {
        this.f3220b.add(latLng);
        return this;
    }

    public NavigateArrowOptions add(LatLng... latLngArr) {
        this.f3220b.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public NavigateArrowOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            this.f3220b.add(add);
        }
        return this;
    }

    public NavigateArrowOptions width(float f) {
        this.f3221c = f;
        return this;
    }

    public NavigateArrowOptions topColor(int i) {
        this.f3222d = i;
        return this;
    }

    @Deprecated
    public NavigateArrowOptions sideColor(int i) {
        this.f3223e = i;
        return this;
    }

    public NavigateArrowOptions zIndex(float f) {
        this.f3224f = f;
        return this;
    }

    public NavigateArrowOptions visible(boolean z) {
        this.f3225g = z;
        return this;
    }

    public List<LatLng> getPoints() {
        return this.f3220b;
    }

    public float getWidth() {
        return this.f3221c;
    }

    public int getTopColor() {
        return this.f3222d;
    }

    @Deprecated
    public int getSideColor() {
        return this.f3223e;
    }

    public float getZIndex() {
        return this.f3224f;
    }

    public boolean isVisible() {
        return this.f3225g;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f3220b);
        parcel.writeFloat(this.f3221c);
        parcel.writeInt(this.f3222d);
        parcel.writeInt(this.f3223e);
        parcel.writeFloat(this.f3224f);
        parcel.writeByte((byte) (this.f3225g ? 1 : 0));
        parcel.writeString(this.f3219a);
    }
}
