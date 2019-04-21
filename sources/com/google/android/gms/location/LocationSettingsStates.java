package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class LocationSettingsStates extends AbstractSafeParcelable {
    public static final Creator<LocationSettingsStates> CREATOR = new zzi();
    private final int mVersionCode;
    private final boolean zzaVA;
    private final boolean zzaVB;
    private final boolean zzaVC;
    private final boolean zzaVD;
    private final boolean zzaVE;
    private final boolean zzaVz;

    LocationSettingsStates(int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.mVersionCode = i;
        this.zzaVz = z;
        this.zzaVA = z2;
        this.zzaVB = z3;
        this.zzaVC = z4;
        this.zzaVD = z5;
        this.zzaVE = z6;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public boolean isBlePresent() {
        return this.zzaVE;
    }

    public boolean isBleUsable() {
        return this.zzaVB;
    }

    public boolean isGpsPresent() {
        return this.zzaVC;
    }

    public boolean isGpsUsable() {
        return this.zzaVz;
    }

    public boolean isNetworkLocationPresent() {
        return this.zzaVD;
    }

    public boolean isNetworkLocationUsable() {
        return this.zzaVA;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }
}
