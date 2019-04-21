package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.zzd.zza;

public final class GroundOverlayOptions implements SafeParcelable {
    public static final zzc CREATOR = new zzc();
    private final int mVersionCode;
    private LatLngBounds zzaYA;
    private BitmapDescriptor zzbaA;
    private LatLng zzbaB;
    private float zzbaC;
    private float zzbaD;
    private float zzbaE;
    private float zzbaF;
    private float zzbaG;
    private boolean zzbaH;
    private float zzbaq;
    private float zzbax;
    private boolean zzbay;

    public GroundOverlayOptions() {
        this.zzbay = true;
        this.zzbaE = 0.0f;
        this.zzbaF = 0.5f;
        this.zzbaG = 0.5f;
        this.zzbaH = false;
        this.mVersionCode = 1;
    }

    GroundOverlayOptions(int i, IBinder iBinder, LatLng latLng, float f, float f2, LatLngBounds latLngBounds, float f3, float f4, boolean z, float f5, float f6, float f7, boolean z2) {
        this.zzbay = true;
        this.zzbaE = 0.0f;
        this.zzbaF = 0.5f;
        this.zzbaG = 0.5f;
        this.zzbaH = false;
        this.mVersionCode = i;
        this.zzbaA = new BitmapDescriptor(zza.zzbz(iBinder));
        this.zzbaB = latLng;
        this.zzbaC = f;
        this.zzbaD = f2;
        this.zzaYA = latLngBounds;
        this.zzbaq = f3;
        this.zzbax = f4;
        this.zzbay = z;
        this.zzbaE = f5;
        this.zzbaF = f6;
        this.zzbaG = f7;
        this.zzbaH = z2;
    }

    public int describeContents() {
        return 0;
    }

    public float getAnchorU() {
        return this.zzbaF;
    }

    public float getAnchorV() {
        return this.zzbaG;
    }

    public float getBearing() {
        return this.zzbaq;
    }

    public LatLngBounds getBounds() {
        return this.zzaYA;
    }

    public float getHeight() {
        return this.zzbaD;
    }

    public LatLng getLocation() {
        return this.zzbaB;
    }

    public float getTransparency() {
        return this.zzbaE;
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public float getWidth() {
        return this.zzbaC;
    }

    public float getZIndex() {
        return this.zzbax;
    }

    public boolean isClickable() {
        return this.zzbaH;
    }

    public boolean isVisible() {
        return this.zzbay;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.zza(this, parcel, i);
    }

    /* Access modifiers changed, original: 0000 */
    public IBinder zzDT() {
        return this.zzbaA.zzDq().asBinder();
    }
}
