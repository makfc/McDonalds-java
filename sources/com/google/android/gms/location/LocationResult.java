package com.google.android.gms.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class LocationResult extends AbstractSafeParcelable {
    public static final Creator<LocationResult> CREATOR = new zzf();
    static final List<Location> zzaVt = Collections.emptyList();
    private final int mVersionCode;
    private final List<Location> zzaVu;

    LocationResult(int i, List<Location> list) {
        this.mVersionCode = i;
        this.zzaVu = list;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LocationResult)) {
            return false;
        }
        LocationResult locationResult = (LocationResult) obj;
        if (locationResult.zzaVu.size() != this.zzaVu.size()) {
            return false;
        }
        Iterator it = this.zzaVu.iterator();
        for (Location time : locationResult.zzaVu) {
            if (((Location) it.next()).getTime() != time.getTime()) {
                return false;
            }
        }
        return true;
    }

    @NonNull
    public List<Location> getLocations() {
        return this.zzaVu;
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        int i = 17;
        Iterator it = this.zzaVu.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            long time = ((Location) it.next()).getTime();
            i = ((int) (time ^ (time >>> 32))) + (i2 * 31);
        }
    }

    public String toString() {
        String valueOf = String.valueOf(this.zzaVu);
        return new StringBuilder(String.valueOf(valueOf).length() + 27).append("LocationResult[locations: ").append(valueOf).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }
}
