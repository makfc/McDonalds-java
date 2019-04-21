package com.amap.api.maps2d.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore2d.C0955ck;

public final class BitmapDescriptor implements Parcelable, Cloneable {
    /* renamed from: a */
    static final BitmapDescriptorCreator f3376a = new BitmapDescriptorCreator();
    /* renamed from: b */
    int f3377b = 0;
    /* renamed from: c */
    int f3378c = 0;
    /* renamed from: d */
    Bitmap f3379d;

    BitmapDescriptor(Bitmap bitmap) {
        if (bitmap != null) {
            this.f3377b = bitmap.getWidth();
            this.f3378c = bitmap.getHeight();
            this.f3379d = bitmap;
        }
    }

    private BitmapDescriptor(Bitmap bitmap, int i, int i2) {
        this.f3377b = i;
        this.f3378c = i2;
        this.f3379d = bitmap;
    }

    public BitmapDescriptor clone() {
        String str = "clone";
        try {
            return new BitmapDescriptor(Bitmap.createBitmap(this.f3379d), this.f3377b, this.f3378c);
        } catch (Throwable th) {
            C0955ck.m3888a(th, "BitmapDescriptor", str);
            return null;
        }
    }

    public Bitmap getBitmap() {
        return this.f3379d;
    }

    public int getWidth() {
        return this.f3377b;
    }

    public int getHeight() {
        return this.f3378c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3379d, i);
        parcel.writeInt(this.f3377b);
        parcel.writeInt(this.f3378c);
    }

    public void recycle() {
        if (this.f3379d != null && !this.f3379d.isRecycled()) {
            this.f3379d.recycle();
            this.f3379d = null;
        }
    }
}
