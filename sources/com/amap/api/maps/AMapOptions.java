package com.amap.api.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.maps.model.CameraPosition;

public class AMapOptions implements Parcelable {
    public static final AMapOptionsCreator CREATOR = new AMapOptionsCreator();
    public static final int LOGO_POSITION_BOTTOM_CENTER = 1;
    public static final int LOGO_POSITION_BOTTOM_LEFT = 0;
    public static final int LOGO_POSITION_BOTTOM_RIGHT = 2;
    public static final int ZOOM_POSITION_RIGHT_BUTTOM = 2;
    public static final int ZOOM_POSITION_RIGHT_CENTER = 1;
    /* renamed from: a */
    private int f3086a = 1;
    /* renamed from: b */
    private boolean f3087b = true;
    /* renamed from: c */
    private boolean f3088c = true;
    /* renamed from: d */
    private boolean f3089d = true;
    /* renamed from: e */
    private boolean f3090e = true;
    /* renamed from: f */
    private boolean f3091f = true;
    /* renamed from: g */
    private boolean f3092g = false;
    /* renamed from: h */
    private CameraPosition f3093h;
    /* renamed from: i */
    private boolean f3094i = false;
    /* renamed from: j */
    private boolean f3095j = false;
    /* renamed from: k */
    private int f3096k = 0;

    public AMapOptions logoPosition(int i) {
        this.f3096k = i;
        return this;
    }

    public AMapOptions zOrderOnTop(boolean z) {
        this.f3092g = z;
        return this;
    }

    public AMapOptions mapType(int i) {
        this.f3086a = i;
        return this;
    }

    public AMapOptions camera(CameraPosition cameraPosition) {
        this.f3093h = cameraPosition;
        return this;
    }

    public AMapOptions scaleControlsEnabled(boolean z) {
        this.f3095j = z;
        return this;
    }

    public AMapOptions zoomControlsEnabled(boolean z) {
        this.f3091f = z;
        return this;
    }

    public AMapOptions compassEnabled(boolean z) {
        this.f3094i = z;
        return this;
    }

    public AMapOptions scrollGesturesEnabled(boolean z) {
        this.f3088c = z;
        return this;
    }

    public AMapOptions zoomGesturesEnabled(boolean z) {
        this.f3090e = z;
        return this;
    }

    public AMapOptions tiltGesturesEnabled(boolean z) {
        this.f3089d = z;
        return this;
    }

    public AMapOptions rotateGesturesEnabled(boolean z) {
        this.f3087b = z;
        return this;
    }

    public int getLogoPosition() {
        return this.f3096k;
    }

    public Boolean getZOrderOnTop() {
        return Boolean.valueOf(this.f3092g);
    }

    public int getMapType() {
        return this.f3086a;
    }

    public CameraPosition getCamera() {
        return this.f3093h;
    }

    public Boolean getScaleControlsEnabled() {
        return Boolean.valueOf(this.f3095j);
    }

    public Boolean getZoomControlsEnabled() {
        return Boolean.valueOf(this.f3091f);
    }

    public Boolean getCompassEnabled() {
        return Boolean.valueOf(this.f3094i);
    }

    public Boolean getScrollGesturesEnabled() {
        return Boolean.valueOf(this.f3088c);
    }

    public Boolean getZoomGesturesEnabled() {
        return Boolean.valueOf(this.f3090e);
    }

    public Boolean getTiltGesturesEnabled() {
        return Boolean.valueOf(this.f3089d);
    }

    public Boolean getRotateGesturesEnabled() {
        return Boolean.valueOf(this.f3087b);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3093h, i);
        parcel.writeInt(this.f3086a);
        parcel.writeBooleanArray(new boolean[]{this.f3087b, this.f3088c, this.f3089d, this.f3090e, this.f3091f, this.f3092g, this.f3094i, this.f3095j});
    }
}
