package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.support.p000v4.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class CircleOptions implements SafeParcelable {
    public static final zzb CREATOR = new zzb();
    private final int mVersionCode;
    private LatLng zzbas;
    private double zzbat;
    private float zzbau;
    private int zzbav;
    private int zzbaw;
    private float zzbax;
    private boolean zzbay;

    public CircleOptions() {
        this.zzbas = null;
        this.zzbat = 0.0d;
        this.zzbau = 10.0f;
        this.zzbav = ViewCompat.MEASURED_STATE_MASK;
        this.zzbaw = 0;
        this.zzbax = 0.0f;
        this.zzbay = true;
        this.mVersionCode = 1;
    }

    CircleOptions(int i, LatLng latLng, double d, float f, int i2, int i3, float f2, boolean z) {
        this.zzbas = null;
        this.zzbat = 0.0d;
        this.zzbau = 10.0f;
        this.zzbav = ViewCompat.MEASURED_STATE_MASK;
        this.zzbaw = 0;
        this.zzbax = 0.0f;
        this.zzbay = true;
        this.mVersionCode = i;
        this.zzbas = latLng;
        this.zzbat = d;
        this.zzbau = f;
        this.zzbav = i2;
        this.zzbaw = i3;
        this.zzbax = f2;
        this.zzbay = z;
    }

    public int describeContents() {
        return 0;
    }

    public LatLng getCenter() {
        return this.zzbas;
    }

    public int getFillColor() {
        return this.zzbaw;
    }

    public double getRadius() {
        return this.zzbat;
    }

    public int getStrokeColor() {
        return this.zzbav;
    }

    public float getStrokeWidth() {
        return this.zzbau;
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public float getZIndex() {
        return this.zzbax;
    }

    public boolean isVisible() {
        return this.zzbay;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }
}
