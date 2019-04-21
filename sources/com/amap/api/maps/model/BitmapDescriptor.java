package com.amap.api.maps.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore.util.Util;

public final class BitmapDescriptor implements Parcelable, Cloneable {
    public static final BitmapDescriptorCreator CREATOR = new BitmapDescriptorCreator();
    /* renamed from: a */
    int f3131a = 0;
    /* renamed from: b */
    int f3132b = 0;
    /* renamed from: c */
    Bitmap f3133c;

    BitmapDescriptor(Bitmap bitmap) {
        if (bitmap != null) {
            this.f3131a = bitmap.getWidth();
            this.f3132b = bitmap.getHeight();
            this.f3133c = m4452a(bitmap, Util.m2339a(this.f3131a), Util.m2339a(this.f3132b));
        }
    }

    private BitmapDescriptor(Bitmap bitmap, int i, int i2) {
        this.f3131a = i;
        this.f3132b = i2;
        this.f3133c = bitmap;
    }

    public BitmapDescriptor clone() {
        try {
            return new BitmapDescriptor(Bitmap.createBitmap(this.f3133c), this.f3131a, this.f3132b);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public Bitmap getBitmap() {
        return this.f3133c;
    }

    public int getWidth() {
        return this.f3131a;
    }

    public int getHeight() {
        return this.f3132b;
    }

    /* renamed from: a */
    private Bitmap m4452a(Bitmap bitmap, int i, int i2) {
        return Util.m2346a(bitmap, i, i2);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3133c, i);
        parcel.writeInt(this.f3131a);
        parcel.writeInt(this.f3132b);
    }

    public void recycle() {
        if (this.f3133c != null && !this.f3133c.isRecycled()) {
            this.f3133c.recycle();
            this.f3133c = null;
        }
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (this.f3133c == null || this.f3133c.isRecycled() || obj == null) {
            return z;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return z;
        }
        BitmapDescriptor bitmapDescriptor = (BitmapDescriptor) obj;
        if (bitmapDescriptor.f3133c == null || bitmapDescriptor.f3133c.isRecycled() || this.f3131a != bitmapDescriptor.getWidth() || this.f3132b != bitmapDescriptor.getHeight()) {
            return z;
        }
        try {
            return this.f3133c.sameAs(bitmapDescriptor.f3133c);
        } catch (Throwable th) {
            return z;
        }
    }
}
