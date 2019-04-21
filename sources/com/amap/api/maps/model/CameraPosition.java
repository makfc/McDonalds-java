package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore.util.AMapThrowException;
import com.amap.api.mapcore.util.RegionUtil;
import com.amap.api.mapcore.util.Util;

public final class CameraPosition implements Parcelable {
    public static final CameraPositionCreator CREATOR = new CameraPositionCreator();
    public final float bearing;
    public final boolean isAbroad;
    public final LatLng target;
    public final float tilt;
    public final float zoom;

    public static final class Builder {
        /* renamed from: a */
        private LatLng f3134a;
        /* renamed from: b */
        private float f3135b;
        /* renamed from: c */
        private float f3136c;
        /* renamed from: d */
        private float f3137d;

        public Builder(CameraPosition cameraPosition) {
            target(cameraPosition.target).bearing(cameraPosition.bearing).tilt(cameraPosition.tilt).zoom(cameraPosition.zoom);
        }

        public Builder target(LatLng latLng) {
            this.f3134a = latLng;
            return this;
        }

        public Builder zoom(float f) {
            this.f3135b = f;
            return this;
        }

        public Builder tilt(float f) {
            this.f3136c = f;
            return this;
        }

        public Builder bearing(float f) {
            this.f3137d = f;
            return this;
        }

        public CameraPosition build() {
            AMapThrowException.m2224a(this.f3134a);
            return new CameraPosition(this.f3134a, this.f3135b, this.f3136c, this.f3137d);
        }
    }

    public CameraPosition(LatLng latLng, float f, float f2, float f3) {
        AMapThrowException.m2225a((Object) latLng, (Object) "CameraPosition 位置不能为null ");
        this.target = latLng;
        this.zoom = Util.m2337a(f);
        this.tilt = Util.m2338a(f2, this.zoom);
        if (((double) f3) <= 0.0d) {
            f3 = (f3 % 360.0f) + 360.0f;
        }
        this.bearing = f3 % 360.0f;
        this.isAbroad = !RegionUtil.m2325a(latLng.latitude, latLng.longitude);
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
        return Util.m2354a(Util.m2353a("target", this.target), Util.m2353a("zoom", Float.valueOf(this.zoom)), Util.m2353a("tilt", Float.valueOf(this.tilt)), Util.m2353a("bearing", Float.valueOf(this.bearing)));
    }
}
