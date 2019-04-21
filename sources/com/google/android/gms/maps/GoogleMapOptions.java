package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.util.AttributeSet;
import com.google.android.gms.C2063R;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.CameraPosition;

public final class GoogleMapOptions implements SafeParcelable {
    public static final zza CREATOR = new zza();
    private final int mVersionCode;
    private Boolean zzaZf;
    private Boolean zzaZg;
    private int zzaZh;
    private CameraPosition zzaZi;
    private Boolean zzaZj;
    private Boolean zzaZk;
    private Boolean zzaZl;
    private Boolean zzaZm;
    private Boolean zzaZn;
    private Boolean zzaZo;
    private Boolean zzaZp;
    private Boolean zzaZq;
    private Boolean zzaZr;

    public GoogleMapOptions() {
        this.zzaZh = -1;
        this.mVersionCode = 1;
    }

    GoogleMapOptions(int i, byte b, byte b2, int i2, CameraPosition cameraPosition, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, byte b9, byte b10, byte b11) {
        this.zzaZh = -1;
        this.mVersionCode = i;
        this.zzaZf = zza.zza(b);
        this.zzaZg = zza.zza(b2);
        this.zzaZh = i2;
        this.zzaZi = cameraPosition;
        this.zzaZj = zza.zza(b3);
        this.zzaZk = zza.zza(b4);
        this.zzaZl = zza.zza(b5);
        this.zzaZm = zza.zza(b6);
        this.zzaZn = zza.zza(b7);
        this.zzaZo = zza.zza(b8);
        this.zzaZp = zza.zza(b9);
        this.zzaZq = zza.zza(b10);
        this.zzaZr = zza.zza(b11);
    }

    public static GoogleMapOptions createFromAttributes(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, C2063R.styleable.MapAttrs);
        GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        if (obtainAttributes.hasValue(C2063R.styleable.MapAttrs_mapType)) {
            googleMapOptions.mapType(obtainAttributes.getInt(C2063R.styleable.MapAttrs_mapType, -1));
        }
        if (obtainAttributes.hasValue(C2063R.styleable.MapAttrs_zOrderOnTop)) {
            googleMapOptions.zOrderOnTop(obtainAttributes.getBoolean(C2063R.styleable.MapAttrs_zOrderOnTop, false));
        }
        if (obtainAttributes.hasValue(C2063R.styleable.MapAttrs_useViewLifecycle)) {
            googleMapOptions.useViewLifecycleInFragment(obtainAttributes.getBoolean(C2063R.styleable.MapAttrs_useViewLifecycle, false));
        }
        if (obtainAttributes.hasValue(C2063R.styleable.MapAttrs_uiCompass)) {
            googleMapOptions.compassEnabled(obtainAttributes.getBoolean(C2063R.styleable.MapAttrs_uiCompass, true));
        }
        if (obtainAttributes.hasValue(C2063R.styleable.MapAttrs_uiRotateGestures)) {
            googleMapOptions.rotateGesturesEnabled(obtainAttributes.getBoolean(C2063R.styleable.MapAttrs_uiRotateGestures, true));
        }
        if (obtainAttributes.hasValue(C2063R.styleable.MapAttrs_uiScrollGestures)) {
            googleMapOptions.scrollGesturesEnabled(obtainAttributes.getBoolean(C2063R.styleable.MapAttrs_uiScrollGestures, true));
        }
        if (obtainAttributes.hasValue(C2063R.styleable.MapAttrs_uiTiltGestures)) {
            googleMapOptions.tiltGesturesEnabled(obtainAttributes.getBoolean(C2063R.styleable.MapAttrs_uiTiltGestures, true));
        }
        if (obtainAttributes.hasValue(C2063R.styleable.MapAttrs_uiZoomGestures)) {
            googleMapOptions.zoomGesturesEnabled(obtainAttributes.getBoolean(C2063R.styleable.MapAttrs_uiZoomGestures, true));
        }
        if (obtainAttributes.hasValue(C2063R.styleable.MapAttrs_uiZoomControls)) {
            googleMapOptions.zoomControlsEnabled(obtainAttributes.getBoolean(C2063R.styleable.MapAttrs_uiZoomControls, true));
        }
        if (obtainAttributes.hasValue(C2063R.styleable.MapAttrs_liteMode)) {
            googleMapOptions.liteMode(obtainAttributes.getBoolean(C2063R.styleable.MapAttrs_liteMode, false));
        }
        if (obtainAttributes.hasValue(C2063R.styleable.MapAttrs_uiMapToolbar)) {
            googleMapOptions.mapToolbarEnabled(obtainAttributes.getBoolean(C2063R.styleable.MapAttrs_uiMapToolbar, true));
        }
        if (obtainAttributes.hasValue(C2063R.styleable.MapAttrs_ambientEnabled)) {
            googleMapOptions.ambientEnabled(obtainAttributes.getBoolean(C2063R.styleable.MapAttrs_ambientEnabled, false));
        }
        googleMapOptions.camera(CameraPosition.createFromAttributes(context, attributeSet));
        obtainAttributes.recycle();
        return googleMapOptions;
    }

    public GoogleMapOptions ambientEnabled(boolean z) {
        this.zzaZr = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions camera(CameraPosition cameraPosition) {
        this.zzaZi = cameraPosition;
        return this;
    }

    public GoogleMapOptions compassEnabled(boolean z) {
        this.zzaZk = Boolean.valueOf(z);
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public CameraPosition getCamera() {
        return this.zzaZi;
    }

    public int getMapType() {
        return this.zzaZh;
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public GoogleMapOptions liteMode(boolean z) {
        this.zzaZp = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions mapToolbarEnabled(boolean z) {
        this.zzaZq = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions mapType(int i) {
        this.zzaZh = i;
        return this;
    }

    public GoogleMapOptions rotateGesturesEnabled(boolean z) {
        this.zzaZo = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions scrollGesturesEnabled(boolean z) {
        this.zzaZl = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions tiltGesturesEnabled(boolean z) {
        this.zzaZn = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions useViewLifecycleInFragment(boolean z) {
        this.zzaZg = Boolean.valueOf(z);
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    public GoogleMapOptions zOrderOnTop(boolean z) {
        this.zzaZf = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions zoomControlsEnabled(boolean z) {
        this.zzaZj = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions zoomGesturesEnabled(boolean z) {
        this.zzaZm = Boolean.valueOf(z);
        return this;
    }

    /* Access modifiers changed, original: 0000 */
    public byte zzDA() {
        return zza.zze(this.zzaZo);
    }

    /* Access modifiers changed, original: 0000 */
    public byte zzDB() {
        return zza.zze(this.zzaZp);
    }

    /* Access modifiers changed, original: 0000 */
    public byte zzDC() {
        return zza.zze(this.zzaZq);
    }

    /* Access modifiers changed, original: 0000 */
    public byte zzDD() {
        return zza.zze(this.zzaZr);
    }

    /* Access modifiers changed, original: 0000 */
    public byte zzDt() {
        return zza.zze(this.zzaZf);
    }

    /* Access modifiers changed, original: 0000 */
    public byte zzDu() {
        return zza.zze(this.zzaZg);
    }

    /* Access modifiers changed, original: 0000 */
    public byte zzDv() {
        return zza.zze(this.zzaZj);
    }

    /* Access modifiers changed, original: 0000 */
    public byte zzDw() {
        return zza.zze(this.zzaZk);
    }

    /* Access modifiers changed, original: 0000 */
    public byte zzDx() {
        return zza.zze(this.zzaZl);
    }

    /* Access modifiers changed, original: 0000 */
    public byte zzDy() {
        return zza.zze(this.zzaZm);
    }

    /* Access modifiers changed, original: 0000 */
    public byte zzDz() {
        return zza.zze(this.zzaZn);
    }
}
