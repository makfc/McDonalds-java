package com.amap.api.maps2d.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public final class TileOverlayOptions implements Parcelable {
    public static final TileOverlayOptionsCreator CREATOR = new TileOverlayOptionsCreator();
    /* renamed from: a */
    private final int f3479a;
    /* renamed from: b */
    private TileProvider f3480b;
    /* renamed from: c */
    private boolean f3481c;
    /* renamed from: d */
    private float f3482d;
    /* renamed from: e */
    private int f3483e;
    /* renamed from: f */
    private int f3484f;
    /* renamed from: g */
    private String f3485g;
    /* renamed from: h */
    private boolean f3486h;
    /* renamed from: i */
    private boolean f3487i;

    public TileOverlayOptions() {
        this.f3481c = true;
        this.f3483e = 5120;
        this.f3484f = 20480;
        this.f3485g = null;
        this.f3486h = true;
        this.f3487i = true;
        this.f3479a = 1;
    }

    TileOverlayOptions(int i, IBinder iBinder, boolean z, float f) {
        this.f3481c = true;
        this.f3483e = 5120;
        this.f3484f = 20480;
        this.f3485g = null;
        this.f3486h = true;
        this.f3487i = true;
        this.f3479a = i;
        this.f3481c = z;
        this.f3482d = f;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeInt(this.f3479a);
        parcel.writeValue(this.f3480b);
        parcel.writeByte((byte) (this.f3481c ? 1 : 0));
        parcel.writeFloat(this.f3482d);
        parcel.writeInt(this.f3483e);
        parcel.writeInt(this.f3484f);
        parcel.writeString(this.f3485g);
        if (this.f3486h) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (!this.f3487i) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
    }

    public TileOverlayOptions tileProvider(TileProvider tileProvider) {
        this.f3480b = tileProvider;
        return this;
    }

    public TileOverlayOptions zIndex(float f) {
        this.f3482d = f;
        return this;
    }

    public TileOverlayOptions visible(boolean z) {
        this.f3481c = z;
        return this;
    }

    public TileOverlayOptions memCacheSize(int i) {
        this.f3483e = i;
        return this;
    }

    public TileOverlayOptions diskCacheSize(int i) {
        this.f3484f = i * 1024;
        return this;
    }

    public TileOverlayOptions diskCacheDir(String str) {
        this.f3485g = str;
        return this;
    }

    public TileOverlayOptions memoryCacheEnabled(boolean z) {
        this.f3486h = z;
        return this;
    }

    public TileOverlayOptions diskCacheEnabled(boolean z) {
        this.f3487i = z;
        return this;
    }

    public TileProvider getTileProvider() {
        return this.f3480b;
    }

    public float getZIndex() {
        return this.f3482d;
    }

    public boolean isVisible() {
        return this.f3481c;
    }

    public int getMemCacheSize() {
        return this.f3483e;
    }

    public int getDiskCacheSize() {
        return this.f3484f;
    }

    public String getDiskCacheDir() {
        return this.f3485g;
    }

    public boolean getMemoryCacheEnabled() {
        return this.f3486h;
    }

    public boolean getDiskCacheEnabled() {
        return this.f3487i;
    }
}
