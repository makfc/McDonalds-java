package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

public class LocationRequestInternal extends AbstractSafeParcelable {
    public static final zzm CREATOR = new zzm();
    static final List<ClientIdentity> zzaWe = Collections.emptyList();
    @Nullable
    String mTag;
    private final int mVersionCode;
    LocationRequest zzaIc;
    boolean zzaUF;
    List<ClientIdentity> zzaWf;
    boolean zzaWg;

    LocationRequestInternal(int i, LocationRequest locationRequest, boolean z, List<ClientIdentity> list, @Nullable String str, boolean z2) {
        this.mVersionCode = i;
        this.zzaIc = locationRequest;
        this.zzaUF = z;
        this.zzaWf = list;
        this.mTag = str;
        this.zzaWg = z2;
    }

    public static LocationRequestInternal zza(@Nullable String str, LocationRequest locationRequest) {
        return new LocationRequestInternal(1, locationRequest, true, zzaWe, str, false);
    }

    @Deprecated
    public static LocationRequestInternal zzb(LocationRequest locationRequest) {
        return zza(null, locationRequest);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LocationRequestInternal)) {
            return false;
        }
        LocationRequestInternal locationRequestInternal = (LocationRequestInternal) obj;
        return zzz.equal(this.zzaIc, locationRequestInternal.zzaIc) && this.zzaUF == locationRequestInternal.zzaUF && this.zzaWg == locationRequestInternal.zzaWg && zzz.equal(this.zzaWf, locationRequestInternal.zzaWf);
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return this.zzaIc.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.zzaIc.toString());
        if (this.mTag != null) {
            stringBuilder.append(" tag=").append(this.mTag);
        }
        stringBuilder.append(" trigger=").append(this.zzaUF);
        stringBuilder.append(" hideAppOps=").append(this.zzaWg);
        stringBuilder.append(" clients=").append(this.zzaWf);
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzm.zza(this, parcel, i);
    }
}
