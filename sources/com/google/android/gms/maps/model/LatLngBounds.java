package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzz;

public final class LatLngBounds implements SafeParcelable {
    public static final zzd CREATOR = new zzd();
    private final int mVersionCode;
    public final LatLng northeast;
    public final LatLng southwest;

    public static final class Builder {
        private double zzbaK = Double.POSITIVE_INFINITY;
        private double zzbaL = Double.NEGATIVE_INFINITY;
        private double zzbaM = Double.NaN;
        private double zzbaN = Double.NaN;

        private boolean zzj(double d) {
            boolean z = false;
            if (this.zzbaM <= this.zzbaN) {
                return this.zzbaM <= d && d <= this.zzbaN;
            } else {
                if (this.zzbaM <= d || d <= this.zzbaN) {
                    z = true;
                }
                return z;
            }
        }

        public LatLngBounds build() {
            zzaa.zza(!Double.isNaN(this.zzbaM), "no included points");
            return new LatLngBounds(new LatLng(this.zzbaK, this.zzbaM), new LatLng(this.zzbaL, this.zzbaN));
        }

        public Builder include(LatLng latLng) {
            this.zzbaK = Math.min(this.zzbaK, latLng.latitude);
            this.zzbaL = Math.max(this.zzbaL, latLng.latitude);
            double d = latLng.longitude;
            if (Double.isNaN(this.zzbaM)) {
                this.zzbaM = d;
                this.zzbaN = d;
            } else if (!zzj(d)) {
                if (LatLngBounds.zzb(this.zzbaM, d) < LatLngBounds.zzc(this.zzbaN, d)) {
                    this.zzbaM = d;
                } else {
                    this.zzbaN = d;
                }
            }
            return this;
        }
    }

    LatLngBounds(int i, LatLng latLng, LatLng latLng2) {
        zzaa.zzb((Object) latLng, (Object) "null southwest");
        zzaa.zzb((Object) latLng2, (Object) "null northeast");
        zzaa.zzb(latLng2.latitude >= latLng.latitude, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(latLng.latitude), Double.valueOf(latLng2.latitude));
        this.mVersionCode = i;
        this.southwest = latLng;
        this.northeast = latLng2;
    }

    public LatLngBounds(LatLng latLng, LatLng latLng2) {
        this(1, latLng, latLng2);
    }

    private static double zzb(double d, double d2) {
        return ((d - d2) + 360.0d) % 360.0d;
    }

    private static double zzc(double d, double d2) {
        return ((d2 - d) + 360.0d) % 360.0d;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        return this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast);
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzz.hashCode(this.southwest, this.northeast);
    }

    public String toString() {
        return zzz.zzy(this).zzg("southwest", this.southwest).zzg("northeast", this.northeast).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }
}
