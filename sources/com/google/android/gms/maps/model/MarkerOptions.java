package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.zzd.zza;

public final class MarkerOptions implements SafeParcelable {
    public static final zzf CREATOR = new zzf();
    private float mAlpha;
    private final int mVersionCode;
    private LatLng zzaZV;
    private String zzavG;
    private float zzbaF;
    private float zzbaG;
    private String zzbaP;
    private BitmapDescriptor zzbaQ;
    private boolean zzbaR;
    private boolean zzbaS;
    private float zzbaT;
    private float zzbaU;
    private float zzbaV;
    private boolean zzbay;

    public MarkerOptions() {
        this.zzbaF = 0.5f;
        this.zzbaG = 1.0f;
        this.zzbay = true;
        this.zzbaS = false;
        this.zzbaT = 0.0f;
        this.zzbaU = 0.5f;
        this.zzbaV = 0.0f;
        this.mAlpha = 1.0f;
        this.mVersionCode = 1;
    }

    MarkerOptions(int i, LatLng latLng, String str, String str2, IBinder iBinder, float f, float f2, boolean z, boolean z2, boolean z3, float f3, float f4, float f5, float f6) {
        this.zzbaF = 0.5f;
        this.zzbaG = 1.0f;
        this.zzbay = true;
        this.zzbaS = false;
        this.zzbaT = 0.0f;
        this.zzbaU = 0.5f;
        this.zzbaV = 0.0f;
        this.mAlpha = 1.0f;
        this.mVersionCode = i;
        this.zzaZV = latLng;
        this.zzavG = str;
        this.zzbaP = str2;
        this.zzbaQ = iBinder == null ? null : new BitmapDescriptor(zza.zzbz(iBinder));
        this.zzbaF = f;
        this.zzbaG = f2;
        this.zzbaR = z;
        this.zzbay = z2;
        this.zzbaS = z3;
        this.zzbaT = f3;
        this.zzbaU = f4;
        this.zzbaV = f5;
        this.mAlpha = f6;
    }

    public int describeContents() {
        return 0;
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public float getAnchorU() {
        return this.zzbaF;
    }

    public float getAnchorV() {
        return this.zzbaG;
    }

    public float getInfoWindowAnchorU() {
        return this.zzbaU;
    }

    public float getInfoWindowAnchorV() {
        return this.zzbaV;
    }

    public LatLng getPosition() {
        return this.zzaZV;
    }

    public float getRotation() {
        return this.zzbaT;
    }

    public String getSnippet() {
        return this.zzbaP;
    }

    public String getTitle() {
        return this.zzavG;
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public MarkerOptions icon(@Nullable BitmapDescriptor bitmapDescriptor) {
        this.zzbaQ = bitmapDescriptor;
        return this;
    }

    public boolean isDraggable() {
        return this.zzbaR;
    }

    public boolean isFlat() {
        return this.zzbaS;
    }

    public boolean isVisible() {
        return this.zzbay;
    }

    public MarkerOptions position(@NonNull LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("latlng cannot be null - a position is required.");
        }
        this.zzaZV = latLng;
        return this;
    }

    public MarkerOptions snippet(@Nullable String str) {
        this.zzbaP = str;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }

    /* Access modifiers changed, original: 0000 */
    public IBinder zzDU() {
        return this.zzbaQ == null ? null : this.zzbaQ.zzDq().asBinder();
    }
}
