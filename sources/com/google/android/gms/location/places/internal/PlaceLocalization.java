package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import java.util.List;

@Deprecated
public final class PlaceLocalization extends AbstractSafeParcelable {
    public static final zzo CREATOR = new zzo();
    public final String address;
    public final String name;
    public final int versionCode;
    public final String zzaYe;
    public final String zzaYf;
    public final List<String> zzaYg;

    public PlaceLocalization(int i, String str, String str2, String str3, String str4, List<String> list) {
        this.versionCode = i;
        this.name = str;
        this.address = str2;
        this.zzaYe = str3;
        this.zzaYf = str4;
        this.zzaYg = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceLocalization)) {
            return false;
        }
        PlaceLocalization placeLocalization = (PlaceLocalization) obj;
        return zzz.equal(this.name, placeLocalization.name) && zzz.equal(this.address, placeLocalization.address) && zzz.equal(this.zzaYe, placeLocalization.zzaYe) && zzz.equal(this.zzaYf, placeLocalization.zzaYf) && zzz.equal(this.zzaYg, placeLocalization.zzaYg);
    }

    public int hashCode() {
        return zzz.hashCode(this.name, this.address, this.zzaYe, this.zzaYf);
    }

    public String toString() {
        return zzz.zzy(this).zzg("name", this.name).zzg("address", this.address).zzg("internationalPhoneNumber", this.zzaYe).zzg("regularOpenHours", this.zzaYf).zzg("attributions", this.zzaYg).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzo zzo = CREATOR;
        zzo.zza(this, parcel, i);
    }
}
