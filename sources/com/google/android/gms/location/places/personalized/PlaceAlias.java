package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;

@Deprecated
public class PlaceAlias extends AbstractSafeParcelable {
    public static final zzd CREATOR = new zzd();
    public static final PlaceAlias zzaYu = new PlaceAlias(0, "Home");
    public static final PlaceAlias zzaYv = new PlaceAlias(0, "Work");
    final int mVersionCode;
    private final String zzaYw;

    PlaceAlias(int i, String str) {
        this.mVersionCode = i;
        this.zzaYw = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceAlias)) {
            return false;
        }
        return zzz.equal(this.zzaYw, ((PlaceAlias) obj).zzaYw);
    }

    public int hashCode() {
        return zzz.hashCode(this.zzaYw);
    }

    public String toString() {
        return zzz.zzy(this).zzg("alias", this.zzaYw).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }

    public String zzDm() {
        return this.zzaYw;
    }
}
