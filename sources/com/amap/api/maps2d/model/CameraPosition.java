package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore2d.C0953ch;
import com.amap.api.mapcore2d.C0954cj;
import com.amap.api.mapcore2d.C0955ck;

public final class CameraPosition implements Parcelable {
    public static final CameraPositionCreator CREATOR = new CameraPositionCreator();
    public final float bearing;
    public final boolean isAbroad;
    public final LatLng target;
    public final float tilt;
    public final float zoom;

    public static final class Builder {
        /* renamed from: a */
        private LatLng f3380a;
        /* renamed from: b */
        private float f3381b;
        /* renamed from: c */
        private float f3382c;
        /* renamed from: d */
        private float f3383d;

        public Builder(CameraPosition cameraPosition) {
            target(cameraPosition.target).bearing(cameraPosition.bearing).tilt(cameraPosition.tilt).zoom(cameraPosition.zoom);
        }

        public Builder target(LatLng latLng) {
            this.f3380a = latLng;
            return this;
        }

        public Builder zoom(float f) {
            this.f3381b = f;
            return this;
        }

        public Builder tilt(float f) {
            this.f3382c = f;
            return this;
        }

        public Builder bearing(float f) {
            this.f3383d = f;
            return this;
        }

        public CameraPosition build() {
            String str = "build";
            try {
                C0953ch.m3870a(this.f3380a);
                return new CameraPosition(this.f3380a, this.f3381b, this.f3382c, this.f3383d);
            } catch (Exception e) {
                C0955ck.m3888a(e, "CameraPosition", str);
                return null;
            }
        }
    }

    public CameraPosition(LatLng latLng, float f, float f2, float f3) {
        C0953ch.m3871a((Object) latLng, (Object) "CameraPosition 位置不能为null");
        this.target = latLng;
        this.zoom = C0955ck.m3895b(f);
        this.tilt = C0955ck.m3879a(f2);
        if (((double) f3) <= 0.0d) {
            f3 = (f3 % 360.0f) + 360.0f;
        }
        this.bearing = f3 % 360.0f;
        this.isAbroad = !C0954cj.m3876a(latLng.latitude, latLng.longitude);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.bearing);
        parcel.writeFloat((float) this.target.latitude);
        parcel.writeFloat((float) this.target.longitude);
        parcel.writeFloat(this.tilt);
        parcel.writeFloat(this.zoom);
    }

    public int describeContents() {
        return 0;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public static final CameraPosition fromLatLngZoom(LatLng latLng, float f) {
        return new CameraPosition(latLng, f, 0.0f, 0.0f);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(CameraPosition cameraPosition) {
        return new Builder(cameraPosition);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CameraPosition)) {
            return false;
        }
        CameraPosition cameraPosition = (CameraPosition) obj;
        if (this.target.equals(cameraPosition.target) && Float.floatToIntBits(this.zoom) == Float.floatToIntBits(cameraPosition.zoom) && Float.floatToIntBits(this.tilt) == Float.floatToIntBits(cameraPosition.tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(cameraPosition.bearing)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return C0955ck.m3887a(C0955ck.m3886a("target", this.target), C0955ck.m3886a("zoom", Float.valueOf(this.zoom)), C0955ck.m3886a("tilt", Float.valueOf(this.tilt)), C0955ck.m3886a("bearing", Float.valueOf(this.bearing)));
    }
}
