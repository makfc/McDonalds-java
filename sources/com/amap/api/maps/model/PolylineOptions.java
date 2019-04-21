package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.ViewCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PolylineOptions implements Parcelable {
    public static final PolylineOptionsCreator CREATOR = new PolylineOptionsCreator();
    /* renamed from: a */
    String f3240a;
    /* renamed from: b */
    private final List<LatLng> f3241b = new ArrayList();
    /* renamed from: c */
    private float f3242c = 10.0f;
    /* renamed from: d */
    private int f3243d = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: e */
    private float f3244e = 0.0f;
    /* renamed from: f */
    private boolean f3245f = true;
    /* renamed from: g */
    private BitmapDescriptor f3246g;
    /* renamed from: h */
    private List<BitmapDescriptor> f3247h;
    /* renamed from: i */
    private List<Integer> f3248i;
    /* renamed from: j */
    private List<Integer> f3249j;
    /* renamed from: k */
    private boolean f3250k = true;
    /* renamed from: l */
    private boolean f3251l = false;
    /* renamed from: m */
    private boolean f3252m = false;
    /* renamed from: n */
    private boolean f3253n = false;

    public PolylineOptions setUseTexture(boolean z) {
        this.f3250k = z;
        return this;
    }

    public PolylineOptions setCustomTexture(BitmapDescriptor bitmapDescriptor) {
        this.f3246g = bitmapDescriptor;
        return this;
    }

    public BitmapDescriptor getCustomTexture() {
        return this.f3246g;
    }

    public PolylineOptions setCustomTextureList(List<BitmapDescriptor> list) {
        this.f3247h = list;
        return this;
    }

    public List<BitmapDescriptor> getCustomTextureList() {
        return this.f3247h;
    }

    public PolylineOptions setCustomTextureIndex(List<Integer> list) {
        this.f3249j = list;
        return this;
    }

    public List<Integer> getCustomTextureIndex() {
        return this.f3249j;
    }

    public PolylineOptions colorValues(List<Integer> list) {
        this.f3248i = list;
        return this;
    }

    public List<Integer> getColorValues() {
        return this.f3248i;
    }

    public PolylineOptions useGradient(boolean z) {
        this.f3253n = z;
        return this;
    }

    public boolean isUseGradient() {
        return this.f3253n;
    }

    public boolean isUseTexture() {
        return this.f3250k;
    }

    public boolean isGeodesic() {
        return this.f3251l;
    }

    public PolylineOptions add(LatLng latLng) {
        this.f3241b.add(latLng);
        return this;
    }

    public PolylineOptions add(LatLng... latLngArr) {
        this.f3241b.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public PolylineOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            this.f3241b.add(add);
        }
        return this;
    }

    public PolylineOptions width(float f) {
        this.f3242c = f;
        return this;
    }

    public PolylineOptions color(int i) {
        this.f3243d = i;
        return this;
    }

    public PolylineOptions zIndex(float f) {
        this.f3244e = f;
        return this;
    }

    public PolylineOptions visible(boolean z) {
        this.f3245f = z;
        return this;
    }

    public PolylineOptions geodesic(boolean z) {
        this.f3251l = z;
        return this;
    }

    public PolylineOptions setDottedLine(boolean z) {
        this.f3252m = z;
        return this;
    }

    public boolean isDottedLine() {
        return this.f3252m;
    }

    public List<LatLng> getPoints() {
        return this.f3241b;
    }

    public float getWidth() {
        return this.f3242c;
    }

    public int getColor() {
        return this.f3243d;
    }

    public float getZIndex() {
        return this.f3244e;
    }

    public boolean isVisible() {
        return this.f3245f;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f3241b);
        parcel.writeFloat(this.f3242c);
        parcel.writeInt(this.f3243d);
        parcel.writeFloat(this.f3244e);
        parcel.writeString(this.f3240a);
        parcel.writeBooleanArray(new boolean[]{this.f3245f, this.f3252m, this.f3251l, this.f3253n});
        if (this.f3246g != null) {
            parcel.writeParcelable(this.f3246g, i);
        }
        if (this.f3247h != null) {
            parcel.writeList(this.f3247h);
        }
        if (this.f3249j != null) {
            parcel.writeList(this.f3249j);
        }
        if (this.f3248i != null) {
            parcel.writeList(this.f3248i);
        }
    }
}
