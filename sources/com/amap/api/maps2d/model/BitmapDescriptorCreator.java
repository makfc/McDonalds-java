package com.amap.api.maps2d.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.maps2d.model.a */
class BitmapDescriptorCreator implements Creator<BitmapDescriptor> {
    BitmapDescriptorCreator() {
    }

    /* renamed from: a */
    public BitmapDescriptor createFromParcel(Parcel parcel) {
        BitmapDescriptor bitmapDescriptor = new BitmapDescriptor(null);
        bitmapDescriptor.f3379d = (Bitmap) parcel.readParcelable(BitmapDescriptor.class.getClassLoader());
        bitmapDescriptor.f3377b = parcel.readInt();
        bitmapDescriptor.f3378c = parcel.readInt();
        return bitmapDescriptor;
    }

    /* renamed from: a */
    public BitmapDescriptor[] newArray(int i) {
        return new BitmapDescriptor[i];
    }
}
