package com.amap.api.maps.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.ViewCompat;

public final class ArcOptions implements Parcelable {
    public static final ArcOptionsCreator CREATOR = new ArcOptionsCreator();
    /* renamed from: a */
    String f3123a;
    /* renamed from: b */
    private LatLng f3124b;
    /* renamed from: c */
    private LatLng f3125c;
    /* renamed from: d */
    private LatLng f3126d;
    /* renamed from: e */
    private float f3127e = 10.0f;
    /* renamed from: f */
    private int f3128f = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: g */
    private float f3129g = 0.0f;
    /* renamed from: h */
    private boolean f3130h = true;

    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        if (this.f3124b != null) {
            bundle.putDouble("startlat", this.f3124b.latitude);
            bundle.putDouble("startlng", this.f3124b.longitude);
        }
        if (this.f3125c != null) {
            bundle.putDouble("passedlat", this.f3125c.latitude);
            bundle.putDouble("passedlng", this.f3125c.longitude);
        }
        if (this.f3126d != null) {
            bundle.putDouble("endlat", this.f3126d.latitude);
            bundle.putDouble("endlng", this.f3126d.longitude);
        }
        parcel.writeBundle(bundle);
        parcel.writeFloat(this.f3127e);
        parcel.writeInt(this.f3128f);
        parcel.writeFloat(this.f3129g);
        parcel.writeByte((byte) (this.f3130h ? 1 : 0));
        parcel.writeString(this.f3123a);
    }

    public int describeContents() {
        return 0;
    }

    public ArcOptions point(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        this.f3124b = latLng;
        this.f3125c = latLng2;
        this.f3126d = latLng3;
        return this;
    }

    public ArcOptions strokeWidth(float f) {
        this.f3127e = f;
        return this;
    }

    public ArcOptions strokeColor(int i) {
        this.f3128f = i;
        return this;
    }

    public ArcOptions zIndex(float f) {
        this.f3129g = f;
        return this;
    }

    public ArcOptions visible(boolean z) {
        this.f3130h = z;
        return this;
    }

    public float getStrokeWidth() {
        return this.f3127e;
    }

    public int getStrokeColor() {
        return this.f3128f;
    }

    public float getZIndex() {
        return this.f3129g;
    }

    public boolean isVisible() {
        return this.f3130h;
    }

    public LatLng getStart() {
        return this.f3124b;
    }

    public LatLng getPassed() {
        return this.f3125c;
    }

    public LatLng getEnd() {
        return this.f3126d;
    }
}
