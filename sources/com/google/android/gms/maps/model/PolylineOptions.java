package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.support.p000v4.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

public final class PolylineOptions implements SafeParcelable {
    public static final zzi CREATOR = new zzi();
    private int mColor;
    private final int mVersionCode;
    private float zzbaC;
    private boolean zzbaH;
    private final List<LatLng> zzbaZ;
    private float zzbax;
    private boolean zzbay;
    private boolean zzbbb;

    public PolylineOptions() {
        this.zzbaC = 10.0f;
        this.mColor = ViewCompat.MEASURED_STATE_MASK;
        this.zzbax = 0.0f;
        this.zzbay = true;
        this.zzbbb = false;
        this.zzbaH = false;
        this.mVersionCode = 1;
        this.zzbaZ = new ArrayList();
    }

    PolylineOptions(int i, List list, float f, int i2, float f2, boolean z, boolean z2, boolean z3) {
        this.zzbaC = 10.0f;
        this.mColor = ViewCompat.MEASURED_STATE_MASK;
        this.zzbax = 0.0f;
        this.zzbay = true;
        this.zzbbb = false;
        this.zzbaH = false;
        this.mVersionCode = i;
        this.zzbaZ = list;
        this.zzbaC = f;
        this.mColor = i2;
        this.zzbax = f2;
        this.zzbay = z;
        this.zzbbb = z2;
        this.zzbaH = z3;
    }

    public int describeContents() {
        return 0;
    }

    public int getColor() {
        return this.mColor;
    }

    public List<LatLng> getPoints() {
        return this.zzbaZ;
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

    public boolean isGeodesic() {
        return this.zzbbb;
    }

    public boolean isVisible() {
        return this.zzbay;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }
}
