package com.amap.api.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore.util.AMapThrowException;

public final class GroundOverlayOptions implements Parcelable {
    public static final GroundOverlayOptionsCreator CREATOR = new GroundOverlayOptionsCreator();
    public static final float NO_DIMENSION = -1.0f;
    /* renamed from: a */
    private final int f3156a;
    /* renamed from: b */
    private BitmapDescriptor f3157b;
    /* renamed from: c */
    private LatLng f3158c;
    /* renamed from: d */
    private float f3159d;
    /* renamed from: e */
    private float f3160e;
    /* renamed from: f */
    private LatLngBounds f3161f;
    /* renamed from: g */
    private float f3162g;
    /* renamed from: h */
    private float f3163h;
    /* renamed from: i */
    private boolean f3164i;
    /* renamed from: j */
    private float f3165j;
    /* renamed from: k */
    private float f3166k;
    /* renamed from: l */
    private float f3167l;

    GroundOverlayOptions(int i, IBinder iBinder, LatLng latLng, float f, float f2, LatLngBounds latLngBounds, float f3, float f4, boolean z, float f5, float f6, float f7) {
        this.f3163h = 0.0f;
        this.f3164i = true;
        this.f3165j = 0.0f;
        this.f3166k = 0.5f;
        this.f3167l = 0.5f;
        this.f3156a = i;
        this.f3157b = BitmapDescriptorFactory.fromBitmap(null);
        this.f3158c = latLng;
        this.f3159d = f;
        this.f3160e = f2;
        this.f3161f = latLngBounds;
        this.f3162g = f3;
        this.f3163h = f4;
        this.f3164i = z;
        this.f3165j = f5;
        this.f3166k = f6;
        this.f3167l = f7;
    }

    public GroundOverlayOptions() {
        this.f3163h = 0.0f;
        this.f3164i = true;
        this.f3165j = 0.0f;
        this.f3166k = 0.5f;
        this.f3167l = 0.5f;
        this.f3156a = 1;
    }

    public GroundOverlayOptions image(BitmapDescriptor bitmapDescriptor) {
        this.f3157b = bitmapDescriptor;
        return this;
    }

    public GroundOverlayOptions anchor(float f, float f2) {
        this.f3166k = f;
        this.f3167l = f2;
        return this;
    }

    public GroundOverlayOptions position(LatLng latLng, float f) {
        boolean z;
        boolean z2 = true;
        if (this.f3161f == null) {
            z = true;
        } else {
            z = false;
        }
        AMapThrowException.m2226a(z, (Object) "Position has already been set using positionFromBounds");
        if (latLng != null) {
            z = true;
        } else {
            z = false;
        }
        AMapThrowException.m2228b(z, "Location must be specified");
        if (f < 0.0f) {
            z2 = false;
        }
        AMapThrowException.m2228b(z2, "Width must be non-negative");
        return m4458a(latLng, f, f);
    }

    public GroundOverlayOptions position(LatLng latLng, float f, float f2) {
        boolean z;
        boolean z2 = true;
        if (this.f3161f == null) {
            z = true;
        } else {
            z = false;
        }
        AMapThrowException.m2226a(z, (Object) "Position has already been set using positionFromBounds");
        if (latLng != null) {
            z = true;
        } else {
            z = false;
        }
        AMapThrowException.m2228b(z, "Location must be specified");
        if (f >= 0.0f) {
            z = true;
        } else {
            z = false;
        }
        AMapThrowException.m2228b(z, "Width must be non-negative");
        if (f2 < 0.0f) {
            z2 = false;
        }
        AMapThrowException.m2228b(z2, "Height must be non-negative");
        return m4458a(latLng, f, f2);
    }

    /* renamed from: a */
    private GroundOverlayOptions m4458a(LatLng latLng, float f, float f2) {
        this.f3158c = latLng;
        this.f3159d = f;
        this.f3160e = f2;
        return this;
    }

    public GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        AMapThrowException.m2226a(this.f3158c == null, "Position has already been set using position: " + this.f3158c);
        this.f3161f = latLngBounds;
        return this;
    }

    public GroundOverlayOptions bearing(float f) {
        this.f3162g = f;
        return this;
    }

    public GroundOverlayOptions zIndex(float f) {
        this.f3163h = f;
        return this;
    }

    public GroundOverlayOptions visible(boolean z) {
        this.f3164i = z;
        return this;
    }

    public GroundOverlayOptions transparency(float f) {
        boolean z = f >= 0.0f && f <= 1.0f;
        AMapThrowException.m2228b(z, "Transparency must be in the range [0..1]");
        this.f3165j = f;
        return this;
    }

    public BitmapDescriptor getImage() {
        return this.f3157b;
    }

    public LatLng getLocation() {
        return this.f3158c;
    }

    public float getWidth() {
        return this.f3159d;
    }

    public float getHeight() {
        return this.f3160e;
    }

    public LatLngBounds getBounds() {
        return this.f3161f;
    }

    public float getBearing() {
        return this.f3162g;
    }

    public float getZIndex() {
        return this.f3163h;
    }

    public float getTransparency() {
        return this.f3165j;
    }

    public float getAnchorU() {
        return this.f3166k;
    }

    public float getAnchorV() {
        return this.f3167l;
    }

    public boolean isVisible() {
        return this.f3164i;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3156a);
        parcel.writeParcelable(this.f3157b, i);
        parcel.writeParcelable(this.f3158c, i);
        parcel.writeFloat(this.f3159d);
        parcel.writeFloat(this.f3160e);
        parcel.writeParcelable(this.f3161f, i);
        parcel.writeFloat(this.f3162g);
        parcel.writeFloat(this.f3163h);
        parcel.writeByte((byte) (this.f3164i ? 1 : 0));
        parcel.writeFloat(this.f3165j);
        parcel.writeFloat(this.f3166k);
        parcel.writeFloat(this.f3167l);
    }
}
