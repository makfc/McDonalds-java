package com.amap.api.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public final class TileOverlayOptions implements Parcelable {
    public static final TileOverlayOptionsCreator CREATOR = new TileOverlayOptionsCreator();
    /* renamed from: a */
    private final int f3276a;
    /* renamed from: b */
    private TileProvider f3277b;
    /* renamed from: c */
    private boolean f3278c;
    /* renamed from: d */
    private float f3279d;
    /* renamed from: e */
    private int f3280e;
    /* renamed from: f */
    private int f3281f;
    /* renamed from: g */
    private String f3282g;
    /* renamed from: h */
    private boolean f3283h;
    /* renamed from: i */
    private boolean f3284i;

    public TileOverlayOptions() {
        this.f3278c = true;
        this.f3280e = 5242880;
        this.f3281f = 20971520;
        this.f3282g = null;
        this.f3283h = true;
        this.f3284i = true;
        this.f3276a = 1;
    }

    TileOverlayOptions(int i, IBinder iBinder, boolean z, float f) {
        this.f3278c = true;
        this.f3280e = 5242880;
        this.f3281f = 20971520;
        this.f3282g = null;
        this.f3283h = true;
        this.f3284i = true;
        this.f3276a = i;
        this.f3278c = z;
        this.f3279d = f;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeInt(this.f3276a);
        parcel.writeValue(this.f3277b);
        parcel.writeByte((byte) (this.f3278c ? 1 : 0));
        parcel.writeFloat(this.f3279d);
        parcel.writeInt(this.f3280e);
        parcel.writeInt(this.f3281f);
        parcel.writeString(this.f3282g);
        if (this.f3283h) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (!this.f3284i) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
    }

    public TileOverlayOptions tileProvider(TileProvider tileProvider) {
        this.f3277b = tileProvider;
        return this;
    }

    public TileOverlayOptions zIndex(float f) {
        this.f3279d = f;
        return this;
    }

    public TileOverlayOptions visible(boolean z) {
        this.f3278c = z;
        return this;
    }

    public TileOverlayOptions memCacheSize(int i) {
        this.f3280e = i;
        return this;
    }

    public TileOverlayOptions diskCacheSize(int i) {
        this.f3281f = i * 1024;
        return this;
    }

    public TileOverlayOptions diskCacheDir(String str) {
        this.f3282g = str;
        return this;
    }

    public TileOverlayOptions memoryCacheEnabled(boolean z) {
        this.f3283h = z;
        return this;
    }

    public TileOverlayOptions diskCacheEnabled(boolean z) {
        this.f3284i = z;
        return this;
    }

    public TileProvider getTileProvider() {
        return this.f3277b;
    }

    public float getZIndex() {
        return this.f3279d;
    }

    public boolean isVisible() {
        return this.f3278c;
    }

    public int getMemCacheSize() {
        return this.f3280e;
    }

    public int getDiskCacheSize() {
        return this.f3281f;
    }

    public String getDiskCacheDir() {
        return this.f3282g;
    }

    public boolean getMemoryCacheEnabled() {
        return this.f3283h;
    }

    public boolean getDiskCacheEnabled() {
        return this.f3284i;
    }
}
