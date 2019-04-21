package com.google.android.gms.location.places.internal;

import android.annotation.SuppressLint;
import android.os.Parcel;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import java.util.Locale;

public class PlacesParams extends AbstractSafeParcelable {
    public static final zzs CREATOR = new zzs();
    public static final PlacesParams zzaYm = new PlacesParams("com.google.android.gms", Locale.getDefault(), null);
    public final int versionCode;
    public final String zzaXm;
    public final String zzaYn;
    public final String zzaYo;
    public final String zzaYp;
    public final int zzaYq;
    public final int zzaYr;

    public PlacesParams(int i, String str, String str2, String str3, String str4, int i2, int i3) {
        this.versionCode = i;
        this.zzaYn = str;
        this.zzaYo = str2;
        this.zzaYp = str3;
        this.zzaXm = str4;
        this.zzaYq = i2;
        this.zzaYr = i3;
    }

    public PlacesParams(String str, Locale locale, String str2) {
        this(3, str, locale.toString(), str2, null, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, 0);
    }

    public PlacesParams(String str, Locale locale, String str2, String str3, int i) {
        this(3, str, locale.toString(), str2, str3, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PlacesParams)) {
            return false;
        }
        PlacesParams placesParams = (PlacesParams) obj;
        return this.zzaYq == placesParams.zzaYq && this.zzaYr == placesParams.zzaYr && this.zzaYo.equals(placesParams.zzaYo) && this.zzaYn.equals(placesParams.zzaYn) && zzz.equal(this.zzaYp, placesParams.zzaYp) && zzz.equal(this.zzaXm, placesParams.zzaXm);
    }

    public int hashCode() {
        return zzz.hashCode(this.zzaYn, this.zzaYo, this.zzaYp, this.zzaXm, Integer.valueOf(this.zzaYq), Integer.valueOf(this.zzaYr));
    }

    @SuppressLint({"DefaultLocale"})
    public String toString() {
        return zzz.zzy(this).zzg("clientPackageName", this.zzaYn).zzg("locale", this.zzaYo).zzg("accountName", this.zzaYp).zzg("gCoreClientName", this.zzaXm).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzs.zza(this, parcel, i);
    }
}
