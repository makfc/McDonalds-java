package com.amap.api.maps2d;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.maps2d.model.CameraPosition;

public class AMapOptions implements Parcelable {
    public static final AMapOptionsCreator CREATOR = new AMapOptionsCreator();
    public static final int LOGO_POSITION_BOTTOM_CENTER = 1;
    public static final int LOGO_POSITION_BOTTOM_LEFT = 0;
    public static final int LOGO_POSITION_BOTTOM_RIGHT = 2;
    public static final int ZOOM_POSITION_RIGHT_BUTTOM = 0;
    public static final int ZOOM_POSITION_RIGHT_CENTER = 1;
    /* renamed from: a */
    private int f3351a = 1;
    /* renamed from: b */
    private boolean f3352b = true;
    /* renamed from: c */
    private boolean f3353c = true;
    /* renamed from: d */
    private boolean f3354d = true;
    /* renamed from: e */
    private boolean f3355e = false;
    /* renamed from: f */
    private CameraPosition f3356f;
    /* renamed from: g */
    private boolean f3357g = false;
    /* renamed from: h */
    private boolean f3358h = false;
    /* renamed from: i */
    private int f3359i = 0;

    public AMapOptions logoPosition(int i) {
        this.f3359i = i;
        return this;
    }

    public AMapOptions zOrderOnTop(boolean z) {
        this.f3355e = z;
        return this;
    }

    public AMapOptions mapType(int i) {
        this.f3351a = i;
        return this;
    }

    public AMapOptions camera(CameraPosition cameraPosition) {
        this.f3356f = cameraPosition;
        return this;
    }

    public AMapOptions scaleControlsEnabled(boolean z) {
        this.f3358h = z;
        return this;
    }

    public AMapOptions zoomControlsEnabled(boolean z) {
        this.f3354d = z;
        return this;
    }

    public AMapOptions compassEnabled(boolean z) {
        this.f3357g = z;
        return this;
    }

    public AMapOptions scrollGesturesEnabled(boolean z) {
        this.f3352b = z;
        return this;
    }

    public AMapOptions zoomGesturesEnabled(boolean z) {
        this.f3353c = z;
        return this;
    }

    public int getLogoPosition() {
        return this.f3359i;
    }

    public Boolean getZOrderOnTop() {
        return Boolean.valueOf(this.f3355e);
    }

    public int getMapType() {
        return this.f3351a;
    }

    public CameraPosition getCamera() {
        return this.f3356f;
    }

    public Boolean getScaleControlsEnabled() {
        return Boolean.valueOf(this.f3358h);
    }

    public Boolean getZoomControlsEnabled() {
        return Boolean.valueOf(this.f3354d);
    }

    public Boolean getCompassEnabled() {
        return Boolean.valueOf(this.f3357g);
    }

    public Boolean getScrollGesturesEnabled() {
        return Boolean.valueOf(this.f3352b);
    }

    public Boolean getZoomGesturesEnabled() {
        return Boolean.valueOf(this.f3353c);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3356f, i);
        parcel.writeInt(this.f3351a);
        parcel.writeBooleanArray(new boolean[]{this.f3352b, this.f3353c, this.f3354d, this.f3355e, this.f3357g, this.f3358h});
    }
}
