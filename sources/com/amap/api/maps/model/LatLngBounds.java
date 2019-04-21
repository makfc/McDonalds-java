package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore.util.AMapThrowException;
import com.amap.api.mapcore.util.Util;

public final class LatLngBounds implements Parcelable {
    public static final LatLngBoundsCreator CREATOR = new LatLngBoundsCreator();
    /* renamed from: a */
    private final int f3188a;
    public final LatLng northeast;
    public final LatLng southwest;

    public static final class Builder {
        /* renamed from: a */
        private double f3184a = Double.POSITIVE_INFINITY;
        /* renamed from: b */
        private double f3185b = Double.NEGATIVE_INFINITY;
        /* renamed from: c */
        private double f3186c = Double.NaN;
        /* renamed from: d */
        private double f3187d = Double.NaN;

        public Builder include(LatLng latLng) {
            this.f3184a = Math.min(this.f3184a, latLng.latitude);
            this.f3185b = Math.max(this.f3185b, latLng.latitude);
            double d = latLng.longitude;
            if (Double.isNaN(this.f3186c)) {
                this.f3186c = d;
                this.f3187d = d;
            } else if (!m4475a(d)) {
                if (LatLngBounds.m4481c(this.f3186c, d) < LatLngBounds.m4482d(this.f3187d, d)) {
                    this.f3186c = d;
                } else {
                    this.f3187d = d;
                }
            }
            return this;
        }

        /* renamed from: a */
        private boolean m4475a(double d) {
            boolean z = false;
            if (this.f3186c > this.f3187d) {
                if (this.f3186c <= d || d <= this.f3187d) {
                    z = true;
                }
                return z;
            } else if (this.f3186c > d || d > this.f3187d) {
                return false;
            } else {
                return true;
            }
        }

        public LatLngBounds build() {
            boolean z;
            if (Double.isNaN(this.f3186c)) {
                z = false;
            } else {
                z = true;
            }
            AMapThrowException.m2226a(z, (Object) "no included points");
            return new LatLngBounds(new LatLng(this.f3184a, this.f3186c, false), new LatLng(this.f3185b, this.f3187d, false));
        }
    }

    LatLngBounds(int i, LatLng latLng, LatLng latLng2) {
        AMapThrowException.m2225a((Object) latLng, (Object) "null southwest");
        AMapThrowException.m2225a((Object) latLng2, (Object) "null northeast");
        AMapThrowException.m2227a(latLng2.latitude >= latLng.latitude, "southern latitude exceeds northern latitude (%s > %s)", new Object[]{Double.valueOf(latLng.latitude), Double.valueOf(latLng2.latitude)});
        this.f3188a = i;
        this.southwest = latLng;
        this.northeast = latLng2;
    }

    public LatLngBounds(LatLng latLng, LatLng latLng2) {
        this(1, latLng, latLng2);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public int mo10716a() {
        return this.f3188a;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean contains(LatLng latLng) {
        return m4477a(latLng.latitude) && m4480b(latLng.longitude);
    }

    public boolean contains(LatLngBounds latLngBounds) {
        if (latLngBounds != null && contains(latLngBounds.southwest) && contains(latLngBounds.northeast)) {
            return true;
        }
        return false;
    }

    public boolean intersects(LatLngBounds latLngBounds) {
        if (latLngBounds == null) {
            return false;
        }
        if (m4478a(latLngBounds) || latLngBounds.m4478a(this)) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private boolean m4478a(LatLngBounds latLngBounds) {
        if (latLngBounds == null || latLngBounds.northeast == null || latLngBounds.southwest == null || this.northeast == null || this.southwest == null) {
            return false;
        }
        double d = ((latLngBounds.northeast.latitude + latLngBounds.southwest.latitude) - this.northeast.latitude) - this.southwest.latitude;
        double d2 = ((this.northeast.latitude - this.southwest.latitude) + latLngBounds.northeast.latitude) - latLngBounds.southwest.latitude;
        if (Math.abs(((latLngBounds.northeast.longitude + latLngBounds.southwest.longitude) - this.northeast.longitude) - this.southwest.longitude) >= ((this.northeast.longitude - this.southwest.longitude) + latLngBounds.northeast.longitude) - this.southwest.longitude || Math.abs(d) >= d2) {
            return false;
        }
        return true;
    }

    public LatLngBounds including(LatLng latLng) {
        double d;
        double min = Math.min(this.southwest.latitude, latLng.latitude);
        double max = Math.max(this.northeast.latitude, latLng.latitude);
        double d2 = this.northeast.longitude;
        double d3 = this.southwest.longitude;
        double d4 = latLng.longitude;
        if (m4480b(d4)) {
            d4 = d3;
            d = d2;
        } else if (m4481c(d3, d4) < m4482d(d2, d4)) {
            d = d2;
        } else {
            d = d4;
            d4 = d3;
        }
        return new LatLngBounds(new LatLng(min, d4, false), new LatLng(max, d, false));
    }

    /* renamed from: c */
    private static double m4481c(double d, double d2) {
        return ((d - d2) + 360.0d) % 360.0d;
    }

    /* renamed from: d */
    private static double m4482d(double d, double d2) {
        return ((d2 - d) + 360.0d) % 360.0d;
    }

    /* renamed from: a */
    private boolean m4477a(double d) {
        return this.southwest.latitude <= d && d <= this.northeast.latitude;
    }

    /* renamed from: b */
    private boolean m4480b(double d) {
        boolean z = false;
        if (this.southwest.longitude > this.northeast.longitude) {
            if (this.southwest.longitude <= d || d <= this.northeast.longitude) {
                z = true;
            }
            return z;
        } else if (this.southwest.longitude > d || d > this.northeast.longitude) {
            return false;
        } else {
            return true;
        }
    }

    public int hashCode() {
        return Util.m2343a(new Object[]{this.southwest, this.northeast});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        if (this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return Util.m2354a(Util.m2353a("southwest", this.southwest), Util.m2353a("northeast", this.northeast));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        LatLngBoundsCreator.m4484a(this, parcel, i);
    }
}
