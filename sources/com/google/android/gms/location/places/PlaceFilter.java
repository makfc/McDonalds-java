package com.google.android.gms.location.places;

import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzz;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class PlaceFilter extends zza implements SafeParcelable {
    public static final zzg CREATOR = new zzg();
    private static final PlaceFilter zzaWN = new PlaceFilter();
    final int mVersionCode;
    private final Set<String> zzaWC;
    private final Set<Integer> zzaWD;
    private final Set<UserDataType> zzaWE;
    final boolean zzaWO;
    final List<String> zzaWx;
    final List<Integer> zzaWy;
    final List<UserDataType> zzaWz;

    @Deprecated
    public static final class zza {
        private boolean zzaWO;
        private Collection<Integer> zzaWP;
        private Collection<UserDataType> zzaWQ;
        private String[] zzaWR;

        private zza() {
            this.zzaWP = null;
            this.zzaWO = false;
            this.zzaWQ = null;
            this.zzaWR = null;
        }

        public PlaceFilter zzCN() {
            return new PlaceFilter(null, false, null, null);
        }
    }

    public PlaceFilter() {
        this(false, null);
    }

    PlaceFilter(int i, @Nullable List<Integer> list, boolean z, @Nullable List<String> list2, @Nullable List<UserDataType> list3) {
        this.mVersionCode = i;
        this.zzaWy = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
        this.zzaWO = z;
        this.zzaWz = list3 == null ? Collections.emptyList() : Collections.unmodifiableList(list3);
        this.zzaWx = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
        this.zzaWD = zza.zzz(this.zzaWy);
        this.zzaWE = zza.zzz(this.zzaWz);
        this.zzaWC = zza.zzz(this.zzaWx);
    }

    public PlaceFilter(@Nullable Collection<Integer> collection, boolean z, @Nullable Collection<String> collection2, @Nullable Collection<UserDataType> collection3) {
        this(0, zza.zzf(collection), z, zza.zzf(collection2), zza.zzf(collection3));
    }

    public PlaceFilter(boolean z, @Nullable Collection<String> collection) {
        this(null, z, collection, null);
    }

    @Deprecated
    public static PlaceFilter zzCM() {
        return new zza().zzCN();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceFilter)) {
            return false;
        }
        PlaceFilter placeFilter = (PlaceFilter) obj;
        return this.zzaWD.equals(placeFilter.zzaWD) && this.zzaWO == placeFilter.zzaWO && this.zzaWE.equals(placeFilter.zzaWE) && this.zzaWC.equals(placeFilter.zzaWC);
    }

    public Set<String> getPlaceIds() {
        return this.zzaWC;
    }

    public int hashCode() {
        return zzz.hashCode(this.zzaWD, Boolean.valueOf(this.zzaWO), this.zzaWE, this.zzaWC);
    }

    public String toString() {
        com.google.android.gms.common.internal.zzz.zza zzy = zzz.zzy(this);
        if (!this.zzaWD.isEmpty()) {
            zzy.zzg("types", this.zzaWD);
        }
        zzy.zzg("requireOpenNow", Boolean.valueOf(this.zzaWO));
        if (!this.zzaWC.isEmpty()) {
            zzy.zzg("placeIds", this.zzaWC);
        }
        if (!this.zzaWE.isEmpty()) {
            zzy.zzg("requestedUserDataTypes", this.zzaWE);
        }
        return zzy.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }

    public Set<Integer> zzCL() {
        return this.zzaWD;
    }
}
