package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.ViewCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PolylineOptions implements Parcelable {
    public static final PolylineOptionsCreator CREATOR = new PolylineOptionsCreator();
    /* renamed from: a */
    String f3448a;
    /* renamed from: b */
    private final List<LatLng> f3449b = new ArrayList();
    /* renamed from: c */
    private float f3450c = 10.0f;
    /* renamed from: d */
    private int f3451d = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: e */
    private float f3452e = 0.0f;
    /* renamed from: f */
    private boolean f3453f = true;
    /* renamed from: g */
    private boolean f3454g = false;
    /* renamed from: h */
    private boolean f3455h = false;

    public PolylineOptions add(LatLng latLng) {
        this.f3449b.add(latLng);
        return this;
    }

    public PolylineOptions add(LatLng... latLngArr) {
        this.f3449b.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public PolylineOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            this.f3449b.add(add);
        }
        return this;
    }

    public PolylineOptions width(float f) {
        this.f3450c = f;
        return this;
    }

    public PolylineOptions color(int i) {
        this.f3451d = i;
        return this;
    }

    public PolylineOptions zIndex(float f) {
        this.f3452e = f;
        return this;
    }

    public PolylineOptions visible(boolean z) {
        this.f3453f = z;
        return this;
    }

    public PolylineOptions geodesic(boolean z) {
        this.f3454g = z;
        return this;
    }

    public List<LatLng> getPoints() {
        return this.f3449b;
    }

    public float getWidth() {
        return this.f3450c;
    }

    public int getColor() {
        return this.f3451d;
    }

    public float getZIndex() {
        return this.f3452e;
    }

    public boolean isVisible() {
        return this.f3453f;
    }

    public PolylineOptions setDottedLine(boolean z) {
        this.f3455h = z;
        return this;
    }

    public boolean isDottedLine() {
        return this.f3455h;
    }

    public boolean isGeodesic() {
        return this.f3454g;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeTypedList(getPoints());
        parcel.writeFloat(getWidth());
        parcel.writeInt(getColor());
        parcel.writeFloat(getZIndex());
        parcel.writeByte((byte) (isVisible() ? 1 : 0));
        parcel.writeString(this.f3448a);
        if (isGeodesic()) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (!isDottedLine()) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
    }
}
