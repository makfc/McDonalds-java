package com.amap.api.maps2d.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore2d.C0953ch;
import com.amap.api.mapcore2d.C0955ck;

public final class GroundOverlayOptions implements Parcelable {
    public static final GroundOverlayOptionsCreator CREATOR = new GroundOverlayOptionsCreator();
    public static final float NO_DIMENSION = -1.0f;
    /* renamed from: a */
    private final int f3394a;
    /* renamed from: b */
    private BitmapDescriptor f3395b;
    /* renamed from: c */
    private LatLng f3396c;
    /* renamed from: d */
    private float f3397d;
    /* renamed from: e */
    private float f3398e;
    /* renamed from: f */
    private LatLngBounds f3399f;
    /* renamed from: g */
    private float f3400g;
    /* renamed from: h */
    private float f3401h;
    /* renamed from: i */
    private boolean f3402i;
    /* renamed from: j */
    private float f3403j;
    /* renamed from: k */
    private float f3404k;
    /* renamed from: l */
    private float f3405l;

    GroundOverlayOptions(int i, IBinder iBinder, LatLng latLng, float f, float f2, LatLngBounds latLngBounds, float f3, float f4, boolean z, float f5, float f6, float f7) {
        this.f3402i = true;
        this.f3403j = 0.0f;
        this.f3404k = 0.5f;
        this.f3405l = 0.5f;
        this.f3394a = i;
        this.f3395b = BitmapDescriptorFactory.fromBitmap(null);
        this.f3396c = latLng;
        this.f3397d = f;
        this.f3398e = f2;
        this.f3399f = latLngBounds;
        this.f3400g = f3;
        this.f3401h = f4;
        this.f3402i = z;
        this.f3403j = f5;
        this.f3404k = f6;
        this.f3405l = f7;
    }

    public GroundOverlayOptions() {
        this.f3402i = true;
        this.f3403j = 0.0f;
        this.f3404k = 0.5f;
        this.f3405l = 0.5f;
        this.f3394a = 1;
    }

    public GroundOverlayOptions image(BitmapDescriptor bitmapDescriptor) {
        this.f3395b = bitmapDescriptor;
        return this;
    }

    public GroundOverlayOptions anchor(float f, float f2) {
        this.f3404k = f;
        this.f3405l = f2;
        return this;
    }

    public GroundOverlayOptions position(LatLng latLng, float f) {
        boolean z = true;
        String str = "position";
        try {
            boolean z2;
            C0953ch.m3872a(this.f3399f == null, (Object) "Position has already been set using positionFromBounds");
            if (latLng != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            C0953ch.m3874b(z2, "Location must be specified");
            if (f < 0.0f) {
                z = false;
            }
            C0953ch.m3874b(z, "Width must be non-negative");
            return m4569a(latLng, f, f);
        } catch (Exception e) {
            C0955ck.m3888a(e, "GroundOverlayOptions", str);
            return null;
        }
    }

    public GroundOverlayOptions position(LatLng latLng, float f, float f2) {
        boolean z = true;
        String str = "position";
        try {
            boolean z2;
            C0953ch.m3872a(this.f3399f == null, (Object) "Position has already been set using positionFromBounds");
            if (latLng != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            C0953ch.m3874b(z2, "Location must be specified");
            if (f >= 0.0f) {
                z2 = true;
            } else {
                z2 = false;
            }
            C0953ch.m3874b(z2, "Width must be non-negative");
            if (f2 < 0.0f) {
                z = false;
            }
            C0953ch.m3874b(z, "Height must be non-negative");
            return m4569a(latLng, f, f2);
        } catch (Exception e) {
            C0955ck.m3888a(e, "GroundOverlayOptions", str);
            return null;
        }
    }

    /* renamed from: a */
    private GroundOverlayOptions m4569a(LatLng latLng, float f, float f2) {
        this.f3396c = latLng;
        this.f3397d = f;
        this.f3398e = f2;
        return this;
    }

    public GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        String str = "positionFromBounds";
        try {
            C0953ch.m3872a(this.f3396c == null, "Position has already been set using position: " + this.f3396c);
            this.f3399f = latLngBounds;
            return this;
        } catch (Exception e) {
            C0955ck.m3888a(e, "GroundOverlayOptions", str);
            return null;
        }
    }

    public GroundOverlayOptions bearing(float f) {
        this.f3400g = f;
        return this;
    }

    public GroundOverlayOptions zIndex(float f) {
        this.f3401h = f;
        return this;
    }

    public GroundOverlayOptions visible(boolean z) {
        this.f3402i = z;
        return this;
    }

    public GroundOverlayOptions transparency(float f) {
        String str = "transparency";
        boolean z = f >= 0.0f && f <= 1.0f;
        try {
            C0953ch.m3874b(z, "Transparency must be in the range [0..1]");
            this.f3403j = f;
            return this;
        } catch (Exception e) {
            C0955ck.m3888a(e, "GroundOverlayOptions", str);
            return null;
        }
    }

    public BitmapDescriptor getImage() {
        return this.f3395b;
    }

    public LatLng getLocation() {
        return this.f3396c;
    }

    public float getWidth() {
        return this.f3397d;
    }

    public float getHeight() {
        return this.f3398e;
    }

    public LatLngBounds getBounds() {
        return this.f3399f;
    }

    public float getBearing() {
        return this.f3400g;
    }

    public float getZIndex() {
        return this.f3401h;
    }

    public float getTransparency() {
        return this.f3403j;
    }

    public float getAnchorU() {
        return this.f3404k;
    }

    public float getAnchorV() {
        return this.f3405l;
    }

    public boolean isVisible() {
        return this.f3402i;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3394a);
        parcel.writeParcelable(this.f3395b, i);
        parcel.writeParcelable(this.f3396c, i);
        parcel.writeFloat(this.f3397d);
        parcel.writeFloat(this.f3398e);
        parcel.writeParcelable(this.f3399f, i);
        parcel.writeFloat(this.f3400g);
        parcel.writeFloat(this.f3401h);
        parcel.writeByte((byte) (this.f3402i ? 1 : 0));
        parcel.writeFloat(this.f3403j);
        parcel.writeFloat(this.f3404k);
        parcel.writeFloat(this.f3405l);
    }
}
