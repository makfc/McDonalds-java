package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore2d.C0953ch;
import com.amap.api.mapcore2d.C0955ck;

public final class LatLngBounds implements Parcelable {
    public static final LatLngBoundsCreator CREATOR = new LatLngBoundsCreator();
    /* renamed from: a */
    private final int f3411a;
    public final LatLng northeast;
    public final LatLng southwest;

    public static final class Builder {
        /* renamed from: a */
        private double f3407a = Double.POSITIVE_INFINITY;
        /* renamed from: b */
        private double f3408b = Double.NEGATIVE_INFINITY;
        /* renamed from: c */
        private double f3409c = Double.NaN;
        /* renamed from: d */
        private double f3410d = Double.NaN;

        public Builder include(LatLng latLng) {
            this.f3407a = Math.min(this.f3407a, latLng.latitude);
            this.f3408b = Math.max(this.f3408b, latLng.latitude);
            double d = latLng.longitude;
            if (Double.isNaN(this.f3409c)) {
                this.f3409c = d;
                this.f3410d = d;
            } else if (!m4571a(d)) {
                if (LatLngBounds.m4577c(this.f3409c, d) < LatLngBounds.m4578d(this.f3410d, d)) {
                    this.f3409c = d;
                } else {
                    this.f3410d = d;
                }
            }
            return this;
        }

        /* renamed from: a */
        private boolean m4571a(double d) {
            boolean z = false;
            if (this.f3409c > this.f3410d) {
                if (this.f3409c <= d || d <= this.f3410d) {
                    z = true;
                }
                return z;
            } else if (this.f3409c > d || d > this.f3410d) {
                return false;
            } else {
                return true;
            }
        }

        public LatLngBounds build() {
            String str = "build";
            try {
                C0953ch.m3872a(!Double.isNaN(this.f3409c), (Object) "no included points");
                return new LatLngBounds(new LatLng(this.f3407a, this.f3409c), new LatLng(this.f3408b, this.f3410d));
            } catch (Exception e) {
                C0955ck.m3888a(e, "LatLngBounds", str);
                return null;
            }
        }
    }

    LatLngBounds(int i, LatLng latLng, LatLng latLng2) {
        boolean z = true;
        String str = "LatLngBounds";
        try {
            C0953ch.m3871a((Object) latLng, (Object) "null southwest");
            C0953ch.m3871a((Object) latLng2, (Object) "null northeast");
            if (latLng2.latitude < latLng.latitude) {
                z = false;
            }
            C0953ch.m3873a(z, "southern latitude exceeds northern latitude (%s > %s)", new Object[]{Double.valueOf(latLng.latitude), Double.valueOf(latLng2.latitude)});
        } catch (Exception e) {
            C0955ck.m3888a(e, "LatLngBounds", str);
        }
        this.f3411a = i;
        this.southwest = latLng;
        this.northeast = latLng2;
    }

    public LatLngBounds(LatLng latLng, LatLng latLng2) {
        this(1, latLng, latLng2);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public int mo11432a() {
        return this.f3411a;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean contains(LatLng latLng) {
        return m4573a(latLng.latitude) && m4576b(latLng.longitude);
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
        if (m4574a(latLngBounds) || latLngBounds.m4574a(this)) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private boolean m4574a(LatLngBounds latLngBounds) {
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
        double min = Math.min(this.southwest.latitude, latLng.latitude);
        double max = Math.max(this.northeast.latitude, latLng.latitude);
        double d = this.northeast.longitude;
        double d2 = this.southwest.longitude;
        double d3 = latLng.longitude;
        if (m4576b(d3) || m4577c(d2, d3) < m4578d(d, d3)) {
        }
        return new LatLngBounds(new LatLng(min, d3), new LatLng(max, d3));
    }

    /* renamed from: c */
    private static double m4577c(double d, double d2) {
        return ((d - d2) + 360.0d) % 360.0d;
    }

    /* renamed from: d */
    private static double m4578d(double d, double d2) {
        return ((d2 - d) + 360.0d) % 360.0d;
    }

    /* renamed from: a */
    private boolean m4573a(double d) {
        return this.southwest.latitude <= d && d <= this.northeast.latitude;
    }

    /* renamed from: b */
    private boolean m4576b(double d) {
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
        return C0955ck.m3880a(new Object[]{this.southwest, this.northeast});
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
        return C0955ck.m3887a(C0955ck.m3886a("southwest", this.southwest), C0955ck.m3886a("northeast", this.northeast));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        LatLngBoundsCreator.m4592a(this, parcel, i);
    }
}
