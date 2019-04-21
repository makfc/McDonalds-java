package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;

public final class LocationAvailability extends AbstractSafeParcelable {
    public static final LocationAvailabilityCreator CREATOR = new LocationAvailabilityCreator();
    private final int mVersionCode;
    int zzaVk;
    int zzaVl;
    long zzaVm;
    int zzaVn;

    LocationAvailability(int i, int i2, int i3, int i4, long j) {
        this.mVersionCode = i;
        this.zzaVn = i2;
        this.zzaVk = i3;
        this.zzaVl = i4;
        this.zzaVm = j;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LocationAvailability)) {
            return false;
        }
        LocationAvailability locationAvailability = (LocationAvailability) obj;
        return this.zzaVn == locationAvailability.zzaVn && this.zzaVk == locationAvailability.zzaVk && this.zzaVl == locationAvailability.zzaVl && this.zzaVm == locationAvailability.zzaVm;
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzz.hashCode(Integer.valueOf(this.zzaVn), Integer.valueOf(this.zzaVk), Integer.valueOf(this.zzaVl), Long.valueOf(this.zzaVm));
    }

    public boolean isLocationAvailable() {
        return this.zzaVn < 1000;
    }

    public String toString() {
        return "LocationAvailability[isLocationAvailable: " + isLocationAvailable() + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        LocationAvailabilityCreator.zza(this, parcel, i);
    }
}
