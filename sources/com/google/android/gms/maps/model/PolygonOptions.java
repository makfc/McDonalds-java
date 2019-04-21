package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.support.p000v4.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

public final class PolygonOptions implements SafeParcelable {
    public static final zzh CREATOR = new zzh();
    private final int mVersionCode;
    private boolean zzbaH;
    private final List<LatLng> zzbaZ;
    private float zzbau;
    private int zzbav;
    private int zzbaw;
    private float zzbax;
    private boolean zzbay;
    private final List<List<LatLng>> zzbba;
    private boolean zzbbb;

    public PolygonOptions() {
        this.zzbau = 10.0f;
        this.zzbav = ViewCompat.MEASURED_STATE_MASK;
        this.zzbaw = 0;
        this.zzbax = 0.0f;
        this.zzbay = true;
        this.zzbbb = false;
        this.zzbaH = false;
        this.mVersionCode = 1;
        this.zzbaZ = new ArrayList();
        this.zzbba = new ArrayList();
    }

    PolygonOptions(int i, List<LatLng> list, List list2, float f, int i2, int i3, float f2, boolean z, boolean z2, boolean z3) {
        this.zzbau = 10.0f;
        this.zzbav = ViewCompat.MEASURED_STATE_MASK;
        this.zzbaw = 0;
        this.zzbax = 0.0f;
        this.zzbay = true;
        this.zzbbb = false;
        this.zzbaH = false;
        this.mVersionCode = i;
        this.zzbaZ = list;
        this.zzbba = list2;
        this.zzbau = f;
        this.zzbav = i2;
        this.zzbaw = i3;
        this.zzbax = f2;
        this.zzbay = z;
        this.zzbbb = z2;
        this.zzbaH = z3;
    }

    public int describeContents() {
        return 0;
    }

    public int getFillColor() {
        return this.zzbaw;
    }

    public List<LatLng> getPoints() {
        return this.zzbaZ;
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
        zzh.zza(this, parcel, i);
    }

    /* Access modifiers changed, original: 0000 */
    public List zzDV() {
        return this.zzbba;
    }
}
