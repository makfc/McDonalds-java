package com.google.android.gms.location.places;

import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.common.internal.zzz.zza;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class NearbyAlertFilter extends zza implements SafeParcelable {
    public static final zzd CREATOR = new zzd();
    final int mVersionCode;
    final String zzaWA;
    final boolean zzaWB;
    private final Set<String> zzaWC;
    private final Set<Integer> zzaWD;
    private final Set<UserDataType> zzaWE;
    final List<String> zzaWx;
    final List<Integer> zzaWy;
    final List<UserDataType> zzaWz;

    NearbyAlertFilter(int i, @Nullable List<String> list, @Nullable List<Integer> list2, @Nullable List<UserDataType> list3, @Nullable String str, boolean z) {
        this.mVersionCode = i;
        this.zzaWy = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
        this.zzaWz = list3 == null ? Collections.emptyList() : Collections.unmodifiableList(list3);
        this.zzaWx = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
        this.zzaWD = zza.zzz(this.zzaWy);
        this.zzaWE = zza.zzz(this.zzaWz);
        this.zzaWC = zza.zzz(this.zzaWx);
        this.zzaWA = str;
        this.zzaWB = z;
    }

    public static NearbyAlertFilter zzh(Collection<String> collection) {
        if (collection != null && !collection.isEmpty()) {
            return new NearbyAlertFilter(0, zza.zzf(collection), null, null, null, false);
        }
        throw new IllegalArgumentException("NearbyAlertFilters must contain at least oneplace ID to match results with.");
    }

    public static NearbyAlertFilter zzi(Collection<Integer> collection) {
        if (collection != null && !collection.isEmpty()) {
            return new NearbyAlertFilter(0, null, zza.zzf(collection), null, null, false);
        }
        throw new IllegalArgumentException("NearbyAlertFilters must contain at least oneplace type to match results with.");
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NearbyAlertFilter)) {
            return false;
        }
        NearbyAlertFilter nearbyAlertFilter = (NearbyAlertFilter) obj;
        return (this.zzaWA != null || nearbyAlertFilter.zzaWA == null) ? this.zzaWD.equals(nearbyAlertFilter.zzaWD) && this.zzaWE.equals(nearbyAlertFilter.zzaWE) && this.zzaWC.equals(nearbyAlertFilter.zzaWC) && ((this.zzaWA == null || this.zzaWA.equals(nearbyAlertFilter.zzaWA)) && this.zzaWB == nearbyAlertFilter.zzCF()) : false;
    }

    public int hashCode() {
        return zzz.hashCode(this.zzaWD, this.zzaWE, this.zzaWC, this.zzaWA, Boolean.valueOf(this.zzaWB));
    }

    public String toString() {
        zza zzy = zzz.zzy(this);
        if (!this.zzaWD.isEmpty()) {
            zzy.zzg("types", this.zzaWD);
        }
        if (!this.zzaWC.isEmpty()) {
            zzy.zzg("placeIds", this.zzaWC);
        }
        if (!this.zzaWE.isEmpty()) {
            zzy.zzg("requestedUserDataTypes", this.zzaWE);
        }
        if (this.zzaWA != null) {
            zzy.zzg("chainName", this.zzaWA);
        }
        zzy.zzg("Beacon required: ", Boolean.valueOf(this.zzaWB));
        return zzy.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }

    public boolean zzCF() {
        return this.zzaWB;
    }
}
